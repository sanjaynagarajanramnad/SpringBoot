package service;

import com.example.demo.TodoRepository;
import com.example.demo.TodoService;
import com.example.demo.models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo();
        todo.setId(1);
        todo.setTitle("Learn Spring");
        todo.setDescription("Complete JUnit Testing");
    }

    @Test
    void testCreateTodo() {

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo savedTodo = todoService.Create(todo);

        assertNotNull(savedTodo);
        assertEquals("Learn Spring", savedTodo.getTitle());

        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void testGetAllTodos() {

        List<Todo> todos = Collections.singletonList(todo);

        when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();

        assertEquals(1, result.size());
        assertEquals("Learn Spring", result.getFirst().getTitle());

        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testDeleteTodo() {

        doNothing().when(todoRepository).deleteById(1);

        todoService.del(1);

        verify(todoRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateTodo() {

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle("Updated Title");
        updatedTodo.setDescription("Updated Description");

        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoService.update(1, updatedTodo);

        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());

        verify(todoRepository).findById(1);
        verify(todoRepository).save(todo);
    }

    @Test
    void testUpdateTodo_NotFound() {

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle("New");
        updatedTodo.setDescription("Desc");

        when(todoRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> todoService.update(1, updatedTodo)
        );

        assertEquals("Todo not found", exception.getMessage());

        verify(todoRepository).findById(1);
        verify(todoRepository, never()).save(any());
    }
}