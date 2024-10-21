package ar.com.santanderrio.obp.servicios.solicitudes.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.Form;
import java.util.List;

public class TransferiSueldoRequestDTO {

        @JsonProperty("typification")
        public String typification;
        @JsonProperty("resolutionType")
        public String resolutionType;
        @JsonProperty("forms")
        public List<Form> forms;

        public String getTypification() {
            return typification;
        }

        public void setTypification(String typification) { this.typification = typification; }

        public String getResolutionType() {
            return resolutionType;
        }

        public void setResolutionType(String resolutionType) { this.resolutionType = resolutionType; }

        public List<Form> getForms() {
            return forms;
        }

        public void setForms(List<Form> forms) { this.forms = forms; }

    }
