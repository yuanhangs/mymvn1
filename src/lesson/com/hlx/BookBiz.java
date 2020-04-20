package lesson.com.hlx;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * @ClassName BookBiz
 * @Description: TODO
 * @Author 44401
 * @Date 2020/2/10
 * @Version V1.0
 **/
public class BookBiz {
    //xml文件
    private  String fileName="books.xml";

    /**
     * 获得文档对象
     * @return
     */
    public Document getDocument(){
        //获取读取对象
        SAXReader reader=new SAXReader();
        try {
            // 读取XML文件,获得document对象
            Document  document= reader.read(new File(fileName));
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 添加
     * @param book
     */
    public void add(Book book){
        //1>获得文档对象
        Document document=getDocument();

        //2>根节点
        Element eleRoot=document.getRootElement();

//  <book id="T1001">
//    <name>时尚大方</name>
//    <price>28.9</price>
//  </book>
        //3>依次添加
          //book节点再添加属性 <book id="T1001">
        Element bookEle=eleRoot.addElement("book").addAttribute("id",book.getId());
          // <name>时尚大方</name>
        bookEle.addElement("name").addText(book.getName());
//          <price>28.9</price>
        bookEle.addElement("price").addText(book.getPrice()+"");

        //写入xml文件
        saveToXml(document);

        System.out.println("添加成功！");
    }

    /**
     * 写入xml文件
     * @param document
     */
    private void saveToXml(Document document) {
        try {

            //输出格式,换行+缩进；
            OutputFormat format =OutputFormat.createPrettyPrint();

            //写入对象
            XMLWriter  writer=new XMLWriter(new FileOutputStream(fileName),format);

            //写入
            writer.write(document);

            //关闭
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 删除数据，
     * @param id  根据ID删除
     */
     public void del(String id){

        //1.文档对象
        Document document =getDocument();

        //2.根节点对象
        Element eleRoot =document.getRootElement();

        //3.获得books的孩子book。。。
        List<Element> list = eleRoot.elements();
        for (Element ele:
                list ) {
            //判断  <book id="J001">
            if(ele.attributeValue("id").equals(id)){
                //删除
                eleRoot.remove(ele);
                //
                break;
            }
        }
        //写入xml文件
        saveToXml(document);

        System.out.println("删除成功！");

    }

    /**
     * 更新一条数据
     * @param book
     */
    public void update(Book book){

        //1.文档对象
        Document document =getDocument();

        //2.根节点对象
        Element eleRoot =document.getRootElement();

        //3.获得books的孩子book。。。
        List<Element> list = eleRoot.elements();
        for (Element ele:
                list ) {
            //判断  <book id="J001">
            if(ele.attributeValue("id").equals(book.getId())){

               //更新 <name>时尚大方</name>
                ele.element("name").setText(book.getName());
                //   更新 <price>28.9</price>
                ele.element("price").setText(book.getPrice()+"");

                break;
            }
        }
        //写入xml文件
        saveToXml(document);

        System.out.println("更新成功！");

    }



    /**
     * 显示xml
     */
    public void show(){
       //1>文档对象
        Document  document=getDocument();

        //2>获得根节点
        Element eleRoot= document.getRootElement();

        //3>books下的孩子节点book....
       List<Element> list=eleRoot.elements();

       //4>循环每一个book数据
        for (Element ele:
            list ) {
            //  <book id="T11">
            //    <name>thinking in java</name>
            //    <price>85.5</price>
            //  </book>
            //字符串保存数据
            StringBuffer buffer=new StringBuffer();

            //获得属性数据<book id="T11">
            buffer.append(ele.attributeValue("id")+"\t");

            //获得元素值 <name>thinking in java</name>
            buffer.append(ele.elementTextTrim("name")+"\t");

            //获得元素值<price>85.5</price>
            buffer.append(ele.elementTextTrim("price"));

            System.out.println(buffer);

        }
    }

}
