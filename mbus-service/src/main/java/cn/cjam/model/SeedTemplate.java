package cn.cjam.model;

/**
 * Created by cheng on 2016/2/23.
 */
public class SeedTemplate {

    private Long id;
    private String startUrl;
    private String content;
    private Integer state;
    private Integer isBrowse;
    private String operator;

    public SeedTemplate(){

    }

    public SeedTemplate(String startUrl,String content,Integer isBrowse){
        this.startUrl = startUrl;
        this.content = content;
        this.isBrowse = isBrowse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsBrowse() {
        return isBrowse;
    }

    public void setIsBrowse(Integer isBrowse) {
        this.isBrowse = isBrowse;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "SeedTemplate{" +
                "id=" + id +
                ", startUrl='" + startUrl + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", isBrowse=" + isBrowse +
                ", operator='" + operator + '\'' +
                '}';
    }
}
