/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBanco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfMoneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfPais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Banco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Moneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Pais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaPaisesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comun.bo.ConsultaPadronCuitBO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronOutEntity;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class ComexConsultasBOImpl.
 */
@Component
public class ComexConsultasBOImpl implements ComexConsultasBO {

	/** ComexConsultas WS DAO. */
	@Autowired
	private ComexConsultasDAO comexConsultasDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The consulta padron cuit DAO. */
	@Autowired
	private ConsultaPadronCuitBO consultaPadronCuitBO;

	/** The Constant BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI. */
	/** private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "1"; */
	// En el documento de CNSPADCUIT dice que hay que si es DNI el campo opcion
	// es 1.. ESTA MAL!! ES 3!!
	private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "3";

	/** The Constant LISTA_VACIA. */
	private static final String LISTA_VACIA = "0";
	
	/** The Constant SIN_REGISTROS. */
	private static final String SIN_REGISTROS = "Error respuesta con lista vacia";
	
	/** The Constant PAIS_NO_VALIDO. */
	private static final String PAIS_NO_VALIDO = "no usar";
	
	/** The Constant MONEDAS_INVALIDAS_COD_BCRA. */
	private static final String MONEDAS_INVALIDAS_COD_BCRA = "(UYU|BRL|UYP)";
	
