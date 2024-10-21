package ar.com.santanderrio.obp.servicios.gestiondecasos.connector;

import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models.InformationObpCardsDto;
import java.util.List;

public interface CasoSFServiceConnector {
    public List<InformationObpCardsDto> getObpCards();
}