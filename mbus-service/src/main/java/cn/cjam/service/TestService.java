package cn.cjam.service;

import cn.cjam.dao.TestDao;
import cn.cjam.model.TestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2015/10/24.
 */
@Service
public class TestService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestDao testDao;


    public List<TestUser> getList(int id){
        ArrayList<TestUser> testUsers = new ArrayList<>();
        for(int i=1;i<10;i++){
            TestUser testUser = new TestUser();
            testUser.setName("name_"+i);
            testUsers.add(testUser);
        }
        return testUsers;
    }
}
