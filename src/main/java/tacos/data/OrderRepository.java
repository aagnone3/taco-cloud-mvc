package tacos.data;

import java.util.Date;
import java.util.List;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    // Spring Data will automatically know how to implement this method
    // Syntax for method name:
    //   <verb> (find, read, get -> fetch 1+ entities)
    //   optional: <subject>
    //   By
    //   <predicate>
    List<Order> findByDeliveryZip(String deliveryZip);

    // Syntax:
    // read -> fetch
    // Orders -> ignored, since we know the entity is Orders
    // By -> start of properties to match
    // DeliveryZip -> matches .deliveryZip or .delivery.zip property
    // And -> additional condition
    // PlacedAt -> matches .placedAt or .placed.at property
    // Between -> value must fall between two Date objets passed
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(
        String deliveryZip, Date startDate, Date endDate);

    // Use @Query to define manual queries for a method
    // @Query("Order o where o.deliveryCity='Seattle'")
    // List<Order> readOrdersDeliveredInSeattle();
}