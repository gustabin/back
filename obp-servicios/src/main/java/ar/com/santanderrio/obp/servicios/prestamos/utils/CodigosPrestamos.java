package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.util.List;
import java.util.Map;

public class CodigosPrestamos {

	private Map<Integer, List<Integer>> data = null;

	public Map<Integer, List<Integer>> getData() {
		return data;
	}

	public void setData(Map<Integer, List<Integer>> data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodigosPrestamos other = (CodigosPrestamos) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
