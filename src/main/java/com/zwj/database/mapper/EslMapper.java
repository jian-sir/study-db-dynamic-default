package com.zwj.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @author zhangweijian
 */
@Mapper
public interface EslMapper {

    public List<Map<String, Object>> select1(String heartTime);

    public List<Map<String, Object>> select2(String heartTime);

}
