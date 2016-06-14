package cn.cjam.model;

import java.util.Date;

/**
 * Created by cheng on 2016/6/11.
 */
public class RunLog {

    Long id;
    Long seedId;
    Integer dateOnly;
    String runInfo;
    Integer state;
    Date ctime;
    Date utime;

    @Override
    public String toString() {
        return "RunLog{" +
                "id=" + id +
                ", seedId=" + seedId +
                ", dateOnly=" + dateOnly +
                ", runInfo='" + runInfo + '\'' +
                ", state=" + state +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }

    public Integer getDateOnly() {
        return dateOnly;
    }

    public void setDateOnly(Integer dateOnly) {
        this.dateOnly = dateOnly;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeedId() {
        return seedId;
    }

    public void setSeedId(Long seedId) {
        this.seedId = seedId;
    }

    public String getRunInfo() {
        return runInfo;
    }

    public void setRunInfo(String runInfo) {
        this.runInfo = runInfo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }
}
