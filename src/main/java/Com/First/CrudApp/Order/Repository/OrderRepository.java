package Com.First.CrudApp.Order.Repository;

import Com.First.CrudApp.Order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
