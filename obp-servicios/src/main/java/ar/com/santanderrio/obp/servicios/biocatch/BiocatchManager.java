package ar.com.santanderrio.obp.servicios.biocatch;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.biocatch.dao.BiocatchDAO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.*;
import ar.com.santanderrio.obp.servicios.biocatch.model.*;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesPagoSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BiocatchManager {

    @Autowired
    private BiocatchDAO biocatchDAO;

    @Autowired
    private SesionParametros sesionParametros;

    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    private static final Logger LOGGER = LoggerFactory.getLogger(BiocatchManager.class);

    public BiocatchResponseDataDTO getScore(String nup, String ip, ActivityName activityName, ActivityType activityType)  {
        try {
            checkIsActive();
            GetScoreRequestDataDTO requestData = new GetScoreRequestDataDTO(sesionParametros.getCsid(), nup, ip, sesionParametros.getRsaGenericRequestData().getUserAgent(), PlatformType.WEB, Brand.OBP, activityName, activityType);
            return biocatchDAO.getScore(requestData);
        } catch (DAOException e) {
            logWarnException(e);
            return null;
        }
    }

    public BiocatchResponseDataDTO getScoreTransferencia(String nup, String ip, ActivityName activityName, ActivityType activityType, BiocatchTransferInfoDTO biocatchTransferInfoDTO)  {

        try {
            checkIsActive();
            GetScoreRequestDataDTO requestData = new GetScoreRequestDataDTO(sesionParametros.getCsid(), nup, ip, sesionParametros.getRsaGenericRequestData().getUserAgent(), PlatformType.WEB, Brand.OBP, activityName, activityType,biocatchTransferInfoDTO);
            return biocatchDAO.getScore(requestData);
        } catch (DAOException e) {
            logWarnException(e);
            return null;
        }
    }

    public BiocatchResponseDataDTO getScorePagoHaberes(String nup, String ip, ActivityName activityName, ActivityType activityType, ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple)  {

        LOGGER.info("Iniciando creacion de bactchFile para pago de haber multiple");
        try {
            checkIsActive();
            String creationDate = TransferenciaUtil.convertirFormatoFecha(comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getFecha());
            BatchFile batchFile = new BatchFile (creationDate,creationDate,TransferenciaUtil.generateRandomId(),
                    comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().getPagoHaberesEmpleadosView().size(),comprobantePagoHaberesPagoSimpleMultiple.getPagoHaberesPagoSimpleMultipleView().amountTotalPayees());
            GetScoreRequestDataDTO requestData = new GetScoreRequestDataDTO(sesionParametros.getCsid(), nup, ip, sesionParametros.getRsaGenericRequestData().getUserAgent(), PlatformType.WEB, Brand.OBP, activityName, activityType,batchFile);
            return biocatchDAO.getScore(requestData);
        } catch (DAOException e) {
            logWarnException(e);
            return null;
        }
    }


    public BiocatchResponseDataDTO getScoreLogin(String nup, String ip) {
        try {
            checkIsActive();
            BaseRequestDataDTO requestData = new BaseRequestDataDTO(sesionParametros.getCsid(), nup, ip, sesionParametros.getRsaGenericRequestData().getUserAgent(), PlatformType.WEB, Brand.OBP);
            return biocatchDAO.getScoreLogin(requestData);
        } catch (DAOException e){
            logWarnException(e);
            return null;
        }
    }

   public void init(String nup, String ip, ActivityName activityName){
        try{
            checkIsActive();
            InitRequestDataDTO requestData = new InitRequestDataDTO(activityName, sesionParametros.getCsid(), nup, ip, sesionParametros.getRsaGenericRequestData().getUserAgent(), PlatformType.WEB, Brand.OBP);
            biocatchDAO.init(requestData);
        } catch (DAOException e){
            logWarnException(e);
        }
   }

    private void checkIsActive() throws DAOException {
        if(!moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.ACTIVAR_BIOCATCH).tienePermisoDeVisibilidad()){
            throw new DAOException("Biocatch inactivo");
        }
    }

    private void logWarnException(DAOException e) {
        LOGGER.warn("Biocatch ha respondido con errores {}", e.getMessage());
    }
}
