package droidmentor.onboardingdemo.OrderPackage;

public class OrderItem {
    private byte[] imageOrderItem;
    private String titleOrder;
    private String descriptionOrder;

    public OrderItem(byte[] imageOrderItem, String titleOrder, String descriptionOrder) {
        this.imageOrderItem = imageOrderItem;
        this.titleOrder = titleOrder;
        this.descriptionOrder = descriptionOrder;
    }

    public byte[] getImageOrderItem() {
        return imageOrderItem;
    }

    public void setImageOrderItem(byte[] imageOrderItem) {
        this.imageOrderItem = imageOrderItem;
    }

    public String getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(String titleOrder) {
        this.titleOrder = titleOrder;
    }

    public String getDescriptionOrder() {
        return descriptionOrder;
    }

    public void setDescriptionOrder(String descriptionOrder) {
        this.descriptionOrder = descriptionOrder;
    }
}
