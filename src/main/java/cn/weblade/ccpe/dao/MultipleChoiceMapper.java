package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.MultipleChoice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
import java.util.List;

@Repository
public interface MultipleChoiceMapper extends Mapper<MultipleChoice>,MySqlMapper<MultipleChoice>{

    public List selectMulByPaperId(int paperId);
    @Delete("delete from multiple_choice where paper_id=#{paperId}")
    public int MultipleChoiceDelete(int paperId);
    @Select("select* from multiple_choice where 1=1")
    public List<MultipleChoice>queryAllMultipleChoice();
    @Select("select answer_true,multiple_choice_id from multiple_choice where paper_id=#{paperId}")
    public List<MultipleChoice> queryMultipleChoiceAnswerTrue(int paperId);

    public List<MultipleChoice> queryMultipleChoiceByListId(List<Integer> MultipleChoiceId);
}