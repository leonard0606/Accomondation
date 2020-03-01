package com.demo.Accommodation_Complaints_Feedback_System.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Accommodation_Complaints_Feedback_System.repository.AdminRepository;
import com.demo.Accommodation_Complaints_Feedback_System.repository.StudentRepository;
import com.demo.Accommodation_Complaints_Feedback_System.repository.CustodianRepository;
import com.demo.Accommodation_Complaints_Feedback_System.model.Custodian;
import com.demo.Accommodation_Complaints_Feedback_System.model.HallsOfficer;
import com.demo.Accommodation_Complaints_Feedback_System.repository.HallsOfficerRepository;
import com.demo.Accommodation_Complaints_Feedback_System.model.Complaint;
import com.demo.Accommodation_Complaints_Feedback_System.repository.ComplaintRepository;
import com.demo.Accommodation_Complaints_Feedback_System.model.Admin;
import com.demo.Accommodation_Complaints_Feedback_System.model.Student;


@Service
public class ServiceHalls {
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	AdminRepository arepo;
	
	@Autowired
	ComplaintRepository crepo;
	
	@Autowired
	HallsOfficerRepository hrepo;
	
	@Autowired
	CustodianRepository curepo;
	
	public void saveStudent(Student student) {
	 	srepo.save(student);
	}
	
	public Student getStudent(String uname, String password) {
		return srepo.findByUnameAndPassword(uname, password);
	}
	
	public Admin getAdmin(String uname, String password) {
		return arepo.findByUnameAndPassword(uname, password);
	}
	
	public String saveComplaint(Complaint complaint) {
		String message="";
		
		crepo.save(complaint);
		
		if(crepo.save(complaint)!=null) {
		message="Complaint Submitted Successfully";	
		}
		else {
			message="Please Try Again!";
		}
		return message;
	}
	
	public void saveHallsOfficer(HallsOfficer hallsOfficer) {
	 	hrepo.save(hallsOfficer);
	}
	
	public HallsOfficer getHallsOfficer(String uname, String password) {
		return hrepo.findByUnameAndPassword(uname, password);
	}
	
	public Custodian getCustodian(String uname, String password) {
		return curepo.findByUnameAndPassword(uname, password);
	}
	
	public void saveCustodian(Custodian custodian) {
		curepo.save(custodian);
	}
}
