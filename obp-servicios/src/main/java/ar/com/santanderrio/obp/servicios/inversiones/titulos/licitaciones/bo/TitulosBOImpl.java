package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentaTituloFirmada;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentasFirmadasResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroDetalleComprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroListaComprobantesOrExt;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteScomp;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.PlazoPDCView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.PoderCompraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosGraficosMapDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosMapDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasGraficos;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ListaTenenciaCuentaTitMoneda;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentaTitulosExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentasTitulosExcelGeneral;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.InfoTenenciasExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.InfoTotalesTenenciasExcel;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.TipoInstrumentoInfoMercadoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.FirmarCuentasDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosMercadoCanalDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CondicionesGeneralesCuentaCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarReversarOrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionVigenteDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionesVigentesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ListasPliegosFiltradasBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.NuevaLicitacionDTOResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.OperacionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CanalTramoCtaTitulo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ColeccionConsultaOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperacionesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPorFirmarEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConfirmarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaComisionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOrdenesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaTitulosOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosDownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosDownloadArchivoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosFinalizarReversarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosReversarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosSimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosSimularOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FinalizarReversaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FirmarCuentasInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.NuevaLicitacionDTOResponseBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OperacionesPorCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OrdenesPorCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RespuestaDatosOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SaldosPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.TenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.exception.TiempoEsperaAgotadoException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.AperturaEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteCnvViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteDisponibleView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarViewBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DatosMonedaEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionCanjeView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionViewResp;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanje;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionVigente;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVIgentesBprivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaAperturaEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeResponseView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.entity.TipoOrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.AperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ListaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.enums.CanalIngresoEnum;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class TitulosBOImpl.
 *
 * @author juan.pablo.picate
 */
@Component("licitacionesBO")
public class TitulosBOImpl extends InversionesAbstractBO implements TitulosBO {

	/** The Constant USD. */
	private static final String USD = "USD";

	/** The Constant CODIGO_DOLARES. */
	private static final String CODIGO_DOLARES = "D";

	/** The Constant COMPRA_ORDENES. */
	private static final String COMPRA_ORDENES = "C";

	/** The Constant ARS. */
	private static final String ARS = "ARS";

	/** The Constant CODIGO_PESOS. */
	private static final String CODIGO_PESOS = "P";

	/** The Constant SIN_ORDENES. */
	private static final String SIN_ORDENES = "10477";

	private static final String CODIGO_OK = "0";

	/** The licitaciones dao. */
	@Autowired
	private TitulosDAO licitacionesDAO;

	/** Dao de inversiones. */
	@Autowired
	private InversionDAO inversionDAO;

	/** The titulos mercado canal DAO. */
	@Autowired
	private TitulosMercadoCanalDAO titulosMercadoCanalDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The mensajeBO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The legalBO. */
	@Autowired
	private LegalBO legalBO;

	/** The tenencia valuada. */
	@Autowired
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	/** The extracto. */
	@Autowired
	private ExtractoDAO extractoDAO;

	/** The myaBO. */
	@Autowired
	private MyaBO myaBO;

	/** The Firmar cuentas DAO. */
	@Autowired
	private FirmarCuentasDAO firmarCuentasDAO;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The ScompDAO. */
	@Autowired
	private ScompDAO scompDAO;

	/** The ScompDAO. */
	@Autowired
	private OrdenesTitulosDAO ordenesTitulosDAO;
	
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	@Autowired
	private ReporteDAO reporteDAO;
	
	/** The canal tipo. */
	@Value("${INVERSIONES.CANAL.BANCAPERSONAL}")
	private String canalRTL;

	/** The canal tipo banca privada. */
	@Value("${INVERSIONES.CANAL.BANCAPRIVADA}")
	private String canalBPriv;

	/** The subcanal tipo. */
	@Value("${INVERSIONES.SUBCANAL.BANCAPERSONAL}")
	private String subcanalRTL;

