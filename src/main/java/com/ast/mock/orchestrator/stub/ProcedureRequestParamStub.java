package com.ast.mock.orchestrator.stub;

import com.cobiscorp.cobis.cts.domains.IProcedureRequestParam;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

public class ProcedureRequestParamStub implements IProcedureRequestParam {
	String name;
	int ioType; // 0 = entrada ; 1 = salida
	int dataType;
	int len;
	String value;
	IResultSetBlock resultSetValue;

	/* Valores posibles de ioType */
	static final int OUT = 1;
	static final int IN = 0;

	private ProcedureRequestParamStub(String name, IResultSetBlock resultSetValue) {
		this.name = name;
		this.resultSetValue = resultSetValue;
		this.ioType = OUT;
	}

	/**
	 * Nuevo parametro de tipo resultset.
	 * @param name Nombre
	 * @param resultSetValue Valor
	 * @return Nuevo parametro resultset.
	 */
	public static ProcedureRequestParamStub newResultsetParam(String name, IResultSetBlock resultSetValue) {
		ProcedureRequestParamStub procedureRequestParamStub = new ProcedureRequestParamStub(name, resultSetValue);
		return procedureRequestParamStub;
	}

	private ProcedureRequestParamStub(String name, int ioType, int dataType, int len, String value, IResultSetBlock resultSetValue) {
		this.name = name;
		this.ioType = ioType;
		this.dataType = dataType;
		this.len = len;
		this.value = value;
		this.resultSetValue = resultSetValue;
	}

	/**
	 * Crea un nuevo parametro de entrada.
	 * @param name Nombre
	 * @param dataType Tipo de dato
	 * @param len Longitud del valor
	 * @param value Valor del dato
	 * @return Nuevo parametro de entrada.
	 */
	public static ProcedureRequestParamStub newInputParam(String name, int dataType, int len, String value) {
		return new ProcedureRequestParamStub(name, IN, dataType, len, value, null);
	}

	/**
	 * Crea un nuevo parametro de salida.
	 * @param name Nombre
	 * @param dataType Tipo de dato
	 * @param len Longitud del valor
	 * @param value Valor del dato
	 * @return Nuevo parametro de salida.
	 */
	public static ProcedureRequestParamStub newOutputParam(String name, int dataType, int len, String value) {
		return new ProcedureRequestParamStub(name, OUT, dataType, len, value, null);
	}

	public int getIOType() {
		return ioType;
	}

	public IResultSetBlock getResultSetValue() {
		return resultSetValue;
	}

	public void setResultSetValue(IResultSetBlock iResultSetBlock) {
		this.resultSetValue = iResultSetBlock;
	}

	public IProcedureRequestParam clone() {
		return new ProcedureRequestParamStub(name, ioType, dataType, len, value, resultSetValue);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ProcedureRequestParamStub that = (ProcedureRequestParamStub) o;

		if (ioType != that.ioType) {
			return false;
		}
		if (dataType != that.dataType) {
			return false;
		}
		if (len != that.len) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
			return false;
		}
		if (value != null ? !value.equals(that.value) : that.value != null) {
			return false;
		}
		return resultSetValue != null ? resultSetValue.equals(that.resultSetValue) : that.resultSetValue == null;
	}

	@Override public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + ioType;
		result = 31 * result + dataType;
		result = 31 * result + len;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (resultSetValue != null ? resultSetValue.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ProcedureRequestParamStub{" +
				"name='" + name + '\'' +
				", ioType=" + ioType +
				", dataType=" + dataType +
				", len=" + len +
				", value='" + value + '\'' +
				", resultSetValue=" + resultSetValue +
				'}';
	}
}
