package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto.FundsPerformancesResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FundsMessageHelper implements IFundsMessageHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundsMessageHelper.class);

    private final FundsPerformances fundsPerformances;

    private static final String ACCOUNT_TITLE_ID = "60";
    private static final String FUND_ID = "6";
    private static final String EMPTY_STRING = "";
    private static final String HYPHEN = "-";

    @Autowired
    public FundsMessageHelper(FundsPerformances fundsPerformances) {
        this.fundsPerformances = fundsPerformances;
    }

    @Override
    public boolean showFundMessage(TransferenciaView transferenciaView, Cliente cliente) {

        return ISBANStringUtils.validarCVU(transferenciaView.getCbu()) && cuitMatches(transferenciaView, cliente);
    }

    @Override
    public boolean hasTitleAccountRetail(Cliente cliente) {

        boolean isTitleAccountRtl = false;

        for (Cuenta cuenta : cliente.getCuentasRetail()) {

            if (ACCOUNT_TITLE_ID.equals(cuenta.getProductoAltair())) {

                isTitleAccountRtl = true;
                break;
            }

        }

        return isTitleAccountRtl;
    }

    @Override
    public boolean hasTitleAccountBP(Cliente cliente) {

        boolean isTitleAccountBP = false;

        for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {

            Cuenta cuenta = cuentaBP.getCuentaTitulo();

            if (ACCOUNT_TITLE_ID.equals(cuenta.getProductoAltair())) {

                isTitleAccountBP = true;
                break;
            }

        }

        return isTitleAccountBP;
    }

    @Override
    public String getPerformanceFund() {

        try {

            FundsPerformancesResponse fundsPerformancesResponse = fundsPerformances.getPerformancesById(FUND_ID);

            String performanceFund = EMPTY_STRING;

            if (canGetAnnualizedLast30Day(fundsPerformancesResponse)) {

                performanceFund = fundsPerformancesResponse.getResults().get(0).getAnnualizedLast30Day();
            }

            LOGGER.info("Performance fci con id 6: {}", performanceFund);

            return performanceFund;

        } catch (FundsPerformancesException e) {

            LOGGER.error("No se pudo obtener el performance del fondo con id 6", e);
            return EMPTY_STRING;
        }

    }

    private boolean canGetAnnualizedLast30Day(FundsPerformancesResponse fundsPerformancesResponse) {
        return !fundsPerformancesResponse.getResults().isEmpty() &&
                fundsPerformancesResponse.getResults().get(0).getAnnualizedLast30Day() != null;
    }

    private boolean cuitMatches(TransferenciaView transferenciaView, Cliente cliente) {

        boolean cuitMatches = false;
        String recipientCuit;
        String originCuit;

        if (cuitsNotNull(transferenciaView, cliente)) {

            recipientCuit = transferenciaView.getCuitCuil().replace(HYPHEN, EMPTY_STRING);
            originCuit = cliente.getNumeroCUILCUIT().replace(HYPHEN, EMPTY_STRING);

            cuitMatches = recipientCuit.equals(originCuit);
        }

        return cuitMatches;
    }

    private boolean cuitsNotNull(TransferenciaView transferenciaView, Cliente cliente) {
        return transferenciaView.getCuitCuil() != null && cliente.getNumeroCUILCUIT() != null;
    }
}
