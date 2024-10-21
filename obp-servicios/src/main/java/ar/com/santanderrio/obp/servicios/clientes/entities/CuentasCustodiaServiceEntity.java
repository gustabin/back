package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CuentasCustodiaServiceEntity {
	
	@JsonProperty("CuentasCustodia")
	private List<CtasTitEntity> cuentas;

	public List<CtasTitEntity> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CtasTitEntity> cuentas) {
		this.cuentas = cuentas;
	}
	
}
