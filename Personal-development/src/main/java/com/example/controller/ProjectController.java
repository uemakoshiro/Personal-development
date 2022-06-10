package com.example.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.CategoryForm;
import com.example.controller.form.CreateUserForm;
import com.example.controller.form.SpendingForm;
import com.example.controller.form.UserForm;
import com.example.entity.Spending;
import com.example.entity.User;
import com.example.service.CategoryService;
import com.example.service.SpendingService;
import com.example.service.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SpendingService spendingService;
	
	@Autowired
	Spending sp;
	
    @Autowired
    HttpSession session;
    
    @RequestMapping({ "/", "/index" })
    public String index(@ModelAttribute("index") UserForm form, Model model) {
        return "index";
    }
    
    @RequestMapping( value="/login", method=RequestMethod.POST)
    public String login(@Validated @ModelAttribute("index") UserForm form,BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
            return "index";
        }
    	String id = form.getLoginId();
    	String pass = form.getPass();
    	
    	User user = userService.loginCheck(id, pass);
    	if(user == null) {
    		model.addAttribute("msg", "不正なログインです");
    		return "index";
    	}else {
    		session.setAttribute("userInfo", user);
    		session.setAttribute("keyword", "6");
//    		session.setAttribute("order", "");
    		model.addAttribute("YearMonthList", spendingService.SelectYearMonth(user.getLoginId()));
    		model.addAttribute("SearchResult", spendingService.SelectDataMonth(user.getLoginId()));
            return "top";
    	}
    }
    
    @RequestMapping( value="/top", method=RequestMethod.GET)
    public String top( Model model) {
    	User user = (User) session.getAttribute("userInfo");
    	model.addAttribute("YearMonthList", spendingService.SelectYearMonth(user.getLoginId()));
		model.addAttribute("SearchResult",spendingService.SelectDataMonth(user.getLoginId(), Integer.parseInt((String)session.getAttribute("keyword"))));
    	return "top";
    }
    
    @RequestMapping( value="/create_user", method=RequestMethod.GET)
    public String createUser(@ModelAttribute("index") CreateUserForm form, Model model) {
    	return "create_user";
    }
    
    @RequestMapping( value="/create_exe", method=RequestMethod.POST)
    public String createExe(@Validated @ModelAttribute("index") CreateUserForm form, BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
            return "create_user";
        }
    	
    	int result = userService.CreateUser(form.getName(), form.getLoginId(), form.getPass());
    	if(result == 1) {
    		model.addAttribute("msg", "アカウントを作成しました");
    	}else {
    		model.addAttribute("msg", "アカウントを作成できませんでした");
    	}
    	return "index";
    	
    }
    
    @RequestMapping( value="/insert", method=RequestMethod.GET)
    public String insert( @ModelAttribute("insert") SpendingForm form,Model model) {
//    	if(session.getAttribute("userInfo") == null) {
//        	return "index";
//    	}
    	User user = (User) session.getAttribute("userInfo");
    	model.addAttribute("categoryList",categoryService.SelectAll(user.getLoginId()));
    	return "insert_data";
    }
    
    @RequestMapping( value="/insert_category", method=RequestMethod.GET)
    public String insertCategory( @ModelAttribute("insert") CategoryForm form,Model model) {
    	return "insert_category";
    }
    
    @RequestMapping( value="/insertCategoryExe", method=RequestMethod.POST)
    public String insertCategoryExe(@Validated @ModelAttribute("insert") CategoryForm form,BindingResult bindingResult, Model model) {
    	
    	User user = (User) session.getAttribute("userInfo");
    	int result = categoryService.Insert(form.getName(),user.getLoginId());
    	if(result == 1) {
    		model.addAttribute("msg", "登録が完了しました");
    	}else {
    		model.addAttribute("msg", "登録出来ませんでした");
    	}
    	return "insert_category";
    }
    	
    
    @RequestMapping( value="/insertexe", method=RequestMethod.POST)
    public String insertExe(@Validated @ModelAttribute("insert") SpendingForm form,BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		User user = (User) session.getAttribute("userInfo");
        	model.addAttribute("categoryList",categoryService.SelectAll(user.getLoginId()));
            return "insert_data";
        }
    	sp.setUserId(((User)session.getAttribute("userInfo")).getLoginId());
    	sp.setCategory(form.getCategory());
    	sp.setExpense(form.getExpense());
    	sp.setDate(form.getDate());
    	sp.setMemo(form.getMemo());
    	int result = spendingService.Insert(sp);
    	if(result == 1) {
    		model.addAttribute("msg", "登録が完了しました");
    	}else {
    		model.addAttribute("msg", "登録出来ませんでした");
    	}
    	User user = (User) session.getAttribute("userInfo");
    	model.addAttribute("categoryList",categoryService.SelectAll(user.getLoginId()));
    	return "insert_data";
    }
    
    @RequestMapping( value="/search", method=RequestMethod.GET)
    public String search(@RequestParam("month") String month, Model model) {
    	session.setAttribute("keyword", month);
    	User user = (User) session.getAttribute("userInfo");
		model.addAttribute("YearMonthList", spendingService.SelectYearMonth(user.getLoginId()));
    	model.addAttribute("SearchResult",spendingService.SelectDataMonth(user.getLoginId(), Integer.parseInt(month)));
    	return "top";
    }
    
    @RequestMapping( value="/detail", method=RequestMethod.GET)
    public String detail(@ModelAttribute("delete") SpendingForm form,@RequestParam("id") int id, Model model) {
    	model.addAttribute("info",spendingService.SelectId(id));
    	form.setId(id);
    	return "detail";
    }
    
    @RequestMapping( value="/delete", method=RequestMethod.POST)
    public String delete(@ModelAttribute("delete") SpendingForm form, Model model) {
    	int result = spendingService.Delete(form.getId());
    	if(result == 1) {
    		model.addAttribute("msg", "削除しました");
    	}else {
    		model.addAttribute("msg", "削除出来ませんでした");
    	}
    	User user = (User) session.getAttribute("userInfo");
		model.addAttribute("YearMonthList", spendingService.SelectYearMonth(user.getLoginId()));
		model.addAttribute("SearchResult", spendingService.SelectDataMonth(user.getLoginId()));
    	return "top";
    }
    
    @RequestMapping( value="/update", method=RequestMethod.GET)
    public String update(@ModelAttribute("update") SpendingForm form, Model model) {
    	sp = spendingService.SelectId(form.getId());
    	form.setDate(sp.getDate());
    	form.setCategory(sp.getCategory());
    	form.setExpense(sp.getExpense());
    	form.setMemo(sp.getMemo());
    	User user = (User) session.getAttribute("userInfo");
    	model.addAttribute("categoryList",categoryService.SelectAll(user.getLoginId()));
    	return "updateInput";
    }
    
    @RequestMapping( value="/updateexe", method=RequestMethod.POST)
    public String updateexe(@ModelAttribute("update") SpendingForm form, Model model) {
    	sp.setDate(form.getDate());
    	sp.setCategory(form.getCategory());
    	sp.setExpense(form.getExpense());
    	sp.setMemo(form.getMemo());
    	sp.setId(form.getId());
    	int result = spendingService.Update(sp);
    	if(result == 1) {
    		model.addAttribute("msg","更新しました");
    	}else {
    		model.addAttribute("msg","更新出来ませんでした");
    	}
    	User user = (User) session.getAttribute("userInfo");
    	model.addAttribute("categoryList",categoryService.SelectAll(user.getLoginId()));
    	return "updateInput";
    }
    
    @RequestMapping({ "/logout" })
    public String index( Model model) {
    	session.invalidate();
        return "logout";
    }
    
    @RequestMapping( value="/graph", method=RequestMethod.GET)
    public String graph(Model model) {
    	User user = (User)session.getAttribute("userInfo");
    	ArrayList<Spending> graphData = spendingService.GetGraphData(user.getLoginId(),Integer.parseInt((String)session.getAttribute("keyword")));
    	ArrayList<String> labelData = new ArrayList<>();
    	ArrayList<Integer> sumAmountData = new ArrayList<>();
    	for(Spending sp : graphData) {
    		labelData.add(sp.getCategoryName());
    	}
    	for(Spending sp : graphData) {
    		sumAmountData.add(sp.getSumAmount());
    	}
    	model.addAttribute("labelData", labelData);
    	model.addAttribute("sumAmountData", sumAmountData);
    	return "graph";
    }
    
    
}
