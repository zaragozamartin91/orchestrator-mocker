package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;

/**
 * Constructor de {@link IResultSetBlock}
 */
public class ResultSetBuilder {
    IResultSetHeader metaData;
    IResultSetData data;
    int type;

    public static ResultSetBuilder buildNew() {
        return new ResultSetBuilder();
    }

    private ResultSetBuilder() { }

    /**
     * Establece la metadata del resultset. Los valores deben pasarse de la siguiente manera:
     * name , type, length,name , type, length,name , type, length,name , type, length,name , type, length,...
     * Cada trio de valores representa una columna.
     *
     * @param columns Definiciones de columna.
     * @return this.
     */
    public ResultSetBuilder withMetadata(Object... columns) {
        this.metaData = ResultSetHeaderStub.buildFull(columns);
        return this;
    }

    /**
     * Establece los datos del resultset. Los valores deben pasarse como multiplos de rowSize.
     * Ejemplo: si rowsize es 3 y se intentan establecer dos filas de valores, entonces el metodo debe invocarse como:
     * <code>withRsData(3,"data_1","data_2","data_3","data_4","data_5","data_6")</code>
     *
     * @param items Items elementos de la fila.
     * @return this.
     */
    public ResultSetBuilder withData(Object... items) {
        if (metaData == null) throw new IllegalStateException("Invocar a withRsMetadata primero");
        this.data = ResultSetDataStub.buildFull(metaData.getColumnsNumber(), false, items);
        return this;
    }

    /**
     * Establece el tipo del resultset.
     *
     * @param type Tipo del resultset.
     * @return this.
     */
    public ResultSetBuilder withType(int type) {
        this.type = type;
        return this;
    }

    /**
     * Termina de construir el resultset.
     *
     * @return Nuevo resultset.
     */
    public IResultSetBlock build() {
        return new ResultSetBlockStub(metaData, data, type);
    }
}
