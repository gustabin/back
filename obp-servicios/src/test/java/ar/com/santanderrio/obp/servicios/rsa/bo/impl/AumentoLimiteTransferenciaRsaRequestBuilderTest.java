package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.feature.AumentoLimiteTransferenciaInOutViewFeature;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
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
public class AumentoLimiteTransferenciaRsaRequestBuilderTest {
    @InjectMocks
    private static AumentoLimiteTransferenciaRsaRequestBuilder aumentoLimiteTransferenciaRsaRequestBuilder;
    private static Predicate antiguedad;
    private static Predicate cuit;
    private static Predicate aumentoLimite;
    private static Predicate acumTotal;
    private static Predicate cantDiasUltimoCambioClave;
    private static Predicate cantTotal;
    private static Predicate acumDestino;
    private static Predicate cantDiasUltimoCambioToken;
    private static Predicate cantDestino;
    private static Predicate scoringDestinatario;
    private static Predicate cuitDestino;

    private static Predicate scoreBiocatch;
    private static Predicate ruleBiocacth;
    private static Predicate ruleIdBiocatch;
    private static Predicate ruleNameBiocatch;
    private static Predicate isBotBc;
    private static Predicate isEmulatorBc;
    private static Predicate isRatBc;
    private static Predicate isRecentRatBc;
    private static Predicate isMobileRatBc;
    private static Predicate voiceScamScoreBc;
    private static Predicate maxScore30DiasBc;
    private static Predicate riskFactorBC;
    private static Predicate genFactorBc;
    private static Predicate cantDiasUltimoCambioMail;


    @BeforeClass
    public static void setup() {
        antiguedad = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.ANTIGUEDAD_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        cuit = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_ORIGINANTE_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };

