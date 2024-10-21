package ar.com.santanderrio.obp.servicios.buscadorempresa.entities;

import java.util.HashSet;
import java.util.Set;

import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

public final class ListaEmpresasRecargaCelularMock {
    
    private ListaEmpresasRecargaCelularMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static Set<MedioPago> generarListaEmpresasRecargaCelular() {
        
        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
        
        MedioPago movistar = generarEmpresaRecargaCelular();
        movistar.setFiid("REMO");
        movistar.setNombreFantasia("RECARGA MOVISTAR");
        listaEmpresas.add(movistar);
        
        MedioPago personal = generarEmpresaRecargaCelular();
        personal.setFiid("REPE");
        personal.setNombreFantasia("RECARGA PERSONAL");
        listaEmpresas.add(personal);

        MedioPago claro = generarEmpresaRecargaCelular();
        claro.setFiid("RECL");
        claro.setNombreFantasia("RECARGA CLARO");
        listaEmpresas.add(claro);

        MedioPago nextel = generarEmpresaRecargaCelular();
        nextel.setFiid("RENX");
        nextel.setNombreFantasia("RECARGA NEXTEL");
        listaEmpresas.add(nextel);

        MedioPago tuenti = generarEmpresaRecargaCelular();
        tuenti.setFiid("QUAM");
        tuenti.setNombreFantasia("RECARGA TUENTI");
        listaEmpresas.add(tuenti);

        return listaEmpresas;
    }
    
    public static MedioPago generarEmpresaRecargaCelular() {
        MedioPago empresa = new MedioPago();
        empresa.setPesGifFactura("SU CODAREA(SIN 0)+NRO(SIN15) CONSTA DE 10 DIGITOS");
        empresa.setPesIdentificacion("Codarea(sin 0)+nro(sin15)");
        empresa.setRubroFantasia("RECARGA DE CELULARES");
        return empresa;
    }
    
}
