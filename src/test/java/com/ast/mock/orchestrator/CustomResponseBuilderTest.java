package com.ast.mock.orchestrator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.ast.mock.orchestrator.stub.MessageBlockStub;
import com.ast.orchestration.base.data.CustomResponse;
import com.cobiscorp.cobis.cts.domains.ICTSTypes;
import com.cobiscorp.cobis.cts.domains.IMessageBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;

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

		String outputName = "@output";
		int outputType = ICTSTypes.SQLVARCHAR;
		int outputLength = 10;
		String outputName_2 = "@output_2";
		int outputType_2 = ICTSTypes.SQLINT4;
		int outputLength_2 = 4;
		String outputValue_2 = "1234";
		/* Creo un custom response que tenga dos mensajes, dos outputs y dos resultsets */
		int exptype = 456;
		CustomResponse customResponse = CustomResponseBuilder.buildNew()
				.withMessage(number, text, type)
				.withMessage(number_2, text_2, type_2)
				
				.withOutput(outputName, outputType, outputLength, text)
				.withOutput(outputName_2, outputType_2, outputLength_2, outputValue_2)
				
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
				.withRsType(exptype)
				.addRs()

				.withReturnCode(retCode)
				.build();

		List<IMessageBlock> messages = customResponse.getMessages();
		assertEquals(2, messages.size());
		assertEquals(new MessageBlockStub(number, text, type), messages.get(0));
		assertEquals(new MessageBlockStub(number_2, text_2, type_2), messages.get(1));

		
		assertEquals(2, customResponse.getOutputList().size());
		assertEquals(outputName, customResponse.getOutputList().get(0).getName());
		assertEquals(outputType, customResponse.getOutputList().get(0).getType());
		assertEquals(outputLength, customResponse.getOutputList().get(0).getLength());
		assertEquals(text, customResponse.getOutputList().get(0).getValue());
		assertEquals(outputName_2, customResponse.getOutputList().get(1).getName());
		assertEquals(outputType_2, customResponse.getOutputList().get(1).getType());
		assertEquals(outputLength_2, customResponse.getOutputList().get(1).getLength());
		assertEquals(outputValue_2, customResponse.getOutputList().get(1).getValue());
		
		
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

		assertEquals(exptype, customResponse.getResultsetList().get(1).getType());
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