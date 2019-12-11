package cn.weblade.ccpe.entity;

public class Video {
    private Integer videoId;
    private String videoName;
    private String courseName;
    private String videoSrc;
    private String videoPath;


    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }



    public Video(String videoName, String courseName, String videoSrc, String videoPath){
        this.videoName=videoName;
        this.courseName = courseName;
        this.videoSrc=videoSrc;
        this.videoPath=videoPath;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }
}
