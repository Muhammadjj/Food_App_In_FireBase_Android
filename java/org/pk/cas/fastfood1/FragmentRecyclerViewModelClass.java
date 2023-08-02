package org.pk.cas.fastfood1;

public class FragmentRecyclerViewModelClass {

    String Name, PKR, image;

//    Default constructor
    public FragmentRecyclerViewModelClass() {

    }

//    Parametrized constructor
    public FragmentRecyclerViewModelClass(String name, String PKR, String image) {
        Name = name;
        this.PKR = PKR;
        this.image = image;
    }


//    Getter and Setter
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPKR() {
        return PKR;
    }

    public void setPKR(String PKR) {
        this.PKR = PKR;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
