package ar.com.santanderrio.obp.servicios.simuladorprestamo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.impl.DestinoPrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class DestinoPrestamoDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DestinoPrestamoDAOTest {

    /** The legal BO. */
    @InjectMocks
    private DestinoPrestamoDAO destinoPrestamoDAO = new DestinoPrestamoDAOImpl();

    /** The archivo resource DAO. */
    @Mock
    private ArchivoDeRecursosDAO archivoResourceDAO;

    /**
     * Cuando carga destino prestamo obtengo coleccion test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void cuandoCargaDestinoPrestamoObtengoColeccionTest() throws DAOException {

        // when
        final String productoUG = "35";
        final String subproductoUG = "0015";
        final String divisaProductoUG = "ARS";
        final String destinoDeFondoUG = "35136";
        final String descripcionUG = "Refinanciacion o cancelac pasivos";

        List<String> destinosLineaDeTexto = new ArrayList<String>();
        destinosLineaDeTexto.add(productoUG + subproductoUG + divisaProductoUG + destinoDeFondoUG + descripcionUG);
        destinosLineaDeTexto.add("350015ARS35138Adquisicion de inmuebles                     ");
        destinosLineaDeTexto.add("350015ARS35139Refaccion o construccion de inmuebles             ");
        destinosLineaDeTexto.add("350016ARS35001Viajes / turismo                                  ");

        // then
        Mockito.when(archivoResourceDAO.leerArchivo(ExternalPropertyType.FILE_DESTINO_PRESTAMO))
                .thenReturn(destinosLineaDeTexto);
        List<DestinoPrestamo> destinos = destinoPrestamoDAO.obtener();
        DestinoPrestamo destino = destinos.get(0);

        // expect
        Assert.assertTrue("No se cargo informacion del archivo DESTINOS-BCRA.TXT", CollectionUtils.isNotEmpty(destinos));
        Assert.assertTrue("Faltan datos", destinos.size() == 4);
        Assert.assertTrue("La variable no corresponde con el dato, error en el mapeo de: productoUG",
                productoUG.equals(destino.getProductoUG()));
        Assert.assertTrue("La variable no corresponde con el dato, error en el mapeo de: subproductoUG",
                subproductoUG.equals(destino.getSubproductoUG()));
        Assert.assertTrue("La variable no corresponde con el dato, error en el mapeo de: divisaProductoUG",
                divisaProductoUG.equals(destino.getDivisaProductoUG()));
        Assert.assertTrue("La variable no corresponde con el dato, error en el mapeo de: destinoDeFondoUG",
                destinoDeFondoUG.equals(destino.getDestinoDeFondosUG()));
        Assert.assertTrue("La variable no corresponde con el dato, error en el mapeo de: productoUG",
                descripcionUG.equals(destino.getDescripcionUG()));

    }

}
