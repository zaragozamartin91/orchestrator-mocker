package com.ast.mock.orchestrator.stub;

import com.cobiscorp.cobis.cts.domains.*;
import com.cobiscorp.cobis.cts.domains.sp.*;

import java.util.*;

public class ProcedureResponseStub implements IProcedureResponse {
	private Map<String, IProcedureResponseParam> params = new HashMap<String, IProcedureResponseParam>();
	private List<IResponseBlock> resBlocks = new ArrayList<IResponseBlock>();
	private List<IMessageBlock> messages = new ArrayList<IMessageBlock>();
	private List<IErrorBlock> errors = new ArrayList<IErrorBlock>();
	private List<IResultSetBlock> resultsets = new ArrayList<IResultSetBlock>();

	private Map<String, IHeaderField> headers = new HashMap<String, IHeaderField>();

	private String text;
	private int retCode;

	@Override public IProcedureResponseParam readParam(String paramName) {
		return this.params.get(paramName);
	}

	@Override public String readValueParam(String paramName) {
		return this.readParam(paramName).getValue();
	}

	@Override public void addParam(String name, int type, int len, String value) {
		this.params.put(name, new ProcedureResponseParamStub(name, type, len, value));
	}

	@Override public void removeParam(String paramName) {
		this.params.remove(paramName);
	}

	@Override public Collection getParams() {
		return new ArrayList(params.entrySet());
	}

	@Override public void setText(String text) {
		this.text = text;
	}

	@Override public String getProcedureResponseAsString() {
		return this.toString();
	}

	@Override public int getReturnCode() {
		return this.retCode;
	}

	@Override public Collection getResponseBlocks() {
		return new ArrayList(this.resBlocks);
	}

	@Override public void addResponseBlock(IResponseBlock iResponseBlock) {
		this.resBlocks.add(iResponseBlock);
	}

	@Override public void setReturnCode(int retCode) {
		this.retCode = retCode;
	}

	@Override public void addMessage(int msgNumber, String message) {
		messages.add(new MessageBlockStub(msgNumber, message, 0));
	}

	@Override public boolean hasError() {
		return errors.size() > 0;
	}

	@Override public IResultSetBlock getResultSet(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return resultsets.get(i);
	}

	@Override public IResultSetHeader getResultSetMetaData(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return resultsets.get(i).getMetaData();
	}

	@Override public IResultSetData getResultSetData(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return resultsets.get(i).getData();
	}

	@Override public List getResultSetRows(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return resultsets.get(i).getData().getRows();
	}

	@Override public IMessageBlock getMessage(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return messages.get(i);
	}

	@Override public IErrorBlock getError(int i) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return errors.get(i);
	}

	@Override public int getResultSetListSize() {
		return resultsets.size();
	}

	@Override public int getMessageListSize() {
		return messages.size();
	}

	@Override public int getErrorListSize() {
		return errors.size();
	}

	@Override public IResultSetRow getResultSetRow(int rsidx, int rowidx) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return this.getResultSet(rsidx).getData().getRow(rowidx);
	}

	@Override public IResultSetRowColumnData getResultSetRowColumnData(int rsidx, int rowidx, int colidx) {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return this.getResultSetRow(rsidx,rowidx).getRowData(colidx);
	}

	@Override public Collection getErrors() {
		return new ArrayList(errors);
	}

	@Override public Collection getMessages() {
		return new ArrayList(messages);
	}

	@Override public Collection getResultSets() {
		return new ArrayList(resultsets);
	}

	@Override public boolean isDataMessageLoaded() {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return false;
	}

	@Override public IProcedureResponse parseMessageData() {
		throw new UnsupportedOperationException("Operacion no soportada");
		//		return null;
	}

	@Override public IHeaderField readFieldInHeader(String headerName) {
		return headers.get(headerName);
	}

	@Override public String readValueFieldInHeader(String headerName) {
		return this.readFieldInHeader(headerName).getValue();
	}

	@Override public void addFieldInHeader(String name, char type, String value) {
		HeaderFieldStub header = new HeaderFieldStub(name, type, value);
		headers.put(name, header);
	}

	@Override public void setValueFieldInHeader(String headerName, String value) {
		readFieldInHeader(headerName).setValue(value);
	}

	@Override public void removeFieldInHeader(String headerName) {
		headers.remove(headerName);
	}

	@Override public String getCTSMessageAsString() {
		return this.toString();
	}

	@Override public Collection getFields() {
		return new ArrayList(headers.values());
	}

	public void addResultset(IResultSetBlock resultSetBlock) {
		this.resultsets.add(resultSetBlock);
	}
}
