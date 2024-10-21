/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo.impl;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO;
import ar.com.santanderrio.obp.servicios.home.entitites.ListaTarjetasDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.home.entitites.TipoSubProductoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers.CreditCardsLimitsObpMapper;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosCuentaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimitesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TarjetasHomeBOImpl.
 *
 * @author B039543
 * @see ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO
 */
@Component
public class TarjetasHomeBOImpl implements TarjetasHomeBO {

	/** The Constant FECHA_VACIA_TARJETA. */
	private static final String FECHA_VACIA_TARJETA = "-/-/-";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetasHomeBOImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The tarjeta DAO. */
	@Autowired
	private TarjetaDAO tarjetaDAO;

	@Autowired
	private CreditCardsServiceConnector creditCardServiceConnector;

	@Autowired
	private CreditCardsLimitsObpMapper creditCardMapper;

	/**
	 * (non-Javadoc).
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO#obtenerTarjetas(
	 *      ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ListaTarjetasDTO> obtenerTarjetas(Cliente cliente) {
		ListaTarjetasDTO listaTarjetasDTO = new ListaTarjetasDTO();
		List<TarjetaHomeDTO> listaTarjetaHomeDTO = new ArrayList<TarjetaHomeDTO>();

		try {
			List<Cuenta> productos = TarjetaBOUtils.filtrarCuentasDeTipoCuentaTarjeta(cliente.getCuentas());

			Cuenta favorita = obtenerFavorita(productos);
			productos.remove(favorita);

			List<Cuenta> productosVisaTitular = TarjetaBOUtils.filtrarVisaTitular(productos);

			List<Cuenta> productosAmexTitular = TarjetaBOUtils.filtrarAmexTitular(productos);

			List<Cuenta> productosMastercardTitular = TarjetaBOUtils.filtrarMastercardTitular(productos);

			List<Cuenta> productosVisaAdicional = TarjetaBOUtils.filtrarVisaAdicional(productos);

			List<Cuenta> productosAmexAdicional = TarjetaBOUtils.filtrarAmexAdicional(productos);

			List<Cuenta> productosMastercardAdicional = TarjetaBOUtils.filtrarMastercardAdicional(productos);

			List<Cuenta> productosTarjetaRecargable = (List<Cuenta>) TarjetaBOUtils.filtrarRecargables(productos);

			List<Cuenta> tarjetas = new ArrayList<Cuenta>();

			if (favorita != null) {
				tarjetas.add(favorita);
			}
			if (!CollectionUtils.isEmpty(productosVisaTitular)) {
				tarjetas.addAll(productosVisaTitular);
			}
			if (!CollectionUtils.isEmpty(productosAmexTitular)) {
				tarjetas.addAll(productosAmexTitular);
			}

			if (!CollectionUtils.isEmpty(productosMastercardTitular)) {
				tarjetas.addAll(productosMastercardTitular);
			}

			if (!CollectionUtils.isEmpty(productosVisaAdicional)) {
				tarjetas.addAll(productosVisaAdicional);
			}
			if (!CollectionUtils.isEmpty(productosAmexAdicional)) {
				tarjetas.addAll(productosAmexAdicional);
			}
			if (!CollectionUtils.isEmpty(productosMastercardAdicional)) {
				tarjetas.addAll(productosMastercardAdicional);
			}

			if (tieneTitulares(tarjetas)) {
				tarjetas.removeAll(obtenerAdicionales(tarjetas));
			}

			if (!CollectionUtils.isEmpty(productosTarjetaRecargable)) {
				tarjetas.addAll(productosTarjetaRecargable);
			}

			listaTarjetaHomeDTO = mapearListaTarjetas(tarjetas);

		} catch (TarjetaBOUtilsException e) {
			listaTarjetaHomeDTO = null;
			LOGGER.error("Error al obtener TarjetasHomeBOImpl - obtenerTarjetas()", e);
		}
		listaTarjetasDTO.setTarjetas(listaTarjetaHomeDTO);
		return respuestaFactory.crearRespuestaOk(listaTarjetasDTO);
	}

	/**
	 * Tiene titulares.
	 *
	 * @param tarjetas the tarjetas
	 * @return true, if successful
	 */
	private boolean tieneTitulares(List<Cuenta> tarjetas) {
		List<Cuenta> productosVisaTitular = TarjetaBOUtils.filtrarVisaTitular(tarjetas);
		List<Cuenta> productosAmexTitular = TarjetaBOUtils.filtrarAmexTitular(tarjetas);
		List<Cuenta> productosMastercardTitular = TarjetaBOUtils.filtrarMastercardTitular(tarjetas);
		return !CollectionUtils.isEmpty(productosVisaTitular) || !CollectionUtils.isEmpty(productosAmexTitular)
				|| !CollectionUtils.isEmpty(productosMastercardTitular);
	}

