package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * HTTPEntity: HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회
     * - 요청 파라미터를 조회하는 기능관 관계 없음 (@RequestParam X, @ModelAttribute X)
     * HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * 
     * 응담에서도 HttpEntity 사용 가능
     * - 메시지 바디 정보 직접 반환 (view 조회 X), 헤더 정보 포함 가능
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * HttpEntity를 상속받은 다음 객체들도 같은 기능을 제공함
     * - RequestEntity: HttpMethod, url 정보가 추가, 요청에서 사용
     * - ResponseEntity: HTTP 상태 코드 설정 가능, 응답에서 사용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        // public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) {
        // 파라미터의 HttpEntity 대신 RequestEntity 사용 가능
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        // 리턴의 HttpEntity 대신 ResponseHttp 사용 가능
        // return new ResponseEntity<String>("ok", HttpStatus.CREATED);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody - 메시지 바디 정보를 직접 조회 (@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * @ResponseBody - 메시지 바디 정보 직접 반환 (view 조회 X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }


}
