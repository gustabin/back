/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesPagoSimpleMultipleView;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The Class ComprobantePagoHaberesPagoSimpleMultipleEntity.
 */

public class ComprobantePagoHaberesPagoSimpleMultipleEntity extends RsaDTOParaDesafio {

    /** Serial version. */
    private static final long serialVersionUID = -669013589087613301L;

    /**
     * Instantiates a new comprobante pago haberes pago simple multiple entity.
     */
    public ComprobantePagoHaberesPagoSimpleMultipleEntity() {
        super(OperacionesRSAEnum.PAGODESUELDOS);
    }

    /** The datos Empleado Pago Haberes. */
    private List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> pagos = new ArrayList<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();

    /** The todosPagosOk. */
    private boolean todosPagosOk;

    /** The pagoHaberesSimpleMultipleView. */
    private PagoHaberesPagoSimpleMultipleView pagoHaberesPagoSimpleMultipleView;

    /** The fecha hora. */
    private String fechaHora;

    /** The legales SEO. */
    private String legalesSEO;

    /** The Modo ejecucion. */
    private Boolean modoEjecucion;

    /**
     * Gets the pagos.
     *
     * @return the pagos
     */
    public List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> getPagos() {
        return pagos;
    }

    /**
     * Sets the pagos.
     *
     * @param pagos
     *            the pagos to set
     */
    public void setPagos(List<DatosEmpleadoPagoHaberesSimpleMultipleEntity> pagos) {
        this.pagos = pagos;
    }

    /**
     * Checks if is todos pagos ok.
     *
     * @return the todosPagosOk
     */
    public boolean isTodosPagosOk() {
        return todosPagosOk;
    }

    /**
     * Sets the todos pagos ok.
     *
     * @param todosPagosOk
     *            the todosPagosOk to set
     */
    public void setTodosPagosOk(boolean todosPagosOk) {
        this.todosPagosOk = todosPagosOk;
    }

    /**
     * Gets the pago haberes pago simple multiple view.
     *
     * @return the pagoHaberesPagoSimpleMultipleView
     */
    public PagoHaberesPagoSimpleMultipleView getPagoHaberesPagoSimpleMultipleView() {
        return pagoHaberesPagoSimpleMultipleView;
    }

    /**
     * Sets the pago haberes pago simple multiple view.
     *
     * @param pagoHaberesPagoSimpleMultipleView
     *            the pagoHaberesPagoSimpleMultipleView to set
     */
    public void setPagoHaberesPagoSimpleMultipleView(
            PagoHaberesPagoSimpleMultipleView pagoHaberesPagoSimpleMultipleView) {
        this.pagoHaberesPagoSimpleMultipleView = pagoHaberesPagoSimpleMultipleView;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Gets the legales SEO.
     *
     * @return the legalesSEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * Sets the legales SEO.
     *
     * @param legalesSEO
     *            the legalesSEO to set
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }

    /**
     * Gets the modo ejecucion.
     *
     * @return the modoEjecucion
     */
    public Boolean getModoEjecucion() {
        return modoEjecucion;
    }

    /**
     * Sets the modo ejecucion.
     *
     * @param modoEjecucion
     *            the modoEjecucion to set
     */
    public void setModoEjecucion(Boolean modoEjecucion) {
        this.modoEjecucion = modoEjecucion;
    }
    @JsonIgnore
    public boolean isPagoSimple(){
       return this.pagoHaberesPagoSimpleMultipleView.getPagoHaberesEmpleadosView().size()==1;
    }

}