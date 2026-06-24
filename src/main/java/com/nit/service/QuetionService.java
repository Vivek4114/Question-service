package com.nit.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.Repository.QuetionRepo;
import com.nit.entity.Quetions;
import com.nit.exception.NoQuetionFoundException;

@Service
public class QuetionService {
	
	@Autowired
	private QuetionRepo repo;
	
	public Quetions AddQuetions(Quetions que) {
		Quetions ques = repo.save(que);
		return ques;
		
	}
	
	public Quetions findQuetionById(Integer id) throws NoQuetionFoundException {
		if(repo.existsById(id)) {
		Quetions quetions = repo.findById(id).get();
		return quetions;
		}else {
			throw new  NoQuetionFoundException("Record is not Found");
		}
	}
	
	public List<Quetions> getAllQuetions() throws NoQuetionFoundException{
		if(repo.count() == 0) {
			throw new NoQuetionFoundException("Record is not Found");
		}else {
			return repo.findAll();
		}
	}
	
	public Quetions updateQuetion(Integer id, Quetions quetions) throws NoQuetionFoundException {
		 
			Quetions quetions2 = findQuetionById(id);
			quetions2.setQuetion(quetions.getQuetion());
			quetions2.setOpt1(quetions.getOpt1());
			quetions2.setOpt2(quetions.getOpt2());
			quetions2.setOpt3(quetions.getOpt3());
			quetions2.setOpt4(quetions.getOpt4());
			quetions2.setCorrectAns(quetions.getCorrectAns());	
			quetions2.setCateogary(quetions.getCateogary());
			quetions2.setLevel(quetions.getLevel());
			return repo.save(quetions2);
			
			 
	}

	public String deleteQuetionById(Integer id) throws NoQuetionFoundException {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return "record is deleted Successfully";
		}
		throw new NoQuetionFoundException("Record is not found");
		
	}
	
	
	public List<Quetions> findQuetionByCategory(String category){
		return  repo.findByCateogary(category);
	}
	
	public List<Quetions> findQuetiosByLevel(String Level){
		return repo.findByLevel(Level);
	}

	 

	public List<Integer> findRandomQuestionsByCategoryAndLevel(String category, String level, Integer count) {
	    List<Quetions> questions = repo.findByCategoryAndLevel(category, level);
	    if (questions.size() < count) {
	        throw new IllegalArgumentException("Not enough questions available. Required: " + count + ", found: " + questions.size());
	    }
	    Collections.shuffle(questions);
	    return questions.stream()
	            .limit(count)
	            .map(Quetions::getId)
	            .collect(Collectors.toList());
	}

	public List<String> getCorrectAnswers(List<Integer> questionIds) {
	    // Fetch all questions
	    List<Quetions> questions = repo.findAllById(questionIds);
	    // Convert to a Map for quick lookup by ID
	    Map<Integer, Quetions> questionMap = questions.stream()
	            .collect(Collectors.toMap(Quetions::getId, Function.identity()));
	    
	    // Build the answer list in the exact order of questionIds
	    return questionIds.stream()
	            .map(id -> {
	                Quetions q = questionMap.get(id);
	                if (q == null) return "";
	                int correct = q.getCorrectAns();
	                switch (correct) {
	                    case 1: return q.getOpt1();
	                    case 2: return q.getOpt2();
	                    case 3: return q.getOpt3();
	                    case 4: return q.getOpt4();
	                    default: return "";
	                }
	            })
	            .collect(Collectors.toList());
	}
	 	 
}
