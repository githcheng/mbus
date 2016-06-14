package cn.cjam.biz;

import cn.cjam.dao.BidDao;
import cn.cjam.model.RunLog;
import cn.cjam.model.SeedTemplate;
import cn.cjam.service.TaskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TaskProcessor taskProcessor;

    @Autowired
    private BidDao bidDao;
    /**
     * 入口
     */
    public void execute(){
        List<SeedTemplate> seedTemplateList = seedTemplateService.getSeedTemplateListByState(1);

        BlockingQueue<Future> futures = new LinkedBlockingQueue<Future>();
        Iterator<SeedTemplate> iterator = seedTemplateList.iterator();
        while (iterator.hasNext()){

            SeedTemplate next = iterator.next();
            TaskProcessor taskProcessor = new TaskProcessor(next,bidDao);
            Future<TaskProcessor> future = executorService.submit(taskProcessor);
            futures.add(future);
        }

        for (Future future : futures){
            try {
                RunLog runLog = (RunLog)future.get();
                logger.info("runLog:{}",runLog);
                System.out.println(runLog);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
