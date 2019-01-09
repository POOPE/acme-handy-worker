
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.FixupTaskService;
import services.WarrantyService;
import domain.Actor;
import domain.Category;
import domain.FixupTask;
import domain.Warranty;

@Controller
@RequestMapping(value = "/fixuptask")
public class FixupTaskController {

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private WarrantyService		warrantyService;


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

	//CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		FixupTask fixupTask = this.fixupTaskService.create();

		res = this.createEditModelAndView(fixupTask);
		return res;
	}

	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		FixupTask fixupTask = this.fixupTaskService.findById(id);
		Assert.notNull(fixupTask);
		res = this.createEditModelAndView(fixupTask);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FixupTask fixupTask, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(fixupTask);

		} else {
			try {
				FixupTask saved = this.fixupTaskService.initialize(fixupTask);
				res = new ModelAndView("redirect:view.do?id=" + saved.getId());
			} catch (Exception e) {
				res = this.createEditModelAndView(fixupTask, "messagebox.commit.error");
			}
		}
		return res;
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
		Actor actor = this.actorService.findPrincipal();

		result = new ModelAndView("fixuptask/view");
		result.addObject("fixupTask", fixupTask);
		result.addObject("user", actor);

		return result;
	}

	protected ModelAndView createEditModelAndView(FixupTask fixupTask) {
		ModelAndView res;
		res = this.createEditModelAndView(fixupTask, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(FixupTask fixupTask, String messageCode) {
		ModelAndView res;

		List<Warranty> warranties = this.warrantyService.findAll();
		List<Category> categories = this.categoryService.findAll();

		res = new ModelAndView("fixuptask/edit");
		res.addObject("categories", categories);
		res.addObject("warranties", warranties);
		res.addObject("fixupTask", fixupTask);
		res.addObject("message", messageCode);
		return res;
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
