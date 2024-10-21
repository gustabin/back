package ar.com.santanderrio.obp.servicios.debinrecurrente.mock;

import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.PrestacionView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.List;

public class DebinRecurrenteArgMatcherMock {
    public static ArgumentMatcher<EmpresasDebinRecurrenteView> listaMismosVendedores(final List<VendedorView> mockVendedoresView) {
        return new ArgumentMatcher<EmpresasDebinRecurrenteView>() {
            @Override
            public boolean matches(Object argument) {
                final EmpresasDebinRecurrenteView req = (EmpresasDebinRecurrenteView) argument;
                if (mockVendedoresView.size() != req.getEmpresas().size()) {
                    return false;
                }
                // This was made to not modify the list received as parameter
                List<VendedorView> copyMockVendedoresView = new ArrayList<VendedorView>(mockVendedoresView);
                CollectionUtils.filter(copyMockVendedoresView, new Predicate() {
                    @Override
                    public boolean evaluate(Object mockVendedorView) {
                        VendedorView vendedor = (VendedorView) mockVendedorView;
                        for (VendedorView otroVendedor: req.getEmpresas()) {
                            if (mismoVendedor(vendedor, otroVendedor)) {
                                return false;
                            }
                        }
                        return true;
                    }
                });
                return copyMockVendedoresView.size() == 0;
            }

            private boolean mismoVendedor(VendedorView vendedor, VendedorView otroVendedor) {
                return vendedor.getCuit().equals(otroVendedor.getCuit())
                        && vendedor.getRubro().equals(otroVendedor.getRubro())
                        && vendedor.getNombreFantasia().equals(otroVendedor.getNombreFantasia());
            }
        };
    }

    public static ArgumentMatcher<VendedorPrestacionesView> listaMismoVendedorPrestaciones(final VendedorPrestacionesView mockVendedorPrestacionesView) {
        return new ArgumentMatcher<VendedorPrestacionesView>() {
            @Override
            public boolean matches(Object argument) {
                final VendedorPrestacionesView req = (VendedorPrestacionesView) argument;
                if (!mockVendedorPrestacionesView.getCuit().equals(req.getCuit()) ||
                !mockVendedorPrestacionesView.getNombreFantasia().equals(req.getNombreFantasia()) ||
                mockVendedorPrestacionesView.getServicios().size() != req.getServicios().size()) {
                    return false;
                }
                // This was made to not modify the list received as parameter
                List<PrestacionView> copyMockPrestacionesView = new ArrayList<PrestacionView>(mockVendedorPrestacionesView.getServicios());
                CollectionUtils.filter(copyMockPrestacionesView, new Predicate() {
                    @Override
                    public boolean evaluate(Object mockPrestacionView) {
                        PrestacionView prestacion = (PrestacionView) mockPrestacionView;
                        for (PrestacionView otraPrestacion: req.getServicios()) {
                            if (mismaPrestacion(prestacion, otraPrestacion)) {
                                return false;
                            }
                        }
                        return true;
                    }
                });
                return copyMockPrestacionesView.size() == 0;
            }

            private boolean mismaPrestacion(PrestacionView prestacion, PrestacionView otraPrestacion) {
                return prestacion.getNombre().equals(otraPrestacion.getNombre())
                        && (prestacion.getMinimo() == null ? otraPrestacion.getMinimo() == null : prestacion.getMinimo().equals(otraPrestacion.getMinimo()))
                        && (prestacion.getMaximo() == null ? otraPrestacion.getMaximo() == null : prestacion.getMaximo().equals(otraPrestacion.getMaximo()))
                        && prestacion.getTooltipReferencia().equals(otraPrestacion.getTooltipReferencia());
            }
        };
    }
}
