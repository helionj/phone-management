package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{
	
	
}
