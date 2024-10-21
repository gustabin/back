package ar.com.santanderrio.obp.servicios.api.accounts.entities;

import java.util.List;

public class PackagesResponseEntity {

    private List<PackagesEntity> packages ;

    public List<PackagesEntity> getPackages() {
        return packages;
    }

    public void setPackages(List<PackagesEntity> packages) {
        this.packages = packages;
    }

}
