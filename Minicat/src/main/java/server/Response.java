package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 封装Response对象，需要依赖于OutputStream
 *
 * 该对象需要提供核⼼⽅法，输出html
 */
public class Response {
    private OutputStream outputStream;
    public Response() {
    }
    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    // 使⽤输出流输出指定字符串
    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }
    /**
     *
     * @param path url，随后要根据url来获取到静态资源的绝对路径，进⼀步根据绝对路径
    读取该静态资源⽂件，最终通过
     * 输出流输出
     * /-----> classes
     */
    public void outputHtml(String path) throws IOException {
        // 获取静态资源⽂件的绝对路径
        String absoluteResourcePath =
                StaticResourceUtil.getAbsolutePath(path);
        // 输⼊静态资源⽂件
        File file = new File(absoluteResourcePath);
        if(file.exists() && file.isFile()) {
            // 读取静态资源⽂件，输出静态资源
            //静态资源请求处理⼯具类
            StaticResourceUtil.outputStaticResource(new
                    FileInputStream(file),outputStream);
        }else{
            // 输出404
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }
}
