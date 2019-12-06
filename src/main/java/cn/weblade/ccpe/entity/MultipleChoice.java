package cn.weblade.ccpe.entity;

import javax.persistence.*;

@Table(name = "multiple_choice")
public class MultipleChoice {
    /**
     * 选择题id
     */
    @Id
    @Column(name = "multiple_choice_id")
    private Integer multipleChoiceId;

    /**
     * 选项一
     */
    private String answer1;

    /**
     * 选项二
     */
    private String answer2;

    /**
     * 选项三
     */
    private String answer3;

    /**
     * 选项四
     */
    private String answer4;

    /**
     * 正确答案
     */
    @Column(name = "answer_true")
    private String answerTrue;

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
     * 获取选择题id
     *
     * @return multiple_choice_id - 选择题id
     */
    public Integer getMultipleChoiceId() {
        return multipleChoiceId;
    }

    /**
     * 设置选择题id
     *
     * @param multipleChoiceId 选择题id
     */
    public void setMultipleChoiceId(Integer multipleChoiceId) {
        this.multipleChoiceId = multipleChoiceId;
    }

    /**
     * 获取选项一
     *
     * @return answer1 - 选项一
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * 设置选项一
     *
     * @param answer1 选项一
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * 获取选项二
     *
     * @return answer2 - 选项二
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * 设置选项二
     *
     * @param answer2 选项二
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * 获取选项三
     *
     * @return answer3 - 选项三
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * 设置选项三
     *
     * @param answer3 选项三
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * 获取选项四
     *
     * @return answer4 - 选项四
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * 设置选项四
     *
     * @param answer4 选项四
     */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    /**
     * 获取正确答案
     *
     * @return answer_true - 正确答案
     */
    public String getAnswerTrue() {
        return answerTrue;
    }

    /**
     * 设置正确答案
     *
     * @param answerTrue 正确答案
     */
    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
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
        return "MultipleChoice{" +" \r\n" +
                "multipleChoiceId=" + multipleChoiceId +" \r\n" +
                ", answer1='" + answer1 + '\'' +" \r\n" +
                ", answer2='" + answer2 + '\'' +" \r\n" +
                ", answer3='" + answer3 + '\'' +" \r\n" +
                ", answer4='" + answer4 + '\'' +" \r\n" +
                ", answerTrue='" + answerTrue + '\'' +" \r\n" +
                ", paperId=" + paperId +" \r\n" +
                ", context='" + context + '\'' +" \r\n" +
                '}';
    }
}