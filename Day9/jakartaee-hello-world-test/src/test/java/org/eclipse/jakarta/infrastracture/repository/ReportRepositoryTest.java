package org.eclipse.jakarta.infrastracture.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

class ReportRepositoryTest {
	
	ReportRepository rp = null;
	ReportDto rep1 = null;
	ReportDto rep2 = null;
	@BeforeEach
	void setUp() throws Exception {
		ReportDto empty = new ReportDto();
		rp = new ReportRepository();
		rep1 = new ReportDto(1L,"Title1","Detail1");
		rep2 = new ReportDto(2L,"Title2","Detail2");
		rp.create(rep1);
		rp.create(rep2);
	}

	@AfterEach
	void tearDown() throws Exception {
		rp = null;
		rep1 =null;
		rep2 = null;
	}
	
	@Test
	void test() {
		List<ReportDto> test = rp.findAll();
		assertEquals(rep1.getId(),test.get(0).getId());
	}
	
	@Test
	void testView() {
		ReportDto test = rp.findById(1L);
		assertEquals(rep1.getId(),test.getId());
		assertEquals(rep1.getTitle(),test.getTitle());
		assertEquals(rep1.getDetail(),test.getDetail());
		assertNull(rp.findById(null));
		assertNull(rp.findById(100L));
	}
	
	@Test
	void testUpdate() {
		ReportDto test = new ReportDto(3L,"Title Not updated","Detail Not Updated");
		rp.create(test);
		rp.update(3L, "Title updated", "Detail updated");
		ReportDto actual = rp.findById(3L);
		assertEquals("Title updated", actual.getTitle());
	}
	
	@Test
	void testDelete() {
		ReportDto test = new ReportDto(3L,"Delete me","Please");
		rp.create(test);
		rp.delete(3L);
		assertNull(rp.findById(3L));
	}
	
	@Test
	void testDeleteAll() {
		rp.deleteAll();
		rp.update(null, null, null);
		assertNull(rp.findById(2L));
	}
}
