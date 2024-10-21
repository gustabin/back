/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * The Class WSODRequest.
 */
public class WSODRequest {

	/** The nombre. */
	private String nombre = null;

	/** The version. */
	private String version = null;

	/** The canal. */
	private String canal = null;

	/** The subcanal. */
	private String subcanal = null;

	/** The usuario. */
	private String usuario = null;

	/** The clave. */
	private String clave = null;

	/** The formato respuesta. */
	private String formatoRespuesta = null;

	/** The motivo consulta. */
	private String motivoConsulta = null;

	/** The usuario consulta. */
	private String usuarioConsulta = null;

	/** The filtros. */
	private Map<String, String> filtros = new HashMap<String, String>();

	/** The Constant FILTRO_TPO_CTA. */
	public static final String FILTRO_TPO_CTA = "tpo-cta";

	/** The Constant FILTRO_TPO_CTA_ALTAIR. */
	public static final String FILTRO_TPO_CTA_ALTAIR = "tpo-cta-altair";

	/** The Constant FILTRO_NRO_CTA. */
	public static final String FILTRO_NRO_CTA = "cuenta";

	/** The Constant FILTRO_SUC_CTA. */
	public static final String FILTRO_SUC_CTA = "sucursal";

	/** The Constant FILTRO_FECHA_DESDE. */
	public static final String FILTRO_FECHA_DESDE = "fecha-desde";

	/** The Constant FILTRO_FECHA_HASTA. */
	public static final String FILTRO_FECHA_HASTA = "fecha-hasta";

	/** The Constant FILTRO_PERSONA. */
	public static final String FILTRO_PERSONA = "persona";

	/** The Constant FILTRO_PAQUETE. */
	public static final String FILTRO_PAQUETE = "paquete";

	/** The Constant FILTRO_FOLDER. */
	public static final String FILTRO_FOLDER = "folder";

	/** The Constant FILTRO_FECHA. */
	public static final String FILTRO_FECHA = "fecha";

	/** The Constant FILTRO_APLICACION. */
	public static final String FILTRO_APLICACION = "aplicacion";

	/** The Constant FILTRO_SOPORTE. */
	public static final String FILTRO_SOPORTE = "soporte";

	/** The Constant FILTRO_MONEDA. */
	public static final String FILTRO_MONEDA = "divisa";

	/** The Constant FILTRO_CLIENTE. */
	public static final String FILTRO_CLIENTE = "cliente";

	/** The Constant FILTRO_DOCUMENTO. */
	public static final String FILTRO_DOCUMENTO = "documento";

	/** The Constant FILTRO_NUMERO_CTA. */
	public static final String FILTRO_NUMERO_CTA = "numero";

	/** The Constant PERSONA_INDIVIDUO. */
	public static final String PERSONA_INDIVIDUO = "I";

	/** The Constant PERSONA_EMPRESA. */
	public static final String PERSONA_EMPRESA = "E";

	/** The Constant PAQUETE_INDIVIDUO. */
	public static final String PAQUETE_INDIVIDUO = "P";

	/** The Constant PAQUETE_SIN_PAQUETE. */
	public static final String PAQUETE_SIN_PAQUETE = "S";

	/** The Constant PAQUETE_EMPRESA. */
	public static final String PAQUETE_EMPRESA = "E";

	/** The Constant CONSULTA_ULTIMO_RESUMEN. */
	public static final String CONSULTA_ULTIMO_RESUMEN = "CNS_ULTIMO_RESUMEN";

	/** The Constant CONSULTA_RESUMEN_PUNTUAL. */
	public static final String CONSULTA_RESUMEN_PUNTUAL = "CNS_RESUMEN_PUNTUAL";

	/** The Constant CONSULTA_RESUMEN_POR_FECHAS. */
	public static final String CONSULTA_RESUMEN_POR_FECHAS = "CNS_RESUMEN_POR_FECHAS";

	/** The Constant CONSULTA_MARCA_IMPRESION. */
	public static final String CONSULTA_MARCA_IMPRESION = "CNS_MARCA_IMPRESION";

	/** The Constant CAMBIO_MARCA_IMPRESION. */
	public static final String CAMBIO_MARCA_IMPRESION = "CMB_MARCA_IMPRESION";

	/** The Constant CONSULTA_RESUMEN_POR_FECHAS_CC. */
	public static final String CONSULTA_RESUMEN_POR_FECHAS_CC = "CNS_RESUMEN_POR_FECHAS_CC";

	/** The Constant CONSULTA_RESUMEN_PUNTUAL_CC. */
	public static final String CONSULTA_RESUMEN_PUNTUAL_CC = "CNS_RESUMEN_PUNTUAL_CC";

