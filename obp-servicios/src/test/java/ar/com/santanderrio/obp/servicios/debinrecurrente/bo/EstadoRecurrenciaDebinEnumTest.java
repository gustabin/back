package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

import org.junit.Assert;
import org.junit.Test;

public class EstadoRecurrenciaDebinEnumTest {
     @Test
    public void testObtenerEstadoPorDescripcion(){

         EstadoRecurrenciaDebinEnum estado= EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion("ACTIVE");
         Assert.assertEquals(EstadoRecurrenciaDebinEnum.ACTIVA , estado);
         Assert.assertEquals("12", estado.getId());
         Assert.assertEquals("ACTIVE", estado.getDescripcion());
         Assert.assertEquals("Activa", estado.getDescView());


         estado= EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion("INACTIVE_DEF");
         Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA_DEFINITIVA , estado);
         Assert.assertEquals("13", estado.getId());
         Assert.assertEquals("INACTIVE_DEF", estado.getDescripcion());
         Assert.assertEquals("Dada de baja", estado.getDescView());

          estado= EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion("INACTIVE_TEMP");
          Assert.assertEquals(EstadoRecurrenciaDebinEnum.INACTIVA , estado);
          Assert.assertEquals("14", estado.getId());
          Assert.assertEquals("INACTIVE_TEMP", estado.getDescripcion());
          Assert.assertEquals("Pausada", estado.getDescView());

          estado= EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion("PENDING");
          Assert.assertEquals(EstadoRecurrenciaDebinEnum.PENDIENTE , estado);
          Assert.assertEquals("15", estado.getId());
          Assert.assertEquals("PENDING", estado.getDescripcion());
          Assert.assertEquals("Solicitud pendiente", estado.getDescView());

          estado= EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion("REJECT");
          Assert.assertEquals(EstadoRecurrenciaDebinEnum.RECHAZADA, estado);
          Assert.assertEquals("16", estado.getId());
          Assert.assertEquals("REJECT", estado.getDescripcion());
          Assert.assertEquals("Rechazada", estado.getDescView());
     }
}
