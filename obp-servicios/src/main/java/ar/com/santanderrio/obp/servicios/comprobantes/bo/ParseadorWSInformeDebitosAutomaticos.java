/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitosTarjetaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.InformeDebitosAutomaticosEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSInformeDebitosAutomaticos.
 *
 * @author sabrina.cis
 */
public interface ParseadorWSInformeDebitosAutomaticos {

	/**
	 * Es tarjeta sin consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esTarjetaSinInformeDebitosAutomaticos(RetornoTarjetasEntity retorno) throws ParseadorVisaException;

	/**
	 * Obtener informe debitos automaticos.
	 *
	 * @param entity
	 *            the entity
	 * @return the informe debitos automaticos entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	InformeDebitosAutomaticosEntity obtenerInformeDebitosAutomaticos(TarjetaEntity entity)
			throws ParseadorVisaException;

	/**
	 * Obtener lista debitos tarjeta.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<DebitoEntity> obtenerListaDebitosTarjeta(DebitosTarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fecha de debito entity.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaDeDebitoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener importe de debito entity.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the importe entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	ImporteEntity obtenerImporteDeDebitoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener consumo dolares.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumoDolares(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener consumo pesos.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumoPesos(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener establecimiento entity.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the establecimiento entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	EstablecimientoEntity obtenerEstablecimientoEntity(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener cod establecimiento.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCodEstablecimiento(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener descripcion debito.
	 *
	 * @param debitoEntity
	 *            the debito entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerDescripcionDebito(DebitoEntity debitoEntity) throws ParseadorVisaException;

	/**
	 * Obtener nombre tarjeta en debitos.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNombreTarjetaEnDebitos(DebitosTarjetaEntity entity) throws ParseadorVisaException;

}
