package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_payments")
public class Payment {
    @Id
    private String id;
    @NotEmpty
    @NotNull
    private String cardNumber;
    @NotEmpty
    @NotNull
    private String ccv;
    @NotNull
    @Column(name = "exp_date")
    private Date expDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
