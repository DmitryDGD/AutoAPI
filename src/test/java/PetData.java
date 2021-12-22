import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetData {

    // создаём класс под JSON
    private Integer id;
   // private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private STATUS_TYPE status;

    public enum STATUS_TYPE {
        available, pending, sold
    }





    // сеттеры и геттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public STATUS_TYPE getStatus_type() {
        return status;
    }

    public void setStatus_type(STATUS_TYPE status) {
        this.status = status;
    }



}




