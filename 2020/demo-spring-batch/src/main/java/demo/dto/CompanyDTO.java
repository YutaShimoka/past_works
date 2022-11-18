package demo.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import demo.constraints.IsValidInt;
import demo.constraints.IsValidLong;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @IsValidLong
    @Size(min = 13, max = 13)
    @NotBlank
    private String corporateNumber;
    @Size(max = 150)
    private String name;
    @Size(max = 330)
    private String address;
    @IsValidInt
    @Size(min = 2, max = 2)
    private String prefectureCode;
    @IsValidInt
    @Size(min = 3, max = 3)
    private String cityCode;
    @IsValidInt
    @Size(min = 7, max = 7)
    private String postCode;

    public static CompanyDTO valueOf(String[] lineSplit) {
        CompanyDTO dto = new CompanyDTO();
        dto.setCorporateNumber(lineSplit[1]); // 4010801014350
        dto.setName(Objects.toString(lineSplit[6], "")); // 株式会社ヘルメスシステムズ
        dto.setAddress(getAddress(lineSplit[9], lineSplit[10], lineSplit[11])); // 東京都港区海岸１丁目１６番１号ニューピア竹芝サウスタワー６階
        dto.setPrefectureCode(lineSplit[13]); // 13
        dto.setCityCode(lineSplit[14]); // 103
        dto.setPostCode(lineSplit[15]); // 1050022
        return dto;
    }

    private static String getAddress(String prefectureName, String cityName, String streetNumber) {
        prefectureName = Objects.toString(prefectureName, "");
        cityName = Objects.toString(cityName, "");
        streetNumber = Objects.toString(streetNumber, "");
        return String.join("", prefectureName, cityName, streetNumber);
    }

    public Long getCorporateNumberAsLong() {
        return Long.valueOf(corporateNumber);
    }

    public Integer getPrefectureCodeAsInteger() {
        return getValueAsInteger(prefectureCode);
    }

    public Integer getCityCodeAsInteger() {
        return getValueAsInteger(cityCode);
    }

    public Integer getPostCodeAsInteger() {
        return getValueAsInteger(postCode);
    }

    private Integer getValueAsInteger(String strValue) {
        return Optional.ofNullable(strValue).map(strVal -> Integer.valueOf(strVal)).orElse(null);
    }

}
