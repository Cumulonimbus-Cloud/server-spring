package inha.cumulonimbus_cloud.chat.domain;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @Column(name = "chat_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "question", nullable = false)
    private String question;
}
