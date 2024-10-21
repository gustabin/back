/*
 *
 */
package ar.com.santanderrio.obp.servicios.seguros.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;

import java.util.List;

/**
 * The Interface SeguroTenenciasBO.
 */
public interface SeguroTenenciasBO {

	/**
	 * Consultar seguros.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<PolizasDTO> consultarSeguros(Cliente cliente);

	Respuesta<PolizaDTO> consultarPoliza(String tokenJwt, Cliente cliente);

	Respuesta<GastoProtegidoDTO> consultarGastoProtegido(GastoProtegido gastoProtegido, Cliente cliente, String tokenJwt);

	Respuesta<CompraProtegidaDTO> consultarCompraProtegida(Cliente cliente, String numeroTarjeta, String numeroCuenta, String tipoTarjeta, String tokenJwt);

	Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegrada(EmisionOfertaIntegrada nuevaEmisioView, Cliente cliente, String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida);

	Respuesta<EmisionOfertaIntegradaDTO> emisionOfertaIntegradaGastoProtegido(EmisionOfertaIntegrada nuevaEmisioView, Cliente cliente, String sucursal, String tipoCuenta, String nroCuenta, String descripcionServicioPago);

	Respuesta<FlagCompraProtegidaDTO> flagCompraProtegida(Cliente cliente);
}
