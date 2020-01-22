package com.cpe.backendfood.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.personnel.entity.Personnel;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotNull
    @Size(min = 4,max = 20)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity =Foodtype.class)
    @JoinColumn(name = "FoodtypeID", insertable = true)
    private Foodtype typeby;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Meal.class)
    @JoinColumn(name = "MealID", insertable = true)
    private Meal mealby;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<FoodOrder> order;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private Personnel createdby;
}