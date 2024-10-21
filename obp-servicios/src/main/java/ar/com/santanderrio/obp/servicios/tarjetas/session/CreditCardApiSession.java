package ar.com.santanderrio.obp.servicios.tarjetas.session;

import ar.com.santanderrio.obp.servicios.tarjetas.session.model.CreditCardApiIds;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreditCardApiSession {

    private final Map<String, CreditCardApiIds> map = new HashMap<String, CreditCardApiIds>();

    public CreditCardApiIds findSession(String number) {
        return map.get(number);
    }

    public String findNumberByCardId(String id) {
        for (Map.Entry<String, CreditCardApiIds> entry : map.entrySet()) {
            if (entry.getValue().getCreditCardId().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String findNumberByAccountId(String id) {
        for (Map.Entry<String, CreditCardApiIds> entry : map.entrySet()) {
            if (entry.getValue().getCreditAccountId().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void save(String number, String creditCardId, String creditAccountId) {
        if (map.get(number) == null) {
            CreditCardApiIds session = new CreditCardApiIds();
            session.setCreditCardId(creditCardId);
            session.setCreditAccountId(creditAccountId);
            map.put(number, session);
        }
    }

}