	/** The Constant CNS_ULTIMO_RESUMEN_CC. */
	public static final String CNS_ULTIMO_RESUMEN_CC = "CNS_ULTIMO_RESUMEN_CC";

	/** The Constant CNS_RESUMEN_POR_FECHAS_BSP. */
	public static final String CNS_RESUMEN_POR_FECHAS_BSP = "CNS_RESUMEN_POR_FECHAS_BSP";

	/** The Constant CNS_RESUMEN_PUNTUAL_BSP. */
	public static final String CNS_RESUMEN_PUNTUAL_BSP = "CNS_RESUMEN_PUNTUAL_BSP";

	/** The Constant CNS_BSP_TIPO. */
	public static final String CNS_BSP_TIPO = "tipo";

	/** The Constant FILTRO_FECHA_DESDE_PERIODO. */
	public static final String FILTRO_FECHA_DESDE_PERIODO = "Fecha_de_cierre_de_periodo";

	/** The Constant CNS_RESUMEN_POR_FECHAS_BP. */
	public static final String CNS_RESUMEN_POR_FECHAS_BP = "CNS_RESUMEN_POR_FECHAS_BP";

	/** The Constant CNS_RESUMEN_PUNTUAL_BP. */
	public static final String CNS_RESUMEN_PUNTUAL_BP = "CNS_RESUMEN_PUNTUAL_BP";

	/** The Constant CNS_RESUMEN_POR_FECHAS_PDE. */
	public static final String CNS_RESUMEN_POR_FECHAS_PDE = "CNS_RESUMEN_POR_FECHAS_PDE";

	/** The Constant CNS_RESUMEN_PUNTUAL_PDE. */
	public static final String CNS_RESUMEN_PUNTUAL_PDE = "CNS_RESUMEN_PUNTUAL_PDE";

	/** The Constant CNS_RESUMEN_OFFLINE_X_CUENTA_PDE. */
	public static final String CNS_RESUMEN_OFFLINE_X_CUENTA_PDE = "CNS_RESUMEN_OFFLINE_X_CUENTA_PDE";

	/** The Constant CNS_LIQ_CHEQUES_POR_FECHAS. */
	public static final String CNS_LIQ_CHEQUES_POR_FECHAS = "CNS_LIQ_CHEQUES_POR_FECHAS";

	/** The Constant CNS_LIQ_CHEQUES_PUNTUAL. */
	public static final String CNS_LIQ_CHEQUES_PUNTUAL = "CNS_LIQ_CHEQUES_PUNTUAL";

	/** The Constant CNS_DA_CUOTA_PRESTAMO_POR_FECHAS. */
	public static final String CNS_DA_CUOTA_PRESTAMO_POR_FECHAS = "CNS_DA_CUOTA_PRESTAMO_POR_FECHAS";

	/** The Constant CNS_DA_CUOTA_PRESTAMO_PUNTUAL. */
	public static final String CNS_DA_CUOTA_PRESTAMO_PUNTUAL = "CNS_DA_CUOTA_PRESTAMO_PUNTUAL";

	/** The Constant CNS_FORM_LIQ_PRESTAMOS_POR_FECHAS. */
	public static final String CNS_FORM_LIQ_PRESTAMOS_POR_FECHAS = "CNS_FORM_LIQ_PRESTAMOS_POR_FECHAS";

	/** The Constant CNS_FORM_LIQ_PRESTAMOS_PUNTUAL. */
	public static final String CNS_FORM_LIQ_PRESTAMOS_PUNTUAL = "CNS_FORM_LIQ_PRESTAMOS_PUNTUAL";

	/** The Constant NRO_PRESTAMO. */
	public static final String NRO_PRESTAMO = "nro-prestamo";

	/** The Constant FILTRO_NRO_PRESTAMO. */
	public static final String FILTRO_NRO_PRESTAMO = "nro-prestamo";

	/** The Constant CNS_PDE_TIPO. */
	public static final String CNS_PDE_TIPO = "tipo";

	/** The Constant CNS_PDE_PAQUETE. */
	public static final String CNS_PDE_PAQUETE = "paquete";

	/** The Constant CNS_PDE_PRODUCTO. */
	public static final String CNS_PDE_PRODUCTO = "producto";

	/** The Constant CNS_PDE_ESTADO_AL. */
	public static final String CNS_PDE_ESTADO_AL = "estado_al";

	/** The Constant FORMATO_RESPUESTA_PDF. */
	public static final String FORMATO_RESPUESTA_PDF = "PDF";

	/** The Constant FORMATO_RESPUESTA_XML. */
	public static final String FORMATO_RESPUESTA_XML = "XML";

