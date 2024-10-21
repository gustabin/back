/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteCompraBO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.RequestBancaPrivadaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class OperacionClienteCompraBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class OperacionClienteCompraBOImpl extends CompraVentaDolaresBOImpl implements OperacionClienteCompraBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionClienteCompraBOImpl.class);

	/** The OperacionCompraDAO. */
	@Autowired
	private OperacionClienteCompraDAO operacionCompraDAO;
	
	/** The OperacionBancaPrivadaDAO. */
	@Autowired
	private OperacionBancaPrivadaDAO operacionBancaPrivadaDAO;
	
	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Obtiene la respuesta con los datos de la simuacion cuando Cliente Compra
	 * Dolares.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaDTO> operacionClienteCompra(ParametrosOperacion parametrosOperacion)
			throws BusinessException {
		try {
			OperacionCompraVentaDatosEntrada datosEntrada = crearDatosEntradaClienteCompra(parametrosOperacion);
			return obtenerRespuestaClienteCompra(parametrosOperacion, datosEntrada);
		} catch (CompraVentaDolaresException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		} catch (BusinessException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		} catch (NullPointerException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		}
	}

	/**
	 * CPVTADOLCN 110 Realiza operaciones de venta (Banco Vende / Cliente
	 * Compra) de dólares.
	 *
	 * @param parametros
	 *            the parametros operacion
	 * @return the simulacion compra venta dolar dato entrada
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 * @throws BusinessException
	 *             the business exception
	 */
	private OperacionCompraVentaDatosEntrada crearDatosEntradaClienteCompra(ParametrosOperacion parametros)
			throws CompraVentaDolaresException, BusinessException {
		OperacionCompraVentaDatosEntrada operacionCompraVentaDatosEntrada = new OperacionCompraVentaDatosEntrada();
		operacionCompraVentaDatosEntrada.setAplicacionPesos(
				this.getCompraVentaDolaresUtil().obtenerAplicacionPesos(parametros.getCuentaOrigen()));
		operacionCompraVentaDatosEntrada.setSucursalctapesos(
				this.getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaOrigen()));
		operacionCompraVentaDatosEntrada.setNumeroctapesos(
				this.getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaOrigen()));
		operacionCompraVentaDatosEntrada.setAplicacionDolar(this.getCompraVentaDolaresUtil()
				.obtenerAplicacionDolar(parametros.getCuentaOrigen(), CompraVentaStringUtil.OPERACION_COMPRA));
		operacionCompraVentaDatosEntrada.setSucursalCtaDolar(
				this.getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaDestino()));
		operacionCompraVentaDatosEntrada.setNumeroCtaDolar(
				this.getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaDestino()));
		operacionCompraVentaDatosEntrada.setIndtuatesora(this.getCompraVentaDolaresUtil().obtenerIndTuAtesora());
		operacionCompraVentaDatosEntrada.setFechavalor(this.getCompraVentaDolaresUtil().obtenerFechVal());
		operacionCompraVentaDatosEntrada
				.setConcepdebito(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.CUATRO));
		operacionCompraVentaDatosEntrada
				.setConcepcred(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.CUATRO));
		operacionCompraVentaDatosEntrada.setCodigodebi(this.getCompraVentaDolaresUtil().obtenerCodigoDebi());
		operacionCompraVentaDatosEntrada.setCodigocred(this.getCompraVentaDolaresUtil().obtenerCodigoCre());
		operacionCompraVentaDatosEntrada.setImportedeb(StringUtils.replace(sesionParametros.getCompraDolaresImpcarg(), "+", ""));
		operacionCompraVentaDatosEntrada.setImportecred(
				this.getCompraVentaDolaresUtil().obtenerImporteCredOperacionCompra(parametros.getImporteCredito()));
		operacionCompraVentaDatosEntrada
				.setImportecoti(this.getCompraVentaDolaresUtil().obtenerImporteCoti(parametros.getCotizacion().replace(".","")));
		operacionCompraVentaDatosEntrada
				.setIndiccompravta(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.UNO));
		operacionCompraVentaDatosEntrada
				.setAutorizafip(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.VEINTE));
		operacionCompraVentaDatosEntrada
				.setTipocambio(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.TRES));
		operacionCompraVentaDatosEntrada
				.setNumbolecvta(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.OCHO));
		operacionCompraVentaDatosEntrada.setCodtributa(this.getCompraVentaDolaresUtil().obtenerCodTributa());
		operacionCompraVentaDatosEntrada
				.setFecingrpais(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.OCHO));
		operacionCompraVentaDatosEntrada
				.setNomapell(this.getCompraVentaDolaresUtil().obtenerNomApell(parametros.getCliente()));
		operacionCompraVentaDatosEntrada
				.setMarcaempleado(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.UNO));
		operacionCompraVentaDatosEntrada.setPaisemisdoc("000");
		operacionCompraVentaDatosEntrada.setPaisnac("000");
		operacionCompraVentaDatosEntrada.setCodigoconcepto(this.getCompraVentaDolaresUtil().obtenerCodigoConcepto());
		operacionCompraVentaDatosEntrada
				.setTpodocbcra(this.getCompraVentaDolaresUtil().obtenerDocBcra(parametros.getNupTipo()));
		operacionCompraVentaDatosEntrada.setNrodocbcra(parametros.getNupNumDoc());
		operacionCompraVentaDatosEntrada
				.setTiprefafip(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.TRES));
		operacionCompraVentaDatosEntrada
				.setNiocuentas(this.getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.VEINTICUATRO));
		return operacionCompraVentaDatosEntrada;
	}

	/**
	 * Carga el objeto que tiene los datos de la respuesta para el paso tres y
	 * cuatro.
	 *
	 * @param entity
	 *            the entity
	 * @param parametros
	 *            the parametros
	 * @return the comprobante compra venta DTO
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private ComprobanteCompraVentaDTO crearDatosResponseClienteCompra(OperacionClienteCompraEntity entity,
			ParametrosOperacion parametros) throws CompraVentaDolaresException {
		ComprobanteCompraVentaDTO comprobanteCompra = new ComprobanteCompraVentaDTO();
		comprobanteCompra
				.setImporteDolarAcreditado(ISBANStringUtils.formatearSaldoString(parametros.getImporteCredito()));
		Cuenta cuentaOrigen = parametros.getCuentaOrigen();
		comprobanteCompra.setCuentaOrigenNumero(
				new IdentificacionCuenta(cuentaOrigen.getNroSucursal(), cuentaOrigen.getNroCuentaProducto())
						.toString());
		comprobanteCompra.setCuentaOrigenTipo(obtenerDescripcionTipoCuenta(cuentaOrigen, false));
		Cuenta cuentaDestino = parametros.getCuentaDestino();
		comprobanteCompra.setCuentaDestinoNumero(
				new IdentificacionCuenta(cuentaDestino.getNroSucursal(), cuentaDestino.getNroCuentaProducto())
						.toString());
		comprobanteCompra.setCuentaDestinoTipo(obtenerDescripcionTipoCuenta(cuentaDestino, true));
		comprobanteCompra.setCotizacionAplicada(ISBANStringUtils.formatearSaldoString(parametros.getCotizacion()));
		comprobanteCompra.setImportePesosDebitado(ISBANStringUtils.formatearSaldoString(parametros.getImporteDebito()));
		comprobanteCompra.setNumeroOperacion(entity.getNroOperacion());
		comprobanteCompra.setNumeroComprobante(entity.getNumbole());
		comprobanteCompra.setFecha(ISBANStringUtils
				.formatearFecha(this.getCompraVentaDolaresUtil().formatearFechaSinHora(entity.getFecha())));
		comprobanteCompra.setHora(entity.getHora());
		comprobanteCompra.setEsCompra(Boolean.TRUE);
		comprobanteCompra.setLegalCompraUno(obtenerLegal("1005"));
		comprobanteCompra.setLegalCompraDos(obtenerLegal("1003"));
		comprobanteCompra.setFechaHoraServicio(entity.getFechaHoraBody());
		comprobanteCompra.setImporteImpuesto(obtenerImporteFormateado(
				getCompraVentaDolaresUtil().convertirSimulacionImporteDebitar(entity.getImplmpu())));
		comprobanteCompra.setMuestraImpuestos(!comprobanteCompra.getImporteImpuesto().equals(BigDecimal.ZERO));
		comprobanteCompra.setImporteImpuesto2(obtenerImporteFormateado(
                getCompraVentaDolaresUtil().convertirSimulacionImporteAComprar(entity.getImporteImpuesto2())));
		comprobanteCompra.setConceptoImpuesto2(entity.getConceptoImpuesto2());
		comprobanteCompra.setImpuesto2(entity.getImpuesto2());
		comprobanteCompra.setRegimenImpositivo2(entity.getRegimenImpositivo2());
		comprobanteCompra.setPorcentajeImpuesto2(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getPorcentajeImpuesto2(),2));
		comprobanteCompra.setImpuestoBienes2(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpuestoBienes(),2));
		return comprobanteCompra;
	}
	
	/**
	 * Obtener importe formateado.
	 *
	 * @param importe
	 *            the importe
	 * @return the big decimal
	 */
	private BigDecimal obtenerImporteFormateado(String importe) {
		String importeFormateado = ISBANStringUtils.agregadorPuntoDivisor(importe);
		return ISBANStringUtils.formatearSaldoString(importeFormateado);
	}

	/**
	 * Genera una respuesta con los datos de la simulacion para la compra de
	 * dolares.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<ComprobanteCompraVentaDTO> obtenerRespuestaClienteCompra(ParametrosOperacion parametrosOperacion,
			OperacionCompraVentaDatosEntrada datosEntrada) throws BusinessException, CompraVentaDolaresException {
		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion cliente compra: {}.", datosEntrada.toString());
			boolean isBancaPrivada = CuentasBancaPrivadaUtil.isBancaPrivada(parametrosOperacion.getCuentaOrigen(), parametrosOperacion.getCuentaDestino());
			OperacionClienteCompraEntity entity = operacionCompraDAO
					.operacionClienteCompra(parametrosOperacion.getCliente(), datosEntrada, isBancaPrivada);
			if (!entity.getTieneError()) {
				if (isBancaPrivada) {
					RequestBancaPrivadaEntity entityBancaPrivada = new RequestBancaPrivadaEntity(entity.getNumbole(),
							entity.getImpabon(), entity.getImpcarg(), entity.getImpcoti(), "C");
					operacionBancaPrivadaDAO.insertarOperacionCambio(parametrosOperacion, datosEntrada,
							entityBancaPrivada);
				}
				ComprobanteCompraVentaDTO comprobante = crearDatosResponseClienteCompra(entity, parametrosOperacion);
				return casuistica.crearRespuestaOk(ComprobanteCompraVentaDTO.class, comprobante);
			}
			return crearRespuestaError(obtenerDatosDelError(entity.getCodError(), true),
					CompraVentaStringUtil.OPERACION_COMPRA, parametrosOperacion.getCuentaOrigen(), entity.getMensajeError());
	    } catch (DAOException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaError(ErrorCompraVentaEnum.CUENTA_INHABILITADA_1);
		}
	}
}