/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.CuentaOrigenRSADTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class AutentificacionDTOMock.
 *
 * @author florencia.n.martinez
 */
public class AutentificacionDTOMock {

    /** The Constant MSJ_SOFT_TOKEN_INPUT. */
    private static final String MSJ_SOFT_TOKEN_INPUT = " <p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>";

    /**
     * Completa desafio token.
     *
     * @return the autentificacion DTO
     */
    public static AutentificacionDTO completarDesafioToken() {
        NuevaRecargaRSADTO nuevaRecargaRSADTO = new NuevaRecargaRSADTO(1, Long.valueOf(200L),
                new CuentaOrigenRSADTO(
                        Long.valueOf(StringUtils.remove(
                                StringUtils.remove(StringUtils.remove("$ 32.456,78", "$").trim(), ".").trim(), ",")),
                        "023-123456/7", "Silvia Godoy"),
                "RECARGA CLARO", Boolean.TRUE, true);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO(TipoDesafioEnum.TOKEN,
                new TokenOperacionDTO(MSJ_SOFT_TOKEN_INPUT));
        autentificacionDTO.setRsaDTO(nuevaRecargaRSADTO);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));
        autentificacionDTO.setCodigoEstadisticaSolicitudBanelco("13419");
        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas("13417");
        autentificacionDTO.setCodigoEstadisticaSolicitudToken("13415");
        autentificacionDTO.setCodigoEstadisticaValidacionBanelco("13420");
        autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas("13418");
        autentificacionDTO.setCodigoEstadisticaValidacionToken("13416");
        return autentificacionDTO;
    }

}
