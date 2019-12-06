package cn.weblade.ccpe.service;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    public String saveSubject(MultipartFile file) throws Exception;
    public int deleteSubject(String paperName);

}
