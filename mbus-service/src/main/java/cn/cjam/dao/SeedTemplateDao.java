package cn.cjam.dao;

import cn.cjam.model.SeedTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SeedTemplateDao {

    final static String tableName = "t_template";

    @Select("<script> select id, url as start_url, content, state, type,is_browse,operator from "+tableName+
            " where state in ( " +
            "<foreach item='item' index='index' collection='states' separator=','>"+
            "#{item}"
            + "</foreach>"+" ) order by utime desc </script>")
    public List<SeedTemplate> getSeedTemplateListByState(@Param("states")List<Integer> states);


    @Insert("insert into "+tableName+"(url, content, state, type,is_browse,operator,ctime,utime)" +
            " values(#{item.startUrl},#{item.content},#{item.state},#{item.type},#{item.isBrowse},#{item.operator},now(),now())")
    public Integer insert(@Param("item") SeedTemplate obj);


    @Update("update "+tableName+" set url=#{item.startUrl}, content=#{item.content}, " +
            " type=#{item.type},is_browse=#{item.isBrowse},operator=#{item.operator},utime=now() where id=#{item.id}")
    public Integer update(@Param("item") SeedTemplate obj);


    @Update("update "+tableName+" set state=#{item.state},utime=now() where id=#{item.id}")
    public Integer confirm(@Param("item") SeedTemplate obj);

    @Delete("delete from "+tableName+" where id=#{id}")
    public Integer delete(@Param("id") Long id);






}