package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.SubjectRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
import java.util.List;
@Repository
public interface SubjectRecordMapper  extends Mapper<SubjectRecord>,MySqlMapper<SubjectRecord>{

    @Insert("insert into subject_record(user_id,fill_blank_id,judge_id,multiple_choice_id,user_answer) values (#{userId},#{fillBlankId},#{judgeId},#{multipleChoiceId},#{userAnswer})")
    public int recordSubject(Integer userId, Integer fillBlankId, Integer judgeId, Integer multipleChoiceId, String userAnswer);

    @Select("select* from subject_record where user_id=#{userId}")
    public List<SubjectRecord> queryAllRecordSubjectId(Integer userId);

    @Select("select* from subject_record where user_id=#{userId} and multiple_choice_id=#{multipleChoiceId}")
    public SubjectRecord isMulExist(Integer userId, Integer multipleChoiceId);
    @Select("select* from subject_record where user_id=#{userId} and fill_blank_id=#{fillBlankId}")
    public SubjectRecord isFilExist(Integer userId, Integer fillBlankId);
    @Select("select* from subject_record where user_id=#{userId} and judge_id=#{judgeId}")
    public SubjectRecord isJudExist(Integer userId, Integer judgeId);

    @Delete("delete from subject_record where fill_blank_id=#{fillBlankId} and user_id=#{userId}")
    public int filOutOfRecord(Integer userId, Integer fillBlankId);
    @Delete("delete from subject_record where multiple_choice_id=#{multipleChoiceId} and user_id=#{userId}")
    public int mulOutOfRecord(Integer userId, Integer multipleChoiceId);
    @Delete("delete from subject_record where judge_id=#{judgeId} and user_id=#{userId}")
    public int judOutOfRecord(Integer userId, Integer judgeId);
}