package ar.com.santanderrio.obp.servicios.comex.transfext.entities;

public class ConceptoLiquidacionOPEntity {

	private String idConcepto;
	private String codigoConcepto;
	private String concepto;
	private String admiteCanje;
	private String documentacionObligatoria;

	public String getIdConcepto() {
		return idConcepto;
	}

	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getAdmiteCanje() {
		return admiteCanje;
	}

	public void setAdmiteCanje(String admiteCanje) {
		this.admiteCanje = admiteCanje;
	}

	public String getDocumentacionObligatoria() {
		return documentacionObligatoria;
	}

	public void setDocumentacionObligatoria(String documentacionObligatoria) {
		this.documentacionObligatoria = documentacionObligatoria;
	}

}
