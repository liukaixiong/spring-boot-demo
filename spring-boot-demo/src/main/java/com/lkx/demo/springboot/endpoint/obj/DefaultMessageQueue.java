package com.lkx.demo.springboot.endpoint.obj;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 16:55
 */
public class DefaultMessageQueue {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);

    public void add(String obj) {
        queue.add(obj);
    }

}
