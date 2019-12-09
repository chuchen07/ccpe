package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.Judge;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
import java.util.List;

@Repository
public interface JudgeMapper  extends Mapper<Judge>,MySqlMapper<Judge>{
    public List selectJudByPaperId(int paperId);
    @Delete("delete from judge where paper_id=#{paperId}")
    public int JudgeDelete(int paperId);
    @Select("select* from judge where 1=1")
    public List<Judge> queryAllJudge();
    @Select("select answer,judge_id from judge where paper_id=#{paperId}")
    public List<Judge> queryJudgeAnswerTrue(int paperId);

    public List<Judge> queryJudgeByListId(List<Integer> JudgeId);
}