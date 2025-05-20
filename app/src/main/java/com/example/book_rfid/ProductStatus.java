package com.example.book_rfid;

public class ProductStatus {
    public String productTitle;
    public boolean isBoxFound;
    public boolean isLeftFound;
    public boolean isRightFound;

    public ProductStatus(String productTitle, boolean isBoxFound, boolean isLeftFound, boolean isRightFound) {
        this.productTitle = productTitle;
        this.isBoxFound = isBoxFound;
        this.isLeftFound = isLeftFound;
        this.isRightFound = isRightFound;
    }
}
