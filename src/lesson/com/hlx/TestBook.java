package lesson.com.hlx;

/**
 * @ClassName TestBook
 * @Description: TODO
 * @Author 44401
 * @Date 2020/2/10
 * @Version V1.0
 **/
public class TestBook {
    public static void main(String[] args) {
        BookBiz biz =new BookBiz();

        biz.show();


        Book book =new Book("J001","JAVA",78.9);
//        biz.add(book);

//        biz.del("J001");

        book =new Book("T1001","小说",35.8);
        biz.update(book);

        biz.show();


    }
}
