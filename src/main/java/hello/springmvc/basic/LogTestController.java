package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(LogTestController.class); 둘다 같다
    // private final Logger log = LoggerFactory.getLogger(getClass());
    // Lombok annotation @Slf4j를 사용하면 LoggerFactory 사용 안해도 된다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";

        // System.out.println("name = " + name); 지양
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);
        return name;
    }
}

// @Controller를 사용하면 String을 반환했을 때 "ok"라는 이름의 view를 찾고 렌더링하여 반환해줌
// @RestController를 사용하면 반환 값이String일 때 "ok"라는 string 자체를 HTTP 메시지 바디에 바로 입력하여 반환된다.
