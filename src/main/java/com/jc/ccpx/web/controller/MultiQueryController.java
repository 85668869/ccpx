package com.jc.ccpx.web.controller;

import com.alibaba.fastjson.JSON;
import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.dao.mapper.MultiQueryMapper;
import com.jc.ccpx.exception.CcpxException;
import com.jc.ccpx.service.MultiQueryService;
import com.jc.ccpx.util.DataConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.jc.ccpx.constant.Constant.IMG_PATH;

@Slf4j
@Controller
@RequestMapping("/multiquery")
public class MultiQueryController {



    @Autowired
    MultiQueryService multiQueryService;

    @GetMapping("/shenqing")
    public ApplicationInfoDO getApplicationInfo(Integer id) throws Exception {
        if(null == id){
            throw new CcpxException("id must not null");
        }
        return multiQueryService.getApplicationInfo(id);
    }

    @GetMapping("/fushen")
    public String getReviewInfo(Integer id, Model model) throws Exception {
        if(null == id){
            throw new CcpxException("id must not null");
        }
        ApplicationInfoDO applicationInfoDO = multiQueryService.getApplicationInfo(id);
        model.addAttribute("user", applicationInfoDO);
        return "table_fushen";
    }

    @GetMapping("/reviewtable")
    public String getReviewInfoTest(Integer id, Model model) throws Exception {
        if(null == id){
            throw new CcpxException("id must not null");
        }
        String s = "{\"idcard\":\"310105********0817\",\"name\":\"钱峰\",\"sex\":\"男\",\"degreeOfEducation\":\"暂无\",\"workUnit\":\"上海外高桥樱花旅行国际贸易有限公司\",\"workUnitAddress\":\"中国（上海）自由贸易试验区日京路35号\",\"workUnitContactName\":\"王爱申\",\"workUnitContactMobile\":\"139****0826\",\"address\":\"中国（上海）自由贸易试验区日京路35号\",\"zipcode\":\"\",\"mobile\":null,\"applyOperationItem\":\"场（厂）内专用机动车辆作业\",\"applyOperationItemCode\":\"N2\",\"jobResumeList\":[{\"startTime\":\"2015年1月\",\"endTime\":\"至今\",\"workUnit\":\"上海外高桥樱花旅行国际贸易有限公司\"}],\"imgHead\":\"http://www.chachepeixun.com/vb/pic/201904122142016030.jpg\",\"imgFrontOfIdCard\":\"http://www.chachepeixun.com/vb/pic/2019041221344894029.jpg\",\"imgBackOfIdCard\":\"http://www.chachepeixun.com/vb/pic/2019041221344839598.jpg\",\"imgSeal\":\"http://www.chachepeixun.com:20001/SignImg/GZ.png\"}";
        ApplicationInfoDO applicationInfoDO = JSON.parseObject(s, ApplicationInfoDO.class);
        applicationInfoDO.setFirstCredentialsData("2013-08-23");
        model.addAttribute("user", applicationInfoDO);
        return "table_fushen";
    }

}
