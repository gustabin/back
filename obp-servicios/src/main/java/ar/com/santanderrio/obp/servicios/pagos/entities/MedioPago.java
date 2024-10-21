/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class MedioPago.
 */
public class MedioPago {
    /** The Constant RUBRO_FANTASIA. */
    public static final int RUBRO_FANTASIA = 0;

    /** The Constant NOMBRE_FANTASIA. */
    public static final int NOMBRE_FANTASIA = 1;

    /** The Constant CUIT. */
    public static final int POSICION_CUIT = 2;

    /** The Constant ADDI_HABILITADO. */
    public static final int ADDI_HABILITADO = 3;

    /** The Constant ADDI_SERVICIO. */
    public static final int ADDI_SERVICIO = 4;

    /** The Constant ADDI_GIFFACTURA. */
    public static final int ADDI_GIFFACTURA = 5;

    /** The Constant ADDI_LEYENDA_IDENTIFICACION. */
    public static final int ADDI_LEYENDA_IDENTIFICACION = 6;

    /** The Constant ADDI_EMPRESA. */
    public static final int ADDI_EMPRESA = 7;

    /** The Constant ADDI_LONGITUD. */
    public static final int ADDI_LONGITUD = 8;

    /** The Constant ADDI_FECHA_BAJA. */
    public static final int ADDI_FECHA_BAJA = 9;

    /** The Constant ADDI_MONEDA. */
    public static final int ADDI_MONEDA = 10;

    /** The Constant PES_HABILITADO. */
    public static final int PES_HABILITADO = 11;

    /** The Constant PES_PREPAGO. */
    public static final int PES_PREPAGO = 12;

    /** The Constant PES_FIID. */
    public static final int PES_FIID = 13;

    /** The Constant PES_GIF_FACTURA. */
    public static final int PES_GIF_FACTURA = 14;

    /** The Constant PES_CODIGO_RUBRO. */
    public static final int PES_CODIGO_RUBRO = 15;

    /** The Constant PES_DESCRIPCION_RUBRO. */
    public static final int PES_DESCRIPCION_RUBRO = 16;

    /** The Constant PES_TIPO_RUBRO. */
    public static final int PES_TIPO_RUBRO = 17;

    /** The Constant PES_ORDEN_RUBRO. */
    public static final int PES_ORDEN_RUBRO = 18;

    /** The Constant PES_DESCRIPCION_EMPRESA. */
    public static final int PES_DESCRIPCION_EMPRESA = 19;

    /** The Constant PES_IDENTIFICACION. */
    public static final int PES_IDENTIFICACION = 20;

    /** The Constant PES_TIPO_ADHESION. */
    public static final int PES_TIPO_ADHESION = 21;

    /** The Constant PES_TIPO_IMPORTE. */
    public static final int PES_TIPO_IMPORTE = 22;

    /** The Constant PES_TIPO_PAGO. */
    public static final int PES_TIPO_PAGO = 23;

    /** The Constant PES_CODIGO_RED_MONEDA. */
    public static final int PES_CODIGO_RED_MONEDA = 24;

    /** The Constant PES_DATOS_ADICIONALES. */
    public static final int PES_DATOS_ADICIONALES = 25;

    /** The Constant PES_CLAVE_SERIE. */
    public static final int PES_CLAVE_SERIE = 26;

    /** The Constant PES_LEYENDA_CLAVE. */
    public static final int PES_LEYENDA_CLAVE = 27;

    /** The Constant PES_LEYENDA_PIE. */
    public static final int PES_LEYENDA_PIE = 28;

    /** The Constant PES_TIPO_EMPRESA. */
    public static final int PES_TIPO_EMPRESA = 29;

    /** The Constant ADAM_HABILITADO. */
    public static final int ADAM_HABILITADO = 30;

    /** The Constant ADAM_CODIGO_SERVICIO. */
    public static final int ADAM_CODIGO_SERVICIO = 31;

    /** The Constant ADAM_GIF_FACTURA. */
    public static final int ADAM_GIF_FACTURA = 32;

