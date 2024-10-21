/*
 * 
 */

package ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;

/**
 * The Class EstadisticaManagerImpl.
 */
@Component
@Scope("singleton")
// TODO sergio: Llamar a la cola asincronica
public class EstadisticaManagerImpl implements EstadisticaManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EstadisticaManagerImpl.class);

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The estadistica BO. */
	@Autowired
	private EstadisticaBO estadisticaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.estadistica.web.manager.EstadisticaManager#
	 * add(ar.com.santanderrio.obp.comun.estadisticas.entities.Estadistica)
	 */
	public boolean add(Estadistica estadistica) {
		try {
			LOGGER.info("Generando estadistica...");
			estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), sesionCliente.getCliente());
			return true;
		} catch (BusinessException e) {
			LOGGER.error("Error al crear logs de estadistica {}.", estadistica, e);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.estadistica.web.manager.EstadisticaManager#
	 * add(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(String codigoTransaccion, String resultado) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		LOGGER.info("Generando estadistica...");
		if (add(estadistica)) {
			return true;
		} else {
			LOGGER.debug("Error al crear logs de estadistica {}.", codigoTransaccion);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.
	 * EstadisticaManager#add(ar.com.santanderrio.obp.servicios.comun.
	 * estadisticas.entities.Estadistica,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public boolean add(Estadistica estadistica, RegistroSesion registroSesion, Cliente cliente) {
		try {
			LOGGER.info("Generando estadistica...");
			estadisticaBO.add(estadistica, registroSesion, cliente);
			return true;
		} catch (BusinessException e) {
			LOGGER.error("Error al crear logs de estadistica {}.", estadistica, e);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.
	 * EstadisticaManager#add(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean add(String codigoTransaccion, String resultado, String nroCtaOrigen) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		estadistica.setNroCtaOrigen(nroCtaOrigen);
		LOGGER.info("Generando estadistica...");
		if (add(estadistica)) {
			return true;
		} else {
			LOGGER.debug("Error al crear logs de estadistica {}.", codigoTransaccion);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.
	 * EstadisticaManager#add(ar.com.santanderrio.obp.servicios.comun.
	 * estadisticas.entities.Estadistica, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(Estadistica estadistica, String codigoTransaccion, String resultado) {
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		LOGGER.info("Generando estadistica...");
		if (add(estadistica)) {
			return true;
		} else {
			LOGGER.debug("Error al crear logs de estadistica {}.", codigoTransaccion);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.
	 * EstadisticaManager#add(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(String codigoTransaccion, String resultado, String nroCtaDestino, String importe,
			String moneda) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		estadistica.setNroCtaDestino(nroCtaDestino);
		estadistica.setImporte(importe);
		estadistica.setMoneda(moneda);

		LOGGER.info("Generando estadistica...");
		if (add(estadistica)) {
			return true;
		} else {
			LOGGER.debug("Error al crear logs de estadistica {}.", codigoTransaccion);
			return false;
		}
	}

	@Override
	public boolean add(String codigoTransaccion, String resultado, String nroComprobante, BigDecimal importe,
			String moneda) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		estadistica.setNroComprobante(nroComprobante);
		estadistica.setImporte(importe);
		estadistica.setMoneda(moneda);

		LOGGER.info("Generando estadistica...");
		if (add(estadistica)) {
			return true;
		} else {
			LOGGER.debug("Error al crear logs de estadistica {}.", codigoTransaccion);
			return false;
		}
	}

}
