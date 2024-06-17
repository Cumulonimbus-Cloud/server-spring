package inha.cumulonimbus_cloud.chat.domain.repository;

import inha.cumulonimbus_cloud.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<Chat, String> {

}
