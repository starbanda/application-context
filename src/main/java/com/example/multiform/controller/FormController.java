package com.example.multiform.controller;

import com.example.multiform.model.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("formData")
public class FormController {

    @ModelAttribute("formData")
    public FormData formData() {
        return new FormData();
    }

    @GetMapping("/")
    public String page1() {
        return "page1";
    }

    @PostMapping("/processPage1")
    public String processPage1(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @ModelAttribute("formData") FormData formData,
            RedirectAttributes redirectAttributes) {

        formData.setFirstName(firstName);
        formData.setLastName(lastName);
        redirectAttributes.addFlashAttribute("firstName", firstName);
        redirectAttributes.addFlashAttribute("lastName", lastName);
        return "redirect:/page2";
    }

    @GetMapping("/page2")
    public String page2(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("firstName", formData.getFirstName());
        model.addAttribute("lastName", formData.getLastName());
        return "page2";
    }

    @PostMapping("/processPage2")
    public String processPage2(
            @RequestParam String email,
            @RequestParam String phone,
            @ModelAttribute("formData") FormData formData,
            RedirectAttributes redirectAttributes) {

        formData.setEmail(email);
        formData.setPhone(phone);
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("phone", phone);
        return "redirect:/page3";
    }

    @GetMapping("/page3")
    public String page3(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("firstName", formData.getFirstName());
        model.addAttribute("lastName", formData.getLastName());
        model.addAttribute("email", formData.getEmail());
        model.addAttribute("phone", formData.getPhone());
        return "page3";
    }

    @PostMapping("/processPage3")
    public String processPage3(
            @RequestParam String city,
            @RequestParam String country,
            @ModelAttribute("formData") FormData formData) {

        formData.setCity(city);
        formData.setCountry(country);
        return "redirect:/summary";
    }

    @GetMapping("/summary")
    public String summary(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("formData", formData);
        return "summary";
    }
}