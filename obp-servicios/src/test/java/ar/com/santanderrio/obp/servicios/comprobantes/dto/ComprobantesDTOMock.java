package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
 

public final class ComprobantesDTOMock {

    private ComprobantesDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener token operacion DTO.
     *
     * @return the token operacion DTO
     */
    public static ComprobantesDTO obtenerComprobantesDTO() {
        ComprobantesDTO data = new ComprobantesDTO(obtenerComprobantes());
        return data;
    }

    private static LinkedList<ComprobanteDTO> obtenerComprobantes() {
        LinkedList<ComprobanteDTO> linkedList = new LinkedList<ComprobanteDTO>();
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(3));
        linkedList.add(obtenerComprobanteDTO(3));
        linkedList.add(obtenerComprobanteDTO(4));
        linkedList.add(obtenerComprobanteDTO(5));
        linkedList.add(obtenerComprobanteDTO(6));
        linkedList.add(obtenerComprobanteDTO(7));
        linkedList.add(obtenerComprobanteDTO(7));
        linkedList.add(obtenerComprobanteDTO(7));
        linkedList.add(obtenerComprobanteDTO(8));
        linkedList.add(obtenerComprobanteDTO(8));
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(9));
        linkedList.add(obtenerComprobanteDTO(9));
        linkedList.add(obtenerComprobanteDTO(2));
        linkedList.add(obtenerComprobanteDTO(9));
        linkedList.add(obtenerComprobanteDTO(10));
        linkedList.add(obtenerComprobanteDTO(2));
        
        return linkedList;
    }

    private static ComprobanteDTO obtenerComprobanteDTO(long dia) {
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new Date(dia));
        comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA_PESOS);
        comprobanteDTO.setDestinatario("Credito ciudad");
        comprobanteDTO.setImportePesos(new BigDecimal(1200));
       return comprobanteDTO;
    }
   
}