package com.ecommerce.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select c from Address c where c.user.id =?1")
	List<Address> findAddressByUser(long userId);

}
