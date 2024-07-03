package com.cibertec.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.modelos.Departamento;
import com.cibertec.modelos.Empleado;
import com.cibertec.repositorio.DepartamentoRepository;
import com.cibertec.repositorio.EmpleadoRepository;

@Controller
public class EmpleadoEditar {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/editarEmpleado")
    public String mostrarFormulario(Model modelo, @RequestParam("id") int idEditar) {
        Empleado empleado = empleadoRepository.findById(idEditar)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Iterable<Departamento> departamentos = departamentoRepository.findAll();
        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("departamentos", departamentos);

        return "vistas/empleado/editar";
    }

    @PostMapping("/editarEmpleado")
    public String procesarFormulario(Model modelo,
                                     @RequestParam("id") int id,
                                     @RequestParam("nombre") String nombre,
                                     @RequestParam("apellido") String apellido,
                                     @RequestParam("salario") double salario,
                                     @RequestParam("departamento") int idDepartamento) {

        Departamento departamento = departamentoRepository.findById(idDepartamento)
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setSalario(salario);
        empleado.setDepartamento(departamento);

        empleadoRepository.save(empleado);

        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("exito", true);
        Iterable<Departamento> departamentos = departamentoRepository.findAll();
        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("departamentos", departamentos);
        
        return "vistas/empleado/editar";
    }
}

