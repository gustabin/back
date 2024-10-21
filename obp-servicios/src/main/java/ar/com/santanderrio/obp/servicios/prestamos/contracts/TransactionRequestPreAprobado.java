package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransactionRequestPreAprobado extends TransactionRequest{
	@JsonProperty("nroContratoVin")
	public String nroContratoVin;
}

