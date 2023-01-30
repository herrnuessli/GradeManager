package ch.bbw.grademanager;

public class Subject {
    private String name;
    private Float[] grades;

    public String gradesToString() {
        String gradesToString = "";
        for(int i = 0; i < this.grades.length; i++){
            gradesToString += this.grades[i];
            gradesToString += " ";
        }
        return gradesToString;
    }

    public Float[] getGrades() {
        return grades;
    }

    public Float calculateAverage(){
        Float sum = 0.0F;
        for (Float value : this.grades) {
            sum += value;
        }
        return Math.round((sum / this.grades.length) * 10F) / 10F;
    }

    public void setGrades(Float[] grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
