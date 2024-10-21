
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the compostion for stock trade information
 * 
 * <p>Java class for StockTradeData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StockTradeData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allOrNone" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lowerChangeLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lowerPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="numberOfShares" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="priceType" type="{http://ws.csd.rsa.com}PriceType" minOccurs="0"/>
 *         &lt;element name="stockData" type="{http://ws.csd.rsa.com}StockData" minOccurs="0"/>
 *         &lt;element name="termType" type="{http://ws.csd.rsa.com}TermType" minOccurs="0"/>
 *         &lt;element name="tradeType" type="{http://ws.csd.rsa.com}TradeType" minOccurs="0"/>
 *         &lt;element name="upperChangeLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="upperPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockTradeData", propOrder = {
    "allOrNone",
    "lowerChangeLimit",
    "lowerPrice",
    "numberOfShares",
    "priceType",
    "stockData",
    "termType",
    "tradeType",
    "upperChangeLimit",
    "upperPrice"
})
public class StockTradeData {

    protected Boolean allOrNone;
    protected Integer lowerChangeLimit;
    protected Amount lowerPrice;
    protected Integer numberOfShares;
    protected PriceType priceType;
    protected StockData stockData;
    protected TermType termType;
    protected TradeType tradeType;
    protected Integer upperChangeLimit;
    protected Amount upperPrice;

    /**
     * Gets the value of the allOrNone property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllOrNone() {
        return allOrNone;
    }

    /**
     * Sets the value of the allOrNone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllOrNone(Boolean value) {
        this.allOrNone = value;
    }

    /**
     * Gets the value of the lowerChangeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLowerChangeLimit() {
        return lowerChangeLimit;
    }

    /**
     * Sets the value of the lowerChangeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLowerChangeLimit(Integer value) {
        this.lowerChangeLimit = value;
    }

    /**
     * Gets the value of the lowerPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLowerPrice() {
        return lowerPrice;
    }

    /**
     * Sets the value of the lowerPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLowerPrice(Amount value) {
        this.lowerPrice = value;
    }

    /**
     * Gets the value of the numberOfShares property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * Sets the value of the numberOfShares property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfShares(Integer value) {
        this.numberOfShares = value;
    }

    /**
     * Gets the value of the priceType property.
     * 
     * @return
     *     possible object is
     *     {@link PriceType }
     *     
     */
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Sets the value of the priceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceType }
     *     
     */
    public void setPriceType(PriceType value) {
        this.priceType = value;
    }

    /**
     * Gets the value of the stockData property.
     * 
     * @return
     *     possible object is
     *     {@link StockData }
     *     
     */
    public StockData getStockData() {
        return stockData;
    }

    /**
     * Sets the value of the stockData property.
     * 
     * @param value
     *     allowed object is
     *     {@link StockData }
     *     
     */
    public void setStockData(StockData value) {
        this.stockData = value;
    }

    /**
     * Gets the value of the termType property.
     * 
     * @return
     *     possible object is
     *     {@link TermType }
     *     
     */
    public TermType getTermType() {
        return termType;
    }

    /**
     * Sets the value of the termType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TermType }
     *     
     */
    public void setTermType(TermType value) {
        this.termType = value;
    }

    /**
     * Gets the value of the tradeType property.
     * 
     * @return
     *     possible object is
     *     {@link TradeType }
     *     
     */
    public TradeType getTradeType() {
        return tradeType;
    }

    /**
     * Sets the value of the tradeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeType }
     *     
     */
    public void setTradeType(TradeType value) {
        this.tradeType = value;
    }

    /**
     * Gets the value of the upperChangeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUpperChangeLimit() {
        return upperChangeLimit;
    }

    /**
     * Sets the value of the upperChangeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUpperChangeLimit(Integer value) {
        this.upperChangeLimit = value;
    }

    /**
     * Gets the value of the upperPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getUpperPrice() {
        return upperPrice;
    }

    /**
     * Sets the value of the upperPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setUpperPrice(Amount value) {
        this.upperPrice = value;
    }

}
