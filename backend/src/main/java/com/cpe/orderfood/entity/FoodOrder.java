package com.cpe.orderfood.entity;

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
@Table(name="FOOF_ORDER")
public class FoodOrder {
    @Id
    @SequenceGenerator(name="FOODORDER_SEQ",sequenceName="FOODORDER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FOODORDER_SEQ")
    @Column(name="FOODORDER_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String details;
}