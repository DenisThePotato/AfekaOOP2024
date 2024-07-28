package DenisMogilevsky;

public class Customer extends User {
    private Address address = new Address();
    private Order currentOrder = new Order();
    private OrderHistory orderHistory = new OrderHistory();

    public Customer(){
    }

    public Customer(String name, String password){
        this.name = name;
        this.password = password;
    }

    public Customer(Customer customer){
        this.name = customer.name;
        this.password = customer.password;
        this.address = new Address(customer.address);
        this.orderHistory = new OrderHistory(customer.orderHistory);
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

    public void currentOrderToHistory() throws IllegalArgumentException{
        if(this.currentOrder.getCurrentProductIndex() == 0){
            throw new IllegalArgumentException("Can't finalize the order because the cart is empty.");
        }
        this.orderHistory.addToOrderHistory(currentOrder);
        this.currentOrder = new Order();
    }

    public void orderHistoryToCurrentOrder(int orderNumber) throws IllegalArgumentException{
        if(orderNumber < 0 || orderNumber > this.orderHistory.getCurrentOrderHistoryIndex() - 1){
            throw new IllegalArgumentException("Invalid order number.");
        }
        this.currentOrder = new Order(this.orderHistory.getPurchase()[orderNumber]);
    }

    @Override
    public String toString() {
        return(name + ". " + address.toString());
    }
}