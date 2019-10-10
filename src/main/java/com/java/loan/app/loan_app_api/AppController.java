package com.java.loan.app.loan_app_api;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	private Session session = new Configuration().configure().buildSessionFactory().openSession();
	@Autowired
	private FormRepository formRepository;
	

	@PostMapping(path="/add") 
	public @ResponseBody String addNewForm (@RequestParam String personalId,
											@RequestParam String first_name,
											@RequestParam String last_name,
											@RequestParam Date birth_date,
											@RequestParam String employer,
											@RequestParam int salary,
											@RequestParam int monthly_liability,
											@RequestParam int requested_amount) {
		LoanForm f = new LoanForm();
		f.setPersonalId(personalId);
		f.setFirst_name(first_name);
		f.setLast_name(last_name);
		f.setBirth_date(birth_date);
		f.setEmployer(employer);
		f.setSalary(salary);
		f.setMonthly_liability(monthly_liability);
		f.setRequested_amount(requested_amount);
		f.calculateResult();
		formRepository.save(f);
		return "Saved" + f;
		
	}
	
	
	
	
	@GetMapping("/forms")
    public List<LoanForm> getAllApplications(@RequestParam Map<String, String> params) {
		if(params.size() != 0) {
			SQLQuery query = generateQuery(params);
			List<LoanForm> result = query.list();
			return result;
		}else return (List<LoanForm>) formRepository.findAll();
    }
	//generate query from url params
	private SQLQuery generateQuery(Map<String, String> params) {
		String hql = "from applications where ";
		int i = 0;
		for(String key : params.keySet()) {
			if(i == 0) hql += key + " = " + params.get(key);
			else hql += " and " + key + " = " + params.get(key);
			i++;
		}
		return session.createSQLQuery(hql);
	}




	@GetMapping("/forms/{id}")
	public LoanForm getForm(@PathVariable int id){
		return formRepository.findOne(id);
	}
	
	@PostMapping("/delete/{id}")
	public @ResponseBody String deleteForm(@PathVariable int id) {
		formRepository.delete(id);
		return "deleted";
	}
	@PostMapping("/setResult/{id}/{result}")
	public @ResponseBody String setResult(@PathVariable int id, @PathVariable String result) {
		if(result != "Approved" && result != "Manual" && result != "Rejected") {
			return "incorrect result format";
		}else {
			
			LoanForm f = getForm(id);
			f.setResult(result);
			formRepository.save(f);
			return "result updated to" + result;
		}
	}
	
	
}