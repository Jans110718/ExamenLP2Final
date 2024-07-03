package com.cibertec.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.modelos.Empleado;
import com.cibertec.modelos.Departamento;
import com.cibertec.repositorio.EmpleadoRepository;
import com.cibertec.repositorio.DepartamentoRepository;

@Controller
public class EmpleadoIndex {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/buscarEmpleado")
    public String mostrarPaginaBusquedaEmpleado(Model model) {
        model.addAttribute("filtro", "nombre"); // Valor por defecto o el último seleccionado
        return "vistas/empleado/index"; // Retorna la ruta de la página de búsqueda de empleados
    }

    // Método POST para procesar la búsqueda de empleados por nombre, apellido o departamento
    @PostMapping("/buscarEmpleado")
    public String buscarEmpleadosPorFiltro(@RequestParam("filtro") String filtro,
                                           @RequestParam("txtBuscar") String terminoBuscar,
                                           Model model) {
        List<Empleado> listaEmpleados = null;

        switch (filtro) {
            case "nombre":
                listaEmpleados = empleadoRepository.buscarPorNombre(terminoBuscar);
                break;
            case "apellido":
                listaEmpleados = empleadoRepository.buscarPorApellido(terminoBuscar);
                break;
            case "departamento":
                Departamento departamento = departamentoRepository.findByNombre(terminoBuscar);
                if (departamento != null) {
                    listaEmpleados = empleadoRepository.buscarPorDepartamento(departamento);
                }
                break;
            default:
                listaEmpleados = empleadoRepository.buscarPorNombre(terminoBuscar);
                break;
        }

        model.addAttribute("listaEmpleados", listaEmpleados); // Agrega la lista de empleados al modelo
        model.addAttribute("nombreBuscado", terminoBuscar); // Agrega el término buscado para mantenerlo en el input
        model.addAttribute("filtro", filtro); // Agrega el filtro seleccionado al modelo

        return "vistas/empleado/index"; // Retorna la ruta de la página de búsqueda de empleados
    }
}