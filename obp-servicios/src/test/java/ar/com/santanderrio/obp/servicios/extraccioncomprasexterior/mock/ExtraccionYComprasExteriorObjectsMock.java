package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorInDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CuentaOperacionExteriorEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ModifTarjetaOperaExteriorEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.TarjetaOperacionExteriorEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;


/** 
 * Entities mocks para extraccion y compras en el exterior
 * @author Silvina_Luque
 *
 */
public final class ExtraccionYComprasExteriorObjectsMock {

    private ExtraccionYComprasExteriorObjectsMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    

    
    /**
     * Obtiene la entidad ConsultaCuentasOperaExteriorOutEntity
     * @return ConsultaCuentasOperaExteriorOutEntity
     */
    public static ConsultaCuentasOperaExteriorOutEntity obtenerConsultaCuentasOutEntity() {
        ConsultaCuentasOperaExteriorOutEntity consultaCuentasOperaExteriorOutEntity = new ConsultaCuentasOperaExteriorOutEntity();
        consultaCuentasOperaExteriorOutEntity.setCantidadCuentas(3L);
        consultaCuentasOperaExteriorOutEntity.setCodigoRetornoExtendido("00000000");
        consultaCuentasOperaExteriorOutEntity.setHeaderTrama("");
        consultaCuentasOperaExteriorOutEntity.setListaCuentas(obtenerListaCuentasEntity(3));
        return consultaCuentasOperaExteriorOutEntity;
    }
    
    /**
     * Obtiene la entidad List<CuentaOperacionExteriorEntity>
     * @return List<CuentaOperacionExteriorEntity>
     */
    public static List<CuentaOperacionExteriorEntity> obtenerListaCuentasEntity(int cantidad){
        List<CuentaOperacionExteriorEntity> listaCuentas = new ArrayList<CuentaOperacionExteriorEntity>();
        for(int x = 0; x < cantidad; x++) {
            CuentaOperacionExteriorEntity cuenta = obtenerCuentaOperacionExteriorEntity(x);
            listaCuentas.add(cuenta);
        }
        return listaCuentas;
    }
    
