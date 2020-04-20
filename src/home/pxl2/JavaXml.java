package home.pxl2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 *
 * 业务逻辑类
 */

public class JavaXml {

    //封装解析方法

    public Document getDocument() {

        //1.创建解析器工厂对象
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        // 2.解析器工厂对象创建解析器对象
        DocumentBuilder builder = null;
        try {
            builder = fac.newDocumentBuilder();
            //获取文档对象
            return builder.parse("student.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }


    //显示方法
    public void showStudent(){
        //获取文档对象
        Document document = getDocument();

        //获取student节点

        NodeList student = document.getElementsByTagName("student");

        //菜单栏
        System.out.println("编号\t\t姓名\t\t年龄");
        //循环
        for (int i = 0; i <student.getLength() ; i++) {

            //创建字符串
            StringBuffer str = new StringBuffer();

            //获取每个项
            Element element = (Element) student.item(i);

            //存入字符串中
            str.append(element.getAttribute("sn")+"\t\t\t");

            //把子节点name的内容存入字符串中
            str.append(document.getElementsByTagName("name").item(i).getFirstChild().getNodeValue()+"\t\t");

            //把子节点name的内容存入字符串中
            str.append(document.getElementsByTagName("age").item(i).getFirstChild().getNodeValue()+"\t\t");

            System.out.println(str.toString());
        }

    }

//添加方法

    public void addStudent(Student student){

        //获取文档对象
        Document document = getDocument();

        //创建父节点
        Element stu = document.createElement("student");
        //创建子节点
        Element name = document.createElement("name");
        //创建子节点
        Element age = document.createElement("age");

        //创建属性sn
        Attr sn = document.createAttribute("sn");
        //设置值
        sn.setValue(student.getSn());

        //添加name和age的文本值
        name.setTextContent(student.getName());
        age.setTextContent(student.getAge()+"");

        //将name和age放到父节点下
        stu.appendChild(name);
        stu.appendChild(age);
        //将sn放到父节点内
        stu.setAttributeNode(sn);

        //把student添加到根节点下
        document.getDocumentElement().appendChild(stu);

        //调用存入方法
        savaXml(new File("student.xml"),document);

        //提示语句
        System.out.println("添加成功！");

    }

    //删除方法
    public void delete(String id){

        //获取文档对象
        Document document = getDocument();
        //获取节点
        NodeList student = document.getElementsByTagName("student");
        //循环
        for (int i = 0; i <student.getLength() ; i++) {

            //得到每一项
            Element element = (Element) student.item(i);

            //得到sn的属性值
            String id1 = element.getAttribute("sn");

            //判断sn的值
            if (id.equals(id1)){
                //进行删除
                document.getDocumentElement().removeChild(element);
                //结束
                break;
            }

        }
        //存入xml
        savaXml(new File("student.xml"),document);

        //提示语句
        System.out.println("删除成功！");
    }

    //修改方法
    public void update(Student student){

        //获取文档对象
        Document document = getDocument();
        //获取节点
        NodeList student1 = document.getElementsByTagName("student");
        //循环
        for (int i = 0; i <student1.getLength() ; i++) {

            //得到每一项
            Element element = (Element) student1.item(i);

            //得到sn的属性值
            String id1 = element.getAttribute("sn");

            //判断sn的值
            if (student.getSn().equals(id1)){
              //进行替换
                element.getElementsByTagName("name").item(0).getFirstChild().setTextContent(student.getName());
                element.getElementsByTagName("age").item(0).getFirstChild().setTextContent(student.getAge()+"");
                //结束
                break;
            }
        }
        //存入xml
        savaXml(new File("student.xml"),document);

        //提示语句
        System.out.println("替换成功！");
    }


    //存入xml方法
    public  void  savaXml(File fileName, Document document){

        //获取TransformerFacory对象
        TransformerFactory fac = TransformerFactory.newInstance();

        //创建Transformer对象

        try {
            Transformer transformer = fac.newTransformer();

            //创建DOMSource对象
            DOMSource source = new DOMSource(document);

            //设置输出类型属性格式
            transformer.setOutputProperty("indent","yes");

            //创建StreamResult对象
            StreamResult result = new StreamResult(new FileOutputStream(fileName));

            //将XML保存到指定文件中
            transformer.transform(source,result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
