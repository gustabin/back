/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class IatxResponse.
 */
@SuppressWarnings("PMD")
public class IatxResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// --- OBP

	/** The fecha transaccion formater. */
	private final SimpleDateFormat fechaTransaccionFormater = new SimpleDateFormat("yyyyMMddHH:mm:ss");

	/** The iatx body. */
	private Vector<String> iatxBody;

	/** The nro mje multiple. */
	private String nroMjeMultiple;

	/** The id sesion transaccional. */
	private String idSesionTransaccional;

	/** The error code. */
	private int errorCode = -1;

	/** The error message. */
	private String errorMessage = "";

	/** The error system. */
	private String errorSystem = "";

	/** The warning ok. */
	private boolean warningOk = false;

	/** The nro comprobante. */
	private String nroComprobante = "";

	/** The fecha. */
	private String fecha = "";

	/** The hora. */
	private String hora = "";

	/** The indic sinonimo. */
	private String indicSinonimo = "";

	/** The first data index. */
	private int firstDataIndex = 1;

	/** The next. */
	private int next = 1;

	/** The sesion usuario. */
	private String sesionUsuario;

	/** The fecha hora req. */
	private String fechaHoraReq;

	/** The nombre servicio. */
	private String nombreServicio;

	/** The version servicio. */
	private String versionServicio;

	/** The mensaje de negocio. */
	// mensaje traido del archivo hbspecialcondition.properties
	private MensajeDeNegocio mensajeDeNegocio = new MensajeDeNegocio();

	// --- Respuesta

	/** The mensaje. */
	private String mensaje;

	/** The estado respuesta. */
	private EstadoRespuesta estadoRespuesta;

	/** The tipo error. */
	private TipoError tipoError;

	/** The trama. */
	private String trama;

	/** The resultado transaccion. */
	private ResultadoTransaccion resultadoTransaccion;
	
	/** The buffering. */
	private boolean buffering;

	/**
	 * Gets the next data.
	 *
	 * @return the next data
	 */
	public String getNextData() {
		String vuelta = null;
		if (iatxBody != null) {
			vuelta = iatxBody.elementAt(next++);
		}
		return vuelta;
	}

	/**
	 * Reset data index.
	 */
	public void resetDataIndex() {
		next = firstDataIndex;
	}

	/**
	 * Gets the data.
	 *
	 * @param index
	 *            the index
	 * @return the data
	 */
	public String getData(int index) {
		String vuelta = null;
		if (iatxBody != null) {
			vuelta = iatxBody.elementAt(index);
		}
		return vuelta;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Setter para mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the estado respuesta.
	 *
	 * @return the estadoRespuesta
	 */
	public EstadoRespuesta getEstadoRespuesta() {
		return estadoRespuesta;
	}

	/**
	 * Setter para estado respuesta.
	 *
	 * @param estadoRespuesta
	 *            the estadoRespuesta to set
	 */
	public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipoError
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Setter para tipo error.
	 *
	 * @param tipoError
	 *            the tipoError to set
	 */
	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the iatx body.
	 *
	 * @return the iatx body
	 */
	public Vector<String> getIatxBody() {
		return iatxBody;
	}

	/**
	 * Setter para iatx body.
	 *
	 * @param iatxBody
	 *            el nuevo iatx body
	 */
	public void setIatxBody(Vector<String> iatxBody) {
		this.iatxBody = iatxBody;
	}

	/**
	 * Gets the nro mje multiple.
	 *
	 * @return the nro mje multiple
	 */
	public String getNroMjeMultiple() {
		return nroMjeMultiple;
	}

	/**
	 * Setter para nro mje multiple.
	 *
	 * @param nroMjeMultiple
	 *            el nuevo nro mje multiple
	 */
	public void setNroMjeMultiple(String nroMjeMultiple) {
		this.nroMjeMultiple = nroMjeMultiple;
	}

	/**
	 * Gets the id sesion transaccional.
	 *
	 * @return the id sesion transaccional
	 */
	public String getIdSesionTransaccional() {
		return idSesionTransaccional;
	}

	/**
	 * Setter para id sesion transaccional.
	 *
	 * @param idSesionTransaccional
	 *            el nuevo id sesion transaccional
	 */
	public void setIdSesionTransaccional(String idSesionTransaccional) {
		this.idSesionTransaccional = idSesionTransaccional;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Setter para error code.
	 *
	 * @param errorCode
	 *            el nuevo error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter para error message.
	 *
	 * @param errorMessage
	 *            el nuevo error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error system.
	 *
	 * @return the error system
	 */
	public String getErrorSystem() {
		return errorSystem;
	}

	/**
	 * Setter para error system.
	 *
	 * @param errorSystem
	 *            el nuevo error system
	 */
	public void setErrorSystem(String errorSystem) {
		this.errorSystem = errorSystem;
	}

	/**
	 * Checks if is warning ok.
	 *
	 * @return true, if is warning ok
	 */
	public boolean isWarningOk() {
		return warningOk;
	}

	/**
	 * Setter para warning ok.
	 *
	 * @param warningOk
	 *            el nuevo warning ok
	 */
	public void setWarningOk(boolean warningOk) {
		this.warningOk = warningOk;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Setter para nro comprobante.
	 *
	 * @param nroComprobante
	 *            el nuevo nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Setter para fecha.
	 *
	 * @param fecha
	 *            el nuevo fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Setter para hora.
	 *
	 * @param hora
	 *            el nuevo hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the indic sinonimo.
	 *
	 * @return the indic sinonimo
	 */
	public String getIndicSinonimo() {
		return indicSinonimo;
	}

	/**
	 * Setter para indic sinonimo.
	 *
	 * @param indicSinonimo
	 *            el nuevo indic sinonimo
	 */
	public void setIndicSinonimo(String indicSinonimo) {
		this.indicSinonimo = indicSinonimo;
	}

	/**
	 * Gets the first data index.
	 *
	 * @return the first data index
	 */
	public int getFirstDataIndex() {
		return firstDataIndex;
	}

	/**
	 * Setter para first data index.
	 *
	 * @param firstDataIndex
	 *            el nuevo first data index
	 */
	public void setFirstDataIndex(int firstDataIndex) {
		this.firstDataIndex = firstDataIndex;
	}

	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public int getNext() {
		return next;
	}

	/**
	 * Setter para next.
	 *
	 * @param next
	 *            el nuevo next
	 */
	public void setNext(int next) {
		this.next = next;
	}

	/**
	 * Gets the sesion usuario.
	 *
	 * @return the sesion usuario
	 */
	public String getSesionUsuario() {
		return sesionUsuario;
	}

	/**
	 * Setter para sesion usuario.
	 *
	 * @param sesionUsuario
	 *            el nuevo sesion usuario
	 */
	public void setSesionUsuario(String sesionUsuario) {
		this.sesionUsuario = sesionUsuario;
	}

	/**
	 * Gets the fecha hora req.
	 *
	 * @return the fecha hora req
	 */
	public String getFechaHoraReq() {
		return fechaHoraReq;
	}

	/**
	 * Setter para fecha hora req.
	 *
	 * @param fechaHoraReq
	 *            el nuevo fecha hora req
	 */
	public void setFechaHoraReq(String fechaHoraReq) {
		this.fechaHoraReq = fechaHoraReq;
	}

	/**
	 * Gets the nombre servicio.
	 *
	 * @return the nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Setter para nombre servicio.
	 *
	 * @param nombreServicio
	 *            el nuevo nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Gets the version servicio.
	 *
	 * @return the version servicio
	 */
	public String getVersionServicio() {
		return versionServicio;
	}

	/**
	 * Setter para version servicio.
	 *
	 * @param versionServicio
	 *            el nuevo version servicio
	 */
	public void setVersionServicio(String versionServicio) {
		this.versionServicio = versionServicio;
	}

	/**
	 * Gets the mensaje de negocio.
	 *
	 * @return the mensaje de negocio
	 */
	public MensajeDeNegocio getMensajeDeNegocio() {
		return mensajeDeNegocio;
	}

	/**
	 * Setter para mensaje de negocio.
	 *
	 * @param mensajeDeNegocio
	 *            el nuevo mensaje de negocio
	 */
	public void setMensajeDeNegocio(String mensajeDeNegocio) {
		this.mensajeDeNegocio.setLinea1(mensajeDeNegocio);
	}

	/**
	 * 2da linea del mensaje, cuando el mensaje tiene 2 lineas de texto, como en
	 * el caso de
	 * http://tfsserver:8080/tfs/DefaultCollection/_api/_wit/DownloadAttachment?
	 * fileName=desktop_Login_bloqued.png&attachmentId=12941&contentOnly=true&
	 * __v=5 y
	 * http://tfsserver:8080/tfs/DefaultCollection/_api/_wit/DownloadAttachment?
	 * fileName=mobile_Login_bloqued.png&attachmentId=12940&contentOnly=true&__v
	 * =5
	 * 
	 * @param mensajeDeNegocioLinea2
	 *            the mensajeDeNegocioLinea2 to set
	 */
	public void setMensajeDeNegocioLinea2(String mensajeDeNegocioLinea2) {
		this.mensajeDeNegocio.setLinea2(mensajeDeNegocioLinea2);
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Sets the trama.
	 *
	 * @param trama
	 *            the new trama
	 */
	public void setTrama(String trama) {
		this.trama = trama;
	}

	/**
	 * Gets the trama.
	 *
	 * @return the trama
	 */
	public String getTrama() {
		return trama;
	}

	/**
	 * Obtiene los datos de respuesta agrupados (nro. comprobante, fecha,
	 * estado, cod. error, sistema asociado al error, mensaje de error)
	 * 
	 * @return ResultadoTransaccion
	 */
	public ResultadoTransaccion getResultadoTransaccion() {
		if (resultadoTransaccion == null) {
			resultadoTransaccion = generarResultadoTransaccion();
		}
		return resultadoTransaccion;
	}

	/**
	 * Generar resultado transaccion.
	 *
	 * @return the resultado transaccion
	 */
	// TODO resolver si esto va en el iatx parser o que hacer
	private ResultadoTransaccion generarResultadoTransaccion() {
		return new ResultadoTransaccion(this.getNroComprobante(), this.obtenerFechaTransaccion(),
				this.getEstadoRespuesta(), this.getErrorCode(), this.getErrorSystem(), this.getErrorMessage());
	}

	/**
	 * Obtener fecha transaccion.
	 *
	 * @return the date
	 */
	private Date obtenerFechaTransaccion() {
		String fechaString = this.getFecha() + this.getHora();
		Date fechaTransaccion = null;
		try {
			fechaTransaccion = fechaTransaccionFormater.parse(fechaString);
		} catch (ParseException e) {
			fechaTransaccion = new Date();
		}
		return fechaTransaccion;

	}

	/**
	 * The Class MensajeDeNegocio.
	 */
	public static class MensajeDeNegocio implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The linea1. */
		private String linea1;

		/** The linea2. */
		private String linea2;

		/**
		 * Gets the linea1.
		 *
		 * @return the linea1
		 */
		public String getLinea1() {
			return linea1;
		}

		/**
		 * Setter para linea1.
		 *
		 * @param linea1
		 *            the linea1 to set
		 */
		public void setLinea1(String linea1) {
			this.linea1 = linea1;
		}

		/**
		 * Gets the linea2.
		 *
		 * @return the linea2
		 */
		public String getLinea2() {
			return linea2;
		}

		/**
		 * Setter para linea2.
		 *
		 * @param linea2
		 *            the linea2 to set
		 */
		public void setLinea2(String linea2) {
			this.linea2 = linea2;
		}
	}

	/**
	 * Checks if is buffering.
	 *
	 * @return true, if is buffering
	 */
	public boolean isBuffering() {
		return buffering;
	}

	/**
	 * Sets the buffering.
	 *
	 * @param buffering
	 *            the new buffering
	 */
	public void setBuffering(boolean buffering) {
		this.buffering = buffering;
	}
	
	
}
