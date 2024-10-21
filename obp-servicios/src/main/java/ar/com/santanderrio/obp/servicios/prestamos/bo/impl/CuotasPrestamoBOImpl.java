/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper2;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao.PrestamoCuotaPagaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CuotasPrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;

/**
 * The Class CuotasPrestamoBOImpl.
 */
@Component
public class CuotasPrestamoBOImpl implements CuotasPrestamoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuotasPrestamoBOImpl.class);

	/** The cuotas prestamo DAO. */
	@Autowired
	CuotasPrestamoDAO cuotasPrestamoDAO;

	/** The prestamo cuota paga DAO. */
	@Autowired
	PrestamoCuotaPagaDAO prestamoCuotaPagaDAO;

	/** The intervinientes dao. */
	@Autowired
	IntervinientesDAO intervinientesDao;

    /** The reporte dao. */
    @Autowired
    private ReporteDAO reporteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO#
	 * consultarProximasCuotas(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public DeudaPrestamoEntity consultarProximasCuotas(Cliente cliente, Cuenta cuenta) throws BusinessException {
		LOGGER.info("Consulto las proximas cuotas");
		try {
			return cuotasPrestamoDAO.consultarProximasCuotas(cliente, cuenta);
		} catch (DAOException e) {
			LOGGER.error("Error en la validacion del bean.", e);
			throw new BusinessException("Error en formato de campo devuelto por el servicio.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO#
	 * consultarCuotasPagas(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 * java.lang.String)
	 */
	@Override
	public ConsultaCuotaPagaOutEntity consultarCuotasPagas(Cliente cliente, Cuenta cuenta, String fechaInicio)
			throws BusinessException, SinCuotasPagasException {
		LOGGER.info("Consulto las cuotas pagas");
		if (fechaInicio != null
				&& Pattern.matches("([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])", fechaInicio)) {
			try {
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				ConsultaCuotaPagaInEntity consultaCuotaPagaInEntity = new ConsultaCuotaPagaInEntity(
						cuenta.getNroSucursal(), cuenta.getNroCuentaProducto(), fechaInicio,
						dateFormatter.format(new Date()));
				ConsultaCuotaPagaOutEntity cuotaPagaOutEntity = prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(cliente,
						consultaCuotaPagaInEntity);
				while (cuotaPagaOutEntity.getHayMasRegistros()) {
					consultaCuotaPagaInEntity.setRellamada(cuotaPagaOutEntity);
					ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntityRellamada = prestamoCuotaPagaDAO
							.obtenerCuotasPagasPrestamo(cliente, consultaCuotaPagaInEntity);
					cuotaPagaOutEntity.agregarResultados(consultaCuotaPagaOutEntityRellamada);
				}
				return cuotaPagaOutEntity;
			} catch (DAOException e) {
				LOGGER.error(e.getMessage(), e);
				throw new BusinessException();
			} 
		}
		throw new BusinessException("Error de fecha");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO#
	 * consultarIntervinientePrestamo(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public Interviniente consultarIntervinientePrestamo(Cliente cliente, Cuenta cuenta) throws BusinessException {
		LOGGER.info("Consulto los clientes asociados a un prestamo");
		try {
			return intervinientesDao.obtenerIntervinienteTitular(cliente, cuenta);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException("INTERVINIENTE");
		}
	}
	
    @Override
    public Respuesta<Reporte> exportarCuotasPagas(Cliente cliente, ProximasCuotasView cuotasPagas) {
        LOGGER.info("Entro al manager para descargar el excel de cuotas pagas");
        return exportarCuotas(cliente, cuotasPagas, ExcelBuilderHelper2.PRESTAMO_CUOTAS_PAGAS);
    }

    @Override
    public Respuesta<Reporte> exportarProximasCuotas(Cliente cliente, ProximasCuotasView proximasCuotas) {
        LOGGER.info("Entro al manager para descargar el excel de proximas cuotas");
        return exportarCuotas(cliente, proximasCuotas, ExcelBuilderHelper2.PRESTAMO_PROXIMAS_CUOTAS);
    }

    private Respuesta<Reporte> exportarCuotas(Cliente cliente, ProximasCuotasView cuotas, String excelBase) {
        if (cuotas == null) {
            LOGGER.error("No existe la informacion previa para descargar el excel");
            Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            return respuesta;
        }
        return reporteDAO.obtenerReporte(cuotas, excelBase, cliente, false);
    }
}