/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.NoSePuedeRealizarLaOperacionException;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Interface DatosSolicitanteDAO.
 */
public interface DatosSolicitanteDAO {

    /**
     * Gets the datos del solicitante.
     *
     * @param datosSolicitante
     *            the datos solicitante
     * @param cliente
     *            the cliente
     * @return the datos del solicitante
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    DatosSolicitanteEntity getDatosDelSolicitante(DatosSolicitanteCriterioEntity datosSolicitante, Cliente cliente)
            throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException;

    /**
     * Gets the datos tarjeta monedero.
     *
     * @param cliente
     *            the cliente
     * @param tipoDeConsulta
     *            the tipo de consulta
     * @return the datos tarjeta monedero
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws DAOException
     *             the DAO exception
     * @throws NoSePuedeRealizarLaOperacionException
     *             the no se puede realizar la operacion exception
     */
    List<MonederoDTO> getDatosTarjetaMonedero(Cliente cliente, String tipoDeConsulta)
            throws OperacionFueraHorarioSucursalException, DAOException, NoSePuedeRealizarLaOperacionException;

    /**
     * Obtener tags.
     *
     * @param idBanco
     *            the id banco
     * @param password
     *            the password
     * @param tipoNumDoc
     *            the tipo num doc
     * @param idCuentVirtual
     *            the id cuent virtual
     * @param categoria
     *            the categoria
     * @param idUsuario
     *            the id usuario
     * @param idTag
     *            the id tag
     * @param pagCantReg
     *            the pag cant reg
     * @param pagNum
     *            the pag num
     * @return the list
     */
    List<TagEntity> obtenerTags(String idBanco, String password, String tipoNumDoc, String idCuentVirtual,
            String categoria, String idUsuario, String idTag, String pagCantReg, String pagNum);

    /**
     * Obtener transacciones tags.
     *
     * @param idBanco
     *            the id banco
     * @param tipoNumDoc
     *            the tipo num doc
     * @param idTag
     *            the id tag
     * @param fecDesde
     *            the fec desde
     * @param fecHasta
     *            the fec hasta
     * @param pagCantReg
     *            the pag cant reg
     * @param pagNum
     *            the pag num
     * @return the list
     */
    List<TransaccionEntity> obtenerTransaccionesTags(String idBanco, String tipoNumDoc, String idTag, String fecDesde,
            String fecHasta, String pagCantReg, String pagNum);

    /**
	 * Se crea metodo en diferentes ramas para ser utilizado en funcionalidad
	 * pago de tarjeta y marcacion por viaje Se setea el sexo del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
    void obtenerSexoCliente(Cliente cliente) throws DAOException;
}
