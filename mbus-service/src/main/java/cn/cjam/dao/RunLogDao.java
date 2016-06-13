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

    @Select("<script> select id, url as start_url, content, state, type,is_browse,operator from "+tableName+
            " where state in ( " +
            "<foreach item='item' index='index' collection='states' separator=','>"+
            "#{item}"
            + "</foreach>"+" ) order by utime desc </script>")
    public List<SeedTemplate> getSeedTemplateListByState(@Param("states") List<Integer> states);


    @Insert("insert into "+tableName+"(seed_id,date_only run_info, state,ctime,utime)" +
            " values(#{item.seedId},#{item.dateOnly},#{item.runInfo},#{item.state},now(),now())")
    public Integer insert(@Param("item") RunLog obj);


    @Update("update "+tableName+" set url=#{item.startUrl}, content=#{item.content}, " +
            " type=#{item.type},is_browse=#{item.isBrowse},operator=#{item.operator},utime=now() where id=#{item.id}")
    public Integer update(@Param("item") SeedTemplate obj);


    @Update("update "+tableName+" set state=#{item.state},utime=now() where id=#{item.id}")
    public Integer confirm(@Param("item") SeedTemplate obj);

    @Delete("delete from "+tableName+" where id=#{id}")
    public Integer delete(@Param("id") Long id);

}