        cuitDestino = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };

        aumentoLimite = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.AUMENTO_LIMITE_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        acumTotal = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.ACUM_TOTAL_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        cantTotal = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CANT_TOTAL_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        acumDestino = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.ACUM_DESTINO_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        cantDestino = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DESTINO_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        cantDiasUltimoCambioClave = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_CLAVE_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        cantDiasUltimoCambioToken = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_TOKEN_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };
        scoringDestinatario = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.SCORING_DESTINATARIO_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };

        scoreBiocatch = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Score_Biocatch".equals(((ClientDefinedFact)fact).getName());
            }
        };

        ruleBiocacth = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Rule_Biocatch".equals(((ClientDefinedFact)fact).getName());
            }
        };

        ruleIdBiocatch = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Rule_ID_Biocatch".equals(((ClientDefinedFact)fact).getName());
            }
        };

        ruleNameBiocatch = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Rule_Name_Biocatch".equals(((ClientDefinedFact)fact).getName());
            }
        };

        isBotBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "IsBot_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        isEmulatorBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "IsEmulator_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        isRatBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "IsRat_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        isRecentRatBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "IsRecentRat_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        isMobileRatBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "IsMobileRat_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        voiceScamScoreBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "VoiceScam_Score_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        maxScore30DiasBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "Maxscore30dias_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        riskFactorBC = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "RiskFactor1_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        genFactorBc = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return "GenFactor1_BC".equals(((ClientDefinedFact)fact).getName());
            }
        };

        cantDiasUltimoCambioMail = new Predicate() {
            @Override
            public boolean evaluate(Object fact) {
                return aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT.equals(((ClientDefinedFact)fact).getName());
            }
        };

    }

    @Test
    public void testBuildOCustomFactOK() {

        AumentoLimiteTransferenciaInOutView aumentoLimiteTransferenciaInOutView = AumentoLimiteTransferenciaInOutViewFeature.getAumentoLimiteTransferenciaInOutView();
        EventData eventData = aumentoLimiteTransferenciaRsaRequestBuilder.build(aumentoLimiteTransferenciaInOutView);
        Assert.assertEquals(EventType.PAYMENT, eventData.getEventType());

        FactList factList = eventData.getClientDefinedAttributeList();
        Assert.assertEquals(17, factList.getFact().size());
        checkCustomFacts(factList);

    }

    @Test
    public void testBuildAllCustomFactOK() {

        AumentoLimiteTransferenciaInOutView aumentoLimiteTransferenciaInOutView = AumentoLimiteTransferenciaInOutViewFeature.getAumentoLimiteTransferenciaInOutView2();
        EventData eventData = aumentoLimiteTransferenciaRsaRequestBuilder.build(aumentoLimiteTransferenciaInOutView);
        Assert.assertEquals(EventType.PAYMENT, eventData.getEventType());

        FactList factList = eventData.getClientDefinedAttributeList();
        Assert.assertEquals(22, factList.getFact().size());
        checkCustomFacts2(factList);

    }

    @Test
    public void testBuildCustomFactBiotCatch() {

        AumentoLimiteTransferenciaInOutView aumentoLimiteTransferenciaInOutView = AumentoLimiteTransferenciaInOutViewFeature.getAumentoLimiteTransferenciaInOutViewBiocatch();
        EventData eventData = aumentoLimiteTransferenciaRsaRequestBuilder.build(aumentoLimiteTransferenciaInOutView);
        Assert.assertEquals(EventType.PAYMENT, eventData.getEventType());

        FactList factList = eventData.getClientDefinedAttributeList();
        Assert.assertEquals(18, factList.getFact().size());
        checkCustomFactsBioCatch(factList);

    }
    private void checkCustomFacts (FactList factList){
        checkCustomFact(
                factList,
                antiguedad,
                aumentoLimiteTransferenciaRsaRequestBuilder.ANTIGUEDAD_CUSTOM_FACT,
                "0",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                cuit,
                aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_ORIGINANTE_CUSTOM_FACT,
                "30-70913506-6",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                cuitDestino,
                aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_CUSTOM_FACT,
                "30-70913308-6",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                aumentoLimite,
                aumentoLimiteTransferenciaRsaRequestBuilder.AUMENTO_LIMITE_CUSTOM_FACT,
                "true",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                aumentoLimite,
                aumentoLimiteTransferenciaRsaRequestBuilder.AUMENTO_LIMITE_CUSTOM_FACT,
                "true",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                scoringDestinatario,
                aumentoLimiteTransferenciaRsaRequestBuilder.SCORING_DESTINATARIO_CUSTOM_FACT,
                String.valueOf(1f),
                DataType.FLOAT
        );

        checkCustomFact(
                factList,
                cantDiasUltimoCambioMail,
                aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT,
                String.valueOf(8),
                DataType.INTEGER
        );

    }

    private void checkCustomFacts2 (FactList factList){
        checkCustomFact(
                factList,
                antiguedad,
                aumentoLimiteTransferenciaRsaRequestBuilder.ANTIGUEDAD_CUSTOM_FACT,
                "0",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                cuit,
                aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_ORIGINANTE_CUSTOM_FACT,
                "30-70913506-6",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                cuitDestino,
                aumentoLimiteTransferenciaRsaRequestBuilder.CUIT_CUSTOM_FACT,
                "30-70913308-6",
                DataType.STRING
        );


        checkCustomFact(
                factList,
                acumTotal,
                aumentoLimiteTransferenciaRsaRequestBuilder.ACUM_TOTAL_CUSTOM_FACT,
                "20563",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                cantDiasUltimoCambioClave,
                aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_CLAVE_CUSTOM_FACT,
                "2",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                cantTotal,
                aumentoLimiteTransferenciaRsaRequestBuilder.CANT_TOTAL_CUSTOM_FACT,
                "0",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                acumDestino,
                aumentoLimiteTransferenciaRsaRequestBuilder.ACUM_DESTINO_CUSTOM_FACT,
                "25000",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                cantDiasUltimoCambioToken,
                aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DIAS_ULTIMO_CAMBIO_TOKEN_CUSTOM_FACT,
                "2",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                cantDestino,
                aumentoLimiteTransferenciaRsaRequestBuilder.CANT_DESTINO_CUSTOM_FACT,
                "2",
                DataType.INTEGER
        );
        checkCustomFact(
                factList,
                aumentoLimite,
                aumentoLimiteTransferenciaRsaRequestBuilder.AUMENTO_LIMITE_CUSTOM_FACT,
                "true",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                scoringDestinatario,
                aumentoLimiteTransferenciaRsaRequestBuilder.SCORING_DESTINATARIO_CUSTOM_FACT,
                String.valueOf(0f),
                DataType.FLOAT
        );
    }

    private void checkCustomFactsBioCatch(FactList factList){

        checkCustomFact(
                factList,
                scoreBiocatch,
                "Score_Biocatch",
                "30",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                ruleBiocacth,
                "Rule_Biocatch",
                "Allow",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                ruleIdBiocatch,
                "Rule_ID_Biocatch",
                "3502",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                ruleNameBiocatch,
                "Rule_Name_Biocatch",
                "DEF_OBP_PERSONAS",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                isBotBc,
                "IsBot_BC",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                isEmulatorBc,
                "IsEmulator_BC",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                isRatBc,
                "IsRat_BC",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                isRecentRatBc,
                "IsRecentRat_BC",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                isMobileRatBc,
                "IsMobileRat_BC",
                "false",
                DataType.BOOLEAN
        );

        checkCustomFact(
                factList,
                voiceScamScoreBc,
                "VoiceScam_Score_BC",
                "57",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                maxScore30DiasBc,
                "Maxscore30dias_BC",
                "0",
                DataType.INTEGER
        );

        checkCustomFact(
                factList,
                riskFactorBC,
                "RiskFactor1_BC",
                "R102",
                DataType.STRING
        );

        checkCustomFact(
                factList,
                genFactorBc,
                "GenFactor1_BC",
                "G011",
                DataType.STRING
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
