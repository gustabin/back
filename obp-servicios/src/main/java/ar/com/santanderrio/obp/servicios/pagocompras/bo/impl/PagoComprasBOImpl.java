/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO;
import ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudasPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaInEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraSinDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagocompras.exception.SinDeudasException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class PagoComprasBOImpl.
 */
@Component
public class PagoComprasBOImpl implements PagoComprasBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoComprasBOImpl.class);

    /** The Constant MONEDA_PESOS. */
    private static final String MONEDA_PESOS = "0";

    /** The Constant MONEDA_DOLARES. */
    private static final String MONEDA_DOLARES = "2";

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The pago compras DAO. */
    @Autowired
    private PagoComprasDAO pagoComprasDAO;

    /** The detalle cuenta DAO. */
    @Autowired
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The sort de deudas. */
    private Comparator<DeudaPagoComprasEntity> sortDeDeudas = new Comparator<DeudaPagoComprasEntity>() {
        @Override
        public int compare(DeudaPagoComprasEntity deuda1, DeudaPagoComprasEntity deuda2) {
            SimpleDateFormat format = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_IATX);
            try {
                return format.parse(deuda1.getFechaVencimientoDeuda())
                        .compareTo(format.parse(deuda2.getFechaVencimientoDeuda()));
            } catch (ParseException e) {
                LOGGER.error("Error de parseo de fecha de vencimiento.", e);
                return 0;
            }
        }
    };

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO#
     * obtenerDeudas(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago,
     * java.lang.String)
     */
    @Override
    public List<DeudaPagoComprasEntity> obtenerDeudas(Cliente cliente, MedioPago medioPago, String identificacion)
            throws BusinessException {

        LOGGER.info("Obtener las deudas para el cliente {}, empresa {} con el identificador {}.", cliente.getNup(),
                medioPago.getFiid(), identificacion);
        try {
            DeudasPagoComprasEntity deudas = pagoComprasDAO.obtenerListaDeudas(cliente, medioPago, identificacion);

            List<DeudaPagoComprasEntity> listaDeudas = deudas.getListaDeudas();
            LOGGER.info("Se ordenan las deudas por fecha: {}.", listaDeudas);
            Collections.sort(listaDeudas, sortDeDeudas);
            LOGGER.info("Se guardan en sesion el objeto obtenido como respuesta del DAO: {}.", deudas);
            sesionParametros.setDeudasPagoComprasEntity(deudas);
            return listaDeudas;
        } catch (SinDeudasException e) {
            LOGGER.error("No existen deudas.", e);
            return new ArrayList<DeudaPagoComprasEntity>();
        } catch (DAOException e) {
            LOGGER.error("Error al armar la respuesta con las deudas.", e);
        }
        throw new BusinessException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO#
     * obtenerCuentas(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum)
     */
    @Override
    public List<Cuenta> obtenerCuentas(Cliente cliente, DivisaEnum divisa) throws BusinessException {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (DivisaEnum.PESO.equals(divisa) && cuenta.esSaldoPesos()) {
                cuentas.add(cuenta);
            } else if (DivisaEnum.DOLAR.equals(divisa) && cuenta.esSaldoDolares()) {
                cuentas.add(cuenta);
            }
        }

        int cantidadCuentas = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isCuentaUnica()) {
                cantidadCuentas = cantidadCuentas + 2;
            } else {
                cantidadCuentas++;
            }
        }

        try {
            detalleCuentaDAO.actualizarSaldo(cuentas, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar saldo de cuentas", e);
            throw new BusinessException("Error al consultar saldo de cuentas");
        }

        return CuentasUtils.ordenarCuentasMonetarias(cuentas);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO#
     * obtenerDivisa(java.lang.String)
     */
    @Override
    public DivisaEnum obtenerDivisa(MedioPago medioPago) {
        return obtenerDivisa(medioPago.getPpdctaMoneda());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO#
     * obtenerDivisa(java.lang.String)
     */
    @Override
    public DivisaEnum obtenerDivisa(String moneda) {
        if (MONEDA_PESOS.equals(moneda)) {
            return DivisaEnum.PESO;
        } else if (MONEDA_DOLARES.equals(moneda)) {
            return DivisaEnum.DOLAR;
        }
        return null;
    }

    /**
     * Ejecuta el pago de compras sin deuda.
     *
     * @param cliente
     *            the cliente
     * @param inDTO
     *            the in DTO
     * @return the pago compras out DTO
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public PagoComprasOutDTO ejecutarPagoComprasSinDeuda(Cliente cliente, PagoComprasInDTO inDTO)
            throws BusinessException {
        try {
            PagoCompraSinDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasSinDeuda(cliente, inDTO);
            if (!entity.getTieneError()) {
                return new PagoComprasOutDTO(entity, inDTO.getPid());
            } else {
                LOGGER.error("Codigo de error distinto a cero: {}.", entity.getCodigoRetornoExtendido());
                return new PagoComprasOutDTO(entity);
            }
        } catch (DAOException e) {
            LOGGER.error("Error timeout...", e);
            throw new BusinessException();
        }
    }

    /**
     * Ejecuta el pago de compras con deuda.
     *
     * @param cliente
     *            the cliente
     * @param pagoCompras
     *            the pago compras
     * @param medioPago
     *            the medio pago
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     * @return the pago compras out DTO
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public PagoComprasOutDTO ejecutarPagoComprasConDeuda(Cliente cliente, NuevoPago pagoCompras, MedioPago medioPago,
            Cuenta cuenta, DivisaEnum divisa) throws BusinessException {
        DeudasPagoComprasEntity deudas = sesionParametros.getDeudasPagoComprasEntity();

        PagoCompraConDeudaInEntity inEntity = new PagoCompraConDeudaInEntity(pagoCompras, medioPago, deudas, divisa,
                cuenta);
        try {
            PagoCompraConDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasConDeuda(cliente, inEntity);
            if (!entity.getTieneError()) {
                realizarAdhesion(cliente, medioPago, pagoCompras.getCodigoPagoElectronico());
            } else {
                LOGGER.error("Codigo de error distinto a cero: {}.", entity.getCodigoRetornoExtendido());
            }
            return new PagoComprasOutDTO(entity);
        } catch (DAOException e) {
            LOGGER.error("Error timeout...", e);
            throw new BusinessException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO#
     * realizarAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago,
     * java.lang.String)
     */
    @Override
    public void realizarAdhesion(Cliente cliente, MedioPago medioPago, String identificacion) {
        try {
            boolean tieneAdhesion = pagoComprasDAO.tieneAdhesion(cliente, medioPago);
            if (!tieneAdhesion) {
                pagoComprasDAO.altaAdhesion(cliente, medioPago, identificacion);
            }
        } catch (DAOException e) {
            LOGGER.error("No se pudo consultar la adhesion.", e);
        }
    }

    @Override
    public PagoComprasOutDTO ejecutarPagoComprasConDeuda(Cliente cliente, PagoComprasMultiple pagoCompras,
            MedioPago medioPago, DivisaEnum divisa) throws BusinessException {
        DeudasPagoComprasEntity deudas = sesionParametros.getDeudasPagoComprasEntity();

        PagoCompraConDeudaInEntity inEntity = new PagoCompraConDeudaInEntity(cliente, pagoCompras, medioPago, deudas, divisa);
        try {
            PagoCompraConDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasConDeuda(cliente, inEntity);
            if (!entity.getTieneError()) {
                realizarAdhesion(cliente, medioPago, pagoCompras.getCodigoPagoElectronico());
            } else {
                LOGGER.error("Codigo de error distinto a cero: {}.", entity.getCodigoRetornoExtendido());
            }
            return new PagoComprasOutDTO(entity);
        } catch (DAOException e) {
            LOGGER.error("Error timeout...", e);
            throw new BusinessException();
        }
    }

}
