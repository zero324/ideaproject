package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Minicat主类
 */
public class Bootstrap {
    /**
     * 定义socket监听端口号
     */
    private int port = 8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Minicat启动初始化一些操作
     */
    public void start() throws IOException {
        /**完成Minicat 1.0版本
         * 访问http://localhost:8080,返回固定的字符串到页面"Hello Minicat!"
         */
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("=====>>>Minicat start on portt: " + port);
       /* while (true){
            Socket socket = serverSocket.accept();
            // 有了socket，接收到请求，获取输出流
            OutputStream outputStream = socket.getOutputStream();
            String data="Hello Minicat!";
            String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
            System.out.println(responseText);
            outputStream.write(responseText.getBytes());
            socket.close();
        }*/
        /**
         * 完成Minicat 2.0版本
         * 需求：封装Request和Response对象，返回html静态资源⽂件
         */
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            //从输入流获取请求信息
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes);
            System.out.println("请求信息=====>" + new String(bytes));
            // 封装Request对象和Response对象
        /*    Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());
            response.outputHtml(request.getUrl());*/
            socket.close();
        }
    }

        /**
         * Minicat 程序启动入口
         * @param args
         */
        public static void main (String[]args){
            Bootstrap bootstrap = new Bootstrap();
            try {
                //启动Minicat
                bootstrap.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
