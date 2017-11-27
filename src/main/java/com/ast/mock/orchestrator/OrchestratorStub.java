package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.ConnectorData;
import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.data.SpData;
import com.ast.orchestration.base.exception.BaseException;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.commons.configuration.IConfigurationReader;
import com.cobiscorp.cobis.commons.domains.log.ILogger;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureResponse;

import java.lang.reflect.Field;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class OrchestratorStub extends Orchestrator {
    Orchestrator orch;

    private OrchestratorStub(Orchestrator orch) {
        this.orch = orch;
    }

    /**
     * Construye un nuevo orquestador mock a partir de un orquestador regular.
     *
     * @param regularOrchestrator Oquestador normal a mockear.
     * @return Orquestador mock o espia.
     */
    public static OrchestratorStub buildNewSpy(Orchestrator regularOrchestrator) {
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
        return new OrchestratorStub(spyOrch);
    }

    /**
     * Crea una llamada mock de {@link Orchestrator#runSp(SpData)}
     *
     * @param spData         Dato de entrada.
     * @param customResponse Resultado de la invocacion usando el dato de entrada.
     * @return this.
     * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
     */
    public OrchestratorStub stubRunSp(SpData spData, CustomResponse customResponse) throws BaseException {
        doReturn(customResponse).when(orch).runSp(spData);
        return this;
    }

    /**
     * Crea varias llamadas mock de {@link Orchestrator#runSp(SpData)} en la cual no importa el dato de entrada.
     *
     * @param firstResponse  Primera respuesta de runSp.
     * @param otherResponses Respuestas siguientes.
     * @return this.
     * @throws BaseException
     */
    public OrchestratorStub stubRunSp(CustomResponse firstResponse, CustomResponse... otherResponses) throws BaseException {
        if (otherResponses.length == 0) doReturn(firstResponse).when(orch).runSp(any(SpData.class));
        else doReturn(firstResponse, otherResponses).when(orch).runSp(any(SpData.class));
        return this;
    }

    @Override
    public CustomResponse runSp(SpData spData) throws BaseException {return orch.runSp(spData);}

    /**
     * Crea una llamada mock de {@link Orchestrator#runConnector(ConnectorData)}
     *
     * @param connectorData  Dato de entrada.
     * @param customResponse Resultado de la invocacion usando el dato de entrada.
     * @return this.
     * @throws BaseException No deberia ocurrir dado que la misma se lanzaria en un escenario hipotetico de llamada a runSp que no ocurre.
     */
    public OrchestratorStub stubRunConnector(ConnectorData connectorData, CustomResponse customResponse) throws BaseException {
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
    public OrchestratorStub stubRunConnector(CustomResponse firstResponse, CustomResponse... nextResponses) throws BaseException {
        if (nextResponses.length == 0) doReturn(firstResponse).when(orch).runConnector(any(ConnectorData.class));
        else doReturn(firstResponse, nextResponses).when(orch).runConnector(any(ConnectorData.class));
        return this;
    }

    @Override
    public CustomResponse runConnector(ConnectorData connectorData) throws BaseException {return orch.runConnector(connectorData);}

    @Override
    public IProcedureResponse executeJavaOrchestration(IProcedureRequest anOriginalRequest, Map<String, Object> dataBag) {return orch.executeJavaOrchestration(anOriginalRequest, dataBag);}

    @Override
    public IProcedureResponse buildResponse(IProcedureRequest request, CustomResponse customResponse) {return orch.buildResponse(request, customResponse);}

    @Override
    public IProcedureRequest buildRequest(IProcedureRequest request) throws BaseException {return orch.buildRequest(request);}

    @Override
    public IProcedureResponse processResponse(IProcedureRequest arg0, Map<String, Object> arg1) {return orch.processResponse(arg0, arg1);}

    @Override
    public void loadConfiguration(IConfigurationReader arg0) {orch.loadConfiguration(arg0);}

    public IProcedureResponse postRun(IProcedureResponse iProcedureResponse) {return orch.postRun(iProcedureResponse);}

    public IProcedureRequest preRun(IProcedureRequest iProcedureRequest) {return orch.preRun(iProcedureRequest);}

    public CustomResponse run(IProcedureRequest iProcedureRequest, Map<String, Object> map) throws Exception {return orch.run(iProcedureRequest, map);}
}
