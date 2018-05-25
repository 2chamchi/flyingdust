package info.flyingdust.controller.swagger;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class SwaggerUIController {


    /**
     * flyingdust Swagger-UI 페이지 요청 주소
     *
     * 기본 swagger의 api json 형태의 요청 주소는
     * http://[ip]:[port]/[ctx]/[version]/api-docs
     * ex) http://localhost:5555/flyingdust/v2/api-docs
     * -> 해당 프로젝트의 swagger api를 json 형태로 반환
     * 그렇기 때문에 swagger-ui 주소는 /[version]/api-docs 의 형태와 "다르게" 처리해야 한다.
     *
     * @param request
     * @param response
     */
    @RequestMapping("")
    public void mainView(
            HttpServletRequest request,
            HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
        String ctx = request.getContextPath();
        StringBuilder url = new StringBuilder();
        url.append(ctx);
        url.append("/").append("swagger");
        url.append("/").append("swagger-ui.html");
        try{
            response.sendRedirect(url.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 요청 주소는
     * http://[ip]:[port]/[ctx]/api/v2/api-docs 이므로
     * 기본 swagger api json 요청 주소와 다르므로 swagger-ui 주소로 사용 가능
     *
     * @param request
     * @param response
     */
    @RequestMapping("/v2/api-docs")
    public void swaggaerUI(
            HttpServletRequest request,
            HttpServletResponse response){

        response.setStatus(HttpServletResponse.SC_OK);
        String ctx = request.getContextPath();
        StringBuilder url = new StringBuilder();
        url.append(ctx);
        url.append("/").append("swagger");
        url.append("/").append("swagger-ui.html");
        try{
            response.sendRedirect(url.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
