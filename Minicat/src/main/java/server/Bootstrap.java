package server;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

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
    public void start() throws Exception {
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
        /*while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            //从输入流获取请求信息
           *//* int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes);  //之后输入流请求信息为空
            System.out.println("请求信息=====>" + new String(bytes));*//*
            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());
            response.outputHtml(request.getUrl());
            socket.close();
        } */
        //加载配置文件 实例化servlet
        loadServlet();

        //定义一个线程池
        int corePoolSize=10;
        int maximumPoolSize=50;
        long keepAliveTime=100l;
        TimeUnit unit=TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory= Executors.defaultThreadFactory();
        RejectedExecutionHandler handler= new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime
        ,unit,workQueue,threadFactory,handler);

        /**
         * 完成Minicat 3.0版本
         * 需求：封可以请求动态资源(servlet)
         * 多线程 如果不用多线程 只有一个main线程 thread.sleep()之后  再发请求阻塞block
         */
        while (true) {
            Socket socket = serverSocket.accept();
            RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
           // requestProcessor.start();多线程
            threadPoolExecutor.execute(requestProcessor);

        }
    }
    private HashMap<String,HttpServlet> servletMap=new HashMap<>();
    /**
     * 加载web.xml文件 初始化servlet
     */
    private void loadServlet() {
        //加载配置文件
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("web.xml");
        try {
            Document document = new SAXReader().read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> selectNodes = rootElement.selectNodes("//servlet");
            for (int i = 0; i <selectNodes.size() ; i++) {
                Element element = selectNodes.get(i);
                //  <servlet-name>lagou</servlet-name>
                Element servletNameNode = (Element)element.selectSingleNode("servlet-name");
                String servletName = servletNameNode.getStringValue();
                //<servlet-class>server.LagouServlet</servlet-class>
                Element servletClassNode = (Element)element.selectSingleNode("servlet-class");
                String servletClass = servletClassNode.getStringValue();

                //根据servlet-name找到url-pattern
                Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName+"']");
                Element urlPatternode = (Element)servletMapping.selectSingleNode("url-pattern");
                String urlPattern = urlPatternode.getStringValue();
                servletMap.put(urlPattern, (HttpServlet) Class.forName(servletClass).newInstance());
            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
