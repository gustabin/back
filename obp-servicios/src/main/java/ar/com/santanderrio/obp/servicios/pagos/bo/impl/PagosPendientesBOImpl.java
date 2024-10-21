/*
 * editado: 29/08/2016 17:04:34 - b039542 - ignacio_valek@itrsa.com.ar
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaAdhesionDebitoAutomaticoCuentaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaDebitoDirectoDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.AdhesionPagoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaInformadaDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.FiltroAgendaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaInformada;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoPagoEmpresaPMC;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;

/**
 * The Class PagosPendientesBOImpl.
 */
@Component
public class PagosPendientesBOImpl implements PagosPendientesBO {

	/** The Constant TITULAR_TARJETA_CODIGO. */
	private static final String TITULAR_TARJETA_CODIGO = "TI";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosPendientesBOImpl.class);

	/** The Constant SOLICITUD_YA_PROCESADADA_CODIGO_IATX. */
	private static final int SOLICITUD_YA_PROCESADADA_CODIGO_IATX = 10000163;

	/** The Constant NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX. */
	private static final int NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX = 10000080;

	/** The Constant DATO_TARJETA_PAGO_PUNTUAL_VISA. */
	private static final String DATO_TARJETA_PAGO_PUNTUAL_VISA = "01";

	/** The Constant DATO_TARJETA_PAGO_PUNTUAL_AMEX. */
	private static final String DATO_TARJETA_PAGO_PUNTUAL_AMEX = "00";

	/** The Constant OBTENER_MEDIO_PAGO_ERROR. */
	private static final String OBTENER_MEDIO_PAGO_ERROR = "Error al intentar obtener medio de pago con codigo: ";

	/** The Constant DISCRIMINAR_PAGOS_AUTOMATICOS_WARNING_MSG. */
	private static final String DISCRIMINAR_PAGOS_AUTOMATICOS_WARNING_MSG = "No se pudo determinar los pagos automaticos";

	/** The Constant ERROR_MEDIO_PAGO_INEXISTENTE. */
	private static final String ERROR_MEDIO_PAGO_INEXISTENTE = "El medio de pago no existe en el archivo de medios.";

	/** The Constant TIPO_DE_PAGO_1. */
	private static final int TIPO_DE_PAGO_1 = 1;

	/** The Constant TIPO_DE_PAGO_2. */
	private static final int TIPO_DE_PAGO_2 = 2;

	/** The Constant TIPO_DE_PAGO_3. */
	private static final int TIPO_DE_PAGO_3 = 3;

	/** The Constant TIPO_DE_EMPRESA_R. */
	private static final String TIPO_DE_EMPRESA_R = "R";

	/** The Constant TIPO_DE_EMPRESA_T. */
	private static final String TIPO_DE_EMPRESA_T = TITULAR_TARJETA_CODIGO;

	/** The Constant TIPO_DE_EMPRESA_F. */
	private static final String TIPO_DE_EMPRESA_F = "F";

	/** The Constant DEBITO_AUTOMATICO_TOTAL_03. */
	private static final String DEBITO_AUTOMATICO_TOTAL_03 = "03";

	/** The Constant DEBITO_AUTOMATICO_TOTAL_05. */
	private static final String DEBITO_AUTOMATICO_TOTAL_05 = "05";

	/** The Constant DEBITO_AUTOMATICO_MINIMO_02. */
	private static final String DEBITO_AUTOMATICO_MINIMO_02 = "02";

	/** The Constant DEBITO_AUTOMATICO_MINIMO_04. */
	private static final String DEBITO_AUTOMATICO_MINIMO_04 = "04";

	/** The Constant ENTERO_DOCE. */
	private static final int ENTERO_DOCE = 12;

	/** The Constant ERROR_DAO. */
	private static final String ERROR_DAO = "Error al acceder a los datos de tarjeta desde el dao. {}";

	/** The Constant CANTIDAD_MESES. */
	private static final int CANTIDAD_MESES = 2;

	/** The Constant FORMATO_FECHA_CNSDDIDERE. */
	private static final String FORMATO_FECHA_CNSDDIDERE = "yyyyMMdd";

	/** The deuda informada dao. */
	@Autowired
	private DeudaInformadaDAO deudaInformadaDao;

	@Autowired
	private ConsultaAdhesionDebitoAutomaticoCuentaDAO consultaAdhesionDebitoAutomaticoCuentaDAO;

	@Autowired
	private ConsultaDebitoDirectoDAO consultaDebitoDirectoDAO;

	/** The filtro agenda dao. */
	@Autowired
	private FiltroAgendaDAO filtroAgendaDao;

	/** The deuda pago automatico dao. */
	@Autowired
	private DeudaPagoAutomaticoDAO deudaPagoAutomaticoDAO;

	/** The medios pago bo. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The banelco dao. */
	@Autowired
	private BanelcoDAO banelcoDAO;

	/** The deuda prestamo dao. */
	@Autowired
	private PrestamoBO prestamoBO;

	/** The datos tarjeta dao. */
	@Autowired
	private DatosTarjetaDAO datosTarjetaDAO;

	/** The adhesion pago DAO. */
	@Autowired
	private AdhesionPagoDAO adhesionPagoDAO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The buscador empresa BO. */
	@Autowired
	private BuscadorEmpresaBO buscadorEmpresaBO;
	
    /** The cantidad debitos automaticos. */
    @Value("${DEBITOS.AUTOMATICOS.CANTIDAD.EMPRESAS:300}")
    private Integer cantidadEmpresasFiltradas;

	/**
	 * Obtener pagos pendientes.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<List<PagoPendiente>> obtenerPagosPendientes(Cliente cliente) {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		try {
			List<DeudaInformada> deudaInformadaList = null;
			List<DeudaPagoAutomaticoEntity> deudaPagoAutomatico = null;
			boolean errorConsultaDebitosAutomaticosPMC = false;
			try {
				deudaInformadaList = deudaInformadaDao.consultarDeudaInformada(cliente);
			} catch (DAOException e) {
				// Error en el servicio de deuda, no se pueden mostrar
				// movimientos
				String mensaje = e.getMessage();
				LOGGER.error(mensaje, e);
				return generarRespuestaError(mensaje);
			}

			EstadoRespuesta estadoRespuesta = EstadoRespuesta.OK;

			try {
				deudaPagoAutomatico = deudaPagoAutomaticoDAO.consultarDeudaPagoAutomatico(cliente);
			} catch (DAOException e) {
				// Error en el servicio de pagos automaticos, se agrega warning
				errorConsultaDebitosAutomaticosPMC = true;
				deudaPagoAutomatico = new ArrayList<DeudaPagoAutomaticoEntity>();
				LOGGER.error(e.getMessage(), e);
			}
			List<PagoPendiente> deudaMergeada = armarListaPagosPendientes(deudaInformadaList, deudaPagoAutomatico,
			        errorConsultaDebitosAutomaticosPMC);

			// se setea si es editableDetalle o no
			deudaMergeada = setearListaPagosPendientesEditables(deudaMergeada);

			respuesta.setEstadoRespuesta(estadoRespuesta);
			respuesta.setRespuestaVacia(false);
			respuesta.setRespuesta(deudaMergeada);
			return respuesta;
		} catch (BusinessException e) {
			LOGGER.error("Se agrupa la captura de excptions", e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
			// TODO emilio.watemberg: set custom message error
			return respuesta;
		}
	}

	/**
	 * Obtener debitos automaticos. busca la empresa a través de un indexador.
	 * 
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<List<PagoPendiente>> obtenerDebitosAutomaticos(Cliente cliente) {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		List<PagoPendiente> listaDebitos = new ArrayList<PagoPendiente>();
		try {
			EmpresasAdheridasDebitoAutoInEntity inEntity = new EmpresasAdheridasDebitoAutoInEntity();
			inEntity.setCliente(cliente);
			inEntity.setNombreApellido(getNombreApellido(cliente));
			EmpresasAdheridasDebitoAutoOutEntity outEntity = consultaAdhesionDebitoAutomaticoCuentaDAO
			        .consultar(inEntity);
			
			List<EmpresaAdheridaEntity> empresas = outEntity.getEmpresas();
		    if (outEntity.getEmpresas()!= null 
		            && outEntity.getEmpresas().size() > cantidadEmpresasFiltradas) {
		        try {
		            empresas = obtenerUltimosDebitos(outEntity.getEmpresas());
                } catch (Exception e) {
                    LOGGER.error("Error al filtrar debitos automáticos",e);
                }
		    }
			for (EmpresaAdheridaEntity empresaAdheridaDebitoAutomatico : empresas) {
				if (Integer.valueOf(empresaAdheridaDebitoAutomatico.getEstadoServicioDDI()).equals(0)
				        && !filtroAgendaDao.existeEmpresa(empresaAdheridaDebitoAutomatico.getCuitEmpresa(),
				                empresaAdheridaDebitoAutomatico.getNombreServicioEmpresa())) {
					ComprobanteDebitoAutomaticoInEntity comprobanteDebitoAutomaticoInEntity = armarComprobanteDebitoAutomaticoInEntity(
					        cliente, empresaAdheridaDebitoAutomatico);
					ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = consultaDebitoDirectoDAO
					        .consultar(comprobanteDebitoAutomaticoInEntity);
					if (comprobanteDebitoAutomaticoOutEntity.getComprobantes() == null
					        || comprobanteDebitoAutomaticoOutEntity.getComprobantes().size() == 0) {
						PagoPendiente pagoPendiente = obtenerPagoPendiente(empresaAdheridaDebitoAutomatico);
						pagoPendiente.setHabilitarStopDebit(Boolean.TRUE);
						listaDebitos.add(pagoPendiente);
					} else {
						for (ComprobanteDebitoAutomatico deudaServicio : comprobanteDebitoAutomaticoOutEntity
						        .getComprobantes()) {
							PagoPendiente pagoPendiente = obtenerPagoPendiente(empresaAdheridaDebitoAutomatico);
							completarPagoConDeuda(pagoPendiente, deudaServicio);
							LOGGER.info(pagoPendiente.toString());
							listaDebitos.add(pagoPendiente);
						}
					}
				}
			}
			respuesta.setRespuesta(listaDebitos);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
		} catch (Exception e) {
			LOGGER.info("Hubo un error al obtener las empresas y su deudas", e);
			respuesta.setRespuesta(null);
			respuesta.setRespuestaVacia(true);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			respuesta.add(new ItemMensajeRespuesta(mensaje.getMensaje()));
		}
		return respuesta;
	}

    
    private List<EmpresaAdheridaEntity> obtenerUltimosDebitos(List<EmpresaAdheridaEntity> empresas) {
        Collections.sort(empresas, new Comparator<EmpresaAdheridaEntity>() {
            @Override
            public int compare(EmpresaAdheridaEntity comprobante1, EmpresaAdheridaEntity comprobante2) {
                return comprobante2.getFechaVigenciaDDI().compareTo(comprobante1.getFechaVigenciaDDI());
            }
        });
        int indiceSublista = empresas.size() > cantidadEmpresasFiltradas? cantidadEmpresasFiltradas : cantidadEmpresasFiltradas;
        return empresas.subList(0, indiceSublista);
    }

	private ComprobanteDebitoAutomaticoInEntity armarComprobanteDebitoAutomaticoInEntity(Cliente cliente,
	        EmpresaAdheridaEntity empresaAdheridaDebitoAutomatico) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA_CNSDDIDERE);
		String hoy = formatter.format(now.getTime());
		now.add(Calendar.MONTH, CANTIDAD_MESES);
		String dosMeses = formatter.format(now.getTime());
		ComprobanteDebitoAutomaticoInEntity comprobanteDebitoAutomaticoInEntity = new ComprobanteDebitoAutomaticoInEntity();
		comprobanteDebitoAutomaticoInEntity.setCliente(cliente);
		comprobanteDebitoAutomaticoInEntity.setEmpresa(empresaAdheridaDebitoAutomatico);
		comprobanteDebitoAutomaticoInEntity.setFechaDesde(hoy);
		comprobanteDebitoAutomaticoInEntity.setFechaHasta(dosMeses);
		comprobanteDebitoAutomaticoInEntity.setFechaContinuacion("");
		comprobanteDebitoAutomaticoInEntity.setIDDebitoDDICont("");
		comprobanteDebitoAutomaticoInEntity.setFlujoDDI(true);
		return comprobanteDebitoAutomaticoInEntity;
	}

	/**
	 * Armar nombre y apellido como se hacia en DeudainformadaDAOImpl.
	 * 
	 * @param cliente
	 * @return
	 */
	private String getNombreApellido(Cliente cliente) {
		String apellido1 = cliente.getApellido1().trim();
		String apellido2 = cliente.getApellido2().trim();
		String nombre = cliente.getNombre().trim();
		return apellido1 + " " + apellido2 + " " + nombre + "                              ";
	}

	/**
	 * Obtener pago pendiente.
	 *
	 * @param serv the serv
	 * @return the pago pendiente
	 * @throws BusinessException the business exception
	 * @throws DAOException
	 */
	private PagoPendiente obtenerPagoPendiente(EmpresaAdheridaEntity serv) throws BusinessException, DAOException {
		PagoPendiente pagoPendiente = new PagoPendiente();
		String cuit = serv.getCuitEmpresa();
		pagoPendiente.setCodigoClienteEmpresa(serv.getNroPartidaServicioEmpresa());
		MedioPago medioDePago = obtenerMedioDePago(serv);
		pagoPendiente.setDivisa(DivisaEnum.fromSimboloString(medioDePago.getMoneda()));
		pagoPendiente.setTipoPagoEmpresa(consultarTipoPago(medioDePago));
		pagoPendiente.setNombreEmpresa(medioDePago.getNombreFantasia());
		if (StringUtils.isBlank(pagoPendiente.getNombreEmpresa())) {
			pagoPendiente.setNombreEmpresa(serv.getNombreServicioEmpresa());
		}
		pagoPendiente.setMedioPago(medioDePago);
		pagoPendiente.setNombreEmpresaIatx(serv.getNombreServicioEmpresa());
		pagoPendiente.setTipoPago(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA);
		pagoPendiente.setCodigoEmpresa(cuit);
		pagoPendiente.setIdentificacionDeFactura("");
		// Se agregan los datos de la deuda
		DatosPagoAutomaticoEntity datosPagoAutomatico = new DatosPagoAutomaticoEntity();
		datosPagoAutomatico.setTope(formatearImporte(serv.getLimiteAdhesionDDI()));
		datosPagoAutomatico.setTipoCuenta(TipoCuenta.fromCodigo(serv.getTipoCuentaDebito()));
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroSucursal(serv.getSucursalCuentaDebito());
		identificacionCuenta.setNroCuentaProducto(serv.getNroCuentaProdDebito());
		datosPagoAutomatico.setIdentificacionCuenta(identificacionCuenta);
		pagoPendiente.setDatosPagoAutomatico(datosPagoAutomatico);
		pagoPendiente.setCuitEmpresa(cuit);
		pagoPendiente.setOrdenFirmante(serv.getNroOrdenFirmante());
		return pagoPendiente;
	}

	/**
	 * Formatear importe.
	 *
	 * @param importe the importe
	 * @return the big decimal
	 * @throws DAOException the DAO exception
	 */
	private BigDecimal formatearImporte(String importe) throws DAOException {
		if (importe == null || importe.trim().isEmpty()) {
			return null;
		}
		return new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico(importe));
	}

	/**
	 * Completar pago con deuda.
	 *
	 * @param pagoPendiente the pago pendiente
	 * @param deudaServicio the deuda servicio
	 * @throws DAOException
	 * @throws ParseException
	 */
	private void completarPagoConDeuda(PagoPendiente pagoPendiente, ComprobanteDebitoAutomatico deudaServicio)
	        throws DAOException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA_CNSDDIDERE);
		pagoPendiente.setImporte(formatearImporte(deudaServicio.getImporteDebitoOriginalDDI()));
		pagoPendiente.setVencimiento(formatter.parse(deudaServicio.getFechaVencimientoDDI()));
		pagoPendiente.getDatosPagoAutomatico().setIdDebitoDDI(deudaServicio.getIdDebitoDDI());
		pagoPendiente.getDatosPagoAutomatico()
		        .setImporteDebitoOriginalDDI(formatearImporte(deudaServicio.getImporteDebitoOriginalDDI()));
		pagoPendiente.getDatosPagoAutomatico().setImporte(formatearImporte(deudaServicio.getImporteDebito()));
		pagoPendiente.getDatosPagoAutomatico().setVencimiento(formatter.parse(deudaServicio.getFechaVencimientoDDI()));
		pagoPendiente.getDatosPagoAutomatico().setCodigoEstadoDDI(deudaServicio.getCodigoEstadoDDI());
	}

	/**
	 * Obtener cuentas habilitadas.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<List<CuentaPagoMisCuentas>> obtenerCuentasHabilitadas(Cliente cliente) {
		Respuesta<List<CuentaPagoMisCuentas>> respuesta = new Respuesta<List<CuentaPagoMisCuentas>>();
		try {
			List<CuentaPagoMisCuentas> cuentasHabilitadas = banelcoDAO.obtenerCuentasBanelcoHabilitadas(cliente);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			respuesta.setRespuesta(cuentasHabilitadas);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta.setRespuesta(null);
			respuesta.setRespuestaVacia(true);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			itemMensajeRespuesta.setMensaje(e.getMessage());
			mensajesList.add(itemMensajeRespuesta);
			respuesta.setItemMensajeRespuesta(mensajesList);
		}

		return respuesta;
	}

	/**
	 * Obtiene el listado de prestamos pendientes .
	 *
	 * @param cliente the cliente
	 * @return the pagos pendientes prestamo
	 */
	@Override
	public Respuesta<List<PagoPendiente>> getPagosPendientesPrestamo(Cliente cliente) {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		List<PrestamoDTO> prestamos = null;
		ObtenerPrestamosInDTO obtenerPrestamosInDTO = new ObtenerPrestamosInDTO();
		try {
			obtenerPrestamosInDTO.setCliente(cliente);
			obtenerPrestamosInDTO.setIsTodos(true);
			Respuesta<List<PrestamoDTO>> respuestaPrestamosDTO = prestamoBO.obtenerPrestamos(obtenerPrestamosInDTO);
			respuesta.setEstadoRespuesta(respuestaPrestamosDTO.getEstadoRespuesta());
			if (EstadoRespuesta.ERROR.equals(respuestaPrestamosDTO.getEstadoRespuesta())) {
				return generarRespuestaError(DISCRIMINAR_PAGOS_AUTOMATICOS_WARNING_MSG);
			}
			if (respuestaPrestamosDTO.getRespuesta() != null) {
				prestamos = respuestaPrestamosDTO.getRespuesta();
				if (CollectionUtils.isNotEmpty(prestamos)) {
					respuesta.setRespuesta(armarListaPrestamosPendientes(prestamos));
				}
			}
			return respuesta;
		} catch (Exception ex) {
			LOGGER.error("Ha ocurrido un error al obtener los pagos pendientes de Prestamos ", ex);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
			return respuesta;
		}
	}

	/**
	 * Armar lista prestamos pendientes.
	 *
	 * @param prestamos the prestamos
	 * @return the list
	 */
	private List<PagoPendiente> armarListaPrestamosPendientes(List<PrestamoDTO> prestamos) {
		List<PagoPendiente> pagosPendientes = new ArrayList<PagoPendiente>();
		for (PrestamoDTO prestamoDTO : prestamos) {
			Prestamo prestamo = prestamoDTO.getPrestamo();
			PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
			pagoPendientePrestamo.setTipoPrestamo(prestamo.getClaseCuenta());
			pagoPendientePrestamo.setVencimiento(prestamo.getVencimientoCuota());
			pagoPendientePrestamo.setNumeroCuotas(prestamo.getNumeroRecibo());
			pagoPendientePrestamo.setDivisa(prestamo.getDivisa());
			pagoPendientePrestamo.setImporte(prestamo.getImporteTotalCuota());
			pagoPendientePrestamo
			        .setCodigoClienteEmpresa(StringUtils.right(prestamo.getNumeroCuentaProducto(), ENTERO_DOCE));
			pagoPendientePrestamo.setTipoPago(TipoDePagoEnum.PRESTAMODEBITOENCUENTA);
			pagoPendientePrestamo.setPrestamo(prestamo);
			pagosPendientes.add(pagoPendientePrestamo);
		}
		return pagosPendientes;
	}

	/**
	 * Gets the pagos pendientes tarjetas.
	 *
	 * @param cliente the cliente
	 * @return the pagos pendientes tarjetas
	 */
	@Override
	public Respuesta<List<PagoPendiente>> getPagosPendientesTarjetas(Cliente cliente) {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		try {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			List<DatosTarjeta> listaDatosTarjetas = new ArrayList<DatosTarjeta>();
			int cantidadErrores = 0;
			int cantidadTarjetas = 0;
			for (Cuenta cuenta : cliente.getCuentas()) {
				TipoCuentaTarjeta tipoCuentaTarjeta = TipoCuentaTarjeta.fromCodigo(cuenta.getTipoCuenta());
				if (tipoCuentaTarjeta != null && TITULAR_TARJETA_CODIGO.equals(cuenta.getCodigoTitularidad())) {
					try {
						cantidadTarjetas++;
						DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
						DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
						if (datosTarjeta != null) {
							datosTarjeta.setTipoCuentaTarjeta(tipoCuentaTarjeta);
							datosTarjeta.setNumeroTarjeta(cuenta.getNroTarjetaCredito());
							// convertir el signo
							datosTarjeta.setSaldoPesosTC(datosTarjeta.getSaldoPesosTC().negate());
							datosTarjeta.setSaldoDolaresTC(datosTarjeta.getSaldoDolaresTC().negate());
							datosTarjeta.setAlias(cuenta.getAlias());
							listaDatosTarjetas.add(datosTarjeta);
						}
						List<DatosTarjeta> pagosProgramadosList = datosTarjetaDAO.obtenerPagosProgramados(cliente,
						        cuenta);
						for (DatosTarjeta pagoProgramado : pagosProgramadosList) {
							if (pagoProgramado.getFechaPagoProgramado() != null) {
								pagoProgramado.setTipoCuentaTarjeta(tipoCuentaTarjeta);
								pagoProgramado.setNumeroTarjeta(cuenta.getNroTarjetaCredito());
								listaDatosTarjetas.add(pagoProgramado);
							}
						}
					} catch (DAOException ex) {
						LOGGER.error("Ha ocurrido un error al obtener los pagos pendientes de TC ", ex);
						respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
						ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
						        DISCRIMINAR_PAGOS_AUTOMATICOS_WARNING_MSG);
						itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
						respuesta.add(itemMensajeRespuesta);
						cantidadErrores++;
					}
				}
			}
			if (cantidadTarjetas != 0 && cantidadErrores == cantidadTarjetas) {
				return generarRespuestaError(DISCRIMINAR_PAGOS_AUTOMATICOS_WARNING_MSG);
			}
			respuesta.setRespuesta(armarListaTarjetasPendientes(listaDatosTarjetas));
			return respuesta;

		} catch (Exception ex) {
			LOGGER.error("Ha ocurrido un error al obtener los pagos pendientes de TC ", ex);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
			return respuesta;
		}
	}

	/**
	 * Armar lista tarjetas pendientes.
	 *
	 * @param datosTarjetas the datos tarjetas
	 * @return the list
	 * @update Manuel.Vargas 41673 logica para el alias.
	 */
	private List<PagoPendiente> armarListaTarjetasPendientes(List<DatosTarjeta> datosTarjetas) {
		List<PagoPendiente> pagoPendientes = new ArrayList<PagoPendiente>();
		for (DatosTarjeta datosTarjeta : datosTarjetas) {
			PagoPendiente pagoPendiente = new PagoPendiente();
			StringBuilder sb = new StringBuilder("Tarjeta ");
			sb.append(datosTarjeta.getTipoCuentaTarjeta().getDescripcion());
			sb.append(" ");
			if (datosTarjeta.getAlias() != null && datosTarjeta.getAlias() != "") {
				sb.append("\"").append(datosTarjeta.getAlias()).append("\"");
				pagoPendiente.setAlias(true);
			} else {
				sb.append(ISBANStringUtils.mascaraTarjetaCredito(datosTarjeta.getNumeroTarjeta(),
				        datosTarjeta.getTipoCuentaTarjeta().getCodigo()));
				pagoPendiente.setAlias(false);
			}
			pagoPendiente.setNombreEmpresa(sb.toString());
			pagoPendiente.setNroTarjetaCredito(datosTarjeta.getNumeroTarjeta());
			pagoPendiente.setNumeroCuentaDeTarjetaCredito(datosTarjeta.getNumeroCuentaDeTarjetaCredito());
			pagoPendiente.setNombreEmpresaAbreviado(datosTarjeta.getTipoCuentaTarjeta().getAbreviatura());
			pagoPendiente.setTipoPago(TipoDePagoEnum.TARJETAPAGOPUNTUAL);
			pagoPendiente.setTipoCuentaTarjeta(datosTarjeta.getTipoCuentaTarjeta());

			if (datosTarjeta.getFechaPagoProgramado() != null) {
				pagoPendiente.setTipoPago(TipoDePagoEnum.PAGOPROGRAMADO);
				pagoPendiente.setFechaPagoProgramado(datosTarjeta.getFechaPagoProgramado());
				datosTarjeta.setFechaVencimientoLiquidacionTC(datosTarjeta.getFechaPagoProgramado());
			} else if (datosTarjeta.getFormaPagoTarjetaCredito().equalsIgnoreCase(DEBITO_AUTOMATICO_TOTAL_03)
			        || datosTarjeta.getFormaPagoTarjetaCredito().equalsIgnoreCase(DEBITO_AUTOMATICO_TOTAL_05)) {
				setPagoMinimo(pagoPendiente, datosTarjeta);
				pagoPendiente.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL);
			} else if (datosTarjeta.getFormaPagoTarjetaCredito().equalsIgnoreCase(DEBITO_AUTOMATICO_MINIMO_02)
			        || datosTarjeta.getFormaPagoTarjetaCredito().equalsIgnoreCase(DEBITO_AUTOMATICO_MINIMO_04)) {
				setPagoMinimo(pagoPendiente, datosTarjeta);
				pagoPendiente.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO);
			}
			pagoPendiente.setVencimiento(datosTarjeta.getFechaVencimientoLiquidacionTC());
			pagoPendiente.setDivisa(DivisaEnum.PESO);

			// datos de importes en pesos y dolares
			pagoPendiente.setImporte(
			        datosTarjeta.getSaldoPesosTC() == null ? BigDecimal.ZERO : datosTarjeta.getSaldoPesosTC());
			pagoPendiente.setTipoCuentaBancoPesos(datosTarjeta.getTipoCuentaBancoPesos());
			pagoPendiente.setSucursalBancoPesos(new Integer(datosTarjeta.getSucursalBancoPesos()).toString());
			pagoPendiente.setNumeroCuentaBancoPesos(datosTarjeta.getNumeroCuentaBancoPesos());
			pagoPendiente.setImporteUSS(
			        datosTarjeta.getSaldoDolaresTC() == null ? BigDecimal.ZERO : datosTarjeta.getSaldoDolaresTC());
			pagoPendiente.setTipoCuentaBancoDolares(datosTarjeta.getTipoCuentaBancoDolares());
			pagoPendiente.setSucursalBancoDolares(new Integer(datosTarjeta.getSucursalBancoDolares()).toString());
			pagoPendiente.setNumeroCuentaBancoDolares(datosTarjeta.getNumeroCuentaBancoDolares());

			if (TipoDePagoEnum.TARJETAPAGOPUNTUAL.equals(pagoPendiente.getTipoPago())) {
				pagoPendiente.setPagoMinimo(datosTarjeta.getPagoMinimoPesosTC());
			}
			pagoPendientes.add(pagoPendiente);
		}
		return pagoPendientes;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoPendiente the pago pendiente
	 * @param datosTarjeta  the datos tarjeta
	 */
	private void setPagoMinimo(PagoPendiente pagoPendiente, DatosTarjeta datosTarjeta) {
		// TODO llenar datos de pago automatico
		if (!pagoPendiente.isPagoAutomatico()) {
			pagoPendiente.setDatosPagoAutomatico(new DatosPagoAutomaticoEntity());
		}
		pagoPendiente.setPagoMinimo(datosTarjeta.getPagoMinimoPesosTC());
	}

	/**
	 * Generar respuesta error.
	 *
	 * @param mensaje the mensaje
	 * @return the respuesta
	 */
	private Respuesta<List<PagoPendiente>> generarRespuestaError(String mensaje) {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		respuesta.setRespuesta(null);
		respuesta.setRespuestaVacia(true);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		itemMensajeRespuesta.setMensaje(mensaje);
		mensajesList.add(itemMensajeRespuesta);
		respuesta.setItemMensajeRespuesta(mensajesList);
		return respuesta;
	}

	/**
	 * Armar lista pagos pendientes. Implementa le envio de un flag para indicar que
	 * hay un numero de VEP.
	 *
	 * @param deudaInformadaList                 the deuda informada list
	 * @param deudaPagoAutomaticoList            the deuda pago automatico list
	 * @param errorConsultaDebitosAutomaticosPMC the error consulta debitos
	 *                                           automaticos pmc
	 * @return the list
	 * @throws BusinessException the business exception
	 * @last modification manuel.Vargas (nro.VEP)
	 */
	private List<PagoPendiente> armarListaPagosPendientes(List<DeudaInformada> deudaInformadaList,
	        List<DeudaPagoAutomaticoEntity> deudaPagoAutomaticoList, boolean errorConsultaDebitosAutomaticosPMC)
	        throws BusinessException {

		// Realizo un sort primero de los archivos para realizar el "merge" y
		// devolver la lista ya ordenada
		Collections.sort(deudaInformadaList);
		Collections.sort(deudaPagoAutomaticoList);
		ArrayList<PagoPendiente> lista = new ArrayList<PagoPendiente>();
		PagoPendiente elemento = null;
		MedioPago medioDePago = null;
		for (DeudaInformada deuda : deudaInformadaList) {
			String idEmpresa = deuda.getEmpresa();
			medioDePago = obtenerMedioDePago(idEmpresa);
			if (!esPagoPendienteValido(medioDePago)) {
				continue;
			}
			elemento = new PagoPendiente();
			elemento.setCodigoClienteEmpresa(deuda.getIdClienteEmpresa());
			elemento.setCodigoEmpresa(idEmpresa);
			elemento.setNombreEmpresa(medioDePago.getNombreFantasia());
			elemento.setNombreEmpresaIatx(deuda.getEmpresa());
			elemento.setMedioPago(medioDePago);
			elemento.setDivisa(deuda.getDivisa());
			elemento.setIdentificacionDeFactura(deuda.getFactura());

			elemento.setEditable(esImporteEditable(deuda));
			if (medioDePago != null) {
				Integer tipoImporte = new Integer(medioDePago.getTipoImporte());
				if (tipoImporte != null && (tipoImporte == 1 || tipoImporte == 2)) {
					elemento.setEditable(true);
				} else {
					elemento.setEditable(false);
				}
			}

			if (deuda.getDivisa().equals(DivisaEnum.DOLAR)) {
				elemento.setImporteUSS(deuda.getImporte());
			} else {
				elemento.setImporte(deuda.getImporte());
			}

			elemento.setVencimiento(deuda.getVencimiento());
			elemento.setTipoPagoEmpresa(consultarTipoPago(medioDePago));

			if (deuda.getTextoEnPantalla() != null) {
				elemento.setDatosAdicionales(deuda.getTextoEnPantalla().trim());
			} else {
				elemento.setDatosAdicionales("");
			}
			if (deuda.getDescripcion() != null) {
				elemento.setInformacionAdicional(deuda.getDescripcion().trim());// deuda.getDescripcion()
			} else {
				elemento.setInformacionAdicional("");
			}
			// correccion para numero de VEP. regla tipo de pago 3 y tipo de
			// empresa F.
			if (medioDePago.getTipoEmpresa().equals(TIPO_DE_EMPRESA_F)
			        && medioDePago.getTipoPago().equals(TIPO_DE_PAGO_3)) {
				elemento.setNumeroDeVEP(deuda.getFactura().substring(0, 11));
				elemento.setPeriodo(deuda.getFactura().substring(12, 15));
				elemento.setCantidadCuotas(deuda.getFactura().substring(17, 19));
			}
			// fin correccion
			// Merge con pago automatico
			mergePagoAutomatico(elemento, deuda, deudaPagoAutomaticoList);
			if (errorConsultaDebitosAutomaticosPMC) {
				elemento.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASERRORCONSULTADEBITOAUTOMATICO);
			} else {
				if (elemento.isPagoAutomatico()) {
					elemento.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASDEBITO);
				} else {
					elemento.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL);
				}
			}
			LOGGER.info(elemento.toString());
			lista.add(elemento);
		}
		lista.addAll(obtenerServiciosAdheridosPagoAutomaticoSinDeudaInformada(lista, deudaPagoAutomaticoList));
		return lista;
	}

	/**
	 * Setea si es editableDetalle o no cada elemeto de la lista de pagos
	 * Pendientes, segun tipoPago es= 'PAGOMISCUENTASDEBITO' o tipoPago es=
	 * 'PAGOMISCUENTASPUNTUAL'
	 * 
	 * @param deudaMergeada
	 * @return
	 */
	private List<PagoPendiente> setearListaPagosPendientesEditables(List<PagoPendiente> deudaMergeada) {
		for (PagoPendiente pagoPendiente : deudaMergeada) {
			pagoPendiente.setEditableDetalle(false);
			if (pagoPendiente.getTipoPago().name().equalsIgnoreCase(TipoDePagoEnum.PAGOMISCUENTASDEBITO.toString())
			        || (pagoPendiente.getTipoPago().name()
			                .equalsIgnoreCase(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL.toString()))) {
				pagoPendiente.setEditableDetalle(true);
			}
		}
		return deudaMergeada;
	}

	/**
	 * Obtener servicios adheridos pago automatico sin deuda informada.
	 *
	 * @param lista                   the lista
	 * @param deudaPagoAutomaticoList the deuda pago automatico list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	private List<PagoPendiente> obtenerServiciosAdheridosPagoAutomaticoSinDeudaInformada(ArrayList<PagoPendiente> lista,
	        List<DeudaPagoAutomaticoEntity> deudaPagoAutomaticoList) throws BusinessException {
		List<PagoPendiente> serviciosSinDeudaInformada = new ArrayList<PagoPendiente>();
		for (DeudaPagoAutomaticoEntity deudaPagoAutomatico : deudaPagoAutomaticoList) {
			if (!existeEmpresaEnLista(deudaPagoAutomatico, lista)) {
				MedioPago medioDePago = obtenerMedioDePago(deudaPagoAutomatico.getEmpresa());
				if (!esPagoPendienteValido(medioDePago)) {
					continue;
				}
				serviciosSinDeudaInformada.add(armarPagoViewServicioSinDeuda(deudaPagoAutomatico, medioDePago));
			}
		}
		return serviciosSinDeudaInformada;
	}

	/**
	 * Existe empresa en lista.
	 *
	 * @param deudaPagoAutomatico the deuda pago automatico
	 * @param lista               the lista
	 * @return the boolean
	 */
	private Boolean existeEmpresaEnLista(DeudaPagoAutomaticoEntity deudaPagoAutomatico,
	        ArrayList<PagoPendiente> lista) {
		for (PagoPendiente pago : lista) {
			if (StringUtils.equals(deudaPagoAutomatico.getEmpresa(), pago.getCodigoEmpresa())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Armar pago view servicio sin deuda.
	 *
	 * @param deudaPagoAutomatico the deuda pago automatico
	 * @param medioDePago         the medio de pago
	 * @return the pago pendiente
	 */
	private PagoPendiente armarPagoViewServicioSinDeuda(DeudaPagoAutomaticoEntity deudaPagoAutomatico,
	        MedioPago medioDePago) {
		PagoPendiente pagoPendiente = new PagoPendiente();
		pagoPendiente.setCodigoClienteEmpresa(deudaPagoAutomatico.getIdClienteEmpresa());
		pagoPendiente.setCodigoEmpresa(deudaPagoAutomatico.getEmpresa());
		pagoPendiente.setNombreEmpresa(medioDePago.getNombreFantasia());
		pagoPendiente.setNombreEmpresaIatx(deudaPagoAutomatico.getEmpresa());
		pagoPendiente.setMedioPago(medioDePago);
		pagoPendiente.setDivisa(deudaPagoAutomatico.getDivisa());
		pagoPendiente.setIdentificacionDeFactura(deudaPagoAutomatico.getFactura());
		pagoPendiente.setEditable(true);
		if (medioDePago != null) {
			Integer tipoImporte = new Integer(medioDePago.getTipoImporte());
			if (tipoImporte != null && (tipoImporte == 1 || tipoImporte == 2)) {
				pagoPendiente.setEditable(true);
			} else {
				pagoPendiente.setEditable(false);
			}
		}
		if (deudaPagoAutomatico.getDivisa().equals(DivisaEnum.DOLAR)) {
			pagoPendiente.setImporteUSS(deudaPagoAutomatico.getImporte());
		} else {
			pagoPendiente.setImporte(deudaPagoAutomatico.getImporte());
		}

		pagoPendiente.setVencimiento(null);
		pagoPendiente.setTipoPagoEmpresa(consultarTipoPago(medioDePago));
		pagoPendiente.setDatosAdicionales("");
		pagoPendiente.setInformacionAdicional("");
		if (medioDePago.getTipoEmpresa().equals(TIPO_DE_EMPRESA_F)
		        && medioDePago.getTipoPago().equals(TIPO_DE_PAGO_3)) {
			pagoPendiente.setNumeroDeVEP(deudaPagoAutomatico.getFactura().substring(0, 11));
			pagoPendiente.setPeriodo(deudaPagoAutomatico.getFactura().substring(12, 15));
			pagoPendiente.setCantidadCuotas(deudaPagoAutomatico.getFactura().substring(17, 19));
		}
		pagoPendiente.setDatosPagoAutomatico(obtenerDatosPagoAutomaticoSinDeuda(deudaPagoAutomatico));
		pagoPendiente.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASDEBITO);
		return pagoPendiente;
	}

	/**
	 * Obtener datos pago automatico sin deuda.
	 *
	 * @param pagoAutomatico the pago automatico
	 * @return the datos pago automatico entity
	 */
	private DatosPagoAutomaticoEntity obtenerDatosPagoAutomaticoSinDeuda(DeudaPagoAutomaticoEntity pagoAutomatico) {
		DatosPagoAutomaticoEntity datosPagoAutomatico = new DatosPagoAutomaticoEntity();
		datosPagoAutomatico.setEmpresa(pagoAutomatico.getEmpresa());
		datosPagoAutomatico.setIdClienteEmpresa(pagoAutomatico.getIdClienteEmpresa());
		datosPagoAutomatico.setVencimiento(pagoAutomatico.getVencimiento());
		datosPagoAutomatico.setDivisa(pagoAutomatico.getDivisa());
		datosPagoAutomatico.setImporte(pagoAutomatico.getImporte());
		datosPagoAutomatico.setFactura(pagoAutomatico.getFactura());
		datosPagoAutomatico.setFechaStopDebit(pagoAutomatico.getFechaStopDebit());
		datosPagoAutomatico.setTipoCuenta(pagoAutomatico.getTipoCuenta());
		datosPagoAutomatico.setIdentificacionCuenta(pagoAutomatico.getIdentificacionCuenta());
		datosPagoAutomatico.setTope(pagoAutomatico.getTope());
		return datosPagoAutomatico;
	}

	/**
	 * Es importe editable.
	 *
	 * @param deuda the deuda
	 * @return true, if successful
	 */
	private boolean esImporteEditable(DeudaInformada deuda) {
		return deuda.getVencimiento() == null;
	}

	/**
	 * Retorna si el medio de pago opera con o sin factura, o de forma mixta.
	 *
	 * @param medioDePago the medio de pago
	 * @return {@linkplain TipoPagoEmpresaPMC} , null si no se ha podido determinar
	 */
	private TipoPagoEmpresaPMC consultarTipoPago(MedioPago medioDePago) {

		TipoPagoEmpresaPMC resultado = null;
		Integer tipoPago = medioDePago.getTipoPago();
		String tipoEmpresa = medioDePago.getTipoEmpresa();
		if (tipoPago != null && tipoEmpresa != null) {
			// sin facturas
			// (PES-Tipo_Pago = 2 o PES-Tipo_Pago = 1) y PES-Tipo_Empresa <> T
			// NOTA: Para el caso de TP = 1 y TE <> T, el servicio NO retorna TI
			// NOTA I: Para TP=2, no existe DA = ' '
			// NOTA II: Para TP=2 y TE = M, solo se permite TI = 0 (no permite
			// modificar importe)
			if (!TIPO_DE_EMPRESA_T.equalsIgnoreCase(tipoEmpresa)
			        && (TIPO_DE_PAGO_1 == tipoPago || TIPO_DE_PAGO_2 == tipoPago)) {
				resultado = TipoPagoEmpresaPMC.SIN_FACTURA;
			}
			// con factura
			// PES-Tipo_Pago = 3
			// NOTA: Para TP=3 y TE = F, solo se permite TI = 0 (no permite
			// modificar importe)
			if (TIPO_DE_PAGO_3 == tipoPago) {
				resultado = TipoPagoEmpresaPMC.CON_FACTURA;
			}
			// FACTURA MIXTA
			// PES-Tipo_Pago = 1 y PES-Tipo_Empresa = T
			if (TIPO_DE_PAGO_1 == tipoPago && TIPO_DE_EMPRESA_T.equalsIgnoreCase(tipoEmpresa)) {
				resultado = TipoPagoEmpresaPMC.MIXTO;
			}
		}
		return resultado;
	}

	/**
	 * Es pago pendiente valido.
	 *
	 * @param medioDePago the medio de pago
	 * @return true, if successful
	 */
	private boolean esPagoPendienteValido(MedioPago medioDePago) {
		if (medioDePago == null) {
			LOGGER.debug(ERROR_MEDIO_PAGO_INEXISTENTE);
			return false;
		}
		// FIXME segun documento funcional excluye si TP=3 y TE=R, segun OBP
		// viejo excluye si TE=R
		if (TIPO_DE_PAGO_3 == medioDePago.getTipoPago()
		        && TIPO_DE_EMPRESA_R.equalsIgnoreCase(medioDePago.getTipoEmpresa())) {
			return false;
		}
		return true;
	}

	/**
	 * Merge pago automatico.
	 *
	 * @param elemento                the elemento
	 * @param deuda                   the deuda
	 * @param deudaPagoAutomaticoList the deuda pago automatico list
	 */
	private void mergePagoAutomatico(PagoPendiente elemento, DeudaInformada deuda,
	        List<DeudaPagoAutomaticoEntity> deudaPagoAutomaticoList) {
		Collections.sort(deudaPagoAutomaticoList);
		for (DeudaPagoAutomaticoEntity deudaPagoAutomaticoEntity : deudaPagoAutomaticoList) {
			if (StringUtils.isNotBlank(deuda.getEmpresa())
			        && deuda.getEmpresa().equals(deudaPagoAutomaticoEntity.getEmpresa())
			        && StringUtils.isNotBlank(deuda.getIdClienteEmpresa())
			        && deuda.getIdClienteEmpresa().equals(deudaPagoAutomaticoEntity.getIdClienteEmpresa())) {
				DatosPagoAutomaticoEntity datosPagoAutomatico = new DatosPagoAutomaticoEntity();
				datosPagoAutomatico.setEmpresa(deudaPagoAutomaticoEntity.getEmpresa());
				datosPagoAutomatico.setIdClienteEmpresa(deudaPagoAutomaticoEntity.getIdClienteEmpresa());
				datosPagoAutomatico.setVencimiento(deudaPagoAutomaticoEntity.getVencimiento());
				datosPagoAutomatico.setDivisa(deudaPagoAutomaticoEntity.getDivisa());
				datosPagoAutomatico.setImporte(deudaPagoAutomaticoEntity.getImporte());
				datosPagoAutomatico.setFactura(deudaPagoAutomaticoEntity.getFactura());
				datosPagoAutomatico.setFechaStopDebit(deudaPagoAutomaticoEntity.getFechaStopDebit());
				datosPagoAutomatico.setTipoCuenta(deudaPagoAutomaticoEntity.getTipoCuenta());
				datosPagoAutomatico.setIdentificacionCuenta(deudaPagoAutomaticoEntity.getIdentificacionCuenta());
				datosPagoAutomatico.setTope(deudaPagoAutomaticoEntity.getTope());
				elemento.setDatosPagoAutomatico(datosPagoAutomatico);
				break;
			}
		}
	}

	/**
	 * Devuelve el {@link MedioPago} para el codigo de empresa.
	 *
	 * @param idEmpresa codigo de empresa
	 * @return null si no existe en medios de pago
	 * @throws BusinessException si hubo error al consultar los datos de la empresa
	 */
	private MedioPago obtenerMedioDePago(String idEmpresa) throws BusinessException {
		Respuesta<MedioPago> respuesta = mediosPagoBO.getByCodigo(idEmpresa);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuesta.getRespuesta();
		} else if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
			LOGGER.error(ERROR_MEDIO_PAGO_INEXISTENTE);
			return null;
		}
		throw new BusinessException(OBTENER_MEDIO_PAGO_ERROR + idEmpresa);
	}

	/**
	 * Devuelve el {@link MedioPago} para el código de empresa.
	 *
	 * @param serv the serv
	 * @return null si no existe en medios de pago
	 * @throws BusinessException si hubo error al consultar los datos de la empresa
	 */
	private MedioPago obtenerMedioDePago(EmpresaAdheridaEntity serv) throws BusinessException {
		MedioPago medioDePago = new MedioPago();
		Respuesta<MedioPago> respuesta = buscadorEmpresaBO.searchEmpresaPorCuitYServicio(serv.getCuitEmpresa(),
		        serv.getNombreServicioEmpresa());
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && respuesta.getRespuesta() != null) {
			medioDePago = respuesta.getRespuesta();
		}
		return medioDePago;
	}

	/**
	 * Obtener recargas agendadas tarjetas recargables.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<List<PagoPendiente>> obtenerRecargasAgendadasTarjetasRecargables(Cliente cliente) {
		return new Respuesta<List<PagoPendiente>>();
	}

	/**
	 * Gets the datos tarjetas.
	 *
	 * @param cliente the cliente
	 * @return the datos tarjetas
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<DatosTarjeta> getDatosTarjetas(Cliente cliente) throws BusinessException {

		List<DatosTarjeta> datosTarjetas = new ArrayList<DatosTarjeta>();
		for (Cuenta cuenta : cliente.getCuentasTarjetaDeCredito()) {
			try {
				DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
				DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
				if (datosTarjeta != null) {
					datosTarjeta.setCodigoTitularidad(cuenta.getCodigoTitularidad());
					datosTarjeta.setTipoCuentaTarjeta(TipoCuentaTarjeta.fromCodigo(cuenta.getTipoCuenta()));
					datosTarjeta.setNumeroTarjeta(cuenta.getNroTarjetaCredito());
					datosTarjeta.setAlias(cuenta.getAlias());
					datosTarjetas.add(datosTarjeta);
				}

			} catch (DAOException e) {
				LOGGER.error(ERROR_DAO, e);
				throw new BusinessException(e);
			}
		}

		return datosTarjetas;
	}

	/**
	 * Obtiene los datos iniciales para el pago de tarjeta de credito.
	 *
	 * @param cliente the cliente
	 * @return the datos iniciales pago tarjetas
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<DatosTarjeta> getDatosInicialesPagoTarjetas(Cliente cliente) throws BusinessException {
		ArrayList<DatosTarjeta> datosTarjetas = new ArrayList<DatosTarjeta>();
		for (Cuenta cuenta : obtenerCuentasClienteTarjetaDeCredito(cliente)) {
			if (cuenta.esRecargable()) {
				continue;
			}
			try {
				DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, false);
				DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
				if (datosTarjeta != null) {
					datosTarjeta.setCodigoTitularidad(cuenta.getCodigoTitularidad());
					datosTarjeta.setTipoCuentaTarjeta(
					        TipoCuentaTarjeta.getTipoCuentaTarjetaFromTipoCuenta(cuenta.getTipoCuentaEnum()));
					datosTarjeta.setNumeroTarjeta(cuenta.getNroTarjetaCredito());
					datosTarjeta.setAlias(cuenta.getAlias());
					datosTarjetas.add(datosTarjeta);
				}

			} catch (DAOException e) {
				LOGGER.error(ERROR_DAO, e);
				throw new BusinessException(e);
			}
		}
		datosTarjetas.trimToSize();
		return datosTarjetas;
	}

	/**
	 * Obtener cuentas cliente de tarjeta de credito.
	 *
	 * @param cliente the cliente
	 * @return the array list
	 */
	private List<Cuenta> obtenerCuentasClienteTarjetaDeCredito(Cliente cliente) {
		List<Cuenta> cuentas = cliente.getCuentasTarjetaDeCredito();
		final Map<Cuenta, Integer> hashPrioridad = new HashMap<Cuenta, Integer>();
		for (Cuenta cuenta : cuentas) {
			hashPrioridad.put(cuenta, getPrioridadTarjeta(cuenta));
		}

		Collections.sort(cuentas, new Comparator<Cuenta>() {
			@Override
			public int compare(Cuenta tarjeta1, Cuenta tarjeta2) {
				return hashPrioridad.get(tarjeta1).compareTo(hashPrioridad.get(tarjeta2));
			}
		});
		return cuentas;
	}

	/**
	 * Gets the prioridad tarjeta.
	 *
	 * @param cuenta the cuenta
	 * @return the prioridad tarjeta
	 */
	private int getPrioridadTarjeta(Cuenta cuenta) {
		int prioridad = 8;
		if (cuenta.esRecargable()) {
			prioridad = 7;
		} else if (cuenta.esTitular()) {
			if (cuenta.esTarjetaVisa()) {
				prioridad = 1;
			} else if (cuenta.esTarjetaAmex()) {
				prioridad = 2;
			} else if (cuenta.esTajetaMaster()) {
				prioridad = 3;
			}
		} else if (cuenta.esAdicional()) {
			if (cuenta.esTarjetaVisa()) {
				prioridad = 4;
			} else if (cuenta.esTarjetaAmex()) {
				prioridad = 5;
			} else if (cuenta.esTajetaMaster()) {
				prioridad = 6;
			}
		}
		return prioridad;
	}

	/**
	 * Obtener datos tarjetas pago puntual.
	 *
	 * @param cliente the cliente
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TarjetasAdhesionDebitoView> obtenerDatosTarjetasPagoPuntual(Cliente cliente) throws BusinessException {
		List<TarjetasAdhesionDebitoView> listaTarjetas = new ArrayList<TarjetasAdhesionDebitoView>();

		for (Cuenta cuenta : cliente.getCuentasTarjetaDeCreditoTitular()) {
			try {
				DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
				DatosTarjeta datos = datosTarjetaBuilder.build();
				if (DATO_TARJETA_PAGO_PUNTUAL_VISA.equals(datos.getFormaPagoTarjetaCredito())
				        || DATO_TARJETA_PAGO_PUNTUAL_AMEX.equals(datos.getFormaPagoTarjetaCredito())) {
					TipoCuentaTarjeta tipoCuentaTarjeta = TipoCuentaTarjeta.fromCodigo(cuenta.getTipoCuenta());

					String nroTarjetaEnmascarado = TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(),
					        TipoCuentaTarjeta.fromCodigo(cuenta.getTipoCuenta()));
					listaTarjetas.add(new TarjetasAdhesionDebitoView(tipoCuentaTarjeta, cuenta.getAlias(),
					        nroTarjetaEnmascarado));
				}

			} catch (DAOException e) {
				LOGGER.error(ERROR_DAO, e);
				throw new BusinessException(e);
			}
		}
		return listaTarjetas;
	}

	/**
	 * Gets the datos tarjetas con adicionales.
	 *
	 * @param cliente the cliente
	 * @return the datos tarjetas con adicionales
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<DatosTarjeta> getDatosTarjetasConAdicionales(Cliente cliente) throws BusinessException {

		List<DatosTarjeta> datosTarjetas = new ArrayList<DatosTarjeta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			TipoCuentaTarjeta tipoCuentaTarjeta = TipoCuentaTarjeta.fromCodigo(cuenta.getTipoCuentaSinUnificar());
			if (tipoCuentaTarjeta != null) {
				try {
					DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
					DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
					if (datosTarjeta != null) {
						datosTarjeta.setCodigoTitularidad(cuenta.getCodigoTitularidad());
						datosTarjeta.setTipoCuentaTarjeta(tipoCuentaTarjeta);
						datosTarjeta.setNumeroTarjeta(cuenta.getNroTarjetaCredito());
						datosTarjetas.add(datosTarjeta);
					}

				} catch (DAOException e) {
					LOGGER.error("Error al traer las tarjetas con adicionales.", e);
					throw new BusinessException(e);
				}
			}
		}

		return datosTarjetas;
	}

	/**
	 * Tiene pagos programados.
	 *
	 * @param cliente      the cliente
	 * @param datosTarjeta the datos tarjeta
	 * @param fechaLimite  the fecha limite
	 * @return the boolean
	 * @throws BusinessException the business exception
	 */
	@Override
	public Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite)
	        throws BusinessException {
		try {
			return datosTarjetaDAO.tienePagosProgramados(cliente, datosTarjeta, fechaLimite);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Modificar adhesion.
	 *
	 * @param cliente            the cliente
	 * @param servicioAdherido   the servicio adherido
	 * @param cuentaDebito       the cuenta debito
	 * @param limiteAdhesion     the limite adhesion
	 * @param tipoDeModificacion the tipo de modificacion
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarAdhesion(Cliente cliente, PagoPendiente servicioAdherido,
	        Cuenta cuentaDebito, BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion) {
		ResultadoTransaccion resultado = null;
		Respuesta<ResultadoTransaccion> respuesta = new Respuesta<ResultadoTransaccion>();

		resultado = this.adhesionPagoDAO.modificarAdhesionPago(cliente, servicioAdherido, cuentaDebito, limiteAdhesion,
		        tipoDeModificacion);
		respuesta.setRespuesta(resultado);
		respuesta.setEstadoRespuesta(resultado.getEstadoRespuesta());
		if (resultado.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			// Ya se ha realizado la modificacion
			String codigoMensaje = null;
			switch (resultado.getCodigoRespuesta()) {
			case SOLICITUD_YA_PROCESADADA_CODIGO_IATX:
				codigoMensaje = CodigoMensajeConstantes.MODIFICACION_ADHESION_DEBITO_YA_SOLICITADA;
				break;
			case NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX:
				codigoMensaje = CodigoMensajeConstantes.MODIFICACION_ADHESION_DEBITO_NO_TITULAR;
				break;
			}
			String mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			respuesta.add(new ItemMensajeRespuesta(mensaje));
		}

		return respuesta;
	}

	/**
	 * Modifica adhesion a pago automatico de Pago Mis Cuentas.
	 *
	 * @param cliente            the cliente
	 * @param servicioAdherido   the servicio adherido
	 * @param cuentaDebito       the cuenta debito
	 * @param limiteAdhesion     the limite adhesion
	 * @param tipoDeModificacion the tipo de modificacion
	 * @param cuentaDelServicio  the cuenta del servicio
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarAdhesionPagoAutomaticoDePagoMisCuentas(Cliente cliente,
	        PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal limiteAdhesion,
	        TipoDeModificacion tipoDeModificacion, String cuentaDelServicio) {

		Respuesta<ResultadoTransaccion> respuestaModificacion = new Respuesta<ResultadoTransaccion>();
		try {
			ResultadoTransaccion resultado = this.adhesionPagoDAO.modificarAdhesionPagoAutomaticoDePagoMisCuentas(
			        cliente, servicioAdherido, cuentaDebito, limiteAdhesion, tipoDeModificacion, cuentaDelServicio);

			if (EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				respuestaModificacion
				        .setItemMensajeRespuesta(this.getMessage(CodigoMensajeConstantes.PAGO_AUTO_MODIFICADO_PMC_OK));
				respuestaModificacion.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaModificacion.setRespuesta(resultado);
				respuestaModificacion.setRespuestaVacia(false);
			} else {
				armarRespuestaModificacion(respuestaModificacion);
			}

		} catch (DAOException e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			return armarRespuestaModificacion(respuestaModificacion);
		} catch (Exception e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			return armarRespuestaModificacion(respuestaModificacion);
		}
		return respuestaModificacion;
	}

	/**
	 * Gets the message.
	 *
	 * @param codigo the codigo
	 * @return the message
	 */
	private List<ItemMensajeRespuesta> getMessage(String codigo) {
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(codigo);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
		itemMensajeRespuesta.add(item);
		return itemMensajeRespuesta;
	}

	/**
	 * arma Respuesta pago automatico.
	 *
	 * @param respuestaPagoPuntual the respuesta pago puntual
	 * @return the respuesta
	 */
	private Respuesta<ResultadoTransaccion> armarRespuestaModificacion(
	        Respuesta<ResultadoTransaccion> respuestaPagoPuntual) {
		respuestaPagoPuntual
		        .setItemMensajeRespuesta(this.getMessage(CodigoMensajeConstantes.PAGO_AUTO_MODIFICADO_PMC_ERROR));
		respuestaPagoPuntual.setRespuesta(null);
		respuestaPagoPuntual.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaPagoPuntual.setRespuestaVacia(true);
		return respuestaPagoPuntual;
	}

	/**
	 * Obtiene la fecha de vencimiento de una tarjeta.
	 *
	 * @author mariano.g.pulera
	 * @param cliente the cliente
	 * @param cuenta  the cuenta
	 * @return the string
	 * @throws BusinessException the business exception
	 * @throws DAOException      the DAO exception
	 */
	@Override
	public String obtenerFechaVencimientoTarjeta(Cliente cliente, Cuenta cuenta)
	        throws BusinessException, DAOException {

		DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
		DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
		String fecha = new DateTime(datosTarjeta.getFechaProximoVencimientoTC()).toString();
		return fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);
	}

	/**
	 * Obtiene los datos de una tarjeta.
	 *
	 * @param cliente the cliente
	 * @param cuenta  the cuenta
	 * @return the datos tarjeta
	 * @throws BusinessException the business exception
	 */
	@Override
	public DatosTarjeta obtenerDatosUnaTarjeta(Cliente cliente, Cuenta cuenta) throws BusinessException {
		try {
			DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuenta, true);
			return datosTarjetaBuilder.build();
		} catch (DAOException e) {
			LOGGER.info(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}

	}

}
