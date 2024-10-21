package ar.com.santanderrio.obp.servicios.comun.dao;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;

/**
 * The Class ArchivoDeRecursosTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArchivoDeRecursosTest {

    /** The archivo de recursos DAO. */
    @InjectMocks
    private ArchivoDeRecursosDAO archivoDeRecursosDAO = new ArchivoDeRecursosDAOImpl();

    /** The file path. */
    @Mock
    private FilePath filePath;

    /**
     * Leer archivo test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void leerArchivoTest() throws DAOException {
        // when
        File inputFile = new File(this.getClass()
                .getResource("/config/test/files/" + ExternalPropertyType.FILE_DESTINO_PRESTAMO.getName()).getFile());
        final int sinDatosEncontrados = 0;

        Mockito.when(filePath.getFilePath()).thenReturn(
                inputFile.getAbsolutePath().replace(ExternalPropertyType.FILE_DESTINO_PRESTAMO.getName(), ""));

        // then
        List<String> lineasEnArchivo = archivoDeRecursosDAO.leerArchivo(ExternalPropertyType.FILE_DESTINO_PRESTAMO);

        // expect
        Assert.assertTrue(
                "No se encontraron datos para el archivo: " + ExternalPropertyType.FILE_DESTINO_PRESTAMO.getName(),
                sinDatosEncontrados < lineasEnArchivo.size());
    }
}
