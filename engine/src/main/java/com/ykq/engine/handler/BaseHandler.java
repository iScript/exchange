package com.ykq.engine.handler;

import com.ykq.engine.bean.command.RbCmd;
import com.lmax.disruptor.EventHandler;

public abstract class BaseHandler implements EventHandler<RbCmd> {
}