	/** The subcanal tipo banca privada. */
	@Value("${INVERSIONES.SUBCANAL.BANCAPRIVADA}")
	private String subcanalBPriv;

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.LICITACIONES.DATOFIRMADO}")
	private String dato;

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.FIRMA.DATO}")
	private String datoTitulos;

	/** The datoMercadoCanal firmado tipo. */
	@Value("${MERCADOSCANAL.LICITACIONES.DATOFIRMADO}")
	private String datoMercadoCanal;

	/** The tipo pliego tipo. */
	@Value("${INVERSIONES.LICITACIONES.TIPOPLIEGO}")
	private String tipoPliego;

	/** The segmento tipo. */
	@Value("${INVERSIONES.SEGMENTO.BANCAPERSONAL}")
	private String segmentoRTL;

	/** The tipo segmento tipo banca privada. */
	@Value("${INVERSIONES.SEGMENTO.BANCAPRIVADA}")
	private String segmentoBPriv;

	/** The Constant MONEDA_USD. */
	private static final String MONEDA_USD = USD;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TitulosBOImpl.class);

	/** The Constant NO. */
	private static final String NO = "NO";

	/** The Constant N. */
	private static final String N = "N";

	/** The constant CUENTA_CORRIENTE. */
	public static final String CUENTA_CORRIENTE = "Cuenta Corriente";

	/** The constant CAJA_AHORRO. */
	public static final String CAJA_AHORRO = "Caja de Ahorro";

	/** The constant CUENTA_UNICA. */
	public static final String CUENTA_UNICA = "Cuenta única";

	/** The constant CUENTA_CUSTODIA. */
	public static final String CUENTA_CUSTODIA = "Cuenta custodia";

	/** The constant CUENTA_CUSTODIA. */
	public static final String TIPO_CUENTA_OPERATIVA = "O";

	/** The constant CUENTA_CUSTODIA. */
	public static final String TIPO_CUENTA_CUSTODIA = "C";

	/** TipoDeNegocio al consultaEspecie Garanizada = BU. */
	public static final String CONSULTA_ORDEN_GARANTIZADA = "BU";

	/** The Constant LARGO_NUMERO_CUENTA. */
	private static final int LARGO_NUMERO_CUENTA = 8;

	/** The Constant LARGO_CUENTA_TITULOS. */
	private static final int LARGO_CUENTA_TITULOS = 7;

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant CERO CERO. */
	private static final String CEROCERO = "00";

	/** The Constant CERO UNO. */
	private static final String CEROUNO = "01";

	/** The Constant CERO DOS. */
	private static final String CERODOS = "02";

	/** The Constant CERO TRES. */
	private static final String CEROTRES = "03";

	/** The Constant CERO CUATRO. */
	private static final String CEROCUATRO = "04";

	/** The Constant CERO NUEVE. */
	private static final String CERONUEVE = "09";

	/** The Constant DIEZ. */
	private static final String DIEZ = "10";

	/** The Constant MENSAJE_SIN_LICITACIONES. */
	private static final String MENSAJE_SIN_LICITACIONES = "En este momento no hay licitaciones vigentes";

	/** The Constant INVERSOR_RIESGO_MEDIO. */
	private static final Object INVERSOR_RIESGO_MEDIO = "1";

	/** The Constant INVERSOR_RIESGO_BLOQUEANTE. */
	private static final Object INVERSOR_RIESGO_BLOQUEANTE = "2";

	/** The Constant TIPO_OPERACION_ERI_SUSCRIPCION_LICITACION. */
	private static final String TIPO_OPERACION_ERI_SUSCRIPCION_LICITACION = "SULIC";

	/** The Constant VALOR_NOMINAL. */
	private static final String VALOR_NOMINAL = "C";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "M";

	/** The Constant LARGO_TOTAL_FECHA. */
	private static final int LARGO_TOTAL_FECHA = 10;

	private static final int LARGO_TOTAL_FECHA_HORA = 22;

	/** The Constant PLIEGOS. */
	private static final String PLIEGOS = "PLIEGOS";

	/** The Constant SI. */
	private static final String SI = "S";

	/** The Constant CONDICIONES_GENERALES_LEGAL1. */
	private static final String CONDICIONES_GENERALES_LEGAL1 = "10037";

	/** The Constant CONDICIONES_GENERALES_LEGAL2. */
	private static final String CONDICIONES_GENERALES_LEGAL2 = "10035";

	/** The Constant CONDICIONES_GENERALES_LEGAL3. */
	private static final String CONDICIONES_GENERALES_LEGAL3 = "10038";

	/** The Constant CONDICIONES_GENERALES_LEGAL_ADICIONAL. */
	private static final String CONDICIONES_GENERALES_LEGAL_ADICIONAL = "10036";

	/** The Constant LONGITUD_60. */
	private static final int LONGITUD_60 = 60;

	/** The Constant LONG_CUENTA_REQUEST. */
	private static final int LONG_CUENTA_REQUEST = 11;

	/** The Constant LONG_SUC_REQUEST. */
	private static final int LONG_SUC_REQUEST = 4;

	/** The Constant PRIMER_ELEMENTO. */
	private static final String PRIMER_ELEMENTO = "";

	/** The Constant SEPARATOR_BARRA_LATERAL. */
	public static final String SEPARATOR_BARRA_LATERAL = "/";

	/** The Constant LARGO_HORA. */
	private static final int LARGO_HORA = 5;

	/** The Constant BANCA_PRIVADA. */
	public static final String BANCA_PRIVADA = "BP";

	/** The Constant BANCA_PERSONAL. */
	public static final String BANCA_PERSONAL = "BR";

	/** The tipo cuenta custodia. */
	private boolean tipoCuentaCustodia = false;

	/** The tipo cuenta operativa. */
	private boolean tipoCuentaOperativa = false;

	/** The Constant horaIsoFecha. */
	private static final String horaIsoFecha = "T00:00:00-03:00";

	/** The especie habilitada. */
	private String especieHabilitada = "OK";

	/** The Constant ALL. */
	private static final String ALL = "ALL";

	/** The especie fuera horario. */
	private String especieFueraHorario = "FH";

	/** The Constant OPERACIONES_DE_VENTA. */
	private static final String OPERACIONES_DE_VENTA = "V";

	/** The Constant FINALIZAR_REVERSA_OK_ORDENES_COMPRA. */
	public static final String FINALIZAR_REVERSA_OK_ORDENES_COMPRA = "10521";

	/** The Constant FINALIZAR_REVERSA_OK_ORDENES_VENTA. */
	public static final String FINALIZAR_REVERSA_OK_ORDENES_VENTA = "10523";

	/** The Constant FINALIZAR_REVERSA_FALLO_ORDENES_COMPRA. */
	public static final String FINALIZAR_REVERSA_FALLO_ORDENES_COMPRA = "10522";

	/** The Constant FINALIZAR_REVERSA_FALLO_ORDENES_VENTA. */
	public static final String FINALIZAR_REVERSA_FALLO_ORDENES_VENTA = "10524";

	/** The Constant CERO_INT. */
	public static final int CERO_INT = 0;

	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static final String MENSAJE_TECNICO_FUERA_DE_HORARIO = "SMC-00007";

	private static final String MENSAJE_TECNICO_SIN_SALDO = "ADB-00001";

	/** The Constant SALDO_INSUFICIENTE_PESOS. */
	private static final String SALDO_INSUFICIENTE_PESOS = "10000515";

	/** The Constant SALDO_INSUFICIENTE_DOLARES. */
	private static final String SALDO_INSUFICIENTE_DOLARES = "10002122";

	private static final String CUENTA_SIN_OPERAR_MAS_180_DIAS = "ADB-00002";

	private static final String EXCEDE_LIMITE_DE_CANAL = "ADB-00003";

	private static final String CUENTA_NO_FIRMADA = "SMC-00001";

	private static final String CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA = "SMC-00002";

	private static final String SE_ENCUENTRA_FUERA_DEL_TUNEL = "SMC-00003";

	private static final String CUENTA_TITULOS_BLOQUEADA = "SMC-00004";

	private static final String TENENCIA_BLOQUEADA = "SMC-00005";

	private static final String CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES = "SMC-00006";

	private static final String SUPERA_TIEMPO_DE_ESPERA = "WEB-00001";

	private static final String NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS = "WEB-00002";

	private static final String OTROS = "WEB-00003";

	private static final String TEST_INVERSOR_NO_REALIZADO = "SAC-00051";

	private static final String TIPO_PRECIO_RATIO = "RATIO";

	private static final String TIPO_PRECIO_DATO_DE_CORTE = "De Corte";

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultarOrdenesOutDTO> consultarLicitaciones(Cliente cliente, TipoBancaEnum banca,
			Boolean isLicitaciones) {

		ConsultarOrdenLicitacion request = new ConsultarOrdenLicitacion();
		ConsultarOrdenResponse response = new ConsultarOrdenResponse();
		try {
			request = generarRequestConsultarOrdenes(cliente, banca, isLicitaciones);
			response = licitacionesDAO.consultarOrdenLicitacion(request);
		} catch (BusinessException e) {
			LOGGER.error("Error al generar la firma", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar dao de licitaciones", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ConsultarOrdenesOutDTO responseBO = createDTOResponse(response.getDatos());
		return respuestaFactory.crearRespuestaOk(ConsultarOrdenesOutDTO.class, responseBO);
	}

	/**
	 * Generar request para consultar el dao de consultarOrdenes (licitaciones).
	 *
	 * @param cliente        the cliente
	 * @param banca          the banca
	 * @param isLicitaciones the is licitaciones
	 * @return the consultar orden
	 * @throws BusinessException the business exception
	 */
	ConsultarOrdenLicitacion generarRequestConsultarOrdenes(Cliente cliente, TipoBancaEnum banca,
			Boolean isLicitaciones) throws BusinessException {
		ConsultarOrdenLicitacion request = new ConsultarOrdenLicitacion();
		request.setFirma(generarFirma(dato));
		request.setDato(dato);
		DatosConsultarLicitacion datos = new DatosConsultarLicitacion();
		if (TipoBancaEnum.BANCA_PERSONAL.equals(banca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
		} else {
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
		}
		if (isLicitaciones) {
			datos.setLiquidadas("S");
		}
		datos.setTipoPliego(tipoPliego);
		datos.setNup(cliente.getNup());
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		datos.setIp(NetworkUtil.getHostAddress());
		request.setDatos(datos);
		return request;
	}

	/**
	 * Creates the DTO response.
	 *
	 * @param list the list
	 * @return the consultar ordenes out DTO
	 */
	ConsultarOrdenesOutDTO createDTOResponse(List<DatosConsultarOrdenResponse> list) {

		Predicate ordenMayorAlAnio = predicateParaOrdenRecientes();
		Predicate filtrarEstados = predicateEstadosFiltrados();

		List<ConsultarOrdenesDTO> listResponse = new ArrayList<ConsultarOrdenesDTO>();
		for (DatosConsultarOrdenResponse datoResponse : list) {
			ConsultarOrdenesDTO consultarOrden = new ConsultarOrdenesDTO();
			if ("LICITACIONES".equalsIgnoreCase(datoResponse.getTipoPliego())) {
				mapearLicitacion(datoResponse, consultarOrden);
			} else {
				mapearLicitacionCanje(datoResponse, consultarOrden);
			}
			listResponse.add(consultarOrden);
		}
		CollectionUtils.filter(listResponse, ordenMayorAlAnio);
		CollectionUtils.filter(listResponse, filtrarEstados);
		ConsultarOrdenesOutDTO consultarOrdenesDTO = new ConsultarOrdenesOutDTO();
		consultarOrdenesDTO.setList(listResponse);
		return consultarOrdenesDTO;
	}

	private void mapearLicitacion(DatosConsultarOrdenResponse datoResponse, ConsultarOrdenesDTO consultarOrden) {
		consultarOrden.setDescripcion(datoResponse.getDescripcionEspecie());
		consultarOrden.setFecha(convertirFechaTenenciasLicitaciones(datoResponse.getFechaOrden()));
		consultarOrden.setFechaHoraComprobante(convertirFechaHoraTenenciasLicitaciones(datoResponse.getFechaOrden()));
		consultarOrden.setNumeroOrden(datoResponse.getNumeroOrden());
		consultarOrden.setEstado(datoResponse.getEstado());
		consultarOrden.setMoneda(datoResponse.getMoneda() != null ? datoResponse.getMoneda().trim() : null);
		if ("ARS".equals(datoResponse.getMonedaEspecie())) {
			consultarOrden.setMonedaEspecie("Pesos");
		} else {
			consultarOrden.setMonedaEspecie("Dolares");
		}
		// consultarOrden.setMonedaEspecie(
		// datoResponse.getMonedaEspecie() != null ?
		// datoResponse.getMonedaEspecie().trim() : null);
		consultarOrden.setCuentaTitulo(ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaTitulos()));

		if (datoResponse.getMoneda().equalsIgnoreCase(datoResponse.getMonedaEspecie())) {
			consultarOrden.setTipoCambio(null);
		} else {
			consultarOrden.setTipoCambio(datoResponse.getTipoCambio());
		}

		if (datoResponse.getMonto() != null) {
			if ("0".equalsIgnoreCase(datoResponse.getMonto())) {
				consultarOrden.setImporte("null");
			} else {
				String tipoMoneda = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
				consultarOrden.setImporte(
						tipoMoneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(datoResponse.getMonto()));
			}
		} else {
			consultarOrden.setCantidadNominal(formatoCantidad(datoResponse.getCantidad()));
		}

		if ("S".equals(datoResponse.getTramoCompetitivo())) {
			String simbolo = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
			consultarOrden.setPrecio(
					"PRECIO".equalsIgnoreCase(datoResponse.getTipoPrecio()) ? simbolo + " " + datoResponse.getPrecio()
							: formatoPrecioTasa(datoResponse.getPrecio()) + " " + "%");

			consultarOrden.setTramo("Competitivo");
		} else {
			consultarOrden.setPrecio("De corte");
			consultarOrden.setTramo("No competitivo");
		}

		if ("0".equalsIgnoreCase(datoResponse.getMontoADebitar())) {
			consultarOrden.setImporteDebitar("SI".equalsIgnoreCase(datoResponse.getMontoADebitar()) ? "Mensaje Reversas"
					: "A determinar en la liquidación");

		} else {

			String tipoMoneda = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
			consultarOrden.setImporteDebitar(
					tipoMoneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(datoResponse.getMontoADebitar()));
		}

		if ("O".equalsIgnoreCase(datoResponse.getTipoCuenta())) {
			consultarOrden.setCuentaDebito(sucursalCuentaOperativa(datoResponse.getSucursal()) + "-"
					+ ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaOperativa()));
			consultarOrden.setTipoCuentaDebito(validarTipoCuentaOperativa(datoResponse.getTipoCuentaOperativa()));

		} else if ("C".equalsIgnoreCase(datoResponse.getTipoCuenta())) {
			consultarOrden.setCuentaDebito(datoResponse.getCuentaCustodia() != null
					? ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaCustodia())
					: null);
			consultarOrden.setTipoCuentaDebito(CUENTA_CUSTODIA);

		} else {
			if (datoResponse.getCuentaOperativa() == null) {
				consultarOrden
						.setCuentaDebito(ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaCustodia()));
				consultarOrden.setTipoCuentaDebito(CUENTA_CUSTODIA);
			} else {
				consultarOrden.setCuentaDebito(sucursalCuentaOperativa(datoResponse.getSucursal()) + "-"
						+ ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaOperativa()));
				consultarOrden.setTipoCuentaDebito(validarTipoCuentaOperativa(datoResponse.getTipoCuentaOperativa()));
			}

		}

		if (!"0".equalsIgnoreCase(datoResponse.getCantidadAdjudicada())) {
			consultarOrden.setCantidadAdjudicada(datoResponse.getCantidadAdjudicada());
		}

		consultarOrden.setTipoPrecioLabel(
				datoResponse.getTipoPrecio().substring(0, 1) + datoResponse.getTipoPrecio().substring(1).toLowerCase());
		consultarOrden.setTipoPrecioAdjudicadaLabel("TASA".equalsIgnoreCase(datoResponse.getTipoPrecio())
				? datoResponse.getTipoPrecio().substring(0, 1) + datoResponse.getTipoPrecio().substring(1).toLowerCase()
						+ " Adjudicada"
				: datoResponse.getTipoPrecio().substring(0, 1) + datoResponse.getTipoPrecio().substring(1).toLowerCase()
						+ " Adjudicado");
		if (!"0".equalsIgnoreCase(datoResponse.getPrecioAdjudicado()) && datoResponse.getPrecioAdjudicado() != null) {
			if (datoResponse.getTipoPrecio() != null) {
				String simbolo = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
				consultarOrden.setPrecioAdjudicada("PRECIO".equalsIgnoreCase(datoResponse.getTipoPrecio())
						? simbolo + " " + datoResponse.getPrecioAdjudicado()
						: formatoPrecioTasa(datoResponse.getPrecioAdjudicado()) + " " + "%");
			}
		}

		if (!"0".equalsIgnoreCase(datoResponse.getMontoComision()) && datoResponse.getMontoComision() != null) {
			consultarOrden.setComision(Double.valueOf(datoResponse.getMontoComision()));
		}

		if (!"0".equalsIgnoreCase(datoResponse.getMontoImpuesto()) && datoResponse.getMontoImpuesto() != null) {
			consultarOrden.setImpuesto(Double.valueOf(datoResponse.getMontoImpuesto()));
		}

		if (datoResponse.getFechaLiquidacion() != null) {
			consultarOrden.setFechaLiquidacion(convertirFechaTenenciasLicitaciones(datoResponse.getFechaLiquidacion()));
		}
		if (datoResponse.getFechaAdjudicacion() != null) {
			consultarOrden
					.setFechaAdjudicacion(convertirFechaTenenciasLicitaciones(datoResponse.getFechaAdjudicacion()));
		}
		if (datoResponse.getFechaCierre() != null) {
			consultarOrden.setFechaCierre(convertirFechaHoraTenenciasLicitaciones(datoResponse.getFechaCierre()));
		}
		if ("S".equalsIgnoreCase(datoResponse.getPermiteReversa())) {
			consultarOrden.setPermiteReversa(true);
		}
		consultarOrden.setLegalPliegue(datoResponse.getLeyendaLegal());
		consultarOrden.setLegalCanal(datoResponse.getLeyendaLegalCan());
		consultarOrden.setCodInstrumento(datoResponse.getCodInstrumento());
		consultarOrden.setDescInstrumento(datoResponse.getDescInstrumento());
		consultarOrden.setSucursal(datoResponse.getSucursal());
		consultarOrden.setCuentaOperativa(datoResponse.getCuentaOperativa());
		consultarOrden.setFechaDebProv(convertirFechaTenenciasLicitaciones(datoResponse.getFechaDebProv()));
		consultarOrden.setTipoPliego(datoResponse.getTipoPliego());
		consultarOrden.setTipoDeOrden("L");
	}

	private void mapearLicitacionCanje(DatosConsultarOrdenResponse datoResponse, ConsultarOrdenesDTO consultarOrden) {
		consultarOrden.setDescripcion(datoResponse.getDescripcionEspecie());
		consultarOrden.setFecha(convertirFechaTenenciasLicitaciones(datoResponse.getFechaOrden()));
		consultarOrden.setFechaHoraComprobante(convertirFechaHoraTenenciasLicitaciones(datoResponse.getFechaOrden()));
		consultarOrden.setNumeroOrden(datoResponse.getNumeroOrden());
		consultarOrden.setEstado(datoResponse.getEstado());
		consultarOrden.setMoneda(datoResponse.getMoneda() != null ? datoResponse.getMoneda().trim() : null);
		if ("ARS".equals(datoResponse.getMonedaEspecie())) {
			consultarOrden.setMonedaEspecie("Pesos");
		} else {
			consultarOrden.setMonedaEspecie("Dólares");
		}
		// consultarOrden.setMonedaEspecie(
		// datoResponse.getMonedaEspecie() != null ?
		// datoResponse.getMonedaEspecie().trim() : null);
		consultarOrden.setCuentaTitulo(ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaTitulos()));

		if (datoResponse.getMonto() != null) {
			if ("0".equalsIgnoreCase(datoResponse.getMonto())) {
				consultarOrden.setImporte("null");
			} else {
				String tipoMoneda = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
				consultarOrden.setImporte(
						tipoMoneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(datoResponse.getMonto()));
			}
		} else {
			consultarOrden.setCantidadNominal(
					ISBANStringUtils.formatearValorEnteroYDecimales(Double.valueOf(datoResponse.getCantidad()),
							obtenerDecimalesValoresCanje(new BigDecimal(datoResponse.getCantidad()))));
		}

		if ("S".equals(datoResponse.getTramoCompetitivo())) {
			consultarOrden.setTramo("Competitivo");
		} else {
			consultarOrden.setTramo("No competitivo");
		}

		if ("0".equalsIgnoreCase(datoResponse.getMontoADebitar()) || datoResponse.getMontoADebitar() == null) {
			consultarOrden.setImporteDebitar("A determinar en la liquidación");
		} else {
			String tipoMoneda = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
			consultarOrden.setImporteDebitar(
					tipoMoneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(datoResponse.getMontoADebitar()));
		}

		if ("O".equalsIgnoreCase(datoResponse.getTipoCuenta())) {
			consultarOrden.setCuentaDebito(sucursalCuentaOperativa(datoResponse.getSucursal()) + "-"
					+ ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaOperativa()));
			consultarOrden.setTipoCuentaDebito(validarTipoCuentaOperativa(datoResponse.getTipoCuentaOperativa()));

		} else if ("C".equalsIgnoreCase(datoResponse.getTipoCuenta())) {
			consultarOrden.setCuentaDebito(datoResponse.getCuentaCustodia() != null
					? ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaCustodia())
					: null);
			consultarOrden.setTipoCuentaDebito(CUENTA_CUSTODIA);

		} else {
			if (datoResponse.getCuentaOperativa() == null) {
				consultarOrden
						.setCuentaDebito(ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaCustodia()));
				consultarOrden.setTipoCuentaDebito(CUENTA_CUSTODIA);
			} else {
				consultarOrden.setCuentaDebito(sucursalCuentaOperativa(datoResponse.getSucursal()) + "-"
						+ ISBANStringUtils.formatearNumeroCuenta(datoResponse.getCuentaOperativa()));
				consultarOrden.setTipoCuentaDebito(validarTipoCuentaOperativa(datoResponse.getTipoCuentaOperativa()));
			}

		}

		if (!"0".equalsIgnoreCase(datoResponse.getCantidadAdjudicada())) {
			consultarOrden.setCantidadAdjudicada(datoResponse.getCantidadAdjudicada());
		}

		consultarOrden.setTipoPrecioLabel(WordUtils.capitalizeFully(datoResponse.getTipoPrecio()));
		consultarOrden.setTipoCambio(formatearTipoCambio(datoResponse.getTipoCambio(), datoResponse.getMoneda(),
				datoResponse.getMonedaEspecie(), datoResponse.getCodMonedaEspecieDestino()));

		if (TIPO_PRECIO_RATIO.equalsIgnoreCase(datoResponse.getTipoPrecio())) {
			consultarOrden.setPrecio(
					ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(datoResponse.getRelacionDerechosCanje()),
							obtenerDecimalesValoresCanje(BigDecimal.valueOf(datoResponse.getRelacionDerechosCanje()))));

		} else {
			consultarOrden.setPrecio("De corte");
			if (!"0".equalsIgnoreCase(datoResponse.getPrecioAdjudicado())
					&& datoResponse.getPrecioAdjudicado() != null) {
				consultarOrden.setTipoPrecioAdjudicadaLabel("TASA".equalsIgnoreCase(datoResponse.getTipoPrecio())
						? consultarOrden.getTipoPrecioLabel() + " Adjudicada"
						: consultarOrden.getTipoPrecioLabel() + " Adjudicado");
				String simbolo = MONEDA_USD.equalsIgnoreCase(datoResponse.getMoneda()) ? "u$s" : "$";
				consultarOrden.setPrecioAdjudicada("PRECIO".equalsIgnoreCase(datoResponse.getTipoPrecio())
						? simbolo + " "
								+ ISBANStringUtils.formatearConComaYVariosDecimalesBis(
										datoResponse.getPrecioAdjudicado(),
										obtenerDecimalesValoresCanje(
												new BigDecimal(datoResponse.getPrecioAdjudicado())))
						: formatoPrecioTasa(datoResponse.getPrecioAdjudicado()) + " " + "%");
			}
		}

		if (!"0".equalsIgnoreCase(datoResponse.getMontoComision()) && datoResponse.getMontoComision() != null) {
			consultarOrden.setComision(Double.valueOf(datoResponse.getMontoComision()));
		}

		if (!"0".equalsIgnoreCase(datoResponse.getMontoImpuesto()) && datoResponse.getMontoImpuesto() != null) {
			consultarOrden.setImpuesto(Double.valueOf(datoResponse.getMontoImpuesto()));
		}

		if (datoResponse.getFechaLiquidacion() != null) {
			consultarOrden.setFechaLiquidacion(convertirFechaTenenciasLicitaciones(datoResponse.getFechaLiquidacion()));
		}
		if (datoResponse.getFechaAdjudicacion() != null) {
			consultarOrden
					.setFechaAdjudicacion(convertirFechaTenenciasLicitaciones(datoResponse.getFechaAdjudicacion()));
		}
		if (datoResponse.getFechaCierre() != null) {
			consultarOrden.setFechaCierre(convertirFechaHoraTenenciasLicitaciones(datoResponse.getFechaCierre()));
		}
		if ("S".equalsIgnoreCase(datoResponse.getPermiteReversa())) {
			consultarOrden.setPermiteReversa(true);
		}
		consultarOrden.setLegalPliegue(datoResponse.getLeyendaLegal());
		consultarOrden.setLegalCanal(datoResponse.getLeyendaLegalCan());
		consultarOrden.setCodInstrumento(datoResponse.getCodInstrumento());
		consultarOrden.setDescInstrumento(datoResponse.getDescInstrumento());
		consultarOrden.setSucursal(datoResponse.getSucursal());
		consultarOrden.setCuentaOperativa(datoResponse.getCuentaOperativa());
		consultarOrden.setFechaDebProv(convertirFechaTenenciasLicitaciones(datoResponse.getFechaDebProv()));
		consultarOrden.setMonedaEspecieDestino(
				datoResponse.getCodMonedaEspecieDestino().equalsIgnoreCase("ARS") ? "Pesos" : "Dólares");
		consultarOrden.setEspecieDestino(datoResponse.getDescripcionEspecieOtorgar());
		consultarOrden.setTipoPliego(datoResponse.getTipoPliego());
		consultarOrden.setTipoDeOrden("L");
	}

	/**
	 * Tipo de cuenta operativa.
	 *
	 * @param codigo the codigo
	 * @return the string
	 */
	private String validarTipoCuentaOperativa(String codigo) {
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(TipoCuenta.fromCodigo(codigo))
				|| TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(TipoCuenta.fromCodigo(codigo))) {
			return CUENTA_CORRIENTE;
		}

		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(TipoCuenta.fromCodigo(codigo))
				|| TipoCuenta.CAJA_AHORRO_PESOS.equals(TipoCuenta.fromCodigo(codigo))) {
			return CAJA_AHORRO;
		}

		if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(codigo))
				|| TipoCuenta.CUENTA_UNICA_PESOS.equals(TipoCuenta.fromCodigo(codigo))
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(TipoCuenta.fromCodigo(codigo))) {
			return CUENTA_UNICA;
		}

		return null;
	}

	/**
	 * Sucursal cuenta operativa.
	 *
	 * @param sucursal the sucursal
	 * @return the string
	 */
	private String sucursalCuentaOperativa(String sucursal) {
		if (sucursal.length() <= 2) {
			int s = sucursal.length();
			while (s < 3) {
				sucursal = "0" + sucursal;
				s++;
			}
		}
		return sucursal;
	}

	/**
	 * Filtra las ordenes de los ultimos 360 dias.
	 *
	 * @return the predicate
	 */
	private Predicate predicateParaOrdenRecientes() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {

				Date fechaActual = new Date();
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				ConsultarOrdenesDTO orden = (ConsultarOrdenesDTO) object;
				try {
					Date fechaOrden = formatoFecha.parse(orden.getFecha());
					int diasDiferencias = (int) ((fechaActual.getTime() - fechaOrden.getTime()) / 86400000);
					if (diasDiferencias <= 360) {
						return true;
					}
				} catch (ParseException e) {
					LOGGER.error("Error al convertir la fecha de la orden", e);
				}
				return false;
			}
		};
	}

	/**
	 * elimina las ordenes cuyo estado sean Simulada,Anulada.
	 *
	 * @return the predicate
	 */
	private Predicate predicateEstadosFiltrados() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultarOrdenesDTO orden = (ConsultarOrdenesDTO) object;
				String estado = orden.getEstado();
				if ("Simulada".equalsIgnoreCase(estado) || "Anulada".equalsIgnoreCase(estado)) {
					return false;
				}
				return true;
			}
		};
	}

	/**
	 * Metodo que convierte un string 1000 en string 1.000,0000000, rellena los
	 * decimales hasta que existan 7 decimales, ademas le da formato a la parte
	 * entera
	 *
	 * @param precio the precio
	 * @return the string
	 */
	private String formatoPrecioTasa(String precio) {

		if (precio.contains(",")) {
			int punto = precio.indexOf(",");
			String decimales = precio.substring(punto + 1);
			String entera = formatoCantidad(precio.substring(0, punto));
			if (decimales.length() < 7) {
				for (int i = decimales.length(); i < 7; i++) {
					decimales = decimales.concat("0");
				}
				return entera + "," + decimales;
			} else {
				return precio;
			}
		}
		return precio;
	}

	/**
	 * Formatea un String 1000 en un String 1.000, tambien dado el caso que sea
	 * 1000000 en 1.000.000. limite 999.999.999.
	 *
	 * @param cantidad the cantidad
	 * @return the string
	 */
	private String formatoCantidad(String cantidad) {
		if (cantidad != null) {
			if (cantidad.length() > 6) {
				String montoModificado = cantidad;
				montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 6, ".")
						.toString();
				montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 3, ".")
						.toString();
				return montoModificado;
			} else if (cantidad.length() > 3) {
				String montoModificado = cantidad;
				montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 3, ".")
						.toString();
				return montoModificado;
			}
		}
		return cantidad;
	}

	/**
	 * Devuelve la hora en formato HH:mm de acuerdo a la fecha recibida.
	 *
	 * @param fechaInicial the fecha inicial
	 * @return the string
	 */
	private String obtenerHoraTenenciasLicitaciones(String fechaInicial) {
		String json = fechaInicial;
		json = json.replace("/Date(", "");
		json = json.replace(")/", "");
		long time;
		try {
			time = Long.parseLong(json);
		} catch (NumberFormatException e) {
			return null;
		}
		return new SimpleDateFormat("HH:mm").format(new Date(time));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#licitacionesVigentes(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente, java.util.List, java.util.List)
	 */
	@Override
	public Respuesta<LicitacionesVigentesOutDTO> licitacionesVigentes(Cliente cliente,
			List<CuentasAdhesionDebitoView> cuentasDebitoPesos, List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {

		return obtenerLicitacionesVigentes(cliente, cuentasDebitoPesos, cuentasDebitoDolares, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#licitacionesVigentesBPriv(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(Cliente cliente,
			NuevaLicitacionViewReq request) {
		Respuesta<NuevaLicitacionDTOResponseBPriv> nuevaLicitacion = nuevaLicitacionBPriv(cliente, request, false);
		if (EstadoRespuesta.OK.equals(nuevaLicitacion.getEstadoRespuesta())) {
			List<CuentaTituloParaLicitarViewBPriv> listCuentas = nuevaLicitacion.getRespuesta().getCuentasTitulo();
			Set<LicitacionVigente> hashSetLicitaciones = new HashSet<LicitacionVigente>();
			for (CuentaTituloParaLicitarViewBPriv c : listCuentas) {
				hashSetLicitaciones.addAll(c.getLicitacionesVigentesList());
			}
			LicitacionesVIgentesBprivOutView resp = new LicitacionesVIgentesBprivOutView();
			resp.getLicitacionesVigentesList().addAll(hashSetLicitaciones);
			return respuestaFactory.crearRespuestaOk(LicitacionesVIgentesBprivOutView.class, resp);
		} else if (EstadoRespuesta.WARNING.equals(nuevaLicitacion.getEstadoRespuesta())) {
			return respuestaFactory.transformar(null, nuevaLicitacion);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Obtener licitaciones vigentes.
	 *
	 * @param cliente              the cliente
	 * @param cuentasDebitoPesos   the cuentas debito pesos
	 * @param cuentasDebitoDolares the cuentas debito dolares
	 * @return the respuesta
	 */
	private Respuesta<LicitacionesVigentesOutDTO> obtenerLicitacionesVigentes(Cliente cliente,
			List<CuentasAdhesionDebitoView> cuentasDebitoPesos, List<CuentasAdhesionDebitoView> cuentasDebitoDolares,
			boolean esActionsheet) {
		ObtenerCanalTramoResponse outEntity = new ObtenerCanalTramoResponse();
		List<DatosObtenerCanalTramoResponse> listaLicitacionesFiltradas;
		try {
			outEntity = licitacionesDAO.obtenerLicitaciones(crearRequestLicitacionesVigentes(cliente, BANCA_PERSONAL));
			listaLicitacionesFiltradas = filtrarLicitaciones(outEntity, esActionsheet, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error en BO de titulos. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO obteniendo licitaciones vigentes.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		LicitacionesVigentesOutDTO licitacionesVigentes = entityToDTO(listaLicitacionesFiltradas);
		licitacionesVigentes.setLicitacionesVigentesList(obtenerLicitacionesPorMoneda(licitacionesVigentes,
				obtenerTipoMonedaParaLicitacion(cuentasDebitoPesos, cuentasDebitoDolares)));

		verificarPrecios(licitacionesVigentes);

		return respuestaFactory.crearRespuestaOk(LicitacionesVigentesOutDTO.class, licitacionesVigentes);
	}

	/**
	 * Verificar precios.
	 *
	 * @param licitacionesVigentes the licitaciones vigentes
	 */
	private void verificarPrecios(LicitacionesVigentesOutDTO licitacionesVigentes) {

		String sinLimite = "Sin límite";

		for (LicitacionVigenteDTO licitacion : licitacionesVigentes.getLicitacionesVigentesList()) {
			if (CERO.equals(licitacion.getPrecioMin())) {
				licitacion.setPrecioMinTexto(sinLimite);
			} else {
				String precioMin = ISBANStringUtils.formatearConComaYDosDecimales(licitacion.getPrecioMin());
				String[] descompongoNumero = precioMin.split(",");
				licitacion.setPrecioMinTexto(descompongoNumero[0]);
			}

			if (CERO_INT == licitacion.getPrecioMax()) {
				licitacion.setPrecioMaxTexto(sinLimite);
			} else {
				String precioMax = ISBANStringUtils.formatearConComaYDosDecimales(licitacion.getPrecioMax().toString());
				String[] descompongoNumero = precioMax.split(",");
				licitacion.setPrecioMaxTexto(descompongoNumero[0]);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#nuevaLicitacionBPriv(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<NuevaLicitacionDTOResponseBPriv> nuevaLicitacionBPriv(Cliente cliente,
			NuevaLicitacionViewReq request, boolean esActionsheet) {
		NuevaLicitacionDTOResponseBPriv response = new NuevaLicitacionDTOResponseBPriv();

		Respuesta<LicitacionesVigentesOutDTO> licitacionesVigentes = obtenerLicitacionesVigentesBpriv(cliente,
				esActionsheet);
		if (!EstadoRespuesta.OK.equals(licitacionesVigentes.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		ObtenerCuentasTitulosResponse cuentasTitulo = null;
		try {
			cuentasTitulo = licitacionesDAO.obtenerCuentasTitulos(crearRequestNuevaLicitacionBPriv(cliente));
		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO obteniendo cuentas titulos habilitadas para licitar. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		response.setCuentasTitulo(
				obtenerCuentasParaLicitarBPriv(request.getCuentasTitulo(), cuentasTitulo.getDatos(), cliente));

		List<LicitacionVigenteDTO> listaLicitacionesVigentes = licitacionesVigentes.getRespuesta()
				.getLicitacionesVigentesList();
		List<LicitacionVigenteDTO> listaLicitacionesVigentesCustodia = licitacionesVigentes.getRespuesta()
				.getLicitacionesVigentesCustodiaList();

		if (!listaLicitacionesVigentesCustodia.isEmpty()) {
			if (tipoCuentaCustodia) {
				// VALIDAR PARA LLAMAR CON AMBAS MONEDAS
				if (sesionCliente.getCliente().getCuentasCustodia().isEmpty()) {
					try {
						sesionCliente.getCliente().getCuentasCustodia()
								.addAll(obtenerSaldoCuentasCustodiaBPriv(cliente, ARS).getDatos());
						sesionCliente.getCliente().getCuentasCustodia()
								.addAll(obtenerSaldoCuentasCustodiaBPriv(cliente, USD).getDatos());
					} catch (BusinessException e) {
						LOGGER.error("Error al obtener custodia monetaria", e);
						sesionCliente.getCliente()
								.setCuentasCustodia(new ArrayList<DatosObtenerSaldoCuentasCustodiaResponse>());
					}
				}
			}
			if (sesionCliente.getCliente().getCuentasCustodia().isEmpty()) {
				tipoCuentaCustodia = false;
			}
		}

		// No tiene cuentas títulos habilitada para Licitar y/o Ninguna especie
		// a Licitar.
		if (response.getCuentasTitulo().isEmpty()
				|| (listaLicitacionesVigentes.isEmpty() && listaLicitacionesVigentesCustodia.isEmpty())) {
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setTag(null);
			item.setTipoError(TipoError.ERROR_SIN_LICITACIONES.getDescripcion());
			item.setMensaje(MENSAJE_SIN_LICITACIONES);
			List<ItemMensajeRespuesta> itemMensajes = new ArrayList<ItemMensajeRespuesta>();
			itemMensajes.add(item);
			return respuestaFactory.crearRespuestaWarning(NuevaLicitacionDTOResponseBPriv.class, response,
					itemMensajes);
		}

		// Agrega las licitaciones a las cuetnas correspondientes
		for (CuentaTituloParaLicitarViewBPriv cuentaTitulo : response.getCuentasTitulo()) {
			agregarListaLicitacionesValidaDerechosBPriv(listaLicitacionesVigentes, cuentaTitulo);
			if (!listaLicitacionesVigentesCustodia.isEmpty()) {
				if (tipoCuentaCustodia) {
					agregarListaLicitacionesValidaDerechosBPriv(listaLicitacionesVigentesCustodia, cuentaTitulo);
				}
			}
			if (cuentaTitulo.getIntervinientes().isEmpty()) {
				// Criterio 2. No tiene ninguna especie a licitar.
				response.getCuentasTitulo().remove(cuentaTitulo);
			}
		}

		return respuestaFactory.crearRespuestaOk(NuevaLicitacionDTOResponseBPriv.class, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#obtenerSaldoCuentasCustodiaBPriv(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente, java.lang.String)
	 */
	public ObtenerSaldoCuentasCustodiaResponse obtenerSaldoCuentasCustodiaBPriv(Cliente cliente, String moneda)
			throws BusinessException {
		ObtenerSaldoCuentasCustodiaResponse response = new ObtenerSaldoCuentasCustodiaResponse();
		try {
			response = licitacionesDAO.obtenerSaldoCuentasCustodia(crearRequestSaldoCuentasCustodia(cliente, moneda));
		} catch (DAOException e) {
			LOGGER.error("");
			throw new BusinessException(e);
		}
		return response;
	}

	/**
	 * Obtener licitaciones vigentes.
	 *
	 * @param cliente              the cliente
	 * @param cuentasDebitoPesos   the cuentas debito pesos
	 * @param cuentasDebitoDolares the cuentas debito dolares
	 * @return the respuesta
	 */
	private Respuesta<LicitacionesVigentesOutDTO> obtenerLicitacionesVigentesBpriv(Cliente cliente,
			boolean esActionsheet) {
		ObtenerCanalTramoResponse outEntity = new ObtenerCanalTramoResponse();
		ListasPliegosFiltradasBPriv listasPliegos = new ListasPliegosFiltradasBPriv();
		try {
			outEntity = licitacionesDAO.obtenerLicitaciones(crearRequestLicitacionesVigentes(cliente, BANCA_PRIVADA));
			listasPliegos = filtrarLicitacionesBPriv(outEntity, esActionsheet, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error en BO de titulos. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO obteniendo licitaciones vigentes.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		LicitacionesVigentesOutDTO licitacionesVigentes = entityToDTO(listasPliegos.getListPliegos());
		LicitacionesVigentesOutDTO licitacionesVigentesCustodia = entityToDTO(listasPliegos.getListPliegosCustodia());
		licitacionesVigentes.setLicitacionesVigentesList(obtenerLicitacionesPorMoneda(licitacionesVigentes, "A"));
		licitacionesVigentes
				.setLicitacionesVigentesCustodiaList(obtenerLicitacionesPorMoneda(licitacionesVigentesCustodia, "A"));

		verificarPrecios(licitacionesVigentes);

		return respuestaFactory.crearRespuestaOk(LicitacionesVigentesOutDTO.class, licitacionesVigentes);
	}

	/**
	 * Crear request licitaciones vigentes.
	 *
	 * @param cliente the cliente
	 * @param banca   the banca
	 * @return the obtener canal tramo
	 * @throws BusinessException the business exception
	 */
	private ObtenerCanalTramo crearRequestLicitacionesVigentes(Cliente cliente, String banca) throws BusinessException {
		ObtenerCanalTramo request = new ObtenerCanalTramo();
		cargarDatosGenericosRequest(request);
		request.setDatos(new DatosObtenerCanalTramo());
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setNup(cliente.getNup());
		if (BANCA_PRIVADA.equals(banca)) {
			request.getDatos().setCanal(canalBPriv);
			request.getDatos().setSubcanal(subcanalBPriv);
		} else {
			request.getDatos().setCanal(canalRTL);
			request.getDatos().setSubcanal(subcanalRTL);
		}
		request.getDatos().setTipoPliego(tipoPliego);
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		return request;
	}

	/**
	 * Entity to DTO.
	 *
	 * @param listaLicitacionesFiltradas the lista licitaciones filtradas
	 * @return the licitaciones vigentes out DTO
	 */
	private LicitacionesVigentesOutDTO entityToDTO(List<DatosObtenerCanalTramoResponse> listaLicitacionesFiltradas) {
		LicitacionesVigentesOutDTO licitacionesVigentes = new LicitacionesVigentesOutDTO();
		List<LicitacionVigenteDTO> listaLicitacionesVigentes = new ArrayList<LicitacionVigenteDTO>();
		licitacionesVigentes.setLicitacionesVigentesList(listaLicitacionesVigentes);
		for (DatosObtenerCanalTramoResponse licitacion : listaLicitacionesFiltradas) {
			listaLicitacionesVigentes.add(convertitLicitacionDTO(licitacion));
		}
		return licitacionesVigentes;
	}

	/**
	 * Filtrar licitaciones.
	 *
	 * @param outEntity the out entity
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	// filtra licitaciones
	private List<DatosObtenerCanalTramoResponse> filtrarLicitaciones(ObtenerCanalTramoResponse outEntity,
			boolean esActionsheet, Cliente cliente) throws BusinessException {

		List<DatosObtenerCanalTramoResponse> listaLicitacionesFiltradas = new ArrayList<DatosObtenerCanalTramoResponse>();
		for (DatosObtenerCanalTramoResponse licitacion : outEntity.getDatos()) {
			if (esActionsheet && licitacion.getTipoEjecucion() != null) {
				continue;
			}
			if (!"C".equalsIgnoreCase(licitacion.getTipoCuenta())) {
				// SI NO ES CUENTA CUSTODIA LA EVALUO
				if (N.equals(licitacion.getValidaDerechos())) {
					// SI NO VALIDA DERECHOS LA AGREGO
					listaLicitacionesFiltradas.add(licitacion);
				} else if (licitacion.getCuentasTitulo() == null) {
					// SI VINO NULL ES UN ERROR
					throw new BusinessException();
				} else if (!licitacion.getCuentasTitulo().isEmpty()) {
					// SI VALIDA DERECHOS Y TIENE CUENTAS TITULO CON TENENCIA LA
					// AGREGO
					listaLicitacionesFiltradas.add(licitacion);
				}
				if ("0".equals(licitacion.getTipoEjecucion())) {
					for (CanalTramoCtaTitulo cuenta : licitacion.getCuentasTitulo()) {
						for (Cuenta c : cliente.getCuentasRetail()) {
							if (cuenta.getCuentaTitulo() == Long
									.valueOf(c.getNroCuentaProducto().replaceAll("/", ""))) {
								cuenta.setIntervinientes(c.getIntervinientes());
							}
						}
					}

				}
			}
		}
		return listaLicitacionesFiltradas;
	}

	/**
	 * Filtrar licitaciones.
	 *
	 * @param outEntity the out entity
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	// filtra licitaciones
	private ListasPliegosFiltradasBPriv filtrarLicitacionesBPriv(ObtenerCanalTramoResponse outEntity,
			boolean esActionsheet, Cliente cliente) throws BusinessException {

		ListasPliegosFiltradasBPriv listaLicitacionesFiltradas = new ListasPliegosFiltradasBPriv();
		for (DatosObtenerCanalTramoResponse licitacion : outEntity.getDatos()) {
			if (esActionsheet && licitacion.getTipoEjecucion() != null) {
				continue;
			}
			if (N.equals(licitacion.getValidaDerechos())) {
				// SI NO VALIDA DERECHOS LA AGREGO
				if ("C".equals(licitacion.getTipoCuenta())) {
					listaLicitacionesFiltradas.getListPliegosCustodia().add(licitacion);
				} else {
					listaLicitacionesFiltradas.getListPliegos().add(licitacion);
				}
				tipoCuentaOperativa = tipoCuentaOperativa || "A".equals(licitacion.getTipoCuenta())
						|| "O".equals(licitacion.getTipoCuenta());
				tipoCuentaCustodia = tipoCuentaCustodia || "C".equals(licitacion.getTipoCuenta());
			} else if (licitacion.getCuentasTitulo() == null) {
				// SI VINO NULL ES UN ERROR
				throw new BusinessException();
			} else if (!licitacion.getCuentasTitulo().isEmpty()) {
				// SI VALIDA DERECHOS Y TIENE CUENTAS TITULO CON TENENCIA LA
				// AGREGO
				if ("C".equals(licitacion.getTipoCuenta())) {
					listaLicitacionesFiltradas.getListPliegosCustodia().add(licitacion);
				} else {
					listaLicitacionesFiltradas.getListPliegos().add(licitacion);
				}
				tipoCuentaOperativa = tipoCuentaOperativa || "A".equals(licitacion.getTipoCuenta())
						|| "O".equals(licitacion.getTipoCuenta());
				tipoCuentaCustodia = tipoCuentaCustodia || "A".equals(licitacion.getTipoCuenta())
						|| "C".equals(licitacion.getTipoCuenta());
			}
			if ("0".equals(licitacion.getTipoEjecucion())) {
				for (CanalTramoCtaTitulo cuenta : licitacion.getCuentasTitulo()) {
					for (CuentaBancaPrivada c : cliente.getCuentasBancaPrivada()) {
						if (cuenta.getCuentaTitulo() == Long
								.valueOf(c.getCuentaTitulo().getNroCuentaProducto().replaceAll("/", ""))) {
							cuenta.setIntervinientes(c.getCuentaTitulo().getIntervinientes());
						}
					}
				}

			}

		}
		return listaLicitacionesFiltradas;
	}

	/**
	 * Convertit licitacion DTO.
	 *
	 * @param licitacion the licitacion
	 * @return the licitacion vigente DTO
	 */
	private LicitacionVigenteDTO convertitLicitacionDTO(DatosObtenerCanalTramoResponse licitacion) {
		LicitacionVigenteDTO licitacionVigenteDTO = new LicitacionVigenteDTO();

		licitacionVigenteDTO.setEspecie(licitacion.getDescripcionEspecie());
		licitacionVigenteDTO.setMonedaEspecie(licitacion.getMonedaEspecie().trim());
		licitacionVigenteDTO.setFechaCierre(convertirFechaTenenciasLicitaciones(licitacion.getFechaHoraCierre()));
		licitacionVigenteDTO.setHoraCierre(obtenerHoraTenenciasLicitaciones(licitacion.getFechaHoraCierre()));
		licitacionVigenteDTO.setTramo(licitacion.getTramoCompetitivo());
		licitacionVigenteDTO.setMonedaLicitacion(licitacion.getMoneda());
		licitacionVigenteDTO.setCodigoEspecie(licitacion.getCodigoEspecie());
		licitacionVigenteDTO.setCodigoTramo(licitacion.getCodigoTramo());
		licitacionVigenteDTO.setCodigoPliego(licitacion.getCodigoPliego());
		licitacionVigenteDTO.setCodigoTramoCanal(licitacion.getCodigoTramoCanal());
		licitacionVigenteDTO.setTipoOferta(licitacion.getTipoOferta());
		licitacionVigenteDTO.setNominalMin(licitacion.getNominalMin());
		licitacionVigenteDTO.setNominalMax(licitacion.getNominalMax());
		licitacionVigenteDTO.setMontoMin(licitacion.getMontoMin());
		licitacionVigenteDTO.setMontoMax(licitacion.getMontoMax());
		licitacionVigenteDTO.setNominalIncrement(licitacion.getNominalIncrement());
		licitacionVigenteDTO.setMontoIncrement(licitacion.getMontoIncrement());
		licitacionVigenteDTO.setPrecio(licitacion.getPrecio());
		licitacionVigenteDTO.setPrecioMin(licitacion.getPrecioMin());
		licitacionVigenteDTO.setPrecioMax(licitacion.getPrecioMax());
		licitacionVigenteDTO.setPrecioIncrement(licitacion.getPrecioIncrement());
		licitacionVigenteDTO.setTipoPrecio(ISBANStringUtils.convertirStringToCamelcase(licitacion.getTipoPrecio()));

		licitacionVigenteDTO.setRenovacion(licitacion.getRenovacion());
		licitacionVigenteDTO
				.setFechaAdjudicacion(convertirFechaTenenciasLicitaciones(licitacion.getFechaAdjudicacion()));
		licitacionVigenteDTO.setFechaLiquidacionTitulos(
				convertirFechaTenenciasLicitaciones(licitacion.getFechaLiquidacionTitulos()));
		licitacionVigenteDTO.setCorreoElectronico(licitacion.getCorreoElectronico());
		licitacionVigenteDTO.setLeyendaLegal(licitacion.getLeyendaLegal());
		licitacionVigenteDTO.setNombreArchivoCondiciones(licitacion.getNombreArchivoPdf());
		licitacionVigenteDTO.setTipoCambio(String.valueOf(licitacion.getTipoCambio()));
		licitacionVigenteDTO.setDescripcionInstrumento(TipoInstrumentoInfoMercadoEnum
				.obtenerTipoPorCodigo(licitacion.getCodTipoInstrumento()).getDescripcion());
		licitacionVigenteDTO.setTipoInstrumento(licitacion.getCodTipoInstrumento());
		licitacionVigenteDTO.setCuentasTitulo(licitacion.getCuentasTitulo());
		licitacionVigenteDTO.setValidaDerechos(licitacion.getValidaDerechos());
		licitacionVigenteDTO.setDescripcionEspecieDerechos(licitacion.getDescripcionEspecieDerechos());
		licitacionVigenteDTO.setRelacionDerechosCanje(licitacion.getRelacionDerechosCanje());
		licitacionVigenteDTO.setValidaDerechos(licitacion.getValidaDerechos());
		licitacionVigenteDTO.setTipoCuenta(licitacion.getTipoCuenta());
		licitacionVigenteDTO.setTipoEjecucion(licitacion.getTipoEjecucion());

		return licitacionVigenteDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#nuevaLicitacion(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * NuevaLicitacionViewReq, java.util.List, java.util.List)
	 */
	@Override
	public Respuesta<NuevaLicitacionDTOResponse> nuevaLicitacion(Cliente cliente, NuevaLicitacionViewReq request,
			List<CuentasAdhesionDebitoView> cuentasDebitoPesos, List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {

		NuevaLicitacionDTOResponse response = new NuevaLicitacionDTOResponse();

		Respuesta<LicitacionesVigentesOutDTO> licitacionesVigentes = obtenerLicitacionesVigentes(cliente,
				cuentasDebitoPesos, cuentasDebitoDolares, true);
		if (!EstadoRespuesta.OK.equals(licitacionesVigentes.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ObtenerCuentasTitulosResponse cuentasTitulo = null;
		try {
			cuentasTitulo = licitacionesDAO.obtenerCuentasTitulos(crearRequestNuevaLicitacion(cliente));
		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO obteniendo cuentas titulos habilitadas para licitar. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		response.setCuentasTitulo(obtenerCuentasParaLicitar(request.getCuentasTitulo(), cuentasTitulo.getDatos()));

		List<LicitacionVigenteDTO> listaLicitacionesVigentes = licitacionesVigentes.getRespuesta()
				.getLicitacionesVigentesList();

		// No tiene cuentas títulos habilitada para Licitar y/o Ninguna especie
		// a Licitar.
		if (response.getCuentasTitulo().isEmpty() || listaLicitacionesVigentes.isEmpty()) {
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setTag(null);
			item.setTipoError(TipoError.ERROR_SIN_LICITACIONES.getDescripcion());
			item.setMensaje(MENSAJE_SIN_LICITACIONES);
			List<ItemMensajeRespuesta> itemMensajes = new ArrayList<ItemMensajeRespuesta>();
			itemMensajes.add(item);
			return respuestaFactory.crearRespuestaWarning(NuevaLicitacionDTOResponse.class, response, itemMensajes);
		}

		for (CuentaTituloParaLicitarView cuentaTitulo : response.getCuentasTitulo()) {
			agregarListaLicitacionesValidaDerechos(licitacionesVigentes, cuentaTitulo);
			if (cuentaTitulo.getIntervinientes().isEmpty()) {
				// Criterio 2. No tiene ninguna especie a licitar.
				response.getCuentasTitulo().remove(cuentaTitulo);
			}

		}
		// warning por si no tiene cuentaTitulo
		if (response.getCuentasTitulo().isEmpty()) {
			return respuestaFactory.crearRespuestaWarning(response, "", TipoError.WARNING, "");
		} else {
			return respuestaFactory.crearRespuestaOk(NuevaLicitacionDTOResponse.class, response);
		}
	}

	/**
	 * Agregar lista licitaciones valida derechos.
	 *
	 * @param licitacionesVigentes the licitaciones vigentes
	 * @param cuentaTitulo         the cuenta titulo
	 */
	private void agregarListaLicitacionesValidaDerechos(Respuesta<LicitacionesVigentesOutDTO> licitacionesVigentes,
			CuentaTituloParaLicitarView cuentaTitulo) {

		List<LicitacionVigenteDTO> listaLicitacionesVigentesResult = new ArrayList<LicitacionVigenteDTO>();
		List<LicitacionVigenteDTO> listaLicitacionesVigentes = licitacionesVigentes.getRespuesta()
				.getLicitacionesVigentesList();
		for (LicitacionVigenteDTO licitacionVigenteDTO : listaLicitacionesVigentes) {
			if (SI.equals(licitacionVigenteDTO.getValidaDerechos())) {
				for (CanalTramoCtaTitulo canalTramoCtaTitulo : licitacionVigenteDTO.getCuentasTitulo()) {
					if (Long.toString(canalTramoCtaTitulo.getCuentaTitulo())
							.equals(ISBANStringUtils.eliminarCeros(cuentaTitulo.getNroCuenta().replace("/", "")))) {
						listaLicitacionesVigentesResult.add(
								validaDerechoConvertitLicitacionDTO(licitacionVigenteDTO, cuentaTitulo.getNroCuenta()));
					}
				}
			} else {
				listaLicitacionesVigentesResult.add(licitacionVigenteDTO);
			}
		}
		cuentaTitulo.setLicitacionesVigentesList(dtoToView(listaLicitacionesVigentesResult));
	}

	/**
	 * Agregar lista licitaciones valida derechos.
	 *
	 * @param licitacionesVigentes the licitaciones vigentes
	 * @param cuentaTitulo         the cuenta titulo
	 */
	private void agregarListaLicitacionesValidaDerechosBPriv(List<LicitacionVigenteDTO> listaLicitacionesVigentes,
			CuentaTituloParaLicitarViewBPriv cuentaTitulo) {

		List<LicitacionVigenteDTO> listaLicitacionesVigentesResult = new ArrayList<LicitacionVigenteDTO>();
		for (LicitacionVigenteDTO licitacionVigenteDTO : listaLicitacionesVigentes) {
			if (SI.equals(licitacionVigenteDTO.getValidaDerechos())) {
				for (CanalTramoCtaTitulo canalTramoCtaTitulo : licitacionVigenteDTO.getCuentasTitulo()) {
					if (Long.toString(canalTramoCtaTitulo.getCuentaTitulo()).equals(
							ISBANStringUtils.eliminarCeros(cuentaTitulo.getNumeroCuentaTitulo().replace("/", "")))) {
						listaLicitacionesVigentesResult.add(validaDerechoConvertitLicitacionDTO(licitacionVigenteDTO,
								cuentaTitulo.getNumeroCuentaTitulo()));
					}
				}
			} else {
				listaLicitacionesVigentesResult.add(licitacionVigenteDTO);
			}
		}
		cuentaTitulo.getLicitacionesVigentesList().addAll(dtoToView(listaLicitacionesVigentesResult));
	}

	/**
	 * Valida derecho convertit licitacion DTO.
	 *
	 * @param licitacionVigente the licitacion vigente
	 * @param cuentaTitulo      the cuenta titulo
	 * @return the licitacion vigente DTO
	 */
	private LicitacionVigenteDTO validaDerechoConvertitLicitacionDTO(LicitacionVigenteDTO licitacionVigente,
			String cuentaTitulo) {
		for (CanalTramoCtaTitulo cuentaTit : licitacionVigente.getCuentasTitulo()) {
			if (Long.toString(cuentaTit.getCuentaTitulo()).equals(cuentaTitulo.replace("/", ""))) {
				licitacionVigente.setCantidad(cuentaTit.getCantidad());
				licitacionVigente.setCantidadMaxima(cuentaTit.getCantidadMaxima());
			}
		}

		return licitacionVigente;
	}

	/**
	 * Obtener tipo moneda para licitacion.
	 *
	 * @param cuentasDebitoPesos   the cuentas debito pesos
	 * @param cuentasDebitoDolares the cuentas debito dolares
	 * @return retorna un String "A" Si tengo cuentasDebito en Pesos y Dolares. "D"
	 *         Si tengo solo cuentasDebito en Dolares. "P" Si solo tengo
	 *         cuentasDebito en Pesos. "" Si no tengo cuentasDebito ni en Pesos ni
	 *         en Dolares.
	 */
	private String obtenerTipoMonedaParaLicitacion(List<CuentasAdhesionDebitoView> cuentasDebitoPesos,
			List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {

		if (!cuentasDebitoPesos.isEmpty() && !cuentasDebitoDolares.isEmpty()) {
			return "A";
		}
		if (!cuentasDebitoPesos.isEmpty() && cuentasDebitoDolares.isEmpty()) {
			return CODIGO_PESOS;
		}
		if (cuentasDebitoPesos.isEmpty() && !cuentasDebitoDolares.isEmpty()) {
			return CODIGO_DOLARES;
		}
		String res = "";
		if (cuentasDebitoPesos.isEmpty() && cuentasDebitoDolares.isEmpty()) {
			return res;
		}
		return res;
	}

	/**
	 * Obtener licitaciones por moneda.
	 *
	 * @param licitacionesVigentes the licitaciones vigentes
	 * @param tipoMoneda           the tipo moneda
	 * @return the list
	 */
	private List<LicitacionVigenteDTO> obtenerLicitacionesPorMoneda(LicitacionesVigentesOutDTO licitacionesVigentes,
			String tipoMoneda) {
		List<LicitacionVigenteDTO> listaLicitacionesVigentesResult = new ArrayList<LicitacionVigenteDTO>();
		List<LicitacionVigenteDTO> listaLicitacionesVigentes = licitacionesVigentes.getLicitacionesVigentesList();
		for (LicitacionVigenteDTO licitacionVigenteDTO : listaLicitacionesVigentes) {
			if (ARS.equals(licitacionVigenteDTO.getMonedaLicitacion())
					&& (CODIGO_PESOS.equals(tipoMoneda) || "A".equals(tipoMoneda))) {
				listaLicitacionesVigentesResult.add(licitacionVigenteDTO);
			} else if (USD.equals(licitacionVigenteDTO.getMonedaLicitacion())
					&& (CODIGO_DOLARES.equals(tipoMoneda) || "A".equals(tipoMoneda))) {
				listaLicitacionesVigentesResult.add(licitacionVigenteDTO);
			}
		}
		return listaLicitacionesVigentesResult;
	}

	/**
	 * Dto to view.
	 *
	 * @param licitacionesVigentesDTO the licitaciones vigentes DTO
	 * @return the list
	 */
	private List<LicitacionVigente> dtoToView(List<LicitacionVigenteDTO> licitacionesVigentesDTO) {
		List<LicitacionVigente> licitacionesVigentes = new ArrayList<LicitacionVigente>();
		try {
			for (LicitacionVigenteDTO licitacionVigenteDTO : licitacionesVigentesDTO) {
				LicitacionVigente licitacion = new LicitacionVigente();
				BeanUtils.copyProperties(licitacion, licitacionVigenteDTO);
				licitacionesVigentes.add(licitacion);
			}
		} catch (IllegalAccessException e) {
			LOGGER.error("Error en BO convirtiendo de DTO a View. ", e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error en BO convirtiendo de DTO a View. ", e.getMessage(), e);
		}
		return licitacionesVigentes;
	}

	/**
	 * Obtener cuentas para licitar.
	 *
	 * @param cuentasTitTotales the cuentas tit totales
	 * @param datosCuentasTit   the datos cuentas tit
	 * @return the list
	 */
	// private List<CuentaTituloParaLicitarView>
	// obtenerCuentasCustodiaParaLicitar(List<DatosObtenerSaldoCuentasCustodiaResponse>
	// listCuentas, List<LicitacionVigenteDTO> listLicitaciones) {
	// List<CuentaTituloParaLicitarView> responseView = new
	// ArrayList<CuentaTituloParaLicitarView>();
	// for (DatosObtenerSaldoCuentasCustodiaResponse informacionCuentaTitulo :
	// listCuentas) {
	// CuentaTituloParaLicitarView cuentaTituloAdd = new
	// CuentaTituloParaLicitarView();
	// cuentaTituloAdd.setIntervinientes(null);
	// cuentaTituloAdd.setNroCuenta(String.valueOf(informacionCuentaTitulo.getCuentaCustodia()));
	// cuentaTituloAdd.setSucursal(null);
	// agregarListaLicitacionesValidaDerechosBPriv(listLicitaciones,
	// cuentaTituloAdd);
	// responseView.add(cuentaTituloAdd);
	// }
	// return responseView;
	// }

	/**
	 * Obtener cuentas para licitar.
	 *
	 * @param cuentasTitTotales the cuentas tit totales
	 * @param datosCuentasTit   the datos cuentas tit
	 * @return the list
	 */
	private List<CuentaTituloParaLicitarView> obtenerCuentasParaLicitar(List<CuentaTituloView> cuentasTitTotales,
			List<DatosObtenerCuentasTitulosResponse> datosCuentasTit) {
		List<CuentaTituloParaLicitarView> responseView = new ArrayList<CuentaTituloParaLicitarView>();
		for (DatosObtenerCuentasTitulosResponse informacionCuentaTitulo : datosCuentasTit) {
			// PREGUNTO SI ESTA HABILITADA PARA LICITAR
			if (informacionCuentaTitulo.getStatusCv() == null || CERO.equals(informacionCuentaTitulo.getStatusCv())) {
				for (CuentaTituloView cuentaTituloView : cuentasTitTotales) {
					String cuentaComparar = StringUtils.right(cuentaTituloView.getNroCuenta().replace("/", ""),
							LARGO_NUMERO_CUENTA);
					// BUSCO LA CUENTA HABILITADA EN LAS TOTALES Y LA AGREGO AL
					// RESPONSE
					if (String.valueOf(StringUtils.leftPad(String.valueOf(informacionCuentaTitulo.getCuentaTitulos()),
							LARGO_CUENTA_TITULOS, "0")).equals(cuentaComparar)) {
						CuentaTituloParaLicitarView cuentaTituloAdd = new CuentaTituloParaLicitarView();
						cuentaTituloAdd.setIntervinientes(cuentaTituloView.getIntervinientes());
						cuentaTituloAdd.setNroCuenta(cuentaTituloView.getNroCuenta());
						cuentaTituloAdd.setSucursal(cuentaTituloView.getSucursal());
						responseView.add(cuentaTituloAdd);
					}
				}
			}
		}
		return responseView;
	}

	/**
	 * Obtener cuentas para licitar.
	 *
	 * @param cuentasTitTotales the cuentas tit totales
	 * @param datosCuentasTit   the datos cuentas tit
	 * @param cliente           the cliente
	 * @return the list
	 */
	private List<CuentaTituloParaLicitarViewBPriv> obtenerCuentasParaLicitarBPriv(
			List<CuentaTituloView> cuentasTitTotales, List<DatosObtenerCuentasTitulosResponse> datosCuentasTit,
			Cliente cliente) {
		List<CuentaTituloParaLicitarViewBPriv> responseView = new ArrayList<CuentaTituloParaLicitarViewBPriv>();
		List<CuentaTituloDTO> listCuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		try {
			listCuentasTituloDTO = obtenerRelacionOperativaTitulo(cliente);
		} catch (BusinessException e) {
			LOGGER.error("No se pudieron obtener las cuentas", e);
		}

		for (DatosObtenerCuentasTitulosResponse informacionCuentaTitulo : datosCuentasTit) {
			// PREGUNTO SI ESTA HABILITADA PARA LICITAR
			if (informacionCuentaTitulo.getStatusCv() == null || CERO.equals(informacionCuentaTitulo.getStatusCv())) {
				for (CuentaTituloView cuentaTituloView : cuentasTitTotales) {
					for (CuentaTituloDTO cuentaDTO : listCuentasTituloDTO) {
						String cuentaComparar = StringUtils.right(cuentaTituloView.getNroCuenta().replace("/", ""),
								LARGO_NUMERO_CUENTA);
						if (Long.valueOf(cuentaDTO.getNroCuentaFormateado()).equals(Long.valueOf(cuentaComparar))) {

							// BUSCO LA CUENTA HABILITADA EN LAS TOTALES Y LA
							// AGREGO AL
							// RESPONSE
							if (String.valueOf(
									StringUtils.leftPad(String.valueOf(informacionCuentaTitulo.getCuentaTitulos()),
											LARGO_CUENTA_TITULOS, "0"))
									.equals(cuentaComparar)) {
								CuentaTituloParaLicitarViewBPriv cuentaTituloAdd = new CuentaTituloParaLicitarViewBPriv();
								cuentaTituloAdd.setIntervinientes(cuentaTituloView.getIntervinientes());
								cuentaTituloAdd.setNumeroCuentaTitulo(cuentaTituloView.getNroCuenta());
								cuentaTituloAdd.setNumeroCuentaOperativa(cuentaDTO.getCuentaOperativaSinFormatear());
								cuentaTituloAdd.setNumeroCuentaOperativaFormateado(cuentaDTO.getCuentaOperativa());
								cuentaTituloAdd.setSucursal(cuentaDTO.getSucursal());
								responseView.add(cuentaTituloAdd);
							}
						}
					}

				}
			}
		}
		return responseView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#reversarOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.view.ReversaRequestView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	public Respuesta<ReversarOrdenResponse> reversarOrdenLicitacion(ReversaRequestView request, Cliente cliente) {

		boolean permiteReintentar;

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		ReversarOrdenResponse response = new ReversarOrdenResponse();
		try {
			response = licitacionesDAO.reversarOrdenLicitacion(crearRequestReversaOrden(request, cliente));
		} catch (TimeOutException e) {
			LOGGER.error("TimeOut en ReversarOrden", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION, request.getDescripcion());
		} catch (DAOException e) {
			LOGGER.error("Error reversando orden", e.getMessage(), e);
		} catch (BusinessException e) {
			LOGGER.error("Error generando request para reversa de orden", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION, request.getDescripcion());
		}
		if ("0".equals(response.getCodigo())) {
			return respuestaFactory.crearRespuestaOk(response, "", CodigoMensajeConstantes.REVERSA_ORDEN_LICITACION_OK);
		}

		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_REINTENTAR,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION, request.getDescripcion());

		}
	}

	/**
	 * Creo el objeto para el llamado al ws de reversa de orden.
	 *
	 * @param request the request
	 * @param cliente the cliente
	 * @return the reversar orden entity
	 * @throws BusinessException the business exception
	 */
	private ReversarOrdenEntity crearRequestReversaOrden(ReversaRequestView request, Cliente cliente)
			throws BusinessException {
		DatosReversarOrden datosReversa = new DatosReversarOrden();
		datosReversa.setNup(cliente.getNup());
		datosReversa.setPasswordRacf(cliente.getPasswordRacf());
		datosReversa.setUsuarioRacf(cliente.getUsuarioRacf());
		datosReversa.setIp(NetworkUtil.getHostAddress());
		datosReversa.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		if ("BR".equals(request.getBanca())) {
			datosReversa.setCanal(canalRTL);
			datosReversa.setSubcanal(subcanalRTL);
		} else {
			datosReversa.setCanal(canalBPriv);
			datosReversa.setSubcanal(subcanalBPriv);
		}
		datosReversa.setNumOrden(Long.parseLong(request.getNumOrden().trim()));
		if (request.isCanje()) {
			datosReversa.setTipoEjecucion("0");
		}
		ReversarOrdenEntity requestEntity = new ReversarOrdenEntity(datosReversa);
		requestEntity.setDatos(datosReversa);
		requestEntity.setFirma(generarFirma(dato));
		requestEntity.setDato(dato);
		return requestEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#finalizarReversarOrden(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<FinalizarReversarOrdenDTO> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest,
			Cliente cliente, TipoBancaEnum banca) {
		FinalizarReversaOrdenesRequestEntity requestDao;
		FinalizarReversarOrdenDTO finalizarReversarOrdenDTO = new FinalizarReversarOrdenDTO();
		String codigoMensaje;

		boolean permiteReintentar;

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
			// provisorio
			if (sessionParametros.getContador().excedeReintentos()) {
				sessionParametros.setContador(new ContadorIntentos(2));
			}
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		try {
			requestDao = generarRequestFinalizarReversarOrdenes(viewRequest, cliente, banca);
			RespuestaDatosOrdenResponse responseDao = ordenesTitulosDAO.finalizarReversarOrdenes(requestDao);
			finalizarReversarOrdenDTO = crearDTOFinalizarReversarOrdenesResponse(responseDao, viewRequest);
			return respuestaFactory.crearRespuestaOk(FinalizarReversarOrdenDTO.class, finalizarReversarOrdenDTO);

		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				codigoMensaje = FINALIZAR_REVERSA_FALLO_ORDENES_VENTA;
			} else {
				codigoMensaje = FINALIZAR_REVERSA_FALLO_ORDENES_COMPRA;
			}
		} catch (DAOException e) {
			LOGGER.error("Error en BO Finalizar reversar Ordenes. ", e.getMessage(), e);
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				codigoMensaje = FINALIZAR_REVERSA_FALLO_ORDENES_VENTA;
			} else {
				codigoMensaje = FINALIZAR_REVERSA_FALLO_ORDENES_COMPRA;
			}
		}
		String mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
		String mensajeError = MessageFormat.format(mensaje, viewRequest.getEspecieDescripcion());
		finalizarReversarOrdenDTO.setMensaje(mensajeError);

		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenDTO.class,
					finalizarReversarOrdenDTO.getMensaje(), "CON_REINTENTOS");
		} else {
			// return
			// respuestaFactory.crearRespuestaError(FinalizarReversarOrdenDTO.class,
			// finalizarReversarOrdenDTO.getMensaje(),TipoError.ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO.toString());
			return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenDTO.class,
					finalizarReversarOrdenDTO.getMensaje(), "SIN_REINTENTOS");
		}
	}

	/**
	 * Crear DTO finalizar reversar ordenes response.
	 *
	 * @param responseDao the response dao
	 * @param viewRequest the view request
	 * @return the finalizar reversar orden DTO
	 */
	private FinalizarReversarOrdenDTO crearDTOFinalizarReversarOrdenesResponse(RespuestaDatosOrdenResponse responseDao,
			FinalizarReversarOrdenViewRequest viewRequest) {
		FinalizarReversarOrdenDTO finalizarReversarOrdenDTO = new FinalizarReversarOrdenDTO();
		finalizarReversarOrdenDTO.setFechaComprobante(formatter.format(new Date()));
		finalizarReversarOrdenDTO
				.setNroComprobante(String.valueOf(responseDao.getDatosOrdenResponse().getNumComprobante()));
		if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_REVERSA_OK_ORDENES_VENTA).getMensaje();
			String mensajeOk = MessageFormat.format(mensaje, viewRequest.getEspecieDescripcion());
			finalizarReversarOrdenDTO.setMensaje(mensajeOk);
		} else {
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_REVERSA_OK_ORDENES_COMPRA).getMensaje();
			String mensajeOk = MessageFormat.format(mensaje, viewRequest.getEspecieDescripcion());
			finalizarReversarOrdenDTO.setMensaje(mensajeOk);
		}
		return finalizarReversarOrdenDTO;
	}

	/**
	 * Generar request finalizar reversar ordenes.
	 *
	 * @param viewRequest the view request
	 * @param cliente     the cliente
	 * @param banca       the banca
	 * @return the finalizar reversa ordenes request entity
	 * @throws BusinessException the business exception
	 */
	private FinalizarReversaOrdenesRequestEntity generarRequestFinalizarReversarOrdenes(
			FinalizarReversarOrdenViewRequest viewRequest, Cliente cliente, TipoBancaEnum banca)
			throws BusinessException {
		FinalizarReversaOrdenesRequestEntity request = new FinalizarReversaOrdenesRequestEntity();
		DatosFinalizarReversarOrden datos = new DatosFinalizarReversarOrden();
		request.setFirma(generarFirma(datoTitulos));
		request.setDato("Online_Banking_Personas");
		datos.setNumeroOrden(viewRequest.getNumeroOrden());
		datos.setCantidad(viewRequest.getCantidadReversada());
		String[] fechaIngreso = viewRequest.getFechaIngreso().split("T");
		datos.setFechaIngreso(fechaIngreso[0].replace("-", "/"));
		datos.setMotivoReversa(viewRequest.getMotivoReversa());
		datos.setIp(NetworkUtil.getHostAddress());
		if (TipoBancaEnum.BANCA_PERSONAL.equals(banca)) {
			datos.setCanal(canalOrdenesRTL);
			datos.setSubCanal(subCanalOrdenesRTL);
			request.setCanal(canalOrdenesRTL);
			request.setSubCanal(subCanalOrdenesRTL);
		} else {
			datos.setCanal(canalOrdenesBPriv);
			datos.setSubCanal(subCanalOrdenesBPriv);
			request.setCanal(canalOrdenesBPriv);
			request.setSubCanal(subCanalOrdenesBPriv);
		}
		datos.setUsuarioRacf(cliente.getUsuarioRacf());
		datos.setPasswordRacf(cliente.getPasswordRacf());
		datos.setNup(cliente.getNup());
		request.setDatos(datos);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#simularLicitacion(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionViewResponse> simularLicitacion(Cliente cliente, SimularLicitacionRequest request,
			String tipoBanca) {
		SimularOrden requestSimulacion;
		SimularOrdenResponse resultadoSimulacion;
		SimularLicitacionViewResponse respuesta;
		try {
			requestSimulacion = crearRequestSimulacion(cliente, request, tipoBanca);
			resultadoSimulacion = licitacionesDAO.simularOrden(requestSimulacion);
			respuesta = crearRespuestaView(resultadoSimulacion);
			respuesta.setArchivoCondiciones(descargarCondiciones(request.getNombreArchivoCondiciones()));
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error en suscripcion por fuera de horario. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO,
					CodigoMensajeConstantes.SUSCRIPCION_LICITACION_FUERA_DE_HORARIO);
		} catch (BusinessException e) {
			LOGGER.error("Error en BO de simular suscripcion a licitacion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO simulando licitacion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if ("BR".equalsIgnoreCase(tipoBanca)) {
			DatosEvaluacionRiesgo datosEvaluacionRiesgo = cargarDatosEvaluacionDeRiesgo(request, cliente,
					resultadoSimulacion);
			EvaluacionDeRiesgoResponse resultadoEvaluacionRiesgo = null;
			try {
				resultadoEvaluacionRiesgo = inversionDAO.evaluacionDeRiesgo(datosEvaluacionRiesgo);
			} catch (DAOException e) {
				LOGGER.error("Servicio evaluacion de riesgo caido. ", e.getMessage(), e);
				resultadoEvaluacionRiesgo = new EvaluacionDeRiesgoResponse();
				resultadoEvaluacionRiesgo.setTipoDisclaimer("0");
				return respuestaFactory.crearRespuestaWarning(respuesta, "", TipoError.SERVICIO_ERI_CAIDO,
						CodigoMensajeConstantes.SERVICIO_EVALUACION_RIESGO_CAIDO);
			}
			if (resultadoEvaluacionRiesgo != null) {
				sessionParametros.setIdEv(resultadoEvaluacionRiesgo.getIdEvaluacion());
			}
			if (!CERO.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())
					&& resultadoEvaluacionRiesgo.getMensaje().getCantidadDeDisclaimers() > 0) {
				respuesta.setCabecera(resultadoEvaluacionRiesgo.getCabecera());
				respuesta.setPie(resultadoEvaluacionRiesgo.getPie());
			}
			if (INVERSOR_RIESGO_BLOQUEANTE.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())) {
				List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(resultadoEvaluacionRiesgo,
						TipoError.RIESGO_BLOQUEANTE);
				return respuestaFactory.crearRespuestaWarning(SimularLicitacionViewResponse.class, respuesta,
						itemsMensajeRespuesta);

			} else if (INVERSOR_RIESGO_MEDIO.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())) {
				List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(resultadoEvaluacionRiesgo,
						TipoError.RIESGO_MEDIO);
				return respuestaFactory.crearRespuestaWarning(SimularLicitacionViewResponse.class, respuesta,
						itemsMensajeRespuesta);
			}
		}
		return respuestaFactory.crearRespuestaOk(SimularLicitacionViewResponse.class, respuesta);
	}

	/**
	 * Crear request simulacion.
	 *
	 * @param cliente   the cliente
	 * @param request   the request
	 * @param tipoBanca the tipo banca
	 * @return the simular orden
	 * @throws BusinessException the business exception
	 */
	private SimularOrden crearRequestSimulacion(Cliente cliente, SimularLicitacionRequest request, String tipoBanca)
			throws BusinessException {
		SimularOrden requestReturn = new SimularOrden();
		cargarDatosGenericosRequest(requestReturn);
		DatosSimularOrden datos = new DatosSimularOrden();
		requestReturn.setDatos(datos);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setNup(cliente.getNup());
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		if ("BR".equalsIgnoreCase(tipoBanca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
			datos.setSegmento(segmentoRTL);
			datos.setTipoCuentaOper(obtenerTipoCuentaDesdeAbreviatura(request.getTipoCuenta(), request.getMoneda()));
			datos.setCuentaTit(request.getSucursalCuentaTitulo() + request.getNumeroCuentaTitulo().replaceAll("/", ""));
		} else {
			datos.setTipoCuentaOper(obtenerTipoCuentaDesdeAbreviatura(request.getTipoCuenta(), request.getMoneda()));
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
			datos.setSegmento(segmentoBPriv);
			datos.setCuentaTit(String.valueOf(new Long(obtenerCuentaTituloBPriv(cliente, request))));
		}

		// datos.setTipoPliego(tipoPliego); NO LA PIDE EL REQUEST
		datos.setCodigoTramoCanal(request.getCodigoTramoCanal());
		datos.setCodigoPliego(request.getCodigoPliego());
		datos.setCodigoTramo(request.getCodigoTramo());
		datos.setTipoCuenta("O");
		datos.setSucursal(request.getSucursalCuenta());

		if (CUENTA_CUSTODIA.equalsIgnoreCase(request.getTipoCuenta())) {
			datos.setCuentaCust(request.getNumeroCuenta().replaceAll("/", ""));
		} else {
			datos.setCuentaOper(request.getNumeroCuenta().replaceAll("/", ""));
		}

		datos.setCodigoEspecie(request.getCodigoEspecie());
		datos.setMoneda(request.getMoneda());
		if (VALOR_NOMINAL.equals(request.getTipoOferta())) {
			datos.setCantidad(request.getValorTipoOferta());
		}
		if (IMPORTE.equals(request.getTipoOferta())) {
			datos.setMonto(request.getValorTipoOferta());
		}
		datos.setPrecio(request.getPrecioTasa());
		datos.setCorreoElectronico(request.getCorreoElectronico());
		datos.setRenovacion("N");

		if ("S".equals(request.getRenovacion())) {
			datos.setRenovacion(request.getRenovacion());
			datos.setEspecieRenovacion(request.getEspecieRenovacion());
			datos.setCantidadRenovable(request.getCantidadRenovacion());
			datos.setLugarRenovacion(request.getLugarRenovacion());
		}

		datos.setUsuarioRacf(cliente.getUsuarioRacf());
		datos.setPasswordRacf(cliente.getPasswordRacf());

		return requestReturn;
	}

	/**
	 * Obtener cuenta titulo B priv.
	 *
	 * @param cliente the cliente
	 * @param request the request
	 * @return the string
	 */
	private String obtenerCuentaTituloBPriv(Cliente cliente, SimularLicitacionRequest request) {
		for (CuentaBancaPrivada cuenta : cliente.getCuentasBancaPrivada()) {
			String nroCuenta = String.valueOf(new Long(cuenta.getCuentaOperativa().getNroCuentaProducto()));
			if (nroCuenta.equals(request.getNumeroCuenta())) {
				return cuenta.getCuentaTitulo().getNroCuentaProducto();
			}
		}
		return "";
	}

	/**
	 * Obtener tipo cuenta desde abreviatura.
	 *
	 * @param tipoCuentaDebito the tipo cuenta debito
	 * @param moneda           the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaDesdeAbreviatura(String tipoCuentaDebito, String moneda) {
		if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(tipoCuentaDebito)) {
			if (TipoMonedaInversionEnum.USD.getCodigo2().equals(moneda)) {
				return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
			} else {
				return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
			}
		}
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString();
		}
		if (TIPO_CUENTA_OPERATIVA.equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_DOLARES.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
		return null;
	}

	/**
	 * Obtener tipo cuenta desde abreviatura.
	 *
	 * @param tipoCuentaDebito the tipo cuenta debito
	 * @param moneda           the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaDesdeAbreviaturaCanje(String tipoCuentaDebito, String moneda) {
		if (TipoCuenta.CUENTA_UNICA.getAbreviatura().equals(tipoCuentaDebito)) {
			if (TipoMonedaInversionEnum.USD.getCodigo2().equals(moneda)) {
				return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
			} else {
				return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
			}
		}
		if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo().toString();
		}
		if (TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura().equals(tipoCuentaDebito)) {
			return CERO + TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString();
		}
		if (TIPO_CUENTA_OPERATIVA.equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_PESOS.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
		if (TipoCuenta.CUENTA_UNICA_DOLARES.getAbreviatura().equalsIgnoreCase(tipoCuentaDebito)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		}
		return null;
	}

	/**
	 * Obtener tipo cuenta desde abreviatura.
	 *
	 * @param moneda the moneda
	 * @return the string
	 */
	private String obtenerTipoCuentaDesdeAbreviatura(String moneda) {
		if (TipoMonedaInversionEnum.USD.getCodigo2().equals(moneda)) {
			return TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
		} else {
			return CERO + TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
		}
	}

	/**
	 * Crear respuesta view.
	 *
	 * @param rtaSimulacion the rta simulacion
	 * @return the simular licitacion view response
	 */
	private SimularLicitacionViewResponse crearRespuestaView(SimularOrdenResponse rtaSimulacion) {

		SimularLicitacionViewResponse respuesta = new SimularLicitacionViewResponse();
		DatosSimularOrdenResponse datos = rtaSimulacion.getDatos();

		respuesta.setImporteDebitar(String.valueOf(datos.getMontoADeb()));
		respuesta.setFechaDebitoCuenta(
				StringUtils.left(convertirFechaHoraTenenciasLicitaciones(datos.getFechaDebProv()), LARGO_TOTAL_FECHA));
		respuesta.setComisiones(String.valueOf(datos.getMontoComision()));
		respuesta.setImpuestos(String.valueOf(datos.getMontoImpuesto()));
		respuesta.setNumeroOrden(String.valueOf(datos.getNumOrden()));
		respuesta.setLegal(datos.getLeyendaLegal());
		respuesta.setLegalCanal(datos.getLeyendaLegalCan());
		respuesta.setDisclaimer(datos.getDisclaimer());
		respuesta.setTipoPrecio(datos.getTipoPrecio());

		return respuesta;
	}

	/**
	 * Crear respuesta view.
	 *
	 * @param rtaSimulacion the rta simulacion
	 * @return the simular licitacion view response
	 */
	private SimularLicitacionCanjeResponseView crearRespuestaViewCanjeRTL(DatosSimularOrdenResponse datosSimulacion,
			Cliente cliente) {

		SimularLicitacionCanjeResponseView respuesta = new SimularLicitacionCanjeResponseView();
		crearRespuestaSimularCanjeBase(datosSimulacion, respuesta);

		for (Cuenta c : cliente.getCuentasRetail()) {
			if (datosSimulacion.getCuentaTit().equals(Long.valueOf(c.getNroCuentaProducto().replaceAll("/", "")))) {
				respuesta.setIntervinientes(c.getIntervinientes());
			}
		}

		return respuesta;
	}

	private void crearRespuestaSimularCanjeBase(DatosSimularOrdenResponse datosSimulacion,
			SimularLicitacionCanjeResponseView respuesta) {
		respuesta.setFechaDebitoCuenta(StringUtils
				.left(convertirFechaHoraTenenciasLicitaciones(datosSimulacion.getFechaDebProv()), LARGO_TOTAL_FECHA));
		respuesta.setDescripcionEspecie(datosSimulacion.getEspecieDescripcion());
		respuesta.setMonedaEspecie(datosSimulacion.getMonedaEspecie());
		respuesta.setTramo(datosSimulacion.getTramo());
		respuesta.setEspecieDestino(datosSimulacion.getEspecieOtorgarDescripcion());
		respuesta.setMonedaEspecieDestino(datosSimulacion.getCodMonedaEspecieDestino());
		respuesta.setValorNominalACanjear(ISBANStringUtils.formatearValorEnteroYDecimales(datosSimulacion.getCantidad(),
				obtenerDecimalesValoresCanje(new BigDecimal(datosSimulacion.getCantidad()))));
		respuesta.setTipoPrecio(datosSimulacion.getTipoPrecio());
		respuesta.setTipoPrecioDato(TIPO_PRECIO_RATIO.equalsIgnoreCase(datosSimulacion.getTipoPrecio())
				? ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(datosSimulacion.getCoeficiente()),
						obtenerDecimalesValoresCanje(BigDecimal.valueOf(datosSimulacion.getCoeficiente())))
				: TIPO_PRECIO_DATO_DE_CORTE);
		respuesta.setImporteADebitar(datosSimulacion.getMontoADeb() > 0
				? ISBANStringUtils.formatearConComaYVariosDecimales2(String.valueOf(datosSimulacion.getMontoADeb()), 2)
				: null);
		respuesta.setFechaDebitoCuenta(StringUtils
				.left(convertirFechaHoraTenenciasLicitaciones(datosSimulacion.getFechaDebProv()), LARGO_TOTAL_FECHA));
		respuesta.setFechaCierre(StringUtils.left(
				convertirFechaHoraTenenciasLicitaciones(datosSimulacion.getFechaCierre()), LARGO_TOTAL_FECHA_HORA));
		respuesta.setFechaAdjudicacion(StringUtils
				.left(convertirFechaHoraTenenciasLicitaciones(datosSimulacion.getFechaAdjud()), LARGO_TOTAL_FECHA));
		respuesta.setFechaLiquidacion(StringUtils
				.left(convertirFechaHoraTenenciasLicitaciones(datosSimulacion.getFechaLiq()), LARGO_TOTAL_FECHA));
		respuesta.setTipoCambio(
				formatearTipoCambio(String.valueOf(datosSimulacion.getTipoCambio()), datosSimulacion.getMoneda(),
						datosSimulacion.getMonedaEspecie(), datosSimulacion.getCodMonedaEspecieDestino()));
		respuesta.setNumeroOrden(datosSimulacion.getNumOrden());
		respuesta.setEmail(datosSimulacion.getCorreoElect());
		if (datosSimulacion.getMontoComision() > 0) {
			respuesta.setComisiones(ISBANStringUtils
					.formatearConComaYVariosDecimales2(String.valueOf(datosSimulacion.getMontoComision()), 2));
		}
		if (datosSimulacion.getMontoImpuesto() > 0) {
			respuesta.setImpuestos(ISBANStringUtils
					.formatearConComaYVariosDecimales2(String.valueOf(datosSimulacion.getMontoImpuesto()), 2));
		}
		respuesta.setLegal(datosSimulacion.getLeyendaLegal());
		respuesta.setLegalCanal(datosSimulacion.getLeyendaLegalCan());
		respuesta.setCuentaOperativa(ISBANStringUtils.formatearSucursal(String.valueOf(datosSimulacion.getSucursal()))
				+ "-" + ISBANStringUtils.formatearNumeroCuenta(String.valueOf(datosSimulacion.getCuentaOper())));
		respuesta.setTipoCuentaOperativa(datosSimulacion.getTipoCuentaOper());
		respuesta.setCuentaTitulos(
				ISBANStringUtils.formatearNumeroCuenta(String.valueOf(datosSimulacion.getCuentaTit())));
		respuesta.setMoneda(datosSimulacion.getMoneda());
	}

	/**
	 * Crear respuesta view.
	 *
	 * @param rtaSimulacion the rta simulacion
	 * @return the simular licitacion view response
	 */
	private SimularLicitacionCanjeResponseView crearRespuestaViewCanjeBPriv(DatosSimularOrdenResponse datosSimulacion,
			Cliente cliente) {

		SimularLicitacionCanjeResponseView respuesta = new SimularLicitacionCanjeResponseView();
		crearRespuestaSimularCanjeBase(datosSimulacion, respuesta);

		for (CuentaBancaPrivada cuenta : cliente.getCuentasBancaPrivada()) {
			String nroCuenta = String.valueOf(new Long(cuenta.getCuentaOperativa().getNroCuentaProducto()));
			if (nroCuenta.equals(String.valueOf(datosSimulacion.getCuentaOper()))) {
				respuesta.setIntervinientes(cuenta.getCuentaTitulo().getIntervinientes());
			}
		}
		respuesta.setDisclaimer(datosSimulacion.getDisclaimer());
		return respuesta;
	}

	/**
	 * Cargar datos evaluacion de riesgo.
	 *
	 * @param request       the request
	 * @param cliente       the cliente
	 * @param rtaSimulacion the rta simulacion
	 * @return the datos evaluacion riesgo
	 */
	private DatosEvaluacionRiesgo cargarDatosEvaluacionDeRiesgo(SimularLicitacionRequest request, Cliente cliente,
			SimularOrdenResponse rtaSimulacion) {
		DatosEvaluacionRiesgo datosERI = cargarDatosERI(cliente);
		DatosSimularOrdenResponse datosSimulacion = rtaSimulacion.getDatos();

		cargarDatosEriComunesSimularLicitacion(rtaSimulacion, datosERI, datosSimulacion);
		if (VALOR_NOMINAL.equals(request.getTipoOferta())) {
			datosERI.setCantidad(String.valueOf(datosSimulacion.getCantidad()));
		}
		if (IMPORTE.equals(request.getTipoOferta())) {
			datosERI.setMonto(String.valueOf(datosSimulacion.getMonto()));
		}

		return datosERI;
	}

	/**
	 * Cargar datos evaluacion de riesgo.
	 *
	 * @param request       the request
	 * @param cliente       the cliente
	 * @param rtaSimulacion the rta simulacion
	 * @return the datos evaluacion riesgo
	 */
	private DatosEvaluacionRiesgo cargarDatosEvaluacionDeRiesgoCanje(SimularLicitacionCanjeRequest request,
			Cliente cliente, SimularOrdenResponse rtaSimulacion) {
		DatosEvaluacionRiesgo datosERI = cargarDatosERI(cliente);
		DatosSimularOrdenResponse datosSimulacion = rtaSimulacion.getDatos();
		cargarDatosEriComunesSimularLicitacion(rtaSimulacion, datosERI, datosSimulacion);
		datosERI.setCantidad(String.valueOf(datosSimulacion.getCantidad()));
		datosERI.setMonto(String.valueOf(datosSimulacion.getMonto()));
		return datosERI;
	}

	private void cargarDatosEriComunesSimularLicitacion(SimularOrdenResponse rtaSimulacion,
			DatosEvaluacionRiesgo datosERI, DatosSimularOrdenResponse datosSimulacion) {
		datosERI.setTipoOperacion(TIPO_OPERACION_ERI_SUSCRIPCION_LICITACION);
		datosERI.setCompraVenta("1");
		datosERI.setImporteDebCred(String.valueOf(rtaSimulacion.getDatos().getMontoADeb()));
		datosERI.setMoneda(datosSimulacion.getMoneda());
		datosERI.setEspecie(datosSimulacion.getEspecie());
		datosERI.setTipoCuenta(String.valueOf(datosSimulacion.getTipoCuentaOper()));
		datosERI.setSucursalCuenta(String.valueOf(datosSimulacion.getSucursal()));
		datosERI.setNroCuenta(String.valueOf(datosSimulacion.getCuentaOper()));
		datosERI.setNroCuentaTitulo(String.valueOf(datosSimulacion.getCuentaTit()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#configurarLicitacionReinvertir(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> configurarLicitacionReinvertir(Cliente cliente,
			ConfigurarLicitacionViewReq request, String banca) {

		ConfigurarLicitacionOutView configurarLicitacionOutView = new ConfigurarLicitacionOutView();
		ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable;
		ConsultarTenenciaRenovable consultarTenenciaRenovable;
		List<TenenciaRenovable> listaTenenciaRenovable = new ArrayList<TenenciaRenovable>();

		for (String moneda : request.getMonedas()) {
			try {
				consultarTenenciaRenovable = crearRequestConsultarTenenciaRenovable(cliente, request, moneda, banca);
				resultadoConsultarTenenciaRenovable = licitacionesDAO
						.consultarTenenciaRenovable(consultarTenenciaRenovable);

				if (null != resultadoConsultarTenenciaRenovable.getDatos()
						&& !resultadoConsultarTenenciaRenovable.getDatos().isEmpty()) {
					TenenciaRenovable tenenciaRenovable = new TenenciaRenovable();
					tenenciaRenovable.setDatosTenenciaRenovable(resultadoConsultarTenenciaRenovable.getDatos());
					tenenciaRenovable.setMoneda(moneda);
					listaTenenciaRenovable.add(tenenciaRenovable);
				}
				if ("-1".equals(resultadoConsultarTenenciaRenovable.getCodigo().trim())) {
					return respuestaFactory.crearRespuestaWarning(configurarLicitacionOutView, "", TipoError.WARNING,
							"");
				}
				configurarLicitacionOutView.setListaTenenciaRenovable(listaTenenciaRenovable);
			} catch (BusinessException e) {
				LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} catch (DAOException e) {
				LOGGER.error("Error en BO consultar tenencia renovable. ", e.getMessage(), e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		return respuestaFactory.crearRespuestaOk(ConfigurarLicitacionOutView.class, configurarLicitacionOutView);
	}

	/**
	 * Crear request consultar tenencia renovable.
	 *
	 * @param cliente the cliente
	 * @param request the request
	 * @param moneda  the moneda
	 * @param banca   the banca
	 * @return the consultar tenencia renovable
	 * @throws BusinessException the business exception
	 */
	private ConsultarTenenciaRenovable crearRequestConsultarTenenciaRenovable(Cliente cliente,
			ConfigurarLicitacionViewReq request, String moneda, String banca) throws BusinessException {
		ConsultarTenenciaRenovable requestReturn = new ConsultarTenenciaRenovable();
		cargarDatosGenericosRequest(requestReturn);
		DatosConsultarTenenciaRenovable datos = new DatosConsultarTenenciaRenovable();
		requestReturn.setDatos(datos);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setNup(cliente.getNup());
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		/**/
		if ("BR".equals(banca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
			datos.setSegmento(segmentoRTL);
		} else {
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
			datos.setSegmento(segmentoBPriv);
		}

		/**/
		datos.setMoneda(moneda);
		datos.setEspecie(request.getCodigoEspecie());
		datos.setCuentaTitulo(request.getCuentaTitulo());

		return requestReturn;
	}

	/**
	 * Devuelve si las condiciones estan aceptadas o no y completa el view recibido
	 * por parametro con las cuentas custodia que correspondan.
	 * 
	 * @param outEntity
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> establecerCondicionesAceptadas(String nup, String banca) {
		
		ConfigurarLicitacionOutView outView = new ConfigurarLicitacionOutView();
		
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.COMPRAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()) {

			try {
				outView = armarListaCuentasSinValidarFirmas(banca, outView);
			} catch (DAOException e) {
				LOGGER.error("Error al armarListaCuentasSinValidarFirmas. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		else {
			try {
				outView = armarListaCuentasConValidarFirmas(nup, banca, outView);
			} catch (DAOException e) {
				LOGGER.error("Error al consultar firmaCuentasPorNup. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			
		}
		
		return respuestaFactory.crearRespuestaOk(ConfigurarLicitacionOutView.class, outView);
		

	}

	private ConfigurarLicitacionOutView armarListaCuentasConValidarFirmas(String nup, String banca, ConfigurarLicitacionOutView outView) throws DAOException{
		
		
		CuentasFirmadasResponse outEntity = extractoDAO.firmasCuentasPorNup(nup, banca);

		List<CuentaCustodiaView> listaCuentasFirmadas = new ArrayList<CuentaCustodiaView>();
		List<CuentaCustodiaView> listaCuentasSinFirmar = new ArrayList<CuentaCustodiaView>();

		for (CuentaTituloFirmada cuentaTituloEntity : outEntity.getListaCuentas().getValue().getCuentaTituloFirmada()) {
			CuentaCustodiaView cuentaTit = new CuentaCustodiaView();
			cuentaTit.setNumeroCuenta(cuentaTituloEntity.getAtit().getValue());
			cuentaTit
					.setSucursal(StringUtils.leftPad(cuentaTituloEntity.getSuc().getValue(), LARGO_COD_SUCURSAL, CERO));
			if (NO.equals(cuentaTituloEntity.getFirmada().getValue())) {
				listaCuentasSinFirmar.add(cuentaTit);
			} else {
				listaCuentasFirmadas.add(cuentaTit);
			}
			outView.getCuentasCustodiaTotales().add(cuentaTit);
		}
		// SI NO HAY NINGUNA SIN FIRMAR, DEVUELVO LAS FIRMADAS
		if (listaCuentasSinFirmar.isEmpty()) {
			outView.setCuentasCustodia(listaCuentasFirmadas);
			outView.setCondicionesGeneralesAceptadas(true);
		} else {
			// SI HAY AL MENOS UNA SIN FIRMAR, DEVUELVO LA/S SIN FIRMAR
			outView.setCuentasCustodia(listaCuentasSinFirmar);
			outView.setCondicionesGeneralesAceptadas(false);
		}
		// SI HAY AL MENOS UNA FIRMADA, DEBERIA HABER COMPROBANTES DISPONIBLES
		if (!listaCuentasFirmadas.isEmpty()) {
			outView.setComprobantesCnvDisponibles(true);
		} else {
			outView.setComprobantesCnvDisponibles(false);
		}
		
		return outView;
	}

	private ConfigurarLicitacionOutView armarListaCuentasSinValidarFirmas(String banca, ConfigurarLicitacionOutView outView) throws DAOException{
		
		List<CuentaCustodiaView> listaCuentasFirmadas = new ArrayList<CuentaCustodiaView>();

		if (BANCA_PRIVADA.equals(banca) && sesionCliente.getCliente().getCuentasBancaPrivada() != null) {
			List<CuentaBancaPrivada> cuentasBP = sesionCliente.getCliente().getCuentasBancaPrivada();
			for (CuentaBancaPrivada cuentaBP : cuentasBP) {
				CuentaCustodiaView cuentaTit = new CuentaCustodiaView();
				BigDecimal nro = new BigDecimal(cuentaBP.getCuentaOperativa().getNroCuentaProducto());
				String cuentaFormat =  Integer.parseInt(cuentaBP.getCuentaOperativa().getProductoAltair()) + llenarConCerosIzqOTruncar(nro.toString(), 9);
				cuentaTit.setNumeroCuenta(cuentaFormat);
				cuentaTit.setSucursal(cuentaBP.getCuentaOperativa().getNroSucursal());
				listaCuentasFirmadas.add(cuentaTit);
				outView.getCuentasCustodiaTotales().add(cuentaTit);
			}
	
		if (BANCA_PERSONAL.equals(banca) && sesionCliente.getCliente().getCuentasRetail() != null) {
			List<Cuenta> cuentasRTL = sesionCliente.getCliente().getCuentasRetail();
			for (Cuenta cuentaTituloEntity : cuentasRTL) {
				CuentaCustodiaView cuentaTit = new CuentaCustodiaView();
				cuentaTit.setNumeroCuenta(cuentaTituloEntity.getNroCuentaProducto());
				cuentaTit.setSucursal(StringUtils.leftPad(cuentaTituloEntity.getNroSucursal(), LARGO_COD_SUCURSAL, CERO));
				listaCuentasFirmadas.add(cuentaTit);
				outView.getCuentasCustodiaTotales().add(cuentaTit);
			}
			List<Cuenta> cuentasRTLRep = sesionCliente.getCliente().getCuentasTitRtlRepatriacion();
			for (Cuenta cuentaTituloEntity : cuentasRTLRep) {
				CuentaCustodiaView cuentaTit = new CuentaCustodiaView();
				cuentaTit.setNumeroCuenta(cuentaTituloEntity.getNroCuentaProducto());
				cuentaTit.setSucursal(StringUtils.leftPad(cuentaTituloEntity.getNroSucursal(), LARGO_COD_SUCURSAL, CERO));
				listaCuentasFirmadas.add(cuentaTit);
				outView.getCuentasCustodiaTotales().add(cuentaTit);
			}
		}
	}

	outView.setCuentasCustodia(listaCuentasFirmadas);
	outView.setCondicionesGeneralesAceptadas(true);
	outView.setComprobantesCnvDisponibles(true);
	
	return outView;
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#finalizarLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request,
			Cliente cliente, String banca) {

		boolean permiteReintentar;

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		FinalizarLicitacionViewResp responseFinalizar = null;
		try {
			ConfirmarOrden requestDao = armarRequestConfirmarOrden(request, cliente, banca, false);
			ConfirmarOrdenResponse responseDao = licitacionesDAO.confirmarOrden(requestDao);
			responseFinalizar = crearViewResponse(responseDao);
			try {
				if (sessionParametros.getIdEv() != null) {
					inversionDAO.confirmacionOrden(cargarDatosConfirmarOrden(responseDao));
				}
			} catch (DAOException e) {
				LOGGER.error("Error al invocar el Ws ERI confirmar orden. ", e);
			}
		} catch (BusinessException e) {
			LOGGER.error("Error en BO al generar el request de confirmar orden. ", e);
			return manejarReintento(request.getDescripcionEspecie(), permiteReintentar,
					CodigoMensajeConstantes.SUSCRIPCION_LICITACION_ERROR);
		} catch (TiempoEsperaAgotadoException e) {
			LOGGER.error("Error en DAO al confirmar orden, limite de espera agotado. ", e);
			return manejarReintento(null, permiteReintentar,
					CodigoMensajeConstantes.SUSCRIPCION_LICITACION_TIEMPO_ESPERA_AGOTADO);
		} catch (TimeOutException e) {
			LOGGER.error("Error en DAO al confirmar orden, timeout. ", e);
			return manejarReintento(request.getDescripcionEspecie(), false,
					CodigoMensajeConstantes.SUSCRIPCION_LICITACION_ERROR);
		} catch (DAOException e) {
			if (SALDO_INSUFICIENTE_PESOS.equals(e.getMessage()) || SALDO_INSUFICIENTE_DOLARES.equals(e.getMessage())) {
				LOGGER.error("Error, saldo insuficiente ", e);
				return manejarReintento(request.getDescripcionEspecie(), permiteReintentar,
						CodigoMensajeConstantes.SUSCRIBIR_LICITACIONES_SALDO_INSUFICIENTE);
			} else {
				LOGGER.error("Error en DAO al confirmar orden. ", e);
				return manejarReintento(request.getDescripcionEspecie(), permiteReintentar,
						CodigoMensajeConstantes.SUSCRIPCION_LICITACION_ERROR);
			}
		}
		return respuestaFactory.crearRespuestaOk(FinalizarLicitacionViewResp.class, responseFinalizar);
	}

	/**
	 * Devuelve respuesta con el mensaje correspondiente e indicando si reintenta o
	 * no.
	 *
	 * @param nombreEspecie     the nombre especie
	 * @param permiteReintentar the permite reintentar
	 * @param codigoMensaje     the codigo mensaje
	 * @return the respuesta
	 */
	private Respuesta<FinalizarLicitacionViewResp> manejarReintento(String nombreEspecie, boolean permiteReintentar,
			String codigoMensaje) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
		if (nombreEspecie != null) {
			mensaje = MessageFormat.format(mensaje, nombreEspecie);
		}
		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaErrorPersonalizado(FinalizarLicitacionViewResp.class, mensaje,
					TipoError.ERROR_FINALIZAR_LICITACION_CON_REINTENTO.toString());
		}
		return respuestaFactory.crearRespuestaErrorPersonalizado(FinalizarLicitacionViewResp.class, mensaje,
				TipoError.ERROR_FINALIZAR_LICITACION_SIN_REINTENTO.toString());
	}

	/**
	 * Crear view response.
	 *
	 * @param responseDao the response dao
	 * @return the finalizar licitacion view resp
	 */
	private FinalizarLicitacionViewResp crearViewResponse(ConfirmarOrdenResponse responseDao) {
		FinalizarLicitacionViewResp responseView = new FinalizarLicitacionViewResp();
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SUSCRIPCION_LICITACION_OK)
				.getMensaje();

		mensaje = MessageFormat.format(mensaje, responseDao.getDatos().getEspecieDescripcion());
		responseView.setMensaje(mensaje);
		responseView.setLeyendaLegal(responseDao.getDatos().getLeyendaLegal());
		responseView.setLeyendaLegalCan(responseDao.getDatos().getLeyendaLegalCan());

		responseView.setDatosConfirmacion(convertirFechas(responseDao.getDatos()));
		return responseView;
	}

	/**
	 * Convertir fechas.
	 *
	 * @param request the request
	 * @return the datos confirmar orden response
	 */
	private DatosConfirmarOrdenResponse convertirFechas(DatosConfirmarOrdenResponse request) {
		request.setFechaOrden(
				StringUtils.left(convertirFechaTenenciasLicitaciones(request.getFechaOrden()), LARGO_TOTAL_FECHA));
		request.setFechaDebProv(
				StringUtils.left(convertirFechaTenenciasLicitaciones(request.getFechaDebProv()), LARGO_TOTAL_FECHA));
		request.setFechaTitulo(
				StringUtils.left(convertirFechaTenenciasLicitaciones(request.getFechaTitulo()), LARGO_TOTAL_FECHA));
		request.setFechaAdjud(
				StringUtils.left(convertirFechaTenenciasLicitaciones(request.getFechaAdjud()), LARGO_TOTAL_FECHA));
		request.setFechaLiq(
				StringUtils.left(convertirFechaTenenciasLicitaciones(request.getFechaLiq()), LARGO_TOTAL_FECHA));
		request.setFechaCierre(convertirFechaHoraTenenciasLicitaciones(request.getFechaCierre()));
		return request;
	}

	/**
	 * Crea el request para el confirmar orden de licitaciones, el que realiza la
	 * suscripcion a la licitacion.
	 *
	 * @param request the request
	 * @param cliente the cliente
	 * @return the confirmar orden
	 * @throws BusinessException the business exception
	 */
	private ConfirmarOrden armarRequestConfirmarOrden(FinalizarLicitacionRequest request, Cliente cliente, String banca,
			boolean isCanje) throws BusinessException {

		ConfirmarOrden requestReturn = new ConfirmarOrden();
		cargarDatosGenericosRequest(requestReturn);

		DatosConfirmarOrden datos = new DatosConfirmarOrden();
		requestReturn.setDatos(datos);

		if ("BR".equals(banca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
			if (isCanje) {
				datos.setTipoCuentaOper(request.getTipoCuentaOperativa());
			} else {
				datos.setTipoCuentaOper(
						obtenerTipoCuentaDesdeAbreviatura(request.getTipoCuentaOperativa(), request.getMoneda()));
			}
		} else {
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
			datos.setTipoCuentaOper("2");
		}

		datos.setNumOrden(Long.valueOf(request.getNumeroDeOrden()));
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		datos.setIp(NetworkUtil.getHostAddress());
		if (isCanje) {
			datos.setTipoEjecucion("0");
			datos.setCuentaOper(Long.valueOf(request.getCuentaOperativa().substring(4).replaceAll("/", "")));
			datos.setMontoADeb(request.getMontoDebito() == null ? 0
					: Double.valueOf(request.getMontoDebito().replace(".", "").replaceAll(",", ".")));
		} else {
			datos.setCuentaOper(Long.valueOf(request.getCuentaOperativa().replaceAll("/", "")));
			datos.setMontoADeb(Double.valueOf(request.getMontoDebito()));

		}
		datos.setPasswordRacf(cliente.getPasswordRacf());
		datos.setUsuarioRacf(cliente.getUsuarioRacf());

		return requestReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#obtenerTenenciaTitulosRTL(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutDTO> obtenerTenenciaTitulosRTL(Cliente cliente) {
		InicioFondoDTO inicioFondoDTO = generarCuentasDTO(cliente);
		if (inicioFondoDTO.getCuentasBancaPersonal().isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIN_CUENTA_TITULOS,
					CodigoMensajeConstantes.ERROR_SIN_CUENTA_TITULOS_TITULOS_VALORES);
		}
		List<CuentaTituloDTO> cuentasDTO = generarCuentasRTLTenencias(inicioFondoDTO.getCuentasBancaPersonal());

		DetalleTenenciaValuadaTitulosEntity tenenciaTitulos = new DetalleTenenciaValuadaTitulosEntity();
		try {
			tenenciaTitulos = tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleTitulosOnline(
					createRequestServiceTenencias(cuentasDTO, cliente, Segmento.BANCA_PERSONAL.getCodigo()));
		} catch (DAOException e) {
			LOGGER.info("Error obteniendo la tenencia valuada para titulos ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		TenenciaTitulosMapDTO mapTenencias = new TenenciaTitulosMapDTO(cuentasDTO, BANCA_PERSONAL);
		mapTenencias.cargarTenencias(tenenciaTitulos.getDatos().getResultado());
		TenenciaTitulosOutDTO respBO = mapTenencias.createResponse();

		if (tenenciaTitulos.getCodigo() == 1) {
			List<ItemMensajeRespuesta> list = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.GRILLA_INVERSIONES_PARCIAL.getDescripcion());
			list.add(itemMensajeRespuesta);
			return respuestaFactory.crearRespuestaWarning(respBO, list);
		}

		TenenciaTitulosGraficosMapDTO mapGraficos = new TenenciaTitulosGraficosMapDTO();
		mapGraficos.cargarGraficos(tenenciaTitulos.getDatos().getResultado());
		respBO.setGraficos(mapGraficos.createResponse());
		return respuestaFactory.crearRespuestaOk(TenenciaTitulosOutDTO.class, respBO);
	}

	/**
	 * carga los datos para el request de confirmar orden de ERI.
	 * 
	 * @param respuestaBO the respuesta BO
	 * @return the parametro datos confirmacion orden
	 */
	private ParametroDatosConfirmacionOrden cargarDatosConfirmarOrden(ConfirmarOrdenResponse respuestaBO) {
		ParametroDatosConfirmacionOrden parametroDatos = new ParametroDatosConfirmacionOrden();
		parametroDatos.setCodCanal(COD_CANAL);
		parametroDatos.setNroOperacion(String.valueOf(respuestaBO.getDatos().getNumOrden()));
		parametroDatos.setIdEvaluacion(sessionParametros.getIdEv());
		return parametroDatos;
	}

	/**
	 * Descargar condiciones.
	 *
	 * @param nombreArchivo the nombre archivo
	 * @return the reporte view
	 * @throws BusinessException the business exception
	 */
	private ReporteView descargarCondiciones(String nombreArchivo) throws BusinessException {

		DownloadArchivo requestDownload = new DownloadArchivo();
		DatosDownloadArchivo datosDownloadArchivo = new DatosDownloadArchivo();
		ReporteView reporteView = new ReporteView();

		cargarDatosGenericosRequest(requestDownload);
		requestDownload.setDatos(datosDownloadArchivo);
		datosDownloadArchivo.setNombreArchivo(nombreArchivo);
		datosDownloadArchivo.setRepositorio(PLIEGOS);
		try {
			DownloadArchivoResponse responseDao = licitacionesDAO.downloadArchivo(requestDownload);
			DatosDownloadArchivoResponse datosResponse = responseDao.getDatos();
			reporteView.setBytes(datosResponse.getArchivoBase64());
			reporteView.setNombre(datosResponse.getNombreArchivo());
			reporteView.setTipoArchivo(TipoArchivoEnum.PDF.getMimeTipe());
		} catch (DAOException e) {
			LOGGER.error("Error al invocar el Ws BEL.downloadArchivo ", e);
		}
		return reporteView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#condicionesGeneralesCuentaCustodia(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, boolean)
	 */
	@Override
	public Respuesta<CondicionesGeneralesCuentaCustodiaDTO> condicionesGeneralesCuentaCustodia(Cliente cliente,
			boolean condicionesAceptadas) {
		CondicionesGeneralesCuentaCustodiaDTO dtoResponse = new CondicionesGeneralesCuentaCustodiaDTO();

		Respuesta<String> rtaLegal1 = legalBO.buscarLegal(CONDICIONES_GENERALES_LEGAL1);
		Respuesta<String> rtaLegal2 = legalBO.buscarLegal(CONDICIONES_GENERALES_LEGAL2);
		Respuesta<String> rtaLegal3 = legalBO.buscarLegal(CONDICIONES_GENERALES_LEGAL3);

		if (!EstadoRespuesta.OK.equals(rtaLegal1.getEstadoRespuesta())
				|| !EstadoRespuesta.OK.equals(rtaLegal2.getEstadoRespuesta())
				|| !EstadoRespuesta.OK.equals(rtaLegal3.getEstadoRespuesta())) {
			LOGGER.error("Error al obtener legales de condiciones generales de cuenta custodia.");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		dtoResponse.setLegal1(rtaLegal1.getRespuesta());
		dtoResponse.setLegal2(rtaLegal2.getRespuesta());
		dtoResponse.setLegal3(rtaLegal3.getRespuesta());

		if (!condicionesAceptadas) {
			Respuesta<String> rtaLegalAdicional = legalBO.buscarLegal(CONDICIONES_GENERALES_LEGAL_ADICIONAL);
			if (!EstadoRespuesta.OK.equals(rtaLegalAdicional.getEstadoRespuesta())) {
				LOGGER.error("Error al obtener legales de condiciones generales de cuenta custodia.");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			dtoResponse.setLegalAdicional(rtaLegalAdicional.getRespuesta());
		}

		MyaDTOOut responseMya = myaBO.consultaWsEstadoCliente(cliente, new MyaDTOIn());
		if (responseMya.getEmail() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		dtoResponse.setEmail(responseMya.getEmail());

		return respuestaFactory.crearRespuestaOk(CondicionesGeneralesCuentaCustodiaDTO.class, dtoResponse);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#firmarCuentas(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentas(Cliente cliente, FirmarCuentasViewRequest viewRequest) {
		try {

			FirmarCuentasInEntity requestEntity = viewToEntity(viewRequest);
			requestEntity.setEmail(StringUtils.rightPad(viewRequest.getEmail(), LONGITUD_60, " "));
			requestEntity.setRazonSocial(
					StringUtils.rightPad(cliente.getNombre() + " " + cliente.getApellido1(), LONGITUD_60, " "));
			firmarCuentasDAO.firmar(cliente, requestEntity);
		} catch (DAOException e) {
			LOGGER.error("Error al firmar las cuentas.", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(String.class);
	}

	/**
	 * View to entity.
	 *
	 * @param viewRequest the view request
	 * @return the firmar cuentas in entity
	 */
	private FirmarCuentasInEntity viewToEntity(FirmarCuentasViewRequest viewRequest) {

		FirmarCuentasInEntity response = new FirmarCuentasInEntity();

		for (CuentaCustodiaView cuentaCustodia : viewRequest.getCuentasCustodia()) {
			CuentasPorFirmarEntity cuentaEntity = new CuentasPorFirmarEntity();
			cuentaEntity.setNumeroDeCuenta(
					StringUtils.leftPad(cuentaCustodia.getNumeroCuenta(), LONG_CUENTA_REQUEST, CERO));
			cuentaEntity.setNumeroDeSucursal(StringUtils.leftPad(cuentaCustodia.getSucursal(), LONG_SUC_REQUEST, CERO));
			response.getCuentasPorFirmarList().add(cuentaEntity);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#consultarOperacionesText(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ConsultaOperaciones)
	 */
	@Override

	public Respuesta<ConsultaOperacionesDTO> consultarOperacionesText(ConsultaOperaciones viewRequest) {

		ConsultaOperacionesRequestEntity requestDao;
		ConsultaOperacionesDTO consultaOperacionesDTO = new ConsultaOperacionesDTO();

		try {
			requestDao = generarRequestConsultarOperacionesText(viewRequest);

			List<DatosConsultaOperacionesResponse> responseDao = titulosMercadoCanalDAO
					.consultarOperacionesText(requestDao);
			consultaOperacionesDTO = crearViewConsultarOperacionesResponse(responseDao, viewRequest);

		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO consultar Operaciones Text. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		boolean tieneOperaciones = false;
		for (OperacionesPorCuenta cuentaTitulo : consultaOperacionesDTO.getCuentasTitulo()) {
			if (!cuentaTitulo.getOperaciones().isEmpty()) {
				tieneOperaciones = true;
				break;
			}
		}
		if (!tieneOperaciones) {
			return respuestaFactory.crearRespuestaWarning(consultaOperacionesDTO, "", TipoError.WARNING_SIN_OPERACIONES,
					"");
		}

		return respuestaFactory.crearRespuestaOk(ConsultaOperacionesDTO.class, consultaOperacionesDTO);
	}

	/**
	 * Generar request consultar operaciones text.
	 *
	 * @param viewRequest the view request
	 * @return the consulta operaciones request entity
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("deprecation")
	/**
	 * Generar request para consultar el dao de consultarOperacionesText (titulos)
	 * 
	 * @param viewRequest
	 * @return
	 * @throws BusinessException
	 */
	ConsultaOperacionesRequestEntity generarRequestConsultarOperacionesText(ConsultaOperaciones viewRequest)
			throws BusinessException {
		ConsultaOperacionesRequestEntity request = new ConsultaOperacionesRequestEntity();
		request.setFirma(generarFirma(datoMercadoCanal));
		request.setDato(datoMercadoCanal);

		DatosConsultaOperaciones datos = new DatosConsultaOperaciones();

		String cuentasTitulo = obtenerCuentasTituloConComa(viewRequest.getCuentasTitulo());
		datos.setCuentaTitulo(cuentasTitulo);

		Date fechaActual = new Date();
		String fechaHasta = "\\/Date(" + fechaActual.getTime() + ")\\/";

		Date fechaActualMenosUnAnio = new Date();
		fechaActualMenosUnAnio.setYear(fechaActualMenosUnAnio.getYear() - 1);
		String fechaDesde = "\\/Date(" + fechaActualMenosUnAnio.getTime() + ")\\/";

		datos.setFechaDesde(fechaDesde);
		datos.setFechaHasta(fechaHasta);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));
		request.setDatos(datos);
		return request;
	}

	/**
	 * Crear view consultar operaciones response.
	 *
	 * @param responseDao the response dao
	 * @param viewRequest the view request
	 * @return the consulta operaciones DTO
	 */
	private ConsultaOperacionesDTO crearViewConsultarOperacionesResponse(
			List<DatosConsultaOperacionesResponse> responseDao, ConsultaOperaciones viewRequest) {

		ConsultaOperacionesDTO consultaOperacionesResponse = new ConsultaOperacionesDTO();

		for (CuentaTituloView cuentaTitulo : viewRequest.getCuentasTitulo()) {
			OperacionesPorCuenta cuentaResponse = new OperacionesPorCuenta();
			cuentaResponse.setIntervinientes(cuentaTitulo.getIntervinientes());
			cuentaResponse.setNroCuenta(cuentaTitulo.getNroCuenta());

			for (DatosConsultaOperacionesResponse datosConsultaOperacionesResponse : responseDao) {
				cuentaTitulo.setNroCuenta(
						StringUtils.leftPad(cuentaTitulo.getNroCuenta().replace("/", ""), LARGO_NUMERO_CUENTA, CERO));
				if (cuentaTitulo.getNroCuenta().equals(datosConsultaOperacionesResponse.getCuentaTitulos())) {
					OperacionDTO operacion = entityToDTO(datosConsultaOperacionesResponse);
					cuentaResponse.getOperaciones().add(operacion);
				}
			}
			consultaOperacionesResponse.getCuentasTitulo().add(cuentaResponse);
		}
		return consultaOperacionesResponse;

	}

	/**
	 * Entity to DTO.
	 *
	 * @param datosConsultaOperacionesResponse the datos consulta operaciones
	 *                                         response
	 * @return the operacion DTO
	 */
	private OperacionDTO entityToDTO(DatosConsultaOperacionesResponse datosConsultaOperacionesResponse) {
		OperacionDTO operacionDTO = new OperacionDTO();
		operacionDTO.setCantidad(datosConsultaOperacionesResponse.getCantidad());
		operacionDTO.setCodMoneda(TipoMonedaInversionEnum
				.fromCodigoNumericoString(CERO + datosConsultaOperacionesResponse.getCodMoneda()).getCodigo2());

		String numeroCuenta = datosConsultaOperacionesResponse.getCuentaTitulos();
		numeroCuenta = numeroCuenta.substring(0, numeroCuenta.length() - 1) + SEPARATOR_BARRA_LATERAL
				+ numeroCuenta.substring(numeroCuenta.length() - 1);
		operacionDTO.setCuentaTitulos(numeroCuenta);
		operacionDTO.setEspecie(datosConsultaOperacionesResponse.getEspecie());
		operacionDTO
				.setFechaOrden(convertirFechaTenenciasLicitaciones(datosConsultaOperacionesResponse.getFechaOrden()));
		operacionDTO.setOrdenId(datosConsultaOperacionesResponse.getOrdenId());
		operacionDTO.setSigno(datosConsultaOperacionesResponse.getSigno());
		operacionDTO.setPrecio(datosConsultaOperacionesResponse.getPrecio());
		operacionDTO.setTipoEspecie(datosConsultaOperacionesResponse.getTipoEspecie());
		operacionDTO.setTipoOperacion(datosConsultaOperacionesResponse.getTipoOperacion());

		operacionDTO.setEstado(datosConsultaOperacionesResponse.getEstado());
		operacionDTO.setPlazo(datosConsultaOperacionesResponse.getPlazo());
		operacionDTO.setPrecioLimite(datosConsultaOperacionesResponse.getPrecioLimite());
		operacionDTO.setImporte(datosConsultaOperacionesResponse.getCapital());
		operacionDTO.setCuentaDebito(datosConsultaOperacionesResponse.getSucCtaOper() + "-"
				+ ISBANStringUtils.formatearNumeroCuenta(datosConsultaOperacionesResponse.getNroCtaOper()));
		operacionDTO.setCanal(obtenerCanal(datosConsultaOperacionesResponse.getCanal(), TipoBancaEnum.BANCA_PERSONAL));

		operacionDTO
				.setTipoCuentaOperativa(obtenerTipoCuentaOperativa(datosConsultaOperacionesResponse.getTipoCtaOper()));
		return operacionDTO;
	}

	/**
	 * Obtener tipo cuenta operativa.
	 *
	 * @param codigoCuentaOperativa the codigo cuenta operativa
	 * @return the string
	 */
	private String obtenerTipoCuentaOperativa(String codigoCuentaOperativa) {

		if (CEROCERO.equals(codigoCuentaOperativa) || CEROTRES.equals(codigoCuentaOperativa)) {
			return CUENTA_CORRIENTE;
		}
		if (CEROUNO.equals(codigoCuentaOperativa) || CEROCUATRO.equals(codigoCuentaOperativa)) {
			return CAJA_AHORRO;
		}
		if (CERODOS.equals(codigoCuentaOperativa) || CERONUEVE.equals(codigoCuentaOperativa)
				|| DIEZ.equals(codigoCuentaOperativa)) {
			return CUENTA_UNICA;
		}
		return codigoCuentaOperativa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#listarComprobantesCNV(ar.com.santanderrio.obp.servicios.inversiones
	 * .titulos.licitaciones.view.ListarComprobantesViewRequest)
	 */
	@Override
	public Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest) {

		FiltroListaComprobantesOrExt filtro = crearFiltroListarComprobantesOrExt(viewRequest);
		ListarComprobantesViewResponse viewResponse = new ListarComprobantesViewResponse();
		try {
			ComprobanteResponse outEntity = scompDAO.listaComprobantesOrExt(filtro);
			if (outEntity.getRespuestaScomp().getComprobante() == null
					|| outEntity.getRespuestaScomp().getComprobante().isEmpty()) {
				return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.SIN_COMPROBANTES_DISPONIBLES);
			}
			viewResponse = entityToView(outEntity);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener la lista de comprobantes.");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(ListarComprobantesViewResponse.class, viewResponse);
	}

	/**
	 * Entity to view.
	 *
	 * @param outEntity the out entity
	 * @return the listar comprobantes view response
	 */
	private ListarComprobantesViewResponse entityToView(ComprobanteResponse outEntity) {
		ListarComprobantesViewResponse viewResponse = new ListarComprobantesViewResponse();
		for (ComprobanteScomp comprobanteEntity : outEntity.getRespuestaScomp().getComprobante()) {
			ComprobanteDisponibleView comprobanteView = new ComprobanteDisponibleView();
			comprobanteView.setIdComprobante(comprobanteEntity.getId());
			comprobanteEntity.setFechaOper(StringUtils.left(comprobanteEntity.getFechaOper(), LARGO_TOTAL_FECHA));
			comprobanteView.setFecha(comprobanteEntity.getFechaOper().replaceAll("-", "/"));
			comprobanteEntity.setHoraOper(StringUtils.left(comprobanteEntity.getHoraOper(), LARGO_HORA));
			comprobanteView.setHora(comprobanteEntity.getHoraOper());
			viewResponse.getComprobantes().add(comprobanteView);
		}
		return viewResponse;
	}

	/**
	 * Crear filtro listar comprobantes or ext.
	 *
	 * @param viewRequest the view request
	 * @return the filtro lista comprobantes or ext
	 */
	private FiltroListaComprobantesOrExt crearFiltroListarComprobantesOrExt(ListarComprobantesViewRequest viewRequest) {

		FiltroListaComprobantesOrExt filtroRequest = new FiltroListaComprobantesOrExt();

		for (CuentaTituloView cuentaTituloView : viewRequest.getCuentasTitulo()) {
			String cuentaRequest = StringUtils.leftPad(cuentaTituloView.getSucursal(), LARGO_COD_SUCURSAL, CERO) + "-";
			cuentaRequest = cuentaRequest + StringUtils.leftPad(cuentaTituloView.getNroCuenta().replaceAll("/", ""),
					LONG_CUENTA_REQUEST, CERO);
			filtroRequest.getCuentasList().add(cuentaRequest);
		}
		return filtroRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#obtenerComprobanteCNV(java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(String idComprobante) {

		FiltroDetalleComprobante filtro = new FiltroDetalleComprobante();
		ComprobanteCnvViewResponse viewResponse = new ComprobanteCnvViewResponse();
		filtro.setIdComp(idComprobante);

		try {
			ComprobanteResponse outEntity = scompDAO.detalleComprobante(filtro);
			viewResponse = entityToViewComprobanteCnv(outEntity);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener la lista de comprobantes.");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(ComprobanteCnvViewResponse.class, viewResponse);
	}

	/**
	 * Entity to view comprobante cnv.
	 *
	 * @param outEntity the out entity
	 * @return the comprobante cnv view response
	 */
	private ComprobanteCnvViewResponse entityToViewComprobanteCnv(ComprobanteResponse outEntity) {
		ComprobanteCnvViewResponse viewResponse = new ComprobanteCnvViewResponse();
		viewResponse.setHtmlResponse(outEntity.getDetalleHtml());
		return viewResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#consultarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.ordenes.view.ConsultarOrdenesViewRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConsultaOrdenesDTO> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest, Cliente cliente,
			TipoBancaEnum banca) {
		ConsultaOrdenesRequestEntity requestDao;
		ConsultaOrdenesDTO consultaOrdenesDTO = new ConsultaOrdenesDTO();
		try {
			requestDao = generarRequestConsultaOrdenes(viewRequest, cliente, banca);
			DatosConsultaOrdenes responseDao = ordenesTitulosDAO.consultarOrdenes(requestDao);
			consultaOrdenesDTO = crearDTOConsultaOrdenesResponse(responseDao, viewRequest);

		} catch (BusinessException e) {
			LOGGER.error("Error en BO obteniendo firma. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO consultar Ordenes. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(ConsultaOrdenesDTO.class, consultaOrdenesDTO);
	}

	/**
	 * Filtrar ordenes por especie.
	 *
	 * @param responseDao the response dao
	 * @param viewRequest the view request
	 * @return the list
	 */
	private List<DatosConsultaOrdenesResponse> filtrarOrdenesPorEspecie(DatosConsultaOrdenes responseDao,
			ConsultarOrdenesViewRequest viewRequest) {

		List<DatosConsultaOrdenesResponse> ordenesFiltradas = new ArrayList<DatosConsultaOrdenesResponse>();
		for (DatosConsultaOrdenesResponse datosConsultaOrdenesResponse : responseDao.getDatos()
				.getListaConsultaOrdenes()) {
			if ((datosConsultaOrdenesResponse.getInstrumentoCodigo() != null)
					&& (datosConsultaOrdenesResponse.getInstrumentoCodigo().equals(viewRequest.getEspecie())
							|| (ALL.equals(viewRequest.getEspecie())))) {
				ordenesFiltradas.add(datosConsultaOrdenesResponse);
			}
		}
		return ordenesFiltradas;
	}

	/**
	 * Filtrar ordenes por nominales.
	 *
	 * @param responseDao the response dao
	 * @param viewRequest the view request
	 * @return the list
	 */
	private List<DatosConsultaOrdenesResponse> filtrarOrdenesPorNominales(DatosConsultaOrdenes responseDao,
			ConsultarOrdenesViewRequest viewRequest) {
		List<DatosConsultaOrdenesResponse> ordenesFiltradas = new ArrayList<DatosConsultaOrdenesResponse>();
		for (DatosConsultaOrdenesResponse datosConsultaOrdenesResponse : responseDao.getDatos()
				.getListaConsultaOrdenes()) {
			if ((datosConsultaOrdenesResponse.getCantidadInicial() != null)) {
				String[] cantidadInicial = datosConsultaOrdenesResponse.getCantidadInicial().split("\\.");
				if ((Integer.parseInt(viewRequest.getCantidadNominalDesde()) <= Integer.parseInt((cantidadInicial[0]))
						&& (Integer.parseInt(viewRequest.getCantidadNominalHasta()) >= Integer
								.parseInt(cantidadInicial[0])))) {
					ordenesFiltradas.add(datosConsultaOrdenesResponse);
				}
			}
		}
		return ordenesFiltradas;
	}

	/**
	 * Crear DTO consulta ordenes response.
	 *
	 * @param responseDao the response dao
	 * @param viewRequest the view request
	 * @return the consulta ordenes DTO
	 */
	private ConsultaOrdenesDTO crearDTOConsultaOrdenesResponse(DatosConsultaOrdenes responseDao,
			ConsultarOrdenesViewRequest viewRequest) {

		ConsultaOrdenesDTO consultaOperacionesResponse = new ConsultaOrdenesDTO();

		for (CuentaTituloView cuentaTitulo : viewRequest.getCuentasTitulo()) {
			OrdenesPorCuenta ordenesPorCuenta = new OrdenesPorCuenta();
			ordenesPorCuenta.setIntervinientes(cuentaTitulo.getIntervinientes());
			ordenesPorCuenta.setCuentaTitulo(cuentaTitulo.getNroCuenta());
			ordenesPorCuenta.setCuentaOperativaCSuc(cuentaTitulo.getCuentaOperativaCSuc());

			// primer filtro.
			if ((viewRequest.getEspecie() != null)) {
				ColeccionConsultaOrdenes coleccionConsultaOrdenes = new ColeccionConsultaOrdenes();
				coleccionConsultaOrdenes.setListaConsultaOrdenes(filtrarOrdenesPorEspecie(responseDao, viewRequest));
				responseDao.setDatos(coleccionConsultaOrdenes);
			}
			// segundo filtro.
			if ((viewRequest.getCantidadNominalDesde() != null) && viewRequest.getCantidadNominalHasta() != null) {
				ColeccionConsultaOrdenes coleccionConsultaOrdenes = new ColeccionConsultaOrdenes();
				coleccionConsultaOrdenes.setListaConsultaOrdenes(filtrarOrdenesPorNominales(responseDao, viewRequest));
				responseDao.setDatos(coleccionConsultaOrdenes);
			}

			for (DatosConsultaOrdenesResponse datosConsultaOrdenesResponse : responseDao.getDatos()
					.getListaConsultaOrdenes()) {
				cuentaTitulo.setNroCuenta(
						StringUtils.leftPad(cuentaTitulo.getNroCuenta().replace("/", ""), LARGO_NUMERO_CUENTA, CERO));

				if (cuentaTitulo.getNroCuenta().equals(
						ISBANStringUtils.formateadorConCerosIzq(datosConsultaOrdenesResponse.getCuentaTitulos(), 8))) {
					TipoOrdenDTO orden = entityOrdenesToDTO(datosConsultaOrdenesResponse);
					ordenesPorCuenta.getOrdenes().add(orden);
				}
			}
			Collections.sort(ordenesPorCuenta.getOrdenes());
			consultaOperacionesResponse.getCuentas().add(ordenesPorCuenta);
			String mensajeSinOrdenes = mensajeBO.obtenerMensajePorCodigo(SIN_ORDENES).getMensaje();
			consultaOperacionesResponse.setMensajeSinOrdenes(mensajeSinOrdenes);
		}
		return consultaOperacionesResponse;

	}

	/**
	 * Entity ordenes to DTO.
	 *
	 * @param datosConsultaOrdenesResponse the datos consulta ordenes response
	 * @return the tipo orden DTO
	 */
	private TipoOrdenDTO entityOrdenesToDTO(DatosConsultaOrdenesResponse datosConsultaOrdenesResponse) {
		TipoOrdenDTO tipoOrdenDTO = new TipoOrdenDTO();
		String divisa = null;
		String monedaVista = "Dólares";
		tipoOrdenDTO.setCantidadNominal(
				ISBANStringUtils.formatearConComaYDosDecimales(datosConsultaOrdenesResponse.getCantidadInicial()));

		tipoOrdenDTO.setEspecie(datosConsultaOrdenesResponse.getEspecieDescripcion().trim());
		tipoOrdenDTO.setEstado(datosConsultaOrdenesResponse.getEstado());

		tipoOrdenDTO.setFecha(datosConsultaOrdenesResponse.getFechaIngreso());
		tipoOrdenDTO.setFechaBuscar(datosConsultaOrdenesResponse.getFechaIngreso().substring(8, 10) + "/"
				+ datosConsultaOrdenesResponse.getFechaIngreso().substring(5, 7) + "/"
				+ datosConsultaOrdenesResponse.getFechaIngreso().substring(0, 4));
		tipoOrdenDTO.setHora(datosConsultaOrdenesResponse.getHora());

		tipoOrdenDTO.setNumeroOrden(datosConsultaOrdenesResponse.getNumeroOrden());

		int plazoInt = Integer.parseInt(datosConsultaOrdenesResponse.getPlazo());
		String plazo = String.valueOf(plazoInt);
		if (CERO.equals(plazo)) {
			tipoOrdenDTO.setPlazo("Inmediato");
		} else {
			tipoOrdenDTO.setPlazo(datosConsultaOrdenesResponse.getPlazo() + " hs");
		}
		if ("ARS".equals(datosConsultaOrdenesResponse.getCodigoMonedaIso())) {
			divisa = "$";
			monedaVista = "Pesos";
		} else {
			divisa = "u$s";
		}

		if (datosConsultaOrdenesResponse.getPrecioLimite() == null) {
			tipoOrdenDTO.setPrecioLimite("-");
		} else {
			// minimo 2, maximo 7 decimales
			String[] partes = String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite()).split("\\.");
			String decimal = partes[1];
			if (decimal.length() <= 1) {
				tipoOrdenDTO.setPrecioLimite(divisa + " " + ISBANStringUtils.formatearConComaYDosDecimalesBis(
						String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite())));
			}
			if (decimal.length() >= 2 && decimal.length() < 8) {
				if (!ISBANStringUtils.esTodoCero(decimal, new Vector<String>())) {
					tipoOrdenDTO.setPrecioLimite(divisa + " " + ISBANStringUtils.formatearConComaYVariosDecimalesBis(
							String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite()), decimal.length()));
				} else {
					tipoOrdenDTO.setPrecioLimite(divisa + " " + ISBANStringUtils.formatearConComaYDosDecimalesBis(
							String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite())));
				}
			}
			if (decimal.length() >= 8) {
				if (!ISBANStringUtils.esTodoCero(decimal, new Vector<String>())) {
					tipoOrdenDTO.setPrecioLimite(divisa + " " + ISBANStringUtils.formatearConComaYVariosDecimalesBis(
							String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite()), 7));
				} else {
					tipoOrdenDTO.setPrecioLimite(divisa + " " + ISBANStringUtils.formatearConComaYDosDecimalesBis(
							String.valueOf(datosConsultaOrdenesResponse.getPrecioLimite())));
				}
			}
		}

		tipoOrdenDTO.setTipoDeOrden(datosConsultaOrdenesResponse.getTipoOperacion());
		tipoOrdenDTO.setSemaforo(datosConsultaOrdenesResponse.getInstanciaorden());

		tipoOrdenDTO.setMotivoRechazo(datosConsultaOrdenesResponse.getMotivoReversa());
		tipoOrdenDTO.setMoneda(monedaVista);
		tipoOrdenDTO.setPrecioConcertacion(divisa + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(datosConsultaOrdenesResponse.getPrecioCierre()));
		tipoOrdenDTO.setPrecioReferencia(divisa + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(datosConsultaOrdenesResponse.getPrecioReferencia()));
		tipoOrdenDTO.setImporteADebitar(divisa + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(datosConsultaOrdenesResponse.getDebitoAnticipado()));
		tipoOrdenDTO.setEspecieTipo(datosConsultaOrdenesResponse.getEspecieTipo());
		tipoOrdenDTO.setRic(datosConsultaOrdenesResponse.getRic());
		tipoOrdenDTO.setInstrumentoCodigo(datosConsultaOrdenesResponse.getInstrumentoCodigo());
		tipoOrdenDTO.setCantidadReversada(datosConsultaOrdenesResponse.getCantidadReversada());

		String numeroCuenta = datosConsultaOrdenesResponse.getCuentaTitulos();
		numeroCuenta = numeroCuenta.substring(0, numeroCuenta.length() - 1) + SEPARATOR_BARRA_LATERAL
				+ numeroCuenta.substring(numeroCuenta.length() - 1);
		tipoOrdenDTO.setCuentaTitulos(numeroCuenta);

		tipoOrdenDTO.setCuentaDebito(datosConsultaOrdenesResponse.getSucursal() + "-"
				+ ISBANStringUtils.formatearNumeroCuenta(datosConsultaOrdenesResponse.getCuentaNumero()));

		Cliente cliente = sesionCliente.getCliente();

		String cuentaPorNumero = ISBANStringUtils.formateadorConCerosIzq(datosConsultaOrdenesResponse.getCuentaNumero(),
				16);
		if (cliente.getCuentaPorNumero(cuentaPorNumero) != null) {
			String descripcionCuenta = cliente
					.getCuentaPorNumero(Integer.parseInt(datosConsultaOrdenesResponse.getCuentaNumero()))
					.getTipoCuentaEnum().getDescripcionConMoneda();
			if (TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda().equals(descripcionCuenta)) {
				if ("u$s".equals(divisa)) {
					descripcionCuenta = TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda();
				}
			}
			tipoOrdenDTO.setDescripcionTipoCuenta(descripcionCuenta);
		}

		tipoOrdenDTO.setCanal(obtenerCanalIngreso(datosConsultaOrdenesResponse.getCanal()));

		return tipoOrdenDTO;
	}

	/**
	 * Obtener canal ingreso.
	 *
	 * @param canalIngreso the canal ingreso
	 * @return the string
	 */
	private String obtenerCanalIngreso(String canalIngreso) {
		return CanalIngresoEnum.obtenerDescripcion(canalIngreso);
	}

	/**
	 * Generar request para consultar el dao de consultaOrdenes
	 * 
	 * @param viewRequest
	 * @param cliente
	 * @param banca
	 * @return
	 * @throws BusinessException
	 */

	ConsultaOrdenesRequestEntity generarRequestConsultaOrdenes(ConsultarOrdenesViewRequest viewRequest, Cliente cliente,
			TipoBancaEnum banca) throws BusinessException {
		ConsultaOrdenesRequestEntity request = new ConsultaOrdenesRequestEntity();
		DatosConsultaTitulosOrdenes datos = new DatosConsultaTitulosOrdenes();
		datos.setCuentaTitulos(null);
		inicializarFechas(viewRequest, datos);
		request.setFirma(generarFirma(datoTitulos));
		request.setDato(datoTitulos);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));
		datos.setCabecera(CabeceraOrdenesTitulosEntity.generarCabeceraRequest(cliente));
		if (TipoBancaEnum.BANCA_PERSONAL.equals(banca)) {
			datos.setCanal(canalOrdenesRTL);
			datos.setSubCanal(subCanalOrdenesRTL);
			request.setCanal(canalOrdenesRTL);
			request.setSubCanal(subCanalOrdenesRTL);
			datos.getCabecera().setCanalTipo(canalOrdenesRTL);
			datos.getCabecera().setSubCanalTipo(subCanalTipoCabeceraOrdenesRTL);
		} else {
			datos.setCanal(canalOrdenesBPriv);
			datos.setSubCanal(subCanalOrdenesBPriv);
			request.setCanal(canalOrdenesBPriv);
			request.setSubCanal(subCanalOrdenesBPriv);
			datos.getCabecera().setCanalTipo(canalOrdenesBPriv);
			datos.getCabecera().setSubCanalTipo(subCanalTipoCabeceraOrdenesBPriv);
		}
		datos.setNup(cliente.getNup());
		request.setDatos(datos);
		return request;
	}

	/***
	 * Inicializa fechaDesde y fechaHasta por defecto: fechaDesde= (fecha actual -
	 * 30 dias) (fechaHasta=fecha actual), Sino fechas correspondientes recibidas
	 * formato entrada: ddMMyyyy formato salida: aaaa-mm-dd:00:00-03:00"
	 * 
	 * @param viewRequest
	 * @param datos
	 */
	private void inicializarFechas(ConsultarOrdenesViewRequest viewRequest, DatosConsultaTitulosOrdenes datos) {
		if (viewRequest.getFechaDesde() == null && viewRequest.getFechaHasta() == null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = Calendar.getInstance().getTime();
			String fechaHasta = df.format(today);
			String fechaDesde = df.format(FechaUtils.obtenerFechaDesde(30));
			datos.setFechaDesde(fechaDesde + horaIsoFecha);
			datos.setFechaHasta(fechaHasta + horaIsoFecha);
		} else {

			String fechaIni = viewRequest.getFechaDesde();
			String diaIni = fechaIni.substring(0, 2);
			String mesIni = fechaIni.substring(2, 4);
			String anioIni = fechaIni.substring(4, 8);
			String fechaDesde = anioIni + "-" + mesIni + "-" + diaIni + horaIsoFecha;

			String fechaHast = viewRequest.getFechaHasta();
			String diaHasta = fechaHast.substring(0, 2);
			String mesHasta = fechaHast.substring(2, 4);
			String anioHasta = fechaHast.substring(4, 8);
			String fechaHasta = anioHasta + "-" + mesHasta + "-" + diaHasta + horaIsoFecha;
			datos.setFechaDesde(fechaDesde);
			datos.setFechaHasta(fechaHasta);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#obtenerTenenciaTitulosBP(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutDTO> obtenerTenenciaTitulosBP(Cliente cliente) {

		if (cliente.getCuentasBancaPrivada().isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIN_CUENTA_TITULOS,
					CodigoMensajeConstantes.ERROR_SIN_CUENTA_TITULOS_TITULOS_VALORES);
		}
		List<CuentaTituloDTO> cuentasDTO = generarCuentasBPTenencias(cliente.getCuentasBancaPrivada());

		DetalleTenenciaValuadaTitulosEntity tenenciaTitulos = new DetalleTenenciaValuadaTitulosEntity();
		try {
			tenenciaTitulos = tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleTitulosOnline(
					createRequestServiceTenencias(cuentasDTO, cliente, Segmento.BANCA_PRIVADA.getCodigo()));
		} catch (DAOException e) {
			LOGGER.info("Error obteniendo la tenencia valuada para titulos ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		// Carga de grilla de tenencias
		List<ListaTenenciaCuentaTitMoneda> listaTenenciaCuentaTitMoneda = tenenciaTitulos.getDatos().getResultado();
		TenenciaTitulosMapDTO mapTenencias = new TenenciaTitulosMapDTO(cuentasDTO, BANCA_PRIVADA);
		mapTenencias.cargarTenencias(listaTenenciaCuentaTitMoneda);
		TenenciaTitulosOutDTO respBO = mapTenencias.createResponse();
		procesarNrosCuenta(cuentasDTO, respBO);

		if (tenenciaTitulos.getCodigo() == 1) {
			List<ItemMensajeRespuesta> list = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.GRILLA_INVERSIONES_PARCIAL.getDescripcion());
			list.add(itemMensajeRespuesta);
			return respuestaFactory.crearRespuestaWarning(respBO, list);
		}

		// Carga de graficos
		Map<String, List<ListaTenenciaCuentaTitMoneda>> mapaTenenciasPorCuenta = armarGraficosListaTenenciasBP(
				tenenciaTitulos.getDatos().getResultado());
		TenenciaTitulosOutDTO tenenciaConGraficos = obtenerGraficosTenenciasBP(respBO, mapaTenenciasPorCuenta);
		return respuestaFactory.crearRespuestaOk(TenenciaTitulosOutDTO.class, tenenciaConGraficos);
	}

	/**
	 * Obtener graficos tenencias BP.
	 *
	 * @param respBO                 the resp BO
	 * @param mapaTenenciasPorCuenta the mapa tenencias por cuenta
	 * @return the tenencia titulos out DTO
	 */
	private TenenciaTitulosOutDTO obtenerGraficosTenenciasBP(TenenciaTitulosOutDTO respBO,
			Map<String, List<ListaTenenciaCuentaTitMoneda>> mapaTenenciasPorCuenta) {
		List<TenenciaTitulosCuentaDTO> listaTenencias = respBO.getList();
		TenenciaTitulosOutDTO tenenciaTitulosOutDTO = new TenenciaTitulosOutDTO();
		List<TenenciaTitulosCuentaDTO> listaConGraficos = new ArrayList<TenenciaTitulosCuentaDTO>();
		for (TenenciaTitulosCuentaDTO tenenciaTitulosCuentaDTO : listaTenencias) {
			List<ListaTenenciaCuentaTitMoneda> listaTenenciaCuentaTitMoneda = mapaTenenciasPorCuenta
					.get(tenenciaTitulosCuentaDTO.getNumeroCuenta().replace("/", ""));
			if (CollectionUtils.isNotEmpty(listaTenenciaCuentaTitMoneda)) {
				TenenciaTitulosGraficosMapDTO mapGraficos = new TenenciaTitulosGraficosMapDTO();
				mapGraficos.cargarGraficos(listaTenenciaCuentaTitMoneda);
				TenenciasGraficos tenenciasGraficos = mapGraficos.createResponse();
				tenenciaTitulosCuentaDTO.setListaPorcentaje(tenenciasGraficos.getTenenciaProductos());
				tenenciaTitulosCuentaDTO.setTotalArs(tenenciasGraficos.getTotalArs());
				tenenciaTitulosCuentaDTO.setTotalUsd(tenenciasGraficos.getTotalUsd());
				tenenciaTitulosCuentaDTO.setGraficosEnCero(tenenciasGraficos.isValorNull());
				tenenciaTitulosCuentaDTO.setWarningTotales(tenenciasGraficos.isValorNull());
			} else {
				tenenciaTitulosCuentaDTO.setGraficosEnCero(true);
				tenenciaTitulosCuentaDTO.setWarningTotales(false);
			}
			listaConGraficos.add(tenenciaTitulosCuentaDTO);
		}
		tenenciaTitulosOutDTO.setList(listaConGraficos);
		return tenenciaTitulosOutDTO;
	}

	/**
	 * Armar graficos lista tenencias BP.
	 *
	 * @param list the list
	 * @return the map
	 */
	private Map<String, List<ListaTenenciaCuentaTitMoneda>> armarGraficosListaTenenciasBP(
			List<ListaTenenciaCuentaTitMoneda> list) {
		Map<String, List<ListaTenenciaCuentaTitMoneda>> mapaListaPorCuenta = new HashMap<String, List<ListaTenenciaCuentaTitMoneda>>();
		for (ListaTenenciaCuentaTitMoneda listaTenenciaCuentaTitMoneda : list) {
			List<ListaTenenciaCuentaTitMoneda> listaTenenciasPorCuenta = mapaListaPorCuenta
					.get(listaTenenciaCuentaTitMoneda.getNumeroCuenta());
			if (CollectionUtils.isEmpty(listaTenenciasPorCuenta)) {
				listaTenenciasPorCuenta = new ArrayList<ListaTenenciaCuentaTitMoneda>();
			}
			listaTenenciasPorCuenta.add(listaTenenciaCuentaTitMoneda);
			mapaListaPorCuenta.put(listaTenenciaCuentaTitMoneda.getNumeroCuenta(), listaTenenciasPorCuenta);
		}
		return mapaListaPorCuenta;
	}

	/**
	 * Armo la lista de objetos CuentaTituloDTO para BP, el nro de cuenta lo
	 * formateo con el 700 delante porque de esa forma viene el resultado del
	 * servicio.
	 * 
	 * @param cuentas the cuentas
	 * @return the list
	 */
	private List<CuentaTituloDTO> generarCuentasBPTenencias(List<CuentaBancaPrivada> cuentas) {
		List<CuentaTituloDTO> listCuentasDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaBancaPrivada cuenta : cuentas) {
			CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
			// aca debo completar con 700
			cuentaTitDTO.setCuentaOperativaSinFormatear(PREFIJO_CUENTA_TITULO
					+ llenarConCerosIzqOTruncar(cuenta.getCuentaOperativa().getNroCuentaProducto(), LONGITUD_CUENTA));
			cuentaTitDTO.setNroCuenta(
					ISBANStringUtils.formatearNumeroCuenta(cuenta.getCuentaTitulo().getNroCuentaProducto()));
			cuentaTitDTO.setNroCuentaFormateado(
					ISBANStringUtils.formatearNumeroCuenta(cuenta.getCuentaTitulo().getNroCuentaProducto()));
			cuentaTitDTO.setSucursal(cuenta.getCuentaOperativa().getNroSucursal());
			cuentaTitDTO.setIntervinientes(cuenta.getCuentaOperativa().getIntervinientes());
			listCuentasDTO.add(cuentaTitDTO);
		}
		return listCuentasDTO;
	}

	/**
	 * procesa el nro de cuenta, para que matchee con las cuentas pasadas en el
	 * inicio de esta forma se muestra en front.
	 * 
	 * @param cuentasDTO the cuentas DTO
	 * @param respBO     the resp BO
	 */
	private void procesarNrosCuenta(List<CuentaTituloDTO> cuentasDTO, TenenciaTitulosOutDTO respBO) {
		List<TenenciaTitulosCuentaDTO> lista = respBO.getList();
		for (TenenciaTitulosCuentaDTO tenencia : lista) {
			for (CuentaTituloDTO cuentaDTO : cuentasDTO) {
				if (cuentaDTO.getCuentaOperativaSinFormatear().equals(tenencia.getNumeroCuenta().replace("/", ""))) {
					// String numeroCuentaSinCortar =
					// tenencia.getNumeroCuenta().replace("/", "");
					tenencia.setNumeroCuentaOperativa(
							ISBANStringUtils.formatearNumeroCuenta(cuentaDTO.getCuentaOperativaSinFormatear()
									.substring(1, cuentaDTO.getCuentaOperativaSinFormatear().length())));
					// tenencia.setNumeroCuentaOperativa(cuentaDTO.getCuentaOperativaSinFormatear().substring(1,
					// cuentaDTO.getCuentaOperativaSinFormatear().length()));
					tenencia.setNumeroCuenta(cuentaDTO.getCuentaOperativaSinFormatear());
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#busquedaOrdenesCompra(ar.com.santanderrio.obp.servicios.inversiones
	 * .titulos.licitaciones.view.BusquedaOrdenesCompra,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<DatosConsultaEspeciesResponse> busquedaOrdenesCompra(BusquedaOrdenesCompra request,
			Cliente cliente, TipoBancaEnum tipoBancaEnum) {
		ConsultarEspeciesResponse respuestaDAO = new ConsultarEspeciesResponse();
		try {
			respuestaDAO = ordenesTitulosDAO.consultaEspecies(
					crearRequestConsultarEspecies(cliente, request.getDescripcionEspecie(), "C", tipoBancaEnum));
			return respuestaFactory.crearRespuestaOk(DatosConsultaEspeciesResponse.class, respuestaDAO.getDatos());
		} catch (DAOException e) {
			LOGGER.error("Ocurrio un error al consultar el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		}
	}

	/**
	 * Crear request consultar especies.
	 *
	 * @param cliente          the cliente
	 * @param criterioBusqueda the criterio busqueda
	 * @param tipoOperacion    the tipo operacion
	 * @param tipoBancaEnum    the tipo banca enum
	 * @return the consultar especies request
	 */
	private ConsultarEspeciesRequest crearRequestConsultarEspecies(Cliente cliente, String criterioBusqueda,
			String tipoOperacion, TipoBancaEnum tipoBancaEnum) {
		ConsultarEspeciesRequest request = new ConsultarEspeciesRequest();
		Boolean esBancaPrivada = TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum);
		setearCanalesRequestMWCanales(request, esBancaPrivada);
		setearDatosRequestMWCanales(request.getDatos(), cliente, esBancaPrivada);
		request.getDatos().setSegmento(esBancaPrivada ? "BP" : "RTL");
		request.getDatos().setDescripcionEspecie(criterioBusqueda);
		DateTimeFormatter sdf = ISODateTimeFormat.dateTimeNoMillis();
		request.getDatos().setFecha(sdf.print(new DateTime()));
		request.getDatos().setTipoOperacion(tipoOperacion);
		request.getDatos().setTipoDeNegociacion(CONSULTA_ORDEN_GARANTIZADA);
		request.getDatos().setEspecieNegociable("S");
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#configuracionOrdenesCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.List,
	 * java.util.List,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(
			ConfiguracionOrdenesCompraRequest request, Cliente cliente, List<CuentasAdhesionDebitoView> cuentasPesos,
			List<CuentasAdhesionDebitoView> cuentasDolares, TipoBancaEnum tipoBancaEnum) {

		ConfiguracionOrdenesCompraResponse response = new ConfiguracionOrdenesCompraResponse();

		try {
			ConsultaAperturaEspecieResponse resp = ordenesTitulosDAO
					.consultaAperturaEspecie(creatRequestAperturaEspecie(request, cliente, tipoBancaEnum));

			response.setAperturaEspecie(toDtoConfiguracionOrdenCompra(resp.getDatos().getListaAperturaEspecie(),
					cuentasPesos, cuentasDolares));
		} catch (DAOException e) {
			LOGGER.error("Ocurrio un error al consultar el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESOLUCION_30_2017,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		} catch (BusinessException e) {
			LOGGER.error("Ocurrio un error al consultar el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		response.setCuentasDebitoPesos(cuentasPesos);
		response.setCuentasDebitoDolares(cuentasDolares);

		mensajesYlegalesConfiguracionOrdenCompra(response, tipoBancaEnum);
		if (response.getLegalesResolucion() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		response = obtenerPoderCompra(response, request, cliente, tipoBancaEnum);
		return respuestaFactory.crearRespuestaOk(ConfiguracionOrdenesCompraResponse.class, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#configuracionOrdenesCompraBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraBPriv(
			ConfiguracionOrdenesCompraRequest request, Cliente cliente, CuentasAdhesionDebitoView cuentasOperativa,
			TipoBancaEnum tipoBancaEnum) {

		ConfiguracionOrdenesCompraResponse response = new ConfiguracionOrdenesCompraResponse();

		try {
			ConsultaAperturaEspecieResponse resp = ordenesTitulosDAO
					.consultaAperturaEspecie(creatRequestAperturaEspecie(request, cliente, tipoBancaEnum));

			// Respuesta<ConsultaAperturaEspecieResponse> rtaValidacion =
			// super.validate(resp,
			// new ConsultaAperturaEspecieResponse());
			// if
			// (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK))
			// {
			// return respuestaFactory.crearRespuestaError("",
			// TipoError.ERROR_VALIDACION_DATOS,
			// CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			// }

			response.setAperturaEspecie(
					toDtoConfiguracionOrdenCompraBPriv(resp.getDatos().getListaAperturaEspecie(), cuentasOperativa));
		} catch (DAOException e) {
			LOGGER.error("Ocurrio un error al consultar el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		}
		List<CuentasAdhesionDebitoView> listaCuentaOperativa = new ArrayList<CuentasAdhesionDebitoView>();
		listaCuentaOperativa.add(cuentasOperativa);
		response.setCuentaOperativa(listaCuentaOperativa);
		mensajesYlegalesConfiguracionOrdenCompra(response, tipoBancaEnum);
		response = obtenerPoderCompra(response, request, cliente, tipoBancaEnum);

		return respuestaFactory.crearRespuestaOk(ConfiguracionOrdenesCompraResponse.class, response);

	}

	/**
	 * Mensajes ylegales configuracion orden compra.
	 *
	 * @param response  the response
	 * @param tipoBanca the tipo banca
	 */
	private void mensajesYlegalesConfiguracionOrdenCompra(ConfiguracionOrdenesCompraResponse response,
			TipoBancaEnum tipoBanca) {
		Respuesta<String> resolucion;
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			resolucion = legalBO.buscarLegal(CodigoMensajeConstantes.ORDEN_COMPRA_VENTA_TITULOS_LEGAL_ORIGEN_FONDOS);
		} else {
			resolucion = legalBO.buscarLegal(CodigoMensajeConstantes.ORDENCOMPRABPRIV_RESOLUCION_ORIGEN_FONDOS);
		}

		if (EstadoRespuesta.OK.equals(resolucion.getEstadoRespuesta())) {
			response.setLegalesResolucion(resolucion.getRespuesta());
		} else {
			response.setLegalesResolucion(null);
		}
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRECIO_REFERENCIA_ORDEN_COMPRA)
				.getMensaje();
		response.setPrecioReferencia(mensaje);

	}

	/**
	 * metodo para obtener el poder de compra de las cuentas titulo.
	 *
	 * @param response      the response
	 * @param request       the request
	 * @param cliente       the cliente
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the configuracion ordenes compra response
	 */
	@Override
	public ConfiguracionOrdenesCompraResponse obtenerPoderCompra(ConfiguracionOrdenesCompraResponse response,
			ConfiguracionOrdenesCompraRequest request, Cliente cliente, TipoBancaEnum tipoBancaEnum) {
		ConsultaSuscripcionSaldoPDCResponse responsePDC = new ConsultaSuscripcionSaldoPDCResponse();
		try {
			responsePDC = ordenesTitulosDAO
					.consultaSuscripcionSaldoPDC(createRequestPDC(request, response, cliente, tipoBancaEnum));
		} catch (DAOException e) {
			LOGGER.error("Ocurrio un error al consultar el servicio de  PDC", e);
			return response;
		}
		response.setPoderCompraOK(true);
		for (CuentasPDC cuenta : responsePDC.getDatos().getListaCuentas()) {
			if ("ARS".equals(cuenta.getMoneda())) {
				response.setIndicadorPDCPesos(cuenta.getIndicadorPDC());
				if (!"NN".equals(cuenta.getIndicadorPDC())) {
					response.setPoderCompraPesos(true);
				}
				if ("AD".equals(cuenta.getIndicadorPDC())) {
					CuentasAdhesionDebitoView cuentaOperativa = getCuentaOperativaConPDC(
							response.getCuentasDebitoPesos(), cuenta, response);
					if (cuentaOperativa != null) {
						response.getCuentasDebitoPesos().add(0, cuentaOperativa);
					} else {
						response.setPoderCompraPesos(false);
					}
				}

			} else {
				response.setIndicadorPDCDolares(cuenta.getIndicadorPDC());
				if (!"NN".equals(cuenta.getIndicadorPDC())) {
					response.setPoderCompraDolares(true);
				}
				if ("AD".equals(cuenta.getIndicadorPDC())) {
					CuentasAdhesionDebitoView cuentaOperativa = getCuentaOperativaConPDC(
							response.getCuentasDebitoDolares(), cuenta, response);
					if (cuentaOperativa != null) {
						response.getCuentasDebitoDolares().add(0, cuentaOperativa);
					} else {
						response.setPoderCompraDolares(false);
					}

				}
			}
		}
		return response;
	}

	/**
	 * Creates the request PDC.
	 *
	 * @param request       the request
	 * @param response      the response
	 * @param cliente       the cliente
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the consulta suscripcion saldo PDC request
	 */
	ConsultaSuscripcionSaldoPDCRequest createRequestPDC(ConfiguracionOrdenesCompraRequest request,
			ConfiguracionOrdenesCompraResponse response, Cliente cliente, TipoBancaEnum tipoBancaEnum) {
		ConsultaSuscripcionSaldoPDCRequest requestPDC = new ConsultaSuscripcionSaldoPDCRequest();
		Boolean esBancaPrivada = TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum);
		setearCanalesRequestMWCanales(requestPDC, esBancaPrivada);
		setearDatosRequestMWCanales(requestPDC.getDatos(), cliente, esBancaPrivada);
		requestPDC.getDatos().setSegmento(esBancaPrivada ? "BP" : "RTL");
		requestPDC.getDatos().setFechaLiquidacion(obtenerFechaPlazoMaximo(response.getAperturaEspecie().getPlazos()));
		requestPDC.getDatos().setCuentaTitulos(request.getCuenta().replaceAll("/", ""));
		requestPDC.getDatos().setProductoInversion(request.getTipoEspecie());
		requestPDC.getDatos().setProductoInversion(response.getAperturaEspecie().getTipoEspecie());
		return requestPDC;
	}

	/**
	 * Metodo que retorna la fecha de liquidacion correspondiente al plazo maximo de
	 * la especie seleccionada.
	 * 
	 * @param list the list
	 * @return String fecha
	 */
	private String obtenerFechaPlazoMaximo(List<AperturaEspecie> list) {
		Integer plazoMaximo = -1;
		String fecha = "";
		for (AperturaEspecie especie : list) {
			for (DatosMonedaEspecie datos : especie.getListaMonedas()) {
				if (especie.getPlazo() > plazoMaximo) {
					fecha = datos.getFechaSinFormato();
				}
			}
		}
		return fecha;
	}

	/**
	 * Creat request apertura especie.
	 *
	 * @param datos         the datos
	 * @param cliente       the cliente
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the consulta apertura especie request entity
	 */
	private ConsultaAperturaEspecieRequestEntity creatRequestAperturaEspecie(ConfiguracionOrdenesCompraRequest datos,
			Cliente cliente, TipoBancaEnum tipoBancaEnum) {
		// Seteo el request con datos de banca personal.
		ConsultaAperturaEspecieRequestEntity request = new ConsultaAperturaEspecieRequestEntity();
		Boolean esBancaPrivada = TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum);
		setearCanalesRequestMWCanales(request, esBancaPrivada);
		setearDatosRequestMWCanales(request.getDatos(), cliente, esBancaPrivada);
		request.getDatos().setSegmento(esBancaPrivada ? "BP" : "RTL");
		DateTimeFormatter sdf = ISODateTimeFormat.dateTimeNoMillis();
		request.getDatos().setFecha(sdf.print(new DateTime()));
		request.getDatos().setCodigoEspecieRossi(datos.getCodigoEspecie());
		request.getDatos().setTipoOperacion(COMPRA_ORDENES);
		return request;

	}

	/**
	 * To dto configuracion orden compra.
	 *
	 * @param list           the list
	 * @param cuentasPesos   the cuentas pesos
	 * @param cuentasDolares the cuentas dolares
	 * @return the lista apertura especie
	 * @throws BusinessException the business exception
	 * @throws DAOException 
	 */
	private ListaAperturaEspecie toDtoConfiguracionOrdenCompra(ListaAperturaEspecieResponse list,
			List<CuentasAdhesionDebitoView> cuentasPesos, List<CuentasAdhesionDebitoView> cuentasDolares)
			throws BusinessException, DAOException {

		ListaAperturaEspecie aperturaEspecie = new ListaAperturaEspecie();
		aperturaEspecie.setDescripcionEspecie(StringUtils.trim(list.getDescripcionEspecie()));
		aperturaEspecie.setCodigoAmigable(StringUtils.trim(list.getCodigoAmigable()));
		aperturaEspecie.setInstrumento(list.getInstrumento());
		aperturaEspecie.setEmisionMonedaEspecie(obtenerMonedaEspecie(list));
		aperturaEspecie.setTipoEspecie(list.getTipoEspecie());
		aperturaEspecie.setLeyendaLegal(list.getLeyendaLegal());
		
		aperturaEspecie.setLegalCNV(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CNV_TITULOS_VALORES));

		LinkedHashMap<String, ArrayList<DatosMonedaEspecie>> map = new LinkedHashMap<String, ArrayList<DatosMonedaEspecie>>();
		for (AperturaEspecieResponse datoEspecie : list.getAperturasEspecie()) {

			DatosMonedaEspecie datosMonedaEspecie = new DatosMonedaEspecie();

			datosMonedaEspecie.setFechaSinFormato(datoEspecie.getFechaLiquidacion());
			datosMonedaEspecie.setFechaLiquidacion(datoEspecie.getFechaLiquidacion() != null
					? convertirFechaConfirmarOrden(datoEspecie.getFechaLiquidacion())
					: datoEspecie.getFechaLiquidacion());
			datosMonedaEspecie.setMonedaNegociacion(obtenerMonedaNegociacion(datoEspecie));
			datosMonedaEspecie.setCodHabilitacion(datoEspecie.getCodHabilitacion());
			datosMonedaEspecie.setFechaCotizacionEspecie(datoEspecie.getFechaCotizacionEspecie() != null
					? convertirFechaConfirmarOrden(datoEspecie.getFechaCotizacionEspecie())
					: datoEspecie.getFechaCotizacionEspecie());
			datosMonedaEspecie.setCotizacionEspecie(datoEspecie.getCotizacionEspecie());
			datosMonedaEspecie.setHoraCotizacionEspecie(datoEspecie.getHoraCotizacionEspecie());
			datosMonedaEspecie.setCantidadMinimoIncremento(datoEspecie.getCantidadMinimoIncremento());
			datosMonedaEspecie.setCantidadMinimaNegociable(datoEspecie.getCantidadMinimaNegociable());
			datosMonedaEspecie.setCantidadMaximaNegociable(datoEspecie.getCantidadMaximaNegociable());
			datosMonedaEspecie.setImporteMinimoIncremento(datoEspecie.getImporteMinimoIncremento());
			datosMonedaEspecie.setImporteMinimoInvertir(datoEspecie.getImporteMinimoInvertir());
			datosMonedaEspecie.setImporteMaximoInvertir(datoEspecie.getImporteMaximoInvertir());
			datosMonedaEspecie.setRequierePrecioLimite(datoEspecie.getRequierePrecioLimite());
			datosMonedaEspecie.setPrecioLimiteIncremento(datoEspecie.getPrecioLimiteIncremento());
			datosMonedaEspecie.setPrecioLimiteMinimo(datoEspecie.getPrecioLimiteMinimo());
			datosMonedaEspecie.setPrecioLimiteMaximo(datoEspecie.getPrecioLimiteMaximo());
			datosMonedaEspecie.setPermiteMonto(datoEspecie.getPermiteMonto());
			datosMonedaEspecie.setTenenciaNominalNegociable(datoEspecie.getTenenciaNominalNegociable());
			datosMonedaEspecie.setTenenciaNominal(datoEspecie.getTenenciaNominal());

			if (map.get(String.valueOf(datoEspecie.getPlazo())) == null) {
				if (especieHabilitada.equalsIgnoreCase(datosMonedaEspecie.getCodHabilitacion())) {
					if (MONEDA_USD.equalsIgnoreCase(datosMonedaEspecie.getMonedaNegociacion())) {
						if (!cuentasDolares.isEmpty()) {
							map.put(String.valueOf(datoEspecie.getPlazo()), new ArrayList<DatosMonedaEspecie>());
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspecieDolares(true);
						}
					} else {
						if (!cuentasPesos.isEmpty()) {
							map.put(String.valueOf(datoEspecie.getPlazo()), new ArrayList<DatosMonedaEspecie>());
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspeciePesos(true);
						}
					}
				}
			} else {
				if (especieHabilitada.equalsIgnoreCase(datosMonedaEspecie.getCodHabilitacion())) {
					if (MONEDA_USD.equalsIgnoreCase(datosMonedaEspecie.getMonedaNegociacion())) {
						if (!cuentasDolares.isEmpty()) {
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspecieDolares(true);
						}
					} else {
						if (!cuentasPesos.isEmpty()) {
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspeciePesos(true);
						}
					}
				}
			}

			if (especieFueraHorario.equalsIgnoreCase(list.getAperturasEspecie().get(0).getCodHabilitacion())) {
				aperturaEspecie.setFueraHorario(true);
			}

		}
		if (!map.isEmpty()) {
			for (Entry<String, ArrayList<DatosMonedaEspecie>> entry : map.entrySet()) {
				AperturaEspecie datosAperturaEspecie = new AperturaEspecie();
				datosAperturaEspecie.setPlazo(Integer.valueOf(entry.getKey()));
				for (DatosMonedaEspecie datos : entry.getValue()) {
					datosAperturaEspecie.getListaMonedas().add(datos);
				}
				aperturaEspecie.getPlazos().add(datosAperturaEspecie);
			}
		}
		return aperturaEspecie;
	}

	/**
	 * Obtener moneda negociacion.
	 *
	 * @param datoEspecie the dato especie
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	private String obtenerMonedaNegociacion(AperturaEspecieResponse datoEspecie) throws BusinessException {
		String monedaNegociacion = datoEspecie.getMonedaNegociacion();
		if (StringUtils.equals(monedaNegociacion, ARS) || StringUtils.equals(monedaNegociacion, USD)) {
			return monedaNegociacion;
		}
		throw new BusinessException();
	}

	/**
	 * Obtener moneda especie.
	 *
	 * @param list the list
	 * @return the string
	 */
	private String obtenerMonedaEspecie(ListaAperturaEspecieResponse list) {
		String descripcionMonedaEspecie = MonedaEspecieEnum.obtenerDescripcionPorCodigo(list.getEmisionMonedaEspecie());
		if (descripcionMonedaEspecie != null) {
			return descripcionMonedaEspecie;
		}
		return list.getEmisionMonedaEspecie();
	}

	/**
	 * To dto configuracion orden compra B priv.
	 *
	 * @param list            the list
	 * @param cuentaOperativa the cuenta operativa
	 * @return the lista apertura especie
	 * @throws BusinessException the business exception
	 * @throws DAOException 
	 */
	private ListaAperturaEspecie toDtoConfiguracionOrdenCompraBPriv(ListaAperturaEspecieResponse list,
			CuentasAdhesionDebitoView cuentaOperativa) throws BusinessException, DAOException {

		ListaAperturaEspecie aperturaEspecie = new ListaAperturaEspecie();
		aperturaEspecie.setDescripcionEspecie(StringUtils.trim(list.getDescripcionEspecie()));
		aperturaEspecie.setCodigoAmigable(StringUtils.trim(list.getCodigoAmigable()));
		aperturaEspecie.setInstrumento(list.getInstrumento());
		aperturaEspecie.setEmisionMonedaEspecie(obtenerMonedaEspecie(list));
		aperturaEspecie.setTipoEspecie(list.getTipoEspecie());
		aperturaEspecie.setLeyendaLegal(list.getLeyendaLegal());
		
		aperturaEspecie.setLegalCNV(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CNV_TITULOS_VALORES));

		LinkedHashMap<String, ArrayList<DatosMonedaEspecie>> map = new LinkedHashMap<String, ArrayList<DatosMonedaEspecie>>();

		for (AperturaEspecieResponse datoEspecie : list.getAperturasEspecie()) {

			DatosMonedaEspecie datosMonedaEspecie = new DatosMonedaEspecie();

			datosMonedaEspecie.setFechaSinFormato(datoEspecie.getFechaLiquidacion());
			datosMonedaEspecie.setFechaLiquidacion(datoEspecie.getFechaLiquidacion() != null
					? convertirFechaConfirmarOrden(datoEspecie.getFechaLiquidacion())
					: datoEspecie.getFechaLiquidacion());
			datosMonedaEspecie.setMonedaNegociacion(obtenerMonedaNegociacion(datoEspecie));
			datosMonedaEspecie.setCodHabilitacion(datoEspecie.getCodHabilitacion());
			datosMonedaEspecie.setFechaCotizacionEspecie(datoEspecie.getFechaCotizacionEspecie() != null
					? convertirFechaConfirmarOrden(datoEspecie.getFechaCotizacionEspecie())
					: datoEspecie.getFechaCotizacionEspecie());
			datosMonedaEspecie.setCotizacionEspecie(datoEspecie.getCotizacionEspecie());
			datosMonedaEspecie.setHoraCotizacionEspecie(datoEspecie.getHoraCotizacionEspecie());
			datosMonedaEspecie.setCantidadMinimoIncremento(datoEspecie.getCantidadMinimoIncremento());
			datosMonedaEspecie.setCantidadMinimaNegociable(datoEspecie.getCantidadMinimaNegociable());
			datosMonedaEspecie.setCantidadMaximaNegociable(datoEspecie.getCantidadMaximaNegociable());
			datosMonedaEspecie.setImporteMinimoIncremento(datoEspecie.getImporteMinimoIncremento());
			datosMonedaEspecie.setImporteMinimoInvertir(datoEspecie.getImporteMinimoInvertir());
			datosMonedaEspecie.setImporteMaximoInvertir(datoEspecie.getImporteMaximoInvertir());
			datosMonedaEspecie.setRequierePrecioLimite(datoEspecie.getRequierePrecioLimite());
			datosMonedaEspecie.setPrecioLimiteIncremento(datoEspecie.getPrecioLimiteIncremento());
			datosMonedaEspecie.setPrecioLimiteMinimo(datoEspecie.getPrecioLimiteMinimo());
			datosMonedaEspecie.setPrecioLimiteMaximo(datoEspecie.getPrecioLimiteMaximo());
			datosMonedaEspecie.setPermiteMonto(datoEspecie.getPermiteMonto());
			datosMonedaEspecie.setTenenciaNominalNegociable(datoEspecie.getTenenciaNominalNegociable());
			datosMonedaEspecie.setTenenciaNominal(datoEspecie.getTenenciaNominal());

			if (map.get(String.valueOf(datoEspecie.getPlazo())) == null) {
				if (especieHabilitada.equalsIgnoreCase(datosMonedaEspecie.getCodHabilitacion())) {
					if (MONEDA_USD.equalsIgnoreCase(datosMonedaEspecie.getMonedaNegociacion())) {
						if (cuentaOperativa.getSaldoDolares() != null
								&& !"0".equalsIgnoreCase(cuentaOperativa.getSaldoDolares())) {
							map.put(String.valueOf(datoEspecie.getPlazo()), new ArrayList<DatosMonedaEspecie>());
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspecieDolares(true);
						}
					} else {
						if (cuentaOperativa.getSaldoPesos() != null
								&& !"0".equalsIgnoreCase(cuentaOperativa.getSaldoPesos())) {
							map.put(String.valueOf(datoEspecie.getPlazo()), new ArrayList<DatosMonedaEspecie>());
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspeciePesos(true);
						}
					}
				}
			} else {
				if (especieHabilitada.equalsIgnoreCase(datosMonedaEspecie.getCodHabilitacion())) {
					if (MONEDA_USD.equalsIgnoreCase(datosMonedaEspecie.getMonedaNegociacion())) {
						if (cuentaOperativa.getSaldoDolares() != null
								&& !"0".equalsIgnoreCase(cuentaOperativa.getSaldoDolares())) {
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspecieDolares(true);
						}
					} else {
						if (cuentaOperativa.getSaldoPesos() != null
								&& !"0".equalsIgnoreCase(cuentaOperativa.getSaldoPesos())) {
							map.get(String.valueOf(datoEspecie.getPlazo())).add(datosMonedaEspecie);
						} else {
							aperturaEspecie.setEspeciePesos(true);
						}
					}
				}
			}

			if (especieFueraHorario.equalsIgnoreCase(list.getAperturasEspecie().get(0).getCodHabilitacion())) {
				aperturaEspecie.setFueraHorario(true);
			}

		}

		if (!map.isEmpty()) {
			for (Entry<String, ArrayList<DatosMonedaEspecie>> entry : map.entrySet()) {
				AperturaEspecie datosAperturaEspecie = new AperturaEspecie();
				datosAperturaEspecie.setPlazo(Integer.valueOf(entry.getKey()));
				for (DatosMonedaEspecie datos : entry.getValue()) {
					datosAperturaEspecie.getListaMonedas().add(datos);
				}
				aperturaEspecie.getPlazos().add(datosAperturaEspecie);
			}
		}
		return aperturaEspecie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#confirmarOrdenCompra(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.view.ConfirmarOrdenCompraRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<CompraVtaTitulosResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request,
			Cliente cliente, String tipoOperacion, TipoBancaEnum tipoBancaEnum) {

		CompraVtaTitulosResponse response = new CompraVtaTitulosResponse();

		try {
			response = ordenesTitulosDAO.compraVtaTitulos(
					crearRequestCompraVtaTitulos(cliente, request, COMPRA_ORDENES, tipoOperacion, tipoBancaEnum));
			if (response.getCodigo() != 0) {
				return obtenerRespuestaError(response);
			}

			String ctaTitulo = request.getCuentaTitulo();
			String especieCodigo = request.getEspecieCodigo();
			String monedaOperacion = request.getMonedaOperacion();
			
			ConsultaComisionResponse comision = setearDatosComision(cliente, ctaTitulo, especieCodigo, monedaOperacion, COMPRA_ORDENES);

			
			response.getDatos().setDatosConsultaComisionResponsee(comision.getDatos());
			
			response.getDatos().getDatosConsultaComisionResponse().setBonificacion
			(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getBonificacion()));
			
			response.getDatos().getDatosConsultaComisionResponse().setComision
			(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getComision()));
			
			response.getDatos().getDatosConsultaComisionResponse().setComisionOriginal
			(ISBANStringUtils.formatearConComaYDosDecimales(comision.getDatos().getComisionOriginal()));
			

			
		} catch (DAOException e) {
			LOGGER.error("Error cargando la simulacion de ordenes de compra", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		} catch (BusinessException e) {
			LOGGER.info("Error cargando la simulacion de ordenes de compra");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		}
		return respuestaFactory.crearRespuestaOk(CompraVtaTitulosResponse.class, response);
	}


	public ConsultaComisionResponse setearDatosComision(Cliente cliente, String ctaTitulo, String especieCodigo, String monedaOperacion, String tipoOperacion) throws BusinessException, DAOException {
		ConsultaComisionRequestEntity requestComision = new ConsultaComisionRequestEntity();
		DatosConsultaComisionRequestEntity datosComision = new DatosConsultaComisionRequestEntity();
		
		datosComision.setNup(cliente.getNup());
		datosComision.setCtaTitulos(ctaTitulo);
		datosComision.setCodigoEspecie(especieCodigo);
		datosComision.seTipoOperacion(tipoOperacion);
		datosComision.setMoneda(monedaOperacion);
		datosComision.setIdMercado(2);
		requestComision.setFirma(generarFirma(datoTitulos));
		requestComision.setCanal(canalOrdenesRTL);
		requestComision.setSubCanal(subCanalOrdenesRTL);
		requestComision.setDatos(datosComision);
	
		return ordenesTitulosDAO.consultaComision(requestComision);

	}


	/**
	 * Obtener respuesta error.
	 *
	 * @param response the response
	 * @return the respuesta
	 */
	private Respuesta<CompraVtaTitulosResponse> obtenerRespuestaError(CompraVtaTitulosResponse response) {
		if (StringUtils.startsWith(response.getMensajeTecnico(), MENSAJE_TECNICO_SIN_SALDO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_INSUFICIENTE,
					CodigoMensajeConstantes.COMPRAVTA_ORDENES_SIN_SALDO);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), MENSAJE_TECNICO_FUERA_DE_HORARIO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.COMPRAVTA_ORDENES_FUERA_HORARIO);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_SIN_OPERAR_MAS_180_DIAS)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_SIN_OPERAR_MAS_180_DIAS,
					CodigoMensajeConstantes.CUENTA_SIN_OPERAR_MAS_180_DIAS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), EXCEDE_LIMITE_DE_CANAL)) {
			return respuestaFactory.crearRespuestaError("", TipoError.EXCEDE_LIMITE_DE_CANAL,
					CodigoMensajeConstantes.EXCEDE_LIMITE_DE_CANAL);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_NO_FIRMADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_NO_FIRMADA,
					CodigoMensajeConstantes.CUENTA_NO_FIRMADA);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA,
					CodigoMensajeConstantes.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), SE_ENCUENTRA_FUERA_DEL_TUNEL)) {
			return respuestaFactory.crearRespuestaError("", TipoError.SE_ENCUENTRA_FUERA_DEL_TUNEL,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_TITULOS_BLOQUEADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_TITULOS_BLOQUEADA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), TENENCIA_BLOQUEADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.TENENCIA_BLOQUEADA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES,
					CodigoMensajeConstantes.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), SUPERA_TIEMPO_DE_ESPERA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.SUPERA_TIEMPO_DE_ESPERA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(),
				NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS)) {
			return respuestaFactory.crearRespuestaError("",
					TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), OTROS)) {
			return respuestaFactory.crearRespuestaError("", TipoError.OTROS_COMPRA_VENTA_CUENTA_TITULOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (StringUtils.startsWith(response.getMensajeTecnico(), TEST_INVERSOR_NO_REALIZADO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.TEST_INVERSOR_NO_REALIZADO,
					CodigoMensajeConstantes.PERFIL_INVERSOR_NO_PERFILADO_SIMULACION);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
	}

	/**
	 * Crear request compra vta titulos.
	 *
	 * @param cliente       the cliente
	 * @param request       the request
	 * @param tipoOperacion the tipo operacion
	 * @param accion        the accion
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the compta vta titulos request entity
	 */
	private ComptaVtaTitulosRequestEntity crearRequestCompraVtaTitulos(Cliente cliente,
			ConfirmarOrdenCompraRequest request, String tipoOperacion, String accion, TipoBancaEnum tipoBancaEnum) {
		ComptaVtaTitulosRequestEntity requestService = new ComptaVtaTitulosRequestEntity();
		Boolean esBancaPrivada = TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum);
		setearCanalesRequestMWCanales(requestService, esBancaPrivada);
		setearDatosRequestMWCanales(requestService.getDatos(), cliente, esBancaPrivada);
		requestService.getDatos().setSegmento(esBancaPrivada ? "BP" : "RTL");
		requestService.getDatos().setTipoAccion(accion);
		requestService.getDatos().setTipoEspecie(request.getTipoEspecie());
		requestService.getDatos().setIdCumplimiento(request.getIdCumplimiento());
		requestService.getDatos().setSucursalCuenta(request.getSucursalCuentaOperativa());
		requestService.getDatos().setCuentaTitulo(request.getCuentaTitulo());
		if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum)) {
			requestService.getDatos().setTipoCtaOper(obtenerTipoCuentaDesdeAbreviatura(request.getMonedaOperacion()));
		} else {
			requestService.getDatos().setTipoCtaOper(
					obtenerTipoCuentaDesdeAbreviatura(request.getTipoCuentaOperativa(), request.getMonedaOperacion()));
		}
		requestService.getDatos().setMonedaOperacion(request.getMonedaOperacion());
		requestService.getDatos().setNumeroCuenta(request.getNumeroCuentaDebito());
		requestService.getDatos().setTipoOperacion(tipoOperacion);
		requestService.getDatos().setImporteDebitoCredito(request.getImporteDebitoCredito());
		requestService.getDatos().setCantidadTitulo(
				request.getCantidadTitulo() == null ? null : ajustarSeparadorDecimal(request.getCantidadTitulo()));
		;
		requestService.getDatos().setEspecieCodigo(request.getEspecieCodigo());
		requestService.getDatos()
				.setCotizacionLimite(request.getCotizacionLimite() == null ? "0" : request.getCotizacionLimite());
		requestService.getDatos().setCotizacion(request.getCotizacion());
		requestService.getDatos().setPlazo(request.getPlazo().length() > 2 ? request.getPlazo().substring(0, 3).trim()
				: request.getPlazo().trim());
		requestService.getDatos().setOperadorAlta("HB");
		requestService.getDatos().setFechaPrecioRef(request.getFechaPrecioRef());
		requestService.getDatos().setHoraPrecioRef(request.getHoraPrecioRef());
		return requestService;
	}

	/**
	 * Gets the cuenta operativa con PDC.
	 * 
	 * @param cuentasOperativas the cuentas operativas
	 * @param cuenta            the cuenta
	 * @param response          the response
	 * @return the cuenta operativa con PDC
	 */
	private CuentasAdhesionDebitoView getCuentaOperativaConPDC(List<CuentasAdhesionDebitoView> cuentasOperativas,
			CuentasPDC cuenta, ConfiguracionOrdenesCompraResponse response) {
		for (CuentasAdhesionDebitoView cuentaOper : cuentasOperativas) {
			if (cuenta.getListaDatosSaldo().get(0).getNumeroCuenta().equals(String.valueOf(
					Long.valueOf(cuentaOper.getNumero().substring(3).replaceAll("-", "").replaceAll("/", ""))))) {
				CuentasAdhesionDebitoView cuentaResponse = cuentaOper;
				cuentaResponse.setPoderCompra(
						obtenerPDCView(cuenta.getListaDatosSaldo(), response.getAperturaEspecie().getPlazos()));
				cuentasOperativas.remove(cuentaOper);
				return cuentaResponse;
			}
		}
		return null;
	}

	/**
	 * Obtener PDC view.
	 *
	 * @param saldosPDC             the saldos PDC
	 * @param plazosAperturaEspecie the plazos apertura especie
	 * @return the poder compra view
	 */
	private PoderCompraView obtenerPDCView(List<SaldosPDC> saldosPDC, List<AperturaEspecie> plazosAperturaEspecie) {
		PoderCompraView pdcView = new PoderCompraView();
		for (AperturaEspecie plazo : plazosAperturaEspecie) {
			PlazoPDCView plazoPDCView = new PlazoPDCView();
			plazoPDCView.setPlazo(formatearPlazo(plazo.getPlazo()));
			SaldosPDC saldoPDC = obtenerSaldoPDCPorFechaLiquidacionPlazo(
					plazo.getListaMonedas().get(0).getFechaLiquidacion(), saldosPDC);
			plazoPDCView.setSaldoPDC(saldoPDC != null ? saldoPDC.getSaldoPDC() : 0);
			pdcView.getListaSaldos().add(plazoPDCView);
		}
		return pdcView;
	}

	/**
	 * Obtener saldo PDC por fecha liquidacion plazo.
	 *
	 * @param fechaLiquidacion the fecha liquidacion
	 * @param saldosPDC        the saldos PDC
	 * @return the saldos PDC
	 */
	private SaldosPDC obtenerSaldoPDCPorFechaLiquidacionPlazo(String fechaLiquidacion, List<SaldosPDC> saldosPDC) {
		for (SaldosPDC saldo : saldosPDC) {
			String fecha = convertirFechaConfirmarOrden(saldo.getFechaSaldoCompra());
			if (StringUtils.equals(fecha, fechaLiquidacion)) {
				return saldo;
			}
		}
		return null;
	}

	/**
	 * Formatear plazo.
	 *
	 * @param plazo the plazo
	 * @return the string
	 */
	private String formatearPlazo(Integer plazo) {
		if (plazo.equals(0)) {
			return "Inmediato";
		}
		return plazo.toString() + " hs";
	}

	/**
	 * Obtener cuentas titulo con coma.
	 *
	 * @param listaCuentasTitulo the lista cuentas titulo
	 * @return the string
	 */
	private String obtenerCuentasTituloConComa(List<CuentaTituloView> listaCuentasTitulo) {
		String cuentasTitulo = "";

		for (CuentaTituloView cuentaTitulo : listaCuentasTitulo) {
			if (PRIMER_ELEMENTO.equals(cuentasTitulo)) {
				cuentasTitulo = cuentaTitulo.getNroCuenta().replace("/", "");
			} else {
				cuentasTitulo += "," + cuentaTitulo.getNroCuenta().replace("/", "");
			}
		}
		return cuentasTitulo;
	}

	/**
	 * Convertir fecha confirmar orden.
	 *
	 * @param fecha the fecha
	 * @return the string
	 */
	// Formatear fecha del servicio para enviar a front en el formato correcto
	private String convertirFechaConfirmarOrden(String fecha) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			date = format.parse(fecha);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(date);
		} catch (ParseException e) {
			LOGGER.error("Error al convertir la fecha", e);
		}
		return null;
	}

	@Override
	public Respuesta<Reporte> obtenerTenenciasReporte(List<TenenciaTitulosCuentaDTO> listaTenencias,
			TipoBancaEnum tipoBanca, Cliente cliente) {

		CuentasTitulosExcelGeneral cuentasTitulosExcel = new CuentasTitulosExcelGeneral();
		List<CuentaTitulosExcel> listaCuentasTitulos = new ArrayList<CuentaTitulosExcel>();

		for (TenenciaTitulosCuentaDTO cuentaTitulosDTO : listaTenencias) {
			CuentaTitulosExcel cuentaTituloExcel = new CuentaTitulosExcel();
			if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
				cuentaTituloExcel.setNumeroCuenta(cuentaTitulosDTO.getNumeroCuenta());
				cuentaTituloExcel.setIntervinientes(
						crearListaIntervinientes(cuentaTitulosDTO.getNumeroCuenta(), cliente, tipoBanca));
			} else {
				cuentaTituloExcel.setNumeroCuenta("250-" + cuentaTitulosDTO.getNumeroCuentaOperativa());
				cuentaTituloExcel.setIntervinientes(
						crearListaIntervinientes(cuentaTitulosDTO.getNumeroCuentaOperativa(), cliente, tipoBanca));
			}

			List<InfoTenenciasExcel> listaTenenciasPesos = new ArrayList<InfoTenenciasExcel>();
			List<InfoTenenciasExcel> listaTenenciasDolares = new ArrayList<InfoTenenciasExcel>();

			if (!cuentaTitulosDTO.getListaPesos().isEmpty()) {
				cuentaTituloExcel.setIsTenenciasPesos(Boolean.TRUE);
				for (TenenciaTitulosDTO tenenciaPesos : cuentaTitulosDTO.getListaPesos()) {
					listaTenenciasPesos.add(new InfoTenenciasExcel(DivisaEnum.PESO.getSimbolo(), tenenciaPesos, false));
				}

				Collections.sort(listaTenenciasPesos);
				eliminarTipoSiRepetido(listaTenenciasPesos);
				cuentaTituloExcel.setTenenciasPesos(listaTenenciasPesos);
			} else {
				listaTenenciasPesos.add(new InfoTenenciasExcel(null, null, true));
				cuentaTituloExcel.setTenenciasPesos(listaTenenciasPesos);
			}

			if (!cuentaTitulosDTO.getListaDolares().isEmpty()) {
				cuentaTituloExcel.setIsTenenciasDolares(Boolean.TRUE);
				for (TenenciaTitulosDTO tenenciaDolares : cuentaTitulosDTO.getListaDolares()) {
					listaTenenciasDolares
							.add(new InfoTenenciasExcel(DivisaEnum.DOLAR.getSimbolo(), tenenciaDolares, false));
				}

				Collections.sort(listaTenenciasDolares);
				eliminarTipoSiRepetido(listaTenenciasDolares);
				cuentaTituloExcel.setTenenciasDolares(listaTenenciasDolares);
			} else {
				listaTenenciasDolares.add(new InfoTenenciasExcel(null, null, true));
				cuentaTituloExcel.setTenenciasDolares(listaTenenciasDolares);
			}
			cuentaTituloExcel.setTotales(new InfoTotalesTenenciasExcel(cuentaTitulosDTO));

			if (cuentaTituloExcel.getIsTenenciasPesos() || cuentaTituloExcel.getIsTenenciasDolares()) {
				listaCuentasTitulos.add(cuentaTituloExcel);
			}
		}
		cuentasTitulosExcel.setCuentasTitulos(listaCuentasTitulos);

		return reporteDAO.obtenerReporte(cuentasTitulosExcel, "titulosValores", cliente, false);
	}

	private String crearListaIntervinientes(String numeroCuenta, Cliente cliente, TipoBancaEnum tipoBanca) {

		List<Interviniente> listaIntervinientes;

		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			Cuenta cuenta = cliente.getCuentaRetailSiContieneNumero(numeroCuenta.replaceAll("/", ""));
			listaIntervinientes = cuenta.getIntervinientes();
		} else {
			Cuenta cuenta = cliente.getCuentaOperativaSiContieneNumero(numeroCuenta.replaceAll("/", ""));
			listaIntervinientes = cuenta.getIntervinientes();
		}

		StringBuilder intervinientes = new StringBuilder();
		for (Interviniente interviniente : listaIntervinientes) {
			intervinientes.append(interviniente.getApellido() + ", " + interviniente.getNombre() + " / ");
		}

		return intervinientes.substring(0, intervinientes.length() - 2);
	}

	private void eliminarTipoSiRepetido(List<InfoTenenciasExcel> listaTitulosValores) {

		String tipoTV = StringUtils.EMPTY;
		for (InfoTenenciasExcel tituloValor : listaTitulosValores) {
			if (tipoTV.isEmpty()) {
				tipoTV = tituloValor.getTipo();
			} else if (tipoTV.equals(tituloValor.getTipo())) {
				tituloValor.setTipo(StringUtils.EMPTY);
			} else {
				tipoTV = tituloValor.getTipo();
			}
		}
	}

	@Override
	public Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanje(LicitacionCanjeRequest request, Cliente cliente,
			String banca) {
		try {
			ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable = licitacionesDAO
					.consultarTenenciaRenovable(crearRequestConsultarTenenciaCanje(cliente, request, banca));

			if (!CODIGO_OK.equals(resultadoConsultarTenenciaRenovable.getCodigo())
					|| resultadoConsultarTenenciaRenovable.getDatos().isEmpty()) {
				LOGGER.error("Error obteniendo la tenencia renovable. Servicio devuelve codigo {} con los datos {}",
						resultadoConsultarTenenciaRenovable.getCodigo(),
						resultadoConsultarTenenciaRenovable.getDatos());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}

			Respuesta<ConfigurarLicitacionOutView> condicionesAceptadas = establecerCondicionesAceptadas(
					cliente.getNup(), banca);
			if ("BP".equalsIgnoreCase(banca)) {
				request.setCtaTitulos(obtenerCuentaOperativaLoadSaldosCanje(request.getCtaTitulos(), cliente));
			}
			return armarRespuestaCanje(resultadoConsultarTenenciaRenovable, condicionesAceptadas.getRespuesta());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	private Respuesta<LicitacionCanjeResponse> armarRespuestaCanje(
			ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable,
			ConfigurarLicitacionOutView condicionesAceptadas) {

		LicitacionCanjeResponse rta = new LicitacionCanjeResponse();
		for (DatosConsultarTenenciaRenovableResponse datos : resultadoConsultarTenenciaRenovable.getDatos()) {
			LicitacionCanje licitacion = new LicitacionCanje();
			licitacion.setEspecieDestino(datos.getEspecieDescripcion());
			licitacion.setMonedaEspecieDestino(datos.getCodigoMoneda());
			licitacion.setIncrementoMinimo(datos.getIncrementoMinimo());
			licitacion.setMinimo(datos.getLaminaMinima());
			licitacion.setTipoPrecioDato(
					ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(datos.getCoeficiente()),
							obtenerDecimalesValoresCanje(BigDecimal.valueOf(datos.getCoeficiente()))));
			licitacion.setLugar(datos.getLugar());
			licitacion.setCodigoEspecie(datos.getEspecie());
			licitacion.setPrecio(datos.getPrecio());
			rta.getLicitaciones().add(licitacion);
		}

		rta.setCondicionesGeneralesAceptadas(condicionesAceptadas.isCondicionesGeneralesAceptadas());
		rta.setCuentasCustodia(condicionesAceptadas.getCuentasCustodia());
		rta.setCuentasCustodiaTotales(condicionesAceptadas.getCuentasCustodiaTotales());
		rta.setComprobantesCnvDisponibles(condicionesAceptadas.isComprobantesCnvDisponibles());

		rta.setTramoAyuda(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LICITACION_MENSAJE_AYUDA).getMensaje());
		return respuestaFactory.crearRespuestaOk(LicitacionCanjeResponse.class, rta);
	}

	private ConsultarTenenciaRenovable crearRequestConsultarTenenciaCanje(Cliente cliente,
			LicitacionCanjeRequest request, String banca) throws BusinessException {
		ConsultarTenenciaRenovable requestReturn = new ConsultarTenenciaRenovable();
		cargarDatosGenericosRequest(requestReturn);
		DatosConsultarTenenciaRenovable datos = new DatosConsultarTenenciaRenovable();
		requestReturn.setDatos(datos);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setNup(cliente.getNup());
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		/**/
		if ("BR".equals(banca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
			datos.setSegmento(segmentoRTL);
		} else {
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
			datos.setSegmento(segmentoBPriv);
		}

		/**/
		datos.setTipoEjecucion("0");
		datos.setEspecie(request.getEspecie());
		datos.setCuentaTitulo(request.getCtaTitulos().replace("/", ""));
		datos.setMoneda(request.getMoneda());
		datos.setCodigoPliego(request.getCodigoPliego());

		return requestReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.
	 * TitulosBO#simularLicitacion(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(Cliente cliente,
			SimularLicitacionCanjeRequest request) {

		SimularLicitacionCanjeResponseView respuesta;
		Respuesta<SimularOrdenResponse> rta = simularLicitacionCanjeBase(cliente, request,
				TipoBancaEnum.BANCA_PERSONAL.getCodigo());

		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		respuesta = crearRespuestaViewCanjeRTL(rta.getRespuesta().getDatos(), cliente);

		try {
			respuesta.setArchivoCondiciones(descargarCondiciones(request.getNombreArchivoCondiciones()));
		} catch (BusinessException e) {
			LOGGER.error("Error al descargar archivo de condiciones. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		DatosEvaluacionRiesgo datosEvaluacionRiesgo = cargarDatosEvaluacionDeRiesgoCanje(request, cliente,
				rta.getRespuesta());
		EvaluacionDeRiesgoResponse resultadoEvaluacionRiesgo = null;
		try {
			resultadoEvaluacionRiesgo = inversionDAO.evaluacionDeRiesgo(datosEvaluacionRiesgo);
		} catch (DAOException e) {
			LOGGER.error("Servicio evaluacion de riesgo caido. ", e.getMessage(), e);
			resultadoEvaluacionRiesgo = new EvaluacionDeRiesgoResponse();
			resultadoEvaluacionRiesgo.setTipoDisclaimer("0");
			return respuestaFactory.crearRespuestaWarning(respuesta, "", TipoError.SERVICIO_ERI_CAIDO,
					CodigoMensajeConstantes.SERVICIO_EVALUACION_RIESGO_CAIDO);
		}
		if (resultadoEvaluacionRiesgo != null) {
			sessionParametros.setIdEv(resultadoEvaluacionRiesgo.getIdEvaluacion());
		}
		if (!CERO.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())
				&& resultadoEvaluacionRiesgo.getMensaje().getCantidadDeDisclaimers() > 0) {
			respuesta.setCabecera(resultadoEvaluacionRiesgo.getCabecera());
			respuesta.setPie(resultadoEvaluacionRiesgo.getPie());
		}
		if (INVERSOR_RIESGO_BLOQUEANTE.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(resultadoEvaluacionRiesgo,
					TipoError.RIESGO_BLOQUEANTE);
			return respuestaFactory.crearRespuestaWarning(SimularLicitacionCanjeResponseView.class, respuesta,
					itemsMensajeRespuesta);

		} else if (INVERSOR_RIESGO_MEDIO.equals(resultadoEvaluacionRiesgo.getTipoDisclaimer())) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(resultadoEvaluacionRiesgo,
					TipoError.RIESGO_MEDIO);
			return respuestaFactory.crearRespuestaWarning(SimularLicitacionCanjeResponseView.class, respuesta,
					itemsMensajeRespuesta);

		}
		return respuestaFactory.crearRespuestaOk(SimularLicitacionCanjeResponseView.class, respuesta);
	}

	private Respuesta<SimularOrdenResponse> simularLicitacionCanjeBase(Cliente cliente,
			SimularLicitacionCanjeRequest request, String tipoBanca) {
		SimularOrden requestSimulacion;
		SimularOrdenResponse resultadoSimulacion;
		try {
			requestSimulacion = crearRequestSimulacionCanje(cliente, request, tipoBanca);
			resultadoSimulacion = licitacionesDAO.simularOrden(requestSimulacion);
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error en suscripcion por fuera de horario. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO,
					CodigoMensajeConstantes.SUSCRIPCION_LICITACION_FUERA_DE_HORARIO);
		} catch (BusinessException e) {
			LOGGER.error("Error en BO de simular suscripcion a licitacion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error en BO simulando licitacion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(SimularOrdenResponse.class, resultadoSimulacion);
	}

	private SimularOrden crearRequestSimulacionCanje(Cliente cliente, SimularLicitacionCanjeRequest request,
			String tipoBanca) throws BusinessException {
		SimularOrden requestReturn = new SimularOrden();
		cargarDatosGenericosRequest(requestReturn);
		DatosSimularOrden datos = new DatosSimularOrden();
		requestReturn.setDatos(datos);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setNup(cliente.getNup());
		datos.setUsuario(StringUtils.substring(System.getProperty("user.name"), 0, 9));
		if ("BR".equalsIgnoreCase(tipoBanca)) {
			datos.setCanal(canalRTL);
			datos.setSubcanal(subcanalRTL);
			datos.setSegmento(segmentoRTL);
			datos.setTipoCuentaOper(
					obtenerTipoCuentaDesdeAbreviaturaCanje(request.getTipoCuentaOper(), request.getMoneda()));
			datos.setCuentaTit(obtenerSucursalCuentaTit(request.getCuentaTit().replaceAll("/", ""), cliente)
					+ request.getCuentaTit().replaceAll("/", ""));
		} else {
			datos.setTipoCuentaOper(
					obtenerTipoCuentaDesdeAbreviaturaCanje(request.getTipoCuentaOper(), request.getMoneda()));
			datos.setCanal(canalBPriv);
			datos.setSubcanal(subcanalBPriv);
			datos.setSegmento(segmentoBPriv);
			datos.setCuentaTit(request.getCuentaTit());
		}

		datos.setCodigoTramoCanal(request.getCodigoTramoCanal());
		datos.setCodigoPliego(request.getCodigoPliego());
		datos.setCodigoTramo(request.getCodigoTramo());
		datos.setTipoCuenta("O");
		datos.setSucursal(request.getSucursal());
		datos.setCuentaOper(request.getCuentaOper().replaceAll("/", ""));

		datos.setCodigoEspecie(request.getCodigoEspecie());
		datos.setMoneda(request.getMoneda());
		datos.setPrecio(String.valueOf(request.getPrecio()));
		datos.setCorreoElectronico(request.getCorreoElectronico());
		datos.setRenovacion(request.getRenovacion());

		datos.setRenovacion(request.getRenovacion());
		datos.setEspecieRenovacion(request.getEspecieRenovacion());
		datos.setCantidad(String.valueOf(request.getCantidad()));
		datos.setCantidadRenovable(request.getCantidad());
		datos.setLugarRenovacion(Short.valueOf(request.getLugarRenovacion()));
		datos.setTipoEjecucion("0");
		datos.setCoeficiente(request.getCoeficiente());

		datos.setUsuarioRacf(cliente.getUsuarioRacf());
		datos.setPasswordRacf(cliente.getPasswordRacf());

		return requestReturn;
	}

	private String obtenerSucursalCuentaTit(String cuentaTitulo, Cliente cliente) {
		for (Cuenta c : cliente.getCuentasRetail()) {
			if (Long.valueOf(cuentaTitulo).equals(Long.valueOf(c.getNroCuentaProducto().replaceAll("/", "")))) {
				return c.getNroSucursal();
			}
		}
		return "000";
	}

	@Override
	public Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request,
			Cliente cliente, String banca) {

		boolean permiteReintentar;

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		FinalizarLicitacionCanjeView responseFinalizar = null;
		try {
			ConfirmarOrden requestDao = armarRequestConfirmarOrden(request, cliente, banca, true);
			ConfirmarOrdenResponse responseDao = licitacionesDAO.confirmarOrden(requestDao);
			responseFinalizar = crearViewResponseCanje(convertirFechas(responseDao.getDatos()));
			try {
				if (sessionParametros.getIdEv() != null) {
					inversionDAO.confirmacionOrden(cargarDatosConfirmarOrden(responseDao));
				}
			} catch (DAOException e) {
				LOGGER.error("Error al invocar el Ws ERI confirmar orden. ", e);
			}
		} catch (BusinessException e) {
			LOGGER.error("Error en BO al generar el request de confirmar orden. ", e);
			return manejarReintentoCanje(request.getDescripcionEspecie(), permiteReintentar,
					CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_ERROR);
		} catch (TiempoEsperaAgotadoException e) {
			LOGGER.error("Error en DAO al confirmar orden, limite de espera agotado. ", e);
			return manejarReintentoCanje(null, permiteReintentar,
					CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_ERROR);
		} catch (TimeOutException e) {
			LOGGER.error("Error en DAO al confirmar orden, timeout. ", e);
			return manejarReintentoCanje(request.getDescripcionEspecie(), false,
					CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_ERROR);
		} catch (DAOException e) {
			if (SALDO_INSUFICIENTE_PESOS.equals(e.getMessage()) || SALDO_INSUFICIENTE_DOLARES.equals(e.getMessage())) {
				LOGGER.error("Error, saldo insuficiente ", e);
				return manejarReintentoCanje(request.getDescripcionEspecie(), permiteReintentar,
						CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_ERROR);
			} else {
				LOGGER.error("Error en DAO al confirmar orden. ", e);
				return manejarReintentoCanje(request.getDescripcionEspecie(), permiteReintentar,
						CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_ERROR);
			}
		}

		Respuesta<FinalizarLicitacionCanjeView> rta = respuestaFactory
				.crearRespuestaOk(FinalizarLicitacionCanjeView.class, responseFinalizar);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(obtenerMensajeFeedbackLicitaciones(responseFinalizar.getNombreEspecie(),
				CodigoMensajeConstantes.FEEDBACK_LICITACION_CANJE_OK));
		item.setTipoError(TipoError.OK.getDescripcion());
		item.setTag(null);
		rta.add(item);
		return rta;
	}

	private FinalizarLicitacionCanjeView crearViewResponseCanje(DatosConfirmarOrdenResponse datos) {
		FinalizarLicitacionCanjeView responseView = new FinalizarLicitacionCanjeView();

		responseView.setNombreEspecie(datos.getEspecieDescripcion());
		responseView.setTramo(WordUtils.capitalizeFully(datos.getTramo()));
		responseView.setMonedaEspecie(ARS.equalsIgnoreCase(datos.getMonedaEspecie()) ? "Pesos" : "Dólares");
		responseView.setCuentaTitulos(ISBANStringUtils.formatearNumeroCuenta(String.valueOf(datos.getCuentaTit())));
		responseView.setCuentaOperativa(ISBANStringUtils.formatearSucursal(String.valueOf(datos.getSucursal())) + "-"
				+ ISBANStringUtils.formatearNumeroCuenta(String.valueOf(datos.getCuentaOper())));
		responseView.setTipoCuentaOperativa(TipoCuenta.fromCodigo(datos.getTipoCuentaOper()).getDescripcionConMoneda());
		responseView.setEspecieDestino(datos.getEspecieOtorgarDescripcion());
		responseView.setMonedaEspecieDestino(ARS.equalsIgnoreCase(datos.getCodMonedaEspecieDestino()) ? "Pesos" : "Dólares");
		responseView.setValorNominal(ISBANStringUtils.formatearValorEnteroYDecimales(datos.getCantidad(),
				obtenerDecimalesValoresCanje(new BigDecimal(datos.getCantidad()))));
		responseView.setTipoPrecio(WordUtils.capitalizeFully(datos.getTipoPrecio()));
		responseView.setTipoPrecioDato(TIPO_PRECIO_RATIO.equalsIgnoreCase(datos.getTipoPrecio())
				? ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(datos.getCoeficiente()),
						obtenerDecimalesValoresCanje(BigDecimal.valueOf(datos.getCoeficiente())))
				: TIPO_PRECIO_DATO_DE_CORTE);
		if (datos.getMontoADeb() != null) {
			responseView
					.setImporteADebitar(
							datos.getMontoADeb() != null && datos.getMontoADeb().compareTo(BigDecimal.ZERO) != 0
									? obtenerMonedaImporteCanje(datos.getMoneda()) + " "
											+ ISBANStringUtils.formatearConComaYVariosDecimales2(
													String.valueOf(datos.getMontoADeb()), 2)
									: "A determinar en la liquidación");
		} else {
			responseView.setImporteADebitar("A determinar en la liquidación");
		}
		responseView.setFechaDebitoCuenta(datos.getFechaDebProv());
		responseView.setFechaHoraCierre(datos.getFechaCierre());
		responseView.setFechaAdjudicacion(datos.getFechaAdjud());
		responseView.setFechaLiquidacionTitulos(datos.getFechaLiq());
		responseView.setTipoCambio(formatearTipoCambio(datos.getTipoCambio(), datos.getMoneda(),
				datos.getMonedaEspecie(), datos.getCodMonedaEspecieDestino()));
		responseView.setNumeroOrden(String.valueOf(datos.getNumOrden()));
		responseView.setEmail(datos.getCorreoElect());
		responseView
				.setComisiones(
						datos.getMontoComision() > 0
								? obtenerMonedaImporteCanje(datos.getMoneda()) + " "
										+ ISBANStringUtils.formatearConComaYVariosDecimales2(
												String.valueOf(datos.getMontoComision()), 2)
								: null);
		responseView
				.setImpuestos(
						datos.getMontoImpuesto() > 0
								? obtenerMonedaImporteCanje(datos.getMoneda()) + " "
										+ ISBANStringUtils.formatearConComaYVariosDecimales2(
												String.valueOf(datos.getMontoImpuesto()), 2)
								: null);
		responseView.setLegal(datos.getLeyendaLegal());
		responseView.setLegalCanal(datos.getLeyendaLegalCan());

		return responseView;
	}

	private String formatearTipoCambio(String tipoCambio, String moneda, String monedaEspecie,
			String codMonedaEspecieDestino) {
		if (moneda.equals(monedaEspecie) && monedaEspecie.equals(codMonedaEspecieDestino)) {
			return null;
		}
		return ISBANStringUtils.formatearValorEnteroYDecimales(Double.valueOf(tipoCambio),
				obtenerDecimalesValoresCanje(new BigDecimal(tipoCambio)));
	}

	private int obtenerDecimalesValoresCanje(BigDecimal valor) {
		String[] saldoSeparado = String.valueOf(valor).split("\\.");
		if (saldoSeparado.length == 1) {
			return 2;
		}

		if (saldoSeparado[1].length() > 2 && saldoSeparado[1].length() <= 7) {
			return saldoSeparado[1].length();
		}

		if (saldoSeparado[1].length() > 7) {
			return 7;
		}

		return 2;
	}

	private String obtenerMensajeFeedbackLicitaciones(String especie, String codigoMensaje) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
		if (especie != null) {
			mensaje = MessageFormat.format(mensaje, especie);
		}
		return mensaje;
	}

	private String obtenerMonedaImporteCanje(String moneda) {
		return MONEDA_USD.equalsIgnoreCase(moneda) ? "u$s" : "$";
	}

	/**
	 * Devuelve respuesta con el mensaje correspondiente e indicando si reintenta o
	 * no.
	 *
	 * @param nombreEspecie     the nombre especie
	 * @param permiteReintentar the permite reintentar
	 * @param codigoMensaje     the codigo mensaje
	 * @return the respuesta
	 */
	private Respuesta<FinalizarLicitacionCanjeView> manejarReintentoCanje(String nombreEspecie,
			boolean permiteReintentar, String codigoMensaje) {
		String mensaje = obtenerMensajeFeedbackLicitaciones(nombreEspecie, codigoMensaje);

		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaErrorPersonalizado(FinalizarLicitacionCanjeView.class, mensaje,
					TipoError.ERROR_FINALIZAR_LICITACION_CON_REINTENTO.toString());
		}
		return respuestaFactory.crearRespuestaErrorPersonalizado(FinalizarLicitacionCanjeView.class, mensaje,
				TipoError.ERROR_FINALIZAR_LICITACION_SIN_REINTENTO.toString());
	}

	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(Cliente cliente,
			SimularLicitacionCanjeRequest request) {
		SimularLicitacionCanjeResponseView respuesta;
		Respuesta<SimularOrdenResponse> rta = simularLicitacionCanjeBase(cliente, request,
				TipoBancaEnum.BANCA_PRIVADA.getCodigo());

		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		respuesta = crearRespuestaViewCanjeBPriv(rta.getRespuesta().getDatos(), cliente);

		try {
			respuesta.setArchivoCondiciones(descargarCondiciones(request.getNombreArchivoCondiciones()));
		} catch (BusinessException e) {
			LOGGER.error("Error al descargar archivo de condiciones. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (respuesta.getDisclaimer() == null || respuesta.getDisclaimer().isEmpty()) {
			return respuestaFactory.crearRespuestaOk(SimularLicitacionCanjeResponseView.class, respuesta);
		}

		// Se devuelve warning si hay un disclaimer para mostrar.
		return respuestaFactory.crearRespuestaWarning(SimularLicitacionCanjeResponseView.class, respuesta, null);
	}

	private String obtenerCuentaOperativaLoadSaldosCanje(String cuentaTitulos, Cliente cliente) throws DAOException {
		List<CuentaTituloDTO> listCuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		try {
			listCuentasTituloDTO = obtenerRelacionOperativaTitulo(cliente);

			for (CuentaTituloDTO cuentaDTO : listCuentasTituloDTO) {
				String cuentaComparar = StringUtils.right(cuentaTitulos.replace("/", ""), LARGO_NUMERO_CUENTA);
				if (Long.valueOf(cuentaDTO.getNroCuentaFormateado()).equals(Long.valueOf(cuentaComparar))) {
					return cuentaDTO.getCuentaOperativaSinFormatear();
				}
			}
		} catch (BusinessException e) {
			LOGGER.error("No se pudieron obtener las cuentas", e);
			throw new DAOException();
		}
		LOGGER.error("La cuenta titulo no coincide con las cuentas del cliente");
		throw new DAOException();
	}
}
