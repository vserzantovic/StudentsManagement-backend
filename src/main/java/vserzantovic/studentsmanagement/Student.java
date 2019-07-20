package vserzantovic.studentsmanagement;

public class Student {

    private String name;

    private float points;

    public Student(String name, long points) {
        this.name = name;
        this.points = points;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float  getPoints() {
        return points;
    }

    public void setPoints(float  points) {
        this.points = points;
    }
}
