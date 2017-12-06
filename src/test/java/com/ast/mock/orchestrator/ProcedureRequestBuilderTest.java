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

	@Test
	public void addParam() throws Exception {
		int name = 1;
		int value = 1;
		IProcedureRequest procedureRequest = ProcedureRequestBuilder.buildNew()
				.addParam("name_" + (name++), 1, 2, 3, "value_" + (value++))
				.addParam("name_" + (name++), 1, 2, 3, "value_" + (value++))
				.addParam("name_" + (name++), 1, 2, 3, "value_" + (value++))
				.build();

		assertEquals("value_1", procedureRequest.readValueParam("name_1"));
		assertEquals("value_2", procedureRequest.readValueParam("name_2"));
		assertEquals("value_3", procedureRequest.readValueParam("name_3"));
	}

	@Test
	public void buildFull() throws Exception {
		int idx = 1;
		IProcedureRequest procedureRequest = ProcedureRequestBuilder.buildNew()
				.addOutputParam("name_" + idx, idx, "value_" + idx++)
				.addInputParam("name_" + idx, idx, "value_" + idx++)
				.setValueParam("name_2", "SPECIALVALUE")
				.setSpName("SOME_SP")
				.addFieldInHeader("name_" + idx, 't', "value_" + idx++)
				.setValueFieldInHeader("name_3", "HEADERVALUE")
				.build();

		assertEquals("value_1", procedureRequest.readValueParam("name_1"));
		assertEquals("SPECIALVALUE", procedureRequest.readValueParam("name_2"));
		assertEquals("SOME_SP", procedureRequest.getSpName());
		assertEquals("HEADERVALUE", procedureRequest.readValueFieldInHeader("name_3"));
		assertEquals(2, procedureRequest.getParams().size());
	}
}