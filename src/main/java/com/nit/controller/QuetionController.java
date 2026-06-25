package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.nit.entity.Quetions;
import com.nit.exception.NoQuetionFoundException;
import com.nit.service.QuetionService;

@RestController
@RequestMapping("/quetions")
@CrossOrigin(origins = "https://quiz-frontend-coral.vercel.app/")
public class QuetionController {
	
	@Autowired
	private QuetionService service;
		
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> generateQuestions(
	        @RequestParam String category,
	        @RequestParam String difficulty,
	        @RequestParam Integer count) {

	    List<Integer> ids = service.findRandomQuestionsByCategoryAndLevel(category, difficulty, count);
	    return ResponseEntity.ok(ids);
	}

	 
	@GetMapping("/correctans")
	public ResponseEntity<List<String>> getCorrectAnswers(@RequestParam List<Integer> questionIds) {
	    List<String> answers = service.getCorrectAnswers(questionIds);
	    return ResponseEntity.ok(answers);
	}
	
	@PostMapping("/add")
	public  ResponseEntity<Quetions> AddQuetions(@RequestBody Quetions quetion ) {
		 Quetions addQuetions = service.AddQuetions(quetion);
		 return new ResponseEntity<Quetions>(addQuetions, HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<Quetions> findQuetionById(@RequestParam Integer id) throws NoQuetionFoundException {
		 
		Quetions quetionById = service.findQuetionById(id);
		return new ResponseEntity<Quetions>(quetionById , HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Quetions>> getAllQuetions() throws NoQuetionFoundException{
		List<Quetions> allQuetions = service.getAllQuetions();
		return new ResponseEntity<List<Quetions>>(allQuetions, HttpStatus.OK);		 
	}
	
	@PutMapping("/update")
	public ResponseEntity<Quetions> updateQuetion(@RequestParam Integer id, @RequestBody Quetions quetions) throws NoQuetionFoundException {
		 	Quetions updateQuetion = service.updateQuetion(id, quetions);
		 	return new ResponseEntity<Quetions>(updateQuetion, HttpStatus.OK);			 
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteQuetionById(@RequestParam Integer id) throws NoQuetionFoundException {
		String deleteQuetionById = service.deleteQuetionById(id);
		return new ResponseEntity<String>(deleteQuetionById, HttpStatus.OK);
	}
	
	
	@GetMapping("/getbycategory")
	public ResponseEntity<List<Quetions>> findQuetionByCategory(@RequestParam String category){
		List<Quetions> quetionByCategory = service.findQuetionByCategory(category);
		return new ResponseEntity<List<Quetions>>(quetionByCategory, HttpStatus.OK);
	}
	
	@GetMapping("/getbylevel")
	public ResponseEntity<List<Quetions>> findQuetiosByLevel(@RequestParam String Level){
		List<Quetions> quetiosByLevel = service.findQuetiosByLevel(Level);
		return new ResponseEntity<List<Quetions>>(quetiosByLevel,HttpStatus.OK);
	}

}
