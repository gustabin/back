package ar.com.santanderrio.obp.servicios.echeq.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.echeq.Aval;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.exceptions.FechaException;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cesion;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.echeq.view.ComboEstadoView;


//TODO: REMOVE ALL CONSTANTS FROM STRINGS/ METHOD OVERLOAD GET ACCIONES HABILITADAS
public enum ECheqAmcoEstados {

	// ESTADOS RECIBIDOS
	/** The emitido pendiente recibidos e. */
	EMITIDO_PENDIENTE_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "EMITIDO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Pendiente de aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque echeq, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The activo recibidos e. */
	ACTIVO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aceptado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if(!ECheqUtils.isAvalista(cliente, cheque)) {
				addAccionDepositar(cheque, accionesHabilitadas);
				addAccionCustodiar(cheque, accionesHabilitadas);
				addAccionEndosar(cheque, accionesHabilitadas);
				addAccionSolicitarAval(cheque, accionesHabilitadas);
				addAccionEmitirCED(cheque, accionesHabilitadas);
			}
			return accionesHabilitadas;
		}
	},

	/** The activo recibidos e cedido. */
	ACTIVO_RECIBIDOS_E_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, "Aceptado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if(!ECheqUtils.isAvalista(cliente, cheque)) {
				addAccionDepositar(cheque, accionesHabilitadas);
				addAccionCustodiar(cheque, accionesHabilitadas);
				addAccionEndosar(cheque, accionesHabilitadas);
				addAccionEmitirCED(cheque, accionesHabilitadas);
			}
			return accionesHabilitadas;
		}
	},

	/** The activo pendiente recibidos e. */
	ACTIVO_PENDIENTE_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "ACTIVO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Endosado Pendiente de aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The presentado recibidos e. */
	PRESENTADO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "PRESENTADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Echeq por acreditar") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The anulado recibidos e. */
	ANULADO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "ANULADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Anulado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The repudiado recibidos e. */
	REPUDIADO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "REPUDIADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Repudiado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The devolucion pendiente recibidos e. */
	DEVOLUCION_PENDIENTE_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "DEVOLUCION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Devolución Pendiente de aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if(!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_PEDIDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The depositado recibidos e. */
	DEPOSITADO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "DEPOSITADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Depositado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The pagado recibidos e. */
	PAGADO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "PAGADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Cobrado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nnn. */
	RECHAZADO_RECIBIDOS_E_NNN(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Rechazado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
				if (validarCantidadCesiones(cheque.getCesiones())) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CED.getAccion());
				}
			}
			return accionesHabilitadas;
		}
	},
	
	/** The rechazado recibidos e nnn cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_NNN_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO",
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, "Rechazado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque) &&
					validarCantidadCesiones(cheque.getCesiones())) {
				accionesHabilitadas.add(OperacionEcheqEnum.ADMITIR_CED.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_CED.getAccion());
			}
			return accionesHabilitadas;
		}
	},
	
	/** The rechazado recibidos e nnn cedido. */
	RECHAZADO_RECIBIDOS_E_NNN_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());

			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
				if (validarCantidadCesiones(cheque.getCesiones())) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CED.getAccion());
				}
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nnn cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_NNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque) &&
					validarCantidadCesiones(cheque.getCesiones())) {
				accionesHabilitadas.add(OperacionEcheqEnum.ADMITIR_CED.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_CED.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nns. */
	RECHAZADO_RECIBIDOS_E_NNS(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nns cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_NNS_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nns cedido. */
	RECHAZADO_RECIBIDOS_E_NNS_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Certificado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nns cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_NNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nsn. */
	RECHAZADO_RECIBIDOS_E_NSN(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nsn cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_NSN_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nsn cedido. */
	RECHAZADO_RECIBIDOS_E_NSN_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nsn cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_NSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nss. */
	RECHAZADO_RECIBIDOS_E_NSS(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nss cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_NSS_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nss cedido. */
	RECHAZADO_RECIBIDOS_E_NSS_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e nss cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_NSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e snn. */
	RECHAZADO_RECIBIDOS_E_SNN(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e snn cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_SNN_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e snn cedido. */
	RECHAZADO_RECIBIDOS_E_SNN_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e snn cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_SNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)) {
				accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sns. */
	RECHAZADO_RECIBIDOS_E_SNS(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sns cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_SNS_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sns cedido. */
	RECHAZADO_RECIBIDOS_E_SNS_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Certficado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sns cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_SNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e ssn. */
	RECHAZADO_RECIBIDOS_E_SSN(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e ssn cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_SSN_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e ssn cedido. */
	RECHAZADO_RECIBIDOS_E_SSN_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e ssn cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_SSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sss. */
	RECHAZADO_RECIBIDOS_E_SSS(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sss cesion pendiente. */
	RECHAZADO_RECIBIDOS_E_SSS_CESION_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sss cedido. */
	RECHAZADO_RECIBIDOS_E_SSS_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado recibidos e sss cesion pendiente cedido. */
	RECHAZADO_RECIBIDOS_E_SSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The custodia recibidos e. */
	CUSTODIA_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "CUSTODIA", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "En custodia") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			addAccionRescatar(cheque, accionesHabilitadas);
			return accionesHabilitadas;
		}
	},

	/** The vencido recibidos e. */
	VENCIDO_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Caducado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			
			if (Boolean.FALSE.equals(habilitarEmisionCertificado(obtenerDate(cheque.getFechaUltModif())))){
				if (!cheque.isCertificadoEmitido() && cheque.getTenencia().getBeneficiarioDocumento().equals(cliente.getNroDocCnsDocXNup())) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
				}
			}
			return accionesHabilitadas;
		}
	},
	
	VENCIDO_RECIBIDOS_E_CERTIFICADO(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,   ECheqUtils.ESTADO_CADUCADOS_EMITIDOS) {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {

			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());

			return accionesHabilitadas;
		}
	}
	,

	/** The negociacion pendiente recibidos e. */
	NEGOCIACION_PENDIENTE_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Negociación pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The negociacion recibidos e. */
	NEGOCIACION_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Negociado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The cesion pendiente recibidos e. */
	CESION_PENDIENTE_RECIBIDOS_E(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "CESION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cesión pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean fechaAnteriorVencimientoFinal = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (!ECheqUtils.isAvalista(cliente, cheque)
					&& fechaAnteriorVencimientoFinal
					&& CARACTER_NO_A_LA_ORDEN.equalsIgnoreCase(cheque.getChequeCaracter())) {
				accionesHabilitadas.add(OperacionEcheqEnum.ADMITIR_CED.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_CED.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	// ESTADOS EMITIDOS
	/** The emitido pendiente emitidos e. */
	EMITIDO_PENDIENTE_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "EMITIDO-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			addAccionAnular(cheque, accionesHabilitadas);
			return accionesHabilitadas;
		}
	},

	/** The activo emitidos e. */
	ACTIVO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aceptado (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if(validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()))) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The activo emitidos e. */
	ACTIVO_EMITIDOS_E_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, "Aceptado (*) - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
 				List<String> accionesHabilitadas = new ArrayList<String>();
			if (validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()))) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The activo pendiente emitidos e. */
	ACTIVO_PENDIENTE_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "ACTIVO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Aceptado - En circulación (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The presentado emitidos e. */
	PRESENTADO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "PRESENTADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Presentado a Pago") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The anulado emitidos e. */
	ANULADO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "ANULADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Anulado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The repudiado emitidos e. */
	REPUDIADO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "REPUDIADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Repudiado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The devolucion pendiente emitidos e. */
	DEVOLUCION_PENDIENTE_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "DEVOLUCION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Devolución Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()))) {
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The depositado emitidos e. */
	DEPOSITADO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "DEPOSITADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Depositado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The pagado emitidos e. */
	PAGADO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "PAGADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Pagado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nnn. */
	RECHAZADO_EMITIDOS_E_NNN(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Rechazado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nnn cesion pendiente. */
	RECHAZADO_EMITIDOS_E_NNN_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nnn cedido. */
	RECHAZADO_EMITIDOS_E_NNN_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nnn cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_NNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nns. */
	RECHAZADO_EMITIDOS_E_NNS(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nns cesion pendiente. */
	RECHAZADO_EMITIDOS_E_NNS_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nns cedido. */
	RECHAZADO_EMITIDOS_E_NNS_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Certificado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nns cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_NNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nsn. */
	RECHAZADO_EMITIDOS_E_NSN(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nsn cesion pendiente. */
	RECHAZADO_EMITIDOS_E_NSN_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nsn cedido. */
	RECHAZADO_EMITIDOS_E_NSN_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nsn cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_NSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nss. */
	RECHAZADO_EMITIDOS_E_NSS(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nss cesion pendiente. */
	RECHAZADO_EMITIDOS_E_NSS_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nss cedido. */
	RECHAZADO_EMITIDOS_E_NSS_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e nss cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_NSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e snn. */
	RECHAZADO_EMITIDOS_E_SNN(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e snn cesion pendiente. */
	RECHAZADO_EMITIDOS_E_SNN_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e snn cedido. */
	RECHAZADO_EMITIDOS_E_SNN_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e snn cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_SNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sns. */
	RECHAZADO_EMITIDOS_E_SNS(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sns cesion pendiente. */
	RECHAZADO_EMITIDOS_E_SNS_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sns cedido. */
	RECHAZADO_EMITIDOS_E_SNS_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Certficado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sns cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_SNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e ssn. */
	RECHAZADO_EMITIDOS_E_SSN(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e ssn cesion pendiente. */
	RECHAZADO_EMITIDOS_E_SSN_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e ssn cedido. */
	RECHAZADO_EMITIDOS_E_SSN_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e ssn cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_SSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sss. */
	RECHAZADO_EMITIDOS_E_SSS(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sss cesion pendiente. */
	RECHAZADO_EMITIDOS_E_SSS_CESION_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sss cedido. */
	RECHAZADO_EMITIDOS_E_SSS_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado emitidos e sss cesion pendiente cedido. */
	RECHAZADO_EMITIDOS_E_SSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The custodia emitidos e. */
	CUSTODIA_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "CUSTODIA", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Aceptado - En gestión del beneficiario (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The vencido emitidos e. */
	VENCIDO_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Caducado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (Boolean.FALSE.equals(habilitarEmisionCertificado(obtenerDate(cheque.getFechaUltModif())))){
				if (Boolean.FALSE.equals(cheque.isCertificadoEmitido())) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
				}
			}
			return accionesHabilitadas;
		}
	},
	
	VENCIDO_EMITIDOS_E_CERTIFICADO(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,  ECheqUtils.ESTADO_CADUCADOS_EMITIDOS) {
	@Override
	public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {

		List<String> accionesHabilitadas = new ArrayList<String>();
		accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());

		return accionesHabilitadas;
	}
	}
	,

	/** The negociacion pendiente emitidos e. */
	NEGOCIACION_PENDIENTE_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "En Circulación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The negociacion emitidos e. */
	NEGOCIACION_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Activo") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The cesion pendiente emitidos e. */
	CESION_PENDIENTE_EMITIDOS_E(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "CESION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cesión pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	// ESTADOS ENDOSADOS
	/** The emitido pendiente endosados e. */
	EMITIDO_PENDIENTE_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "EMITIDO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "-") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			return new ArrayList<String>();
		}
	},

	/** The activo endosados e. */
	ACTIVO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Endosado (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaSolicitarDevolucion = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaSolicitarDevolucion) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},
	
	/** The activo endosados e cedido. */
	ACTIVO_ENDOSADOS_E_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, "Endosado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaSolicitarDevolucion = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaSolicitarDevolucion) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The activo pendiente endosados e. */
	ACTIVO_PENDIENTE_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "ACTIVO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Endoso pendiente de aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ENDOSO.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The presentado endosados e. */
	PRESENTADO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "PRESENTADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Presentado a Pago") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The anulado endosados e. */
	ANULADO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "ANULADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Anulado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The repudiado endosados e. */
	REPUDIADO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "REPUDIADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Repudiado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The devolucion pendiente endosados e. */
	DEVOLUCION_PENDIENTE_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "DEVOLUCION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Devolución Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaAnularPedidoDevolucion = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaAnularPedidoDevolucion) {
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The depositado endosados e. */
	DEPOSITADO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "DEPOSITADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Depositado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The pagado endosados e. */
	PAGADO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "PAGADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Pagado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nnn. */
	RECHAZADO_ENDOSADOS_E_NNN(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Rechazado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nnn cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_NNN_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nnn cedido. */
	RECHAZADO_ENDOSADOS_E_NNN_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nnn cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_NNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nns. */
	RECHAZADO_ENDOSADOS_E_NNS(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nns cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_NNS_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nns cedido. */
	RECHAZADO_ENDOSADOS_E_NNS_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Certificado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nns cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_NNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nsn. */
	RECHAZADO_ENDOSADOS_E_NSN(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nsn cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_NSN_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nsn cedido. */
	RECHAZADO_ENDOSADOS_E_NSN_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nsn cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_NSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nss. */
	RECHAZADO_ENDOSADOS_E_NSS(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nss cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_NSS_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nss cedido. */
	RECHAZADO_ENDOSADOS_E_NSS_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e nss cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_NSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e snn. */
	RECHAZADO_ENDOSADOS_E_SNN(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e snn cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_SNN_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e snn cedido. */
	RECHAZADO_ENDOSADOS_E_SNN_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e snn cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_SNN_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sns. */
	RECHAZADO_ENDOSADOS_E_SNS(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sns cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_SNS_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sns cedido. */
	RECHAZADO_ENDOSADOS_E_SNS_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Certficado Emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sns cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_SNS_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e ssn. */
	RECHAZADO_ENDOSADOS_E_SSN(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e ssn cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_SSN_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e ssn cedido. */
	RECHAZADO_ENDOSADOS_E_SSN_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Cheque Acordado - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e ssn cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_SSN_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Cheque Acordado - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sss. */
	RECHAZADO_ENDOSADOS_E_SSS(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sss cesion pendiente. */
	RECHAZADO_ENDOSADOS_E_SSS_CESION_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sss cedido. */
	RECHAZADO_ENDOSADOS_E_SSS_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The rechazado endosados e sss cesion pendiente cedido. */
	RECHAZADO_ENDOSADOS_E_SSS_CESION_PENDIENTE_CEDIDO(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - cheque acordado - certificado emitido - En Cesión Pendiente - Cedido") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The custodia endosados e. */
	CUSTODIA_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "CUSTODIA", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Endosado - En gestión del beneficiario (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The vencido endosados e. */
	VENCIDO_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Caducado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			

			return accionesHabilitadas;
		}
	},

	/** The negociacion pendiente endosados e. */
	NEGOCIACION_PENDIENTE_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "NEGOCIACION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Endosado - En circulación (*)") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ENDOSO.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The negociacion endosados e. */
	NEGOCIACION_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "NEGOCIACION", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Negociado") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	
	/** The cesion pendiente endosados e. */
	CESION_PENDIENTE_ENDOSADOS_E(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "CESION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cesión pendiente") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/*EMITIDO_PENDIENTE_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "EMITIDO_PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},*/
	/** activo - cedidos e.*/
	ACTIVO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (validarFechaEnRangoPosteriorPago(obtenerDate(cheque.getFechaPago()))
					&& CARACTER_A_LA_ORDEN.equals(cheque.getChequeCaracter())) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},
	/** activo-cedido - cedidos e.*/
	ACTIVO_CEDIDOS_E_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "ACTIVO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean esFechaEnRangoPosteriorPago = validarFechaEnRangoPosteriorPago(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (esFechaEnRangoPosteriorPago && CARACTER_A_LA_ORDEN.equals(cheque.getChequeCaracter())) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},
	/** activo-pendiente - cedidos e.*/
	ACTIVO_PENDIENTE_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "ACTIVO-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cedido - En circulación"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**presentado - cecidos e.*/
	PRESENTADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "PRESENTADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Presentado a Pago"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**anulado - cecidos e.*/
	ANULADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "ANULADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Anulado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**repudiado - cedidos e.*/
	REPUDIADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "REPUDIADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Repudiado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**devolucion-pendiente - cedidos e.*/
	DEVOLUCION_PENDIENTE_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "DEVOLUCION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Devolución Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			
			Boolean esFechaEnRangoPosteriorPago = validarFechaEnRangoPosteriorPago(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (esFechaEnRangoPosteriorPago && CARACTER_A_LA_ORDEN.equals(cheque.getChequeCaracter())) {
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			return accionesHabilitadas;
		}
	},
	/**depositado - cedidos e.*/
	DEPOSITADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "DEPOSITADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Depositado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**pagado - cedidos e.*/
	PAGADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "PAGADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Pagado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado - cedidos e.*/
	RECHAZADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado en cesion pendiente - cecidos e.*/
	RECHAZADO_CEDIDOS_E_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_CED.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cedido - cedidos e. */
	RECHAZADO_CEDIDOS_E_RECHAZADO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cesion-pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - En Cesion Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	
	/**rechazado cert emitido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_CERT_EMITIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - Certificado emitido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cert emitido en cesion pentiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_CERT_EMITIDO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cert emitido cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_CERT_EMITIDO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Certificado Emitido - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cert emitido en cesion pentiente y cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_CERT_EMITIDO_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Certificado Emitido - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - cheque acordado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado en cesion pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	
	// CEDIDOS
	
	/**rechazado cheque acordado en cesion pendiente cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado cert emitido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CERT_EMITIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - Cheque Acordado - Certificado Emitido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado cert emitido en cesion pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CERT_EMITIDO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado Emitido - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado cert emitido cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CERT_EMITIDO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Cheque Acordado - Certificado Emitido - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque acordado cert emitido en cesion pendiente cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_ACORDADO_CERT_EMITIDO_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Cheque Acordado - Certificado Emitido - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo en cesion pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo  - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo en cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo en cesion pendiente cedido  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Solicitando Acuerdo - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo cert emitido  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CERT_EMITIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - solicitando acuerdo - certificado emitido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo cert emitido en cesion pendiente  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CERT_EMITIDO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En cesion Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo cert emitido cedido  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CERT_EMITIDO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - solicitando acuerdo - Certficado Emitido - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo cert emitido cesion pendiente cedido  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_CERT_EMITIDO__CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - solicitando acuerdo - Certficado Emitido - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo - Cheque Acordado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado en cesion pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado en cesion pendiente cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado cert emitido  - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CERT_EMITIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - Certificado Emitido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado cert emitido en cesion pendiente - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CERT_EMITIDO_CESION_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.FALSE, Boolean.TRUE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - Certificado Emitido - En Cesión Pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado cert emitido cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CERT_EMITIDO_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.FALSE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - Certificado Emitido - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**rechazado cheque solicita acuerdo acordado cert emitido en cesion pendiente cedido - cedidos e.*/
	RECHAZADO_CEDIDOS_E_SOLICITUD_ACUERDO_ACORDADO_CERT_EMITIDO__CESION_PENDIENTE_CEDIDO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "RECHAZADO", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
			Boolean.TRUE, Boolean.TRUE, "Rechazado - Solicitando Acuerdo - Cheque Acordado - Certificado Emitido - En Cesión Pendiente - Cedido"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**custodia aceptado - cedidos e.*/
	CUSTODIA_CEDIDOS_E_ACEPTADO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "ACEPTADO", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Aceptado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**caducado - cedidos e.*/
	CADUCADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Caducado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (Boolean.FALSE.equals(habilitarEmisionCertificado(obtenerDate(cheque.getFechaUltModif())))){
				if (Boolean.FALSE.equals(cheque.isCertificadoEmitido()) && cheque.getTenencia().getBeneficiarioDocumento().equals(cliente.getNroDocCnsDocXNup())) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
				}
			}
			return accionesHabilitadas;
		}
	},
	
		CADUCADO_CEDIDOS_E_CERTIFICADO(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CADUCADOS, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE,
			Boolean.FALSE, Boolean.FALSE,  ECheqUtils.ESTADO_CADUCADOS_EMITIDOS){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			
			return accionesHabilitadas;
		}
	},
	
	/**negocicacion pendiente - cedidos e.*/
	NEGOCIACION_PENDIENTE_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cedido -  En circulación"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**negociaco - cedidos e.*/
	NEGOCIADO_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "NEGOCIACION", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Negociado"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/**cesion pendiente - cedidos e.*/
	CESION_PENDIENTE_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "CESION-PENDIENTE", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Cesión pendiente"){
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if(validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago())))
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_CED.getAccion());
			return accionesHabilitadas;
		}
	},

	//ESTADOS AVAL
	RECIBIDO_AVAL_PENDIENTE(TiposGrilla.RECIBIDOS.getId(), EstadosBae.E.getId(), "AVAL-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aval Pendiente de Aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			final List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			addAccionesAvalRecibido(cheque, cliente, accionesHabilitadas);
			return accionesHabilitadas;
		}
	},

	EMITIDO_AVAL_PENDIENTE(TiposGrilla.EMITIDOS.getId(), EstadosBae.E.getId(), "AVAL-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aval Pendiente de Aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			final List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	ENDOSADO_AVAL_PENDIENTE(TiposGrilla.ENDOSADOS.getId(), EstadosBae.E.getId(), "AVAL-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aval Pendiente de Aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			final List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	CEDIDOS_AVAL_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "AVAL-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "Aval Pendiente de Aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			final List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	CEDIDOS_CEDIDO_AVAL_PENDIENTE(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), "AVAL-PENDIENTE", Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, "Aval Pendiente de Aceptación") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			final List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	/** The custodia cedidos e. */
	CUSTODIA_CEDIDOS_E(TiposGrilla.CEDIDOS.getId(), EstadosBae.E.getId(), ECheqUtils.ESTADO_CESION_CUSTODIA, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
			Boolean.FALSE, Boolean.FALSE, "Aceptado - En gestión del beneficiario") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) {
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The sin estado recibidos e. */
	SIN_ESTADO_RECIBIDOS_E(null, null, null, null, null, null, null, null, "En gestión") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			Boolean habilitaDepositar = habilitarDepositar(obtenerDate(cheque.getFechaPago()));
			Boolean habilitaCustodiar = fechaDiaMenorQueFechaPago(obtenerDate(cheque.getFechaPago()));
			Boolean habilitaRescatar = fechaDiaMenorQueFechaPago(obtenerDate(cheque.getFechaPago()));
			Boolean habilitaEndosar = habilitarEndosar(obtenerDate(cheque.getFechaPago()));
			Boolean fechaAnteriorVencimientoFinal = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			Boolean validaCantidadCesiones = validarCantidadCesiones(cheque.getCesiones());
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR.getAccion());
			if (habilitaDepositar) {
				accionesHabilitadas.add(OperacionEcheqEnum.DEPOSITAR.getAccion());
			}
			if (habilitaCustodiar) {
				accionesHabilitadas.add(OperacionEcheqEnum.CUSTODIAR.getAccion());
			}
			if (habilitaRescatar) {
				accionesHabilitadas.add(OperacionEcheqEnum.RESCATAR.getAccion());
			}
			if (habilitaEndosar) {
				accionesHabilitadas.add(OperacionEcheqEnum.ENDOSAR.getAccion());
			}
			accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_PEDIDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_PEDIDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CERTIFICADO.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION.getAccion());
			if (fechaAnteriorVencimientoFinal) {
				if (validaCantidadCesiones) {
					accionesHabilitadas.add(OperacionEcheqEnum.EMITIR_CED.getAccion());
				}
				accionesHabilitadas.add(OperacionEcheqEnum.ADMITIR_CED.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.REPUDIAR_CED.getAccion());
			}
			return accionesHabilitadas;
		}
	},

	/** The sin estado emitidos e. */
	SIN_ESTADO_EMITIDOS_E(null, null, null, null, null, null, null, null, "En gestión") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaOpciones = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaOpciones) {
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The sin estado endosados e. */
	SIN_ESTADO_ENDOSADOS_E(null, null, null, null, null, null, null, null, "En gestión") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaOperaciones = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaOperaciones) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},
	
	/** The sin estado cedidos e. */
	SIN_ESTADO_CEDIDOS_E(null, null, null, null, null, null, null, null, "En gestión") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			Boolean habilitaOperaciones = validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()));
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			if (habilitaOperaciones) {
				accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION.getAccion());
				accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_PEDIDO_DEVOLUCION.getAccion());
			}
			accionesHabilitadas.add(OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION.getAccion());
			accionesHabilitadas.add(OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The estado no reconocido recibidos e. */
	ESTADO_NO_RECONOCIDO_RECIBIDOS_E(null, null, null, null, null, null, null, null, "") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The estado no reconocido emitidos e. */
	ESTADO_NO_RECONOCIDO_EMITIDOS_E(null, null, null, null, null, null, null, null, "") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The estado no reconocido endosados e. */
	ESTADO_NO_RECONOCIDO_ENDOSADOS_E(null, null, null, null, null, null, null, null, "") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},
	
	/** The estado no reconocido cedidos e. */
	ESTADO_NO_RECONOCIDO_CEDIDOS_E(null, null, null, null, null, null, null, null, "") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	},

	/** The actualizando. */
	ACTUALIZANDO(null, null, null, null, null, null, null, null, "Actualizando") {
		@Override
		public List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente) { 
			List<String> accionesHabilitadas = new ArrayList<String>();
			accionesHabilitadas.add(OperacionEcheqEnum.VER_DETALLE.getAccion());
			return accionesHabilitadas;
		}
	};

	private static final Logger LOGGER = LoggerFactory.getLogger(ECheqAmcoEstados.class);
	public static final List<String> ESTADOS_AMCO_LST = Arrays.asList("ACTIVO", "ACTIVO-PENDIENTE", "ANULADO", "AVAL-PENDIENTE",
			"CUSTODIA", "DEPOSITADO", "DEVOLUCION-PENDIENTE", "EMITIDO-PENDIENTE", "PAGADO",
			"PRESENTADO", "RECHAZADO", "REPUDIADO", "CADUCADO", "NEGOCIACION-PENDIENTE", "NEGOCIACION",
			"CESION-PENDIENTE");

	private static final String CARACTER_A_LA_ORDEN = "A LA ORDEN";
	private static final String CARACTER_NO_A_LA_ORDEN = "NO A LA ORDEN";

	private static final String ESTADO_CESION_ACEPTADA = "Aceptada";
	private static final String ESTADO_CESION_PENDIENTE = "Pendiente";
	private static final String ESTADO_CESION_REPUDIADA = "Repudiada";

	private final String tipoGrilla;
	private final String estadoBae;
	private final String estadoAmco;
	private final Boolean solicitandoAcuerdo;
	private final Boolean chequeAcordado;
	private final Boolean certificadoEmitido;
	private final Boolean cedido;
	private final Boolean cesionPendiente;
	private String descripcion;

	/**
	 * Instantiates a new e cheq amco estados.
	 *
	 * @param tipoGrilla the tipo grilla
	 * @param estadoBae the estado bae
	 * @param estadoAmco the estado amco
	 * @param solicitandoAcuerdo the solicitando acuerdo
	 * @param chequeAcordado the cheque acordado
	 * @param certificadoEmitido the certificado emitido
	 * @param cedido the cedido
	 * @param cesionPendiente the cesion pendiente
	 * @param descripcion the descripcion
	 */
	ECheqAmcoEstados(String tipoGrilla, String estadoBae, String estadoAmco, Boolean solicitandoAcuerdo,
			Boolean chequeAcordado, Boolean certificadoEmitido, Boolean cedido, Boolean cesionPendiente, String descripcion) {
		this.tipoGrilla = tipoGrilla;
		this.estadoBae = estadoBae;
		this.estadoAmco = estadoAmco;
		this.solicitandoAcuerdo = solicitandoAcuerdo;
		this.chequeAcordado = chequeAcordado;
		this.certificadoEmitido = certificadoEmitido;
		this.cedido = cedido;
		this.cesionPendiente = cesionPendiente;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the estado bae.
	 *
	 * @return the estado bae
	 */
	public String getEstadoBae() {
		return estadoBae;
	}

	/**
	 * Gets the estado amco.
	 *
	 * @return the estado amco
	 */
	public String getEstadoAmco() {
		return estadoAmco;
	}

	/**
	 * Gets the solicitando acuerdo.
	 *
	 * @return the solicitando acuerdo
	 */
	public Boolean getSolicitandoAcuerdo() {
		return solicitandoAcuerdo;
	}

	/**
	 * Gets the cheque acordado.
	 *
	 * @return the cheque acordado
	 */
	public Boolean getChequeAcordado() {
		return chequeAcordado;
	}

	/**
	 * Gets the certificado emitido.
	 *
	 * @return the certificado emitido
	 */
	public Boolean getCertificadoEmitido() {
		return certificadoEmitido;
	}

	/**
	 * Gets the cedido.
	 *
	 * @return the cedido
	 */
	public Boolean getCedido() {
		return cedido;
	}

	/**
	 * Gets the cesion pendiente.
	 *
	 * @return the cesion pendiente
	 */
	public Boolean getCesionPendiente() {
		return cesionPendiente;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Gets the tipo grilla.
	 *
	 * @return the tipo grilla
	 */
	public String getTipoGrilla() {
		return tipoGrilla;
	}

	public abstract List<String> getAccionesHabilitadas(Cheque cheque, Cliente cliente);

	/**
	 * Gets the by estado.
	 *
	 * @param tipoGrilla the tipo grilla
	 * @param estadoBae the estado bae
	 * @param estadoAmco the estado amco
	 * @param solicitandoAcuerdo the solicitando acuerdo
	 * @param chequeAcordado the cheque acordado
	 * @param certificadoEmitido the certificado emitido
	 * @param cedido the cedido
	 * @param cesionPendiente the cesion pendiente
	 * @return the by estado
	 */
	public static ECheqAmcoEstados getByEstado(String tipoGrilla, String estadoBae, String estadoAmco,
			Boolean solicitandoAcuerdo, Boolean chequeAcordado, Boolean certificadoEmitido, Boolean cedido, Boolean cesionPendiente) {

		ECheqAmcoEstados estado;

		if (!isEstadoValido(estadoAmco.toUpperCase())) {
			if (TiposGrilla.RECIBIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
				estado = ECheqAmcoEstados.ESTADO_NO_RECONOCIDO_RECIBIDOS_E;
				estado.descripcion = estadoAmco;
				return estado;
			} else if (TiposGrilla.EMITIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
				estado = ECheqAmcoEstados.ESTADO_NO_RECONOCIDO_EMITIDOS_E;
				estado.descripcion = estadoAmco;
				return estado;
			} else if (TiposGrilla.ENDOSADOS.getId().equalsIgnoreCase(tipoGrilla)) {
				estado = ECheqAmcoEstados.ESTADO_NO_RECONOCIDO_ENDOSADOS_E;
				estado.descripcion = estadoAmco;
				return estado;
			} else if(TiposGrilla.CEDIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
				estado = ECheqAmcoEstados.ESTADO_NO_RECONOCIDO_CEDIDOS_E;
				estado.descripcion = estadoAmco;
				return estado;
			}
			return null;
		} else if (StringUtils.isBlank(estadoAmco)) {
			if (TiposGrilla.RECIBIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
				return ECheqAmcoEstados.SIN_ESTADO_RECIBIDOS_E;
			} else if (TiposGrilla.EMITIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
				return ECheqAmcoEstados.SIN_ESTADO_EMITIDOS_E;
			} else if (TiposGrilla.ENDOSADOS.getId().equalsIgnoreCase(tipoGrilla)) {
				return ECheqAmcoEstados.SIN_ESTADO_ENDOSADOS_E;
			} else if (TiposGrilla.CEDIDOS.getId().equalsIgnoreCase(tipoGrilla)){
				return ECheqAmcoEstados.SIN_ESTADO_CEDIDOS_E;
			}
			return null;
		}

		if (EstadosBae.P.getId().equals(estadoBae) || EstadosBae.R.getId().equals(estadoBae)) {
			return ECheqAmcoEstados.ACTUALIZANDO;
		}

		if (EstadosBae.F.getId().equals(estadoBae) || EstadosBae.X.getId().equals(estadoBae) || EstadosBae.C.getId().equals(estadoBae)) {
			estadoBae = EstadosBae.E.getId();
		}

		for (ECheqAmcoEstados echeqAmcoEstado : ECheqAmcoEstados.values()) {
			if (estadoAmco.equals(ECheqAmcoEstados.RECHAZADO_EMITIDOS_E_NNN.getEstadoAmco())) {
				if (echeqAmcoEstado.getTipoGrilla().equals(tipoGrilla)
						&& echeqAmcoEstado.getEstadoBae().equals(estadoBae)
						&& echeqAmcoEstado.getEstadoAmco().equals(estadoAmco)
						&& echeqAmcoEstado.getSolicitandoAcuerdo().equals(solicitandoAcuerdo)
						&& echeqAmcoEstado.getChequeAcordado().equals(chequeAcordado)
						&& echeqAmcoEstado.getCertificadoEmitido().equals(certificadoEmitido)
						&& echeqAmcoEstado.getCedido().equals(cedido)
						&& echeqAmcoEstado.getCesionPendiente().equals(cesionPendiente)) {
					return echeqAmcoEstado;
				}
			} else if (estadoAmco.equals(ECheqAmcoEstados.VENCIDO_EMITIDOS_E_CERTIFICADO.getEstadoAmco())){
				if (echeqAmcoEstado.getTipoGrilla().equals(tipoGrilla)
						&& echeqAmcoEstado.getEstadoBae().equals(estadoBae)
						&& echeqAmcoEstado.getEstadoAmco().equals(estadoAmco)
						&& echeqAmcoEstado.getCertificadoEmitido().equals(certificadoEmitido)) {

					return echeqAmcoEstado;
				}
			}else {
				if (echeqAmcoEstado.getTipoGrilla().equals(tipoGrilla)
						&& echeqAmcoEstado.getEstadoBae().equals(estadoBae)
						&& echeqAmcoEstado.getEstadoAmco().equals(estadoAmco)) {
					return echeqAmcoEstado;
				}
			}
		}
		return null;
	}

	/**
	 * Checks if is estado valido.
	 *
	 * @param estadoAmco the estado amco
	 * @return true, if is estado valido
	 */
	private static boolean isEstadoValido(String estadoAmco) {
		return ESTADOS_AMCO_LST.contains(estadoAmco);
	}

	/**
	 * Obtener date.
	 *
	 * @param fecha the fecha
	 * @return the date
	 */
	private static Date obtenerDate(String fecha) {
		SimpleDateFormat sd1 = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_AMCO);
		String fechaAmco = fecha.substring(0, ECheqUtils.LEN_FECHA_AMCO);
		try {
			return sd1.parse(fechaAmco);
		} catch (ParseException e) {
			LOGGER.error("Error parseando fecha: {}", fechaAmco, e);
			return null;
		}
	}

	/**
	 * Habilitar depositar.
	 *
	 * @param fechaPago the fecha pago
	 * @return the boolean
	 */
	private static Boolean habilitarDepositar(Date fechaPago) {
		Boolean retorno = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaPago);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int cantidadDiasVencimiento = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.DIAS.VENCIMIENTO"));
		int cantidadDiasVencimientoNorm = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.DIAS.VENCIMIENTO.NORM"));
		String desdeNorm = ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.FECHA.DESDE.NORM");
		String hastaNorm = ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.FECHA.HASTA.NORM");
		try {
			FechaUtils fechaHoy = new FechaUtils();
			int cantDias = 0;
			fechaHoy.add(Calendar.DATE, cantDias);
			FechaUtils fechaDePago = new FechaUtils(day, (month + 1), year);
			FechaUtils fechaDePagoFin = new FechaUtils(day, (month + 1), year);
			fechaDePagoFin.add(Calendar.DATE, cantidadDiasVencimiento);
			FechaUtils fechaDePagoFinNorm = new FechaUtils(day, (month + 1), year);
			fechaDePagoFinNorm.add(Calendar.DATE, cantidadDiasVencimientoNorm);
			FechaUtils fechaDesdeNorm = null;
			FechaUtils fechaHastaNorm = null;
			if (desdeNorm != null && hastaNorm != null && !"".equals(desdeNorm.trim()) && !"".equals(hastaNorm.trim())) {
				fechaDesdeNorm = new FechaUtils(desdeNorm.substring(0, 2), desdeNorm.substring(3, 5), desdeNorm.substring(6, 10));
				fechaHastaNorm = new FechaUtils(hastaNorm.substring(0, 2), hastaNorm.substring(3, 5), hastaNorm.substring(6, 10));
			}
			if (fechaDesdeNorm != null && fechaHastaNorm != null && ((fechaDePago.mayorQue(fechaDesdeNorm) && fechaDePago.menorQue(fechaHastaNorm))
					|| fechaDePago.equalsFecha(fechaDesdeNorm) || fechaDePago.equalsFecha(fechaHastaNorm))) {
				if ((fechaHoy.mayorQue(fechaDePago) && fechaHoy.menorQue(fechaDePagoFinNorm))
						|| fechaHoy.equalsFecha(fechaDePago) || fechaHoy.equalsFecha(fechaDePagoFinNorm)) {
					retorno = true;
				}
			} else {
				if ((fechaHoy.mayorQue(fechaDePago) && fechaHoy.menorQue(fechaDePagoFin))
						|| fechaHoy.equalsFecha(fechaDePago) || fechaHoy.equalsFecha(fechaDePagoFin)) {
					retorno = true;
				}
			}
		} catch (FechaException e) {
			LOGGER.error("Error parseando fechas", e);
		}
		return retorno;
	}

	/**
	 * Habilitar endosar.
	 *
	 * @param fechaPago the obtener date
	 * @return the boolean
	 */
	private static Boolean habilitarEndosar(Date fechaPago) {
		Boolean retorno = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaPago);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int cantidadDiasVencimiento = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.DIAS.VENCIMIENTO"));
		try {
			FechaUtils fechaHoy = new FechaUtils();
			int cantDias = 0;
			fechaHoy.add(Calendar.DATE, cantDias);
			FechaUtils fechaDePagoFin = new FechaUtils(day, (month + 1), year);
			fechaDePagoFin.add(Calendar.DATE, cantidadDiasVencimiento);
			if (fechaHoy.menorQue(fechaDePagoFin) || fechaHoy.equalsFecha(fechaDePagoFin)) {
				retorno = true;
			}
		} catch (FechaException e) {
			LOGGER.error("Error parseando fechas", e);
		}
		return retorno;
	}
	
	private static Boolean habilitarEmisionCertificado(Date fechaultModificacion) {
		Boolean retorno = false;
		Date fechaImplementacion = new Date();
		fechaImplementacion = obtenerDate("2023-08-15");

		if (fechaultModificacion.before(fechaImplementacion)) {
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * Validar fecha en rango posterior pago.
	 *
	 * @param fechaPago the fecha pago
	 * @return the boolean
	 */
	private static Boolean validarFechaEnRangoPosteriorPago(Date fechaPago) {
		Boolean retorno = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaPago);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int cantidadDiasVencimiento = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.DIAS.VENCIMIENTO"));
		try {
			FechaUtils fechaHoy = new FechaUtils();
			int cantDias = 0;
			fechaHoy.add(Calendar.DATE, cantDias);
			FechaUtils fechaDePago = new FechaUtils(day, (month + 1), year);
			FechaUtils fechaDePagoFin = new FechaUtils(day, (month + 1), year);
			fechaDePagoFin.add(Calendar.DATE, cantidadDiasVencimiento);
			if ((fechaHoy.mayorQue(fechaDePago) || fechaHoy.equalsFecha(fechaDePago))
					&& (fechaHoy.menorQue(fechaDePagoFin) || fechaHoy.equalsFecha(fechaDePagoFin))) {
				retorno = true;
			}
		} catch (FechaException e) {
			LOGGER.error("Error parseando fechas", e);
		}
		return retorno;
	}

	/**
	 * Validar cantidad cesiones.
	 *
	 * @param cesiones the cesiones
	 * @return the boolean
	 */
	private static Boolean validarCantidadCesiones(List<Cesion> cesiones) {
		int cantCesiones = 0;
		int cantidadCesiones = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.CESIONES"));
		if (cesiones != null) {
			for (Cesion cesion : cesiones) {
				if (ESTADO_CESION_ACEPTADA.equals(cesion.getEstadoCesion())
						|| ESTADO_CESION_PENDIENTE.equals(cesion.getEstadoCesion())
						|| ESTADO_CESION_REPUDIADA.equals(cesion.getEstadoCesion())) {
					cantCesiones++;
				}
			}
		}
		return cantCesiones < cantidadCesiones;
	}

	private static Boolean validarHabilitaCantidadAvales(Cheque cheque) {
		List<Aval> avalList = cheque.getAvalistas() != null ? cheque.getAvalistas() : new ArrayList<Aval>();
		int maxAvales = Integer.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.AVAL"));
		return avalList.size() < maxAvales;
	}

	/**
	 * Validar fecha anterior vencimiento.
	 *
	 * @param fechaPago the fecha pago
	 * @return the boolean
	 */
	private static Boolean validarFechaAnteriorVencimientoFinal(Date fechaPago) {
		Boolean retorno = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaPago);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int cantidadDiasVencimiento = Integer
				.parseInt(ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.CANTIDAD.DIAS.VENCIMIENTO"));
		try {
			FechaUtils fechaHoy = new FechaUtils();
			int cantDias = 0;
			fechaHoy.add(Calendar.DATE, cantDias);
			FechaUtils fechaDePagoFin = new FechaUtils(day, (month + 1), year);
			fechaDePagoFin.add(Calendar.DATE, cantidadDiasVencimiento);
			if (fechaHoy.menorQue(fechaDePagoFin) || fechaHoy.equalsFecha(fechaDePagoFin)) {
				retorno = true;
			}
		} catch (FechaException e) {
			LOGGER.error("Error parseando fechas", e);
		}
		return retorno;
	}

	/**
	 * Fecha dia menor que fecha pago.
	 *
	 * @param fechaPago the fecha pago
	 * @return the boolean
	 */
	private static Boolean fechaDiaMenorQueFechaPago(Date fechaPago) {
	    
	    Date ahora = new Date();
	    return ahora.before(fechaPago);
	}

	/**
	 * 
	 * Obtengo los estados y descripcion de la grilla
	 * Filtro la opcion que no tiene descripcion (ENDOSADOS - EMITIDO-PENDIENTE)
	 * No agrego ningun rechazado de ninguna grilla
	 * Luego agrego un unico rechazado para las 3 grillas por igual
	 * 
	 * @param tipoGrilla
	 * @return
	 */
	public static List<ComboEstadoView> getComboEstadoView(String tipoGrilla) {
		List<ComboEstadoView> combo = new ArrayList<ComboEstadoView>();
		for (ECheqAmcoEstados echeqAmcoEstado : ECheqAmcoEstados.values()) {
			if (echeqAmcoEstado.getTipoGrilla() != null
					&& echeqAmcoEstado.getEstadoBae() != null
					&& echeqAmcoEstado.getTipoGrilla().equals(tipoGrilla)
					&& echeqAmcoEstado.getEstadoBae().equals(EstadosBae.E.getId())
					&& !"-".equals(echeqAmcoEstado.getDescripcion())
					&& !"".equals(echeqAmcoEstado.getDescripcion())
					&& !echeqAmcoEstado.getEstadoAmco().equals(ECheqAmcoEstados.RECHAZADO_EMITIDOS_E_NNN.getEstadoAmco())) {
				ComboEstadoView comboEstadoView = new ComboEstadoView();
				comboEstadoView.setDescripcion(echeqAmcoEstado.getDescripcion());
				comboEstadoView.setId(echeqAmcoEstado.getEstadoAmco());
				combo.add(comboEstadoView);
			}
		}
		combo.add(new ComboEstadoView(ECheqAmcoEstados.RECHAZADO_EMITIDOS_E_NNN.getEstadoAmco(), ECheqAmcoEstados.RECHAZADO_EMITIDOS_E_NNN.getDescripcion()));
		return combo;
	}

	private static void addAccionesAvalRecibido(Cheque cheque, Cliente cliente, List<String> acciones) {
		Aval aval = ECheqUtils.getAvalPendiente(cheque);
		if (aval == null) return;

		if (ECheqUtils.isAvalista(cliente, aval)) {
			acciones.add(OperacionEcheqEnum.ACEPTAR_AVAL.getAccion());
			acciones.add(OperacionEcheqEnum.REPUDIAR_AVAL.getAccion());
		}

		if (ECheqUtils.isBeneficiario(cliente, cheque)) {
			acciones.add(OperacionEcheqEnum.ANULAR_SOLICITUD_AVAL.getAccion());
		}
	}

	private static void addAccionSolicitarAval(Cheque cheque, List<String> acciones) {
		if (validarHabilitaCantidadAvales(cheque)) {
			acciones.add(OperacionEcheqEnum.SOLICITAR_AVAL.getAccion());
		}
	}

	private static void addAccionEndosar(Cheque cheque, List<String> acciones) {
		if (habilitarEndosar(obtenerDate(cheque.getFechaPago()))) {
			acciones.add(OperacionEcheqEnum.ENDOSAR.getAccion());
		}
	}

	private static void addAccionDepositar(Cheque cheque, List<String> acciones) {
		if (habilitarDepositar(obtenerDate(cheque.getFechaPago()))) {
			acciones.add(OperacionEcheqEnum.DEPOSITAR.getAccion());
		}
	}

	private static void addAccionCustodiar(Cheque cheque, List<String> acciones) {
		if (fechaDiaMenorQueFechaPago(obtenerDate(cheque.getFechaPago()))) {
			acciones.add(OperacionEcheqEnum.CUSTODIAR.getAccion());
		}
	}

	private static void addAccionEmitirCED(Cheque cheque, List<String> acciones) {
		if (validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()))
				&& CARACTER_NO_A_LA_ORDEN.equalsIgnoreCase(cheque.getChequeCaracter())
				&& validarCantidadCesiones(cheque.getCesiones())) {
			acciones.add(OperacionEcheqEnum.EMITIR_CED.getAccion());
		}
	}

	private static void addAccionRescatar(Cheque cheque, List<String> acciones) {
		if (fechaDiaMenorQueFechaPago(obtenerDate(cheque.getFechaPago()))) {
			acciones.add(OperacionEcheqEnum.RESCATAR.getAccion());
		}
	}

	private static void addAccionAnular(Cheque cheque, List<String> acciones) {
		if (validarFechaAnteriorVencimientoFinal(obtenerDate(cheque.getFechaPago()))) {
			acciones.add(OperacionEcheqEnum.ANULAR.getAccion());
		}
	}
}