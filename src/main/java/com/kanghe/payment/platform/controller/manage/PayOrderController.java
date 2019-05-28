package com.kanghe.payment.platform.controller.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.base.PageModel;
import com.kanghe.payment.platform.entity.PayOrder;
import com.kanghe.payment.platform.service.PayOrderService;
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

import java.util.Date;
import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: PayOrderController
 */
@Controller
@RequestMapping("/pay_order")
@Slf4j
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping("/list.html")
    public String listInput(ModelMap model) {
        return "pay_order/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(@ModelAttribute PayOrder payOrder, Integer pageIndex, Integer pageSize) {
        PageModel pageModel = new PageModel();
        int count = payOrderService.getCount(payOrder);
        if (count <= 0) {
            return JSON.toJSONString(pageModel);
        }
        List<PayOrder> payOrderList = payOrderService.getList((pageIndex - 1) * pageSize, pageSize, payOrder);
        if (!CollectionUtils.isEmpty(payOrderList)) {
            JSONArray array = new JSONArray();
            for (PayOrder po : payOrderList) {
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
    public String viewInput(String payOrderId, ModelMap model) {
        PayOrder item = null;
        if (StringUtils.isNotBlank(payOrderId)) {
            item = payOrderService.selectPayOrder(payOrderId);
        }
        if (item == null) {
            item = new PayOrder();
            model.put("item", item);
            return "pay_order/view";
        }
        JSONObject object = (JSONObject) JSON.toJSON(item);
        if (item.getPaySuccTime() != null) {
            object.put("paySuccTime", DateUtil.date2Str(new Date(item.getPaySuccTime())));
        }
        if (item.getLastNotifyTime() != null) {
            object.put("lastNotifyTime", DateUtil.date2Str(new Date(item.getLastNotifyTime())));
        }
        if (item.getExpireTime() != null) {
            object.put("expireTime", DateUtil.date2Str(new Date(item.getExpireTime())));
        }
        if (item.getAmount() != null) {
            object.put("amount", AmountUtil.convertCent2Dollar(item.getAmount() + ""));
        }
        model.put("item", object);
        return "pay_order/view";
    }
}