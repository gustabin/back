package ar.com.santanderrio.obp.servicios.microfrontend.bo;

public interface MicrofrontendsBO {

    /**
     * Obtiene los clientes que pueden utilizar premify.
     *
     * @param String nup
     * @param Boolean errValue
     * @return
     */
    Boolean consultaVisibilidadPorNup(String nup, String microFrontName, Boolean errValue);
}
