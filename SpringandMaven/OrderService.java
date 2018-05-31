package file.webshop.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import file.webshop.domain.Item;
import file.webshop.domain.Order;
import file.webshop.domain.OrderItem;
import file.webshop.domain.ShoppingCart;

import file.webshop.repository.ItemRepository;
import file.webshop.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Transactional
    public void placeOrder(String name, String address) {

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);

        List<OrderItem> items = new ArrayList<>();
        for (Item item : shoppingCart.getItems().keySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(itemRepository.findOne(item.getId()));
            orderItem.setItemCount(shoppingCart.getItems().get(item));

            items.add(orderItem);
        }

        order.setOrderItems(items);

        orderRepository.save(order);
        shoppingCart.getItems().clear();
    }
}
