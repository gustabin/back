package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.DebinRecurrenteRSADTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class DebinRecurrenteRsaRequestBuilderTest {

    /** The rsa manager impl. */
    @InjectMocks
    private DebinRecurrenteRsaRequestBuilder debinRecurrenteRsaRequestBuilderImpl;

    private static Predicate predicadoCuitVendedor;
    private static Predicate predicadoCuitComprador;
    private static Predicate predicadoConcepto;
    private static Predicate predicadoDescripcion;
    private static Predicate predicadoPrestacion;
    private static Predicate predicadoMoneda;
    private static Predicate predicadoCantDiasUltimoCambioClave;
    private static Predicate predicadoCantDiasUltimoCambioToken;
    private static Predicate predicadoTipoSegmentoCliente;
    private static Predicate predicadoMismoTitular;
    private static Predicate predicadoVendedorPersonaJuridica;

    @BeforeClass
    public static void setup() {
        predicadoCuitVendedor = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cuit_vendedor".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoCuitComprador = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cuit_comprador".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoConcepto = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "recurrencia_conc".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoDescripcion = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "recurrencia_desc".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoPrestacion = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "recurrencia_prest".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoMoneda = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "moneda".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoCantDiasUltimoCambioClave = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cantDiasUltimoCambioClave".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoCantDiasUltimoCambioToken = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cantDiasUltimoCambioToken".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoTipoSegmentoCliente = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "tipo_segmento_cliente".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoMismoTitular = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "mismo_titular".equals(((ClientDefinedFact)fact).getName());
            }
        };
        predicadoVendedorPersonaJuridica = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "es_persona_juridica".equals(((ClientDefinedFact)fact).getName());
            }
        };
    }

    @Test
    public void testBuildOK(){
        DebinRecurrenteRSADTO debinRecurrenteRSADTO = new DebinRecurrenteRSADTO();
        debinRecurrenteRSADTO.setValidarBloqueo(true);
        debinRecurrenteRSADTO.setCuitComprador("23302005044");
        debinRecurrenteRSADTO.setCuitVendedor("20242201524");
        debinRecurrenteRSADTO.setConcepto("VAR");
        debinRecurrenteRSADTO.setDescripcion("Varios");
        debinRecurrenteRSADTO.setPrestacion("Prueba Aviso");
        debinRecurrenteRSADTO.setMoneda("peso");
        debinRecurrenteRSADTO.setCantDiasUltimoCambioClave(Integer.valueOf("1"));
        debinRecurrenteRSADTO.setCantDiasUltimoCambioToken(Integer.valueOf("2"));
        debinRecurrenteRSADTO.setTipoSegmentoCliente("santander");
        debinRecurrenteRSADTO.setMismoTitular(false);
        debinRecurrenteRSADTO.setVendedorPersonaJuridica(true);

        EventData eventData = debinRecurrenteRsaRequestBuilderImpl.build(debinRecurrenteRSADTO);

        Assert.assertEquals(EventType.CLIENT_DEFINED, eventData.getEventType());
        Assert.assertEquals(RSAConstants.ADHESION_DEBIN_RECURRENTE, eventData.getClientDefinedEventType());

        FactList factList = eventData.getClientDefinedAttributeList();
        Assert.assertEquals(11, factList.getFact().size());
        checkCustomFacts(factList);
    }

    private void checkCustomFacts(FactList factList) {
        checkCustomFact(
                factList,
                predicadoCuitVendedor,
                "cuit_vendedor",
                "20-24220152-4",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoCuitComprador,
                "cuit_comprador",
                "23-30200504-4",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoConcepto,
                "recurrencia_conc",
                "VAR",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoDescripcion,
                "recurrencia_desc",
                "Varios",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoPrestacion,
                "recurrencia_prest",
                "Prueba Aviso",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoMoneda,
                "moneda",
                "peso",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoCantDiasUltimoCambioClave,
                "cantDiasUltimoCambioClave",
                "1",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                predicadoCantDiasUltimoCambioToken,
                "cantDiasUltimoCambioToken",
                "2",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                predicadoTipoSegmentoCliente,
                "tipo_segmento_cliente",
                "santander",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                predicadoMismoTitular,
                "mismo_titular",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                predicadoVendedorPersonaJuridica,
                "es_persona_juridica",
                "true",
                DataType.BOOLEAN
        );
    }

    private void checkCustomFact(FactList factList, Predicate predicate, String expectedName, String expectedValue, DataType expectedDataType) {
        ClientDefinedFact customFact = getCustomFact(factList.getFact(), predicate);
        Assert.assertNotNull(customFact);
        Assert.assertEquals(expectedName, customFact.getName());
        Assert.assertEquals(expectedValue, customFact.getValue());
        Assert.assertEquals(expectedDataType, customFact.getDataType());
    }

    private ClientDefinedFact getCustomFact(Collection factList, Predicate predicate) {
        return (ClientDefinedFact) CollectionUtils.find(factList, predicate);
    }
}
