package ar.com.santanderrio.obp.servicios.comex.transfext.view;

public class ConceptoLiquidacionOrdenPagoView {

	private String idConcepto;
	private String nombre;
	private Boolean muestraCuentasDolares;
	private Boolean documentacionObligatoria;
	private Boolean muestraVinculante;

	public String getIdConcepto() {
		return idConcepto;
	}

	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getMuestraCuentasDolares() {
		return muestraCuentasDolares;
	}

	public void setMuestraCuentasDolares(Boolean muestraCuentasDolares) {
		this.muestraCuentasDolares = muestraCuentasDolares;
	}

	public Boolean getDocumentacionObligatoria() {
		return documentacionObligatoria;
	}

	public void setDocumentacionObligatoria(Boolean documentacionObligatoria) {
		this.documentacionObligatoria = documentacionObligatoria;
	}

	public Boolean getMuestraVinculante() {
		return muestraVinculante;
	}

	public void setMuestraVinculante(Boolean muestraVinculante) {
		this.muestraVinculante = muestraVinculante;
	}

}
