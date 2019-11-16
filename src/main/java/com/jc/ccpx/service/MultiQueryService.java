package com.jc.ccpx.service;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import com.jc.ccpx.exception.CcpxException;

import java.util.Map;

public interface MultiQueryService {

    Map<String, Object> getApplicationInfo(int id) throws CcpxException;

    Map<String, String> getReviewInfo(int id) throws CcpxException;
}
