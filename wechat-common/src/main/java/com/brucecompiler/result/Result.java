package com.brucecompiler.result;

import com.brucecompiler.enums.ResponseStatusEnum;
import lombok.Data;

import java.util.Map;

/**
 * A unified response data structure for API Response.
 */
@Data
public class Result {

    // The Response status code
    private Integer status;

    // The Response message
    private String message;

    // Indicates whether the operation was successful
    private Boolean success;

    // The response data payload, which can be any object, list or map
    private Object data;

    /**
     * Default constructor
     */
    public Result() {}

    /**
     * Constructor for successful responses with data
     *
     * @param data The data to be included in the response
     */
    public Result(Object data) {
        this.status = ResponseStatusEnum.SUCCESS.getStatus();
        this.message = ResponseStatusEnum.SUCCESS.getMessage();
        this.success = ResponseStatusEnum.SUCCESS.getSuccess();
        this.data = data;
    }

    /**
     * Constructor for responses with a custom status
     *
     * @param status The custom response status
     */
    public Result(ResponseStatusEnum status) {
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.success = status.getSuccess();
    }

    /**
     * Constructor for response with a custom and data
     *
     * @param status The custom response status
     * @param data The data to be included in the response
     */
    public Result(ResponseStatusEnum status, Object data) {
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.success = status.getSuccess();
        this.data = data;
    }

    /**
     * Create a successful response with data
     *
     * @param data The data to be included in the response
     * @return A new Result instance representing success
     */
    public static Result ok(Object data) {
        return new Result(data);
    }

    /**
     * Create a generic error response
     *
     * @return A new Result instance representing error
     */
    public static Result error() {
        return new Result(ResponseStatusEnum.FAILED);
    }

    /**
     * Create an error response containing a map of error details
     *
     * @param map A map containing error details
     * @return A new Result instance representing an error with additional details
     */
    public static Result errorMap(Map<Object, Object> map) {
        return new Result(ResponseStatusEnum.FAILED, map);
    }

    /**
     * Create a response for custom exception or errors
     *
     * @param status A custom response status
     * @return A new Result instance representing the exception
     */
    public static Result exception(ResponseStatusEnum status) {
        return new Result(status);
    }
}
