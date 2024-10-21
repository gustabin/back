package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.AuthCliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.IPAddress;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Class SesionCliente.
 *
 * @author B039636
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionCliente {

    /** The cliente. */
    private Cliente cliente;

    /** The tiene token RSA. */
    private Boolean tieneTokenRSA = Boolean.TRUE;

    /** The resumen cliente. */
    private ResumenCliente resumenCliente;

    /** items globales relacionados al cliente *. */
    private List<ItemMensajeRespuesta> itemsRespuesta;
    
    /** la ip del cliente. */
    private String ipCliente;
    
    /** The primer ingreso. */
    private Boolean primerIngreso = Boolean.TRUE;
    
    /** The invocoCtaPivote. */
    private Boolean invocoCtaPivote = Boolean.FALSE;
    
	private Boolean actualizacionAutomatica = Boolean.FALSE;
	
	private int tiempoActualizacion = 0;
	
	private AuthCliente authCliente = new AuthCliente();

	/**
     * Gets the items respuesta.
     *
     * @return the itemsRespuesta
     */
    public List<ItemMensajeRespuesta> getItemsRespuesta() {
        return itemsRespuesta;
    }

    /**
     * Sets the items respuesta.
     *
     * @param itemsRespuesta
     *            the itemsRespuesta to set
     */
    public void setItemsRespuesta(List<ItemMensajeRespuesta> itemsRespuesta) {
        this.itemsRespuesta = itemsRespuesta;
    }

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Setter para cliente.
     *
     * @param cliente
     *            the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the resumen cliente.
     *
     * @return the resumen cliente
     */
    public ResumenCliente getResumenCliente() {
        return resumenCliente;
    }

    /**
     * Setter para resumen cliente.
     *
     * @param resumenCliente
     *            el nuevo resumen cliente
     */
    public void setResumenCliente(ResumenCliente resumenCliente) {
        this.resumenCliente = resumenCliente;
    }

    /**
     * Este metodo se encarga de validar si pudieron recuperarse los token de
     * RSA cuando el usuario se loguea a la app.
     * 
     * <P>
     * Si no pudieron obtenerse los mismos, ya sea por Time Out u otro
     * impedimento, se debera devolver CHALLENGE para todas las operaciones
     * hasta que finalice la sesion y evitar que se consulte a RSA.
     * </P>
     * <P>
     * Si se pudieron recuperar, se deberan usar esos TOKENS cada vez que se
     * consulte a RSA por un desafio.
     * </P>
     * 
     *
     * @return the boolean
     */
    public Boolean getTieneTokenRSA() {
        return tieneTokenRSA;
    }

    /**
     * Sets the tiene token RSA.
     *
     * @param tieneToken
     *            the tieneToken to set
     */
    public void setTieneTokenRSA(Boolean tieneToken) {
        this.tieneTokenRSA = tieneToken;
    }

    /**
	 * Sets the ip cliente.
	 *
	 * @param ip
	 *            the new ip cliente
	 */
    public void setIpCliente(String ip) {
        this.ipCliente = ip;        
    }
    
    /**
	 * Gets the ip cliente.
	 *
	 * @return the ip cliente
	 */
    public String getIpCliente() {
        return ipCliente;
    }
    
    /**
     * Gets the primer ingreso.
     *
     * @return the primer ingreso
     */
    public Boolean getPrimerIngreso() {
		return primerIngreso;
	}

	/**
	 * Sets the primer ingreso.
	 *
	 * @param primerIngreso the new primer ingreso
	 */
	public void setPrimerIngreso(Boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}
		

	/**
	 * Gets the invoco cta pivote.
	 *
	 * @return the invoco cta pivote
	 */
	public Boolean getInvocoCtaPivote() {
		return invocoCtaPivote;
	}

	/**
	 * Sets the invoco cta pivote.
	 *
	 * @param invocoCtaPivote the new invoco cta pivote
	 */
	public void setInvocoCtaPivote(Boolean invocoCtaPivote) {
		this.invocoCtaPivote = invocoCtaPivote;
	}

	/**
	 * Obtener ip V 4 sin puntos.
	 *
	 * @return the string
	 */
	public String obtenerIpV4SinPuntos() {
        if (StringUtils.isNotBlank(this.ipCliente)) {
            if (IPAddress.isValidIPv4(this.ipCliente)) {
                String[] octetos = StringUtils.splitPreserveAllTokens(this.ipCliente, '.');
                StringBuilder ipv4 = new StringBuilder();
                for (String octeto : octetos) {
                    ipv4.append(StringUtils.leftPad(octeto, 3, '0'));
                }
                return ipv4.toString();
            } else if (IPAddress.isValidIPv6(this.ipCliente) && StringUtils.containsNone(this.ipCliente, '.')) {
                String[] grupos = StringUtils.splitPreserveAllTokens(this.ipCliente, ':');
                String grupo = StringUtils.leftPad(grupos[grupos.length-2], 4, '0') + StringUtils.leftPad(grupos[grupos.length-1], 4, '0');
                
                StringBuilder ipv4 = new StringBuilder();
                for (int i = 0; i < 8; i=i+2) {
                    ipv4.append(StringUtils.leftPad(String.valueOf(Integer.parseInt(StringUtils.substring(grupo, i, i+2), 16)), 3,'0'));
                }
                return ipv4.toString();
            }
        }
        return StringUtils.repeat('0', 12);
    }
	
	/**
	 * Gets the actualizacionAutomatica.
	 *
	 * @return the actualizacionAutomatica
	 */
	public Boolean getActualizacionAutomatica() {
		return actualizacionAutomatica;
	}

	/**
	 * Sets the actualizacionAutomatica.
	 *
	 * @param actualizacionAutomatica
	 */
	public void setActualizacionAutomatica(Boolean actualizacionAutomatica) {
		this.actualizacionAutomatica = actualizacionAutomatica;
	}
	
	/**
	 * Gets the tiempoActualizacion.
	 *
	 * @return the tiempoACtualización
	 */
	public int getTiempoActualizacion() {
		return tiempoActualizacion;
	}

	/**
	 * Sets the tiempoActualizacion.
	 *
	 * @param tiempoActualizacion
	 */
	public void setTiempoActualizacion(int tiempoActualizacion) {
		this.tiempoActualizacion = tiempoActualizacion;
	}

	/**
	 * @return the authCliente
	 */
	public AuthCliente getAuthCliente() {
		return authCliente;
	}

	/**
	 * @param authCliente the authCliente to set
	 */
	public void setAuthCliente(AuthCliente authCliente) {
		this.authCliente = authCliente;
	}

}
