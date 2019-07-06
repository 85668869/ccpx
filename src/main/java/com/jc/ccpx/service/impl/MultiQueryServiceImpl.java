package com.jc.ccpx.service.impl;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.dao.mapper.MultiQueryMapper;
import com.jc.ccpx.service.MultiQueryService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.jc.ccpx.constant.Constant.*;

@Service
public class MultiQueryServiceImpl implements MultiQueryService{

    @Autowired
    MultiQueryMapper multiQueryMapper;

    @Override
    public ApplicationInfoDO getApplicationInfo(int id) {
        ApplicationInfoDO applicationInfoDO = multiQueryMapper.getApplicationInfo(id);
        applicationInfoDO.setDegreeOfEducation(ObjectUtils.defaultIfNull(applicationInfoDO.getDegreeOfEducation(),"高中"));
        applicationInfoDO.setZipcode(ObjectUtils.defaultIfNull(applicationInfoDO.getZipcode(), COMPANY_ZIPCODE));
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
        return applicationInfoDO;
    }
}
