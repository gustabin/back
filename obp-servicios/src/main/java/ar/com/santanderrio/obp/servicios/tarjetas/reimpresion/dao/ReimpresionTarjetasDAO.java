/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitada;

/**
 * The Interface ReimpresionTarjetasDAO.
 */
public interface ReimpresionTarjetasDAO {

	/**
	 * Consulta datos tarjetas.
	 *
	 * @param in
	 *            the in
	 * @return the consulta datos tarjetas out
	 */
	ConsultaDatosTarjetasOut consultaDatosTarjetas(ConsultaDatosTarjetasIn in);

	/**
	 * Alta reimpresion tarjeta debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tarjetaSolicitada
	 *            the tarjeta solicitada
	 * @return the tarjeta out
	 */
	TarjetaOut altaReimpresionTarjetaDebito(Cliente cliente, TarjetaSolicitada tarjetaSolicitada);

	/**
	 * Alta reimpresion tarjeta credito.
	 *
	 * @param in
	 *            the in
	 * @return the alta reimpresion tarjetas out
	 */
	AltaReimpresionTarjetasOut altaReimpresionTarjetaCredito(AltaReimpresionTarjetasIn in);

	/**
	 * Descargar comprobante.
	 *
	 * @param datos
	 *            the datos
	 * @return the reporte
	 */
	Reporte descargarComprobante(DatosReimpresionComprobante datos);

}
