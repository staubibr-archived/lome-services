package com.models.lib.lom.services.contributors;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Contributors {

    private Long id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String email;
    private String affiliation;
    private Date creation_date;
}
