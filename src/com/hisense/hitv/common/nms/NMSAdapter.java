package com.hisense.hitv.common.nms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hisense.hitv.nms.javaagent.NEAgentFacade;
import com.hisense.hitv.nms.javaagent.NEConstant;
import com.hisense.hitv.nms.javaagent.NMSSocketListener;
import com.hisense.hitv.nms.javaagent.dto.Alarm;
import com.hisense.hitv.nms.javaagent.dto.Param;
import com.hisense.hitv.utils.MyPropertyPlaceholderConfigurerUtil;

public class NMSAdapter {
    private static final Logger LOG = Logger.getLogger(NMSAdapter.class);

    private NECfg neCfg;
    
    private MyPropertyPlaceholderConfigurerUtil propertyConfigurer;
    
    

    private boolean isRunning = false;

    public void startMonitor() {
        try{
            NMSSocketListener nmsMonitor = new NMSSocketListener(neCfg.getNePort(), new CamNE());
            nmsMonitor.start();
            LOG.info("NMS 监听启动...");
            isRunning = true;
        }catch (Exception e) {
            LOG.error("NMS 监听启动失败...", e); 
            isRunning = false;
        }
        
    }

    @SuppressWarnings("unchecked")
    public void sendConfigParams() {
        if(!isRunning){
            LOG.warn("NMS 监听未启动..."); 
            return;
        }
        try{
            Properties properties = propertyConfigurer.getProperties();
            Set<Object> keySet = properties.keySet();
            List<Param> paramList = new ArrayList<Param>();
            Param param = null;
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String key = (String) it.next();             
                if(key.indexOf("[")!=0){
                    String value = (String)properties.get(key);
                    param = new Param();
                    param.setParamName(key);
                    param.setParamType("String");
                    param.setParamValue(value);
                    LOG.info("------paramName:" + param.getParamName() + ", paramType:" + param.getParamType()
                        + ", paramValue:" + param.getParamValue());
                    paramList.add(param);
                }
            }
            LOG.info("paramList size:" + paramList.size());
            NEAgentFacade.sendConfigParamsWithMaxCount(neCfg.getNeName(), neCfg.getNePort(), neCfg.getNeVersion(),
                paramList, neCfg.getAgentIP());
            LOG.info("NMS 上报参数成功...");
        }catch (Exception e) {
            LOG.error("NMS 上报参数失败...", e);
        }
        

    }

    public void sendAlarm(int alarmId, String alarmContent) {
        if(!isRunning){
            LOG.warn("NMS 监听未启动..."); 
            return;
        }
        try{
            Alarm alarm = new Alarm();
            alarm.setNeId(neCfg.getNeID());
            alarm.setAlarmId(alarmId);
            alarm.setAlarmState((short) NEConstant.NEW_ALARM); 
            alarm.setAlarmLevel((short) NEConstant.ALARM_LEVEL_CRITICAL);
            alarm.setAlarmContent(alarmContent);
            alarm.setAlarmTime(Math.round(System.currentTimeMillis() / 1000f) * 1000);
            NEAgentFacade.sendAlarm(alarm, neCfg.getAgentIP());
            LOG.info("NMS 告警成功:"+alarmId+"-"+alarmContent);
        }catch (Exception e) {
            LOG.error("NMS 告警失败...", e);
        }
    }

    

    public NECfg getNeCfg() {
        return neCfg;
    }

    public void setNeCfg(NECfg neCfg) {
        this.neCfg = neCfg;
    }

    public MyPropertyPlaceholderConfigurerUtil getPropertyConfigurer() {
        return propertyConfigurer;
    }

    public void setPropertyConfigurer(MyPropertyPlaceholderConfigurerUtil propertyConfigurer) {
        this.propertyConfigurer = propertyConfigurer;
    }
    
    
}
