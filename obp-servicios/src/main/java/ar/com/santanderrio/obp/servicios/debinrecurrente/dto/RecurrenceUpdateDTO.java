package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import ar.com.santanderrio.obp.servicios.debinapi.dto.OperationMode;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize
public class RecurrenceUpdateDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("authorCuit")
    String authorCuit;

    @JsonProperty("recurrenceAction")
    String recurrenceAction;

    @JsonProperty("operationMode")
    OperationMode operationMode;

    public String getAuthorCuit() {
        return authorCuit;
    }

    public void setAuthorCuit(String authorCuit) {
        this.authorCuit = authorCuit;
    }

    public String getRecurrenceAction() {
        return recurrenceAction;
    }

    public void setRecurrenceAction(String recurrenceId) {
        this.recurrenceAction = recurrenceId;
    }

    public OperationMode getOperationMode() { return operationMode; }

    public void setOperationMode(OperationMode operationMode) { this.operationMode = operationMode; }
}
