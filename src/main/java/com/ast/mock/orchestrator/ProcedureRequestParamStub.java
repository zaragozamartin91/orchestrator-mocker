package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.IProcedureRequestParam;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;

public class ProcedureRequestParamStub implements IProcedureRequestParam {
    String name;
    int ioType;
    int dataType;
    int len;
    String value;
    IResultSetBlock resultSetValue;

    public ProcedureRequestParamStub(String name, int ioType, int dataType, int len, String value) {
        this.name = name;
        this.ioType = ioType;
        this.dataType = dataType;
        this.len = len;
        this.value = value;
    }

    public ProcedureRequestParamStub(String name, IResultSetBlock resultSetValue) {
        this.name = name;
        this.resultSetValue = resultSetValue;
    }

    public ProcedureRequestParamStub(String name, int ioType, String value) {
        this.name = name;
        this.ioType = ioType;
        this.value = value;
    }

    private ProcedureRequestParamStub(String name, int ioType, int dataType, int len, String value, IResultSetBlock resultSetValue) {
        this.name = name;
        this.ioType = ioType;
        this.dataType = dataType;
        this.len = len;
        this.value = value;
        this.resultSetValue = resultSetValue;
    }

    public int getIOType() {
        return ioType;
    }

    public IResultSetBlock getResultSetValue() {
        return resultSetValue;
    }

    public void setResultSetValue(IResultSetBlock iResultSetBlock) {
        this.resultSetValue = iResultSetBlock;
    }

    public IProcedureRequestParam clone() {
        return new ProcedureRequestParamStub(name, ioType, dataType, len, value, resultSetValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "ProcedureRequestParamStub{" +
                "name='" + name + '\'' +
                ", ioType=" + ioType +
                ", dataType=" + dataType +
                ", len=" + len +
                ", value='" + value + '\'' +
                ", resultSetValue=" + resultSetValue +
                '}';
    }
}
