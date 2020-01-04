package com.cpe.personnel.entity;
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
@Table(name="EDUCATIONLEVEL")
public class Educationlevel{
    @Id
    @SequenceGenerator(name="EDUCATIONLEVEL_SEQ",sequenceName="EDUCATIONLEVEL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EDUCATIONLEVEL_SEQ")
    @Column(name="EDUCATIONLEVEL_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String educationlevel_name;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Personnel> personnel;
}