package ar.com.santanderrio.obp.servicios.api.transfers.recipients.mapper;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.AccountType;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaAgendaDestinatarioMapper {

    private static final String SANTANDER_TYPE = "SANTANDER";
    private static final String VACIO = "";
    private static final String RIO = "RIO";
    private static final String RIO_ACCOUNT_TYPE_FORMAT = "00";
    private static final int DESTINATION_BANK_MAX_LENGTH = 50;
    private static final int DESTINATION_CBU_MAX_LENGTH = 22;
    private static final int DESTINATION_ALIAS_MAX_LENGTH = 20;
    private static final int DESTINATION_DOCUMENT_TYPE_MAX_LENGTH = 2;
    private static final int DESTINATION_DOCUMENT_NUMBER_MAX_LENGTH = 11;
    private static final int DESTINATION_ACCOUNT_NUMBER_MAX_LENGTH = 12;
    private static final int DESTINATION_BRANCH_MAX_LENGTH = 4;
    private static final int DESTINATION_PHONE_MAX_LENGTH = 16;
    private static final int DESTINATION_ACCOUNT_CHARACTERISTIC_MAX_LENGTH = 10;
    private static final int DESTINATION_ACCOUNT_DESCRIPTION_MAX_LENGTH = 30;
    private static final int DESTINATION_ACCOUNT_TYPE_MAX_LENGTH = 2;
    private static final int DESTINATION_OWNER_MAX_LENGTH = 64;
    private static final int DESTINATION_CREATION_DATE_MAX_LENGTH = 26;
    private static final int DESTINATION_ACTIVATION_DATE_MAX_LENGTH = 26;
    private static final int DESTINATION_EMAIL_MAX_LENGTH = 100;
    private static final int DESTINATION_CHANNEL_MAX_LENGTH = 2;
    private static final int DESTINATION_SUBCHANNEL_MAX_LENGTH = 4;
    private static final int DESTINATION_AGENDA_TYPE_MAX_LENGTH = 3;

    private ConsultaAgendaDestinatarioMapper(){}

    public static ConsultaAgendaDestinatarioOutEntity toConsultaAgendaDestinatarioOutEntity(List<Recipient> recipients) {

        ConsultaAgendaDestinatarioOutEntity entity = new ConsultaAgendaDestinatarioOutEntity();

        entity.setDestinatarios(toDestinatariosList(recipients));
        entity.setCantidadRegistros((long) entity.getDestinatarios().size());

        return entity;

    }

    private static List<DestinatarioEntity> toDestinatariosList(List<Recipient> recipients) {

        List<DestinatarioEntity> destinatarios = new ArrayList<DestinatarioEntity>();

        for (Recipient recipient : recipients) {

            destinatarios.add(toDestinatarioEntity(recipient));

        }

        return destinatarios;

    }

    private static DestinatarioEntity toDestinatarioEntity(Recipient recipient) {

        DestinatarioEntity destinatario = new DestinatarioEntity();

        destinatario.setId(recipient.getId() != null ? recipient.getId() : VACIO);
        destinatario.setBancoDestinatario(addPadding(recipient.getDestinationBank(), DESTINATION_BANK_MAX_LENGTH));
        destinatario.setCbuDestinatario(addPadding(recipient.getDestinationCBU(), DESTINATION_CBU_MAX_LENGTH));
        destinatario.setAlias(addPadding(recipient.getCbuAlias(), DESTINATION_ALIAS_MAX_LENGTH));
        destinatario.setTipoDocumentoDestinatario(addPadding(recipient.getDestinationDocumentType(), DESTINATION_DOCUMENT_TYPE_MAX_LENGTH));
        destinatario.setDocumentoDestinatario(addPadding(recipient.getDestinationDocumentNumber(), DESTINATION_DOCUMENT_NUMBER_MAX_LENGTH));
        destinatario.setNumeroCuentaDestinatario(addPadding(formatAccountNumber(recipient.getDestinationAccountNumber()), DESTINATION_ACCOUNT_NUMBER_MAX_LENGTH));
        destinatario.setSucursalCuentaDestinatario(addPadding(recipient.getDestinationBranch(), DESTINATION_BRANCH_MAX_LENGTH));
        destinatario.setTelefonoDestinatario(addPadding(recipient.getDestinationPhone(), DESTINATION_PHONE_MAX_LENGTH));
        destinatario.setCaracteristicasCuentaDestinatario(addPadding(recipient.getDestinationAccountCharacteristic(), DESTINATION_ACCOUNT_CHARACTERISTIC_MAX_LENGTH));
        destinatario.setDescripcionCuentaDestinatario(addPadding(recipient.getDestinationAccountDescription(), DESTINATION_ACCOUNT_DESCRIPTION_MAX_LENGTH));
        destinatario.setTipoCuentaDestinatario(addPadding(getTipoCuentaDestinatario(recipient.getDestinationAccountNumber()), DESTINATION_ACCOUNT_TYPE_MAX_LENGTH));
        destinatario.setTitular(addPadding(recipient.getOwner(), DESTINATION_OWNER_MAX_LENGTH));
        destinatario.setTimestampAlta(addPadding(formatearFecha(recipient.getCreationDate()), DESTINATION_CREATION_DATE_MAX_LENGTH));
        destinatario.setTimestampActivacion(addPadding(formatearFecha(recipient.getActivationDate()), DESTINATION_ACTIVATION_DATE_MAX_LENGTH));
        destinatario.setDireccionCorreo(addPadding(recipient.getEmail(), DESTINATION_EMAIL_MAX_LENGTH));
        destinatario.setCanalOperacion(addPadding(recipient.getChannel(), DESTINATION_CHANNEL_MAX_LENGTH));
        destinatario.setSubCanalOperacion(addPadding(recipient.getSubchannel(), DESTINATION_SUBCHANNEL_MAX_LENGTH));
        destinatario.setTipoAgendaOcurrencia(addPadding(getTipoAgendaOcurrencia(recipient.getType()), DESTINATION_AGENDA_TYPE_MAX_LENGTH));

        return destinatario;

    }

    private static String addPadding(String field, int length) {

        return StringUtils.rightPad(StringUtils.defaultIfEmpty(field, VACIO), length, VACIO);

    }

    private static String formatAccountNumber(String accountNumber) {

        if (accountNumber != null) {


            return accountNumber.charAt(0) + RIO_ACCOUNT_TYPE_FORMAT + accountNumber.substring(3);

        }

        return VACIO;

    }

    private static String getTipoCuentaDestinatario(String accountNumber) {

        if (accountNumber != null) {

            String accountTypeDigits = StringUtils.substring(accountNumber, 1, 3);

            AccountType accountType = AccountType.getAccountTypeFromAccountDigits(accountTypeDigits);

            if (accountType != null) {

                return accountType.getAccountTypeValue();

            }

        }

        return VACIO;

    }

    private static String getTipoAgendaOcurrencia(String type) {

        if (type != null) {

            if (SANTANDER_TYPE.equalsIgnoreCase(type)) {

                return RIO;

            } else {

                return type;

            }

        }

        return VACIO;

    }

    private static String formatearFecha(String fechaOriginal) {

        try {

            String fechaSinZonaHoraria = StringUtils.substringBefore(fechaOriginal, "+");

            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");

            Date fecha = formatoEntrada.parse(fechaSinZonaHoraria);

            return formatoSalida.format(fecha);

        } catch (Exception e) {

            return null;

        }

    }

}
