package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;

public class CreditCardResponseDto {
    private CreditCardDto[] content;

    public CreditCardDto[] getContent() {
        return content;
    }

    public void setContent(CreditCardDto[] content) {
        this.content = content;
    }
}
