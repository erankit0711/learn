package Com.First.ecommerce.util;

public class CustomResponse<T> {
    private boolean success;
    private T Data;
    private String errorMessage;
    private Integer statusCode;

    public CustomResponse() {
    }

    public CustomResponse(boolean success, T data, String errorMessage, Integer statusCode) {
        this.success = success;
        Data = data;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public static <T> CustomResponse<T> success(T data, String errorMessage, Integer statusCode){
        return new CustomResponse<>(true, data, errorMessage, statusCode);
    }
    public static <T> CustomResponse<T> failure(T data, String errorMessage, Integer statusCode){
        return new CustomResponse<>(false, data, errorMessage, statusCode);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
