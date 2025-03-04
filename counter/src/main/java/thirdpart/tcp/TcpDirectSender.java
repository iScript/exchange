package thirdpart.tcp;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import lombok.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Log4j2
@RequiredArgsConstructor
public class TcpDirectSender {

    @NonNull
    private String ip;

    @NonNull
    private int port;

    @NonNull
    private Vertx vertx;

    //////////////////////////////////////////////////////////////////////////////


    private volatile NetSocket socket;

    public void startup() {
        vertx.createNetClient().connect(port, ip, new ClientConnHandler());

        new Thread(() -> {
            while (true) {
                try {
                    //获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素），如有必要将在指定的等待时间内等待可用元素。
                    Buffer msgBuffer = sendCache.poll(5, TimeUnit.SECONDS);
                    if (msgBuffer != null
                            && msgBuffer.length() > 0
                            && socket != null) {
                        socket.write(msgBuffer);
                    }

                } catch (Exception e) {
                    log.error("msg send fail,continue");
                }
            }
        }).start();

    }

    //socket自己去缓存中取数据进行发送
    //链表的双端阻塞队列，线程安全
    private final BlockingQueue<Buffer> sendCache = new LinkedBlockingDeque<>();

    public boolean send(Buffer bufferMsg) {

        return sendCache.offer(bufferMsg);
    }


    private class ClientConnHandler implements Handler<AsyncResult<NetSocket>> {

        private void reconnect() {
            vertx.setTimer(1000 * 5, r -> {
                log.info("try reconnect to server to {}:{} failed", ip, port);
                  vertx.createNetClient()
                        .connect(port, ip, new ClientConnHandler());
            });
        }

        @Override
        public void handle(AsyncResult<NetSocket> result) {
            if (result.succeeded()) {
                log.info("connect success to remote {}:{}", ip, port);
                socket = result.result();

                socket.closeHandler(close -> {
                    log.info("connect to remote {} closed", socket.remoteAddress());
                    //重连
                    reconnect();
                });

                socket.exceptionHandler(ex -> {
                    log.error("error exist", ex.getCause());
                });

            } else {

            }

        }
    }


}
