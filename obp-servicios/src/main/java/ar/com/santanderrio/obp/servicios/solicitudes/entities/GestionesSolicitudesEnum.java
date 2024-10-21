/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Enum GestionesCYPEnum.
 */
public enum GestionesSolicitudesEnum {

    /** The cajaahorro. */
    CAJAAHORRO("cajaAhorro", false, AccionController.IR_INICIO_SOLICITUDES_CAJA_AHORRO, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The cajaahorro. */
    CAJAAHORRODOLARES("cajaAhorroDolares", false, AccionController.IR_INICIO_SOLICITUDES_CAJA_AHORRO_DOLARES, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The cajaahorro. */
    CAJAAHORRODOLARESMF("cajaAhorroMF", false, AccionController.IR_INICIO_SOLICITUDES_CAJA_AHORRO_MICROFRONT, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The cuentatitulos. */
    CUENTATITULOS("cuentaTitulos", false, AccionController.IR_INICIO_SOLICITUDES_CUENTA_TITULOS, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esSaldoPesos();
        }
    },
    
    /** The cuentatitulos. */
    CUENTATITULOSREP("cuentaTitulosRep", false, AccionController.IR_INICIO_SOLICITUDES_CUENTA_TIT_REP, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esSaldoPesos();
        }
    },
    
    /** The cuentatitulos. */
    CUENTATITULOSENRI("cuentaTitulosEnri", false, AccionController.IR_INICIO_SOLICITUDES_CUENTA_TIT_ENRI, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esSaldoPesos();
        }
    },
    /** The cuentatitulos. */
    BAJAPRODUCTOCUENTA("bajaProductoCuenta", false, AccionController.IR_INICIO_BAJA_PRODUCTO_CUENTA, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return "TI".equals(cuenta.getCodigoTitularidad());
        }
    },
    
	/** The getnet. */
	GETNET("getnet", false, AccionController.IR_INICIO_GETNET, false, true) {

		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},

    /** The chequeras. */
    CHEQUERAS("chequeras", false, AccionController.IR_INICIO_SOLICITUDES_CHEQUERAS, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return "09".equals(cuenta.getTipoCuentaSinUnificar()) && "TI".equals(cuenta.getCalidadDeParticipacion());
        }
    },

    /** The limiteextraccion. */
    LIMITEEXTRACCION("limiteExtraccionCYP", false, AccionController.IR_INICIO_SOLICITUDES_LIMITE_EXTRACCION, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum();
        }
    },

    /** The reimpresiontarjetas. */
    REIMPRESIONTARJETAS("reimpresionTarjetasCYP", false, AccionController.IR_INICIO_SOLICITUDES_REIMPRESION_TARJETAS,
            false, false) {

        private String celularVinculado = "0000n0";

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            boolean valido = false;
            if (TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum()) {
                valido = true;
            }

            if (cuenta.esTarjetaDeCredito() && cuenta.esTitular() && verificarCondiciones(cuenta)) {
                valido = true;
            }
            return valido;
        }

        private boolean verificarCondiciones(Cuenta cuenta) {
            boolean valido = false;

            if (celularVinculado.equals(cuenta.getGrupoAfinidad())
                    && (cuenta.esTitular() && (cuenta.esTarjetaVisa() || cuenta.esTarjetaAmex()))
                    && !(TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum()
                            && (cuenta.esTitular() || cuenta.getCodigoTitularidad() == "C"))) {
                valido = true;
            }
            return valido;
        }
    },

    /** The upgrade. */
    UPGRADE("upgrade", false, AccionController.IR_INICIO_SOLICITUDES_UPGRADE, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return false;
        }
    },

    /** The tarjetavisa. */
    TARJETAVISA("tarjetaVisa", true, AccionController.IR_INICIO_SOLICITUDES_TARJETA_VISA, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esTitularTarjetaVisa() && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante())
                    && !cuenta.esVisaRecargable();
        }
    },

    /** The tarjetaamex. */
    TARJETAAMEX("tarjetaAmex", true, AccionController.IR_INICIO_SOLICITUDES_TARJETA_AMEX, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esTitularTarjetaAmex() && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante());
        }
    },
    TARJETAMASTER("tarjetaMaster", true , AccionController.IR_INICIO_SOLICITUDES_TARJETA_MASTER, true , false){
    	
    	@Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return cuenta.esTitularTarjetaMaster() && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante());
        }
    },

    /** The tarjetacredito. */
    TARJETACREDITO("tarjetaCredito", false, AccionController.IR_INICIO_SOLICITUDES_TARJETA_CREDITO, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return false;// "tarjetaVisa" || "tarjetaAmex"
        }
    },

    /** The tarjetacreditoadicional. */
    TARJETACREDITOADICIONAL("tarjetaCreditoAdicional", false,
            AccionController.IR_INICIO_SOLICITUDES_TARJETA_CREDITO_ADICIONAL, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return (cuenta.esTitularTarjetaAmex() || (cuenta.esTitularTarjetaVisa() && !cuenta.esVisaRecargable()))
                    && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante());
        }
    },

    /** The bajaproductotarjeta. */
    BAJAPRODUCTOTARJETA("bajaProductoTarjeta", false, AccionController.IR_INICIO_BAJAPRODUCTO, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())
                    && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante());
        }
    },
    /** The bajatarjetas. */
    BAJATARJETAS("tarjetaBajaTarjetas", false, AccionController.IR_INICIO_BAJATARJETAS, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())
                    && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante());
        }
    },
    /** The tarjetavisarecargable. */
    TARJETAVISARECARGABLE("tarjetaVisaRecargable", true, AccionController.IR_INICIO_SOLICITUDES_TARJETA_VISA_RECARGABLE,
            true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())
                    && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante()) && cuenta.esVisaRecargable();
        }
    },

    /** The tarjetavisarecargableadicional. */
    TARJETAVISARECARGABLEADICIONAL("tarjetaVisaRecargableAdicional", false,
            AccionController.IR_INICIO_SOLICITUDES_TARJETA_VISA_RECARGABLE_ADICIONAL, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())
                    && nroOrdenFirmante.equals(cuenta.getNroOrdenFirmante()) && cuenta.esVisaRecargable();
        }
    },

    /** The reimpresiontarjetas. */
    REIMPRESION_TARJETAS("reimpresionTarjetasT", false, AccionController.IR_INICIO_SOLICITUDES_REIMPRESION_TARJETAS,
            true, false) {

        private String celularVinculado = "0000n0";

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            boolean valido = false;
            if (TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum()) {
                valido = true;
            }

            if (cuenta.esTarjetaDeCredito() && cuenta.esTitular() && verificarCondiciones(cuenta)) {
                valido = true;
            }
            return valido;
        }

        private boolean verificarCondiciones(Cuenta cuenta) {
            boolean valido = false;

            if (celularVinculado.equals(cuenta.getGrupoAfinidad())
                    && (cuenta.esTitular() && (cuenta.esTarjetaVisa() || cuenta.esTarjetaAmex()))
                    && !(TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum()
                            && (cuenta.esTitular() || cuenta.getCodigoTitularidad() == "C"))) {
                valido = true;
            }
            return valido;
        }
    },

    /** The habilitartarjetasporviaje. */
    HABILITARTARJETASPORVIAJE("habilitarTarjetasPorViaje", false,
            AccionController.IR_INICIO_SOLICITUDES_HABILITAR_TARJETAS_POR_VIAJE, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum() || cuenta.esTarjetaDeCredito();
        }
    },

    /** The afinidad. */
    AFINIDAD("afinidad", false, AccionController.IR_INICIO_SOLICITUDES_AFINIDAD, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return (cuenta.esTitularTarjetaVisa() || cuenta.esTitularTarjetaAmex())
                    && TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad());
        }
    },
    /** The limiteextraccion. */
    LIMITEEXTRACCION_T("limiteExtraccionT", false, AccionController.IR_INICIO_SOLICITUDES_LIMITE_EXTRACCION, true, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return TipoCuenta.BANELCO == cuenta.getTipoCuentaEnum();
        }
    },

    /** The solicitud de aummento de limite de transferencia. */
    SOLAUMENTOLIMTRANSF("aumentoLimiteTransferencia", false, AccionController.IR_INICIO_SOLICITUDES_AFINIDAD, false, false) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The cuentaExterior. */
    CUENTAEXTERIOR("cuentaExterior", false, AccionController.IR_INICIO_SOLICITUDES_CUENTAEXTERIOR, false, false) {

		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},

    /** The monederoActivar. */
    MONEDEROACTIVAR("monederoActivar", false, AccionController.IR_INICIO_TARJETA_MONEDERO, false, true) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The monederoAltaAdicional. */
    MONEDEROALTAADICIONAL("monederoAltaAdicional", false, AccionController.IR_INICIO_TARJETA_MONEDERO, false, true) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The monederoAlta. */
    MONEDEROALTA("monederoAlta", false, AccionController.IR_INICIO_TARJETA_MONEDERO, false, true) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

    /** The billeteraVirtual. */
    BILLETERAVIRTUAL("billeteraVirtual", false, AccionController.IR_INICIO_BILLETERA_VIRTUAL, false, true) {

        @Override
        public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
            return true;
        }
    },

	/** The botonPago. */
	BOTONPAGO("botonDePago", false, AccionController.IR_INICIO_BOTON_PAGO, false, true) {

		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},

	CONSENTIMIENTO_BILLETERAS("billeterasCuentasVinculadas", false, AccionController.IR_CONSENTIMIENTO_BILLETERAS, false, true) {
		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},

	ADHESIONWOMEN("adhesionWomen", false, AccionController.IR_INICIO_ADHESION_WOMEN, true, false) {
		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			boolean correspondeCard = false;
			if (StringUtils.isNotEmpty(habilitacionWomen)) {
				String[] tarjetas = this.habilitacionWomen.split("\\|");
			
				for (String tarjeta : tarjetas) {
					if (Integer.parseInt(tarjeta) == cuenta.getTipoCuentaEnum().getCodigo() &&
						tarjetaActiva.equals(cuenta.getEstadoTarjetaCredito().trim()) &&
						tarjetaTitular.equals(cuenta.getCodigoTitularidad().trim()) &&
						!claseCuenta.equals(cuenta.getClaseCuenta())) {
						correspondeCard = true;
					}
				}
			}
			return correspondeCard;
		}
	},

	CLAVEBANELCO("tarjetaClaveBanelco", false, AccionController.IR_CLAVE_BANELCO, true, false) {
		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},
	
	DENUNCIAYREPOSICION("tarjetaDenuncias", false, AccionController.IR_DENUNCIA_Y_REPO, true, false) {
		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},
	/** The documentacionCuenta. */
	DOCUMENTACIONCUENTA("documentacionCuenta", false, AccionController.IR_DOCUMENTACION_CUENTA, false, false) {

		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			return true;
		}
	},
	/** The documentacionCuenta. */
	BENEFICIOTRANSFERITUSUELDO("transferiTuSueldo", false, AccionController.IR_BENEFICIO_TRANSFERI_TU_SUELDO, false, false) {

		@Override
		public boolean cumpleCondicionesCuenta(Cuenta cuenta) {
			boolean correspondeCuenta = false;
			if (cuenta.esCuentaUnica()) {
				correspondeCuenta = true;
			}
			return correspondeCuenta;
		}
	},
	;

    /** The nro orden firmante. */
    protected String nroOrdenFirmante = "001";
    
	protected String habilitacionWomen;
	
	protected String tarjetaActiva = "20";
	
	protected String tarjetaTitular = "TI";
	
	protected String claseCuenta = "T";

    /** The tipo. */
    private String tipo;

    /** The valor inicial. */
    private boolean valorInicial;

    /** The accion. */
    private AccionController accion;

    /** The es tarjeta. */
    private boolean esTarjeta;
    
    /** The es otro medio de pago. */
    private boolean esOtroMedioDePago;


    /**
     * Instantiates a new gestiones CYP enum.
     *
     * @param tipo
     *            the tipo
     * @param valorInicial
     *            the valor inicial
     * @param accion
     *            the accion
     * @param esTarjeta
     *            the es tarjeta
     * @param esOtroMedioDePago
     * 			  the es otro medio de pago
     */
    GestionesSolicitudesEnum(String tipo, boolean valorInicial, AccionController accion, boolean esTarjeta, boolean esOtroMedioDePago) {
        this.tipo = tipo;
        this.valorInicial = valorInicial;
        this.accion = accion;
        this.esTarjeta = esTarjeta;
        this.esOtroMedioDePago = esOtroMedioDePago;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesEnum#
     * getTipo()
     */

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesEnum#
     * getValorInicial()
     */

    /**
     * Gets the valor inicial.
     *
     * @return the valor inicial
     */
    public boolean getValorInicial() {
        return valorInicial;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesEnum#
     * getAccion()
     */

    /**
     * Gets the accion.
     *
     * @return the accion
     */
    public AccionController getAccion() {
        return accion;
    }
    
    
    /**
	 * Checks if is es otro medio de pago.
	 *
	 * @return the esOtroMedioDePago
	 */
	public boolean isEsOtroMedioDePago() {
		return esOtroMedioDePago;
	}

	/**
     * Obtener mapa inicial de cuentas y paquetes.
     *
     * @return the map
     */
    public static Map<GestionesSolicitudesEnum, Boolean> obtenerMapaInicialCuentasPaquetes() {
        Map<GestionesSolicitudesEnum, Boolean> gestionesMap = new LinkedHashMap<GestionesSolicitudesEnum, Boolean>();
        for (GestionesSolicitudesEnum gestionEnum : values()) {
            if (!gestionEnum.esTarjeta && !gestionEnum.isEsOtroMedioDePago()) {
                gestionesMap.put(gestionEnum, gestionEnum.getValorInicial());
            }
        }
        return gestionesMap;
    }

    /**
     * Obtener mapa inicial de cuentas y paquetes.
     *
     * @return the map
     */
    public static Map<GestionesSolicitudesEnum, Boolean> obtenerMapaInicialTarjetas() {
        Map<GestionesSolicitudesEnum, Boolean> gestionesMap = new LinkedHashMap<GestionesSolicitudesEnum, Boolean>();
        for (GestionesSolicitudesEnum gestionEnum : values()) {
            if (gestionEnum.esTarjeta) {
                gestionesMap.put(gestionEnum, gestionEnum.getValorInicial());
            }
        }
        return gestionesMap;
    }

	/**
     * Obtener mapa inicial de otros medios de pago.
     *
     * @return the map
     */
    public static Map<GestionesSolicitudesEnum, Boolean> obtenerMapaInicialOtrosMediosPago() {
        Map<GestionesSolicitudesEnum, Boolean> gestionesMap = new LinkedHashMap<GestionesSolicitudesEnum, Boolean>();
        gestionesMap.put(GETNET, GETNET.getValorInicial());
//        gestionesMap.put(BILLETERAVIRTUAL, BILLETERAVIRTUAL.getValorInicial());
//        gestionesMap.put(BOTONPAGO, BOTONPAGO.getValorInicial());
        return gestionesMap;
    }

	/**
	 * Cumple condiciones cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 */
	public abstract boolean cumpleCondicionesCuenta(Cuenta cuenta);
	
	public String getHabilitacionWomen() {
		return habilitacionWomen;
	}

	public void setHabilitacionWomen(String habilitacionWomen) {
		this.habilitacionWomen = habilitacionWomen;
	}

	public static void main(String[] args) {
		
		String numero = "";
		
		if (numero.isEmpty()) {
			System.out.println("IGUAL");
		} else {
			System.out.println("DISTRINTO");
		}
		
	}

	
	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