	/** The Constant MONEDAS_INVALIDAS_COD_MONEDA. */
	private static final String MONEDAS_INVALIDAS_COD_MONEDA = "(010|092)";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexConsultasBOImpl.class);

	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.bo.ComexConsultasBO#
	 * consultaPaises()
	 */
	@Override
	public Respuesta<List<ConsultaPaisesOutDTO>> consultaPaises() {
		ConsultaPaisesResponse response;
		try {
			response = comexConsultasDAO.consultaPaises();
			if (StringUtils.isNotBlank(response.getErrorInterface().getValue()) || StringUtils.isNotBlank(response.getErrorSistema().getValue())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		List<ConsultaPaisesOutDTO> consultaPaisesOutDTO = createConsultaPaisesOutDTO(response);
		return respuestaFactory.crearRespuestaOk(consultaPaisesOutDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.bo.ComexConsultasBO#
	 * consultaMonedas()
	 */
	@Override
	public Respuesta<List<ConsultaMonedaOutDTO>> consultaMonedas() {
		ConsultaMonedasResponse response;
		try {
			response = comexConsultasDAO.consultaMonedas();
			if (StringUtils.isNotBlank(response.getErrorInterface().getValue()) || StringUtils.isNotBlank(response.getErrorSistema().getValue())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		List<ConsultaMonedaOutDTO> consultaMonedaOutDTO = createConsultaMonedaOutDTO(response);
		return respuestaFactory.crearRespuestaOk(consultaMonedaOutDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.bo.ComexConsultasBO#
	 * consultaBancos(ar.com.santanderrio.obp.servicios.comex.transfext.dto.
	 * ConsultaBancosInDTO)
	 */
	@Override
	public Respuesta<List<ConsultaBancosOutDTO>> consultaBancos(ConsultaBancosInDTO consultaBancosInDTO) {
		ConsultaBancosResponse response;
		ConsultaBancosInEntity consultaBancosInEntity = new ConsultaBancosInEntity();
		consultaBancosInEntity.setTipoCodigo(consultaBancosInDTO.getTipoCodigo());
		consultaBancosInEntity.setCodigoBancario(consultaBancosInDTO.getCodigoBancario());
		try {
			response = comexConsultasDAO.consultaBancos(consultaBancosInEntity);
			if (StringUtils.isNotBlank(response.getErrorInterface().getValue()) || StringUtils.isNotBlank(response.getErrorSistema().getValue())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} else if (LISTA_VACIA.equals(String.valueOf(response.getCantReg()))) {
				LOGGER.error(SIN_REGISTROS);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		List<ConsultaBancosOutDTO> consultaBancosOutDTO = createConsultaBancosOutDTO(response);
		return respuestaFactory.crearRespuestaOk(consultaBancosOutDTO);
	}

	/**
	 * Pasa los datos del Response a un objeto dto.
	 *
	 * @param response
	 *            the response
	 * @return lista consulta monedas out dto
	 */
	private List<ConsultaBancosOutDTO> createConsultaBancosOutDTO(ConsultaBancosResponse response) {
		List<ConsultaBancosOutDTO> consultaBancosOutDTO = new ArrayList<ConsultaBancosOutDTO>();
		ArrayOfBanco listBancos = response.getRegistros().getValue();
		Iterator<Banco> itBanco = listBancos.getBanco().iterator();
		while (itBanco.hasNext()) {
			Banco banco = itBanco.next();
			ConsultaBancosOutDTO consultaBanco = new ConsultaBancosOutDTO();
			consultaBanco.setNombreBanco(banco.getNombreBanco().getValue());
			consultaBanco.setAbaBanco(banco.getAbaBanco().getValue());
			consultaBanco.setSwiftBanco(banco.getSwiftBanco().getValue());
			consultaBanco.setPaisBanco(banco.getPaisBanco().getValue());
			consultaBanco.setLocalidadBanco(banco.getLocalidadBanco().getValue());
			consultaBanco.setDomicilioBanco(banco.getDomicilioBanco().getValue());
			consultaBancosOutDTO.add(consultaBanco);
		}
		Collections.sort(consultaBancosOutDTO, compararCodSwift);
		return consultaBancosOutDTO;
	}

	/**
	 * Pasa los datos del Response a un objeto dto.
	 *
	 * @param response
	 *            the response
	 * @return lista consulta monedas out dto
	 */
	private List<ConsultaPaisesOutDTO> createConsultaPaisesOutDTO(ConsultaPaisesResponse response) {
		List<ConsultaPaisesOutDTO> consultaPaisesOutDTO = new ArrayList<ConsultaPaisesOutDTO>();
		ArrayOfPais listPaises = response.getRegistros().getValue();
		Iterator<Pais> itPais = listPaises.getPais().iterator();
		while (itPais.hasNext()) {
			Pais pais = itPais.next();
			if (pais.getDescripcionPais().getValue().toLowerCase().contains(PAIS_NO_VALIDO)){
				itPais.remove();
				continue;
			}
			ConsultaPaisesOutDTO consultaPaises = new ConsultaPaisesOutDTO();
			consultaPaises.setCodigoPais(pais.getCodigoPais().getValue());
			consultaPaises.setDescripcionPais(pais.getDescripcionPais().getValue());
			consultaPaisesOutDTO.add(consultaPaises);
		}
		Collections.sort(consultaPaisesOutDTO);
		return consultaPaisesOutDTO;
	}

	/**
	 * Pasa los datos del Response a un objeto dto.
	 *
	 * @param response
	 *            the response
	 * @return lista consulta monedas out dto
	 */
	private List<ConsultaMonedaOutDTO> createConsultaMonedaOutDTO(ConsultaMonedasResponse response) {
		List<ConsultaMonedaOutDTO> consultaMonedaOutDTO = new ArrayList<ConsultaMonedaOutDTO>();
		ArrayOfMoneda listMonedas = response.getRegistros().getValue();
		Iterator<Moneda> itMoneda = listMonedas.getMoneda().iterator();
		while (itMoneda.hasNext()) {
			Moneda moneda = itMoneda.next();
			if (moneda.getCodBCRAMoneda().getValue().matches(MONEDAS_INVALIDAS_COD_BCRA) || moneda.getCodigoMoneda().getValue().matches(MONEDAS_INVALIDAS_COD_MONEDA)){
				itMoneda.remove();
				continue;
			}
			ConsultaMonedaOutDTO consultaMoneda = new ConsultaMonedaOutDTO();
			consultaMoneda.setCodBCRAMoneda(moneda.getCodBCRAMoneda().getValue());
			consultaMoneda.setCodigoMoneda(moneda.getCodigoMoneda().getValue());
			consultaMoneda.setDescripcionMoneda(moneda.getDescripcionMoneda().getValue());
			consultaMonedaOutDTO.add(consultaMoneda);
		}
		return consultaMonedaOutDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.bo.ComexConsultasBO#
	 * getDatosPadronBO(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Respuesta<PadronOutEntity> getDatosPadronBO(Cliente cliente) {
		ConsultaPadronCuitInEntity objConsultaPadronInEntity = new ConsultaPadronCuitInEntity();
		objConsultaPadronInEntity.setCliente(cliente);
		objConsultaPadronInEntity.setOpcion(BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI);
		objConsultaPadronInEntity.setArgumento(cliente.getDni());
		PadronOutEntity cuit;
		try {
		    cuit = this.consultaPadronCuitBO
		            .consultaPadron(objConsultaPadronInEntity);
			if (cuit != null) { 
				return respuestaFactory.crearRespuestaOk(PadronOutEntity.class, cuit);
			} else {
				return respuestaFactory.crearRespuestaWarning("", TipoError.FECHA_NACIMIENTO_NO_COINCIDE, CodigoMensajeConstantes.ERROR_FECHA_NACIMIENTO_NO_COINCIDE);
			}
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.bo.ComexConsultasBO#
	 * getDatosPadronBO()
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobante(ComprobanteComexDTO comprobanteDTO) {

		ComprobanteComexInEntity comprobante = new ComprobanteComexInEntity();
		comprobante.setNombre(comprobanteDTO.getNombre());
		comprobante.setImporte(comprobanteDTO.getImporte());
		comprobante.setMotivo(comprobanteDTO.getMotivo());
		comprobante.setVinculo(comprobanteDTO.getVinculo());
		comprobante.setDomicilio(comprobanteDTO.getDomicilio());
		comprobante.setPais(comprobanteDTO.getPais());
		comprobante.setNombreEmisor(comprobanteDTO.getNombreEmisor());
		comprobante.setCuenta(comprobanteDTO.getCuentaOrigen());
		comprobante.setDescripcionCuenta(comprobanteDTO.getDescripcionCuentaOrigen());
		comprobante.setCuentaDestino(comprobanteDTO.getCuentaDestino());
		comprobante.setCodigoBancario(comprobanteDTO.getCodigoBancario());
		comprobante.setGastosACargo(comprobanteDTO.getGastosACargo());
		comprobante.setCuentaBancoIntermediario(comprobanteDTO.getBancoIntermediario());
		comprobante.setCodigoBancarioIntermediario(comprobanteDTO.getCodigoIntermediario());
		comprobante.setLegales(comprobanteDTO.getLegales());
		comprobante.setNumeroComprobante(comprobanteDTO.getNumeroComprobante());
		comprobante.setConceptoCodigo(comprobanteDTO.getConceptoCodigo());
		comprobante.setMoneda(comprobanteDTO.getMoneda());
		comprobante.setDocumentacion(comprobanteDTO.getNombreDocumentacion());
		comprobante.setFecha(comprobanteDTO.getFechaHora());
		comprobante.setVinculante(BooleanUtils.toString(comprobanteDTO.getVinculante(), "Sí", "No", null));
		try {
			return respuestaFactory.crearRespuestaOk(Reporte.class, comexConsultasDAO.generarComprobante(comprobante));
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO#verificarArchivo(ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO)
	 */
	@Override
	public Respuesta<Boolean> verificarArchivo(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		AdjuntarArchivosInEntity adjuntarArchivosInEntity = createAdjuntarArchivosInEntity(adjuntarArchivosInDto.getNroTransferencia(), adjuntarArchivosInDto.getArchivo());
		return this.comexConsultasDAO.verificarVirusArchivoFs(adjuntarArchivosInEntity);
	}

	



	
	/**
	 * Creates the adjuntar archivos in entity.
	 *
	 * @param nroTransferencia
	 *            the nro transferencia
	 * @param reporteView
	 *            the reporte view
	 * @return the adjuntar archivos in entity
	 */
	private AdjuntarArchivosInEntity createAdjuntarArchivosInEntity(Integer nroTransferencia, ReporteView reporteView) {
		AdjuntarArchivosInEntity adjuntarArchivosInEntity = new AdjuntarArchivosInEntity();
		adjuntarArchivosInEntity.setNroTransferencia(nroTransferencia);
		adjuntarArchivosInEntity.setArchivo(reporteView);
		return adjuntarArchivosInEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO#limpiarPaises()
	 */
	@Override

	public Respuesta<Boolean> limpiarPaises() {
		comexConsultasDAO.limpiarPaises();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO#limpiarMonedas()
	 */
	@Override
	public Respuesta<Boolean> limpiarMonedas() {
		comexConsultasDAO.limpiarMonedas();
		return respuestaFactory.crearRespuestaOk(true);
		
	}

	/** The comparar cod swift. */
	private Comparator<ConsultaBancosOutDTO> compararCodSwift = new Comparator<ConsultaBancosOutDTO>(){
		public int compare(ConsultaBancosOutDTO o1, ConsultaBancosOutDTO o2) {
			boolean esCodSwift = !"".equals(o1.getSwiftBanco());
			String o1Cod;
			String o2Cod;
			if(esCodSwift){
				o1Cod = o1.getSwiftBanco();
				o2Cod = o2.getSwiftBanco();
				if(o1Cod.endsWith("XXX") && !o2Cod.endsWith("XXX")) {
					return -1;
				}
				if(! o1Cod.endsWith("XXX") && o2Cod.endsWith("XXX")) {
					
				}
					return 1;

			}else{/*
	                o1Cod = o1.getAbaBanco().getValue();
	                o2Cod = o2.getAbaBanco().getValue();
	                if(o1Cod.endsWith("XXX") && !o2Cod.endsWith("XXX")) return -1;
	                if(! o1Cod.endsWith("XXX") && o2Cod.endsWith("XXX")) return 1;
	                */
			}
			return 0;
		}
	};

	/**
	 * Gets the cuentas saldo ordenadas BP.
	 *
	 * @param cliente the cliente
	 * @return the cuentas saldo ordenadas BP
	 * @throws BusinessException the business exception
	 * @throws SQLException the SQL exception
	 * @throws ImporteConvertException the importe convert exception
	 */
	@Override
	public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadasBP(Cliente cliente)
			throws BusinessException, SQLException, ImporteConvertException {
		List<Cuenta> cuentasBP = cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente);
		return getCuentasSaldoBP(cuentasBP, cliente);
	}

	/**
	 * Gets the cuentas saldo BP.
	 *
	 * @param cuentasBP the cuentas BP
	 * @param cliente the cliente
	 * @return the cuentas saldo BP
	 * @throws SQLException the SQL exception
	 * @throws BusinessException the business exception
	 * @throws ImporteConvertException the importe convert exception
	 */
	private Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoBP(List<Cuenta> cuentasBP,
			Cliente cliente) throws SQLException, BusinessException, ImporteConvertException {
		List<ItemMensajeRespuesta> itemsMensajesErrores = new ArrayList<ItemMensajeRespuesta>();
		List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
		List<CuentaIntermedioDTO> saldosCuenta = cuentasBancaPrivadaBO.obtenerSaldoServicioIatx(cliente);
		for (Cuenta cuentaBP : cuentasBP) {
			if (CuentasBancaPrivadaUtil.isCuentaPrivada(cuentaBP) && CuentasBancaPrivadaUtil.validaCuentaBPComex(cuentaBP)) {
				ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
				ConsultaSaldoCtasConAperturaOutEntity saldoCuenta = null;
				for (CuentaIntermedioDTO cuentaIntermedioDTO : saldosCuenta) {
					if (cuentaBP.obtenerNroCuentaFormateado().equals(cuentaIntermedioDTO.getNumeroCuenta())) {
						saldoCuenta = cuentaIntermedioDTO.getSaldosServicioIatx();
						resumenDetalleCuenta = CuentasBancaPrivadaUtil.initResumenDetalleCuenta(cuentaBP, saldoCuenta);
					}
				}
				respuestaDetalleCuenta.add(resumenDetalleCuenta);
				resumenDetalleCuenta.setAlias(null);
				resumenDetalleCuenta.setFavorita(Boolean.FALSE);
			}
		}

		Respuesta<List<ResumenDetalleCuenta>> repuesta = new Respuesta<List<ResumenDetalleCuenta>>();
		repuesta.setRespuesta(respuestaDetalleCuenta);
		repuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		repuesta.setRespuestaVacia(false);
		repuesta.setItemMensajeRespuesta(itemsMensajesErrores);
		return repuesta;
	}

}
