/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;



/**
 * The Class CargarLineaDetalleConsumoTarjeta
 * 
 * @author florencia.n.martinez
 *
 */
public class CargarLineaDetalleConsumoTarjeta {

    /**
     * Arma una lista de linea detalle consumo tarjeta para las lineas 1 y 2.
     * 
     * @return List<LineaDetalleConsumoTarjeta>
     */
    public List<LineaDetalleConsumoTarjetaDTO> armarListaDeLineas1Y2() {
        List<LineaDetalleConsumoTarjetaDTO> lineaList = cargarLineaALista(new ArrayList<LineaDetalleConsumoTarjetaDTO>(),
                cargarLinea1());
        lineaList = cargarLineaALista(lineaList, cargarLinea2());
        return lineaList;
    }

    /**
     * Arma una lista de linea detalle consumo tarjeta para la linea 3.
     * 
     * @return List<LineaDetalleConsumoTarjeta>
     */
    public List<LineaDetalleConsumoTarjetaDTO> armarListaDeLineas3() {
        return cargarLineaALista(new ArrayList<LineaDetalleConsumoTarjetaDTO>(), cargarLinea3());
    }

    /**
     * Carga la linea con id 1.
     * 
     * @return LineaDetalleConsumoTarjeta
     */
    public LineaDetalleConsumoTarjetaDTO cargarLinea1() {
        return cargarLinea("TOSCANA", "03/03", "356,66", "23/03/2016", "0025219841", "4050710076564470");
    }

    /**
     * Carga la linea con id 2.
     * 
     * @return LineaDetalleConsumoTarjeta
     */
    public LineaDetalleConsumoTarjetaDTO cargarLinea2() {
        return cargarLinea("COTO 045", "12/18", "44,38", "07/06/2015", "0013150370", "4050710076564470");
    }

    /**
     * Carga la linea con id 3.
     * 
     * @return LineaDetalleConsumoTarjeta
     */
    public LineaDetalleConsumoTarjetaDTO cargarLinea3() {
        return cargarLinea("ALL GREEN", "01/03", "123,34", "09/05/2016", "0013686795", "4050718005374697");
    }

    /*
     * Carga una linea a la lista de lineas.
     * 
     * @param lineaList
     * 
     * @param linea
     * 
     * @return List<LineaDetalleConsumoTarjeta>
     */
    private List<LineaDetalleConsumoTarjetaDTO> cargarLineaALista(List<LineaDetalleConsumoTarjetaDTO> lineaList,
            LineaDetalleConsumoTarjetaDTO linea) {
        lineaList.add(linea);
        return lineaList;
    }

    /*
     * Carga la linea con datos.
     * 
     * @param id
     * 
     * @param descripcion
     * 
     * @param cuota
     * 
     * @param importePesos
     * 
     * @param fecha
     * 
     * @param codigoEstablecimiento
     * 
     * @param nroTarjeta
     * 
     * @return LineaDetalleConsumoTarjeta
     */
    private LineaDetalleConsumoTarjetaDTO cargarLinea(String descripcion, String cuota, String importePesos,
            String fecha, String codigoEstablecimiento, String nroTarjeta) {
        LineaDetalleConsumoTarjetaDTO lineaDetalleConsumoTarjeta = new LineaDetalleConsumoTarjetaDTO();
        lineaDetalleConsumoTarjeta.setDescripcion(descripcion);
        lineaDetalleConsumoTarjeta.setCuota(cuota);
        lineaDetalleConsumoTarjeta.setCodigoEstablecimiento(codigoEstablecimiento);
        lineaDetalleConsumoTarjeta.setNroTarjeta(nroTarjeta);
        return lineaDetalleConsumoTarjeta;
    }

}
