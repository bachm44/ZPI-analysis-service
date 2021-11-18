package com.zpi.infrastructure.analysis;

import com.zpi.domain.analysis.request.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER_INFO")
class UserTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true, nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<RequestTuple> requests = new HashSet<>();

    UserTuple(User user) {
        this.username = user.username();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserTuple userTuple = (UserTuple) o;
        return username != null && Objects.equals(username, userTuple.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public User toDomain() {
        return new User(username);
    }
}