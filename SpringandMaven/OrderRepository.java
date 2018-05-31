package file.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import file.webshop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
