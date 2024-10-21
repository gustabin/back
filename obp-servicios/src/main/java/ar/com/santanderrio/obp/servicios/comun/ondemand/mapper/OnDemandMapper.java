/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.mapper;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandConstants;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;

/**
 * The Class OnDemandMapper.
 */
@Component
public class OnDemandMapper {

	/** The Constant PDF. */
	private static final String PDF = "PDF";

	/** The Constant MOTIVO. */
	private static final String MOTIVO = "Motivo";

	/** The Constant NROSUCURSAL_LEN. */
	private static final int NROSUCURSAL_LEN = 3;

	/** The Constant NROCTATIT_LEN. */
	private static final int NROCTATIT_LEN = 11;

	private static final String TIPO_CUENTA = "tpo-cta";
	
	private static final String SUCURSAL = "sucursal";
	
	private static final String CUENTA = "cuenta";
	
	private static final String PERSONA = "persona";
	
	private static final String PAQUETE = "paquete";
	
	private static final String FECHA_DESDE = "fecha-desde";
	
	private static final String FECHA_HASTA = "fecha-hasta";
	
	private static final String TIPO = "tipo";
	
	private static final String FECHA = "fecha";
	
	private static final String CUENTA_TITULOS = "cuenta-titulos";
	
	private static final String FORMATO_FECHA = "dd/MM/yy";
	

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The on demand id. */
	@Value("${DB.ONDEMAND.CONN.SECURITY.ID}")
	private String onDemandId;

