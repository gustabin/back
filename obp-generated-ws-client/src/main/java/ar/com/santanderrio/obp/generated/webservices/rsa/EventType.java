
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NULL"/>
 *     &lt;enumeration value="SESSION_SIGNIN"/>
 *     &lt;enumeration value="FAILED_LOGIN_ATTEMPT"/>
 *     &lt;enumeration value="ENROLL"/>
 *     &lt;enumeration value="UPDATE_USER"/>
 *     &lt;enumeration value="CREATE_USER"/>
 *     &lt;enumeration value="OLB_ENROLL"/>
 *     &lt;enumeration value="FAILED_OLB_ENROLL_ATTEMPT"/>
 *     &lt;enumeration value="ACTIVATE_CARD"/>
 *     &lt;enumeration value="OPEN_NEW_ACCOUNT"/>
 *     &lt;enumeration value="REQUEST_CREDIT"/>
 *     &lt;enumeration value="ADD_PAYEE"/>
 *     &lt;enumeration value="EDIT_PAYEE"/>
 *     &lt;enumeration value="PAYMENT"/>
 *     &lt;enumeration value="DEPOSIT"/>
 *     &lt;enumeration value="STOCK_TRADE"/>
 *     &lt;enumeration value="OPTIONS_TRADE"/>
 *     &lt;enumeration value="CHANGE_LOGIN_ID"/>
 *     &lt;enumeration value="CHANGE_EMAIL"/>
 *     &lt;enumeration value="CHANGE_PHONE"/>
 *     &lt;enumeration value="CHANGE_ADDRESS"/>
 *     &lt;enumeration value="CHANGE_PASSWORD"/>
 *     &lt;enumeration value="CHANGE_LIFE_QUESTIONS"/>
 *     &lt;enumeration value="FAILED_CHANGE_PASSWORD_ATTEMPT"/>
 *     &lt;enumeration value="CHANGE_ALERT_SETTINGS"/>
 *     &lt;enumeration value="CHANGE_STATEMENT_SETTINGS"/>
 *     &lt;enumeration value="CHANGE_AUTH_DATA"/>
 *     &lt;enumeration value="SEND_SECURE_MESSAGE"/>
 *     &lt;enumeration value="READ_SECURE_MESSAGE"/>
 *     &lt;enumeration value="VIEW_CHECK"/>
 *     &lt;enumeration value="VIEW_STATEMENT"/>
 *     &lt;enumeration value="REQUEST_CHECK_COPY"/>
 *     &lt;enumeration value="REQUEST_STATEMENT_COPY"/>
 *     &lt;enumeration value="REQUEST_CHECKS"/>
 *     &lt;enumeration value="REQUEST_NEW_CARD"/>
 *     &lt;enumeration value="REQUEST_NEW_PIN"/>
 *     &lt;enumeration value="EXTRA_AUTH"/>
 *     &lt;enumeration value="USER_DETAILS"/>
 *     &lt;enumeration value="CLIENT_DEFINED"/>
 *     &lt;enumeration value="WITHDRAW"/>
 *     &lt;enumeration value="CARD_PIN_CHANGE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventType")
@XmlEnum
public enum EventType {

    NULL("NULL"),
    SESSION_SIGNIN("SESSION_SIGNIN"),
    FAILED_LOGIN_ATTEMPT("FAILED_LOGIN_ATTEMPT"),
    ENROLL("ENROLL"),
    UPDATE_USER("UPDATE_USER"),
    CREATE_USER("CREATE_USER"),
    OLB_ENROLL("OLB_ENROLL"),
    FAILED_OLB_ENROLL_ATTEMPT("FAILED_OLB_ENROLL_ATTEMPT"),
    ACTIVATE_CARD("ACTIVATE_CARD"),
    OPEN_NEW_ACCOUNT("OPEN_NEW_ACCOUNT"),
    REQUEST_CREDIT("REQUEST_CREDIT"),
    ADD_PAYEE("ADD_PAYEE"),
    EDIT_PAYEE("EDIT_PAYEE"),
    PAYMENT("PAYMENT"),
    DEPOSIT("DEPOSIT"),
    STOCK_TRADE("STOCK_TRADE"),
    OPTIONS_TRADE("OPTIONS_TRADE"),
    CHANGE_LOGIN_ID("CHANGE_LOGIN_ID"),
    CHANGE_EMAIL("CHANGE_EMAIL"),
    CHANGE_PHONE("CHANGE_PHONE"),
    CHANGE_ADDRESS("CHANGE_ADDRESS"),
    CHANGE_PASSWORD("CHANGE_PASSWORD"),
    CHANGE_LIFE_QUESTIONS("CHANGE_LIFE_QUESTIONS"),
    FAILED_CHANGE_PASSWORD_ATTEMPT("FAILED_CHANGE_PASSWORD_ATTEMPT"),
    CHANGE_ALERT_SETTINGS("CHANGE_ALERT_SETTINGS"),
    CHANGE_STATEMENT_SETTINGS("CHANGE_STATEMENT_SETTINGS"),
    CHANGE_AUTH_DATA("CHANGE_AUTH_DATA"),
    SEND_SECURE_MESSAGE("SEND_SECURE_MESSAGE"),
    READ_SECURE_MESSAGE("READ_SECURE_MESSAGE"),
    VIEW_CHECK("VIEW_CHECK"),
    VIEW_STATEMENT("VIEW_STATEMENT"),
    REQUEST_CHECK_COPY("REQUEST_CHECK_COPY"),
    REQUEST_STATEMENT_COPY("REQUEST_STATEMENT_COPY"),
    REQUEST_CHECKS("REQUEST_CHECKS"),
    REQUEST_NEW_CARD("REQUEST_NEW_CARD"),
    REQUEST_NEW_PIN("REQUEST_NEW_PIN"),
    EXTRA_AUTH("EXTRA_AUTH"),
    USER_DETAILS("USER_DETAILS"),
    CLIENT_DEFINED("CLIENT_DEFINED"),
    WITHDRAW("WITHDRAW"),
    CARD_PIN_CHANGE("CARD_PIN_CHANGE");

	private String event;
	
	EventType(String event) {
		this.event=event;
	}

	public String getEvent(){
		return event;
	}

	public String value(){
		return event;
	}
}
