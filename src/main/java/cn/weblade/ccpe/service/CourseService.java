package cn.weblade.ccpe.service;
import cn.weblade.ccpe.entity.Course;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    public String saveSubject(String fileName) throws Exception;
    public String deleteSubject(String paperName);
    public Course subjectBrowse(String fileName) throws Exception;
    public String subjectBrowseUpload(MultipartFile file)throws Exception;

}
