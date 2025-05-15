package com.example.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.crud.model.Mahasiswa;
import com.example.crud.repository.MahasiswaRepository;


@Controller
public class MahasiswaController {

    private final MahasiswaRepository repo;
    public MahasiswaController(MahasiswaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/mahasiswa")
    public String listMahasiswa(Model model) {
        model.addAttribute("mahasiswaList", repo.findAll());
        return "mahasiswa";
    }

    @PostMapping("/mahasiswa")
    public String addMahasiswa(Mahasiswa mahasiswa) {
        repo.save(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @PostMapping("/mahasiswa/delete/{id}")
    public String deleteMahasiswa(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/mahasiswa";
    }

    @GetMapping("/mahasiswa/edit/{id}")
    public String editMahasiswa(@PathVariable Long id, Model model) {
        Mahasiswa mahasiswa = repo.findById(id).orElse(null);
        model.addAttribute("mahasiswa", mahasiswa);
        return "edit-mahasiswa";
    }

    @PostMapping("/mahasiswa/update")
    public String updateMahasiswa(Mahasiswa mahasiswa) {
        repo.save(mahasiswa);
        return "redirect:/mahasiswa";
    }
    
}
