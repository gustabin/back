package ar.com.santanderrio.obp.servicios.getnet.bo.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetEmailValidationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.view.ComboView;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.getnet.bo.GetnetBO;
import ar.com.santanderrio.obp.servicios.getnet.dao.GetnetDAO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetConfiguracionOutDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.ActualizacionConsultaMarcaAdhesionFinal;
import ar.com.santanderrio.obp.servicios.getnet.entities.ConsultaSitImpositivaOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.ConsultarInformacionImpositivaEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetInEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.ListaIndicadoresMarcaAdhesion;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetValidationException;
import ar.com.santanderrio.obp.servicios.getnet.view.CuentaView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class GetnetBOImpl.
 */
@Component
public class GetnetBOImpl implements GetnetBO {
	
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GetnetBOImpl.class);
    
	/** The getnet DAO. */
	@Autowired
	GetnetDAO getNetDAO;
	
	/** The selectores BO. */
    @Autowired
    private DatosSelectoresBO selectoresBO;
    
    /** The cuentas BO. */
    @Autowired
    private CuentaBO cuentaBO;
    
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
    
    /** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;
    
    /** The nup DAO. */
	@Autowired
	private NUPDAO nupDAO;
    
    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;
    
	/** The Salto URL. */
	@Value("${GETNET.SALTO.URL}")
	private String getNetSaltoURL;

	/** The Constant GETNET_BOTON_SOLICITUD. */
    public static final String GETNET_BOTON_SOLICITUD = "getnetSolicitud";

    /** The Constant GETNET_BOTON_ACTIVO. */
    public static final String GETNET_BOTON_ACTIVO = "getnetActivo";
    
    /** The Constant GETNET_BOTON_INACTIVO. */
    public static final String GETNET_BOTON_INACTIVO = "getnetInactivo";
    
	/**
     * Devuelve los datos a mostrar en el stack de GetNet
     * Puede ser: solicitud / activo / inactivo
     *
     * @return the getnet configuracion out dto
     */
	@Override
	public Respuesta<GetnetConfiguracionOutDTO> configuracion() {
		Respuesta<GetnetConfiguracionOutDTO> respuesta = new Respuesta<GetnetConfiguracionOutDTO>();
		GetnetConfiguracionOutDTO getNetDTO = new GetnetConfiguracionOutDTO();
		String estado = sesionParametros.getEstadoGetNet();
		
		if (GETNET_BOTON_SOLICITUD.equalsIgnoreCase(estado)) {
			CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
			List<Cuenta> cuentasSesion = sesionParametros.getCuentasHabilitadasGetnet();
			if (StringUtils.isNotBlank(credencialesMya.getCodigoArea()) && StringUtils.isNotBlank(credencialesMya.getCelular())) {
		        getNetDTO.setCodigoArea(credencialesMya.getCodigoArea());
		        getNetDTO.setCelular(credencialesMya.getCelular());
		    }
			
			getNetDTO.setEmail(credencialesMya.getEmail());
			List<CuentaView> cuentas = new ArrayList<CuentaView>();
			List<Opcion> actividades = selectoresBO.obtenerActividadesGetnet();
			List<Opcion> ingresos = selectoresBO.obtenerIngresosGetnet();
			String legal = "";
			try {
				cuentas = obtenerCuentasView(cuentasSesion);
				legal = legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_GETNET_SOLICITUD_TERMINOS_Y_CONDICIONES);
				sesionParametros.setLegalTycGetnet(legal);
			} catch (BusinessException e) {
				LOGGER.error("Error al actualizar el saldo de las cuentas.", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			} catch (DAOException e) {
				LOGGER.error("Error al obtener legal", e);
			}
			getNetDTO.setCuentas(cuentas);
			getNetDTO.setLegal(legal);
			getNetDTO.setActividades(obtenerComboView(actividades));
			getNetDTO.setIngresos(obtenerComboView(ingresos));
		} else if (GETNET_BOTON_ACTIVO.equalsIgnoreCase(estado)) {
			getNetDTO.setSaltoURL(getNetSaltoURL);
		} else if(GETNET_BOTON_INACTIVO.equalsIgnoreCase(estado)) {
			String email = sesionParametros.getEmailGetNet();
			getNetDTO.setLegal(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.SOLICITUD_GETNET_INACTIVO));
			getNetDTO.setEmail(email);
		}
		respuesta.setRespuesta(getNetDTO);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		return respuesta;
	}
	
	private List<ComboView> obtenerComboView(List<Opcion> listaOpciones) {
    	List<ComboView> retorno = new ArrayList<ComboView>();
    	for(Opcion o : listaOpciones) {
    		retorno.add(new ComboView(o.getOpcion(), o.getId()));
    	}
    	return retorno;
    }
	
	private List<CuentaView> obtenerCuentasView(List<Cuenta> cuentasSesion) throws BusinessException {
		List<CuentaView> cuentasView = new ArrayList<CuentaView>();
		List<ResumenDetalleCuenta> cuentas = obtenerSaldoCuentas(cuentasSesion);
		int id = 0;
		for (ResumenDetalleCuenta cuenta : cuentas) {
			boolean isSaldoPesosNegativo = false;
			int tipoCuenta = Integer.valueOf(cuenta.getTipoCuenta());
			CuentaView cuentaView = new CuentaView();
			cuentaView.setId(id++);
			cuentaView.setAlias(cuenta.getAlias());
			cuentaView.setCbu(cuenta.getCbu());
			cuentaView.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(tipoCuenta).getDescripcionConMoneda());
			cuentaView.setNumero(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal())
					.concat(ISBANStringUtils.GUION_STRING)
					.concat(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto())));
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(
						ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(cuenta.getSaldoPesos())));
				if (cuenta.getSaldoDolares().equals(BigDecimal.ZERO)) {
					cuentaView.setSaldoDolares(ISBANStringUtils
							.formatearConComaYDosDecimales(String.valueOf(cuenta.getSaldoCajaAhorroDolares())));
				} else {
					cuentaView.setSaldoDolares(ISBANStringUtils
							.formatearConComaYDosDecimales(String.valueOf(cuenta.getSaldoDolares())));
				}
				cuentaView.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
				cuentasView.add(cuentaView);
			} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_PESOS.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(
						ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(cuenta.getSaldoPesos())));
				cuentasView.add(cuentaView);
			}
			if (cuentaView.getSaldoPesos() != null && cuenta.getSaldoPesos().signum() < 0) {
				cuentaView.setIsSaldoPesosNegativo(!isSaldoPesosNegativo);
			} else {
				cuentaView.setIsSaldoPesosNegativo(isSaldoPesosNegativo);
			}
		}
		return cuentasView;
	}
	
	private List<ResumenDetalleCuenta> obtenerSaldoCuentas(List<Cuenta> cuentasSesion) throws BusinessException {
		List<ResumenDetalleCuenta> cuentas = cuentaBO.getCuentasSaldo(sesionCliente.getCliente(), cuentasSesion).getRespuesta();
		if (cuentas == null || cuentas.isEmpty()) {
			throw new BusinessException();
		}
		return cuentas;
	}
	
	/**
     * Confirma la adhesion a GetNet
     * @param
     * 			the dto
     * @return the getnet adhesion out view
     */
    @Override
	public Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionDTO dto) {
		ConsultaSitImpositivaOutEntity consultaSitImpositivaOutEntity = new ConsultaSitImpositivaOutEntity();
		ActualizacionConsultaMarcaAdhesionFinal actualizacionConsultaMarcaAdhesionFinal = new ActualizacionConsultaMarcaAdhesionFinal();
		Cliente cliente = sesionCliente.getCliente();
		GetnetAdhesionOutView getnetOutView = new GetnetAdhesionOutView();
    	GetnetInEntity inEntity = new GetnetInEntity(dto);
    	String legal = "";
    	
		try {
			if (sesionParametros.getContadorAdhesionGetnet() == null) {
	            sesionParametros.setContadorAdhesionGetnet(new ContadorIntentos(3));
	        }
	        if (sesionParametros.getContadorAdhesionGetnet().permiteReintentar()) {
	        	
				// CNSDOCXNUP
				if (StringUtils.isBlank(cliente.getNroDocCnsDocXNup())) {
	                NupDTO nupDTO = nupDAO.obtenerNUP(cliente);
	                String nroDoc = nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
	                sesionCliente.getCliente().setNroDocCnsDocXNup(nroDoc);
	                inEntity.getInformacionPersonal().setCuit(nroDoc);
	            } else {
	            	inEntity.getInformacionPersonal().setCuit(cliente.getNroDocCnsDocXNup());
	            }
				
				// TyC
				if (StringUtils.isNotBlank(sesionParametros.getLegalTycGetnet())) {
					legal = sesionParametros.getLegalTycGetnet();
				} else {
					legal = legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_GETNET_SOLICITUD_TERMINOS_Y_CONDICIONES);
				}
				inEntity.setTyc(legal);
	
				// CNSINFIMPO
				if (sesionParametros.getCNSINFIMPO_categorizacionIibb() != null 
						&& sesionParametros.getCNSINFIMPO_categorizacionIva() != null 
						&& sesionParametros.getCNSINFIMPO_numeroIibb() != null) {
					ConsultarInformacionImpositivaEntity consultarInformacionImpositivaEntity = new ConsultarInformacionImpositivaEntity();
					consultarInformacionImpositivaEntity.setRespAnteIngBrutos(sesionParametros.getCNSINFIMPO_categorizacionIibb());
					consultarInformacionImpositivaEntity.setRespAnteIVA(sesionParametros.getCNSINFIMPO_categorizacionIva());
					consultarInformacionImpositivaEntity.setNumIngBrutos(sesionParametros.getCNSINFIMPO_numeroIibb());
					consultaSitImpositivaOutEntity.getListaInfoImpositiva().add(consultarInformacionImpositivaEntity);
				} else {
					consultaSitImpositivaOutEntity = getNetDAO.consultarSitImpositiva(cliente);
					sesionParametros.setCNSINFIMPO_categorizacionIibb(consultaSitImpositivaOutEntity.getListaInfoImpositiva().get(0).getRespAnteIngBrutos().trim());
					sesionParametros.setCNSINFIMPO_categorizacionIva(consultaSitImpositivaOutEntity.getListaInfoImpositiva().get(0).getRespAnteIVA().trim());
					sesionParametros.setCNSINFIMPO_numeroIibb(consultaSitImpositivaOutEntity.getListaInfoImpositiva().get(0).getNumIngBrutos().trim());
				}
				
				// ACTDHECNL - Consulta
				if (sesionParametros.getACTADHECNL_isExpuestoPoliticamente() != null) {
					ListaIndicadoresMarcaAdhesion listaIndicadoresMarcaAdhesion = new ListaIndicadoresMarcaAdhesion();
					if (sesionParametros.getACTADHECNL_isExpuestoPoliticamente()) {
						listaIndicadoresMarcaAdhesion.setIndicador("FUN");
					} 
					actualizacionConsultaMarcaAdhesionFinal.getListaIndicadores().add(listaIndicadoresMarcaAdhesion);
				} else {
					sesionParametros.setACTADHECNL_isExpuestoPoliticamente(false);
					actualizacionConsultaMarcaAdhesionFinal = getNetDAO.ActualizacionConsultaMarcaAdhesion(cliente, true);
					for (ListaIndicadoresMarcaAdhesion listaIndicadoresMarcaAdhesion : actualizacionConsultaMarcaAdhesionFinal.getListaIndicadores()) {
						if (listaIndicadoresMarcaAdhesion.getIndicador() == "FUN") {
							sesionParametros.setACTADHECNL_isExpuestoPoliticamente(true);
						}
					}
				}
				inEntity.cargarDatosIdeClienteConSaldo(cliente);
				inEntity.cargarDatosCnsPersFis(sesionParametros.getDatosSolicitante());
				inEntity.cargarSituacionImpositivaYPolitica(consultaSitImpositivaOutEntity, 
				sesionParametros.getACTADHECNL_isExpuestoPoliticamente());
				
				// Servicio CNSCTADATO - Fecha de apertura de cuenta
				Cuenta cuenta = sesionCliente.getCliente().getCuenta(inEntity.getCbu());
				ConsultaDetalleCuentaOutEntity detalleCuenta = cuentaBO.obtenerDetalleCuenta(cuenta);
				inEntity.setFechaAperturaCuenta(detalleCuenta.getFechaApertura());
				
				// API GetNet
				getNetDAO.postPersons(inEntity, dto.getNumeroCuenta());
				
				// ACTDHECNL - Alta
				actualizacionConsultaMarcaAdhesionFinal = getNetDAO.ActualizacionConsultaMarcaAdhesion(cliente, false);
				getnetOutView.setComprobante(actualizacionConsultaMarcaAdhesionFinal.getComprobante());
				getnetOutView.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
				getnetOutView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_GETNET_OK).getMensaje());
				// Guardo el comprobante en el DTO para setearlo en sesion
