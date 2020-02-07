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
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOODTYPE")
public class Foodtype {
    @Id
    @SequenceGenerator(name="foodtype_SEQ",sequenceName="foodtype_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="foodtype_SEQ")
    @Column(name="id",unique = true, nullable = true)
    private @NonNull Long id;
    private @NotNull String type;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "settype"
    private Collection<Food> food;
}