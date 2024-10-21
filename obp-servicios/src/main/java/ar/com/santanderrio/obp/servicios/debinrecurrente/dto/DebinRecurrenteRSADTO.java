package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import java.io.Serializable;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

public class DebinRecurrenteRSADTO extends RsaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The operacion. */
    private int operacion;

    /** Se guarda en sesion si hay un desafio en curso. */
    private boolean existeDesafioEnCurso = false;

    /** The metodo desafio. */
    private AutentificacionDTO desafio;

    /** The tipo desafio. */
    private TipoDesafioEnum tipoDesafio;

	private String cuitVendedor;

	private String cuitComprador;

	private String concepto;

	private String descripcion;

	private String prestacion;

	private String moneda;

	private Integer cantDiasUltimoCambioClave;

	private Integer cantDiasUltimoCambioToken;

	private String tipoSegmentoCliente;

	private boolean mismoTitular;

	private boolean vendedorPersonaJuridica;


	public DebinRecurrenteRSADTO() {
		super(OperacionesRSAEnum.DEBIN_RECURRENTE);
	}
    
	public DebinRecurrenteRSADTO(int operacion) {
		this();
		this.operacion = operacion;
	}

	
	public int getOperacion() {
		return operacion;
	}

	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	public boolean isExisteDesafioEnCurso() {
		return existeDesafioEnCurso;
	}

	public void setExisteDesafioEnCurso(boolean existeDesafioEnCurso) {
		this.existeDesafioEnCurso = existeDesafioEnCurso;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	public String getCuitVendedor() {
		return cuitVendedor;
	}

	public void setCuitVendedor(String cuitVendedor) {
		this.cuitVendedor = cuitVendedor;
	}

	public String getCuitComprador() {
		return cuitComprador;
	}

	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}


	public String getTipoSegmentoCliente() {
		return tipoSegmentoCliente;
	}

	public void setTipoSegmentoCliente(String tipoSegmentoCliente) {
		this.tipoSegmentoCliente = tipoSegmentoCliente;
	}

	public boolean isMismoTitular() {
		return mismoTitular;
	}

	public void setMismoTitular(boolean mismoTitular) {
		this.mismoTitular = mismoTitular;
	}

	public boolean isVendedorPersonaJuridica() {
		return vendedorPersonaJuridica;
	}

	public void setVendedorPersonaJuridica(boolean vendedorPersonaJuridica) {
		this.vendedorPersonaJuridica = vendedorPersonaJuridica;
	}
}
