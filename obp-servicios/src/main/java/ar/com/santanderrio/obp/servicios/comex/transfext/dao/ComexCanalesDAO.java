/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import java.io.UnsupportedEncodingException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity;

/**
 * The Interface ComexCanalesDAO.
 *
 * @author IT Resources
 */
public interface ComexCanalesDAO {

	/**
	 * Consulta al webservice del Comex CanalesTrf para obtener el detalle de
	 * transferencia.
	 *
	 * @param consultaDetalleTrfOBPInEntity
	 *            the consulta detalle trf OBP in entity
	 * @return the consulta detalle trf OBP response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDetalleTrfOBPResponse consultaDetalleTrf(ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity) throws DAOException;

	/**
	 * Consulta al webservice del Comex CanalesTrf para obtener los Conceptos
	 * por tipo.
	 *
	 * @return the conceptos por tipo response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConceptosPorTipoResponse consultaConceptosPorTipo(ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity) throws DAOException;

	/**
	 * Consulta al webservice del Comex CanalesTrf para obtener todas las
	 * transferencias.
	 *
	 * @param consultaOperacionesInEntity
	 *            the consulta operaciones in entity
	 * @return the consulta operaciones response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaOperacionesResponse consultaOperaciones(ConsultaOperacionesInEntity consultaOperacionesInEntity) throws DAOException;

	/**
	 * Consulta al webservice del Comex CanalesTrf para obtener los Conceptos.
	 *
	 * @param consultaConceptoInEntity
	 *            the consulta concepto in entity
	 * @return the consulta concepto response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaConceptoResponse consultaConceptos(ConsultaConceptoInEntity consultaConceptoInEntity) throws DAOException;

	/**
	 * Procesar transferencia comex.
	 *
	 * @param procesarTransferenciaComexInEntity
	 *            the procesar transferencia comex in entity
	 * @return the procesar transferencia OBP response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ProcesarTransferenciaOBPResponse procesarTransferenciaComex(ProcesarTransferenciaComexInEntity procesarTransferenciaComexInEntity) throws DAOException;

	/**
	 * Borrar doc.
	 *
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the borrar doc response
	 * @throws DAOException
	 *             the DAO exception
	 */
	BorrarDocResponse borrarDoc(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException;

	/**
	 * Adjuntar archivos.
	 *
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the carga doc response
	 * @throws DAOException
	 *             the DAO exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	CargaDocResponse adjuntarArchivos(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException, UnsupportedEncodingException;

	/**
	 * Consulta imagen trf.
	 *
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the consulta imagen trf response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaImagenTrfResponse consultaImagenTrf(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException;

}
