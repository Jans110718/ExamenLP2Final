package com.cibertec.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.repositorio.DepartamentoRepository;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoListado {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/listar")
    public String listarDepartamentos(Model model) {
        List<String> listaNombresDepartamentos = departamentoRepository.findAllNames();
        model.addAttribute("departamentos", listaNombresDepartamentos);
        return "listadoDepartamentos"; // Nombre de la vista Thymeleaf
    }
}
