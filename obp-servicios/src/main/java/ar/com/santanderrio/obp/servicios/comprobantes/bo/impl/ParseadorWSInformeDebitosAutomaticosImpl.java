/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comprobantes.bo.ParseadorWSInformeDebitosAutomaticos;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitosTarjetaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.InformeDebitosAutomaticosEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSUltimoConsumoImpl;

/**
 * The Class ParseadorWSInformeDebitosAutomaticosImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ParseadorWSInformeDebitosAutomaticosImpl implements ParseadorWSInformeDebitosAutomaticos {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParseadorWSUltimoConsumoImpl.class);

	/** The Constant CODIGO_ERROR_CONSUMOS. */
	private static final String CODIGO_ERROR_INFORMES_DEBITOS_AUTOMATICOS = "112501";

	/**
	 * Analiza si todas las tarjetas tienen el codigo error 112501.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean esTarjetaSinInformeDebitosAutomaticos(RetornoTarjetasEntity retorno) throws ParseadorVisaException {
		if (CODIGO_ERROR_INFORMES_DEBITOS_AUTOMATICOS != null) {
			boolean sinComprobantes = true;
			List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
			for (TarjetaEntity tarjeta : tarjetasEntity) {
				if (!tarjetaTieneError(tarjeta, CODIGO_ERROR_INFORMES_DEBITOS_AUTOMATICOS)) {
					sinComprobantes = false;
					break;
				}
			}
			return sinComprobantes;
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtener titular.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerTitular(DatosEntity datos) throws ParseadorVisaException {
		if (datos == null) {
			throw new ParseadorVisaException();
		}
		if (datos.getTitular() == null) {
			throw new ParseadorVisaException();
		}
		return datos.getTitular();
	}

	/**
	 * Obtiene los informe Debitos Automaticos de la tarjeta ingresada por
	 * parametro. Tag /tarjetas/tarjeta/document/informeDebitosAutomaticos/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public InformeDebitosAutomaticosEntity obtenerInformeDebitosAutomaticos(TarjetaEntity entity)
			throws ParseadorVisaException {
		TarjetaDocumentEntity document = obtenerDocument(entity);
		if (document.getInformeDebitosAutomaticos() == null) {
			throw new ParseadorVisaException();
		}
		return document.getInformeDebitosAutomaticos();
	}

	/**
	 * Obtiene TarjetaDocumentEntity. Tag /tarjetas/tarjeta/document/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the tarjeta document entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public TarjetaDocumentEntity obtenerDocument(TarjetaEntity tarjeta) throws ParseadorVisaException {
		if (!tarjetaTieneError(tarjeta) && tarjeta.getTarjetaDocument() == null) {
			throw new ParseadorVisaException();
		}
		return tarjeta.getTarjetaDocument();
	}

	/**
	 * Verifica que el tag /tarjetas/error/codigo/ sea el mismo que el ingresado
	 * por parametro.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param codigo
	 *            the codigo
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public boolean tarjetaTieneError(TarjetaEntity tarjeta, String codigo) throws ParseadorVisaException {
		String error = obtenerCodigoError(tarjeta);
		return obtenerCodigoError(tarjeta) != null && error.equals(codigo);
	}

	/**
	 * Verifica si tiene datos del tag /tarjetas/error/codigo/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public boolean tarjetaTieneError(TarjetaEntity tarjeta) throws ParseadorVisaException {
		return obtenerCodigoError(tarjeta) != null;
	}

	/**
	 * Devuelve si la tarjeta tiene tarjeta activa. Obtiene los datos del tag
	 * Tag /tarjetas/tarjeta/error/codigo/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parceador visa exception
	 */
	public String obtenerCodigoError(TarjetaEntity tarjeta) throws ParseadorVisaException {
		if (tarjeta == null) {
			throw new ParseadorVisaException();
		}
		if (tarjeta.getError() != null && tarjeta.getError().getCodigo() == null) {
			throw new ParseadorVisaException();
		}
		if (tarjeta.getError() != null) {
			return tarjeta.getError().getCodigo();
		}
		return null;
	}

	/**
	 * Busca la tarjeta a consultar por numero de tarjeta activa. Retorna los
	 * datos del tag /tarjetas/tarjeta/
	 *
	 * @param retorno
	 *            the retorno
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @return the tarjeta document entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public TarjetaEntity obtenerPorNumeroDeTarjetaActiva(RetornoTarjetasEntity retorno, String nroTarjetaCreditoCortado)
			throws ParseadorVisaException {
		List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
		for (TarjetaEntity tarjeta : tarjetasEntity) {
			if (!tarjetaTieneError(tarjeta)) {
				String tarjetaActiva = obtenerNumeroTarjetaActiva(tarjeta);
				if (StringUtils.equals(nroTarjetaCreditoCortado, tarjetaActiva)) {
					return tarjeta;
				}
			}
		}
		return null;
	}

	/**
	 * Devuelve si la tarjeta tiene tarjeta activa. Devuelve los datos del tag
	 * /tarjetas/tarjeta/document/datos/tarjetaActiva/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parceador visa exception
	 */
	public String obtenerNumeroTarjetaActiva(TarjetaEntity tarjeta) throws ParseadorVisaException {
		if (tarjeta != null) {
			DatosEntity datos = obtenerDatos(tarjeta);
			if (datos.getTarjetaActiva() == null) {
				throw new ParseadorVisaException();
			}
			return datos.getTarjetaActiva();
		}
		return null;
	}

	/**
	 * Obtiene los datos del tag /tarjetas/tarjeta/document/datos/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parceador visa exception
	 */
	public DatosEntity obtenerDatos(TarjetaEntity tarjeta) throws ParseadorVisaException {
		if (!tarjetaTieneError(tarjeta) && tarjeta.getTarjetaDocument() == null
				|| tarjeta.getTarjetaDocument().getDatos() == null) {
			throw new ParseadorVisaException();
		}
		return tarjeta.getTarjetaDocument().getDatos();
	}

	/**
	 * Obtiene la lista de tarjetas del tag tag /tarjetas/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public List<TarjetaEntity> obtenerTarjetas(RetornoTarjetasEntity entity) throws ParseadorVisaException {
		if (!tieneErrorDeCredenciales(entity) && entity.getTarjetas() == null
				|| (entity.getTarjetas() != null && entity.getTarjetas().getTarjetaList() == null)) {
			throw new ParseadorVisaException();
		}
		return entity.getTarjetas().getTarjetaList();
	}

	/**
	 * Tiene error de credenciales.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public boolean tieneErrorDeCredenciales(RetornoTarjetasEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getError() == null && entity.getTarjetas() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getErrorTarjetas() == null && entity.getTarjetas() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getError() != null && entity.getError();
	}

	/**
	 * Obtiene la lista de debitos automaticos de la tarjeta ingresada por
	 * parametro. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<DebitoEntity> obtenerListaDebitosTarjeta(DebitosTarjetaEntity tarjeta) throws ParseadorVisaException {
		if (tarjeta == null) {
			throw new ParseadorVisaException();
		}
		if (tarjeta.getListDebitos() == null) {
			throw new ParseadorVisaException();
		}
		return tarjeta.getListDebitos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ParseadorWSInformeDebitosAutomaticos#obtenerNombreTarjetaEnDebitos(ar.com
	 * .santanderrio.obp.servicios.comprobantes.entities.
	 * InformeDebitosAutomaticosEntity)
	 */
	@Override
	public String obtenerNombreTarjetaEnDebitos(DebitosTarjetaEntity entity) throws ParseadorVisaException {
		String nombreTarjeta = entity.getNombreTarjeta();
		if (nombreTarjeta == null || StringUtils.isBlank(nombreTarjeta)) {
			throw new ParseadorVisaException();
		}
		return nombreTarjeta;
	}

	/**
	 * Obtiene la fecha de debitoEntity ingresada. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/fecha/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaDeDebitoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException {
		if (debitoEntity == null) {
			throw new ParseadorVisaException();
		}
		if (debitoEntity.getFecha() == null) {
			throw new ParseadorVisaException();
		}
		return debitoEntity.getFecha().replace("-", "/").replace("/", "/");
	}

	/**
	 * Obtiene la fecha de debitoEntity ingresada. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/importe/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public ImporteEntity obtenerImporteDeDebitoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException {
		if (debitoEntity == null) {
			throw new ParseadorVisaException();
		}
		if (debitoEntity.getImporte() == null) {
			throw new ParseadorVisaException();
		}
		return debitoEntity.getImporte();
	}

	/**
	 * Gets the consumo dolares. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/importe/dolares/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumoDolares(DebitoEntity debitoEntity) throws ParseadorVisaException {
		ImporteEntity importe = obtenerImporteDeDebitoEntity(debitoEntity);
		String entity = importe.getDolares();
		if (entity == null || StringUtils.isEmpty(entity)) {
			throw new ParseadorVisaException();
		}
		try {
			return TarjetaBOUtils.convertirSaldo(entity);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error("Error en Gets the consumo dolares.", e);
			throw new ParseadorVisaException();
		}
	}

	/**
	 * Obtener pesos. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/importe/pesos/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumoPesos(DebitoEntity debitoEntity) throws ParseadorVisaException {
		ImporteEntity importe = obtenerImporteDeDebitoEntity(debitoEntity);
		String entity = importe.getPesos();
		if (entity == null || StringUtils.isEmpty(entity)) {
			throw new ParseadorVisaException();
		}
		try {
			return TarjetaBOUtils.convertirSaldo(entity);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error("Error en Gets the consumo dolares.", e);
			throw new ParseadorVisaException();
		}
	}

	/**
	 * Obtiene la fecha de debitoEntity ingresada. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/establecimiento/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public EstablecimientoEntity obtenerEstablecimientoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException {
		if (debitoEntity == null) {
			throw new ParseadorVisaException();
		}
		if (debitoEntity.getEstablecimiento() == null) {
			throw new ParseadorVisaException();
		}
		return debitoEntity.getEstablecimiento();
	}

	/**
	 * Obtener pesos. Tag
	 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/establecimiento/codEstablecimiento/.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerCodEstablecimiento(DebitoEntity debitoEntity) throws ParseadorVisaException {
		EstablecimientoEntity establecimientoEntity = obtenerEstablecimientoEntity(debitoEntity);
		String entity = establecimientoEntity.getCodEstablecimiento();
		if (entity == null || StringUtils.isEmpty(entity)) {
			throw new ParseadorVisaException();
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ParseadorWSInformeDebitosAutomaticos#obtenerDescripcionDebito(ar.com.
	 * santanderrio.obp.servicios.comprobantes.entities.DebitoEntity)
	 */
	@Override
	public String obtenerDescripcionDebito(DebitoEntity debitoEntity) throws ParseadorVisaException {
		String descripcion = debitoEntity.getDescripcion();
		if (descripcion == null || StringUtils.isEmpty(descripcion)) {
			throw new ParseadorVisaException();
		}
		return descripcion;
	}

}
