package com.zcq.controller.portal;

import com.zcq.common.Const;
import com.zcq.common.ResponseCode;
import com.zcq.common.ServerResponse;
import com.zcq.pojo.User;
import com.zcq.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: zcq
 * @Date: 2019/4/26 16:49
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

    private static  final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;

    /**
     *
     * @param session
     * @param orderNo 订单号
     * @param request
     * @return
     */
    @RequestMapping("pay.do")
    @ResponseBody
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        //二维码存放地址
        String path = request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(orderNo,user.getId(),path);
    }

}
