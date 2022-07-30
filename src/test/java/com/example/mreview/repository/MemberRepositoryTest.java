package com.example.mreview.repository;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

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
}