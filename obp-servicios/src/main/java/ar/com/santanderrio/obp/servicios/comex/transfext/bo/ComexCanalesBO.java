/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoPorTipoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesView;

/**
 * The Interface ComexCanalesBO.
 *
 * @author IT Resources
 */
public interface ComexCanalesBO {

	/**
	 * Obtiene el detalle de una transferencia.
	 *
	 * @param consultaDetalleTrfOBPInDTO
	 *            the consulta detalle trf OBP in DTO
	 * @param operacion
	 *            the operacion
	 * @return the respuesta
	 */
	Respuesta<ConsultaDetalleTrfOBPOutDTO> consultaDetalleTrf(ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO, ConsultaOperacionesView operacion);

	/**
	 * Consulta concepto por tipo.
	 *
	 * @return the respuesta
	 */
	Respuesta<List<ConceptoPorTipoOutDTO>> consultaConceptoPorTipo(ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity);

	/**
	 * Obtiene todas las operaciones.
	 *
	 * @param consultaOperacionesInDTO
	 *            the consulta operaciones in DTO
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesOutDTO> consultaOperaciones(ConsultaOperacionesInDTO consultaOperacionesInDTO);

	/**
	 * Procesa la baja, el alta y la modificacion de la transferencia
	 * dependiendo de la accion.
	 *
	 * @param procesarTransferenciaComexInDTO
	 *            the procesar transferencia comex in DTO
	 * @return the respuesta
	 */
	Respuesta<ProcesarTransferenciaComexOutDTO> procesarTransferenciaComex(ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO);

	/**
	 * Adjuntar archivos.
	 *
	 * @param adjuntarArchivosInDto
	 *            the adjuntar archivos in dto
	 * @return the respuesta
	 */
	Respuesta<Integer> adjuntarArchivos(AdjuntarArchivosDTO adjuntarArchivosInDto);

	/**
	 * Borrar doc.
	 *
	 * @param adjuntarArchivosInDto
	 *            the adjuntar archivos in dto
	 * @return the respuesta
	 */
	Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto);

	/**
	 * Consulta imagen trf.
	 *
	 * @param adjuntarArchivosInDto
	 *            the adjuntar archivos in dto
	 * @return the respuesta
	 */
	Respuesta<AdjuntarArchivosDTO> consultaImagenTrf(AdjuntarArchivosDTO adjuntarArchivosInDto);

	/**
	 * Consulta concepto.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConceptoOutDTO> consultaConcepto(int idConcepto);
}
