package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

public class ConceptoConfiguracionOrdenPagoDTO {

	private String idConcepto;
	private String nombre;
	private Boolean admiteCanje;
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

	public Boolean getAdmiteCanje() {
		return admiteCanje;
	}

	public void setAdmiteCanje(Boolean admiteCanje) {
		this.admiteCanje = admiteCanje;
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
