package ar.com.santanderrio.obp.servicios.prestamos.dao;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamosBffDAOImpl;

@RunWith(MockitoJUnitRunner.class)
public class PrestamosBffDAOImplTest extends AbstractPrestamosBffDAOImplTest {

	@InjectMocks
	private PrestamosBffDAOImpl prestamosBffDAO;

	@Override
	PrestamosBffDAO getPrestamosDao() {
		return prestamosBffDAO;
	}

}