    /**
     * Obtiene la entidad CuentaOperacionExteriorEntity
     * @return CuentaOperacionExteriorEntity
     */
    public static CuentaOperacionExteriorEntity obtenerCuentaOperacionExteriorEntity(int numero) {
        CuentaOperacionExteriorEntity cuenta = new CuentaOperacionExteriorEntity();
        cuenta.setCuentaPreferida("N");
        cuenta.setCuentaRelacionada("ACAH00000000006391700010"+numero);
        cuenta.setNumeroPan("1234123412341234");
        cuenta.setRelacionEstado("1");
        return cuenta;
        
    }    
        
        
    /**
     * Obtiene la entidad ConsultaTarjetasOperaExteriorOutEntity
     * @return ConsultaTarjetasOperaExteriorOutEntity
     */
    public static ConsultaTarjetasOperaExteriorOutEntity obtenerConsultaTarjetasOutEntity(boolean haySegundoLLamado) {
        ConsultaTarjetasOperaExteriorOutEntity consultaTarjetasOperaExteriorOutEntity = new ConsultaTarjetasOperaExteriorOutEntity();
        
        consultaTarjetasOperaExteriorOutEntity.setCodigoRetornoExtendido("00000000");
        consultaTarjetasOperaExteriorOutEntity.setHeaderTrama("");
        consultaTarjetasOperaExteriorOutEntity.setClaveRellamada(""); 
        if(haySegundoLLamado) {
            consultaTarjetasOperaExteriorOutEntity.setCantidadTarjetas(50L);
            consultaTarjetasOperaExteriorOutEntity.setClaveRellamada("324234234324111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
            consultaTarjetasOperaExteriorOutEntity.setHayMasDatos("S");
        }else {
            consultaTarjetasOperaExteriorOutEntity.setCantidadTarjetas(1L);
            consultaTarjetasOperaExteriorOutEntity.setClaveRellamada("");
            consultaTarjetasOperaExteriorOutEntity.setHayMasDatos("N");
            
        }
        consultaTarjetasOperaExteriorOutEntity.setListaTarjetas(obtenerListaTarjetasEntity(1));
        return consultaTarjetasOperaExteriorOutEntity;
    }
    

    /**
     * Obtiene la entidad List<TarjetaOperacionExteriorEntity>
     * @return List<TarjetaOperacionExteriorEntity>
     */
    public static List<TarjetaOperacionExteriorEntity> obtenerListaTarjetasEntity(int cantidad) {
        List<TarjetaOperacionExteriorEntity> listaTarjetas = new ArrayList<TarjetaOperacionExteriorEntity>();
        for(int x = 0; x < cantidad; x++) {
            TarjetaOperacionExteriorEntity tarjeta = obtenerTarjetaOperacionExteriorEntity(x);
            listaTarjetas.add(tarjeta);
        }
        return listaTarjetas;
   
    }
    
    /**
     * Obtiene la entidad TarjetaOperacionExteriorEntity
     * @return TarjetaOperacionExteriorEntity
     */
    public static TarjetaOperacionExteriorEntity obtenerTarjetaOperacionExteriorEntity(int numero) {
        TarjetaOperacionExteriorEntity tarjeta = new TarjetaOperacionExteriorEntity();
        tarjeta.setNroPan("12341234123"+ numero);
        tarjeta.setNumeroDocumento("30385112");
        tarjeta.setTipoDocumento("N");
        return tarjeta;
        
    }

    /**
     * Obtiene la entidad CambioTarjetaOperaExteriorOutEntity
     * @return CambioTarjetaOperaExteriorOutEntity
     */
    public static CambioTarjetaOperaExteriorOutEntity obtenerCambioTarjetaOutEntity() {
        CambioTarjetaOperaExteriorOutEntity cambioTarjeta = new CambioTarjetaOperaExteriorOutEntity();
        cambioTarjeta.setCodigoRetornoExtendido("000000000");
        cambioTarjeta.setFecha("");
        cambioTarjeta.setNroComprobante("123456789");
        cambioTarjeta.setHora("");
        cambioTarjeta.setHeaderTrama("");
        cambioTarjeta.setListaTarjetas(obtenerListaCmbTarjetaEntity());
        return cambioTarjeta;
    }

    /**
     * Obtiene la entidad  List<ModifTarjetaOperaExteriorEntity>
     * @return  List<ModifTarjetaOperaExteriorEntity>
     */
    private static List<ModifTarjetaOperaExteriorEntity> obtenerListaCmbTarjetaEntity() {
        List<ModifTarjetaOperaExteriorEntity> listaCmbTarjetas = new ArrayList<ModifTarjetaOperaExteriorEntity>();
        ModifTarjetaOperaExteriorEntity cmbTarjeta = new ModifTarjetaOperaExteriorEntity();
        cmbTarjeta.setNumeroTarjeta("123412341234");
        listaCmbTarjetas.add(cmbTarjeta);
        return listaCmbTarjetas;
    }

    /**
     * Obtiene la entidad CambioTarjetaOperaExteriorInDTO
     * @return CambioTarjetaOperaExteriorInDTO
     */
    public static CambioTarjetaOperaExteriorInDTO obtenerDatosCambioTarjeta() {
        CambioTarjetaOperaExteriorInDTO datosCambioTarjetaDTO = new CambioTarjetaOperaExteriorInDTO();
        datosCambioTarjetaDTO.setCuenta("ACAH000000000063917000101");
        datosCambioTarjetaDTO.setNumeroDocumento("30385112");
        datosCambioTarjetaDTO.setTipoDocumento("N");
        datosCambioTarjetaDTO.setNumeroTarjeta("1234123412341243");
        return datosCambioTarjetaDTO;
    }

    /**
     * Obtiene la entidad DatosExtraccionYComprasExteriorDTO
     * @return DatosExtraccionYComprasExteriorDTO
     */
    public static DatosExtraccionYComprasExteriorDTO obtenerDatosOperaExteriorDTO() {
        DatosExtraccionYComprasExteriorDTO datosDTO = new DatosExtraccionYComprasExteriorDTO();
        datosDTO.setCuentaSeleccionada("Cuenta en pesos 000-063917/0");
        datosDTO.setTarjetaSeleccionada("XXXX-1234");
        datosDTO.setCuentasOperacionExterior(obtenerListaCuentasDTO());
        datosDTO.setTarjetasOperacionExterior(obtenerListaTarjetasDTO());
        return datosDTO;
    }
    
    /**
     * Obtiene la entidad List<CuentaOperacionExteriorDTO>
     * @return List<CuentaOperacionExteriorDTO>
     */
    public static List<CuentaOperacionExteriorDTO> obtenerListaCuentasDTO(){
        List<CuentaOperacionExteriorDTO> listaCuentasDTO = new ArrayList<CuentaOperacionExteriorDTO>();
        CuentaOperacionExteriorDTO cuentaDTO = new CuentaOperacionExteriorDTO();
        cuentaDTO.setId("1234-1234");
        cuentaDTO.setCuentaRelacionada("ACAH000000000063917000101");
        cuentaDTO.setCuentaPreferida(false);
        cuentaDTO.setNumeroTarjeta("1234123412341234");
        listaCuentasDTO.add(cuentaDTO);
        
        CuentaOperacionExteriorDTO cuentaDTO2 = new CuentaOperacionExteriorDTO();
        cuentaDTO2.setId("1234-1235");
        cuentaDTO2.setCuentaRelacionada("ACAH000000000063917000101");
        cuentaDTO2.setCuentaPreferida(false);
        cuentaDTO2.setNumeroTarjeta("1234123412341234");
        listaCuentasDTO.add(cuentaDTO2);
        
        return listaCuentasDTO;
    }
    
    /**
     * Obtiene la entidad List<TarjetaOperacionExteriorDTO>
     * @return List<TarjetaOperacionExteriorDTO>
     */
    public static List<TarjetaOperacionExteriorDTO> obtenerListaTarjetasDTO(){
        List<TarjetaOperacionExteriorDTO> listaTarjetasDTO = new ArrayList<TarjetaOperacionExteriorDTO>();
        TarjetaOperacionExteriorDTO tarjetaDTO = new TarjetaOperacionExteriorDTO();
        tarjetaDTO.setId("1234-1234");
        tarjetaDTO.setNumeroDocumento("30385112");
        tarjetaDTO.setTipoDocumento("N");
        tarjetaDTO.setNumeroTarjeta("1234123412341234");
        listaTarjetasDTO.add(tarjetaDTO);
        return listaTarjetasDTO;
    }


    /**
     * Obtiene la entidad CambioTarjetaOperaExteriorOutDTO
     * @return CambioTarjetaOperaExteriorOutDTO
     */
    public static CambioTarjetaOperaExteriorOutDTO obtenerCambioTarjetaOutDTO() {
        CambioTarjetaOperaExteriorOutDTO cambioTarjetaDTO = new CambioTarjetaOperaExteriorOutDTO();
        cambioTarjetaDTO.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        cambioTarjetaDTO.setHora(new SimpleDateFormat("HH:mm").format(new Date()));
        cambioTarjetaDTO.setNumeroComprobante("123456789");
        return cambioTarjetaDTO;
    }

    /**
     * Obtiene la entidad ModifTarjetaOperaExtraccionView
     * @return ModifTarjetaOperaExtraccionView
     */
    public static ModifTarjetaOperaExtraccionView obtenerModifTarjetaView() {
        ModifTarjetaOperaExtraccionView modifTarjetaOperaExteriorView = new ModifTarjetaOperaExtraccionView();
        modifTarjetaOperaExteriorView.setIdCuenta("1234-1234");
        modifTarjetaOperaExteriorView.setIdTarjeta("1234-1234");
        return modifTarjetaOperaExteriorView;
    }
    


}
