/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.codehaus.jackson.annotate.JsonBackReference;

import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Esta clase representa los datos de entrada a las operaciones de
 * autentificacion en curso.
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @see {@link TokenOperacionDTO}
 * @see {@link CoordenadasOperacionDTO}
 * @since Sep 28, 2016.
 */
public class AutentificacionDTO {

	/** The operacion. */
	private int operacion;

	/**
	 * Notificar RSA: se utiliza en
	 * AutentificacionManagerImpl#ejecutarMetodoAutenticacionNotificandoRSA()
	 * para guardar el valor a notificar a RSA.
	 */
	private boolean valorNotificarRSA = false;

	/**
	 * Esta propiedad se utiliza en
	 * AutentificacionManagerImpl#ejecutarMetodoAutenticacionNotificandoRSA()
	 * para almacenar si se agotaron los reintenos y notificar a rsa.
	 */
	private boolean reintentosAgotados = false;

	/** El dto que se utiliza para validar la operacion RSA. */

	@JsonBackReference
	private RsaDTO rsaDTO;

	/** The cliente DTO. */
	private ClienteDTO clienteDTO;

	/** The tipo desafio. */
	private TipoDesafioEnum tipoDesafio;

	/** The token operacion. */
	private TokenOperacionDTO tokenOperacion;

	/** The coordenadas operacion. */
	private CoordenadasOperacionDTO coordenadasOperacion;

	/** The banelco operacion. */
	private BanelcoOperacionDTO banelcoOperacion;

	/** The cvv2 operacion. */
	private Cvv2OperacionDTO cvv2Operacion;

	/** The codigo estadistica solicitud token. */
	private String codigoEstadisticaSolicitudToken;

	/** The codigo estadistica validacion token. */
	private String codigoEstadisticaValidacionToken;

	/** The codigo estadistica solicitud coordenadas. */
	private String codigoEstadisticaSolicitudCoordenadas;

	/** The codigo estadistica validacion coordenadas. */
	private String codigoEstadisticaValidacionCoordenadas;

	/** The codigo estadistica solicitud banelco. */
	private String codigoEstadisticaSolicitudBanelco;

	/** The codigo estadistica validacion banelco. */
	private String codigoEstadisticaValidacionBanelco;

	/** The codigo estadistica sin desafios. */
	private String codigoEstadisticaSinDesafios = null;

	/** The ejecutarRsa. */
	private boolean evitarRsa;
	
	private Boolean bloquearUsuario = Boolean.FALSE;
	
	private boolean noSetearDesafio = false;

	/**
	 * @return the bloquearUsuario
	 */
	public Boolean getBloquearUsuario() {
		return bloquearUsuario;
	}

	/**
	 * @param bloquearUsuario the bloquearUsuario to set
	 */
	public void setBloquearUsuario(Boolean bloquearUsuario) {
		this.bloquearUsuario = bloquearUsuario;
	}

	/**
     * Mantiene el actionCode de rsa si es que se consulto previamente en el mismo
     * flujo y se desea evitar la rellamada que sube le scoring.
     */
	@JsonIgnore
    private ActionCode cnsActionCodePrevioRsaAnalizar;

	/**
	 * Instantiates a new autentificacion DTO.
	 */
	public AutentificacionDTO() {
		super();
	}

	/**
	 * Instantiates a new autentificacion DTO.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @param operacion
	 *            the operacion
	 * @param coordenadasOperacion
	 *            the coordenadas operacion
	 */
	public AutentificacionDTO(RsaDTO rsaDTO, int operacion, CoordenadasOperacionDTO coordenadasOperacion) {
		super();
		this.rsaDTO = rsaDTO;
		this.operacion = operacion;
		this.coordenadasOperacion = coordenadasOperacion;
	}

	/**
	 * Instantiates a new autentificacion DTO.
	 *
	 * @param tipoDesafio
	 *            the tipo desafio
	 * @param tokenOperacion
	 *            the token operacion
	 */
	public AutentificacionDTO(TipoDesafioEnum tipoDesafio, TokenOperacionDTO tokenOperacion) {
		super();
		this.tipoDesafio = tipoDesafio;
		this.tokenOperacion = tokenOperacion;
	}

	/**
	 * Instantiates a new autentificacion DTO.
	 *
	 * @param tokenOperacion
	 *            the token operacion
	 * @param coordenadasOperacion
	 *            the coordenadas operacion
	 */
	public AutentificacionDTO(TokenOperacionDTO tokenOperacion, CoordenadasOperacionDTO coordenadasOperacion) {
		super();
		this.tokenOperacion = tokenOperacion;
		this.coordenadasOperacion = coordenadasOperacion;
	}

	/**
	 * Instantiates a new autentificacion DTO.
	 *
	 * @param tipoDesafio
	 *            the tipo desafio
	 * @param cvv2Operacion
	 *            the cvv2 operacion
	 */
	public AutentificacionDTO(TipoDesafioEnum tipoDesafio, Cvv2OperacionDTO cvv2Operacion) {
		super();
		this.tipoDesafio = tipoDesafio;
		this.cvv2Operacion = cvv2Operacion;
	}

