package mulThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    // 处理HTTPRequest的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>();

    // SimpleHttpServer的根路径
    static String basePath;

    static ServerSocket serverSocket;

    // 服务监听端口
    static int port = 8080;

    public static void setPort (int port){
        if (port > 0){
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath){
        if (basePath != null && new File(basePath).exists()
                && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    // 启动SimpleHttpServer
    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        setBasePath("/Users/sun/webapps");
        Socket socket = null;
        try{
            while ((socket = serverSocket.accept()) != null){
                // 接收一个客户端Socket，生成一个HttpRequestHandler，放入线程池执行
                threadPool.execute(new HttpRequestHandler(socket));
            }
        } catch (Exception e){
            System.out.println("exception...");
        }

        // 这个什么时候会执行到呢？
        serverSocket.close();
        System.out.println("close...");
    }

    public static void shutdown() throws Exception{
        threadPool.shutdown();
        serverSocket.close();
        System.out.println("shutdown...");
    }

    static class HttpRequestHandler implements Runnable{
        private Socket socket;
        public HttpRequestHandler (Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());

                System.out.println("get " + filePath);

                // 如果请求资源的后缀为jpg或者ico，则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1){
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                }
                else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null){
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception e){
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }

        // 关闭流或者Socket
        public static void close(Closeable... closeables){
            if (closeables != null){
                for (Closeable closeable : closeables){
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
