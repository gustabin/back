package ar.com.santanderrio.obp.servicios.echeq.entities;

import javax.xml.ws.Holder;

import ar.com.santanderrio.obp.generated.webservices.echeq.BaeCCEStat;

/**
 * The Class ECheqOutEntity.
 *
 * @param <T> the generic type
 */
public class ECheqOutEntity<T> {

	/** The code. */
	Holder<String> code = new Holder<String>();
	
	/** The message. */
	Holder<String> message = new Holder<String>();
	
	/** The status. */
	Holder<BaeCCEStat> status = new Holder<BaeCCEStat>();
	
	/** The data. */
	Holder<T> data = new Holder<T>();

	public Holder<String> getCode() {
		return code;
	}

	public void setCode(Holder<String> code) {
		this.code = code;
	}

	public Holder<String> getMessage() {
		return message;
	}

	public void setMessage(Holder<String> message) {
		this.message = message;
	}

	public Holder<BaeCCEStat> getStatus() {
		return status;
	}

	public void setStatus(Holder<BaeCCEStat> status) {
		this.status = status;
	}

	public Holder<T> getData() {
		return data;
	}

	public void setData(Holder<T> data) {
		this.data = data;
	}

}
