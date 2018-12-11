
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FixupTaskService;
import domain.FixupTask;

@Controller
@RequestMapping(value = "/fixuptask")
public class FixupTaskController {

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private ActorService		actorService;


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

	@RequestMapping(value = "/thyme-list")
	public ModelAndView tListAll() {
		ModelAndView result;

		List<FixupTask> fixupTasks = this.fixupTaskService.findAll();

		result = new ModelAndView("fixuptask/thyme-list");
		result.addObject("fixupTasks", fixupTasks);

		return result;
	}
}
