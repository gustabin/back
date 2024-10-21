package ar.com.santanderrio.obp.servicios.prestamos.bo;

public interface PrestamoManagerBO {

    boolean isValidToken(String token);
    
    void enqueueKafka(Object body, String customer);

    void dequeueKafka(String loanType, String customer, boolean fueraDeHorario);
    
}
