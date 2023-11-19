package com.dang.dnolja.location.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
public class LocationListRequest {

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,20}$", message="한글/영문/숫자로만 구성된 1~20자 이내의 검색어을 입력해주세요")
    private String keyWord;

    @NotEmpty
    @Pattern(regexp = "^[0-1000]$", message="pageNo는 정수를 입력해주세요")
    private int currentPage;

    @NotEmpty
    @Pattern(regexp = "^[0-1000]$", message="pageNo는 정수를 입력해주세요")
    private int sizePerPage;

}
