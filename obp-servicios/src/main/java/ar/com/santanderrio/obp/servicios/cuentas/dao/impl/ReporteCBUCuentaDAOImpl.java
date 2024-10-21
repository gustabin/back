/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ReporteCBUCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteCBUCuentaDAOImpl.
 */
@Component("reporteCBUCuentaDAO")
public class ReporteCBUCuentaDAOImpl implements ReporteCBUCuentaDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteCBUCuentaDAOImpl.class);

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = " ";

	/** The Constant SEPARATOR_BARRA_LATERAL. */
	private static final String SEPARATOR_BARRA_LATERAL = "/";

	/** The Constant SUCURSAL_PAD_SIZE. */
	private static final int SUCURSAL_PAD_SIZE = 3;

	/** The Constant CUENTA_PAD_SIZE. */
	private static final int CUENTA_PAD_SIZE = 7;

	/** The Constant SUCURSAL_PAD_CHAR. */
	private static final String SUCURSAL_PAD_CHAR = "0";

	/** The file jasper. */
	@Value("classpath:/report/cuentas/detallecbucuenta.jasper")
	private Resource fileJasper;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logo_cabecera;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logo_pie;

	/** The consultar sucursales service. */
	@Autowired
	private ConsultarSucursalesBO consultarSucursalesBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.ReporteCBUCuentaDAO#
	 * obtenerReporteCBUCuenta(ar.com.santanderrio.obp.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Reporte obtenerReporteCBUCuenta(Cliente cliente, AbstractCuenta cuenta, String alias) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			String sucursal = StringUtils.leftPad(ISBANStringUtils.eliminarCeros(cuenta.getNroSucursal()),
					SUCURSAL_PAD_SIZE, SUCURSAL_PAD_CHAR);
			String decripcionSucursal = sucursal;
			if (StringUtils.isNotBlank(cuenta.getNroSucursal())) {
				Sucursal sucursalCuenta = consultarSucursalesBO.consultarSucursalPorId(cuenta.getNroSucursal())
						.getRespuesta();
				if (sucursalCuenta != null) {
					decripcionSucursal = sucursal + " - " + sucursalCuenta.getDescripcion();
				}
			}
			String numeroCuenta = StringUtils.leftPad(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()),
					CUENTA_PAD_SIZE, SUCURSAL_PAD_CHAR);
			numeroCuenta = numeroCuenta.substring(0, numeroCuenta.length() - 1) + SEPARATOR_BARRA_LATERAL
					+ numeroCuenta.substring(numeroCuenta.length() - 1);

			parameters.put("TITULO_COMPROBANTE", "Comprobante de detalle de CBU y Alias");
			parameters.put("NOMBRE", cliente.getNombre() + SEPARATOR + cliente.getApellido1());
			if (cliente.getNumeroCUILCUIT()!= null) {
				parameters.put("CUIL", "CUIT/CUIL : " + cliente.getNumeroCUILCUIT());
			}
			parameters.put("CUENTA", sucursal + "-" + numeroCuenta);
			parameters.put("BANCO", BancoEnum.SANTANDER_RIO.getDescripcion());
			parameters.put("CBU", cuenta.getCbu());
			parameters.put("TIPO_CUENTA", TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda());
			parameters.put("SUCURSAL", decripcionSucursal);
			parameters.put("ALIAS", alias);
			parameters.put("LOGO_CABECERA", logo_cabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logo_pie.getFile().getPath());

			Date fechaComprobante = new Date();
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = formatoFecha.format(fechaComprobante);
			DateFormat formatoHora = new SimpleDateFormat("HH:mm");
			String hora = formatoHora.format(fechaComprobante);

			parameters.put("FECHA_ACTUAL", fecha + " - " + hora);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("ComprobanteCbuyAlias.pdf");
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
