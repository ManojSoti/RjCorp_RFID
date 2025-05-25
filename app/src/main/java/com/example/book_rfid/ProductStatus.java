package com.example.book_rfid;

public class ProductStatus {
    public String productTitle;
    public String boxEPC;
    public String leftEPC;
    public String rightEPC;

    public boolean isBoxFound;
    public boolean isLeftFound;
    public boolean isRightFound;

    // Constructor for reading from Excel
    public ProductStatus(String title, String box, String left, String right) {
        this.productTitle = title;
        this.boxEPC = box;
        this.leftEPC = left;
        this.rightEPC = right;
        this.isBoxFound = false;
        this.isLeftFound = false;
        this.isRightFound = false;
    }

    // Constructor for found tags
    public ProductStatus(String title, boolean isBoxFound, boolean isLeftFound, boolean isRightFound) {
        this.productTitle = title;
        this.isBoxFound = isBoxFound;
        this.isLeftFound = isLeftFound;
        this.isRightFound = isRightFound;
    }
}
