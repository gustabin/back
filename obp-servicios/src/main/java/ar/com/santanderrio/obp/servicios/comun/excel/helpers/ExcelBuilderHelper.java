/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlException;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ExcelBuilderHelper.
 *
 * @author B039636
 */
@Component
@Scope("prototype")
public class ExcelBuilderHelper {

    /** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBuilderHelper.class);

	/** The Constant ENCABEZADO. */
	private static final String ENCABEZADO = "encabezado_";

	/** The Constant ULTIMOS_MOVIMIENTOS. */
	public static final String ULTIMOS_MOVIMIENTOS = "UltimosMovimientos";
	
	/** The Constant VALORES_PENDIENTES. */
	public static final String VALORES_PENDIENTES = "valoresPendientes";
	
	public static final String PLAZOS_FIJOS = "PlazosFijos";
	
	/** The Constant MOVIMIENTOS_PENDIENTES_CONFIRMACION. */
	public static final String MOVIMIENTOS_PENDIENTES_CONFIRMACION = "movimientosPendientesDeConfirmacion";

	/** The Constant PIE. */
	private static final String PIE = "legales";

	/** The Constant FOREACH. */
	private static final String FOREACH = "#foreach";

	/** The Constant FOREACH. */
	private static final String ENDFOREACH = "#endforeach";

	/** The Constant TODAY. */
	private static final String TODAY = "#today";

	/** The Constant CONDITION. */
	private static final String CONDITION = "#(";

	/** The Constant ATRIBUTO. */
	private static final String ATRIBUTO = "#{";

	/** The Constant IF. */
	private static final String IF = "#if";

	/** The Constant ELSE. */
	private static final String ELSE = "#else";

	/** The Constant ENDIF. */
	private static final String ENDIF = "#endif";

	/** The Constant ENDSHEET. */
	private static final String ENDSHEET = "#endsheet";

	/** The Constant MAXCOL1LENGTH. */
	private static final int MAXCOL1LENGTH = 2900;

	/** The Constant MAXROWPIE. */
	private static final int MAXROWPIE = 6;

	/** The Constant PRIMERACOLUMNA. */
	private static final int PRIMERACOLUMNA = 1;

	/** The Constant MAXIMACOLUMA. */
	private static final int MAXIMACOLUMA = 7;

	/** The proxima fila destino. */
	private int proximaFilaDestino = 0;

	/** The fila actual base. */
	private int filaActualBase = 0;

	/** The new workbook. */
	private HSSFWorkbook newWorkbook;

	/** The body. */
	private Object body;

	/** The columnas usadas. */
	private Set<Integer> columnasUsadas = new HashSet<Integer>();

	/** The jexl engine. */
	@Autowired
	private JexlEngine jexlEngine;

	/** The resource loader. */
	@Autowired
	private ResourceLoader resourceLoader;

	/** The if actual. */
	private Boolean ifActual = null;

	/** The end sheet actual. */
	private boolean endSheetActual;
	
	private Map<String, HSSFCellStyle> estilos = new HashMap<String,HSSFCellStyle>();
	
	   /** The CELL'S STYLES. */
    private static final String TOP = "tp_";
    private static final String LEFT= "lt_";
    private static final String RIGHT = "rt_";
    private static final String BOTTOM = "bt_";
    private static final String BACKGROUND = "bg_";
    private static final String FILL_FOREGROUND = "ffg";
    private static final String FILL_PATTERN = "fp_";
    private static final String FONT = "font_";
    private static final String FONT_UNDERLINE = "fu";
    private static final String FONT_ITALIC = "fi";
    private static final String V_ALIGNMENT = "valignment_";
    private static final String ALIGNMENT = "alignment_";

