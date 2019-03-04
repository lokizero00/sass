package com.loki.sass.common.dto;

/**
 * created by lokizero00 on 2019-02-14
 */
public class ResultDTO<T> extends BaseResultDTO {

    private static final long serialVersionUID = -3434272908589805662L;

    private T data;

    public ResultDTO() {

    }


    public ResultDTO(String key, boolean result) {
        setResultCode(key);
        //setErrorMessage(BaseResultCode.getValueWithKey(key));
        setSuccess(result);
    }

    public ResultDTO(T data) {
        this.data = data;
    }

    public static <T> ResultDTO<T> getResult() {
        return new ResultDTO<T>();
    }

    public T getModule() {
        return data;
    }

    public void setModule(T data) {
        this.data = data;
    }

    public void setError(String key){
        setSuccess(false);
        setResultCode(key);
        //setErrorMessage(BaseResultCode.getValueWithKey(key));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultDTO{");
        sb.append("success=").append(this.isSuccess());
        sb.append("resultCode=").append(this.getResultCode());
        sb.append("errorMessage=").append(this.getErrorMessage());
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
