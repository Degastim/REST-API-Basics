package com.epam.esm.validator;

import com.epam.esm.dto.param.ParamColumnName;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.dto.param.ParamType;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidURLParameterException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParamValidator {

    public void isParamValid(ParamContainer paramContainer) {
        List<String> columnList = paramContainer.getColumn();
        List<String> typeList = paramContainer.getType();
        List<String> paramList = paramContainer.getParam();
        checkListsSize(columnList, typeList, paramList);
        if (columnList == null) {
            return;
        }
        int listsLength = columnList.size();
        for (int i = 0; i < listsLength; i++) {
            String column = columnList.get(i);
            String type = typeList.get(i);
            String param = paramList.get(i);
            checkColumnName(column);
            checkType(type);
            if (ParamType.valueOf(type.toUpperCase()) == ParamType.SORT) {
                checkSortType(param);
            }
        }
    }

    private void checkColumnName(String column) {
        try {
            ParamColumnName.valueOf(column.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidURLParameterException("The column name is invalid", ExceptionCauseCode.UNKNOWN);
        }
    }

    private void checkSortType(String param) {
        try {
            SortType.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidURLParameterException("The sort param is invalid", ExceptionCauseCode.UNKNOWN);
        }
    }

    private void checkListsSize(List<String> columnList, List<String> typeList, List<String> paramList) {
        if (columnList != null) {
            if (typeList != null) {
                if (columnList.size() != typeList.size()) {
                    throw new InvalidURLParameterException("The number of columns must match the number of types", ExceptionCauseCode.UNKNOWN);
                }
            } else {
                throw new InvalidURLParameterException("The number of columns must match the number of types", ExceptionCauseCode.UNKNOWN);
            }
            if (paramList != null) {
                if (columnList.size() != paramList.size()) {
                    throw new InvalidURLParameterException("The number of columns must match the number of params", ExceptionCauseCode.UNKNOWN);
                }
            } else {
                throw new InvalidURLParameterException("The number of columns must match the number of params", ExceptionCauseCode.UNKNOWN);
            }
        } else {
            if (typeList != null) {
                throw new InvalidURLParameterException("The number of columns must match the number of types", ExceptionCauseCode.UNKNOWN);
            }
            if (paramList != null) {
                throw new InvalidURLParameterException("The number of columns must match the number of params", ExceptionCauseCode.UNKNOWN);
            }
        }
    }

    private void checkType(String type) {
        try {
            ParamType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidURLParameterException("The type is invalid", ExceptionCauseCode.UNKNOWN);
        }
    }
}


