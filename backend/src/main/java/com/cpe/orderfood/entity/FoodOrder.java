package com.cpe.orderfood.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.cpe.backendfood.entity.Food;
import com.cpe.personnel.entity.Personnel;
import com.cpe.register.entity.Register;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOODORDER")
public class FoodOrder {
    @Id
    @SequenceGenerator(name="FOODORDER_SEQ",sequenceName="FOODORDER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FOODORDER_SEQ")
    @Column(name="FOODORDER_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[[ก-๙][ ]A-Za-z0-9[+][-][*]]*")
    private String details;
    @NotNull
    private Date orderDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Food.class)
    @JoinColumn(name = "Food_ID", insertable = true)
    private @NotNull Food food;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private @NotNull Personnel orderBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Register.class)
    @JoinColumn(name = "register_ID", insertable = true)
    private @NotNull Register patient;
}