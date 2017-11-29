package com.ast.mock.orchestrator.stub;

import com.ast.mock.orchestrator.Mocker;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeaderColumn;
import org.junit.Assert;
import org.junit.Test;

public class ResultSetHeaderStubTest {
	@Test(expected = IllegalArgumentException.class)
	public void getColumnMetaDataAndFailOnWrongIndex() throws Exception {
		IResultSetHeader headerStub = Mocker.stub().resultSetHeader("name", 1, 12);
		headerStub.getColumnMetaData(0);
	}

	@Test
	public void getColumnMetaDataSuccessfully() throws Exception {
		IResultSetHeader headerStub = Mocker.stub().resultSetHeader("name", 1, 12);

		IResultSetHeaderColumn columnMetaData = headerStub.getColumnMetaData(1);
		Assert.assertEquals(new ResultSetHeaderColumnStub("name", 1, 12), columnMetaData);
	}
}