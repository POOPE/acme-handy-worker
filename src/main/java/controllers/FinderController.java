
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.FinderService;
import services.FixupTaskService;
import domain.Finder;

@Controller
@RequestMapping(value = "/finder")
public class FinderController {

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private FinderService		finderService;


	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView res;
		Finder finder = this.finderService.findPrincipal();
		if (finder == null) {
			finder = this.finderService.create();
		}
		res = new ModelAndView("finder/edit");
		res.addObject("finder", finder);
		res.addObject("fixupTasks", finder.getFixUpTasks());
		return res;
	}

	@RequestMapping(value = "/handyworker/find", method = RequestMethod.POST, params = "save")
	public ModelAndView fetchFixupTasks(@Valid Finder finder, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(finder);
		} else {
			try {
				Finder saved = this.finderService.doSearch(finder);
				res = new ModelAndView("finder/edit");
				res.addObject("finder", saved);
				res.addObject("fixupTasks", saved.getFixUpTasks());
				return res;
			} catch (Exception e) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
			}
		}
		return res;
	}

	protected ModelAndView createEditModelAndView(Finder finder) {
		ModelAndView res;
		res = this.createEditModelAndView(finder, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(Finder finder, String messageCode) {
		ModelAndView res;

		res = new ModelAndView("finder/edit");
		res.addObject("finder", finder);
		res.addObject("message", messageCode);
		return res;
	}
}
