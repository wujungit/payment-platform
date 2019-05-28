package com.kanghe.payment.platform.controller.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.base.PageModel;
import com.kanghe.payment.platform.entity.TransOrder;
import com.kanghe.payment.platform.service.TransOrderService;
import com.kanghe.payment.platform.util.AmountUtil;
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
 * @Description: TransOrderController
 */
@Controller
@RequestMapping("/trans_order")
@Slf4j
public class TransOrderController {

    @Autowired
    private TransOrderService transOrderService;

    @RequestMapping("/list.html")
    public String listInput(ModelMap model) {
        return "trans_order/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute TransOrder transOrder, Integer pageIndex, Integer pageSize) {
        PageModel pageModel = new PageModel();
        int count = transOrderService.getCount(transOrder);
        if (count <= 0) {
            return JSON.toJSONString(pageModel);
        }
        List<TransOrder> transOrderList = transOrderService.getList((pageIndex - 1) * pageSize, pageSize, transOrder);
        if (!CollectionUtils.isEmpty(transOrderList)) {
            JSONArray array = new JSONArray();
            for (TransOrder po : transOrderList) {
                JSONObject object = (JSONObject) JSONObject.toJSON(po);
                if (po.getCreateTime() != null) {
                    object.put("createTime", DateUtil.date2Str(po.getCreateTime()));
                }
                if (po.getAmount() != null) {
                    object.put("amount", AmountUtil.convertCent2Dollar(po.getAmount() + ""));
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
    public String viewInput(String transOrderId, ModelMap model) {
        TransOrder item = null;
        if (StringUtils.isNotBlank(transOrderId)) {
            item = transOrderService.selectTransOrder(transOrderId);
        }
        if (item == null) {
            item = new TransOrder();
            model.put("item", item);
            return "trans_order/view";
        }
        JSONObject object = (JSONObject) JSON.toJSON(item);
        if (item.getTransSuccTime() != null) {
            object.put("transSuccTime", DateUtil.date2Str(item.getTransSuccTime()));
        }
        if (item.getExpireTime() != null) {
            object.put("expireTime", DateUtil.date2Str(item.getExpireTime()));
        }
        if (item.getAmount() != null) {
            object.put("amount", AmountUtil.convertCent2Dollar(item.getAmount() + ""));
        }
        model.put("item", object);
        return "trans_order/view";
    }
}