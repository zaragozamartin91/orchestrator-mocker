package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.IHeaderField;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureRequestParam;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProcedureRequestStub implements IProcedureRequest {
	String spName;
	Map<String, IProcedureRequestParam> procedureRequestParamMap = new HashMap<String, IProcedureRequestParam>();
	Map<String, IHeaderField> headerFieldMap = new HashMap<String, IHeaderField>();

	public IProcedureRequestParam readParam(String paramName) {
		return procedureRequestParamMap.get(paramName);
	}

	public String readValueParam(String paramName) {
		IProcedureRequestParam param = readParam(paramName);
		return param == null ? null : param.getValue();
	}

	public void addParam(String name, int ioType, int dataType, int len, String value) {
		IProcedureRequestParam procedureRequestParam = new ProcedureRequestParamStub(name, ioType, dataType, len, value);
		procedureRequestParamMap.put(name, procedureRequestParam);
	}

	public void addResultSetParam(String name, IResultSetBlock iResultSetBlock) {
		IProcedureRequestParam procedureRequestParam = new ProcedureRequestParamStub(name, iResultSetBlock);
		procedureRequestParamMap.put(name, procedureRequestParam);
	}

	public void addInputParam(String name, int type, String value) {
		IProcedureRequestParam procedureRequestParam = new ProcedureRequestParamStub(name, type, value);
		procedureRequestParamMap.put(name, procedureRequestParam);
	}

	public void addOutputParam(String name, int type, String value) {
		IProcedureRequestParam procedureRequestParam = new ProcedureRequestParamStub(name, type, value);
		procedureRequestParamMap.put(name, procedureRequestParam);
	}

	public void setValueParam(String name, String value) {
		readParam(name).setValue(value);
	}

	public void removeParam(String name) {
		procedureRequestParamMap.remove(name);
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Collection getParams() {
		return procedureRequestParamMap.values();
	}

	public String getProcedureRequestAsString() {
		return null;
	}

	public IProcedureRequest clone() {
		ProcedureRequestStub procedureRequest = new ProcedureRequestStub();
		procedureRequest.setSpName(spName);
		procedureRequest.procedureRequestParamMap = new HashMap<String, IProcedureRequestParam>(this.procedureRequestParamMap);
		procedureRequest.headerFieldMap = new HashMap<String, IHeaderField>(this.headerFieldMap);
		return procedureRequest;
	}

	public IHeaderField readFieldInHeader(String headerFieldName) {
		return headerFieldMap.get(headerFieldName);
	}

	public String readValueFieldInHeader(String headerFieldName) {
		return readFieldInHeader(headerFieldName).getValue();
	}

	public void addFieldInHeader(String name, char type, String value) {
		headerFieldMap.put(name, new HeaderFieldStub(name, type, value));
	}

	public void setValueFieldInHeader(String name, String value) {
		readFieldInHeader(name).setValue(value);
	}

	public void removeFieldInHeader(String name) {
		headerFieldMap.remove(name);
	}

	public String getCTSMessageAsString() {
		return null;
	}

	public Collection getFields() {
		// TODO : ES CORRECTO ?
		return headerFieldMap.values();
	}

	@Override
	public String toString() {
		return "ProcedureRequestStub{" +
				"spName='" + spName + '\'' +
				", procedureRequestParamMap=" + procedureRequestParamMap +
				", headerFieldMap=" + headerFieldMap +
				'}';
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProcedureRequestStub)) {
			return false;
		}

		ProcedureRequestStub that = (ProcedureRequestStub) o;

		if (spName != null ? !spName.equals(that.spName) : that.spName != null) {
			return false;
		}
		if (procedureRequestParamMap != null ? !procedureRequestParamMap.equals(that.procedureRequestParamMap) : that.procedureRequestParamMap != null) {
			return false;
		}
		return headerFieldMap != null ? headerFieldMap.equals(that.headerFieldMap) : that.headerFieldMap == null;
	}

	@Override public int hashCode() {
		int result = spName != null ? spName.hashCode() : 0;
		result = 31 * result + (procedureRequestParamMap != null ? procedureRequestParamMap.hashCode() : 0);
		result = 31 * result + (headerFieldMap != null ? headerFieldMap.hashCode() : 0);
		return result;
	}
}
