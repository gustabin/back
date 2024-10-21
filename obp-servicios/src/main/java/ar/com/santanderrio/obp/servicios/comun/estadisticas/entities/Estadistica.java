/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadisticas.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class Estadistica.
 *
 * @author Federico_Juliano
 */
public class Estadistica extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo transaccion. */
	private String codigoTransaccion;

	/** The codigo error. */
	private String codigoError = "";

	/** The nro cta origen. */
	private String nroCtaOrigen = "";

	/** The nro cta destino. */
	private String nroCtaDestino = "";

	/** The nro comprobante. */
	private String nroComprobante = "";

	/** The importe. */
	private String importe = "";

	/** The moneda. */
	private String moneda = "";

	/** The tipo teclado. */
	private String tipoTeclado = "";

	/** The ip. */
	private String ip = "";

	/** The host name. */
	private String hostName = "";

	/** The file name. */
	private String fileName = "";

	/** The cliente NUP. */
	private String clienteNUP = "";

	/** The cliente sinonimo. */
	private String fechaNacimiento = "";

	/** The cliente pid. */
	private String clientePID = "";

	/** The cliente dni. */
	private String clienteDNI = "";

	/** The fecha. */
	private String fecha;

	/** The dispositivo. */
	private String dispositivo;

	private String csId;

	/** The navegador. */
	private String navegador;

	/** The canal. */
	private String canal;

	/**
	 * Instantiates a new estadistica.
	 */
	public Estadistica() {
		super();
		Date fechaD = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy HHmmss");
		fecha = format.format(fechaD);
	}

	/**
	 * Crea una estadistica nueva con el codigo de estadistica y el codigo de
	 * resultado ya seteados.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param codigoResultado
	 *            the codigo resultado
	 */
	public Estadistica(String codigoTransaccion, String codigoResultado) {
		super();
		Date fechaD = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy HHmmss");
		this.fecha = format.format(fechaD);
		this.codigoError = codigoResultado;
		this.codigoTransaccion = codigoTransaccion;
	}

	/**
	 * Gets the codigo transaccion.
	 *
	 * @return the codigo transaccion
	 */
	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}

	/**
	 * Setter para codigo transaccion.
	 *
	 * @param codigoTransaccion
	 *            el nuevo codigo transaccion
	 */
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigo error
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Setter para codigo error.
	 *
	 * @param codigoError
	 *            el nuevo codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the nro cta origen.
	 *
	 * @return the nro cta origen
	 */
	public String getNroCtaOrigen() {
		return nroCtaOrigen;
	}

	/**
	 * Setter para nro cta origen.
	 *
	 * @param nroCtaOrigen
	 *            el nuevo nro cta origen
	 */
	public void setNroCtaOrigen(String nroCtaOrigen) {
		this.nroCtaOrigen = nroCtaOrigen;
	}

	/**
	 * Gets the nro cta destino.
	 *
	 * @return the nro cta destino
	 */
	public String getNroCtaDestino() {
		return nroCtaDestino;
	}

	/**
	 * Setter para nro cta destino.
	 *
	 * @param nroCtaDestino
	 *            el nuevo nro cta destino
	 */
	public void setNroCtaDestino(String nroCtaDestino) {
		this.nroCtaDestino = nroCtaDestino;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

    /**
     * Cargar el importe con el siguiente formato ^\d+\.\d{2}$ es decir dos
     * decimales luego del punto. En caso de recibir una coma se remueve y prosigue.
     *
     * @param importe
     *            el nuevo importe
     */
    public void setImporte(String importe) {
        if (!StringUtils.isBlank(importe)) {
            String nro = StringUtils.contains(importe, ',') ? StringUtils.remove(importe, ',') : importe;
            BigDecimal importeBigD = new BigDecimal(nro);
            this.setImporte(importeBigD);
        }
    }

    /**
     * Cargar el importe con el siguiente formato ^\d+\.\d{2}$ es decir dos
     * decimales luego del punto.
     * 
     * @param importe
     */
    public void setImporte(BigDecimal importe) {
        if (importe != null) {
            BigDecimal importeDecimal = importe.setScale(2, BigDecimal.ROUND_DOWN);
            this.importe = importeDecimal.toString();
        }
    }

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Setter para moneda.
	 *
	 * @param moneda
	 *            el nuevo moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the tipoTeclado.
	 *
	 * @return the tipoTeclado
	 */
	public String getTipoTeclado() {
		return tipoTeclado;
	}

	/**
	 * Setter para tv.
	 *
	 * @param tipoTeclado
	 *            el nuevo tipoTeclado
	 */
	public void setTipoTeclado(String tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setter para ip.
	 *
	 * @param ip
	 *            el nuevo ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Setter para host name.
	 *
	 * @param hostName
	 *            el nuevo host name
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter para file name.
	 *
	 * @param fileName
	 *            el nuevo file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the cliente nup.
	 *
	 * @return the cliente nup
	 */
	public String getClienteNUP() {
		return clienteNUP;
	}

	/**
	 * Setter para cliente nup.
	 *
	 * @param clienteNUP
	 *            el nuevo cliente nup
	 */
	public void setClienteNUP(String clienteNUP) {
		this.clienteNUP = clienteNUP;
	}

	/**
	 * Gets the fecha de Nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Setter para cliente sinonimo.
	 *
	 * @param fechaNacimiento
	 *            el nuevo cliente sinonimo
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the cliente pid.
	 *
	 * @return the cliente pid
	 */
	public String getClientePID() {
		return clientePID;
	}

	/**
	 * Setter para cliente pid.
	 *
	 * @param clientePID
	 *            el nuevo cliente pid
	 */
	public void setClientePID(String clientePID) {
		this.clientePID = clientePID;
	}

	/**
	 * Gets the cliente dni.
	 *
	 * @return the cliente dni
	 */
	public String getClienteDNI() {
		return clienteDNI;
	}

	/**
	 * Setter para cliente dni.
	 *
	 * @param clienteDNI
	 *            el nuevo cliente dni
	 */
	public void setClienteDNI(String clienteDNI) {
		this.clienteDNI = clienteDNI;
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
	 * Gets the dispositivo.
	 *
	 * @return the dispositivo
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 * Setter para dispositivo.
	 *
	 * @param dispositivo
	 *            the dispositivo to set
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Gets the navegador.
	 *
	 * @return the navegador
	 */
	public String getNavegador() {
		return navegador;
	}

	/**
	 * Setter para navegador.
	 *
	 * @param navegador
	 *            el nuevo navegador
	 */
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Setter canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getCsId() { return csId; }

	public void setCsId(String csId) { this.csId = csId; }

	/*
	 * (non-Javadoc)**
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Estadistica [");
		agregarAlBuilderPrimerSetParametros(builder);

		builder.append(hostName != null ? "hostName=" + hostName + ", " : "")
				.append(fileName != null ? "fileName=" + fileName + ", " : "")
				.append(clienteNUP != null ? "clienteNUP=" + clienteNUP + ", " : "")
				.append(fechaNacimiento != null ? "fechaNacimiento=" + fechaNacimiento + ", " : "")
				.append(clientePID != null ? "clientePID=" + clientePID + ", " : "")
				.append(clienteDNI != null ? "clienteDNI=" + clienteDNI + ", " : "")
				.append(fecha != null ? "fecha=" + fecha + ", " : "")
				.append(dispositivo != null ? "dispositivo=" + dispositivo + ", " : "")
				.append(navegador != null ? "navegador=" + navegador + ", " : "")
				.append(canal != null ? "canal=" + canal + " " : "").append("]");
		return builder.toString();
	}

	/**
	 * 66 Agregar al builder primer set parametros.
	 *
	 * @param builder
	 *            the builder
	 */
	private void agregarAlBuilderPrimerSetParametros(StringBuilder builder) {
		builder.append((codigoTransaccion != null ? "codigoTransaccion=" + codigoTransaccion + ", " : ""))
				.append(codigoError != null ? "codigoError=" + codigoError + ", " : "")
				.append(nroCtaOrigen != null ? "nroCtaOrigen=" + nroCtaOrigen + ", " : "")
				.append(nroCtaDestino != null ? "nroCtaDestino=" + nroCtaDestino + ", " : "")
				.append(nroComprobante != null ? "nroComprobante=" + nroComprobante + ", " : "")
				.append(importe != null ? "importe=" + importe + ", " : "")
				.append(moneda != null ? "moneda=" + moneda + ", " : "")
				.append((tipoTeclado != null ? "tipoTeclado=" + tipoTeclado + ", " : "")
						+ (ip != null ? "ip=" + ip + ", " : ""));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(canal);
		hcb.append(clienteDNI);
		hcb.append(clienteNUP);
		hcb.append(clientePID);
		hcb.append(codigoError);
		hcb.append(codigoTransaccion);
		hcb.append(dispositivo);
		hcb.append(fecha);
		hcb.append(fechaNacimiento);
		hcb.append(fileName);
		hcb.append(hostName);
		hcb.append(importe);
		hcb.append(ip);
		hcb.append(moneda);
		hcb.append(navegador);
		hcb.append(nroComprobante);
		hcb.append(nroCtaDestino);
		hcb.append(nroCtaOrigen);
		hcb.append(tipoTeclado);
		return hcb.toHashCode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Estadistica other = (Estadistica) obj;
		return new EqualsBuilder().append(canal, other.canal).append(clienteDNI, other.clienteDNI)
				.append(clienteNUP, other.clienteNUP).append(clientePID, other.clientePID)
				.append(codigoError, other.codigoError).append(codigoTransaccion, other.codigoTransaccion)
				.append(dispositivo, other.dispositivo).append(fecha, other.fecha)
				.append(fechaNacimiento, other.fechaNacimiento).append(fileName, other.fileName)
				.append(hostName, other.hostName).append(importe, other.importe).append(ip, other.ip)
				.append(moneda, other.moneda).append(navegador, other.navegador)
				.append(nroComprobante, other.nroComprobante).append(nroCtaDestino, other.nroCtaDestino)
				.append(nroCtaOrigen, other.nroCtaOrigen).append(tipoTeclado, other.tipoTeclado).isEquals();
	}

}
