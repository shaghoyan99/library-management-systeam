package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.User;
import com.carrental.librarymanagementsysteam.service.UserService;
import com.carrental.librarymanagementsysteam.service.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${library.management.upload.image.directory.path}")
    private String imageDirectoryPath;

    private final UserService userService;


    @GetMapping("/")
    public String home(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "loginPage";
    }



    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam("pic") MultipartFile multipartFile) {
        if (userService.findOptionalByUsername(user.getUsername()).isPresent()) {
            return "redirect:/registerPage?msg=Username already exists!";
        }
        userService.save(user, multipartFile);
        return "redirect:/loginPage?msg=Registration successful, pls login!";
    }


    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("pic") String picName) {
        File file = new File(imageDirectoryPath + picName);
        if (file.exists() && file.isFile()) {
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
        }
        return null;
    }



}
