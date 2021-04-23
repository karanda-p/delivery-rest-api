package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.exception.EntityNotFoundException;
import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.mapper.common.CustomerMapper;
import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.model.Message;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.model.notification.NotificationMessage;
import com.itfb.fooddeliveryservice.repository.OrderRepository;
import com.itfb.fooddeliveryservice.repository.PaymentDetailsRepository;
import com.itfb.fooddeliveryservice.service.integration.CourierIntegrationService;
import com.itfb.fooddeliveryservice.service.integration.NotificationIntegrationService;
import com.itfb.fooddeliveryservice.service.integration.PaymentIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CartItemToOrderItemMapper cartItemToOrderItemMapper;
    private final CourierIntegrationService courierIntegrationService;
    private final OrderMapper orderMapper;
    private final PaymentIntegrationService paymentIntegrationService;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final CustomerMapper customerMapper;
    private final NotificationIntegrationService notificationIntegrationService;
    private final NotificationService notificationService;


    @Transactional
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Message.ORDER_NOT_FOUND, id));
    }

    @Transactional
    public Collection<Order> getAllOrdersByCustomerId(Long customerId) {
        return orderRepository.getAllByCustomerId(customerId);
    }

    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order createNewOrder(String login, Order order) {
        Customer customer = customerService.getCustomerByLogin(login);
        if (customer.getCart() == null)
            throw new EntityNotFoundException(Message.CART_IS_EMPTY, login);
        order.setCustomer(customer);
        double amount = 0D;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            order.addOrderItemToOrder(cartItemToOrderItemMapper.cartItemToOrderItem(cartItem));
            amount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        order.setAmount(amount);
        order.setRestaurant(order.getOrderItems()
                .get(0)
                .getProduct()
                .getRestaurant());
        order.setStatus(OrderStatus.CREATED);
        order.setCreationDate(LocalDateTime.now());
        order = orderRepository.save(order);
        customer.setCart(null);
        cartService.deleteCartById(customer.getCartId());
        customer = customerService.updateCustomer(customer);

        notificationIntegrationService.sendNotification(notificationService.configureNotificationMessage(customer
                , order, NotificationMessage.CREATE));

        PaymentDetails paymentDetails = paymentIntegrationService.commitPayment(orderMapper.domainToDto(order));
        paymentDetails.setDoneDate(LocalDateTime.now());
        paymentDetailsRepository.save(paymentDetails);
        order.setPaymentDetails(paymentDetails);
        order.setPaymentId(paymentDetails.getId());
        order.setStatus(OrderStatus.PAID);


        notificationIntegrationService.sendNotification(notificationService.configureNotificationMessage(customer,
                order, NotificationMessage.PAID));

        return orderRepository.save(order);
    }

    @Transactional
    public Order changeOrderStatus(Order order) {
        Order newOrder = orderRepository.getOne(order.getId());
        newOrder.setStatus(order.getStatus());
        return orderRepository.save(newOrder);
    }

    @Transactional
    @Scheduled(fixedDelay = 15000)
    public void sendOrderToCourierService() {

        List<Order> orders = new ArrayList<>(orderRepository.getAllByStatus(OrderStatus.PAID));

        if (!orders.isEmpty()) {
            for (Order order : orders) {
                courierIntegrationService.sendOrderToCourierService(orderMapper.domainToDto(order));
                order.setStatus(OrderStatus.IN_PROGRESS);
                Customer customer = customerService.getCustomerById(order.getCustomerId());
                orderRepository.save(order);
//                notificationIntegrationService.sendNotification(new NotificationMessage(customer, order));
            }
        } else {
            System.out.println("Ожидание новых заказов");
        }
    }

}
