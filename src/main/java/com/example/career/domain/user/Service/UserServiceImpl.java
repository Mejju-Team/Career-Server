package com.example.career.domain.user.Service;

import com.example.career.domain.consult.Repository.ConsultRepository;
import com.example.career.domain.user.Dto.MentorHomeRespDto;
import com.example.career.domain.user.Dto.UserReqDto;
import com.example.career.domain.user.Dto.SignUpReqDto;
import com.example.career.domain.user.Entity.Authority;
import com.example.career.domain.user.Entity.TutorDetail;
import com.example.career.domain.user.Entity.User;
import com.example.career.domain.user.Exception.DuplicateMemberException;
import com.example.career.domain.user.Exception.NotFoundMemberException;
import com.example.career.domain.user.Exception.PasswordWrongException;
import com.example.career.domain.user.Exception.UsernameWrongException;
import com.example.career.domain.user.Repository.TutorDetailRepository;
import com.example.career.domain.user.Repository.UserRepository;
import com.example.career.domain.user.Util.SecurityUtil;
import com.example.career.global.utils.S3Uploader;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    private final ConsultRepository consultRepository;
    private final S3Uploader s3Uploader;
    @Override
    public User signIn(UserReqDto userReqDto) {
        String username = userReqDto.getUsername();
        String passwd = userReqDto.getPassword();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameWrongException(username));

        if (!passwordEncoder.matches(passwd, user.getPassword())) {
            throw new PasswordWrongException();
        }
        return user;
    }
    //    @Override
//    public User signUp(SignUpReqDto signUpReqDto) {
//        String passwd = signUpReqDto.getPassword();
//        signUpReqDto.setPassword(passwordEncoder.encode(passwd));
//        User user = signUpReqDto.toUserEntity();
//        return userRepository.save(user);
//    }
    @Transactional
    @Override
    public SignUpReqDto signup(SignUpReqDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .gender(userDto.getGender())
                .birth(userDto.getBirth())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return SignUpReqDto.from(userRepository.save(user));
    }
    @Transactional(readOnly = true)
    @Override
    public SignUpReqDto getUserWithAuthorities(String username) {
        return SignUpReqDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    @Override
    public SignUpReqDto getMyUserWithAuthorities() {
        return SignUpReqDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

    @Override
    public String uploadProfile(MultipartFile multipartFile) throws IOException {
        String url = s3Uploader.upload(multipartFile, "static/profile");
        System.out.println(url);
        return url;
    }

    @Override
    public List<String> uploadActiceImages(List<MultipartFile> MultipartFile) throws IOException {
        List<String> urlList = s3Uploader.upload(MultipartFile, "static/active");
        for (int i=0; i< urlList.size(); i++) {
            System.out.println(urlList);
        }
        return urlList;
    }

    @Override
    public boolean validUsername(String username) {

        if (userRepository.findByUsername(username)==null) return true;
        return false;
    }

    @Override
    public boolean validNickname(String nickname) {
        if (userRepository.findByNickname(nickname)==null) return true;
        return false;
    }
    @Override
    public boolean validTelephone(String telephone) {
        if (userRepository.findByTelephone(telephone)==null) return true;
        return false;
    }

    @Override
    public MentorHomeRespDto mentorHome() {
        // 유저부터
        Long userid = 10L;
        // 유저 정보부터 가져오자
        MentorHomeRespDto mentorHomeRespDto = new MentorHomeRespDto();
        mentorHomeRespDto = userRepository.findById(userid).get().toHomeDto();

        // Consulting 정보





        return null;
    }
}
