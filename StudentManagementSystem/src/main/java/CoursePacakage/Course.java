package CoursePacakage;

public class Course {
    private String code;
    private String name;
    private String classroom;

    public Course(String code, String classroom, String name) {
        this.code = code;
        this.name = name;
        this.classroom = classroom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

