package com.juanroam.reservations.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Hidden
@Controller
@RequestMapping("documentation")
public class DocumentationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentationController.class);

    @ResponseBody
    @GetMapping
    public void redirectToDocumentation(HttpServletResponse response) {
        try {
            response.sendRedirect("swagger-ui.html");
        } catch (IOException ioe) {
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR: ");
            if (ioe.getMessage() != null) {
                sb.append(ioe.getMessage());
            }
            LOGGER.error(sb.toString());
        }
    }
}
