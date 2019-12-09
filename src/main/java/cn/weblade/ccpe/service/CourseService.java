package cn.weblade.ccpe.service;
import cn.weblade.ccpe.entity.Course;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    public String saveSubject(MultipartFile file) throws Exception;
    public int deleteSubject(String paperName);
    public Course subjectBrowse(MultipartFile file) throws Exception;

}
