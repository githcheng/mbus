package cn.cjam.biz;

import cn.cjam.dao.BidDao;
import cn.cjam.model.RunLog;
import cn.cjam.model.SeedTemplate;
import cn.cjam.model.ShowResultTemplate;
import cn.cjam.service.TaskProcessor;
import cn.cjam.util.ParseUtil;
import cn.cjam.util.TimeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by jam on 2016/6/14.
 */
@Service
public class ScheduleService {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeedTemplateService seedTemplateService;

    @Autowired
    private BidDao bidDao;

    @Autowired
    private RunLogService runLogService;

    /**
     * 入口
     */
    public void execute() {


        String beginDate = TimeUtil.today();
        String endDate = TimeUtil.addDay(beginDate,1);

        List<SeedTemplate> seedTemplateList = seedTemplateService.getSeedTemplateListByState(1);

        HashMap<Long, SeedTemplate> seedDict = new HashMap<Long, SeedTemplate>();
        ArrayList<RunLog> runLogArrayList = new ArrayList<RunLog>();
        for (SeedTemplate item : seedTemplateList) {
            RunLog runLog = new RunLog();
            Long id = item.getId();
            runLog.setSeedId(id);
            runLog.setDateOnly(beginDate);

            seedDict.put(id, item);
            runLogArrayList.add(runLog);
        }

        try {
            runLogService.insertList(runLogArrayList);
        } catch (Exception e){
            logger.info("insertList exception",e);
        }
        ArrayList<Integer> states = new ArrayList<Integer>();
        states.add(0);


        List<RunLog> runLogs = runLogService.getRunLogListByTime(states, beginDate, endDate);

        CompletionService<RunLog> execcomp = new ExecutorCompletionService<RunLog>(executorService);
        BlockingQueue<Future<RunLog>> futures = new LinkedBlockingQueue<Future<RunLog>>();
        Iterator<RunLog> iterator = runLogs.iterator();
        while (iterator.hasNext()) {

            RunLog next = iterator.next();
            next.setSeed(seedDict.get(next.getSeedId()));
            TaskProcessor taskProcessor = new TaskProcessor(next);
            Future<RunLog> future = execcomp.submit(taskProcessor);
            futures.add(future);
        }

        for (Future future : futures) {
            try {
                RunLog runLog = (RunLog) future.get();
                handleResult(runLog);
                logger.info("runLog:{}", runLog);
                System.out.println(runLog);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleResult(RunLog runLog){
        SeedTemplate seed = runLog.getSeed();
        JSONArray resultArr = runLog.getResult();
        List<ShowResultTemplate> resultList = ParseUtil.parse(resultArr);
        Iterator<ShowResultTemplate> iterator = resultList.iterator();
        int failCount = 0;
        while (iterator.hasNext()) {
            ShowResultTemplate next = iterator.next();
            next.setType(seed.getType());
            try {
                bidDao.insert(next);
            } catch (Exception e) {
                logger.info("bidDao insert fail:{}", e);
                failCount++;
            }
        }
        JSONObject runInfo = new JSONObject();
        runInfo.put("total", resultList.size());
        runInfo.put("fail", failCount);
        if (failCount == resultList.size() || resultList.size() == 0){
            // 以失败结束
            runLog.setState(RunLog.state_fail);
        } else if ( failCount == 0 ){
            runLog.setState(RunLog.state_ok);
        } else if (failCount > 0 && failCount < resultList.size()){
            runLog.setState(RunLog.state_partfail);
        }
        runLog.setRunInfo(runInfo.toJSONString());
        runLogService.update(runLog);
        return;
    }
}
