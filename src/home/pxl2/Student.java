package home.pxl2;

/***
 * 学生类
 */
public class Student {

    private String sn;
    private String name;
    private int age;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String sn, String name, int age) {

        this.sn = sn;
        this.name = name;
        this.age = age;
    }

    public Student() {

    }
}
