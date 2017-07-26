package com.oa.controller;

import com.oa.entity.SysUser;
import com.oa.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/7/21.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 增加用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        userService.create(request);
        return "redirect:/login_page";
    }

    /**
     * 分页查找所有用户信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index (@RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         ModelMap modelMap) {
        System.out.println("----------------是否调用index----------------");
        modelMap.addAttribute("userList",userService.index(0,10));
        return "findalluser_page";
    }

    /**
     * 查找id为id的用户信息
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    public String findOne(@PathVariable Long id,ModelMap modelMap) {
        System.out.println("是否调用findOne-"+id);
        modelMap.addAttribute("userList",userService.findOne(id));
        return "edituser_page";
    }

    /**
     * 用户更新操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update (SysUser user) {
        user.setUpdate_time(new Date());
        userService.update(user);
        return "redirect:/users/index";
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        userService.destroy(id);
        return "redirect:/users/index";
    }

    /**
     * 建树 返回json数据
     * @return
     */
    @RequestMapping(value = "/getTree.do", method = RequestMethod.POST)
    @ResponseBody
    public List<SysUser> getTree() {
        List<SysUser> root = userService.findByRid("0");  //获取根节点（获取的值存到list中）
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buildTree(root));
        System.out.println("------------toString之前的输出------------");
        System.out.println(jsonArray.toString());
        System.out.println("------------toString之后的输出------------");
        List<SysUser> tree = buildTree(root);

        return tree;
    }

    public List<SysUser> buildTree(List<SysUser> root) {
        for(int i=0;i<root.size();i++){
            List<SysUser> children = userService.findByPid(root.get(i).getRid()); //查询某节点的子节点（获取的是list）
            buildTree(children);
            root.get(i).setChildren(children);
        }
        return root;
    }

    @RequestMapping(value = "/findoneRole",method = RequestMethod.GET)
    public String findoneRole(ModelMap modelMap) {
        modelMap.addAttribute("userList",userService.findoneRole());
        return "ztree_page";
    }
}
