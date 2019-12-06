package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.FillBlank;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
import java.util.List;

@Repository
public interface FillBlankMapper  extends Mapper<FillBlank>,MySqlMapper<FillBlank>{
    public List selectFilByPaperId(int paperId);

    @Delete("delete from fill_blank where paper_id=#{paperId}")
    public int FillBlankDelete(int paperId);

    @Select("select* from fill_blank where 1=1")
    public List<FillBlank> queryAllFillBlank();

    @Select("select answer,fill_blank_id from fill_blank where paper_id=#{paperId}")
    public List<FillBlank> queryFillBlankAnswerTrue(int paperId);

    @Select("select* from fill_blank where 1=1")
    public List<FillBlank> queryFillBlankUnCorrect();

    public List<FillBlank> queryFillBlankByListId(List<Integer> fillBlankId);
}