//				dto.setNumeroComprobante(getnetOutView.getComprobante());
	        } else {
	        	sesionParametros.setContadorAdhesionGetnet(null);
	        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS, 
	        			CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
	        }
		} catch (TimeOutException e) {
			LOGGER.error(e.getMessage());
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_GETNET_ERROR_DAO);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS, 
	        			CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_COD_RETORNO, 
						CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage());
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, 
					CodigoMensajeConstantes.CODIGO_GETNET_ERROR_DAO);
		} catch (ISBANRuntimeException e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS, 
	        			CodigoMensajeConstantes.GETNET_ERROR_EMAIL_EXISTENTE);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_EMAIL_EXISTENTE, 
						CodigoMensajeConstantes.GETNET_ERROR_EMAIL_EXISTENTE);
			}
		} catch (GetnetValidationException e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS,
						CodigoMensajeConstantes.GETNET_VALIDATION_ERROR);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_VALIDATION,
						CodigoMensajeConstantes.GETNET_VALIDATION_ERROR);
			}
		} catch (GetnetEmailValidationException e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS,
						CodigoMensajeConstantes.GETNET_EMAIL_VALIDATION_ERROR);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_EMAIL_VALIDATION,
						CodigoMensajeConstantes.GETNET_EMAIL_VALIDATION_ERROR);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS, 
	        			CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_COD_RETORNO, 
						CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			if (sesionParametros.getContadorAdhesionGetnet().getContador() >= 3) {
				sesionParametros.setContadorAdhesionGetnet(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_SUPERA_REINTENTOS, 
	        			CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GETNET_COD_RETORNO, 
						CodigoMensajeConstantes.CODIGO_GETNET_ERROR_COD_RETORNO);
			}
		}
		return respuestaFactory.crearRespuestaOk(GetnetAdhesionOutView.class, getnetOutView);
	}
    
    @Override
    public Respuesta<Reporte> descargaComprobanteAdhesion() {
    	Reporte reporte = null;
    	GetnetAdhesionDTO comprobante = sesionParametros.getComprobanteGetnetDTO();
        try {
            reporte = getNetDAO.descargaComprobanteAdhesion(comprobante);
        } catch (ISBANRuntimeException e) {
            return respuestaFactory.crearRespuestaError(null);
        }
        return respuestaFactory.crearRespuestaOk(reporte);
    }

    
}
