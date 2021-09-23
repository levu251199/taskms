package com.taskms.controller;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskms.bean.MailInfo;
import com.taskms.dao.AccountDAO;
import com.taskms.entity.Account;
import com.taskms.service.CookieService;
import com.taskms.service.MailService;

@Controller
public class AccountController {

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@Autowired
	MailService mailer;

	@Autowired
	HttpServletRequest request;

	/* LOGIN */
	// GET
	@GetMapping("/user/login")
	public String login(Model model) {
		Cookie ckid = cookie.readCookie("accountID");
		Cookie ckpw = cookie.readCookie("accountPwd");
		if (ckid != null) {
			String username = ckid.getValue();
			String password = ckpw.getValue();
			model.addAttribute("username", username);
			model.addAttribute("password", password);
		}
		return "user/login";
	}

	// POST
	@PostMapping("/user/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value = "remember", defaultValue = "false") boolean remember) {
		Account account = accountDAO.findbyUsername(username);
		if (account == null) {
			model.addAttribute("message", "Sai thông tin");
		} else if (!username.equals(account.getUsername())) {
			model.addAttribute("message", "Sai tên người dùng");
		} else if (!password.equals(account.getPassword())) {
			model.addAttribute("message", "Sai mật khẩu");
		} else if (!account.getActivated()) {
			model.addAttribute("message", "Tài khoản của bạn chưa được kích hoạt");
		} else {
			model.addAttribute("message", "Đăng nhập thành công");
			session.setAttribute("account", account);
			// Remember by Cookies
			if (remember) {
				cookie.createCookie("accountID", account.getUsername(), 30);
				cookie.createCookie("accountPwd", account.getPassword(), 30);
			} else {
				cookie.deleteCookie("accountID");
				cookie.deleteCookie("accountPwd");
			}
			String backURI = (String) session.getAttribute("back-uri");
			if (backURI != null) {
				return "redirect:" + backURI;
			}
		}
		return "redirect:/task/mytask";

	}

	/* LOGOUT */
	@RequestMapping("/user/logout")
	public String logout(Model model) {
		session.removeAttribute("account");
		return "redirect:/task/mytask";
	}

	/* REGISTER */
	// GET
	@GetMapping("/user/register")
	public String register(Model model) {
		Account account = new Account();
		model.addAttribute("form", account);
		return "/user/register";
	}

	// POST
	@PostMapping("/user/register")
	public String register(Model model, @ModelAttribute("form") Account account) throws MessagingException {
		account.setActivated(false);
		accountDAO.create(account);
		model.addAttribute("message", "Đăng ký thành công, chào mừng đến với TaskMS");
		// Mail verify
		String url = request.getRequestURL().toString().replace("register", "activated/" + account.getUsername());
		String from = "service025897@gmail.com";
		String to = account.getEmail();
		String subject = "Thank you for registed TaskMS";
		String body = "<a href='" + url + "'> Please verify your account here </a>";
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailer.send(mail);
		return "redirect:/user/verified";
	}

	/* CONFIRM ACCOUNT VERIFIED */
	@GetMapping("/user/activated/{id}")
	public String activated(Model model, @PathVariable("id") String id) {
		Account account = accountDAO.findbyUsername(id);
		account.setActivated(true);
		accountDAO.update(account);
		model.addAttribute("form", account);
		return "redirect:/user/login";
	}

	/* FORGOT PASSWORD */
	// GET
	@GetMapping("/user/forgot")
	public String forgotPassword(Model model) {
		return "user/forgot";
	}

	// POST
	@PostMapping("/user/forgot")
	public String forgotPassword(Model model, @RequestParam("username") String username,
			@RequestParam("email") String email) throws MessagingException {
		Account account = accountDAO.findbyUsername(username);
		if (account == null) {
			model.addAttribute("message", "Sai tên người dùng, vui lòng thử lại");
		} else if (!email.equals(account.getEmail())) {
			model.addAttribute("message", "Sai email, vui lòng thử lại");
		} else {
			String from = "service025897@gmail.com";
			String to = account.getEmail();
			String subject = "TaskMS | Reset your password";
			String body = "Your password is" + account.getPassword() + "";
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailer.send(mail);
			model.addAttribute("message", "Đã gửi password qua email");
		}
		return "redirect:/user/login";
	}

	/* CHANGE PASSWORD */
	// GET
	@GetMapping("/user/change")
	public String changePassword(Model model) {
		return "user/change-pass";
	}

	// POST
	@PostMapping("/user/change")
	public String changePassword(Model model, @RequestParam("username") String username,
			@RequestParam("old-password") String oldPassword, @RequestParam("new-password") String newPassword,
			@RequestParam("retype-password") String retypePassword) throws MessagingException {
		// Check retype password
		if (!newPassword.equals(retypePassword)) {
			model.addAttribute("message", "Nhập lại mật khẩu không khớp, vui lòng thử lại");
		} else {
			Account account = accountDAO.findbyUsername(username);
			if (account == null) {
				model.addAttribute("message", "Sai tên người dùng, vui lòng thử lại");
			} else if (!oldPassword.equals(account.getPassword())) {
				model.addAttribute("message", "Sai mật khẩu, vui lòng thử lại");
			} else {
				account.setPassword(newPassword);
				accountDAO.update(account);
				model.addAttribute("message", "Đổi mật khẩu thành công");
			}
		}
		return "redirect:/user/login";
	}

	/* UPDATE ACCOUNT INFO */
	// GET
	@GetMapping("/user/update")
	public String update(Model model) {
		Account account = (Account) session.getAttribute("account");
		model.addAttribute("form", account);
		return "/user/update";
	}

	// POST
	@PostMapping("/user/update")
	public String update(Model model, @ModelAttribute("form") Account account) {
		accountDAO.update(account);
		session.setAttribute("account", account);
		model.addAttribute("message", "Cập nhật thông tin thành công");
		return "redirect:/task/mytask";
	}
}
