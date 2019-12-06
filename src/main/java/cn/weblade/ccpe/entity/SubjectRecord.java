package cn.weblade.ccpe.entity;

import javax.persistence.*;

@Table(name = "subject_record")
public class SubjectRecord {
    @Id
    @Column(name = "subject_record_id")
    private Integer subjectRecordId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fill_blank_id")
    private Integer fillBlankId;

    @Column(name = "judge_id")
    private Integer judgeId;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "multiple_choice_id")
    private Integer multipleChoiceId;

    /**
     * @return subject_record_id
     */
    public Integer getSubjectRecordId() {
        return subjectRecordId;
    }

    /**
     * @param subjectRecordId
     */
    public void setSubjectRecordId(Integer subjectRecordId) {
        this.subjectRecordId = subjectRecordId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return fill_blank_id
     */
    public Integer getFillBlankId() {
        return fillBlankId;
    }

    /**
     * @param fillBlankId
     */
    public void setFillBlankId(Integer fillBlankId) {
        this.fillBlankId = fillBlankId;
    }

    /**
     * @return judge_id
     */
    public Integer getJudgeId() {
        return judgeId;
    }

    /**
     * @param judgeId
     */
    public void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId;
    }

    /**
     * @return user_answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * @param userAnswer
     */
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getMultipleChoiceId() {
        return multipleChoiceId;
    }

    public void setMultipleChoiceId(Integer multipleChoiceId) {
        this.multipleChoiceId = multipleChoiceId;
    }

    @Override
    public String toString() {
        return "SubjectRecord{" +" \r\n" +
                "subjectRecordId=" + subjectRecordId +" \r\n" +
                ", userId=" + userId +" \r\n" +
                ", fillBlankId=" + fillBlankId +" \r\n" +
                ", judgeId=" + judgeId +" \r\n" +
                ", multipleChoiceId=" + multipleChoiceId +" \r\n" +
                ", userAnswer='" + userAnswer+" \r\n" +
                '}';
    }
}