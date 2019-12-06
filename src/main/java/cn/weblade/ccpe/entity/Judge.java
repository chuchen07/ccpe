package cn.weblade.ccpe.entity;

import javax.persistence.*;

public class Judge {
    /**
     * 判断题id
     */
    @Id
    @Column(name = "judge_id")
    private Integer judgeId;

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
     * 获取判断题id
     *
     * @return judge_id - 判断题id
     */
    public Integer getJudgeId() {
        return judgeId;
    }

    /**
     * 设置判断题id
     *
     * @param judgeId 判断题id
     */
    public void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId;
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
        return "Judge{" +" \r\n" +
                "judgeId=" + judgeId +" \r\n" +
                ", answer='" + answer + '\'' +" \r\n" +
                ", paperId=" + paperId +" \r\n" +
                ", context='" + context + '\'' +" \r\n" +
                '}';
    }
}