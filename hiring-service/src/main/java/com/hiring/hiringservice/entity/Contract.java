package com.hiring.hiringservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "contracts")
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "street_from", nullable = false)
    private String streetFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_from_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private District districtFrom;

    @Column(name = "street_to", nullable = false)
    private String streetTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_to_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private District districtTo;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_arrival", nullable = false)
    private Time timeArrival;

    @Column(name = "time_departure", nullable = false)
    private Time timeDeparture;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private User carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private StatusContract status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Notification notification;
}
