package com.hisense.hitv.common.nms;

import java.util.List;

import org.apache.log4j.Logger;

import com.hisense.hitv.nms.javaagent.NE;
import com.hisense.hitv.nms.javaagent.dto.Param;

public class CamNE implements NE {
    private static final Logger LOG = Logger.getLogger(CamNE.class);
    @Override
    public void getConfig() {
        LOG.info("----CamNE getConfig()");
    }

    @Override
    public void setConfig(List<Param> arg0) {
        LOG.info("----CamNE setConfig():"+arg0);
    }

}
