
package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.FinderService;
import services.FixupTaskService;
import domain.Finder;
import domain.FixupTask;

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

	@RequestMapping(value = "/fetch", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody
	List<FixupTask> fetchFixupTasks(@RequestParam Finder finder, BindingResult binding) {
		List<FixupTask> res;
		if (binding.hasErrors()) {
			res = new ArrayList<FixupTask>();
		} else {
			try {
				this.finderService.save(finder);
				this.finderService.doSearch(finder);
				return finder.getFixUpTasks();
			} catch (Exception e) {
				res = new ArrayList<>();
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

		res = new ModelAndView("messaging/edit");
		res.addObject("finder", finder);
		res.addObject("message", messageCode);
		return res;
	}
}
