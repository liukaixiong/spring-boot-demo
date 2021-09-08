package com.lkx.demo.springboot.endpoint.obj;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 16:57
 */
public class MessageHelper {

    public static DefaultMessageQueue INSTANCE = new DefaultMessageQueue();

    public static DefaultMessageQueue getINSTANCE() {
        return INSTANCE;
    }
}
