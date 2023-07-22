package com.example.career.Controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor

public class HomeController {
    @GetMapping("test")
    public TestDTO conntectTest() {
        return new TestDTO();
    }

}
@Data
class TestDTO {
    private int code = 400;
}
