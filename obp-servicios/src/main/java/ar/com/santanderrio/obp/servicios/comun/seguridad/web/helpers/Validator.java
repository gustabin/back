/*
 * Created on Sep 20, 2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ar.com.santanderrio.obp.servicios.comun.seguridad.web.helpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.exceptions.SecurityValidatorException;

/**
 * The Class Validator.
 *
 * @author b089716
 * 
 */
public final class Validator {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

	/** The Constant CsNumerico. */
	public static final String CS_NUMERICO = "0123456789";

	/** The Constant CsEspeciales. */
	public static final String CS_ESPECIALES = " \n\t\r\"?¿!´{}[]¡'&,.;:-@|";

	/** The Constant CsAlfaLower. */
	public static final String CS_ALFA_LOWER = "abcdefghijklmnñopqrstuvwxyzáéíóúü";

	/** The Constant CsAlfaUpper. */
	public static final String CS_ALFA_UPPER = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ";

	/** The Constant CsAlfa. */
	public static final String CS_ALFA = CS_ALFA_LOWER + CS_ALFA_UPPER;

	/** The Constant CsTexto. */
	public static final String CS_TEXTO = CS_ALFA + CS_ESPECIALES;

	/** The Constant CsSimbolo. */
	public static final String CS_SIMBOLO = Validator.CS_MATEMATICA + "&$#\"\\!<>;,:_";

	/** The Constant CsMatematica. */
	public static final String CS_MATEMATICA = "+-*/=.()%";

	/** The Constant CsEmail. */
	public static final String CS_EMAIL = "@._" + CS_ALFA_LOWER;

	/** The Constant CsMonto. */
	public static final String CS_MONTO = CS_NUMERICO + ".,-";

	/** The Constant CsTodo. */
	public static final String CS_TODO = CS_NUMERICO + CS_ALFA + CS_SIMBOLO + CS_MATEMATICA + CS_EMAIL + CS_MONTO
			+ CS_ESPECIALES;

	/** The Constant generic. */
	private static final String GENERIC_MSG = "Por inconvenientes en la comunicación, le solicitamos verifique mas tarde su operación o consulta. Disculpe las molestias.";

	/** The Constant ErrMsg. */
	private static final String ERROR_MSG = ContextHolder.getContext().getEnvironment().getProperty("LISTA_NEGRA_MSG",
			GENERIC_MSG);

	/** Lista default de palabras prohibidas. */
	private static final String DEFAULT_WORDLIST = "script|form|http:|https:|href|submit|input|cookie|../|update|from|delete|insert|select|javascript|vbscript|style|iframe|frame|img|having|alert|onmouseover|confirm|prompt";

	/** Lista default de payload palabras prohibidas. */
	private static final String DEFAULT_PAYLOAD_WORDLIST = "script|form|http:|https:|href|submit|cookie|../|update|from|delete|insert|select|javascript|vbscript|iframe|frame|img|having|alert|onmouseover|confirm|prompt";

	/** The wordlist. */
	private static Collection<String> wordlist = null;

	/** The payload wordlist. */
	private static Collection<String> payloadWordlist = null;

	/** The exception wordlist. */
	private static Collection<String> exceptionWordlist = null;

	/** The payload reduced wordlist. */
	private static Collection<String> payloadReducedWordlist = null;

	/** The reduced wordlist. */
	private static Collection<String> reducedWordlist = null;

	static {
		String validatorExceptionWordlist = ContextHolder.getContext().getEnvironment().getProperty("VALIDATOR_EXCEPTION_WORDLIST", "");
		exceptionWordlist = new HashSet<String>(Arrays.asList(StringUtils.split(validatorExceptionWordlist, '|')));

		String validatorWordlist = ContextHolder.getContext().getEnvironment().getProperty("VALIDATOR_WORDLIST",
				DEFAULT_WORDLIST);
		List<String> validatorWords = Arrays.asList(StringUtils.split(validatorWordlist, '|'));
		wordlist = new HashSet<String>(validatorWords);
		reducedWordlist = new HashSet<String>(validatorWords);
		reducedWordlist.removeAll(exceptionWordlist);

		String validatorPayloadWordlist = ContextHolder.getContext().getEnvironment().getProperty("VALIDATOR_PAYLOAD_WORDLIST",
		        DEFAULT_PAYLOAD_WORDLIST);
		List<String> validatorPayloadWords = Arrays.asList(StringUtils.split(validatorPayloadWordlist, '|'));
		payloadWordlist = new HashSet<String>(validatorPayloadWords);
		payloadReducedWordlist = new HashSet<String>(validatorPayloadWords);
		payloadReducedWordlist.removeAll(exceptionWordlist);
	}

	
	/**
	 * Constructor privado.
	 */
	private Validator() {
	}

	/**
	 * Checks if is char ok.
	 *
	 * @param p
	 *            the p
	 * @param charset
	 *            the charset
	 * @return true, if is char ok
	 */
	public static boolean isCharOk(char p, String charset) {
		return charset.indexOf(p) > -1;
	}

