package com.cpe.DiseasePackage.entity;

import lombok.*;
import java.util.Collection;
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
import com.cpe.personnel.entity.Personnel;

@Data
@Entity
@NoArgsConstructor
@Table(name="DISEASE")
public class Disease {
	@Id
	@SequenceGenerator(name="disease_seq",sequenceName="disease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="disease_seq")
   
    @Column(unique = true, nullable = true)
    private @NonNull Long id;

    @Column(unique = true, nullable = true)
    private @NonNull String name;

    @Column(unique = true, nullable = true)
    private @NonNull String symptom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = LifeSpan.class)
    @JoinColumn(name = "LIFESPAN_ID", insertable = true)
    private LifeSpan lifespan;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    private Personnel personnel;	
	

}