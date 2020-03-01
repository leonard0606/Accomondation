package com.demo.Accommodation_Complaints_Feedback_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Accommodation_Complaints_Feedback_System.dao.ServiceHalls;
import com.demo.Accommodation_Complaints_Feedback_System.model.Admin;
import com.demo.Accommodation_Complaints_Feedback_System.model.Student;
import com.demo.Accommodation_Complaints_Feedback_System.model.HallsOfficer;
import com.demo.Accommodation_Complaints_Feedback_System.model.Complaint;
import com.demo.Accommodation_Complaints_Feedback_System.model.Custodian;

@Controller
public class HallsController {
	
	@Autowired
	ServiceHalls service;
	
	@RequestMapping("/")
	public String getUser() {
		return "login.jsp";
	}
	
	@PostMapping("/studentRegistration")
	String addStudent(Student student) {
		
		service.saveStudent(student);
		return "adminUI.jsp";
	}
	
	@PostMapping("/login")
	public String validate(@RequestParam String category, @RequestParam String uname, @RequestParam String password, Model model){	
		   
		 switch (category) {  
		 case "Admin":  
			 Admin admin = service.getAdmin(uname, password);
				
				if(admin!=null) {
					model.addAttribute("admin", admin);
					return "adminUI.jsp";
				}
				else {
					return "login.jsp";
				}
		  case "Student":  
			Student student=service.getStudent(uname, password);
			
			if(student!=null) {
				model.addAttribute("student", student);
				return "studentUI.jsp";
			}
			else {
				return "login.jsp";
			}
		   case "Halls Officer":  
				 HallsOfficer hallsOfficer = service.getHallsOfficer(uname, password);
					
					if(hallsOfficer!=null) {
						model.addAttribute("hallsOfficer", hallsOfficer);
						return "hallsOfficerUI.jsp";
					}
					else {
						return "login.jsp";
					}
		   case "Custodian":  
				 Custodian custodian = service.getCustodian(uname, password);
					
					if(custodian!=null) {
						model.addAttribute("custodian", custodian);
						return "custodianUI.jsp";
					}
					else {
						return "login.jsp";
					}
		   default:  
		     return "login.jsp";  
	}
	}
	
	@PostMapping("/submitComplaint")
	public String submitComplaint(@RequestParam String complaintTitle,
			@RequestParam String complaintDescription,@RequestParam String fname,
			@RequestParam String lname,
			@RequestParam String regNo, @RequestParam String phoneNumber, @RequestParam String hostel, @RequestParam String block, 
			@RequestParam String roomNumber
			,Model model)
	{
		
		Complaint complaint =new Complaint();
		complaint.setComplaintTitle(complaintTitle);
		complaint.setComplaintDescription(complaintDescription);
		complaint.setFname(fname);
		complaint.setLname(lname);
		complaint.setRegNo(regNo);
		complaint.setPhoneNumber(phoneNumber);
		complaint.setHostel(hostel);
		complaint.setBlock(block);
		complaint.setRoomNumber(roomNumber);
		
		String message=service.saveComplaint(complaint);
		model.addAttribute("message", message);
		model.addAttribute("complaint", complaint);
		return "studentUI.jsp";
	}
	
	@PostMapping("/hallsOfficerRegister")
	String addHallsOfficer(HallsOfficer hallsOfficer) {
		
		service.saveHallsOfficer(hallsOfficer);
		return "hallsOfficerRegistration.jsp";
	}
	
	@PostMapping("/custodianRegister")
	String addCustodian(Custodian custodian) {
		
		service.saveCustodian(custodian);
		return "custodianRegistration.jsp";
	}
}
