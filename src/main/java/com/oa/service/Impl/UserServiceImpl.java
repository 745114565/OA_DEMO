package com.oa.service.Impl;

import com.oa.entity.SysRole;
import com.oa.entity.SysUser;
import com.oa.repository.SysRoleRepository;
import com.oa.repository.SysUserRepository;
import com.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/7/21.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    SysUserRepository repository;

    @Autowired
    SysRoleRepository roleRepository;

    @Override
    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public Iterable<SysUser> index(int page, int size) {
        Page<SysUser> users = repository.findAll(new PageRequest(page,size));
        List<SysUser> userList = new LinkedList<>();
        for(SysUser x:users){
            userList.add(x);
        }
        return userList;
    }

    @Override
//    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public SysUser findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void create(HttpServletRequest request) {
        //添加权限
        SysRole role = roleRepository.findOne(2L);
        List<SysRole> sysRoles_user = new LinkedList<>();
        sysRoles_user.add(role);
        //set用户属性
        SysUser user = new SysUser();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setUpdate_time(new Date());
        user.setText("xx部员工");
        user.setRoles(sysRoles_user);
        //保存
        repository.save(user);
    }


    @Override
    public void update(SysUser user) {
        repository.save(user);
    }

    @Override
    public void destroy(Long id) {
        repository.delete(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<SysUser> findByPid(String pid) {
        return repository.findByPid(pid);
    }

    @Override
    public List<SysUser> findByRid(String rid) {
        return repository.findByRid(rid);
    }

    @Cacheable(value = "rolecache",keyGenerator = "wiselyKeyGenerator")
    public SysRole findoneRole() {
        System.out.println("----------是否有调用到测试redis方法----------");
        System.out.println("----------如果刷新不可见此消息则已经是从redis中加载出数据----------");
        return roleRepository.findOne(1L);
    }

    @Cacheable(value = "rolecache2",keyGenerator = "wiselyKeyGenerator")
    public SysRole findoneRole2() {
        System.out.println("----------是否有调用到测试redis方法----------");
        System.out.println("----------如果刷新不可见此消息则已经是从redis中加载出数据----------");
        return roleRepository.findOne(2L);
    }

}
