package com.yandoama.gestmoto.controller;

import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.service.GmServiceMetier;
import com.yandoama.gestmoto.utils.GmConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(GmConstants.URLS.BASE_URL)
public class GestMotoController {

    private final GmServiceMetier serviceMetier;


    @GetMapping(GmConstants.URLS.USER)
    public GmUserDto getUser(final GmUserDto user) {
        return user;
    }

}
