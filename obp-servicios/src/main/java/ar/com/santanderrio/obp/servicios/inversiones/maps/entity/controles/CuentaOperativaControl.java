package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemCuentaOperativa;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemCuentaOperativaComprobante;

public class CuentaOperativaControl extends ListaControl<ItemCuentaOperativa> {
    
    
    @Override
    public String valorComprobante() {
        
        ItemCuentaOperativa item = itemSeleccionado();
        if(item!= null && item.getValor() != null) {
                return item.getNumeroCtaOperativa();
        }
        return "";
    }
    
    @Override
    public ItemComprobante imprimirComprobante(){       
        ItemCuentaOperativaComprobante itemComprobante = new ItemCuentaOperativaComprobante();
        itemComprobante.setEtiqueta(this.getEtiqueta());
        itemComprobante.setValor(this.numeroCuenta());
        itemComprobante.setHastaValor(this.tipoCuenta());
        return itemComprobante;
    }
    
    public String tipoCuenta() {

        ItemCuentaOperativa item = itemSeleccionado();
        if(item!= null && item.getTipoCtaOperativa() != null) {
                return visualizarTipoCuenta(item);
        }
        return "";
    }

    public String numeroCuenta() {
        
        ItemCuentaOperativa item = itemSeleccionado();
        if(item!= null && item.getNumeroCtaOperativa() != null && item.getSucursalCtaOperativa() != null) {
            return ISBANStringUtils.formatearNroCuentaProductoConSucursalMaps(item.getNumeroCtaOperativa(), item.getSucursalCtaOperativa());
        }
        return "";
    }
    
    
    
    private String visualizarTipoCuenta(ItemCuentaOperativa item) {
        
        if ("00".equals(item.getTipoCtaOperativa())) {
            return "Cuenta corriente en $";
        }
        if ("01".equals(item.getTipoCtaOperativa())) {
            return "Caja de ahorro en $";
        }
        if ("03".equals(item.getTipoCtaOperativa())) {
            return "Cuenta corriente en u$s";
        }
        if ("04".equals(item.getTipoCtaOperativa())) {
            return "Caja de ahorro en u$s";
        }
        if ("02".equals(item.getTipoCtaOperativa()) ||
                "09".equals(item.getTipoCtaOperativa())||
                        "10".equals(item.getTipoCtaOperativa())) {
            return "Cuenta \u00FAnica";
        }
        return null;
    }

}