    /** The Constant ADAM_LEYENDA_IDENTIFICACION. */
    public static final int ADAM_LEYENDA_IDENTIFICACION = 33;

    /** The Constant VISA_HABILITADA. */
    public static final int VISA_HABILITADA = 34;

    /** The Constant VISA_RUBRO. */
    public static final int VISA_RUBRO = 35;

    /** The Constant VISA_EMPRESA. */
    public static final int VISA_EMPRESA = 36;

    /** The Constant VISA_COD_ESTABLECIMIENTO. */
    public static final int VISA_COD_ESTABLECIMIENTO = 37;

    /** The Constant VISA_GIF_FACTURA. */
    public static final int VISA_GIF_FACTURA = 38;

    /** The Constant VISA_IDENTIFICADOR. */
    public static final int VISA_IDENTIFICADOR = 39;

    /** The Constant VISA_HELP_IDENTIFICADOR. */
    public static final int VISA_HELP_IDENTIFICADOR = 40;

    /** The Constant VISA_LONGITUD. */
    public static final int VISA_LONGITUD = 41;

    /** The Constant AMEX_HABILITADO. */
    public static final int AMEX_HABILITADO = 42;

    /** The Constant AMEX_RUBRO. */
    public static final int AMEX_RUBRO = 43;

    /** The Constant AMEX_EMPRESA. */
    public static final int AMEX_EMPRESA = 44;

    /** The Constant AMEX_GIF_FACTURA. */
    public static final int AMEX_GIF_FACTURA = 45;

    /** The Constant AMEX_IDENTIFICADOR. */
    public static final int AMEX_IDENTIFICADOR = 46;

    /** The Constant AMEX_HELP_IDENTIFICADOR. */
    public static final int AMEX_HELP_IDENTIFICADOR = 47;

    /** The Constant AMEX_LONGITUD. */
    public static final int AMEX_LONGITUD = 48;

    /** The Constant PPDCTA_HABILITADO. */
    public static final int PPDCTA_HABILITADO = 49;

    /** The Constant PPDCTA_IDEM_PACUERDO. */
    public static final int PPDCTA_ID_EMP_ACUERDO = 50;

    /** The Constant PPDCTA_COD_PROD_ACUERDO. */
    public static final int PPDCTA_COD_PROD_ACUERDO = 51;

    /** The Constant PPDCTA_NRO_INSTA_CUERDO. */
    public static final int PPDCTA_NRO_INSTA_ACUERDO = 52;

    /** The Constant PPDCTA_LIMITE_MAXIMO. */
    public static final int PPDCTA_LIMITE_MAXIMO = 53;

    /** The Constant PPDCTA_MODALIDAD. */
    public static final int PPDCTA_MODALIDAD = 54;

    /** The Constant PPDCTA_MONEDA. */
    public static final int PPDCTA_MONEDA = 55;

    /** The Constant PPDCTA_IDENTIFICADOR_PYRIP. */
    public static final int PPDCTA_IDENTIFICADOR_PYRIP = 56;

    /** The Constant PPDCTA_PAGO_PARCIAL. */
    public static final int PPDCTA_PAGO_PARCIAL = 57;

    /** The Constant PPDCTA_GIF_FACTURA. */
    public static final int PPDCTA_GIF_FACTURA = 58;

    /** The Constant PES_MODO_TC. */
    public static final int PES_MODO_TC = 59;

    /** The Constant PES_PA_HABILITADO. */
    public static final int PES_PA_HABILITADO = 60;

    /** The Constant PES_GIF_FACTURA_ELECTRONICA. */
    public static final int PES_GIF_FACTURA_ELECTRONICA = 61;

    /** The Constant MARCA_PAGO_TC. */
    public static final int MARCA_PAGO_TC = 62;

    /** The Constant MONTO_MAXIMO_PAGO_TC. */
    public static final int MONTO_MAXIMO_PAGO_TC = 63;

    /** The Constant MONTO_MINIMO_PAGO_TC. */
    public static final int MONTO_MINIMO_PAGO_TC = 64;

