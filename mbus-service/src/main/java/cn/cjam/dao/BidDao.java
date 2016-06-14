package cn.cjam.dao;

import cn.cjam.model.ShowResultTemplate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jam on 2016/6/14.
 */
public interface BidDao {

    final static String tableName = "t_bid";

    @Insert("insert into "+tableName+"(url, title, content,type,create_time)" +
            " values(#{url},#{title},#{content},#{type},now());")
    public int insert(@Param("item") ShowResultTemplate result);
}
