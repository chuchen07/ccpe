package cn.weblade.ccpe.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "course")
@SecondaryTables({
        @SecondaryTable(name = "judge"),
        @SecondaryTable(name = "paper"),
        @SecondaryTable(name = "fill_blank"),
        @SecondaryTable(name = "subject_record"),
        @SecondaryTable(name = "multiple_choice")
})
public class Course {
        @Id
        @Column(name = "course_id")
        private Integer courseId;
        private List<FillBlank> fillBlankList;
        private List<Judge>judgeList;
        private List<MultipleChoice>multipleChoiceList;
        private List<Paper>paperList;
        private List<SubjectRecord>subjectRecordList;

        public List<FillBlank> getFillBlankList() {
            return fillBlankList;
        }

        public void setFillBlankList(List<FillBlank> fillBlankList) {
            this.fillBlankList = fillBlankList;
        }

        public List<Judge> getJudgeList() {
            return judgeList;
        }

        public void setJudgeList(List<Judge> judgeList) {
            this.judgeList = judgeList;
        }

        public List<MultipleChoice> getMultipleChoiceList() {
            return multipleChoiceList;
        }

        public void setMultipleChoiceList(List<MultipleChoice> multipleChoiceList) {
            this.multipleChoiceList = multipleChoiceList;
        }

        public List<Paper> getPaperList() {
            return paperList;
        }

        public void setPaperList(List<Paper> paperList) {
            this.paperList = paperList;
        }

        public List<SubjectRecord> getSubjectRecordList() {
            return subjectRecordList;
        }

        public void setSubjectRecordList(List<SubjectRecord> subjectRecordList) {
            this.subjectRecordList = subjectRecordList;
        }


    @Override
    public String toString() {
        return "Course{" +
                "fillBlankList=" + fillBlankList +" \r\n" +
                ", judgeList=" + judgeList +" \r\n" +
                ", multipleChoiceList=" + multipleChoiceList +" \r\n" +
                ", paperList=" + paperList +" \r\n" +
                ", subjectRecordList=" + subjectRecordList +" \r\n" +
                '}';
    }
}
