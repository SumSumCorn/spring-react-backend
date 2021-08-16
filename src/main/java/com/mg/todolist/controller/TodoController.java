package com.mg.todolist.controller;

import com.mg.todolist.domain.Todo;
import com.mg.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public Result getTodos() {
        List<Todo> todos = todoService.getTodos();

        List<MemberDTO> collect = todos.stream()
                .map(m -> new MemberDTO(m.getId(),m.getTodoName(),m.getCompleted()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping
    public CreateTodoResponse createTodo(@RequestBody @Valid CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.init(request.getName());

        Long id = todoService.insertTodo(todo);

        return new CreateTodoResponse(id);
    }

    @PutMapping("/{todoId}")
    public UpdateTodoResponse updateTodo(@PathVariable("todoId") Long todoId) {
        Todo todo = todoService.updateTodo(todoId);
        return new UpdateTodoResponse(todo.getTodoName(),todo.getCompleted());
    }


    @DeleteMapping("/{todoId}")
    public DeleteTodoResponse deleteTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return new DeleteTodoResponse("" + todoId +  " delete success");
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private Long id;
        private String name;
        private Boolean completed;
    }

    @Data
    static class CreateTodoRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class CreateTodoResponse{
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class UpdateTodoResponse{
        private String todoName;
        private Boolean completed;
    }

    @Data
    @AllArgsConstructor
    static class DeleteTodoResponse{
        private String message;
    }
}
