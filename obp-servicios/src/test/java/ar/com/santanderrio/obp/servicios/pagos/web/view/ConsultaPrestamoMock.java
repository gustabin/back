/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

/**
 * @author florencia.n.martinez
 *
 */
public final class ConsultaPrestamoMock {

    private ConsultaPrestamoMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static ConsultaPrestamo completarInfoConsultaPrestamoParaCuotasPagas() {
        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("000-00110354/8");
        consultaPrestamo.setFechaInicioPrestamo("2010-09-13");
        return consultaPrestamo;
    }
}
