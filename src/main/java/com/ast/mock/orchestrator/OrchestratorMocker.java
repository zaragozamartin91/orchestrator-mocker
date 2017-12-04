package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.ConnectorData;
import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.data.SpData;
import com.ast.orchestration.base.exception.BaseException;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.commons.domains.log.ILogger;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureResponse;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Generador de mocks y stubs de orquestadores.
 * <p>
 * Para poder generar algunos de los metodos stubs del orquestador, es necesario crear un archivo
 * llamado org.mockito.plugins.MockMaker en la carpeta src/test/resources.
 */
public class OrchestratorMocker {
	Orchestrator orch;

	private OrchestratorMocker(Orchestrator orch) {
		this.orch = orch;
	}

	/**
	 * Crea un stub a partir de un orquestador normal.
	 *
	 * @param regularOrchestrator Orquestador normal a partir del cualq crear un orquestador stub.
	 * @return Orquestador stub.
	 */
	public static OrchestratorMocker stub(Orchestrator regularOrchestrator) {
		try {
			/* Hago un mock del logger y lo seteo al orquestador para evitar Nullpointer exceptions */
			Field loggerField = Orchestrator.class.getDeclaredField("logger");
			loggerField.setAccessible(true);
			ILogger loggerMock = mock(ILogger.class);
			loggerField.set(regularOrchestrator, loggerMock);
		} catch (Exception e) {
			throw new IllegalStateException("No es posible hacer un stub del campo logger");
		}

		Orchestrator spyOrch = spy(regularOrchestrator);
		return new OrchestratorMocker(spyOrch);
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#runSp(SpData)}
	 *
	 * @param spData         Dato de entrada.
	 * @param customResponse Resultado de la invocacion usando el dato de entrada.
	 * @return this.
	 * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
	 */
	public OrchestratorMocker stubRunSp(SpData spData, CustomResponse customResponse) throws BaseException {
		doReturn(customResponse).when(orch).runSp(spData);
		return this;
	}

	/**
	 * Crea varias llamadas mock de {@link Orchestrator#runSp(SpData)} en la cual no importa el dato de entrada.
	 *
	 * @param firstResponse  Primera respuesta de runSp.
	 * @param otherResponses Respuestas siguientes.
	 * @return this.
	 * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
	 */
	public OrchestratorMocker stubRunSp(CustomResponse firstResponse, CustomResponse... otherResponses) throws BaseException {
		if (otherResponses.length == 0) {
			doReturn(firstResponse).when(orch).runSp(any(SpData.class));
		} else {
			doReturn(firstResponse, otherResponses).when(orch).runSp(any(SpData.class));
		}
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#runSp(SpData)} que lanza una excepcion al recibir un spdata determinado.
	 *
	 * @param exception Excepcion a lanzar.
	 * @param spdata    Spdata input.
	 * @return this.
	 * @throws BaseException No deberia ocurrir.
	 */
	public OrchestratorMocker stubRunSpAndThrow(Class<? extends Throwable> exception, SpData spdata) throws BaseException {
		doThrow(exception).when(orch).runSp(spdata);
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#runSp(SpData)} que lanza una excepcion al recibir cualquier tipo de input.
	 *
	 * @param exception Excepcion a lanzar.
	 * @return this.
	 * @throws BaseException No deberia ocurrir.
	 */
	public OrchestratorMocker stubRunSpAndThrowWithAnyInput(Class<? extends Throwable> exception) throws BaseException {
		doThrow(exception).when(orch).runSp(any(SpData.class));
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#runConnector(ConnectorData)}
	 *
	 * @param connectorData  Dato de entrada.
	 * @param customResponse Resultado de la invocacion usando el dato de entrada.
	 * @return this.
	 * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
	 */
	public OrchestratorMocker stubRunConnector(ConnectorData connectorData, CustomResponse customResponse) throws BaseException {
		doReturn(customResponse).when(orch).runConnector(connectorData);
		return this;
	}

	/**
	 * Crea llamadas mock de {@link Orchestrator#runConnector(ConnectorData)} con cualquier dato de entrada.
	 *
	 * @param firstResponse Primera respuesta.
	 * @param nextResponses siguientes respuestas.
	 * @return this.
	 * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
	 */
	public OrchestratorMocker stubRunConnector(CustomResponse firstResponse, CustomResponse... nextResponses) throws BaseException {
		if (nextResponses.length == 0) {
			doReturn(firstResponse).when(orch).runConnector(any(ConnectorData.class));
		} else {
			doReturn(firstResponse, nextResponses).when(orch).runConnector(any(ConnectorData.class));
		}
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#initProcedureRequest(IProcedureRequest)}.
	 *
	 * @param outProcedureRequest Salida que se espera luego de invocarlo.
	 * @param nextRequests        Siguientes requests de salida esperados.
	 * @return this.
	 */
	public OrchestratorMocker stubInitProcedureRequestMulti(IProcedureRequest outProcedureRequest, IProcedureRequest... nextRequests) {
		if (nextRequests.length == 0) {
			doReturn(outProcedureRequest).when(orch).initProcedureRequest(any(IProcedureRequest.class));
		} else {
			doReturn(outProcedureRequest, nextRequests).when(orch).initProcedureRequest(any(IProcedureRequest.class));
		}
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#initProcedureRequest()}.
	 *
	 * @param outProcedureRequest
	 * @param nextRequests
	 * @return
	 */
	public OrchestratorMocker stubInitProcedureRequesNoParams(IProcedureRequest outProcedureRequest, IProcedureRequest... nextRequests) {
		if (nextRequests.length == 0) {
			doReturn(outProcedureRequest).when(orch).initProcedureRequest();
		} else {
			doReturn(outProcedureRequest, nextRequests).when(orch).initProcedureRequest();
		}
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#initProcedureRequest(IProcedureRequest)}.
	 *
	 * @param inProcedureRequest  Parametro de entrada.
	 * @param outProcedureRequest Salida que se espera luego de invocarlo.
	 * @return this.
	 */
	public OrchestratorMocker stubInitProcedureRequest(IProcedureRequest inProcedureRequest, IProcedureRequest outProcedureRequest) {
		doReturn(outProcedureRequest).when(orch).initProcedureRequest(inProcedureRequest);
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#initProcedureResponse(IProcedureRequest)}
	 *
	 * @param request  Parametro de entrada.
	 * @param response Respuesta del metodo al recibir el parametro de entrada indicado.
	 * @return this.
	 */
	public OrchestratorMocker stubInitProcedureResponse(IProcedureRequest request, IProcedureResponse response) {
		doReturn(response).when(orch).initProcedureResponse(request);
		return this;
	}

	/**
	 * Crea una llamada mock de {@link Orchestrator#initProcedureResponse(IProcedureRequest)} en la cual no interesa el parametro de entrada.
	 *
	 * @param firstResponse Primera respuesta.
	 * @param nextResponses Respuestas siguientes.
	 * @return this.
	 */
	public OrchestratorMocker stubInitProcedureResponse(IProcedureResponse firstResponse, IProcedureResponse... nextResponses) {
		if (nextResponses.length == 0) {
			doReturn(firstResponse).when(orch).initProcedureResponse(any(IProcedureRequest.class));
		} else {
			doReturn(firstResponse, nextResponses).when(orch).initProcedureResponse(any(IProcedureRequest.class));
		}
		return this;
	}

	/**
	 * Obtiene el orquestador mockeado
	 *
	 * @return orquestador mockeado
	 */
	public Orchestrator get() { return orch; }
}
