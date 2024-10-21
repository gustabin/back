package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobanteAltaPlazoFijoUVA extends Comprobante {
    
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;

    @XmlElement(name = "FechaOper")
    protected String fechaOper;
    
    @XmlElement(name = "Titulo")
    protected String titulo;
    
    @XmlElement(name = "ImporteEnPesos")
    protected String importeEnPesos;
    
    @XmlElement(name = "TipoPlazoFijo")
    protected String tipoPlazoFijo;

    @XmlElement(name = "Plazo")
    protected String plazo;
    
    @XmlElement(name = "Moneda")
    protected String moneda;
    
    @XmlElement(name = "ValorUVA")
    protected String valorUva;
    
    @XmlElement(name = "CapitalUVA")
    protected String capitalUVA;
    
    @XmlElement(name = "CuentaDebito")
    protected String cuentaDebito;
    
    @XmlElement(name = "TasaNominal")
    protected String tasaNominal;
    
    @XmlElement(name = "TasaEfectiva")
    protected String tasaEfectiva;
    
    @XmlElement(name = "Intereses")
    protected String intereses;
    
    @XmlElement(name = "CampoImpuestoV1")
    protected String campoImpuestoUno;
    
    @XmlElement(name = "DatoImpuestoV1")
    protected String datoImpuestoUno;
    
    @XmlElement(name = "CampoImpuestoV2")
    protected String campoImpuestoDos;
    
    @XmlElement(name = "DatoImpuestoV2")
    protected String datoImpuestoDos;
    
    @XmlElement(name = "CampoImpuestoV3")
    protected String campoImpuestoTres;
    
    @XmlElement(name = "DatoImpuestoV3")
    protected String datoImpuestoTres;
    
    @XmlElement(name = "CampoImpuestoV4")
    protected String campoImpuestoCuatro;
    
    @XmlElement(name = "DatoImpuestoV4")
    protected String datoImpuestoCuatro;
    
    @XmlElement(name = "CampoImpuestoV5")
    protected String campoImpuestoCinco;
    
    @XmlElement(name = "DatoImpuestoV5")
    protected String datoImpuestoCinco;
    
    @XmlElement(name = "CampoImpuestoV6")
    protected String campoImpuestoSeis;
    
    @XmlElement(name = "DatoImpuestoV6")
    protected String datoImpuestoSeis;
    
    @XmlElement(name = "CampoImpuestoV7")
    protected String campoImpuestoSiete;
    
    @XmlElement(name = "DatoImpuestoV7")
    protected String datoImpuestoSiete;
    
    @XmlElement(name = "CampoImpuestoV8")
    protected String campoImpuestoOcho;
    
    @XmlElement(name = "DatoImpuestoV8")
    protected String datoImpuestoOcho;
    
    @XmlElement(name = "CampoImpuestoV9")
    protected String campoImpuestoNueve;
    
    @XmlElement(name = "DatoImpuestoV9")
    protected String datoImpuestoNueve;
    
    @XmlElement(name = "CampoImpuestoV10")
    protected String campoImpuestoDiez;
    
    @XmlElement(name = "DatoImpuestoV10")
    protected String datoImpuestoDiez;
    
    @XmlElement(name = "InteresesNetosImpuestos")
    protected String interesesNetosImpuestos;
    
    @XmlElement(name = "FechaConstitucion")
    protected String fechaConstitucion;
    
    @XmlElement(name = "FechaVencimiento")
    protected String fechaVencimiento;
    
    @XmlElement(name = "AccionVencimiento")
    protected String accionVencimiento;
    
    @XmlElement(name = "CuentaDeposito")
    protected String cuentaDeposito;
    
    @XmlElement(name = "Estado")
    protected String estado;
    
    @XmlElement(name = "FechaHora")
    protected String fechaHora;
        
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;
    
    @XmlElement(name = "Cliente")
    protected Cliente cliente;

    /**
     * @return the estadoOper
     */
    public OperacionEstado getEstadoOper() {
        return estadoOper;
    }

    /**
     * @param estadoOper the estadoOper to set
     */
    public void setEstadoOper(OperacionEstado estadoOper) {
        this.estadoOper = estadoOper;
    }

    /**
     * @return the fechaOper
     */
    public String getFechaOper() {
        return fechaOper;
    }

    /**
     * @param fechaOper the fechaOper to set
     */
    public void setFechaOper(String fechaOper) {
        this.fechaOper = fechaOper;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the importeEnPesos
     */
    public String getImporteEnPesos() {
        return importeEnPesos;
    }

    /**
     * @param importeEnPesos the importeEnPesos to set
     */
    public void setImporteEnPesos(String importeEnPesos) {
        this.importeEnPesos = importeEnPesos;
    }

    /**
     * @return the tipoPlazoFijo
     */
    public String getTipoPlazoFijo() {
        return tipoPlazoFijo;
    }

    /**
     * @param tipoPlazoFijo the tipoPlazoFijo to set
     */
    public void setTipoPlazoFijo(String tipoPlazoFijo) {
        this.tipoPlazoFijo = tipoPlazoFijo;
    }

    /**
     * @return the plazo
     */
    public String getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the valorUva
     */
    public String getValorUva() {
        return valorUva;
    }

    /**
     * @param valorUva the valorUva to set
     */
    public void setValorUva(String valorUva) {
        this.valorUva = valorUva;
    }

    /**
     * @return the capitalUVA
     */
    public String getCapitalUVA() {
        return capitalUVA;
    }

    /**
     * @param capitalUVA the capitalUVA to set
     */
    public void setCapitalUVA(String capitalUVA) {
        this.capitalUVA = capitalUVA;
    }

    /**
     * @return the cuentaDebito
     */
    public String getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * @param cuentaDebito the cuentaDebito to set
     */
    public void setCuentaDebito(String cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    /**
     * @return the tasaNominal
     */
    public String getTasaNominal() {
        return tasaNominal;
    }

    /**
     * @param tasaNominal the tasaNominal to set
     */
    public void setTasaNominal(String tasaNominal) {
        this.tasaNominal = tasaNominal;
    }

    /**
     * @return the tasaEfectiva
     */
    public String getTasaEfectiva() {
        return tasaEfectiva;
    }

    /**
     * @param tasaEfectiva the tasaEfectiva to set
     */
    public void setTasaEfectiva(String tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    /**
     * @return the intereses
     */
    public String getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    /**
     * @return the campoImpuestoUno
     */
    public String getCampoImpuestoUno() {
        return campoImpuestoUno;
    }

    /**
     * @param campoImpuestoUno the campoImpuestoUno to set
     */
    public void setCampoImpuestoUno(String campoImpuestoUno) {
        this.campoImpuestoUno = campoImpuestoUno;
    }

    /**
     * @return the datoImpuestoUno
     */
    public String getDatoImpuestoUno() {
        return datoImpuestoUno;
    }

    /**
     * @param datoImpuestoUno the datoImpuestoUno to set
     */
    public void setDatoImpuestoUno(String datoImpuestoUno) {
        this.datoImpuestoUno = datoImpuestoUno;
    }

    /**
     * @return the campoImpuestoDos
     */
    public String getCampoImpuestoDos() {
        return campoImpuestoDos;
    }

    /**
     * @param campoImpuestoDos the campoImpuestoDos to set
     */
    public void setCampoImpuestoDos(String campoImpuestoDos) {
        this.campoImpuestoDos = campoImpuestoDos;
    }

    /**
     * @return the datoImpuestoDos
     */
    public String getDatoImpuestoDos() {
        return datoImpuestoDos;
    }

    /**
     * @param datoImpuestoDos the datoImpuestoDos to set
     */
    public void setDatoImpuestoDos(String datoImpuestoDos) {
        this.datoImpuestoDos = datoImpuestoDos;
    }

    /**
     * @return the campoImpuestoTres
     */
    public String getCampoImpuestoTres() {
        return campoImpuestoTres;
    }

    /**
     * @param campoImpuestoTres the campoImpuestoTres to set
     */
    public void setCampoImpuestoTres(String campoImpuestoTres) {
        this.campoImpuestoTres = campoImpuestoTres;
    }

    /**
     * @return the datoImpuestoTres
     */
    public String getDatoImpuestoTres() {
        return datoImpuestoTres;
    }

    /**
     * @param datoImpuestoTres the datoImpuestoTres to set
     */
    public void setDatoImpuestoTres(String datoImpuestoTres) {
        this.datoImpuestoTres = datoImpuestoTres;
    }

    /**
     * @return the campoImpuestoCuatro
     */
    public String getCampoImpuestoCuatro() {
        return campoImpuestoCuatro;
    }

    /**
     * @param campoImpuestoCuatro the campoImpuestoCuatro to set
     */
    public void setCampoImpuestoCuatro(String campoImpuestoCuatro) {
        this.campoImpuestoCuatro = campoImpuestoCuatro;
    }

    /**
     * @return the datoImpuestoCuatro
     */
    public String getDatoImpuestoCuatro() {
        return datoImpuestoCuatro;
    }

    /**
     * @param datoImpuestoCuatro the datoImpuestoCuatro to set
     */
    public void setDatoImpuestoCuatro(String datoImpuestoCuatro) {
        this.datoImpuestoCuatro = datoImpuestoCuatro;
    }

    /**
     * @return the campoImpuestoCinco
     */
    public String getCampoImpuestoCinco() {
        return campoImpuestoCinco;
    }

    /**
     * @param campoImpuestoCinco the campoImpuestoCinco to set
     */
    public void setCampoImpuestoCinco(String campoImpuestoCinco) {
        this.campoImpuestoCinco = campoImpuestoCinco;
    }

    /**
     * @return the datoImpuestoCinco
     */
    public String getDatoImpuestoCinco() {
        return datoImpuestoCinco;
    }

    /**
     * @param datoImpuestoCinco the datoImpuestoCinco to set
     */
    public void setDatoImpuestoCinco(String datoImpuestoCinco) {
        this.datoImpuestoCinco = datoImpuestoCinco;
    }

    /**
     * @return the campoImpuestoSeis
     */
    public String getCampoImpuestoSeis() {
        return campoImpuestoSeis;
    }

    /**
     * @param campoImpuestoSeis the campoImpuestoSeis to set
     */
    public void setCampoImpuestoSeis(String campoImpuestoSeis) {
        this.campoImpuestoSeis = campoImpuestoSeis;
    }

    /**
     * @return the datoImpuestoSeis
     */
    public String getDatoImpuestoSeis() {
        return datoImpuestoSeis;
    }

    /**
     * @param datoImpuestoSeis the datoImpuestoSeis to set
     */
    public void setDatoImpuestoSeis(String datoImpuestoSeis) {
        this.datoImpuestoSeis = datoImpuestoSeis;
    }

    /**
     * @return the campoImpuestoSiete
     */
    public String getCampoImpuestoSiete() {
        return campoImpuestoSiete;
    }

    /**
     * @param campoImpuestoSiete the campoImpuestoSiete to set
     */
    public void setCampoImpuestoSiete(String campoImpuestoSiete) {
        this.campoImpuestoSiete = campoImpuestoSiete;
    }

    /**
     * @return the datoImpuestoSiete
     */
    public String getDatoImpuestoSiete() {
        return datoImpuestoSiete;
    }

    /**
     * @param datoImpuestoSiete the datoImpuestoSiete to set
     */
    public void setDatoImpuestoSiete(String datoImpuestoSiete) {
        this.datoImpuestoSiete = datoImpuestoSiete;
    }

    /**
     * @return the campoImpuestoOcho
     */
    public String getCampoImpuestoOcho() {
        return campoImpuestoOcho;
    }

    /**
     * @param campoImpuestoOcho the campoImpuestoOcho to set
     */
    public void setCampoImpuestoOcho(String campoImpuestoOcho) {
        this.campoImpuestoOcho = campoImpuestoOcho;
    }

    /**
     * @return the datoImpuestoOcho
     */
    public String getDatoImpuestoOcho() {
        return datoImpuestoOcho;
    }

    /**
     * @param datoImpuestoOcho the datoImpuestoOcho to set
     */
    public void setDatoImpuestoOcho(String datoImpuestoOcho) {
        this.datoImpuestoOcho = datoImpuestoOcho;
    }

    /**
     * @return the campoImpuestoNueve
     */
    public String getCampoImpuestoNueve() {
        return campoImpuestoNueve;
    }

    /**
     * @param campoImpuestoNueve the campoImpuestoNueve to set
     */
    public void setCampoImpuestoNueve(String campoImpuestoNueve) {
        this.campoImpuestoNueve = campoImpuestoNueve;
    }

    /**
     * @return the datoImpuestoNueve
     */
    public String getDatoImpuestoNueve() {
        return datoImpuestoNueve;
    }

    /**
     * @param datoImpuestoNueve the datoImpuestoNueve to set
     */
    public void setDatoImpuestoNueve(String datoImpuestoNueve) {
        this.datoImpuestoNueve = datoImpuestoNueve;
    }

    /**
     * @return the campoImpuestoDiez
     */
    public String getCampoImpuestoDiez() {
        return campoImpuestoDiez;
    }

    /**
     * @param campoImpuestoDiez the campoImpuestoDiez to set
     */
    public void setCampoImpuestoDiez(String campoImpuestoDiez) {
        this.campoImpuestoDiez = campoImpuestoDiez;
    }

    /**
     * @return the datoImpuestoDiez
     */
    public String getDatoImpuestoDiez() {
        return datoImpuestoDiez;
    }

    /**
     * @param datoImpuestoDiez the datoImpuestoDiez to set
     */
    public void setDatoImpuestoDiez(String datoImpuestoDiez) {
        this.datoImpuestoDiez = datoImpuestoDiez;
    }

    /**
     * @return the fechaConstitucion
     */
    public String getFechaConstitucion() {
        return fechaConstitucion;
    }

    /**
     * @param fechaConstitucion the fechaConstitucion to set
     */
    public void setFechaConstitucion(String fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    /**
     * @return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the accionVencimiento
     */
    public String getAccionVencimiento() {
        return accionVencimiento;
    }

    /**
     * @param accionVencimiento the accionVencimiento to set
     */
    public void setAccionVencimiento(String accionVencimiento) {
        this.accionVencimiento = accionVencimiento;
    }

    /**
     * @return the cuentaDeposito
     */
    public String getCuentaDeposito() {
        return cuentaDeposito;
    }

    /**
     * @param cuentaDeposito the cuentaDeposito to set
     */
    public void setCuentaDeposito(String cuentaDeposito) {
        this.cuentaDeposito = cuentaDeposito;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return the nroComprobante
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * @param nroComprobante the nroComprobante to set
     */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the interesesNetosImpuestos
     */
    public String getInteresesNetosImpuestos() {
        return interesesNetosImpuestos;
    }

    /**
     * @param interesesNetosImpuestos the interesesNetosImpuestos to set
     */
    public void setInteresesNetosImpuestos(String interesesNetosImpuestos) {
        this.interesesNetosImpuestos = interesesNetosImpuestos;
    }
    
    
    

}
