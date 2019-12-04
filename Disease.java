package com.cpe.orderpackage.entity;

import lombok.*;
import java.util.Collection;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;

import org.springframework.lang.Nullable;

@Data
@Entity
@NoArgsConstructor
@Table(name="Disease")
public class Disease {
	@Id
	@SequenceGenerator(name="disease_seq",sequenceName="disease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="disease_seq")
    @Column(name = "DISEASE_ID", unique = true, nullable = true)
    private @NonNull Long id;

   @Column(name="DATE")
    private @Nullable Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity =Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", insertable = true)
    @JsonManagedReference
    private Doctor doctor;
//ttt
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    @JsonManagedReference
    private Type type;

	
	

	

}