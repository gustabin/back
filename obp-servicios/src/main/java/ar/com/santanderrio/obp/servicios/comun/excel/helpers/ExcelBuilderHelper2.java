/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jexl3.JexlException;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ExcelBuilderHelper.
 *
 * @author B039636
 */
@Component
@Scope("prototype")
public class ExcelBuilderHelper2 extends ExcelBuilderBase{
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBuilderHelper2.class);

	/** The Constant ULTIMOS_CONSUMOS_DE_TARJETAS. */
	public static final String ULTIMOS_CONSUMOS_DE_TARJETAS = "consumosDeTarjetasRenovado";
	
	public static final String PRESTAMO_CUOTAS_PAGAS = "prestamoCuotasPagas";
	
	public static final String PRESTAMO_PROXIMAS_CUOTAS = "prestamoProximasCuotas";


	/** The if actual. */
	private List<Boolean> ifActual = new ArrayList<Boolean>();



	/** The old cell. */
	private HSSFCell oldCell;

	/** The new cell. */
	private HSSFCell newCell;

	/** The proceso string. */
	private String procesoString;

	/** The my map context. */
	MapContext myMapContext = new MapContext();
	
	Map<HSSFCellStyle, HSSFCellStyle> cellStyleMap;

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
	    this.procesoString = proceso;
	    this.body = body;
	    
		String segmento = cliente.getSegmento().getNombre();
		String nombreEncabezado = ENCABEZADO + segmento;
		
		HSSFWorkbook encabezado = getWorkbook(nombreEncabezado);
		HSSFWorkbook cuerpo = getWorkbook(proceso);
		HSSFWorkbook pie = ULTIMOS_MOVIMIENTOS.equals(proceso) ? getWorkbook(PIE) : null;
		
		crearExcel(encabezado, cuerpo, pie);
		newWorkbook.setSheetName(0, proceso);
		
		if (itemsProcesados == 0) {
			throw new ExcelBuildException();
		}

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
	protected void crearExcel(HSSFWorkbook encabezado, HSSFWorkbook cuerpo, HSSFWorkbook pie) throws ExcelBuildException {
	    this.myMapContext.set("ISBANStringUtils", ISBANStringUtils.class);
	    this.myMapContext.set("root", body);
	    this.newWorkbook = encabezado;
	    this.cellStyleMap = new HashMap<HSSFCellStyle, HSSFCellStyle>();
	   
	    append(encabezado);
	    
		append(cuerpo);

		HSSFSheet newSheet = this.newWorkbook.getSheetAt(0);
		/*
		 * Si no se usa el pie comentar estas lineas
		 */
		
		if (pie != null) {
		    int filaInicioPie = proximaFilaDestino;
			append(pie);
			newSheet.addMergedRegion(
	                new CellRangeAddress(filaInicioPie, filaInicioPie + MAXROWPIE, PRIMERACOLUMNA, MAXIMACOLUMA));
	        newSheet.setColumnWidth(1, MAXCOL1LENGTH);
		}

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
		this.endSheetActual = false;
		this.ifActual = new ArrayList<Boolean>();
		for (filaActualBase = 0; filaActualBase <= hojaBase.getLastRowNum() && !this.endSheetActual; filaActualBase++) {
			procesarFila(hojaBase, hojaNueva);
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
	private void procesarFila(HSSFSheet hojaBase, HSSFSheet hojaNueva) throws ExcelBuildException {
		HSSFRow row2 = hojaBase.getRow(this.filaActualBase);

		HSSFRow lineaNueva = null;

		// si no existe condicion o la condicion es true
		if (ifActual == null || ifActual.isEmpty() || ifActual.get(ifActual.size() - 1)) {
			lineaNueva = crearFila(hojaNueva, this.proximaFilaDestino++);
		}
		
		procesarMergedRegions(hojaBase, hojaNueva, this.filaActualBase, this.proximaFilaDestino - 1);

		int columna = 0;
		if (row2 != null) {
			for (int j = 0; j < row2.getLastCellNum(); j++) {
			    if (row2.getRowNum() != this.filaActualBase) {
			        break;
			    }
				HSSFCell cell = row2.getCell(j);
				if (lineaNueva != null) {
					HSSFCell celdaNueva = crearCelda(lineaNueva, columna++);
					this.newCell = celdaNueva;
					if (cell != null) {
					    this.oldCell = cell;
						buildCell(cell, celdaNueva, hojaBase, true);
					}
				} else {
					// evaluar else o endif
					if (isExpresion(cell)) {
						precesarExpresion(cell, cell, hojaBase);
					}
				}
			}
		}
	}

	private void procesarMergedRegions(HSSFSheet hojaBase, HSSFSheet hojaNueva, int filaBase, int fila) {
	    List<CellRangeAddress> mergedRegions = new ArrayList<CellRangeAddress>();
        for (int i = 0; i < hojaBase.getNumMergedRegions(); i++) {
            CellRangeAddress region = hojaBase.getMergedRegion(i); //Region of merged cells
            if (region.getFirstRow() == filaBase) {
                mergedRegions.add(new CellRangeAddress(fila, fila + region.getLastRow() - region.getFirstRow(), region.getFirstColumn(), region.getLastColumn()));
            }
        }
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            hojaNueva.addMergedRegion(cellRangeAddress);
        }
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
	protected void buildCell(HSSFCell oldCell, HSSFCell newCell, HSSFSheet hojaBase, boolean evaluaExpresion)
			throws ExcelBuildException {

		// If the old cell is null jump to next cell
		if (oldCell == null) {
			return;
		}

		// cloneStyle(oldCell, newCell);

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
		setCellDataValue(oldCell, newCell, true, evaluaExpresion, hojaBase);

		this.columnasUsadas.add(newCell.getColumnIndex());

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
	        HSSFSheet hojaBase) throws ExcelBuildException {
		switch (oldCell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			//newCell.setCellValue(oldCell.getStringCellValue());
			cloneStyle(oldCell, newCell);
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
					precesarExpresion(oldCell, newCell, hojaBase);
				} catch (ExcelBuildException e) {
					LOGGER.info("Coleccion sin datos: " + e);
				} catch (JexlException e) {
					LOGGER.info("Celda sin datos: " + e);
				}
			} else {
				cloneStyle(oldCell, newCell);
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
	private void precesarExpresion(HSSFCell oldCell, HSSFCell newCell, HSSFSheet hojaBase)
			throws ExcelBuildException {
		String texto = getCellAsString(oldCell);
		if (ifActual == null || ifActual.isEmpty() || ifActual.get(ifActual.size() - 1)) {
			if (texto.startsWith(FOREACH)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(this.filaActualBase);
				this.filaActualBase = procesarForeach(this.body, texto, hojaBase, list);
			}
			if (texto.startsWith(TODAY)) {
				cloneStyle(oldCell, newCell);
				procesarFechaHoy(newCell);
			}
			if (texto.startsWith(CONDITION)) {
				cloneStyle(oldCell, newCell);
				procesarCondicion(texto, newCell);
			}
			if (texto.startsWith(IF)) {
				procesarIf(texto);
			}
			if (texto.startsWith(ATRIBUTO)) {
				cloneStyle(oldCell, newCell);
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
			procesarEndif(texto);
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
	 *
	 * @param cellVal
	 *            the cell val
	 */
	private void procesarEndif(String cellVal) {
		String numero = cellVal.replace(ENDIF, "");
		if(numero.isEmpty()|| (!numero.isEmpty()&& Integer.valueOf(numero)+1 == ifActual.size())){
			this.ifActual.remove(ifActual.size() - 1);	
		}
	}

	/**
	 * Procesar else.
	 */
	private void procesarElse() {
		this.ifActual.set(ifActual.size() - 1, !this.ifActual.get(ifActual.size() - 1));
	}

	/**
	 * Procesar if.
	 *
	 * @param texto
	 *            the texto
	 */
	private void procesarIf(String texto) {
		proximaFilaDestino--;

		String condicionIf = texto.replace(IF + " ", "");
		Object result = jexlEngine.createScript(condicionIf).execute(myMapContext);

		Boolean condicionResultado = (Boolean) result;

		ifActual.add(condicionResultado);
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
		ejecutarExpresion(texto, newCell);
	}

	/**
	 * Procesar condicion.
	 *
	 * @param texto
	 *            the texto
	 * @param newCell
	 *            the new cell
	 */
	private void ejecutarExpresion(String texto, HSSFCell newCell) {
		cloneStyle(oldCell, newCell);
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
	private int procesarForeach(Object body, String texto, HSSFSheet hojaBase, List<Integer> filaActualList)
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
		HSSFSheet sheet = hojaBase;
		int filaActual = filaActualBase;
		// La posicion donde escribir en el excel final.
		proximaFilaDestino--;

		for (Object itemCollection : collection) {

			filaActual = filaActualBase;
			// Donde se ubica la primer instruccion del body del foreach
			++filaActual;
			// la primer fila del template
			HSSFRow baseRow = sheet.getRow(filaActual);

			myMapContext.set(nombreColeccion, itemCollection);
			boolean existeEndForEach = false;
			do {

				// Realiza la copia de la fila del template al excel destino
				// para obtener el formato de la fila.
				copyRow(hojaBase, baseRow.getRowNum(), proximaFilaDestino);
				// Se obtiene la fila del excel destino para la escritura, si es
				// necesario.
				HSSFRow newRow = newWorkbook.getSheetAt(0).getRow(proximaFilaDestino);
				// Se cicla por columna de la fila del template que esta
				// seleccionada.
				
				if (ultimaCondicion()) {
				    procesarMergedRegions(hojaBase, newWorkbook.getSheetAt(0), filaActual, this.proximaFilaDestino);
				}
				
				for (int i = 0; i < baseRow.getLastCellNum(); i++) {
					if (StringUtils.isNotBlank(getCellAsString(baseRow.getCell(i)))) {
						HSSFCell currentCell = baseRow.getCell(i);
						String cellVal = getCellAsString(currentCell);
						oldCell = currentCell;
						HSSFCell newCell = newRow.getCell(i);
						this.newCell = newCell;
						if (isExpresion(currentCell)) {
							if (!ultimaCondicion()) {
								if (cellVal.startsWith(ENDIF)) {
									procesarEndif(cellVal);
									proximaFilaDestino--;
									proximaFilaDestino--;
								}
							} else {
								if (cellVal.startsWith(ATRIBUTO)) {
									cloneStyle(oldCell, newCell);
									String attr = cellVal.substring(2, cellVal.length() - 1);
									try {
										newCell.setCellValue(BeanUtils.getProperty(itemCollection, attr));
									} catch (Exception e) {
										LOGGER.error("Error al generar celda del excel {}.", attr, e);
										throw new ExcelBuildException(e);
									}
								} else if (cellVal.startsWith(FOREACH)) {
									List<Integer> list = new ArrayList<Integer>();
									list.add(filaActual);
									proximaFilaDestino++;
									filaActual = procesarForeach(itemCollection, cellVal, hojaBase, list);
									proximaFilaDestino--;
								} else if (cellVal.startsWith(IF)) {
									procesarIf(cellVal);
								} else if (cellVal.startsWith(ELSE) || cellVal.startsWith(ENDIF)) {
									procesarElseEndif(cellVal);
									proximaFilaDestino--;
								} else {
									ejecutarExpresion(cellVal, newCell);
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
				itemsProcesados++;
			} while (!existeEndForEach);
		} // end for
		return filaActual;
	}

	private boolean ultimaCondicion() {
	    return ifActual == null || ifActual.isEmpty() || ifActual.get(ifActual.size() - 1);
	}


	



	/**
	 * Clone style.
	 *
	 * @param oldCell
	 *            the old cell
	 * @param newCell
	 *            the new cell
	 */
	private void cloneStyle(HSSFCell oldCell, HSSFCell newCell) {
		HSSFCellStyle origStyle = oldCell.getCellStyle();

		if (this.cellStyleMap.containsKey(origStyle)) {
		    newCell.setCellStyle(this.cellStyleMap.get(origStyle));
		} else {
			HSSFCellStyle newStyle = newWorkbook.createCellStyle(); 
		    newStyle.cloneStyleFrom(origStyle);
		    this.cellStyleMap.put(origStyle, newStyle);
		    newCell.setCellStyle(newStyle);
		}
		
	}





}
