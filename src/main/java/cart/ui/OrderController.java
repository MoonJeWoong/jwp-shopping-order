package cart.ui;

import cart.application.OrderService;
import cart.domain.Member;
import cart.dto.OrderCreateRequest;
import cart.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@Auth Member member, @RequestBody OrderCreateRequest orderCreateRequest) {
        final Long id = orderService.createOrder(orderCreateRequest, member);

        return ResponseEntity.created(URI.create("/orders/" + id)).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findSingleOrder(@PathVariable Long orderId, @Auth Member member) {
        final OrderResponse response = orderService.findById(orderId, member);
        return ResponseEntity.ok(response);
    }

}