package com.zwj.database.controller;

import com.alibaba.druid.pool.DruidDataSource;

import com.zwj.database.mapper.EslMapper;
import com.zwj.database.util.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zwj
 * @version 1.0
 * @date 2020/8/6
 */
@RestController
public class TestController {

    @Autowired
    private EslMapper eslMapper;

    @RequestMapping(value="/testclient", method=RequestMethod.GET)
    public List<Map<String, Object>> testclient() {
        if (!DynamicDataSource.dataSourcesMap.containsKey("client-db")) {
            DruidDataSource druidDataSource = new DruidDataSource();
            //新的地址
            druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
            //用户
            druidDataSource.setUsername("root");
            //密码
            druidDataSource.setPassword("root");
            DynamicDataSource.dataSourcesMap.put("client-db", druidDataSource);
        }
        //切换新增的数据源
        DynamicDataSource.setDataSource("client-db");
        List<Map<String, Object>> map = eslMapper.select1("1000000000");
        //切换默认数据源
        DynamicDataSource.clear();
        return map;
    }

    @RequestMapping(value="/testserver", method=RequestMethod.GET)
    public List<Map<String, Object>> testserver() {
        return eslMapper.select2("1000000000");
    }


}
