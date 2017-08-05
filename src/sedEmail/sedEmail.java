/**
 * 
 */
/**
 * @author kami
 *
 */
package sedEmail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.keyVal;
import util.getMonDate;
public class sedEmail {
    public static void semail(String text) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");//必须 普通客户端
        props.setProperty("mail.transport.protocol", "smtp");//必须选择协议的
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);//设置debug模式   在控制台看到交互信息
        Message msg = new MimeMessage(session);  //建立一个要发送的信息
        msg.setSubject(getMonDate.getDay(1, keyVal.ymd)+":统计数据");
        msg.setText(text);//设置简单的发送内容
        msg.setFrom(new InternetAddress("13612100484@163.com"));//发件人邮箱号
        Transport transport = session.getTransport();//发送信息的工具
        transport.connect("smtp.163.com", 25, "13612100484@163.com", "hyjf2215710");//发件人邮箱号 和密码
        transport.sendMessage(msg, new Address[] { 
        new InternetAddress("dejun.wang@xiaoniangao.cn"),
//        new InternetAddress("yunchao.lu@xiaoniangao.cn")
        });//对方的地址
        transport.close();
    }


}