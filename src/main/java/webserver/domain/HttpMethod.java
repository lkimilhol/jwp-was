package webserver.domain;

import java.util.Arrays;

import webserver.exception.InvalidHttpMethodException;

public enum HttpMethod {
    GET("GET"),
    POST("POST");

    private final String requestMethod;

    HttpMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public static HttpMethod from(String requestMethod) {
        return Arrays.stream(values())
                .filter(httpMethod -> httpMethod.requestMethod.equals(requestMethod))
                .findAny()
                .orElseThrow(() -> new InvalidHttpMethodException("유효하지 않은 HttpMethod 입니다."));
    }
}
