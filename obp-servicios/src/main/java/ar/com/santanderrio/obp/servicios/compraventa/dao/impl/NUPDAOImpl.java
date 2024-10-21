package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.customers.model.Document;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;

import java.util.HashMap;
import java.util.Map;

//TODO: move this to ClienteBO
@Component
public class NUPDAOImpl implements NUPDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(NUPDAOImpl.class);

    @Autowired
    private CustomersApi customersApi;

    @Override
    public NupDTO obtenerNUP(Cliente cliente) throws DAOException {
        try {
            DocumentsResponse documents = customersApi.getDocumentsById(cliente.getNup());
            return mapResponse(documents);
        } catch (ApiException apiException) {
            throw new DAOException(apiException, apiException.getErrorResponse().getMessage());
        }
    }

    private NupDTO mapResponse(DocumentsResponse documents) {
        NupDTO response = new NupDTO();
        Map<String, DetalleDocumentoDTO> documentsByType = new HashMap<String, DetalleDocumentoDTO>();

        for(Document document: documents.getDocuments()) {
            try {
                TipoDocumentoEnum tipoDocumento = TipoDocumentoEnum.getTipoDocumento(document.getType().getValue());
                DetalleDocumentoDTO documentDto = new DetalleDocumentoDTO();
                documentDto.setTipoDocumento(tipoDocumento.getIdentificadorMainframe());
                documentDto.setNroDocumento(document.getNumber());
                documentDto.setIsDocumentoPrincipal(document.getIsPrincipal());
                documentsByType.put(documentDto.getTipoDocumento(), documentDto);
            } catch (IllegalArgumentException ex) {
                LOGGER.error("Could not map document type: {}", document.getType().getValue());
            }
        }
        response.setDetalleDocumento(documentsByType);
        return response;
    }
}
