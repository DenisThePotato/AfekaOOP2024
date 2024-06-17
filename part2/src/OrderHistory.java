public class OrderHistory implements ResizableArrayUtility {
    private Order[] orderHistory = new Order[2];

    public Order[] getPurchase() {
        return orderHistory;
    }

    public void addToOrderHistory(Order order){
        order.setPurchaseDate();
        if(ResizableArrayUtility.bIsArrayFull(orderHistory)){
            ResizableArrayUtility.doubleArraySize(orderHistory);
        }
        for(Order currentOrder: orderHistory){
            if(currentOrder == null){
                currentOrder = order;
            }
        }
    }

    public void printOrderHistory(){
        int index = 1;
        for(Order order: orderHistory){
            if(order == null){
                return;
            }
            System.out.println("Order number " + index + ":");
            order.printOrder();
            index++;
        }
    }
}
