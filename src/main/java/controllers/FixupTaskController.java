
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.FixupTaskService;
import domain.Category;
import domain.FixupTask;

@Controller
@RequestMapping(value = "/fixuptask")
public class FixupTaskController {

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CategoryService		categoryService;


	// LIST ALL
	@RequestMapping(value = "/list")
	public ModelAndView listAll() {
		ModelAndView result;

		List<FixupTask> fixupTasks = this.fixupTaskService.findAll();

		result = new ModelAndView("fixuptask/list");
		result.addObject("fixupTasks", fixupTasks);
		result.addObject("user", this.actorService.findPrincipal());

		return result;
	}

	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result = null;

		return result;
	}

	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView result = null;

		return result;
	}

	//VIEW
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int id) {
		ModelAndView result;
		FixupTask fixupTask = this.fixupTaskService.findById(id);

		result = new ModelAndView("fixuptask/list");
		result.addObject("fixupTask", fixupTask);

		return result;
	}

	// THYME LIST TESTS

	// Thymeleaf test

	@RequestMapping(value = "/thyme-list", method = RequestMethod.GET)
	public String list(Model model) {
		List<FixupTask> fixupTasks = this.fixupTaskService.findAll();
		model.addAttribute("fixupTasks", fixupTasks);
		return "fixuptask/table";
	}

	@RequestMapping(value = "/thyme-create", method = RequestMethod.GET)
	public String create(Model model) {

		FixupTask fixupTask = this.fixupTaskService.create();
		List<Category> categories = this.categoryService.findAll();
		model.addAttribute("fixupTask", fixupTask);
		model.addAttribute("categories", categories);
		return "fixuptask/edit";
	}

	@RequestMapping(value = "/thyme-save", method = RequestMethod.POST)
	public String create(Model model, @RequestParam FixupTask fixupTask) {

		FixupTask saved = this.fixupTaskService.initialize(fixupTask);
		List<FixupTask> fixupTasks = this.fixupTaskService.findAll();
		model.addAttribute("fixupTasks", fixupTasks);
		return "fixuptask/table";
	}

}
