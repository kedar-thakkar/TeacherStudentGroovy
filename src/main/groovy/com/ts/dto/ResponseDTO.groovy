package com.ts.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ResponseDTO {
    private String status;
    private String message;
    private Object data;
}
