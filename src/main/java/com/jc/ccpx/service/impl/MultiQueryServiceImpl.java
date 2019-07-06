package com.jc.ccpx.service.impl;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.dao.mapper.MultiQueryMapper;
import com.jc.ccpx.service.MultiQueryService;
import java.util.HashMap;
import java.util.Map;

import com.jc.ccpx.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.jc.ccpx.constant.Constant.*;

@Service
public class MultiQueryServiceImpl implements MultiQueryService{

    @Autowired
    MultiQueryMapper multiQueryMapper;

    /**
     *
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
    public ApplicationInfoDO getApplicationInfo(int id) {
        ApplicationInfoDO applicationInfoDO = multiQueryMapper.getApplicationInfo(id);
        applicationInfoDO.setDegreeOfEducation(ObjectUtils.defaultIfNull(applicationInfoDO.getDegreeOfEducation(),"高中"));
        applicationInfoDO.setZipcode(ObjectUtils.defaultIfNull(applicationInfoDO.getZipcode(), COMPANY_ZIPCODE));
        applicationInfoDO.setCertificateId(applicationInfoDO.getIdcard());
        applicationInfoDO.setImgHead(IMG_PATH+applicationInfoDO.getImgHead());
        applicationInfoDO.setImgFrontOfIdCard(IMG_PATH+applicationInfoDO.getImgFrontOfIdCard());
        applicationInfoDO.setImgBackOfIdCard(IMG_PATH+applicationInfoDO.getImgBackOfIdCard());
        applicationInfoDO.setImgSeal(IMG_SEAL);
        applicationInfoDO.setWorkUnit(COMPANY_NAME);
        applicationInfoDO.setWorkUnitAddress(COMPANY_ADDRESS);
        applicationInfoDO.setAddress(COMPANY_ADDRESS);
        applicationInfoDO.setWorkUnitContactName(COMPANY_CONTACT_NAME);
        applicationInfoDO.setWorkUnitContactMobile(COMPANY_CONTACT_MOBILE);
        applicationInfoDO.setApplyOperationItem("场（厂）内专用机动车辆作业");
        applicationInfoDO.setApplyOperationItemCode("N2");
        applicationInfoDO.setJobResumeList(Arrays.asList(new ApplicationInfoDO.JobResume("2015年1月","至今", "上海外高桥樱花旅行国际贸易有限公司")));
        applicationInfoDO.setFirstCredentialsData("2013-08-23");
        applicationInfoDO.setJobResumeStr(DEFAULET_JOB_RESUME);
        return applicationInfoDO;
    }

    @Override
    public Map<String, String> getApplicationInfoOfMap(int id) {
        ApplicationInfoDO applicationInfoDO = getApplicationInfo(id);
        Map<String, String> map = new HashMap<>();
        map.put( "name", applicationInfoDO.getName());
        map.put( "sex", applicationInfoDO.getSex());
        map.put( "degreeOfEducation", applicationInfoDO.getDegreeOfEducation());
        map.put( "zipcode", applicationInfoDO.getZipcode());
        map.put( "idcard", applicationInfoDO.getIdcard());
        map.put( "mobile", applicationInfoDO.getMobile());
        map.put( "certificateId", applicationInfoDO.getCertificateId());
        map.put( "firstCredentialsData", applicationInfoDO.getFirstCredentialsData());
        map.put( "jobResumeStr", applicationInfoDO.getJobResumeStr());
        map.put( "imgHead", applicationInfoDO.getImgHead());
        map.put( "imgFrontOfIdCard", applicationInfoDO.getImgFrontOfIdCard());
        map.put( "imgBackOfIdCard", applicationInfoDO.getImgBackOfIdCard());
        return map;
    }
}
