package ar.com.santanderrio.obp.servicios.buscadorempresa.dao;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.buscadorempresa.entities.ListaEmpresasRecargaCelularMock;
import ar.com.santanderrio.obp.servicios.lucene.MediosDePagoTextFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.BuscadorEmpresaDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * 
 * @author mariano.g.pulera
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BuscadorEmpresaDAOTest {
	
	@InjectMocks
	private BuscadorEmpresaDAOImpl buscadorEmpresaDAO;
	
	@Mock
    private MediosDePagoTextFileIndexer indexer;
	
	@Mock
    private FilePath filePath;
	
	
	@Test
	public void searchOK() throws DAOException, IllegalAccessException {
	        
        //When
        Set<MedioPago> listaEmpresas = ListaEmpresasRecargaCelularMock.generarListaEmpresasRecargaCelular();
        FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
        when(indexer.toSerch(Matchers.any(BooleanQuery.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
        
        //Then
        Set<MedioPago> resultado = buscadorEmpresaDAO.search("rec");
        
        //Expected
        Assert.assertNotNull(resultado);
        
	}
	
	
	@Test
	public void searchRecargaCelularesOK() throws DAOException, IllegalAccessException {
		
		//When
		Set<MedioPago> listaEmpresas = ListaEmpresasRecargaCelularMock.generarListaEmpresasRecargaCelular();
		FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
		when(indexer.toSerch(Matchers.any(BooleanQuery.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
		
		//Then
		Set<MedioPago> resultado = buscadorEmpresaDAO.searchRecargaCelulares();
		
		//Expected
		Assert.assertNotNull(resultado);
		
	}
	

    @Test
    public void searchByCodeOK() throws DAOException, IllegalAccessException {
        
        //When
        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
        
        MedioPago movistar = ListaEmpresasRecargaCelularMock.generarEmpresaRecargaCelular();
        movistar.setFiid("remo");
        movistar.setNombreFantasia("RECARGA MOVISTAR");
        listaEmpresas.add(movistar);
    
        FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
        when(indexer.toSerch(Matchers.any(BooleanQuery.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
        
        //Then
        Set<MedioPago> resultado = buscadorEmpresaDAO.searchByCode("remo");
        
        //Expected
        Assert.assertNotNull(resultado);
        
    }

    
    @Test
    public void searchPagoAutomaticoOK() throws DAOException, IllegalAccessException {
        
        //When
        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
        
        MedioPago movistar = ListaEmpresasRecargaCelularMock.generarEmpresaRecargaCelular();
        movistar.setFiid("remo");
        movistar.setNombreFantasia("RECARGA MOVISTAR");
        movistar.setAddiHabilitado("s");
        movistar.setPesPAHabilitado("s");
        listaEmpresas.add(movistar);
    
        FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
        when(indexer.toSerch(Matchers.any(BooleanQuery.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
        
        //Then
        Set<MedioPago> resultado = buscadorEmpresaDAO.searchPagoAutomatico("mov");
        
        //Expected
        Assert.assertNotNull(resultado);
        
    }
    
    
    @Test
    public void searchEmpresaDebitoAutomaticoEnTarjetaOK() throws DAOException, IllegalAccessException {
        
        //When
        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
        
        MedioPago empresa = new MedioPago();
        empresa.setRubroFantasia("CLUBES Y GIMNASIOS");        
        empresa.setNombreFantasia("BILOG");
        empresa.setCuit("74783873");
        empresa.setVisaHabilitado("S");
        empresa.setAmexHabilitado("N");
        listaEmpresas.add(empresa);
    
        FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
        when(indexer.toSerch(Matchers.any(BooleanQuery.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
        
        //Then
        Set<MedioPago> resultado = buscadorEmpresaDAO.searchEmpresaDebitoAutomaticoEnTarjeta("bil");
        
        //Expected
        Assert.assertNotNull(resultado);
        
    }
    
    @Test
    public void searchEmpresaNuevoPagoOK() throws DAOException, IllegalAccessException {
        
        //When
        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
        
        MedioPago empresa = new MedioPago();
        empresa.setRubroFantasia("CLUBES Y GIMNASIOS");        
        empresa.setNombreFantasia("BILOG");
        empresa.setCuit("74783873");
        empresa.setVisaHabilitado("S");
        empresa.setAmexHabilitado("N");
        listaEmpresas.add(empresa);
    
        FieldUtils.writeDeclaredField(buscadorEmpresaDAO, "basePath", "C:\\tmp\\opb", true);
        when(indexer.toSerch(Matchers.any(Query.class), Matchers.anyString(), Matchers.anyInt())).thenReturn(listaEmpresas);
        
        //Then
        Set<MedioPago> resultado = buscadorEmpresaDAO.searchEmpresaNuevoPago("bil");
        
        //Expected
        Assert.assertNotNull(resultado);
        
    }
    
}
