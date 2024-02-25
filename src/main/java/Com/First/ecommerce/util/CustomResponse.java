package Com.First.ecommerce.util;

public class CustomResponse<T> {
    private boolean success;
    private T Data;
    private String errorMessage;

    public CustomResponse() {
    }

    public CustomResponse(boolean success, T data, String errorMessage) {
        this.success = success;
        Data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> CustomResponse<T> success(T data, String errorMessage){
        return new CustomResponse<>(true, data, errorMessage);
    }
    public static <T> CustomResponse<T> failure(T data, String errorMessage){
        return new CustomResponse<>(false, data, errorMessage);
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

}
