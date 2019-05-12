package io.github.iodar.persistence.repos;

import io.github.iodar.persistence.entities.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserDbo, Long> {
    List<UserDbo> findByVorname(final String nachname);

    List<UserDbo> findByNachname(final String vorname);

    List<UserDbo> findByNachnameAndVorname(final String nachname, final String vorname);

}
