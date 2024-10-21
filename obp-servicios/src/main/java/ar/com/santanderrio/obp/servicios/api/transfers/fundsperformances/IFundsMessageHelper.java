package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

public interface IFundsMessageHelper {

    boolean showFundMessage(TransferenciaView transferenciaView, Cliente cliente);

    boolean hasTitleAccountRetail(Cliente cliente);

    boolean hasTitleAccountBP(Cliente cliente);

    String getPerformanceFund();
}
