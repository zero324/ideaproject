package server;

import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

public class RequestProcessor extends Thread {
    private Socket socket;
    private HashMap<String,HttpServlet> servletMap;

    public RequestProcessor(Socket socket, HashMap<String,HttpServlet> servletMap){
        this.socket=socket;
        this.servletMap=servletMap;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            //从输入流获取请求信息
           /* int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes);  //之后输入流请求信息为空
            System.out.println("请求信息=====>" + new String(bytes));*/
            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());
            if (servletMap.get(request.getUrl()) == null) {
                //静态资源
                response.outputHtml(request.getUrl());
            } else {
                HttpServlet httpServlet = servletMap.get(request.getUrl());
                //动态资源
                httpServlet.service(request, response);
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
