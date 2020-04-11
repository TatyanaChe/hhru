package org.ch.tan;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class vacancies {

	@Before
	public void setup() {
//        RestAssured.baseURI = "https://www.googleapis.com/books/v1";
		RestAssured.baseURI = "https://api.hh.ru/";
		// RestAssured.port = 443;
	}

//	@Test
//	public void whenQueryParamWhenGetVacanciesThenOk() {
//		// Response response = RestAssured.get("/vacancies").prettyPeek();
//
//		given().queryParam("vacancy_search_fields", "qa").when().log().all()
//		.get("/vacancies")
////		.prettyPeek()
//		.then()
//		.statusCode(200);

//	}

	@Test
	public void whenQueryVacancyIdWhenGetVacancyThenOk() {
		// Response response = RestAssured.get("/vacancies").prettyPeek();

		given()
		.pathParam("{vacancy_id}", "4095490")
		.when().log().all().get("/vacancies/{vacancy_id}")
		.prettyPeek()
		.then()
//		.assertThat()
		.body("id", equals("{vacancy_id}"));
//		.statusCode(200);

	}

}
