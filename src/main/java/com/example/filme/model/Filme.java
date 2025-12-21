package com.example.filme.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
    private String title;
    private String release_year;
    private String locations;
    private String production_company;
    private String distributor;
    private String director;
    private String actor_1;
    private String actor_2;
    private String actor_3;
    private String longitude;
    private String latitude;
}
