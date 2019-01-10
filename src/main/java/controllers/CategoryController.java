
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Category;
import services.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	// LIST ALL
	@RequestMapping(value = "admin/list")
	public ModelAndView listAll() {
		ModelAndView result;

		List<Category> categories = this.categoryService.findAll();

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);

		return result;
	}

	//EDIT
	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		Category category = this.categoryService.findById(id);
		Assert.notNull(category);
		res = this.createEditModelAndView(category);
		return res;
	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Category category, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(category);

		} else {
			try {
				this.categoryService.save(category);
				res = new ModelAndView("redirect:list.do");
			} catch (Exception e) {
				res = this.createEditModelAndView(category, "messagebox.commit.error");
			}
		}
		return res;
	}

	//CREATE
	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Category category = this.categoryService.create();
		res = this.createEditModelAndView(category);

		return res;
	}

	//DELETE
	@RequestMapping(value = "admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView result = null;
		Category category = this.categoryService.findById(id);
		this.categoryService.delete(category);
		result = new ModelAndView("redirect:list.do");
		return result;
	}

	// methods extras

	protected ModelAndView createEditModelAndView(Category category) {
		ModelAndView res;
		res = this.createEditModelAndView(category, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(Category category, String messageCode) {
		ModelAndView res;

		res = new ModelAndView("category/edit");

		res.addObject("category", category);
		res.addObject("message", messageCode);
		return res;
	}
}