	/**
	 * Obtener favorita.
	 *
	 * @param productos the productos
	 * @return the cuenta
	 */
	private Cuenta obtenerFavorita(List<Cuenta> productos) {

		Cuenta favorita = null;

		for (Cuenta cuenta : productos) {
			if (cuenta.getIsFavorita()) {
				favorita = cuenta;
				break;
			}
		}

		return favorita;
	}

	/**
	 * Obtener adicionales.
	 *
	 * @param tarjetas the tarjetas
	 * @return the list
	 */
	private List<Cuenta> obtenerAdicionales(List<Cuenta> tarjetas) {

		List<Cuenta> adicionales = new ArrayList<Cuenta>();

		for (Cuenta cuenta : tarjetas) {
			if (TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL.equals(cuenta.getCodigoTitularidad())) {
				adicionales.add(cuenta);
			}
		}

		return adicionales;
	}

	/**
	 * Mapear lista tarjetas.
	 *
	 * @param tarjetas the tarjetas
	 * @return the list
	 */
	private List<TarjetaHomeDTO> mapearListaTarjetas(List<Cuenta> tarjetas) {

		List<TarjetaHomeDTO> listaTarjetaHomeDTO = new ArrayList<TarjetaHomeDTO>();

		for (Cuenta cuenta : tarjetas) {
			TarjetaHomeDTO tarjetaHomeDTO = new TarjetaHomeDTO();
			tarjetaHomeDTO.setAlias(cuenta.getAlias());
			tarjetaHomeDTO.setHasAlias(StringUtils.isNotBlank(cuenta.getAlias()));
			tarjetaHomeDTO.setId(cuenta.getIndex());
			tarjetaHomeDTO.setMarca(TarjetaUtils.obtenerMarca(cuenta));
			tarjetaHomeDTO.setProducto(obtenerProductoDeTarjeta(cuenta));
			tarjetaHomeDTO.setSubProducto(obtenerSubProductoDeTarjeta(cuenta));
			tarjetaHomeDTO.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(),
					TarjetaUtils.getMarca(cuenta)));
			tarjetaHomeDTO.setSaldoPesos(ISBANStringUtils.parseStringToBigDecimal(cuenta.getSaldoParaConformar()));
			tarjetaHomeDTO.setRecargable(cuenta.esRecargable());

			listaTarjetaHomeDTO.add(tarjetaHomeDTO);
		}

		return listaTarjetaHomeDTO;
	}
	
	/**
	 * Obtener Producto de la tarjeta.
	 *
	 * @param cuenta the cuenta
	 * @return the string
	 */
	private String obtenerProductoDeTarjeta (Cuenta cuenta) {
		Integer tipo = Integer.parseInt(cuenta.getTipoCuenta().trim());
		return TipoProductoEnum.fromCodigo(tipo).getTipoProducto();
	}
	
	/**
	 * Obtener SubProducto de la tarjeta.
	 *
	 * @param cuenta the cuenta
	 * @return the string
	 */
	private String obtenerSubProductoDeTarjeta (Cuenta cuenta) {
		String clase = cuenta.getClaseCuenta();
		return TipoSubProductoEnum.fromCodigo(clase).getDescripcion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO#
	 * obtenerSaldoTarjeta(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * Cuenta)
	 */
	@Override
	public Respuesta<TarjetaHomeDTO> obtenerSaldoTarjeta(Cuenta cuenta) {
		try {
			String cardId = creditCardServiceConnector.getCreditCardId(cuenta.getNroTarjetaCredito());
			LimitsAndTotalsDto limitsAndTotalsDtoV2 = creditCardServiceConnector.getCreditCardLimits(cardId);
			TarjetaHomeDTO tarjetaHomeDTO = creditCardMapper.mapLimits(cuenta, limitsAndTotalsDtoV2);
			return respuestaFactory.crearRespuestaOk(tarjetaHomeDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TABLERO_HOME,
                    CodigoMensajeConstantes.ERROR_TABLERO_HOME);
        }
	}

	/**
	 * Obtiene las tarjetas adicionales y recargables. Para despues extraeras de las
	 * cajas que se muestran en la home. Esto se realiza ya que el servicio de
	 * PRISMA actualmente no devuelve datos para estas tarjetas. Cuando el servicio
	 * este nuevamente activo se podra suprimir este metodo y la linea que lo llama
	 * en el metodo obtenerTarjetas(Cliente cliente).
	 *
	 * @param tarjetas the tarjetas
	 * @return the list
	 */
	private List<Cuenta> obtenerRecargables(List<Cuenta> tarjetas) {
		List<Cuenta> recargables = new ArrayList<Cuenta>();
		for (Cuenta cuenta : tarjetas) {
			if (TarjetaBOUtils.esCuentaRecargable(cuenta)) {
				recargables.add(cuenta);
			}
		}
		return recargables;
	}
}
