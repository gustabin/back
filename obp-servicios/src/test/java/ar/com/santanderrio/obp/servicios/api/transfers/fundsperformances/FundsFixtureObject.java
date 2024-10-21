package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto.FundsPerformancesResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

import java.util.ArrayList;
import java.util.List;

public class FundsFixtureObject {

    public static TransferenciaView getTransferenciaViewCase1() {

        TransferenciaView transferenciaView = new TransferenciaView();

        transferenciaView.setCbu("0007829000008927128987");
        transferenciaView.setCuitCuil("20-348759783-7");

        return transferenciaView;
    }

    public static TransferenciaView getTransferenciaViewCase2() {

        TransferenciaView transferenciaView = new TransferenciaView();

        transferenciaView.setCbu("0077829000008927128987");
        transferenciaView.setCuitCuil("20-348759783-7");

        return transferenciaView;
    }

    public static TransferenciaView getTransferenciaViewCase3() {

        TransferenciaView transferenciaView = new TransferenciaView();

        transferenciaView.setCbu("0007829000008927128987");
        transferenciaView.setCuitCuil(null);

        return transferenciaView;
    }

    public static Cliente getClienteCase1() {

        Cliente cliente = new Cliente();

        cliente.setNumeroCUILCUIT("20-348759783-7");

        return cliente;

    }

    public static Cliente getClienteCase2() {

        Cliente cliente = new Cliente();

        cliente.setNumeroCUILCUIT("20-28047593-7");

        return cliente;

    }

    public static Cliente getClienteCase3() {

        Cliente cliente = new Cliente();

        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta.setProductoAltair("60");
        cuenta2.setProductoAltair("50");

        List<Cuenta> cuentaList = new ArrayList<Cuenta>();

        cuentaList.add(cuenta);
        cuentaList.add(cuenta2);

        cliente.setCuentasRetail(cuentaList);

        return cliente;

    }

    public static Cliente getClienteCase4() {

        Cliente cliente = new Cliente();

        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta.setProductoAltair("40");
        cuenta2.setProductoAltair("50");

        List<Cuenta> cuentaList = new ArrayList<Cuenta>();

        cuentaList.add(cuenta);
        cuentaList.add(cuenta2);


        return cliente;

    }

    public static Cliente getClienteCase5() {

        Cliente cliente = new Cliente();

        cliente.setNumeroCUILCUIT(null);

        return cliente;

    }

    public static Cliente getClienteCase6AccountBP() {

        Cliente cliente = new Cliente();

        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta.setProductoAltair("60");
        cuenta2.setProductoAltair("50");

        CuentaBancaPrivada cuentaBancaPrivada1 = new CuentaBancaPrivada();
        CuentaBancaPrivada cuentaBancaPrivada2 = new CuentaBancaPrivada();

        cuentaBancaPrivada1.setCuentaTitulo(cuenta);
        cuentaBancaPrivada2.setCuentaTitulo(cuenta2);

        List<CuentaBancaPrivada> cuentaBPList = new ArrayList<CuentaBancaPrivada>();

        cuentaBPList.add(cuentaBancaPrivada1);
        cuentaBPList.add(cuentaBancaPrivada2);

        cliente.setCuentasBancaPrivada(cuentaBPList);

        return cliente;

    }

    public static Cliente getClienteCase7AccountBP() {

        Cliente cliente = new Cliente();

        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta.setProductoAltair("30");
        cuenta2.setProductoAltair("50");

        CuentaBancaPrivada cuentaBancaPrivada1 = new CuentaBancaPrivada();
        CuentaBancaPrivada cuentaBancaPrivada2 = new CuentaBancaPrivada();

        cuentaBancaPrivada1.setCuentaTitulo(cuenta);
        cuentaBancaPrivada2.setCuentaTitulo(cuenta2);

        List<CuentaBancaPrivada> cuentaBPList = new ArrayList<CuentaBancaPrivada>();

        cuentaBPList.add(cuentaBancaPrivada1);
        cuentaBPList.add(cuentaBancaPrivada2);

        cliente.setCuentasBancaPrivada(cuentaBPList);

        return cliente;

    }

    public static FundsPerformancesResponse getFundsPerformancesResponse() {

        FundsPerformancesResponse fundsPerformancesResponse = new FundsPerformancesResponse();

        FundsPerformancesResponse.FundResult fundResult = new FundsPerformancesResponse.FundResult();
        fundResult.setAnnualizedLast30Day("123%");
        List<FundsPerformancesResponse.FundResult> fundResultList = new ArrayList<FundsPerformancesResponse.FundResult>();
        fundResultList.add(fundResult);

        fundsPerformancesResponse.setResults(fundResultList);
        fundsPerformancesResponse.setTotal(1);
        fundsPerformancesResponse.setCurrentDate("2024-05-07T17:58:43-03:00");

        return fundsPerformancesResponse;
    }

    public static FundsPerformancesResponse getFundsPerformancesResponseCase2() {

        FundsPerformancesResponse fundsPerformancesResponse = new FundsPerformancesResponse();

        List<FundsPerformancesResponse.FundResult> fundResultList = new ArrayList<FundsPerformancesResponse.FundResult>();

        fundsPerformancesResponse.setResults(fundResultList);
        fundsPerformancesResponse.setTotal(1);
        fundsPerformancesResponse.setCurrentDate("2024-05-07T17:58:43-03:00");

        return fundsPerformancesResponse;
    }

    public static FundsPerformancesResponse getFundsPerformancesResponseCase3() {

        FundsPerformancesResponse fundsPerformancesResponse = new FundsPerformancesResponse();

        FundsPerformancesResponse.FundResult fundResult = new FundsPerformancesResponse.FundResult();
        List<FundsPerformancesResponse.FundResult> fundResultList = new ArrayList<FundsPerformancesResponse.FundResult>();
        fundResultList.add(fundResult);

        fundsPerformancesResponse.setResults(fundResultList);
        fundsPerformancesResponse.setTotal(1);
        fundsPerformancesResponse.setCurrentDate("2024-05-07T17:58:43-03:00");

        return fundsPerformancesResponse;
    }
}
