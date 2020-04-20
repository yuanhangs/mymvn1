package home.pxl2;

/***
 *
 * 测试类
 */
public class TestStudent {

    public static void main(String[] args) {

        //实例化业务逻辑类
        JavaXml javaXml = new JavaXml();

        //调用显示方法
       javaXml.showStudent();

     //创建并实例化学生对象
       Student stu = new Student("12", "皮皮虾", 35);

        //调用添加方法
       javaXml.addStudent(stu);

        //调用删除方法
        javaXml.delete("12");

        //创建并实例化对象
        Student stu1 = new Student("03", "你妹", 10);
        //调用替换方法
        javaXml.update(stu1);
        //调用显示方法
        javaXml.showStudent();
    }
}
