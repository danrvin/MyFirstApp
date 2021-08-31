package sample.objects;

public class Item {
    private String nameItem;
    private String descriptionItem;

    public Item(String nameItem, String descriptionItem) {
        this.nameItem = nameItem;
        this.descriptionItem = descriptionItem;
    }

    public Item() {
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }
}
