package org.example.controller;

import org.example.common.core.web.domain.AjaxResult;
import org.example.system.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CodeController {
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("code")
    public AjaxResult code() {
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createCaptcha();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ajax;

    }
}
