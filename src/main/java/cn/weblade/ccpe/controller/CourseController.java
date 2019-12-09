package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.Course;
import cn.weblade.ccpe.entity.Video;
import cn.weblade.ccpe.service.CourseService;
import cn.weblade.ccpe.service.serviceImpl.VideoserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    VideoserviceImpl videoservice;

    /*添加题*/
    @RequestMapping("/saveSubject")
    public String subjectAdd(MultipartFile file) throws Exception {

        String ret=courseService.saveSubject(file);
        return ret;
    }

    /*删除题*/
    @RequestMapping("/deleteSubject")
    public int subjectDelete(String paperName){

        courseService.deleteSubject(paperName);
        return 0;
    }


    /*预览题，不添加进数据库*/
    @RequestMapping("/subjectBrowse")
    public Course subjectBrowse(MultipartFile file) throws Exception {

        Course ret=courseService.subjectBrowse(file);
        return ret;
    }


    //返回上传页面
    @RequestMapping("/Upload")
    public String Upload(){
        return "send";
    }


    //上传视频第一步
    @RequestMapping("/UploadVideo")
    @ResponseBody
    public Map<String,Integer> UploadVideo(@RequestParam("file")MultipartFile file){
        Map<String,Integer> resultmap=new HashMap<>();
        String video_path="F:\\ChickenVideo\\"; //定义视频真实存储路径
        String video_src="/ChickenVideo/";//定义视频虚拟路径
        String newName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+file.getOriginalFilename();//定义物理路径文件的名称
        java.io.File videofile=new File(video_path+newName);//创建文件对象
        String []videoName=file.getOriginalFilename().split("\\.");
        Video video=new Video(videoName[0],"初级",video_src+newName,video_path+newName);
        videoservice.addVideo(video);
        System.out.println(videofile.getPath());
        try{
            file.transferTo(videofile);//把视频文件写入真实路径
        }catch(Exception e){
            resultmap.put("code",1);//返回失败状态码
            return resultmap;
        }
        resultmap.put("code",0);//返回成功状态码
        resultmap.put("videoId",video.getVideoId());
        return resultmap;

    }



    //上传视频第二步
    @RequestMapping("/UploadVideo2")
    @ResponseBody
    public Map<String,String> UploadVideo2(Integer videoId,String courseName){
        Map<String,String> resultmap=new HashMap<>();
        videoservice.addVideo2(videoId,courseName);
        resultmap.put("success","success");
        return resultmap;
    }



    //删除视频
    @RequestMapping("/deletevideo")
    @ResponseBody
    public Map<String,Integer> delevideo(Integer videoId){
        Map<String,Integer> resultmap=new HashMap<>();
        int i=videoservice.deletevideo(videoId);
        resultmap.put("deletevideo",i);
        return resultmap;
    }

    //视频列表，通过课程名称和名字查询
    @RequestMapping("/queryVideoByCourseAndName")
    @ResponseBody
    public List<Video> getvideo(Video video){
        return videoservice.getvideo(video);
    }

    @RequestMapping("/test")
    @ResponseBody
    public List<Video> test(){
        return videoservice.test();
    }





}
