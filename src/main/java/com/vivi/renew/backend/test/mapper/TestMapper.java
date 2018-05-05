package com.vivi.renew.backend.test.mapper;

import com.vivi.renew.backend.test.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="testMapper")
public interface TestMapper {
    @Select("SELECT * FROM test where userID = #{userID}")
    Test findByUserId(@Param("userID") int userID);

}