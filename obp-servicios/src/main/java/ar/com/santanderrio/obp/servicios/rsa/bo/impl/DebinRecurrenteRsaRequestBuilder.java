
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.DebinRecurrenteRSADTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 */
public class DebinRecurrenteRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
     * com.santanderrio.obp.servicios.comun.autentificacion.entities.rsaDTO)
     */
    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        DebinRecurrenteRSADTO debinRecurrenteRSADTO = (DebinRecurrenteRSADTO)operacionDeRiesgo;
        EventData ed = new EventData();
        ed.setEventType(EventType.CLIENT_DEFINED);
        ed.setClientDefinedEventType(RSAConstants.ADHESION_DEBIN_RECURRENTE);
        ed.setClientDefinedAttributeList(generarFactListTransferencia(debinRecurrenteRSADTO));
        return ed;
    }

    /**
     * Generar fact list recurrence.
     *            the request data
     * @return the fact list
     */
    private FactList generarFactListTransferencia ( DebinRecurrenteRSADTO debinRecurrenteRSADTO ) {
        FactList factList = new FactList();

        ClientDefinedFact cuitComprador = new ClientDefinedFact();
        cuitComprador.setName("cuit_comprador");
        cuitComprador.setValue(formatCuitCuil(debinRecurrenteRSADTO.getCuitComprador()));
        cuitComprador.setDataType(DataType.STRING);
        factList.getFact().add(cuitComprador);

        ClientDefinedFact cuitVendedor = new ClientDefinedFact();
        cuitVendedor.setName("cuit_vendedor");
        cuitVendedor.setValue(formatCuitCuil(debinRecurrenteRSADTO.getCuitVendedor()));
        cuitVendedor.setDataType(DataType.STRING);
        factList.getFact().add(cuitVendedor);

        ClientDefinedFact concepto = new ClientDefinedFact();
        concepto.setName("recurrencia_conc");
        concepto.setValue(debinRecurrenteRSADTO.getConcepto());
        concepto.setDataType(DataType.STRING);
        factList.getFact().add(concepto);

        ClientDefinedFact descripcion = new ClientDefinedFact();
        descripcion.setName("recurrencia_desc");
        descripcion.setValue(debinRecurrenteRSADTO.getDescripcion());
        descripcion.setDataType(DataType.STRING);
        factList.getFact().add(descripcion);

        ClientDefinedFact prestacion = new ClientDefinedFact();
        prestacion.setName("recurrencia_prest");
        prestacion.setValue(debinRecurrenteRSADTO.getPrestacion());
        prestacion.setDataType(DataType.STRING);
        factList.getFact().add(prestacion);

        ClientDefinedFact moneda = new ClientDefinedFact();
        moneda.setName("moneda");
        moneda.setValue(debinRecurrenteRSADTO.getMoneda());
        moneda.setDataType(DataType.STRING);
        factList.getFact().add(moneda);

        if(debinRecurrenteRSADTO.getCantDiasUltimoCambioClave() != null) {
            ClientDefinedFact cantDiasClave = new ClientDefinedFact();
            cantDiasClave.setName("cantDiasUltimoCambioClave");
            cantDiasClave.setValue(String.valueOf(debinRecurrenteRSADTO.getCantDiasUltimoCambioClave()));
            cantDiasClave.setDataType(DataType.INTEGER);
            factList.getFact().add(cantDiasClave);
        }

        if(debinRecurrenteRSADTO.getCantDiasUltimoCambioToken() != null) {
            ClientDefinedFact cantDiasToken = new ClientDefinedFact();
            cantDiasToken.setName("cantDiasUltimoCambioToken");
            cantDiasToken.setValue(String.valueOf(debinRecurrenteRSADTO.getCantDiasUltimoCambioToken()));
            cantDiasToken.setDataType(DataType.INTEGER);
            factList.getFact().add(cantDiasToken);
        }

        ClientDefinedFact tipoSegmentoCliente = new ClientDefinedFact();
        tipoSegmentoCliente.setName("tipo_segmento_cliente");
        tipoSegmentoCliente.setValue(debinRecurrenteRSADTO.getTipoSegmentoCliente());
        tipoSegmentoCliente.setDataType(DataType.STRING);
        factList.getFact().add(tipoSegmentoCliente);

        ClientDefinedFact mismoTitular = new ClientDefinedFact();
        mismoTitular.setName("mismo_titular");
        mismoTitular.setValue(String.valueOf(debinRecurrenteRSADTO.isMismoTitular()));
        mismoTitular.setDataType(DataType.BOOLEAN);
        factList.getFact().add(mismoTitular);

        ClientDefinedFact esPersonaJuridica = new ClientDefinedFact();
        esPersonaJuridica.setName("es_persona_juridica");
        esPersonaJuridica.setValue(String.valueOf(debinRecurrenteRSADTO.isVendedorPersonaJuridica()));
        esPersonaJuridica.setDataType(DataType.BOOLEAN);
        factList.getFact().add(esPersonaJuridica);

        return factList;
    }


    private String formatCuitCuil(String cuit) {
        if (cuit == null) {
            return "";
        }
        return !cuit.contains("-") ?
                ISBANStringUtils.agregarGuionesANumeroCuitCuil(cuit) :
                cuit;
    }

}
