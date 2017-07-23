package com.oa.service;

import com.oa.entity.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by HOZANDUNG on 17/5/18.
 */
public interface IUserService {

    Iterable<SysUser> index(int page, int size);

    SysUser findOne(Long id);

    SysUser findByUsername(String username);

    void create(HttpServletRequest request);

    void update(SysUser user);

    void destroy(Long id);

    public List<SysUser> findByPid(String pid);

    public List<SysUser> findByRid(String rid);

}
