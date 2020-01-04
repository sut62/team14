package com.cpe.DiseasePackage.entity;

import lombok.*;

import javax.persistence.Id;
import com.cpe.DiseasePackage.entity.Disease;
import org.springframework.lang.Nullable;
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
@Table(name="TYPE")
public class Type {
    @Id
    @SequenceGenerator(name="type_seq",sequenceName="type_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="type_seq")
    @Column(name="TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
  
    private @NonNull String typename;
   
     public void setType(String typename){
         this.typename=typename;
     }

    @OneToMany(fetch = FetchType.EAGER ,mappedBy="type")
    private Collection<Disease> disease;
}