package home.sgj.homework1;

public class Test {
    public static void main(String[] args) {
        //业务对象
        PhonePe pe = new PhonePe();
        pe.showXml();
        pe.del("华为");
        Phone phone = new Phone("苹果","11");
        pe.update(phone);
        pe.showXml();
    }
}
