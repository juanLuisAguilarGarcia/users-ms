package com.dvp.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identification {
    private Long typeId;
    private Long number;
}
