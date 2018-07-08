package blog.bean;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class CommentPojo {
    private int id;
    private int ismaincomment;
    private String observername;
    private String toobservername;
    private Date date;
    private String formatDate;
    private int vicecomment_maincomment_id;
    private String value;
    public List<CommentPojo> viceComment;


    public int getId() {
        return id;
    }


    public List<CommentPojo> getViceComment() {
        return viceComment;
    }

    @JsonIgnore
    public int getIsmaincomment() {
        return ismaincomment;
    }

    public void setIsmaincomment(int ismaincomment) {
        this.ismaincomment = ismaincomment;
    }

    public String getObservername() {
        return observername;
    }

    public void setObservername(String observername) {
        this.observername = observername;
    }

    public String getToobservername() {
        return toobservername;
    }

    public void setToobservername(String toobservername) {
        this.toobservername = toobservername;
    }

    @JSONField(serialize = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JSONField(name = "date")
    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public void setViceComment(List<CommentPojo> viceComment) {
        this.viceComment = viceComment;
    }

    @JSONField(serialize = false)
    public int getVicecomment_maincomment_id() {
        return vicecomment_maincomment_id;
    }

    public void setVicecomment_maincomment_id(int vicecomment_maincomment_id) {
        this.vicecomment_maincomment_id = vicecomment_maincomment_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

}
