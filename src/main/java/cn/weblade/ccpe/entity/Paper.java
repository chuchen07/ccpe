package cn.weblade.ccpe.entity;

import javax.persistence.*;

public class Paper {
    /**
     * 试卷id
     */
    @Id
    @Column(name = "paper_id")
    private Integer paperId;

    /**
     * 试卷名称
     */
    @Id
    @Column(name = "paper_name")
    private String paperName;

    /**
     * 获取试卷id
     *
     * @return paper_id - 试卷id
     */
    public Integer getPaperId() {
        return paperId;
    }

    /**
     * 设置试卷id
     *
     * @param paperId 试卷id
     */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /**
     * 获取试卷名称
     *
     * @return paper_name - 试卷名称
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * 设置试卷名称
     *
     * @param paperName 试卷名称
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    @Override
    public String toString() {
        return "Paper{" +" \r\n" +
                "paperId=" + paperId +" \r\n" +
                ", paperName='" + paperName + '\'' +" \r\n" +
                '}';
    }
}