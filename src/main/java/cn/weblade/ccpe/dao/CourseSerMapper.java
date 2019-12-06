package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.Course;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
import java.util.List;

@Repository
public interface CourseSerMapper extends Mapper<Course>,MySqlMapper<Course>{

    @Select("select paper_id from paper where paper_name=#{paperName}")
    public Integer selectByPaperName(String paperName);

    @Select("select paper_name from paper where 1=1")
    public List selectPaperName();



    @Select("Select paper_id from paper where paper_name=#{paperName}")
    public int selectPaperByPaperName(String paperName);

}