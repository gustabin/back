/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.merlin.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class ResultadoMerlinEntity.
 */
@Record
public class ResultadoMerlinWarningEntity {

    /** The header trama. */
    @Field
    private String headerTrama;

    /** The codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** The id sistema. */
    @Field
    private String idSistema;

    /** The cant desc status resultado. */
    @Field
    private Integer cantDescStatusResultado;

    /** The descripcion status resultado. */
    @Field(occursRef = "cantDescStatusResultado")
    private List<String> descripcionStatusResultado = new ArrayList<String>();

    /** The cpa. */
    @Field
    private String cpa;

    /** The provincia. */
    @Field
    private String provincia;

    /** The localidad. */
    @Field
    private String localidad;

    /** The cp 4. */
    @Field
    private String cp4;

    /** The barrio. */
    @Field
    private String barrio;

    /** The nombre calle. */
    @Field
    private String nombreCalle;

    /** The numero bloque. */
    @Field
    private String numeroBloque;

    /** The piso. */
    @Field
    private String piso;

    /** The departamento. */
    @Field
    private String departamento;

    /** The edificio. */
    @Field
    private String edificio;

    /** The motivo. */
    @Field
    private String motivo;

    /** The no alt. */
    @Field
    private String noAlt;

    /** The no cal. */
    @Field
    private String noCal;

    /** The no loc. */
    @Field
    private String noLoc;

    /** The no par. */
    @Field
    private String noPar;

    /** The no loc eq. */
    @Field
    private String noLocEq;

    /** The no loc ve. */
    @Field
    private String noLocVe;

    /** The delta CP. */
    @Field
    private String deltaCP;

    /** The ex match. */
    @Field
    private String exMatch;

    /** The cantidad de dudas. */
    @Field
    private Integer cantidadDeDudas;

    /** The dudas. */
    @Segment(occursRef = "cantidadDeDudas")
    private List<DudaEntity> dudas = new ArrayList<DudaEntity>();

    /**
     * Gets the codigo retorno extendido.
     *
     * @return the codigoRetornoExtendido
     */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
     * Sets the codigo retorno extendido.
     *
     * @param codigoRetornoExtendido
     *            the codigoRetornoExtendido to set
     */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
	 * Gets the id sistema.
	 *
	 * @return the id sistema
	 */
    public String getIdSistema() {
        return idSistema;
    }

    /**
	 * Sets the id sistema.
	 *
	 * @param idSistema
	 *            the new id sistema
	 */
    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    /**
	 * Gets the cant desc status resultado.
	 *
	 * @return the cant desc status resultado
	 */
    public Integer getCantDescStatusResultado() {
        return cantDescStatusResultado;
    }

    /**
	 * Sets the cant desc status resultado.
	 *
	 * @param cantDescStatusResultado
	 *            the new cant desc status resultado
	 */
    public void setCantDescStatusResultado(Integer cantDescStatusResultado) {
        this.cantDescStatusResultado = cantDescStatusResultado;
    }

    /**
	 * Gets the descripcion status resultado.
	 *
	 * @return the descripcion status resultado
	 */
    public List<String> getDescripcionStatusResultado() {
        return descripcionStatusResultado;
    }

    /**
	 * Sets the descripcion status resultado.
	 *
	 * @param descripcionStatusResultado
	 *            the new descripcion status resultado
	 */
    public void setDescripcionStatusResultado(List<String> descripcionStatusResultado) {
        this.descripcionStatusResultado = descripcionStatusResultado;
    }

    /**
     * Gets the header trama.
     *
     * @return the headerTrama
     */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the header trama.
     *
     * @param headerTrama
     *            the headerTrama to set
     */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
     * Gets the cpa.
     *
     * @return the cpa
     */
    public String getCpa() {
        return cpa;
    }

    /**
     * Sets the cpa.
     *
     * @param cpa
     *            the cpa to set
     */
    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    /**
     * Gets the provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia
     *            the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the localidad.
     *
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the localidad.
     *
     * @param localidad
     *            the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Gets the cp 4.
     *
     * @return the cp4
     */
    public String getCp4() {
        return cp4;
    }

    /**
     * Sets the cp 4.
     *
     * @param cp4
     *            the cp4 to set
     */
    public void setCp4(String cp4) {
        this.cp4 = cp4;
    }

    /**
     * Gets the barrio.
     *
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * Sets the barrio.
     *
     * @param barrio
     *            the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * Gets the nombre calle.
     *
     * @return the nombreCalle
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Sets the nombre calle.
     *
     * @param nombreCalle
     *            the nombreCalle to set
     */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    /**
     * Gets the numero bloque.
     *
     * @return the numeroBloque
     */
    public String getNumeroBloque() {
        return numeroBloque;
    }