	/**
	 * Checks if is string ok.
	 *
	 * @param p
	 *            the p
	 * @param charset
	 *            the charset
	 * @return true, if is string ok
	 */
	private static boolean isStringOk(String p, String charset) {
		boolean ofendingChar = false;
		int i = 0;
		for (i = 0; !ofendingChar && i < p.length(); i++) {
			ofendingChar = !isCharOk(p.charAt(i), charset);
		}
		return i > (p.length() - 1) && !ofendingChar;
	}

	/**
	 * Checks if is words ok.
	 *
	 * @param p
	 *            the p
	 * @param useReducedWordList 
	 *            the use reduced word list
	 * @return true, if is words ok
	 */
	private static boolean isWordsOk(String p, Boolean isPayload, Boolean useReducedWordlist) {
		String newP = p.toLowerCase(Locale.getDefault());
		Iterator<String> it = null;
		if (isPayload) {
		    it = !useReducedWordlist ? payloadWordlist.iterator() : payloadReducedWordlist.iterator();
		} else {
		    it = !useReducedWordlist ? wordlist.iterator() : reducedWordlist.iterator();
		}
		while (it.hasNext()) {
			String tmp = it.next();
			int pos = 0;
			while ((pos = newP.indexOf(tmp, pos)) > -1) {
				if (isACompleteWord(newP, pos, tmp.length())) {
					LOGGER.info("La palabra ingresada {} no es valida segun la Lista Negra.", newP);
					return false;
				}
				pos++;
			}
		}
		return true;
	}

	/**
	 * Checks if is a complete word.
	 *
	 * @param s
	 *            the s
	 * @param pos
	 *            the pos
	 * @param wordLen
	 *            the word len
	 * @return true, if is a complete word
	 */
	public static boolean isACompleteWord(String s, int pos, int wordLen) {
		boolean preCharBlanco;
		boolean postCharBlanco;

		if (pos <= 0) {
			preCharBlanco = true;
		} else {
			preCharBlanco = !Character.isLetterOrDigit(s.charAt(pos - 1));
		}
		if (pos + wordLen >= s.length()) {
			postCharBlanco = true;
		} else {
			postCharBlanco = !Character.isLetterOrDigit(s.charAt(pos + wordLen));
		}
		return preCharBlanco && postCharBlanco;
	}

	/**
	 * Gets the param.
	 *
	 * @param p
	 *            the p
	 * @param defString
	 *            the def string
	 * @param useReducedWordList 
	 *            the use reduced word list
	 * @return the param
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	public static String getParam(String p, String defString, Boolean isPayload, Boolean useReducedWordList) throws SecurityValidatorException {
		if (p == null) {
			return defString;
		} else if (!isWordsOk(p, isPayload, useReducedWordList)) {
			throw new SecurityValidatorException(ERROR_MSG);
		}
		return p;
	}

	/**
	 * Gets the param.
	 *
	 * @param p
	 *            the p
	 * @param useReducedWordList 
	 *            the use reduced word list
	 * @return the param
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	public static String getParam(String p, Boolean isPayload, Boolean useReducedWordList) throws SecurityValidatorException {
		return getParam(p, null, isPayload, useReducedWordList);
	}
	
	/**
	 * Gets the param ext.
	 *
	 * @param p
	 *            the p
	 * @param tipo
	 *            the tipo
	 * @param oblig
	 *            the oblig
	 * @param defString
	 *            the def string
	 * @param useReducedWordList
	 *            the use reduced word list
	 * @return the param ext
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	public static String getParamExt(String p, String tipo, boolean oblig, String defString, Boolean useReducedWordList)
			throws SecurityValidatorException {
		if (p == null) {
			if (oblig) {
				throw new SecurityValidatorException(ERROR_MSG);
			} else {
				return defString;
			}
		} else if (isStringOk(getParam(p, defString, Boolean.FALSE, useReducedWordList), tipo)) {
			return p;
		} else {
			throw new SecurityValidatorException(ERROR_MSG);
		}
	}

	/**
	 * Gets the param ext.
	 *
	 * @param p
	 *            the p
	 * @param tipo
	 *            the tipo
	 * @param oblig
	 *            the oblig
	 * @param useReducedWordList
	 *            the use reduced word list
	 * @return the param ext
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	public static String getParamExt(String p, String tipo, boolean oblig, Boolean useReducedWordList) throws SecurityValidatorException {
		return getParamExt(p, tipo, oblig, null, useReducedWordList);
	}

	/**
	 * Gets the param ext.
	 *
	 * @param p
	 *            the p
	 * @param tipo
	 *            the tipo
	 * @param useReducedWordList
	 *            the use reduced word list
	 * @return the param ext
	 * @throws SecurityValidatorException
	 *             the security validator exception
	 */
	public static String getParamExt(String p, String tipo, Boolean useReducedWordList) throws SecurityValidatorException {
		return getParamExt(p, tipo, false, null, useReducedWordList);
	}

}