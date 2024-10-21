/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.CambioDireccionamientoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaCambioDireccionamientoInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

import java.util.List;

/**
 * The Class TraspasoAutomaticoBOImpl.
 */
@Component
public class TraspasoAutomaticoBOImpl implements TraspasoAutomaticoBO {

	/** The Constant CANTIDAD_CUENTAS. */
	private static final String CANTIDAD_CUENTAS = "001";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TraspasoAutomaticoBOImpl.class);

	/** The consulta paquetes DAO. */
	@Autowired
	private ConsultaPaquetesDAO consultaPaquetesDAO;

	/** The cambio direccionamiento DAO. */
	@Autowired
	private CambioDireccionamientoDAO cambioDireccionamientoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.
	 * TraspasoAutomaticoBO#confirmarTraspasoAutomatico(ar.com.santanderrio.obp.
	 * servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO)
	 */
	@Override
	public Respuesta<Void> confirmarTraspasoAutomatico(TraspasoAutomaticoDTO traspasoAutomaticoDTO) {

		ConsultaPaquetesInEntity consultaPaquetesInEntity = new ConsultaPaquetesInEntity();
		ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity = new ConsultaCambioDireccionamientoInEntity();

		consultaPaquetesInEntity.setCantidadDeCuentas(CANTIDAD_CUENTAS);

		String sucursal = StringUtils.stripStart(traspasoAutomaticoDTO.getSucursalCuenta(), "0");
		String sucursalFormateada = StringUtils.leftPad(sucursal, 3, "0");
		String numeroCuenta = StringUtils.stripStart(traspasoAutomaticoDTO.getNumeroCuenta(), "0");
		String numeroCuentaFormateada = StringUtils.leftPad(numeroCuenta, 7, "0");
		String tipoCuenta = StringUtils.leftPad(traspasoAutomaticoDTO.getTipoCuenta(), 2, "0");

		consultaPaquetesInEntity.setCliente(traspasoAutomaticoDTO.getCliente());
		consultaPaquetesInEntity.setNumeroCuenta(numeroCuentaFormateada);
		consultaPaquetesInEntity.setSucursalCuenta(sucursalFormateada);
		consultaPaquetesInEntity.setTipoCuenta(tipoCuenta);

		try {
			ConsultaPaquetesOutEntity consultaPaquetesOutEntity = consultaPaquetesDAO
					.consultar(consultaPaquetesInEntity);
			PaqueteEntity paqueteEntity = findPaqueteEntityByCuenta(consultaPaquetesOutEntity, sucursalFormateada, numeroCuentaFormateada, tipoCuenta);

			consultaCambioDireccionamientoInEntity.setCliente(traspasoAutomaticoDTO.getCliente());
			consultaCambioDireccionamientoInEntity.setNumeroPaquete(paqueteEntity.getPaquete());
			consultaCambioDireccionamientoInEntity.setSucursalPaquete(paqueteEntity.getSucursal());
			consultaCambioDireccionamientoInEntity
					.setIndicadorDireccionaFondos(traspasoAutomaticoDTO.getIndicadorFondosCA().getIndicador());
			cambioDireccionamientoDAO.cambiarDireccionamiento(consultaCambioDireccionamientoInEntity);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SOLICITUD_TRASPASO,
					CodigoMensajeConstantes.ERROR_SOLICITUD_TRASPASO, "realizar",
					traspasoAutomaticoDTO.getIndicadorFondosCA().getDescripcion());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SOLICITUD_TRASPASO,
					CodigoMensajeConstantes.ERROR_SOLICITUD_TRASPASO, "realizar",
					traspasoAutomaticoDTO.getIndicadorFondosCA().getDescripcion());
		}

		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * Finds a PaqueteEntity in the given consultaPaquetesOutEntity that matches the specified cuenta.
	 *
	 * @param consultaPaquetesOutEntity The consultaPaquetesOutEntity to search in.
	 * @param sucursalCuenta
	 * @param numeroCuenta
	 * @param tipoCuenta
	 * @return The first PaqueteEntity in consultaPaquetesOutEntity that matches the specified cuenta.
	 * @throws BusinessException If no PaqueteEntities match the specified cuenta.
	 */
	private PaqueteEntity findPaqueteEntityByCuenta(ConsultaPaquetesOutEntity consultaPaquetesOutEntity, String sucursalCuenta, String numeroCuenta, String tipoCuenta) throws BusinessException {
		List<PaqueteEntity> listaPaquetes = consultaPaquetesOutEntity.getPaquetes();

		if (listaPaquetes.isEmpty()) throw new BusinessException("No hay paquetes para la cuenta");

		for (PaqueteEntity paquete : listaPaquetes) {
			if (paquete.getSucursalCuenta().equals(sucursalCuenta) && paquete.getNumeroCuenta().equals(numeroCuenta) && paquete.getTipoCuenta().equals(tipoCuenta)) {
				return paquete;
			}
		}

		throw new BusinessException("No se encontraron paquetes para la cuenta");
	}
}
