package home.pxl;

/**
 * 测试类
 */

public class TestPhone {

    public static void main(String[] args) {

        //实例化业务逻辑类
        JavaXml javaXml = new JavaXml();

        //调用显示方法
      javaXml.showPhone();

        //实例化并初始化Phone对象

      Phone phone = new Phone("OPPO", "R17");

        //调用添加方法
       javaXml.add(phone);

        //调用删除方法
        javaXml.delete("华为");
        //创建并实例化Phone对象
        Phone phone1 = new Phone("苹果", "iPhone11");
        //调用替换方法
        javaXml.update(phone1);

        //调用显示方法
        javaXml.showPhone();
    }
}
