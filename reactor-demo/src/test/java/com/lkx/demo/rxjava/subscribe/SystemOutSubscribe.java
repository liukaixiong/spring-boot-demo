package com.lkx.demo.rxjava.subscribe;

import io.reactivex.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统打印
 *
 * @author ： liukx
 * @time ： 2019/5/30 - 15:18
 */
public class SystemOutSubscribe implements Consumer<Long> {

    private Logger logger = LoggerFactory.getLogger(SystemOutSubscribe.class);

    @Override
    public void accept(Long aLong) throws Exception {
        logger.info("---->>>>>>>>>>>>>" + aLong);
    }
}
