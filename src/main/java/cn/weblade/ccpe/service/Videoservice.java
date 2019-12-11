package cn.weblade.ccpe.service;

import cn.weblade.ccpe.entity.CourseContext;
import cn.weblade.ccpe.entity.Video;

import java.util.List;
import java.util.Map;

public interface Videoservice {
    public List<CourseContext> getcourse();
    public int addVideo(Video video);
    public int addVideo2(Integer videoId, String courseName);
    public int deletevideo(Integer videoId);
    public List<Video> getvideo(Map map);
    public List<Video> test();
}
