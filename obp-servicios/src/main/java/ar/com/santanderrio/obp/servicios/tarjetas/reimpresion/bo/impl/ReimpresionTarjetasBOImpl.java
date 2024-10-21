/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.ReimpresionTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.ReimpresionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ComprobanteAltaReimpresionTarjetas;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitada;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitadaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetas;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.TarjetaSolicitadaView;

/**
 * The Class ReimpresionTarjetasBOImpl.
 */
@Component
public class ReimpresionTarjetasBOImpl implements ReimpresionTarjetasBO {

	/** The Constant DESCRIPCION_AMEX. */
	private static final String DESCRIPCION_AMEX = "AMEX";

	/** The Constant DESCRIPCION_BANELCO. */
	private static final String DESCRIPCION_BANELCO = "VISA Debito";

	/** The Constant DESCRIPCION_VISA. */
	private static final String DESCRIPCION_VISA = "VISA";
	
	/** The Constant DESCRIPCION_VISA_RECARGABLE. */
	private static final String DESCRIPCION_VISA_RECARGABLE = "VISA Recargable";
	
	/** The Constant DESCRIPCION_MASTER. */
	private static final String DESCRIPCION_MASTER = "MASTER";

	/** The Constant CODIGO_MARCA_AMEX. */
	private static final String CODIGO_MARCA_AMEX = "4";
	
	/** The Constant CODIGO_MARCA_AMEX. */
	private static final String CODIGO_MARCA_DEBITO = "0";

	/** The Constant CODIGO_MARCA_VISA. */
	private static final String CODIGO_MARCA_VISA = "1";

	/** The Constant CODIGO_MARCA_MASTER. */
	private static final String CODIGO_MARCA_MASTER = "3";

	/** The Constant ESTADO_TARJETA_DEDBITO_INVALIDO. */
	private static final String ESTADO_TARJETA_DEDBITO_INVALIDO = "09";

	/** The Constant TIPO_CUENTA_DEBITO. */
	private static final String TIPO_CUENTA_DEBITO = "05";

