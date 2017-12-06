package com.ast.mock.orchestrator.stub;

import com.cobiscorp.cobis.cts.domains.IProcedureResponseParam;

public class ProcedureResponseParamStub implements IProcedureResponseParam {
	private String name;
	private String value;
	private int type;
	private int len;

	public ProcedureResponseParamStub(String name, int type, int len, String value) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.len = len;
	}

	@Override public String getName() {
		return this.name;
	}

	@Override public String getValue() {
		return this.value;
	}

	@Override public int getDataType() {
		return this.type;
	}

	@Override public int getLen() {
		return this.len;
	}

	@Override public void setValue(String s) {
		this.value = s;
	}
}
