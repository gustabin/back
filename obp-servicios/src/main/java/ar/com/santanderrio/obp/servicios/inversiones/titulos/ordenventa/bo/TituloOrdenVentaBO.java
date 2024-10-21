/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CompraVentaTitulosValoresDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Interface TituloOrdenVentaBO.
 */
public interface TituloOrdenVentaBO {

	/**
	 * Consultar cuentas titulos web service.
	 *
	 * @param cliente
	 *            the cliente
	 * @param listaCuentasTitulos
	 *            the lista cuentas titulos
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the consulta de tenencia response DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	ConsultaDeTenenciaResponseDTO consultarCuentasTitulosWebService(Cliente cliente, List<Cuenta> listaCuentasTitulos, Boolean esBancaPrivada) throws BusinessException;

	/**
	 * Consultar titulares.
	 *
	 * @param listaIntervinientes
	 *            the lista intervinientes
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<String> consultarTitulares(List<Interviniente> listaIntervinientes) throws BusinessException;
		
	/**
	 * Ejecutar metodo compra venta titulos valores.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param accion
	 *            the accion
	 * @return the compra venta titulos valores DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	CompraVentaTitulosValoresDTO ejecutarMetodoCompraVentaTitulosValores(Cliente cliente, ConfirmacionVentaTitulosInView confirmacionInView, String accion) throws BusinessException;
	
	/**
	 * Consultar datos suscripcion saldo PDC.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @return the consulta suscripcion saldo PDCDTO
	 */
	ConsultaSuscripcionSaldoPDCDTO consultarDatosSuscripcionSaldoPDC(Cliente cliente, ConfirmacionVentaTitulosInView confirmacionInView);
	
}