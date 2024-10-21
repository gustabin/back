package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransactionRequest {
	@JsonProperty("canalOrigen")
	public String canalOrigen;
	@JsonProperty("linea")
	public String linea;
	@JsonProperty("codigoProducto")
	public int codigoProducto;
	@JsonProperty("codigoSubproducto")
	public int codigoSubproducto;
	@JsonProperty("destinoFondos")
	public int destinoFondos;
	@JsonProperty("cantidadCuotas")
	public int cantidadCuotas;
	@JsonProperty("oficinaTitular")
	public String oficinaTitular;
	@JsonProperty("oficinaElevadora")
	public String oficinaElevadora;
	@JsonProperty("oficinaPresentadora")
	public String oficinaPresentadora;
	@JsonProperty("montoSolicitado")
	public BigDecimal montoSolicitado;
//	public String montoSolicitado;

	@JsonProperty("cuentaDestinoALiquidar")
	public String cuentaDestinoALiquidar;
	@JsonProperty("nup")
	public String nroCliente;
	@JsonProperty("divisa")
	public String divisa;
	@JsonProperty("codigoInstrumento")
	public String codigoInstrumento;
	@JsonProperty("fechaAprobacion")
	public String fechaAprobacion;
	@JsonProperty("fechaForma")
	public String fechaFormalizacion;
	@JsonProperty("fechaPrimerVto")
	public String fechaPrimerVto;
	@JsonProperty("tipoTasa")
	public String tipoTasa;
	@JsonProperty("indiceNegociable")
	public String indiceNegociable;
	@JsonProperty("tna")
	public BigDecimal tipAplicacion;
//	public String tipAplicacion;
	@JsonProperty("indBonifCta")
	public String indBonifcta;
	@JsonProperty("toPayOff")
	public boolean toPayOff;
}

