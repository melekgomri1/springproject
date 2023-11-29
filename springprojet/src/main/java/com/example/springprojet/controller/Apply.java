package com.example.springprojet.controller;

import com.example.springprojet.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Apply{
    @Autowired
    private ApplyService applyService;

    @GetMapping("/formjob")
    public String showApplyForm(Model model) {
        model.addAttribute("apply", new com.example.springprojet.model.Apply()); // Utilisation de la classe Apply du package model
        return "apply";
    }

    @PostMapping("/apply/save")
    public String saveApply(@ModelAttribute("apply") com.example.springprojet.model.Apply apply) { // Utilisation de la classe Apply du package model
        applyService.addapply(apply);
        return "apply";
    }

    @GetMapping("/list")
    public String showApplyList(Model model) {
        List<com.example.springprojet.model.Apply> applies = applyService.getallappply(); // Utilisation de la classe Apply du package model
        model.addAttribute("applies", applies);
        return "apply-list";
    }
}