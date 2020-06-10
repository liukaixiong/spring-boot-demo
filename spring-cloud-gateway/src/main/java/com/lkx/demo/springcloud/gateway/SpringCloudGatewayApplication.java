package com.lkx.demo.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    public static final String HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS = "hello from fake /actuator/metrics/gateway.requests";

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        //@formatter:off
//        return builder.routes()
//                .route("user",r-> r.uri("lb://spring-cloud-provider").predicate())
//                .route("path_route", r -> r.path("/get")
//                        .uri("http://www.baidu.com"))
//                .route("host_route", r -> r.host("*.myhost.org")
//                        .uri("http://httpbin.org"))
//                .route("rewrite_route", r -> r.host("*.rewrite.org")
//                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)",
//                                "/${segment}"))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_route", r -> r.host("*.hystrix.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//                        .uri("http://httpbin.org"))
////                .route("limit_route", r -> r
////                        .host("*.limited.org").and().path("/anything/**")
////                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
////                        .uri("http://httpbin.org"))
//                .route("websocket_route", r -> r.path("/echo")
//                        .uri("ws://localhost:9000"))
//                .build();
//        //@formatter:on
//    }
//
//    /**
//     * 限流
//     *
//     * @return
//     */
////    @Bean
////    RedisRateLimiter redisRateLimiter() {
////        return new RedisRateLimiter(1, 2);
////    }
//
//    @Bean
//    public RouterFunction<ServerResponse> testFunRouterFunction() {
//        RouterFunction<ServerResponse> route = RouterFunctions.route(
//                RequestPredicates.path("/testfun"),
//                request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
//        return route;
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
//        RouterFunction<ServerResponse> route = RouterFunctions.route(
//                RequestPredicates.path("/actuator/metrics/gateway.requests"),
//                request -> ServerResponse.ok().body(BodyInserters
//                        .fromObject(HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS)));
//        return route;
//    }

//    @Bean
//    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
//        return http.httpBasic().and()
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers("/anything/**").authenticated()
//                .anyExchange().permitAll()
//                .and()
//                .build();
//    }
//
//    @Bean
//    public MapReactiveUserDetailsService reactiveUserDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
//        return new MapReactiveUserDetailsService(user);
//    }

}
