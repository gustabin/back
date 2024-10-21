package ar.com.santanderrio.obp.servicios.echeq.dto;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EcheqRSADTO  extends RsaDTO {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The operacion. */
    private int operacion;

    /** The monto. */
    private Long monto;

    /** The monto. */
    private String chequeId;

    /** The cuitEmison. */
    private String cuitEmisor;

    /** The cuitBeneficiario. */
    private String cuitBeneficiario;

    /** Se guarda en sesion si hay un desafio en curso. */
    private boolean existeDesafioEnCurso = false;

    /** The metodo desafio. */
    private AutentificacionDTO desafio;

    /** The tipo desafio. */
    private TipoDesafioEnum tipoDesafio;

    /** The tipo desafio. */
    private OperacionEcheqEnum operacionEcheqEnum;

    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;


    public EcheqRSADTO() {
        super(OperacionesRSAEnum.ECHEQ_ALTA);
    }

    public EcheqRSADTO(int operacion, Long monto, String chequeId, String cuitEmisor, String cuitBeneficiario, OperacionEcheqEnum operacionEcheqEnum) {
        this();
        this.operacion = operacion;
        this.monto = monto;
        this.cuitEmisor = cuitEmisor;
        this.cuitBeneficiario = cuitBeneficiario;
        this.chequeId = chequeId;
        this.operacionEcheqEnum = operacionEcheqEnum;
    }

    public OperacionEcheqEnum getOperacionEcheqEnum() {
        return operacionEcheqEnum;
    }

    public void setOperacionEcheqEnum(OperacionEcheqEnum operacionEcheqEnum) {
        this.operacionEcheqEnum = operacionEcheqEnum;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getChequeId() {
        return chequeId;
    }

    public void setChequeId(String chequeId) {
        this.chequeId = chequeId;
    }

    public String getCuitEmisor() {
        return cuitEmisor;
    }

    public void setCuitEmisor(String cuitEmisor) {
        this.cuitEmisor = cuitEmisor;
    }

    public String getCuitBeneficiario() {
        return cuitBeneficiario;
    }

    public void setCuitBeneficiario(String cuitBeneficiario) {
        this.cuitBeneficiario = cuitBeneficiario;
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
        hcb.append(monto);
        hcb.append(operacion);
        hcb.append(tipoDesafio);
        hcb.append(cuitEmisor);
        hcb.append(cuitBeneficiario);
        hcb.append(chequeId);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EcheqRSADTO other = (EcheqRSADTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(desafio, other.getDesafio());
        eb.append(existeDesafioEnCurso, other.isExisteDesafioEnCurso());
        eb.append(monto, other.getMonto());
        eb.append(operacion, other.getOperacion());
        eb.append(tipoDesafio, other.getTipoDesafio());
        eb.append(cuitEmisor, other.getCuitEmisor());
        eb.append(cuitBeneficiario, other.getCuitBeneficiario());
        eb.append(chequeId, other.getChequeId());
        return eb.isEquals();
    }

    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("operacion", operacion).append("monto", monto)
                .append("existeDesafioEnCurso", existeDesafioEnCurso).append("desafio", desafio)
                .append("tipoDesafio", tipoDesafio).append(cuitEmisor, cuitEmisor)
                .append("cuitBeneficiario", cuitBeneficiario).append("chequeId", chequeId).toString();
    }

}
