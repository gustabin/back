package ar.com.santanderrio.obp.servicios.login.dao;

import java.io.IOException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.login.dao.impl.ReporteTyCDAOImpl;

/**
 * The Class ReporteComprobanteDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteTyCDAOTest {

    /** The reporte comprobante DAO. */
    @InjectMocks
    private ReporteTyCDAO reporteTyCDAO = new ReporteTyCDAOImpl();
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Thread.interrupted();
    }
    
    @Test
    public void buildReporteComprobanteOkTest() throws DAOException, IllegalAccessException, IOException {


        FieldUtils.writeField(reporteTyCDAO, "logo",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_tyc.png"),
                true);
        
        FieldUtils.writeField(reporteTyCDAO, "fileJasper", 
                appContext.getResource("classpath:/report/mya/terminosycondiciones/reportMyaTyC.jasper"), true);
        
        
        String texto = "1. Funcionamiento Para habilitar los sistemas de Online Banking (para el segmento individuos) utilizaré mi número de documento, una clave compuesta por cuatro dígitos y una clave alfanumérica (usuario) de entre 8 a 20 posiciones, las cuales elegiré y, por tanto, serán únicamente de mi conocimiew+<br><br>Tomo conocimiento que una vez ingresado al sistema Online Banking, y para la realización de ciertas operaciones, me podrá ser requerido indefectiblemente el ingreso de dos de los códigos que surgen de la Tarjeta Coordenadas que deberé solicitar al Banco, a efectos de la autenticación de esas operaciones. Sólo será posible realizar las mismas, en las oportunidades que el sistema lo requiera, contando con los códigos contenidos en la Tarjeta Coordenadas que me fuera asignada.<br><br>Me comprometo a informar al Banco el extravío de la Tarjeta Coordenadas, y solicitar el envío de una nueva en su reemplazo, ya que cada una de ellas posee un único número de serie que la identifica unívocamente y es asociada a cada cliente<BR>El ingreso de los datos al sistema Online Banking de acuerdo a los procedimientos mencionados validará las operaciones efectuadas como si hubiesen sido instrumentadas con mi firma personal y a mi exclusivo riesgo."
                + "El Banco queda totalmente liberado de responsabilidad por el uso que un tercero pueda hacer del sistema, utilizando mi número de documento y/o el de la clave secreta y/o el del usuario y/o la Tarjeta Coordenadas que me fuera entregada."
                + "El servicio de Online Banking (para el segmento individuos) podrá ser operado las 24 hs. todos los días de la semana. De 21 a 7 hs. sólo estará disponible para la realización de consultas."
                + "<BR>Sólo será posible acceder a Online Banking (para el segmento individuos) utilizando los navegadores Microsoft Internet Explorer 6.0 o superior, o Mozilla Firefox 3.0 o superior."
                + "<BR><BR>Dejo constancia que he sido amplia y correctamente informado sobre las respectivas operatorias y los riesgos que implica su uso."
                + "<BR><BR>2. Operaciones"
                + "<BR><BR>Al ingresar al sistema, quedaré habilitado para realizar todas las operaciones que el Banco determine, incluida la adquisición de productos y/o servicios, compra y venta de moneda extranjera, realización de transferencias, suscripción de fondos comunes de inversión y/o las que el Banco pudiera disponer bajo las condiciones particulares de cada una de ellas y de los productos afectados por las mismas."
                + "Las operaciones cursadas a través de este sistema serán tomadas \"en firme\", salvo aquéllas que se contrapongan con otras pactadas en el mismo día o simultáneamente, o que deban ser necesariamente confirmadas por el Banco."
                + "<BR><BR>En todos aquellos supuestos en los que la operación requiera la firma de un contrato previo, éste deberá haber sido suscripto y encontrarse vigente, así como, en su caso, las cuentas sobre las que opere deberán hallarse abiertas y sin pesar sobre ellas suspensiones del servicio de pago ni medidas cautelares que las afecten."
                + "<BR>Del mismo modo aquellas operaciones que requieran de la existencia de un tipo de cuenta determinado para su realización o habilitación de clave específica, deberán contar con la apertura de esa cuenta y/o habilitación de clave antes de la realización de la operación pretendida."
                + "<BR><BR>Mi ingreso a cualquiera de los sistemas no variará ni modificará los contratos antes referidos, no pudiendo realizar por este medio cualquier tipo de operación que pueda ser interpretada como novación, quita, espera y/o tácita reconducción de otra ya existente, además de no poder suprimir o disminuir ninguna garantía constituida, las cuales mantendrán todas ellas su vigencia."
                + "<BR>En caso de efectuar, por este u otro sistema distinto, operaciones que se contrapongan entre sí, se entenderá que la única válida es la primera procesada por el Banco."
                + "<BR>La constitución de depósitos a plazo fijo se realizará sin emisión de certificado, y el importe de la imposición se debitará de la cuenta que indique y al vencimiento, se acreditará en la misma cuenta. El Banco emitirá mensualmente un resumen de estas operaciones."
                + "<BR><BR>3. Topes"
                + "<BR>Ante la realización de cada operación deberé cerciorarme, a través de los canales habilitados al efecto, de los límites con que cuento para la realización de las mismas."
                + "<BR><BR>4. Responsabilidad"
                + "<BR><BR>Asumo expresamente las siguientes obligaciones:"
                + "<BR>1. Efectuar las operaciones ajustándome estrictamente a las instrucciones dadas por el Banco."
                + "<BR><BR>2. Mantener la confidencialidad de la Tarjeta Coordenadas asignada, del número de mi documento personal, el usuario y la clave de identificación que elija para acceder al sistema de Online Banking (para el segmento individuos) de Banco Santander Río S.A. la cual será, de igual modo, la misma clave utilizable en los sistemas de Súper Línea y Autoservicio."
                + "<BR><BR>3. Mantener a resguardo la Tarjeta Coordenadas."
                + "<BR><BR>Me hago totalmente responsable por cualquier circunstancia que pueda generarse en virtud del incumplimiento de las obligaciones que asumo en la presente."
                + "<BR><BR>5. Autorización"
                + "<BR><BR>Autorizo expresamente al Banco a que, por motivos de seguridad, proceda a grabar las operaciones que realice, por cualquiera de los servicios que utilice y a ser presentados eventualmente como medio de prueba en un juicio."
                + "<BR><BR>Autorizo asimismo, que el Banco emita el resumen de mis productos de forma electrónica y me envíe un aviso indicando que el mismo ya se encuentra disponible en Online Banking a mi casilla de email por mi registrada. A tal efecto tomo conocimiento que podré notificar al Banco con antelación cualquier modificación a mi decisión de recibir mi resumen por esta vía."
                + "<BR><BR>El Banco queda expresamente autorizado a que toda notificación a todos los efectos que correspondan, que se relacionen con los servicios contratados, sea realizada por correo electrónico o mediante un aviso de disponibilidad enviado a la casilla de email por mi registrada."
                + "<BR><BR>5. Resolución"
                + "<BR><BR>El Banco queda expresamente autorizado a suprimir, total o parcialmente, el servicio a que se refiere la presente y/o suspenderlos cuando lo considere conveniente, sin necesidad de notificación previa de ninguna naturaleza, sirviendo como suficiente aviso el brindado por medio del sistema Online Banking (para el segmento individuos), dando cuenta de dicha circunstancia."
                + "<BR><BR>7. Rescisión del servicio"
                + "<BR>Por mi parte, también podré hacer uso del derecho a hacer cesar mi adhesión al sistema, dando aviso con 10 días de antelación al Banco."
                + "<BR><BR>8. Misceláneos"
                + "<BR><BR>Declaro conocer que el Banco compromete sus mejores esfuerzos a los fines de que las operaciones realizadas a través de Online Banking no puedan resultar comprometidas por terceras personas, así como también haber sido debidamente informado que:"
                + "<BR><BR>i) la clave de acceso o el usuario no deberán corresponderse con otros números o datos que pueda obtenerse fácilmente. Ninguno de esos datos deberá divulgarse, ya que son de uso personal y, además, constituyen la llave de ingreso al sistema y por ende a las cuentas y operaciones que mantengo con el Banco;"
                + "<BR><BR>ii) deberé notificar de inmediato al Banco en caso de pérdida o robo de la tarjeta coordenadas o, en su caso, cambiar sin demora la clave o usuario cuando entienda que la misma pueda estar comprometida o en conocimiento de terceras personas;"
                + "<BR><BR>iii) no proporcionare mi número de clave personal, el de usuario, los datos de la tarjeta coordenadas ni tampoco información personal de identidad que me sea requerida, por cualquier medio, invocando el nombre del Banco;"
                + "<BR><BR>iv) el personal del Banco se encuentra a disposición para brindar toda la información que estime necesaria acerca del uso de Online Banking."
                + "<BR><BR>Entiendo que toda medida que pudiera tomar el Banco a los fines de reforzar la seguridad en Online Banking deberá entenderse como complementaria de aquellas que me corresponda tomar como usuario a los fines de verificar el normal funcionamiento del equipo utilizado y la posible existencia en dicho equipo de virus, troyanos y/o cualquier tipo de software malicioso."
                + "<BR><BR>EL INGRESO DE SU NUMERO DE DOCUMENTO, DE SU CLAVE PERSONAL Y DE SU USUARIO IMPORTARAN LA EXPRESA ACEPTACION A TODAS Y CADA UNA DE LAS CLAUSULAS Y CONDICIONES INDICADAS PRECEDENTEMENTE LAS CUALES SE TENDRAN POR DEBIDAMENTE NOTIFICADAS AL DIGITARSE EL CUADRO DE DIALOGO CON LA LEYENDA ACEPTO."
                + "<BR><BR>DERECHO DE REVOCACIÓN: Ud. podrá revocar su adhesión al sistema Online Banking dentro de diez (10) días hábiles contados a partir de la fecha de la aceptación del presente, o de la disponibilidad efectiva del mismo, lo que suceda último, debiendo notificar al BANCO de manera fehaciente tal decisión. Dicha revocación es sin costo alguno ni responsabilidad alguna para Ud., en la medida que no haya utilizado el mismo."
                + "<BR><BR>En caso de utilización Ud. deberá abonar los fondos utilizados, más las intereses, comisiones y cargos previstos proporcionalmente al tiempo de utilización de los mismos.";
        
//        byte[] byteArray = 
        Reporte reporte = reporteTyCDAO.obtenerReporteTyCMya(texto);
//        FileUtils.writeByteArrayToFile(new File("c:/file10.pdf"),
//                 reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());

    }

    
}