	/**
	 * Gets the token operacion.
	 *
	 * @return the token operacion
	 */
	public TokenOperacionDTO getTokenOperacion() {
		return tokenOperacion;
	}

	/**
	 * Sets the token operacion.
	 *
	 * @param tokenOperacion
	 *            the new token operacion
	 */
	public void setTokenOperacion(TokenOperacionDTO tokenOperacion) {
		this.tokenOperacion = tokenOperacion;
	}

	/**
	 * Gets the coordenadas operacion.
	 *
	 * @return the coordenadas operacion
	 */
	public CoordenadasOperacionDTO getCoordenadasOperacion() {
		return coordenadasOperacion;
	}

	/**
	 * Sets the coordenadas operacion.
	 *
	 * @param coordenadasOperacion
	 *            the new coordenadas operacion
	 */
	public void setCoordenadasOperacion(CoordenadasOperacionDTO coordenadasOperacion) {
		this.coordenadasOperacion = coordenadasOperacion;
	}

	/**
	 * Gets the tipo desafio.
	 *
	 * @return the tipo desafio
	 */
	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the new tipo desafio
	 */
	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	/**
	 * Gets the banelco operacion.
	 *
	 * @return the banelco operacion
	 */
	public BanelcoOperacionDTO getBanelcoOperacion() {
		return banelcoOperacion;
	}

	/**
	 * Sets the banelco operacion.
	 *
	 * @param banelcoOperacion
	 *            the new banelco operacion
	 */
	public void setBanelcoOperacion(BanelcoOperacionDTO banelcoOperacion) {
		this.banelcoOperacion = banelcoOperacion;
	}

	/**
	 * Gets the cvv2 operacion.
	 *
	 * @return the cvv2 operacion
	 */
	public Cvv2OperacionDTO getCvv2Operacion() {
		return cvv2Operacion;
	}

