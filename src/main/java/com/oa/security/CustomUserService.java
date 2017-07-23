package com.oa.security;

import com.oa.entity.SysRole;
import com.oa.entity.SysUser;
import com.oa.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by HOZANDUNG on 17/5/10.
 */
@Service("userDetailsService")
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        user.setUpdate_time(new Date());
        userRepository.save(user);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        for (int i = 0;i<user.getRoles().size();i++) {
            SysRole role = user.getRoles().get(i);
            String name = role.getName();
            System.out.println("role:"+name);
        }
        System.out.println("s:"+s);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}
