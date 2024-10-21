
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetalleCuotaparteRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetalleFondoRimpRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetallesParticipantesRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetallesPlazoFijoRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetallesPlazoFijoResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetallesRespuestaTituloEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosHabilitaCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosParticipantesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRequestHabilitaCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaFondosRimp;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleCuotasPrestamosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleFondoRimpRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleFondosRimpEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleImpuestoMonedaExtranjeraEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleMensualImpuestosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallePlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallePlazoFijoRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallePlazoFijoResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleTarjetaCreditoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleTitulosResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallesTitulosRequetsEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ParticipantesRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ParticipantesResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ResumenCuentaInversionesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ValorCuotaParteRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ValorCuotaParteRespEntity;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.InversionesHelper;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;
import oracle.jdbc.OracleTypes;

/**
 * The Class TenenciasDetalleDAOImpl.
 */
@Component
@TargetSystem(DataBase.IMPUESTOS)
public class TenenciasDetalleDAOImpl extends BaseDatoDAOImpl implements TenenciasDetalleDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasDetalleDAOImpl.class);

	/** SCHEMA. */
	private static final String TENENCIAS_SCHEMA = "VIR03";

	/** PACKAGE Estadistica. */
	private static final String TENENCIAS_PACKAGE = "HB_CONS";

	/** PROCEDURES. */
	private static final String PF_PROCEDURE = "PFIDET";

	/** The Constant RESUMEN_CUENTA_INVERSIONES_PROCEDURE. */
	private static final String RESUMEN_CUENTA_INVERSIONES_PROCEDURE = "movinv";

	/** The Constant CUOTAS_PRESTAMOS_PROCEDURE. */
	private static final String CUOTAS_PRESTAMOS_PROCEDURE = "detpre";

	/** The Constant DETALLE_IMP_PROCEDURE. */
	private static final String DETALLE_IMP_PROCEDURE = "detimp";

	/** The Constant DETALLE_MONEDA_EXT_PROCEDURE. */
	private static final String DETALLE_MONEDA_EXT_PROCEDURE = "detimpme";

	/** The Constant DETALLE_TARJETA_CRED_PROCEDURE. */
	private static final String DETALLE_TARJETA_CRED_PROCEDURE = "dettjc";

	/** The Constant LOG_PROCEDURE. */
	private static final String LOG_PROCEDURE = "Se consultara el procedure {} con los datos nup: {} - anio: {}.";

	/** The Constant IN_OUT_SALIDA_PFI. */
	private static final String IN_OUT_SALIDA_PFI = "pfi_cv";
	/** The Constant IN_OUT_SALIDA_MINV. */
	private static final String IN_OUT_SALIDA_MINV = "minv_cv";
	/** The Constant IN_OUT_SALIDA_PRC. */
	private static final String IN_OUT_SALIDA_PRC = "prc_cv";
	/** The Constant IN_OUT_SALIDA_IMPDET. */
	private static final String IN_OUT_SALIDA_IMPDET = "impdet_cv";
	/** The Constant IN_OUT_SALIDA_IMPME. */
	private static final String IN_OUT_SALIDA_IMPME = "impmedet_cv";
	/** The Constant IN_OUT_SALIDA_TJCDET. */
	private static final String IN_OUT_SALIDA_TJCDET = "tjcdet_cv";

	/** The Constant ANIO. */
	private static final String ANIO = "ANIO";
	/** The Constant PECODPRO. */
	private static final String PECODPRO = "PECODPRO";
	/** The Constant PECODSUB. */
	private static final String PECODSUB = "PECODSUB";
	/** The Constant PENUMCON. */
	private static final String PENUMCON = "PENUMCON";
	/** The Constant CERTIFICADO. */
	private static final String CERTIFICADO = "CERTIFICADO";
	/** The Constant DIVISA. */
	private static final String DIVISA = "DIVISA";
	/** The Constant SALDO. */
	private static final String SALDO = "SALDO";
	/** The Constant INT_COBRADO. */
	private static final String INT_COBRADO = "INT_COBRADO";
	/** The Constant AJU_COBRADO. */
	private static final String AJU_COBRADO = "AJU_COBRADO";
	/** The Constant INT_DEVENGADO. */
	private static final String INT_DEVENGADO = "INT_DEVENGADO";

	/** The Constant AJU_DEVENGADO. */
	private static final String AJU_DEVENGADO = "AJU_DEVENGADO";

	/** The Constant RET_GANANCIAS. */
	private static final String RET_GANANCIAS = "RET_GANANCIAS";
	/** The Constant ESTADO. */
	private static final String ESTADO = "ESTADO";
	/** The Constant CUENTA_DEBITO_TIPO. */
	private static final String CUENTA_DEBITO_TIPO = "CUENTA_DEBITO_TIPO";
	/** The Constant CUENTA_DEBITO_SUC. */
	private static final String CUENTA_DEBITO_SUC = "CUENTA_DEBITO_SUC";
	/** The Constant CUENTA_DEBITO_NRO. */
	private static final String CUENTA_DEBITO_NRO = "CUENTA_DEBITO_NRO";
	/** The Constant CUENTA_CREDITO_TIPO. */
	private static final String CUENTA_CREDITO_TIPO = "CUENTA_CREDITO_TIPO";
	/** The Constant CUENTA_CREDITO_SUC. */
	private static final String CUENTA_CREDITO_SUC = "CUENTA_CREDITO_SUC";
	/** The Constant CUENTA_CREDITO_NRO. */
	private static final String CUENTA_CREDITO_NRO = "CUENTA_CREDITO_NRO";
	/** The Constant PLAZO. */
	private static final String PLAZO = "PLAZO";
	/** The Constant FECHA_VENCIMIENTO. */
	private static final String FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";
	/** The Constant FECHA_CONSTITUCION. */
	private static final String FECHA_CONSTITUCION = "FECHA_CONSTITUCION";
	/** The Constant TIPO. */
	private static final String TIPO = "TIPO";
	/** The Constant ESPE_TIPO. */
	private static final String ESPE_TIPO = "espe_tipo";
	/** The Constant ESPE_COD. */
	private static final String ESPE_COD = "Espe_cod";
	/** The Constant CUENTA_M. */
	private static final String CUENTA_M = "cuenta";
	/** The Constant fecha. */
	private static final String FECHA = "fecha";
	/** The Constant concepto. */
	private static final String CONCEPTO = "concepto";
	/** The Constant comprobante. */
	private static final String COMPROBANTE = "comprobante";
	/** The Constant cantidad. */
	private static final String CANTIDAD = "cantidad";
	/** The Constant cotizacion. */
	private static final String COTIZACION = "cotizacion";
	/** The Constant importe. */
	private static final String IMPORTE = "importe";
	/** The Constant suc_cta_ori. */
	private static final String SUC_CTA_ORI = "suc_cta_ori";
	/** The Constant tip_cta_ori. */
	private static final String TIPO_CTA_ORI = "tip_cta_ori";
	/** The Constant nro_cta_ori. */
	private static final String NRO_CTA_ORI = "num_cta_ori";
	/** The Constant divisa. */
	private static final String DIVISA_M = "divisa";
	/** The Constant NRO_RECIBO. */
	private static final String NRO_RECIBO = "NRO_RECIBO";
	/** The Constant FECHA_VTO. */
	private static final String FECHA_VTO = "FECHA_VTO";
	/** The Constant SDO_ANT_SIN_AJUSTAR. */
	private static final String SDO_ANT_SIN_AJUSTAR = "SDO_ANT_SIN_AJUSTAR";
	/** The Constant IMPORTE_CUOTA. */
	private static final String IMPORTE_CUOTA = "IMPORTE_CUOTA";
	/** The Constant CAPITAL. */
	private static final String CAPITAL = "CAPITAL";
	/** The Constant INTERESES. */
	private static final String INTERESES = "INTERESES";
	/** The Constant GASTOS. */
	private static final String GASTOS = "GASTOS";
	/** The Constant COMISIONES. */
	private static final String COMISIONES = "COMISIONES";
	/** The Constant SEGUROS. */
	private static final String SEGUROS = "SEGUROS";
	/** The Constant IMPUESTOS. */
	private static final String IMPUESTOS = "IMPUESTOS";
	/** The Constant INT_COMP. */
	private static final String INT_COMP = "INT_COMP";
	/** The Constant INT_MORA. */
	private static final String INT_MORA = "INT_MORA";
	/** The Constant TNA. */
	private static final String TNA = "TNA";
	/** The Constant TIPO_CUENTA. */
	private static final String TIPO_CUENTA = "TIPO_CUENTA";
	/** The Constant PECODOFI. */
	private static final String PECODOFI = "PECODOFI";
	/** The Constant CUENTA. */
	private static final String CUENTA = "CUENTA";
	/** The Constant ALICUOTA. */
	private static final String ALICUOTA = "ALICUOTA";
	/** The Constant ORIGEN. */
	private static final String ORIGEN = "ORIGEN";
	/** The Constant TIPO_TARJETA. */
	private static final String TIPO_TARJETA = "TIPO_TARJETA";
	/** The Constant IMP_DEBITO_01. */
	private static final String IMP_DEBITO_01 = "IMP_DEBITO_01";
	/** The Constant IMP_DEBITO_02. */
	private static final String IMP_DEBITO_02 = "IMP_DEBITO_02";
	/** The Constant IMP_DEBITO_03. */
	private static final String IMP_DEBITO_03 = "IMP_DEBITO_03";
	/** The Constant IMP_DEBITO_04. */
	private static final String IMP_DEBITO_04 = "IMP_DEBITO_04";
	/** The Constant IMP_DEBITO_05. */
	private static final String IMP_DEBITO_05 = "IMP_DEBITO_05";
	/** The Constant IMP_DEBITO_06. */
	private static final String IMP_DEBITO_06 = "IMP_DEBITO_06";
	/** The Constant IMP_DEBITO_07. */
	private static final String IMP_DEBITO_07 = "IMP_DEBITO_07";
	/** The Constant IMP_DEBITO_08. */
	private static final String IMP_DEBITO_08 = "IMP_DEBITO_08";
	/** The Constant IMP_DEBITO_09. */
	private static final String IMP_DEBITO_09 = "IMP_DEBITO_09";
	/** The Constant IMP_DEBITO_10. */
	private static final String IMP_DEBITO_10 = "IMP_DEBITO_10";
	/** The Constant IMP_DEBITO_11. */
	private static final String IMP_DEBITO_11 = "IMP_DEBITO_11";
	/** The Constant IMP_DEBITO_12. */
	private static final String IMP_DEBITO_12 = "IMP_DEBITO_12";
	/** The Constant IMP_CREDITO_01. */
	private static final String IMP_CREDITO_01 = "IMP_CREDITO_01";
	/** The Constant IMP_CREDITO_02. */
	private static final String IMP_CREDITO_02 = "IMP_CREDITO_02";
	/** The Constant IMP_CREDITO_03. */
	private static final String IMP_CREDITO_03 = "IMP_CREDITO_03";
	/** The Constant IMP_CREDITO_04. */
	private static final String IMP_CREDITO_04 = "IMP_CREDITO_04";
	/** The Constant IMP_CREDITO_05. */
	private static final String IMP_CREDITO_05 = "IMP_CREDITO_05";
	/** The Constant IMP_CREDITO_06. */
	private static final String IMP_CREDITO_06 = "IMP_CREDITO_06";
	/** The Constant IMP_CREDITO_07. */
	private static final String IMP_CREDITO_07 = "IMP_CREDITO_07";
	/** The Constant IMP_CREDITO_08. */
	private static final String IMP_CREDITO_08 = "IMP_CREDITO_08";
	/** The Constant IMP_CREDITO_09. */
	private static final String IMP_CREDITO_09 = "IMP_CREDITO_09";
	/** The Constant IMP_CREDITO_10. */
	private static final String IMP_CREDITO_10 = "IMP_CREDITO_10";
	/** The Constant IMP_CREDITO_11. */
	private static final String IMP_CREDITO_11 = "IMP_CREDITO_11";
	/** The Constant IMP_CREDITO_12. */
	private static final String IMP_CREDITO_12 = "IMP_CREDITO_12";
	/** The Constant IMP_computable_01. */
	private static final String IMP_COMPUTABLE_01 = "IMP_computable_01";
	/** The Constant IMP_computable_02. */
	private static final String IMP_COMPUTABLE_02 = "IMP_computable_02";
	/** The Constant IMP_computable_03. */
	private static final String IMP_COMPUTABLE_03 = "IMP_computable_03";
	/** The Constant IMP_computable_04. */
	private static final String IMP_COMPUTABLE_04 = "IMP_computable_04";
	/** The Constant IMP_COMPUTABLE_05. */
	private static final String IMP_COMPUTABLE_05 = "IMP_computable_05";
	/** The Constant IMP_COMPUTABLE_06. */
	private static final String IMP_COMPUTABLE_06 = "IMP_computable_06";
	/** The Constant IMP_COMPUTABLE_07. */
	private static final String IMP_COMPUTABLE_07 = "IMP_computable_07";
	/** The Constant IMP_COMPUTABLE_08. */
	private static final String IMP_COMPUTABLE_08 = "IMP_computable_08";
	/** The Constant IMP_COMPUTABLE_09. */
	private static final String IMP_COMPUTABLE_09 = "IMP_computable_09";
	/** The Constant IMP_COMPUTABLE_10. */
	private static final String IMP_COMPUTABLE_10 = "IMP_computable_10";
	/** The Constant IMP_COMPUTABLE_11. */
	private static final String IMP_COMPUTABLE_11 = "IMP_computable_11";
	/** The Constant IMP_COMPUTABLE_12. */
	private static final String IMP_COMPUTABLE_12 = "IMP_computable_12";
	/** The Constant IN_NUP. */
	private static final String IN_NUP = "p_nup";
	/** The Constant P_ANIO. */
	private static final String P_ANIO = "p_anio";
	/** The Constant IN_ANIO_DESDE. */
	private static final String IN_ANIO_DESDE = "p_anio_desde";
	/** The Constant IN_ANIO_HASTA. */
	private static final String IN_ANIO_HASTA = "p_anio_hasta";
	/** The Constant IN_ESPE_TIPO. */
	private static final String IN_ESPE_TIPO = "p_espe_tipo";
	/** The Constant DIFERENCIA. */
	private static final String DIFERENCIA = "diferencia";

	private  List<ResumenCuentaInversionesEntity> listaResumenFondos;
	
	private List<ResumenCuentaInversionesEntity>  listaResumenInversion;
	
	private List<DetallePlazoFijoEntity> listaDetallePlazoFijo;

	private String anioForFondo;

	private static final int ANIO_CAMBIO = 2018;

	@Autowired
	private RestWebClient restWebClient;

	@Autowired
	protected SesionCliente sesionCliente;

	@Autowired
	private InversionWSHelper inversionWSHelper;

	private static final String NOMBRE_WS_RIMP = "INVERSIONES.TENENCIAS";

	private static final String FONDOS_EXCLUIDOS = "190,191,238,239,240,241,250,251,255,256,260,261,265,266,270,271,275,276,280,281,286,287,290,291,295,296,300";

	private static String pathObtenerMovimientosFondosPorNup = "/ObtenerMovimientosFondosPorNup";
	
	private static String pathObtenerMovimientosDetallePF = "/ObtenerDetallePlazoFijo";
	
	private static String pathValorCuotaparte = "/ObtenerValorCuotaparteFondo";
	
	private static String PATH_TITULO_DETALLE = "/ObtenerDetalleTitulosValores";
	
	private static String pathConsularHabilitaCompraVetaBonos = "/ConsultaHabilitacionCompraVentaBonos";
	
	/** The Constant OBTENER PARTICIPANTES */
	private static String OBTENER_PARTICIPANTES = "/ObtenerParticipantes";

	@Value("${FONDOS.CANALTIPO}")
	private String canalTipo;

	/** The canal id. */
	@Value("${FONDOS.CANALID}")
	private String canalId;

	/** The canal version. */
	@Value("${FONDOS.CANALVERSION}")
	private String canalVersion;

	/** The subcanal tipo. */
	@Value("${FONDOS.SUBCANALTIPO}")
	private String subcanalTipo;

	/** The subcanal id. */
	@Value("${FONDOS.SUBCANALID}")
	private String subcanalId;

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerDetallePlazoFijo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DetallePlazoFijoEntity> obtenerDetallePlazoFijo(String nup, String anioDesde, String anioHasta)
			throws DAOException {

		LOGGER.info("Se consultara el procedure {} con los datos nup: {} - anioDesde: {} - anioHasta: {}.",
				PF_PROCEDURE, nup, anioDesde, anioHasta);
		this.listaDetallePlazoFijo = new ArrayList<DetallePlazoFijoEntity>();
		
		if (Integer.parseInt(anioDesde) > ANIO_CAMBIO) {
			List<DatosDetallesPlazoFijoResponseEntity> responsePart = new ArrayList<DatosDetallesPlazoFijoResponseEntity>();
			
			responsePart= obtenerDetallesPlazoFijo(anioDesde, nup).getDatos();
			
			for (DatosDetallesPlazoFijoResponseEntity datosDet: responsePart) {
				DetallePlazoFijoEntity detallepf = new DetallePlazoFijoEntity();
				setDetallePlazoFijo(detallepf, datosDet);
				
				listaDetallePlazoFijo.add(detallepf);
			}

			return listaDetallePlazoFijo;

		} else {

			List<SqlParameter> parametros = new ArrayList<SqlParameter>();
			parametros.add(new SqlOutParameter(IN_OUT_SALIDA_PFI, OracleTypes.CURSOR,
					new ResultSetExtractor<List<DetallePlazoFijoEntity>>() {
						@Override
						public List<DetallePlazoFijoEntity> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							List<DetallePlazoFijoEntity> detallePlazoFijoList = new ArrayList<DetallePlazoFijoEntity>();
							while (rs.next()) {
								DetallePlazoFijoEntity detalleCuotasPrestamosEntity = new DetallePlazoFijoEntity();
								setDetalleCuotasPrestamosEntity(detalleCuotasPrestamosEntity, rs);

								detallePlazoFijoList.add(detalleCuotasPrestamosEntity);
							}
							return detallePlazoFijoList;
						}

						private void setDetalleCuotasPrestamosEntity(
								DetallePlazoFijoEntity detalleCuotasPrestamosEntity, ResultSet rs) throws SQLException {
							detalleCuotasPrestamosEntity.setAnio(rs.getString(ANIO));
							detalleCuotasPrestamosEntity.setPecodpro(rs.getString(PECODPRO));
							detalleCuotasPrestamosEntity.setPecodofi(rs.getString(PECODOFI));
							detalleCuotasPrestamosEntity.setPenumcon(rs.getString(PENUMCON));
							detalleCuotasPrestamosEntity.setCertificado(rs.getString(CERTIFICADO));
							detalleCuotasPrestamosEntity
									.setDivisa(TenenciasHelper.monedaCanonico(rs.getString(DIVISA)));
							detalleCuotasPrestamosEntity.setSaldo(rs.getString(SALDO));
							detalleCuotasPrestamosEntity.setIntCobrado(rs.getString(INT_COBRADO));
							detalleCuotasPrestamosEntity.setAjuCobrado(rs.getString(AJU_COBRADO));
							detalleCuotasPrestamosEntity.setIntDevengado(rs.getString(INT_DEVENGADO));
							detalleCuotasPrestamosEntity.setAjuDevengado(
									ISBANStringUtils.formatearConComaYDosDecimales(rs.getString(AJU_DEVENGADO)));
							detalleCuotasPrestamosEntity.setRetGanancias(rs.getString(RET_GANANCIAS));
							detalleCuotasPrestamosEntity.setEstado(rs.getString(ESTADO));
							detalleCuotasPrestamosEntity.setCuentaDebitoTipo(rs.getString(CUENTA_DEBITO_TIPO));
							detalleCuotasPrestamosEntity.setCuentaDebitoSuc(rs.getString(CUENTA_DEBITO_SUC));
							detalleCuotasPrestamosEntity.setCuentaDebitoNro(rs.getString(CUENTA_DEBITO_NRO));
							detalleCuotasPrestamosEntity.setCuentaCreditoTipo(rs.getString(CUENTA_CREDITO_TIPO));
							detalleCuotasPrestamosEntity.setCuentaCreditoSuc(rs.getString(CUENTA_CREDITO_SUC));
							detalleCuotasPrestamosEntity.setCuentaCreditoNro(rs.getString(CUENTA_CREDITO_NRO));
							detalleCuotasPrestamosEntity.setPlazo(rs.getString(PLAZO));
							detalleCuotasPrestamosEntity.setFechaVencimiento(rs.getString(FECHA_VENCIMIENTO));
							detalleCuotasPrestamosEntity.setFechaConstitucion(rs.getString(FECHA_CONSTITUCION));
							detalleCuotasPrestamosEntity.setTipo(rs.getString(TIPO));

						}
					}));
			parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
			parametros.add(new SqlParameter(IN_ANIO_DESDE, Types.VARCHAR));
			parametros.add(new SqlParameter(IN_ANIO_HASTA, Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup).addValue(IN_ANIO_DESDE, anioDesde)
					.addValue(IN_ANIO_HASTA, anioHasta);

			try {
				Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, PF_PROCEDURE, in,
						parametros.toArray(new SqlParameter[parametros.size()]));
				@SuppressWarnings("unchecked")
				List<DetallePlazoFijoEntity> lista = (List<DetallePlazoFijoEntity>) respuesta.get(IN_OUT_SALIDA_PFI);
				return lista;

			} catch (SQLException sqle) {
				LOGGER.error(sqle.getMessage(), sqle);
				throw new DAOException(sqle);
			} catch (RuntimeException ex) {
				LOGGER.error(ex.getMessage(), ex);
				throw new DAOException(ex);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerResumenCuentaInversiones(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<ResumenCuentaInversionesEntity> obtenerResumenCuentaInversiones(String nup, String anio,
			String espeTipo) throws DAOException {
		this.anioForFondo = anio;
		this.listaResumenFondos = new ArrayList<ResumenCuentaInversionesEntity>();
		this.listaResumenInversion = new ArrayList<ResumenCuentaInversionesEntity>();
		
		LOGGER.info("Se consultara el procedure {} con los datos nup: {} - anio: {} - espeTipo: {}.",
				RESUMEN_CUENTA_INVERSIONES_PROCEDURE, nup, anio, espeTipo);
		if (espeTipo.equals("FON") && Integer.parseInt(anio) > ANIO_CAMBIO) {

			try {

				List<DatosRespuestaFondosRimp> responseFondos = new ArrayList<DatosRespuestaFondosRimp>();
				responseFondos = obtenerDetallesFondos(anio, nup).getDatos();

				for (DatosRespuestaFondosRimp item : responseFondos) {
					ResumenCuentaInversionesEntity fondo = new ResumenCuentaInversionesEntity();
					try {
						setResumenCuentaInversionesEntity(fondo, item);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					listaResumenFondos.add(fondo);
				}

				List<ResumenCuentaInversionesEntity> clonedListaFondos = new ArrayList<ResumenCuentaInversionesEntity>(
						listaResumenFondos);
				depurarListaFondo(clonedListaFondos);

				return listaResumenFondos;
			} catch (DAOException e) {
				System.out.println(e);
				return listaResumenFondos;
			}
		} if ((espeTipo.equals("MON") || espeTipo.equals("CEF") || espeTipo.equals("SHS") || espeTipo.equals("BON") )&& Integer.parseInt(anio) > ANIO_CAMBIO) {
			List<DatosDetallesRespuestaTituloEntity> tituloList = new ArrayList<DatosDetallesRespuestaTituloEntity>();
			tituloList = obtenerDetallesTitulos(anio, nup,espeTipo);
			if (tituloList != null) {
				for (DatosDetallesRespuestaTituloEntity item : tituloList) {
					ResumenCuentaInversionesEntity titulo = new ResumenCuentaInversionesEntity();
					if (espeTipo.equals(item.getTipo_especie())) {
						setTitulosDetalles(titulo, item);
						listaResumenInversion.add(titulo);
					}
				}
			}
			
		
			return listaResumenInversion;
		}else {

			List<SqlParameter> parametros = new ArrayList<SqlParameter>();
			parametros.add(new SqlOutParameter(IN_OUT_SALIDA_MINV, OracleTypes.CURSOR,
					new ResultSetExtractor<List<ResumenCuentaInversionesEntity>>() {
						@Override
						public List<ResumenCuentaInversionesEntity> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							List<ResumenCuentaInversionesEntity> resumenCuentaInversionesList = new ArrayList<ResumenCuentaInversionesEntity>();
							while (rs.next()) {

								ResumenCuentaInversionesEntity resumenCuentaInversiones = new ResumenCuentaInversionesEntity();
								setResumenCuentaInversionesEntity(resumenCuentaInversiones, rs);
								resumenCuentaInversionesList.add(resumenCuentaInversiones);
							}

							return resumenCuentaInversionesList;
						}

						private void setResumenCuentaInversionesEntity(
								ResumenCuentaInversionesEntity resumenCuentaInversiones, ResultSet rs)
								throws SQLException {
							resumenCuentaInversiones.setEspeTipo(rs.getString(ESPE_TIPO));
							resumenCuentaInversiones.setEspeCod(rs.getString(ESPE_COD));
							resumenCuentaInversiones.setCuenta(rs.getString(CUENTA_M));
							resumenCuentaInversiones.setFecha(rs.getString(FECHA));
							if (rs.getString(CONCEPTO).equals("DIVIDENDOS")) {
								resumenCuentaInversiones.setConcepto("Amort./Renta");
							} else {
								resumenCuentaInversiones.setConcepto(rs.getString(CONCEPTO));
							}
							resumenCuentaInversiones.setComprobante(rs.getString(COMPROBANTE));
							resumenCuentaInversiones.setCantidad(rs.getString(CANTIDAD));
							resumenCuentaInversiones.setCotizacion(rs.getString(COTIZACION));
							resumenCuentaInversiones.setImporte(rs.getString(IMPORTE));
							if (rs.getString(CONCEPTO).equals("Saldo Actual")
									|| rs.getString(CONCEPTO).equals("Saldo Anterior")) {
								// resumenCuentaInversiones.setNroCtaOri(rs.getString(NRO_CTA_ORI));
							} else {
								resumenCuentaInversiones.setNroCtaOri(rs.getString(NRO_CTA_ORI));
							}
							resumenCuentaInversiones.setSucCtaOri(rs.getString(SUC_CTA_ORI));
							resumenCuentaInversiones.setTipCtaOri(rs.getString(TIPO_CTA_ORI));

							if (rs.getString(ESPE_TIPO).equals("BON")) {
								resumenCuentaInversiones.setDivisa("");
							} else {
								resumenCuentaInversiones.setDivisa(rs.getString(DIVISA_M));
							}

						}

					}));

			parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
			parametros.add(new SqlParameter(P_ANIO, Types.VARCHAR));
			parametros.add(new SqlParameter(IN_ESPE_TIPO, Types.VARCHAR));
			parametros.add(new SqlOutParameter(DIFERENCIA, Types.NUMERIC));

			SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup).addValue(P_ANIO, anio)
					.addValue(IN_ESPE_TIPO, espeTipo);

			try {
				Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE,
						RESUMEN_CUENTA_INVERSIONES_PROCEDURE, in,
						parametros.toArray(new SqlParameter[parametros.size()]));
				@SuppressWarnings("unchecked")
				List<ResumenCuentaInversionesEntity> resp = (List<ResumenCuentaInversionesEntity>) respuesta
						.get(IN_OUT_SALIDA_MINV);
				return resp;

			} catch (SQLException sqle) {
				LOGGER.error(sqle.getMessage(), sqle);
				throw new DAOException(sqle);
			} catch (RuntimeException ex) {
				LOGGER.error(ex.getMessage(), ex);
				throw new DAOException(ex);
			}

		}

	}

	private void depurarListaFondo(List<ResumenCuentaInversionesEntity> listaFondos) {
		List<String> fondosExc = Arrays.asList(FONDOS_EXCLUIDOS.split(","));

		Set<String> cuentas = new HashSet<String>();
		for (ResumenCuentaInversionesEntity fondo : listaFondos) {
			cuentas.add(fondo.getCuenta());
		}

		for (String cuenta : cuentas) {
			Set<String> codFondos = new HashSet<String>();

			for (ResumenCuentaInversionesEntity fondo : listaFondos) {

				if (fondo.getCuenta().equals(cuenta)) {

					if (codFondos.add(fondo.getEspeCod())) {
						Map<String, String> mapFondoConcepto = new HashMap<String, String>();
						for (ResumenCuentaInversionesEntity itemFondo : listaFondos) {

							if (itemFondo.getCuenta().equals(cuenta)
									&& itemFondo.getEspeCod().equals(fondo.getEspeCod())) {

								mapFondoConcepto.put(itemFondo.getConcepto(), itemFondo.getEspeCod());

							}
						}

						if (mapFondoConcepto.get("Saldo Anterior") != null) {

						} else {

							ResumenCuentaInversionesEntity saldoAnterior = new ResumenCuentaInversionesEntity();
							boolean isExcluido = false;

							for (String fondoExc : fondosExc) {
								if (fondoExc.equals(mapFondoConcepto.get("Saldo Actual"))) {
									isExcluido = true;
								}
							}

							saldoAnterior.setCantidad("0,0000");
							saldoAnterior.setConcepto("Saldo Anterior");
							saldoAnterior.setEspeCod(mapFondoConcepto.get("Saldo Actual"));
							if (saldoAnterior.getEspeCod().isEmpty()) {
								String espeCod = "";
								if (mapFondoConcepto.get("Suscripción") != null) {
									espeCod = mapFondoConcepto.get("Suscripción");
								} if (mapFondoConcepto.get("Rescate") != null) {
									espeCod = mapFondoConcepto.get("Rescate");
								} else {
									espeCod = mapFondoConcepto.get("Saldo Anterior");
								}
								saldoAnterior.setEspeCod(espeCod);
							}
							saldoAnterior.setEspeTipo("FCR");
							saldoAnterior.setCuenta(cuenta);
							saldoAnterior.setComprobante("");
							saldoAnterior.setGastoEntrada("0");
							saldoAnterior.setGastoSalida("0");

							int anioParseado = Integer.parseInt(anioForFondo) - 1;

							if (isExcluido) {
								saldoAnterior.setImporte("0");
								saldoAnterior.setCotizacion("0");
							} else {

								try {
									saldoAnterior.setImporte("0,00");
									saldoAnterior.setCotizacion(obtenerValorCuotaparte(
											mapFondoConcepto.get("Saldo Actual"), anioParseado + "-12-31"));
								} catch (DAOException e) {
									saldoAnterior.setCotizacion("0,000000");
									e.printStackTrace();
								}

							}
							saldoAnterior.setDivisa("");
							saldoAnterior.setFecha("31/12/" + anioParseado);
							saldoAnterior.setFecha_liq("31/12/" + anioParseado);
							saldoAnterior.setNroCtaOri("");
							saldoAnterior.setSucCtaOri("");
							saldoAnterior.setTipCtaOri("");

							listaResumenFondos.add(0, saldoAnterior);

						}

						if (mapFondoConcepto.get("Saldo Actual") != null) {

						} else {

							ResumenCuentaInversionesEntity saldoActual = new ResumenCuentaInversionesEntity();
							saldoActual.setCantidad("0,0000");
							saldoActual.setConcepto("Saldo Actual");
							String espeCod = "";
							if (mapFondoConcepto.get("Suscripción") != null) {
								espeCod = mapFondoConcepto.get("Suscripción");
							} if (mapFondoConcepto.get("Rescate") != null) {
								espeCod = mapFondoConcepto.get("Rescate");
							} else {
								espeCod = mapFondoConcepto.get("Saldo Anterior");
							}
							saldoActual.setEspeCod(espeCod);
							saldoActual.setEspeTipo("FCR");
							saldoActual.setCuenta(cuenta);
							saldoActual.setComprobante("");
							int anioParseado = Integer.parseInt(anioForFondo);
							try {
								saldoActual.setCotizacion(obtenerValorCuotaparte(espeCod, anioParseado + "-12-31"));
							} catch (DAOException e) {
								saldoActual.setCotizacion("0,000000");
								e.printStackTrace();
							}
							saldoActual.setDivisa("");
							saldoActual.setFecha("31/12/" + anioParseado);
							saldoActual.setFecha_liq("31/12/" + anioParseado);
							saldoActual.setImporte("0,00");
							saldoActual.setNroCtaOri("");
							saldoActual.setSucCtaOri("");
							saldoActual.setTipCtaOri("");
							saldoActual.setGastoEntrada("0");
							saldoActual.setGastoSalida("0");

							listaResumenFondos.add(saldoActual);

						}
					}

				}

			}

		}

	}
	
	private void setTitulosDetalles(ResumenCuentaInversionesEntity titulo, DatosDetallesRespuestaTituloEntity datos) {

		titulo.setEspeTipo(datos.getTipo_especie().trim());
		titulo.setCantidad(datos.getCantidad().trim());
		titulo.setComprobante(datos.getComprobante());
		titulo.setConcepto(datos.getConcepto());
		titulo.setCotizacion(datos.getCotizacion().trim());
		titulo.setCuenta(datos.getCuentaTitulos());
		titulo.setDivisa(datos.getMonedaEmision().trim());
		titulo.setEspeCod(datos.getCodEspecie().trim());
		titulo.setFecha(datos.getFechaConcert().substring(0, 10));
		titulo.setGastoEntrada(datos.getGastos()==null ? "0":datos.getGastos());
		titulo.setImporte(datos.getImporteNeto()==null ? "0":datos.getImporteNeto());
		titulo.setImporteBruto(datos.getImporteBruto()==null ? "0":datos.getImporteBruto());
		titulo.setImpuestos(datos.getImpuestos());
		titulo.setNroBoleto(datos.getNroBoleto());
		titulo.setNroCtaOri(datos.getCuentaOper());
		titulo.setTipCtaOri(datos.getTipoCtaOper());
		titulo.setSucCtaOri(datos.getSucCtaOper());
		titulo.setNroOrden(datos.getNroOrden());
		titulo.setMonedaTransaccion(datos.getMonedaTrx());
		titulo.setAmortizacion(datos.getAmortizacion());
		titulo.setRenta(datos.getRenta());

	}

	private void setResumenCuentaInversionesEntity(ResumenCuentaInversionesEntity fondo, DatosRespuestaFondosRimp item)
			throws ParseException {
		List<String> fondosExc = Arrays.asList(FONDOS_EXCLUIDOS.split(","));
		boolean isExcluido = false;
		for (String fondoExc : fondosExc) {
			if (fondoExc.equals(item.getEspecie())) {
				isExcluido = true;
			}
		}
		if (isExcluido) {
			fondo.setImporte("0");
			fondo.setCotizacion("0");
			fondo.setDivisa("");
		} else {
			fondo.setImporte(item.getImporte());
			fondo.setCotizacion(item.getCotizacion());
			fondo.setDivisa(item.getDivisa());
		}
		fondo.setCantidad(item.getCantidad());
		fondo.setComprobante(item.getComprobante());
		fondo.setConcepto(item.getConcepto());
		fondo.setCuenta(item.getCuenta());

		fondo.setEspeCod(item.getEspecie());
		fondo.setEspeTipo("FCR");
		if (item.getFecha()!=null) {
		fondo.setFecha(item.getFecha().substring(8, 10) + "/" + item.getFecha().substring(5, 7) + "/"
				+ item.getFecha().substring(0, 4));
		} else {
			throw new ParseException(anioForFondo, 0);
		}

		if (item.getCuentaOperativa().equals("-")) {
			fondo.setNroCtaOri(item.getCuentaOperativa());
		} else if (!item.getCuentaOperativa().equals("-") && Integer.parseInt(item.getCuentaOperativa()) > 0) {
			fondo.setNroCtaOri(item.getCuentaOperativa());
		}

		fondo.setSucCtaOri(item.getSucursal());
		fondo.setTipCtaOri("-");
		fondo.setGastoEntrada(String.valueOf(item.getGastoEntrada()));
		fondo.setGastoSalida(String.valueOf(item.getGastoSalida()));

		fondo.setFecha_liq(item.getFecha_liq().substring(8,10) + "/"+ item.getFecha_liq().substring(5,7)+"/"
		+item.getFecha_liq().substring(0,4));
	}
	
	public void setDetallePlazoFijo(DetallePlazoFijoEntity outpf, DatosDetallesPlazoFijoResponseEntity response) {

		outpf.setAccionAlVencimiento(response.getAccionAlVencimiento());
		outpf.setAnio(response.getFechaConstitucion().substring(6, 10));
		outpf.setCapitalInvertido(response.getCapitalInvertido());
		outpf.setCertificado(response.getNroPlazoFijo());
		outpf.setPenumcon(response.getComprobante());
		outpf.setCuentaInversor(response.getCuentaInversor());
		outpf.setClausulaAjuste(response.getClausulaAjuste());
		outpf.setCuentaCreditoNro(response.getCuentaOrigenDestino());
		outpf.setCuentaCreditoSuc(response.getSurcursal());
		outpf.setCuentaCreditoTipo(response.getTipoCuenta());
		outpf.setCuentaDebitoNro(response.getCuentaOrigenDestino());
		outpf.setCuentaDebitoSuc(response.getSurcursal());
		outpf.setCuentaDebitoTipo(response.getTipoCuenta());
		outpf.setCustodia(response.getCustodia());
		outpf.setDivisa(response.getMoneda());
		outpf.setEstado(response.getEstado());
	
		outpf.setFechaConstitucion(response.getFechaConstitucion().substring(0, 10));
		outpf.setFechaLiquidacion(response.getFechaLiquidacion().substring(0,10));
		if (response.getFechaMinimaCancelacion()!=null) {
		outpf.setFechaMinimaCancelacion(response.getFechaMinimaCancelacion().substring(0,10));
		}
		
		outpf.setFechaSolicitudCancelacion(response.getFechaSolicitudCancelacion().substring(0,10));
		outpf.setFechaVencimiento(response.getFechaVencimiento().substring(0,10));
		outpf.setFrecuenciaCobroInteres(response.getFrecuenciaCobroInteres());
		outpf.setFrecuenciaPrecancelacion(response.getFrecuenciaPrecancelacion());
		outpf.setImpuestos(String.valueOf(response.getImpuestos()));
		outpf.setIntCobrado(String.valueOf(response.getInteresesPercibidos()));
		outpf.setIntDevengado(String.valueOf(response.getInteresesDevengados()));
		outpf.setInteresesTotales(String.valueOf(response.getInteresesTotales()));
		outpf.setModalidad(response.getModalidad());
		outpf.setParticipante(response.getParticipante());
		outpf.setPecodofi(response.getSurcursal());
		outpf.setPecodpro(response.getProducto());
		outpf.setPecodsub(response.getSubproducto());
		outpf.setPenumcon(response.getComprobante());
		outpf.setPlazo(response.getPlazo());
		outpf.setTasaEfectivaAnual(response.getTasaEfectivaAnual());
		outpf.setTasaNominalAnual(response.getTasaNominalAnual());
		outpf.setTipo(response.getCodPlazoFijo());
		//outpf.setNombrePlazoFijo(response.getTipoPF());
		outpf.setNombrePlazoFijo(response.getNombrePF());
		outpf.setAjuCobrado("0");
		outpf.setAjuDevengado("0,00");
		
	}
	
	public List<DatosParticipantesEntity> consultarFirmantesPL(String anio, String nup) throws DAOException {
		List<DatosParticipantesEntity> lParticipantes = new ArrayList<DatosParticipantesEntity>();
		List<DatosParticipantesEntity> dParticipantes = new ArrayList<DatosParticipantesEntity>();
		
		try {
			dParticipantes =  obtenerParticipantes(anio, nup).getDatos();
			for(DatosParticipantesEntity item: dParticipantes) {
				
				lParticipantes.add(item);
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	

		return lParticipantes;

	}
	
	public ParticipantesResponseEntity obtenerParticipantes(String anio, String nup) throws DAOException {

		ParticipantesRequestEntity request = armarRequestParticiantes(anio, nup);
		ParticipantesResponseEntity rta = new ParticipantesResponseEntity();
		WebClient miservice = crearLlamadaAWebService(OBTENER_PARTICIPANTES);
		rta = miservice.post(request, ParticipantesResponseEntity.class);
		if (rta == null) {
			LOGGER.error("Error al invocar Consulta de Perfil Inversor: rta null");
			throw new DAOException();
		}
		return rta;
	}
	
	public ParticipantesRequestEntity armarRequestParticiantes(String anio, String nup) {

		ParticipantesRequestEntity request = new ParticipantesRequestEntity();
		DatosDetallesParticipantesRequestEntity datos = new DatosDetallesParticipantesRequestEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();

		datos.setAnio(anio);
		datos.setNup(nup);
		CabeceraOrdenesTitulosEntity encabezado = new CabeceraOrdenesTitulosEntity();
		
		encabezado = CabeceraOrdenesTitulosEntity.generarCabeceraRequest(sesionCliente.getCliente());

		datosServicios.setCanalId(encabezado.getCanalId());
		datosServicios.setCanalTipo(encabezado.getCanalTipo());
		datosServicios.setCanalVersion(encabezado.getCanalVer());
		datosServicios.setFechaNac(encabezado.getFechaNac());
		datosServicios.setSubcanalTipo(encabezado.getSubCanalTipo());
		datosServicios.setSubcanalId(encabezado.getSubCanalId());
		datosServicios.setUsuarioTipo(encabezado.getUsuarioTipo());
		datosServicios.setUsuarioId(encabezado.getUsuarioId());
		datosServicios.setUsuarioAtrib(encabezado.getUsuarioAttr());
		datosServicios.setUsuarioPwd(encabezado.getUsuarioPwd());
		datosServicios.setTipoPersona(encabezado.getTipoCliente());
		datosServicios.setTipoId(encabezado.getTipoIdCliente());
		datos.setDatosServicios(datosServicios);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setDato(dato);
		request.setDatos(datos);
		request.setFirma(generarFirma());

		return request;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerDetalleCuotasPrestamos(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleCuotasPrestamosEntity> obtenerDetalleCuotasPrestamos(String nup, String anio)
			throws DAOException {
		LOGGER.info(LOG_PROCEDURE, CUOTAS_PRESTAMOS_PROCEDURE, nup, anio);

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_PRC, OracleTypes.CURSOR,
				new ResultSetExtractor<List<DetalleCuotasPrestamosEntity>>() {
					@Override
					public List<DetalleCuotasPrestamosEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<DetalleCuotasPrestamosEntity> detalleCuotasPrestamosList = new ArrayList<DetalleCuotasPrestamosEntity>();
						while (rs.next()) {
							DetalleCuotasPrestamosEntity detalleCuotasPrestamos = new DetalleCuotasPrestamosEntity();
							setDetalleCuotasPrestamosEntity(detalleCuotasPrestamos, rs);

							detalleCuotasPrestamosList.add(detalleCuotasPrestamos);
						}
						return detalleCuotasPrestamosList;
					}

					private void setDetalleCuotasPrestamosEntity(DetalleCuotasPrestamosEntity detalleCuotasPrestamos,
							ResultSet rs) throws SQLException {
						detalleCuotasPrestamos.setAnio(rs.getString(ANIO));
						detalleCuotasPrestamos.setCuenta(rs.getString(CUENTA));
						detalleCuotasPrestamos.setPecodpro(rs.getString(PECODPRO));
						detalleCuotasPrestamos.setPecodsub(rs.getString(PECODSUB));
						detalleCuotasPrestamos.setPecodofi(rs.getString(PECODOFI));
						detalleCuotasPrestamos.setNroRecibo(rs.getString(NRO_RECIBO));
						detalleCuotasPrestamos.setFechaVto(rs.getString(FECHA_VTO));
						detalleCuotasPrestamos.setEstado(rs.getString(ESTADO));
						detalleCuotasPrestamos.setSdoAntSinAjustar(rs.getString(SDO_ANT_SIN_AJUSTAR));
						detalleCuotasPrestamos.setImporteCuota(rs.getString(IMPORTE_CUOTA));
						detalleCuotasPrestamos.setCapital(rs.getString(CAPITAL));
						detalleCuotasPrestamos.setIntereses(rs.getString(INTERESES));
						detalleCuotasPrestamos.setGastos(rs.getString(GASTOS));
						detalleCuotasPrestamos.setComisiones(rs.getString(COMISIONES));
						detalleCuotasPrestamos.setSeguros(rs.getString(SEGUROS));
						detalleCuotasPrestamos.setImpuestos(rs.getString(IMPUESTOS));
						detalleCuotasPrestamos.setIntComp(rs.getString(INT_COMP));
						detalleCuotasPrestamos.setIntMora(rs.getString(INT_MORA));
						detalleCuotasPrestamos.setTna(rs.getString(TNA));
					}
				}));

		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ANIO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup)
				// .addValue(IN_ANIO_DESDE, anio)
				.addValue(P_ANIO, anio);

		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, CUOTAS_PRESTAMOS_PROCEDURE,
					in, parametros.toArray(new SqlParameter[parametros.size()]));

			return (List<DetalleCuotasPrestamosEntity>) respuesta.get(IN_OUT_SALIDA_PRC);

		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerDetalleMensualImpuestos(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleMensualImpuestosEntity> obtenerDetalleMensualImpuestos(String nup, String anio)
			throws DAOException {
		LOGGER.info(LOG_PROCEDURE, DETALLE_IMP_PROCEDURE, nup, anio);

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_IMPDET, OracleTypes.CURSOR,
				new ResultSetExtractor<List<DetalleMensualImpuestosEntity>>() {
					@Override
					public List<DetalleMensualImpuestosEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<DetalleMensualImpuestosEntity> detalleMensualImpuestosList = new ArrayList<DetalleMensualImpuestosEntity>();
						while (rs.next()) {
							DetalleMensualImpuestosEntity detalleMensualImpuestos = new DetalleMensualImpuestosEntity();
							setDetalleMensualImpuestosEntity(detalleMensualImpuestos, rs);

							detalleMensualImpuestosList.add(detalleMensualImpuestos);
						}
						return detalleMensualImpuestosList;
					}

					private void setDetalleMensualImpuestosEntity(DetalleMensualImpuestosEntity detalleMensualImpuestos,
							ResultSet rs) throws SQLException {
						BigDecimal aux;
						BigDecimal totalImpCredito = new BigDecimal(0);
						BigDecimal totalImpDebito = new BigDecimal(0);
						BigDecimal totalImpComputable = new BigDecimal(0);
						detalleMensualImpuestos.setAnio(rs.getString(ANIO));
						detalleMensualImpuestos.setTipoCuenta(rs.getString(TIPO_CUENTA));
						detalleMensualImpuestos.setPecodofi(rs.getString(PECODOFI));
						detalleMensualImpuestos.setCuenta(rs.getString(CUENTA));
						detalleMensualImpuestos.setDivisa(rs.getString(DIVISA));
						detalleMensualImpuestos.setAlicuota(rs.getString(ALICUOTA));
						aux = rs.getBigDecimal(IMP_DEBITO_01);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito01(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_02);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito02(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_03);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito03(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_04);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito04(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_05);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito05(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_06);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito06(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_07);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito07(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_08);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito08(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_09);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito09(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_10);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito10(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_11);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito11(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_12);
						totalImpDebito = totalImpDebito.add(aux);
						detalleMensualImpuestos.setImpDebito12(InversionesHelper.formatearSaldo4Decimales(aux));
						detalleMensualImpuestos
								.setTotalImpDebito(InversionesHelper.formatearSaldo4Decimales(totalImpDebito));
						aux = rs.getBigDecimal(IMP_CREDITO_01);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito01(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_02);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito02(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_03);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito03(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_04);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito04(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_05);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito05(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_06);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito06(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_07);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito07(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_08);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito08(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_09);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito09(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_10);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito10(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_11);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito11(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_12);
						totalImpCredito = totalImpCredito.add(aux);
						detalleMensualImpuestos.setImpCredito12(InversionesHelper.formatearSaldo4Decimales(aux));
						detalleMensualImpuestos
								.setTotalImpCredito(InversionesHelper.formatearSaldo4Decimales(totalImpCredito));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_01);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable01(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_02);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable02(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_03);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable03(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_04);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable04(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_05);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable05(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_06);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable06(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_07);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable07(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_08);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable08(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_09);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable09(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_10);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable10(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_11);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable11(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_COMPUTABLE_12);
						totalImpComputable = totalImpComputable.add(aux);
						detalleMensualImpuestos.setImpComputable12(InversionesHelper.formatearSaldo4Decimales(aux));
						detalleMensualImpuestos
								.setTotalImpComputable(InversionesHelper.formatearSaldo4Decimales(totalImpComputable));
					}
				}));

		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ANIO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup).addValue(P_ANIO, anio);

		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, DETALLE_IMP_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			return (List<DetalleMensualImpuestosEntity>) respuesta.get(IN_OUT_SALIDA_IMPDET);

		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerDetalleImpuestoMonedaExtranjera(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleImpuestoMonedaExtranjeraEntity> obtenerDetalleImpuestoMonedaExtranjera(String nup, String anio)
			throws DAOException {
		LOGGER.info(LOG_PROCEDURE, DETALLE_MONEDA_EXT_PROCEDURE, nup, anio);

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_IMPME, OracleTypes.CURSOR,
				new ResultSetExtractor<List<DetalleImpuestoMonedaExtranjeraEntity>>() {
					@Override
					public List<DetalleImpuestoMonedaExtranjeraEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<DetalleImpuestoMonedaExtranjeraEntity> detalleImpuestoMonedaExtranjeraList = new ArrayList<DetalleImpuestoMonedaExtranjeraEntity>();
						while (rs.next()) {
							DetalleImpuestoMonedaExtranjeraEntity detalleImpuestoMonedaExtranjera = new DetalleImpuestoMonedaExtranjeraEntity();
							setDetalleImpuestoMonedaExtranjeraEntity(detalleImpuestoMonedaExtranjera, rs);

							detalleImpuestoMonedaExtranjeraList.add(detalleImpuestoMonedaExtranjera);
						}
						return detalleImpuestoMonedaExtranjeraList;
					}

					private void setDetalleImpuestoMonedaExtranjeraEntity(
							DetalleImpuestoMonedaExtranjeraEntity detalleImpuestoMonedaExtranjera, ResultSet rs)
							throws SQLException {
						detalleImpuestoMonedaExtranjera.setAnio(rs.getString(ANIO));
						detalleImpuestoMonedaExtranjera.setTipoCuenta(rs.getString(TIPO_CUENTA));
						detalleImpuestoMonedaExtranjera.setOrigen(rs.getString(ORIGEN));
						detalleImpuestoMonedaExtranjera.setPecodofi(rs.getString(PECODOFI));
						detalleImpuestoMonedaExtranjera.setCuenta(rs.getString(CUENTA));
						detalleImpuestoMonedaExtranjera.setDivisa(rs.getString(DIVISA));
						detalleImpuestoMonedaExtranjera.setAlicuota(rs.getString(ALICUOTA));
						detalleImpuestoMonedaExtranjera.setImpDebito01(rs.getString(IMP_DEBITO_01));
						detalleImpuestoMonedaExtranjera.setImpDebito02(rs.getString(IMP_DEBITO_02));
						detalleImpuestoMonedaExtranjera.setImpDebito03(rs.getString(IMP_DEBITO_03));
						detalleImpuestoMonedaExtranjera.setImpDebito04(rs.getString(IMP_DEBITO_04));
						detalleImpuestoMonedaExtranjera.setImpDebito05(rs.getString(IMP_DEBITO_05));
						detalleImpuestoMonedaExtranjera.setImpDebito06(rs.getString(IMP_DEBITO_06));
						detalleImpuestoMonedaExtranjera.setImpDebito07(rs.getString(IMP_DEBITO_07));
						detalleImpuestoMonedaExtranjera.setImpDebito08(rs.getString(IMP_DEBITO_08));
						detalleImpuestoMonedaExtranjera.setImpDebito09(rs.getString(IMP_DEBITO_09));
						detalleImpuestoMonedaExtranjera.setImpDebito10(rs.getString(IMP_DEBITO_10));
						detalleImpuestoMonedaExtranjera.setImpDebito11(rs.getString(IMP_DEBITO_11));
						detalleImpuestoMonedaExtranjera.setImpDebito12(rs.getString(IMP_DEBITO_12));
						detalleImpuestoMonedaExtranjera.setImpCredito01(rs.getString(IMP_CREDITO_01));
						detalleImpuestoMonedaExtranjera.setImpCredito02(rs.getString(IMP_CREDITO_02));
						detalleImpuestoMonedaExtranjera.setImpCredito03(rs.getString(IMP_CREDITO_03));
						detalleImpuestoMonedaExtranjera.setImpCredito04(rs.getString(IMP_CREDITO_04));
						detalleImpuestoMonedaExtranjera.setImpCredito06(rs.getString(IMP_CREDITO_05));
						detalleImpuestoMonedaExtranjera.setImpCredito06(rs.getString(IMP_CREDITO_06));
						detalleImpuestoMonedaExtranjera.setImpCredito07(rs.getString(IMP_CREDITO_07));
						detalleImpuestoMonedaExtranjera.setImpCredito08(rs.getString(IMP_CREDITO_08));
						detalleImpuestoMonedaExtranjera.setImpCredito09(rs.getString(IMP_CREDITO_09));
						detalleImpuestoMonedaExtranjera.setImpCredito10(rs.getString(IMP_CREDITO_10));
						detalleImpuestoMonedaExtranjera.setImpCredito11(rs.getString(IMP_CREDITO_11));
						detalleImpuestoMonedaExtranjera.setImpCredito12(rs.getString(IMP_CREDITO_12));
					}
				}));

		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ANIO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup)
				// .addValue(IN_ANIO_DESDE, anio);
				.addValue(P_ANIO, anio);

		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, DETALLE_MONEDA_EXT_PROCEDURE,
					in, parametros.toArray(new SqlParameter[parametros.size()]));

			return (List<DetalleImpuestoMonedaExtranjeraEntity>) respuesta.get(IN_OUT_SALIDA_IMPME);

		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDetalleDAO#
	 * obtenerDetalleTarjetaCredito(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleTarjetaCreditoEntity> obtenerDetalleTarjetaCredito(String nup, String anio) throws DAOException {
		LOGGER.info(LOG_PROCEDURE, DETALLE_TARJETA_CRED_PROCEDURE, nup, anio);

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_TJCDET, OracleTypes.CURSOR,
				new ResultSetExtractor<List<DetalleTarjetaCreditoEntity>>() {
					@Override
					public List<DetalleTarjetaCreditoEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<DetalleTarjetaCreditoEntity> detalleTarjetaCreditoList = new ArrayList<DetalleTarjetaCreditoEntity>();
						while (rs.next()) {
							DetalleTarjetaCreditoEntity detalleTarjetaCreditoEntity = new DetalleTarjetaCreditoEntity();
							setDetalleTarjetaCreditoEntity(detalleTarjetaCreditoEntity, rs);

							detalleTarjetaCreditoList.add(detalleTarjetaCreditoEntity);
						}
						return detalleTarjetaCreditoList;
					}

					private void setDetalleTarjetaCreditoEntity(DetalleTarjetaCreditoEntity detalleTarjetaCreditoEntity,
							ResultSet rs) throws SQLException {
						BigDecimal aux;
						BigDecimal totalImpDebito = new BigDecimal(0);
						BigDecimal totalImpCredito = new BigDecimal(0);
						detalleTarjetaCreditoEntity.setAnio(rs.getString(ANIO));
						detalleTarjetaCreditoEntity.setTipoTarjeta(rs.getString(TIPO_TARJETA));
						detalleTarjetaCreditoEntity.setPecodofi(rs.getString(PECODOFI));
						detalleTarjetaCreditoEntity.setCuenta(rs.getString(CUENTA));
						detalleTarjetaCreditoEntity.setDivisa(rs.getString(DIVISA));
						detalleTarjetaCreditoEntity.setAlicuota(rs.getString(ALICUOTA));
						aux = rs.getBigDecimal(IMP_DEBITO_01);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito01(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_02);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito02(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_03);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito03(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_04);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito04(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_05);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito05(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_06);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito06(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_07);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito07(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_08);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito08(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_09);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito09(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_10);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito10(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_11);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito11(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_DEBITO_12);
						totalImpDebito = totalImpDebito.add(aux);
						detalleTarjetaCreditoEntity.setImpDebito12(InversionesHelper.formatearSaldo4Decimales(aux));
						detalleTarjetaCreditoEntity
								.setTotalImpDebito(InversionesHelper.formatearSaldo4Decimales(totalImpDebito));
						aux = rs.getBigDecimal(IMP_CREDITO_01);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito01(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_02);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito02(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_03);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito03(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_04);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito04(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_05);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito05(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_06);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito06(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_07);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito07(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_08);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito08(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_09);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito09(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_10);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito10(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_11);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito11(InversionesHelper.formatearSaldo4Decimales(aux));
						aux = rs.getBigDecimal(IMP_CREDITO_12);
						totalImpCredito = totalImpCredito.add(aux);
						detalleTarjetaCreditoEntity.setImpCredito12(InversionesHelper.formatearSaldo4Decimales(aux));
						detalleTarjetaCreditoEntity
								.setTotalImpCredito(InversionesHelper.formatearSaldo4Decimales(totalImpCredito));

					}
				}));

		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_ANIO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup)
				// .addValue(IN_ANIO_DESDE, anio);
				.addValue(P_ANIO, anio);

		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE,
					DETALLE_TARJETA_CRED_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));

			return (List<DetalleTarjetaCreditoEntity>) respuesta.get(IN_OUT_SALIDA_TJCDET);

		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}

	}

	public DetallePlazoFijoResponseEntity obtenerDetallesPlazoFijo(String anio, String nup) throws DAOException{

		DetallePlazoFijoRequestEntity request = armarRequestPF(anio, nup);
		DetallePlazoFijoResponseEntity rta = new DetallePlazoFijoResponseEntity();
		WebClient miservice = crearLlamadaAWebService(pathObtenerMovimientosDetallePF);
		rta = miservice.post(request, DetallePlazoFijoResponseEntity.class);
		return rta;
	}
	
	private List<DatosDetallesRespuestaTituloEntity> obtenerDetallesTitulos(String anio, String nup,String espeTipo) {
		
	
		DatosTitulosRequestEntity datos = new DatosTitulosRequestEntity();
		DetallesTitulosRequetsEntity request = new DetallesTitulosRequetsEntity();
		datos.setAnio(anio);
		datos.setNup(nup);
		datos.setEspeTipo(espeTipo);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		request.setCanal("04");
		request.setDato("Prueba");
		request.setFirma(generarFirma());
		request.setDatos(datos);
		
		DetalleTitulosResponseEntity respuesta = new DetalleTitulosResponseEntity();
		WebClient miService;
		try {
			miService = crearLlamadaAWebService(PATH_TITULO_DETALLE);
			respuesta = miService.post(request,DetalleTitulosResponseEntity.class);
			if (Integer.parseInt(respuesta.getCodigo())!=0) {
				LOGGER.error("Error al invocar Titulos Detalles Response	");
				throw new DAOException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		return respuesta.getDatos();
		
	}

	public DetalleFondosRimpEntity obtenerDetallesFondos(String anio, String nup) throws DAOException {

		DetalleFondoRimpRequestEntity request = armarRequest(anio, nup);
		DetalleFondosRimpEntity rta = new DetalleFondosRimpEntity();
		WebClient miservice = crearLlamadaAWebService(pathObtenerMovimientosFondosPorNup);
		rta = miservice.post(request, DetalleFondosRimpEntity.class);
		return rta;

	}
	
	public String obtenerValorCuotaparte(String espeCod, String fecha) throws DAOException {

		ValorCuotaParteRequestEntity request = armarRequestCuota(espeCod, fecha);
		ValorCuotaParteRespEntity rta = new ValorCuotaParteRespEntity();
		WebClient miservice = crearLlamadaAWebService(pathValorCuotaparte);
		rta = miservice.post(request, ValorCuotaParteRespEntity.class);
		if (rta.getDatos().getValorCuotaParte() != null) {
			return rta.getDatos().getValorCuotaParte();
		} else {
			return "0,000000";
		}

	}

	private ValorCuotaParteRequestEntity armarRequestCuota(String espeCod, String fecha) {

		ValorCuotaParteRequestEntity request = new ValorCuotaParteRequestEntity();
		DatosDetalleCuotaparteRequestEntity datos = new DatosDetalleCuotaparteRequestEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datos.setDatosServicios(cargarDatosDefault(datosServicios));
		datos.setEspeCod(espeCod);
		datos.setFecha(fecha);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());
		return request;
	}

	private DatosServiciosEntity cargarDatosDefault(DatosServiciosEntity datosServicios) {
		CabeceraOrdenesTitulosEntity encabezado = new CabeceraOrdenesTitulosEntity();

		encabezado = CabeceraOrdenesTitulosEntity.generarCabeceraRequest(sesionCliente.getCliente());

		datosServicios.setCanalId(encabezado.getCanalId());
		datosServicios.setCanalTipo(encabezado.getCanalTipo());
		datosServicios.setCanalVersion(encabezado.getCanalVer());
		datosServicios.setFechaNac(encabezado.getFechaNac());
		datosServicios.setSubcanalTipo(encabezado.getSubCanalTipo());
		datosServicios.setSubcanalId(encabezado.getSubCanalId());
		datosServicios.setUsuarioTipo(encabezado.getUsuarioTipo());
		datosServicios.setUsuarioId(encabezado.getUsuarioId());
		datosServicios.setUsuarioAtrib(encabezado.getUsuarioAttr());
		datosServicios.setUsuarioPwd(encabezado.getUsuarioPwd());
		datosServicios.setTipoPersona(encabezado.getTipoCliente());
		datosServicios.setTipoId(encabezado.getTipoIdCliente());
		return datosServicios;
	}

	private DetalleFondoRimpRequestEntity armarRequest(String anio, String nup) {

		DetalleFondoRimpRequestEntity request = new DetalleFondoRimpRequestEntity();
		DatosDetalleFondoRimpRequestEntity datos = new DatosDetalleFondoRimpRequestEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();

		String fechaInicio = anio + "-01-01";
		String fechaFin = anio + "-12-31";
		datos.setDatosServicios(cargarDatosDefault(datosServicios));
		datos.setFechaFin(fechaFin);
		datos.setFechaInicio(fechaInicio);
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());

		return request;
	}
	
	private DetallePlazoFijoRequestEntity armarRequestPF(String anio, String nup) {

		DetallePlazoFijoRequestEntity request = new DetallePlazoFijoRequestEntity();
		DatosDetallesPlazoFijoRequestEntity datos = new DatosDetallesPlazoFijoRequestEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();

		datos.setDatosServicios(cargarDatosDefault(datosServicios));
		datos.setAnio(anio);
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());

		return request;
	}

	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS_RIMP);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);

		return service;
	}

	/**
	 * @param null
	 * 
	 * @return firma
	 */
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma", e);
			}
		}
		return firma;
	}

	/**
	 * Consulta servicio ConsultaHabilitacionCompraVentaBonos
	 */
	@Override
	public DatosRespuestaHabilitaCompraVentaUSDEntity consultarHabilitacionCompraVtaUSD(String nup) throws DAOException {
		WebClient miservice = crearLlamadaAWebService(pathConsularHabilitaCompraVetaBonos);
		DatosRespuestaHabilitaCompraVentaUSDEntity rta = miservice.post(armarRequestCnsHabilitaCompraVtaUSD(nup), DatosRespuestaHabilitaCompraVentaUSDEntity.class);
		if (rta.getDatos() != null) {
			return rta;
		} else {
			throw new DAOException();
		}
	}

	/**
	 * Arma el request para invocar al servicio ConsultaHabilitacionCompraVentaBonos
	 * @param nup
	 * @return
	 */
	private DatosRequestHabilitaCompraVentaUSDEntity armarRequestCnsHabilitaCompraVtaUSD(String nup) {
		DatosRequestHabilitaCompraVentaUSDEntity request = new DatosRequestHabilitaCompraVentaUSDEntity();
		DatosHabilitaCompraVentaUSDEntity datos = new DatosHabilitaCompraVentaUSDEntity();

		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario("system");
		request.setDatos(datos);
		request.setDato(dato);
		request.setFirma(generarFirma());

		return request;
	}

}
