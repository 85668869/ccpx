package com.jc.ccpx.service.impl;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.dao.mapper.MultiQueryMapper;
import com.jc.ccpx.exception.CcpxException;
import com.jc.ccpx.service.MultiQueryService;

import java.util.*;

import com.jc.ccpx.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jc.ccpx.constant.Constant.*;

/**
 * 综合查询
 */
@Service
public class MultiQueryServiceImpl implements MultiQueryService{

    @Autowired
    MultiQueryMapper multiQueryMapper;

    /**
     * 申请表
     * @param id
     * @return
     *
     *
     *  map.put( "name", "张三");
     *  map.put( "sex", "女" );
     *  map.put( "degreeOfEducation", "高中" );
     *  map.put( "zipcode", "236400" );
     *  map.put( "idcard", "310105197012050817" );
     *  map.put( "mobile", "85641257" );
     *  map.put( "certificateId", "310105197012050817" );
     *  map.put( "firstCredentialsData", "2016-05-23" );
     *  map.put( "imgHead", "http://www.chachepeixun.com/vb/pic/201904122142016030.jpg" );
     *  map.put( "jobResumeStr", "持证期间未中断作业，且遵守特种设备法关于作业人员不间断保持知识更新的规定！" );
     *
     */
    @Override
    public Map<String, Object> getApplicationInfo(int id) throws CcpxException {
        ApplicationInfoDO applicationInfoDO = multiQueryMapper.getApplicationInfo(id);
        if (applicationInfoDO==null){
            throw new CcpxException("getApplicationInfo 数据查询不到 id："+id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put( "name", applicationInfoDO.getName());
        map.put( "sex", applicationInfoDO.getSex());
        map.put( "idcard", applicationInfoDO.getIdcard());
        map.put( "degreeOfEducation", ObjectUtils.defaultIfNull(applicationInfoDO.getDegreeOfEducation(),"高中"));
        map.put( "enrolmentArea", SHENQING.DEFAULET_ENROLMENT_AREA);
        map.put( "workUnit", COMPANY_NAME);
        map.put( "workUnitAddress", COMPANY_ADDRESS);
        map.put( "address", COMPANY_ADDRESS);
        map.put( "zipcode", ObjectUtils.defaultIfNull(applicationInfoDO.getZipcode(), COMPANY_ZIPCODE));
        map.put( "mobile", COMPANY_CONTACT_PHONE);
        map.put( "applyOperationItem", JOB_ITEMS);
        map.put( "applyOperationItemCode", JOB_CODE);
        map.put( "jobResumeStr", SHENQING.DEFAULET_JOB_RESUME);
        map.put( "imgHead", StringUtils.isBlank(applicationInfoDO.getImgHead()) ? null : IMG_PATH+applicationInfoDO.getImgHead());
        map.put( "imgFrontOfIdCard", StringUtils.isBlank(applicationInfoDO.getImgFrontOfIdCard()) ? null : IMG_PATH+applicationInfoDO.getImgFrontOfIdCard());
        map.put( "imgBackOfIdCard", StringUtils.isBlank(applicationInfoDO.getImgBackOfIdCard()) ? null : IMG_PATH+applicationInfoDO.getImgBackOfIdCard());
        map.put( "imgSeal", IMG_SEAL);
        map.put("imgGdiploma", StringUtils.isBlank(applicationInfoDO.getImgGdiploma()) ? null : IMG_PATH+applicationInfoDO.getImgGdiploma());

        Calendar calendar = Calendar.getInstance();
        map.put( "nowDate", DateFormatUtils.format(calendar.getTime(), "yyyy年MM月dd日"));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        map.put( "signDate", DateFormatUtils.format(calendar.getTime(), "yyyy年MM月dd日"));

        //体检信息
		map.put("sg", ObjectUtils.defaultIfNull(applicationInfoDO.getSg(), ""));
		map.put("tz", ObjectUtils.defaultIfNull(applicationInfoDO.getTz(), ""));
		map.put("lsz", ObjectUtils.defaultIfNull(applicationInfoDO.getLsz(), ""));
		map.put("lsy", ObjectUtils.defaultIfNull(applicationInfoDO.getLsy(), ""));
		map.put("jsz", ObjectUtils.defaultIfNull(applicationInfoDO.getJsz(), ""));
		map.put("jsy", ObjectUtils.defaultIfNull(applicationInfoDO.getJsy(), ""));
		map.put("ej", ObjectUtils.defaultIfNull(applicationInfoDO.getEj(), ""));
		map.put("bsl", ObjectUtils.defaultIfNull(applicationInfoDO.getBsl(), ""));
		map.put("xx", ObjectUtils.defaultIfNull(applicationInfoDO.getXx(), ""));
		map.put("xy", ObjectUtils.defaultIfNull(applicationInfoDO.getXy(), ""));

        return map;
    }

    @Override
    public Map<String, String> getReviewInfo(int id) throws CcpxException {
        ApplicationInfoDO applicationInfoDO = multiQueryMapper.getApplicationInfo(id);
        if (applicationInfoDO==null){
            throw new CcpxException("getReviewInfo 数据查询不到 id："+id);
        }
        Map<String, String> map = new HashMap<>();
        map.put( "name", applicationInfoDO.getName());
        map.put( "sex", applicationInfoDO.getSex());
        map.put( "degreeOfEducation", ObjectUtils.defaultIfNull(applicationInfoDO.getDegreeOfEducation(),"高中"));
        map.put( "zipcode", ObjectUtils.defaultIfNull(applicationInfoDO.getZipcode(), COMPANY_ZIPCODE));
        map.put( "idcard", applicationInfoDO.getIdcard());
        map.put( "mobile", COMPANY_CONTACT_PHONE);
        map.put( "certificateId", applicationInfoDO.getIdcard());
        map.put( "firstCredentialsData", "2013-08-23");
        map.put( "jobResumeStr", FUSHEN.DEFAULET_JOB_RESUME);
        map.put( "imgHead", StringUtils.isBlank(applicationInfoDO.getImgHead()) ? null : IMG_PATH+applicationInfoDO.getImgHead());
		map.put( "imgFrontOfIdCard", StringUtils.isBlank(applicationInfoDO.getImgFrontOfIdCard()) ? null : IMG_PATH+applicationInfoDO.getImgFrontOfIdCard());
		map.put( "imgBackOfIdCard", StringUtils.isBlank(applicationInfoDO.getImgBackOfIdCard()) ? null : IMG_PATH+applicationInfoDO.getImgBackOfIdCard());
        return map;
    }
}
