package com.cpe.orderfood.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.cpe.backendfood.entity.Food;
import com.cpe.personnel.entity.Personnel;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOOD_ORDER")
public class FoodOrder {
    @Id
    @SequenceGenerator(name="FOODORDER_SEQ",sequenceName="FOODORDER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FOODORDER_SEQ")
    @Column(name="FOODORDER_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String details;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Food.class)
    @JoinColumn(name = "Food_ID", insertable = true)
    private Food food;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private Personnel orderBy;
}