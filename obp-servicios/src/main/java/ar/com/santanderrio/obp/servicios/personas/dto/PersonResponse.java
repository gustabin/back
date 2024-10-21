package ar.com.santanderrio.obp.servicios.personas.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PersonResponse {

    @NotNull
    private List<Person> results;

    public List<Person> getResults () {
        return results;
    }

}
