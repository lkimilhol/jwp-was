package webserver.http.response.handler.post;

import db.DataBase;
import model.User;
import utils.UserParser;
import webserver.http.request.header.RequestHeader;
import webserver.http.response.HttpResponseStatus;
import webserver.http.response.handler.ResponseHandler;
import webserver.http.response.header.ContentType;
import webserver.http.response.header.ResponseHeader;

public class UserCreatePostResponseHandler implements ResponseHandler {
    private static final String REDIRECT_INDEX_HTML = "/index.html";

    @Override
    public ResponseHeader run(RequestHeader requestHeader, String requestBody, byte[] responseBody) {
        User user = UserParser.createUser(requestBody);

        DataBase.addUser(user);

        return new ResponseHeader(requestHeader.protocolVersion(), HttpResponseStatus.FOUND)
                .addContentType(ContentType.HTML)
                .addContentLength(responseBody.length)
                .addLocation(REDIRECT_INDEX_HTML);
    }
}
