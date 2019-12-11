package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.CourseContext;
import cn.weblade.ccpe.entity.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {

    @Select("select * from course")
    public List<CourseContext> getcourse();

    @Insert("Insert into video(video_id,video_name,course_name,video_src,video_path)values(#{videoId},#{videoName},#{courseName},#{videoSrc},#{videoPath})")
    @Options(useGeneratedKeys=true, keyProperty="videoId", keyColumn="video_id")
    public int addvideo(Video video);

    @Update("update video set course_name=#{courseName} where video_id=#{videoId}")
    public int addvideo2(Integer videoId, String courseName);

    @Delete("delete from video where video_id=#{videoId}")
    public int deletevideo(Integer videoId);

    @Select("select * from video where video_id=#{videoId}")
    public Video geivideobyId(Integer videoId);

    @Select("select * from video where course_name=#{courseName}")
    public List<Video> selectvideobyCourse(String courseName);



    //用于视频列表，视频课程查询
    public List<Video> getvideo(Video video);

    //测试
    public List<Video> test();


}
