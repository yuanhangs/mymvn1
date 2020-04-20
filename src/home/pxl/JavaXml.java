package home.pxl;



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

/**
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
            return builder.parse("Phone.xml");
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
    public void showPhone(){
        //获取文档对象
        Document document = getDocument();

        //Phone标签
        NodeList brand = document.getElementsByTagName("Brand");

        //循环
        for (int i = 0; i <brand.getLength() ; i++) {

            //存结果
            StringBuffer str = new StringBuffer();
            //获得每个节点
            Element element = (Element)brand.item(i);
            //得到name
            String name =element.getAttribute("name");

            str.append(name+"\t\n");

            //获取brand的子节点
            NodeList brand1 =element.getElementsByTagName("Type");

            for (int j = 0; j <brand1.getLength() ; j++) {

                //获得每个节点
                Element element1 = (Element)brand1.item(j);

                //得到name
                String name1 =element1.getAttribute("name");

                str.append(name1+"\t\n");
            }
            System.out.println(str.toString());

        }
    }

    //添加的方法
    public void add(Phone phone){

        //获取文档对象
        Document document = getDocument();

        //创建父节点
        Element brand = document.createElement("Brand");
        //创建子节点
        Element type = document.createElement("Type");

        //创建属性name
        Attr name = document.createAttribute("name");
        Attr name1 = document.createAttribute("name");
        //给name值赋值
        name.setValue(phone.getBrand());
        name1.setValue(phone.getType());

        //将type添加到父节点下
        brand.appendChild(type);
        //将name值添加到父节点和子节点下
        brand.setAttributeNode(name);
        type.setAttributeNode(name1);

        //把父节点添加到根节点下
        document.getDocumentElement().appendChild(brand);

        //调用存入方法
        savaXml(new File("Phone.xml"),document);

        //提示语句
        System.out.println("添加成功！");
    }

    //删除方法
    public void delete(String name){

        //获取文档对象
        Document document = getDocument();
        //获取节点
        NodeList brand = document.getElementsByTagName("Brand");
        //循环
        for (int i = 0; i <brand.getLength() ; i++) {

            //得到每一项
            Element element = (Element) brand.item(i);

            //得到name的属性值
            String names = element.getAttribute("name");

            //判断sn的值
            if (names.equals(name)){
                //进行删除
                document.getDocumentElement().removeChild(element);
                //结束
                break;
            }

        }
        //存入xml
        savaXml(new File("Phone.xml"),document);

        //提示语句
        System.out.println("删除成功！");
    }

    //修改方法
    public void update(Phone phone){

        //获取文档对象
        Document document = getDocument();
        //获取节点
        NodeList brand = document.getElementsByTagName("Brand");
        //循环
        for (int i = 0; i <brand.getLength() ; i++) {

            //得到每一项
            Element element = (Element) brand.item(i);

            //得到name的属性值
            String name = element.getAttribute("name");

            //判断name的值
            if (phone.getBrand().equals(name)){

                //获取节点
                NodeList brand1 =element.getElementsByTagName("Type");

                //得到每一项
               Element element1= (Element) brand1.item(i);

                //进行替换
                 element1.setAttribute("name",phone.getType());

                //结束
                break;
            }
        }
        //存入xml
        savaXml(new File("Phone.xml"),document);

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
