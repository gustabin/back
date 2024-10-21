package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPreaprobadoMonoproductoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;

/**
 * Clase relacionada al servicio ALTFOPMOPE
 * @author A309331
 *
 */
@Component
public class PrestamoPreaprobadoMonoproductoDAOImpl extends IatxBaseDAO implements PrestamoPreaprobadoMonoproductoDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoPreaprobadoMonoproductoDAOImpl.class);
	
	private static final int OK_CODIGO_RETORNO = 0;
	private static final String SERVICE_NAME = "ALTFOPMOPE";
	private static final String SERVICE_VERSION = "120";

	/** The Constant CODIGO_ERROR_VALID_TOKEN. */
	public static final String CODIGO_ERROR_VALID_TOKEN = "002";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	@Override
	public PrestamoPreaprobadoMonoproductoOutEntity altaPrestamoPreabrobadoMonoproducto(
			PrestamoPreaprobadoMonoproductoInEntity pPreaprobadoMonoInEntity, Cliente cliente) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(SERVICE_NAME, SERVICE_VERSION);
		try {
			IatxRequestData iatxRequestData = generateRequestData(pPreaprobadoMonoInEntity, cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				PrestamoPreaprobadoMonoproductoOutEntity respuesta = processTrama(iatxResponse.getTrama(), PrestamoPreaprobadoMonoproductoOutEntity.class);
				respuesta.setNroComprobante(iatxResponse.getNroComprobante());
				return respuesta;
			} else {
				throw new DAOException("Error al consultar servicio ALTFOPMOPE, codigo de error:  ", String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			if (e.getClass().equals(DAOException.class)) {
				LOGGER.error("Error consultando prestamos.");
				throw (DAOException) e;
			} else {
				LOGGER.error(e.getMessage(), e);
			}
			throw new DAOException(e);
		}
	}
	
	/**
	 * Obtiene el request data para llamar al servicio
	 * @param pPreaprobadoMonoInEntity
	 * @param cliente
	 * @return
	 */
	private IatxRequestData generateRequestData(PrestamoPreaprobadoMonoproductoInEntity pPreaprobadoMonoInEntity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNumProp(), 20)); // Num_Prop A20
		iatxRequestData.addBodyValue(pPreaprobadoMonoInEntity.getProducto()); // Producto A02
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getSubproProp(), 4, "0")); // Subpro_Prop A04
		iatxRequestData.addBodyValue(StringUtils.right(pPreaprobadoMonoInEntity.getCodDestino(), 5)); // Cod_Destino A05
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCuotasProp(), 5, "0")); // Cuotas_Prop N05
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getOficTitular(), 4)); // Ofic_Titular A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getOficElevadora(), 4)); // Ofic_Elevadora A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getOficPresentadora(), 4)); // Ofic_Presentadora A04
		iatxRequestData.addBodyValue(ISBANStringUtils.ajustadorBigDecimalIatx(pPreaprobadoMonoInEntity.getMontoSolic(), 17, 4)); // Monto_Solic N17,4
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCccVin(), 20)); // Ccc_Vin  A20
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCccAbo(), 20)); // Ccc_Abo A20
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCliente(), 8)); // Nro_Cliente A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCotitular1(), 8)); // Nro_Cotitular1 A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCotitular2(), 8)); // Nro_Cotitular2 A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCotitular3(), 8)); // Nro_Cotitular3 A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCotitular4(), 8)); // Nro_Cotitular4 A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getNroCotitular5(), 8)); // Nro_Cotitular5 A08
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getDivisa(), 3)); // Divisa A03
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCanalVenta(), 2)); // Canal_Venta A02
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getOficialventa(), 4)); // Oficial_venta A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getOficialComercial(), 4)); // Oficial_Comercial A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCodInstrumto(), 1)); // Cod_Instrumto A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getFecAprobacion(), 10)); // Fec_Aprobacion A10
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getFecFormalizacion(), 10)); // Fec_Formalizacion A10
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getFecValor(), 10)); // Fec_Valor A10
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getFecPrimerVto(), 10)); // Fec_Primer_Vto A10
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getDiaVto(), 2)); // Dia_Vto  A02
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getTipTasa(), 1)); // Tip_Tasa A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getIndNegociabil(), 1)); // Ind_Negociabil A01
		iatxRequestData.addBodyValue(ISBANStringUtils.ajustadorBigDecimalIatx(pPreaprobadoMonoInEntity.getTipAplicacion(), 9, 6)); // Tip_Aplicacion N9,6
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getClarevis(), 3)); // Clarevis A03
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getModAcreditac(), 1)); // Mod_Acreditac A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getTipCondicAlternat(), 1)); // Tip_Condic_Alternat A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCodCondAlternat(), 15)); // Cod_Cond_Alternat A15
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCodAgen(), 4)); // Cod_Agen A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getIndContSeg(), 1)); // Ind_Cont_Seg A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCodCiaSeg(), 4)); // Cod_Cia_Seg A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getIndBonifcta(), 1)); // Ind_Bonif_cta A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCodRiesgo(), 1)); // Cod_Riesgo A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getIndIndexado(), 1)); // Ind_Indexado A01
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCoeficiIndex(), 4)); // Coefici_index A04
		iatxRequestData.addBodyValue(StringUtils.leftPad(pPreaprobadoMonoInEntity.getCoeficiVisual(), 4)); // Coefici_Visual A04
		iatxRequestData.addBodyValue(StringUtils.left(pPreaprobadoMonoInEntity.getFase(), 1)); // Fase A01
		return iatxRequestData; 
	}	
	

}