	/**
	 * Sets the Cvv2 operacion.
	 *
	 * @param cvv2Operacion
	 *            the new cvv2 operacion
	 */
	public void setCvv2Operacion(Cvv2OperacionDTO cvv2Operacion) {
		this.cvv2Operacion = cvv2Operacion;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the new operacion
	 */
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the cliente DTO.
	 *
	 * @return the cliente DTO
	 */
	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	/**
	 * Sets the cliente DTO.
	 *
	 * @param clienteDTO
	 *            the new cliente DTO
	 */
	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	/**
	 * Get the valor notificar RSA.
	 *
	 * @return true, if successful
	 */
	public boolean getValorNotificarRSA() {
		return valorNotificarRSA;
	}

	/**
	 * Set the valor notificar RSA.
	 *
	 * @param valorNotificarRSA
	 *            the valor notificar RSA
	 * @return the valor notificar RSA
	 */
	public void setValorNotificarRSA(boolean valorNotificarRSA) {
		this.valorNotificarRSA = valorNotificarRSA;
	}

	/**
	 * Checks if is reintentos agotados.
	 *
	 * @return true, if is reintentos agotados
	 */
	public boolean isReintentosAgotados() {
		return reintentosAgotados;
	}

	/**
	 * Sets the reintentos agotados.
	 *
	 * @param reintentosAgotados
	 *            the new reintentos agotados
	 */
	public void setReintentosAgotados(boolean reintentosAgotados) {
		this.reintentosAgotados = reintentosAgotados;
	}

	/**
	 * Gets the rsa DTO.
	 *
	 * @return the rsa DTO
	 */
	public RsaDTO getRsaDTO() {
		return rsaDTO;
	}

	/**
	 * Sets the rsa DTO.
	 *
	 * @param rsaDTO
	 *            the new rsa DTO
	 */
	public void setRsaDTO(RsaDTO rsaDTO) {
		this.rsaDTO = rsaDTO;
	}

	/**
	 * Gets the codigo estadistica solicitud token.
	 *
	 * @return the codigo estadistica solicitud token
	 */
	public String getCodigoEstadisticaSolicitudToken() {
		return codigoEstadisticaSolicitudToken;
	}

	/**
	 * Sets the codigo estadistica solicitud token.
	 *
	 * @param codigoEstadisticaSolicitudToken
	 *            the new codigo estadistica solicitud token
	 */
	public void setCodigoEstadisticaSolicitudToken(String codigoEstadisticaSolicitudToken) {
		this.codigoEstadisticaSolicitudToken = codigoEstadisticaSolicitudToken;
	}

	/**
	 * Gets the codigo estadistica validacion token.
	 *
	 * @return the codigo estadistica validacion token
	 */
	public String getCodigoEstadisticaValidacionToken() {
		return codigoEstadisticaValidacionToken;
	}

	/**
	 * Sets the codigo estadistica validacion token.
	 *
	 * @param codigoEstadisticaValidacionToken
	 *            the new codigo estadistica validacion token
	 */
	public void setCodigoEstadisticaValidacionToken(String codigoEstadisticaValidacionToken) {
		this.codigoEstadisticaValidacionToken = codigoEstadisticaValidacionToken;
	}

	/**
	 * Gets the codigo estadistica solicitud coordenadas.
	 *
	 * @return the codigo estadistica solicitud coordenadas
	 */
	public String getCodigoEstadisticaSolicitudCoordenadas() {
		return codigoEstadisticaSolicitudCoordenadas;
	}

	/**
	 * Sets the codigo estadistica solicitud coordenadas.
	 *
	 * @param codigoEstadisticaSolicitudCoordenadas
	 *            the new codigo estadistica solicitud coordenadas
	 */
	public void setCodigoEstadisticaSolicitudCoordenadas(String codigoEstadisticaSolicitudCoordenadas) {
		this.codigoEstadisticaSolicitudCoordenadas = codigoEstadisticaSolicitudCoordenadas;
	}

	/**
	 * Gets the codigo estadistica validacion coordenadas.
	 *
	 * @return the codigo estadistica validacion coordenadas
	 */
	public String getCodigoEstadisticaValidacionCoordenadas() {
		return codigoEstadisticaValidacionCoordenadas;
	}

	/**
	 * Sets the codigo estadistica validacion coordenadas.
	 *
	 * @param codigoEstadisticaValidacionCoordenadas
	 *            the new codigo estadistica validacion coordenadas
	 */
	public void setCodigoEstadisticaValidacionCoordenadas(String codigoEstadisticaValidacionCoordenadas) {
		this.codigoEstadisticaValidacionCoordenadas = codigoEstadisticaValidacionCoordenadas;
	}

	/**
	 * Gets the codigo estadistica solicitud banelco.
	 *
	 * @return the codigo estadistica solicitud banelco
	 */
	public String getCodigoEstadisticaSolicitudBanelco() {
		return codigoEstadisticaSolicitudBanelco;
	}

	/**
	 * Sets the codigo estadistica solicitud banelco.
	 *
	 * @param codigoEstadisticaSolicitudBanelco
	 *            the new codigo estadistica solicitud banelco
	 */
	public void setCodigoEstadisticaSolicitudBanelco(String codigoEstadisticaSolicitudBanelco) {
		this.codigoEstadisticaSolicitudBanelco = codigoEstadisticaSolicitudBanelco;
	}

	/**
	 * Gets the codigo estadistica validacion banelco.
	 *
	 * @return the codigo estadistica validacion banelco
	 */
	public String getCodigoEstadisticaValidacionBanelco() {
		return codigoEstadisticaValidacionBanelco;
	}

	/**
	 * Sets the codigo estadistica validacion banelco.
	 *
	 * @param codigoEstadisticaValidacionBanelco
	 *            the new codigo estadistica validacion banelco
	 */
	public void setCodigoEstadisticaValidacionBanelco(String codigoEstadisticaValidacionBanelco) {
		this.codigoEstadisticaValidacionBanelco = codigoEstadisticaValidacionBanelco;
	}

	/**
	 * Gets the codigo estadistica sin desafios.
	 *
	 * @return the codigo estadistica sin desafios
	 */
	public String getCodigoEstadisticaSinDesafios() {
		return codigoEstadisticaSinDesafios;
	}

	/**
	 * Sets the codigo estadistica sin desafios.
	 *
	 * @param codigoEstadisticaSinDesafios
	 *            the new codigo estadistica sin desafios
	 */
	public void setCodigoEstadisticaSinDesafios(String codigoEstadisticaSinDesafios) {
		this.codigoEstadisticaSinDesafios = codigoEstadisticaSinDesafios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AutentificacionDTO [operacion=" + operacion + ", valorNotificarRSA=" + valorNotificarRSA
				+ ", reintentosAgotados=" + reintentosAgotados + ", rsaDTO=" + rsaDTO + ", clienteDTO=" + clienteDTO
				+ ", tipoDesafio=" + tipoDesafio + ", tokenOperacion=" + tokenOperacion + ", coordenadasOperacion="
				+ coordenadasOperacion + ", banelcoOperacion=" + banelcoOperacion + "]";
	}

    /**
     * @return the cnsActionCodePrevioRsaAnalizar
     */
    public ActionCode getCnsActionCodePrevioRsaAnalizar() {
        return cnsActionCodePrevioRsaAnalizar;
    }

    /**
     * @param cnsActionCodePrevioRsaAnalizar the cnsActionCodePrevioRsaAnalizar to set
     */
    public void setCnsActionCodePrevioRsaAnalizar(ActionCode cnsActionCodePrevioRsaAnalizar) {
        this.cnsActionCodePrevioRsaAnalizar = cnsActionCodePrevioRsaAnalizar;
    }

	/**
	 * @return the ejecutarRsa
	 */
	public boolean isEvitarRsa() {
		return evitarRsa;
	}

	/**
	 * @param ejecutarRsa the ejecutarRsa to set
	 */
	public void setEvitarRsa(boolean ejecutarRsa) {
		this.evitarRsa = ejecutarRsa;
	}

	public boolean getNoSetearDesafio() {
		return noSetearDesafio;
	}

	public void setNoSetearDesafio(boolean noSetearDesafio) {
		this.noSetearDesafio = noSetearDesafio;
	}	
	
}
