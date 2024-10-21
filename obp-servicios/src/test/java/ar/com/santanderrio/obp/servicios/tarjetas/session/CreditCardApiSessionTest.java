package ar.com.santanderrio.obp.servicios.tarjetas.session;

import ar.com.santanderrio.obp.servicios.tarjetas.session.model.CreditCardApiIds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditCardApiSessionTest {

    private CreditCardApiSession idSession;

    public static final String CARD_NUMBER = "142314531215321";
    public static final String CARD_ID = "1111-1111-1111-1111";
    public static final String ACCOUNT_ID = "2222-2222-2222-2222";

    @Before
    public void setUp() throws Exception {
        idSession = new CreditCardApiSession();
        idSession.save(CARD_NUMBER, CARD_ID, ACCOUNT_ID);
    }

    @Test
    public void testFindByCardNumber() {
        CreditCardApiIds session = idSession.findSession(CARD_NUMBER);
        Assert.assertNotNull(session);
        Assert.assertEquals(CARD_ID, session.getCreditCardId());
        Assert.assertEquals(ACCOUNT_ID, session.getCreditAccountId());
    }

    @Test(expected = Test.None.class)
    public void testKeyThatAlreadyExistsDoNotThrowsError() {
        idSession.save(CARD_NUMBER, CARD_ID, ACCOUNT_ID);
    }

    @Test
    public void testFindByCardId() {
        Assert.assertEquals(CARD_NUMBER, idSession.findNumberByCardId(CARD_ID));
        Assert.assertNull(idSession.findNumberByCardId("card-id"));
    }

    @Test
    public void testFindByAccountId() {
        Assert.assertEquals(CARD_NUMBER, idSession.findNumberByAccountId(ACCOUNT_ID));
        Assert.assertNull(idSession.findNumberByAccountId("account-id"));
    }

}
