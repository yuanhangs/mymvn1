package home.sgj.homework2;

public class Test {
    public static void main(String[] args) {
        //业务对象
        StudentStu stu = new StudentStu();
        stu.showXml();  //显示所有的数
        //删除
        stu.del("11");
        Student student = new Student("03","阿强",37);
        //修改
        stu.update(student);
        //输出
        stu.showXml();
    }
}
