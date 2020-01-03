package com.cpe.backendfood.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
@Table(name="FOOD")
public class Food {

    @Id
    @SequenceGenerator(name="food_seq",sequenceName="food_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="food_seq")
    @Column(name = "Food_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity =Foodtype.class)
    @JoinColumn(name = "FoodtypeID", insertable = true)
    private Foodtype typeby;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Meal.class)
    @JoinColumn(name = "MealID", insertable = true)
    private Meal mealby;
}