package com.financewallet.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmationCodeDTO {
    @Getter
    @Setter
    @NotNull(message = "The field 'emailCode' must not be null.")
    @NotEmpty(message = "The field 'emailCode' must not be empty.")
    @NotBlank(message = "The field 'emailCode' must not be blank.")
    @Pattern(regexp = "^\\S+$", message = "The code cannot contain spaces.")
    @Size(min = 6, max = 6, message = "The code must be exactly 6 characters long.")
    String emailCode;
}
