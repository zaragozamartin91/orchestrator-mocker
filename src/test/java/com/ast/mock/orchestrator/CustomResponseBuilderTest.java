package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.MessageBlockStub;
import com.ast.orchestration.base.data.CustomResponse;
import com.cobiscorp.cobis.cts.domains.IMessageBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomResponseBuilderTest {
	@Test
	public void build() throws Exception {
		int col = 1;
		int val = 1;

		int number = 123;
		String text = "Some text";
		int type = 456;

		int number_2 = 987;
		String text_2 = "Other text";
		int type_2 = 852;

		int retCode = 999;

		/* Creo un custom response que tenga dos mensajes y dos resultsets */
		CustomResponse customResponse = CustomResponseBuilder.buildNew()
				.withMessage(number, text, type)
				.withMessage(number_2, text_2, type_2)

				.withResultset()
				.withRsMetadata(
						"col_" + (col++), 12, 5,
						"col_" + (col++), 12, 5,
						"col_" + (col++), 12, 5
				)
				.withRsData(
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++)
				)
				.addRs()

				.withResultset()
				.withRsMetadata(
						"col_" + (col++), 13, 6,
						"col_" + (col++), 13, 6,
						"col_" + (col++), 13, 6
				)
				.withRsData(
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++),
						"val_" + (val++), "val_" + (val++), "val_" + (val++)
				)
				.addRs()

				.withReturnCode(retCode)
				.build();

		List<IMessageBlock> messages = customResponse.getMessages();
		assertEquals(2, messages.size());
		assertEquals(new MessageBlockStub(number, text, type), messages.get(0));
		assertEquals(new MessageBlockStub(number_2, text_2, type_2), messages.get(1));

		assertEquals(retCode, customResponse.getReturnCode());

		assertEquals(2, customResponse.getResultsetList().size());
		IResultSetData data = customResponse.getResultsetList().get(0).getData();
		val = 1;
		for (int rowIdx = 1; rowIdx <= data.getRowsNumber(); rowIdx++) {
			IResultSetRow row = data.getRow(rowIdx);
			for (int colIdx = 1; colIdx <= row.getColumnsNumber(); colIdx++) {
				assertEquals("val_" + (val++), row.getRowData(colIdx).getValue());
			}
		}

		data = customResponse.getResultsetList().get(1).getData();
		val = 13;
		for (int rowIdx = 1; rowIdx <= data.getRowsNumber(); rowIdx++) {
			IResultSetRow row = data.getRow(rowIdx);
			for (int colIdx = 1; colIdx <= row.getColumnsNumber(); colIdx++) {
				assertEquals("val_" + (val++), row.getRowData(colIdx).getValue());
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void buildFailOnWrongArgTypes() throws Exception {
		CustomResponseBuilder.buildNew()
				.withResultset()
				.withRsMetadata("value1", "value2", "value3")
				.addRs()
				.build();
	}
}