package models;



public class Clothes {


    private String clotheImage;
    private String shopName,price, address;

    public Clothes() {

    }

    public Clothes(String clotheImage, String shopName, String price, String address) {
        this.clotheImage = clotheImage;
        this.shopName = shopName;
        this.price = price;
        this.address = address;
    }

    public void setClotheImage(String clotheImage) {
        this.clotheImage = clotheImage;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClotheImage() {
        return clotheImage;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }







}

