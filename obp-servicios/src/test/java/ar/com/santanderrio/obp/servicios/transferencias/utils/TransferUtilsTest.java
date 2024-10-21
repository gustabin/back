package ar.com.santanderrio.obp.servicios.transferencias.utils;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class TransferUtilsTest {

    private final String nup = "123";
    private final String enabledNupDigits123 = "1,2,3";
    private final String enabledNupDigits12 = "1,2";
    private final String enableAllWildCard = "*";
    private final String disableAllWildCard = "-";

    private final List<String> cuitsAlycs = Arrays.asList("30512823099","30527516737","30528549450","30528981700","30531490866","30534987753","30538006404");

    @Test
    public void givenEnableAllWildcard_WhenIsNupEnabled_thenReturnsTrue() {

        Assert.assertTrue(TransferUtils.isNupEnabled(nup, enableAllWildCard));

    }

    @Test
    public void givenDisableAllWildcard_WhenIsNupEnabled_thenReturnsFalse() {

        Assert.assertFalse(TransferUtils.isNupEnabled(nup, disableAllWildCard));

    }

    @Test
    public void givenEnabledNupDigits123_WhenIsNupEnabled_thenReturnsTrue() {

        Assert.assertTrue(TransferUtils.isNupEnabled(nup, enabledNupDigits123));

    }

    @Test
    public void givenEnabledNupDigits12_WhenIsNupEnabled_thenReturnsFalse() {

        Assert.assertFalse(TransferUtils.isNupEnabled(nup, enabledNupDigits12));

    }

    @Test
    public void givenNupAndEnabledNupDigitsBothEqualNull_WhenIsNupEnabled_thenReturnsFalse() {

        Assert.assertFalse(TransferUtils.isNupEnabled(null, null));

    }

    @Test
    public void givenNullNupAndEnabledNupDigits123_WhenIsNupEnabled_thenReturnsFalse() {

        Assert.assertFalse(TransferUtils.isNupEnabled(null, enabledNupDigits123));

    }

    @Test
    public void whenRecipientCuitIsAlyc_thenReturnTrue(){

        boolean isAlyc = TransferUtils.isRecipientCuitAlyc("30-52898170-0", cuitsAlycs);

        Assert.assertTrue(isAlyc);
    }

    @Test
    public void whenRecipientCuitIsNotAlyc_thenReturnFalse(){

        boolean isAlyc = TransferUtils.isRecipientCuitAlyc("30-52898111-0", cuitsAlycs);

        Assert.assertFalse(isAlyc);
    }


    @Test
    public void whenRecipientCuitIsNull_thenReturnFalse(){

        boolean isAlyc = TransferUtils.isRecipientCuitAlyc(null, cuitsAlycs);

        Assert.assertFalse(isAlyc);
    }

    @Test
    public void whenRecipientCuitAlycsIsNull_thenReturnFalse(){

        boolean isAlyc = TransferUtils.isRecipientCuitAlyc("30-52898111-0", null);

        Assert.assertFalse(isAlyc);
    }

}
