package ar.com.santanderrio.obp.servicios.echeq.utils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Aval;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import org.apache.commons.collections.Predicate;

import java.util.List;

public class AvalEcheqUtils {

    private AvalEcheqUtils(){
        // Do nothing
    }

    public static Aval getAvalByFilter(List<Aval> avales, Predicate filter) {
        for(Aval aval : avales) {
            if(filter.evaluate(aval)) {
                return aval;
            }
        }
        return null;
    }

    public static Aval getAvalByCuitCliente(Cheque cheque, Cliente cliente) {
        final String cuitCliente = cliente.getNumeroCUILCUIT().replace("-","");
        Predicate filter =  new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return cuitCliente.equals(((Aval) object).getAvalDocumento());
            }
        };
        return AvalEcheqUtils.getAvalByFilter(cheque.getAvalistas(), filter);
    }

    public static String mapClientName(Cliente cliente) {
        StringBuilder name = new StringBuilder(cliente.getNombre());
        name.append(" ")
            .append(cliente.getApellido1())
            .append(" ")
            .append(cliente.getApellido2());
        return name.toString().trim();
    }
}
