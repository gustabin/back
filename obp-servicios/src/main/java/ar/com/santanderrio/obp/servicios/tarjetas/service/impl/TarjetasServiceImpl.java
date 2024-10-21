/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AliasFavoritoTarjetaBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.service.TarjetasService;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class TarjetasServiceImpl.
 */
@Component
public class TarjetasServiceImpl implements TarjetasService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetasServiceImpl.class);

	/** The tarjeta bo. */
	@Autowired
	private SelectorYCabeceraBO tarjetaBO;

	/** The alias favorito tarjeta bo. */
	@Autowired
	private AliasFavoritoTarjetaBo aliasFavoritoTarjetaBo;

	/** The CuentaBO cuentaBO. */
	@Autowired
	private CuentaBO cuentaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.tarjetas.service.TarjetasService#
	 * obtenerTarjetaDefault(ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DisponiblesYConsumoDTO> obtenerTarjetas(Cliente cliente) throws ServiceException {
		try {
			Respuesta<DisponiblesYConsumoDTO> respuesta = tarjetaBO.obtenerTarjetas(cliente);
			LOGGER.info("Obteniendo respuesta de BO: {}. ", respuesta.toString());
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("Error al obtener las cuentas del cliente {}.", cliente, e);
			throw new ServiceException(e);
		}
	}

	/**
	 * Servicio que actualiza la tarjeta favorita.
	 *
	 * @author florencia.n.martinez
	 * @param identificacionCuenta
	 *            the identificación de cuenta
	 * @param cliente
	 *            the cliente
	 * @return Respuesta<Void>
	 * @throws ServiceException
	 *             the service exception
	 */
	@Override
	public Respuesta<Void> actualizarTarjetaFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws ServiceException {
		Respuesta<Void> respuestaTarjetaFavorita = new Respuesta<Void>();
		try {
			respuestaTarjetaFavorita = aliasFavoritoTarjetaBo.marcarFavorita(identificacionCuenta, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error del servicio al actualizar la tarjeta favorita.", e);
		}
		LOGGER.info("Respuesta de tarjeta favorita obtenida de la capa BO: {}", respuestaTarjetaFavorita.toString());
		return respuestaTarjetaFavorita;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.service.TarjetasService#
	 * esMarcaVisa(java.lang.String)
	 */
	@Override
	public Boolean esMarcaVisa(String marca) {
		return TarjetaUtils.esMarcaVisa(marca);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.service.TarjetasService#
	 * esMarcaAmex(java.lang.String)
	 */
	@Override
	public Boolean esMarcaAmex(String marca) {
		return TarjetaUtils.esMarcaAmex(marca);
	}

	/**
	 * Corta el numero de tarjeta de la cuenta segun la marca.
	 *
	 * @author florencia.n.martinez
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param marca
	 *            the marca
	 * @return String
	 */
	@Override
	public String cortarNumeroTarjetaComoTarjetaActiva(String numeroTarjeta, String marca) {
		return TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(numeroTarjeta, marca);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.service.TarjetasService#
	 * obtenerTooltipFavorito()
	 */
	public String obtenerTooltipFavorito() {

		return tarjetaBO.obtenerTooltipFavorito();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.service.TarjetasService#
	 * obtenerTooltipNoFavorito()
	 */
	public String obtenerTooltipNoFavorito() {

		return tarjetaBO.obtenerTooltipNoFavorito();
	}

	/**
	 * Gets the tarjeta BO.
	 *
	 * @return the tarjeta BO
	 */
	public SelectorYCabeceraBO getTarjetaBO() {
		return tarjetaBO;
	}

	/**
	 * Sets the tarjeta BO.
	 *
	 * @param tarjetaBO
	 *            the new tarjeta BO
	 */
	public void setTarjetaBO(SelectorYCabeceraBO tarjetaBO) {
		this.tarjetaBO = tarjetaBO;
	}

}
