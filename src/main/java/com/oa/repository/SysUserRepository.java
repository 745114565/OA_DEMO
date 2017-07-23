package com.oa.repository;

import com.oa.entity.SysUser;

import java.util.List;

/**
 * Created by HOZANDUNG on 17/5/8.
 */
public interface SysUserRepository extends BaseRepository<SysUser> {

    SysUser findByUsername(String username);

    public List<SysUser> findByPid(String pid);

    public List<SysUser> findByRid(String rid);

}
