package com.jc.ccpx.util;

import org.apache.commons.lang3.StringUtils;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    public static String defaultIfNull(String object, String defaultValue) {
        return StringUtils.isNotBlank(object) ? object : defaultValue;
    }
}
