package blog.bean;


import java.util.Date;

public class WordPojo {

  private long id;
  private String title;
  private long permission;
  private Date date;
  private String summary;
  private String writer;
  private String backgroundImage;
  private String wordTextID;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public long getPermission() {
    return permission;
  }

  public void setPermission(long permission) {
    this.permission = permission;
  }


  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }


  public String getBackgroundimage() {
    return backgroundImage;
  }

  public void setBackgroundimage(String backgroundimage) {
    this.backgroundImage = backgroundimage;
  }

  public String getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  public String getWordTextID() {
    return wordTextID;
  }

  public void setWordTextID(String wordTextID) {
    this.wordTextID = wordTextID;
  }
}
