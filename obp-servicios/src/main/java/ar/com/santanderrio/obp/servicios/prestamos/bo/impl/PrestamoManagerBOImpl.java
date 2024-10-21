package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionRequest;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionRequestPreAcordado;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionRequestPreAprobado;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTokenDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTransactionDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosOBPBffDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class PrestamoManagerBOImpl implements PrestamoManagerBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoManagerBOImpl.class);

    @Autowired
    private SesionCliente sesionCliente;

    @Autowired
    private PrestamosOBPBffDAO prestamosObpBffDAO;
    
    @Autowired
    private PrestamoTransactionDAO prestamoTransactionDAO;
    
    private static final String PREAPROBADO_LOAN_TYPE = "PREAPROBADO";
    private static final String PREACORDADO_LOAN_TYPE = "PREACORDADO";

	@Override
	public boolean isValidToken(String token) {
		if (StringUtils.isEmpty(token))
			return false;
		try {
			return prestamosObpBffDAO.validarTokenSms(sesionCliente.getCliente().getNup(), token).getValid();
		} catch (GenericRestException e) {
			LOGGER.error("Error al validar el token sms - error_code {} - SID {} - CID {}",
					e.getResponseErrorDTO().getCode(), PrestamosUtils.getSessionId(),
					PrestamosUtils.getCorrelationId());
			return false;
		} catch (Exception e) {
			LOGGER.error("Error al validar el token sms - SID {} - CID {}", PrestamosUtils.getSessionId(),
					PrestamosUtils.getCorrelationId());
			return false;
		}
	}

	@Override
	public void enqueueKafka(Object body, String customer) {
		TransactionRequest transaction = body instanceof PrestamoInEntity ?
				generateBodyPreAcordado((PrestamoInEntity) body, customer) :
				generateBodyPreAprobado((PrestamoPreaprobadoMonoproductoInEntity) body);
		try {
			this.prestamoTransactionDAO.postEnqueue(transaction);
		} catch (Exception e) {
			LOGGER.error("Error al encolar en kafka: {}", e.getMessage());
		}
	}

	@Override
	public void dequeueKafka(String loanType, String customer, boolean fueraDeHorario) {
		TransactionRequest transaction = generateBodyDequeue(loanType, customer, fueraDeHorario);
		try {
			this.prestamoTransactionDAO.putDequeue(transaction);
		} catch (Exception e) {
			LOGGER.error("Error al desencolar en kafka: {}", e.getMessage());
		}
	}
	
	public TransactionRequest generateBodyPreAcordado(PrestamoInEntity body, String customer) {
		TransactionRequestPreAcordado transaction = new TransactionRequestPreAcordado();
		transaction.canalOrigen = "OBP";
		transaction.linea = PREACORDADO_LOAN_TYPE;
		transaction.codigoProducto = Integer.parseInt(body.getCodProductoUG());
		transaction.codigoSubproducto = Integer.parseInt(body.getCodSubpUG());
		transaction.destinoFondos = Integer.parseInt(body.getDestFondosUG());
		transaction.cantidadCuotas = Integer.parseInt(body.getCantidadCuotas());
		
		BigInteger importeInInt = new BigInteger(body.getImportePrestamo());
	    transaction.montoSolicitado = new BigDecimal(importeInInt, 2);
	    
		transaction.nroCliente = customer;
		transaction.divisa = body.getCodDivisaO();
		
		transaction.fechaPrimerVto = body.getFechaPrimerCuota().substring(0, 4)+"-"+body.getFechaPrimerCuota().substring(4, 6)+"-"+body.getFechaPrimerCuota().substring(6, 8);
		
		transaction.tipoTasa = body.getTipoTasa();
		
		BigInteger tasaInInt = new BigInteger(body.getValorTasa());
	    transaction.tipAplicacion = new BigDecimal(tasaInInt, 6);
	    
		transaction.tipCuentaVin  = body.getTipoCuenta();
		transaction.sucCuentaVin = body.getSucursalCuenta();
		transaction.nroCuentaVin = body.getNumeroCuenta();
		transaction.sucursalPaquete = body.getSucPaquete();
		transaction.cuentaPaquete = body.getNumPaquete();
		transaction.tipoPoliza = body.getTpoPolizaDs();
		transaction.tipoRiesgo = body.getTpoRiesgoDs();
		transaction.lineaUva = body.getIndLineaUVA();
		
		return transaction;		
	}
	public TransactionRequest generateBodyPreAprobado(PrestamoPreaprobadoMonoproductoInEntity body) {
		TransactionRequestPreAprobado transaction = new TransactionRequestPreAprobado();
		transaction.canalOrigen = "OBP";
		transaction.linea = PREAPROBADO_LOAN_TYPE;
		transaction.codigoProducto = Integer.parseInt(body.getProducto());
		transaction.codigoSubproducto = Integer.parseInt(body.getSubproProp());
		transaction.destinoFondos = Integer.parseInt(body.getCodDestino().substring(9));
		transaction.cantidadCuotas = Integer.parseInt(body.getCuotasProp());
		transaction.oficinaTitular = body.getOficTitular();
		transaction.oficinaElevadora = body.getOficElevadora();
		transaction.oficinaPresentadora = "";
		transaction.montoSolicitado = body.getMontoSolic();

		transaction.cuentaDestinoALiquidar = body.getCccAbo();
		transaction.nroCliente = body.getNroCliente();
		transaction.divisa = body.getDivisa();
		transaction.codigoInstrumento = "S";
		transaction.fechaAprobacion = body.getFecAprobacion();
		transaction.fechaFormalizacion = body.getFecFormalizacion();
		transaction.fechaPrimerVto = body.getFecPrimerVto();
		transaction.tipoTasa = body.getTipTasa();
		transaction.indiceNegociable = body.getIndNegociabil();
		
		transaction.tipAplicacion = body.getTipAplicacion().setScale(6);
		
		transaction.indBonifcta = body.getIndBonifcta();
		transaction.nroContratoVin  = body.getCccVin();
		return transaction;		
	}
	
	public TransactionRequest generateBodyDequeue(String loanType, String customer, boolean fueraDeHorario) {
		TransactionRequest transaction = new TransactionRequest();
		transaction.linea = loanType;
		transaction.nroCliente = customer;
		transaction.toPayOff = fueraDeHorario;
		return transaction;
	}
}
