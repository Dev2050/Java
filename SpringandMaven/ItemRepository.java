package file.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import file.webshop.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
