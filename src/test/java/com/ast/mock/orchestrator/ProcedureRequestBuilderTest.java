package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.HeaderFieldStub;
import com.ast.mock.orchestrator.stub.ProcedureRequestParamStub;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProcedureRequestBuilderTest {
	@Test
	public void setHeaderFieldMap() throws Exception {
		int key = 1;
		int value = 1;
		IProcedureRequest procedureRequest = ProcedureRequestBuilder.buildNew()
				.setHeaderFieldMap(
						"key_" + (key++), new HeaderFieldStub("name", 't', "value_" + (value++)),
						"key_" + (key++), new HeaderFieldStub("name", 't', "value_" + (value++)),
						"key_" + (key++), new HeaderFieldStub("name", 't', "value_" + (value++))
				)
				.build();

		//System.out.println(procedureRequest.readValueFieldInHeader("key_1"));
		int fieldCount = procedureRequest.getFields().size();
		assertEquals(3, fieldCount);
		for (int i = 1; i <= fieldCount; i++) {
			assertEquals("value_" + i, procedureRequest.readValueFieldInHeader("key_" + i));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeaderFieldMapAndFailOnWrongArgsCount() throws Exception {
		int key = 1;
		int value = 1;
		ProcedureRequestBuilder.buildNew()
				.setHeaderFieldMap(
						"key_" + (key++), new HeaderFieldStub("name", 't', "value_" + (value++)),
						"key_" + (key++), new HeaderFieldStub("name", 't', "value_" + (value++)),
						"key_" + (key++)
				)
				.build();

	}

	@Test
	public void setProcedureRequestParamMap() throws Exception {
		int key = 1;
		int value = 1;
		int field = 1;
		IProcedureRequest procedureRequest = ProcedureRequestBuilder.buildNew()
				.setProcedureRequestParamMap(
						"key_" + (key++), ProcedureRequestParamStub.newInputParam("field_" + (field++), value, value, "value_" + (value++)),
						"key_" + (key++), ProcedureRequestParamStub.newOutputParam("field_" + (field++), value, value, "value_" + (value++)),
						"key_" + (key++), ProcedureRequestParamStub.newOutputParam("field_" + (field++), value, value, "value_" + (value++))
				)
				.build();

		int paramsCount = procedureRequest.getParams().size();
		assertEquals(3, paramsCount);
		for (int i = 1; i <= paramsCount; i++) {
			assertEquals("value_" + i, procedureRequest.readValueParam("key_" + i));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void setProcedureRequestParamMapAndFailOnWrongArgsCount() throws Exception {
		int key = 1;
		int value = 1;
		int field = 1;
		ProcedureRequestBuilder.buildNew()
				.setProcedureRequestParamMap(
						"key_" + (key++), ProcedureRequestParamStub.newInputParam("field_" + (field++), value, value, "value_" + (value++)),
						"key_" + (key++), ProcedureRequestParamStub.newOutputParam("field_" + (field++), value, value, "value_" + (value++)),
						ProcedureRequestParamStub.newOutputParam("field_" + (field++), value, value, "value_" + (value++))
				)
				.build();

	}
}