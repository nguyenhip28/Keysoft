package model;

public class Product {
    private int id;
    private String productID;
    private String productName;
    private String productType;
    private int quantity;
    private int price;



    public Product() {
    }

    public Product(int id, String productID, String productName, String productType, int quantity, int price) {
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative. Setting quantity to 0.");
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
            if (price < 0) {
                System.out.println("Price cannot be negative. Setting price to 0.");
                this.price = 0;
            } else {
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';}

    public String toFileString() {
        return id + "," + productID + "," + productName + "," + productType + "," + quantity + "," + price;
    }

    public static Product parse(String line) {
        String[] parts = line.split(",");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[5])
        );
    }
}
