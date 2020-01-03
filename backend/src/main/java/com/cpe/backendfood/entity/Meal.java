package com.cpe.backendfood.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MEAL")
public class Meal {
    @Id
    @SequenceGenerator(name="meal_SEQ",sequenceName="meal_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="meal_SEQ")
    @Column(name="id",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String mealtype;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "settype"
    private Collection<Food> food;
}