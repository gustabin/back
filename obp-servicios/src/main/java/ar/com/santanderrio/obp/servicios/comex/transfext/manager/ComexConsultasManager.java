/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComprobanteInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConfiguracionAdjuntarArchivosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaConceptoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.TransferenciaComexOutView;

/**
 * The Interface ComexConsultasManager.
 *
 * @author B040619
 */
public interface ComexConsultasManager {

	/**
	 * Obtener datos de beneficiario.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosBeneficiarioOutView> obtenerDatosBeneficiarios();
	
	/**
	 * Obtener datos beneficiarios I 07.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosBeneficiarioOutView> obtenerDatosBeneficiariosA07();

	/**
	 * Obtiene los datos de la pantalla inicial de COMEX.
	 *
	 * @return the respuesta
	 */
	Respuesta<TransferenciaComexOutView> obtenerDatosComex();

	/**
	 * Obtiene la lista de bancos.
	 *
	 * @param consultaBancosInView
	 *            the consultaBancos In View
	 * @return the respuesta
	 */
	Respuesta<ConsultaBancosOutView> obtenerBancos(ConsultaBancosInView consultaBancosInView);

	/**
	 * descargar Comprobante.
	 *
	 * @param documentacion
	 *            the documentacion
	 * @return respuesta
	 */
	Respuesta<ReporteView> descargarComprobante(ComprobanteInView documentacion);

	/**
	 * verificar archivo.
	 *
	 * @param archivo
	 *            the archivo
	 * @return respuesta
	 */
	Respuesta<Boolean> verificarArchivo(ReporteView archivo);

	/**
	 * Obtener configuracion adjuntar archivo.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAdjuntarArchivosOutView> obtenerConfiguracionAdjuntarArchivo();
	
	/**
	 * Limpiar el cache de Paises.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarPaises();
	
	/**
	 * Limpiar el cache de Paises.
	 *
	 * @return the Monedas
	 */
	Respuesta<Boolean> vaciarMonedas();

	/**
	 * Obtiene consulta concepto.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultaConceptoOutView> obtenerConsultaConcepto(int idConcepto);

	/**
	 * obtiene mensaje de ayuda - pregunta de vinculante
	 *
	 * @return respuesta
	 */
	Respuesta<String> obtenerMensajeVinculante();

	/**
	 * descarga de pdf - normativa vinculante
	 *
	 * @return respuesta
	 */
	Respuesta<ReporteView> descargarNormativa();

	/**
	 * graba estadistica vinculante + acceso a confirmacion
	 *
	 * @return respuesta
	 */
	Respuesta<Void> estadisticaVinculante(ProcesarTransferenciaComexView view);

	/**
	 * graba estadistica datos beneficiario
	 *
	 * @return respuesta
	 */
	Respuesta<Void> estadisticaDatosBeneficiario();

	/**
	 * graba estadistica datos transferencia
	 *
	 * @return respuesta
	 */
	Respuesta<Void> estadisticaDatosTransferencia();

	/**
	 * graba estadistica de ingreso a adjuntar
	 *
	 * @return respuesta
	 */
	Respuesta<Void> estadisticaIngresoAdjuntar();

	Respuesta<Void> estadisticaVerComprobante();
	
}
