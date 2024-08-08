package pack.model;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.BuserEntity;

public interface BuserInterface extends JpaRepository<BuserEntity, String> {

}
