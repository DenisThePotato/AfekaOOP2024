package DenisMogilevsky_ShayKrinizky;

public class Customer extends User {
    private Address address = new Address();
    private Order currentOrder = new Order();
    private OrderHistory orderHistory = new OrderHistory();

    public Customer(String name, String password){
        this.name = name;
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void addProductToCurrentOrder(Product product){
        currentOrder.addProduct(product);
    }

    public void currentOrderToHistory(){
        this.orderHistory.addToOrderHistory(currentOrder);
        this.currentOrder = new Order();
    }

    @Override
    public String toString() {
        return(name + ", customer.");
    }
}