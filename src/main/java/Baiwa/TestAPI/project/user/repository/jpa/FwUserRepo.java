package Baiwa.TestAPI.project.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Baiwa.TestAPI.project.user.model.FwUser;

@Repository
public interface FwUserRepo extends JpaRepository<FwUser, Long> {

	FwUser findByUsername(String username);
}
