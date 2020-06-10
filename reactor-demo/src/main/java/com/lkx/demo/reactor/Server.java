package com.lkx.demo.reactor;

public class Server {
    Selector selector = new Selector();
    private Dispatcher eventLooper = new Dispatcher(selector);
    private Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
    }

    public void start() {
        // 注册一个handler
        eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }

    public static void main(String[] args) {
        Server server = new Server(9999);
        InputSource inputSource = new InputSource("a", 12312312312L);
        Event event = new Event();
        event.setSource(inputSource);
        event.setType(EventType.ACCEPT);
//        server.selector.addEvent(event);
        server.acceptor.addNewConnection(inputSource);


        server.start();

        System.out.println("服务启动 : port 9999");
    }
}
