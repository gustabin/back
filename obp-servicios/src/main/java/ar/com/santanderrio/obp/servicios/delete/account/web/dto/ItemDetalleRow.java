package ar.com.santanderrio.obp.servicios.delete.account.web.dto;

public class ItemDetalleRow {
	private String label;
    private String descripcionPaquete;
    private String numeroPaquete;

    public String getLabel() {
    	return label;
    }
    
    public void setLabel(String label) {
    	this.label = label;
    }
    
    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public String getNumeroPaquete() {
        return numeroPaquete;
    }

    public void setNumeroPaquete(String numeroPaquete) {
        this.numeroPaquete = numeroPaquete;
    }
}
