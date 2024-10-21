package ar.com.santanderrio.obp.servicios.api.funds;

public class FundsApiConstants {

    private FundsApiConstants() {
    }

    public static class Paths {

        private Paths() {
        }

        public static final String REDEMPTIONS_BFF = "REDEMPTIONS_BFF";
        public static final String HOLDINGS_BFF = "HOLDINGS_BFF";
        public static final String PYL_SERVICE_CACHE = "PYL_SERVICE_CACHE";
        public static final String HOLDINGS_CACHE = "HOLDINGS_CACHE";
    }

    public static class Bouncer {

        private Bouncer() {
        }

        public static final String ACCESS = "/access";
        public static final String ACCESS_SUMMARY = "/access-summary";
    }

}
