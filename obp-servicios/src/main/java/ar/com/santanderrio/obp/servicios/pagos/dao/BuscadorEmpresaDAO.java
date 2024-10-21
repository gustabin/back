/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;
import java.util.Set;

import org.apache.lucene.queryparser.classic.ParseException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Interface MediosPagoDAO.
 *
 * @author B039636
 */
public interface BuscadorEmpresaDAO {

    /**
     * Dado el codigo de una empresa devuelve los datos para el medio de pago.
     *
     * @param empresa
     *            the empresa
     * @return the by codigo
     */
    MedioPago getByCodigo(String empresa);

    /**
     * Retorna una lista de Medios de pago representado el contenido del archivo de
     * MediosdePago.txt
     *
     * @return List<MedioPago>
     * @throws DAOException
     *             the DAO exception
     */
    List<MedioPago> findAllMediosDePago() throws DAOException;

    /**
     * Realiza una busqueda en los nodos indexados previamente.
     *
     * @param term
     *            the term
     * @return the sets the
     */
    Set<MedioPago> search(String term);

    /**
     * Realiza una busqueda en los nodos indexados previamente. (PesPA_habilitado =
     * S O Addi_habilitado = S)
     *
     * @param term
     *            the term
     * @return the sets the
     */
    Set<MedioPago> searchPagoAutomatico(String term);

    /**
     * Realiza una busqueda en los nodos indexados previamente por codigo.
     *
     * @param term
     *            the term
     * @return the sets the
     */
    Set<MedioPago> searchByCode(String term);

    /**
     * Lee el archivo de medios de pagos.
     */
    void init();

    /**
     * Gets the by cuit servicio.
     *
     * @param cuit
     *            the cuit
     * @param servicio
     *            the servicio
     * @return the by cuit servicio
     * @throws DAOException
     *             the DAO exception
     */
    MedioPago getByCuitServicio(String cuit, String servicio) throws DAOException;

    /**
     * Realiza una busqueda solo entre las empresas de recarga de celulares
     * (rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the sets the
     * @throws DAOException
     *             the DAO exception
     */
    Set<MedioPago> searchRecargaCelulares() throws DAOException;

    /**
     * Realiza una busqueda solo entre las empresas que acepten adhesion a debito
     * automatico en tarjetas de credito (Pes_habilitado = S y Addi_habilitado = N).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the sets the
     * @throws DAOException
     *             the DAO exception
     */
    Set<MedioPago> searchEmpresaDebitoAutomaticoEnTarjeta(String terminoBusqueda) throws DAOException;

    /**
     * Search empresa by nombre fantasia.
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the sets the
     */
    Set<MedioPago> searchEmpresaByNombreFantasia(String terminoBusqueda);

    /**
     * Search by visa cod establecimiento.
     *
     * @param codEst
     *            the cod est
     * @return the sets the
     */
    Set<MedioPago> searchByVisaCodEstablecimiento(String codEst);

    /**
     * Search empresa by nombre fantasia exacto.
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the sets the
     */
    Set<MedioPago> porNombreFantasiaTarjeta(String terminoBusqueda);

    /**
     * Buscar medio pago por cuit y servicio.
     *
     * @param cuit
     *            the cuit posision 1
     * @param servicio
     *            the servicio posicion 4
     * @return the medio pago
     * @throws ParseException
     *             the parse exception
     */
    Set<MedioPago> buscarMedioPagoPorCuitServicio(String cuit, String servicio) throws ParseException;

    /**
     * Search empresa nuevo pago.
     *
     * @param term
     *            the term
     * @return the sets the
     */
    Set<MedioPago> searchEmpresaNuevoPago(String term);

    /**
     * Search empresa by id acuerdo compras.
     *
     * @param idAcuerdo
     *            the id acuerdo
     * @return the sets the
     */
    Set<MedioPago> searchEmpresaByIdAcuerdoCompras(String idAcuerdo);

    Set<MedioPago> porNombreFantasiaPagoAutomatico(String terminoBusqueda);
    
    /**
     * Este metodo solo se debe llamar al levantar el contexto, modificar el archivo
     * de medios de pagos o por demanda de limpieza.
     * 
     * @throws DAOException
     */
    void loadMediosPago() throws DAOException;
    
    /**
     * Realiza una busqueda solo entre las empresas de recarga transporte
     * (rubroFantasia = S).
     *
     * @return the sets the
     * @throws DAOException
     *             the DAO exception
     */
    Set<MedioPago> searchRecargaTransporte() throws DAOException;

}
