package ar.com.santanderrio.obp.servicios.api.transfers.recipients.exception;

public class RecipientsApiException extends RuntimeException{

    public RecipientsApiException(String message , Throwable e) {

        super(message, e);

    }

    public RecipientsApiException(Throwable e) {

        super(e);

    }

}
