package cn.cjam.biz;

import cn.cjam.dao.RunLogDao;
import cn.cjam.model.RunLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jam on 2016/6/14.
 */
@Service
public class RunLogService {

    @Autowired
    private RunLogDao runLogDao;

    public Integer insertList(List<RunLog> runLogList){
        return runLogDao.insertList(runLogList);
    }

    public List<RunLog> getRunLogListByTime(List<Integer> states, String beginDate, String endDate){
        return runLogDao.getRunLogListByTime(states,beginDate,endDate);
    }

    public Integer update(RunLog runLog){
        return runLogDao.update(runLog);
    }
}
