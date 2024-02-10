package Com.First.ecommerce.order.Model;

import Com.First.ecommerce.address.Address;
import Com.First.ecommerce.shipment.Shipment;
import Com.First.ecommerce.user.domain.UserDetail;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    private UserDetail userDetailId;
    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItemList;
    @ManyToOne
    private Address shippingAddress;
    @OneToOne
    private Shipment shipmentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String orderStatus;

    public Order() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UserDetail getUserId() {
        return userDetailId;
    }

    public void setUserId(UserDetail userId) {
        this.userDetailId = userId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Shipment getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Shipment shipmentId) {
        this.shipmentId = shipmentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
