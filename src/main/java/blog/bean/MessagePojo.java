package blog.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagePojo implements Serializable {

    private String nick_name;
    private Date date;
    private String content;
    private String messageTime;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月dd,yyyy HH:mm:ss");

    @JSONField(name = "messageName")
    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    @JSONField(serialize = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.messageTime=simpleDateFormat.format(date);
        this.date = date;
    }

    @JSONField(name = "messageContent")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
