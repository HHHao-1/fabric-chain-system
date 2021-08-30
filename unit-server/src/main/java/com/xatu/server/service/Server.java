package com.xatu.server.service;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author hao
 * @create 2021/6/28 9:29 下午
 */
@Slf4j
public class Server {
  static int count = 0;
  
  public static void start() throws IOException {
    
    //创建Socket
    @SuppressWarnings("resource")
    ServerSocket ssocket = new ServerSocket(888);
    log.info("服务器：服务器已开启...");
    //等待连接的Socket，他在等生成实例，生成了便说明有连接成功了
    Socket socket;
    while (true) {
      socket = ssocket.accept();
      //每接收到一个客户端连接刚创建一个线程来处理该连接的情况
      Thread t = new Thread(new MyRunnable(socket, count));
      t.start();
    }
  }

//模仿数据库存用户的账号和密码
//  public static HashMap<String, String> accountMap = new HashMap<String, String>();
//
//  static {
//    accountMap.put("小明", "123456");
//    accountMap.put("小红", "888888");
//    accountMap.put("小强", "666666");
//    accountMap.put("王二", "987654");
//  }
}

//创建一个内部类来为每个连接处理线程情况
@Slf4j
class MyRunnable implements Runnable {
  Socket socket;
  int count;
  
  
  public MyRunnable(Socket socket, int count) {
    this.socket = socket;
    this.count = count;
  }
  
  @Override
  public void run() {
    count++;
    if (socket == null) {
      return;
    }
    InputStream ins = null;
    BufferedReader br = null;
    OutputStream os = null;
    PrintWriter pw = null;
    try {
      //连接上之后获取对方传过来的信息
      ins = socket.getInputStream();
      //将字节流转为字符
      br = new BufferedReader(new InputStreamReader(ins));
      
      StringBuffer info = new StringBuffer();
      String linedata = null;
      //将信息收信进info
      while ((linedata = br.readLine()) != null) {
        info.append(linedata);
      }
//      数据处理
//      String name = "";
//      String password = "";
//      String resultStr = "错误的用户名或密码 ";
//      //"name:小明;password:123456"
//      //将信息按约定的解析
//      String[] list = info.toString() == null ? null : info.toString().split(";");
//      for (String str : list) {
//        if (str.startsWith("name:")) {
//          name = str.replace("name:", "");
//        } else if (str.startsWith("password:")) {
//          password = str.replace("password:", "");
//        }
//      }
//      String mypassword = Server.accountMap.get(name);
//      if (mypassword != null && mypassword.equals(password)) {
//        resultStr = "登录成功!";
//      }
      //关闭接收数据
      socket.shutdownInput();
      String resultStr = "打击目标x" + count;
      System.out.println(resultStr);
      
      //将结果返回给客户端
      os = socket.getOutputStream();
      pw = new PrintWriter(os);
      pw.write(resultStr);
      System.out.println("服务器：响应内容--> 完成目标打击x" + count);
//      log.info("《服务器》：响应内容-->" + resultStr);
      //将结果刷走
      pw.flush();
      //关闭输出流
      socket.shutdownOutput();
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //关闭所在资源(与创建反序)
      try {
        pw.close();
        os.close();
        br.close();
        ins.close();
        if (null != socket) {
          socket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

