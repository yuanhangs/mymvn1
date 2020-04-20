package home.sgj.homework1;

public class Phone {
    //品牌
    private String Brand;
    //型号
    private String Type;
    //无参
    public Phone() {
    }
    //有参
    public Phone(String brand, String type) {
        Brand = brand;
        Type = type;
    }
    //封装
    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
