package com.example.mreview.repository;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.member.repository.MemberRepository;
import com.example.mreview.domain.review.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertDummyMembers() throws Exception{
        //give
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r" + i + "@gmail.com")
                    .pw("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });
        //when

        //then
    }

    @Test
    @Transactional
    public void deleteMemberTest() throws Exception{
        //give
        Long memberId = 3L;
        Member member = Member.builder().mid(memberId).build();
        //when

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(memberId);

        Optional<Member> findMember = memberRepository.findById(memberId);

        //then
        Assertions.assertThat(findMember.isPresent()).isEqualTo(false);
    }
}