package ar.com.santanderrio.obp.servicios.solicitudes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class Form {

        @JsonProperty("product")
        public Product product;
        @JsonProperty("fullName")
        public String fullName;
        @JsonProperty("dni")
        public String dni;
        @JsonProperty("nup")
        public String nup;
        @JsonProperty("email")
        public String email;


        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) { this.dni = dni; }

        public String getNup() {
            return nup;
        }

        public void setNup(String nup) {
            this.nup = nup;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) { this.email = email; }
    }

