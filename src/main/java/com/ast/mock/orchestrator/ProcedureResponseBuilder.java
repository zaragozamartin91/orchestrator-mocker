package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.ProcedureResponseStub;
import com.cobiscorp.cobis.cts.domains.IResponseBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

public class ProcedureResponseBuilder {
	private ProcedureResponseStub procedureResponseStub = new ProcedureResponseStub();

	private ProcedureResponseBuilder() {}

	public static ProcedureResponseBuilder buildNew() {
		return new ProcedureResponseBuilder();
	}

	public ProcedureResponseBuilder withParam(String name, int type, int len, String value) {
		procedureResponseStub.addParam(name, type, len, value);
		return this;
	}

	public ProcedureResponseBuilder withText(String text) {
		procedureResponseStub.setText(text);
		return this;
	}

	public ProcedureResponseBuilder withResponseBlock(IResponseBlock iResponseBlock) {
		procedureResponseStub.addResponseBlock(iResponseBlock);
		return this;
	}

	public ProcedureResponseBuilder withReturnCode(int retCode) {
		procedureResponseStub.setReturnCode(retCode);
		return this;
	}

	public ProcedureResponseBuilder withMessage(int index, String message) {
		procedureResponseStub.addMessage(index, message);
		return this;
	}

	public ProcedureResponseBuilder withResultset(IResultSetBlock resultSetBlock) {
		procedureResponseStub.addResultset(resultSetBlock);
		return this;
	}

	public ProcedureResponseStub build() {
		return this.procedureResponseStub;
	}
}
