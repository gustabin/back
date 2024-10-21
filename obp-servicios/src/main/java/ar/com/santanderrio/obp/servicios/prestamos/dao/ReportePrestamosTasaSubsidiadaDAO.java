package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;

public interface ReportePrestamosTasaSubsidiadaDAO {

	Reporte generarComprobantePrestamoTasaSubsidiada(ComprobantePrestamoTasaSubsidiadaView comprobantePrestamoTasaSubsidiadaView) throws DAOException;
}
