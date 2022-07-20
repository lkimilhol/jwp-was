package webserver.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RequestLineTest {

    @DisplayName("유효하지 않은 requestLine 생성실패")
    @CsvSource(value = {
            "GET /docs/index.html HTTP/",
            "TEST /docs/index.html HTTP/1.1",
            "/docs/index.html HTTP/1.1",
            "GET /docs/index.html FTP/1.1",
            "GET  HTTP/1.1"
    })
    @ParameterizedTest
    void createFailed(String request) {
        // given
        // when
        // then
        assertThatThrownBy(() -> RequestLine.create(request))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("GET 요청 생성")
    @Test
    void createGet() {
        // given
        String requestLine = "GET /docs/index.html HTTP/1.1";

        // when
        RequestLine actual = RequestLine.create(requestLine);

        // then
        assertThat(actual.httpMethod()).isEqualTo(HttpMethod.GET.name());
        assertThat(actual.index()).isEqualTo("/docs/index.html");
        assertThat(actual.requestParams()).isEmpty();
        assertThat(actual.protocol()).isEqualTo(Protocol.HTTP.name());
        assertThat(actual.getVersion()).isEqualTo("1.1");
    }

    @DisplayName("QueryString이 있는 GET 요청 생성")
    @Test
    void createGetWithQueryString() {
        // given
        String requestLine = "GET /user?name=test&age=20 HTTP/1.1";

        // when
        RequestLine actual = RequestLine.create(requestLine);

        // then
        assertThat(actual.httpMethod()).isEqualTo(HttpMethod.GET.name());
        assertThat(actual.index()).isEqualTo("/user");
        assertThat(actual.requestParams()).containsKey("name").containsValue("test");
        assertThat(actual.requestParams()).containsKey("age").containsValue("20");
        assertThat(actual.protocol()).isEqualTo(Protocol.HTTP.name());
        assertThat(actual.getVersion()).isEqualTo("1.1");
    }

    @DisplayName("POST 요청 생성")
    @Test
    void createPost() {
        // given
        String requestLine = "POST /docs/index.html HTTP/1.1";

        // when
        RequestLine actual = RequestLine.create(requestLine);

        // then
        assertThat(actual.httpMethod()).isEqualTo(HttpMethod.POST.name());
        assertThat(actual.index()).isEqualTo("/docs/index.html");
        assertThat(actual.requestParams()).isEmpty();
        assertThat(actual.protocol()).isEqualTo(Protocol.HTTP.name());
        assertThat(actual.getVersion()).isEqualTo("1.1");
    }

    @DisplayName("QueryString이 있는 POST 요청 생성")
    @Test
    void createPostWithQueryString() {
        // given
        String requestLine = "POST /user?name=test&age=20 HTTP/1.1";

        // when
        RequestLine actual = RequestLine.create(requestLine);

        // then
        assertThat(actual.httpMethod()).isEqualTo(HttpMethod.POST.name());
        assertThat(actual.index()).isEqualTo("/user");
        assertThat(actual.requestParams()).containsKey("name").containsValue("test");
        assertThat(actual.requestParams()).containsKey("age").containsValue("20");
        assertThat(actual.protocol()).isEqualTo(Protocol.HTTP.name());
        assertThat(actual.getVersion()).isEqualTo("1.1");
    }
}
