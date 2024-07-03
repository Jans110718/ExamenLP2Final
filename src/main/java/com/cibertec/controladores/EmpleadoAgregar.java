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
public class EmpleadoAgregar {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/agregarEmpleado")
    public String mostrarFormulario(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("empleado", empleado);

        // Obtener la lista de departamentos para seleccionar en el formulario
        Iterable<Departamento> departamentos = departamentoRepository.findAll();
        modelo.addAttribute("departamentos", departamentos);

        return "vistas/empleado/agregar";
    }

    @PostMapping("/agregarEmpleado")
    public String procesarFormulario(Model modelo,
                                     @RequestParam("nombre") String nombre,
                                     @RequestParam("apellido") String apellido,
                                     @RequestParam("salario") double salario,
                                     @RequestParam("departamento") int idDepartamento) {

        Departamento departamento = departamentoRepository.findById(idDepartamento)
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setSalario(salario);
        empleado.setDepartamento(departamento);

        empleadoRepository.save(empleado);

        // Obtener el nuevo ID del empleado agregado
        int nuevoId = empleado.getId_empleado();

        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("exito", true);

        // Obtener la lista de departamentos para seleccionar en el formulario
        Iterable<Departamento> departamentos = departamentoRepository.findAll();
        modelo.addAttribute("departamentos", departamentos);
        return "vistas/empleado/agregar";
    }
}
