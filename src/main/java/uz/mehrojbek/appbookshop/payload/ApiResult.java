package uz.mehrojbek.appbookshop.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private String message;
    private Boolean success;
    private T data;


    public ApiResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResult(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static<E> ApiResult<E> successResponse(String message){
        return new ApiResult<E>(message,true);
    }

    public static<E> ApiResult<E> successRespWithData(E data){
        return new ApiResult<E>(true,data);
    }
}
