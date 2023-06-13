package my.quize.myquize.Modal;

public class ItemMyOrderModal {
    String select,point,result,amount;

    public ItemMyOrderModal(String select, String point, String result, String amount) {
        this.select = select;
        this.point = point;
        this.result = result;
        this.amount = amount;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
