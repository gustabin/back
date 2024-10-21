package ar.com.santanderrio.obp.servicios.campaniabeneficios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

/**
 * The Interface CampaniaBeneficiosBO.
 */
public interface CampaniaBeneficiosBO {

    /**
     * Suscripcion campania beneficios.
     *
     * @param idcampania
     *            the idcampania
     * @param respuesta
     *            the respuesta
     * @param programa
     *            the programa
     * @param comentarios
     *            the comentarios
     * @return the respuesta
     */
    Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> suscripcionCampaniaBeneficios(SuscCampaniaBeneficiosInDTO inDTO);

    /**
     * Consulta Suscripcion campania beneficios.
     *
     * @param idcampania
     *            the idcampania
     * @return the respuesta
     */
    Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> consSuscripcionCampaniaBeneficios(
            SuscCampaniaBeneficiosInDTO inDTO);

    /**
     * Informar gestion AC.
     *
     * @param cliente the cliente
     * @param ofertaComercialDTO the oferta comercial DTO
     * @return the boolean
     */
	Boolean informarGestionAC(Cliente cliente, OfertaComercialDTO ofertaComercialDTO);
}
