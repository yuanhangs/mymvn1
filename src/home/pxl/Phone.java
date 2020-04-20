package home.pxl;

/**
 * 手机类
 */

public class Phone {

    private String  Brand;
    private String Type;

    public Phone(String brand, String type) {
        Brand = brand;
        Type = type;
    }

    public Phone() {

    }

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
