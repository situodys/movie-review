package com.example.mreview.domain.member;

import com.example.mreview.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "m_member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String email;
    private String pw;
    private String nickname;

}
