package com.junitmockito.junitmockito.Student;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    StudentController underTest;

    @Mock
    StudentService studentService;

    @Test
    void itShouldGetAllStudents() {
        //Given
        Student student1 = new Student("Petra", "petra@mail.com",Gender.FEMALE);
        Student student2 = new Student("Marina", "marina@mail.com", Gender.FEMALE);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        //When
        when(studentService.getAllStudents()).thenReturn(students);
        List<Student> result = underTest.getAllStudents();

        //Then
        assertThat(result).isEqualTo(students);
        assertThat(result.get(0).getName()).isEqualTo(students.get(0).getName());
        assertThat(result.get(0).getEmail()).isEqualTo(students.get(0).getEmail());
        assertThat(result.get(0).getGender()).isEqualTo(students.get(0).getGender());
    }

    @Test
    void itShouldAddStudent() {
        // Given
        Student student = new Student("Petra", "petra@mail.com", Gender.FEMALE);
        //When
        underTest.addStudent(student);
        //Then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentService).addStudent(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void itShouldDeleteStudent() {
        // Given
        Student student = new Student(
                "Petra",
                "petra@mail.com",
                Gender.FEMALE
        );
        // When
        underTest.deleteStudent(student.getId());
        // Then

        verify(studentService).deleteStudent(student.getId());
    }


}