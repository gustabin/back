package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Clase para consumir api de prestamos encolados
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PrestamoEncoladoDebitAccountEntity {

    @JsonProperty("ContractNumber")
    private String ContractNumber;

    @JsonProperty("Branch")
    private String Branch;

    @JsonProperty("Number")
    private String Number;

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setContractNumber(String contractNumber) {
        ContractNumber = contractNumber;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNroCuenta(){
        if (this.ContractNumber != null) {
            return  this.ContractNumber.substring(13);
        } else if (this.Number != null ) {
            return this.Number;
        }
        return "-";
    }

    public String getNroPrestamo() {
        if (this.ContractNumber != null) {
            int length = this.ContractNumber.length();
            return this.ContractNumber.substring(5, 8) + "-" + this.ContractNumber.substring(13, length - 1) + "/"
                    + this.ContractNumber.charAt(length - 1);


        } else if (this.Number != null && this.Branch != null) {
            int length = this.Number.length();
            return this.Branch + "-" + this.Number.substring(0, length - 1) + "/"
                    + this.Number.charAt(length - 1);
        }
        return "-";
    }
}