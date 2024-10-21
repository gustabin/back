package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.StringUtils;

public class GotoLinkMicrofront extends GotoLink{
    /**
     * Instantiates a new goto link.
     *
     * @param gotoLink the goto link
     */
    public GotoLinkMicrofront(String gotoLink) {
        super(gotoLink);
        this.link = gotoLink;
    }
}
