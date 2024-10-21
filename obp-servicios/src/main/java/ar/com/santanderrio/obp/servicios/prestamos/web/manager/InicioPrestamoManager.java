/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;

/**
 * Gestiona la logica relacionada a Prestamo.
 * 
 * @author
 *
 */
public interface InicioPrestamoManager {

	/**
	 * obtiene calificacion crediticia para mostrar en cabecera.
	 *
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<CabeceraPrestamosView> obtenerCabecera();

	/**
	 * Metodo que obtiene los prestamos para rellenar la grilla de prestamos de
	 * acuerdo al filtro que se le pase.
	 *
	 * @param obtenerPrestamoInView
	 *            the obtener prestamo in view
	 * @return Respuesta<PrestamoView>
	 * @Input ObtenerPrestamoView
	 */
	Respuesta<PrestamosView> obtenerPrestamos(ObtenerPrestamoInView obtenerPrestamoInView);


	/**
	 * Metodo que obtiene los prestamos para rellenar la grilla de prestamos encolados
	 *
	 * @return Respuesta<Void>
	 */
	Respuesta<PrestamosEncoladosView> obtenerPrestamosEncolados();

	/**
	 * Obtener prestamos stop debit.
	 *
	 * @param confirmacionStopDebitView the confirmacion stop debit view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteStopDebitPrestamoView> confirmarPrestamosStopDebit(ConfirmacionStopDebitView confirmacionStopDebitView);
	
	
	
	/**
	 * Obtener prestamo sueldos.
	 *
	 * @param obtenerPrestamoInView the obtener prestamo in view
	 * @return the respuesta
	 */
	Respuesta<PrestamoSueldosView> obtenerPrestamoSueldos(ObtenerPrestamoInView obtenerPrestamoInView);
	
	
	/**
	 * Adjuntar archivo prestamo sueldos.
	 *
	 * @param documentacionAdjuntaView the documentacion adjunta view
	 * @return the respuesta
	 */
	Respuesta<ArchivoAdjuntoPrestamoSueldosView> adjuntarArchivoPrestamoSueldos(DocumentacionAdjuntaView documentacionAdjuntaView);
	
	
	/**
	 * Borrar archivo.
	 *
	 * @param documentacionAdjuntaView the documentacion adjunta view
	 * @return the respuesta
	 */
	Respuesta<Void> borrarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView);
	
	
	
	/**
	 * Confirmar prestamo sueldos.
	 *
	 * @param prestamoSueldosConfirmacionView the prestamo sueldos confirmacion view
	 * @return the respuesta
	 */
	Respuesta<PrestamoSueldosView> confirmarPrestamoSueldos(PrestamoSueldosConfirmacionView prestamoSueldosConfirmacionView);
	
	
	
	

	/**
	 * Notificar acceso desde home (graba estadistica).
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoDesdeHome();

	/**
	 * Método para obtener el detalle de un préstamo específico.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param desdeInicioPrestamo
	 *            the desde inicio prestamo
	 * @return Respuesta<DetallePrestamoView>
	 * @Input String
	 */
	Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo, Boolean desdeInicioPrestamo);

	/**
	 * Método para obtener la configuración de un préstamo específico.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param desdeInicioPrestamo
	 *            the desde inicio prestamo
	 * @return Respuesta<ConfiguracionPagoCuotaPrestamo>
	 * @Input ConsultaPrestamo
	 */
	Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(ConsultaPrestamo consultaPrestamo,
			Boolean desdeInicioPrestamo);

	/**
	 * Obtener prestamos cancelados.
	 *
	 * @return the respuesta
	 */
	Respuesta<PrestamosCanceladosView> obtenerPrestamosCancelados();

	/**
	 * Ver detalle prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo);

    /**
     * Descargar PDF calculador prestamos.
     *
     * @return the respuesta
     */
	Respuesta<ReporteView> descargarPDFCalculadorPrestamos();

    /**
     * Descargar cuotas pagas PDF.
     *
     * @param cuotaView
     *            the cuota view
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarCuotasPagasPDF(ProximaCuotaView cuotaView);
	
    /**
	 * Descargar proximas cuotas PDF.
	 *
	 * @param detalleCuotaPrestamoView
	 *            the detalle cuota prestamo view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteDetallePrestamo(DetalleCuotaPrestamoView detalleCuotaPrestamoView);
	
   /**
     * Descargar proximas cuotas PDF.
     *
     * @param proximaCuotaView
     *            the proxima cuota view
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarProximasCuotasPDF(CuotaView proximaCuotaView);

    /**
     * Descargar detalle prestamo PDF.
     *
     * @param detallePrestamosView
     *            the detalle prestamos view
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarDetallePrestamoPDF(DetallePrestamosView detallePrestamosView);
    
    
    /**
     * Descargar comp stop debit prestamo.
     *
     * @param ComprobanteStopDebitPrestamoView the confirmacion stop debit view
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView ComprobanteStopDebitPrestamoView);

    /**
     * Consulta si el cliente puede solicitar el prestamo
     * En tal caso, devuelve datos de configuración
     * @return
     */
    Respuesta<PrestamoSueldoTasaSubsidiadaView> consultarPrestamoATPVigente();
    
    /**
     * Ontiene legal de inicio
     * @return
     */
    Respuesta<PrestamoSueldoTasaSubsidiadaView> getPreparacionPrestamoTasaSubsidiada();
    
    /**
     * Graba estadistica al ingresar en pantalla de preparacion
     * @return
     */
    Respuesta<Void> grabarEstadisticaConfiguracionPrestamoTasaSubsidiada();
    
    /**
     * Graba estadistica al ingresar en pantalla de confirmacion
     * @return
     */
    Respuesta<Void> grabarEstadisticaConfirmacionPrestamoTasaSubsidiada();
    
    /**
     * Agrega un nuevo CBU
     * @param prestamoSueldoTasaSubsidiadaView
     * @return1
     */
    Respuesta<PrestamoSueldoTasaSubsidiadaView> agregarNuevoCBU(PrestamoSueldoTasaSubsidiadaView prestamoSueldoTasaSubsidiadaView);
    
    /**
     * Retorna el PDF del comprobante del prestamo con tasa subsidiada
     * @return
     */
    Respuesta<ReporteView> descargarPrestamoTasaSubsidiada();

    /**
     * Realiza el alta de un prestamo sueldo a tasa subsidiada
     * @param prestamoSueldoView
     * @return
     */
	Respuesta<PrestamoSueldoTasaSubsidiadaView> confirmarPrestamoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView);
    
	Respuesta<CancelacionPrestamoView> simularCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView);
	
	Respuesta<CancelacionPrestamoView> ejecutarCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView);
	
	Respuesta<ReporteView> generarComprobantePDF();

	Respuesta<Void> grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo();

}
