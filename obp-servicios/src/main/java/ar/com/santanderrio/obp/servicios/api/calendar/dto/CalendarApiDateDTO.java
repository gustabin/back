package ar.com.santanderrio.obp.servicios.api.calendar.dto;


public class CalendarApiDateDTO {

    private String date;

    private boolean enabled;

    public CalendarApiDateDTO() {
    }

    public CalendarApiDateDTO(String date, boolean enabled) {
        this.date = date;
        this.enabled = enabled;
    }

    public String getDate() {return date;  }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
