package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello";
    }
    // @Controller면서 String을 반환하면, 반환값이 view의 논리적 이름이 된다
    // @RestController나 @ResponseBody 붙으면 뷰 리졸버를 실행하지 않기 때문에
    // view를 찾지 않고 그냥 String으로 반환할 수 있다

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }
    // controller의 경로와 view의 논리적 이름이 똑같으면 반환값이 없어도
    // 논리적 뷰의 이름으로 진행된다.
}
