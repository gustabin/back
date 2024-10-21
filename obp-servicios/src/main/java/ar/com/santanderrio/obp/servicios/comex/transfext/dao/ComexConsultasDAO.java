/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;

/**
 * The Interface ComexConsultasDAO.
 */
public interface ComexConsultasDAO {

	/**
	 * Consulta al webservice ConsultaPaises.
	 *
	 * @return the consulta paises response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaPaisesResponse consultaPaises() throws DAOException;

	/**
	 * Consulta al webservice ConsultaMonedas.
	 *
	 * @return the consulta monedas response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaMonedasResponse consultaMonedas() throws DAOException;

	/**
	 * Consulta al webservice ConsultaBancos.
	 *
	 * @param consultaBancosInEntity
	 *            the consulta bancos in entity
	 * @return the consulta bancos response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaBancosResponse consultaBancos(ConsultaBancosInEntity consultaBancosInEntity) throws DAOException;

	/**
	 * Genera el comprobante PDF.
	 *
	 * @param comprobanteInEntity
	 *            the comprobante in entity
	 * @return the reporte
	 * @throws DAOException 
	 */
	Reporte generarComprobante(ComprobanteComexInEntity comprobanteInEntity) throws DAOException;


	
	/**
	 * Verificar virus archivo fs.
	 *
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the respuesta
	 */
	Respuesta<Boolean> verificarVirusArchivoFs(AdjuntarArchivosInEntity adjuntarArchivosInEntity);
	
	/**
	 * Limpia la cache de paises para que impacten los refrescos externos.
	 */
	void limpiarPaises();
	
	/**
	 * Limpia la cache de monedas para que impacten los refrescos externos.
	 */
	void limpiarMonedas();



}
