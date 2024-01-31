package Com.First.ecommerce.order.Repository;

import Com.First.ecommerce.order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
