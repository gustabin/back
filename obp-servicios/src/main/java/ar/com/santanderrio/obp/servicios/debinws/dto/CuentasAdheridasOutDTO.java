/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;

/**
 * The Class CuentasAdheridasOutDTO.
 */
public class CuentasAdheridasOutDTO {
    
    /** cuentasAdheridas. */
    private List<CuentaDebinDTO> cuentasAdheridas;

    /**
	 * Gets the cuentas adheridas.
	 *
	 * @return the cuentasAdheridas
	 */
    public List<CuentaDebinDTO> getCuentasAdheridas() {
        return cuentasAdheridas;
    }

    /**
	 * Sets the cuentas adheridas.
	 *
	 * @param cuentasAdheridas
	 *            the cuentasAdheridas to set
	 */
    public void setCuentasAdheridas(List<CuentaDebinDTO> cuentasAdheridas) {
        this.cuentasAdheridas = cuentasAdheridas;
    }
    

}
