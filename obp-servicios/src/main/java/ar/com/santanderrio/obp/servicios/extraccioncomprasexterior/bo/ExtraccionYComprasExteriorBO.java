/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorInDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;

/**
 * Interface ExtraccionComprasExteriorBO.
 *
 * @author Silvina_Luque
 */
public interface ExtraccionYComprasExteriorBO {

	/**
	 * Consulta de cuentas por tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return lista cuentas
	 */
	Respuesta<List<CuentaOperacionExteriorDTO>> consultarCuentasOperaExterior(String numeroTarjeta);

	/**
	 * Consulta de tarjetas de debito para operar en el exterior.
	 *
	 * @return lista tarjetas
	 */
	Respuesta<List<TarjetaOperacionExteriorDTO>> consultarTarjetasOperaExterior();

	/**
	 * Modificacion de tarjeta para operar en el exterior.
	 *
	 * @param datosCambio
	 *            the datos cambio
	 * @return the respuesta
	 */
	Respuesta<CambioTarjetaOperaExteriorOutDTO> cambioTarjetaOperaExterior(CambioTarjetaOperaExteriorInDTO datosCambio);

	/**
	 * comprobante de extraccionYCompras en el exterior.
	 *
	 * @return Reporte
	 */
	Respuesta<Reporte> descargarComprobante();
}
