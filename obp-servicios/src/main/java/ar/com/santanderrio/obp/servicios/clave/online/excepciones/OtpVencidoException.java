package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

public class OtpVencidoException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public OtpVencidoException(String mje){
        super(mje);
    }
}
