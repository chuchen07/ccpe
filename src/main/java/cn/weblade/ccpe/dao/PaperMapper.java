package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.Paper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.*;
@Repository
public interface PaperMapper  extends Mapper<Paper>,MySqlMapper<Paper>{

    public int insertReKey(Paper paper);
    @Delete("delete from paper where paper_id=#{paperId}")
    public int PaperDelete(int paperId);
}