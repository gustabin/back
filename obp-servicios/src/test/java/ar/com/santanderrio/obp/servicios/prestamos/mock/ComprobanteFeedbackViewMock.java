package ar.com.santanderrio.obp.servicios.prestamos.mock;

import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class ComprobanteFeedbackViewMock.
 *
 * @author florencia.n.martinez
 */
public final class ComprobanteFeedbackViewMock {

    /**
     * Instantiates a new comprobante feedback view mock.
     */
    private ComprobanteFeedbackViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /** The Constant MENSAJE_FEEDBACK. */
    private static final String MENSAJE_FEEDBACK = "<p>Tu solicitud de <b>Super Préstamo Personal N° 154009652136598745</b> por <b>$ 40.000,00</b> en <b>36 cuotas</b> ha sido realizada exitosamente</p>";
    
    /** The Constant LEGAL_SEUO. */
    private static final String LEGAL_SEUO = "Conserve este ticket como comprobante S.E.U.O.";
    
    /**
     * Obtiene el comprobante/feedback OK de una solicitud de un prestamo.
     *
     * @return the comprobante feedback view
     */
    public static ComprobanteFeedbackView obtenerComprobanteFeedbackOKSolicitudPrestamo() {
        ComprobanteFeedbackView comprobanteFeedbackView = new ComprobanteFeedbackView();
        comprobanteFeedbackView.setMensajeFeedback(MENSAJE_FEEDBACK);
        comprobanteFeedbackView.setNroDeComprobante("006541254785");
        comprobanteFeedbackView.setFechaHora("12/05/2016 - 23:12");
        comprobanteFeedbackView.setLegalesSEUO(LEGAL_SEUO);
        return comprobanteFeedbackView;
    }

}