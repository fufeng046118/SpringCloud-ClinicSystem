package cn.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
public class IndexController {
    @GetMapping("/index")
    public void index(HttpServletResponse response){
        try {
            System.out.println("sss");
            response.sendRedirect("http://localhost:63342/qianduan/static/asd.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
