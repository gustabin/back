/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import java.net.SocketTimeoutException;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ConfirmarOrdenFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ConsultarOrdenFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ConsultarTenenciaRenovableFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.DownloadArchivoFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ILicitacionesCanalService;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ObtenerCanalTramoFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ObtenerCuentasTitulosFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ObtenerSaldoCuentasCustodiaFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ReversarOrdenFault;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.SimularOrdenFault;
import ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws.LicitacionesWSImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerTiposPliego;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerTiposPliegoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.exception.TiempoEsperaAgotadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * Implementacion del DAO de licitaciones con WS BEL.
 *
 * @author marcelo.ruiz
 */

@Component("LicitacionesWSDAO")
public class TitulosWSDAOImpl implements TitulosDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TitulosWSDAOImpl.class);

	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "0";

	/** The Constant ERROR_FUERA_DE_HORARIO. */
	private static final String ERROR_FUERA_DE_HORARIO = "4";

	/** The Constant LIMITE_TIEMPO_SUPERADO. */
	private static final String LIMITE_TIEMPO_SUPERADO = "3";
	
	/** The Constant SALDO_INSUFICIENTE_PESOS. */
	private static final String SALDO_INSUFICIENTE_PESOS = "10000515";
	
	/** The Constant SALDO_INSUFICIENTE_DOLARES. */
	private static final String SALDO_INSUFICIENTE_DOLARES = "10002122";

	/** The ws soap. */
	@Autowired
	private LicitacionesWSImpl wsSoap;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#obtenerCuentasTitulos(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos)
	 */
	@Override
	public ObtenerCuentasTitulosResponse obtenerCuentasTitulos(ObtenerCuentasTitulos request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.obtenerCuentasTitulos");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ObtenerCuentasTitulosResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.obtenerCuentasTitulos(miJson);
				respuesta = gson.fromJson(response, ObtenerCuentasTitulosResponse.class);
				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					LOGGER.error("Error al invocar a BEL.obtenerCuentasTitulos. ");
					throw new DAOException();
				}
			} catch (ObtenerCuentasTitulosFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#consultarTenenciaRenovable(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable)
	 */
	@Override
	public ConsultarTenenciaRenovableResponse consultarTenenciaRenovable(ConsultarTenenciaRenovable request)
			throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.obtenerCuentasTitulos");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ConsultarTenenciaRenovableResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.consultarTenenciaRenovable(miJson);
				respuesta = gson.fromJson(response, ConsultarTenenciaRenovableResponse.class);
			} catch (ConsultarTenenciaRenovableFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#downloadArchivo(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.entity.DownloadArchivo)
	 */
	@Override
	public DownloadArchivoResponse downloadArchivo(DownloadArchivo request) throws DAOException {

		LOGGER.info("llamando a servicio soap BEL.downloadArchivo");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		DownloadArchivoResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.downloadArchivo(miJson);
				respuesta = gson.fromJson(response, DownloadArchivoResponse.class);
				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					LOGGER.error("Error al invocar a BEL.downloadArchivo. ");
					throw new DAOException();
				}
			} catch (DownloadArchivoFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL.downloadArchivo ");
				throw new DAOException();
			}
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#reversarOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.entity.ReversarOrdenEntity)
	 */
	@Override
	public ReversarOrdenResponse reversarOrdenLicitacion(ReversarOrdenEntity request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.reversarOrden");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ReversarOrdenResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.reversarOrden(miJson);
				respuesta = gson.fromJson(response, ReversarOrdenResponse.class);
			} catch (ReversarOrdenFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			} catch (WebServiceException e) {
				if (e.getCause() instanceof SocketTimeoutException) {
					LOGGER.error("Error de TIMEOUT al invocar al web service BEL. {}.", e.getMessage(), e);
					throw new TimeOutException(e.getMessage());
				}
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#obtenerLicitaciones(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ObtenerCanalTramo)
	 */
	@Override
	public ObtenerCanalTramoResponse obtenerLicitaciones(ObtenerCanalTramo request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.obtenerCanalTramo");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ObtenerCanalTramoResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.obtenerCanalTramo(miJson);
				respuesta = gson.fromJson(response, ObtenerCanalTramoResponse.class);
				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					LOGGER.error("Error al invocar a BEL.obtenerCanalTramo. ");
					throw new DAOException();
				}
			} catch (ObtenerCanalTramoFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#obtenerSaldoCuentasCustodia(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodia)
	 */
	@Override
	public ObtenerSaldoCuentasCustodiaResponse obtenerSaldoCuentasCustodia(ObtenerSaldoCuentasCustodia request)
			throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.obtenerSaldoCuentasCustodia");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new Gson();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ObtenerSaldoCuentasCustodiaResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.obtenerSaldoCuentasCustodia(miJson);
				respuesta = gson.fromJson(response, ObtenerSaldoCuentasCustodiaResponse.class);
			} catch (ObtenerSaldoCuentasCustodiaFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#confirmarOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.entity.ConfirmarOrden)
	 */
	@Override
	public ConfirmarOrdenResponse confirmarOrden(ConfirmarOrden request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.confirmarOrden");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ConfirmarOrdenResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.confirmarOrden(miJson);
				respuesta = gson.fromJson(response, ConfirmarOrdenResponse.class);
				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					if (LIMITE_TIEMPO_SUPERADO.equals(respuesta.getCodigo())) {
						LOGGER.error("Limite de tiempo disponible para realiza la operacion superado. ");
						throw new TiempoEsperaAgotadoException();
					} else if (SALDO_INSUFICIENTE_PESOS.equals(respuesta.getCodigo())) {
						LOGGER.error("No hay suficientes pesos para realizar la operacion");
						throw new DAOException(SALDO_INSUFICIENTE_PESOS);
					} else if (SALDO_INSUFICIENTE_DOLARES.equals(respuesta.getCodigo())) {
						LOGGER.error("No hay suficientes dolares para realizar la operacion");
						throw new DAOException(SALDO_INSUFICIENTE_DOLARES);
					} else {
						LOGGER.error("Codigo de respuesta desconocido de web service BEL.confirmarOrden. Codigo: ",
								respuesta.getCodigo());
						throw new DAOException();
					}
				}
			} catch (ConfirmarOrdenFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			} catch (WebServiceException e) {
				if (e.getCause() instanceof SocketTimeoutException) {
					LOGGER.error("Error de TIMEOUT al invocar al web service BEL. {}.", e.getMessage(), e);
					throw new TimeOutException(e.getMessage());
				}
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException();
			}
		} finally {
			wsSoap.liberarPort(cliente);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#obtenerTiposPliego(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ObtenerTiposPliego)
	 */
	@Override
	public ObtenerTiposPliegoResponse obtenerTiposPliego(ObtenerTiposPliego request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#consultarOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.entity.ConsultarOrden)
	 */
	// MIGUE.
	@Override
	public ConsultarOrdenResponse consultarOrdenLicitacion(ConsultarOrdenLicitacion request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.consultarOrden");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		ConsultarOrdenResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.consultarOrden(miJson);
				respuesta = gson.fromJson(response, ConsultarOrdenResponse.class);

				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					LOGGER.error("Error del servicio. Codigo distinto de 0");
					throw new DAOException();
				}
			} catch (ConsultarOrdenFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			} catch (WebServiceException e) {
				LOGGER.error("Error webservice");
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#simularOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.entity.SimularOrden)
	 */
	@Override
	public SimularOrdenResponse simularOrden(SimularOrden request) throws DAOException {
		LOGGER.info("llamando a servicio soap BEL.simularOrden");

		String miJson = (new GsonBuilder()).create().toJson(request);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		ILicitacionesCanalService cliente = null;
		String response = null;
		SimularOrdenResponse respuesta = null;
		try {
			cliente = wsSoap.obtenerPort();
			try {
				response = cliente.simularOrden(miJson);
				respuesta = gson.fromJson(response, SimularOrdenResponse.class);
				if (ERROR_FUERA_DE_HORARIO.equals(respuesta.getCodigo())) {
					LOGGER.error("Error del servicio por FUERA DE HORARIO. Codigo: ", respuesta.getCodigo());
					throw new FueraDeHorarioException();
				}
				if (!CODIGO_OK.equals(respuesta.getCodigo())) {
					LOGGER.error("Error del servicio. Codigo desconocido: ", respuesta.getCodigo());
					throw new DAOException();
				}
			} catch (SimularOrdenFault e) {
				LOGGER.error("Error al invocar al web service BEL. {}.", e.getMessage(), e);
				throw new DAOException(e);
			}
		} finally {
			wsSoap.liberarPort(cliente);
			if (response == null) {
				LOGGER.debug("Error al invocar al web service BEL. ");
				throw new DAOException();
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.
	 * TitulosDAO#obtenerSaldoCuentasTitulos(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasTitulos)
	 */
	@Override
	public ObtenerSaldoCuentasTitulosResponse obtenerSaldoCuentasTitulos(ObtenerSaldoCuentasTitulos request) {
		// TODO Auto-generated method stub
		return null;
	}
}
