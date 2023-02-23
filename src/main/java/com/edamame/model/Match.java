package com.edamame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;
    @Column
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "idLeague")
    private League league;

    @OneToMany
    @JoinColumn(name = "idResult")
    private List<Result> results;


}
