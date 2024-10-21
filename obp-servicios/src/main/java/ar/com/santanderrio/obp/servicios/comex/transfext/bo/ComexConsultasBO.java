/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.bo;

import java.sql.SQLException;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaPaisesOutDTO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * Interface ComexConsultasBO.
 *
 * @author B040619
 */
public interface ComexConsultasBO {

	/**
	 * Consulta paises.
	 *
	 * @return Lista paises
	 */
	Respuesta<List<ConsultaPaisesOutDTO>> consultaPaises();

	/**
	 * Consulta monedas.
	 *
	 * @return Lista monedas
	 */
	Respuesta<List<ConsultaMonedaOutDTO>> consultaMonedas();

	/**
	 * Consulta bancos segun codigo swift o aba.
	 *
	 * @param consultaBancosInDTO
	 *            the consulta bancos in DTO
	 * @return Lista bancos
	 */
	Respuesta<List<ConsultaBancosOutDTO>> consultaBancos(ConsultaBancosInDTO consultaBancosInDTO);

	/**
	 * Consulta cuit.
	 *
	 * @param cliente
	 *            the cliente
	 * @return Consulta padron cuit out entity
	 */
	Respuesta<PadronOutEntity> getDatosPadronBO(Cliente cliente);

	/**
	 * Obtiene el comprobante.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return Reporte
	 */
	Respuesta<Reporte> obtenerComprobante(ComprobanteComexDTO comprobante);

	/**
	 * verifica archivos.
	 *
	 * @param adjuntarArchivosInDto
	 *            the adjuntar archivos in dto
	 * @return Reporte
	 */
	Respuesta<Boolean> verificarArchivo(AdjuntarArchivosDTO adjuntarArchivosInDto);


	


	
	/**
	 * Limpiar-refrescar los paises en el cache.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> limpiarPaises();
	
	/**
	 * Limpiar-refrescar las monedas en el cache.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> limpiarMonedas();

	/**
	 * Get cuentas saldo ordenadas BP.
	 *
	 * @param cliente the cliente
	 * @return the cuentas saldo ordenadas BP
	 */
	Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadasBP(Cliente cliente) throws BusinessException, SQLException, ImporteConvertException;

}
