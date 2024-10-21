package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo;

import java.sql.SQLException;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Interface CuentasBancaPrivadaBO.
 */
public interface CuentasBancaPrivadaBO {

	List<CuentaIntermedioDTO> consultarSaldosCuenta(Cliente cliente) throws SQLException, BusinessException;
	
	Respuesta<Void> actualizarApodo(IdentificacionCuenta idCuenta, String apodo, Cliente cliente);

	AbstractCuenta buscarCuentaPorId(Cliente cliente, IdentificacionCuenta idCuenta);

	List<Cuenta> obtenerCuentasBancaPrivada(Cliente cliente);

	List<CuentasAdhesionDebitoView> obtenerSaldosCuentas(Cliente cliente) throws BusinessException;
	
	Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity)throws DAOException, SQLException;

	Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity, Cliente cliente);
	
	Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws BusinessException, WSODException;
	
	Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(ResumenMensualCuenta resumenSeleccionado, AbstractCuenta cuenta);
	
	List<CuentaIntermedioDTO> obtenerSaldoServicioIatx(Cliente cliente) throws SQLException;

	CuentaIntermedioDTO obtenerSaldoServicioIatx(Cuenta cuenta) throws SQLException;
	
}
