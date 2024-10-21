package ar.com.santanderrio.obp.servicios.prestamos.entity;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;

public class GenericRestException extends DAOException {

	private static final long serialVersionUID = 1L;

	private GenericRestResponseDto responseErrorDTO;

	public GenericRestException(GenericRestResponseDto responseDTO) {
		super(responseDTO.getMessage());
		this.setResponseErrorDTO(responseDTO);
	}

	public GenericRestResponseDto getResponseErrorDTO() {
		return responseErrorDTO;
	}

	public void setResponseErrorDTO(GenericRestResponseDto responseErrorDTO) {
		this.responseErrorDTO = responseErrorDTO;
	}

}
