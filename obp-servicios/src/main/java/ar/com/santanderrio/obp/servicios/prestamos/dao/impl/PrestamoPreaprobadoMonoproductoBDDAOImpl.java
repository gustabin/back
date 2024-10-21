package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPreaprobadoMonoproductoBDDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class PrestamoPreaprobadoMonoproductoBDDAOImpl extends BaseDatoDAOImpl
        implements PrestamoPreaprobadoMonoproductoBDDAO {

    @Autowired
    private PrestamoPermitidoDAO prestamoPermitidoDAO;

    /**
     * Obtiene la lista de prestamos permitidos preaprobados
     */
    @Override
    public List<PrestamoPermitidoEntity> consultarPrestamoPreaprobadoMonoproducto(Cliente cliente) {
        return getPrestamoPermitidoEntities(cliente);
    }


    /**
     * Obtiene la mayor oferta de prestamos permitidos preaprobados
     */
    @Override
    public PrestamoPermitidoEntity consultarValorMaximoOferta(Cliente cliente) {

        List<PrestamoPermitidoEntity> prestamosPermitidosEntity = getPrestamoPermitidoEntities(cliente);
        PrestamoPermitidoEntity maximaOferta = null;
        for (PrestamoPermitidoEntity prestamoPermitidoEntity : prestamosPermitidosEntity) {
            if (maximaOferta == null) {
                maximaOferta = prestamoPermitidoEntity;
            } else {
                if (new BigDecimal(prestamoPermitidoEntity.getMaxImpPrest()).compareTo(new BigDecimal(maximaOferta.getMaxImpPrest())) > 0) {
                    maximaOferta = prestamoPermitidoEntity;
                }
            }
        }
        return maximaOferta;
    }

    /**
     * Obtiene todas las ofertas para todas las cuentas en pesos de un cliente
     */
    private List<PrestamoPermitidoEntity> getPrestamoPermitidoEntities(Cliente cliente) {
        Set<PrestamoPermitidoEntity> prestamosPermitidosEntity = new HashSet<PrestamoPermitidoEntity>();
        for (Cuenta cuenta : cliente.getCuentasEnPesos()) {
            PrestamoPermitidoInEntity prestamoPermitidoIn = new PrestamoPermitidoInEntity();
            prestamoPermitidoIn.setCliente(cliente);
            prestamoPermitidoIn.setCuenta(cuenta);
            try {
                prestamosPermitidosEntity.addAll(prestamoPermitidoDAO.consultarPrestamosPermitidosPreaprobados(prestamoPermitidoIn).getListaResult());
            } catch (DAOException ignored) {
            }
        }
        return new ArrayList<PrestamoPermitidoEntity>(prestamosPermitidosEntity);
    }


}
