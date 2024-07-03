package com.cibertec.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.modelos.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Query("SELECT d.nombre FROM Departamento d")
    List<String> findAllNames();
    
    Departamento findByNombre(String nombre);
}
