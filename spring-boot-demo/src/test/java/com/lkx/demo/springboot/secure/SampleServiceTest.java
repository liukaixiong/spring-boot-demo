//package com.lkx.demo.springboot.secure;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import com.lkx.demo.springboot.SpringCloudAdminApplication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.Assert;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SpringCloudAdminApplication.class})
//public class SampleServiceTest {
//
////    @Autowired
////    private SampleService service;
//
//    private Authentication authentication;
//
//    @Before
//    public void init() {
//        this.authentication = new UsernamePasswordAuthenticationToken("user", "123456");
//    }
//
//    @After
//    public void close() {
//        SecurityContextHolder.clearContext();
//    }
//
//    @Test(expected = AuthenticationException.class)
//    public void secure() {
//        Assert.isTrue("Hello Security".equals(this.service.secure()), "OK");
//    }
//
//    @Test
//    public void authenticated() {
//        SecurityContextHolder.getContext().setAuthentication(this.authentication);
//        Assert.isTrue("Hello Security".equals(this.service.secure()), "OK");
//    }
//
//    @Test
//    public void preauth() {
//        SecurityContextHolder.getContext().setAuthentication(this.authentication);
//    }
//
//    @Test(expected = AccessDeniedException.class)
//    public void denied() {
//        SecurityContextHolder.getContext().setAuthentication(this.authentication);
//        Assert.isTrue("Goodbye World".equals(this.service.denied()), "OK");
//    }
//
//}