package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.ProcedureRequestStub;
import com.cobiscorp.cobis.cts.domains.IHeaderField;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureRequestParam;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructor de requests.
 */
public class ProcedureRequestBuilder {
	private static ProcedureRequestBuilder ourInstance = new ProcedureRequestBuilder();
	private ProcedureRequestStub procedureRequest = new ProcedureRequestStub();

	public static ProcedureRequestBuilder buildNew() {
		return ourInstance;
	}

	private ProcedureRequestBuilder() { }

	public IProcedureRequest build() {
		return procedureRequest;
	}

	public ProcedureRequestBuilder addParam(String name, int ioType, int dataType, int len, String value) {
		procedureRequest.addParam(name, ioType, dataType, len, value);
		return this;
	}

	public ProcedureRequestBuilder addResultSetParam(String name, IResultSetBlock iResultSetBlock) {
		procedureRequest.addResultSetParam(name, iResultSetBlock);
		return this;
	}

	public ProcedureRequestBuilder addInputParam(String name, int type, String value) {
		procedureRequest.addInputParam(name, type, value);
		return this;
	}

	public ProcedureRequestBuilder addOutputParam(String name, int type, String value) {
		procedureRequest.addOutputParam(name, type, value);
		return this;
	}

	public ProcedureRequestBuilder setValueParam(String name, String value) {
		procedureRequest.setValueParam(name, value);
		return this;
	}

	public ProcedureRequestBuilder setSpName(String spName) {
		procedureRequest.setSpName(spName);
		return this;
	}

	public ProcedureRequestBuilder setValueFieldInHeader(String name, String value) {
		procedureRequest.setValueFieldInHeader(name, value);
		return this;
	}

	/* Builders simplificados */

	/**
	 * Establece el mapa de parametros del request.
	 *
	 * @param items Pares de elementos clave, valor donde clave es String y valor es IProcedureRequestParam
	 * @return this.
	 */
	public ProcedureRequestBuilder setProcedureRequestParamMap(Object... items) {
		if (items.length == 0 || items.length % 2 != 0) {
			throw new IllegalArgumentException("Cantidad de elementos invalida. Deben pasarse pares de valores (String,IProcedureRequestParam)");
		}

		Map<String, IProcedureRequestParam> procedureRequestParamMap = new HashMap<String, IProcedureRequestParam>();
		for (int i = 0; i < items.length; ) {
			String key = (String) items[i++];
			IProcedureRequestParam value = (IProcedureRequestParam) items[i++];

			procedureRequestParamMap.put(key, value);
		}

		procedureRequest.setProcedureRequestParamMap(procedureRequestParamMap);
		return this;
	}

	/**
	 * Establece el mapa de headers del request.
	 *
	 * @param items Pares de elementos clave, valor donde clave es String y valor es IHeaderField
	 * @return this.
	 */
	public ProcedureRequestBuilder setHeaderFieldMap(Object... items) {
		if (items.length == 0 || items.length % 2 != 0) {
			throw new IllegalArgumentException("Cantidad de elementos invalida. Deben pasarse pares de valores (String,IHeaderField)");
		}

		Map<String, IHeaderField> headerFieldMap = new HashMap<String, IHeaderField>();
		for (int i = 0; i < items.length; ) {
			String key = (String) items[i++];
			IHeaderField value = (IHeaderField) items[i++];

			headerFieldMap.put(key, value);
		}

		procedureRequest.setHeaderFieldMap(headerFieldMap);
		return this;
	}
}
