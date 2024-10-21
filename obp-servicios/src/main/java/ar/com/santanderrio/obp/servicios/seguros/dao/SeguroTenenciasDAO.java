/*
 *
 */
package ar.com.santanderrio.obp.servicios.seguros.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.*;

/**
 * The Interface SeguroTenenciasDAO.
 */
public interface SeguroTenenciasDAO {

	/**
	 * Consultar seguros.
	 *
	 * @param token
	 *            the token
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Poliza> consultarSeguros(String token, Cliente cliente) throws DAOException;

	String callLoginSeguros(String token) throws DAOException;

	GastoProtegidoDTO consultarGastosProtegidos(Cliente cliente, GastoProtegido gastoProtegido, String tokenJwt) throws  DAOException;

	CompraProtegidaDTO consultarCompraProtegida(Cliente cliente, String numeroTarjeta, String numeroCuenta, String tipoTarjeta, String tokenJwt) throws DAOException;

	EmisionOfertaIntegradaDTO emitirOfertaIntegrada(String token, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView,  String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida) throws DAOException;

	EmisionOfertaIntegradaDTO emitirOfertaIntegradaGastoProtegido(String tokenJWT, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView,  String sucursal, String tipoCuenta, String nroCuenta, String descripcionServicioPago) throws DAOException;

	FlagCompraProtegidaResponse consultarFlagCompraProtegida(String token) throws DAOException;

	FlagCompraProtegidaDTO obtenerFlagCompraProtegida(String token) throws DAOException;

	List<Poliza> consultarSegurosJwt(String tokenJwt, Cliente cliente ) throws DAOException;

}