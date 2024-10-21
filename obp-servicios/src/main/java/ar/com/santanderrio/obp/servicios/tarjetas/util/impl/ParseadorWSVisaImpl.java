/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSVisa;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ParseadorWSVisaImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ParseadorWSVisaImpl implements ParseadorWSVisa {

    /** The Constant CUATRO_ENTERO. */
    private static final int CUATRO_ENTERO = 4;

    /**
     * Error de Credenciales. Verifica los datos del tag /error/
     *
     * @param entity
     *            the entity
     * @return true, if successful
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean tieneErrorDeCredenciales(RetornoTarjetasEntity entity) throws ParseadorVisaException {
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
     * Obtiene la lista de tarjetas del tag tag /tarjetas/. Valida que no tenga
     * error de credenciales.
     *
     * @param entity
     *            the entity
     * @return the list
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public List<TarjetaEntity> obtenerTarjetas(RetornoTarjetasEntity entity) throws ParseadorVisaException {
        if (tieneErrorDeCredenciales(entity)) {
            throw new ParseadorVisaException();
        }
        if (entity.getTarjetas() == null
                || (entity.getTarjetas() != null && entity.getTarjetas().getTarjetaList() == null)) {
            throw new ParseadorVisaException();
        }
        return entity.getTarjetas().getTarjetaList();
    }

    /**
     * Devuelve si la tarjeta tiene tarjeta activa. Obtiene los datos del tag Tag
     * /tarjetas/tarjeta/error/codigo/.
     *
     * @param tarjeta
     *            the tarjeta
     * @return true, if successful
     * @throws ParseadorVisaException
     *             the parceador visa exception
     */
    @Override
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
     * Verifica si la tarjeta tiene el codigo de error que ingresa por parametro.
     * Tag /tarjetas/tarjeta/error/codigo.
     *
     * @param tarjeta
     *            the tarjeta
     * @param codigoError
     *            the codigo error
     * @return true, if successful
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean tieneCodigoError(TarjetaEntity tarjeta, String codigoError) throws ParseadorVisaException {
        String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
        return codigoError != null && codigoErrorTarjeta != null && codigoErrorTarjeta.equals(codigoError);
    }

    /**
     * Verifica si todos los codigos de errores es el codigo ingresado por
     * parametro. Tag /tarjetas/tarjeta/error/codigo.
     *
     * @param retorno
     *            the retorno
     * @param codigoError
     *            the codigo error
     * @return true, if successful
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean tieneCodigoError(RetornoTarjetasEntity retorno, String codigoError) throws ParseadorVisaException {
        if (codigoError != null) {
            Boolean error = true;
            List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
            for (TarjetaEntity tarjeta : tarjetasEntity) {
                String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
                if (codigoErrorTarjeta != null && !codigoErrorTarjeta.equals(codigoError)) {
                    error = false;
                    break;
                }
            }
            return error;
        }
        throw new ParseadorVisaException();
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
    public Boolean tarjetaTieneError(TarjetaEntity tarjeta) throws ParseadorVisaException {
        return obtenerCodigoError(tarjeta) != null;
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
    @Override
    public TarjetaDocumentEntity obtenerDocument(TarjetaEntity tarjeta) throws ParseadorVisaException {
        if (!tarjetaTieneError(tarjeta) && tarjeta.getTarjetaDocument() == null) {
            throw new ParseadorVisaException();
        }
        return tarjeta.getTarjetaDocument();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSVisa#obtenerTitularTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity)
     */
    @Override
    public String obtenerTitularTarjeta(TarjetaEntity datos) throws ParseadorVisaException {
        if (datos == null) {
            throw new ParseadorVisaException();
        }
        if (datos.getTarjetaDocument().getDatos().getTitular() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getTarjetaDocument().getDatos().getTitular();
    }

    /**
	 * Busca la tarjeta a consultar por numero de tarjeta activa. Retorna los
	 * datos del tag /tarjetas/tarjeta/document/
	 *
	 * @param retornoTarjetasEntity
	 *            the retorno tarjetas entity
	 * @return the tarjeta document entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
    @Override
    public TarjetaEntity obtenerDatosTarjetas(RetornoTarjetasEntity retornoTarjetasEntity)
            throws ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retornoTarjetasEntity);
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (!tarjetaTieneError(tarjeta)) {
                if (retornoTarjetasEntity.getTarjetas() == null
                        || retornoTarjetasEntity.getTarjetas().getTarjetaList() == null) {
                    throw new ParseadorVisaException();
                }
                return tarjeta;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSVisa#buscarPorNumeroDeTarjetaActiva(ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity, java.lang.String)
     */
    @Override
    public TarjetaDocumentEntity buscarPorNumeroDeTarjetaActiva(RetornoTarjetasEntity retorno,
            String nroTarjetaCreditoCortado) throws ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (!tarjetaTieneError(tarjeta)) {
                String tarjetaActiva = obtenerNumeroTarjetaActiva(tarjeta);
                if (StringUtils.equals(nroTarjetaCreditoCortado, tarjetaActiva)) {
                    return tarjeta.getTarjetaDocument();
                }
            }
        }
        return null;
    }

    /**
     * Busca la tarjeta a consultar por numero de tarjeta activa. Retorna los datos
     * del tag /tarjetas/tarjeta/
     *
     * @param retorno
     *            the retorno
     * @param nroTarjetaCreditoCortado
     *            the nro tarjeta credito cortado
     * @return the tarjeta document entity
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
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
     * Devuelve si la tarjeta tiene tarjeta activa. Obtiene los datos del tag
     * /tarjetas/tarjeta/document/datos/tarjetaActiva/
     *
     * @param tarjetaDocument
     *            the tarjeta document
     * @return true, if successful
     * @throws ParseadorVisaException
     *             the parceador visa exception
     */
    @Override
    public String obtenerTarjetaActiva(TarjetaDocumentEntity tarjetaDocument) throws ParseadorVisaException {
        if (tarjetaDocument == null
                || tarjetaDocument.getDatos() == null && tarjetaDocument.getDatos().getTarjetaActiva() == null) {
            throw new ParseadorVisaException();
        }
        return tarjetaDocument.getDatos().getTarjetaActiva();
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
        if (tarjetaTieneError(tarjeta)
                || (tarjeta.getTarjetaDocument() == null || tarjeta.getTarjetaDocument().getDatos() == null)) {
            throw new ParseadorVisaException();
        }
        return tarjeta.getTarjetaDocument().getDatos();
    }

    /**
     * Tiene categoria titular.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the Boolean
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean esCategoriaTitular(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
        String categoria = obtenerCategoria(tarjetaEntity);
        if (StringUtils.equals(categoria, TarjetaUtils.CATEGORIA_TITULAR)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica si la TarjetaEntity es adicional consultando si la categoría de la
     * misma es igual a 1.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the Boolean
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean esCategoriaAdicional(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
        String categoria = obtenerCategoria(tarjetaEntity);
        if (StringUtils.equals(categoria, TarjetaUtils.CATEGORIA_ADICIONAL)) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene la categoria de la tarjeta.
     *
     * @param tarjetaEntity
     *            the tarjeta entity
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerCategoria(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
        DatosEntity datos = obtenerDatos(tarjetaEntity);
        if (datos.getCategoria() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getCategoria();
    }

    /**
     * Retorna los datos de la tarjeta, del tag
     * /tarjetas/tarjeta/document/datos/tarjetaActiva/, a partir de un numero de
     * tarjeta de credito.
     *
     * @param retorno
     *            the retorno
     * @param nroTarjetaCreditoCortado
     *            the nro tarjeta credito cortado
     * @return the tarjeta entity
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public TarjetaEntity obtenerTarjetaPorNroTarjetaActiva(RetornoTarjetasEntity retorno,
            String nroTarjetaCreditoCortado) throws ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (!tarjetaTieneError(tarjeta)) {
                String nroTarjetaActiva = obtenerNumeroTarjetaActiva(tarjeta);
                if (nroTarjetaCreditoCortado != null && nroTarjetaActiva != null
                        && StringUtils.equals(nroTarjetaCreditoCortado, nroTarjetaActiva)) {
                    return tarjeta;
                }
            }
        }
        return null;
    }

    /**
     * Retorna los datos de la tarjeta, del tag /tarjetas/tarjeta/, a partir de los
     * ultimos cuatro numeros del codigo de tarjeta de credito.
     *
     * @param retorno
     *            the retorno
     * @param ultimosCuatroNros
     *            the ultimos cuatro nros
     * @return the tarjeta entity
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public TarjetaEntity obtenerTarjetaPorUltimosCuatroNros(RetornoTarjetasEntity retorno, String ultimosCuatroNros)
            throws ParseadorVisaException {
        List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            if (!tarjetaTieneError(tarjeta)) {
                String nroTarjetaActiva = obtenerNumeroTarjetaActiva(tarjeta);
                String cuatroNumeros = obtenerUltimosCuatroNumeros(nroTarjetaActiva);
                if (ultimosCuatroNros != null && cuatroNumeros != null
                        && StringUtils.equals(ultimosCuatroNros, cuatroNumeros)) {
                    return tarjeta;
                }
            }
        }
        return null;
    }

    /**
     * Obtener cuatro digitos de la derecha.
     *
     * @param numero
     *            the numero
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    public String obtenerUltimosCuatroNumeros(String numero) throws ParseadorVisaException {
        if (numero == null) {
            throw new ParseadorVisaException();
        }
        return StringUtils.right(numero, CUATRO_ENTERO);
    }

    /**
     * Obtiene el nombre de la persona del tag
     * /tarjetas/tarjeta/document/datos/titular/.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerNombre(TarjetaEntity tarjeta) throws ParseadorVisaException {
        DatosEntity datos = obtenerDatos(tarjeta);
        if (datos.getTitular() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getTitular();
    }

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
    @Override
    public String obtenerTarjetaActiva(TarjetaEntity tarjeta) throws ParseadorVisaException {
        DatosEntity datos = obtenerDatos(tarjeta);
        if (datos.getTitular() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getTarjetaActiva();
    }

    /**
     * Obtiene el nombre de la persona del tag
     * /tarjetas/tarjeta/document/datos/fechaDesde/.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerFechaDesde(TarjetaEntity tarjeta) throws ParseadorVisaException {
        DatosEntity datos = obtenerDatos(tarjeta);
        if (datos.getFechaDesde() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getFechaDesde();
    }

    /**
     * Devuelve si la tarjeta tiene tarjeta activa. Devuelve los datos del tag
     * /tarjetas/tarjeta/document/datos/tarjetaActiva/
     *
     * @param datos
     *            the datos
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerNumeroTarjetaActiva(DatosEntity datos) throws ParseadorVisaException {
        if (datos == null) {
            throw new ParseadorVisaException();
        }
        if (datos.getTarjetaActiva() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getTarjetaActiva();
    }

    /**
     * Devuelve si la tarjeta tiene tarjeta activa. Devuelve los datos del tag
     * /tarjetas/tarjeta/document/datos/titular
     *
     * @param datos
     *            the datos
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
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
     * Devuelve si la tarjeta tiene tarjeta activa. Devuelve los datos del tag
     * /tarjetas/tarjeta/document/datos/fechaDesde
     *
     * @param datos
     *            the datos
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerFechaDesde(DatosEntity datos) throws ParseadorVisaException {
        if (datos == null) {
            throw new ParseadorVisaException();
        }
        if (datos.getFechaDesde() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getFechaDesde();
    }

    /**
     * Obtiene el nombre del habiente. Tag tarjeta/document/datos/habiente.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the string
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public String obtenerNombreHabiente(TarjetaEntity tarjeta) throws ParseadorVisaException {
        DatosEntity datos = obtenerDatos(tarjeta);
        if (datos.getHabiente() == null) {
            throw new ParseadorVisaException();
        }
        return datos.getHabiente();
    }

    /**
     * Verifica si el XML de VISA tiene todas las tarjetas con error.
     *
     * @param tarjetas
     *            the tarjetas
     * @return the Boolean
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Override
    public Boolean tieneXMLTodasTarjetasConCodigosDeError(List<TarjetaEntity> tarjetas) throws ParseadorVisaException {
        for (TarjetaEntity tarjetaEntity : tarjetas) {
            if (tarjetaEntity.getError() != null) {
                continue;
            } else {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
