package com.RestaurantApp.order.mapper;

import com.RestaurantApp.order.dto.OrderDTO;
import com.RestaurantApp.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE =  Mappers.getMapper(OrderMapper.class);

    OrderDTO mapOrderToOrderDTO(Order order);
    Order mapOrderDTOToOrder(OrderDTO orderDTO);
}
