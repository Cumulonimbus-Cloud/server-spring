package inha.cumulonimbus_cloud.domain.user.repository;


import inha.cumulonimbus_cloud.common.BaseEntity.State;
import inha.cumulonimbus_cloud.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndState(String username, State state);


}