	/**
	 * Realiza el excel tomando el encabezado cuerpo y pie para generar un nuevo
	 * archivo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param proceso
	 *            the proceso
	 * @param body
	 *            the body
	 * @return the byte[]
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	public byte[] hacerExcel(Cliente cliente, String proceso, Object body) throws ExcelBuildException {
		String segmento = getSegmento(cliente.getSegmento());
		HSSFWorkbook encabezado = getWorkbook(ENCABEZADO + segmento);
		HSSFWorkbook cuerpo = getWorkbook(proceso);
		HSSFWorkbook pie = ULTIMOS_MOVIMIENTOS.equals(proceso) ? getWorkbook(PIE) : null;
		newWorkbook = getWorkbook(ENCABEZADO + segmento);
		HSSFSheet sheet = newWorkbook.getSheetAt(0);
		sheet.createRow(sheet.getLastRowNum());
		newWorkbook.setSheetName(newWorkbook.getSheetIndex(sheet), proceso);

		this.body = body;
		crearExcel(encabezado, cuerpo, pie);
// 		Para que, cuando no haya datos para mostrar, de todas formas arme un excel vacio		
//		if (itemsProcesados == 0) {
//			throw (new ExcelBuildException());
//		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			newWorkbook.write(os);
		} catch (IOException e) {
			LOGGER.error("Error de io del excel para el cliente {}.", cliente, e);
			throw new ExcelBuildException(e);
		}
		return os.toByteArray();
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

		String segmento = getSegmento(cliente.getSegmento());
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
	 * Crear excel.
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
	private void crearExcel(HSSFWorkbook encabezado, HSSFWorkbook cuerpo, HSSFWorkbook pie) throws ExcelBuildException {

        append(encabezado);
        append(cuerpo);        

        HSSFSheet newSheet = this.newWorkbook.getSheetAt(0);
        /*
         * Si no se usa el pie comentar estas lineas
         */
        int filaInicioPie = proximaFilaDestino;
        if(pie != null) {
            append(pie);
        }
        newSheet.addMergedRegion(new CellRangeAddress(filaInicioPie, filaInicioPie + MAXROWPIE, PRIMERACOLUMNA, MAXIMACOLUMA));

		newSheet.setColumnWidth(1, MAXCOL1LENGTH);

		HSSFFormulaEvaluator.evaluateAllFormulaCells(this.newWorkbook);
		for (Integer columnaUsada : columnasUsadas) {
			if (columnaUsada != 0) {
				newSheet.autoSizeColumn(columnaUsada);
			}
		}

