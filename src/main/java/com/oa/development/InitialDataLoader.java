package com.oa.development;

import com.oa.entity.SysRole;
import com.oa.entity.SysUser;
import com.oa.repository.SysRoleRepository;
import com.oa.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/5/24.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    //启动服务器不生成数据时请设置为true,自动生成数据请设置为false
    boolean alreadySetup = false;
    @Autowired
    SysUserRepository userRepository;
    @Autowired
    SysRoleRepository roleRepository;


    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) return;


        //Create Role
        SysRole sysRole_admin = new SysRole();
        sysRole_admin.setName("ROLE_ADMIN");
        sysRole_admin.setDescription("管理员");
        roleRepository.save(sysRole_admin);

        SysRole sysRole_user = new SysRole();
        sysRole_user.setName("ROLE_USER");
        sysRole_user.setDescription("用户");
        roleRepository.save(sysRole_user);

        SysRole sysRole_manager = new SysRole();
        sysRole_manager.setName("ROLE_MANAGER");
        sysRole_manager.setDescription("经理");
        roleRepository.save(sysRole_manager);


        //Create User
        SysUser sysUser_admin = new SysUser();
        SysRole role_admin = roleRepository.findOne(1L);
        List<SysRole> sysRoles_admin = new LinkedList<>();
        sysRoles_admin.add(role_admin);
        sysUser_admin.setUsername("root");
        sysUser_admin.setPassword("root");
        sysUser_admin.setPid("-1");
        sysUser_admin.setRid("0");
        sysUser_admin.setText("总经理");
        sysUser_admin.setRoles(sysRoles_admin);
        userRepository.save(sysUser_admin);

        SysUser sysUser_user = new SysUser();
        SysRole role_user = roleRepository.findOne(2L);
        List<SysRole> sysRoles_user = new LinkedList<>();
        sysRoles_user.add(role_user);
        sysUser_user.setUsername("dong");
        sysUser_user.setPassword("dong");
        sysUser_user.setPid("001");
        sysUser_user.setRid("001001");
        sysUser_user.setText("xx部员工");
        sysUser_user.setRoles(sysRoles_user);
        userRepository.save(sysUser_user);

        SysUser sysUser_manager = new SysUser();
        SysRole role_manager = roleRepository.findOne(3L);
        List<SysRole> sysRoles_manager = new LinkedList<>();
        sysRoles_manager.add(role_manager);
        sysUser_manager.setUsername("sang");
        sysUser_manager.setPassword("sang");
        sysUser_manager.setPid("0");
        sysUser_manager.setRid("001");
        sysUser_manager.setText("xx部经理");
        sysUser_manager.setRoles(sysRoles_manager);
        userRepository.save(sysUser_manager);


        alreadySetup = true;
    }
}
