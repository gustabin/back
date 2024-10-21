/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;


/**
 * The Class CargarConsumoTarjeta
 * 
 * @author florencia.n.martinez
 *
 */
public class CargarConsumoTarjeta {

    /** The new Class CargarLineaDetalleConsumoTarjeta. **/
    private CargarLineaDetalleConsumoTarjeta cargarLinea = new CargarLineaDetalleConsumoTarjeta();

    /**
     * Carga la lista de consumo tarjeta con dos objetos ConsumoTarjeta.
     * 
     * @return List<ConsumoTarjeta>
     */
    public List<ConsumoTarjetaDTO> cargarListaConsumoTarjetaConDosConsumos() {
        ConsumoTarjetaDTO consumoTarjeta = cargarConsumoTarjetaConLineas(
                cargarConsumoTarjeta("Visa", "4050710076564470", "",  true),
                cargarLinea.armarListaDeLineas1Y2());
        ConsumoTarjetaDTO consumoTarjeta2 = cargarConsumoTarjetaConLineas(
                cargarConsumoTarjeta("Visa", "4050718004243604", "Joaquin", false),
                cargarLinea.armarListaDeLineas3());
        List<ConsumoTarjetaDTO> consumoTarjetaList = cargarListaConConsumoTarjeta(new ArrayList<ConsumoTarjetaDTO>(),
                consumoTarjeta);
        consumoTarjetaList = cargarListaConConsumoTarjeta(consumoTarjetaList, consumoTarjeta2);
        return consumoTarjetaList;
    }

    /*
     * Carga la lista de consumo tarjeta con un objeto ConsumoTarjeta.
     * 
     * @param consumoList
     * 
     * @param consumo
     * 
     * @return List<ConsumoTarjeta>
     */
    private List<ConsumoTarjetaDTO> cargarListaConConsumoTarjeta(List<ConsumoTarjetaDTO> consumoList,
            ConsumoTarjetaDTO consumo) {
        consumoList.add(consumo);
        return consumoList;
    }

    /*
     * Carga con datos el consumo tarjeta.
     * 
     * @param marca
     * 
     * @param numero
     * 
     * @param nombreAdicional
     * 
     * @param consumoPesos
     * 
     * @param consumoDolares
     * 
     * @param isTitular
     * 
     * @param isAdicional
     * 
     * @param lineas
     * 
     * @return ConsumoTarjeta
     */
    private ConsumoTarjetaDTO cargarConsumoTarjeta(String marca, String numero, String nombreAdicional,
            Boolean isTitular) {
        ConsumoTarjetaDTO consumoTarjeta = new ConsumoTarjetaDTO();
        consumoTarjeta.setMarca(marca);
        consumoTarjeta.setNumero(numero);
        consumoTarjeta.setNombreAdicional(nombreAdicional);
        consumoTarjeta.setIsTitular(isTitular);
        consumoTarjeta.setIsAdicional(!isTitular);
        return consumoTarjeta;
    }

    /*
     * Agrega al objeto ConsumoTarjeta la lista de lineas.
     * 
     * @param consumo
     * 
     * @param lineas
     * 
     * @return ConsumoTarjeta
     */
    private ConsumoTarjetaDTO cargarConsumoTarjetaConLineas(ConsumoTarjetaDTO consumo,
            List<LineaDetalleConsumoTarjetaDTO> lineas) {
        consumo.setLineas(lineas);
        return consumo;
    }

}
