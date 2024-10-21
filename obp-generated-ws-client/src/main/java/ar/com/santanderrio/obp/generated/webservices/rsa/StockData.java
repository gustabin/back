
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This defines the composition for specific stock information
 * 
 * <p>Java class for StockData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StockData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ETF" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OTC" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SP500" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="currentMarketPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="last30DaysAveragePrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="last30DaysAverageVolume" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="last30DaysHighPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="last30DaysLowPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="percentSharesHeldByInstitution" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sharesFloating" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sharesOut" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="todayHighPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="todayLowPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="todayOpenPrice" type="{http://ws.csd.rsa.com}Amount" minOccurs="0"/>
 *         &lt;element name="todayVolume" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockData", propOrder = {
    "etf",
    "otc",
    "sp500",
    "currentMarketPrice",
    "last30DaysAveragePrice",
    "last30DaysAverageVolume",
    "last30DaysHighPrice",
    "last30DaysLowPrice",
    "percentSharesHeldByInstitution",
    "sharesFloating",
    "sharesOut",
    "symbol",
    "todayHighPrice",
    "todayLowPrice",
    "todayOpenPrice",
    "todayVolume"
})
public class StockData {

    @XmlElement(name = "ETF")
    protected Boolean etf;
    @XmlElement(name = "OTC")
    protected Boolean otc;
    @XmlElement(name = "SP500")
    protected Boolean sp500;
    protected Amount currentMarketPrice;
    protected Amount last30DaysAveragePrice;
    protected Integer last30DaysAverageVolume;
    protected Amount last30DaysHighPrice;
    protected Amount last30DaysLowPrice;
    protected Integer percentSharesHeldByInstitution;
    protected Integer sharesFloating;
    protected Integer sharesOut;
    protected String symbol;
    protected Amount todayHighPrice;
    protected Amount todayLowPrice;
    protected Amount todayOpenPrice;
    protected Integer todayVolume;

    /**
     * Gets the value of the etf property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isETF() {
        return etf;
    }

    /**
     * Sets the value of the etf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setETF(Boolean value) {
        this.etf = value;
    }

    /**
     * Gets the value of the otc property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOTC() {
        return otc;
    }

    /**
     * Sets the value of the otc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOTC(Boolean value) {
        this.otc = value;
    }

    /**
     * Gets the value of the sp500 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSP500() {
        return sp500;
    }

    /**
     * Sets the value of the sp500 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSP500(Boolean value) {
        this.sp500 = value;
    }

    /**
     * Gets the value of the currentMarketPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    /**
     * Sets the value of the currentMarketPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCurrentMarketPrice(Amount value) {
        this.currentMarketPrice = value;
    }

    /**
     * Gets the value of the last30DaysAveragePrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLast30DaysAveragePrice() {
        return last30DaysAveragePrice;
    }

    /**
     * Sets the value of the last30DaysAveragePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLast30DaysAveragePrice(Amount value) {
        this.last30DaysAveragePrice = value;
    }

    /**
     * Gets the value of the last30DaysAverageVolume property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLast30DaysAverageVolume() {
        return last30DaysAverageVolume;
    }

    /**
     * Sets the value of the last30DaysAverageVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLast30DaysAverageVolume(Integer value) {
        this.last30DaysAverageVolume = value;
    }

    /**
     * Gets the value of the last30DaysHighPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLast30DaysHighPrice() {
        return last30DaysHighPrice;
    }

    /**
     * Sets the value of the last30DaysHighPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLast30DaysHighPrice(Amount value) {
        this.last30DaysHighPrice = value;
    }

    /**
     * Gets the value of the last30DaysLowPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLast30DaysLowPrice() {
        return last30DaysLowPrice;
    }

    /**
     * Sets the value of the last30DaysLowPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLast30DaysLowPrice(Amount value) {
        this.last30DaysLowPrice = value;
    }

    /**
     * Gets the value of the percentSharesHeldByInstitution property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPercentSharesHeldByInstitution() {
        return percentSharesHeldByInstitution;
    }

    /**
     * Sets the value of the percentSharesHeldByInstitution property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPercentSharesHeldByInstitution(Integer value) {
        this.percentSharesHeldByInstitution = value;
    }

    /**
     * Gets the value of the sharesFloating property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSharesFloating() {
        return sharesFloating;
    }

    /**
     * Sets the value of the sharesFloating property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSharesFloating(Integer value) {
        this.sharesFloating = value;
    }

    /**
     * Gets the value of the sharesOut property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSharesOut() {
        return sharesOut;
    }

    /**
     * Sets the value of the sharesOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSharesOut(Integer value) {
        this.sharesOut = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the todayHighPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTodayHighPrice() {
        return todayHighPrice;
    }

    /**
     * Sets the value of the todayHighPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTodayHighPrice(Amount value) {
        this.todayHighPrice = value;
    }

    /**
     * Gets the value of the todayLowPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTodayLowPrice() {
        return todayLowPrice;
    }

    /**
     * Sets the value of the todayLowPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTodayLowPrice(Amount value) {
        this.todayLowPrice = value;
    }

    /**
     * Gets the value of the todayOpenPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTodayOpenPrice() {
        return todayOpenPrice;
    }

    /**
     * Sets the value of the todayOpenPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTodayOpenPrice(Amount value) {
        this.todayOpenPrice = value;
    }

    /**
     * Gets the value of the todayVolume property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTodayVolume() {
        return todayVolume;
    }

    /**
     * Sets the value of the todayVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTodayVolume(Integer value) {
        this.todayVolume = value;
    }

}
