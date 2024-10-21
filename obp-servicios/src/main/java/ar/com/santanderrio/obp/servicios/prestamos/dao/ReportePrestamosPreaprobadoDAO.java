package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;

public interface ReportePrestamosPreaprobadoDAO {

	Reporte generarComprobantePrestamoPreaprobado(PrestamoPreaprobadoMonoproductoInOutView datos) throws DAOException;
}
