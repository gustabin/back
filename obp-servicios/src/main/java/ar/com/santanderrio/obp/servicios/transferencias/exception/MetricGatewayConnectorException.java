package ar.com.santanderrio.obp.servicios.transferencias.exception;

public class MetricGatewayConnectorException extends Exception{

    public MetricGatewayConnectorException(String message){

        super(message);

    }

    public MetricGatewayConnectorException(Throwable ex) {

        super(ex);

    }
}
