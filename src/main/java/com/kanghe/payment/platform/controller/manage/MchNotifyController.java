package com.kanghe.payment.platform.controller.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.base.PageModel;
import com.kanghe.payment.platform.entity.MchNotify;
import com.kanghe.payment.platform.service.MchNotifyService;
import com.kanghe.payment.platform.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: MchNotifyController
 */
@Controller
@RequestMapping("/mch_notify")
@Slf4j
public class MchNotifyController {

    @Autowired
    private MchNotifyService mchNotifyService;

    @RequestMapping("/list.html")
    public String listInput(ModelMap model) {
        return "mch_notify/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute MchNotify mchNotify, Integer pageIndex, Integer pageSize) {
        PageModel pageModel = new PageModel();
        int count = mchNotifyService.getCount(mchNotify);
        if (count <= 0) {
            return JSON.toJSONString(pageModel);
        }
        List<MchNotify> mchNotifyList = mchNotifyService.getList((pageIndex - 1) * pageSize, pageSize, mchNotify);
        if (!CollectionUtils.isEmpty(mchNotifyList)) {
            JSONArray array = new JSONArray();
            for (MchNotify po : mchNotifyList) {
                JSONObject object = (JSONObject) JSONObject.toJSON(po);
                if (po.getCreateTime() != null) {
                    object.put("createTime", DateUtil.date2Str(po.getCreateTime()));
                }
                if (po.getLastNotifyTime() != null) {
                    object.put("lastNotifyTime", DateUtil.date2Str(po.getLastNotifyTime()));
                }
                array.add(object);
            }
            pageModel.setList(array);
        }
        pageModel.setCount(count);
        pageModel.setMsg("ok");
        pageModel.setRel(true);
        return JSON.toJSONString(pageModel);
    }

    @RequestMapping("/view.html")
    public String viewInput(String orderId, ModelMap model) {
        MchNotify item = null;
        if (StringUtils.isNotBlank(orderId)) {
            item = mchNotifyService.selectMchNotify(orderId);
        }
        if (item == null) {
            item = new MchNotify();
            model.put("item", item);
            return "mch_notify/view";
        }
        JSONObject object = (JSONObject) JSON.toJSON(item);
        if (item.getCreateTime() != null) {
            object.put("createTime", DateUtil.date2Str(item.getCreateTime()));
        }
        if (item.getUpdateTime() != null) {
            object.put("updateTime", DateUtil.date2Str(item.getUpdateTime()));
        }
        if (item.getLastNotifyTime() != null) {
            object.put("lastNotifyTime", DateUtil.date2Str(item.getLastNotifyTime()));
        }
        model.put("item", object);
        return "mch_notify/view";
    }
}