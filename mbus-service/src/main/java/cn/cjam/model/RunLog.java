package cn.cjam.model;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

/**
 * Created by cheng on 2016/6/11.
 */
public class RunLog {

    public final static int state_default = 0;
    public final static int state_ok = 1;
    public final static int state_fail = 2;
    public final static int state_partfail = 3;
    Long id;
    Long seedId;
    String dateOnly;
    String runInfo = "";
    // 0:未完成， 1：正常完成， 2:失败完成
    Integer state = 0;
    Date ctime;
    Date utime;
    SeedTemplate seed;
    JSONArray result;

    public JSONArray getResult() {
        return result;
    }

    public void setResult(JSONArray result) {
        this.result = result;
    }

    public SeedTemplate getSeed() {
        return seed;
    }

    public void setSeed(SeedTemplate seed) {
        this.seed = seed;
    }

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

    public String getDateOnly() {
        return dateOnly;
    }

    public void setDateOnly(String dateOnly) {
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
