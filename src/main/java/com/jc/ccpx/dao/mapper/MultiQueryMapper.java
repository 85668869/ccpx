package com.jc.ccpx.dao.mapper;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MultiQueryMapper {

    @Select({"select i.idcard,i.name,i.sex, i.pic imgHead, i.idzfilename imgFrontOfIdCard, i.idffilename imgBackOfIdCard, " +
            "i.level degreeOfEducation, i.youbian zipcode " +
            "from information i " +
            "left JOIN register r ON i.idcard = r.idcard " +
            "where i.ID1=#{id} " +
            "order by r.createtime DESC limit 1"})
    ApplicationInfoDO getApplicationInfo(int id);
}
