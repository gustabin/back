/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorUltimaLiquidacionException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSUltimoResumen.
 *
 * @author florencia.n.martinez
 */
public interface ParseadorWSUltimoResumen {

	/**
	 * Tiene error de credenciales.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneErrorDeCredenciales(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Obtiene la TarjetaEntity por el número de tarjeta activa de útima
	 * liquidacion.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the tarjeta entity
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerTarjetaPorNroTarjetaActivaUltimaLiquidacion(RetornoTarjetasEntity entity, String nroTarjeta)
			throws ParseadorUltimaLiquidacionException, ParseadorVisaException;

	/**
	 * Obtener fecha vencimiento actual.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaVencimientoActual(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener fecha cierre actual.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaCierreActual(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener saldo en pesos.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerSaldoEnPesos(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener saldo en dolares.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerSaldoEnDolares(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener pago minimo.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerPagoMinimo(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener limite financiacion.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerLimiteFinanciacion(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener limite compra.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerLimiteCompra(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener limite compra en cuotas.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerLimiteCompraEnCuotas(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener fecha proximo cierre.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaProximoCierre(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener fecha proximo vencimiento.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaProximoVencimiento(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener fecha cierre anterior.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaCierreAnterior(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener fecha vencimiento anterior.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerFechaVencimientoAnterior(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener tasa nominal anual pesos.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerTasaNominalAnualPesos(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener tasa nominal anual dolares.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerTasaNominalAnualDolares(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener tasa efectiva mensual pesos.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerTasaEfectivaMensualPesos(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * Obtener tasa efectiva mensual dolares.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the string
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 */
	String obtenerTasaEfectivaMensualDolares(TarjetaEntity tarjetaEntity) throws ParseadorUltimaLiquidacionException;

	/**
	 * No tiene ultimo resumen.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the Boolean
	 */
	Boolean noTieneUltimoResumen(RetornoTarjetasEntity respuesta);

	/**
	 * Obtener campos detalle liquidacion.
	 *
	 * @param linea
	 *            the linea
	 * @param trjTipo
	 *            the trj tipo
	 * @return the ultimo resumen fila bean
	 */
	UltimoResumenFilaBean obtenerCamposDetalleLiquidacion(String linea, String trjTipo);

	/**
	 * Es movimiento.
	 *
	 * @param fila
	 *            the fila
	 * @return the Boolean
	 */
	Boolean esMovimiento(UltimoResumenFilaBean fila);

	/**
	 * Obtener fecha linea.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerFechaLinea(UltimoResumenFilaBean fila);

	/**
	 * Formatear fecha date.
	 *
	 * @param fila
	 *            the fila
	 * @return the date
	 */
	Date formatearFechaDate(UltimoResumenFilaBean fila);

	/**
	 * Obtener descripcion.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerDescripcion(UltimoResumenFilaBean fila);

	/**
	 * Tiene totales.
	 *
	 * @param fila
	 *            the fila
	 * @return the Boolean
	 */
	Boolean tieneTotales(UltimoResumenFilaBean fila);

	/**
	 * Obtener numero tarjeta.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerNumeroTarjeta(UltimoResumenFilaBean fila);

	/**
	 * Obtener total pesos.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerTotalPesos(UltimoResumenFilaBean fila);

	/**
	 * Obtener total dolares.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerTotalDolares(UltimoResumenFilaBean fila);

	/**
	 * Obtener importe pesos.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerImportePesos(UltimoResumenFilaBean fila);

	/**
	 * Obtener importe dolares.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerImporteDolares(UltimoResumenFilaBean fila);

	/**
	 * Tiene legales.
	 *
	 * @param fila
	 *            the fila
	 * @return the Boolean
	 */
	Boolean tieneLegales(UltimoResumenFilaBean fila);

	/**
	 * Obtener comprobante.
	 *
	 * @param fila
	 *            the fila
	 * @return the string
	 */
	String obtenerComprobante(UltimoResumenFilaBean fila);

}