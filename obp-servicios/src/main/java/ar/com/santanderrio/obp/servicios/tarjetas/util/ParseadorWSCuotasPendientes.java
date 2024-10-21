/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * Obtencion de datos desde la respuesta del DAO para Cuotas Pendientes.
 *
 * @author federico.n.flores
 */
public interface ParseadorWSCuotasPendientes {

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
	 * Obtener tarjeta por nro tarjeta activa.
	 *
	 * @param retorno
	 *            the retorno
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerTarjetaPorNroTarjetaActiva(RetornoTarjetasEntity retorno, String nroTarjetaCreditoCortado)
			throws ParseadorVisaException;

	/**
	 * Obtener tarjetas.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaEntity> obtenerTarjetas(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Tarjeta notiene consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tarjetaNotieneConsumos(RetornoTarjetasEntity retorno) throws ParseadorVisaException;

	/**
	 * Obtener tarjeta activa.
	 *
	 * @param tarjetaDocument
	 *            the tarjeta document
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTarjetaActiva(TarjetaDocumentEntity tarjetaDocument) throws ParseadorVisaException;

	/**
	 * Es categoria titular.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esCategoriaTitular(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener cuotas pendientes.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the cuotas pendientes entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	CuotasPendientesEntity obtenerCuotasPendientes(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener lista cuotas pendientes.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaCuotasPendientesEntity> obtenerListaCuotasPendientes(TarjetaEntity tarjeta)
			throws ParseadorVisaException;

	/**
	 * Obtiene el subtotal de la tarjeta correspondiente al codigo recibido.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param codigo
	 *            the codigo
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	BigDecimal obtenerSubtotalCuotasPendientes(TarjetaEntity tarjeta, String codigo)
			throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtiene el total de la tarjeta seleccionada del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/codigoTarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCodigoTarjeta(TarjetaCuotasPendientesEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Retorna la lista de los ultimos cuatro numeros del codigo de la tarjeta,
	 * con cuotas pendientes. El codigo viene encriptado con X, ej. XXXX XXXX
	 * XXXX3775, se obtiene del atributo codigoTarjeta del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<String> obtenerCodigosDeTarjetas(TarjetaEntity tarjetaEntity) throws ParseadorVisaException;

	/**
	 * Obtener tarjeta por ultimos cuatro nros.
	 *
	 * @param retorno
	 *            the retorno
	 * @param ultimosCuatroNros
	 *            the ultimos cuatro nros
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerTarjetaPorUltimosCuatroNros(RetornoTarjetasEntity retorno, String ultimosCuatroNros)
			throws ParseadorVisaException;

	/**
	 * Obtener nombre.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNombre(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtiene el comprobante del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/comprobante/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNumeroComprobante(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException;

	/**
	 * Obtiene la cantidad de cuotas del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/cuotas/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the integer
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	Integer obtenerCuotas(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtiene la cuotas pendientes del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/
	 * cuotasPendientes/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the integer
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	Integer obtenerCuotasPendientes(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtiene el establecimiento del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/establecimiento
	 * /.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerEstablecimiento(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException;

	/**
	 * Obtiene la fecha del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/fecha/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the date
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	Date obtenerFecha(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtiene el importe del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/importe/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the BigDecimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	BigDecimal obtenerImporte(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtiene la moneda del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/moneda/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerMoneda(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException;

	/**
	 * Obtiene el nombre de la persona del tag
	 * /tarjetas/tarjeta/document/datos/tarjetaActiva/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTarjetaActiva(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Es categoria adicional.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esCategoriaAdicional(TarjetaEntity tarjetaEntity) throws ParseadorVisaException;

	/**
	 * Obtiene total de cuotas pendientes.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	BigDecimal obtenerTotalCuotasPendientes(TarjetaEntity tarjeta)
			throws ParseadorVisaException, TarjetaBOUtilsException;
}
