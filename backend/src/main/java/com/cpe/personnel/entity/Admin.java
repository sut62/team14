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
@Table(name="ADMIN")
public class Admin{
    @Id
    @SequenceGenerator(name="ADMIN_SEQ",sequenceName="ADMIN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADMIN_SEQ")
    @Column(name="ADMIN_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String user;
    private @NonNull String password;
}