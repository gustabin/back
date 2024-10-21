/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSConsumosPendientes;

/**
 * The Class ParseadorWSConsumosPendientesImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class ParseadorWSConsumosPendientesImpl extends ParseadorWSVisaImpl implements ParseadorWSConsumosPendientes {

	/** The Constant CERO_FLOTANTE. */
	private static final String CERO_FLOTANTE = "0,00";

	/** The Constant CODIGO_ERROR_CONSUMOS_PENDIENTES. */
	private static final String CODIGO_ERROR_CONSUMOS_PENDIENTES = "112107";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerAutorizacionesDeTarjetaEntity(ar.com
	 * .santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity)
	 */
	@Override
	public List<ConsumoPendienteConfirmacionEntity> obtenerAutorizacionesDeTarjetaEntity(TarjetaEntity tarjetaEntity)
			throws ParseadorVisaException {
		if (tieneAutorizaciones(tarjetaEntity)) {
			return tarjetaEntity.getTarjetaDocument().getAutorizaciones().getConsumoPendienteConfirmacionList();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerConsumoTotalEnPesos(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.
	 * ConsumoPendienteConfirmacionEntity)
	 */
	@Override
	public String obtenerConsumoTotalEnPesos(ConsumoPendienteConfirmacionEntity consumoPendiente)
			throws ParseadorVisaException {
		if (consumoPendiente != null && consumoPendiente.getPesos() != null) {
			return consumoPendiente.getPesos();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerConsumoTotalEnDolares(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.
	 * ConsumoPendienteConfirmacionEntity)
	 */
	@Override
	public String obtenerConsumoTotalEnDolares(ConsumoPendienteConfirmacionEntity consumoPendiente)
			throws ParseadorVisaException {
		if (consumoPendiente != null && consumoPendiente.getPesos() != null) {
			return consumoPendiente.getDolares();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#esConsumoTotalEnPesosCero(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.
	 * ConsumoPendienteConfirmacionEntity)
	 */
	@Override
	public Boolean esConsumoTotalEnPesosCero(ConsumoPendienteConfirmacionEntity consumoPendiente) {
		if (consumoPendiente.getPesos().equals(CERO_FLOTANTE)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#esConsumoTotalEnDolaresCero(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.
	 * ConsumoPendienteConfirmacionEntity)
	 */
	@Override
	public Boolean esConsumoTotalEnDolaresCero(ConsumoPendienteConfirmacionEntity consumoPendiente) {
		if (consumoPendiente.getDolares().equals(CERO_FLOTANTE)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#
	 * obtenerAutorizacionesDeConsumoPendienteConfirmacion(ar.com.santanderrio.
	 * obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity)
	 */
	@Override
	public List<AutorizacionEntity> obtenerAutorizacionesDeConsumoPendienteConfirmacion(
			ConsumoPendienteConfirmacionEntity consumoPendiente) throws ParseadorVisaException {
		if (consumoPendiente != null && consumoPendiente.getAutorizacionList() != null
				&& !consumoPendiente.getAutorizacionList().isEmpty()) {
			return consumoPendiente.getAutorizacionList();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerDescripcion(ar.com.santanderrio.obp.
	 * servicios.tarjetas.dao.entities.AutorizacionEntity)
	 */
	@Override
	public String obtenerDescripcion(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		if (autorizacion != null && autorizacion.getEstablecimiento() != null
				&& autorizacion.getEstablecimiento().getDescripcion() != null) {
			return autorizacion.getEstablecimiento().getDescripcion();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerImporte(ar.com.santanderrio.obp.
	 * servicios.tarjetas.dao.entities.AutorizacionEntity)
	 */
	@Override
	public String obtenerImporte(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		if (tieneValor(autorizacion)) {
			return autorizacion.getImporte().getValor();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerFecha(ar.com.santanderrio.obp.
	 * servicios.tarjetas.dao.entities.AutorizacionEntity)
	 */
	@Override
	public String obtenerFecha(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		if (autorizacion != null && autorizacion.getFecha() != null) {
			return autorizacion.getFecha();
		} else {
			throw new ParseadorVisaException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#noTieneConsumosPendientes(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity)
	 */
	@Override
	public Boolean noTieneConsumosPendientes(RetornoTarjetasEntity respuesta) {
		for (TarjetaEntity tarjeta : respuesta.getTarjetas().getTarjetaList()) {
			if (tarjeta.getError() != null && StringUtils.equals(tarjeta.getError().getCodigo(),
					CodigoMensajeConstantes.CODIGO_ERROR_SIN_ULTIMO_RESUMEN)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#tieneTodasTarjetasConCodigoErrorConsumos(
	 * java.util.List)
	 */
	@Override
	public Boolean tieneTodasTarjetasConCodigoErrorConsumos(List<TarjetaEntity> tarjetas)
			throws ParseadorVisaException {
		for (TarjetaEntity tarjeta : tarjetas) {
			if (StringUtils.equals(CODIGO_ERROR_CONSUMOS_PENDIENTES, tarjeta.getError().getCodigo())) {
				continue;
			} else {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#tienenTodasTarjetasErrorConsumos(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity)
	 */
	@Override
	public Boolean tienenTodasTarjetasErrorConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException {
		List<TarjetaEntity> tarjetas = obtenerTarjetas(entity);
		if (tieneXMLTodasTarjetasConCodigosDeError(tarjetas) && !tieneTodasTarjetasConCodigoErrorConsumos(tarjetas)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#tienenTodasTarjetasErrorSinConsumos(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity)
	 */
	@Override
	public Boolean tienenTodasTarjetasErrorSinConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException {
		List<TarjetaEntity> tarjetas = obtenerTarjetas(entity);
		if (tieneXMLTodasTarjetasConCodigosDeError(tarjetas) && tieneTodasTarjetasConCodigoErrorConsumos(tarjetas)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#tienenErrorConsumos(ar.com.santanderrio.obp
	 * .servicios.tarjetas.dao.entities.RetornoTarjetasEntity)
	 */
	@Override
	public Boolean tienenErrorConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException {
		List<TarjetaEntity> tarjetas = obtenerTarjetas(entity);
		if (!tieneTodasTarjetasConCodigoErrorConsumos(tarjetas)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Tiene valor.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the boolean
	 */
	private Boolean tieneValor(AutorizacionEntity autorizacion) {
		if (autorizacion != null && autorizacion.getMoneda() != null && autorizacion.getImporte() != null
				&& autorizacion.getImporte().getValor() != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Tiene autorizaciones.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the boolean
	 */
	private Boolean tieneAutorizaciones(TarjetaEntity tarjetaEntity) {
		return tieneTarjetaDocYAutorizaciones(tarjetaEntity)
				&& tarjetaEntity.getTarjetaDocument().getAutorizaciones().getConsumoPendienteConfirmacionList() != null
				&& !tarjetaEntity.getTarjetaDocument().getAutorizaciones().getConsumoPendienteConfirmacionList()
						.isEmpty();
	}

	/**
	 * Tiene tarjeta doc Y autorizaciones.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the boolean
	 */
	private Boolean tieneTarjetaDocYAutorizaciones(TarjetaEntity tarjetaEntity) {
		if (tarjetaEntity != null && tarjetaEntity.getTarjetaDocument() != null
				&& tarjetaEntity.getTarjetaDocument().getAutorizaciones() != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerCodigoEstablecimiento(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity)
	 */
	@Override
	public String obtenerCodigoEstablecimiento(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		if (autorizacion == null || autorizacion.getEstablecimiento() == null) {
			throw new ParseadorVisaException();
		} else {
			return autorizacion.getEstablecimiento().getCodigo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#recuperarNumeroTarjetaDesdeCodigoTarjeta(
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.
	 * RetornoTarjetasEntity)
	 */
	@Override
	public String recuperarNumeroTarjetaDesdeCodigoTarjeta(String codigoTarjeta, RetornoTarjetasEntity entity) {
		for (TarjetaEntity tarjeta : entity.getTarjetas().getTarjetaList()) {
			DatosEntity datos;
			try {
                datos = this.obtenerDatos(tarjeta);
            } catch (ParseadorVisaException e) {
                continue;
            }
			String ultimosCuatroNumerosCodigoTarjeta = StringUtils.right(codigoTarjeta, 4);
			String ultimosCuatroNumerosTarjetaActiva = StringUtils.right(datos.getTarjetaActiva(), 4);
			if (StringUtils.equals(ultimosCuatroNumerosCodigoTarjeta.trim(),
					ultimosCuatroNumerosTarjetaActiva.trim())) {
				return datos.getTarjetaActiva();
			}
		}
		return codigoTarjeta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSConsumosPendientes#obtenerNroComprobante(ar.com.santanderrio.
	 * obp.servicios.tarjetas.dao.entities.AutorizacionEntity)
	 */
	@Override
	public String obtenerNroComprobante(AutorizacionEntity autorizacion) throws ParseadorVisaException {
		if (autorizacion == null || autorizacion.getCodigo() == null) {
			throw new ParseadorVisaException();
		}
		return autorizacion.getCodigo();
	}

}