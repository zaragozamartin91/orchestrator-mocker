package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.ResultSetHeaderColumnStub;
import com.cobiscorp.cobis.cts.domains.sp.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultSetBuilderTest {
	@Test
	public void build() throws Exception {
		int col = 1;
		int val = 1;

		/* Construyo un resultset que tenga 3 columnas y 4 filas */
		IResultSetBlock resultSetBlock = ResultSetBuilder.buildNew()
				.withMetadata(
						"col_" + (col++), 12, 5,
						"col_" + (col++), 12, 5,
						"col_" + (col++), 12, 5
				)
				.withData(
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++)
				)
				.withType(654)
				.build();

		IResultSetData data = resultSetBlock.getData();
		IResultSetHeader metaData = resultSetBlock.getMetaData();

		assertEquals(654, resultSetBlock.getType());
		assertEquals(3, metaData.getColumnsNumber());
		assertEquals(4, data.getRowsNumber());

		val = 1;
		for (int rowIdx = 1; rowIdx <= data.getRowsNumber(); rowIdx++) {
			IResultSetRow row = data.getRow(rowIdx);
			for (int colIdx = 1; colIdx <= row.getColumnsNumber(); colIdx++) {
				assertEquals("val_" + (val++), row.getRowData(colIdx).getValue());
			}
		}

		col = 1;
		for (int colIdx = 1; colIdx <= metaData.getColumnsNumber(); colIdx++) {
			IResultSetHeaderColumn column = metaData.getColumnMetaData(colIdx);
			assertEquals(new ResultSetHeaderColumnStub("col_" + (col++), 12, 5), column);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuildAndFailForWrongMetadataArgumentsType() {
		ResultSetBuilder.buildNew()
				.withMetadata(
						"col_1", "12", 5
				)
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuildAndFailForWrongMetadataArgumentsCount() {
		ResultSetBuilder.buildNew()
				.withMetadata(
						"col_1", 12, 5, 6
				)
				.build();
	}

}