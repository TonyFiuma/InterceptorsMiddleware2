package co.develhope.interceptorsmiddleware2.controllers;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("months")
public class MonthController {
    @GetMapping("")
    public Month getMonth(@RequestAttribute("month") Month month) {
        return month;
    }
}