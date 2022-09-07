package project.todoList.after.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.after.domain.Member.Member;
import project.todoList.after.domain.TodoList.OrderTodoList;
import project.todoList.after.domain.TodoList.TodoItem;
import project.todoList.after.domain.TodoList.TodoListToItem;
import project.todoList.after.repository.MemberRepository;
import project.todoList.after.repository.OrderTodoListRepository;
import project.todoList.after.repository.OrderTodoListSearch;
import project.todoList.after.repository.TodoItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderTodoListService {
    private final OrderTodoListRepository orderTodoListRepository;
    private final MemberRepository memberRepository;
    private final TodoItemRepository todoItemRepository;

    @Transactional
    public Long orderTodo(Long memberId, Long todoId, String content) {
        Member member = memberRepository.findOne(memberId);
        TodoItem todoItem = todoItemRepository.findOne(todoId);

        TodoListToItem todoListToItem = TodoListToItem.createOrderTodo(todoItem, content);

        OrderTodoList orderTodoList = OrderTodoList.createTodoOrder(member, todoListToItem);

        orderTodoListRepository.save(orderTodoList);

        return orderTodoList.getId();
    }

    public List<OrderTodoList> findOrders(OrderTodoListSearch orderTodoListSearch) {
        return orderTodoListRepository.findAll(orderTodoListSearch);
    }
}
