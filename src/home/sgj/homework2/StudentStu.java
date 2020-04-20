package home.sgj.homework2;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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

public class StudentStu {
    public Document getDocument() {
        //1.解析器工厂对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.获得解析器对象
        DocumentBuilder db = null;
        try {
            db = factory.newDocumentBuilder();
            //3.获得文档对象
            return db.parse("student.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //删除
    public void del(String sn){
        //1>获得文档对象
        Document document =getDocument();

        //2>获得student节点
        NodeList list =document.getElementsByTagName("student");

        //3>循环
        for (int i = 0; i <list.getLength() ; i++) {

            //获得每一个
            Element ele = (Element) list.item(i);

            //获得属性
            String strId =ele.getAttribute("sn");

            //判断是否相等
            if (sn.equals(strId)) {
                //删除
                document.getDocumentElement().removeChild(ele);
                //退出
                break;

            }
        }

        //写入
        saveToXml(new File("student.xml"),document);
        System.out.println("删除成功!");

    }
    //修改
    public void update(Student stu){

        //1>获得文档对象
        Document document =getDocument();

        //2>获得student节点孩子
        NodeList list = document.getElementsByTagName("student");

        //3>循环
        for (int i = 0; i <list.getLength() ; i++) {
            //4>获得每一个
            Element ele = (Element) list.item(i);

            //5>获得属性sn
            String strId =ele.getAttribute("sn");

            //6>判断是否相等
            if (strId .equals(stu.getSn())) {
                //修改数
                ele.getElementsByTagName("name").item(0).getFirstChild().setTextContent(stu.getName());
                ele.getElementsByTagName("age").item(0).getFirstChild().setTextContent(stu.getAge()+"");
                //退出
                break;
            }

        }
        //写入
        saveToXml(new File("student.xml"),document);
        System.out.println("更新成功!");

    }
    public void saveToXml(File fileName, Document document) {

//        1>获得TransformerFactory对象
        TransformerFactory factory = TransformerFactory.newInstance();
//       2>创建Transformer对象
        try {
            Transformer transformer = factory.newTransformer();

            //    3)创建DOMSource对象
            DOMSource source = new DOMSource(document);

            //  4) 设置输出属性格式 /可以换行
            transformer.setOutputProperty("indent","yes");
            ///////////////////////////

            //  5)创建StreamResult对象
            StreamResult result = new StreamResult(new FileOutputStream(fileName));

            // 6) 将XML保存到指定文件中
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void showXml() {
        //1.获得文档对象
        Document document = getDocument();

        //4.获得student节点孩子
        NodeList list = document.getElementsByTagName("student");
        //循环
        for (int i = 0; i < list.getLength(); i++) {
            //字符串对象
            StringBuffer buffer = new StringBuffer();
            //每个项 (强制类型转换Element)
            Element ele = (Element) list.item(i);
            //存入字符串对象中
            buffer.append(ele.getAttribute("sn") + "\t");
            buffer.append(document.getElementsByTagName("name").item(i).getFirstChild().getNodeValue() + "\t");
            buffer.append(document.getElementsByTagName("age").item(i).getFirstChild().getNodeValue());
            System.out.println(buffer.toString());
        }

    }

}
