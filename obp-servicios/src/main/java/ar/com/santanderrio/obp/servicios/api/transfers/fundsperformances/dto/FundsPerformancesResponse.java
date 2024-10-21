package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto;

import java.util.List;

public class FundsPerformancesResponse {

    private int total;
    private List<FundResult> results;
    private String currentDate;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FundResult> getResults() {
        return results;
    }

    public void setResults(List<FundResult> results) {
        this.results = results;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public static class FundResult {
        private FundInfo fund;
        private String currency;
        private String performanceDate;
        private double currentShareValue;
        private String equityDate;
        private String priceType;
        private double aum;
        private String lastDay;
        private String last30Days;
        private String last90Days;
        private String last12Months;
        private String annualizedLastDay;
        private String annualizedLast30Day;
        private String annualizedLast90Days;
        private String annualizedLast365Days;
        private String monthToDay;
        private String yearToDay;
        private String annualizedMonthToDay;
        private String annualizedYearToDay;
        private String last7Days;
        private String annualizedLast7Days;

        public FundInfo getFund() {
            return fund;
        }

        public void setFund(FundInfo fund) {
            this.fund = fund;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPerformanceDate() {
            return performanceDate;
        }

        public void setPerformanceDate(String performanceDate) {
            this.performanceDate = performanceDate;
        }

        public double getCurrentShareValue() {
            return currentShareValue;
        }

        public void setCurrentShareValue(double currentShareValue) {
            this.currentShareValue = currentShareValue;
        }

        public String getEquityDate() {
            return equityDate;
        }

        public void setEquityDate(String equityDate) {
            this.equityDate = equityDate;
        }

        public String getPriceType() {
            return priceType;
        }

        public void setPriceType(String priceType) {
            this.priceType = priceType;
        }

        public double getAum() {
            return aum;
        }

        public void setAum(double aum) {
            this.aum = aum;
        }

        public String getLastDay() {
            return lastDay;
        }

        public void setLastDay(String lastDay) {
            this.lastDay = lastDay;
        }

        public String getLast30Days() {
            return last30Days;
        }

        public void setLast30Days(String last30Days) {
            this.last30Days = last30Days;
        }

        public String getLast90Days() {
            return last90Days;
        }

        public void setLast90Days(String last90Days) {
            this.last90Days = last90Days;
        }

        public String getLast12Months() {
            return last12Months;
        }

        public void setLast12Months(String last12Months) {
            this.last12Months = last12Months;
        }

        public String getAnnualizedLastDay() {
            return annualizedLastDay;
        }

        public void setAnnualizedLastDay(String annualizedLastDay) {
            this.annualizedLastDay = annualizedLastDay;
        }

        public String getAnnualizedLast30Day() {
            return annualizedLast30Day;
        }

        public void setAnnualizedLast30Day(String annualizedLast30Day) {
            this.annualizedLast30Day = annualizedLast30Day;
        }

        public String getAnnualizedLast90Days() {
            return annualizedLast90Days;
        }

        public void setAnnualizedLast90Days(String annualizedLast90Days) {
            this.annualizedLast90Days = annualizedLast90Days;
        }

        public String getAnnualizedLast365Days() {
            return annualizedLast365Days;
        }

        public void setAnnualizedLast365Days(String annualizedLast365Days) {
            this.annualizedLast365Days = annualizedLast365Days;
        }

        public String getMonthToDay() {
            return monthToDay;
        }

        public void setMonthToDay(String monthToDay) {
            this.monthToDay = monthToDay;
        }

        public String getYearToDay() {
            return yearToDay;
        }

        public void setYearToDay(String yearToDay) {
            this.yearToDay = yearToDay;
        }

        public String getAnnualizedMonthToDay() {
            return annualizedMonthToDay;
        }

        public void setAnnualizedMonthToDay(String annualizedMonthToDay) {
            this.annualizedMonthToDay = annualizedMonthToDay;
        }

        public String getAnnualizedYearToDay() {
            return annualizedYearToDay;
        }

        public void setAnnualizedYearToDay(String annualizedYearToDay) {
            this.annualizedYearToDay = annualizedYearToDay;
        }

        public String getLast7Days() {
            return last7Days;
        }

        public void setLast7Days(String last7Days) {
            this.last7Days = last7Days;
        }

        public String getAnnualizedLast7Days() {
            return annualizedLast7Days;
        }

        public void setAnnualizedLast7Days(String annualizedLast7Days) {
            this.annualizedLast7Days = annualizedLast7Days;
        }

        public static class FundInfo {
            private int id;
            private String href;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }
    }
}
