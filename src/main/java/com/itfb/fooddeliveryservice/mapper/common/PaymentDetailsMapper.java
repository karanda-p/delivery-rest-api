package com.itfb.fooddeliveryservice.mapper.common;

import com.itfb.fooddeliveryservice.mapper.BaseMapper;
import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.model.dto.PaymentDetailsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentDetailsMapper extends BaseMapper<PaymentDetails, PaymentDetailsDTO> {
}
