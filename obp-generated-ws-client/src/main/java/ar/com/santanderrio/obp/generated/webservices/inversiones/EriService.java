package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * The Interface EriService.
 *
 * @author sergio.e.goldentair
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "ERIService")
public interface EriService {

    /**
     * Consulta perfil inversor.
     *
     * @param consultaPerfilInversor
     *            the consulta perfil inversor
     * @return the consulta perfil inversor response
     */
    @WebResult(name = "ConsultaPerfilInversorResult", targetNamespace = "http://tempuri.org/")
    @WebMethod(operationName = "ConsultaPerfilInversor", action = "http://tempuri.org/IERIService/ConsultaPerfilInversor")
    ConsultaPerfilInversorResponse consultaPerfilInversor(
            @WebParam(name = "parameter", targetNamespace = "http://tempuri.org/") ConsultaPerfilInversorReq consultaPerfilInversor) throws EriException;

    /**
     * Evaluacion de riesgo.
     *
     * @param evaluacionDeRiesgo
     *            the evaluacion de riesgo
     * @return the evaluacion de riesgo response
     * @throws EriException
     *             the eri exception
     */
    @WebResult(name = "EvaluacionDeRiesgoResult", targetNamespace = "http://tempuri.org/")
    @WebMethod(operationName = "EvaluacionDeRiesgo", action = "http://tempuri.org/IERIService/EvaluacionDeRiesgo")
    EvaluacionDeRiesgoResponse evaluacionDeRiesgo(
            @WebParam(name = "parameter", targetNamespace = "http://tempuri.org/") EvaluacionDeRiesgoReq evaluacionDeRiesgo)
            throws EriException;

    /**
     * Confirmacion orden.
     *
     * @param confirmacionOrden
     *            the confirmacion orden
     * @return the confirmacion orden response
     */
    @WebResult(name = "ConfirmacionOrdenResult", targetNamespace = "http://tempuri.org/")
    @WebMethod(operationName = "ConfirmacionOrden", action = "http://tempuri.org/IERIService/ConfirmacionOrden")
    ConfirmacionOrdenResponse confirmacionOrden(
            @WebParam(name = "parameter", targetNamespace = "http://tempuri.org/") ConfirmacionOrdenReq confirmacionOrden)throws EriException;
}
