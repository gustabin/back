package ar.com.santanderrio.obp.servicios.prestamos.bo;

import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoInOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;

public interface PrestamoPreaprobadoBO {

	/**
	 * Obtiene el importe maximo de existir una oferta 
	 * de prestamo preapronbado monoproducto
	 * @param nup
	 * @return
	 */
	Respuesta<PrestamoPermitidoEntity> getMaxImporteOfertaPrestamoPreaprobado(Cliente cliente);
	
	/**
	 * Obtiene ofertas de prestamos preaprobados monoproducto
	 * @param nup
	 * @return
	 * @throws DAOException
	 */
	Respuesta<List<PrestamoPermitidoEntity>> consultarPrestamoPreaprobadoMonoproducto(Cliente cliente);

	
	/**
	 * Realiza el alta de un prestamo preaprobado monoproducto
	 * @param prestamoPreaprobadoInOutEntity
	 * @param cliente
	 * @return
	 * @throws DAOException
	 */
	Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> altaSimulacionPrestamoPreabrobadoMonoproducto(PrestamoPreaprobadoMonoproductoInEntity prestamoPreaprobadoInOutEntity, Cliente cliente);

	/**
	 * Obtiene el reporte para armar el comprobante PDF
	 * @param datos
	 * @return
	 */
	Respuesta<Reporte> descargarPrestamoPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoInOutView datos);
	
}
