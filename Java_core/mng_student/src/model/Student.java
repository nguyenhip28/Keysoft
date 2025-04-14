package model;

public class Student {
    private int id;
    private String studentID;
    private String studentName;
    private int age;
    private String address;
    private int avgScore;

    public Student() {
    }

    public Student(int id, String studentID, String studentName, int age, String address, int avgScore) {
        this.id = id;
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.address = address;
        this.avgScore = avgScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { 
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "| ID: " + id + " | StudentID: " + studentID + " | StudentName: " + studentName + " | Age: " + age
                + " | Address: " + address + " | AvgScore: " + avgScore + " |";
    }

    public String toFileString() {
        return id + "," + studentID + "," + studentName + "," + age + "," + address + "," + avgScore;
    }

    public static Student parse(String line) {
        String[] parts = line.split(",");
        return new Student(
            Integer.parseInt(parts[0]), 
            parts[1],
            parts[2],
            Integer.parseInt(parts[3]),
            parts[4],
            Integer.parseInt(parts[5])
        );
    }
}