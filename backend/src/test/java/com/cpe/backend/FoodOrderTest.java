package com.cpe.backend;

import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.orderfood.repository.FoodOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import com.fasterxml.jackson.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class FoodOrderTest {

    private Validator validator;

    @Autowired
    private FoodOrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6026042_testFoodorderOKWithAllDataNotNull() {
        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(new Date());
        order = orderRepository.saveAndFlush(order);

        Optional<FoodOrder> found = orderRepository.findById(order.getId());
        assertEquals("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", found.get().getDetails());

    }
    @Test
    void b6026042_testDetailsOKWithNotbeNull() {
        FoodOrder order = new FoodOrder();
        order.setDetails(null);
        order.setOrderDate(new Date());
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("details", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testDetailsOKWithNotbe101Char() {
        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789a");
        order.setOrderDate(new Date());
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("size must be between 1 and 100", v.getMessage());
        assertEquals("details", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testOrderDateMustNotBeNull(){
        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(null);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("orderDate", v.getPropertyPath().toString());
    }

}




















