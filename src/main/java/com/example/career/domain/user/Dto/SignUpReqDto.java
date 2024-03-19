package com.example.career.domain.user.Dto;

import com.example.career.domain.user.Entity.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpReqDto {
    private Long id;
    private String name; //
    private String username; //
    private String birth;
    private String nickname;//
    private String telephone; //
    private String password; //
    private String role ="USER"; // USER
    private Boolean gender; //
    private String email;
    private String introduce; //
    private String hobby;
    private Set<AuthorityDto> authorityDtoSet;

    private Boolean isTutor;

    private String profileImg; // MultipartFile

    private String interestingMajor1;
    private String interestingMajor2;
    private String interestingMajor3;
    private String consultMajor1;
    private String consultMajor2;
    private String consultMajor3;

    private String myLife;

    private String plan; // 커리어 모ㅗㄱㄱ표

    private List<SchoolDto> schoolList;

    private List<CareerDto> careerList;

    private List<TagDto> tagList;

    private List<MultipartFile> activeImg;
    private List<FAQ> FAQ;


    public User toUserEntity(Set<Authority> authorities, boolean isSns){

        return User.builder()
                .password(password)
                .name(name)
                .email(email)
                .profileImg(profileImg)
                .username(username)
                .isTutor(isTutor)
                .nickname(nickname)
                .telephone(telephone)
                .gender(gender)
                .birth(birth)
                .role(role)
                .hobby(hobby)
                .status(0)
                .introduce(introduce)
                .authType(1)
                .authorities(authorities)
                .activated(true)
                .isSns(isSns)
                .build();
    }

    public StudentDetail toStudentDetailEntity(Long studentId) {
        return StudentDetail.builder()
                .studentId(studentId)
                .interestingMajor1(interestingMajor1)
                .interestingMajor2(interestingMajor2)
                .interestingMajor3(interestingMajor3)
                .build();
    }
    public TutorDetail toTutorDetailEntity(Long tutorId) {
        return TutorDetail.builder()
                .tutorId(tutorId)
                .consultMajor1(consultMajor1)
                .consultMajor2(consultMajor2)
                .consultMajor3(consultMajor3)
                .myLife(myLife)
                .build();
    }

    public static SignUpReqDto from(User user) {
        if(user == null) return null;

        return SignUpReqDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .isTutor(user.getIsTutor())
                .password(user.getPassword())
                .gender(user.getGender())
                .hobby(user.getHobby())
                .birth(user.getBirth())
                .telephone(user.getTelephone())
                .introduce(user.getIntroduce())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
