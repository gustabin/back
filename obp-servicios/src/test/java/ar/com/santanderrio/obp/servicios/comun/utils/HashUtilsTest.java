/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Probar la clase utilitaria de hash HashUtils. {@link HashUtils}
 * 
 * @author sergio.e.goldentair
 *
 */
public class HashUtilsTest implements Serializable {
    private static final long serialVersionUID = -3673572624870065617L;
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtilsTest.class);

    @Test
    public void obtenerMd5DeString() {
        String s = "This is a test";
        String esperado = "35D154C31D6E7A010AF424168FBD1687";
        String hash = HashUtils.obtenerHash(s);
        LOGGER.info("El hash de \'{}\' es {}.", s, hash);
        Assert.assertEquals(esperado, hash);
    }

    @Test
    public void obtenerMd5DeDosObjetosIguales() {
        PersonaHash ph = getPersonaHash(1);
        PersonaHash ph2 = getPersonaHash(1);
        LOGGER.info("Persona {}, hash {}.", ph, HashUtils.obtenerHash(ph, HashUtils.ALGORITMO_MD5));
        LOGGER.info("Persona2 {}, hash {}.", ph2, HashUtils.obtenerHash(ph2, HashUtils.ALGORITMO_MD5));
        Assert.assertEquals("Los hash deben ser iguales", HashUtils.obtenerHash(ph, HashUtils.ALGORITMO_MD5),
                HashUtils.obtenerHash(ph2, HashUtils.ALGORITMO_MD5));
    }

    @Test
    public void obtenerMd5DiferenteDeObjetosDiferentes() {
        PersonaHash ph = getPersonaHash(1);
        PersonaHash ph2 = getPersonaHash(2);
        LOGGER.info("Persona {}, hash {}.", ph, HashUtils.obtenerHash(ph, HashUtils.ALGORITMO_MD5));
        LOGGER.info("Persona2 {}, hash {}.", ph2, HashUtils.obtenerHash(ph2, HashUtils.ALGORITMO_MD5));
        Assert.assertNotEquals("Los hash no deben ser iguales", HashUtils.obtenerHash(ph, HashUtils.ALGORITMO_MD5),
                HashUtils.obtenerHash(ph2, HashUtils.ALGORITMO_MD5));
    }

    @Test
    public void obtenerMD5DeListaDeObjetosIguales() {
        PersonaHash ph = getPersonaHash(1);
        PersonaHash ph2 = getPersonaHash(2);
        List<PersonaHash> lph = new ArrayList<PersonaHash>();
        lph.add(ph);
        lph.add(ph2);
        List<PersonaHash> lph2 = new ArrayList<PersonaHash>();
        lph2.add(ph);
        lph2.add(ph2);
        LOGGER.info("Lista de PersonaHash {}, hash {}.", lph, HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5));
        LOGGER.info("Lista de PersonaHash 2 {}, hash {}.", lph2, HashUtils.obtenerHash(lph2, HashUtils.ALGORITMO_MD5));
        Assert.assertNotNull("El hash de la lista no debe ser nulo",
                HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5));
        Assert.assertEquals("Los hash deben ser iguales", HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5),
                HashUtils.obtenerHash(lph2, HashUtils.ALGORITMO_MD5));
    }

    @Test
    public void obtenerMD5DeListaDeObjetosDiferentes() {
        PersonaHash ph = getPersonaHash(1);
        PersonaHash ph2 = getPersonaHash(2);
        List<PersonaHash> lph = new ArrayList<PersonaHash>();
        lph.add(ph);
        lph.add(ph2);
        List<PersonaHash> lph2 = new ArrayList<PersonaHash>();
        lph2.add(ph);
        LOGGER.info("Lista de PersonaHash {}, hash {}.", lph, HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5));
        LOGGER.info("Lista de PersonaHash 2 {}, hash {}.", lph2, HashUtils.obtenerHash(lph2, HashUtils.ALGORITMO_MD5));
        Assert.assertNotNull("El hash de la lista no debe ser nulo",
                HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5));
        Assert.assertNotEquals("Los hash deben ser iguales", HashUtils.obtenerHash(lph, HashUtils.ALGORITMO_MD5),
                HashUtils.obtenerHash(lph2, HashUtils.ALGORITMO_MD5));
    }

    /**
     * Generar instancias de prueba.
     * 
     * @param numero
     * @return PersonaHash
     */
    private PersonaHash getPersonaHash(Integer numero) {
        return new HashUtilsTest.PersonaHash("Pepe", "Pistola", numero);
    }

    /**
     * El objetivo es poder probar los diferentes comportamientos para la
     * funcion de hash.
     * 
     * @author sergio.e.goldentair
     *
     */
    public class PersonaHash implements Serializable {
        /** Serial id. */
        private static final long serialVersionUID = 4478749856853318476L;
        /** El nombre. */
        private String nombre;
        /** El apellido. */
        private String apellido;
        /** El UID. */
        private Integer numero;

        /**
         * @param nombre
         * @param apellido
         * @param numero
         */
        public PersonaHash(String nombre, String apellido, Integer numero) {
            super();
            this.nombre = nombre;
            this.apellido = apellido;
            this.numero = numero;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(numero).toHashCode();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }

            PersonaHash other = (PersonaHash) obj;
            EqualsBuilder eb = new EqualsBuilder();
            return eb.append(numero, other.numero).isEquals();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this).append("numero", numero).append("nombre", nombre)
                    .append("apellido", apellido).toString();
        }

    }
}
