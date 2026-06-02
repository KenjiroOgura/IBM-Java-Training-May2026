package org.eclipse.jakarta.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportDtoTest {
	
	ReportRepository rp = null;
	ReportDto rep1 = null;
	ReportDto rep2 = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ReportDto empty = new ReportDto();
		rp = new ReportRepository();
		rep1 = new ReportDto(1L,"Title1","Detail1");
		rep2 = new ReportDto(2L,"Title2","Detail2");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test() {
		
	}

	

}
