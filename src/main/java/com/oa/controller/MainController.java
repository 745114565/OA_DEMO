package com.oa.controller;

import com.oa.entity.SysUser;
import com.oa.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/7/21.
 */
@Controller
public class MainController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 项目启动返回的index视图
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 返回登陆页面视图
     * @return
     */
    @RequestMapping(value = "/login_page",method = RequestMethod.GET)
    public String login_page() {
        return "login_page";
    }

    /**
     * 账号密码错误返回的视图
     * @return
     */
    @RequestMapping(value = "/incorrect_page",method = RequestMethod.GET)
    public String incorrect_page() {
        return "incorrect_page";
    }

    /**
     * 返回登陆成功后跳转的目标视图
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Principal principal, ModelMap modelMap) {
        System.out.println("----------------查找当前用户----------------");
        modelMap.addAttribute("myself",userService.findByUsername(principal.getName()));
        System.out.println("----------------调用完毕----------------");
        return "home";
    }

    /**
     * 返回ztree页面
     * @return
     */
    @RequestMapping(value = "/ztree_page",method = RequestMethod.GET)
    public String ztree_page() {
        return "ztree_page";
    }

}
