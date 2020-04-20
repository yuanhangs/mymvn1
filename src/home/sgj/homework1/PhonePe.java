package home.sgj.homework1;

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
public class PhonePe {
    public Document getDocument(){
        //1.实例化DOM解析器工厂对象（DocumentBuilderFactory）对象
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        //2.从解析器工厂对象获得解析器对象，即DocumentBuilder对象
        DocumentBuilder db= null;
        try {
            db = dbf.newDocumentBuilder();

            //获得文档对象
            return db.parse("Phone.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Phone pe){
        //1>获得文档对象
        Document document =getDocument();
        //2>获得Brand节点孩子
        NodeList list = document.getElementsByTagName("Brand");
        //3>循环
        for (int i = 0; i <list.getLength() ; i++) {
            //4>获得每一个
            Element ele = (Element) list.item(i);

            //5>获得属性name
            String strId =ele.getAttribute("name");

            //6>判断是否相等
            if (strId .equals(pe.getBrand())) {
                //修改数////
                NodeList brand=ele.getElementsByTagName("Type");
                Element element=(Element)brand.item(0);
                //修改数/////////////////
                element.setAttribute("name",pe.getType());
                ///////////////////
                //退出
                break;
            }
        }
        //写入
        saveToXml(new File("Phone.xml"),document);
        System.out.println("更新成功!");
    }
    //删除
    public void del(String name){
        //1>获得文档对象
        Document document =getDocument();
        //2>获得Brand节点
        NodeList list =document.getElementsByTagName("Brand");
        //3>循环
        for (int i = 0; i <list.getLength() ; i++) {
            //获得每一个
            Element ele = (Element) list.item(i);
            //获得属性
            String strId =ele.getAttribute("name");
            //判断是否相等
            if (name.equals(strId)) {
                //删除
                document.getDocumentElement().removeChild(ele);
                //退出
                break;
            }
        }
        //写入
        saveToXml(new File("Phone.xml"),document);
        System.out.println("删除成功!");
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
    public void showXml(){
        //获得文档对象
        Document document=getDocument();
        //获得父节点孩子
        NodeList list=document.getElementsByTagName("Brand");
        //循环
        for (int i = 0; i <list.getLength() ; i++) {
            //创建字符串对象
            StringBuffer buffer=new StringBuffer();
            //获得每一项节点（强制类型转换Element）
            Element ele=(Element)list.item(i);
            //获取第i个Brand元素的name属性值
            String value=ele.getAttribute("name");
            //得到Brand节点的所有子节点的列表信息
            NodeList typeList=ele.getChildNodes();
            //内层循环型号信息
            for (int j = 0; j <typeList.getLength() ; j++) {
                //获取第i个Type元素信息
                Node type = typeList.item(j);
                //判断是否是元素节点，若为true，则表示是想要的元素
                if (type.getNodeType()==Node.ELEMENT_NODE) {
                    //将型号转换成标签元素对象（Element）
                    Element types = (Element) type;
                    //获取第i个Type元素的name属性值
                    String typeValue = types.getAttribute("name");
                    //输出手机品牌与型号
                    System.out.println("手机品牌：" + value + "\t手机型号：" + typeValue);
                }
            }
        }
    }
}
