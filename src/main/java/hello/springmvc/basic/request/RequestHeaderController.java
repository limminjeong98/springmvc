package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        /**
         * MultiValueMap: 하나의 key에 여러 value를 배열로 저장할 수 있다
         * HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
         * keyA=value1&keyA=value2
         *
         * MultiValueMap<String, String> map = new LinkedMultiValueMap();
         * map.add("keyA", "value1");
         * map.add("keyA", "value2");
         *
         * List<String> values = map.get("keyA"); // [value1,value2]
         * 
         * 여기서 Headers에 Accept를 중복해서 요청하면 배열로 반환된다
         * headerMap={accept=[application/json, text/html], user-agent=[PostmanRuntime/7.28.4], postman-token=[1aa1b05b-1fa3-4945-9d7d-1db15ece6bac], host=[localhost:8080], accept-encoding=[gzip, deflate, br], connection=[keep-alive]}
         */
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale); // 언어 정보
        log.info("headerMap={}", headerMap); // 헤더 정보
        log.info("header host={}", host); // 헤더의 단일 정보 받고 싶을 때
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
