package com.alpha.interns.utility;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<ErrorDetail> errors = new ArrayList<>();

    public void addError(String fieldName, String errorMessage) {
        ErrorDetail errorDetail = new ErrorDetail(fieldName, errorMessage);
        errors.add(errorDetail);
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public class ErrorDetail {
        private String fieldName;
        private String errorMessage;

        public ErrorDetail(String fieldName, String errorMessage) {
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }

    }
}
