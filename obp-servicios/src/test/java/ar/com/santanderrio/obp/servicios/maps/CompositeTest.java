package ar.com.santanderrio.obp.servicios.maps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.RequestMap;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.DisclaimerControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaCompuestaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ImporteCompuestoControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.InputNumberControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.InputTextControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ListaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemDisclaimer;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGenericoMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemLegal;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.LegalMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;


@RunWith(MockitoJUnitRunner.class)
public class CompositeTest {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeTest.class);

    @Test
    public void control2Json() throws JsonParseException, JsonMappingException, IOException {
        ListaControl<ItemGenericoMaps> listaControl = new ListaControl<ItemGenericoMaps>();
        listaControl.setId("cuenta-titulos-1");
        listaControl.setNombre("cuenta-títulos");
        listaControl.setTipoDataValor("string");
        List<ItemGenericoMaps> items = new ArrayList<ItemGenericoMaps>();
        ItemGenericoMaps item1 = new ItemGenericoMaps();
        item1.setValorPadre("ARS");
        item1.setValor(220549);
        item1.setDesc("Cuenta Titulos número 220549");
        item1.setSeleccionado(true);
        items.add(item1);
        ItemGenericoMaps item2 = new ItemGenericoMaps();
        item2.setValorPadre("USD");
        item2.setValor(false);
        item2.setDesc("Cuenta Titulos número 225687");
        item2.setSeleccionado(false);
        items.add(item2);
        listaControl.setItems(items);
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(listaControl);
        ListaControl<?> map = mapper.readValue(listaAsJson, ListaControl.class);
        LOGGER.info("Lista como json: {}", listaAsJson);
        LOGGER.info("Lista como json: {}", map.getPadreId());
        assertEquals(map.getItems().get(0).getValor().getClass(),Integer.class);
        assertEquals(map.getItems().get(1).getValor().getClass(),Boolean.class);
        LOGGER.info("Cumple validacion {}", ValidationEntity.validate(map));
    }

    @Test
    public void controlLegal2Json() throws JsonParseException, JsonMappingException, IOException {
        ListaControl<LegalMaps> listaControl = new ListaControl<LegalMaps>();
        listaControl.setId("cuenta-titulos-1");
        listaControl.setNombre("cuenta-títulos");
        listaControl.setTipoDataValor("string");
        listaControl.setPadreId("monedaId");
        List<LegalMaps> legales = new ArrayList<LegalMaps>();
        LegalMaps lm = new LegalMaps();
        List<ItemLegal> items = new ArrayList<ItemLegal>();
        ItemLegal item2 = new ItemLegal();
        item2.setEtiqueta("otra");
        items.add(item2);
        lm.setItems(items);
        legales.add(lm);
        listaControl.setItems(legales);
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(listaControl);
        LOGGER.info("Lista Legal como json: {}", listaAsJson);
    }
    
   

    
    @Test
    public void inputTextControl2Json() throws JsonParseException, JsonMappingException, IOException {
        InputTextControl inputTextControl = new InputTextControl();
        inputTextControl.setNombre("nombre");
        inputTextControl.setMinLength(0);
        inputTextControl.setMaxLength(22);        
        ObjectMapper mapper = new ObjectMapper();
        String inputTextAsJson = mapper.writeValueAsString(inputTextControl);
        LOGGER.info("InputText como json: {}", inputTextAsJson);
    }
    
    
    @Test
    public void inputNumberControl2Json() throws JsonParseException, JsonMappingException, IOException {
        InputNumberControl inputNumberControl = new InputNumberControl();
        inputNumberControl.setNombre("nombre");
        inputNumberControl.setMinValor(new BigDecimal(2));
        inputNumberControl.setMaxValor(new BigDecimal(11));
        ObjectMapper mapper = new ObjectMapper();
        String inputNumberAsJson = mapper.writeValueAsString(inputNumberControl);
        LOGGER.info("InputNumber como json: {}", inputNumberAsJson);
    }

    @Test
    public void formularioControl2Json() throws JsonParseException, JsonMappingException, IOException {
        RequestMap inputNumberControl = new FormularioControl();
        ObjectMapper mapper = new ObjectMapper();
        String inputNumberAsJson = mapper.writeValueAsString(inputNumberControl);
        LOGGER.info("InputNumber como json: {}", inputNumberAsJson);
    }

    
    @SuppressWarnings("unchecked")
	@Test
    public void fechaCompuestaControl2Json() throws JsonParseException, JsonMappingException, IOException {
    	FechaCompuestaControl  fechaCompuestaControl  = new FechaCompuestaControl();
    	fechaCompuestaControl.setId("id");
    	fechaCompuestaControl.setNombre("nombre_FechaCompuesta");
   	    	
    	ItemGenericoMaps itemGenericoMaps1 = new ItemGenericoMaps();
    	itemGenericoMaps1.setValor(90);
    	itemGenericoMaps1.setDesc("último 90 días");
    	itemGenericoMaps1.setSeleccionado(true);
    	
    	ItemGenericoMaps itemGenericoMaps2 = new ItemGenericoMaps();
    	itemGenericoMaps2.setValor(0);
    	itemGenericoMaps2.setDesc("otro intervalo");
    	itemGenericoMaps2.setSeleccionado(false);
    	
    	List<ItemGenericoMaps> itemsListaControl = new ArrayList<ItemGenericoMaps>();
    	itemsListaControl.add(itemGenericoMaps1);
    	itemsListaControl.add(itemGenericoMaps2);    	
    	List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();    	
    	@SuppressWarnings("rawtypes")
		ListaControl listaControl1 = new ListaControl();
    	listaControl1.setItems(itemsListaControl);
    	itemsControlMaps.add(listaControl1);    	   	
    	fechaCompuestaControl.setItems(itemsControlMaps);
        ObjectMapper mapper = new ObjectMapper();
        String fechaCompuestaControlAsJson = mapper.writeValueAsString(fechaCompuestaControl);
        LOGGER.info("FechaCompuestaControl como json: {}", fechaCompuestaControlAsJson);
    }

  
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void disclaimerControl2Json() throws JsonParseException, JsonMappingException, IOException {
    	
    	DisclaimerControl disclaimerControl = new DisclaimerControl();
    	disclaimerControl.setNombre("disclaimer");
    	disclaimerControl.setPosicion(2);
    	disclaimerControl.setRequerido(true);
    	disclaimerControl.setTipoDataValor("String");
    	
    	ItemDisclaimer itemDisclaimer1 = new ItemDisclaimer();
    	itemDisclaimer1.setDesc("Titulo Disclaimer 1");
    	itemDisclaimer1.setTipoDisclaimer(0);
    	itemDisclaimer1.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer1.setValorPadre(null);
    	
    	ItemDisclaimer itemDisclaimer2 = new ItemDisclaimer();
    	itemDisclaimer2.setDesc("Titulo Disclaimer 2");
    	itemDisclaimer2.setTipoDisclaimer(1);
    	itemDisclaimer2.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer2.setValorPadre(null);
    	
    	ItemDisclaimer itemDisclaimer3 = new ItemDisclaimer();
    	itemDisclaimer3.setDesc("Titulo Disclaimer 3");
    	itemDisclaimer3.setTipoDisclaimer(2);
    	itemDisclaimer3.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer3.setValorPadre(null);
    	    	
    	List<ItemDisclaimer> items = new ArrayList<ItemDisclaimer>();
    	
    	items.add(itemDisclaimer1);
    	items.add(itemDisclaimer2);
    	items.add(itemDisclaimer3);
    	disclaimerControl.setItems(items);
    	    	
        ObjectMapper mapper = new ObjectMapper();
        String disclaimerControlAsJson = mapper.writeValueAsString(disclaimerControl);
        LOGGER.info("DisclaimerControl como json: {}", disclaimerControlAsJson);
    }
    
    
    
    
    
    @Test
    public void json2ListaControl() throws JsonParseException, JsonMappingException, IOException {
    	
 //   	String jsonServicio = "{ \"Id\": \"lista-servicio-1\", \"Nombre\": \"lista-servicio\", \"Tipo\":\"servicio\", \"Etiqueta\": \"Servicio de Inversiones\", \"Items\": [ { \"Icono\": \"MAPS-Default\", \"Footer\": \"Cantidad de Activaciones\", \"Valor\": \"SAF\", \"Desc\": \"Suscripción de Fondos Automáticos\", \"CantAdhesiones\": \"2\", \"TipoServicio\": \"Suscripción Automática de Fondos \", \"Titulo\": \"SAF\", \"Seleccionado\": false }, { \"Icono\": \"MAPS-Default\", \"Footer\": \"Cantidad de Activaciones\", \"Valor\": \"PDC\", \"Desc\": \"Poder de Compra\", \"CantAdhesiones\": \"5\", \"TipoServicio\": \"Poder de Compra\", \"Titulo\": \"PDC\", \"Seleccionado\": false },";
        String jsonFormulario = "{\"Tipo\":\"formulario\",\"Id\":\"SAF2-CASO-BASE-0001\",\"Nombre\":\"SAF-Home\",\"Implementa\":null,\"Etiqueta\":\"Etiqueta\",\"Ayuda\":\"Ayuda\",\"Requerido\":true,\"Bloqueado\":false,\"Posicion\":\"1\",\"Error\":\"0\",\"Error_desc\":\"No hubo errores\",\"Error_tecnico\":\"No hubo errores\",\"Validado\":false,\"Informacion\":\"texto de ayuda\",\"Config\":null,\"IdServicio\":\"SAF\",\"IdSimulacion\":\"0234\",\"Comprobante\":\"34234\",\"Estado\":\"carga\",\"FormAnterior\":null,\"IdAdhesion\":null,\"Titulo\":\"Ingreso de Datos\",\"Nup\":\"00001312\",\"Segmento\":\"BP\",\"Canal\":\"04\",\"SubCanal\":\"99\",\"PerfilInversor\":\"Moderado\",\"Items\":[{\"Tipo\":\"input-text\",\"Id\":\"input-text-nombre\",\"Nombre\":\"Nombre y Apellido\",\"Implementa\":null,\"Etiqueta\":\"Ingrese nombre\",\"Ayuda\":\"Mensje ayuda\",\"Requerido\":false,\"Bloqueado\":false,\"Posicion\":\"2\",\"Error\":\"0\",\"Error_desc\":\"Eror_desc se \",\"Error_tecnico\":\"Error_tecnico  \",\"Validado\":false,\"Informacion\":\"Informacion 60 caracteres\",\"Config\":null,\"Valor\":\"s\",\"MinLength\":0,\"MaxLength\":60}]},";
        String jsonInputNumber = "{\"Id\": \"input-numero-1\", \"Etiqueta\":\"Servicio de Inversiones\",\"Nombre\":\"input\",\"Tipo\":\"input-number\", \"Ayuda\": \"Datos de prueba \", \"Requerido\":false,\"Bloqueado\":false ,\"Posicion\":\"1\",\"Error\":\"0\",\"Error_desc\":\"No hubo errores\",\"Error_tecnico\":\"No hubo errores\",\"Validado\":false,\"Informacion\":\"texto de ayuda\", \"Valor\":null,\"MinValor\":100, \"MaxValor\":1000,\"Incremento\":\"1.15\", \"Simbolo\":\"ARS\"},";
        String jsonStringImporteCompuesto = "{\"Id\":\"importe-compuesto-1\",\"Implementa\":null,\"Etiqueta\":\"Ingrese rango de precio\",\"Nombre\":\"Imp_compuesto\",\"Tipo\":\"importe-compuesto\",\"Ayuda\":\"Aqui podrá ingresar un rango del importe a solicitar\",\"Requerido\":true,\"Bloqueado\":false,\"Posicion\":\"1\",\"Error\":\"0\",\"Error_desc\":\"No hubo errores\",\"Error_tecnico\":\"No hubo errores\",\"Validado\":true,\"Informacion\":\"texto de ayuda\",\"Items\":[{\"Id\":\"input-number-1\",\"Implementa\":null,\"Etiqueta\":\"Mínimo a solicitar\",\"Nombre\":\"minimo\",\"Tipo\":\"input-number\",\"Ayuda\":\"Mensje ayuda\",\"Requerido\":false,\"Bloqueado\":false,\"Posicion\":\"2\",\"Error\":\"0\",\"Error_desc\":\"Eror_desc se \",\"Error_tecnico\":\"Error_tecnico  \",\"Validado\":false,\"Informacion\":\"Informacion 60 caracteres\",\"Valor\":\"s\",\"MinValor\":20,\"MaxValor\":100,\"Incremento\":1.15,\"Simbolo\":\"ARS\"},{\"Id\":\"input-number-2\",\"Implementa\":null,\"Etiqueta\":\"Máximo a solicitar\",\"Nombre\":\"maximo\",\"Tipo\":\"input-number\",\"Ayuda\":\"Mensje ayuda\",\"Requerido\":false,\"Bloqueado\":false,\"Posicion\":\"2\",\"Error\":\"0\",\"Error_desc\":\"Eror_desc se \",\"Error_tecnico\":\"Error_tecnico  \",\"Validado\":false,\"Informacion\":\"Informacion 60 caracteres\",\"Valor\":\"s\",\"MinValor\":20,\"MaxValor\":100,\"Incremento\":\"1.15\", \"Simbolo\":\"ARS\"}]},";
        String jsoninpuText = "{\"Tipo\":\"input-text\",\"Id\":\"input-text-nombre\",\"Nombre\":\"Nombre y Apellido\",\"Implementa\":null,\"Etiqueta\":\"Ingrese nombre\",\"Ayuda\":\"Mensje ayuda\",\"Requerido\":false,\"Bloqueado\":false,\"Posicion\":\"2\",\"Error\":\"0\",\"Error_desc\":\"Eror_desc se \",\"Error_tecnico\":\"Error_tecnico  \",\"Validado\":false,\"Informacion\":\"Informacion 60 caracteres\",\"Config\":null,\"Valor\":\"s\",\"MinLength\":0,\"MaxLength\":60}]";
        String end = ",\"Informacion\": \"texto de ayuda alternativo debajo del control\", \"Ayuda\": \"ayuda sobre el aviso legal \" }";
    	
    	String jsonString =  jsonFormulario + jsonStringImporteCompuesto + jsonInputNumber + jsoninpuText + end;
    	
        ObjectMapper mapper = new ObjectMapper();
        ControlMaps map = mapper.readValue(jsonString, ControlMaps.class);
//        LOGGER.info("Lista tipoDataValor: {}.", ((ListaControl<ServicioMaps>)map).getTipoDataValor());
//        LOGGER.info("ListaControl items size {}.",((ListaControl<ServicioMaps>)map).getItems().size());
        String listaAsJson = mapper.writeValueAsString(map);
        LOGGER.info("Lista Legal como json: {}", listaAsJson);
    }
    
    @Test
    public void validationControlTest() throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
        
        FormularioControl formulario = new FormularioControl();
        formulario.setAyuda("ayuda");
        formulario.setIdServicio("323");
        formulario.setIdSimulacion("3dsds");
        formulario.setComprobante("comprobante");
        formulario.setEstado("estado");
        formulario.setSegmento("RTL");
        formulario.setNombre("nombre");
        formulario.setId("ID");
        formulario.setNup("343243");
        formulario.setCanal("canal");
        formulario.setSubCanal("subcanal");
        formulario.validate();
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(formulario);
        LOGGER.info("Formulario como json: \n{}", listaAsJson);
        formulario.setIdSimulacion(null);
        formulario.setComprobante(null);
        listaAsJson = mapper.writeValueAsString(formulario);
        LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
        LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
        LOGGER.info("piso campo estado");
        formulario.setEstado(null);
        try {
            formulario.validate();
        }catch (ControlMapValidationException e) {
            LOGGER.info("Error validacion campo estado obligatorio");
        }
    }
    
 
    
    @Test
    public void validationInputTextTest() throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
        
    	InputTextControl inputText = new InputTextControl();  	
    	inputText.setAyuda("ayuda");
    	inputText.setNombre("nombre");
    	inputText.setId("ID");
    	inputText.setValor("micorreolaboral1.pepito@laboral.com,micorreolaboral2.pepito@laboral.com, ");
    	inputText.setMinLength(new Integer(0));
    	inputText.setMaxLength(new Integer(50));
    	inputText.validate();
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(inputText);
        LOGGER.info("inputNumber como json: \n{}", listaAsJson);

        listaAsJson = mapper.writeValueAsString(inputText);
        LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
        LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
        LOGGER.info("piso campo estado");
        try {
        	inputText.validate();
        }catch (ControlMapValidationException e) {
            LOGGER.info("Error validacion campo estado obligatorio");
        }
    }
    
    
    
    @Test
    public void validationInputNumberTest() throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
        
    	InputNumberControl inputNumber = new InputNumberControl();  	
    	inputNumber.setAyuda("ayuda");
    	inputNumber.setNombre("nombre");
    	inputNumber.setId("ID");
    	inputNumber.setSimbolo("ARS");
    	inputNumber.setValor(new BigDecimal(125.52));
    	inputNumber.setIncremento(new BigDecimal(1.15));
    	inputNumber.setMinValor(new BigDecimal(100));
    	inputNumber.setMaxValor(new BigDecimal(1000));
    	inputNumber.validate();
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(inputNumber);
        LOGGER.info("inputNumber como json: \n{}", listaAsJson);

        listaAsJson = mapper.writeValueAsString(inputNumber);
        LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
        LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
        LOGGER.info("piso campo estado");
        try {
        	inputNumber.validate();
        }catch (ControlMapValidationException e) {
            LOGGER.info("Error validacion campo estado obligatorio");
        }
    }
    
    
    @Test
    public void validationImporteCompuestoTest() throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
    	ImporteCompuestoControl importeCompuesto = new ImporteCompuestoControl();
    	importeCompuesto.setAyuda("ayuda");
    	importeCompuesto.setNombre("nombre");
    	importeCompuesto.setId("ID");
    	 	
    	InputNumberControl inputNumber = new InputNumberControl();  	
    	inputNumber.setAyuda("ayuda");
    	inputNumber.setNombre("nombre");
    	inputNumber.setId("ID");
    	inputNumber.setSimbolo("ARS");
    	inputNumber.setValor(new BigDecimal(125.52));
    	inputNumber.setIncremento(new BigDecimal(1.15));
    	inputNumber.setMinValor(new BigDecimal(100));
    	inputNumber.setMaxValor(new BigDecimal(1000));
    	inputNumber.validate();
    	
    	InputNumberControl inputNumber2 = new InputNumberControl();  	
    	inputNumber2.setAyuda("ayuda");
    	inputNumber2.setNombre("nombre");
    	inputNumber2.setId("ID");
    	inputNumber2.setSimbolo("ARS");
    	inputNumber2.setValor(new BigDecimal(125.52));
    	inputNumber2.setIncremento(new BigDecimal(1.15));
    	inputNumber2.setMinValor(new BigDecimal(100));
    	inputNumber2.setMaxValor(new BigDecimal(1000));
    	inputNumber2.validate();
    	
    	List<InputNumberControl> items = new ArrayList<InputNumberControl>();
    	items.add(inputNumber);
    	items.add(inputNumber2);
    	importeCompuesto.setItems(items);    	
    	importeCompuesto.validate();
    	
    	
        ObjectMapper mapper = new ObjectMapper();
        String listaAsJson = mapper.writeValueAsString(importeCompuesto);
        LOGGER.info("importeCompuesto como json: \n{}", listaAsJson);
        listaAsJson = mapper.writeValueAsString(importeCompuesto);
        LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
        LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
        LOGGER.info("piso campo estado");
        try {
        	importeCompuesto.validate();
        }catch (ControlMapValidationException e) {
            LOGGER.info("Error validacion campo estado obligatorio");
        }
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validationFechaCompuestaTest()
			throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
		FechaCompuestaControl fechaCompuesta = new FechaCompuestaControl();
		fechaCompuesta.setAyuda("ayuda");
		fechaCompuesta.setNombre("nombre");
		fechaCompuesta.setId("ID");

		ItemGenericoMaps itemGenericoMaps1 = new ItemGenericoMaps();
		itemGenericoMaps1.setValor(90);
		itemGenericoMaps1.setDesc("último 90 días");
		itemGenericoMaps1.setSeleccionado(true);
		ItemGenericoMaps itemGenericoMaps2 = new ItemGenericoMaps();
		itemGenericoMaps2.setValor(0);
		itemGenericoMaps2.setDesc("otro intervalo");
		itemGenericoMaps2.setSeleccionado(false);
		List<ItemGenericoMaps> itemsListaControl = new ArrayList<ItemGenericoMaps>();
		itemsListaControl.add(itemGenericoMaps1);
		itemsListaControl.add(itemGenericoMaps2);
		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();
		ListaControl listaControl1 = new ListaControl();

		FechaControl fechaControl = new FechaControl();
		fechaControl.setId("id");
		fechaControl.setNombre("nombre");
		fechaControl.setFechaMin("2018-08-03");
		fechaControl.setFechaMax("2018-10-03");
		fechaControl.setMinLength(1);
		fechaControl.setMaxLength(2);

		FechaControl fechaControl2 = new FechaControl();
		fechaControl2.setId("id");
		fechaControl2.setNombre("nombre");
		fechaControl2.setFechaMin("2018-08-03");
		fechaControl2.setFechaMax("2018-10-03");
		fechaControl2.setMinLength(1);
		fechaControl2.setMaxLength(2);

		listaControl1.setNombre("nombre1");
		listaControl1.setId("id1");
		listaControl1.setTipoDataValor("tipoDataValor1");
		listaControl1.setItems(itemsListaControl);
		
		itemsControlMaps.add(listaControl1);
		itemsControlMaps.add(fechaControl);
		itemsControlMaps.add(fechaControl2);

		fechaCompuesta.setItems(itemsControlMaps);
		fechaCompuesta.validate();

		ObjectMapper mapper = new ObjectMapper();
		String listaAsJson = mapper.writeValueAsString(fechaCompuesta);
		LOGGER.info("fechaCompuesta como json: \n{}", listaAsJson);
		listaAsJson = mapper.writeValueAsString(fechaCompuesta);
		LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
		LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
		LOGGER.info("piso campo estado");
		try {
			fechaCompuesta.validate();
		} catch (ControlMapValidationException e) {
			LOGGER.info("Error validacion campo estado obligatorio");
		}
	}
     
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validationDisclaimerTest()
			throws JsonParseException, JsonMappingException, IOException, ControlMapValidationException {
		    	
    	DisclaimerControl disclaimerControl = new DisclaimerControl();
    	disclaimerControl.setNombre("disclaimer");
    	disclaimerControl.setPosicion(2);
    	disclaimerControl.setRequerido(true);
    	disclaimerControl.setTipoDataValor("String");
    	disclaimerControl.setId("id");
 	
    	ItemDisclaimer itemDisclaimer1 = new ItemDisclaimer();
    	itemDisclaimer1.setDesc("Titulo Disclaimer 1");
    	itemDisclaimer1.setTipoDisclaimer(0);
    	itemDisclaimer1.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer1.setValorPadre(null);
    	
    	ItemDisclaimer itemDisclaimer2 = new ItemDisclaimer();
    	itemDisclaimer2.setDesc("Titulo Disclaimer 2");
    	itemDisclaimer2.setTipoDisclaimer(1);
    	itemDisclaimer2.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer2.setValorPadre(null);
    	
    	ItemDisclaimer itemDisclaimer3 = new ItemDisclaimer();
    	itemDisclaimer3.setDesc("Titulo Disclaimer 3");
    	itemDisclaimer3.setTipoDisclaimer(2);
    	itemDisclaimer3.setValor("cuadre perfil El cliente S/D, tras haber sido informado de las caracter�sticas y riesgos de la inversi�n y en pleno conocimiento de que la misma excede, por ser m�s riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y habiendo realizado su propio an�lisis, proceder a la Compra de 0019, por el importe de $100 al precio 19. Los fondos ser�n debitados de su cuenta 250-7003527692. Asimismo, toma conocimiento de que deber� ajustar su cartera de inversi�n, al perfil acordado oportunamente o modificar sus inversiones en funci�n de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendaci�n para realizar la presente inversi�n. notioper No hay Restricciones");
    	itemDisclaimer3.setValorPadre(null);
    	    	
    	List<ItemDisclaimer> items = new ArrayList<ItemDisclaimer>();
    	
    	items.add(itemDisclaimer1);
    	items.add(itemDisclaimer2);
    	items.add(itemDisclaimer3);
    	disclaimerControl.setItems(items); 	
    	disclaimerControl.validate();
   
		ObjectMapper mapper = new ObjectMapper();
		String listaAsJson = mapper.writeValueAsString(disclaimerControl);
		LOGGER.info("Disclaimer como json: \n{}", listaAsJson);
		listaAsJson = mapper.writeValueAsString(disclaimerControl);
		LOGGER.info("No se propagan atributos no obligatorios que no permiten null: idSimulacion Comprobante");
		LOGGER.info("json idSimulacion null:\n{}", listaAsJson);
		LOGGER.info("piso campo estado");
		try {
			disclaimerControl.validate();
		} catch (ControlMapValidationException e) {
			LOGGER.info("Error validacion campo estado obligatorio");
		}
	}
    
    
    
}