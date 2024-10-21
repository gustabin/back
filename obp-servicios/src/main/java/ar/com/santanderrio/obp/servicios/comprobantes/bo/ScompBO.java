/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;

/**
 * The Interface TransferenciaInmediataBO.
 *
 * @author sabrina.cis
 */
public interface ScompBO {

	/**
	 * Obtener comprobante DTO para transf inmediatas rio rio T banco.
	 *
	 * @param comprobante the comprobante
	 * @return the comprobante DTO
	 */
	ComprobanteDTO  obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco(
			ComprobanteTransfInmRioRio comprobante);

	/**
	 * Obtener comprobantes rio.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesRio(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes otros comprobantes async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesOtrosComprobantes(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes rio async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes otros bancos.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancos(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes otros bancos async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes otros comprobantes.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosComprobantesAsync(String nup,
			TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes tarjeta recargable.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesTarjetaRecargable(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes tarjeta recargable async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesTarjetaRecargableAsync(String nup,
			TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes pago de compras.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPagoDeCompras(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes pago de compras async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoDeComprasAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Alta de transferencia en scomp.
	 *
	 * @param altaRequest
	 *            the alta request
	 * @return the respuesta
	 */
	Respuesta<Void> altaScompTransferencia(AltaComprobanteRequestBuilder altaRequest);

	/**
	 * Obtener comprobantes rio T banco.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesRioTBanco(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes rio T banco async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioTBancoAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC con deuda async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCConDeudaAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC con deuda.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCConDeuda(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC sin deuda async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCSinDeudaAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC sin deuda.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCSinDeuda(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMCVEP async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCVEPAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMCVEP.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCVEP(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC afip async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAfipAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC afip.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesOBTBanco(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC afip async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOBTBancoAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes PMC afip.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCAfip(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes compra venta dolar.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesCompraVentaDolar(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes compra venta dolar async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesCompraVentaDolarAsync(String nup, TransaccionDTO transaccion);

	/**
	 * Obtener comprobantes pago de compras async.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoDeComprasAsync(Cliente cliente,
			TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC sin deuda TC async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
    Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCSinDeudaTCAsync(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC sin deuda TC.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
    Respuesta<ComprobantesDTO> obtenerComprobantesPMCSinDeudaTC(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC con deuda TC async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
    Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCConDeudaTCAsync(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC con deuda TC.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
    Respuesta<ComprobantesDTO> obtenerComprobantesPMCConDeudaTC(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC VEP TC async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCVEPTCAsync(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC VEP TC.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCVEPTC(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC AFIP TC async.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAfipTCAsync(String nup, TransaccionDTO transaccion);

    /**
	 * Obtener comprobantes PMC AFIP TC.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPMCAfipTC(String nup, TransaccionDTO transaccion);

	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCOpenBankAsync(String nup, TransaccionDTO transaccion);

	Respuesta<ComprobantesDTO> obtenerComprobantesPMCOpenBank(String nup, TransaccionDTO transaccion);

}
