package com.dang.dnolja.location.model.service

import com.dang.dnolja.location.model.dto.LocationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification



@SpringBootTest
class SpotServiceImplTest extends Specification {

    @Autowired
    SpotService spotService;

    def "spot 테이블 전체조회 "(){
        when:
        LocationDto result = spotService.findAll().get(0);


       then:
        result.getId() == 1;
        result.getType() == "박물관"
        result.getName() == "감귤박물관"
    }

}
