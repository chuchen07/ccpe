package cn.weblade.ccpe.entity;

import javax.persistence.*;

@Table(name = "fill_blank")
public class FillBlank {
    /**
     * 填空题id
     */
    @Id
    @Column(name = "fill_blank_id")
    private Integer fillBlankId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 来源试卷id
     */
    @Column(name = "paper_id")
    private Integer paperId;

    /**
     * 内容
     */
    private String context;

    /**
     * 获取填空题id
     *
     * @return fill_blank_id - 填空题id
     */
    public Integer getFillBlankId() {
        return fillBlankId;
    }

    /**
     * 设置填空题id
     *
     * @param fillBlankId 填空题id
     */
    public void setFillBlankId(Integer fillBlankId) {
        this.fillBlankId = fillBlankId;
    }

    /**
     * 获取答案
     *
     * @return answer - 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案
     *
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取来源试卷id
     *
     * @return paper_id - 来源试卷id
     */
    public Integer getPaperId() {
        return paperId;
    }

    /**
     * 设置来源试卷id
     *
     * @param paperId 来源试卷id
     */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    /**
     * 获取内容
     *
     * @return context - 内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置内容
     *
     * @param context 内容
     */
    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "FillBlank{" +" \r\n" +
                "fillBlankId=" + fillBlankId +" \r\n" +
                ", answer='" + answer + '\'' +" \r\n" +
                ", paperId=" + paperId +" \r\n" +
                ", context='" + context + '\'' +" \r\n" +
                '}';
    }
}