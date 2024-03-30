package com.example;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// Este decorador le avisa a Junit que usaremos Mockito para el mock de los objetos.
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    // Crea un Mock del repositorio. Es un objeto vacio de clase StudentRepository, el comportamiento
    // De los metodos debemos simularlo nosotros luego.
    @Mock
    private StudentRepository studentRepository;

    // Cada variable que haya sido creada como mock, sera inyectada en el servicio. De esta forma,
    // Evitamos que al crear una instancia del servicio, se cree un studentRepository real, solo tendremos el mock.
    @InjectMocks
    private StudentService studentService;

    // Este decorador hace que dicho metodo se ejecute antes de la ejecucion de cada test.
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Este decorador significa que este metodo es un test unitario.
    @Test
    @DisplayName("Obtener estudiantes")
    public void testGetStudents() {
        // Datos de prueba
        Student student1 = new Student("John", "Doe", "john@example.com");
        Student student2 = new Student("Jane", "Smith", "jane@example.com");
        List<Student> students = Arrays.asList(student1, student2);

        // Mock del comportamiento del repositorio
        when(studentRepository.findAll()).thenReturn(students);

        // Ejecutar el método a probar
        List<Student> result = studentService.getStudents();

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(students, result);
    }

    // Este decorador significa que este metodo es un test unitario.
    @Test
    @DisplayName("Existe estudiante con el ID dado")
    public void testGetStudentById_Exists() {
        // Datos de prueba
        long id = 1L;
        Student student = new Student("John", "Doe", "john@example.com");

        // Mock del comportamiento del repositorio
        // Usamos optional para evitar valores nulos (student puede ser null) y que no explote todo.
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        // Ejecutar el método a probar
        Student result = studentService.getStudentById(id);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(student, result);
    }

    // Este decorador significa que este metodo es un test unitario.
    @Test
    @DisplayName("No existe estudiante con el ID dado")
    public void testGetStudentById_NotExists() {
        // Datos de prueba
        long id = 1L;

        // Mock del comportamiento del repositorio
        // Optional.empty() significa que no existe un elemento con el ID dado.
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecutar el método a probar
        Student result = studentService.getStudentById(id);

        // Verificar que el resultado esta bien, es decir, el usuario es null (no existe)
        assertNull(result);
    }

    // Este decorador significa que este metodo es un test unitario.
    @Test
    @DisplayName("Guardar estudiante")
    public void testSaveOrUpdateStudent() {
        // Datos de prueba
        Student student = new Student("John", "Doe", "john@example.com");

        // Ejecutar el método a probar
        studentService.saveOrUpdateStudent(student);

        // Verificar que el método save() del repositorio haya sido llamado una vez con el estudiante dado
        verify(studentRepository, times(1)).save(student);
    }

    // Este decorador significa que este metodo es un test unitario.
    @Test
    @DisplayName("Borrar estudiante")
    public void testDeleteStudentById() {
        // Datos de prueba
        long id = 1L;

        // Ejecutar el método a probar
        studentService.deleteStudentById(id);

        // Verificar que el método deleteById() del repositorio haya sido llamado una vez con el ID dado
        verify(studentRepository, times(1)).deleteById(id);
    }
}
