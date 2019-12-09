package cn.weblade.ccpe.service.serviceImpl;

import cn.weblade.ccpe.dao.CourseMapper;
import cn.weblade.ccpe.entity.CourseContext;
import cn.weblade.ccpe.entity.Video;
import cn.weblade.ccpe.service.Videoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class VideoserviceImpl implements Videoservice {
    @Autowired
    CourseMapper courseMapper;

    //返回所有课程
    @Override
    public List<CourseContext> getcourse() {
        List<CourseContext> list=courseMapper.getcourse();
        return list;
    }

    //添加视频第一步
    @Override
    public int addVideo(Video video) {
        int i=courseMapper.addvideo(video);
        return i;
    }

    //添加视频第二步
    @Override
    public int addVideo2(Integer videoId, String courseName) {
        return courseMapper.addvideo2(videoId,courseName);
    }


    //删除视频
    @Override
    public int deletevideo(Integer videoId) {
        Video video=courseMapper.geivideobyId(videoId);
        File file=new File(video.getVideoPath());
        if(file.exists()){
            file.delete();
            System.out.println("文件已成功被删除");
        }
        return courseMapper.deletevideo(videoId);
    }

    //返回视频列表
    @Override
    public List<Video> getvideo(Video video) {
        return courseMapper.getvideo(video);
    }

    @Override
    public List<Video> test() {
        return courseMapper.test();
    }
}
