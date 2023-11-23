package com.dang.dnolja.review.controller.dto.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

public class ReviewListRequest {

    @DecimalMin(value = "0", inclusive = false, message = "currentPage는 0보다 큰 정수여야 합니다.")
    @DecimalMax(value = "10000", inclusive = false, message = "currentPage는 10000보다 작은 정수여야 합니다.")
    private Integer currentPage;

    @DecimalMin(value = "-1", inclusive = false, message = "sizePerPage는 0보다 큰 정수여야 합니다.")
    @DecimalMax(value = "100000", inclusive = false, message = "x는 100000보다 작은 정수여야 합니다.")
    private Integer sizePerPage;


    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,20}$", message="한글/영문/숫자로만 구성된 1~20자 이내의 검색어을 입력해주세요")
    private String keyword;

}
