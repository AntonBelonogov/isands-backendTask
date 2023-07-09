package ru.isands.BackendTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.repository.ApplianceRepository;

import java.util.HashMap;

@SpringBootTest
class BackendTaskApplicationTests {

	private final ApplianceRepository applianceRepository;

	@Autowired
	public BackendTaskApplicationTests(ApplianceRepository applianceRepository) {
		this.applianceRepository = applianceRepository;
	}

	@Test
	void contextLoads() {
		Appliance appliance = new Appliance();
		appliance.setName("string");
		appliance.setCountry("string");
		appliance.setManufacturer("string");
		appliance.setOnlineOrder(true);
		appliance.setInstallment(true);

		HashMap<String, Object> map = new HashMap<>();
		map.put("a1", "String1");
		map.put("a2", true);

		appliance.setApplianceAttributes(map);

		Appliance outputAppliance = applianceRepository.save(appliance);
		Assertions.assertEquals(2, outputAppliance.getApplianceAttributes().size());


	}

}
