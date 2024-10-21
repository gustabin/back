/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class PagoTarjetaInEntity.
 *
 * @author florencia.n.martinez
 */
public class PagoTarjetaInEntity extends PagoInEntity {

    /** The modo alta. */
    @Pattern(regexp = "0")
    private String modoAlta;

    /** The cuotas. */
    @Pattern(regexp = "01")
    private String cuotas;

    /** The codigo. */
    @Pattern(regexp = "04|02")
    private String codigo;

    /** The vto. */
    @Pattern(regexp = "[0-9| ]{4}")
    private String vto;

    /** The nombre. */
    
    private String nombre;

    /** The sexo. */
    @Pattern(regexp = "0|1")
    private String sexo;

    /** The fec nac. */
    @Pattern(regexp = "[0-9]{8}")
    private String fecNac;

    /** The txnsps. */
    @Pattern(regexp = "[ ]{10}")
    private String txnsps;

    /** The nrocom. */
    @Pattern(regexp = "[ ]{8}")
    private String nrocom;

    /** The usrfld. */
    @Pattern(regexp = "[ ]{21}")
    private String usrfld;

    /**
     * Instantiates a new pago tarjeta in entity.
     *
     * @param pagoInEntity
     *            the pago in entity
     * @param cliente
     *            the cliente
     */
    public PagoTarjetaInEntity(PagoInEntity pagoInEntity, Cliente cliente) {
        this.setCodigoEmpresa(StringUtils.rightPad(pagoInEntity.getCodigoEmpresa(), 4, " "));
        this.setIdentificacion(StringUtils.rightPad(pagoInEntity.getIdentificacion(), 19, " "));
        this.setTipoMonto(pagoInEntity.getTipoMonto());
        this.setTipoSeleccion(pagoInEntity.getTipoSeleccion());
        this.setNumeroFactura(StringUtils.isNotBlank(pagoInEntity.getNumeroFactura())
                ? StringUtils.rightPad(pagoInEntity.getNumeroFactura(), 20, " ")
                : StringUtils.rightPad("", 20, " "));
        this.setTipoCuenta("31");
        this.setNroTarjeta(pagoInEntity.getNroTarjeta());
        this.setMoneda(pagoInEntity.getMoneda());
        this.setMonto(
                ISBANStringUtils.formateadorConCerosIzq(pagoInEntity.getMonto().replace(".", "").replace(",", ""), 14));
        this.modoAlta = "0";
        this.cuotas = "01";
        this.codigo = obtenerCodigo(pagoInEntity.getTipoCuentaTC());
        this.vto = StringUtils.defaultIfBlank(pagoInEntity.getVencimientoTarjeta(), "    ");
        this.nombre = StringUtils.rightPad(StringUtils.deleteWhitespace(cliente.getNombre()) + " "
                + StringUtils.deleteWhitespace(cliente.getApellido1()), 30);
        this.sexo = obtenerSexo(cliente.getSexo());
        this.fecNac = StringUtils.substring(cliente.getFechaNacimiento(), 6, 8)
                .concat(StringUtils.substring(cliente.getFechaNacimiento(), 4, 6))
                .concat(StringUtils.substring(cliente.getFechaNacimiento(), 0, 4));
        this.txnsps = StringUtils.repeat(" ", 10);
        this.nrocom = StringUtils.repeat(" ", 8);
        this.usrfld = StringUtils.repeat(" ", 21);
    }

    /**
     * Obtener sexo.
     *
     * @param genero
     *            the genero
     * @return the string
     */
    private String obtenerSexo(String genero) {
        if (StringUtils.equals(genero, "H")) {
            return "0";
        }
        if (StringUtils.equals(genero, "M")) {
            return "1";
        }
        return null;
    }

    /**
     * Obtener codigo.
     *
     * @param tipoCuentaTC
     *            the tipo cuenta TC
     * @return the string
     */
    private String obtenerCodigo(Integer tipoCuentaTC) {
        if (tipoCuentaTC == 7) {
            return "04";
        }
        if (tipoCuentaTC == 42) {
            return "02";
        }
        return null;
    }

    /**
     * Gets the modo alta.
     *
     * @return the modoAlta
     */
    public String getModoAlta() {
        return modoAlta;
    }

    /**
     * Sets the modo alta.
     *
     * @param modoAlta
     *            the modoAlta to set
     */
    public void setModoAlta(String modoAlta) {
        this.modoAlta = modoAlta;
    }

    /**
     * Gets the cuotas.
     *
     * @return the cuotas
     */
    public String getCuotas() {
        return cuotas;
    }

    /**
     * Sets the cuotas.
     *
     * @param cuotas
     *            the cuotas to set
     */
    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the vto.
     *
     * @return the vto
     */
    public String getVto() {
        return vto;
    }

    /**
     * Sets the vto.
     *
     * @param vto
     *            the vto to set
     */
    public void setVto(String vto) {
        this.vto = vto;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the sexo.
     *
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the sexo.
     *
     * @param sexo
     *            the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Gets the fec nac.
     *
     * @return the fecNac
     */
    public String getFecNac() {
        return fecNac;
    }

    /**
     * Sets the fec nac.
     *
     * @param fecNac
     *            the fecNac to set
     */
    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    /**
     * Gets the txnsps.
     *
     * @return the txnsps
     */
    public String getTxnsps() {
        return txnsps;
    }

    /**
     * Sets the txnsps.
     *
     * @param txnsps
     *            the txnsps to set
     */
    public void setTxnsps(String txnsps) {
        this.txnsps = txnsps;
    }

    /**
     * Gets the nrocom.
     *
     * @return the nrocom
     */
    public String getNrocom() {
        return nrocom;
    }

    /**
     * Sets the nrocom.
     *
     * @param nrocom
     *            the nrocom to set
     */
    public void setNrocom(String nrocom) {
        this.nrocom = nrocom;
    }

    /**
     * Gets the usrfld.
     *
     * @return the usrfld
     */
    public String getUsrfld() {
        return usrfld;
    }

    /**
     * Sets the usrfld.
     *
     * @param usrfld
     *            the usrfld to set
     */
    public void setUsrfld(String usrfld) {
        this.usrfld = usrfld;
    }

}
