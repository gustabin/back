package ar.com.santanderrio.obp.servicios.api.transfers.recipients;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.mapper.ConsultaAgendaDestinatarioMapper;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.AccountType;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsultaAgendaDestinatarioMapperTest {

    @Test
    public void givenEmptyRecipientList_whenToConsultaAgendaDestinatarioOutEntity_ThenDestinatariosListIsEmpty() {

        ConsultaAgendaDestinatarioOutEntity entity = ConsultaAgendaDestinatarioMapper.toConsultaAgendaDestinatarioOutEntity(new ArrayList<Recipient>());

        Assert.assertEquals(Boolean.TRUE, entity.getDestinatarios().isEmpty());

    }

    @Test
    public void givenTwoRecipients_whenToConsultaAgendaDestinatarioOutEntity_ThenCantidadDeRegistrosSizeEqualsTwo() {

        ConsultaAgendaDestinatarioOutEntity entity = ConsultaAgendaDestinatarioMapper.toConsultaAgendaDestinatarioOutEntity(getRecipientListOfSizetwo());

        Assert.assertEquals(new Long(2), entity.getCantidadRegistros());

    }

    @Test
    public void givenRecipientWithValues_whenToConsultaAgendaDestinatarioOutEntity_ThenAgendaAndTipoCuentaMappingOk() {

        ConsultaAgendaDestinatarioOutEntity entity = ConsultaAgendaDestinatarioMapper.toConsultaAgendaDestinatarioOutEntity(getRecipientsListWithRecipientValues());

        Assert.assertEquals("RIO", entity.getDestinatarios().get(0).getTipoAgendaOcurrencia());
        Assert.assertEquals(AccountType.PESOS_ACCOUNT.getAccountTypeValue(), entity.getDestinatarios().get(0).getTipoCuentaDestinatario());

    }

    @Test
    public void givenRecipientWithValues_whenToConsultaAgendaDestinatarioOutEntity_ThenFechaCreacionActivacionFormatOk() {

        ConsultaAgendaDestinatarioOutEntity entity = ConsultaAgendaDestinatarioMapper.toConsultaAgendaDestinatarioOutEntity(getRecipientsListWithRecipientValues());

        Assert.assertEquals("2022-10-19-12.05.49.573   ", entity.getDestinatarios().get(0).getTimestampAlta());
        Assert.assertEquals("2022-11-20-12.05.49.573   ", entity.getDestinatarios().get(0).getTimestampActivacion());

    }

    private List<Recipient> getRecipientListOfSizetwo() {

        List<Recipient> recipients = new ArrayList<Recipient>();

        recipients.add(new Recipient());
        recipients.add(new Recipient());

        return recipients;

    }

    private List<Recipient> getRecipientsListWithRecipientValues() {

        Recipient recipient = new Recipient();

        recipient.setType("SANTANDER");
        recipient.setDestinationAccountNumber("005000012345");
        recipient.setCreationDate("2022-10-19T12:05:49.573+00:00");
        recipient.setActivationDate("2022-11-20T12:05:49.573+00:00");

        List<Recipient> recipients = new ArrayList<Recipient>();

        recipients.add(recipient);

        return recipients;

    }

}
