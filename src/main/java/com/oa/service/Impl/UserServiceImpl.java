package com.oa.service.Impl;

import com.oa.entity.SysRole;
import com.oa.entity.SysUser;
import com.oa.repository.SysRoleRepository;
import com.oa.repository.SysUserRepository;
import com.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;
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
    public Iterable<SysUser> index(int page, int size) {
        Page<SysUser> users = repository.findAll(new PageRequest(page,size));
        List<SysUser> userList = new LinkedList<>();
        for(SysUser x:users){
            userList.add(x);
        }
        return userList;
    }

    @Override
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
}
