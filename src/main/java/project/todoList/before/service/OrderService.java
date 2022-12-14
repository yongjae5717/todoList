package project.todoList.before.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.before.repository.DiaryItemRepository;
import project.todoList.before.repository.MemberRepository;
import project.todoList.before.repository.OrderRepository;
import project.todoList.before.repository.TodoItemRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final DiaryItemRepository diaryItemRepository;
    private final TodoItemRepository todoItemRepository;

//    //다이어리 주문
//    public Long orderDiaryItem(Long memberId, Long diaryItemId, String content){
//        //엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//        DiaryItem diaryItem = diaryItemRepository.findOne(diaryItemId);
//
//        //다이어리 생성
//    }
}