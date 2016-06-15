package cn.cjam.model;

/**
 * Created by jam on 2016/6/8.
 */
public class ShowResultTemplate {

    public Integer skip;
    public Integer statusCode;
    public String title;
    public String content;
    public Integer contentSize;
    public String url;

    public Integer type;

    public String pureContent;

    public String getPureContent() {
        return pureContent;
    }

    public void setPureContent(String pureContent) {
        this.pureContent = pureContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentSize() {
        return contentSize;
    }

    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
