package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;

/**
 * The Class CuentaDTOMock.
 *
 * @author florencia.n.martinez
 */
public final class CuentaDTOMock {

	/**
	 * Instantiates a new cuenta DTO mock.
	 */
	private CuentaDTOMock() {
		throw new IllegalAccessError("Clase para testing");
	}

	/**
	 * Completar info cta destino CC en Pesos.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCCPesos() {
		CuentaDTO ctaDTO = obtenerCtaDTO();
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaPesos());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCC());
		return ctaDTO;
	}

	/**
	 * Completar info cta destino CC pesos, numCTA y CBU.
	 *
	 * @param nroCuentaProdDestino the nro cuenta prod destino
	 * @param cBUDestino the c BU destino
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCCPesos(String nroCuentaProdDestino, String cBUDestino) {
		CuentaDTO ctaDTO = obtenerCtaDTO(nroCuentaProdDestino, cBUDestino);
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaPesos());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCC());
		return ctaDTO;
	}

	private static CuentaDTO obtenerCtaDTO(String nroCuentaProdDestino, String cBUDestino) {
		CuentaDTO cuenta = obtenerCtaDTO();
		cuenta.setNumero(nroCuentaProdDestino);
		cuenta.setNumeroCBU(cBUDestino);
		return cuenta;
	}

	/**
	 * Completar info cta destino CC dolares.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCCDolares() {
		CuentaDTO ctaDTO = obtenerCtaDTO();
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaDolares());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCC());
		return ctaDTO;
	}

	/**
	 * Completar info cta destino CA pesos.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCAPesos() {
		CuentaDTO ctaDTO = obtenerCtaDTO();
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaPesos());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCA());
		return ctaDTO;
	}

	/**
	 * Completar info cta destino CA dolares.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCADolares() {
		CuentaDTO ctaDTO = obtenerCtaDTO();
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaDolares());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCA());
		return ctaDTO;
	}

	/**
	 * Obtener cta DTO.
	 *
	 * @return the cuenta DTO
	 */
	private static CuentaDTO obtenerCtaDTO() {
		CuentaDTO ctaDTO = new CuentaDTO();
		ctaDTO.setNumero("0601364");
		ctaDTO.setNumeroCBU("0720000788000006013640");
		return ctaDTO;
	}

	/**
	 * Completar info cta destino CC pesos cta propia.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCCPesosCtaPropia() {
		CuentaDTO ctaDTO = new CuentaDTO();
		ctaDTO.setNumero("1234567");
		ctaDTO.setNumeroCBU("1234567890123456789012");
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaPesos());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCC());
		return ctaDTO;
	}

	/**
	 * Completar info cta destino CA dolares otros bancos.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCADolaresOtrosBancos() {
		CuentaDTO ctaDTO = obtenerCtaDTONoRio();
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaDolares());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoTipoCA());
		return ctaDTO;
	}

	/**
	 * Completar info cta destino C banelco dolares rio.
	 *
	 * @return the cuenta DTO
	 */
	public static CuentaDTO completarInfoCtaDestinoCBanelcoDolaresRio() {
		CuentaDTO ctaDTO = new CuentaDTO();
		ctaDTO.setNumero("0601364");
		ctaDTO.setNumeroCBU("0720000755000006013640");
		ctaDTO.setMoneda(MonedaDTOMock.completarInfoMonedaDolares());
		ctaDTO.setTipo(TipoCuentaDTOMock.completarInfoOtroTipo());
		return ctaDTO;
	}

	/**
	 * Obtener cta DTO no rio.
	 *
	 * @return the cuenta DTO
	 */
	private static CuentaDTO obtenerCtaDTONoRio() {
		CuentaDTO ctaDTO = new CuentaDTO();
		ctaDTO.setNumero("2234567");
		ctaDTO.setNumeroCBU("5434567890123456789012");
		return ctaDTO;
	}
}
