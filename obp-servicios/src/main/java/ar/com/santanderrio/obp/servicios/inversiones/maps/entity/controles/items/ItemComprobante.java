package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

public class ItemComprobante {

    protected String etiqueta;
    protected String valor;
    protected String desde;
    protected String hasta;
    protected String desdeValor;
    protected String hastaValor;

    
    public ItemComprobante(String etiqueta) {
        super();
        this.etiqueta = etiqueta;
    }

    
    public ItemComprobante() {
        super();
    }


    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getDesdeValor() {
        return desdeValor;
    }

    public void setDesdeValor(String desdeValor) {
        this.desdeValor = desdeValor;
    }

    public String getHastaValor() {
        return hastaValor;
    }

    public void setHastaValor(String hastaValor) {
        this.hastaValor = hastaValor;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo(){
        return "control";
    }
}
