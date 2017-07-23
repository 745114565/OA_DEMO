package com.example.demo;

import com.oa.DemoApplication;
import com.oa.entity.SysUser;
import com.oa.excel.ExcelUtil;

import com.oa.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class ExportExcelTest {

    @Autowired
    SysUserRepository repository;

    @Test
    public void test() throws Exception {
        System.out.println("这里是ExportExcelTest的test方法,ExcelUtil之前");
        List<SysUser> list = new ArrayList<SysUser>();
        Page<SysUser> users = repository.findAll(new PageRequest(0,10));
        for(SysUser x:users){
            list.add(x);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "用户信息表");
        map.put("total", list.size()+" 条");
        map.put("date", getDate());

        ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "web-info-template.xls", new FileOutputStream("/Users/ho/Desktop/excel_test/out.xls"),
                list, SysUser.class, true);
        System.out.println("这里是ExportExcelTest的test方法,ExcelUtil之后");
    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date());
    }
}