package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dto;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
@JsonSerialize(include = Inclusion.NON_NULL)
public class MepRsaDTO extends RsaDTO{
	
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Se guarda en sesion si hay un desafio en curso. */
    private boolean existeDesafioEnCurso = false;

    /** The metodo desafio. */
    private AutentificacionDTO desafio;

    /** The tipo desafio. */
    private TipoDesafioEnum tipoDesafio;

    /** The especie MEP (SecID del bono). */       
    private String Especie_USDMEP;
    
    /** Monto de la solicitud en usd. */       
    private Float amount;
    
    /** Cuenta debito */       
    private String account;

    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;
    
    private static final String ESPECIE = "9519";

    public MepRsaDTO() {
		super(OperacionesRSAEnum.NUEVO_PAGO);
		// TODO Auto-generated constructor stub
	}

   

    public MepRsaDTO(Boolean especieDefault, String especieCustom) {
        this();
        if(especieDefault){
        	this.setEspecie_USDMEP(ESPECIE);
        }else{
        	this.setEspecie_USDMEP(especieCustom);
        }                
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

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(desafio);
        hcb.append(existeDesafioEnCurso);        
        hcb.append(tipoDesafio);        
        return hcb.toHashCode();
    }

   

	public String getEspecie_USDMEP() {
		return Especie_USDMEP;
	}



	public void setEspecie_USDMEP(String especie_USDMEP) {
		Especie_USDMEP = especie_USDMEP;
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public Float getAmount() {
		return amount;
	}



	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
