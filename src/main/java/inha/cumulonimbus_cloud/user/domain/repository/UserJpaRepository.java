package inha.cumulonimbus_cloud.user.domain.repository;


import inha.cumulonimbus_cloud.common.BaseEntity;
import inha.cumulonimbus_cloud.user.domain.User;
import inha.cumulonimbus_cloud.user.domain.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndState(String username, BaseEntity.State state);

    boolean existsByUsernameAndSocialTypeAndState(String username, SocialType socialType, BaseEntity.State state);

}
