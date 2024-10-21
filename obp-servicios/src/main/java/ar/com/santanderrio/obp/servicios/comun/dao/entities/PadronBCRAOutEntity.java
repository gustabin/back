package ar.com.santanderrio.obp.servicios.comun.dao.entities;

import org.beanio.annotation.Field;

public class PadronBCRAOutEntity extends PadronOutEntity {
    
 /** Denominación en BCRA *. */
 @Field
 private String bcraApellidoNombre1;


 /**
  * Gets the bcra apellido nombre 1.
  *
  * @return the bcra apellido nombre 1
  */
 public String getBcraApellidoNombre1() {
     return bcraApellidoNombre1;
 }

 /**
  * Sets the bcra apellido nombre 1.
  *
  * @param bcraApellidoNombre1
  *            the new bcra apellido nombre 1
  */
 public void setBcraApellidoNombre1(String bcraApellidoNombre1) {
     this.bcraApellidoNombre1 = bcraApellidoNombre1;
 }


}
