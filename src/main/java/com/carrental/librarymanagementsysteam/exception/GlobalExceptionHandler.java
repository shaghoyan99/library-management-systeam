package com.carrental.librarymanagementsysteam.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFound(
            MemberNotFoundException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", ex.getMessage());
        return "redirect:/members";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFound(
            BookNotFoundException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", ex.getMessage());
        return "redirect:/members";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUserNotFound(
            UsernameNotFoundException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", ex.getMessage());
        return "redirect:/loginPage";
    }
}
