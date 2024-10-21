package ar.com.santanderrio.obp.servicios.debinapi.dto;


public enum RecurrenceActionEnum {
    ACTIVATE,
    DEACTIVATE,
    REACTIVATE,
    INACTIVATE_DEF,
    INACTIVATE_TEMP,
    CONFIRM,
    REJECT;

    public static RecurrenceActionEnum fromString(String text) {
        for (RecurrenceActionEnum r : RecurrenceActionEnum.values()) {
            if (r.toString().equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
    }
}