		newSheet.setDisplayGridlines(cuerpo.getSheetAt(0).isDisplayGridlines());
		newSheet.setPrintGridlines(cuerpo.getSheetAt(0).isPrintGridlines());
	}

	/**
	 * Append.
	 *
	 * @param wbBase
	 *            the wb base
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private void append(HSSFWorkbook wbBase) throws ExcelBuildException {
		HSSFSheet hojaBase = wbBase.getSheetAt(0);
		HSSFSheet hojaNueva = newWorkbook.getSheetAt(0);
		endSheetActual = false;
		ifActual = null;
		for (filaActualBase = 0; filaActualBase <= hojaBase.getLastRowNum() && !endSheetActual; filaActualBase++) {
			procesarFila(hojaBase, hojaNueva, wbBase);
		}
		hojaNueva.createRow(hojaNueva.getLastRowNum() + 1);
	}

	/**
	 * Procesar fila.
	 *
	 * @param hojaBase
	 *            the hoja base
	 * @param hojaNueva
	 *            the hoja nueva
	 * @param wbBase
	 *            the wb base
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private void procesarFila(HSSFSheet hojaBase, HSSFSheet hojaNueva, HSSFWorkbook wbBase) throws ExcelBuildException {
		HSSFRow row2 = hojaBase.getRow(filaActualBase);

		HSSFRow lineaNueva = null;

		// si no existe condicion o la condicion es true
		if (ifActual == null || ifActual) {
			lineaNueva = crearFila(hojaNueva, proximaFilaDestino++);
		}
        int auxFila = filaActualBase;
		int columna = 0;
		if (row2 != null) {
            for (int j = 0; j < row2.getLastCellNum() && auxFila == filaActualBase; j++) {
				HSSFCell cell = row2.getCell(j);
				if (lineaNueva != null) {
					HSSFCell celdaNueva = crearCelda(lineaNueva, columna++);
					if (cell != null) {
						buildCell(cell, celdaNueva, wbBase, true);
					}
				} else {
					// evaluar else o endif
					if (isExpresion(cell)) {
						precesarExpresion(cell, cell, wbBase);
					}
				}
			}
		}
	}

	/**
	 * Crear celda.
	 *
	 * @param lineaNueva
	 *            the linea nueva
	 * @param columna
	 *            the columna
	 * @return the XSSF cell
	 */
	private HSSFCell crearCelda(HSSFRow lineaNueva, int columna) {
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
	private HSSFRow crearFila(HSSFSheet hojaNueva, int fila) {
		return hojaNueva.createRow(fila);
	}

	/**
	 * Builds the cell.
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
	private void buildCell(HSSFCell oldCell, HSSFCell newCell, HSSFWorkbook oldWorkbook, boolean evaluaExpresion)
			throws ExcelBuildException {

		boolean columnaUsada = true;

		// If the old cell is null jump to next cell
		if (oldCell == null) {
			return;
		}

		cloneStyle(oldCell, newCell, oldWorkbook);

		// If there is a cell comment, copy
		if (oldCell.getCellComment() != null) {
			newCell.setCellComment(oldCell.getCellComment());
		}

		// If there is a cell hyperlink, copy
		if (oldCell.getHyperlink() != null) {
			newCell.setHyperlink(oldCell.getHyperlink());
		}

		// Set the cell data type
		newCell.setCellType(oldCell.getCellType());

		// Set the cell data value
		setCellDataValue(oldCell, newCell, columnaUsada, evaluaExpresion, oldWorkbook);

		columnasUsadas.add(newCell.getColumnIndex());

	}

	/**
	 * Sets the cell data value.
	 *
	 * @param oldCell
	 *            the old cell
	 * @param newCell
	 *            the new cell
	 * @param columnaUsada
	 *            the columna usada
	 * @param evaluaExpresion
	 *            the evalua expresion
	 * @param oldWorkbook
	 *            the old workbook
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private void setCellDataValue(HSSFCell oldCell, HSSFCell newCell, boolean columnaUsada, boolean evaluaExpresion,
			HSSFWorkbook oldWorkbook) throws ExcelBuildException {
		switch (oldCell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			newCell.setCellValue(oldCell.getStringCellValue());
			columnaUsada = false;
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			newCell.setCellValue(oldCell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			newCell.setCellErrorValue(oldCell.getErrorCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			newCell.setCellFormula(oldCell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			newCell.setCellValue(oldCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			if (evaluaExpresion && isExpresion(oldCell)) {
				try {
					precesarExpresion(oldCell, newCell, oldWorkbook);
				} catch (ExcelBuildException e) {
					LOGGER.info("Coleccion sin datos: " + e);
				} catch (JexlException e) {
					LOGGER.info("Celda sin datos: " + e);
				}
			} else {
				newCell.setCellValue(oldCell.getRichStringCellValue());
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Precesar expresion.
	 *
	 * @param oldCell
	 *            the old cell
	 * @param newCell
	 *            the new cell
	 * @param oldWorkbook
	 *            the old workbook
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private void precesarExpresion(HSSFCell oldCell, HSSFCell newCell, HSSFWorkbook oldWorkbook)
			throws ExcelBuildException {
		String texto = getCellAsString(oldCell);
		if (ifActual == null || ifActual) {
			if (texto.startsWith(FOREACH)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(this.filaActualBase);
				this.filaActualBase = procesarForeach(this.body, texto, oldWorkbook, list);
			}
			if (texto.startsWith(TODAY)) {
				procesarFechaHoy(newCell);
			}
			if (texto.startsWith(CONDITION)) {
				procesarCondicion(texto, newCell);
			}
			if (texto.startsWith(IF)) {
				procesarIf(texto);
			}
			if (texto.startsWith(ATRIBUTO)) {
				procesarAtributo(texto, newCell);
			}
			if (texto.startsWith(ENDSHEET)) {
				procesarEndSheet();
			}
		}
		procesarElseEndif(texto);
	}

	/**
	 * Procesar else endif.
	 *
	 * @param texto
	 *            the texto
	 */
	private void procesarElseEndif(String texto) {
		if (texto.startsWith(ELSE)) {
			procesarElse();
		}
		if (texto.startsWith(ENDIF)) {
			procesarEndif();
		}
	}

	/**
	 * Procesar end sheet.
	 */
	private void procesarEndSheet() {
		proximaFilaDestino--;
		this.endSheetActual = true;
		// limpio los if
		this.ifActual = null;
	}

	/**
	 * Procesar endif.
	 */
	private void procesarEndif() {
		if (ifActual != null && ifActual) {
			proximaFilaDestino--;
		}
		this.ifActual = null;
	}

	/**
	 * Procesar else.
	 */
	private void procesarElse() {
		if (ifActual != null && ifActual) {
			proximaFilaDestino--;
		}
		if (ifActual != null) {
			this.ifActual = !this.ifActual;
		}
	}

	/**
	 * Procesar if.
	 *
	 * @param texto
	 *            the texto
	 */
	private void procesarIf(String texto) {
		proximaFilaDestino--;

		MapContext myMapContext = new MapContext();
		myMapContext.set("root", body);

		String condicionIf = texto.replace(IF + " ", "");
		Object result = jexlEngine.createScript(condicionIf).execute(myMapContext);

		Boolean condicionResultado = (Boolean) result;

		if (condicionResultado) {
			this.ifActual = true;
		} else {
			this.ifActual = false;
		}
	}

	/**
	 * Procesar condicion.
	 *
	 * @param texto
	 *            the texto
	 * @param newCell
	 *            the new cell
	 */
	private void procesarCondicion(String texto, HSSFCell newCell) {
		MapContext myMapContext = new MapContext();
		myMapContext.set("root", body);
		ejecutarExpresion(texto, newCell, myMapContext);
	}

	/**
	 * Procesar condicion.
	 *
	 * @param texto
	 *            the texto
	 * @param newCell
	 *            the new cell
	 * @param myMapContext
	 *            the my map context
	 */
	private void ejecutarExpresion(String texto, HSSFCell newCell, MapContext myMapContext) {
		myMapContext.set("ISBANStringUtils", ISBANStringUtils.class);
		Object myObjectWithTernaryConditional = jexlEngine.createScript(texto.substring(1)).execute(myMapContext);

		if (myObjectWithTernaryConditional instanceof Number) {
			Double number = ((Number) myObjectWithTernaryConditional).doubleValue();
			newCell.setCellValue(number);
		} else {
			String string = "";
			if (myObjectWithTernaryConditional != null) {
				string = myObjectWithTernaryConditional.toString();
			}
			newCell.setCellValue(string);
		}
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
	private void procesarAtributo(String texto, HSSFCell cell) throws ExcelBuildException {
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
	private void procesarFechaHoy(HSSFCell cell) {
		SimpleDateFormat formateador = new SimpleDateFormat("EEEE ',' dd 'de'  MMMM 'de' yyyy '-' HH:mm",
				new Locale("es", "ES"));
		cell.setCellValue(formateador.format(new Date()));
	}

	/** The fila. */
	StringBuilder fila = new StringBuilder("");

	/** The columna. */
	StringBuilder columna = new StringBuilder("");

	/**
	 * Procesar foreach.
	 *
	 * @param body
	 *            the body
	 * @param texto
	 *            the texto
	 * @param oldWorkbook
	 *            the old workbook
	 * @param filaActualList
	 *            the fila actual list
	 * @return the int
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private int procesarForeach(Object body, String texto, HSSFWorkbook oldWorkbook, List<Integer> filaActualList)
			throws ExcelBuildException {
		// filaActualBase => el valor indica el indice donde se encuentra el tag
		// #foreach [collecion]
		int filaActualBase = filaActualList.get(NumberUtils.INTEGER_ZERO);
		// Se obtiene la coleccion mencionada en el foreach
		String nombreColeccion = texto.replace(FOREACH + " ", "");
		Collection<?> collection;
		try {
			collection = (Collection<?>) PropertyUtils.getProperty(body, nombreColeccion);
		} catch (Exception e) {
			LOGGER.error("Error al obtener la prop {}", nombreColeccion, e);
			throw new ExcelBuildException(e);
		}

		//
		if (collection == null || collection.isEmpty()) {
			throw new ExcelBuildException("Collecion vacia");
		}
		HSSFSheet sheet = oldWorkbook.getSheetAt(0);
		int filaActual = filaActualBase;
		// La posicion donde escribir en el excel final.
		proximaFilaDestino--;

		for (Object itemCollection : collection) {

			filaActual = filaActualBase;
			// Donde se ubica la primer instruccion del body del foreach
			++filaActual;
			// la primer fila del template
			HSSFRow baseRow = sheet.getRow(filaActual);

			MapContext myMapContext = new MapContext();
			myMapContext.set(nombreColeccion, itemCollection);
			boolean existeEndForEach = false;
			do {
				// Realiza la copia de la fila del template al excel destino
				// para obtener el formato de la fila.
				copyRow(oldWorkbook, baseRow.getRowNum(), proximaFilaDestino);
				// Se obtiene la fila del excel destino para la escritura, si es
				// necesario.
				HSSFRow newRow = newWorkbook.getSheetAt(0).getRow(proximaFilaDestino);

				// Se cicla por columna de la fila del template que esta
				// seleccionada.
				for (int i = 0; i < baseRow.getLastCellNum(); i++) {
					if (StringUtils.isNotBlank(getCellAsString(baseRow.getCell(i)))) {
						HSSFCell currentCell = baseRow.getCell(i);
						String cellVal = getCellAsString(currentCell);
						HSSFCell newCell = newRow.getCell(i);
						if (isExpresion(currentCell)) {
							if (cellVal.startsWith(ATRIBUTO)) {
								String attr = cellVal.substring(2, cellVal.length() - 1);
								try {
									newCell.setCellValue(BeanUtils.getProperty(itemCollection, attr));
								} catch (Exception e) {
									LOGGER.error("Error al generar celda del excel {}.", attr, e);
									throw new ExcelBuildException(e);
								}
							} else {
								if (cellVal.startsWith(FOREACH)) {
									List<Integer> list = new ArrayList<Integer>();
									list.add(filaActual);
									proximaFilaDestino++;
									filaActual = procesarForeach(itemCollection, cellVal, oldWorkbook, list);
								} else {
									ejecutarExpresion(cellVal, newCell, myMapContext);
								}
							}
						} else {
							// Realiza una copia directa del valor de la celda
							// de template sobre la celda del excel resultante.
							newCell.setCellValue(cellVal);
						}
					}
					// end for
				}
				proximaFilaDestino++;
				// Se posiciona sobre la siguiente linea
				baseRow = sheet.getRow(++filaActual);
				existeEndForEach = esEndForEach(baseRow);
			} while (!existeEndForEach);
		} // end for

		return filaActual;
	}

	/**
	 * Es end for each.
	 *
	 * @param baseRow
	 *            the base row
	 * @return true, if successful
	 */
	private boolean esEndForEach(HSSFRow baseRow) {
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
	 * Evalua si la celda es una expresion.
	 *
	 * @param cell
	 *            the cell
	 * @return true, if successful
	 */
	private boolean isExpresion(HSSFCell cell) {
		String texto = getCellAsString(cell);
		if (texto.startsWith("#")) {
			return true;
		}
		return false;
	}

	/**
	 * Clone style.
	 *
	 * @param oldCell
	 *            the old cell
	 * @param newCell
	 *            the new cell
	 */
	private void cloneStyle(HSSFCell oldCell, HSSFCell newCell, HSSFWorkbook oldWorkbook) {
		HSSFCellStyle origStyle = oldCell.getCellStyle();
		HSSFCellStyle cellStyle = getStyle(origStyle, oldWorkbook);
		newCell.setCellStyle(cellStyle);
	}

    private HSSFCellStyle getStyle(HSSFCellStyle origStyle, HSSFWorkbook oldWorkbook) {
        
        HSSFFont font = origStyle.getFont(oldWorkbook);
        String style_key = BACKGROUND + origStyle.getFillBackgroundColor() + 
                            FILL_FOREGROUND + origStyle.getFillForegroundColor() +
                            FILL_PATTERN + origStyle.getFillPattern() +
                            TOP + origStyle.getBorderTop() +
                            LEFT + origStyle.getBorderLeft() +
                            RIGHT + origStyle.getBorderRight() +
                            BOTTOM + origStyle.getBorderBottom() +
                            ALIGNMENT + origStyle.getAlignment() +
                            V_ALIGNMENT + origStyle.getVerticalAlignment() +
                            FONT + font.getFontName() +
                            FONT_UNDERLINE + font.getUnderline() +
                            FONT_ITALIC + font.getItalic() +
                            font.getFontHeight() +
                            font.getBold() + font.getColor();
	    if(!estilos.containsKey(style_key)) {
	        HSSFCellStyle newStyle = newWorkbook.createCellStyle();
	        newStyle.cloneStyleFrom(origStyle);
	        estilos.put(style_key, newStyle);
	    }
        return estilos.get(style_key);
    }

	/**
	 * Obtiene el segmento del usuario.
	 *
	 * @param segmento
	 *            the segmento
	 * @return the segmento
	 */
	private String getSegmento(Segmento segmento) {
		return segmento.getNombre();
	}

	/**
	 * Obtiene un workbook por nombre.
	 *
	 * @param nombre
	 *            the nombre
	 * @return the workbook
	 * @throws ExcelBuildException
	 *             the excel build exception
	 */
	private HSSFWorkbook getWorkbook(String nombre) throws ExcelBuildException {
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
	private String getCellAsString(Cell cell) {
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
	private void copyRow(HSSFWorkbook template, int sourceRowNum, int destinationRowNum) throws ExcelBuildException {
		// Get the source / new row
		HSSFRow sourceRow = template.getSheetAt(0).getRow(sourceRowNum);

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
			buildCell(oldCell, newCell, template, false);
		}
	}
}
