package com.dang.dnolja.shortest.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ShortestReqDto {

    @NotNull(message = "x는 필수값입니다.")
    @DecimalMin(value = "126.0", inclusive = false, message = "x는 126.0보다 큰 실수여야 합니다.")
    @DecimalMax(value = "128.0", inclusive = false, message = "y는 128.0보다 작은 실수여야 합니다.")
    private Double x;

    @NotNull(message = "y는 필수값입니다.")
    @DecimalMin(value = "33.0", inclusive = false, message = "y는 33.0보다 큰 실수여야 합니다.")
    @DecimalMax(value = "35.0", inclusive = false, message = "y는 35.0보다 작은 실수여야 합니다.")
    private Double y;

    @NotNull(message = "limit는 필수값입니다.")
    @DecimalMin(value = "1", inclusive = false, message = "limit는 0보다 큰 정수이어야합니다.")
    @DecimalMax(value = "100", inclusive = false, message = "limit는 100보다 작은 정수이어야합니다.")
    private Double limit;

    @NotNull(message = "maxCount는 필수값입니다.")
    @DecimalMin(value = "1", inclusive = false, message = "maxCount는 0보다 큰 정수이어야합니다.")
    @DecimalMax(value = "100", inclusive = false, message = "maxCount는 100보다 작은 정수이어야합니다.")
    private Integer maxCount;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$", message="한글/영문/숫자로만 구성된 2~20자 이내의 검색어를 입력하세요")
    private String keyword;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$", message="한글/영문/숫자로만 구성된 2~20자 이내의 카테고리명을 입력하세요")
    private String category;

}
