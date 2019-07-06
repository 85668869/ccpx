package com.jc.ccpx.dao.entry;

import lombok.*;

import java.util.List;

@Data
public class ApplicationInfoDO {
    //身份证号
    private String idcard;
    //姓名
    private String name;
    //性别
    private String sex;
    //文化程度
    private String degreeOfEducation;
    //工作单位
    private String workUnit;
    //工作单位地址
    private String workUnitAddress;
    //单位联系人
    private String workUnitContactName;
    //单位联系电话
    private String workUnitContactMobile;
    //通信地址
    private String address;
    //邮编
    private String zipcode;
    //联系电话
    private String mobile;
    //申请作业项目
    private String applyOperationItem;
    //申请项目代号
    private String applyOperationItemCode;
    //证件编号
    private String certificateId;
    //首次发证日期
    private String firstCredentialsData;
    //工作简历
    private List<JobResume> jobResumeList;
    //工作简历
    private String jobResumeStr;
    //头像
    private String imgHead;
    //身份证-正面
    private String imgFrontOfIdCard;
    //身份证-反面
    private String imgBackOfIdCard;
    //印章
    private String imgSeal;




    /**
     * 工作简历
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobResume{
        //开始时间
        private String startTime;
        //结束时间
        private String endTime;
        //工作单位
        private String workUnit;
    }

}
