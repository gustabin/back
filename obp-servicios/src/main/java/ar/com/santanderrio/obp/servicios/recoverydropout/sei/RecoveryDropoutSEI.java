package ar.com.santanderrio.obp.servicios.recoverydropout.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionRequest;
import ar.com.santanderrio.obp.servicios.recoverydropout.model.FlowExecutionResponse;

@Path("/recovery")
public interface RecoveryDropoutSEI {

    /**
     * Actualiza el estado de un flow execution id.
     *
     */
    @POST
    @Path("/flows/{flowNameKey}/flowExecutions/{flowExecutionId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<FlowExecutionResponse> updateFlowExecution(@PathParam("flowNameKey")String flowNameKey, @PathParam("flowExecutionId")String flowExecutionId, FlowExecutionRequest bodyRequest);

    /**
     * Solicita un nuevo flow execution id para un flow id determinado.
     *
     */
    @POST
    @Path("/flows/{flowNameKey}/flowExecutions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<FlowExecutionResponse> postFlowExecution(@PathParam("flowNameKey")String flowNameKey, FlowExecutionRequest bodyRequest);
}
