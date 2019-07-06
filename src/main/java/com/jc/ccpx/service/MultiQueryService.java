package com.jc.ccpx.service;

import com.jc.ccpx.dao.entry.ApplicationInfoDO;
import java.util.Map;

public interface MultiQueryService {

    ApplicationInfoDO getApplicationInfo(int id);

    Map<String, String> getApplicationInfoOfMap(int id);
}
