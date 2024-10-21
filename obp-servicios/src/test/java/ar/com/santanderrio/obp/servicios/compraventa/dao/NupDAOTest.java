package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.NUPDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentsResponse;
import ar.com.santanderrio.obp.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class NupDAOTest {
    @InjectMocks
    private NUPDAO nUPDAO = new NUPDAOImpl();

    @Mock
    private CustomersApi customersApi;

    @Spy
    public CompraVentaDolaresUtil compraVentaDolaresUtil = Mockito.spy(new CompraVentaDolaresUtil());

    @Test
    public void consultaNUPOk() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getDocumentsById.json");
        DocumentsResponse apiResponse = TestUtils.readAndClose(jsonResponse, DocumentsResponse.class);
        Mockito.when(customersApi.getDocumentsById(Mockito.anyString())).thenReturn(apiResponse);

        NupDTO nupResponse = nUPDAO.obtenerNUP(obtenerCliente());

        Assert.assertNotNull(nupResponse);
        Assert.assertEquals(2, nupResponse.getDetalleDocumento().size());
        Assert.assertTrue(nupResponse.getDetalleDocumento().containsKey(NupDTO.TIPO_DOC_CUIT));
        Assert.assertTrue(nupResponse.getDetalleDocumento().containsKey("N"));

        String[] tiposCuils = { NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI };
        String cuit = nupResponse.getCuit(tiposCuils);
        Assert.assertEquals("20185939597", cuit);

        DetalleDocumentoDTO documentoDTO = nupResponse.getDetalleDocumento().get("N");
        Assert.assertEquals("00018593959", documentoDTO.getNroDocumento());
        Assert.assertTrue(documentoDTO.isDocumentoPrincipal());
    }



    private Cliente obtenerCliente() {
        Cliente c1 = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        c1.setNup("02569328");
        return c1;
    }
}
