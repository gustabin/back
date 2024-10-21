/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extracto.dao;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentasFirmadasResponse;
import ar.com.santanderrio.obp.generated.webservices.extractos.IExtractos;
import ar.com.santanderrio.obp.generated.webservices.extractos.MovFondosResponse;

/**
 * The Class ExtractoDAOImpl.
 */
@Component
public class ExtractoDAOImpl implements ExtractoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtractoDAOImpl.class);

	/** The Constant JKS. */
	private static final String JKS = "WSEXTRACTOS";

	/** The Constant CANAL. */
	private static final String CANAL = "04";

	/** The Constant CANAL. */
	private static final String CANAL_BPRIV = "79";

	/** The Constant SUBCANAL. */
	private static final String SUBCANAL = "99";

	/** The Constant SUBCANAL. */
	private static final String SUBCANAL_BPRIV = "00";

	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "0";

	/** The Constant ERROR_FIRMA. */
	private static final String ERROR_FIRMA = "Error generando firma: ";

	/** The ws extractos client. */
	@Autowired
	@Qualifier("extractos")
	private GestionarExtractoWSImpl wsExtractosClient;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The sign. */
	@Autowired
	private Sign sign;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO#
	 * consultaMovimientosOly(java.lang.String, java.lang.String,
	 * javax.xml.datatype.XMLGregorianCalendar,
	 * javax.xml.datatype.XMLGregorianCalendar, java.lang.String)
	 */
	@Override
	public MovFondosResponse consultaMovimientosOly(String cuentaTitulo, String codigoFondo,
			XMLGregorianCalendar fechaDesde, XMLGregorianCalendar fechaHasta, String nup) throws DAOException {
		String nupfirmado = generarFirma(nup);
		IExtractos services = null;
		try {
			services = wsExtractosClient.obtenerPort();
			MovFondosResponse respAlias = services.wmCNSMOVFONDOSOLY(cuentaTitulo, codigoFondo, fechaDesde, fechaHasta,
					CANAL, SUBCANAL, nupfirmado);
			LOGGER.info("Respuesta {}:", respAlias.toString());
			return respAlias;
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias para la operacion altaAlias con los datos");
			throw new DAOException(e);
		} finally {
			wsExtractosClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO#
	 * firmasCuentasPorNup(java.lang.String, java.lang.String)
	 */
	@Override
	public CuentasFirmadasResponse firmasCuentasPorNup(String nup, String banca) throws DAOException {
		String nupfirmado = generarFirma(nup);
		IExtractos services = null;
		try {
			services = wsExtractosClient.obtenerPort();
			CuentasFirmadasResponse respCuentasFirmadas = new CuentasFirmadasResponse();
			if ("BP".equals(banca)) {
				respCuentasFirmadas = services.wmFIRMASCUENTASXNUP(nup, CANAL_BPRIV, SUBCANAL_BPRIV, nupfirmado);
			} else {
				respCuentasFirmadas = services.wmFIRMASCUENTASXNUP(nup, CANAL, SUBCANAL, nupfirmado);
			}
			if (!CODIGO_OK.equals(respCuentasFirmadas.getCodigo().getValue())
					|| respCuentasFirmadas.getListaCuentas().getValue().getCuentaTituloFirmada().isEmpty()) {
				LOGGER.error(
						"Codigo de retorno distinto de OK al invocar a WSEXTRACTOS, metodo: firmas_cuentas_por_nup. ");
				throw new DAOException();
			}
			LOGGER.info("Respuesta {}:", respCuentasFirmadas.toString());
			return respCuentasFirmadas;
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar a WSEXTRACTOS, metodo firmas cuentas por nup. ");
			throw new DAOException(e);
		} finally {
			wsExtractosClient.liberarPort(services);
		}
	}

	/**
	 * Generar firma.
	 *
	 * @param datosSinFirmar
	 *            the datos sin firmar
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String generarFirma(String datosSinFirmar) throws DAOException {
		LOGGER.info("Generando Firma");
		String msgException;
		byte[] dataToSign = new byte[(int) datosSinFirmar.length()];
		dataToSign = datosSinFirmar.getBytes();
		try {

			byte[] firma = sign.buildB64Signature(dataToSign, JKS, true);
			return new String(firma);

		} catch (DAOException e) {
			LOGGER.error(ERROR_FIRMA, e);
			msgException = e.getMessage();
		}
		throw new DAOException(msgException);
	}

}