	/** The Constant FORMATO_RESPUESTA_ZIP. */
	public static final String FORMATO_RESPUESTA_ZIP = "ZIP";

	/** The Constant CONSULTA_RESUMEN_POR_FECHAS_TC. */
	public static final String CONSULTA_RESUMEN_POR_FECHAS_TC = "CONSULTA_RESUMEN_POR_FECHAS_TC";

	/** The Constant CONSULTA_RESUMEN_PUNTUAL_TC. */
	public static final String CONSULTA_RESUMEN_PUNTUAL_TC = "CONSULTA_RESUMEN_PUNTUAL_TC";

	/** The Constant FILTRO_TARJETA. */
	public static final String FILTRO_TARJETA = "proveedor-tarjeta";

	/** The grabar log. */
	private Boolean grabarLog;

	/**
	 * Gets the grabar log.
	 *
	 * @return the grabar log
	 */
	public Boolean getGrabarLog() {
		return grabarLog;
	}

	/**
	 * Sets the grabar log.
	 *
	 * @param grabarLog
	 *            the new grabar log
	 */
	public void setGrabarLog(Boolean grabarLog) {
		this.grabarLog = grabarLog;
	}

	/**
	 * Instantiates a new WSOD request.
	 */
	public WSODRequest() {
		this.filtros = new HashMap<String, String>();
	}

	/**
	 * Adds the filtro busqueda.
	 *
	 * @param nombre
	 *            the nombre
	 * @param valor
	 *            the valor
	 */
	public void addFiltroBusqueda(String nombre, String valor) {
		this.filtros.put(nombre, valor);
	}

	/**
	 * Gets the filtro busqueda.
	 *
	 * @param nombre
	 *            the nombre
	 * @return the filtro busqueda
	 */
	public String getFiltroBusqueda(String nombre) {
		return this.filtros.get(nombre);
	}

	/**
	 * Sets the canal.
	 *
	 * @param string
	 *            the new canal
	 */
	public void setCanal(String string) {
		this.canal = string;
	}

	/**
	 * Sets the clave.
	 *
	 * @param string
	 *            the new clave
	 */
	public void setClave(String string) {
		this.clave = string;
	}

	/**
	 * Sets the formato respuesta.
	 *
	 * @param string
	 *            the new formato respuesta
	 */
	public void setFormatoRespuesta(String string) {
		this.formatoRespuesta = string;
	}

	/**
	 * Sets the motivo consulta.
	 *
	 * @param string
	 *            the new motivo consulta
	 */
	public void setMotivoConsulta(String string) {
		this.motivoConsulta = string;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param string
	 *            the new nombre
	 */
	public void setNombre(String string) {
		this.nombre = string;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param string
	 *            the new subcanal
	 */
	public void setSubcanal(String string) {
		this.subcanal = string;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param string
	 *            the new usuario
	 */
	public void setUsuario(String string) {
		this.usuario = string;
	}

	/**
	 * Sets the usuario consulta.
	 *
	 * @param string
	 *            the new usuario consulta
	 */
	public void setUsuarioConsulta(String string) {
		this.usuarioConsulta = string;
	}

	/**
	 * Sets the version.
	 *
	 * @param string
	 *            the new version
	 */
	public void setVersion(String string) {
		this.version = string;
	}

	/**
	 * Gets the as DOM.
	 *
	 * @return the as DOM
	 */
	public Document getAsDOM() {
		Document dom = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("consulta");
		if (this.nombre != null) {
			root.addElement("nombre").setText(this.nombre);
		}
		if (this.version != null) {
			root.addElement("version").setText(this.version);
		}
		if (this.canal != null) {
			root.addElement("canal").setText(this.canal);
		}
		if (this.subcanal != null) {
			root.addElement("subcanal").setText(this.subcanal);
		}
		if (this.usuario != null) {
			root.addElement("usuario").setText(this.usuario);
		}
		if (this.clave != null) {
			root.addElement("clave").setText(this.clave);
		}
		Element parametros = root.addElement("parametros");
		if (this.formatoRespuesta != null) {
			parametros.addElement("formato-respuesta").setText(this.formatoRespuesta);
		}
		if (this.motivoConsulta != null) {
			parametros.addElement("motivo-consulta").setText(this.motivoConsulta);
		}
		if (this.usuarioConsulta != null) {
			parametros.addElement("usuario-consulta").setText(this.usuarioConsulta);
		}
		Element filtrosRequest = root.addElement("filtros");
		for (Iterator<Entry<String, String>> iter = this.filtros.entrySet().iterator(); iter.hasNext();) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			filtrosRequest.addElement(entry.getKey()).setText((String) entry.getValue());
		}
		dom.setRootElement(root);

		return dom;
	}
}