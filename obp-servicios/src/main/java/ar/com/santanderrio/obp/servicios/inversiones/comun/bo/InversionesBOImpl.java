/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApiConstants;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsSummary;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsSummaryResponse;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.PerfilInversorEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoOperacionInversionesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoBPrivEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciaConsolidadaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciasPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TotalesTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaTenenciaExperesada;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaCuentaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaExcel;
import ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel.InfoTenenciaConsolidadaGeneralExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;

/**
 * The Class InversionesBOImpl.
 */
@Component("inversionesBO")
public class InversionesBOImpl extends InversionesAbstractBO implements InversionesBO {

	/** Inversion DAO. */
	@Autowired
	private InversionDAO inversionDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The tenencia valuada DAO. */
	@Autowired
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The licitaciones dao. */
	@Autowired
	private TitulosDAO titulosDAO;

	@Autowired
	private ReporteDAO reporteDAO;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	@Autowired
	private FundsApi fundsApi;

	/** The Constant LARGO_NUP. 
	private static final int LARGO_NUP = 8;
	*/
	/** The fecha ejecucion formatter. 
	private final SimpleDateFormat fechaEjecucionFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	*/
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionesAbstractBO.class);

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant CERO. */
	private static final String CERO_CON_DECIMALES = "0.00";

	/** The Constant CERO. */
	private static final String CODIGO_RETORNO_OK = "0";

	/** The Constant UNO. */
	private static final String CODIGO_RETORNO_UNO = "1";

	/** The Constant DOS. */
	private static final String ERROR_TOTAL = "2";

	/** The Constant LARGO_NUMERO_CUENTA. */
	private static final int LARGO_NUMERO_CUENTA = 8;

	/** The Constant LARGO_NUMERO_CUENTA. */
	private static final int LARGO_NUMERO_CUENTA_OP_REQUEST_TENENCIA_CONSOLIDADA = 9;

	/** The Constant LARGO_CUENTA_TITULOS. */
	private static final int LARGO_CUENTA_TITULOS = 8;

	/** The Constant SI. */
	private static final String SI = "S";

	/** The Constant NO. */
	private static final String NO = "N";

	/** The Constant MONEDA_DOLARES. */
	private static final String MONEDA_DOLARES = "USD";

	/** The Constant MONEDA_PESOS. */
	private static final String MONEDA_PESOS = "ARS";

	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The Constant GUION_MEDIO. 
	private static final String TIPO_OPERACION_TARJETA_HOME = "TAR";
	*/
	/** Constante Canal BP */
	private String CanalBP = "79";

	/** Constante Subancal BP */
	private String SubCanalBP = "0000";

	/** Constante Canal RTL */
	private String CanalRtl = "04";

	/** Constante SubCanal RTL */
	private String SubCanalRtl = "0099";

	/** Constante Banca RTL */
	private String bancaRetail = "RTL";

	/** Constante Banca RTL */
	private String bancaPrivada = "BP";

	/** Constante FCI */
	private String productoFondos = "FCI";

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * consultarPerfilInversor(java.lang.String)
	 */
	@Override
	public Respuesta<PerfilInversorResponse> consultarPerfilInversor(boolean esBancaPrivada) {
		PerfilInversorRequestEntity request = new PerfilInversorRequestEntity();
		request.getDatos().setUsuario(sesionCliente.getCliente().getUsuarioRacf());
		request.getDatos().setNup(sesionCliente.getCliente().getNup());
		request.getDatos().setIp(sesionCliente.getIpCliente());

		CabeceraOrdenesTitulosEntity encabezado = new CabeceraOrdenesTitulosEntity();
		encabezado = CabeceraOrdenesTitulosEntity.generarCabeceraRequest(sesionCliente.getCliente());
		if (esBancaPrivada) {
			encabezado.setCanalTipo(CanalBP);
			encabezado.setSubCanalTipo(SubCanalBP);
		} else {
			encabezado.setCanalTipo(CanalRtl);
			encabezado.setSubCanalTipo(SubCanalRtl);
		}
		request.getDatos().setEncabezado(encabezado);
		PerfilInversorResponse response = new PerfilInversorResponse();
		try {
			response = inversionDAO.consultaPerfilInversor(request);

			if (response.getCodigo() != 0) {
				return obtenerRespuestaError(response);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar perfil de inversor", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		return respuestaFactory.crearRespuestaOk(PerfilInversorResponse.class, response);

	}

	/**
	 * Obtener respuesta error.
	 *
	 * @param response the response
	 * @return the respuesta
	 */
	private Respuesta<PerfilInversorResponse> obtenerRespuestaError(PerfilInversorResponse response) {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

	}

	/**
	 * Obtener descripcion larga segun perfil.
	 *
	 * @param idPerfil the id perfil
	 * @return the string
	 */
	@Override
	public String obtenerDescripcionLargaSegunPerfil(String idPerfil) {
		String codigoMensaje = null;
		if (PerfilInversorEnum.AGRESIVO.getIdPerfil().equals(idPerfil)) {
			codigoMensaje = CodigoMensajeConstantes.PERFIL_INVERSOR_AGRESIVO;
		} else if (PerfilInversorEnum.MODERADO.getIdPerfil().equals(idPerfil)) {
			codigoMensaje = CodigoMensajeConstantes.PERFIL_INVERSOR_MODERADO;
		} else if (PerfilInversorEnum.CONSERVADOR.getIdPerfil().equals(idPerfil)) {
			codigoMensaje = CodigoMensajeConstantes.PERFIL_INVERSOR_CONSERVADOR;
		} else {
			codigoMensaje = CodigoMensajeConstantes.PERFIL_INVERSOR_NO_PERFILADO;
		}
		return mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<InicioFondoDTO> inicioInversiones(Cliente cliente, String tipoDeOperacion) {

		String mensajeSinCuentas = null;

		boolean errorLoadAtits = false;

		cargarIntervinientes(cliente.getCuentasRetail());

		if (cliente.getCuentasPrivadas().size() > 0) {
			cargarIntervinientes(cliente.getCuentasPrivadas());
			try {
				vincularCuentasBriv(cliente);
			} catch (BusinessException e) {
				errorLoadAtits = true;
			}
		}
		InicioFondoDTO inicioFondoDTO;
		LOGGER.info("Obteniendo la lista de cuentas de banca privada para el nup : " + cliente.getNup());
		if (TipoOperacionInversionesEnum.PLAZO_FIJO.getCodigoProducto().equals(tipoDeOperacion)) {
			inicioFondoDTO = generarCuentasDTOPlazosFijos(cliente);
		} else {
			inicioFondoDTO = generarCuentasDTO(cliente);
		}
		boolean sinCtas=false;
		if (tipoDeOperacion.equals(TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto())) {
			if ((cliente.getCuentasPrivadas().size() == 0) && (cliente.getCuentasRetail().size() == 0)) {
				sinCtas=true;
			}
		} else {
			if ((cliente.getCuentasPrivadas().size() == 0) && (cliente.getCuentasRetail().size() == 0) && (cliente.getCuentasTitRtlRepatriacion().size()==0)) {
				sinCtas=true;
			}
		}
		if (sinCtas) {
			mensajeSinCuentas = CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS;
		} else {
			if (TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto().equals(tipoDeOperacion)
					&& !establecerCuentasParaLicitar(inicioFondoDTO, cliente)) {
				inicioFondoDTO.setErrorCuentasBloqueadas(true);
			}
		}
		if (cuentaBO.hasCuentasMonetariasActivas(cliente) || CuentasBancaPrivadaUtil.cuentasBPMonetariasActivas(cliente)) {
			inicioFondoDTO.setTieneCuentasMonetarias(SI);
		} else {
			inicioFondoDTO.setTieneCuentasMonetarias(NO);
			mensajeSinCuentas = CodigoMensajeConstantes.ERROR_SIN_CUENTA_OPERATIVA;
		}
		if (mensajeSinCuentas != null) {
			inicioFondoDTO.setMensajeErrorSinCuentas(mensajeSinCuentas);
		}
		if (errorLoadAtits) {
			inicioFondoDTO.setErrorCuentasBPriv(true);
		}
		
		inicioFondoDTO = validaFlagENRI(inicioFondoDTO);
		
		return respuestaFactory.crearRespuestaOk(InicioFondoDTO.class, inicioFondoDTO);
	}

	private InicioFondoDTO validaFlagENRI(InicioFondoDTO inicioFondoDTO) {
		boolean flagEnri = inversionWSHelper.enriFlag();
		inicioFondoDTO.setNuevaApertura(flagEnri);
		return inicioFondoDTO;
	}

	/**
	 * Setea en cada una de las cuentas recibidas si pueden licitar o no, devuelve
	 * false si hubo error.
	 *
	 * @param inicioFondoDTO the inicio fondo DTO
	 * @param cliente        the cliente
	 * @return true, if successful
	 */
	private boolean establecerCuentasParaLicitar(InicioFondoDTO inicioFondoDTO, Cliente cliente) {
		ObtenerCuentasTitulosResponse cuentasTitulo = null;
		try {
			cuentasTitulo = titulosDAO.obtenerCuentasTitulos(crearRequestNuevaLicitacion(cliente));
		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			return false;
		} catch (DAOException e) {
			LOGGER.error("Error en BO obteniendo cuentas titulos habilitadas para licitar. ", e.getMessage(), e);
			return false;
		}
		marcarCuentas(inicioFondoDTO.getCuentasBancaPersonal(), cuentasTitulo.getDatos());
		marcarCuentas(inicioFondoDTO.getCuentasBancaPrivada(), cuentasTitulo.getDatos());

		return true;
	}

	/**
	 * Recibe las cuentas titulos totales y las marca como habilitadas o no para
	 * licitar segun corresponda.
	 *
	 * @param cuentasTitTotales the cuentas tit totales
	 * @param datosCuentasTit   the datos cuentas tit
	 */
	private void marcarCuentas(List<CuentaTituloDTO> cuentasTitTotales,
			List<DatosObtenerCuentasTitulosResponse> datosCuentasTit) {
		// RECORRO LAS CTATIT DEVUELTAS DEL WS
		for (DatosObtenerCuentasTitulosResponse informacionCuentaTitulo : datosCuentasTit) {
			// RECORRO LAS TOTALES DEL CLIENTE
			for (CuentaTituloDTO cuentaTituloDto : cuentasTitTotales) {
				String cuentaComparar = StringUtils.right(cuentaTituloDto.getNroCuenta().replace("/", ""),
						LARGO_NUMERO_CUENTA);
				// COMPARO AMBAS CUENTAS
				if (cuentaComparar.equals(String.valueOf(StringUtils.leftPad(
						String.valueOf(informacionCuentaTitulo.getCuentaTitulos()), LARGO_CUENTA_TITULOS, "0")))) {
					// PREGUNTO SI ESTA HABILITADA PARA LICITAR Y SETEO LO QUE
					// CORRESPONDA
					if (CERO.equals(informacionCuentaTitulo.getStatusCv())) {
						cuentaTituloDto.setPuedeLicitar(SI);
					} else {
						cuentaTituloDto.setPuedeLicitar(NO);
					}
				}
			}
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * obtenerTotalesTenencia(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<TotalesTenenciaDTO> obtenerTotalesTenencia(Cliente cliente, String tipoDeOperacion,
			TipoBancaEnum banca) {
		TenenciaValuadaCarteraRequestEntity requestTenencia = generarRequestTenencia(cliente, tipoDeOperacion, banca);
		try {
			requestTenencia.getDatos().setDatosServicios(createDatosServicio(cliente));
			CarteraTenenciaValuadaEntity tenenciaEntity = createTenenciaEntity(cliente, requestTenencia);
			TotalesTenenciaDTO tenenciaDTO = tenenciaEntityToDTO(tenenciaEntity);

			boolean esParcialRTL = esErrorParcial(tenenciaEntity, "RTL");
			boolean esParcialBPRIV = esErrorParcial(tenenciaEntity, "BP");

			tenenciaDTO.setEsParcialBPRIV(esParcialBPRIV);
			tenenciaDTO.setEsParcialRTL(esParcialRTL);

			if (esParcialRTL || esParcialBPRIV) {
				return respuestaFactory.crearRespuestaWarning(tenenciaDTO, "",
						TipoError.WARNING_PARCIAL_INVERSIONES_CONSOLIDADO, "");
			}

			return respuestaFactory.crearRespuestaOk(TotalesTenenciaDTO.class, tenenciaDTO);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar la tenencia valuada. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (BusinessException e) {
			LOGGER.error("Error al consultar la tenencia valuada. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Es error parcial.
	 *
	 * @param tenenciaEntity the tenencia entity
	 * @param tipobanca      the tipobanca
	 * @return true, if successful
	 */
	private boolean esErrorParcial(CarteraTenenciaValuadaEntity tenenciaEntity, String tipobanca) {
		boolean esParcial = false;
		for (TenenciaValuadaCarteraListaResultadosEntity tenenciaValuadaCarteraListaResultadosEntity : tenenciaEntity
				.getDatos().getListaResultadoPorProducto()) {
			if (tenenciaValuadaCarteraListaResultadosEntity.getSegmento().equals(tipobanca)) {
				if (tenenciaValuadaCarteraListaResultadosEntity.getResultado() != 0) {
					esParcial = true;
					return esParcial;
				}
			}

		}
		return esParcial;
	}

	/**
	 * Tenencia entity to DTO.
	 *
	 * @param tenenciaEntity the tenencia entity
	 * @return the totales tenencia DTO
	 * @throws BusinessException the business exception
	 */
	private TotalesTenenciaDTO tenenciaEntityToDTO(CarteraTenenciaValuadaEntity tenenciaEntity)
			throws BusinessException {
		if (tenenciaEntity == null || tenenciaEntity.getDatos() == null
				|| tenenciaEntity.getDatos().getTenenciaTotalCarteraRTL() == null
				|| tenenciaEntity.getDatos().getTenenciaTotalCarteraBP() == null) {
			throw new BusinessException();
		}
		TotalesTenenciaDTO totalesDTO = new TotalesTenenciaDTO();

		totalesDTO.setTenenciaBPersPesos(ISBANStringUtils.formatearConComaYVariosDecimales2(
				tenenciaEntity.getDatos().getTenenciaTotalCarteraRTL().getPesos(), 2));
		totalesDTO.setTenenciaBPersDolares(ISBANStringUtils.formatearConComaYVariosDecimales2(
				tenenciaEntity.getDatos().getTenenciaTotalCarteraRTL().getDolares(), 2));
		totalesDTO.setTenenciaBPrivPesos(ISBANStringUtils.formatearConComaYVariosDecimales2(
				tenenciaEntity.getDatos().getTenenciaTotalCarteraBP().getPesos(), 2));
		totalesDTO.setTenenciaBPrivDolares(ISBANStringUtils.formatearConComaYVariosDecimales2(
				tenenciaEntity.getDatos().getTenenciaTotalCarteraBP().getDolares(), 2));

		return totalesDTO;
	}

	/**
	 * Si se llama a generar request sin la banca, se asume que se solicitan las dos
	 * banca
	 * 
	 * @param cliente
	 * @param tipoDeOperacion
	 * @return
	 
	private TenenciaValuadaCarteraRequestEntity generarRequestTenencia(Cliente cliente, String tipoDeOperacion) {
		return generarRequestTenencia(cliente, tipoDeOperacion, TipoBancaEnum.AMBAS_BANCAS);
	}*/

	/**
	 * Genera el request para onsultar las tenencias
	 * 
	 * @param cliente         el cliente
	 * @param tipoDeOperacion
	 * @param banca
	 * @return
	 */
	private TenenciaValuadaCarteraRequestEntity generarRequestTenencia(Cliente cliente, String tipoDeOperacion,
			TipoBancaEnum banca) {
		TenenciaValuadaCarteraRequestEntity request = new TenenciaValuadaCarteraRequestEntity();
		DatosTenenciaValuadaCarteraRequest datosRequest = new DatosTenenciaValuadaCarteraRequest();
		request.setDatos(datosRequest);
		TipoOperacionInversionesEnum operacion = TipoOperacionInversionesEnum.fromCodigoProducto(tipoDeOperacion);
		List<CuentaOPEntity> listaCuentasRTL = new ArrayList<CuentaOPEntity>();
		List<CuentaOPEntity> listaCuentasBP = new ArrayList<CuentaOPEntity>();
		if (operacion != null) {
			datosRequest.setCodigoProducto(operacion.getCodigoProducto());
		}
		if (TipoBancaEnum.BANCA_PERSONAL.equals(banca) || TipoBancaEnum.AMBAS_BANCAS.equals(banca)) {
			if (cliente.getCuentasRetail() != null) {
				List<Cuenta> cuentasRTL = cliente.getCuentasRetail();
				for (Cuenta cuentaRTL : cuentasRTL) {
					CuentaOPEntity cuenta = new CuentaOPEntity();
					cuenta.setNroCuentaOP(cuentaRTL.getNroCuentaProducto());
					cuenta.setSucursal(cuentaRTL.getNroSucursal());
					listaCuentasRTL.add(cuenta);
				}
			}
			
			List<Cuenta> cuentasRTLRep = cliente.getCuentasTitRtlRepatriacion();
			for (Cuenta ctaRepRtl: cuentasRTLRep) {
				CuentaOPEntity cuenta = new CuentaOPEntity();
				cuenta.setNroCuentaOP(ctaRepRtl.getNroCuentaProducto());
				cuenta.setSucursal(ctaRepRtl.getNroSucursal());
				listaCuentasRTL.add(cuenta);
			}
			
		}
		if (TipoBancaEnum.BANCA_PRIVADA.equals(banca) || TipoBancaEnum.AMBAS_BANCAS.equals(banca)) {
			if (cliente.getCuentasBancaPrivada() != null) {
				List<CuentaBancaPrivada> cuentasBP = cliente.getCuentasBancaPrivada();
				for (CuentaBancaPrivada cuentaBP : cuentasBP) {
					CuentaOPEntity cuenta = new CuentaOPEntity();
					BigDecimal nro = new BigDecimal(cuentaBP.getCuentaOperativa().getNroCuentaProducto());
//					String cuentaFormat = "7" + llenarConCerosIzqOTruncar(nro.toString(), 9);
					String cuentaFormat =  Integer.parseInt(cuentaBP.getCuentaOperativa().getProductoAltair()) + llenarConCerosIzqOTruncar(nro.toString(), 9);
					cuenta.setNroCuentaOP(cuentaFormat);
					cuenta.setSucursal(cuentaBP.getCuentaOperativa().getNroSucursal());
					listaCuentasBP.add(cuenta);
				}
			}
		}
		datosRequest.setNup(cliente.getNup());
		datosRequest.setListaCuentasBP(listaCuentasBP);
		datosRequest.setListaCuentasRTL(listaCuentasRTL);
		datosRequest.setIp(NetworkUtil.getHostAddress());
		datosRequest.setUsuario(System.getProperty("user.name"));
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.
	 * InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * obtenerTenenciaConsolidadaPorProducto(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto(Cliente cliente) {
		List<CuentaTituloDTO> cuentasDTO = new ArrayList<CuentaTituloDTO>();
		InicioFondoDTO inicioFondoDTO = generarCuentasDTO(cliente);
		inicioFondoDTO=inyectarCuentasTitRep(inicioFondoDTO, cliente);
		String segmento = Segmento.BANCA_PERSONAL.getCodigo();
		cuentasDTO = generarCuentasRTLTenencias(inicioFondoDTO.getCuentasBancaPersonal());
		CuentaProductoOnlineEntity tenenciaConsolidada = new CuentaProductoOnlineEntity();
		try {
			tenenciaConsolidada = tenenciaValuadaDAO.obtenerTenenciaValuadaCuentaProductoOnline(
					createRequestServiceTenencias(cuentasDTO, cliente, segmento));
		} catch (DAOException e) {
			LOGGER.error("Error consultando tenencia", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}
//armar la lista de productor con error, y mandarsela a cargarTenenciasPorProducto

		TenenciasPorProductoDTO tenenciasPorProductoDTO = new TenenciasPorProductoDTO(
				tenenciaConsolidada.getDatos().getListaResultadoPorProducto());

		if (!tenenciasPorProductoDTO.mostrarProductos()) {
			LOGGER.error("Ningun producto OK");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}
		tenenciasPorProductoDTO.cargarTenenciasPorProducto(tenenciaConsolidada.getDatos().getResultado(),
				tenenciasPorProductoDTO.getListaProductosError());
		TenenciaConsolidadaPorProductoDTO tenenciaConsolidadaDTO = tenenciasPorProductoDTO.createTenenciaDTO();
		Collections.sort(tenenciaConsolidadaDTO.getListaTenencia());
//		tenenciaConsolidadaDTO.setMensajeWarning(mensajeBO
//				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_WARNING_INVERSIONES_TENENCIA_CONSOLIDADA)
//				.getMensaje());
		return respuestaFactory.crearRespuestaOk(TenenciaConsolidadaPorProductoDTO.class, tenenciaConsolidadaDTO);
	}
	
	private InicioFondoDTO inyectarCuentasTitRep(InicioFondoDTO inicioFondoDTO, Cliente cliente) {
		List<Cuenta> ctasTitRep=new ArrayList<Cuenta>();
		//if(tipoBanca.equals("BR")) {
			ctasTitRep=cliente.getCuentasTitRtlRepatriacion();
		//}else {
		//	ctasTitRep=cliente.getCuentasTitBPRepatriacion();
		//}
		List<CuentaTituloDTO> ListaDTO=inicioFondoDTO.getCuentasBancaPersonal();	
		
		for(Cuenta ctaTitRep : ctasTitRep) {
			CuentaTituloDTO ctaDto=new CuentaTituloDTO();
			ctaDto.setIntervinientes(ctaTitRep.getIntervinientes());
			ctaDto.setNroCuenta(ctaTitRep.getNroCuentaProducto());
			String nroCuenta=ctaTitRep.getNroCuentaProducto();
			ctaDto.setNroCuentaFormateado(nroCuenta.substring(nroCuenta.length() - (nroCuenta.length()-1), nroCuenta.length() - 1) + "/"
		            + nroCuenta.substring(nroCuenta.length() - 1, nroCuenta.length()));
			ctaDto.setRepatriacion(true);
			ctaDto.setSucursal("000");
			
			ListaDTO.add(ctaDto);
		}

		inicioFondoDTO.setCuentasBancaPersonal(ListaDTO);
		return inicioFondoDTO;
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * obtenerCustodia(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.view.
	 * DetalleCustodiaInView)
	 */
	@Override
	public DetalleCustodiaDTO obtenerCustodia(Cliente cliente, DetalleCustodiaInView detalleIn) {
		String segmento;
		List<CuentaTituloDTO> cuentasDTO;
		if (detalleIn.getEsBancaPrivada()) {
			segmento = Segmento.BANCA_PRIVADA.getCodigo();
			cuentasDTO = buscarCuentaParaDetalleCustodiaBP(cliente.getCuentasBancaPrivada(), detalleIn);
		} else {
			InicioFondoDTO inicioFondoDTO = generarCuentasDTO(cliente);
			segmento = Segmento.BANCA_PERSONAL.getCodigo();
			cuentasDTO = generarCuentasRTLTenencias(inicioFondoDTO.getCuentasBancaPersonal());
		}
		DetalleCustodiaOnlineEntity detalleCustodiaOnlineEntity = new DetalleCustodiaOnlineEntity();
		DetalleCustodiaDTO detalleDTO = new DetalleCustodiaDTO();
		try {
			detalleCustodiaOnlineEntity = tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleCustOnline(
					createRequestServiceTenencias(cuentasDTO, cliente, segmento));
		} catch (DAOException e) {
			LOGGER.error("Error consultando tenencia", e);
			detalleDTO.setHayError(true);
			return detalleDTO;
		}
		if (detalleIn.getEsBancaPrivada()) {
			List<TenenciaCuentaCustodiaMoneda> listaTenencia = new ArrayList<TenenciaCuentaCustodiaMoneda>();
			for (TenenciaCuentaCustodiaMoneda tenencia : detalleCustodiaOnlineEntity.getDatos().getResultado()) {
				if (tenencia.getNumeroCuenta().contains(detalleIn.getNumeroCuenta())) {
					listaTenencia.add(tenencia);
				}
			}
			detalleDTO = armarDetalleCustodiaDTO(listaTenencia, cuentasDTO, cliente);
		} else {
			detalleDTO = armarDetalleCustodiaDTO(detalleCustodiaOnlineEntity.getDatos().getResultado(), cuentasDTO,
					cliente);
		}
		return detalleDTO;
	}

	/**
	 * Armar detalle custodia DTO.
	 *
	 * @param listaTenencia      the lista tenencia
	 * @param listaCuentasTitulo the lista cuentas titulo
	 * @param cliente            the cliente
	 * @return the detalle custodia DTO
	 */
	private DetalleCustodiaDTO armarDetalleCustodiaDTO(List<TenenciaCuentaCustodiaMoneda> listaTenencia,
			List<CuentaTituloDTO> listaCuentasTitulo, Cliente cliente) {

		Collections.sort(listaTenencia);

		DetalleCustodiaDTO detalleDTO = new DetalleCustodiaDTO();
		List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView = new ArrayList<DetalleCustodiaCuentaView>();

		String numeroCuenta = StringUtils.EMPTY;

		for (int i = 0; i < listaTenencia.size(); i++) {
			if (numeroCuenta.equals(StringUtils.EMPTY)
					|| !numeroCuenta.equals(listaTenencia.get(i).getNumeroCuenta())) {
				listaDetalleCustodiaCuentaView
						.add(crearObjetoPorCuenta(listaTenencia.get(i), listaTenencia, listaCuentasTitulo, cliente));
				numeroCuenta = listaTenencia.get(i).getNumeroCuenta();
			} else if (i == listaTenencia.size() - 1) {
				break;
			} else {
				numeroCuenta = listaTenencia.get(i).getNumeroCuenta();
			}
		}

		detalleDTO.setListaDetalleCustodiaCuentaView(listaDetalleCustodiaCuentaView);

		return detalleDTO;
	}

	/**
	 * Crear objeto por cuenta.
	 *
	 * @param tenencia           the tenencia
	 * @param lista              the lista
	 * @param listaCuentasTitulo the lista cuentas titulo
	 * @param cliente            the cliente
	 * @return the detalle custodia cuenta view
	 */
	private DetalleCustodiaCuentaView crearObjetoPorCuenta(TenenciaCuentaCustodiaMoneda tenencia,
			List<TenenciaCuentaCustodiaMoneda> lista, List<CuentaTituloDTO> listaCuentasTitulo, Cliente cliente) {

		Boolean hayErrorParcial = false;

		for (TenenciaCuentaCustodiaMoneda tenenciaSola : lista) {
			if (tenenciaSola.getCotizacion() == null || tenenciaSola.getTenenciaValuada() == null
					|| tenenciaSola.getTenenciaValuadaReexp() == null) {
				hayErrorParcial = true;
			}
		}

		DetalleCustodiaCuentaView detalleCustodia = new DetalleCustodiaCuentaView();
		detalleCustodia.setNumeroCuenta(setearNroCuenta(tenencia.getNumeroCuenta(), tenencia.getSucursalCuenta()));
		detalleCustodia.setSucursalCuenta(tenencia.getSucursalCuenta());
		detalleCustodia.setDetalleCustodiaCuentaPeso(
				armarListaPorMoneda(lista, DivisaEnum.PESO.getCodigo(), tenencia.getNumeroCuenta()));
		detalleCustodia.setTotalPesos(sumarTotales(detalleCustodia.getDetalleCustodiaCuentaPeso(), true));
		detalleCustodia.setDetalleCustodiaCuentaDolar(
				armarListaPorMoneda(lista, DivisaEnum.DOLAR.getCodigo(), tenencia.getNumeroCuenta()));
		detalleCustodia.setTotalDolares(sumarTotales(detalleCustodia.getDetalleCustodiaCuentaDolar(), true));
		detalleCustodia.setTenenciaExpresadaPesos(
				armarTenenciaExpresadaPorMoneda(detalleCustodia, DivisaEnum.PESO.getCodigo(), hayErrorParcial));
		detalleCustodia.setTenenciaExpresadaDolares(
				armarTenenciaExpresadaPorMoneda(detalleCustodia, DivisaEnum.DOLAR.getCodigo(), hayErrorParcial));
		detalleCustodia.setTotalPesos(ISBANStringUtils.formatearSaldo(new BigDecimal(detalleCustodia.getTotalPesos())));
		detalleCustodia
				.setTotalDolares(ISBANStringUtils.formatearSaldo(new BigDecimal(detalleCustodia.getTotalDolares())));

		for (CuentaTituloDTO cuentaTitulo : listaCuentasTitulo) {
			Integer sucursalCuenta = Integer.parseInt(tenencia.getSucursalCuenta());
			Boolean esBancaPrivada = sucursalCuenta >= 250;
			if (cuentaTitulo.getCuentaOperativaSinFormatear().contains(tenencia.getNumeroCuenta())) {
				Cuenta cuentaOperativa = buscarCuenta(cliente, tenencia.getNumeroCuenta(), esBancaPrivada);
				detalleCustodia.setIntervinientes(obtenerIntervinientes(cuentaOperativa));
			}
		}

		return detalleCustodia;
	}

	/**
	 * Setear nro cuenta.
	 *
	 * @param nroCuenta   the nro cuenta
	 * @param nroSucursal the nro sucursal
	 * @return the string
	 */
	private String setearNroCuenta(String nroCuenta, String nroSucursal) {

		String nroCuentaCorrecto;
		int nroSucursalNumerico = Integer.parseInt(nroSucursal);

		if (nroSucursalNumerico >= 250) {
			nroCuentaCorrecto = nroCuenta.substring(1, nroCuenta.length());
		} else {
			nroCuentaCorrecto = nroCuenta;
		}

		return nroCuentaCorrecto;
	}

	/**
	 * Buscar cuenta.
	 *
	 * @param cliente        the cliente
	 * @param nroCuenta      the nro cuenta
	 * @param esBancaPrivada the es banca privada
	 * @return the cuenta
	 */
	private Cuenta buscarCuenta(Cliente cliente, String nroCuenta, Boolean esBancaPrivada) {

		Cuenta cuentaElegida = new Cuenta();
		if (esBancaPrivada) {
			for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
				if (cuenta.getNroCuentaProducto().contains(nroCuenta.substring(1, nroCuenta.length()))) {
					cuentaElegida = cuenta;
				}
			}
		} else {
			for (Cuenta cuenta : cliente.getCuentasRetail()) {
				if (cuenta.getNroCuentaProducto().contains(nroCuenta)) {
					cuentaElegida = cuenta;
				}
			}
		}
		return cuentaElegida;
	}

	/**
	 * Armar lista por moneda.
	 *
	 * @param lista        the lista
	 * @param moneda       the moneda
	 * @param numeroCuenta the numero cuenta
	 * @return the list
	 */
	private List<DetalleCustodiaCuentaMonedaView> armarListaPorMoneda(List<TenenciaCuentaCustodiaMoneda> lista,
			String moneda, String numeroCuenta) {

		List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaPeso = new ArrayList<DetalleCustodiaCuentaMonedaView>();
		for (TenenciaCuentaCustodiaMoneda tenenciaCuenta : lista) {
			if (numeroCuenta.equals(tenenciaCuenta.getNumeroCuenta()) && moneda.equals(tenenciaCuenta.getMoneda())) {
				DetalleCustodiaCuentaMonedaView detalleCuentaMoneda = new DetalleCustodiaCuentaMonedaView();
				detalleCuentaMoneda.setDescripcionCustodiaMonetaria(tenenciaCuenta.getDescripcionEspecie());
				detalleCuentaMoneda.setCantidadValorNominal(tenenciaCuenta.getTenenciaNominal());
				detalleCuentaMoneda.setPrecioMercado(
						tenenciaCuenta.getCotizacion() == null ? GUION_MEDIO : tenenciaCuenta.getCotizacion());
				detalleCuentaMoneda.setTenenciaValuadaHoy(tenenciaCuenta.getTenenciaValuada() == null ? GUION_MEDIO
						: tenenciaCuenta.getTenenciaValuada());
				detalleCuentaMoneda
						.setTenenciaReexpresada(tenenciaCuenta.getTenenciaValuadaReexp() == null ? GUION_MEDIO
								: tenenciaCuenta.getTenenciaValuadaReexp());
				detalleCuentaMoneda.setEstado(tenenciaCuenta.getDescripcionEstado().trim());
				detalleCustodiaCuentaPeso.add(detalleCuentaMoneda);
			}
		}

		return detalleCustodiaCuentaPeso;
	}

	/**
	 * Sumar totales.
	 *
	 * @param listaDetalle      the lista detalle
	 * @param isTenenciaValuada the is tenencia valuada
	 * @return the string
	 */
	private String sumarTotales(List<DetalleCustodiaCuentaMonedaView> listaDetalle, Boolean isTenenciaValuada) {

		BigDecimal total = new BigDecimal("0");
		if (isTenenciaValuada) {
			for (DetalleCustodiaCuentaMonedaView detalleMoneda : listaDetalle) {
				if (!GUION_MEDIO.equals(detalleMoneda.getTenenciaValuadaHoy())) {
					total = total.add(new BigDecimal(detalleMoneda.getTenenciaValuadaHoy()));
				}
			}
		} else {
			for (DetalleCustodiaCuentaMonedaView detalleMoneda : listaDetalle) {
				if (!GUION_MEDIO.equals(detalleMoneda.getTenenciaValuadaHoy())) {
					total = total.add(new BigDecimal(detalleMoneda.getTenenciaReexpresada()));
				} else {
					return GUION_MEDIO;
				}
			}
		}

		return total.toString();
	}

	/**
	 * Armar tenencia expresada por moneda.
	 *
	 * @param detalleCustodia the detalle custodia
	 * @param moneda          the moneda
	 * @param hayErrorParcial the hay error parcial
	 * @return the detalle custodia tenencia experesada
	 */
	private DetalleCustodiaTenenciaExperesada armarTenenciaExpresadaPorMoneda(DetalleCustodiaCuentaView detalleCustodia,
			String moneda, Boolean hayErrorParcial) {

		DetalleCustodiaTenenciaExperesada tenenciaExpresada = new DetalleCustodiaTenenciaExperesada();

		if (hayErrorParcial && DivisaEnum.PESO.getCodigo().equals(moneda)) {
			tenenciaExpresada.setTenenciaMonedaPrincipal(DivisaEnum.PESO.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTenenciaPrincipalEnOtraMoneda(DivisaEnum.DOLAR.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTenenciaMonedaSecundaria(DivisaEnum.DOLAR.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTotalMonedaSecundaria(DivisaEnum.DOLAR.getSimbolo() + " " + GUION_MEDIO);
			return tenenciaExpresada;
		} else if (hayErrorParcial) {
			tenenciaExpresada.setTenenciaMonedaPrincipal(DivisaEnum.DOLAR.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTenenciaPrincipalEnOtraMoneda(DivisaEnum.PESO.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTenenciaMonedaSecundaria(DivisaEnum.PESO.getSimbolo() + " " + GUION_MEDIO);
			tenenciaExpresada.setTotalMonedaSecundaria(DivisaEnum.PESO.getSimbolo() + " " + GUION_MEDIO);
			return tenenciaExpresada;
		}

		if (DivisaEnum.PESO.getCodigo().equals(moneda)) {
			tenenciaExpresada.setTenenciaMonedaPrincipal(detalleCustodia.getTotalPesos());
			tenenciaExpresada.setTenenciaPrincipalEnOtraMoneda(
					sumarTotales(detalleCustodia.getDetalleCustodiaCuentaPeso(), false));
			tenenciaExpresada.setTenenciaMonedaSecundaria(detalleCustodia.getTotalDolares());
			BigDecimal totalMonedaSecundaria = new BigDecimal("0");
			totalMonedaSecundaria = totalMonedaSecundaria.add(new BigDecimal(detalleCustodia.getTotalDolares()));
			totalMonedaSecundaria = totalMonedaSecundaria
					.add(new BigDecimal(tenenciaExpresada.getTenenciaPrincipalEnOtraMoneda()));
			tenenciaExpresada.setTotalMonedaSecundaria(totalMonedaSecundaria.toString());
		} else {
			tenenciaExpresada.setTenenciaMonedaPrincipal(detalleCustodia.getTotalDolares());
			tenenciaExpresada.setTenenciaPrincipalEnOtraMoneda(
					sumarTotales(detalleCustodia.getDetalleCustodiaCuentaDolar(), false));
			tenenciaExpresada.setTenenciaMonedaSecundaria(detalleCustodia.getTotalPesos());
			BigDecimal totalMonedaSecundaria = new BigDecimal("0");
			totalMonedaSecundaria = totalMonedaSecundaria.add(new BigDecimal(detalleCustodia.getTotalPesos()));
			totalMonedaSecundaria = totalMonedaSecundaria
					.add(new BigDecimal(tenenciaExpresada.getTenenciaPrincipalEnOtraMoneda()));
			tenenciaExpresada.setTotalMonedaSecundaria(totalMonedaSecundaria.toString());
		}

		return tenenciaExpresada;
	}

	/**
	 * Buscar cuenta para detalle custodia BP.
	 *
	 * @param cuentasBancaPrivada the cuentas banca privada
	 * @param detalleIn           the detalle in
	 * @return the list
	 */
	private List<CuentaTituloDTO> buscarCuentaParaDetalleCustodiaBP(List<CuentaBancaPrivada> cuentasBancaPrivada,
			DetalleCustodiaInView detalleIn) {

		List<CuentaTituloDTO> listaCuentasDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaBancaPrivada cuentaBPriv : cuentasBancaPrivada) {
			if (cuentaBPriv.getCuentaOperativa().getNroCuentaProducto().contains(detalleIn.getNumeroCuenta())) {
				CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
				cuentaTitDTO.setCuentaOperativaSinFormatear(
						"7" + StringUtils.right(cuentaBPriv.getCuentaOperativa().getNroCuentaProducto(),
								LARGO_NUMERO_CUENTA_OP_REQUEST_TENENCIA_CONSOLIDADA));
				cuentaTitDTO.setSucursal(cuentaBPriv.getCuentaOperativa().getNroSucursal());
				listaCuentasDTO.add(cuentaTitDTO);
			}
		}
		return listaCuentasDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * obtenerDatosCustodiaRespuestaError(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.comun.view.
	 * DetalleCustodiaInView)
	 */
	@Override
	public DetalleCustodiaDTO obtenerDatosCustodiaRespuestaError(Cliente cliente, DetalleCustodiaInView detalleIn) {

		DetalleCustodiaDTO detalleCustodiaDTO = new DetalleCustodiaDTO();
		List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView = new ArrayList<DetalleCustodiaCuentaView>();

		if (detalleIn.getEsBancaPrivada()) {
			for (CuentaBancaPrivada cuentaBPriv : cliente.getCuentasBancaPrivada()) {
				if (cuentaBPriv.getCuentaOperativa().getNroCuentaProducto().contains(detalleIn.getNumeroCuenta())) {
					DetalleCustodiaCuentaView detalleView = new DetalleCustodiaCuentaView();
					detalleView.setNumeroCuenta(cuentaBPriv.getCuentaOperativa().getNroSucursal().substring(1,
							cuentaBPriv.getCuentaOperativa().getNroSucursal().length()) + "-"
							+ ISBANStringUtils
									.formatearNumeroCuenta(cuentaBPriv.getCuentaOperativa().getNroCuentaProducto()));
					detalleView.setIntervinientes(cuentaBPriv.getCuentaOperativa().getIntervinientes());
					listaDetalleCustodiaCuentaView.add(detalleView);
				}
			}
			detalleCustodiaDTO.setListaDetalleCustodiaCuentaView(listaDetalleCustodiaCuentaView);
		} else {
			InicioFondoDTO inicioFondoDTO = generarCuentasDTO(cliente);
			List<CuentaTituloDTO> cuentasDTO = generarCuentasRTLTenencias(inicioFondoDTO.getCuentasBancaPersonal());
			for (CuentaTituloDTO cuentaTitulo : cuentasDTO) {
				DetalleCustodiaCuentaView detalleView = new DetalleCustodiaCuentaView();
				detalleView.setNumeroCuenta(cuentaTitulo.getCuentaOperativa());
				detalleView.setIntervinientes(cuentaTitulo.getIntervinientes());
				listaDetalleCustodiaCuentaView.add(detalleView);
			}
			detalleCustodiaDTO.setListaDetalleCustodiaCuentaView(listaDetalleCustodiaCuentaView);
		}

		return detalleCustodiaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO#
	 * obtenerTenenciaConsolidadaPorProductoBPriv(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaConsolidadaBPrivDTO> obtenerTenenciaConsolidadaPorProductoBPriv(Cliente cliente) {

		List<CuentaTituloDTO> cuentasDTO = generarCuentasBPrivTenencias(cliente.getCuentasBancaPrivada());
		String segmento = Segmento.BANCA_PRIVADA.getCodigo();

		CuentaProductoOnlineEntity tenenciaConsolidadaEntity = new CuentaProductoOnlineEntity();
		try {
			DetalleFondoRequestEntity request = createRequestServiceTenencias(cuentasDTO, cliente, segmento);
			tenenciaConsolidadaEntity = tenenciaValuadaDAO.obtenerTenenciaValuadaCuentaProductoOnline(request);
		} catch (DAOException e) {
			LOGGER.error("Error consultando tenencia", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}

		boolean errorEnTodosLosProductos = true;
		boolean respuestaWarning = false;
		for (ProductoEntity productoEntity : tenenciaConsolidadaEntity.getDatos().getListaResultadoPorProducto()) {
			if (CODIGO_RETORNO_OK.equals(productoEntity.getResultado())) {
				errorEnTodosLosProductos = false;
			} else {
				respuestaWarning = true;
			}
		}

		if (errorEnTodosLosProductos) {
			LOGGER.error("No hubo ningun producto con resultado OK");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}

		TenenciaConsolidadaBPrivDTO tenenciasTotalesCuentasResponse = new TenenciaConsolidadaBPrivDTO();
		TenenciaProductosPorMonedaDTO tenenciaMonedaBuscada = new TenenciaProductosPorMonedaDTO();
		boolean esCodigoRetornoError = false;

		for (ResultadoCuentaProductoOL resultadoCuentaProductoOL : tenenciaConsolidadaEntity.getDatos()
				.getResultado()) {
			TenenciaPorCuentaBPrivDTO tenenciaCuentaBuscada = new TenenciaPorCuentaBPrivDTO();

			String codigoRetorno = tenenciaConsolidadaEntity.getDatos()
					.getResultadoProductoPorCodigo(resultadoCuentaProductoOL.getCodProducto()).getResultado();

			tenenciaCuentaBuscada.setTenenciaDolares(inicializarProductos(tenenciaConsolidadaEntity.getDatos()));
			tenenciaCuentaBuscada.setTenenciaPesos(inicializarProductos(tenenciaConsolidadaEntity.getDatos()));

			boolean cuentaEncontrada = false;

			for (TenenciaPorCuentaBPrivDTO tenenciaPorCuenta : tenenciasTotalesCuentasResponse
					.getListaTenenciaPorCuenta()) {
				String numeroCuenta = resultadoCuentaProductoOL.getNumeroCuenta().substring(2, 10);

				if (numeroCuenta.equals(tenenciaPorCuenta.getNroCuenta())) {
					tenenciaCuentaBuscada = tenenciaPorCuenta;
					cuentaEncontrada = true;
				}
			}
			if (!cuentaEncontrada) {
				tenenciaCuentaBuscada.setNroCuenta(
						StringUtils.right(resultadoCuentaProductoOL.getNumeroCuenta(), LARGO_NUMERO_CUENTA));
				String nroCuentaFormateado = ISBANStringUtils.formatearNroCuentaProductoConSucursal(
						StringUtils.right(resultadoCuentaProductoOL.getNumeroCuenta(), LARGO_NUMERO_CUENTA), "250");
				tenenciaCuentaBuscada.setNroCuentaFormateado(nroCuentaFormateado);
				tenenciasTotalesCuentasResponse.getListaTenenciaPorCuenta().add(tenenciaCuentaBuscada);
			}

			if (resultadoCuentaProductoOL.getMoneda().equals(MONEDA_PESOS)) {
				tenenciaMonedaBuscada = tenenciaCuentaBuscada.getTenenciaPesos();
				tenenciaMonedaBuscada.setMoneda(MONEDA_PESOS);
			} else {
				tenenciaMonedaBuscada = tenenciaCuentaBuscada.getTenenciaDolares();
				tenenciaMonedaBuscada.setMoneda(MONEDA_DOLARES);
			}

			TenenciaPorProductoBPrivDTO tenenciaPorProductoDTO = tenenciaMonedaBuscada
					.getTenenciaProductoPorCodigo(resultadoCuentaProductoOL.getCodProducto());
			tenenciaPorProductoDTO.setProducto(resultadoCuentaProductoOL.getCodProducto());

			// SI EL RESULTADO DEL PRODUCTO ACTUAL NO ES OK, MUESTRO LAS 3 COLUMNAS DE ESE
			// PRODUCTO CON GUIONES
			if ((null != resultadoCuentaProductoOL.getGuidError() && CODIGO_RETORNO_UNO.equals(codigoRetorno))
					|| ERROR_TOTAL.equals(codigoRetorno)) {
				tenenciaPorProductoDTO.setTenenciaValuadaCosto(GUION_MEDIO);
				tenenciaPorProductoDTO.setTenenciaValuadaHoy(GUION_MEDIO);
				tenenciaPorProductoDTO.setResultado(GUION_MEDIO);
				tenenciaPorProductoDTO.setTenenciaValuadaReexpresada(GUION_MEDIO);
				esCodigoRetornoError = true;
			}

			if (null == resultadoCuentaProductoOL.getGuidError() && CODIGO_RETORNO_UNO.equals(codigoRetorno)
					|| CODIGO_RETORNO_OK.equals(codigoRetorno)) {
				tenenciaPorProductoDTO.setTenenciaValuadaCosto(resultadoCuentaProductoOL.getTenenciaValuadaCompra());
				tenenciaPorProductoDTO.setTenenciaValuadaHoy(resultadoCuentaProductoOL.getTenenciaValuada());
				tenenciaPorProductoDTO
						.setTenenciaValuadaReexpresada(resultadoCuentaProductoOL.getTenenciaValuadaReexp());

				if (TipoProductoEnum.TITULOS_VALORES.getCodigo().equals(resultadoCuentaProductoOL.getCodProducto())) {
					// PARA TITULOS SE USA RESULTADO BRUTO CORREGIDO, PARA LOS DEMAS PRODUCTOS SE
					// USA RESULTADO BRUTO
					tenenciaPorProductoDTO.setResultado(resultadoCuentaProductoOL.getResultadoBrutoCorregido());
				} else {
					tenenciaPorProductoDTO.setResultado(resultadoCuentaProductoOL.getResultadoBruto());
				}

				if (null != resultadoCuentaProductoOL.getGuidError() && (!esCodigoRetornoError)) {
					tenenciaPorProductoDTO.setTenenciaValuadaCosto(GUION_MEDIO);
					tenenciaPorProductoDTO.setTenenciaValuadaHoy(GUION_MEDIO);
					tenenciaPorProductoDTO.setResultado(GUION_MEDIO);
					tenenciaPorProductoDTO.setTenenciaValuadaReexpresada(GUION_MEDIO);
				}

				if (TipoProductoEnum.CUSTODIA.getCodigo().equals(resultadoCuentaProductoOL.getCodProducto())) {
					// PARA CUSTODIA NO SE MUESTRAN LOS VALORES TENENCIA VALUADA COSTO Y RESULTADO
					tenenciaPorProductoDTO.setTenenciaValuadaCosto(GUION_MEDIO);
					tenenciaPorProductoDTO.setResultado(GUION_MEDIO);
				}

			}

			if (eliminarProductoDeGrilla(tenenciaConsolidadaEntity.getDatos(),
					resultadoCuentaProductoOL.getCodProducto(), tenenciaPorProductoDTO.getTenenciaValuadaHoy())) {
				tenenciaMonedaBuscada.eliminarProductoDelistaPorCodigo(resultadoCuentaProductoOL.getCodProducto());
			} else {
				tenenciaMonedaBuscada.acumularTotalResultado(tenenciaPorProductoDTO.getResultado());
				tenenciaMonedaBuscada
						.acumularTotalTenenciaValuadaCosto(tenenciaPorProductoDTO.getTenenciaValuadaCosto());
				tenenciaMonedaBuscada.acumularTotalTenenciaValuadaHoy(tenenciaPorProductoDTO.getTenenciaValuadaHoy());
				tenenciaMonedaBuscada.acumularMonedaReexpresada(tenenciaPorProductoDTO.getTenenciaValuadaReexpresada());
			}
		}

		if (respuestaWarning) {
			return respuestaFactory.crearRespuestaWarning(TenenciaConsolidadaBPrivDTO.class,
					tenenciasTotalesCuentasResponse, TipoError.GRILLA_INVERSIONES_PARCIAL, "", "");

		}

		for (TenenciaPorCuentaBPrivDTO tenenciaPorCuenta : tenenciasTotalesCuentasResponse
				.getListaTenenciaPorCuenta()) {

			double totalReexpresadoDolares = Double
					.valueOf(tenenciaPorCuenta.getTenenciaDolares().getMonedaReexpresada())
					+ Double.valueOf(tenenciaPorCuenta.getTenenciaPesos().getTotalTenenciaValuadaHoy());
			tenenciaPorCuenta.getTenenciaDolares().setTotalMonedaReexpresada(String.valueOf(totalReexpresadoDolares));

			double totalReexpresadoPesos = Double.valueOf(tenenciaPorCuenta.getTenenciaPesos().getMonedaReexpresada())
					+ Double.valueOf(tenenciaPorCuenta.getTenenciaDolares().getTotalTenenciaValuadaHoy());
			tenenciaPorCuenta.getTenenciaPesos().setTotalMonedaReexpresada(String.valueOf(totalReexpresadoPesos));

			BigDecimal tenenciaPesos = new BigDecimal(
					tenenciaPorCuenta.getTenenciaPesos().getTotalTenenciaValuadaHoy());
			BigDecimal tenenciaDolaresReexpresada = new BigDecimal(
					tenenciaPorCuenta.getTenenciaDolares().getMonedaReexpresada());
			BigDecimal valorTotal = tenenciaPesos.add(tenenciaDolaresReexpresada);
			tenenciaPorCuenta
					.setPorcentajeMonedaPesos(ISBANStringUtils.obtenerPorcentaje(tenenciaPesos, valorTotal).toString());
		}

		return respuestaFactory.crearRespuestaOk(TenenciaConsolidadaBPrivDTO.class, tenenciasTotalesCuentasResponse);
	}

	/**
	 * Inicializa los valores con guiones o cero segun si el producto vino con
	 * codigo ERROR TOTAL o no, respectivamente.
	 *
	 * @param entityResponse the entity response
	 * @return the tenencia productos por moneda DTO
	 */
	private TenenciaProductosPorMonedaDTO inicializarProductos(DatosRespuestaCuentaProductoOnline entityResponse) {

		TenenciaProductosPorMonedaDTO tenenciaPorMoneda = new TenenciaProductosPorMonedaDTO();

		for (TipoProductoBPrivEnum tipoProducto : TipoProductoBPrivEnum.values()) {

			TenenciaPorProductoBPrivDTO producto = new TenenciaPorProductoBPrivDTO();
			producto.setProducto(tipoProducto.getCodigo());
			String resultadoProducto = entityResponse.getResultadoProductoPorCodigo(tipoProducto.getCodigo())
					.getResultado();
			String valorCorrespondiente;

			if (ERROR_TOTAL.equals(resultadoProducto)) {
				valorCorrespondiente = GUION_MEDIO;
			} else {
				valorCorrespondiente = CERO_CON_DECIMALES;
			}

			producto.setResultado(valorCorrespondiente);
			producto.setTenenciaValuadaCosto(valorCorrespondiente);
			producto.setTenenciaValuadaHoy(valorCorrespondiente);
			producto.setTenenciaValuadaReexpresada(valorCorrespondiente);

			tenenciaPorMoneda.getListaTenenciaProductos().add(producto);
		}
		return tenenciaPorMoneda;
	}

	/**
	 * Generar cuentas B priv tenencias.
	 *
	 * @param cuentasBancaPrivada the cuentas banca privada
	 * @return the list
	 */
	private List<CuentaTituloDTO> generarCuentasBPrivTenencias(List<CuentaBancaPrivada> cuentasBancaPrivada) {

		List<CuentaTituloDTO> listaCuentasDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaBancaPrivada cuentaBPriv : cuentasBancaPrivada) {
			CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
			cuentaTitDTO.setCuentaOperativaSinFormatear(
//					"7" + StringUtils.right(cuentaBPriv.getCuentaOperativa().getNroCuentaProducto(),
					Integer.parseInt(cuentaBPriv.getCuentaOperativa().getProductoAltair()) + StringUtils.right(cuentaBPriv.getCuentaOperativa().getNroCuentaProducto(),
							LARGO_NUMERO_CUENTA_OP_REQUEST_TENENCIA_CONSOLIDADA));
			cuentaTitDTO.setSucursal(cuentaBPriv.getCuentaOperativa().getNroSucursal());
			listaCuentasDTO.add(cuentaTitDTO);
		}
		return listaCuentasDTO;
	}

	/**
	 * Eliminar producto de grilla.
	 *
	 * @param datos              the datos
	 * @param codProducto        the cod producto
	 * @param tenenciaValuadaHoy the tenencia valuada hoy
	 * @return true, if successful
	 */
	private boolean eliminarProductoDeGrilla(DatosRespuestaCuentaProductoOnline datos, String codProducto,
			String tenenciaValuadaHoy) {
		if (!TipoProductoEnum.CUSTODIA.getCodigo().equals(codProducto)) {
			return false;
		}
		if (tenenciaValuadaHoy == null || tenenciaValuadaHoy.equals(CERO)
				|| !CODIGO_RETORNO_OK.equals(datos.getResultadoProductoPorCodigo(codProducto).getResultado())) {
			return true;
		}
		return false;
	}

	@Override
	public Respuesta<Reporte> obtenerTenenciaConsolidadaReporte(List<TenenciaProductosPorMonedaView> listaTenencias,
			Cliente cliente) {

		return reporteDAO.obtenerReporte(new InfoTenenciaConsolidadaExcel(listaTenencias), "TenenciaConsolidadaRTL",
				cliente, false);
	}

	@Override
	public Respuesta<Reporte> obtenerTenenciaConsolidadaReporteBP(List<TenenciaPorCuentaBPrivDTO> listaTenenciasBP,
			Cliente cliente) {

		InfoTenenciaConsolidadaGeneralExcel tcGeneralExcel = new InfoTenenciaConsolidadaGeneralExcel();
		List<InfoTenenciaConsolidadaCuentaExcel> listaCuentas = new ArrayList<InfoTenenciaConsolidadaCuentaExcel>();

		for (TenenciaPorCuentaBPrivDTO cuentaBP : listaTenenciasBP) {
			InfoTenenciaConsolidadaCuentaExcel cuentaTC = new InfoTenenciaConsolidadaCuentaExcel(cuentaBP, cliente);
			listaCuentas.add(cuentaTC);
		}
		tcGeneralExcel.setListaCuentas(listaCuentas);

		return reporteDAO.obtenerReporte(tcGeneralExcel, "TenenciaConsolidadaBP", cliente, false);
	}

	@Override
	public Respuesta<TarjetaTenenciaConsolidadaView> obtenerTotalesTenenciaHome(Cliente cliente, TipoBancaEnum banca) {		
		return respuestaFactory.crearRespuestaWarning(null, "", TipoError.WARNING_CAJA_VACIA, "");
	}

	private CarteraTenenciaValuadaEntity holdingSummaryToTenenciaEntity(HoldingsSummaryResponse holdingSummary) {
		CarteraTenenciaValuadaEntity tenenciaEntity = new CarteraTenenciaValuadaEntity();
		DatosRespuestaCartera datosRespuestaCartera = new DatosRespuestaCartera();

		tenenciaEntity.setCodigo(0);
		tenenciaEntity.setMensajeTecnico("Se pudieron valuar todos los activos de este producto de inversión");
		datosRespuestaCartera.setTenenciaTotalCarteraRTL(getSummaryBySegment(holdingSummary, bancaRetail));
		datosRespuestaCartera.setTenenciaTotalCarteraBP(getSummaryBySegment(holdingSummary, bancaPrivada));
		tenenciaEntity.setDatos(datosRespuestaCartera);

		return tenenciaEntity;
	}

	private TenenciaValuadaCarteraResultadosEntity getSummaryBySegment(HoldingsSummaryResponse holdingsSummary, String segment) {
		TenenciaValuadaCarteraResultadosEntity tenenciaValuadaCarteraResultados =
				new TenenciaValuadaCarteraResultadosEntity();
		for (HoldingsSummary summary : holdingsSummary.getSummary()) {
			if (summary.getSegment().equalsIgnoreCase(segment)) {
				if (summary.getCurrency().equalsIgnoreCase(MONEDA_PESOS)) {
					tenenciaValuadaCarteraResultados.setPesos(Double.toString(summary.getAmount()));
				} else {
					tenenciaValuadaCarteraResultados.setDolares(Double.toString(summary.getAmount()));
				}
			}
		}

		return tenenciaValuadaCarteraResultados;
	}

	private CarteraTenenciaValuadaEntity createTenenciaEntity(Cliente cliente,
															  TenenciaValuadaCarteraRequestEntity requestTenencia) throws DAOException {
		CarteraTenenciaValuadaEntity tenenciaEntity;
		if (fundsApi.checkBouncerAccess(FundsApiConstants.Paths.HOLDINGS_BFF,
				FundsApiConstants.Bouncer.ACCESS_SUMMARY,
				cliente.getNup()) && requestTenencia.getDatos().getCodigoProducto().equalsIgnoreCase(productoFondos)) {
			try {
				HoldingsSummaryResponse holdingSummary = fundsApi.getHoldingSummary(cliente);
				tenenciaEntity = holdingSummaryToTenenciaEntity(holdingSummary);
			} catch(ApiException e) {
				tenenciaEntity = tenenciaValuadaDAO
						.obtenerTenenciaValuadaCarteraTotalOnline(requestTenencia);
			}
		} else {
			tenenciaEntity = tenenciaValuadaDAO
					.obtenerTenenciaValuadaCarteraTotalOnline(requestTenencia);
		}

		return tenenciaEntity;
	}

}
