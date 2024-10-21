/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf324931821440052060NillableString;
import ar.com.santanderrio.obp.generated.webservices.productos.InfoRequeridaWS;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.CambioGrupoAfinidadBO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadConsultaTarjetaDAO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadDAO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaDTO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadOutEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaOutEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoCuentaTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class CambioGrupoAfinidadBOImpl.
 */
@Component
public class CambioGrupoAfinidadBOImpl implements CambioGrupoAfinidadBO {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The cambio grupo afinidad DAO. */
	@Autowired
	private CambioGrupoAfinidadDAO cambioGrupoAfinidadDAO;

	/** The cambio grupo afinidad consulta tarjeta DAO. */
	@Autowired
	private CambioGrupoAfinidadConsultaTarjetaDAO cambioGrupoAfinidadConsultaTarjetaDAO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioGrupoAfinidadBOImpl.class);

	/** The Constant ESTADO_TARJETA_ACTIVA. */
	private static final String ESTADO_TARJETA_ACTIVA = "20";

	/** The Constant ESTADO_TARJETA_NO_RENOVAR. */
	private static final String ESTADO_TARJETA_NO_RENOVAR = "22";

	/** The Constant CODIGO_MARCA_VISA. */
	private static final String CODIGO_MARCA_VISA = "1";

	/** The Constant CODIGO_MARCA_AMEX. */
	private static final String CODIGO_MARCA_AMEX = "4";

	/** The Constant CODIGO_MARCA_MASTER. */
	private static final String CODIGO_MARCA_MASTER = "3";

	/** The Constant CODIGO_MARCA_MASTER. */
	private static final String CODIGO_TIPO_VISA = "1";

	/** The Constant CODIGO_TIPO_MASTER. */
	private static final String CODIGO_TIPO_MASTER = "3";

	/** The Constant CODIGO_MARCA_MASTER. */
	private static final String CODIGO_TIPO_AMEX = "3";

	/** The Constant COMENTARIO_ALTA_AADV. */
	private static final String COMENTARIO_ALTA_AADV = "Alta usuario advantage";

	/** The Constant TIPO_PERSONA_FISICA. */
	private static final String TIPO_PERSONA_FISICA = "F";

	/** The Constant COD_USER. */
	private static final String COD_USER = "HBAN0002";

	/** The Constant MEDIO_INGRESO. */
	private static final Integer MEDIO_INGRESO = 35;

	/** The Constant COD_SECTOR_ADVANTAGE. */
	private static final String COD_SECTOR_ADVANTAGE = "INFO";

	/** The Constant COD_VISA. */
	private static final Integer COD_VISA = 2470;

	/** The Constant COD_VISA. */
	private static final Integer COD_VISA_2 = 3106;

	/** The Constant COD_AMEX. */
	private static final Integer COD_AMEX = 2471;

	/** The Constant COD_SOCIO_ADV. */
	private static final Integer COD_SOCIO_ADV = 2948;

	/** The Constant COD_TIPO_TARJETA. */
	private static final Integer COD_TIPO_TARJETA = 3039;

	/** The Constant COD_NUM_TARJETA. */
	private static final Integer COD_NUM_TARJETA = 3056;

	/** The Constant TIPO_TARJETA_VISA. */
	private static final String TIPO_TARJETA_VISA = "VISA";

	/** The Constant TIPO_TARJETA_AMEX. */
	private static final String TIPO_TARJETA_AMEX = "AMEX";

	/** The Constant TIPO_TARJETA_MASTER. */
	private static final String TIPO_TARJETA_MASTER = "MAST";

	/** The Constant DESC_AMEX. */
	private static final String DESC_AMEX = "Numero de cuenta Amex";

	/** The Constant DESC_VISA. */
	private static final String DESC_VISA = "Numero de cuenta Visa";

	/** The Constant DESC_VISA. */
	private static final String DESC_VISA_2 = "Numero de cuenta Visa 2";

	/** The Constant DESC_SOCIO_ADV. */
	private static final String DESC_SOCIO_ADV = "Numero de socio AAdvantage";

	/** The Constant DESC_TIPO_TARJETA. */
	private static final String DESC_TIPO_TARJETA = "Tipo de Tarjeta de Credito";

	/** The Constant DESC_NUM_TARJETA. */
	private static final String DESC_NUM_TARJETA = "Numero de Cuenta";

	/** The Constant COMENTARIO_MODIF. */
	private static final String COMENTARIO_MODIF = "Modificar grupo de afinidad a";

	/** The Constant MODIF_AADVANTAGE. */
	private static final String MODIF_AADVANTAGE = "Advantage";

	/** The Constant MODIF_SUPERCLUB. */
	private static final String MODIF_SUPERCLUB = "Super Club";

	/** The Constant CODIGO_TARJETA_CERRADA. */
	private static final String CODIGO_TARJETA_CERRADA = "29";

	/** The Constant CODIGO_TARJETA_CON_PROBLEMAS. */
	private static final String CODIGO_TARJETA_CON_PROBLEMAS = "25";

	/** The codigo de asociacion. */
	@Value("${ADVANTAGE.COD.ASOCIACION}")
	private String codAsociacion;

	/** The cod modificacion. */
	@Value("${ADVANTAGE.CODIGO.MODIFICACION}")
	private String codModificacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.
	 * CambioGrupoAfinidadBO#obtenerCuentasTarjetasCredito(boolean)
	 */
	public Respuesta<List<Cuenta>> obtenerCuentasTarjetasCredito(boolean isAfinidadAadvantage) {

		Respuesta<List<Cuenta>> respuesta = new Respuesta<List<Cuenta>>();
		List<Cuenta> cuentasTarjetasCredito = new ArrayList<Cuenta>();

		Cliente cliente = sesionCliente.getCliente();
		if (cliente.getCuentas().isEmpty()) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuesta(cuentasTarjetasCredito);
			return respuesta;
		}

		for (Cuenta cuenta : cliente.getCuentas()) {
			if ((cuenta.esTitularTarjetaVisa() || cuenta.esTitularTarjetaAmex() || cuenta.esTitularTarjetaMaster())
					&& TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())) {

				cuentasTarjetasCredito.add(cuenta);
			}
		}

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(cuentasTarjetasCredito);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.
	 * CambioGrupoAfinidadBO#buildTarjetasAsociadas(java.util.List,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	public Respuesta<List<TarjetaAsociadaDTO>> buildTarjetasAsociadas(List<Cuenta> cuentasTarjetasCredito,
			Cliente cliente) {
		Respuesta<List<TarjetaAsociadaDTO>> respuesta = new Respuesta<List<TarjetaAsociadaDTO>>();
		List<TarjetaAsociadaDTO> tarjetasAsociadas = new ArrayList<TarjetaAsociadaDTO>();

		for (Cuenta cuenta : cuentasTarjetasCredito) {
			ConsultaDatosTarjetaInEntity in = new ConsultaDatosTarjetaInEntity();
			in.setNroCuenta(cuenta.getNroCuentaProducto());
			in.setCliente(cliente);
			in.setTipoCuenta(getTipoCuenta(cuenta));
			in.setMarca(getMarca(cuenta));
			try {
				ConsultaDatosTarjetaOutEntity respuestaConsultaDatosTarjetas = cambioGrupoAfinidadConsultaTarjetaDAO
						.consultaDatosTarjetas(in);
				for (TarjetaDatos tarjetaDatos : respuestaConsultaDatosTarjetas.getTarjetas()) {
					if ((ESTADO_TARJETA_ACTIVA.equals(tarjetaDatos.getEstadoTarjeta())
							|| ESTADO_TARJETA_NO_RENOVAR.equals(tarjetaDatos.getEstadoTarjeta()))
							&& cuenta.getNroTarjetaCredito().contains(tarjetaDatos.getNroTarjeta())) {
						TarjetaAsociadaDTO tarjetaCredito = new TarjetaAsociadaDTO();
						tarjetaCredito.setTipoTarjeta(
								TipoCuentaTarjetaEnum.fromCodigo(cuenta.getTipoCuenta()).getAbreviatura());
						tarjetaCredito.setNumeroFormateado(TarjetaUtils.obtenerNroTarjetaEnmascarada(cuenta));
						tarjetaCredito.setTitular(tarjetaDatos.getApellidoNombreEmbozado());
						tarjetaCredito.setTitular(cuenta.getCliente().getNombre()
								.concat(ISBANStringUtils.ESPACIO_STRING).concat(cuenta.getCliente().getApellido1()));
						tarjetasAsociadas.add(tarjetaCredito);
					}
				}
			} catch (DAOException e) {
				LOGGER.error("Error al consultar la tarjeta, Error", e);
			}
		}
		if (!tarjetasAsociadas.isEmpty()) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(tarjetasAsociadas);
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.
	 * CambioGrupoAfinidadBO#altaSolicitudAdhesion(java.lang.String,
	 * java.lang.String, boolean)
	 */
	public Respuesta<String> altaSolicitudAdhesion(String nroSocioAdvantage, String nup, boolean isUsuarioAdvantage) {
		AltaSolicitudCambioAfinidadInEntity inEntity = new AltaSolicitudCambioAfinidadInEntity();
		Respuesta<List<Cuenta>> respuestaCuentas = obtenerCuentasTarjetasCredito(isUsuarioAdvantage);
		Cliente cliente = sesionCliente.getCliente();

		this.cargarInfoCabeceraWS(inEntity, cliente.getNup(), isUsuarioAdvantage, nroSocioAdvantage);
		this.cargarInfoRequeridaWS(inEntity, respuestaCuentas.getRespuesta());

		try {
			AltaSolicitudCambioAfinidadOutEntity respuestaDAO = cambioGrupoAfinidadDAO.altaSolicitudAdhesion(inEntity);
			if (respuestaDAO.getNroGestion() != null) {
				return respuestaFactory.crearRespuestaOk(respuestaDAO.getNroGestion());
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestionUser en CambioGrupoAfinidadBOImpl", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.
	 * CambioGrupoAfinidadBO#generarComprobanteCambioGrupoAfinidad(ar.com.
	 * santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.
	 * ComprobanteSolicitudCambioAfinidadView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteCambioGrupoAfinidad(
			ComprobanteSolicitudCambioAfinidadView datosComprobante) {

		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = cambioGrupoAfinidadDAO.generarComprobanteCambioGrupoAfinidad(datosComprobante);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * Gets the marca.
	 *
	 * @param cuenta the cuenta
	 * @return the marca
	 */
	private String getMarca(Cuenta cuenta) {

		String tipoCuenta = cuenta.getTipoCuenta();
		if (TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(tipoCuenta)) {
			return CODIGO_MARCA_VISA;
		} else if (TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo().equals(tipoCuenta)) {
			return CODIGO_MARCA_AMEX;
		} else if (TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo().equals(tipoCuenta)) {
			return CODIGO_MARCA_MASTER;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the tipo cuenta
	 */
	private String getTipoCuenta(Cuenta cuenta) {

		String tipoCuenta = cuenta.getTipoCuenta();
		if (TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(tipoCuenta)) {
			return CODIGO_TIPO_VISA;
		} else if (TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo().equals(tipoCuenta)) {
			return CODIGO_TIPO_AMEX;
		} else if (TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo().equals(tipoCuenta)) {
			return CODIGO_TIPO_MASTER;
		}

		return StringUtils.EMPTY;
	}

	/**
	 * Setea info de cabecera del XML para el consumo del WS.
	 *
	 * @param inEntity           the in entity
	 * @param nup                the nup
	 * @param isUsuarioAdvantage the is usuario advantage
	 * @param nroSocioAdvantage  the nro socio advantage
	 */
	private void cargarInfoCabeceraWS(AltaSolicitudCambioAfinidadInEntity inEntity, String nup,
			boolean isUsuarioAdvantage, String nroSocioAdvantage) {
		LOGGER.info("Se ejecuta el metodo de carga de info parcial CambioGrupoAfinidad.cargarInfoCabeceraWS");
		inEntity.setUsuarioAdvantage(isUsuarioAdvantage);
		inEntity.setNroSocioAdvantage(!isUsuarioAdvantage ? nroSocioAdvantage : null);
		inEntity.setCodAsociacion(Integer.parseInt(codAsociacion));
		inEntity.setTipoPersona(TIPO_PERSONA_FISICA);
		inEntity.setNup(Integer.parseInt(nup));
		inEntity.setCodUser(COD_USER);
		inEntity.setCodSector(COD_SECTOR_ADVANTAGE);
		inEntity.setMedioIngreso(MEDIO_INGRESO);
		inEntity.setComentario(COMENTARIO_ALTA_AADV);

	}

	/**
	 * Cargar info requerida WS.
	 *
	 * @param inEntity        the in entity
	 * @param cuentasTarjetas the cuentas tarjetas
	 */
	private void cargarInfoRequeridaWS(AltaSolicitudCambioAfinidadInEntity inEntity, List<Cuenta> cuentasTarjetas) {
		LOGGER.info(
				"Se ejecuta el metodo de carga de info requerida para el WS CambioGrupoAfinidadBOImpl.cargarInfoRequerida");
		ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida = new ArrayOf158770343432493182NillableInfoRequeridaWS();
		List<Cuenta> cuentasTarjetasHabilitadas = obtenerCuentasTarjetasHabilitadas(cuentasTarjetas);
		Integer codigoModif = Integer.parseInt(codModificacion);
		InfoRequeridaWS infoModificacion = crearItemInfoRef(codigoModif, COMENTARIO_MODIF, null);
		InfoRequeridaWS infoTipoTarjeta = crearItemInfoRef(COD_TIPO_TARJETA, DESC_TIPO_TARJETA, null);
		InfoRequeridaWS infoNumTarjeta = crearItemInfoRef(COD_NUM_TARJETA, DESC_NUM_TARJETA, null);
		InfoRequeridaWS infoNroSocio = crearItemInfoRef(COD_SOCIO_ADV, DESC_SOCIO_ADV, null);
		String Modificacion = null;
		String NroSocio = null;

		if (inEntity.isUsuarioAdvantage()) {
			Modificacion = MODIF_SUPERCLUB;
		} else {
			Modificacion = MODIF_AADVANTAGE;
		}

		if (StringUtils.isNotEmpty(inEntity.getNroSocioAdvantage()) && inEntity.getNroSocioAdvantage().length() <= 7) {
			NroSocio = inEntity.getNroSocioAdvantage();
		}

		if (!cuentasTarjetasHabilitadas.isEmpty()) {
			for (Cuenta cuenta : cuentasTarjetasHabilitadas) {
				infoModificacion.getValor().getString().add(Modificacion);
				// VISA
				if (TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(cuenta.getTipoCuenta())
						&& infoRequerida.getInfoRequeridaWS().isEmpty()) {
					infoTipoTarjeta.getValor().getString().add(TIPO_TARJETA_VISA);
					infoNumTarjeta.getValor().getString().add(cuenta.getNroCuentaProducto().substring(
							cuenta.getNroCuentaProducto().length() - 10, cuenta.getNroCuentaProducto().length()));

					// VISA EX CITI
				} else if (TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(cuenta.getTipoCuenta())) {
					infoTipoTarjeta.getValor().getString().add(TIPO_TARJETA_VISA);
					infoNumTarjeta.getValor().getString().add(cuenta.getNroCuentaProducto().substring(
							cuenta.getNroCuentaProducto().length() - 10, cuenta.getNroCuentaProducto().length()));

					// AMEX
				} else if (TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo().equals(cuenta.getTipoCuenta())) {
					infoTipoTarjeta.getValor().getString().add(TIPO_TARJETA_AMEX);
					infoNumTarjeta.getValor().getString()
							.add(cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - 9,
									cuenta.getNroCuentaProducto().length()) + cuenta.getCbu().charAt(18));

					// MASTER
				} else if (TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo().equals(cuenta.getTipoCuenta())) {
					infoTipoTarjeta.getValor().getString().add(TIPO_TARJETA_MASTER);
					infoNumTarjeta.getValor().getString()
							.add(cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - 9,
									cuenta.getNroCuentaProducto().length()) + cuenta.getCbu().charAt(18));
				}

				if (StringUtils.isNotEmpty(NroSocio)) {
					infoNroSocio.getValor().getString().add(NroSocio);
				} else {
					infoNroSocio.getValor().getString().add("");
				}

			}
		}
		infoRequerida.getInfoRequeridaWS().add(infoModificacion);
		infoRequerida.getInfoRequeridaWS().add(infoTipoTarjeta);
		infoRequerida.getInfoRequeridaWS().add(infoNumTarjeta);
		infoRequerida.getInfoRequeridaWS().add(infoNroSocio);
		inEntity.setInfoRequerida(infoRequerida);
	}

	/**
	 * @param cuentasTarjetas
	 * @return
	 */
	private List<Cuenta> obtenerCuentasTarjetasHabilitadas(List<Cuenta> cuentasTarjetas) {
		List<Cuenta> cuentasTarjetasHabilitadas = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cuentasTarjetas) {
			if (!cuenta.getEstadoTarjetaCredito().equals(CODIGO_TARJETA_CERRADA)
					&& !cuenta.getEstadoTarjetaCredito().equals(CODIGO_TARJETA_CON_PROBLEMAS)) {
				cuentasTarjetasHabilitadas.add(cuenta);
			}
		}
		return cuentasTarjetasHabilitadas;
	}

	/**
	 * @param infoRequeridaWS
	 * @param codVisa
	 * @param descVisa
	 * @return
	 */
	private InfoRequeridaWS ajustarInfo(InfoRequeridaWS infoRequeridaWS, Integer codVisa, String descVisa) {
		if (infoRequeridaWS == null) {
			return crearItemInfoRef(codVisa, descVisa, null);
		}
		return infoRequeridaWS;
	}

	/**
	 * @param codVisa
	 * @param descVisa
	 * @param cuenta
	 * @return
	 */
	private InfoRequeridaWS crearItemInfoRef(Integer codVisa, String descVisa, Cuenta cuenta) {
		InfoRequeridaWS infoRequerida = new InfoRequeridaWS();
		infoRequerida.setCodigo(codVisa);
		infoRequerida.setDescripcion(descVisa);
		ArrayOf324931821440052060NillableString valor = new ArrayOf324931821440052060NillableString();
		if (cuenta != null) {
			valor.getString().add(cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - 10,
					cuenta.getNroCuentaProducto().length()));
		}
		infoRequerida.setValor(valor);
		return infoRequerida;
	}

	@Override
	public Respuesta<String> obtenerGrupoAfinidadParaFlujos(String nroSocioAdvantage,
			EventosComercialesDTO ofertasComerciales) {

		String respuesta = "";

		if (!ofertasComerciales.getOfertas().isEmpty() || ofertasComerciales.getCantidadPuntos() != null
				|| StringUtils.isNumeric(ofertasComerciales.getCantidadPuntos())) {
			respuesta = "superclub";
		}
		
		if (nroSocioAdvantage != null) {

			if ("superclub".equalsIgnoreCase(respuesta)) {
				respuesta = "ambas";
			} else {
				respuesta = "aadvantage";
			}
		}
		
		return respuestaFactory.crearRespuestaOk(respuesta);
	}

}
