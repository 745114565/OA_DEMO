package com.oa.entity;

import com.oa.excel.ExcelResources;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/5/10.
 */
@Entity
public class SysUser extends BaseModel implements UserDetails {


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String rid;

    @Column
    private String pid;

    @Column
    private String text;

    @OneToMany
    private List<SysUser> children;   //用于存储子节点

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

    public SysUser(){
    }

    @Override
    @ExcelResources(title = "用户名称",order = 1)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @ExcelResources(title = "用户密码",order = 2)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ExcelResources(title = "用户根节点",order = 3)
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @ExcelResources(title = "用户父节点",order = 4)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @ExcelResources(title = "用户职称",order = 5)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ExcelResources(title = "用户下属",order = 6)
    public List<SysUser> getChildren() {
        return children;
    }

    public void setChildren(List<SysUser> children) {
        this.children = children;
    }

    @ExcelResources(title = "用户角色",order = 7)
    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rid='" + rid + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", children=" + children +
                ", roles=" + roles +
                '}';
    }

}
