package com.ts.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ErrorResponseDTO {

    private String status;
    private String message;
    private Object data;
}
