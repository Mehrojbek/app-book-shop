package uz.mehrojbek.appbookshop.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.enums.ProductEnum;
import uz.mehrojbek.appbookshop.enums.RegionEnum;


import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @ApiModelProperty(notes = "bu to'ldirish uchun kerakmas",example = "buni to'ldirish kerakmas")
    private UUID id;

    @ApiModelProperty(example = "Aliyev Vali")
    @NotBlank
    private String fullName;

    @ApiModelProperty(example = "998991234567")
    @NotBlank
    private String phoneNumber;

    @ApiModelProperty(example = "TASHKENT_V, JIZZAX, SIRDARYO, GULISTON, NAMANGAN, ANDIJON, FARGONA, QASHQADARYO, SURXONDARYO, NAVOIY, BUXORO, XORAZM, QORAQALPOGISTON, TASHKENT_SH")
    @NotBlank
    private RegionEnum region;

    @ApiModelProperty(example = "[BOOK, JAVA, JAVA_SCRIPT, PYTHON, C_PLUS_PLUS] list formatida ichida maximum shular bo'lishi mumkin")
    @NotBlank
    private List<ProductEnum> productList;

    @ApiModelProperty(example = "client qo'shayotganda kerak emas, shu clientni agar kitobni sotib olsa clientni put qilayotganda true qilib qo'yiladi")
    private boolean buy;
}
