package com.cpe.personnel.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Collection;
import com.cpe.personnel.entity.Personnel;

@Data
@Entity
@NoArgsConstructor
@Table(name="PERSONNEL")
public class Personnel {

    @Id
    @SequenceGenerator(name="PERSONNEL_seq",sequenceName="PERSONNEL_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERSONNEL_seq")
    @Column(name = "PERSONNEL_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String firstname;
    private @NonNull String lastname;
    private @NonNull String telephone;
    private @NonNull LocalDate Birthday;
    

   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    private Position posit;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Maritalstatus.class)
    @JoinColumn(name = "MARITALSTATUS_ID", insertable = true)
    private Maritalstatus status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Educationlevel.class)
    @JoinColumn(name = "EDUCATIONLEVEL_ID", insertable = true)
    private Educationlevel level;

}