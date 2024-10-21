package ar.com.santanderrio.obp.servicios.segmento.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.xml.bind.JAXBElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectLaborableDataResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.segmento.bo.impl.SegmentoClienteBOImpl;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO;

/**
 * The Class SegmentoClienteBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SegmentoClienteBOTest {

    /** The segmento cliente BO. */
    @InjectMocks
    private SegmentoClienteBOImpl segmentoClienteBO = new SegmentoClienteBOImpl();

    /** The segmento cliente DAO. */
    @Mock
    private SegmentoClienteDAO segmentoClienteDAO;
    
    @Spy
    private RespuestaFactory respuestaFactory;

    /**
     * Obtener segmento select test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSegmentoSelectTest() throws DAOException {

        Cliente cliente = new Cliente();
        GetClientSegmentSelectLaborableDataResponse getClientSegmentDataResponse = new GetClientSegmentSelectLaborableDataResponse();
        JAXBElement<String> ejecutivoJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesucadmJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> cuadranteJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesubsegJax = Mockito.mock(JAXBElement.class);

        getClientSegmentDataResponse.setIsAdvance(true);
        getClientSegmentDataResponse.setIsSelect(true);
        getClientSegmentDataResponse.setIsUniv(true);
        getClientSegmentDataResponse.setEjecutivo(ejecutivoJax);
		getClientSegmentDataResponse.setPesucadm(pesucadmJax);
        getClientSegmentDataResponse.setCuadrante(cuadranteJax);
		getClientSegmentDataResponse.setPesubseg(pesubsegJax);

		when(pesucadmJax.getValue()).thenReturn("002");
        when(ejecutivoJax.getValue()).thenReturn("Ejecutivo");
        when(segmentoClienteDAO.getClientSelectSegmentLaborable(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString())).thenReturn(getClientSegmentDataResponse);

        Respuesta<Segmento> respuesta = segmentoClienteBO.obtenerSegmento(cliente);

        assertNotNull(respuesta);
        Segmento segmentoResponse = respuesta.getRespuesta();
        assertNotNull(segmentoResponse);
        assertTrue(segmentoResponse.isSelect());
    }

    /**
     * Obtener segmento Pyme test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSegmentoPymeTest() throws DAOException {

        Cliente cliente = new Cliente();
        GetClientSegmentSelectLaborableDataResponse getClientSegmentDataResponse = new GetClientSegmentSelectLaborableDataResponse();
        JAXBElement<String> ejecutivoJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesucadmJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> cuadranteJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesubsegJax = Mockito.mock(JAXBElement.class);



        getClientSegmentDataResponse.setIsAdvance(true);
        getClientSegmentDataResponse.setIsUniv(true);
		getClientSegmentDataResponse.setEjecutivo(ejecutivoJax);
		getClientSegmentDataResponse.setPesucadm(pesucadmJax);
		getClientSegmentDataResponse.setCuadrante(cuadranteJax);
		getClientSegmentDataResponse.setPesubseg(pesucadmJax);

		when(pesucadmJax.getValue()).thenReturn("002");
		when(ejecutivoJax.getValue()).thenReturn("Ejecutivo");
		when(cuadranteJax.getValue()).thenReturn("P1");
		when(pesubsegJax.getValue()).thenReturn("PYM");



		
        when(segmentoClienteDAO.getClientSelectSegmentLaborable(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString())).thenReturn(getClientSegmentDataResponse);

        Respuesta<Segmento> respuesta = segmentoClienteBO.obtenerSegmento(cliente);

        assertNotNull(respuesta);
        Segmento segmentoResponse = respuesta.getRespuesta();
        assertNotNull(segmentoResponse);
        assertTrue(segmentoResponse.isPyme());
        assertEquals("Ejecutivo", segmentoResponse.getEjecutivo());
    }

    /**
     * Obtener segmento universidades test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSegmentoUniversidadesTest() throws DAOException {

        Cliente cliente = new Cliente();
        GetClientSegmentSelectLaborableDataResponse getClientSegmentDataResponse = new GetClientSegmentSelectLaborableDataResponse();
        JAXBElement<String> ejecutivoJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesucadmJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> cuadranteJax = Mockito.mock(JAXBElement.class);
        JAXBElement<String> pesubsegJax = Mockito.mock(JAXBElement.class);

        getClientSegmentDataResponse.setIsUniv(true);
        getClientSegmentDataResponse.setEjecutivo(ejecutivoJax);
        getClientSegmentDataResponse.setPesucadm(pesucadmJax);
        getClientSegmentDataResponse.setCuadrante(cuadranteJax);
		getClientSegmentDataResponse.setPesubseg(pesubsegJax);

        when(pesucadmJax.getValue()).thenReturn("002");
        when(ejecutivoJax.getValue()).thenReturn("Ejecutivo");
        when(segmentoClienteDAO.getClientSelectSegmentLaborable(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString())).thenReturn(getClientSegmentDataResponse);

        Respuesta<Segmento> respuesta = segmentoClienteBO.obtenerSegmento(cliente);

        assertNotNull(respuesta);
        Segmento segmentoResponse = respuesta.getRespuesta();
        assertNotNull(segmentoResponse);
        assertTrue(segmentoResponse.isUniversidad());
    }

    /**
     * Obtener segmento DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSegmentoDAOExceptionTest() throws DAOException {

        Cliente cliente = new Cliente();

        when(segmentoClienteDAO.getClientSelectSegment(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString())).thenThrow(DAOException.class);

        Respuesta<Segmento> respuesta = segmentoClienteBO.obtenerSegmento(cliente);

        assertNotNull(respuesta);
        Segmento segmentoResponse = respuesta.getRespuesta();
        assertNotNull(segmentoResponse);
        assertFalse(segmentoResponse.isPyme());
        assertFalse(segmentoResponse.isSelect());
        assertFalse(segmentoResponse.isUniversidad());
    }

}