	/**
	 * Mapear request reporte consulta puntual.
	 *
	 * @param resumenMensualCuenta
	 *            the resumen mensual cuenta
	 * @param cuenta
	 *            the cuenta
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param variablePaquete
	 *            the variable paquete
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest mapearRequestReporteConsultaPuntual(ResumenMensualCuenta resumenMensualCuenta,
			AbstractCuenta cuenta, String nombreServicio, String variablePaquete) throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		if (OnDemandConstants.VARIABLE_PAQUETE_ADVANCE.equals(variablePaquete)) {
			req.setMotivoConsulta("");
		}
		if (OnDemandConstants.VARIABLE_PAQUETE.equals(variablePaquete)) {
			req.addFiltroBusqueda("tpo-cta-altair", "");
		}
		req.setUsuarioConsulta(cuenta.getCliente().getNup());
		req.addFiltroBusqueda(TIPO_CUENTA, cuenta.getTipoCuenta());
		req.addFiltroBusqueda(SUCURSAL, cuenta.getNroSucursal());
		req.addFiltroBusqueda(CUENTA, cuenta.getNroCuentaProducto());
		req.addFiltroBusqueda(PERSONA, OnDemandConstants.VARIABLE_PERSONA);
		req.addFiltroBusqueda(PAQUETE, variablePaquete);
		req.addFiltroBusqueda(FECHA, ISBANStringUtils.formatearFecha(resumenMensualCuenta.getFecha(), FORMATO_FECHA));
		if (OnDemandConstants.VARIABLE_PAQUETE.equals(variablePaquete)) {
			req.addFiltroBusqueda("folder", resumenMensualCuenta.getCarpeta());
		}
		req.setGrabarLog(Boolean.FALSE);
		return req;
	}

	/**
	 * Mapear request resumen fechas.
	 *
	 * @param params
	 *            the params
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param variablePaquete
	 *            the variable paquete
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest mapearRequestResumenFechas(ResumenParams params, String nombreServicio, String variablePaquete)
			throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());

		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_RESUMEN_FECHAS);
		req.setMotivoConsulta(params.getMotivoConsulta());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		req.addFiltroBusqueda(TIPO_CUENTA, params.getCuenta().getTipoCuenta());
		if (OnDemandConstants.VARIABLE_PAQUETE_ADVANCE.equalsIgnoreCase(variablePaquete)) {
			req.addFiltroBusqueda("producto", params.getCuenta().getProductoAltair());
		}
		req.addFiltroBusqueda(SUCURSAL, params.getCuenta().getNroSucursal());
		req.addFiltroBusqueda(CUENTA, params.getCuenta().getNroCuentaProducto());
		req.addFiltroBusqueda(PERSONA, OnDemandConstants.VARIABLE_PERSONA);
		req.addFiltroBusqueda(PAQUETE, variablePaquete);
		req.addFiltroBusqueda(FECHA_DESDE, params.getFechaDesde());
		req.addFiltroBusqueda(FECHA_HASTA, params.getFechaHasta());
		req.setGrabarLog(Boolean.TRUE);
		return req;
	}

	/**
	 * Crea el request para invocar al servicio ondemand de resumenes anteriores
	 * de tarjetas.
	 *
	 * @param params
	 *            the params
	 * @param nombreServicio
	 *            the nombre servicio
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest mapearRequestResumenesAnterioresTC(ResumenParams params, String nombreServicio)
			throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		req.addFiltroBusqueda(FECHA_DESDE, params.getFechaDesde());
		req.addFiltroBusqueda(FECHA_HASTA, params.getFechaHasta());
		//req.addFiltroBusqueda("sucursal", StringUtils.stripStart(params.getCuenta().getNroSucursal(), NumberUtils.BYTE_ZERO.toString())); Cambio por cierre sucursales 21/11
		req.addFiltroBusqueda(SUCURSAL, StringUtils.EMPTY);
		req.addFiltroBusqueda(CUENTA, StringUtils.stripStart(params.getCuenta().getNroCuentaProducto(), NumberUtils.BYTE_ZERO.toString()));
		req.addFiltroBusqueda("proveedor-tarjeta", params.getProveedorTarjeta());
		req.addFiltroBusqueda(PERSONA, OnDemandConstants.VARIABLE_PERSONA);
		req.addFiltroBusqueda(PAQUETE, OnDemandConstants.VARIABLE_PAQUETE);
		req.setGrabarLog(Boolean.TRUE);
		return req;
	}

	/**
	 * Crea el request para invocar al servicio ondemand de Descargar Resumen de
	 * tarjetas.
	 *
	 * @param params
	 *            the params
	 * @param nombreServicio
	 *            the nombre servicio
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest mapearRequestDescargarResumenTC(ResumenParams params, String nombreServicio)
			throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		req.addFiltroBusqueda(PAQUETE, OnDemandConstants.VARIABLE_PAQUETE);
		req.addFiltroBusqueda(FECHA, params.getFechaPuntual());
		req.addFiltroBusqueda("folder", params.getFolder());
		req.addFiltroBusqueda(CUENTA, params.getCuenta().getNroCuentaProducto());
		req.addFiltroBusqueda(SUCURSAL, " ");//se envia un blanco por el proyecto cierre de sucursales 07-12-18
		req.addFiltroBusqueda(PERSONA, OnDemandConstants.VARIABLE_PERSONA);
		req.addFiltroBusqueda("proveedor-tarjeta", params.getProveedorTarjeta());
		return req;
	}

	/**
	 * Crea el request para invocar al servicio ondemand de Marca de Impresion.
	 *
	 * @param params
	 *            the params
	 * @param nombreServicio
	 *            the nombre servicio
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest consultaMarcaImpresion(ResumenParams params, String nombreServicio) throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		req.addFiltroBusqueda("aplicacion", params.getFiltroAplicacion());
		req.addFiltroBusqueda(SUCURSAL, ISBANStringUtils.formatearSucursal(params.getCuenta().getNroSucursal()));
		req.addFiltroBusqueda(CUENTA, ISBANStringUtils.formatNroCuenta(params.getCuenta().getNroCuentaProducto()));
		return req;
	}

	/**
	 * Modifica la marca de impresion para la cuenta.
	 *
	 * @param params
	 *            the params
	 * @param nombreServicio
	 *            the nombre servicio
	 * @return the WSOD request
	 * @throws SQLException
	 *             the SQL exception
	 */
	public WSODRequest modificarMarcaImpresion(ResumenParams params, String nombreServicio) throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		req.setFormatoRespuesta(PDF);
		req.addFiltroBusqueda("aplicacion", params.getFiltroAplicacion());
		req.addFiltroBusqueda(SUCURSAL, params.getCuenta().getNroSucursal());
		req.addFiltroBusqueda(CUENTA, params.getCuenta().getNroCuentaProducto());
		req.addFiltroBusqueda("soporte", params.getSoporte());
		req.addFiltroBusqueda("moneda", params.getMoneda());

