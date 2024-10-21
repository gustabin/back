/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSVisa.
 *
 * @author sabrina.cis
 */
public interface ParseadorWSVisa {

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
	 * Error de tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param codigoError
	 *            the codigo error
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneCodigoError(TarjetaEntity tarjeta, String codigoError) throws ParseadorVisaException;

	/**
	 * Obtener tarjetas.
	 *
	 * @param retornoTarjetas
	 *            the retorno tarjetas
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaEntity> obtenerTarjetas(RetornoTarjetasEntity retornoTarjetas) throws ParseadorVisaException;

	/**
	 * Buscar tarjeta por numero de tarjeta activa.
	 *
	 * @param retorno
	 *            the retorno
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return the tarjeta document entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaDocumentEntity buscarPorNumeroDeTarjetaActiva(RetornoTarjetasEntity retorno, String numeroTarjeta)
			throws ParseadorVisaException;

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
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esCategoriaTitular(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Es categoria adicional.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esCategoriaAdicional(TarjetaEntity tarjetaEntity) throws ParseadorVisaException;

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
	 * Obtener fecha desde.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaDesde(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener codigo error.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCodigoError(TarjetaEntity tarjeta) throws ParseadorVisaException;

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
	 * Obtener por numero de tarjeta activa.
	 *
	 * @param retorno
	 *            the retorno
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerPorNumeroDeTarjetaActiva(RetornoTarjetasEntity retorno, String nroTarjetaCreditoCortado)
			throws ParseadorVisaException;

	/**
	 * Obtener categoria.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCategoria(TarjetaEntity tarjetaEntity) throws ParseadorVisaException;

	/**
	 * Obtener document.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the tarjeta document entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaDocumentEntity obtenerDocument(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener numero tarjeta activa.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNumeroTarjetaActiva(DatosEntity datos) throws ParseadorVisaException;

	/**
	 * Obtener titular.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTitular(DatosEntity datos) throws ParseadorVisaException;

	/**
	 * Obtener fecha desde.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaDesde(DatosEntity datos) throws ParseadorVisaException;

	/**
	 * Verifica si en todos los codigos de errores si alguno de ellos es el
	 * codigo ingresado por parametro. Tag /tarjetas/tarjeta/error/codigo.
	 *
	 * @param retorno
	 *            the retorno
	 * @param codigoError
	 *            the codigo error
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneCodigoError(RetornoTarjetasEntity retorno, String codigoError) throws ParseadorVisaException;

	/**
	 * Obtiene el nombre del habiente. Tag tarjeta/document/datos/habiente.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNombreHabiente(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Verifica si el XML de VISA tiene todas las tarjetas con error.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneXMLTodasTarjetasConCodigosDeError(List<TarjetaEntity> tarjetas) throws ParseadorVisaException;

	/**
	 * Obtener datos tarjetas.
	 *
	 * @param retornoTarjetasEntity
	 *            the retorno tarjetas entity
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerDatosTarjetas(RetornoTarjetasEntity retornoTarjetasEntity) throws ParseadorVisaException;

	/**
	 * Obtener titular tarjeta.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTitularTarjeta(TarjetaEntity datos) throws ParseadorVisaException;
}