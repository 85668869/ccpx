/**
 * Created by jingchun.zhang on 2019/5/15.
 */
package com.jc.ccpx.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/5/15
 */
@Mapper
@Repository
public interface HelloMapper {

    @Select({"select name from  user where id=#{id}"})
    String queryUser(int id);

}
