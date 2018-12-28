package com.hope.filmweb.controller.index;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangjiachen
 * @Date: 2018/10/9 16:53
 * @Description:
 */
@Controller
public class IndexStatic{

    private static final String PATH = "E:\\工作文档\\film-project\\film-web\\src\\main\\resources\\html\\";

    @Resource
    Configuration cfg;

    @RequestMapping("/static")
    public String main(Model model,HttpServletRequest request) {
        String w = "Welcome FreeMarker!";
        Map root = new HashMap(16);
        root.put("w", w);
        freeMarkerContent(root, "freemaker.ftl",request);
        model.addAttribute("w", "Welcome FreeMarker!");
        return "freemaker";
    }


    private void freeMarkerContent(Map<String, Object> root, String templateName,HttpServletRequest request) {
        try {
            String path = System.getProperty("user.dir");
            String allPath = path + "\\film-web\\src\\main\\resources\\html\\html.html";
            System.out.println("allPath   "+allPath);


            //Template temp = cfg.getTemplate("freemaker.ftl");
            Template temp = cfg.getTemplate(templateName);
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            Writer file = new FileWriter(new File(allPath));
            //Writer file = new FileWriter(new File(PATH + "html.html"));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
