package cn.cjam.biz;

import cn.cjam.dao.SeedTemplateDao;
import cn.cjam.model.SeedTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2016/6/9.
 */
@Service
public class SeedTemplateService {

    @Autowired
    private SeedTemplateDao seedTemplateDao;

    public List<SeedTemplate> getSeedTemplateListByState(Integer... states){

        List<Integer> stateList = new ArrayList<Integer>(states.length);
        for (Integer i : states){
            System.out.println(i);
            stateList.add(i);
        }
        return seedTemplateDao.getSeedTemplateListByState(stateList);
    }

    public Integer insert(SeedTemplate seedTemplate){
        return seedTemplateDao.insert(seedTemplate);
    }

    public Integer update(SeedTemplate obj){
        return seedTemplateDao.update(obj);
    }

    public Integer confirm(SeedTemplate obj){
        return seedTemplateDao.confirm(obj);
    }

    public Integer delete(Long id){
        return seedTemplateDao.delete(id);
    }
}
