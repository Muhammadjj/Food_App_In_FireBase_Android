package org.pk.cas.fastfood1;

public class MyCartRecyclerViewModelclass {

    int  deleteicon;
    String mycartfoodname;
    String mycartfoodprice;
    String Bigimage;
    String mycartfoodQuantity;
    String DocumentId;

    public MyCartRecyclerViewModelclass() {
    }

    public MyCartRecyclerViewModelclass(int deleteicon, String mycartfoodname, String mycartfoodprice, String bigimage, String mycartfoodQuantity, String documentId) {
        this.deleteicon = deleteicon;
        this.mycartfoodname = mycartfoodname;
        this.mycartfoodprice = mycartfoodprice;
        Bigimage = bigimage;
        this.mycartfoodQuantity = mycartfoodQuantity;
        DocumentId = documentId;
    }

    public int getDeleteicon() {
        return deleteicon;
    }

    public void setDeleteicon(int deleteicon) {
        this.deleteicon = deleteicon;
    }

    public String getMycartfoodname() {
        return mycartfoodname;
    }

    public void setMycartfoodname(String mycartfoodname) {
        this.mycartfoodname = mycartfoodname;
    }

    public String getMycartfoodprice() {
        return mycartfoodprice;
    }

    public void setMycartfoodprice(String mycartfoodprice) {
        this.mycartfoodprice = mycartfoodprice;
    }

    public String getBigimage() {
        return Bigimage;
    }

    public void setBigimage(String bigimage) {
        Bigimage = bigimage;
    }

    public String getMycartfoodQuantity() {
        return mycartfoodQuantity;
    }

    public void setMycartfoodQuantity(String mycartfoodQuantity) {
        this.mycartfoodQuantity = mycartfoodQuantity;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }
}