    /** The Constant DATOS_ADICIONALES. */
    public static final String DATOS_ADICIONALES = "datosAdicionales";

    /** The Constant CAMPO_BUSQUEDA. */
    public static final String CAMPO_BUSQUEDA = "campoBusqueda";

    /** The Constant RUBRO_FANTASIA_RECARGA_CELULARES. */
    public static final String RUBRO_FANTASIA_RECARGA_CELULARES = "RECARGA DE CELULARES";
    
    /** The Constant RUBRO_FANTASIA_RECARGA_CELULARES. */
    public static final String RUBRO_RECARGA_TRANSPORTE = "RECARGA TRANSPORTE";

    /** The rubro fantasia. */
    private String rubroFantasia;

    /** The nombre fantasia. */
    private String nombreFantasia;

    /** The cuit. */
    private String cuit;

    /** The tipo pago. */
    private Integer tipoPago;

    /** The tipo empresa. */
    private String tipoEmpresa;

    /** The tipo importe. */
    private String tipoImporte;

    /** The datos adicionales. */
    private String datosAdicionales;

    /** The fiid. */
    private String fiid;

    /** The pes prepago. */
    private String pesPrepago;

    /** The pes gif factura. */
    private String pesGifFactura;

    /** The pes habilitado. */
    private String pesHabilitado;

    /** The pes identificacion. */
    private String pesIdentificacion;

    /** The pes codigo rubro. */
    private String pescodigorubro;

    /** The moneda. */
    private String moneda;

    /** The servicio. */
    private String servicio;

    /** The pes PA habilitado. */
    private String pesPAHabilitado;

    /** The addi habilitado. */
    private String addiHabilitado;

    /** The addi leyenda identificacion. */
    private String addiLeyendaIdentificacion;

    /** The addi longitud. */
    private String addiLongitud;

    /** The addi gif factura. */
    private String addiGifFactura;

    /** The campo busqueda. */
    private String campoBusqueda;

    /** The marca pago tc. */
    private String marcaPagoTc;

    /** The monto maximo pago tc. */
    private String montoMaximoPagoTc;

    /** The monto minimo pago tc. */
    private String montoMinimoPagoTc;

    /** The visa habilitado. */
    private String visaHabilitado;

    /** The amex habilitado. */
    private String amexHabilitado;

    /** The amex identificador. */
    private String amexIdentificador;

    /** The amex gif factura. */
    private String amexGifFactura;

    /** The visa identificador. */
    private String visaIdentificador;

    /** The visa gif factura. */
    private String visaGifFactura;

    /** The visa cod establecimiento. */
    private String visaCodEstablecimiento;

    /** The ppdcta habilitado. */
    private String ppdctaHabilitado;

    /** The ppdcta id emp acuerdo. */
    private String ppdctaIdEmpAcuerdo;

    /** The ppdcta cod prod acuerdo. */
    private String ppdctaCodProdAcuerdo;

    /** The ppdcta nro insta cuerdo. */
    private String ppdctaNroInstaCuerdo;

    /** The ppdcta limite maximo. */
    private String ppdctaLimiteMaximo;

    /** The ppdcta modalidad. */
    private String ppdctaModalidad;

    /** The ppdcta moneda. */
    private String ppdctaMoneda;

    /** The ppdcta identificador pyrip. */
    private String ppdctaIdentificadorPyrip;

    /** The ppdcta pago parcial. */
    private String ppdctaPagoParcial;

    /**
     * Instantiates a new medio pago.
     */
    public MedioPago() {
        super();
        this.nombreFantasia = "";
    };

