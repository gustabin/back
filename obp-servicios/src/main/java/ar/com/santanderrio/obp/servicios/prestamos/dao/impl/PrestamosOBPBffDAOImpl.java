package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosOBPBffDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsResponseDTO;

@Component("prestamosObpBffDAO")
public class PrestamosOBPBffDAOImpl extends AbstractPrestamosBffDAOImpl implements PrestamosOBPBffDAO {

	@Value("${PRESTAMOS-OBP-BFF.LIQUIDACION.ENCOLAR:/api/v1/prestamos/encolar}")
	private String baseUriEncolar;

	@Value("${PRESTAMOS-OBP-BFF.LIQUIDACION.LIQUIDAR:/api/v1/prestamos/liquidar}")
	private String baseUriLiquidar;

	private static final String NOMBRE_WS = "PRESTAMOS-OBP-BFF";

	public PrestamosOBPBffDAOImpl() {
		super(NOMBRE_WS);
	}

	@Override
	protected String getBaseUriEncolar() {
		return baseUriEncolar;
	}

	@Override
	protected String getBaseUriLiquidar() {
		return baseUriLiquidar;
	}

	@Override
	public PagoCuotaResponseDTO pagarCuota(String nup, PagoCuotaRequestDTO pagoCuotaRequestDTO) throws DAOException {
		String uri = String.format("/api/v1/tenencias/%s/cuotas/%s", nup, pagoCuotaRequestDTO.getLoanNumber());
		return this.postBff(uri, pagoCuotaRequestDTO, PagoCuotaResponseDTO.class);
	}

	@Override
	public CancelacionAnticipadaResponseDTO cancelarAnticipadamente(String nup,
			CancelacionAnticipadaRequestDTO cancelacionAnticipadaRequestDTO) throws DAOException {
		String uri = String.format("/api/v1/tenencias/%s/cuotas/%s/cancelacion-anticipada/pago", nup,
				cancelacionAnticipadaRequestDTO.getLoanNumber());
		return this.postBff(uri, cancelacionAnticipadaRequestDTO, CancelacionAnticipadaResponseDTO.class);
	}

	@Override
	public ValidateTokenSmsResponseDTO validarTokenSms(String nup, String tokenSms) throws DAOException {
		ValidateTokenSmsRequestDTO validateTokenSmsRequestDTO = new ValidateTokenSmsRequestDTO(nup, tokenSms);
		return this.postBff("/api/v1/tokens/sms/validate", validateTokenSmsRequestDTO, ValidateTokenSmsResponseDTO.class);
	}

}
