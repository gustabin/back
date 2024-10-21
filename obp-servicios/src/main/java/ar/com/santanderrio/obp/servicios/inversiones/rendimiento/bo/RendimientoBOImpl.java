/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.PeriodoRendimientoTenenciaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.ElementoRendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoPorCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRespuestaRendimientoTenencia;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoTenenciaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ResultadoRendimientoTenenciaEntity;

/**
 * The Class RendimientoBOImpl.
 */
@Component("rendimientoTenenciaBO")
public class RendimientoBOImpl extends InversionesAbstractBO implements RendimientoTenenciaBO {

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "RTL";
	
	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BP";
	
	/** The rendimiento tenencia DAO. */
	@Autowired
	private RendimientoTenenciaDAO rendimientoTenenciaDAO;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The Constant PRUEBA. */
	private static final String PRUEBA = "Prueba";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RendimientoBOImpl.class);

	/** The Constant CANAL_RENDIMIENTO_BPRIV. */
	private static final String CANAL_RENDIMIENTO_BPRIV = "18";
	
	/** The Constant CANAL_RENDIMIENTO_RTL. */
	private static final String CANAL_RENDIMIENTO_RTL = "04";
	
	/** The Constant SUBCANAL_RENDIMIENTO_RTL. */
	private static final String SUBCANAL_RENDIMIENTO_RTL = "0099";
	
	/** The Constant LARGO_NRO_CUENTA_DEBITO. */
    private static final int LARGO_NRO_CUENTA = 11;
    
	/** The Constant Largo num cuenta operativa a mostrar en front. */
    private static final int LARGO_NRO_CUENTA_7 = 7;
    
    /** The Constant LARGO_COD_SUCURSAL. */
	private static final int LARGO_COD_SUCURSAL = 4;

	/** The Constant RESPUESTA_PARCIAL1. */
	private static final int RESPUESTA_PARCIAL1 = 1;
	
	/** The Constant RESPUESTA_PARCIAL2. */
	private static final int RESPUESTA_PARCIAL2 = 3;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoTenenciaBO#obtenerRendimientoTenenciaRTL(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<RendimientoDTO> obtenerRendimientoTenenciaRTL(Cliente cliente, String codigoProducto) {
		
		RendimientoConsolidadoRequestEntity request = crearRequestRendimientoConsolidado(cliente, codigoProducto, BANCA_RETAIL);
		
		try {
			RendimientoTenenciaEntity respuesta = consultarServicio(request);
			if(respuesta.getDatos().getResultado().isEmpty()){
				//CLIENTE SIN TENENCIA, SE DEBERAN VER 2 PERIODOS CON GUIONES
				respuesta.getDatos().setResultado(cargarPeriodosVacios());
			}
			RendimientoDTO dtoResponse = entityToDtoRendimiento(respuesta.getDatos().getResultado(), respuesta.getCodigo());
			if(respuesta.getCodigo() == RESPUESTA_PARCIAL1 || respuesta.getCodigo() == RESPUESTA_PARCIAL2){
				return respuestaFactory.crearRespuestaWarning(dtoResponse, null);
			}
			return respuestaFactory.crearRespuestaOk(RendimientoDTO.class, dtoResponse);
		} catch (DAOException e) {
			LOGGER.error("Error consultando rendimiento consolidado. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		
	}

	/**
	 * Cargar periodos vacios.
	 *
	 * @return the list
	 */
	private List<ResultadoRendimientoTenenciaEntity> cargarPeriodosVacios() {
		
		List<ResultadoRendimientoTenenciaEntity> listaPeriodosDefault = new ArrayList<ResultadoRendimientoTenenciaEntity>();
		
		ResultadoRendimientoTenenciaEntity periodo30 = new ResultadoRendimientoTenenciaEntity();
		periodo30.setCodigoPeriodo(PeriodoRendimientoTenenciaEnum.ULT30DIAS.getCodigo());
		listaPeriodosDefault.add(periodo30);
		
		ResultadoRendimientoTenenciaEntity periodoAnioCurso = new ResultadoRendimientoTenenciaEntity();
		periodoAnioCurso.setCodigoPeriodo(PeriodoRendimientoTenenciaEnum.ANIOCURSO.getCodigo());
		listaPeriodosDefault.add(periodoAnioCurso);
		
		return listaPeriodosDefault;
	}

	/**
	 * Entity to dto rendimiento.
	 *
	 * @param listaRendimientos
	 *            the lista rendimientos
	 * @param codigoRespuesta
	 *            the codigo respuesta
	 * @return the rendimiento DTO
	 */
	private RendimientoDTO entityToDtoRendimiento(List<ResultadoRendimientoTenenciaEntity> listaRendimientos, int codigoRespuesta) {
		RendimientoDTO responseDTO = new RendimientoDTO();
		if(RESPUESTA_PARCIAL1 == codigoRespuesta || RESPUESTA_PARCIAL2 == codigoRespuesta){
			responseDTO.setInformacionParcial(true);
		}
		for (ResultadoRendimientoTenenciaEntity rendimiento : listaRendimientos) {
			responseDTO.getRendimientoDolares().add(new ElementoRendimientoDTO(rendimiento.getCodigoPeriodo(), rendimiento.getRendimientoDolares(), rendimiento.getRentabilidadDolares()));
			responseDTO.getRendimientoPesos().add(new ElementoRendimientoDTO(rendimiento.getCodigoPeriodo(), rendimiento.getRendimientoPesos(), rendimiento.getRentabilidadPesos()));
		}
		return responseDTO;
	}

	/**
	 * Crear request rendimiento consolidado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param codigoProducto
	 *            the codigo producto
	 * @param segmento
	 *            the segmento
	 * @return the rendimiento consolidado request entity
	 */
	private RendimientoConsolidadoRequestEntity crearRequestRendimientoConsolidado(Cliente cliente, String codigoProducto, String segmento) {

		RendimientoConsolidadoRequestEntity request = new RendimientoConsolidadoRequestEntity();
		
		request.setDatos(cargarDatosRequestPropiosDelCliente(cliente));
		request.getDatos().setSegmento(segmento);
		request.getDatos().setCodProducto(codigoProducto);
		request.setDato(PRUEBA);
		if(BANCA_RETAIL.equals(segmento)){
			request.setSubCanal(SUBCANAL_RENDIMIENTO_RTL);
			request.setCanal(CANAL_RENDIMIENTO_RTL);
		}else{
			//EL SUB CANAL QUEDA EL QUE SE GENERA AUTOMATICAMENTE QUE ES 00
			request.setCanal(CANAL_RENDIMIENTO_BPRIV);
		}
		
		return request;
	}

	/**
	 * Cargar datos request propios del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos rendimiento consolidado request entity
	 */
	private DatosRendimientoConsolidadoRequestEntity cargarDatosRequestPropiosDelCliente(Cliente cliente) {
		
		DatosRendimientoConsolidadoRequestEntity request = new DatosRendimientoConsolidadoRequestEntity();
		
		request.setDatosServicios(cargarDatosServiciosCliente(cliente));
		request.setNup(cliente.getNup());
		request.setIp(NetworkUtil.getHostAddress());
		request.setUsuario(System.getProperty("user.name"));
		
		return request;
	}

	/**
	 * Cargar datos servicios cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos servicios entity
	 */
	private DatosServiciosEntity cargarDatosServiciosCliente(Cliente cliente) {
		
		DatosServiciosEntity datosServicio = new DatosServiciosEntity();
		datosServicio.setUsuarioPwd(cliente.getPasswordRacf());
		datosServicio.setTipoId(cliente.getTipoDocumento());
		datosServicio.setIdCliente(cliente.getDni());
		datosServicio.setFechaNac(cliente.getFechaNacimiento());
		datosServicio.setUsuarioId(cliente.getUsuarioRacf());
		
		return datosServicio;
	}

	
	 /**
     * Obtener cuentas titulo.
     *
     * @param cuentasBancaPrivada
     *            the cuentas banca privada
     * @return the list
     */
    private List<CuentaBancaPrivada> obtenerCuentasTituloYOperativa(List<CuentaBancaPrivada> cuentasBancaPrivada) {
        List<CuentaBancaPrivada> cuentas = new ArrayList<CuentaBancaPrivada>();
        for (CuentaBancaPrivada cuentaBancaPrivada : cuentasBancaPrivada) {
            if (cuentaBancaPrivada.getCuentaTitulo() != null && cuentaBancaPrivada.getCuentaOperativa() != null) {
            	cuentas.add(cuentaBancaPrivada);
            }
        }
        return cuentas;
    }
	
    
    /**
	 * Entity to dto rendimiento B priv.
	 *
	 * @param listaRendimientos
	 *            the lista rendimientos
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param codigoRespuesta
	 *            the codigo respuesta
	 * @return the rendimiento por cuenta DTO
	 */
    private RendimientoPorCuentaDTO entityToDtoRendimientoBPriv(List<ResultadoRendimientoTenenciaEntity> listaRendimientos, String numeroCuenta, int codigoRespuesta) {
    	
		RendimientoPorCuentaDTO responseDTO = new RendimientoPorCuentaDTO();
		responseDTO.setNroCuenta(numeroCuenta);
		numeroCuenta = StringUtils.right(numeroCuenta, LARGO_NRO_CUENTA_7);
		responseDTO.setNroCuentaFormateado(ISBANStringUtils.formatearNumeroCuenta(numeroCuenta));
		
		if(RESPUESTA_PARCIAL1 == codigoRespuesta || RESPUESTA_PARCIAL2 == codigoRespuesta){
			responseDTO.setInformacionParcial(true);
		}
		for (ResultadoRendimientoTenenciaEntity rendimiento : listaRendimientos) {
			responseDTO.getRendimientoDolares().add(new ElementoRendimientoDTO(rendimiento.getCodigoPeriodo(), rendimiento.getRendimientoDolares(), rendimiento.getRentabilidadDolares()));
			responseDTO.getRendimientoPesos().add(new ElementoRendimientoDTO(rendimiento.getCodigoPeriodo(), rendimiento.getRendimientoPesos(), rendimiento.getRentabilidadPesos()));
		}
		return responseDTO;
	}
    
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoTenenciaBO#obtenerRendimientoTenenciaBPriv(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<RendimientoBPrivDTO> obtenerRendimientoTenenciaBPriv(Cliente cliente, String codigoProducto) {
		
		RendimientoPorCuentaDTO dtoResponse = new RendimientoPorCuentaDTO(); 
		RendimientoBPrivDTO rendimientoBPrivDTO = new RendimientoBPrivDTO();
		RendimientoConsolidadoRequestEntity request = crearRequestRendimientoConsolidado(cliente, codigoProducto, BANCA_PRIVADA);
		RendimientoTenenciaEntity respuesta = new RendimientoTenenciaEntity();

		List<CuentaBancaPrivada> cuentasBancaPrivada = obtenerCuentasTituloYOperativa(cliente.getCuentasBancaPrivada());
		boolean rtaWarning = false;

		for (CuentaBancaPrivada cuenta : cuentasBancaPrivada) {
			setearCuentaTituloAlRequest(request, cuenta);
			CuentaOPEntity cuentaOPEntity = setearCuentaOperativa(cuenta);	
			try {
				respuesta = consultarServicio(request);
				if (respuesta.getDatos().getResultado().isEmpty()) {
					// CLIENTE SIN TENENCIA, SE DEBERAN VER 2 PERIODOS CON GUIONES
					respuesta.getDatos().setResultado(cargarPeriodosVacios());
				}
				if(respuesta.getCodigo() == RESPUESTA_PARCIAL1 || respuesta.getCodigo() == RESPUESTA_PARCIAL2){
					rtaWarning = true;
				}
			} catch (DAOException e) {
				LOGGER.error("Error consultando rendimiento consolidado. ", e);
				respuesta.setDatos(new DatosRespuestaRendimientoTenencia());
				respuesta.getDatos().setResultado(cargarPeriodosVacios());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			dtoResponse = entityToDtoRendimientoBPriv(respuesta.getDatos().getResultado(), cuentaOPEntity.getNroCuentaOP(), respuesta.getCodigo());
			rendimientoBPrivDTO.getRendimientoPorCuenta().add(dtoResponse);
		}
		if(rtaWarning){
			return respuestaFactory.crearRespuestaWarning(rendimientoBPrivDTO, null);
		}
		return respuestaFactory.crearRespuestaOk(RendimientoBPrivDTO.class, rendimientoBPrivDTO);
	 }
		
		
			
	/**
	 * Setear cuenta titulo al request.
	 *
	 * @param request
	 *            the request
	 * @param cuenta
	 *            the cuenta
	 * @return the rendimiento consolidado request entity
	 */
	private RendimientoConsolidadoRequestEntity setearCuentaTituloAlRequest(RendimientoConsolidadoRequestEntity request, CuentaBancaPrivada cuenta) {			
		CuentaOPEntity cuentaTitulo = new CuentaOPEntity();
		cuentaTitulo.setNroCuentaOP(StringUtils.right(cuenta.getCuentaTitulo().getNroCuentaProducto(), LARGO_NRO_CUENTA));
		cuentaTitulo.setSucursal(StringUtils.right(cuenta.getCuentaTitulo().getNroSucursal(), LARGO_COD_SUCURSAL));
		List<CuentaOPEntity> listaCuentas  = new ArrayList<CuentaOPEntity>();
		listaCuentas.add(cuentaTitulo);
		request.getDatos().setListaCuentas(listaCuentas);		
		return request;
	}

	/**
	 * Setear cuenta operativa.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the cuenta OP entity
	 */
	private CuentaOPEntity  setearCuentaOperativa(CuentaBancaPrivada cuenta) {
		CuentaOPEntity cuentaOPEntity = new CuentaOPEntity();
		cuentaOPEntity.setNroCuentaOP(StringUtils.right(cuenta.getCuentaOperativa().getNroCuentaProducto(), LARGO_NRO_CUENTA));		
		cuentaOPEntity.setSucursal(StringUtils.right(cuenta.getCuentaOperativa().getNroSucursal(), LARGO_COD_SUCURSAL));
		return cuentaOPEntity;
	}

	/**
	 * Consultar servicio.
	 *
	 * @param request
	 *            the request
	 * @return the rendimiento tenencia entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private RendimientoTenenciaEntity consultarServicio(RendimientoConsolidadoRequestEntity request) throws DAOException{
		RendimientoTenenciaEntity outEntity = rendimientoTenenciaDAO.obtenerRendimientoConsolidado(request);
		return outEntity;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

}
