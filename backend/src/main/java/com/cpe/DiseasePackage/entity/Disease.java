package com.cpe.DiseasePackage.entity;

import lombok.*;
import java.util.Collection;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.cpe.personnel.entity.Personnel;
import com.cpe.register.entity.Register;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name="DISEASE")
public class Disease {
	@Id
	@SequenceGenerator(name="disease_seq",sequenceName="disease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="disease_seq")
   
    @Column(name = "DISEASE_ID" , unique = true, nullable = true)
    private @NonNull Long id;

    //@Column(unique = true, nullable = true)
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    //@Column(nullable = true)
    @NotNull
    @Pattern(regexp = "[[ก-๙][ ]A-Za-z0-9]*")
    private String symptom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    @NotNull
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = LifeSpan.class)
    @JoinColumn(name = "LIFESPAN_ID", insertable = true)
    @NotNull
    private LifeSpan lifespan;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Personnel.class)
    @JoinColumn(name = "PERSONNEL_ID", insertable = true)
    @NotNull
    private Personnel personnel;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Register> regis;

	

}