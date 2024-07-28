package DenisMogilevsky;

public class OrderHistory implements ResizableArrayUtility<Order> {
    private Order[] orderHistory = new Order[2];
    private int currentOrderHistoryIndex = 0;

    public Order[] getPurchase() {
        return orderHistory;
    }

    public OrderHistory(){
    }

    public OrderHistory(OrderHistory orderHistory){
        this.orderHistory = orderHistory.orderHistory.clone();
        this.currentOrderHistoryIndex = orderHistory.currentOrderHistoryIndex;
        Order[] newOrderHistory = new Order[orderHistory.orderHistory.length];
        for(int i = 0; i < newOrderHistory.length; i++){
            if(orderHistory.orderHistory[i] == null){
                break;
            }
            newOrderHistory[i] = new Order(orderHistory.orderHistory[i]);
        }
        this.orderHistory = newOrderHistory;
    }

    public int getCurrentOrderHistoryIndex() {
        return currentOrderHistoryIndex;
    }

    public void addToOrderHistory(Order order){
        order.setPurchaseDate();
        if(bIsArrayFull(orderHistory)){
            orderHistory = doubleArraySize(orderHistory);
        }
        orderHistory[currentOrderHistoryIndex] = order;
        currentOrderHistoryIndex++;
    }

    public void printOrderHistory(){
        int index = 1;
        for(Order order: orderHistory){
            if(order == null){
                return;
            }
            System.out.print("Order number " + index);
            System.out.println(" (Date of purchase: " + order.getPurchaseDate().toString() + "):");
            order.printOrder();
            index++;
        }
    }

    @Override
    public boolean bIsArrayFull(Order[] arr) {
        return (currentOrderHistoryIndex >= orderHistory.length);
    }

    @Override
    public Order[] doubleArraySize(Order[] arr) {
        Order[] newArr = new Order[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }
}
