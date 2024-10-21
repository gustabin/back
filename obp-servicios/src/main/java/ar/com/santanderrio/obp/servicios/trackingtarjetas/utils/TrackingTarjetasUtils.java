/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class TrackingTarjetasUtils.
 *
 * @author Silvina_Luque
 */
public final class TrackingTarjetasUtils {

    /**
	 * Instantiates a new tracking tarjetas utils.
	 */
    private TrackingTarjetasUtils(){
        
    }
    
    /**
	 * Devuelve 4X mas los ultimos 4 digitos *.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the string
	 */
    public static String crearMascaraNroTarjeta(String nroTarjeta) {
        int nroLen = nroTarjeta.trim().length();
        return "XXXX-" + nroTarjeta.substring(nroLen-4, nroLen);

     }    
    
    
    /**
	 * Formatea la fecha para mostrarla en el detalle de tracking.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
    public static String obtenerFechaTracking(String fecha) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
        Date piezaDate = formatter.parse(fecha);
        SimpleDateFormat formatoView = new SimpleDateFormat("dd/MM/yyyy");
        return formatoView.format(piezaDate);
    }
    
    /**
	 * Obtener fecha retorno.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
    public static String obtenerFechaRetorno(String fecha) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
        Date piezaDate = formatter.parse(fecha);
        Calendar cal = Calendar.getInstance();
        cal.setTime(piezaDate);
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date fechaRetorno = cal.getTime();
        SimpleDateFormat formatoView = new SimpleDateFormat("dd/MM/yyyy");
        return formatoView.format(fechaRetorno);
    }

}
