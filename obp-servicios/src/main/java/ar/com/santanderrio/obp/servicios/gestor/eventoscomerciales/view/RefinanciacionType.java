package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;


import static ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes.*;
import static ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes.CUOTAPHONE_AYUDA;

public enum RefinanciacionType {

    EARLY_CARD(EARLY_CARD_LEGAL,
               EARLY_CARD_AYUDA,
               EARLY_CARD_TYC,
               EARLY_CARD_DESCRIPCION_,
               EARLY_TITULO_MSJ_CONFIRMACION,
               EARLY_MSJ_CONFIRMACION,
               EARLY_BULLETS,
            EARLY_TITULO),
    CUOTAPHONE(CUOTAPHONE_LEGAL,
            CUOTAPHONE_AYUDA,
            CUOTAPHONE_TYC,
            CUOTAPHONE_DESCRIPCION,
            CUOTAPHONE_TITULO_MSJ_CONFIRMACION,
            CUOTAPHONE_MSJ_CONFIRMACION,
            CUOTAPHONE_BULLETS,
            CUOTAPHONE_TITULO);

    RefinanciacionType(String legalCode, String ayudaCode, String tycCode, String masInfoCode, String tituloMsjConfirmacionCode, String msjConfirmacionCode, String bulletCode, String titulo){
        this.legalCode = legalCode;
        this.ayudaCode = ayudaCode;
        this.tycCode = tycCode;
        this.masInfoCode = masInfoCode;
        this.bulletCode = bulletCode;
        this.msjConfirmacionCode = msjConfirmacionCode;
        this.tituloMsjConfirmacionCode = tituloMsjConfirmacionCode;
        this.tituloCode = titulo;
    }

    private String legalCode;
    private String tituloMsjConfirmacionCode;
    private String msjConfirmacionCode;
    private String ayudaCode;
    private String tycCode;
    private String masInfoCode;
    private String bulletCode;
    private String tituloCode;


    public String getLegalCode() {
        return legalCode;
    }

    public String getAyudaCode() {
        return ayudaCode;
    }

    public String getTycCode() {
        return tycCode;
    }

    public String getMasInfoCode() {
        return masInfoCode;
    }

    public String getBulletCode() {
        return bulletCode;
    }

    public String getMsjConfirmacionCode() {
        return msjConfirmacionCode;
    }

    public String getTituloMsjConfirmacionCode() {
        return tituloMsjConfirmacionCode;
    }

    public String getTituloCode() {
        return tituloCode;
    }
}
