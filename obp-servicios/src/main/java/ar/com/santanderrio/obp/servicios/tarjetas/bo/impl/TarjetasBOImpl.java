/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.TarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class TarjetasBOImpl.
 *
 * @author sabrina.cis
 */
public class TarjetasBOImpl implements TarjetasBO {

	/** The Constant CODIGO_TIPO_CUENTA_VISA. */
	private static final Integer CODIGO_TIPO_CUENTA_VISA = 7;

	/** The Constant C_CUOTAS. */
	private static final String C_CUOTAS = "C.";
	
	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The tarjeta dao. */
	@Autowired
	private TarjetaDAO tarjetaDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** Legal BO. */
	@Autowired
	private LegalBO legalBO;

	/**
	 * Obtiene la marca de la tarjeta desde la identificacion de la cuenta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public String obtenerMarcaDeTarjeta(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws BusinessException {
		Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
		return TarjetaUtils.getMarca(cuenta);
	}

	/**
	 * Obtiene la marca de la tarjeta desde la cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	@Override
	public String obtenerMarcaDeTarjeta(Cuenta cuenta) {
		return TarjetaUtils.getMarca(cuenta);
	}

	/**
	 * Es titular cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esTitularCuenta(Cuenta cuenta) {
		return TarjetaBOUtils.esTitularCuenta(cuenta);
	}

	/**
	 * Es adicional cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	@Override
	public Boolean esAdicionalCuenta(Cuenta cuenta) {
		return TarjetaBOUtils.esAdicionalCuenta(cuenta);
	}

	/**
	 * Corta el numero de tarjeta desde la cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	@Override
	public String cortarNroTarjetaDesdeCuenta(Cuenta cuenta) {
		return TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(cuenta.getNroTarjetaCredito(),
				obtenerMarcaDeTarjeta(cuenta));
	}

	/**
	 * Obtener respuesta visa.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param operacion
	 *            the operacion
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public RetornoTarjetasEntity obtenerRespuestaVisa(IdentificacionCuenta identificacionCuenta, Cliente cliente,
			OperacionTarjetaWSEnum operacion) throws DAOException, BusinessException {
		Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
		return tarjetaDao.obtenerTarjetasDeVisaWS(cuenta, operacion, cuenta.getCliente());
	}

	/**
	 * Obtener respuesta visa.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public RetornoTarjetasEntity obtenerRespuestaVisa(Cuenta cuenta, OperacionTarjetaWSEnum operacion)
			throws DAOException {
		return getTarjetaDao().obtenerTarjetasDeVisaWS(cuenta, operacion, cuenta.getCliente());
	}

	/**
	 * Obtiene las cuotas de la descripcion del establecimiento.
	 *
	 * @author florencia.n.martinez
	 * @param descripcion
	 *            the descripcion
	 * @return String
	 */
	public String obtenerCuotas(String descripcion) {
		String[] strArray = StringUtils.split(descripcion);
		for (String str : strArray) {
			if (StringUtils.startsWithIgnoreCase(str, C_CUOTAS)) {
				str = StringUtils.upperCase(str);
				return StringUtils.remove(str, C_CUOTAS);
			}
		}
		return null;
	}

	/**
	 * Verifica si el campo descripcion matchea con la regular expresion de las
	 * cuotas.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the boolean
	 */
	public Boolean tieneRegexpCuotas(String descripcion) {
		String regex = "(.*)[0-9][0-9]/[0-9][0-9]";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(descripcion);
		return m.matches();
	}

	/**
	 * Gets the cuenta BO.
	 *
	 * @return the cuenta BO
	 */
	public CuentaBO getCuentaBO() {
		return cuentaBO;
	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuesta factory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}

	/**
	 * Gets the tarjeta dao.
	 *
	 * @return the tarjetaDao
	 */
	public TarjetaDAO getTarjetaDao() {
		return tarjetaDao;
	}

	/**
	 * Gets the legal BO.
	 *
	 * @return the legalBO
	 */
	public LegalBO getLegalBO() {
		return legalBO;
	}

	/**
	 * Gets the mensaje BO.
	 *
	 * @return the mensajeBO
	 */
	public MensajeBO getMensajeBO() {
		return mensajeBO;
	}

	/**
	 * Muestra cabecera.
	 *
	 * @param ultimosConsumos
	 *            the ultimos consumos
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public Boolean muestraCabecera(List<ConsumoTarjetaDTO> ultimosConsumos, Cuenta cuenta) {
		if (ultimosConsumos.size() > 1
				|| (ultimosConsumos.size() == 1 && !esTarjetaSeleccionada(ultimosConsumos.get(0), cuenta))) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Es tarjeta seleccionada.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esTarjetaSeleccionada(ConsumoTarjetaDTO tarjeta, Cuenta cuenta) {
		if (cuenta.getTipoCuentaEnum().getCodigo().equals(CODIGO_TIPO_CUENTA_VISA)) {
			return esTarjetaSeleccionadaVisa(tarjeta, cuenta);
		} else {
			return esTarjetaSeleccionadaAmex(tarjeta, cuenta);
		}

	}

	/**
	 * Es tarjeta seleccionada visa.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esTarjetaSeleccionadaVisa(ConsumoTarjetaDTO tarjeta, Cuenta cuenta) {
		String numeroTarjetaDTO = StringUtils.right(tarjeta.getNumero(), 4);
		String numeroTarjetaCuenta = StringUtils.right(cuenta.getNroTarjetaCredito(), 4);
		if (StringUtils.equals(numeroTarjetaDTO, numeroTarjetaCuenta)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Es tarjeta seleccionada amex.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esTarjetaSeleccionadaAmex(ConsumoTarjetaDTO tarjeta, Cuenta cuenta) {
		String numeroTarjetaDTO = StringUtils.right(tarjeta.getNumero(), 5);
		String numeroTarjetaCuenta = StringUtils.right(
				TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), TarjetaUtils.getMarca(cuenta)), 5);
		if (StringUtils.equals(numeroTarjetaDTO, numeroTarjetaCuenta)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
    public void invertirSignoConsumoSiEsRecargable(Boolean esRecargable, LineaDetalleConsumoTarjetaDTO lineaDTO) {
		if (esRecargable) {
			if (lineaDTO.getConsumoPesos()) {
				lineaDTO.setImportePesos(lineaDTO.getImportePesos().negate());
			} else {
				lineaDTO.setImporteDolares(lineaDTO.getImporteDolares().negate());
			}
		}
	}

    public Boolean esRecargable(Cuenta cuenta) {
		return TarjetaBOUtils.esTipoCuentaYClaseCuenta(TipoCuenta.VISA.getCodigo(), TipoCuenta.VISA_RECARGABLE.getCodigo(),
				TarjetaUtils.VISA_RECARGABLE, cuenta);
	}


}