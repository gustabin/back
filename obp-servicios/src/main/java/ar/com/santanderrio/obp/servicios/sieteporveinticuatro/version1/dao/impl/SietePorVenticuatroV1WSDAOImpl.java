/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;

/**
 * The Class SietePorVenticuatroV1WSDAOImpl.
 * 
 * @author version 1,2,3: B041299 Manuel Vargas
 * @version 1: se vuelve a la invocacion de 7x24V1.ENDPOINT @see properties en
 *          configuracion
 * @version 2: cambios en el request, response. @see XMLRequestEntity,
 *          XMLResponseEntity
 * @version 3: logger en info.
 */
@Component("SietePorVenticuatroV1WSDAO")
public class SietePorVenticuatroV1WSDAOImpl implements SietePorVenticuatroV1DAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SietePorVenticuatroV1WSDAOImpl.class);

    /** The Constant CODIGO_WEB_SERVICE_7X24V1. */
    private static final String CODIGO_WEB_SERVICE_7X24V1 = "7x24V1";

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;
    
    /** The error banelco coelsa habilitado. */
    @Value("${7X24.ENCODING}")
    private String encoding; 

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.
     * SietePorVenticuatroV1DAO#ejecutar(ar.com.santanderrio.obp.servicios.
     * sieteporveinticuatro.version1.entities.XMLRequest)
     */
    @Override
    public XMLResponseEntity ejecutar(XMLRequestEntity xmlRequest) throws DAOException {
        WebClient clienteWS = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE_7X24V1);

        try {
            LOGGER.info("REQUEST - " + xmlRequest.getMETA().getNombre() + " " + xmlRequest.getMETA().getVersion());
            Form form = new Form();
            form.param("datos", JaxbUtils.transformarObjetoAXml(xmlRequest, encoding, Boolean.TRUE, Boolean.TRUE, ""));

            Response response = clienteWS.form(form);
            Object entity = response.getEntity();

            InputStream is = InputStream.class.cast(entity);
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer stringBuffer = new StringBuffer();
                String str = null;
                while ((str = br.readLine()) != null) {
                    if (!(stringBuffer.length() == 0 && str.trim().isEmpty())) {
                        stringBuffer.append(str);
                    }
                }
                String xml = stringBuffer.toString();
                LOGGER.info("RESPONSE - " + xmlRequest.getMETA().getNombre() + " " + xmlRequest.getMETA().getVersion());
                return JaxbUtils.transformarXmlAObject(xml, XMLResponseEntity.class);
            }
        } catch (JAXBException e) {
            LOGGER.error("Error de parseo ", e);
            throw new DAOException(e);
        } catch (IOException e) {
            LOGGER.error("Error en la lectura del xml ", e);
            throw new DAOException(e);
        } catch (ProcessingException e) {
            LOGGER.error("Sin conección al servicio ", e);
            throw new DAOException(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.
     * SietePorVenticuatroV1DAO#ejecutarAsync(ar.com.santanderrio.obp.servicios.
     * sieteporveinticuatro.version1.entities.XMLRequestEntity)
     */
    @Async
    @Override
    public Future<XMLResponseEntity> ejecutarAsync(XMLRequestEntity request) throws DAOException {
        return new AsyncResult<XMLResponseEntity>(ejecutar(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.
     * SietePorVenticuatroV1DAO#obtenerComprobanteErroneoAsync()
     */
    @Override
    public Future<XMLResponseEntity> obtenerComprobanteErroneoAsync() {
        return new AsyncResult<XMLResponseEntity>(obtenerComprobanteErroneo());
    }

    /**
     * Obtener comprobante erroneo.
     *
     * @return the XML response entity
     */
    private XMLResponseEntity obtenerComprobanteErroneo() {
        XMLResponseEntity xmlResponseEntity = new XMLResponseEntity();
        DATOSRESULTADO value = new DATOSRESULTADO();
        value.setCodRet("1");
        xmlResponseEntity.setDATOSRESULTADO(value);
        return xmlResponseEntity;
    }
}
