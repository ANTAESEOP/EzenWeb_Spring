package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Member.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 풀 생성자
@Getter // 필드들의 get 메소드 주입
@Setter // 필드들의 set 메소드 주입
@ToString // 객체내 필드 정보 확인 ToString 메소드 주입
@Builder // 객체 생성 안전성 보장 [ 매개변수 개수 / 순서 무관 ]
public class MemberDto implements UserDetails {
    private int mno;
    private String memail;
    private String mpassword;
    private String mphone; // 회원 전화번호 필드
    private Set<GrantedAuthority> authorities; // 인증 권한
            // GrantedAuthority : 권한 [ 토큰 ]
    // * dto ---> entity 변환
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();
    }

    public void setAuthorities(Set<GrantedAuthority> authorities){
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() { return this.mpassword; }

    @Override
    public String getUsername() {
        return this.memail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
