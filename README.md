# BookingManager

## 代码目标
  使用纯Java语言实现一个酒店房间预定系统，包含检索可用房间、记录已预定房间、预定房间、根据姓名查找可用房间的功能
  
  
## 代码说明
  1.JDK1.8版本开发编译，未使用其他第三方代码
  2.数据存储没有使用数据库，使用Java集合在内存中实现
  3.未使用框架或其他jar包，所有功能为本地实现
  
## 其他说明
  1.由于要求是微服务API，具体实现规则没好意思问，所以我个人理解是从协议层开始一步步实现，没有使用Web容器+Servlet程序，程序流程大致如下：
    TCP socket -> 解析成HttpRequest -> 参数获取 -> 执行具体函数 -> 构建HttpResponse -> TCP socket返回结果
  2. 因为没有使用外部Web容器，所以使用了线程池+Socket监听某一端口来实现部分Http服务的能力，调用者向这个端口发起基于Http协议的请求，
     程序根据Http协议规定进行内容解析
  3.得到访问的具体Url后，我参照了Servlet的思路，使用反射动态生成处理某一逻辑的Service实例。
  4.处理完成后，程序根据Http协议规范，拼接Response报文并执行返回
  
  
  ## 程序缺点
   1. 异常捕获这块没有做太好，有时间的话需要优化下
   2. 自己实现的Http服务能力，由于时间及技术原因，只支持请求头中包含Content-Length的POST或PUT请求（比如chunked模式的数据传输，短连接模式的传输是不支持的。）
   3. 终极实现目标是使用注解来规定service和url之间的映射关系（类似SpringMVC中的RequestMapping）；
      其次想使用xml配置文件或yml配置文件来标记service和url之间的映射关系。（参照Servlet），但由于时间关系，只能先将映射关系硬编码进了UrlDispatcher类中。
   4.Http响应头及响应报文处理的也不太全面，有机会会继续优化
