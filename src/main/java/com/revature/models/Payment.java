package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_payments")
public class Payment {
    @Id
    private String id;
    private String ccv;
    @Column(name = "exp_date")
    private Date expDate;
    private String zip;
    private float balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
