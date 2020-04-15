package org.ch.tan;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.ch.tan.hh.model.Vacancy;

import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VacanciesTest {

	@Before
	public void setup() {
//        RestAssured.baseURI = "https://www.googleapis.com/books/v1";
		RestAssured.baseURI = "https://api.hh.ru/";
		// RestAssured.port = 443;
	}

	@Test
	public void whenQueryParamWhenGetVacanciesThenOk() throws IOException {
		// Response response = RestAssured.get("/vacancies").prettyPeek();
//
		String json = given().queryParam("text", "qa engineer")
//				
				.queryParam("schedule", "remote")
//				
				.when().log().all().get("/vacancies")

//		.prettyPeek()
				.body().asString();
//		.then().statusCode(200);

		FileUtils.write(new File("shedule_remote.json"), json, "UTF-8");

	}

	@Test
	public void whenQueryVacancyDateThenCount() throws JsonParseException, JsonMappingException, IOException, ParseException {

//		Date date = new Date();

		Calendar date = Calendar.getInstance();
		System.out.println("date = " + date);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date ddd = df.parse("2020-04-14");
		System.out.println("ddd: " + ddd);
//		LocalDate localDate = DateWithoutTime.getLocalDate();
		
		Response response = given().pathParam("vacancy_id", "36460145")
				.when()
//				.log().all()
				.get("/vacancies/{vacancy_id}");
//				.prettyPeek();
		String json = response.getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Vacancy vac = objectMapper.readValue(json, Vacancy.class);  
		Date tt = vac.getCreated_at();
		System.out.println("created_at: " + tt);
//				.pathParam("", arguments);
//		String dateStr = response.body("created_at", is("36460145")).asString();		
		
//		Response response = given().queryParam("text", "qa engineer")
//		.queryParam("schedule", "remote")
//		.queryParam("created_at", "remote")
//		.when().log().all()
//		.get("/vacancies");

	}

	@Test
	public void whenQueryVacancyIdWhenGetVacancyThenOk() {
		given().pathParam("vacancy_id", "36460145").when().log().all().get("/vacancies/{vacancy_id}").prettyPeek()
				.then().body("id", is("36460145")).statusCode(200);

	}
}
