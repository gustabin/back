package ar.com.santanderrio.obp.servicios.chat.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.chat.dao.impl.ChatClientesHabilitadosDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;

/**
 * The Class ChatClientesHabilitadosDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatClientesHabilitadosDAOTest {

	
	/** The chat clientes habilitados DAO. */
    @InjectMocks
	private ChatClientesHabilitadosDAO chatClientesHabilitadosDAO = new ChatClientesHabilitadosDAOImpl();
	
    /** The archivo resource DAO. */
    @Mock
    private ArchivoDeRecursosDAO archivoDeRecursosDAO;
    
    /** The file path. */
    @Mock
    private FilePath filePath;
	
	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
    /**
     * obtener Lista Clientes Habilitados NO OK
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerListaClientesHabilitadosNull() throws DAOException {
  
        List<String> filtrochat = null;
        
        // then
        Mockito.when(archivoDeRecursosDAO.leerArchivo("filtrochat.txt"))
                .thenReturn(filtrochat);
    	
        List<String> clientesHabilitados = chatClientesHabilitadosDAO.obtenerListaClientesHabilitados();
        
        assertNotNull(clientesHabilitados);
    }
    
    /**
     * obtener Lista Clientes Habilitados OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerListaClientesHabilitadosOK() throws DAOException {
  
        List<String> filtrochat = new ArrayList<String>();
        filtrochat.add("11111111");
        filtrochat.add("22222222");
        filtrochat.add("33333333");
        
        // then
        Mockito.when(archivoDeRecursosDAO.leerArchivo("filtrochat.txt"))
                .thenReturn(filtrochat);
    	
        List<String> clientesHabilitados = chatClientesHabilitadosDAO.obtenerListaClientesHabilitados();
        
        assertNotNull(clientesHabilitados);
    }
	
}
