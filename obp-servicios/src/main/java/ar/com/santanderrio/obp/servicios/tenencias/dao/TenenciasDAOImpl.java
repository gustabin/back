/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
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
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoDescripcionEnum;
import ar.com.santanderrio.obp.servicios.tenencias.entity.CuentasEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.CustodiaEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosDetallesPlazoFijoRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosResumenFondosRimptEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallePlazoFijoRequestEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallesTitulosRequetsEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmanteEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantePrestamoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesOutPLEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosPendientesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosRimpEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ImpuestoMonedaExtranjeraEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ImpuestosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.PlazoFijoResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.PrestamosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.RendimientoFondosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TitulosValoresResponseEntity;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondosResponseEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class TenenciasDAOImpl.
 */
@Component
@TargetSystem(DataBase.IMPUESTOS)
public class TenenciasDAOImpl extends BaseDatoDAOImpl implements TenenciasDAO {

	private static final int ANIO_PL = 2019;

	private static final String SEGMENTO_RTL = "RTL";

	private static final String SEGMENTO_BP = "BP";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasDAOImpl.class);

	/** SCHEMA. */
	private static final String TENENCIAS_SCHEMA = "VIR03";
	/** PACKAGE Tenencias. */
	private static final String TENENCIAS_PACKAGE = "HB_CONS";
	/** PROCEDURE Tenencias. */
	private static final String TENENCIAS_PROCEDURE = "PRIN";
	/** The Constant IN_OUT_SALIDA_CUENTAS. */
	private static final String IN_OUT_SALIDA_CUENTAS = "cue_cv";
	/** The Constant IN_OUT_SALIDA_CUSTODIA. */
	private static final String IN_OUT_SALIDA_CUSTODIA = "cus_cv";
	/** The Constant IN_OUT_SALIDA_FONDOS. */
	private static final String IN_OUT_SALIDA_FONDOS = "fco_cv";
	/** The Constant IN_OUT_SALIDA_FONDOS. */
	private static final String IN_OUT_SALIDA_FCT = "fct_cv";
	/** The Constant IN_OUT_SALIDA_FONDOS_PENDIENTES. */
	private static final String IN_OUT_SALIDA_FONDOS_PENDIENTES = "fre_cv";
	/** The Constant IN_OUT_SALIDA_IMPUESTOS. */
	private static final String IN_OUT_SALIDA_IMPME = "impme_cv";
	/** The Constant IN_OUT_SALIDA_IMPUESTOS. */
	private static final String IN_OUT_SALIDA_TJC = "tjc_cv";
	/** The Constant IN_OUT_SALIDA_IMPUESTOS. */
	private static final String IN_OUT_SALIDA_IMPUESTOS = "imp_cv";
	/** The Constant IN_OUT_SALIDA_PF. */
	private static final String IN_OUT_SALIDA_PF = "pft_cv";
	/** The Constant IN_OUT_SALIDA_PRESTAMOS. */
	private static final String IN_OUT_SALIDA_PRESTAMOS = "pre_cv";
	/** The Constant IN_NUP. */
	private static final String IN_NUP = "p_nup";
	/** The Constant IN_ANIO_DESDE. */
	private static final String IN_ANIO_DESDE = "p_anio_desde";
	/** The Constant IN_ANIO_HASTA. */
	private static final String IN_ANIO_HASTA = "p_anio_hasta";
	/** The Constant IN_OUT_COTIZACION_AFIP. */
	private static final String OUT_COTIZACION_AFIP = "coti_afip_usd";
	/** The Constant ANIO. */
	private static final String ANIO = "ANIO";
	/** The Constant TIPO_CUENTA. */
	private static final String TIPO_CUENTA = "TIPO_CUENTA";
	/** The Constant PECODOFI. */
	private static final String PECODOFI = "PECODOFI";
	/** The Constant CUENTA. */
	private static final String CUENTA = "CUENTA";
	/** The Constant DIVISA. */
	private static final String DIVISA = "DIVISA";
	/** The Constant SALDO. */
	private static final String SALDO = "SALDO";
	/** The Constant INT_DEVENGADO. */
	private static final String INT_DEVENGADO = "INT_DEVENGADO";
	/** The Constant CBU. */
	private static final String CBU = "CBU";
	/** The Constant DESC_F_OPERAR. */
	private static final String DESC_F_OPERAR = "DESC_F_OPERAR";
	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";
	/** The Constant IMPORTE_INT_DEV. */
	private static final String IMPORTE_INT_DEV = "IMPORTE_INT_DEV";
	/** The Constant ESPE_TIPO. */
	private static final String ESPE_TIPO = "ESPE_TIPO";
	/** The Constant ESPE_NOM. */
	private static final String ESPE_NOM = "ESPE_NOM";
	/** The Constant ESPE_COD. */
	private static final String ESPE_COD = "ESPE_COD";
	/** The Constant VALOR_NOMINAL. */
	private static final String VALOR_NOMINAL = "VALOR_NOMINAL";
	/** The Constant PORC_RESIDUAL. */
	private static final String PORC_RESIDUAL = "PORC_RESIDUAL";
	/** The Constant PRECIO. */
	private static final String PRECIO = "PRECIO";
	/** The Constant COTI_AFIP. */
	private static final String COTI_AFIP = "COTI_AFIP";
	/** The Constant COD_FONDO. */
	private static final String COD_FONDO = "COD_FONDO";
	/** The Constant DESCRIPCION. */
	private static final String DESCRIPCION = "DESCRIPCION";
	/** The Constant SALDO_CUOTAS. */
	private static final String SALDO_CUOTAS = "SALDO_CUOTAS";
	/** The Constant VALOR_CUOTA. */
	private static final String VALOR_CUOTA = "VALOR_CUOTA";
	/** The Constant SUM_RENTA. */
	private static final String SUM_RENTA = "SUM_RENTA";
	/** The Constant ALICUOTA. */
	private static final String ALICUOTA = "ALICUOTA";
	/** The Constant IMP_DEBITO. */
	private static final String IMP_DEBITO = "IMP_DEBITO";
	/** The Constant IMP_CREDITO. */
	private static final String IMP_CREDITO = "IMP_CREDITO";
	/** The Constant TOTAL_cobrado. */
	private static final String TOTAL_COBRADO = "TOTAL_cobrado";
	/** The Constant PORC_computable. */
	private static final String PORC_COMPUTABLE = "PORC_computable";
	/** The Constant IMP_computable. */
	private static final String IMP_COMPUTABLE = "IMP_computable";
	/** The Constant PENUMCON. */
	private static final String PENUMCON = "PENUMCON";
	/** The Constant TIPO. */
	private static final String TIPO = "TIPO";
	/** The Constant ORDEN. */
	private static final String ORDEN = "ORDEN";
	/** The Constant CONCEPTO. */
	private static final String CONCEPTO = "CONCEPTO";
	/** The Constant IMPORTE_PESOS. */
	private static final String IMPORTE_PESOS = "IMPORTE_PESOS";
	/** The Constant PECODPRO. */
	private static final String PECODPRO = "PECODPRO";
	/** The Constant PECODSUB. */
	private static final String PECODSUB = "PECODSUB";
	/** The Constant DESTINO_FONDOS. */
	private static final String DESTINO_FONDOS = "DESTINO_FONDOS";
	/** The Constant DESTINO_DESCRIPCION. */
	private static final String DESTINO_DESCRIPCION = "DESTINO_DESCRIPCION";
	/** The Constant SITUACION. */
	private static final String SITUACION = "SITUACION";
	/** The Constant SALDO_DEUDA. */
	private static final String SALDO_DEUDA = "SALDO_DEUDA";
	/** The Constant SALDO_VENCIDO. */
	private static final String SALDO_VENCIDO = "SALDO_VENCIDO";
	/** The Constant INT_VENCIDO. */
	private static final String INT_VENCIDO = "INT_VENCIDO";
	/** The Constant INT_COBRADO. */
	private static final String INT_COBRADO = "INT_COBRADO";
	/** The Constant PLAZO. */
	private static final String PLAZO = "PLAZO";
	/** The Constant TIPO_PRODUCTO. */
	private static final String TIPO_PRODUCTO = "TIPO_PRODUCTO";
	/** The Constant FECHA_FORMALIZACION. */
	private static final String FECHA_FORMALIZACION = "FECHA_FORMALIZACION";
	/** The Constant ORIGEN. */
	private static final String ORIGEN = "ORIGEN";
	/** The Constant TIPO_TARJETA. */
	private static final String TIPO_TARJETA = "TIPO_TARJETA";
	/** The Constant IMP_NETO. */
	private static final String IMP_NETO = "IMP_NETO";
	/** PROCEDURE Firmantes. */
	private static final String FIRMANTES_PROCEDURE = "FIRDET";

	/** The Constant IN_OUT_FIRMANTE_CUENTA. */
	private static final String IN_OUT_FIRMANTE_CUENTA = "fir_cue_cv";

	/** The Constant IN_OUT_FIRMANTE_CUSTODIA. */
	private static final String IN_OUT_FIRMANTE_CUSTODIA = "fir_cus_cv";

	/** The Constant IN_OUT_FIRMANTE_FONDO. */
	private static final String IN_OUT_FIRMANTE_FONDO = "fir_fco_cv";

	/** The Constant IN_OUT_FIRMANTE_PF. */
	private static final String IN_OUT_FIRMANTE_PF = "fir_pfi_cv";

	/** The Constant IN_OUT_FIRMANTE_PRESTAMOS. */
	private static final String IN_OUT_FIRMANTE_PRE = "fir_pre_cv";

	/** The Constant PEPRIAPE. */
	private static final String PEPRIAPE = "PEPRIAPE";

	/** The Constant PENOMPER. */
	private static final String PENOMPER = "PENOMPER";

	/** The Constant PEITPDOC. */
	private static final String PETIPDOC = "petipdoc";

	/** The Constant PENUMDOC. */
	private static final String PENUMDOC = "PENUMDOC";

	/** The Class SesionCliente */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "INVERSIONES.TENENCIAS";

	/** The Constant OBTENER_SALDO_FONOD. */
	private static final String OBTENER_SALDO_FONOD = "/ObtenerSaldosFondosPorAnio";

	/** The Constant OBTENER_PLAZO_FIJO. */
	private static final String OBTENER_PLAZO_FIJO = "/ObtenerResumenPlazoFijo";
	
	/** The Constant OBTENER_RESUMEN_TITULOS */
	private static String OBTENER_RESUMEN_TITULOS = "/ObtenerResumenTitulosValores";

	/** The Constant MENSAJE_ERROR_1. */
	private static final String MENSAJE_ERROR_1 = "Error en el REsponse Llamando al servicio de consulta de Fondo";

	/** The Constant MENSAJE_ERROR_2. */
	private static final String MENSAJE_ERROR_2 = "Error en el Procesamiento Llamando al servicio de consulta de Fondo";

	/** The Constant MENSAJE_ERROR_3. */
	private static final String MENSAJE_ERROR_3 = "Error en tiempo de ejecucion Llamando al servicio de consulta de Fondo";

	/** The Constant FONDOS_EXCLUIDOS. */
	private static final String FONDOS_EXCLUIDOS = "190,191,238,239,240,241,250,251,255,256,260,261,265,266,270,271,275,276,280,281,286,287,290,291,295,296,300";

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDAO#
	 * obtenerTenecias(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TenenciasOutEntity obtenerTenecias(String nup, String anioDesde, String anioHasta) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con los datos nup: {} - anioDesde: {} - anioHasta: {}.",
				TENENCIAS_PROCEDURE, nup, anioDesde, anioHasta);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_CUENTAS, OracleTypes.CURSOR,
				new ResultSetExtractor<List<CuentasEntity>>() {
					@Override
					public List<CuentasEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CuentasEntity> lstRet = new ArrayList<CuentasEntity>();
						while (rs.next()) {
							CuentasEntity cuentasEntity = parseCuentas(rs);
							lstRet.add(cuentasEntity);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_CUSTODIA, OracleTypes.CURSOR,
				new ResultSetExtractor<List<CustodiaEntity>>() {
					@Override
					public List<CustodiaEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CustodiaEntity> lstRet = new ArrayList<CustodiaEntity>();
						while (rs.next()) {
							CustodiaEntity custodiaEntity = parseCustodia(rs);
							lstRet.add(custodiaEntity);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_FONDOS, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FondosEntity>>() {
					@Override
					public List<FondosEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<FondosEntity> lstRet = new ArrayList<FondosEntity>();
						while (rs.next()) {
							FondosEntity fondosEntity = parseFondos(rs);
							lstRet.add(fondosEntity);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_FCT, OracleTypes.CURSOR,
				new ResultSetExtractor<List<RendimientoFondosEntity>>() {
					@Override
					public List<RendimientoFondosEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<RendimientoFondosEntity> lstRet = new ArrayList<RendimientoFondosEntity>();
						while (rs.next()) {
							RendimientoFondosEntity rendimientoFondosEntity = new RendimientoFondosEntity();
							rendimientoFondosEntity.setAnio(rs.getString(ANIO));
							rendimientoFondosEntity.setCuenta(rs.getString(CUENTA));
							rendimientoFondosEntity.setDivisa(rs.getString(DIVISA));
							rendimientoFondosEntity.setSumRenta(rs.getString(SUM_RENTA));

							lstRet.add(rendimientoFondosEntity);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_FONDOS_PENDIENTES, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FondosPendientesEntity>>() {
					@Override
					public List<FondosPendientesEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<FondosPendientesEntity> lstRet = new ArrayList<FondosPendientesEntity>();
						while (rs.next()) {
							FondosPendientesEntity fondosPendientes = parseFondosPendients(rs);
							lstRet.add(fondosPendientes);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_IMPUESTOS, OracleTypes.CURSOR,
				new ResultSetExtractor<List<ImpuestosEntity>>() {
					@Override
					public List<ImpuestosEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<ImpuestosEntity> lstRet = new ArrayList<ImpuestosEntity>();
						while (rs.next()) {
							ImpuestosEntity impuestos = parseImpuesto(rs);
							lstRet.add(impuestos);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_IMPME, OracleTypes.CURSOR,
				new ResultSetExtractor<List<ImpuestoMonedaExtranjeraEntity>>() {
					@Override
					public List<ImpuestoMonedaExtranjeraEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<ImpuestoMonedaExtranjeraEntity> lstRet = new ArrayList<ImpuestoMonedaExtranjeraEntity>();
						while (rs.next()) {
							ImpuestoMonedaExtranjeraEntity impuestoMonedaExtranjeraEntity = parseImpuestoME(rs);

							lstRet.add(impuestoMonedaExtranjeraEntity);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_TJC, OracleTypes.CURSOR,
				new ResultSetExtractor<List<TarjetasEntity>>() {
					@Override
					public List<TarjetasEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<TarjetasEntity> lstRet = new ArrayList<TarjetasEntity>();
						while (rs.next()) {
							TarjetasEntity tarjetas = new TarjetasEntity();
							tarjetas.setAnio(rs.getString(ANIO));
							tarjetas.setTipoTarjeta(rs.getString(TIPO_TARJETA));
							tarjetas.setPecodofi(rs.getString(PECODOFI));
							tarjetas.setCuenta(rs.getString(CUENTA));
							tarjetas.setDivisa(rs.getString(DIVISA));
							tarjetas.setAlicuota(rs.getString(ALICUOTA));
							tarjetas.setImpDebito(rs.getString(IMP_DEBITO));
							tarjetas.setImpCredito(rs.getString(IMP_CREDITO));
							tarjetas.setImpNeto(rs.getString(IMP_NETO));

							lstRet.add(tarjetas);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_PF, OracleTypes.CURSOR,
				new ResultSetExtractor<List<PlazoFijoEntity>>() {
					@Override
					public List<PlazoFijoEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<PlazoFijoEntity> lstRet = new ArrayList<PlazoFijoEntity>();
						while (rs.next()) {
							PlazoFijoEntity plazoFijo = new PlazoFijoEntity();
							plazoFijo.setAnio(rs.getString(ANIO));
							plazoFijo.setDivisa(rs.getString(DIVISA));
							plazoFijo.setPecodofi(rs.getString(PECODOFI));
							plazoFijo.setPenumcon(rs.getString(PENUMCON));
							plazoFijo.setTipo(rs.getString(TIPO));
							plazoFijo.setOrden(rs.getString(ORDEN));
							plazoFijo.setConcepto(rs.getString(CONCEPTO));
							plazoFijo.setImporte(rs.getString(IMPORTE));
							plazoFijo.setImportePesos(rs.getString(IMPORTE_PESOS));

							lstRet.add(plazoFijo);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_SALIDA_PRESTAMOS, OracleTypes.CURSOR,
				new ResultSetExtractor<List<PrestamosEntity>>() {
					@Override
					public List<PrestamosEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<PrestamosEntity> lstRet = new ArrayList<PrestamosEntity>();
						while (rs.next()) {
							PrestamosEntity prestamos = parsePrestamos(rs);
							lstRet.add(prestamos);
						}
						return lstRet;
					}

				}));
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ANIO_DESDE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ANIO_HASTA, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COTIZACION_AFIP, OracleTypes.NUMBER));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup).addValue(IN_ANIO_DESDE, anioDesde)
				.addValue(IN_ANIO_HASTA, anioHasta);
		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, TENENCIAS_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			TenenciasOutEntity tenencias = new TenenciasOutEntity();
			tenencias.setCuentasEntity((List<CuentasEntity>) respuesta.get(IN_OUT_SALIDA_CUENTAS));
			if (Integer.parseInt(anioDesde) >= ANIO_PL) {
				tenencias.setFondosEntity   ((List<FondosRimpEntity>) obtenerTenenciasFondos(nup, anioDesde).getDatos());
				tenencias.setPlazoFijoEntity((List<PlazoFijoEntity>) obtenerTenenciasPlazoFijo(nup, anioDesde).getDatos());
				List<FondosPendientesEntity> fondosPendientes = new ArrayList<FondosPendientesEntity>();
				tenencias.setFondosPendientesEntity((List<FondosPendientesEntity>) fondosPendientes);
				
				tenencias.setCustodiaEntity((List<CustodiaEntity>) ObtenerResumenTitulos(anioDesde,nup).getDatos());
			} else {
				tenencias.setFondosEntity((List<FondosRimpEntity>) respuesta.get(IN_OUT_SALIDA_FONDOS));
				tenencias.setFondosPendientesEntity(
						(List<FondosPendientesEntity>) respuesta.get(IN_OUT_SALIDA_FONDOS_PENDIENTES));
				tenencias.setPlazoFijoEntity((List<PlazoFijoEntity>) respuesta.get(IN_OUT_SALIDA_PF));
				tenencias.setCustodiaEntity((List<CustodiaEntity>) respuesta.get(IN_OUT_SALIDA_CUSTODIA));
			}
			tenencias.setImpuestosEntity((List<ImpuestosEntity>) respuesta.get(IN_OUT_SALIDA_IMPUESTOS));
			tenencias.setPrestamosEntity((List<PrestamosEntity>) respuesta.get(IN_OUT_SALIDA_PRESTAMOS));
			tenencias.setTarjetasEntity((List<TarjetasEntity>) respuesta.get(IN_OUT_SALIDA_TJC));
			tenencias.setRendimientoFondosEntity((List<RendimientoFondosEntity>) respuesta.get(IN_OUT_SALIDA_FCT));
			tenencias.setImpuestoMonedaExtranjeraEntity(
					(List<ImpuestoMonedaExtranjeraEntity>) respuesta.get(IN_OUT_SALIDA_IMPME));
			tenencias.setCotiDolar((BigDecimal) respuesta.get(OUT_COTIZACION_AFIP));
			return tenencias;
		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}
	}

	public TitulosValoresResponseEntity ObtenerResumenTitulos(String anio, String nup) {
		
		DatosTitulosRequestEntity datos = new DatosTitulosRequestEntity();
		DetallesTitulosRequetsEntity request = new DetallesTitulosRequetsEntity();
		datos.setAnio(anio);
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		request.setCanal("04");
		request.setDato("Prueba");
		request.setFirma(generarFirma());
		request.setDatos(datos);

		TitulosValoresResponseEntity respuesta = new TitulosValoresResponseEntity();
		try {
			WebClient miService = crearLlamadaAWebService(OBTENER_RESUMEN_TITULOS);
			respuesta = miService.post(request,TitulosValoresResponseEntity.class);
			if (Integer.parseInt(respuesta.getCodigo())!=0) {
				LOGGER.error("Error al invocar Titulos Response	null");
				throw new DAOException();
			}
				
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return respuesta;
	}

	public FondosResponseEntity obtenerTenenciasFondos(String nup, String anioDesde) {

		FondosResponseEntity response = new FondosResponseEntity();

		DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
		DatosResumenFondosRimptEntity datos = new DatosResumenFondosRimptEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
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
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		datos.setAño(anioDesde);

		if (sesionCliente.getCliente().getClienteBancaPrivada()) {
			datos.setSegmento(SEGMENTO_BP);
		} else {
			datos.setSegmento(SEGMENTO_RTL);
		}
		request.setDatos(datos);
		request.setFirma(generarFirma());
		request.setDato(dato);

		try {
			response = obtenerFondosPL(request);
		} catch (DAOException e) {
			LOGGER.error("ERROR al el response de la Consulta de FONDO" + response.getMensajeTecnico());
		}
		return response;
	}

	public PlazoFijoResponseEntity obtenerTenenciasPlazoFijo(String nup, String anioDesde) {

		PlazoFijoResponseEntity response = new PlazoFijoResponseEntity();

		DetallePlazoFijoRequestEntity request = new DetallePlazoFijoRequestEntity();
		DatosDetallesPlazoFijoRequestEntity datos = new DatosDetallesPlazoFijoRequestEntity();
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
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
		datos.setNup(nup);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		datos.setAnio(anioDesde);
		

		request.setDatos(datos);
		request.setFirma(generarFirma());
		request.setDato(dato);

		try {
			response = obtenerPlazoFijoPL(request);
		} catch (DAOException e) {
			LOGGER.error("ERROR en el response de la Consulta de Plazo fijo" + response.getMensajeTecnico());
		}
		return response;
	}

	public PlazoFijoResponseEntity obtenerPlazoFijoPL(DetallePlazoFijoRequestEntity request) throws DAOException {

		PlazoFijoResponseEntity rta = new PlazoFijoResponseEntity();
		try {
			WebClient service = crearLlamadaAWebService(OBTENER_PLAZO_FIJO);
			rta = service.post(request, PlazoFijoResponseEntity.class);

			if (rta.getCodigo() != 0) {
				LOGGER.error("Error al invocar de Fondo rta	 null");
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(MENSAJE_ERROR_1 + " Error Response", e);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(MENSAJE_ERROR_2 + " ProcessingException", e);
			throw new DAOException();
		} catch (RuntimeException e) {
			LOGGER.error(MENSAJE_ERROR_3 + "RuntimeException", e);
			throw new DAOException();
		}

		return rta;
	};
	
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

	public FondosResponseEntity obtenerFondosPL(DetalleFondoRequestEntity request) throws DAOException {

		FondosResponseEntity rta = new FondosResponseEntity();
		List<String> fondosExc = Arrays.asList(FONDOS_EXCLUIDOS.split(","));
		List<FondosRimpEntity> listaFondo = new ArrayList<FondosRimpEntity>();
		rta.setDatos(listaFondo);
		try {

			WebClient service = crearLlamadaAWebService(OBTENER_SALDO_FONOD);
			rta = service.post(request, FondosResponseEntity.class);

			for (FondosRimpEntity item : rta.getDatos()) {

				boolean isExcluido = false;

				for (String fondoExc : fondosExc) {
					if (item.getCodFondo().equals(fondoExc)) {
						isExcluido = true;
					}
				}

				if (isExcluido) {
					item.setDivisa("");
					item.setCotiAfip("-");
					item.setImporte("-");
				}

			}

			if (rta.getCodigo() != 0) {
				LOGGER.error("Error al invocar de Fondo rta	 null");
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(MENSAJE_ERROR_1 + " Error Response", e);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(MENSAJE_ERROR_2 + " ProcessingException", e);
			throw new DAOException();
		} catch (RuntimeException e) {
			LOGGER.error(MENSAJE_ERROR_3 + "RuntimeException", e);
			throw new DAOException();
		}

		return rta;
	};
	
	

	/**
	 * Crear llamada A web service.
	 *
	 * @param pathDeConsulta the path de consulta
	 * @return the web client
	 * @throws DAOException the DAO exception
	 */
	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);

		return service;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDAO#
	 * obtenerFirmantes(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public FirmantesOutEntity obtenerFirmantes(String nup, String anioDesde, String anioHasta) throws DAOException {

		LOGGER.info("Se consultara el procedure {} con los datos nup: {} - anioDesde: {} - anioHasta: {}.",
				FIRMANTES_PROCEDURE, nup, anioDesde, anioHasta);

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(IN_OUT_FIRMANTE_CUENTA, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FirmanteEntity>>() {
					@Override
					public List<FirmanteEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<FirmanteEntity> lstRet = new ArrayList<FirmanteEntity>();
						while (rs.next()) {
							lstRet.add(parseFirmante(rs));
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_FIRMANTE_CUSTODIA, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FirmanteEntity>>() {
					@Override
					public List<FirmanteEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<FirmanteEntity> lstRet = new ArrayList<FirmanteEntity>();
						while (rs.next()) {
							FirmanteEntity firmante = parseFirmante(rs);
							lstRet.add(firmante);
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_FIRMANTE_FONDO, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FirmanteEntity>>() {
					@Override
					public List<FirmanteEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<FirmanteEntity> lstRet = new ArrayList<FirmanteEntity>();
						while (rs.next()) {
							lstRet.add(parseFirmante(rs));
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_FIRMANTE_PF, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FirmanteEntity>>() {
					@Override
					public List<FirmanteEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<FirmanteEntity> lstRet = new ArrayList<FirmanteEntity>();
						while (rs.next()) {
							lstRet.add(parseFirmantePF(rs));
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlOutParameter(IN_OUT_FIRMANTE_PRE, OracleTypes.CURSOR,
				new ResultSetExtractor<List<FirmantePrestamoEntity>>() {
					@Override
					public List<FirmantePrestamoEntity> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<FirmantePrestamoEntity> lstRet = new ArrayList<FirmantePrestamoEntity>();
						while (rs.next()) {
							lstRet.add(parseFirmantePrestamo(rs));
						}
						return lstRet;
					}
				}));
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ANIO_DESDE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ANIO_HASTA, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup).addValue(IN_ANIO_DESDE, anioDesde)
				.addValue(IN_ANIO_HASTA, anioHasta);
		try {
			Map<String, Object> respuesta = consultar(TENENCIAS_SCHEMA, TENENCIAS_PACKAGE, FIRMANTES_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			FirmantesOutEntity firmantes = new FirmantesOutPLEntity();
			firmantes.setCuentas((List<FirmanteEntity>) respuesta.get(IN_OUT_FIRMANTE_CUENTA));
				firmantes.setCustodias((List<FirmanteEntity>) respuesta.get(IN_OUT_FIRMANTE_CUSTODIA));
			firmantes.setFondos((List<FirmanteEntity>) respuesta.get(IN_OUT_FIRMANTE_FONDO));
			firmantes.setPlazoFijo((List<FirmanteEntity>) respuesta.get(IN_OUT_FIRMANTE_PF));
			firmantes.setPrestamos((List<FirmantePrestamoEntity>) respuesta.get(IN_OUT_FIRMANTE_PRE));
			return firmantes;
		} catch (SQLException sqle) {
			LOGGER.error(sqle.getMessage(), sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}
	}
	

	/**
	 * Parses the firmante.
	 *
	 * @param rs the rs
	 * @return the firmante entity
	 * @throws SQLException the SQL exception
	 */
	private FirmanteEntity parseFirmante(ResultSet rs) throws SQLException {
		FirmanteEntity firmante = new FirmanteEntity();
		firmante.setAnio(rs.getString(ANIO));
		firmante.setCuenta(rs.getString(CUENTA));
		firmante.setPepriape(rs.getString(PEPRIAPE));
		firmante.setPenomper(rs.getString(PENOMPER));
		firmante.setPetidoc(
				TipoDocumentoDescripcionEnum.obtenerTipoDocumentoDescripcion(rs.getString(PETIPDOC)).getDescripcion());
		firmante.setPenumdoc(rs.getString(PENUMDOC));
		return firmante;
	}

	/**
	 * Parses the firmante PF.
	 *
	 * @param rs the rs
	 * @return the firmante entity
	 * @throws SQLException the SQL exception
	 */
	private FirmanteEntity parseFirmantePF(ResultSet rs) throws SQLException {
		FirmanteEntity firmante = new FirmanteEntity();
		firmante.setAnio(rs.getString(ANIO));
		firmante.setPecodofi(rs.getString(PECODOFI));
		firmante.setPenumcom(rs.getString(CUENTA));
		firmante.setPepriape(rs.getString(PEPRIAPE));
		firmante.setPenomper(rs.getString(PENOMPER));
		firmante.setPetidoc(
				TipoDocumentoDescripcionEnum.obtenerTipoDocumentoDescripcion(rs.getString(PETIPDOC)).getDescripcion());
		firmante.setPenumdoc(rs.getString(PENUMDOC));

		return firmante;
	}

	/**
	 * Parses the firmante prestamo.
	 *
	 * @param rs the rs
	 * @return the firmante prestamo entity
	 * @throws SQLException the SQL exception
	 */
	private FirmantePrestamoEntity parseFirmantePrestamo(ResultSet rs) throws SQLException {
		FirmantePrestamoEntity firmante = new FirmantePrestamoEntity();
		firmante.setAnio(rs.getString(ANIO));
		firmante.setPecodofi(rs.getString(PECODOFI));
		firmante.setPepriape(rs.getString(PEPRIAPE));
		firmante.setPenomper(rs.getString(PENOMPER));
		firmante.setPetidoc(
				TipoDocumentoDescripcionEnum.obtenerTipoDocumentoDescripcion(rs.getString(PETIPDOC)).getDescripcion());
		firmante.setPenumdoc(rs.getString(PENUMDOC));
		firmante.setCuenta(rs.getString(CUENTA));
		firmante.setPecodpro(rs.getString("PECODPRO"));
		firmante.setPecodsub(rs.getString("PECODSUB"));
		return firmante;
	}

	/**
	 * Parses the cuentas.
	 *
	 * @param rs the rs
	 * @return the cuentas entity
	 * @throws SQLException the SQL exception
	 */
	private CuentasEntity parseCuentas(ResultSet rs) throws SQLException {
		CuentasEntity cuentasEntity = new CuentasEntity();
		cuentasEntity.setAnio(rs.getString(ANIO));
		cuentasEntity.setTipoCuenta(rs.getString(TIPO_CUENTA));
		cuentasEntity.setPecodofi(rs.getString(PECODOFI));
		cuentasEntity.setCuenta(rs.getString(CUENTA));
		cuentasEntity.setDivisa(rs.getString(DIVISA));
		cuentasEntity.setSaldo(rs.getString(SALDO));
		cuentasEntity.setIntDevengado(rs.getString(INT_DEVENGADO));
		cuentasEntity.setCbu(rs.getString(CBU));
		cuentasEntity.setDescfOperar(rs.getString(DESC_F_OPERAR));
		cuentasEntity.setImporte(rs.getString(IMPORTE));
		cuentasEntity.setImporteIntDev(rs.getString(IMPORTE_INT_DEV));
		return cuentasEntity;
	}

	/**
	 * Parses the custodia.
	 *
	 * @param rs the rs
	 * @return the custodia entity
	 * @throws SQLException the SQL exception
	 */
	private CustodiaEntity parseCustodia(ResultSet rs) throws SQLException {
		CustodiaEntity custodiaEntity = new CustodiaEntity();
		custodiaEntity.setAnio(rs.getString(ANIO));
		custodiaEntity.setEspeTipo(rs.getString(ESPE_TIPO));
		custodiaEntity.setEspeNom(rs.getString(ESPE_NOM));
		custodiaEntity.setEspeCod(rs.getString(ESPE_COD));
		custodiaEntity.setCuenta(rs.getString(CUENTA));
		custodiaEntity.setDivisa(rs.getString(DIVISA));
		custodiaEntity.setValorNominal(rs.getString(VALOR_NOMINAL));
		custodiaEntity.setPorcResidual(rs.getString(PORC_RESIDUAL));
		custodiaEntity.setPrecio(rs.getString(PRECIO));
		if (rs.getFloat(COTI_AFIP) > 0) {
			custodiaEntity.setCotiAfip(rs.getString(COTI_AFIP));
		} else {
			custodiaEntity.setCotiAfip("-");
		}

		custodiaEntity.setImporte(rs.getString(IMPORTE));
		return custodiaEntity;
	}

	/**
	 * Parses the fondos.
	 *
	 * @param rs the rs
	 * @return the fondos entity
	 * @throws SQLException the SQL exception
	 */
	private FondosEntity parseFondos(ResultSet rs) throws SQLException {
		FondosEntity fondosEntity = new FondosEntity();
		fondosEntity.setAnio(rs.getString(ANIO));
		fondosEntity.setCodFondo(rs.getString(COD_FONDO));
		fondosEntity.setDescripcion(rs.getString(DESCRIPCION));
		fondosEntity.setCuenta(rs.getString(CUENTA));
		fondosEntity.setDivisa(rs.getString(DIVISA));
		fondosEntity.setSaldoCuotas(rs.getString(SALDO_CUOTAS));
		fondosEntity.setValorCuota(rs.getString(VALOR_CUOTA));
		fondosEntity.setCotiAfip(rs.getString(COTI_AFIP));
		fondosEntity.setImporte(rs.getString(IMPORTE));
		fondosEntity.setSumRenta(rs.getString(SUM_RENTA));
		return fondosEntity;
	}

	/**
	 * Parses the fondos pendients.
	 *
	 * @param rs the rs
	 * @return the fondos pendientes entity
	 * @throws SQLException the SQL exception
	 */
	private FondosPendientesEntity parseFondosPendients(ResultSet rs) throws SQLException {
		FondosPendientesEntity fondosPendientes = new FondosPendientesEntity();
		fondosPendientes.setAnio(rs.getString(ANIO));
		fondosPendientes.setCodFondo(rs.getString(COD_FONDO));
		fondosPendientes.setDescripcion(rs.getString(DESCRIPCION));
		fondosPendientes.setCuenta(rs.getString(CUENTA));
		fondosPendientes.setDivisa(rs.getString(DIVISA));
		fondosPendientes.setSaldoCuotas(rs.getString(SALDO_CUOTAS));
		fondosPendientes.setValorCuota(rs.getString(VALOR_CUOTA));
		fondosPendientes.setCotiAfip(rs.getString(COTI_AFIP));
		fondosPendientes.setImporte(rs.getString(IMPORTE));
		return fondosPendientes;
	}

	/**
	 * Parses the impuesto.
	 *
	 * @param rs the rs
	 * @return the impuestos entity
	 * @throws SQLException the SQL exception
	 */
	private ImpuestosEntity parseImpuesto(ResultSet rs) throws SQLException {
		ImpuestosEntity impuestos = new ImpuestosEntity();
		impuestos.setAnio(rs.getString(ANIO));
		impuestos.setTipoCuenta(rs.getString(TIPO_CUENTA));
		impuestos.setPecodofi(rs.getString(PECODOFI));
		impuestos.setCuenta(rs.getString(CUENTA));
		impuestos.setDivisa(rs.getString(DIVISA));
		impuestos.setAlicuota(rs.getString(ALICUOTA));
		impuestos.setImpDebito(rs.getString(IMP_DEBITO));
		impuestos.setImpCredito(rs.getString(IMP_CREDITO));
		impuestos.setTotalCobrado(rs.getString(TOTAL_COBRADO));
		impuestos.setPorcComputable(rs.getString(PORC_COMPUTABLE));
		impuestos.setImpComputable(rs.getString(IMP_COMPUTABLE));
		return impuestos;
	}

	/**
	 * Parses the prestamos.
	 *
	 * @param rs the rs
	 * @return the prestamos entity
	 * @throws SQLException the SQL exception
	 */
	private PrestamosEntity parsePrestamos(ResultSet rs) throws SQLException {
		PrestamosEntity prestamos = new PrestamosEntity();
		prestamos.setAnio(rs.getString(ANIO));
		prestamos.setPecodofi(rs.getString(PECODOFI));
		prestamos.setPecodpro(rs.getString(PECODPRO));
		prestamos.setPecodsub(rs.getString(PECODSUB));
		prestamos.setTipoProducto(rs.getString(TIPO_PRODUCTO));
		prestamos.setDivisa(rs.getString(DIVISA));
		prestamos.setDestinoFondos(rs.getString(DESTINO_FONDOS));
		prestamos.setDestinoDescripcion(rs.getString(DESTINO_DESCRIPCION));
		prestamos.setFechaFormalizacion(rs.getString(FECHA_FORMALIZACION));
		prestamos.setSituacion(rs.getString(SITUACION));
		prestamos.setSaldoDeuda(rs.getString(SALDO_DEUDA));
		prestamos.setSaldoVencido(rs.getString(SALDO_VENCIDO));
		prestamos.setIntDevengado(rs.getString(INT_DEVENGADO));
		prestamos.setIntVencido(rs.getString(INT_VENCIDO));
		prestamos.setIntCobrado(rs.getString(INT_COBRADO));
		prestamos.setPlazo(rs.getString(PLAZO));
		prestamos.setCuenta(rs.getString(CUENTA));
		return prestamos;
	}

	/**
	 * Parses the impuesto ME.
	 *
	 * @param rs the rs
	 * @return the impuesto moneda extranjera entity
	 * @throws SQLException the SQL exception
	 */
	private ImpuestoMonedaExtranjeraEntity parseImpuestoME(ResultSet rs) throws SQLException {
		ImpuestoMonedaExtranjeraEntity impuestoMonedaExtranjeraEntity = new ImpuestoMonedaExtranjeraEntity();
		impuestoMonedaExtranjeraEntity.setAnio(rs.getString(ANIO));
		impuestoMonedaExtranjeraEntity.setOrigen(rs.getString(ORIGEN));
		impuestoMonedaExtranjeraEntity.setTipoCuenta(rs.getString(TIPO_CUENTA));
		impuestoMonedaExtranjeraEntity.setPecodofi(rs.getString(PECODOFI));
		impuestoMonedaExtranjeraEntity.setCuenta(rs.getString(CUENTA));
		impuestoMonedaExtranjeraEntity.setDivisa(rs.getString(DIVISA));
		impuestoMonedaExtranjeraEntity.setAlicuota(rs.getString(ALICUOTA));
		impuestoMonedaExtranjeraEntity.setImpDebito(rs.getString(IMP_DEBITO));
		impuestoMonedaExtranjeraEntity.setImpCredito(rs.getString(IMP_CREDITO));
		impuestoMonedaExtranjeraEntity.setTotalCobrado(rs.getString(TOTAL_COBRADO));
		return impuestoMonedaExtranjeraEntity;
	}
}
