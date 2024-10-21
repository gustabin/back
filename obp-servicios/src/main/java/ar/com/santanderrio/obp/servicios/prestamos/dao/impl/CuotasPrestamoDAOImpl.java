/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CuotasPrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;

/**
 * The Class CuotasPrestamoDAOImpl.
 */
@Component
public class CuotasPrestamoDAOImpl extends IatxBaseDAO implements CuotasPrestamoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuotasPrestamoDAOImpl.class);

	/** The servicio cnsptmodeu. */
	@Value("${SERVICIO.PREFIJO.CNSPTMODEU}")
	private String servicioCnsptmodeu;

	/** The version cnsptmodeu. */
	@Value("${SERVICIO.VERSION.CNSPTMODEU}")
	private String versionCnsptmodeu;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant CODIGO_RETORNO_OK. */
	private static final Integer CODIGO_RETORNO_OK = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.dao.CuotasPrestamoDAO#
	 * consultarProximasCuotas(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public DeudaPrestamoEntity consultarProximasCuotas(Cliente cliente, Cuenta cuenta) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(servicioCnsptmodeu, versionCnsptmodeu);
		try {
			String numeroCuentaCortado = cuenta.getNroCuentaProducto().substring(4,
					cuenta.getNroCuentaProducto().length());
			String entidadOficinaCuenta = "0072" + cuenta.getNroSucursal() + numeroCuentaCortado;
			String numeroCuotaVencer = "06";
			IatxRequestData iatxRequestData = new IatxRequestData(cliente);
			iatxRequestData.addBodyValue(entidadOficinaCuenta);
			iatxRequestData.addBodyValue(numeroCuotaVencer);
			iatxRequest.setData(iatxRequestData);

			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

			if (CODIGO_RETORNO_OK.equals(iatxResponse.getErrorCode())) {
				DeudaPrestamoEntity deudaPrestamoEntity = this.processTrama(iatxResponse.getTrama(),
						DeudaPrestamoEntity.class);
				if (esRespuestaValida(deudaPrestamoEntity)) {
					deudaPrestamoEntity.setNroComprobante(iatxResponse.getNroComprobante());
					deudaPrestamoEntity.setFecha(iatxResponse.getFechaHoraReq());
					return deudaPrestamoEntity;
				} else {
					throw new DAOException("Error en formato de campo devuelto por el servicio.");
				}
			}

			LOGGER.debug(iatxResponse.getErrorMessage());
			DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();
			deudaPrestamoEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			return deudaPrestamoEntity;

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			return new DeudaPrestamoEntity();
		}

	}

	/**
	 * Es respuesta valida. Valida si el formato de la respuesta del servicio
	 * "CNSPTMODEU" es correcta.
	 *
	 * @param deudaPrestamoEntity
	 *            the deuda prestamo entity
	 * @return the boolean
	 */
	private Boolean esRespuestaValida(DeudaPrestamoEntity deudaPrestamoEntity) {
		for (CuotaPrestamoEntity cuotaEntity : deudaPrestamoEntity.getListaRepeticiones()) {
			if (!ValidationEntity.validate(cuotaEntity)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

}
