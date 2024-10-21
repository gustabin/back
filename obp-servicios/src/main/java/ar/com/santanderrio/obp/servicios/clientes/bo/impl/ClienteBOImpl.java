/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteSeguridadDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.delete.account.utils.AccountUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ClienteBOImpl.
 */
@Component
public class ClienteBOImpl implements ClienteBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteBOImpl.class);

	/** The Constant ERROR_TARJETA_VISA_MASTERCARD. */
	private static final Integer ERROR_TARJETA_VISA_MASTERCARD = 20010071;

	/** The Constant ERROR_TARJETA_VISA_MASTERCARD_BANELCO. */
	private static final Integer ERROR_TARJETA_VISA_MASTERCARD_BANELCO = 20010075;

	/** The Constant ERROR_ALTAIR_PRESTAMOS. */
	private static final Integer ERROR_ALTAIR_PRESTAMOS = 20010081;

	/** The Constant ERROR_TARJETA_BANELCO. */
	private static final Integer ERROR_TARJETA_BANELCO = 20010073;

	/** The Constant SIN_ERROR. */
	private static final Integer SIN_ERROR = 20010079;

	/** The Constant SIN_ERROR_2. */
	private static final Integer SIN_ERROR_2 = 20010080;

	/** The Constant SIN_ERROR_2. */
	private static final Integer SIN_PRODUCTO = 20010077;

	/** The Constant ESTADO_OK. */
	private static final Integer ESTADO_OK = 0;

	/** Constant to compare if client is PAS **/
	private static final String CLIENTE_ES_PAS = "00000000000000000001";
	
	/** The cliente dao. */
	@Autowired
	private ClienteDAO clienteDAO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The respuesta BO. */
	@SuppressWarnings("deprecation")
	@Autowired
	private RespuestaBO respuestaBO;
	
	@Autowired
	private ClienteSeguridadDAO clienteSeguridadDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.clientes.bo.ClienteBO#obtenerCliente(ar.com.
	 * santanderrio.base.clientes.entities.ResumenCliente)
	 */
	@Override
	public Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente) {

		Respuesta<Cliente> respuesta = null;
		ClienteConSaldoResponse clienteConSaldo = clienteDAO.obtenerCliente(resumenCliente);

		List<Cuenta> cuentas = clienteConSaldo.getCliente().getCuentas();

		sesionParametros.setPas(false);
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getNroTarjetaCredito().equals(CLIENTE_ES_PAS)) {
				sesionParametros.setPas(true);
				break;
			}
		}

		if (EstadoRespuesta.ERROR.equals(clienteConSaldo.getEstadoRespuesta()) 
				&& (clienteConSaldo.getCodigoRespuesta() == null ||
					clienteConSaldo.getCodigoRespuesta().intValue() != AccountUtils.ERROR_SALDO.intValue())) {
			return respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
		}
		respuesta = evaluarRespuestaClienteConSaldo(clienteConSaldo);
		if (clienteConSaldo.getCodigoRespuesta().intValue() == AccountUtils.ERROR_SALDO.intValue()) {
			sesionParametros.setErrorSaldo(true);
		}
    	
		Respuesta<Void> respuestaAliasFav = cargarAliasYFavoritos(clienteConSaldo.getCliente());
		if (EstadoRespuesta.ERROR.equals(respuestaAliasFav.getEstadoRespuesta())) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			List<ItemMensajeRespuesta> itemsAliasFav = respuestaAliasFav.getItemsMensajeRespuesta();
			for (ItemMensajeRespuesta itemMensajeRespuesta2 : itemsAliasFav) {
				respuesta.add(itemMensajeRespuesta2);
			}
		}
		//System.out.println("Cuenta Tit Repatriacion"+respuesta.getRespuesta().getCuentasTitRtlRepatriacion().get(0).getNroCuentaProducto());
		return respuesta;
	}
	
	/**
	 * Evaluar warnings cliente con saldo.
	 *
	 * @param clr
	 *            the clr
	 * @return the respuesta
	 */
	private Respuesta<Cliente> evaluarRespuestaClienteConSaldo(ClienteConSaldoResponse clr) {

		Respuesta<Cliente> respuestaCliente = null;

		if (ERROR_TARJETA_VISA_MASTERCARD.equals(clr.getCodigoRespuesta())) {
			respuestaCliente = respuestaFactory.crearRespuestaWarning(clr.getCliente(), null,
					TipoError.ERROR_TARJETA_VISA_MASTERCARD, "");
		} else if (ERROR_TARJETA_VISA_MASTERCARD_BANELCO.equals(clr.getCodigoRespuesta())) {
			respuestaCliente = respuestaFactory.crearRespuestaWarning(clr.getCliente(), null,
					TipoError.ERROR_TARJETA_MASTERCARD_VISA_BANELCO, "");
		} else if (ERROR_ALTAIR_PRESTAMOS.equals(clr.getCodigoRespuesta())) {
			respuestaCliente = respuestaFactory.crearRespuestaWarning(clr.getCliente(), null,
					TipoError.ERROR_ALTAIR_PRESTAMOS, "");
		} else if (ERROR_TARJETA_BANELCO.equals(clr.getCodigoRespuesta())) {
			respuestaCliente = respuestaFactory.crearRespuestaWarning(clr.getCliente(), null,
					TipoError.ERROR_TARJETA_BANELCO, "");
		}else if (AccountUtils.ERROR_SALDO.equals(clr.getCodigoRespuesta())) {
					respuestaCliente = respuestaFactory.crearRespuestaWarning(clr.getCliente(), null,
							TipoError.ERROR_SALDO, "");
		} else if (SIN_ERROR.equals(clr.getCodigoRespuesta()) || SIN_ERROR_2.equals(clr.getCodigoRespuesta())
				|| ESTADO_OK.equals(clr.getCodigoRespuesta())) {
			respuestaCliente = respuestaFactory.crearRespuestaOk(clr.getCliente());
		} else if (SIN_PRODUCTO.equals(clr.getCodigoRespuesta())) {
			clr.getCliente().setSinProductos(true);
			respuestaCliente = respuestaFactory.crearRespuestaOk(clr.getCliente());
		} else {
			respuestaCliente = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
		}

		return respuestaCliente;
	}

	/**
	 * Cargar alias Y favoritos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	@SuppressWarnings("deprecation")
	private Respuesta<Void> cargarAliasYFavoritos(Cliente cliente) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		try {
			cuentaBO.cargarAliasYFavoritos(cliente, true);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_OBTENER_FAVORITO,
					CodigoMensajeConstantes.CODIGO_ERROR_CARGAR_ALIAS_FAVORITO, null);
		}
		return respuesta;
	}

	/**
	 * Actualiza la fecha de ultimo cambio de clave
	 * 
	 * @param nup
	 */
	@Override
	public Respuesta<Boolean> actualizarUltimaFechaCambioClave(Long nup) throws BusinessException {
		
		Boolean resultado = clienteSeguridadDAO.actualizarFechaActualizacionClave(nup);
		
		if(resultado) {
			return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
		}
		
		return respuestaFactory.crearRespuestaError(null);
	}

	@Override
	public Respuesta<List<BigDecimal>> obtenerAntiguedadDiasUltCambioClaveToken(Long nup) throws BusinessException {
		
		List<BigDecimal> antiguedades = clienteSeguridadDAO.obtenerAntiguedadDiasUltCambioClaveToken(nup);
		
		if(antiguedades != null) {
			return respuestaFactory.crearRespuestaOk(antiguedades);
		}
		
		return respuestaFactory.crearRespuestaError(null);
	}
	

}
