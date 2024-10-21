package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsResponseDTO;

/**
 * DAO para consumir los servicios de prestamos-bff
 *
 */
public interface PrestamosOBPBffDAO extends PrestamosBffDAO {

	PagoCuotaResponseDTO pagarCuota(String nup, PagoCuotaRequestDTO pagoCuotaRequestDTO) throws DAOException;

	CancelacionAnticipadaResponseDTO cancelarAnticipadamente(String nup,
			CancelacionAnticipadaRequestDTO cancelacionAnticipadaRequestDTO) throws DAOException;

	ValidateTokenSmsResponseDTO validarTokenSms(String nup, String token) throws DAOException;

}
