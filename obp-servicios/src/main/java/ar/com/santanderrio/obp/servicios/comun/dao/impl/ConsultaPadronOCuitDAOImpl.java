/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronesCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronABAOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronBCRAOutEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ConsultaPadronCuitDAOImpl.
 *
 * @author alejandro_leal
 */
@Component("consultaPadronOCuitDAO")
public class ConsultaPadronOCuitDAOImpl extends IatxBaseDAO implements ConsultaPadronOCuitDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPadronOCuitDAOImpl.class);


    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /**
     * Consultar iatx para Fondo.
     * 
     * @param entity
     *            the entity
     * @return entidad con la informacion de retorno de iatx de Fondo
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public ConsultaPadronCuitOutEntity consultaPadron(ConsultaPadronCuitInEntity entity) throws DAOException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

        String servicio = "CNSPADCUIT";
        String version = "100";

        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        ConsultaPadronCuitOutEntity padronCuitOut = new ConsultaPadronCuitOutEntity();
        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSPADCUIT(entity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

            if ((iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK))
                    && (iatxResponse.getErrorCode() == 00000000)) {
                padronCuitOut.setCodigoRetornoExtendido(iatxResponse.getIatxBody().get(0));
                padronCuitOut.setCantidadBcra(iatxResponse.getIatxBody().get(1));
                padronCuitOut.setBcraNroCuit(iatxResponse.getIatxBody().get(2));
                padronCuitOut.setBcraApellidoNombre1(iatxResponse.getIatxBody().get(3));

                // tomo el 1er elemento de la lista
                int cantAba = Integer.parseInt(iatxResponse.getIatxBody().get(22));
                if (cantAba > 0) {
                    padronCuitOut.setCantidadABA(iatxResponse.getIatxBody().get(22));
                    padronCuitOut.setAbaNroCuit(iatxResponse.getIatxBody().get(23));
                    padronCuitOut.setAbaTipoDocumento(iatxResponse.getIatxBody().get(24));
                    padronCuitOut.setAbaNroDocumento(iatxResponse.getIatxBody().get(25));
                    padronCuitOut.setAbaNombre(iatxResponse.getIatxBody().get(26));
                    padronCuitOut.setAbaApellido(iatxResponse.getIatxBody().get(27));

                    Date date1 = sdf1.parse(iatxResponse.getIatxBody().get(28));
                    String fecNac = sdf2.format(date1);
                    padronCuitOut.setAbaFechaNacimiento(fecNac);

                    padronCuitOut.setAbaSexo(iatxResponse.getIatxBody().get(29));
                    padronCuitOut.setAbaTipoPersona(iatxResponse.getIatxBody().get(30));
                }
            } else {
                // 1.4.4. Flujo alternativo 3 – Persona no existe en Padron
                return new ConsultaPadronCuitOutEntity(Integer.toString(iatxResponse.getErrorCode()), false);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return padronCuitOut;
    }

    /**
     * Consultar iatx para Fondo.
     * 
     * @param entity
     *            the entity
     * @return entidad con la informacion de retorno de iatx de Fondo
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public List<ConsultaPadronCuitOutEntity> consultaPadronRetornandoListaCoincidencias(
            ConsultaPadronCuitInEntity entity) throws DAOException {

        List<ConsultaPadronCuitOutEntity> respuesta = new ArrayList<ConsultaPadronCuitOutEntity>();

        String servicio = "CNSPADCUIT";
        String version = "100";

        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSPADCUIT(entity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

            if ((iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK))
                    && (iatxResponse.getErrorCode() == 00000000)) {
                respuesta = getListaCoincidenciasEnPadron(iatxResponse);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return respuesta;
    }

	/**
	 * Arma lista con elementos obtenidos del padron - pueden ser mas de uno.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the lista coincidencias en padron
	 */
	private List<ConsultaPadronCuitOutEntity> getListaCoincidenciasEnPadron(IatxResponse iatxResponse) {
		List<ConsultaPadronCuitOutEntity> elementos = new ArrayList<ConsultaPadronCuitOutEntity>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		int registrosEnTrama = Integer.parseInt(iatxResponse.getIatxBody().get(22));
		Integer aba = 23;
		for (int i = 0; i < registrosEnTrama; i++) {
			try {
				ConsultaPadronCuitOutEntity elemento = new ConsultaPadronCuitOutEntity();
				elemento.setAbaNroCuit(iatxResponse.getIatxBody().get(aba));
				elemento.setAbaTipoDocumento(iatxResponse.getIatxBody().get(aba += 1));
				elemento.setAbaNroDocumento(iatxResponse.getIatxBody().get(aba += 1));
				elemento.setAbaNombre(iatxResponse.getIatxBody().get(aba += 1));
				elemento.setAbaApellido(iatxResponse.getIatxBody().get(aba += 1));
				Date date1 = sdf1.parse(iatxResponse.getIatxBody().get(aba += 1));
				String fecNac = sdf2.format(date1);
				elemento.setAbaFechaNacimiento(fecNac);
				elemento.setAbaSexo(iatxResponse.getIatxBody().get(aba += 1));
				elemento.setAbaTipoPersona(iatxResponse.getIatxBody().get(aba += 1));
				aba+=1;
				elementos.add(elemento);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return elementos;
	}

    /**
     * Genera el objeto IatxRequestData para llamar al servicio iatx.
     * 
     * @param entity
     *            the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCNSPADCUIT(ConsultaPadronCuitInEntity entity) {
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(entity.getOpcion());
        iatxRequestData.addBodyValue(entity.getArgumento());
        return iatxRequestData;
    }
    
    
    /**
     * Retorna ConsultaPadronesCuitOutEntity con listas de padrones ABA y BCRA por separado.
     *
     * @param ConsultaPadronCuitInEntity
     *            the Padron Cuit In Entity
     * @return the ConsultaPadronesCuitOutEntity
     */
    @Override
    public ConsultaPadronesCuitOutEntity consultaPadrones(ConsultaPadronCuitInEntity entity) throws DAOException {

        ConsultaPadronesCuitOutEntity consultaPadrones = new ConsultaPadronesCuitOutEntity();
        String servicio = "CNSPADCUIT";
        String version = "100";

        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        try {
            IatxRequestData iatxRequestData = generateRequestDataCNSPADCUIT(entity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

            if ((iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK))
                    && (iatxResponse.getErrorCode() == 00000000)) {
                consultaPadrones = getListaCoincidencias(iatxResponse);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return consultaPadrones;
    }

    /**
     * Arma ConsultaPadronesCuitOutEntity con listas de padrones ABA y BCRA por separado.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the lista coincidencias en padron
     * @throws ParseException 
     */
    private ConsultaPadronesCuitOutEntity getListaCoincidencias(IatxResponse iatxResponse)
            throws IndexOutOfBoundsException, ParseException {
        ConsultaPadronesCuitOutEntity consultaPadrones = new ConsultaPadronesCuitOutEntity();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        int registrosBCRA = Integer.parseInt(iatxResponse.getIatxBody().get(1));
        consultaPadrones.setCodigoRetornoExtendido(iatxResponse.getIatxBody().get(0));
        consultaPadrones.setCantidadBcra(registrosBCRA);
        for (int i = 0; i < registrosBCRA; i++) {
            try {
            PadronBCRAOutEntity cuitBCRA = new PadronBCRAOutEntity();
            cuitBCRA.setCuit(iatxResponse.getIatxBody().get(2));
            cuitBCRA.setBcraApellidoNombre1(iatxResponse.getIatxBody().get(3));
            consultaPadrones.getPadronBCRA().add(cuitBCRA);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }
        int registrosABA = Integer.parseInt(iatxResponse.getIatxBody().get(22));
        consultaPadrones.setCantidadABA(registrosABA);
        int indiceABA = 23;
        for (int i = 0; i < registrosABA; i++) {
            try {
                PadronABAOutEntity cuitABA = new PadronABAOutEntity();
                cuitABA.setCuit(iatxResponse.getIatxBody().get(indiceABA++));
                cuitABA.setAbaTipoDocumento(iatxResponse.getIatxBody().get(indiceABA++));
                cuitABA.setAbaNroDocumento(iatxResponse.getIatxBody().get(indiceABA ++));
                cuitABA.setAbaNombre(iatxResponse.getIatxBody().get(indiceABA ++));
                cuitABA.setAbaApellido(iatxResponse.getIatxBody().get(indiceABA ++));
                Date date1 = sdf1.parse(iatxResponse.getIatxBody().get(indiceABA++));
                String fecNac = sdf2.format(date1);
                cuitABA.setAbaFechaNacimiento(fecNac);
                cuitABA.setAbaSexo(iatxResponse.getIatxBody().get(indiceABA++));
                cuitABA.setAbaTipoPersona(iatxResponse.getIatxBody().get(indiceABA++));
                consultaPadrones.getPadronABA().add(cuitABA);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            } catch (ParseException e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }
        return consultaPadrones;
    }


}
