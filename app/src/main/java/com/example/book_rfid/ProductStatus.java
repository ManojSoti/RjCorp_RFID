package com.example.book_rfid;

public class ProductStatus {
    public String productTitle;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getBoxEPC() {
        return boxEPC;
    }

    public void setBoxEPC(String boxEPC) {
        this.boxEPC = boxEPC;
    }

    public String getLeftEPC() {
        return leftEPC;
    }

    public void setLeftEPC(String leftEPC) {
        this.leftEPC = leftEPC;
    }

    public String getRightEPC() {
        return rightEPC;
    }

    public void setRightEPC(String rightEPC) {
        this.rightEPC = rightEPC;
    }

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
