package com.hiring.hiringservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiring.hiringservice.model.Carrier;
import com.hiring.hiringservice.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name="contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount", nullable = false)
    private String amount;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="quantity", nullable = false)
    private String quantity;

    @Column(name="subject", nullable = false)
    private String subject;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="time_arrival", nullable = false)
    private Time timeArrival;

    @Column(name="time_departure", nullable = false)
    private Time timeDeparture;

    @Column(name="visible", nullable = false)
    private boolean visible;

    @Transient
    private Client client;

    @Transient
    private Carrier carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private StatusContract status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Notification notifications;

}
