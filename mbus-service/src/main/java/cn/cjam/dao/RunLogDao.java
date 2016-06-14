package cn.cjam.dao;

import cn.cjam.model.RunLog;
import cn.cjam.model.SeedTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RunLogDao {


    /*
              `seed_id` VARCHAR(255) NOT NULL DEFAULT '',
              `run_info` LONGTEXT NOT NULL,
              `state` TINYINT(4) NOT NULL DEFAULT '0',
              `ctime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
              `utime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',

     */
    final static String tableName = "t_run_log";

    @Select("<script> select id, seed_id from "+tableName+
            " where state in ( " +
            "<foreach item='item' index='index' collection='states' separator=','>"+
            "#{item}"
            + "</foreach>"+" ) order by utime desc </script>")
    public List<RunLog> getSeedIdListByState(@Param("states") List<Integer> states);




    @Insert("<script>  insert into "+tableName+"(seed_id,date_only, run_info, state,ctime,utime)" +
            " values <foreach item='item' index='index' collection='items' separator=','> " +
            "(#{item.seedId},#{item.dateOnly},#{item.runInfo},#{item.state},now(),now()) " +
            "</foreach> </script>")
    public Integer insertList(@Param("items") List<RunLog> runLogList);

    @Insert("insert into "+tableName+"(seed_id,date_only, run_info, state,ctime,utime)" +
            " values(#{item.seedId},#{item.dateOnly},#{item.runInfo},#{item.state},now(),now())")
    public Integer insert(@Param("item") RunLog obj);


    @Update("update "+tableName+" set run_info=#{item.runInfo}, state=#{item.state}, " +
            " utime=now() where id=#{item.id}")
    public Integer update(@Param("item")RunLog obj);


    @Select("<script>   select * from "+tableName
            +" where state in ( <foreach item='item' index='index' collection='states' separator=','> #{item}</foreach> ) " +
            "and ctime between #{beginDate} and #{endDate}  </script>")
    List<RunLog> getRunLogListByTime(@Param("states") List<Integer>  states, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
}