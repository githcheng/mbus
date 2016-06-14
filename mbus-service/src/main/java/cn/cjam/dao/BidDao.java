package cn.cjam.dao;

import cn.cjam.model.ShowResultTemplate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jam on 2016/6/14.
 */
public interface BidDao {

    final static String tableName = "t_bid";

    @Insert("insert into "+tableName+"(url, title, content,type,time,create_time)" +
            " values(#{item.url},#{item.title},#{item.content},#{item.type},now(),now());")
    public int insert(@Param("item") ShowResultTemplate result);
}
