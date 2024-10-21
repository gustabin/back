/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.AdhesionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;

/**
 * The Class EncabezadoTarjetasBOImpl.
 */
@Component
public class EncabezadoTarjetasBOImpl extends TarjetasBOImpl implements EncabezadoTarjetasBO {

	/** The Constant CONSULTA_DE_ADHESION_DE_TARJETA_OK. */
	private static final String CONSULTA_DE_ADHESION_DE_TARJETA_OK = "Consulta de adhesión de tarjeta OK";

	/** The Constant GENERANDO_RESPUESTA_DE_ERROR. */
	private static final String GENERANDO_RESPUESTA_DE_ERROR = "Generando respuesta de error...";

	/** The Constant ERROR_ADHESION_TARJETA. */
	private static final String ERROR_ADHESION_TARJETA = "No se pudo obtener información para la tarjeta: ";

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EncabezadoTarjetasBOImpl.class);

	/** The adhesion tarjetas DAO. */
	@Autowired
	private AdhesionTarjetasDAO adhesionTarjetasDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO#
	 * obtenerAdhesionTarjeta(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public Respuesta<AdhesionTarjeta> obtenerAdhesionTarjeta(Cliente cliente, Cuenta tarjeta) {
		// FIXME terminar esto, se paso para otro release/sprint
		Respuesta<AdhesionTarjeta> respuesta = new Respuesta<AdhesionTarjeta>();
		AdhesionTarjeta adhesionTarjeta = null;
		try {
			adhesionTarjeta = adhesionTarjetasDAO.consultarAdhesionTarjeta(tarjeta, cliente);
			LOGGER.info(CONSULTA_DE_ADHESION_DE_TARJETA_OK);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(adhesionTarjeta);
		} catch (DAOException e) {
			LOGGER.info(GENERANDO_RESPUESTA_DE_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
					ERROR_ADHESION_TARJETA + tarjeta.getNroCuentaProducto());
			respuesta.add(itemMensajeRespuesta);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO#
	 * obtenerAdhesionTarjetasRecargables(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<AdhesionTarjeta>> obtenerAdhesionTarjetasRecargables(Cliente cliente) {
		// FIXME revisar(terminar) esto, se paso para otro release/sprint
		Respuesta<List<AdhesionTarjeta>> respuesta = new Respuesta<List<AdhesionTarjeta>>();
		List<AdhesionTarjeta> listaAdhesionTarjetasRecargables = new ArrayList<AdhesionTarjeta>();
		// Obtiene todas las tarjetas recargables del cliente para consultar
		// adhesion
		Respuesta<List<Cuenta>> tarjetasRecargables = obtenerTarjetasRecargables(cliente);
		EstadoRespuesta estadoRespuesta = tarjetasRecargables.getEstadoRespuesta();
		// Guardo la respuesta del servicio para conservar el estado
		respuesta.setEstadoRespuesta(estadoRespuesta);
		List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
		// agrego todos los mensajes de error de la llamada anterior
		if(tarjetasRecargables.getItemsMensajeRespuesta() != null){
			itemMensajeRespuestaList.addAll(tarjetasRecargables.getItemsMensajeRespuesta());
		}
		if (EstadoRespuesta.ERROR.equals(estadoRespuesta)) {
			return respuesta;
		}
		Respuesta<AdhesionTarjeta> adhesionTarjetaRespuesta = null;
		for (Cuenta tarjeta : tarjetasRecargables.getRespuesta()) {
			// Consulta de adhesion por tarjeta
			adhesionTarjetaRespuesta = this.obtenerAdhesionTarjeta(cliente, tarjeta);
			AdhesionTarjeta adhesionTarjeta = adhesionTarjetaRespuesta.getRespuesta();
			// Caso OK, agrega la respuesta
			if (adhesionTarjetaRespuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				listaAdhesionTarjetasRecargables.add(adhesionTarjeta);
			} else {
				// Si surge error, se setea en warning la respuesta
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				//
				if (adhesionTarjeta == null) {
					itemMensajeRespuestaList.add(new ItemMensajeRespuesta(
							"No se pudo obtener detalles para la tarjeta: " + tarjeta.getNroCuentaProducto()));
				} else {
					itemMensajeRespuestaList.addAll(adhesionTarjetaRespuesta.getItemsMensajeRespuesta());
					listaAdhesionTarjetasRecargables.add(adhesionTarjeta);
				}
			}

		}
		// En caso de haber tarjetas recargables pero no se pudo consultar
		// ninguna dato de adhesion hay error.
		if (listaAdhesionTarjetasRecargables.isEmpty() && !tarjetasRecargables.getRespuesta().isEmpty()) {
			listaAdhesionTarjetasRecargables = null;
			estadoRespuesta = EstadoRespuesta.ERROR;
		}
		respuesta.setItemMensajeRespuesta(itemMensajeRespuestaList);
		respuesta.setRespuesta(listaAdhesionTarjetasRecargables);
		respuesta.setEstadoRespuesta(estadoRespuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO#
	 * obtenerTarjetasRecargables(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Respuesta<List<Cuenta>> obtenerTarjetasRecargables(Cliente cliente) {
		// FIXME revisar(terminar) esto, se paso para otro release/sprint
		List<Cuenta> tarjetasRecargables = new ArrayList<Cuenta>();
		List<Cuenta> cuentas = cliente.getCuentas();
		for (Cuenta cuenta : cuentas) {
			TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
			String claseCuenta = cuenta.getClaseCuenta();
			// Por alguna razon el dato de IATX se guarda en
			// CalidadDeParticipacion y codigoTitularidad se carga con el primer
			// caracter del anterior
			// String codigoTitularidad = cuenta->getCodigoTitularidad
			String codigoTitularidad = cuenta.getCalidadDeParticipacion();
			String nroOrdenFirmante = cuenta.getNroOrdenFirmante();
			if (TipoCuenta.VISA.equals(tipoCuentaEnum)) {
				if (("T".equalsIgnoreCase(claseCuenta)
						&& ("TI".equalsIgnoreCase(codigoTitularidad) || "001".equals(nroOrdenFirmante)))) {
					tarjetasRecargables.add(cuenta);
				} else if ("AD".equalsIgnoreCase(codigoTitularidad) && !"001".equals(nroOrdenFirmante)) {
					tarjetasRecargables.add(cuenta);
				}

			}
		}
		Respuesta<List<Cuenta>> respuesta = new Respuesta<List<Cuenta>>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(tarjetasRecargables);
		return respuesta;
	}

}