    /**
     * Instantiates a new medio pago.
     *
     * @param strLine
     *            the str line
     */
    public MedioPago(String strLine) {
        String delimiter = "|";
        String[] temp = StringUtils.split(strLine.replace("||", "| |").replace("||", "| |"), delimiter);

        this.rubroFantasia = temp[MedioPago.RUBRO_FANTASIA].trim();
        this.nombreFantasia = temp[MedioPago.NOMBRE_FANTASIA].trim();
        this.cuit = temp[MedioPago.POSICION_CUIT].trim();

        if (temp[MedioPago.PES_TIPO_PAGO].trim().length() > 0) {
            this.tipoPago = Integer.valueOf(temp[MedioPago.PES_TIPO_PAGO].trim());
        } else {
            tipoPago = null;
        }
        this.tipoEmpresa = temp[MedioPago.PES_TIPO_EMPRESA].trim();
        this.tipoImporte = temp[MedioPago.PES_TIPO_IMPORTE].trim();
        this.datosAdicionales = temp[MedioPago.PES_DATOS_ADICIONALES].trim();
        this.fiid = temp[MedioPago.PES_FIID].trim();
        this.pesPrepago = temp[MedioPago.PES_PREPAGO].trim();
        this.pesGifFactura = temp[MedioPago.PES_GIF_FACTURA].trim();
        this.pesHabilitado = temp[MedioPago.PES_HABILITADO].trim();
        this.pesIdentificacion = temp[MedioPago.PES_IDENTIFICACION].trim();
        this.moneda = temp[MedioPago.ADDI_MONEDA].trim();
        this.servicio = temp[MedioPago.ADDI_SERVICIO].trim();
        this.addiHabilitado = temp[MedioPago.ADDI_HABILITADO].trim();
        this.pesPAHabilitado = temp[MedioPago.PES_PA_HABILITADO].trim();
        this.addiLeyendaIdentificacion = temp[MedioPago.ADDI_LEYENDA_IDENTIFICACION].trim();
        this.addiLongitud = temp[MedioPago.ADDI_LONGITUD].trim();
        this.addiGifFactura = temp[MedioPago.ADDI_GIFFACTURA].trim();
        this.pescodigorubro = temp[MedioPago.PES_CODIGO_RUBRO].trim();
        this.marcaPagoTc = temp[MedioPago.MARCA_PAGO_TC].trim();
        this.montoMaximoPagoTc = temp[MedioPago.MONTO_MAXIMO_PAGO_TC].trim();
        this.montoMinimoPagoTc = temp[MedioPago.MONTO_MINIMO_PAGO_TC].trim();
        this.amexHabilitado = temp[MedioPago.AMEX_HABILITADO].trim();
        this.visaHabilitado = temp[MedioPago.VISA_HABILITADA].trim();
        this.amexIdentificador = temp[MedioPago.AMEX_IDENTIFICADOR].trim();
        this.amexGifFactura = temp[MedioPago.AMEX_GIF_FACTURA].trim();
        this.visaIdentificador = temp[MedioPago.VISA_IDENTIFICADOR].trim();
        this.visaGifFactura = temp[MedioPago.VISA_GIF_FACTURA].trim();
        this.visaCodEstablecimiento = temp[MedioPago.VISA_COD_ESTABLECIMIENTO].trim();
        this.ppdctaHabilitado = temp[MedioPago.PPDCTA_HABILITADO].trim();
        this.ppdctaIdEmpAcuerdo = temp[MedioPago.PPDCTA_ID_EMP_ACUERDO].trim();
        this.ppdctaCodProdAcuerdo = temp[MedioPago.PPDCTA_COD_PROD_ACUERDO].trim();
        this.ppdctaNroInstaCuerdo = temp[MedioPago.PPDCTA_NRO_INSTA_ACUERDO].trim();
        this.ppdctaLimiteMaximo = temp[MedioPago.PPDCTA_LIMITE_MAXIMO].trim();
        this.ppdctaModalidad = temp[MedioPago.PPDCTA_MODALIDAD].trim();
        this.ppdctaMoneda = temp[MedioPago.PPDCTA_MONEDA].trim();
        this.ppdctaIdentificadorPyrip = temp[MedioPago.PPDCTA_IDENTIFICADOR_PYRIP].trim();
        this.ppdctaPagoParcial = temp[MedioPago.PPDCTA_PAGO_PARCIAL].trim();

    }

    /**
     * Gets the rubro fantasia.
     *
     * @return the rubro fantasia
     */
    public String getRubroFantasia() {
        return rubroFantasia;
    }

