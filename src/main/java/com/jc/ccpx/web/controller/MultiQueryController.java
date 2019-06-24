package com.jc.ccpx.web.controller;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.dao.mapper.MultiQueryMapper;
import com.jc.ccpx.exception.CcpxException;
import com.jc.ccpx.service.MultiQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jc.ccpx.constant.Constant.IMG_PATH;

@Slf4j
@Controller
@RequestMapping("/multiquery")
public class MultiQueryController {



    @Autowired
    MultiQueryService multiQueryService;

    @GetMapping("/application")
    @ResponseBody
    public ApplicationInfoDO getApplicationInfo(Integer id) throws Exception {
        if(null == id){
            throw new CcpxException("id must not null");
        }
        return multiQueryService.getApplicationInfo(id);
    }

    @GetMapping("/review")
    @ResponseBody
    public ApplicationInfoDO getReviewInfo(Integer id) throws Exception {
        if(null == id){
            throw new CcpxException("id must not null");
        }
        return multiQueryService.getApplicationInfo(id);
    }

}
