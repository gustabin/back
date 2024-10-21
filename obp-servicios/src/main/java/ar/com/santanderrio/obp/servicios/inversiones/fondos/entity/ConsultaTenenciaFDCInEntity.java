/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;

/**
 * The Class ConsultaTenenciaFDCInEntity.
 */
public class ConsultaTenenciaFDCInEntity {

    
    /** The Constant CINCO_CEROS. */
    private static final String CINCO_CEROS = "00000";
    
    /** The Constant CLIENTE_EXCITI. */
    private static final String CLIENTE_EXCITI = "S";

    /** The tipo papel enum. */
    private TipoPapel tipoPapelEnum;

    /** The especie codigo. */
    private String especieCodigo = CINCO_CEROS;
    
    /** The cliente exciti. */
    private String clienteExciti = CLIENTE_EXCITI;

    /** The fecha periodo. */
    // FORMATO YYYYMMDD
    private String fechaPeriodo;

    /** The hora periodo. */
    // FORMATO (HH:MM:SS)
    private String horaPeriodo;

    /** The sucursal cuenta list. */
    private List<SucursalCuentaEntity> sucursalCuentaList;

    /**
	 * Gets the tipo papel enum.
	 *
	 * @return the tipo papel enum
	 */
    public TipoPapel getTipoPapelEnum() {
        return tipoPapelEnum;
    }

    /**
	 * Sets the tipo papel enum.
	 *
	 * @param tipoPapelEnum
	 *            the new tipo papel enum
	 */
    public void setTipoPapelEnum(TipoPapel tipoPapelEnum) {
        this.tipoPapelEnum = tipoPapelEnum;
    }

    /**
	 * Gets the especie codigo.
	 *
	 * @return the especie codigo
	 */
    public String getEspecieCodigo() {
        return especieCodigo;
    }

    /**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the new especie codigo
	 */
    public void setEspecieCodigo(String especieCodigo) {
        this.especieCodigo = especieCodigo;
    }

    /**
	 * Gets the cliente exciti.
	 *
	 * @return the cliente exciti
	 */
    public String getClienteExciti() {
        return clienteExciti;
    }

    /**
	 * Sets the cliente exciti.
	 *
	 * @param clienteExciti
	 *            the new cliente exciti
	 */
    public void setClienteExciti(String clienteExciti) {
        this.clienteExciti = clienteExciti;
    }

    /**
	 * Gets the fecha periodo.
	 *
	 * @return the fecha periodo
	 */
    public String getFechaPeriodo() {
        return fechaPeriodo;
    }

    /**
	 * Sets the fecha periodo.
	 *
	 * @param fechaPeriodo
	 *            the new fecha periodo
	 */
    public void setFechaPeriodo(String fechaPeriodo) {
        this.fechaPeriodo = fechaPeriodo;
    }

    /**
	 * Gets the hora periodo.
	 *
	 * @return the hora periodo
	 */
    public String getHoraPeriodo() {
        return horaPeriodo;
    }

    /**
	 * Sets the hora periodo.
	 *
	 * @param horaPeriodo
	 *            the new hora periodo
	 */
    public void setHoraPeriodo(String horaPeriodo) {
        this.horaPeriodo = horaPeriodo;
    }

    /**
	 * Gets the sucursal cuenta list.
	 *
	 * @return the sucursal cuenta list
	 */
    public List<SucursalCuentaEntity> getSucursalCuentaList() {
        return sucursalCuentaList;
    }

    /**
	 * Sets the sucursal cuenta list.
	 *
	 * @param sucursalCuentaList
	 *            the new sucursal cuenta list
	 */
    public void setSucursalCuentaList(List<SucursalCuentaEntity> sucursalCuentaList) {
        this.sucursalCuentaList = sucursalCuentaList;
    }
    
}
