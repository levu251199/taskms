package com.taskms.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taskms.dao.AccountDAO;
import com.taskms.dao.TaskDAO;
import com.taskms.entity.Account;
import com.taskms.entity.Task;
import com.taskms.service.CookieService;

@Controller
public class TaskController {

	@Autowired
	TaskDAO taskDAO;

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	// Show All Task
	@RequestMapping("/task/mytask")
	public String listAll(Model model) {
		Account account = (Account) session.getAttribute("account");
		List<Task> list = taskDAO.findAll(account.getUsername());
		model.addAttribute("taskList", list);
		return "task/task";
	}
	
	// Add New Task
	// ROUTE
	@RequestMapping("/task/add")
	public String addTask(Model model, @ModelAttribute("entity") Task task) {
		model.addAttribute("entity", task);
		return "task/add";
	}
	
	// FORM
	@RequestMapping("/task/create")
	public String createTask(Model model, @ModelAttribute("entity") Task task) {
		taskDAO.create(task);
		model.addAttribute("message", "Thêm thành công");
		return "redirect:/task/mytask";
	}
	
	// Update Task
//	@RequestMapping("/task/edit/{id}")
//	public String editTask(Model model, @PathVariable("id") Integer id) {
//		Task task = taskDAO.findByID(id);
//		model.addAttribute("task", task);
//		return "task/edit";
//	}
	
	/* UPDATE ACCOUNT INFO */
	// ROUTE
	@RequestMapping("/task/edit/{id}")
	public String editTask(Model model, @PathVariable("id") Integer id) {
		Task task = taskDAO.findByID(id);
		model.addAttribute("form", task);
		model.addAttribute("task", task);
		return "task/edit";
	}

	// FORM
	@RequestMapping("/task/update")
	public String updateTask(Model model, @ModelAttribute("form") Task task) {
		taskDAO.update(task);
		model.addAttribute("message", "Cập nhật thông tin thành công");
		return "redirect:/task/mytask";
	}

	// Delete Done Task
	@RequestMapping("/task/done/{id}")
	public String doneTask(Model model, @PathVariable("id") Integer id) {
		Task task = taskDAO.delete(id);
		model.addAttribute("task", task);
		return "redirect:/task/mytask";
	}

	// Delete Important Done Task
	@RequestMapping("/task/important-done/{id}")
	public String doneImportantTask(Model model, @PathVariable("id") Integer id) {
		Task task = taskDAO.delete(id);
		model.addAttribute("task", task);
		return "redirect:/task/important";
	}

	// Find Task By Keyword
	@RequestMapping("/task/search")
	public String listByKeyword(Model model, @RequestParam("keyword") String keyword) {
		Account account = (Account) session.getAttribute("account");
		List<Task> list = taskDAO.findByKeyword(keyword, account.getUsername());
		model.addAttribute("taskList", list);
		return "task/task";
	}

	// Add Important Task
	@ResponseBody
	@RequestMapping("/task/important/{id}")
	public boolean addImportantTask(Model model, @PathVariable("id") Integer id) {
		Cookie important = cookie.readCookie("important");
		String value = id.toString();
		if (important != null) {
			value = important.getValue();
			if (!value.contains(id.toString())) {
				value += "," + id.toString();
			} else {
				return false;
			}
		}
		important = cookie.createCookie("important", value, 30);
		return true;
	}

	// Show Important Task
	@RequestMapping("/task/important")
	public String importantTask(Model model) {
		Cookie important = cookie.readCookie("important");
		Account account = (Account) session.getAttribute("account");
		if (important != null) {
			String ids = important.getValue();
			List<Task> importantTasks = taskDAO.findImportant(ids, account.getUsername());
			model.addAttribute("importantTasks", importantTasks);
		}
		return "task/important";
	}

}
