package ar.com.santanderrio.obp.servicios.prestamos.bo;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoCanceladoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosCanceladosOutEntity;

public class PrestamoCanceladoMock extends IatxBaseDAO {
    private String trama;

    private PrestamosCanceladosOutEntity prestamosCanceladosEntity;
    
    private List<PrestamoCanceladoDTO> prestamosCanceladosDTO;
    
        
    public PrestamoCanceladoMock() {
        prestamosCanceladosDTO = new ArrayList<PrestamoCanceladoDTO>();
        trama  = "200000000000P04HTML0009900010300QMRR92  ********00664763000000122017041216583000000000IBF28362000000000000CNSCONTCAN1100000            00627792N   00        00 000000000201704121658230000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0013800000000õ001õ0099õ035190395485õ35õ0015õTIõ001õ2016-09-30õCõ00000õ05õ2016-09-30-10.54.30.709099õNõ2015-10-06õ30õ                      õNõ4õ    õ            õ";
        prestamosCanceladosEntity =  this.processTrama(trama, PrestamosCanceladosOutEntity.class);
        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("24");
        preformalizacion.getPrestamoDebitoAdherido().setImpconce("5000000");
        
        for (PrestamoCanceladoOutEntity prestamoCanceladoEntity : prestamosCanceladosEntity.getListaResult()) {
            prestamosCanceladosDTO.add(new PrestamoCanceladoDTO(prestamoCanceladoEntity, preformalizacion, "Motivo de prestamo"));
        }
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public PrestamosCanceladosOutEntity getPrestamosCanceladosEntity() {
        return prestamosCanceladosEntity;
    }

    public void setPrestamosCanceladosEntity(PrestamosCanceladosOutEntity prestamosCanceladosEntity) {
        this.prestamosCanceladosEntity = prestamosCanceladosEntity;
    }

    public List<PrestamoCanceladoDTO> getPrestamosCanceladosDTO() {
        return prestamosCanceladosDTO;
    }

    public void setPrestamosCanceladosDTO(List<PrestamoCanceladoDTO> prestamosCanceladosDTO) {
        this.prestamosCanceladosDTO = prestamosCanceladosDTO;
    }

}
