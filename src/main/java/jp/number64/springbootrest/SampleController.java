package jp.number64.springbootrest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author number64tech
 */
@Controller
@EnableAutoConfiguration
public class SampleController
{
    @RequestMapping("/")
    @ResponseBody
    public String start() {
        return "This is sample.";
    }
}
