package project.todoList.after.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.after.domain.Diary.DiaryItem;
import project.todoList.after.domain.Diary.DiaryListToItem;
import project.todoList.after.domain.Diary.OrderDiaryList;
import project.todoList.after.domain.Member.Member;
import project.todoList.after.repository.DiaryItemRepository;
import project.todoList.after.repository.MemberRepository;
import project.todoList.after.repository.OrderDiaryListRepository;
import project.todoList.after.repository.OrderDiaryListSearch;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderDiaryListService {
    private final OrderDiaryListRepository orderDiaryListRepository;
    private final MemberRepository memberRepository;
    private final DiaryItemRepository diaryItemRepository;

    //주문
    @Transactional
    public Long orderDiary(Long memberId, Long diaryId, String content){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        DiaryItem diaryItem = diaryItemRepository.findOne(diaryId);

        //주문상품 생성
        DiaryListToItem diaryListToItem = DiaryListToItem.createOrderDiary(diaryItem, content);

        //주문 생성
        OrderDiaryList orderDiaryList = OrderDiaryList.createDiaryOrder(member, diaryListToItem);

        //주문 저장
        orderDiaryListRepository.save(orderDiaryList);

        return orderDiaryList.getId();
    }

    //    검색
    public List<OrderDiaryList> findOrders(OrderDiaryListSearch orderDiaryListSearch){
        return orderDiaryListRepository.findAll(orderDiaryListSearch);
    }
}