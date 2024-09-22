package com.samhcoco.ip.bookingapp.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "`user`")
public class User {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private Date created;
    private boolean deleted;

}
