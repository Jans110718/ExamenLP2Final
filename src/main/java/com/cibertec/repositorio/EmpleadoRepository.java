package com.cibertec.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import com.cibertec.modelos.Departamento;
import com.cibertec.modelos.Empleado;

@Repository								// <Clase_del_elemento_a_persistir, tipo_de_dato_de_mi_ID>
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> { // ID de Empleado es int, por eso es Integer
	   @Query("SELECT e FROM Empleado e WHERE e.id_empleado = :id")
	    public Empleado buscarPorId(Integer id);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Empleado (nombre, apellido, salario, id_departamento) VALUES (:nombre, :apellido, :salario, :id_departamento)", nativeQuery = true)
    void agregar(String nombre, String apellido, double salario, int id_departamento);

 

    @Transactional
    @Modifying
    @Query("UPDATE Empleado e SET e.nombre = :nombre, e.apellido = :apellido, e.salario = :salario, e.departamento = :departamento WHERE e.id_empleado = :id")
    void actualizar(Integer id, String nombre, String apellido, double salario, Departamento departamento);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Empleado e WHERE e.id_empleado = :id")
    void eliminar(Integer id);

    @Query("SELECT MAX(e.id_empleado) FROM Empleado e WHERE e.nombre = :nombre")
    Integer nuevoId(String nombre);

    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre%")
    public List<Empleado> buscarPorNombre(String nombre);
    @Query("SELECT e FROM Empleado e WHERE e.apellido LIKE %:apellido%")
    List<Empleado> buscarPorApellido(String apellido);
    @Query("SELECT e FROM Empleado e WHERE e.departamento = :departamento")
    List<Empleado> buscarPorDepartamento(Departamento departamento);
    
    @Query("SELECT e FROM Empleado e WHERE e.departamento.nombre = :nombreDepartamento")
    List<Empleado> buscarPorNombreDepartamento(String nombreDepartamento);
    
}
