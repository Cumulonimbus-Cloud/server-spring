package inha.cumulonimbus_cloud.user.domain;

import inha.cumulonimbus_cloud.common.BaseEntity;
import inha.cumulonimbus_cloud.user.domain.enums.Role;
import inha.cumulonimbus_cloud.user.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 20)
    private String username;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, name = "social_type")
    private SocialType socialType;


    @Column(length = 20)
    private String nickname;

    @Column(length = 255, name = "grade_card_url")
    private String gradeCardUrl;

    @Column(nullable = false, name = "has_grade_card")
    private boolean hasGradeCard = false;
    //성적표 업로드한 날짜
    @Column(name = "grade_card_date")
    private String gradeCardDate;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public String getPassword() {
        return password;
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

    public void setGradeCardUrl(String gradCardUrl) {
        this.gradeCardUrl = gradCardUrl;
        this.hasGradeCard = true;
        this.gradeCardDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"));
    }

}
