package ar.com.santanderrio.obp.servicios.transferencias.utils;

import java.util.Arrays;
import java.util.List;

public class TransferUtils {

    public static final String ENABLED_FOR_ALL_WILDCARD = "*";

    public static final String DISABLED_FOR_ALL_WILDCARD = "-";

    private TransferUtils(){}

    public static boolean isNupEnabled(String nupToCheck, String enabledNupDigits) {

        List<String> enabledNupDigitsList;

        if (enabledNupDigits != null && nupToCheck != null) {

            if (ENABLED_FOR_ALL_WILDCARD.equals(enabledNupDigits)) {

                return true;

            } else if (DISABLED_FOR_ALL_WILDCARD.equals(enabledNupDigits)) {

                return false;

            }

            enabledNupDigitsList = Arrays.asList(enabledNupDigits.split(","));

        } else {

            return false;

        }

        String lastDigitsNup = getLastDigitsNupClient(nupToCheck, enabledNupDigitsList.get(0).length());

        return enabledNupDigitsList.contains(lastDigitsNup);

    }

    private static String getLastDigitsNupClient(String nupClient, int endingDigitsAmount) {

        int beginIndex = nupClient.length() - endingDigitsAmount;
        beginIndex = Math.max(beginIndex, 0);

        return nupClient.substring(beginIndex);

    }

    public static boolean isRecipientCuitAlyc(String recipientCuit, List<String> cuitsAlycs) {

        if (recipientCuit != null && cuitsAlycs != null) {

            String normalizedCuit = recipientCuit.replace("-", "");

            return cuitsAlycs.contains(normalizedCuit);

        } else {

            return false;
        }

    }

    public static String limpiarCUIT(String cuit) {
        return cuit.replace("-", "");
    }

}
