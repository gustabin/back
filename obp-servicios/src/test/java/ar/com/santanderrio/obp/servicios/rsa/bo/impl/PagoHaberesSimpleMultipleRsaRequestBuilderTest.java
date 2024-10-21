package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.pagohaberes.fixture.PagoHaberesFixture;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
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
public class PagoHaberesSimpleMultipleRsaRequestBuilderTest {

    @InjectMocks
    private PagoHaberesSimpleMultipleRsaRequestBuilder pagoHaberesSMRsaRequestBuilder;

    private static Predicate cantDiasUltimoCambioClave;
    private static Predicate isChallenge;
    private static Predicate cuil;

    private static Predicate pagoSueldo;
    private static Predicate editFrontData;

    @BeforeClass
    public static void setup() {
        cantDiasUltimoCambioClave = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cantDiasUltimoCambioClave".equals(((ClientDefinedFact)fact).getName());
            }
        };
        isChallenge = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "isChallenge".equals(((ClientDefinedFact)fact).getName());
            }
        };
        cuil = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "cuil".equals(((ClientDefinedFact)fact).getName());
            }
        };
        pagoSueldo = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Pago_Sueldo".equals(((ClientDefinedFact)fact).getName());
            }
        };

        editFrontData = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Edit_front_data".equals(((ClientDefinedFact)fact).getName());
            }
        };

    }

    @Test
    public void testBuildOK() {

        PagoInformadoView pagoInformadoView = PagoHaberesFixture.getPagosInformadosView1();
        EventData eventData = pagoHaberesSMRsaRequestBuilder.build(pagoInformadoView);
        Assert.assertEquals(EventType.PAYMENT, eventData.getEventType());

        FactList factList = eventData.getClientDefinedAttributeList();
        Assert.assertEquals(8, factList.getFact().size());
        checkCustomFacts(factList);

    }

    private void checkCustomFacts (FactList factList){
        checkCustomFact(
                factList,
                cantDiasUltimoCambioClave,
                "cantDiasUltimoCambioClave",
                "1",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                isChallenge,
                "isChallenge",
                "true",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                cuil,
                "cuil",
                "42644834",
                DataType.STRING
        );
        checkCustomFact(
                factList,
                pagoSueldo,
                "Pago_Sueldo",
                "true",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                editFrontData,
                "Edit_front_data",
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
