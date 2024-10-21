package ar.com.santanderrio.obp.base.iatx.helpers;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxUtil.
 */
public final class IatxUtil {

	/**
	 * Instantiates a new iatx util.
	 */
	private IatxUtil() {

	}

	/**
	 * Censurar.
	 *
	 * @param s
	 *            the s
	 * @param nCensurar
	 *            the n censurar
	 * @return the string buffer
	 */
	static StringBuilder censurar(StringBuilder s, int nCensurar) {
		String strDelim = "|";
		int start = 0;
		int n = 0;
		// salteo lo necesario
		for (int ini = 0; ini < nCensurar && n >= 0; ++ini) {
			n = s.indexOf(strDelim, start);
			start = n + 1;
		}
		if (n >= 0) {
			n = s.indexOf(strDelim, start);
			if (n < 0) {
				n = s.length();
			}
			// censuro
			for (int i = start; i < n; ++i) {
				s.setCharAt(i, '*');
			}
		}
		return s;
	}

}
