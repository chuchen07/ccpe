package cn.weblade.ccpe.service;

import cn.weblade.ccpe.entity.Course;
import cn.weblade.ccpe.entity.FillBlank;
import cn.weblade.ccpe.entity.Judge;
import cn.weblade.ccpe.entity.MultipleChoice;

import java.util.List;
import java.util.Map;

public interface CourseSerService {
    public int updateSubject();
    public Course querySubject(Integer paperId);
    public int queryPaperNameById(String paperName);
    public List selectPaperName();
    public List<FillBlank> queryAllFillBlank();
    public List<Judge> queryAllJudge();
    public List<MultipleChoice>queryAllMultipleChoice();
    public Map<String,Object> correctPaper(Integer paperId, Integer userId, String[] fillBlank, String[] judge, String[] multipleChoice);
    public Course queryAllRecordSubject(Integer userId);
    public Map<String,Object> correctWrongSubject(Integer userId, String[] fillBlank, String[] judge, String[] multipleChoice);


}
