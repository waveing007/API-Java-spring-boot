package Baiwa.TestAPI.project.repo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import Baiwa.TestAPI.project.model.ShrimpModel;

public interface ShrimpRepository  extends JpaRepository<ShrimpModel, Long> {

}
