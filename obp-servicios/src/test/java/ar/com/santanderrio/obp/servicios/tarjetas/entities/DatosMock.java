package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TipoDocumento;

/**
 * The Class DatosMock.
 *
 * @author florencia.n.martinez
 */
public final class DatosMock {

    private DatosMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info datos.
     *
     * @return the datos
     */
    public static DatosEntity completarInfoDatos() {
        DatosEntity datos = new DatosEntity();
        datos.setId("93849377");
        datos.setAffinityGroup("2");
        datos.setApellido("PEREZ");
        datos.setCategoria("0");
        datos.setCodTipoTarjeta("CHIP EMV C/CONTAC");
        datos.setCodigoSucursal("404");
        datos.setCuenta("389390510");
        datos.setFechaDesde("27/08/2015");
        datos.setFechaNacimiento("09/03/1989");
        datos.setHabiente("PEREZ/JORGE J");
        datos.setNombre("JORGE J   ");
        datos.setDocumento("33467953");
        datos.setProducto("American Express Gold");
        datos.setTarjetaActiva("3777910120417280");
        datos.setTarjetaProdu("AM0202");
        datos.setTelefono("0424971");
        datos.setTipoDocumento(completarInfoTipoDocumento());
        datos.setTipoTarjetaDetalle("CHIP EMV C/CONTAC");
        datos.setTitular("PEREZ/JORGE J");
        datos.setVencimiento("1701");
        return datos;
    }

    /**
     * Completar info tipo documento.
     *
     * @return the tipo documento
     */
    private static TipoDocumento completarInfoTipoDocumento() {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setCodigo("1");
        tipoDocumento.setValor("DNI");
        return tipoDocumento;
    }
}
