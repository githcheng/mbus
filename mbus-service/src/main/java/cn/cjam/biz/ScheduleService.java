package cn.cjam.biz;

import cn.cjam.dao.BidDao;
import cn.cjam.model.RunLog;
import cn.cjam.model.SeedTemplate;
import cn.cjam.model.ShowResultTemplate;
import cn.cjam.service.TaskProcessor;
import cn.cjam.util.HtmlUtil;
import cn.cjam.util.ParseUtil;
import cn.cjam.util.TimeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
            for (RunLog runLog : runLogArrayList){
                ArrayList<RunLog> runLogs = new ArrayList<RunLog>(1);
                runLogs.add(runLog);
                runLogService.insertList(runLogs);
            }
            // runLogService.insertList(runLogArrayList);
        } catch (Exception e){
            logger.info("insertList exception",e);
        }
        ArrayList<Integer> states = new ArrayList<Integer>();
        states.add(RunLog.state_default);
        states.add(RunLog.state_ok);

        List<RunLog> runLogs = runLogService.getRunLogListByTime(states, beginDate, endDate);

        if (CollectionUtils.isEmpty(runLogs)){
            return;
        }
        Iterator<RunLog> runLogIterator = runLogs.iterator();
        while (runLogIterator.hasNext()){
            RunLog next = runLogIterator.next();
            if (next.getState() == RunLog.state_ok){
                Date utime = next.getUtime();
                long timeBefore4 =utime.getTime() + 3600 * 4;
                if (System.currentTimeMillis() < timeBefore4){
                    runLogIterator.remove();
                }
            }
        }

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
        int sucessCount = 0;
        while (iterator.hasNext()) {
            ShowResultTemplate next = iterator.next();
            next.setType(seed.getType());
            next.setTitle(HtmlUtil.clear(next.getTitle()));
            String content = HtmlUtil.clear(next.getContent());
            if (StringUtils.isNotBlank(content)){
                next.setContent(content.trim());
            } else {
                next.setContent("暂无内容...");
            }

            try {
                bidDao.insert(next);
                sucessCount++;
            } catch (Exception e) {
                logger.info("bidDao insert fail:{}", e);
            }
        }
        JSONObject runInfo = new JSONObject();
        runInfo.put("total", resultList.size());
        runInfo.put("success", sucessCount);
        if (resultList.size() == 0){
            // 以失败结束
            runLog.setState(RunLog.state_fail);
        } else if ( resultList.size() < 10){
            runLog.setState(RunLog.state_partfail);
        }  else {
            runLog.setState(RunLog.state_ok);
        }

        runLog.setRunInfo(runInfo.toJSONString());
        runLogService.update(runLog);
        return;
    }
}
