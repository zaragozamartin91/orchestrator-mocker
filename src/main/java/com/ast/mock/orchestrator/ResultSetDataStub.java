package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetRow;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetRowColumnData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSetDataStub implements IResultSetData {
    List<IResultSetRow> rows = new ArrayList<IResultSetRow>();

    public ResultSetDataStub() { }

    /**
     * Construye un objeto resultset data dinamicamente.
     * <p>
     * Ejemplo de uso para construir un resultset de dos filas con 7 columnas cada una...:
     * <code>ResultSetDataStub.buildFull(7, true,
     * 123, "uno", "dos", "tres", "cuatro", "cinco", "seis",
     * 999, "siete", "ocho", "nueve", "diez", "once", "doce");</code>
     *
     * @param rowSize             Cantidad de columnas de cada fila.
     * @param lenientResultSetRow True si acaso se debe devolver un valor aleatorio al solicitar un campo que excede la cantidad de columnas.
     * @param items               Valores de cada fila.
     * @return objeto resultset
     */
    public static ResultSetDataStub buildFull(int rowSize, boolean lenientResultSetRow, Object... items) {
        if (items.length == 0) throw new IllegalArgumentException("No se pasaron valores!");
        if (items.length % rowSize != 0) throw new IllegalArgumentException("El tamano de fila es incorrecto!");

        List<IResultSetRow> rows = new ArrayList<IResultSetRow>();

        ResultSetRowStub currentRow = new ResultSetRowStub().setLenientMissingRow(lenientResultSetRow);
        currentRow.addRowData(new ResultSetRowColumnDataStub(items[0]));

        for (int i = 1; i <= items.length; i++) {
            if (i % rowSize == 0) {
                rows.add(currentRow.copy());
                if (i == items.length) break;
                currentRow = new ResultSetRowStub().setLenientMissingRow(lenientResultSetRow);
            }
            Object item = items[i];
            currentRow.addRowData(new ResultSetRowColumnDataStub(item));
        }

        return new ResultSetDataStub(rows);
    }

    public ResultSetDataStub(IResultSetRow... rows) {
        this.rows = Arrays.asList(rows);
    }

    public ResultSetDataStub(List<IResultSetRow> rows) {
        this.rows = rows;
    }

    public int getRowsNumber() {
        return rows.size();
    }

    /**
     * Obtiene una fila del resultset.
     *
     * @param i Indice de fila (comienza en 1)
     * @return Fila
     */
    public IResultSetRow getRow(int i) {
        if (i <= 0) throw new IllegalArgumentException("El indice comienza en 1");
        return rows.get(i - 1);
    }

    public void addRow(IResultSetRow iResultSetRow) {
        rows.add(iResultSetRow);
    }

    public List getRows() {
        // TODO : DEBERIA DEVOLVER UNA COPIA?
        return rows;
    }

    public IResultSetRow[] getRowsAsArray() {
        return rows.toArray(new IResultSetRow[]{});
    }

    public ResultSetDataStub addDataRow(String... values) {
        IResultSetRow row = new ResultSetRow();
        for (String value : values) {
            if (value == null) row.getColumns().add(new ResultSetRowColumnData(true, value));
            else row.getColumns().add(new ResultSetRowColumnData(false, value));
        }

        rows.add(row);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultSetDataStub that = (ResultSetDataStub) o;

        return rows != null ? rows.equals(that.rows) : that.rows == null;
    }

    @Override
    public int hashCode() {
        return rows != null ? rows.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultSetDataStub{" +
                "rows=" + rows +
                '}';
    }
}
