package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteTransferenciaDTOTest {
    @Test
    public void construirDetalle7x24OBPesos() {

        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalle_transferenciaProgramadaOBDTO());
        comprobanteDTO.getDetalle().setNroCuentaOrigen("123123415412");
        comprobanteDTO.getDetalle().setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_PESOS);
        comprobanteDTO.getDetalle().setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "20378668817"));
        ((DetalleComprobanteTransferenciaDTO) comprobanteDTO.getDetalle())
                .setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleComprobanteTransferenciaView res = (DetalleComprobanteTransferenciaView) comprobanteDTO.getDetalle()
                .getView(comprobanteDTO);

        Assert.assertNotNull(res);
    }

    @Test
    public void construirDetalle7x24OBDolares() {
        // Es un test para probar el caso en que la mayoria de los campos no se
        // manden(los que accionan condicionales)
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalle_transferenciaProgramadaOBDTO());

        DetalleComprobanteTransferenciaView res = (DetalleComprobanteTransferenciaView) comprobanteDTO.getDetalle()
                .getView(comprobanteDTO);

        Assert.assertNotNull(res);

    }

    /**
     * Obtener comprobante.
     *
     * @param pagoServicios
     *            the pago servicios
     * @return the comprobante DTO
     */
    private ComprobanteDTO obtenerComprobante(TipoOperacionComprobanteEnum pagoServicios) {
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 9, 1).getTime());
        comprobanteDTO.setTipoOperacion(pagoServicios);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Destinatario");
        comprobanteDTO.setImporteDolares(null);
        comprobanteDTO.setImportePesos(new BigDecimal(1200));
        return comprobanteDTO;
    }

    /**
     * Obtener detalle transferencia programada OBDTO.
     *
     * @return Tthe detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalle_transferenciaProgramadaOBDTO() {
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
        return detalle;
    }

    /**
     * Completar detalle comprobante DTO.
     *
     * @param detalle
     *            the detalle
     */
    private void completarDetalleComprobanteDTO(DetalleComprobanteDTO detalle) {
        detalle.setIdentificacion("Identificacion");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
    }

    @Test
    public void hashTest() {
        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));

        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setBanco("banco");

        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
    }

    @Test
    public void equalsTest() {
        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));

        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO2
                .setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_DOLARES);

        Assert.assertTrue(!detalleComprobanteDTO.equals(detalleComprobanteDTO2));
        Assert.assertTrue(detalleComprobanteDTO.equals(detalleComprobanteDTO));
    }

    @Test
    public void toStringTest() {
        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));

        DetalleComprobanteTransferenciaDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_DOLARES);

        Assert.assertTrue(!detalleComprobanteDTO.toString().equals(detalleComprobanteDTO2.toString()));
        Assert.assertTrue(detalleComprobanteDTO.toString().equals(detalleComprobanteDTO.toString()));
    }
}
