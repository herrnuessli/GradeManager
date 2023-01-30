package ch.bbw.grademanager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ViewModel {
    //Attributes
    private File file;
    private Float newGrade;
    private Float changeGrade;
    private int changeGradeIndex;
    private String changeGradeSubject;
    private String newGradeSubject;
    public String getNewGradeSubject() {
        return newGradeSubject;
    }
    public void setNewGradeSubject(String newGradeSubject) {
        this.newGradeSubject = newGradeSubject;
    }
    public void setChangeSubjectIndexGrade(String subject, int index, Float grade){
        this.changeGradeSubject = subject;
        this.changeGradeIndex = index;
        this.changeGrade = grade;
    }
    public Float getChangeGrade() {
        return changeGrade;
    }
    public void setChangeGrade(Float changeGrade) {
        this.changeGrade = changeGrade;
    }
    public int getChangeGradeIndex() {
        return changeGradeIndex;
    }
    public void setChangeGradeIndex(int changeGradeIndex) {
        this.changeGradeIndex = changeGradeIndex;
    }
    public String getChangeGradeSubject() {
        return changeGradeSubject;
    }
    public void setChangeGradeSubject(String changeGradeSubject) {
        this.changeGradeSubject = changeGradeSubject;
    }
    public List<Subject> getSubject() {
        return subjects;
    }
    public void setSubjects(List<Subject> subject) {
        this.subjects = subject;
    }
    private List<Subject> subjects;
    public StringBuilder getSubjectInfo(){
        if(this.subjects != null) {
            StringBuilder output = new StringBuilder();
            for (Subject subject : this.subjects){
                output.append(subject.getName() +" Noten: "+ subject.gradesToString() + " Durchschnitt: " + subject.calculateAverage());
                output.append("<br>");
            }
            return output;
        }
        else {
            return new StringBuilder("");
        }

    }
    private String name;
    public String getNewSubjectName() {
        return newSubjectName;
    }
    public void setNewSubjectName(String newSubjectName) {
        this.newSubjectName = newSubjectName;
    }
    private String newSubjectName;
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public Float getNewGrade() {
        return newGrade;
    }
    public void setNewGrade(Float newGrade) {
        this.newGrade = newGrade;
    }
    public void setNewSubjectGrade(String subject, Float grade){
        this.newGradeSubject = subject;
        this.newGrade = grade;
    }
}