	/** The Constant CODIGO_COTITULARIDAD. */
	private static final String CODIGO_COTITULARIDAD = "C";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReimpresionTarjetasBOImpl.class);

	/** The Constant ESTADO_TARJETA_ACTIVA. */
	private static final String ESTADO_TARJETA_ACTIVA = "20";

	/** The Constant TIPOCTA_VISA. */
	public static final String TIPOCTA_VISA = "07";

	/** The Constant TIPOCTA_AMEX. */
	public static final String TIPOCTA_AMEX = "42";

	/** The Constant TIPOCTA_MASTER. */
	public static final String TIPOCTA_MASTER = "06";

	/** The Constant TIPO_TARJETA_VISA. */
	public static final String TIPO_TARJETA_VISA = "1";

	/** The Constant TIPO_TARJETA_MASTER. */
	public static final String TIPO_TARJETA_MASTER = "3";

	/** The Constant TIPO_TARJETA_AMEX. */
	public static final String TIPO_TARJETA_AMEX = "3";

	/** The Constant TIPO_TARJETA_AMEX. */
	public static final String TIPO_TARJETA_BANELCO = "4";

	/** The Constant CERO. */
	public static final String CERO = "0";

	/** The Constant CODIGO_TARJETA_DEBITO. */
	public static final String CODIGO_TARJETA_DEBITO = "05";

	/** The Constant CODIGO_RESPUESTA_OK. */
	private static final String CODIGO_RESPUESTA_OK = "00";

	/** The Constant CLAVE_SEGUNDO_LLAMADO. */
	private static final String CLAVE_SEGUNDO_LLAMADO = "S";

	/** The Constant SOLICITUD_EN_CURSO. */
	private static final String SOLICITUD_EN_CURSO = "10000050";

	/** The Constant SOLICITUD_EN_CURSO_TC. */
	private static final String SOLICITUD_EN_CURSO_TC = "15";

	private static final String ID_CONTACTLESS = "09";

	private static final String ID_DESTRUCCION = "06";
	
	private static final String DESCRIPCION_DESTRUCCION = "Destruccion y/o Deterioro";
	
	private static final String DESCRIPCION_CONTACTLESS = "Contactless";
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The reimpresion tarjetas DAO. */
	@Autowired
	private ReimpresionTarjetasDAO reimpresionTarjetasDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.
	 * ReimpresionTarjetasBO#getTarjetasCandidatasReimpresion()
	 */
	@Override
	public Respuesta<List<TarjetaCandidataDTO>> getTarjetasCandidatasReimpresion() {
		Respuesta<List<TarjetaCandidataDTO>> respuesta = new Respuesta<List<TarjetaCandidataDTO>>();

		final Cliente cliente = sesionCliente.getCliente();
		if (cliente.getCuentas().isEmpty()) {
			return respuesta;
		}
		try {
			final List<TarjetaCandidataDTO> tarjetasCandidatas = new ArrayList<TarjetaCandidataDTO>();
			for (Cuenta cuenta : cliente.getCuentas()) {
				if (isTarjetaDebito(cuenta)) {
					tarjetasCandidatas.add(buildTarjetaCandidataDTO(cuenta));
				} else if (isTarjetaCredito(cuenta)) {
					ConsultaDatosTarjetasIn in = new ConsultaDatosTarjetasIn();
					in.setNroCuenta(cuenta.getNroCuentaProducto());
					in.setCliente(cliente);
					in.setTipoCuenta(getTipoCuenta(cuenta));
					in.setMarca(getMarca(cuenta));
					in.setClaveSegundoLlamado("0000000000000000");
					ConsultaDatosTarjetasOut out = reimpresionTarjetasDAO.consultaDatosTarjetas(in);
					tarjetasCandidatas.addAll(buildTarjetasCandidatas(out, cuenta));

					if (CLAVE_SEGUNDO_LLAMADO.equals(out.getHayMasDatos())) {
						do {
							in = new ConsultaDatosTarjetasIn();
							in.setNroCuenta(cuenta.getNroCuentaProducto());
							in.setCliente(cliente);
							in.setTipoCuenta(getTipoCuenta(cuenta));
							in.setMarca(getMarca(cuenta));
							in.setClaveSegundoLlamado(out.getClaveSegundoLlamado());
							out = reimpresionTarjetasDAO.consultaDatosTarjetas(in);
							tarjetasCandidatas.addAll(buildTarjetasCandidatas(out, cuenta));

						} while (CLAVE_SEGUNDO_LLAMADO.equals(out.getHayMasDatos()) && out.getTarjetas() != null);

					}
				} 
				else if (esReimprimibleTarjetaRecargable(cuenta)) {
					ConsultaDatosTarjetasIn in = new ConsultaDatosTarjetasIn();
					in.setNroCuenta(cuenta.getNroCuentaProducto());
					in.setCliente(cliente);
					in.setTipoCuenta(validarTipoCuenta(cuenta));
					in.setMarca(getMarca(cuenta));
					in.setClaveSegundoLlamado("0000000000000000");
					ConsultaDatosTarjetasOut out = reimpresionTarjetasDAO.consultaDatosTarjetas(in);
					tarjetasCandidatas.addAll(buildTarjetasCandidatas(out, cuenta));

				}
			}
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(tarjetasCandidatas);
			sesionParametros.setTarjetasCandidatasReimpresion(tarjetasCandidatas);
		} catch (RobotException e) {
			LOGGER.error("getTarjetasCandidatasReimpresion", e);
			return this.respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SOLICITUDES_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
		}

		return respuesta;
	}

	private boolean esReimprimibleTarjetaRecargable(Cuenta cuenta) {
		if (cuenta.getTipoCuentaEnum().getCodigo().equals(TipoCuenta.VISA.getCodigo()) && (cuenta.getNroOrdenFirmante().equals("001")|| cuenta.getNroOrdenFirmante().equals("002")) && cuenta.getCodigoTitularidad().equals("AD") && cuenta.getSubproductoAltair().equals("PREN")) {
			return true;
		}
		return false;
	}

	public String validarTipoCuenta(Cuenta cuenta){
		String tipoTarjeta ;
		if ( TipoCuenta.VISA.getCodigo().equals(cuenta.getTipoCuentaEnum().getCodigo())) {
			tipoTarjeta = "1"; //VISA
		} else if (TipoCuenta.AMEX.getCodigo().equals(cuenta.getTipoCuentaEnum().getCodigo())) {
			tipoTarjeta ="3"; //AMEX
		} else {
			tipoTarjeta ="4"; //BANELCO
		}
		return tipoTarjeta;
	}
	
	public String validarMarca(Cuenta cuenta){
		if (CODIGO_TARJETA_DEBITO.equals(cuenta.getTipoCuentaEnum().getCodigo())) {
			return CODIGO_MARCA_DEBITO;
		} else if (validarTipoCuenta(cuenta).equals("1")) {
			return CODIGO_MARCA_VISA;
		} else {
			return CODIGO_MARCA_AMEX;
		}
	}
	
	
	/**
	 * Builds the tarjetas candidatas.
	 *
	 * @param out
	 *            the out
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 */
	private List<TarjetaCandidataDTO> buildTarjetasCandidatas(ConsultaDatosTarjetasOut out, Cuenta cuenta) {
		List<TarjetaCandidataDTO> tarjetasCandidatas = new ArrayList<TarjetaCandidataDTO>();
		for (TarjetaDatos tarjetaDatos : out.getTarjetas()) {
			if (ESTADO_TARJETA_ACTIVA.equals(tarjetaDatos.getEstadoTarjeta())) {
				tarjetasCandidatas.add(buildTarjetaCandidataDTO(cuenta, tarjetaDatos));
			}
		}
		return tarjetasCandidatas;
	}

	/**
	 * * Obtiene la marca segun la tarjeta es 1 si es VISA sino es Amex = 4.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the marca
	 */
	private String getMarca(Cuenta cuenta) {
		if (CODIGO_TARJETA_DEBITO.equals(cuenta.getTipoCuenta())) {
			return CODIGO_MARCA_DEBITO;
		} else if (TipoCuenta.VISA.getCodigo().equals(cuenta.getTipoCuentaEnum().getCodigo())) {
			return CODIGO_MARCA_VISA;
		} else if (TipoCuenta.MASTERCARD.getCodigo().equals(cuenta.getTipoCuentaEnum().getCodigo())) {
			return CODIGO_MARCA_MASTER;
		} else {
			return CODIGO_MARCA_AMEX;
		}
	}

	/**
	 * * Obtiene la marca segun la tarjeta es 1 si es VISA sino es Amex = 3.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the tipo cuenta
	 */
	private String getTipoCuenta(Cuenta cuenta) {
		if (cuenta.esTarjetaVisa()) {
			return TIPO_TARJETA_VISA;
		} else if (cuenta.esTarjetaAmex()) {
			return TIPO_TARJETA_AMEX;
		} else if (cuenta.esTarjetaMaster()) {
			return TIPO_TARJETA_MASTER;
		}
		return TIPO_TARJETA_BANELCO;
	}

	/**
	 * * Obtiene la marca segun la tarjeta es 1 si es VISA sino es Amex = 4.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the descripcion
	 */
	private String getDescripcion(Cuenta cuenta) {
		if (esReimprimibleTarjetaRecargable(cuenta)) {
			return DESCRIPCION_VISA_RECARGABLE;
		} else if (cuenta.esTarjetaVisa()) {
			return DESCRIPCION_VISA;
		} else if (cuenta.esTarjetaAmex()) {
			return DESCRIPCION_AMEX;
		} else if (cuenta.esTajetaMaster()) {
			return DESCRIPCION_MASTER;
		}

		return DESCRIPCION_BANELCO;
	}

	/**
	 * Gets the descripcion por tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the descripcion por tipo cuenta
	 */
	private String getDescripcionPorTipoCuenta(String tipoCuenta) {
		if (TIPO_TARJETA_VISA.equals(tipoCuenta)) {
			return DESCRIPCION_VISA;
		} else if (TIPO_TARJETA_AMEX.equals(tipoCuenta)) {
			return DESCRIPCION_AMEX;
		}

		return DESCRIPCION_BANELCO;
	}
	
	private String getDescripcionPorTipoTarjeta(String tipoTarjeta) {
		if (CODIGO_MARCA_VISA.equals(tipoTarjeta)) {
			return DESCRIPCION_VISA;
		} else if (CODIGO_MARCA_AMEX.equals(tipoTarjeta)) {
			return DESCRIPCION_AMEX;
		} else if (CODIGO_MARCA_MASTER.equals(tipoTarjeta)) {
			return DESCRIPCION_MASTER;
		} 

		return DESCRIPCION_BANELCO;
	}

	/**
	 * Checks if is tarjeta debito.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is tarjeta debito
	 */
	private final boolean isTarjetaDebito(Cuenta cuenta) {
		return TIPO_CUENTA_DEBITO.equals(cuenta.getTipoCuenta())
				&& !ESTADO_TARJETA_DEDBITO_INVALIDO.equals(cuenta.getEstadoTarjetaCredito())
				&& (cuenta.esTitular() || CODIGO_COTITULARIDAD.equals(cuenta.getCodigoTitularidad()));
	}

	/**
	 * Checks if is tarjeta credito.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is tarjeta credito
	 */
	private final boolean isTarjetaCredito(Cuenta cuenta) {
		return ((TIPOCTA_MASTER.equals(cuenta.getTipoCuenta()) || TIPOCTA_VISA.equals(cuenta.getTipoCuenta()) || TIPOCTA_AMEX.equals(cuenta.getTipoCuenta()))
				&& !ESTADO_TARJETA_DEDBITO_INVALIDO.equals(cuenta.getEstadoTarjetaCredito())
				&& (cuenta.esTitular() || CODIGO_COTITULARIDAD.equals(cuenta.getCodigoTitularidad()))
				&& ESTADO_TARJETA_ACTIVA.equals(cuenta.getEstadoTarjetaCredito()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.
	 * ReimpresionTarjetasBO#solicitudReimpresionTarjetas(ar.com.santanderrio.
	 * obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetas)
	 */
	@Override
	public Respuesta<ComprobanteAltaReimpresionTarjetas> solicitudReimpresionTarjetas(
			AltaDatosReimpresionTarjetas altaDatosReimpresionTarjetas) {
		List<TarjetaSolicitadaDTO> tarjetasSolicitadas = new ArrayList<TarjetaSolicitadaDTO>();
		if (altaDatosReimpresionTarjetas.getTarjetasSolicitadas().isEmpty()) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		Cliente cliente = sesionCliente.getCliente();
		AltaReimpresionTarjetasIn in = buildAltaReimpresionTarjetasIn(cliente, altaDatosReimpresionTarjetas);
		if (!in.getTarjetasDebito().isEmpty()) {
			for (TarjetaSolicitada tarjetaSolicitada : in.getTarjetasDebito()) {
				try {
					TarjetaOut tarjetaOut = reimpresionTarjetasDAO.altaReimpresionTarjetaDebito(in.getCliente(),
							tarjetaSolicitada);
					tarjetasSolicitadas.add(buildTarjetaSolicitadaDTO(cliente, tarjetaSolicitada, tarjetaOut, false));
				} catch (RobotException e) {
					tarjetasSolicitadas.add(buildTarjetaSolicitadaDTO(cliente, tarjetaSolicitada, null, false));
				}
			}
		}
		if (!in.getTarjetasCredito().isEmpty()) {
			try {

				AltaReimpresionTarjetasOut out = reimpresionTarjetasDAO.altaReimpresionTarjetaCredito(in);
				int contador = 0;
				for (TarjetaOut tarjeta : out.getTarjetasCredito()) {
					if (contador < in.getTarjetasCredito().size()) {
						TarjetaSolicitadaDTO tarjetaDTO = buildTarjetaSolicitadaDTO(cliente,
								in.getTarjetasCredito().get(contador), tarjeta, true);
						tarjetasSolicitadas.add(tarjetaDTO);
					}
					contador++;
				}
			} catch (RobotException e) {
				for (TarjetaSolicitada tarjetaSolicitada : in.getTarjetasCredito()) {
					TarjetaSolicitadaDTO tarjetaDTO = buildTarjetaSolicitadaDTO(cliente, tarjetaSolicitada, null, true);
					tarjetasSolicitadas.add(tarjetaDTO);
				}
			}
		}
		Respuesta<ComprobanteAltaReimpresionTarjetas> respuesta = null;
		ComprobanteAltaReimpresionTarjetas comprobanteAltaReimpresionTarjetas = new ComprobanteAltaReimpresionTarjetas();
		comprobanteAltaReimpresionTarjetas.setTarjetaSolicitadas(tarjetasSolicitadas);
		respuesta = respuestaFactory.crearRespuestaOk(ComprobanteAltaReimpresionTarjetas.class);
		respuesta.setRespuesta(comprobanteAltaReimpresionTarjetas);

		return respuesta;
	}

	/**
	 * Builds the tarjeta solicitada DTO.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tarjetaSolicitada
	 *            the tarjeta solicitada
	 * @param tarjetaOut
	 *            the tarjeta out
	 * @param isCredito
	 *            the is credito
	 * @return tarjetaSolicitada
	 */
	private TarjetaSolicitadaDTO buildTarjetaSolicitadaDTO(Cliente cliente, TarjetaSolicitada tarjetaSolicitada,
			TarjetaOut tarjetaOut, boolean isCredito) {

		TarjetaSolicitadaDTO tarjetaDTO = new TarjetaSolicitadaDTO();
		tarjetaDTO.setSolicitudEnCurso(false);
		if (tarjetaOut == null) {
			tarjetaDTO.setNroTarjetaConFormato(TarjetaBOUtils.formatearNumeroTarjeta(
					StringUtils.leftPad(tarjetaSolicitada.getNroTarjeta(), 20, CERO),
					this.getDescripcionPorTipoTarjeta(tarjetaSolicitada.getCodigoTarjeta())));
			tarjetaDTO.setSolicitudNro(null);
			tarjetaDTO.setIsOk(false);
		} else if (SOLICITUD_EN_CURSO.equals(tarjetaOut.getCodigoTarjeta()) || SOLICITUD_EN_CURSO_TC.equals(
				tarjetaOut.getDescripcionMensaje().substring(tarjetaOut.getDescripcionMensaje().length() - 2))) {
			tarjetaDTO.setNroTarjetaConFormato(TarjetaBOUtils.formatearNumeroTarjeta(
					StringUtils.leftPad(tarjetaSolicitada.getNroTarjeta(), 20, CERO),
					this.getDescripcionPorTipoTarjeta(tarjetaSolicitada.getCodigoTarjeta())));
			tarjetaDTO.setSolicitudNro(null);
			tarjetaDTO.setIsOk(false);
			tarjetaDTO.setSolicitudEnCurso(true);
		} else {
			tarjetaDTO.setNroTarjetaConFormato(TarjetaBOUtils.formatearNumeroTarjeta(
					StringUtils.leftPad(tarjetaOut.getNroTarjeta().trim(), 20, CERO),
					this.getDescripcionPorTipoTarjeta(tarjetaSolicitada.getCodigoTarjeta())));
			tarjetaDTO.setSolicitudNro(cliente.getNup().concat(tarjetaOut.getFechaHoraOperacion()));

			if (isCredito) {
				tarjetaDTO.setIsOk(CODIGO_RESPUESTA_OK.equals(
						tarjetaOut.getDescripcionMensaje().substring(tarjetaOut.getDescripcionMensaje().length() - 2)));
			} else {
				tarjetaDTO.setIsOk(true);
			}
		}
		tarjetaDTO.setTipoCuenta(this.getDescripcionPorTipoTarjeta(tarjetaSolicitada.getCodigoTarjeta()));
		tarjetaDTO.setMotivoReimpresion(tarjetaSolicitada.getMotivoReimpresionDescripcion());
		tarjetaDTO.setTitular(tarjetaSolicitada.getNombreApellido());
		tarjetaDTO.setAlias(tarjetaSolicitada.getAlias());

		return tarjetaDTO;
	}

	/**
	 * Builds the alta reimpresion tarjetas in.
	 *
	 * @param cliente
	 *            the cliente
	 * @param altaDatosReimpresionTarjetas
	 *            the alta datos reimpresion tarjetas
	 * @return the alta reimpresion tarjetas in
	 */
	private AltaReimpresionTarjetasIn buildAltaReimpresionTarjetasIn(Cliente cliente,
			AltaDatosReimpresionTarjetas altaDatosReimpresionTarjetas) {
		AltaReimpresionTarjetasIn in = new AltaReimpresionTarjetasIn();
		List<TarjetaSolicitada> tarjetaDebitoCol = new ArrayList<TarjetaSolicitada>();
		List<TarjetaSolicitada> tarjetaCreditoCol = new ArrayList<TarjetaSolicitada>();
		int contador = 0;
		for (TarjetaSolicitadaDTO tarjetaSolicitadaDTO : altaDatosReimpresionTarjetas.getTarjetasSolicitadas()) {
			Cuenta cuenta = cliente.getTarjetaDesdeNroTarjeta(tarjetaSolicitadaDTO.getNro());
			TarjetaCandidataDTO tarjetaCandidataDTO = obtenerCuentaTarjetaCandidataSesion(tarjetaSolicitadaDTO);
			TarjetaSolicitada tarjetaSolicitada = new TarjetaSolicitada();

			if (cuenta != null) {
				tarjetaSolicitada.setNroTarjeta(ISBANStringUtils.eliminarCeros(cuenta.getNroTarjetaCredito()));
			} else if (cuenta == null && tarjetaCandidataDTO != null) {
				cuenta = tarjetaCandidataDTO.getCuentaRel();
				tarjetaSolicitada.setNroTarjeta(ISBANStringUtils.eliminarCeros(tarjetaCandidataDTO.getNro()));
			}

			if (cuenta != null && cuenta.esTarjetaDeCredito()) {
				tarjetaSolicitada.setCodigoTarjeta(this.getMarca(cuenta));
				if (ID_CONTACTLESS.equals(altaDatosReimpresionTarjetas.getMotivos().get(contador).getId())) {
					tarjetaSolicitada.setMotivoReimpresion(ID_DESTRUCCION);
					tarjetaSolicitada.setMotivoReimpresionDescripcion(DESCRIPCION_CONTACTLESS);
				} else {
				tarjetaSolicitada.setMotivoReimpresion(altaDatosReimpresionTarjetas.getMotivos().get(contador).getId());
				tarjetaSolicitada.setMotivoReimpresionDescripcion(
						altaDatosReimpresionTarjetas.getMotivos().get(contador).getDescripcion());
				}
				tarjetaSolicitada.setNroCuenta(cuenta.getNroCuentaProducto());

				tarjetaSolicitada.setTipoCuenta(this.getTipoCuenta(cuenta));
				tarjetaSolicitada.setNombreApellido(tarjetaSolicitadaDTO.getTitular());
				tarjetaSolicitada
						.setDomicilio(altaDatosReimpresionTarjetas.getDomicilios().get(0).getSecuenciaDomicilio());
				tarjetaSolicitada.setAlias(cuenta.getAlias());
				tarjetaCreditoCol.add(tarjetaSolicitada);
			}
			if (cuenta != null && this.isTarjetaDebito(cuenta)) {
				tarjetaSolicitada.setCodigoTarjeta(this.getMarca(cuenta));
				if (ID_CONTACTLESS.equals(altaDatosReimpresionTarjetas.getMotivos().get(contador).getId())) {
					tarjetaSolicitada.setMotivoReimpresion(ID_DESTRUCCION);
					tarjetaSolicitada.setMotivoReimpresionDescripcion(DESCRIPCION_CONTACTLESS);
				} else {
				tarjetaSolicitada.setMotivoReimpresion(altaDatosReimpresionTarjetas.getMotivos().get(contador).getId());
				tarjetaSolicitada.setMotivoReimpresionDescripcion(
						altaDatosReimpresionTarjetas.getMotivos().get(contador).getDescripcion());
				}
				tarjetaSolicitada.setNroCuenta(cuenta.getContratoAltair());

				tarjetaSolicitada.setTipoCuenta(this.getTipoCuenta(cuenta));
				tarjetaSolicitada.setNombreApellido(tarjetaSolicitadaDTO.getTitular());
				tarjetaSolicitada.setAlias(cuenta.getAlias());
				tarjetaSolicitada
						.setDomicilio(altaDatosReimpresionTarjetas.getDomicilios().get(0).getSecuenciaDomicilio());
				tarjetaDebitoCol.add(tarjetaSolicitada);
			}
			contador++;
		}
		in.setCliente(cliente);
		in.setTarjetasDebito(tarjetaDebitoCol);
		in.setTarjetasCredito(tarjetaCreditoCol);
		return in;
	}

	/**
	 * obtenerCuentaTarjetaCandidataSesion.
	 *
	 * @param tarjetaSolicitadaDTO
	 *            the tarjeta solicitada DTO
	 * @return the tarjeta candidata DTO
	 */
	private TarjetaCandidataDTO obtenerCuentaTarjetaCandidataSesion(TarjetaSolicitadaDTO tarjetaSolicitadaDTO) {
		List<TarjetaCandidataDTO> listaSesion = sesionParametros.getTarjetasCandidatasReimpresion();
		for (TarjetaCandidataDTO tarjetaSesionDTO : listaSesion) {
			if (tarjetaSesionDTO.getNro().equals(tarjetaSolicitadaDTO.getNro())) {
				return tarjetaSesionDTO;
			}
		}
		return null;
	}

	/**
	 * Builds the TarjetaCandidataDTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the tarjeta candidata
	 */
	private TarjetaCandidataDTO buildTarjetaCandidataDTO(Cuenta cuenta) {
		TarjetaCandidataDTO tarjetaCandidataDTO = new TarjetaCandidataDTO();
		tarjetaCandidataDTO.setTitular(
				cuenta.getCliente().getNombre().concat(StringUtils.EMPTY).concat(cuenta.getCliente().getApellido1()));
		tarjetaCandidataDTO.setAlias(cuenta.getAlias());
		tarjetaCandidataDTO.setNro(cuenta.getNroTarjetaCredito());
		tarjetaCandidataDTO.setNroTarjetaConFormato(
				TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), getDescripcion(cuenta)));
		tarjetaCandidataDTO.setTipoDescripcion(this.getDescripcion(cuenta));
		tarjetaCandidataDTO.setCuentaRel(cuenta);
		return tarjetaCandidataDTO;
	}

	/**
	 * Builds the TarjetaCandidataDTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tarjetaDatos
	 *            the tarjetaDatos
	 * @return the tarjeta candidata
	 */
	private TarjetaCandidataDTO buildTarjetaCandidataDTO(Cuenta cuenta, TarjetaDatos tarjetaDatos) {
		TarjetaCandidataDTO tarjetaCandidataDTO = new TarjetaCandidataDTO();
		tarjetaCandidataDTO.setTitular(tarjetaDatos.getApellidoNombreEmbozado());
		tarjetaCandidataDTO.setAlias(cuenta.getAlias());
		tarjetaCandidataDTO.setTipoDescripcion(this.getDescripcion(cuenta));
		tarjetaCandidataDTO.setNro(StringUtils.leftPad(tarjetaDatos.getNroTarjeta(), 20, CERO));
		tarjetaCandidataDTO.setNroTarjetaConFormato(
				TarjetaBOUtils.formatearNumeroTarjeta(tarjetaDatos.getNroTarjeta(), this.getDescripcion(cuenta)));
		tarjetaCandidataDTO.setCuentaRel(cuenta);
		return tarjetaCandidataDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.
	 * ReimpresionTarjetasBO#descargarComprobante(ar.com.santanderrio.obp.
	 * servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante)
	 */
	@Override
	public Respuesta<Reporte> descargarComprobante(DatosReimpresionComprobante datos) {
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		try {
			List<TarjetaSolicitadaView> tarjetasParaComprobante = new ArrayList<TarjetaSolicitadaView>();
			for (TarjetaSolicitadaView tarjetaView : datos.getTarjetas()) {
				if (tarjetaView.getIsOk()) {
					tarjetasParaComprobante.add(tarjetaView);
				}
			}
			datos.setTarjetas(tarjetasParaComprobante);
			Reporte reporte = reimpresionTarjetasDAO.descargarComprobante(datos);

			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaReporte.add(itemMensajeRespuesta);
		}
		return respuestaReporte;
	}

}
