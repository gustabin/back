package ar.com.santanderrio.obp.servicios.api.accounts.entities;

import java.util.List;

public class PackagesEntity {

    private String mainAccountId;            //": "007000701149",
    private List<String> associatedAccounts; //: ["007000701149" ],
    private String id;                       //: "090000000071",
    private boolean isCompanyPackage;        //": false,
    private String productCode;             //": "90",
    private String subProductCode;         //": "0355",
    private String branchCode;             //": "0212",
    private String status;                 //": "ACTIVE"

    public String getMainAccountId(){
        return mainAccountId;
    }

    public void setMainAccountId(String mainAccountId){
        this.mainAccountId = mainAccountId;
    }

    public List<String> getAssociatedAccounts(){
        return associatedAccounts;
    }

    public void setAssociatedAccounts(List<String> associatedAccounts){
        this.associatedAccounts = associatedAccounts;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getIsCompanyPackage(){
        return id;
    }

    public void setIsCompanyPackage(boolean isCompanyPackage){
        this.isCompanyPackage = isCompanyPackage;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getSubProductCode(){
        return subProductCode;
    }

    public void setSubProductCode(String subProductCode){
        this.subProductCode = subProductCode;
    }
    public String getBranchCode(){
        return branchCode;
    }

    public void setBranchCode(String branchCode){
        this.branchCode = branchCode;
    }
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }


}