    /**
     * Gets the nombre fantasia.
     *
     * @return the nombre fantasia
     */
    public String getNombreFantasia() {
        return nombreFantasia;
    }

    /**
     * Gets the cuit.
     *
     * @return the cuit
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Gets the tipo pago.
     *
     * @return the tipo pago
     */
    public Integer getTipoPago() {
        return tipoPago;
    }

    /**
     * Gets the campo busqueda.
     *
     * @return the campo busqueda
     */
    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    /**
     * Sets the campo busqueda.
     *
     * @param campoBusqueda
     *            the new campo busqueda
     */
    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }

    /**
     * Gets the tipo empresa.
     *
     * @return the tipo empresa
     */
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    /**
     * Gets the tipo importe.
     *
     * @return the tipo importe
     */
    public String getTipoImporte() {
        return tipoImporte;
    }

    /**
     * Gets the datos adicionales.
     *
     * @return the datos adicionales
     */
    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    /**
     * Gets the fiid.
     *
     * @return the fiid
     */
    public String getFiid() {
        return fiid;
    }

    /**
     * Gets the pes prepago.
     *
     * @return the pes prepago
     */
    public String getPesPrepago() {
        return pesPrepago;
    }

    /**
     * Gets the pes gif factura.
     *
     * @return the pes gif factura
     */
    public String getPesGifFactura() {
        return pesGifFactura;
    }

    /**
     * Gets the pes habilitado.
     *
     * @return the pes habilitado
     */
    public String getPesHabilitado() {
        return pesHabilitado;
    }

    /**
     * Gets the pes identificacion.
     *
     * @return the pes identificacion
     */
    public String getPesIdentificacion() {
        return pesIdentificacion;
    }

    /**
     * Sets the rubro fantasia.
     *
     * @param rubroFantasia
     *            the new rubro fantasia
     */
    public void setRubroFantasia(String rubroFantasia) {
        this.rubroFantasia = rubroFantasia;
    }

    /**
     * Sets the nombre fantasia.
     *
     * @param nombreFantasia
     *            the new nombre fantasia
     */
    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    /**
     * Sets the cuit.
     *
     * @param cuit
     *            the new cuit
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    /**
     * Sets the tipo pago.
     *
     * @param tipoPago
     *            the new tipo pago
     */
    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * Sets the tipo empresa.
     *
     * @param tipoEmpresa
     *            the new tipo empresa
     */
    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    /**
     * Sets the tipo importe.
     *
     * @param tipoImporte
     *            the new tipo importe
     */
    public void setTipoImporte(String tipoImporte) {
        this.tipoImporte = tipoImporte;
    }

    /**
     * Sets the datos adicionales.
     *
     * @param datosAdicionales
     *            the new datos adicionales
     */
    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    /**
     * Sets the fiid.
     *
     * @param fiid
     *            the new fiid
     */
    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    /**
     * Sets the pes prepago.
     *
     * @param pesPrepago
     *            the new pes prepago
     */
    public void setPesPrepago(String pesPrepago) {
        this.pesPrepago = pesPrepago;
    }

    /**
     * Sets the pes gif factura.
     *
     * @param pesGifFactura
     *            the new pes gif factura
     */
    public void setPesGifFactura(String pesGifFactura) {
        this.pesGifFactura = pesGifFactura;
    }

    /**
     * Sets the pes habilitado.
     *
     * @param pesHabilitado
     *            the new pes habilitado
     */
    public void setPesHabilitado(String pesHabilitado) {
        this.pesHabilitado = pesHabilitado;
    }

    /**
     * Sets the pes identificacion.
     *
     * @param pesIdentificacion
     *            the new pes identificacion
     */
    public void setPesIdentificacion(String pesIdentificacion) {
        this.pesIdentificacion = pesIdentificacion;
    }

    /**
     * Sets the moneda.
     *
     * @param moneda
     *            the new moneda
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * Sets the servicio.
     *
     * @param servicio
     *            the new servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Sets the pes PA habilitado.
     *
     * @param pesPAHabilitado
     *            the new pes PA habilitado
     */
    public void setPesPAHabilitado(String pesPAHabilitado) {
        this.pesPAHabilitado = pesPAHabilitado;
    }

    /**
     * Sets the addi habilitado.
     *
     * @param addiHabilitado
     *            the new addi habilitado
     */
    public void setAddiHabilitado(String addiHabilitado) {
        this.addiHabilitado = addiHabilitado;
    }

    /**
     * Sets the addi leyenda identificacion.
     *
     * @param addiLeyendaIdentificacion
     *            the new addi leyenda identificacion
     */
    public void setAddiLeyendaIdentificacion(String addiLeyendaIdentificacion) {
        this.addiLeyendaIdentificacion = addiLeyendaIdentificacion;
    }

    /**
     * Sets the addi longitud.
     *
     * @param addiLongitud
     *            the new addi longitud
     */
    public void setAddiLongitud(String addiLongitud) {
        this.addiLongitud = addiLongitud;
    }

    /**
     * Sets the addi gif factura.
     *
     * @param addiGifFactura
     *            the new addi gif factura
     */
    public void setAddiGifFactura(String addiGifFactura) {
        this.addiGifFactura = addiGifFactura;
    }

    /**
     * Gets the moneda.
     *
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Gets the servicio.
     *
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Gets the pes PA habilitado.
     *
     * @return the pes PA habilitado
     */
    public String getPesPAHabilitado() {
        return pesPAHabilitado;
    }

    /**
     * Gets the addi habilitado.
     *
     * @return the addi habilitado
     */
    public String getAddiHabilitado() {
        return addiHabilitado;
    }

    /**
     * Gets the addi leyenda identificacion.
     *
     * @return the addi leyenda identificacion
     */
    public String getAddiLeyendaIdentificacion() {
        return addiLeyendaIdentificacion;
    }

    /**
     * Gets the addi longitud.
     *
     * @return the addi longitud
     */
    public String getAddiLongitud() {
        return addiLongitud;
    }

    /**
     * Gets the addi gif factura.
     *
     * @return the addi gif factura
     */
    public String getAddiGifFactura() {
        return addiGifFactura;
    }

    /**
     * Gets the pescodigorubro.
     *
     * @return the pescodigorubro
     */
    public String getPescodigorubro() {
        return pescodigorubro;
    }

    /**
     * Sets the pescodigorubro.
     *
     * @param pescodigorubro
     *            the pescodigorubro to set
     */
    public void setPescodigorubro(String pescodigorubro) {
        this.pescodigorubro = pescodigorubro;
    }

    /**
     * Gets the marca pago tc.
     *
     * @return the marca pago tc
     */
    public String getMarcaPagoTc() {
        return marcaPagoTc;
    }

    /**
     * Sets the marca pago tc.
     *
     * @param marcaPagoTc
     *            the new marca pago tc
     */
    public void setMarcaPagoTc(String marcaPagoTc) {
        this.marcaPagoTc = marcaPagoTc;
    }

    /**
     * Gets the monto maximo pago tc.
     *
     * @return the monto maximo pago tc
     */
    public String getMontoMaximoPagoTc() {
        return montoMaximoPagoTc;
    }

    /**
     * Sets the monto maximo pago tc.
     *
     * @param montoMaximoPagoTc
     *            the new monto maximo pago tc
     */
    public void setMontoMaximoPagoTc(String montoMaximoPagoTc) {
        this.montoMaximoPagoTc = montoMaximoPagoTc;
    }

    /**
     * Gets the monto minimo pago tc.
     *
     * @return the monto minimo pago tc
     */
    public String getMontoMinimoPagoTc() {
        return montoMinimoPagoTc;
    }

    /**
     * Sets the monto minimo pago tc.
     *
     * @param montoMinimoPagoTc
     *            the new monto minimo pago tc
     */
    public void setMontoMinimoPagoTc(String montoMinimoPagoTc) {
        this.montoMinimoPagoTc = montoMinimoPagoTc;
    }

    /**
     * Gets the visa habilitado.
     *
     * @return the visa habilitado
     */
    public String getVisaHabilitado() {
        return visaHabilitado;
    }

    /**
     * Sets the visa habilitado.
     *
     * @param visaHabilitado
     *            the new visa habilitado
     */
    public void setVisaHabilitado(String visaHabilitado) {
        this.visaHabilitado = visaHabilitado;
    }

    /**
     * Gets the amex habilitado.
     *
     * @return the amex habilitado
     */
    public String getAmexHabilitado() {
        return amexHabilitado;
    }

    /**
     * Sets the amex habilitado.
     *
     * @param amexHabilitado
     *            the new amex habilitado
     */
    public void setAmexHabilitado(String amexHabilitado) {
        this.amexHabilitado = amexHabilitado;
    }

    /**
     * Gets the amex identificador.
     *
     * @return the amex identificador
     */
    public String getAmexIdentificador() {
        return amexIdentificador;
    }

    /**
     * Sets the amex identificador.
     *
     * @param amexIdentificador
     *            the new amex identificador
     */
    public void setAmexIdentificador(String amexIdentificador) {
        this.amexIdentificador = amexIdentificador;
    }

    /**
     * Gets the amex gif factura.
     *
     * @return the amex gif factura
     */
    public String getAmexGifFactura() {
        return amexGifFactura;
    }

    /**
     * Sets the amex gif factura.
     *
     * @param amexGifFactura
     *            the new amex gif factura
     */
    public void setAmexGifFactura(String amexGifFactura) {
        this.amexGifFactura = amexGifFactura;
    }

    /**
     * Gets the visa identificador.
     *
     * @return the visa identificador
     */
    public String getVisaIdentificador() {
        return visaIdentificador;
    }

    /**
     * Sets the visa identificador.
     *
     * @param visaIdentificador
     *            the new visa identificador
     */
    public void setVisaIdentificador(String visaIdentificador) {
        this.visaIdentificador = visaIdentificador;
    }

    /**
     * Gets the visa gif factura.
     *
     * @return the visa gif factura
     */
    public String getVisaGifFactura() {
        return visaGifFactura;
    }

    /**
     * Sets the visa gif factura.
     *
     * @param visaGifFactura
     *            the new visa gif factura
     */
    public void setVisaGifFactura(String visaGifFactura) {
        this.visaGifFactura = visaGifFactura;
    }

    /**
     * Gets the visa cod establecimiento.
     *
     * @return the visa cod establecimiento
     */
    public String getVisaCodEstablecimiento() {
        return visaCodEstablecimiento;
    }

    /**
     * Sets the visa cod establecimiento.
     *
     * @param visaCodEstablecimiento
     *            the new visa cod establecimiento
     */
    public void setVisaCodEstablecimiento(String visaCodEstablecimiento) {
        this.visaCodEstablecimiento = visaCodEstablecimiento;
    }

    /**
     * Gets the ppdcta habilitado.
     *
     * @return the ppdcta habilitado
     */
    public String getPpdctaHabilitado() {
        return ppdctaHabilitado;
    }

    /**
     * Sets the ppdcta habilitado.
     *
     * @param ppdctaHabilitado
     *            the new ppdcta habilitado
     */
    public void setPpdctaHabilitado(String ppdctaHabilitado) {
        this.ppdctaHabilitado = ppdctaHabilitado;
    }

    /**
     * Gets the ppdcta id emp acuerdo.
     *
     * @return the ppdcta id emp acuerdo
     */
    public String getPpdctaIdEmpAcuerdo() {
        return ppdctaIdEmpAcuerdo;
    }

    /**
     * Sets the ppdcta id emp acuerdo.
     *
     * @param ppdctaIdEmpAcuerdo
     *            the new ppdcta id emp acuerdo
     */
    public void setPpdctaIdEmpAcuerdo(String ppdctaIdEmpAcuerdo) {
        this.ppdctaIdEmpAcuerdo = ppdctaIdEmpAcuerdo;
    }

    /**
     * Gets the ppdcta cod prod acuerdo.
     *
     * @return the ppdcta cod prod acuerdo
     */
    public String getPpdctaCodProdAcuerdo() {
        return ppdctaCodProdAcuerdo;
    }

    /**
     * Sets the ppdcta cod prod acuerdo.
     *
     * @param ppdctaCodProdAcuerdo
     *            the new ppdcta cod prod acuerdo
     */
    public void setPpdctaCodProdAcuerdo(String ppdctaCodProdAcuerdo) {
        this.ppdctaCodProdAcuerdo = ppdctaCodProdAcuerdo;
    }

    /**
     * Gets the ppdcta nro insta cuerdo.
     *
     * @return the ppdcta nro insta cuerdo
     */
    public String getPpdctaNroInstaCuerdo() {
        return ppdctaNroInstaCuerdo;
    }

    /**
     * Sets the ppdcta nro insta cuerdo.
     *
     * @param ppdctaNroInstaCuerdo
     *            the new ppdcta nro insta cuerdo
     */
    public void setPpdctaNroInstaCuerdo(String ppdctaNroInstaCuerdo) {
        this.ppdctaNroInstaCuerdo = ppdctaNroInstaCuerdo;
    }

    /**
     * Gets the ppdcta limite maximo.
     *
     * @return the ppdcta limite maximo
     */
    public String getPpdctaLimiteMaximo() {
        return ppdctaLimiteMaximo;
    }

    /**
     * Sets the ppdcta limite maximo.
     *
     * @param ppdctaLimiteMaximo
     *            the new ppdcta limite maximo
     */
    public void setPpdctaLimiteMaximo(String ppdctaLimiteMaximo) {
        this.ppdctaLimiteMaximo = ppdctaLimiteMaximo;
    }

    /**
     * Gets the ppdcta modalidad.
     *
     * @return the ppdcta modalidad
     */
    public String getPpdctaModalidad() {
        return ppdctaModalidad;
    }

    /**
     * Sets the ppdcta modalidad.
     *
     * @param ppdctaModalidad
     *            the new ppdcta modalidad
     */
    public void setPpdctaModalidad(String ppdctaModalidad) {
        this.ppdctaModalidad = ppdctaModalidad;
    }

    /**
     * Gets the ppdcta moneda.
     *
     * @return the ppdcta moneda
     */
    public String getPpdctaMoneda() {
        return ppdctaMoneda;
    }

    /**
     * Sets the ppdcta moneda.
     *
     * @param ppdctaMoneda
     *            the new ppdcta moneda
     */
    public void setPpdctaMoneda(String ppdctaMoneda) {
        this.ppdctaMoneda = ppdctaMoneda;
    }

    /**
     * Gets the ppdcta identificador pyrip.
     *
     * @return the ppdcta identificador pyrip
     */
    public String getPpdctaIdentificadorPyrip() {
        return ppdctaIdentificadorPyrip;
    }

    /**
     * Sets the ppdcta identificador pyrip.
     *
     * @param ppdctaIdentificadorPyrip
     *            the new ppdcta identificador pyrip
     */
    public void setPpdctaIdentificadorPyrip(String ppdctaIdentificadorPyrip) {
        this.ppdctaIdentificadorPyrip = ppdctaIdentificadorPyrip;
    }

    /**
     * Gets the ppdcta pago parcial.
     *
     * @return the ppdcta pago parcial
     */
    public String getPpdctaPagoParcial() {
        return ppdctaPagoParcial;
    }

    /**
     * Sets the ppdcta pago parcial.
     *
     * @param ppdctaPagoParcial
     *            the new ppdcta pago parcial
     */
    public void setPpdctaPagoParcial(String ppdctaPagoParcial) {
        this.ppdctaPagoParcial = ppdctaPagoParcial;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cuit", getCuit()).append("campoBusqueda", getCampoBusqueda())
                .append("rubroFantasia", getRubroFantasia()).append("fiid", getFiid()).toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        MedioPago medioPago = (MedioPago) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.nombreFantasia, medioPago.getNombreFantasia()).append(this.rubroFantasia,
                medioPago.getRubroFantasia());
        return eb.isEquals();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(nombreFantasia).append(rubroFantasia);
        return hcb.toHashCode();
    }

}
