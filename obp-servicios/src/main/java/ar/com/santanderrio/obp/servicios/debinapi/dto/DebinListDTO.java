package ar.com.santanderrio.obp.servicios.debinapi.dto;

import java.util.List;

public class DebinListDTO {
    private List<DebinDetailDTO> debins;

    private int totalPages;

    public List<DebinDetailDTO> getDebins() {
        return debins;
    }

    public void setDebins(List<DebinDetailDTO> debins) {
        this.debins = debins;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
