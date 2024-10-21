package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransactionRequestPreAcordado extends TransactionRequest{
	@JsonProperty("tipCuentaVin")
	public String tipCuentaVin;
	@JsonProperty("sucCuentaVin")
	public String sucCuentaVin;
	@JsonProperty("nroCuentaVin")
	public String nroCuentaVin;
	
	@JsonProperty("sucursalPaquete")
	public String sucursalPaquete;
	@JsonProperty("cuentaPaquete")
	public String cuentaPaquete;
	@JsonProperty("tipoPoliza")
	public String tipoPoliza;
	@JsonProperty("tipoRiesgo")
	public String tipoRiesgo;
	@JsonProperty("lineaUva")
	public String lineaUva;
}

