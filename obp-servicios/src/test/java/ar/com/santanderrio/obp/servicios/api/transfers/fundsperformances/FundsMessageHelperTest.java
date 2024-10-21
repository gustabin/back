package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FundsMessageHelperTest {

    @Mock
    FundsPerformances fundsPerformances;

    private IFundsMessageHelper fundsMessageHelper;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        fundsMessageHelper = new FundsMessageHelper(fundsPerformances);
    }



    @Test
    public void whenShowFundMessage_thenReturnTrue() {

     boolean result = fundsMessageHelper.showFundMessage(FundsFixtureObject.getTransferenciaViewCase1(),
             FundsFixtureObject.getClienteCase1());

        Assert.assertTrue(result);
    }

    @Test
    public void whenShowFundMessageWithCBU_thenReturnFalse() {

        boolean result = fundsMessageHelper.showFundMessage(FundsFixtureObject.getTransferenciaViewCase2(),
                FundsFixtureObject.getClienteCase1());

        Assert.assertFalse(result);
    }

    @Test
    public void whenShowFundMessageWithCuitNotMatches_thenReturnFalse() {

        boolean result = fundsMessageHelper.showFundMessage(FundsFixtureObject.getTransferenciaViewCase1(),
                FundsFixtureObject.getClienteCase2());

        Assert.assertFalse(result);
    }

    @Test
    public void whenShowFundMessageWithRecipientCuitNull_thenReturnFalse() {

        boolean result = fundsMessageHelper.showFundMessage(FundsFixtureObject.getTransferenciaViewCase3(),
                FundsFixtureObject.getClienteCase2());

        Assert.assertFalse(result);
    }

    @Test
    public void whenShowFundMessageWithOriginCuitNull_thenReturnFalse() {

        boolean result = fundsMessageHelper.showFundMessage(FundsFixtureObject.getTransferenciaViewCase1(),
                FundsFixtureObject.getClienteCase5());

        Assert.assertFalse(result);
    }

    @Test
    public void whenHasTitleAccountRetail_thenReturnTrue() {

        boolean result = fundsMessageHelper.hasTitleAccountRetail(FundsFixtureObject.getClienteCase3());

        Assert.assertTrue(result);
    }

    @Test
    public void whenNotHasTitleAccountRetail_thenReturnFalse() {

        boolean result = fundsMessageHelper.hasTitleAccountRetail(FundsFixtureObject.getClienteCase4());

        Assert.assertFalse(result);
    }

    @Test
    public void whenHasTitleAccountBP_thenReturnTrue() {

        boolean result = fundsMessageHelper.hasTitleAccountBP(FundsFixtureObject.getClienteCase6AccountBP());

        Assert.assertTrue(result);
    }

    @Test
    public void whenNotHasTitleAccountBP_thenReturnFalse() {

        boolean result = fundsMessageHelper.hasTitleAccountBP(FundsFixtureObject.getClienteCase4());

        Assert.assertFalse(result);
    }

    @Test
    public void whenGetPerformanceFund_thenOK() {

        Mockito.when(fundsPerformances.getPerformancesById("6")).thenReturn(FundsFixtureObject.getFundsPerformancesResponse());

        String performancesResponse = fundsMessageHelper.getPerformanceFund();

        Assert.assertEquals("123%", performancesResponse);
    }

    @Test
    public void whenGetPerformanceFundCatchException_thenReturnEmptyString() {

        Exception e = new Exception();

        Mockito.when(fundsPerformances.getPerformancesById("6")).thenThrow(new FundsPerformancesException(e));

        String result = fundsMessageHelper.getPerformanceFund();

        Assert.assertEquals("", result);

    }

    @Test
    public void whenGetPerformanceFundWithEmptyList_thenReturnEmptyString() {

        Mockito.when(fundsPerformances.getPerformancesById("6")).thenReturn(FundsFixtureObject.getFundsPerformancesResponseCase2());

        String performancesResponse = fundsMessageHelper.getPerformanceFund();

        Assert.assertEquals("", performancesResponse);
    }

    @Test
    public void whenGetPerformanceFundWithAnnualizedLast30DayNull_thenReturnEmptyString() {

        Mockito.when(fundsPerformances.getPerformancesById("6")).thenReturn(FundsFixtureObject.getFundsPerformancesResponseCase3());

        String performancesResponse = fundsMessageHelper.getPerformanceFund();

        Assert.assertEquals("", performancesResponse);
    }

}
