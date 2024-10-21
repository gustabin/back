package ar.com.santanderrio.obp.servicios.extraccionefectivo.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class CardlessWithdrawalResponse {

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("withdrawal_code")
    private String codigoExtraccion;
	   
	@JsonProperty("expiration_date")
	private String fechaExpiracion;
	
	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("origin")
	private Origin origin;
	
	@JsonProperty("destination")
	private Destination destination;
	
	@JsonProperty("timestamp")
	private String timestamp;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("error")
	private String error;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("path")
	private String path;
	
	@JsonProperty("ticket_number")
	private String numeroComprobante;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Origin getOrigin() {
		return origin;
	}
	
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	public Destination getDestination() {
		return destination;
	}
	
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

    /**
     * @return the codigoExtraccion
     */
    public String getCodigoExtraccion() {
        return codigoExtraccion;
    }

    /**
     * @param codigoExtraccion the codigoExtraccion to set
     */
    public void setCodigoExtraccion(String codigoExtraccion) {
        this.codigoExtraccion = codigoExtraccion;
    }

}
