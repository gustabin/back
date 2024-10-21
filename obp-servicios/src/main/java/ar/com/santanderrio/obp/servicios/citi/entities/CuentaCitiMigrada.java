/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class CuentaCitiMigrada.
 */
public class CuentaCitiMigrada {

    /** The cod retorno extendido. */
    private String codRetornoExtendido;

    /** The entidad. */
    private String entidad;
    
    /** The sucursal. */
    private String sucursal;
    
    /** The cuenta. */
    private String cuenta;
    
    /** The divisa. */
    private String divisa;
  
    /** The aplicion BSR. */
    private String aplicionBSR;
  
    /** The sucursal BSR. */
    private String sucursalBSR;
    
    /** The cuenta BSR. */
    private String cuentaBSR;
    
    /** The cuenta formato CBU. */
    private String cuentaFormatoCBU;
    
    /** The cod producto. */
    private String codProducto;
    
    /** The cod subproducto. */
    private String codSubproducto;
    
    /** The indicador paquete. */
    private String indicadorPaquete;
    
    /** The cod producto contable. */
    private String codProductoContable;
    
    /** The cod subproducto contable. */
    private String codSubproductoContable;
    
    /** The indicador sobregiro. */
    private String indicadorSobregiro;
    
    /** The cant cuentas citi. */
    private Integer cantCuentasCiti;
    
    /** The cuentas citi. */
    private List<CuentaCitiEntity> cuentasCiti = new ArrayList<CuentaCitiEntity>();

    /**
     * Gets the cod retorno extendido.
     *
     * @return the cod retorno extendido
     */
    public String getCodRetornoExtendido() {
        return codRetornoExtendido;
    }

    /**
     * Sets the cod retorno extendido.
     *
     * @param codRetornoExtendido the new cod retorno extendido
     */
    public void setCodRetornoExtendido(String codRetornoExtendido) {
        this.codRetornoExtendido = codRetornoExtendido;
    }

    /**
     * Gets the entidad.
     *
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Sets the entidad.
     *
     * @param entidad the new entidad
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * Gets the sucursal.
     *
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Sets the sucursal.
     *
     * @param sucursal the new sucursal
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Sets the cuenta.
     *
     * @param cuenta the new cuenta
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Gets the divisa.
     *
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Sets the divisa.
     *
     * @param divisa the new divisa
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    /**
     * Gets the aplicion BSR.
     *
     * @return the aplicion BSR
     */
    public String getAplicionBSR() {
        return aplicionBSR;
    }

    /**
     * Sets the aplicion BSR.
     *
     * @param aplicionBSR the new aplicion BSR
     */
    public void setAplicionBSR(String aplicionBSR) {
        this.aplicionBSR = aplicionBSR;
    }

    /**
     * Gets the sucursal BSR.
     *
     * @return the sucursal BSR
     */
    public String getSucursalBSR() {
        return sucursalBSR;
    }

    /**
     * Sets the sucursal BSR.
     *
     * @param sucursalBSR the new sucursal BSR
     */
    public void setSucursalBSR(String sucursalBSR) {
        this.sucursalBSR = sucursalBSR;
    }

    /**
     * Gets the cuenta BSR.
     *
     * @return the cuenta BSR
     */
    public String getCuentaBSR() {
        return cuentaBSR;
    }

    /**
     * Sets the cuenta BSR.
     *
     * @param cuentaBSR the new cuenta BSR
     */
    public void setCuentaBSR(String cuentaBSR) {
        this.cuentaBSR = cuentaBSR;
    }

    /**
     * Gets the cuenta formato CBU.
     *
     * @return the cuenta formato CBU
     */
    public String getCuentaFormatoCBU() {
        return cuentaFormatoCBU;
    }

    /**
     * Sets the cuenta formato CBU.
     *
     * @param cuentaFormatoCBU the new cuenta formato CBU
     */
    public void setCuentaFormatoCBU(String cuentaFormatoCBU) {
        this.cuentaFormatoCBU = cuentaFormatoCBU;
    }

    /**
     * Gets the cod producto.
     *
     * @return the cod producto
     */
    public String getCodProducto() {
        return codProducto;
    }

    /**
     * Sets the cod producto.
     *
     * @param codProducto the new cod producto
     */
    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    /**
     * Gets the cod subproducto.
     *
     * @return the cod subproducto
     */
    public String getCodSubproducto() {
        return codSubproducto;
    }

    /**
     * Sets the cod subproducto.
     *
     * @param codSubproducto the new cod subproducto
     */
    public void setCodSubproducto(String codSubproducto) {
        this.codSubproducto = codSubproducto;
    }

    /**
     * Gets the indicador paquete.
     *
     * @return the indicador paquete
     */
    public String getIndicadorPaquete() {
        return indicadorPaquete;
    }

    /**
     * Sets the indicador paquete.
     *
     * @param indicadorPaquete the new indicador paquete
     */
    public void setIndicadorPaquete(String indicadorPaquete) {
        this.indicadorPaquete = indicadorPaquete;
    }

    /**
     * Gets the cod producto contable.
     *
     * @return the cod producto contable
     */
    public String getCodProductoContable() {
        return codProductoContable;
    }

    /**
     * Sets the cod producto contable.
     *
     * @param codProductoContable the new cod producto contable
     */
    public void setCodProductoContable(String codProductoContable) {
        this.codProductoContable = codProductoContable;
    }

    /**
     * Gets the cod subproducto contable.
     *
     * @return the cod subproducto contable
     */
    public String getCodSubproductoContable() {
        return codSubproductoContable;
    }

    /**
     * Sets the cod subproducto contable.
     *
     * @param codSubproductoContable the new cod subproducto contable
     */
    public void setCodSubproductoContable(String codSubproductoContable) {
        this.codSubproductoContable = codSubproductoContable;
    }

    /**
     * Gets the indicador sobregiro.
     *
     * @return the indicador sobregiro
     */
    public String getIndicadorSobregiro() {
        return indicadorSobregiro;
    }

    /**
     * Sets the indicador sobregiro.
     *
     * @param indicadorSobregiro the new indicador sobregiro
     */
    public void setIndicadorSobregiro(String indicadorSobregiro) {
        this.indicadorSobregiro = indicadorSobregiro;
    }

    /**
     * Gets the cant cuentas citi.
     *
     * @return the cant cuentas citi
     */
    public Integer getCantCuentasCiti() {
        return cantCuentasCiti;
    }

    /**
     * Sets the cant cuentas citi.
     *
     * @param cantCuentasCiti the new cant cuentas citi
     */
    public void setCantCuentasCiti(Integer cantCuentasCiti) {
        this.cantCuentasCiti = cantCuentasCiti;
    }

    /**
     * Gets the cuentas citi.
     *
     * @return the cuentas citi
     */
    public List<CuentaCitiEntity> getCuentasCiti() {
        return cuentasCiti;
    }

    /**
     * Sets the cuentas citi.
     *
     * @param cuentasCiti the new cuentas citi
     */
    public void setCuentasCiti(List<CuentaCitiEntity> cuentasCiti) {
        this.cuentasCiti = cuentasCiti;
    }

}
