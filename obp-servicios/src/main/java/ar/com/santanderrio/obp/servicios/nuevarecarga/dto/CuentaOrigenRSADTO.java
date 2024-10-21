/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class CuentaOrigenRSADTO.
 *
 * @author florencia.n.martinez
 */
public class CuentaOrigenRSADTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The saldo. */
    private Long saldo;

    /** The numero. */
    private String numero;

    /** The nombre cliente. */
    private String nombreCliente;

    /**
     * Instantiates a new cuenta origen RSADTO.
     */
    public CuentaOrigenRSADTO() {
        super();
    }

    /**
     * Instantiates a new cuenta origen RSADTO.
     *
     * @param saldo
     *            the saldo
     * @param numero
     *            the numero
     * @param nombreCliente
     *            the nombre cliente
     */
    public CuentaOrigenRSADTO(Long saldo, String numero, String nombreCliente) {
        this.saldo = saldo;
        this.numero = numero;
        this.nombreCliente = nombreCliente;
    }

    /**
     * Instantiates a new cuenta origen RSADTO.
     *
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     */
    public CuentaOrigenRSADTO(Cuenta cuenta, DivisaEnum divisa) {
        if (cuenta.esTarjetaDeCredito()) {
            this.numero = TarjetaUtils.obtenerNroTarjetaConFormato(cuenta);
            List<Cuenta> cuentas = cuenta.getCliente().getCuentasMonetarias();
            if (!cuentas.isEmpty()) {
                List<Cuenta> cuentasOrdenadas = CuentasUtils.ordenarCuentasMonetarias(cuentas);
                this.saldo = obtenerSaldoCuentaOrigen(cuentasOrdenadas.get(0).obtenerSaldo());
            } else {
                this.saldo = 0l;
            }
        } else {
            this.numero = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
            if (DivisaEnum.PESO.equals(divisa)) {
                this.saldo = obtenerSaldoCuentaOrigen(cuenta.obtenerSaldo());
            } else {
                this.saldo = obtenerSaldoCuentaOrigen(cuenta.obtenerSaldoDolares());
            }
        }
        this.nombreCliente = obtenerNombreApellido(cuenta.getCliente());
    }

    /**
     * Obtiene el saldo en la cuenta origen.
     *
     * @param saldoCuentaOrigen
     *            the saldo cuenta origen
     * @return the long
     */
    private Long obtenerSaldoCuentaOrigen(String saldoCuentaOrigen) {
        return Long.valueOf(StringUtils.replace(StringUtils.replace(saldoCuentaOrigen, ".", ""), ",", ""));
    }

    /**
     * Obtiene el nombre y el apellido del cliente.
     *
     * @param cliente
     *            the cliente
     * @return the string
     */
    private String obtenerNombreApellido(Cliente cliente) {
        return (cliente.getNombre()).trim().concat(" ").concat((cliente.getApellido1()).trim());
    }

    /**
     * Gets the saldo.
     *
     * @return the saldo
     */
    public Long getSaldo() {
        return saldo;
    }

    /**
     * Sets the saldo.
     *
     * @param saldo
     *            the new saldo
     */
    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero
     *            the new numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the nombre cliente.
     *
     * @return the nombre cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Sets the nombre cliente.
     *
     * @param nombreCliente
     *            the new nombre cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(nombreCliente);
        hcb.append(numero);
        hcb.append(saldo);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
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
        CuentaOrigenRSADTO other = (CuentaOrigenRSADTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(nombreCliente, other.getNombreCliente());
        eb.append(numero, other.getNumero());
        eb.append(saldo, other.getSaldo());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("saldo", saldo).append("numero", numero)
                .append("nombreCliente", nombreCliente).toString();
    }

}
