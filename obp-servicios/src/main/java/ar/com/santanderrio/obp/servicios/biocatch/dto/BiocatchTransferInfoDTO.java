package ar.com.santanderrio.obp.servicios.biocatch.dto;

import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.transferencias.utils.TransferUtils;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class BiocatchTransferInfoDTO {

    public static final Logger LOGGER = LoggerFactory.getLogger(BiocatchTransferInfoDTO.class);
    private static final String ENTIDADSANTANDER= "0072";
    private String origenCBU;
    private String destinoCBU;
    private BigDecimal importe;
    private String cuitOrigen;
    private String cuitDestino;
    private LocalDate fechaCreacionCuenta;

    private static final String STRINGVACIO ="";

    public String getOrigenCBU() { return origenCBU; }

    public void setOrigenCBU(String origenCBU) {this.origenCBU = origenCBU; }

    public String getDestinoCBU() {return destinoCBU;}

    public void setDestinoCBU(String destinoCBU) {this.destinoCBU = destinoCBU;}

    public BigDecimal getImporte() {return importe;}

    public void setImporte(BigDecimal importe) {this.importe = importe;}

    public String getCuitOrigen() {return cuitOrigen;}

    public void setCuitOrigen(String cuitOrigen) {this.cuitOrigen = cuitOrigen;}

    public String getCuitDestino() {return cuitDestino;}

    public void setCuitDestino(String cuitDestino) {this.cuitDestino = cuitDestino;}

    public LocalDate getFechaCreacionCuenta() {return fechaCreacionCuenta;}

    public void setFechaCreacionCuenta(LocalDate fechaCreacionCuenta) {this.fechaCreacionCuenta = fechaCreacionCuenta;}


    public void setBiocatchTransferInfoDTO(TransferenciaView transferenciaView,AccountsApi accountsApi){

        this.cuitOrigen = TransferUtils.limpiarCUIT(transferenciaView.getCuitCliente());
        this.cuitDestino =transferenciaView.getCuitCuil();
        this.importe =new BigDecimal(transferenciaView.getImporte());

        String accountIdOrigin = getAccountNumberSesionCliente(transferenciaView);
        String nupCliente = transferenciaView.getNupCliente();

        try {

            AccountEntity originAccount = accountsApi.getAccountByAccountId(accountIdOrigin, nupCliente);
            this.origenCBU =originAccount.getCbuDetails().getNumber();
            this.destinoCBU = obtenerRecipientCbu(transferenciaView,accountsApi);
            this.fechaCreacionCuenta = new LocalDate(StringUtils.substringBefore(originAccount.getCreationDate(), "T"));

        }catch (Exception e){

            LOGGER.error("Error al invocar el servicio de account-api",e);
            this.origenCBU = STRINGVACIO;
            this.destinoCBU = STRINGVACIO;
            this.fechaCreacionCuenta =null;
        }

    }

    private String getAccountNumberSesionCliente(TransferenciaView transferenciaView){

        StringBuilder resultado = new StringBuilder(ENTIDADSANTANDER.length() + 20);
        resultado.append(ENTIDADSANTANDER)
                .append(TransferenciaUtil.obtenerContratoAltair(transferenciaView.getNroCuenta(),transferenciaView.getTipoCuentaEnum().getAbreviatura()));

        return resultado.toString();
    }

    private String obtenerRecipientCbu(TransferenciaView transferenciaView,AccountsApi accountsApi) {

        LOGGER.info("Obteniendo el cbu de destino: ");

        if (transferenciaView.getIsRioRio()) {
            LOGGER.info("Transferencia Santander-Santander: Se consulta a account-api para obtener el cbu de destino");
            StringBuilder accountIdDestino = new StringBuilder(ENTIDADSANTANDER.length() + 20);
            accountIdDestino.append(ENTIDADSANTANDER)
                    .append(TransferenciaUtil.obtenerContratoAltair(transferenciaView.getNroCuentaDestino(),transferenciaView.getTipoCuentaDestino()));

            LOGGER.info("accountIdDestino: {}",accountIdDestino);

            try {
                AccountEntity recipientAccount = accountsApi.getAccountPublicByAccountId(String.valueOf(accountIdDestino), transferenciaView.getMonedaAltair());
                return recipientAccount.getCbuDetails().getNumber();
            }catch (Exception e) {
                LOGGER.error("Error en la consulta de Account-api: ", e);
                return STRINGVACIO;
            }
        }
        LOGGER.info("Transferencia a otros bancos - Obteniendo el cbu de destino: ");
        return transferenciaView.getCbu();
    }
}
