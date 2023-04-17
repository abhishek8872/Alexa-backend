package com.bookmycon.AlexaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingObjDto {
    private String BookingObjAuditoriamName;
    private String BookingObjDate;
    private String BookingObjTimeFrom;
    private String BookingObjTimeTo;

}
