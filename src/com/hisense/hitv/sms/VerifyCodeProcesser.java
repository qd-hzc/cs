package com.hisense.hitv.sms;

import com.hisense.hitv.test.ReceiveMessageForVerifyCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by FelixYin/Paul on 2015/3/4.
 */
public class VerifyCodeProcesser implements ApplicationListener<ContextRefreshedEvent> {

    private ReceiveMessageForVerifyCode receiveMessageForVerifyCode;

    public ReceiveMessageForVerifyCode getReceiveMessageForVerifyCode() {
        return receiveMessageForVerifyCode;
    }

    public void setReceiveMessageForVerifyCode(ReceiveMessageForVerifyCode receiveMessageForVerifyCode) {
        this.receiveMessageForVerifyCode = receiveMessageForVerifyCode;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        receiveMessageForVerifyCode.receiveMessage();
    }
}
