package com.spring.boot.plugin.test.service;

import com.spring.api.ILogService;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/15 - 11:46
 */
public class ConsoleLogService implements ILogService {
    @Override
    public void println(String text) {
        System.out.println(getClass().getSimpleName() + " : " + text);
    }
}
