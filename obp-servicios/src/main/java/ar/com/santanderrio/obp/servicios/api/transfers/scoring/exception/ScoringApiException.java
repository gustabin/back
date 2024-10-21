package ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception;

public class ScoringApiException extends RuntimeException{

    public ScoringApiException(String message , Throwable e) {

        super(message, e);

    }

    public ScoringApiException(Throwable e) {

        super(e);

    }

}
