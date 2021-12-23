package PoJo;

public class OrderData {

   // создаём класс под JSON
   private Integer id;
   private Integer petId;
   private Integer quantity;
   private String  shipDate;
   private STATUS_TYPE status;
   private boolean complete;


    public enum STATUS_TYPE {
        placed, approved, delivered
    }


    // сеттеры и геттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String  getShipDate() {
        return shipDate;
    }

    public void setShipDate(String  shipDate) {
        this.shipDate = shipDate;
    }

    public STATUS_TYPE getStatus_type() {
        return status;
    }

    public void setStatus_type(STATUS_TYPE status) {
        this.status = status;
    }

    public boolean getCompleteType() {
        return complete;
    }

    public void setCompleteType(boolean complete) {
        this.complete = complete;
    }


}



