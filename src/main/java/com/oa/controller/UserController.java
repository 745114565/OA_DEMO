package com.oa.controller;

import com.oa.entity.SysUser;
import com.oa.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("是否调用findOne");
        modelMap.addAttribute("userList",userService.findOne(id));
        return "home";
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
        System.out.println(jsonArray.toString());
        return buildTree(root);
    }

    public List<SysUser> buildTree(List<SysUser> root) {
        for(int i=0;i<root.size();i++){
            List<SysUser> children = userService.findByPid(root.get(i).getRid()); //查询某节点的子节点（获取的是list）
            buildTree(children);
            root.get(i).setChildren(children);
        }
        return root;
    }

}
