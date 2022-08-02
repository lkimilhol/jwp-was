package webserver.http.request.handler.post;

import webserver.http.request.handler.RequestHandler;

public class UserCreateByBodyRequestHandler implements RequestHandler {
    private static final String REQUEST_INDEX = "/user/create";
    private static final String EMPTY_BODY = "";

    public static String requestIndex() {
        return REQUEST_INDEX;
    }

    @Override
    public byte[] execute() {
        return EMPTY_BODY.getBytes();
    }
}