    /**
     * Sets the numero bloque.
     *
     * @param numeroBloque
     *            the numeroBloque to set
     */
    public void setNumeroBloque(String numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    /**
     * Gets the piso.
     *
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the piso.
     *
     * @param piso
     *            the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Gets the departamento.
     *
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the departamento.
     *
     * @param departamento
     *            the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Gets the edificio.
     *
     * @return the edificio
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * Sets the edificio.
     *
     * @param edificio
     *            the edificio to set
     */
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    /**
     * Gets the motivo.
     *
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the motivo.
     *
     * @param motivo
     *            the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Gets the no alt.
     *
     * @return the noAlt
     */
    public String getNoAlt() {
        return noAlt;
    }

    /**
     * Sets the no alt.
     *
     * @param noAlt
     *            the noAlt to set
     */
    public void setNoAlt(String noAlt) {
        this.noAlt = noAlt;
    }

    /**
     * Gets the no cal.
     *
     * @return the noCal
     */
    public String getNoCal() {
        return noCal;
    }

    /**
     * Sets the no cal.
     *
     * @param noCal
     *            the noCal to set
     */
    public void setNoCal(String noCal) {
        this.noCal = noCal;
    }

    /**
     * Gets the no loc.
     *
     * @return the noLoc
     */
    public String getNoLoc() {
        return noLoc;
    }

    /**
     * Sets the no loc.
     *
     * @param noLoc
     *            the noLoc to set
     */
    public void setNoLoc(String noLoc) {
        this.noLoc = noLoc;
    }

    /**
     * Gets the no par.
     *
     * @return the noPar
     */
    public String getNoPar() {
        return noPar;
    }

    /**
     * Sets the no par.
     *
     * @param noPar
     *            the noPar to set
     */
    public void setNoPar(String noPar) {
        this.noPar = noPar;
    }

    /**
     * Gets the no loc eq.
     *
     * @return the noLocEq
     */
    public String getNoLocEq() {
        return noLocEq;
    }

    /**
     * Sets the no loc eq.
     *
     * @param noLocEq
     *            the noLocEq to set
     */
    public void setNoLocEq(String noLocEq) {
        this.noLocEq = noLocEq;
    }

    /**
     * Gets the no loc ve.
     *
     * @return the noLocVe
     */
    public String getNoLocVe() {
        return noLocVe;
    }

    /**
     * Sets the no loc ve.
     *
     * @param noLocVe
     *            the noLocVe to set
     */
    public void setNoLocVe(String noLocVe) {
        this.noLocVe = noLocVe;
    }

    /**
     * Gets the delta CP.
     *
     * @return the deltaCP
     */
    public String getDeltaCP() {
        return deltaCP;
    }

    /**
     * Sets the delta CP.
     *
     * @param deltaCP
     *            the deltaCP to set
     */
    public void setDeltaCP(String deltaCP) {
        this.deltaCP = deltaCP;
    }

    /**
     * Gets the ex match.
     *
     * @return the exMatch
     */
    public String getExMatch() {
        return exMatch;
    }

    /**
     * Sets the ex match.
     *
     * @param exMatch
     *            the exMatch to set
     */
    public void setExMatch(String exMatch) {
        this.exMatch = exMatch;
    }

    /**
     * Gets the cantidad de dudas.
     *
     * @return the cantidad de dudas
     */
    public Integer getCantidadDeDudas() {
        return cantidadDeDudas;
    }

    /**
     * Sets the cantidad de dudas.
     *
     * @param cantidadDeDudas
     *            the new cantidad de dudas
     */
    public void setCantidadDeDudas(Integer cantidadDeDudas) {
        this.cantidadDeDudas = cantidadDeDudas;
    }

    /**
     * Gets the dudas.
     *
     * @return the dudas
     */
    public List<DudaEntity> getDudas() {
        return dudas;
    }

    /**
     * Sets the dudas.
     *
     * @param dudas
     *            the dudas to set
     */
    public void setDudas(List<DudaEntity> dudas) {
        this.dudas = dudas;
    }

    /**
	 * To resultado merlin entity.
	 *
	 * @return the resultado merlin entity
	 */
    public ResultadoMerlinEntity toResultadoMerlinEntity() {
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        resultadoMerlinEntity.setHeaderTrama(headerTrama);
        resultadoMerlinEntity.setCodigoRetornoExtendido(codigoRetornoExtendido);
        resultadoMerlinEntity.setCpa(cpa);
        resultadoMerlinEntity.setProvincia(provincia);
        resultadoMerlinEntity.setLocalidad(localidad);
        resultadoMerlinEntity.setCp4(cp4);
        resultadoMerlinEntity.setBarrio(barrio);
        resultadoMerlinEntity.setNombreCalle(nombreCalle);
        resultadoMerlinEntity.setNumeroBloque(numeroBloque);
        resultadoMerlinEntity.setPiso(piso);
        resultadoMerlinEntity.setDepartamento(departamento);
        resultadoMerlinEntity.setEdificio(edificio);
        resultadoMerlinEntity.setMotivo(motivo);
        resultadoMerlinEntity.setNoAlt(noAlt);
        resultadoMerlinEntity.setNoCal(noCal);
        resultadoMerlinEntity.setNoLoc(noLoc);
        resultadoMerlinEntity.setNoPar(noPar);
        resultadoMerlinEntity.setNoLocEq(noLocEq);
        resultadoMerlinEntity.setNoLocVe(noLocVe);
        resultadoMerlinEntity.setDeltaCP(deltaCP);
        resultadoMerlinEntity.setExMatch(exMatch);
        resultadoMerlinEntity.setCantidadDeDudas(cantidadDeDudas);
        resultadoMerlinEntity.setDudas(dudas);
        return resultadoMerlinEntity;
    }
}