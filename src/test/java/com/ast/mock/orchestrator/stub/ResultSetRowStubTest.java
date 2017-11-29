package com.ast.mock.orchestrator.stub;

import com.ast.mock.orchestrator.Mocker;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultSetRowStubTest {
	@Test
	public void getRowData() throws Exception {
		int val = 1;
		IResultSetRow rowStub = Mocker.stub().resultSetRow("val_" + (val++), "val_" + (val++), "val_" + (val++));

		int columnsNumber = rowStub.getColumnsNumber();
		assertEquals(3, columnsNumber);
		assertEquals("val_3", rowStub.getRowData(columnsNumber).getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getRowDataAndFailOnWrongIndex() throws Exception {
		int val = 1;
		ResultSetRowStub rowStub = ResultSetRowStub.build("val_" + (val++), "val_" + (val++), "val_" + (val++));

		rowStub.getRowData(0).getValue();
	}

}