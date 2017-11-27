package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.CustomResponse;
import com.cobiscorp.cobis.cts.domains.IMessageBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

/**
 * Cosntructor de custom responses
 */
public class CustomResponseBuilder {
    CustomResponse customResponse = new CustomResponse();

    private CustomResponseBuilder() {}

    public static CustomResponseBuilder buildNew() { return new CustomResponseBuilder(); }

    /**
     * Agrega un nuevo mensaje.
     *
     * @param number Codigo de mensaje.
     * @param text   Texto de mensaje.
     * @param type   Tipo de mensaje.
     * @return this.
     */
    public CustomResponseBuilder withMessage(int number, String text, int type) {
        IMessageBlock message = new MessageBlockStub(number, text, type);
        customResponse.getMessages().add(message);
        return this;
    }

    /**
     * Establece el codigo de retorno del custom response.
     *
     * @param code Codigo de retorno.
     * @return this.
     */
    public CustomResponseBuilder withReturnCode(int code) {
        customResponse.setReturnCode(code);
        return this;
    }

    /**
     * Constructor interno de resultsets para el Custom response.
     */
    public class InnerResultsetBuilder {
        CustomResponseBuilder parent;
        ResultSetBuilder resultSetBuilder = ResultSetBuilder.buildNew();

        private InnerResultsetBuilder(CustomResponseBuilder parent) {
            this.parent = parent;
        }

        /**
         * Asigna el encabezado o metadata. Debe llamarse antes de {@link InnerResultsetBuilder#withRsData(Object...)}.
         *
         * @param columns Caracteristicas del encabezado agrupadas de a 3 (nombre, tipo, longitud).
         * @return this.
         */
        public InnerResultsetBuilder withRsMetadata(Object... columns) {
            resultSetBuilder.withMetadata(columns);
            return this;
        }

        /**
         * Asigna una fila al resultset interno.
         *
         * @param items Valores del resultset.
         * @return this.
         */
        public InnerResultsetBuilder withRsData(Object... items) {
            resultSetBuilder.withData(items);
            return this;
        }

        /**
         * Asigna el tipo del resultset interno.
         *
         * @param type Tipo.
         * @return this.
         */
        public InnerResultsetBuilder withRsType(int type) {
            resultSetBuilder.withType(type);
            return this;
        }

        /**
         * Agrega el resultset nuevo al custom responose en construccion.
         *
         * @return Constructor de custom response.
         */
        public CustomResponseBuilder addRs() {
            IResultSetBlock newRs = resultSetBuilder.build();
            customResponse.getResultsetList().add(newRs);
            return parent;
        }
    }

    /**
     * Comienza a construir un resultset a agregar al custom response.
     *
     * @return Constructor interno de resultset nuevo.
     */
    public InnerResultsetBuilder withResultset() { return new InnerResultsetBuilder(this); }

    /**
     * Obtiene el custom response.
     *
     * @return Nuevo custom response-
     */
    public CustomResponse build() { return customResponse; }
}