package com.epam.esm.dto.param;

import java.util.List;

/**
 * Container for parameter for searching.
 *
 * @author Yauheni Tsitou
 */
public class ParamContainer {
    private List<String> column;
    private List<String> type;
    private List<String> param;

    public ParamContainer() {
    }

    public ParamContainer(List<String> column, List<String> type, List<String> param) {
        this.column = column;
        this.type = type;
        this.param = param;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> columnList) {
        this.column = columnList;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> typeList) {
        this.type = typeList;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> paramList) {
        this.param = paramList;
    }
}
