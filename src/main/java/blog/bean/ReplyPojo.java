package blog.bean;

import java.util.List;

/**
 * Created by cao on 2017/2/2.
 */
public class ReplyPojo {

    private String wordId;
    private String toObservername;
    private String mainCommentId;
    private String viceCommentId;
    private String nickname;
    private String email;
    private String value;
    private String isNewMainComment;

    public String getIsNewMainComment() {
        return isNewMainComment;
    }

    public void setIsNewMainComment(String isNewMainComment) {
        this.isNewMainComment = isNewMainComment;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getToObservername() {
        return toObservername;
    }

    public void setToObservername(String toObservername) {
        this.toObservername = toObservername;
    }

    public String getMainCommentId() {
        return mainCommentId;
    }

    public void setMainCommentId(String mainCommentId) {
        this.mainCommentId = mainCommentId;
    }

    public String getViceCommentId() {
        return viceCommentId;
    }

    public void setViceCommentId(String viceCommentId) {
        this.viceCommentId = viceCommentId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
