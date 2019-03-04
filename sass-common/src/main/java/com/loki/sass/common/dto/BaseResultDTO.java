package com.loki.sass.common.dto;

import java.io.Serializable;

/**
 * created by lokizero00 on 2019-02-14
 */
public class BaseResultDTO implements Serializable {

    private static final long serialVersionUID = 4455702538105064491L;
    private boolean success = true;
    private String resultCode;
    protected String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void setErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success=false;
    }

}
