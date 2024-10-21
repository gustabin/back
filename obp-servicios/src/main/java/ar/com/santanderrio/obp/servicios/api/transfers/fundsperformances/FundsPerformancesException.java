package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

public class FundsPerformancesException extends RuntimeException{

    public FundsPerformancesException(String message, Throwable e) {

        super(message, e);
    }

    public FundsPerformancesException(Throwable e) {

        super(e);
    }
}
