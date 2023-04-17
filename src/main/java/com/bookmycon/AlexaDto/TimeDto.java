package com.bookmycon.AlexaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeDto {
    private String timeFrom;
    private String timeTo;
}
