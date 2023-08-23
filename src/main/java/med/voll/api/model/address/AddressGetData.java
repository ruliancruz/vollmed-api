package med.voll.api.model.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressGetData
(
    @NotBlank
    @Size(max = 100, message = "{validation.street.size.too_long}")
    String street,

    @Size(max = 20, message = "{validation.number.size.too_long}")
    String number,

    @Size(max = 100, message = "{validation.complement.size.too_long}")
    String complement,

    @NotBlank
    @Size(max = 100, message = "{validation.district.size.too_long}")
    String district,

    @NotBlank
    @Size(max = 100, message = "{validation.city.size.too_long}")
    String city,

    @NotBlank
    @Size(max = 2, message = "{validation.uf.size.too_long}")
    String uf,

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep
){}