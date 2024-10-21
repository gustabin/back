/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ExcelBuilderBase.
 */
public abstract class ExcelBuilderBase {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBuilderBase.class);

	/** The Constant ENCABEZADO. */
	protected static final String ENCABEZADO = "encabezado_";

	/** The Constant ULTIMOS_MOVIMIENTOS. */
	public static final String ULTIMOS_MOVIMIENTOS = "UltimosMovimientos";
	
	/** The Constant VALORES_PENDIENTES. */
	public static final String VALORES_PENDIENTES = "valoresPendientes";
	
	/** The Constant MOVIMIENTOS_PENDIENTES_CONFIRMACION. */
	public static final String MOVIMIENTOS_PENDIENTES_CONFIRMACION = "movimientosPendientesDeConfirmacion";

	/** The Constant PIE. */
	protected static final String PIE = "legales";

	/** The Constant FOREACH. */
	protected static final String FOREACH = "#foreach";

	/** The Constant FOREACH. */
	protected static final String ENDFOREACH = "#endforeach";
	
	/** The Constant TODAY. */
	protected static final String TODAY = "#today";

	/** The Constant CONDITION. */
	protected static final String CONDITION = "#(";

	/** The Constant ATRIBUTO. */
	protected static final String ATRIBUTO = "#{";

	/** The Constant IF. */
	protected static final String IF = "#if";

	/** The Constant ELSE. */
	protected static final String ELSE = "#else";

	/** The Constant ENDIF. */
	protected static final String ENDIF = "#endif";

	/** The Constant ENDSHEET. */
	protected static final String ENDSHEET = "#endsheet";
	

	/** The Constant MAXCOL1LENGTH. */
	protected static final int MAXCOL1LENGTH = 2900;

	/** The Constant MAXROWPIE. */
	protected static final int MAXROWPIE = 6;

	/** The Constant PRIMERACOLUMNA. */
	protected static final int PRIMERACOLUMNA = 1;

	/** The Constant MAXIMACOLUMA. */
	protected static final int MAXIMACOLUMA = 7;
	

	/** The proxima fila destino. */
	protected int proximaFilaDestino = 0;

	/** The fila actual base. */
	protected int filaActualBase = 0;

	/** The items procesados. */
	protected int itemsProcesados = 0;

	/** The new workbook. */
	protected HSSFWorkbook newWorkbook;

	/** The body. */
	protected Object body;
	
	/** The columnas usadas. */
	protected Set<Integer> columnasUsadas = new HashSet<Integer>();

	/** The jexl engine. */
	@Autowired
	protected JexlEngine jexlEngine;

	/** The resource loader. */
	@Autowired
	protected ResourceLoader resourceLoader;
	
	/** The end sheet actual. */
	protected boolean endSheetActual;
	
	/**
	 * Obtiene un workbook por nombre.
	 *
	 * @param nombre
	 *            the nombre
	 * @return the workbook
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected HSSFWorkbook getWorkbook(String nombre) throws ExcelBuildException {
		FileInputStream file;
		try {
			Resource resource = resourceLoader.getResource("classpath:/excel/HSSF/" + nombre + ".xls");
			file = new FileInputStream(resource.getFile());
			return new HSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		} catch (IOException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		}
	}
	
	
	
	/**
	 * Gets the workbook HSSF.
	 *
	 * @param nombre
	 *            the nombre
	 * @return the workbook HSSF
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected HSSFWorkbook getWorkbookHSSF(String nombre) throws ExcelBuildException {
		FileInputStream file;
		try {
			Resource resource = resourceLoader.getResource("classpath:/excel/HSSF/" + nombre + ".xls");
			file = new FileInputStream(resource.getFile());
			return new HSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		} catch (IOException e) {
			LOGGER.error("Error al obtener el excel {}", nombre, e);
			throw new ExcelBuildException(e);
		}
	}
	
	
	/**
	 * Gets the cell as string.
	 *
	 * @param cell
	 *            the cell
	 * @return the cell as string
	 */
	protected String getCellAsString(Cell cell) {
		String val = "";
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			val = cell.getStringCellValue();
		}
		return val;
	}
	
	/**
	 * Copy row.
	 *
	 * @param template
	 *            the workbook
	 * @param sourceRowNum
	 *            the source row num
	 * @param destinationRowNum
	 *            the destination row num
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected void copyRow(HSSFSheet hojaBase, int sourceRowNum, int destinationRowNum) throws ExcelBuildException {
		// Get the source / new row
		HSSFRow sourceRow = hojaBase.getRow(sourceRowNum);

		// If the row exist in destination, push down all rows by 1 else create
		// a new row
		HSSFRow newRow = newWorkbook.getSheetAt(0).createRow(destinationRowNum);

		// Loop through source columns to add to new row
		for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
			// Grab a copy of the old/new cell
			HSSFCell oldCell = sourceRow.getCell(i);
			HSSFCell newCell = newRow.createCell(i);
			// If the old cell is null jump to next cell
			if (oldCell == null) {
				newCell = null;
				continue;
			}
			buildCell(oldCell, newCell, hojaBase, false);
		}
	}
	
	/**
	 * cada sub clase tiene su implementacion particular de este metodo.
	 *
	 * @param oldCell
	 *            the old cell
	 * @param newCell
	 *            the new cell
	 * @param oldWorkbook
	 *            the old workbook
	 * @param evaluaExpresion
	 *            the evalua expresion
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected abstract void buildCell(HSSFCell oldCell, HSSFCell newCell, HSSFSheet hojaBase, boolean evaluaExpresion)
			throws ExcelBuildException;


	/**
	 * Se debe reprocesar el template.
	 *
	 * @param baseRow
	 *            the base row
	 * @return the boolean
	 */
	protected Boolean seDebeReprocesarElTemplate(HSSFRow baseRow) {
		for (int i = 0; i < baseRow.getLastCellNum(); i++) {
			String value = getCellAsString(baseRow.getCell(i));
			if (StringUtils.isNotBlank(value)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	/**
	 * Es end for each.
	 *
	 * @param baseRow
	 *            the base row
	 * @return true, if successful
	 */
	protected boolean esEndForEach(HSSFRow baseRow) {
		for (int i = 0; i < baseRow.getLastCellNum(); i++) {
			if (StringUtils.isNotBlank(getCellAsString(baseRow.getCell(i)))) {
				HSSFCell currentCell = baseRow.getCell(i);
				String cellVal = getCellAsString(currentCell);
				if (cellVal.contains(ENDFOREACH)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * me replica un excel segun mi necesidad.
	 *
	 * @param cliente
	 *            the cliente
	 * @param proceso
	 *            the proceso
	 * @param body
	 *            the body
	 * @return the XSSF workbook
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */

	public HSSFWorkbook libroExcel(Cliente cliente, String proceso, Object body) throws ExcelBuildException {
		/** The proxima fila destino. */
		proximaFilaDestino = 0;

		/** The fila actual base. */
		filaActualBase = 0;

		String segmento = cliente.getSegmento().getNombre();
		HSSFWorkbook encabezado = getWorkbook(ENCABEZADO + segmento);
		HSSFWorkbook cuerpo = getWorkbook(proceso);
		HSSFWorkbook pie = getWorkbook(PIE);
		newWorkbook = getWorkbook(ENCABEZADO + segmento);
		HSSFSheet sheet = newWorkbook.getSheetAt(0);
		sheet.createRow(sheet.getLastRowNum());
		newWorkbook.setSheetName(newWorkbook.getSheetIndex(sheet), proceso);

		this.body = body;
		if(ULTIMOS_MOVIMIENTOS.equals(proceso)) {
		    pie = null;
		}
		crearExcel(encabezado, cuerpo, pie);
		return newWorkbook;
	}
	
	/**
	 * Cada sub clase implementa este metodo como necesite.
	 *
	 * @param encabezado
	 *            the encabezado
	 * @param cuerpo
	 *            the cuerpo
	 * @param pie
	 *            the pie
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected abstract void crearExcel(HSSFWorkbook encabezado, HSSFWorkbook cuerpo, HSSFWorkbook pie) throws ExcelBuildException;
	
	/**
	 * Crear celda.
	 *
	 * @param lineaNueva
	 *            the linea nueva
	 * @param columna
	 *            the columna
	 * @return the XSSF cell
	 */
	protected HSSFCell crearCelda(HSSFRow lineaNueva, int columna) {
		return lineaNueva.createCell(columna);
	}

	/**
	 * Crear fila.
	 *
	 * @param hojaNueva
	 *            the hoja nueva
	 * @param fila
	 *            the fila
	 * @return the XSSF row
	 */
	protected HSSFRow crearFila(HSSFSheet hojaNueva, int fila) {
		return hojaNueva.createRow(fila);
	}
	
	/**
	 * Evalua si la celda es una expresion.
	 *
	 * @param cell
	 *            the cell
	 * @return true, if successful
	 */
	protected boolean isExpresion(HSSFCell cell) {
		String texto = getCellAsString(cell);
		if (texto.startsWith("#")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Procesar atributo.
	 *
	 * @param texto
	 *            the texto
	 * @param cell
	 *            the cell
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	protected void procesarAtributo(String texto, HSSFCell cell) throws ExcelBuildException {
		String attr = texto.substring(2, texto.length() - 1);
		try {
			cell.setCellValue(BeanUtils.getProperty(body, attr));
		} catch (Exception e) {
			LOGGER.error("Error al procesar el texto", e);
			throw new ExcelBuildException(e);
		}
	}
	
	/**
	 * Procesar fecha hoy.
	 *
	 * @param cell
	 *            the cell
	 */
	protected void procesarFechaHoy(HSSFCell cell) {
		SimpleDateFormat formateador = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy '-' HH:mm",
				new Locale("es", "ES"));
		cell.setCellValue(formateador.format(new Date()));
	}
}
