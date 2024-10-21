package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class PromocionDto {
	
	@JsonProperty("de_producto_comercial")
	public String deProductoComercial;
    
	@JsonProperty("fe_suscripcion")
	public String feSuscripcion;
	@JsonProperty("cd_ramo")
	public int cdRamo;
	@JsonProperty("nu_poliza")
    public long nuPoliza;
	@JsonProperty("nu_certificado")
	public long nuCertificado;
	@JsonProperty("cd_producto")
	public String cdProducto;
	@JsonProperty("cd_plan")
    public String cdPlan;
	@JsonProperty("nu_prioridad")
	public int nuPrioridad;
	@JsonProperty("fe_desde_promocion")
    public String feDesdePromocion;
	@JsonProperty("fe_hasta_promocion")
    public String feHastaPromocion;
	@JsonProperty("nu_periodo_vigencia")
    public int nuPeriodoVigencia;
	@JsonProperty("de_categoria")
    public String deCategoria;
	@JsonProperty("cd_tipo_beneficio")
    public String cdTipoBeneficio;
	@JsonProperty("de_beneficio")
    public String deBeneficio;
	@JsonProperty("po_beneficio")
    public String poBeneficio;
	@JsonProperty("de_legales")
    public String deLegales;
	@JsonProperty("de_link_detalle")
    public String deLinkDetalle;
	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder("Promocion detalle: \n");
		 sb.append("de_producto_comercial: ").append(deProductoComercial).append("\n");
		 sb.append("cd_ramo: ").append(cdRamo).append("\n");
		 sb.append("nu_poliza: ").append(nuPoliza).append("\n");
		 sb.append("nu_certificado: ").append(nuCertificado).append("\n");
		 sb.append("cd_producto: ").append(cdProducto).append("\n");
		 sb.append("cd_plan: ").append(cdPlan).append("\n");
		 sb.append("nu_prioridad: ").append(nuPrioridad).append("\n");
		 sb.append("fe_desde_promocion: ").append(feDesdePromocion).append("\n");
		 sb.append("fe_hasta_promocion: ").append(feHastaPromocion).append("\n");
		 sb.append("nu_periodo_vigencia: ").append(nuPeriodoVigencia).append("\n");
		 sb.append("de_categoria: ").append(deCategoria).append("\n");
		 sb.append("cd_tipo_beneficio: ").append(cdTipoBeneficio).append("\n");
		 sb.append("de_beneficio: ").append(deBeneficio).append("\n");
		 sb.append("po_beneficio: ").append(poBeneficio).append("\n");
		 sb.append("de_legales: ").append(deLegales).append("\n");
		 sb.append("de_link_detalle: ").append(deLinkDetalle).append("\n");
		 return sb.toString();
	}
	public String getDeProductoComercial() {
		return deProductoComercial;
	}
	public void setDeProductoComercial(String deProductoComercial) {
		this.deProductoComercial = deProductoComercial;
	}
	public String getFeSuscripcion() {
		return feSuscripcion;
	}
	public void setFeSuscripcion(String feSuscripcion) {
		this.feSuscripcion = feSuscripcion;
	}
	public int getCdRamo() {
		return cdRamo;
	}
	public void setCdRamo(int cdRamo) {
		this.cdRamo = cdRamo;
	}
	public long getNuPoliza() {
		return nuPoliza;
	}
	public void setNuPoliza(long nuPoliza) {
		this.nuPoliza = nuPoliza;
	}
	public long getNuCertificado() {
		return nuCertificado;
	}
	public void setNuCertificado(long nuCertificado) {
		this.nuCertificado = nuCertificado;
	}
	public String getCdProducto() {
		return cdProducto;
	}
	public void setCdProducto(String cdProducto) {
		this.cdProducto = cdProducto;
	}
	public String getCdPlan() {
		return cdPlan;
	}
	public void setCdPlan(String cdPlan) {
		this.cdPlan = cdPlan;
	}
	public int getNuPrioridad() {
		return nuPrioridad;
	}
	public void setNuPrioridad(int nuPrioridad) {
		this.nuPrioridad = nuPrioridad;
	}
	public String getFeDesdePromocion() {
		return feDesdePromocion;
	}
	public void setFeDesdePromocion(String feDesdePromocion) {
		this.feDesdePromocion = feDesdePromocion;
	}
	public String getFeHastaPromocion() {
		return feHastaPromocion;
	}
	public void setFeHastaPromocion(String feHastaPromocion) {
		this.feHastaPromocion = feHastaPromocion;
	}
	public int getNuPeriodoVigencia() {
		return nuPeriodoVigencia;
	}
	public void setNuPeriodoVigencia(int nuPeriodoVigencia) {
		this.nuPeriodoVigencia = nuPeriodoVigencia;
	}
	public String getDeCategoria() {
		return deCategoria;
	}
	public void setDeCategoria(String deCategoria) {
		this.deCategoria = deCategoria;
	}
	public String getCdTipoBeneficio() {
		return cdTipoBeneficio;
	}
	public void setCdTipoBeneficio(String cdTipoBeneficio) {
		this.cdTipoBeneficio = cdTipoBeneficio;
	}
	public String getDeBeneficio() {
		return deBeneficio;
	}
	public void setDeBeneficio(String deBeneficio) {
		this.deBeneficio = deBeneficio;
	}
	public String getPoBeneficio() {
		return poBeneficio;
	}
	public void setPoBeneficio(String poBeneficio) {
		this.poBeneficio = poBeneficio;
	}
	public String getDeLegales() {
		return deLegales;
	}
	public void setDeLegales(String deLegales) {
		this.deLegales = deLegales;
	}
	public String getDeLinkDetalle() {
		return deLinkDetalle;
	}
	public void setDeLinkDetalle(String deLinkDetalle) {
		this.deLinkDetalle = deLinkDetalle;
	}
	
	
	
	

    
}
