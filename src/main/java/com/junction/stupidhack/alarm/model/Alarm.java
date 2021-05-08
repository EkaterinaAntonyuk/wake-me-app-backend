package com.junction.stupidhack.alarm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private User creator;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination")
    private User destination;
    private Date date;
    private String message;
    private int length;

    public Alarm(User creator, User destination, Date date, String message, int length) {
        this.creator = creator;
        this.destination = destination;
        this.date = date;
        this.message = message;
        this.length = length;
    }
}
