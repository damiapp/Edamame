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
@Table(name = "leagueuserstats")
public class LeagueUserStats{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeagueUserStats;
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
    @OneToOne
    @JoinColumn(name = "idLeague")
    private League league;
    @Column
    private Integer matchesPlayed;
    @Column
    private Integer score;

}