		return req;
	}

	/**
     * Mapear request resumen fechas inversiones.
     *
     * @param params
     *            the params
     * @param nombreServicio
     *            the nombre servicio
     * @return the WSOD request
     * @throws SQLException
     *             the SQL exception
     */
    public WSODRequest mapearRequestResumenFechasInversiones(ResumenParams params, String nombreServicio)
            throws SQLException {
        Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
        WSODRequest req = new WSODRequest();
        req.setNombre(nombreServicio);
        req.setVersion(OnDemandConstants.SERVICIO_VERSION);
        req.setCanal(OnDemandConstants.SERVICIO_CANAL);
        req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
        req.setUsuario(credential.getUsuario());
        req.setClave(credential.getPassword());
        req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_RESUMEN_FECHAS);
        req.setMotivoConsulta(params.getMotivoConsulta());
        req.setUsuarioConsulta(params.getUsuarioConsulta());
        req.addFiltroBusqueda(SUCURSAL,ISBANStringUtils.formateadorConCerosIzq(params.getCuenta().getNroSucursal(), NROSUCURSAL_LEN));
        req.addFiltroBusqueda(CUENTA_TITULOS, ISBANStringUtils.formateadorConCerosIzq(params.getCuenta().getNroCuentaProducto(), NROCTATIT_LEN));
        req.addFiltroBusqueda(FECHA_DESDE, params.getFechaDesde());
        req.addFiltroBusqueda(FECHA_HASTA, params.getFechaHasta());
        req.setGrabarLog(Boolean.TRUE);
        return req;
    }

    /**
     * Mapear request reporte consulta puntual inversiones.
     *
     * @param resumen
     *            the resumen mensual inversiones
     * @param cuenta
     *            the cuenta
     * @param nombreServicio
     *            the nombre servicio
     * @return the WSOD request
     * @throws SQLException
     *             the SQL exception
     */
    public WSODRequest mapearRequestReporteConsultaPuntualInversiones(ResumenMensualInversiones resumen,
            AbstractCuenta cuenta, String nombreServicio) throws SQLException {
        Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
        WSODRequest req = new WSODRequest();
        req.setNombre(nombreServicio);
        req.setVersion(OnDemandConstants.SERVICIO_VERSION);
        req.setCanal(OnDemandConstants.SERVICIO_CANAL);
        req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
        req.setUsuario(credential.getUsuario());
        req.setClave(credential.getPassword());
        req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
        req.setMotivoConsulta(MOTIVO);
        req.setUsuarioConsulta(cuenta.getCliente().getNup());
        req.addFiltroBusqueda(SUCURSAL,ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroSucursal(), NROSUCURSAL_LEN));
        req.addFiltroBusqueda(CUENTA_TITULOS, ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), NROCTATIT_LEN));
        req.addFiltroBusqueda("periodo", resumen.getPeriodo());
        req.addFiltroBusqueda(FECHA_DESDE, resumen.getFechaDesde());
        req.addFiltroBusqueda(FECHA_HASTA, resumen.getFechaHasta());
        req.setGrabarLog(Boolean.FALSE);
        return req;
    }


    public WSODRequest mapearRequestResumenFinanciero(ResumenFinancieroDTO dto, String nombreServicio) throws SQLException {
        Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
        WSODRequest req = new WSODRequest();
        req.setNombre(nombreServicio);
        req.setVersion(OnDemandConstants.SERVICIO_VERSION);
        req.setCanal(OnDemandConstants.SERVICIO_CANAL);
        req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL);
        req.setUsuario(credential.getUsuario());
        req.setClave(credential.getPassword());
        req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
        req.setMotivoConsulta(MOTIVO);
        req.setUsuarioConsulta(dto.getNup());
        req.addFiltroBusqueda(SUCURSAL,ISBANStringUtils.formateadorConCerosIzq(dto.getSucursal(), NROSUCURSAL_LEN));
        req.addFiltroBusqueda(CUENTA_TITULOS, dto.getNup());
        req.addFiltroBusqueda("periodo", dto.getPeriodo());
        req.addFiltroBusqueda(FECHA_DESDE, dto.getFechaDesde());
        req.addFiltroBusqueda(FECHA_HASTA, dto.getFechaHasta());
        req.setGrabarLog(Boolean.FALSE);
        return req;
    }

    
	public WSODRequest mapearRequestResumenFechasBP(ResumenParams params, String nombreServicio)
			throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL_BP);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL_BP);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_RESUMEN_FECHAS);
		req.setMotivoConsulta(params.getMotivoConsulta());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		String numeroCuenta = params.getCuenta().getNroCuentaProducto();
		req.addFiltroBusqueda(CUENTA, numeroCuenta.substring(numeroCuenta.length()-7, numeroCuenta.length()));
		req.addFiltroBusqueda(FECHA_DESDE, params.getFechaDesde());
		req.addFiltroBusqueda(FECHA_HASTA, params.getFechaHasta());
		req.addFiltroBusqueda(TIPO, "RES");
		String nroSucursal = params.getCuenta().getNroSucursal();
		req.addFiltroBusqueda(SUCURSAL, nroSucursal.substring(nroSucursal.length()-3, nroSucursal.length()));
		req.setGrabarLog(Boolean.TRUE);
		return req;
	}
	
	public WSODRequest mapearRequestReporteConsultaPuntualBP(ResumenMensualCuenta resumenMensualCuenta,
			AbstractCuenta cuenta, String nombreServicio) throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL_BP);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL_BP);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
		req.setMotivoConsulta("request");
		req.setUsuarioConsulta(cuenta.getCliente().getNup());
		req.addFiltroBusqueda(FECHA, ISBANStringUtils.formatearFecha(resumenMensualCuenta.getFecha(), FORMATO_FECHA));
		String numeroCuenta = cuenta.getNroCuentaProducto();
		req.addFiltroBusqueda(CUENTA, numeroCuenta.substring(numeroCuenta.length()-7, numeroCuenta.length()));
		String nroSucursal = cuenta.getNroSucursal();
		req.addFiltroBusqueda(SUCURSAL, nroSucursal.substring(nroSucursal.length()-3, nroSucursal.length()));
		req.setGrabarLog(Boolean.FALSE);
		return req;
	}
	
	public WSODRequest mapearRequestComprobantesTVBP(ResumenParams params, String nombreServicio)
			throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL_BP);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL_BP);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_RESUMEN_FECHAS);
		req.setMotivoConsulta(params.getMotivoConsulta());
		req.setUsuarioConsulta(params.getUsuarioConsulta());
		String numeroCuenta = params.getCuenta().getNroCuentaProducto();
		req.addFiltroBusqueda(CUENTA, numeroCuenta.substring(numeroCuenta.length()-8, numeroCuenta.length()));
		req.addFiltroBusqueda(FECHA_DESDE, params.getFechaDesde());
		req.addFiltroBusqueda(FECHA_HASTA, params.getFechaHasta());
		req.addFiltroBusqueda(TIPO, TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(params.getTipoPDF()) ? "LIQ" : "TYA");
		req.addFiltroBusqueda(SUCURSAL, "");
		req.setGrabarLog(Boolean.TRUE);
		return req;
	}
	
	public WSODRequest mapearRequestComprobantesBP(ResumenMensualCuenta resumenMensualCuenta,
			AbstractCuenta cuenta, String nombreServicio, TipoPDFEnum tipoPDF) throws SQLException {
		Credential credential = credentialSecurityFactory.getCredentialById(onDemandId);
		WSODRequest req = new WSODRequest();
		req.setNombre(nombreServicio);
		req.setVersion(OnDemandConstants.SERVICIO_VERSION);
		req.setCanal(OnDemandConstants.SERVICIO_CANAL_BP);
		req.setSubcanal(OnDemandConstants.SERVICIO_SUBCANAL_BP);
		req.setUsuario(credential.getUsuario());
		req.setClave(credential.getPassword());
		req.setFormatoRespuesta(OnDemandConstants.FORMATO_RESPUESTA_CONSULTA_PUNTUAL);
		req.setMotivoConsulta("request");
		req.setUsuarioConsulta(cuenta.getCliente().getNup());
		String numeroCuenta = cuenta.getNroCuentaProducto();
		req.addFiltroBusqueda(CUENTA, numeroCuenta.substring(numeroCuenta.length()-8, numeroCuenta.length()));
		req.addFiltroBusqueda(FECHA, ISBANStringUtils.formatearFecha(resumenMensualCuenta.getFecha(), FORMATO_FECHA));
		req.addFiltroBusqueda(TIPO, TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF) ? "LIQ" : "TYA");
		String nroSucursal = cuenta.getNroSucursal();
		req.addFiltroBusqueda(SUCURSAL, TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF) ? nroSucursal.substring(nroSucursal.length()-3, nroSucursal.length()) : "");
		req.addFiltroBusqueda("referencia", TipoPDFEnum.TITULOS_VALORES.equals(tipoPDF) ? resumenMensualCuenta.getReferencia() : "");
		if (TipoPDFEnum.FONDOS_COMUNES_INVERSION.equals(tipoPDF)) {
			req.addFiltroBusqueda("nro_liquidacion", resumenMensualCuenta.getNroLiquidacion());
		}
		req.setGrabarLog(Boolean.FALSE);
		return req;
	}

}
