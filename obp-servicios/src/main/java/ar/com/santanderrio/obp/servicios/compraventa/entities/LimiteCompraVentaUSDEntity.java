package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class LimiteCompraVentaUSDEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The tipoDocumento. */
	@Field
	private String tipoDocumento;

	/** The nroDocumento. */
	@Field
	private String nroDocumento;

	/** The fechaNacimiento. */
	@Field
	private String fechaNacimiento;

	/** The sexo. */
	@Field
	private String sexo;

	/** The tipoOperacion. */
	@Field
	private String tipoOperacion;

	/** The importeDolar. */
	@Field
	private String importeDolar;

	/** The importeDolarAumVta. */
	@Field
	private String importeDolarAumVta;

	/** The importeDolarAumCpr. */
	@Field
	private String importeDolarAumCpr;

	/** The importeDispVta. */
	@Field
	private String importeDispVta;

	/** The importeDispCpr. */
	@Field
	private String importeDispCpr;

	/** The relacion. */
	@Field
	private String relacion;

	/** The alertas. */
	@Field
	private String alertas;

	/** The impDispAyudaFlia. */
	@Field
	private String impDispAyudaFlia;

	/** The impDispIntrum. */
	@Field
	private String impDispIntru;

	/** The importeDispRepatriacion. */
	@Field
	private String importeDispRepatriacion;

	/** The impRepaUsado. */
	@Field
	private String impRepaUsado;

	/** The afectaRepatriacion. */
	@Field
	private String afectaRepatriacion;
	

	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getImporteDolar() {
		return importeDolar;
	}

	public void setImporteDolar(String importeDolar) {
		this.importeDolar = importeDolar;
	}

	public String getImporteDolarAumVta() {
		return importeDolarAumVta;
	}

	public void setImporteDolarAumVta(String importeDolarAumVta) {
		this.importeDolarAumVta = importeDolarAumVta;
	}

	public String getImporteDolarAumCpr() {
		return importeDolarAumCpr;
	}

	public void setImporteDolarAumCpr(String importeDolarAumCpr) {
		this.importeDolarAumCpr = importeDolarAumCpr;
	}

	public String getImporteDispVta() {
		return importeDispVta;
	}

	public void setImporteDispVta(String importeDispVta) {
		this.importeDispVta = importeDispVta;
	}

	public String getImporteDispCpr() {
		return importeDispCpr;
	}

	public void setImporteDispCpr(String importeDispCpr) {
		this.importeDispCpr = importeDispCpr;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public String getAlertas() {
		return alertas;
	}

	public void setAlertas(String alertas) {
		this.alertas = alertas;
	}

	public String getImpDispAyudaFlia() {
		return impDispAyudaFlia;
	}

	public void setImpDispAyudaFlia(String impDispAyudaFlia) {
		this.impDispAyudaFlia = impDispAyudaFlia;
	}

	public String getImpDispIntru() {
		return impDispIntru;
	}

	public void setImpDispIntru(String impDispIntru) {
		this.impDispIntru = impDispIntru;
	}

	public String getImporteDispRepatriacion() {
		return importeDispRepatriacion;
	}

	public void setImporteDispRepatriacion(String importeDispRepatriacion) {
		this.importeDispRepatriacion = importeDispRepatriacion;
	}

	public String getImpRepaUsado() {
		return impRepaUsado;
	}

	public void setImpRepaUsado(String impRepaUsado) {
		this.impRepaUsado = impRepaUsado;
	}

	public String getAfectaRepatriacion() {
		return afectaRepatriacion;
	}

	public void setAfectaRepatriacion(String afectaRepatriacion) {
		this.afectaRepatriacion = afectaRepatriacion;
	}

}
