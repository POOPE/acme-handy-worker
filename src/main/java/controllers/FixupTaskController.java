
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.FixupTaskService;
import domain.FixupTask;

@Controller
@RequestMapping(value = "/fixuptask")
public class FixupTaskController {

	@Autowired
	private FixupTaskService	fixupTaskService;


	@RequestMapping(value = "/list")
	public ModelAndView listAll() {
		ModelAndView result;

		List<FixupTask> fixupTasks = this.fixupTaskService.findAll();

		result = new ModelAndView("fixuptask/list");
		result.addObject("fixupTasks", fixupTasks);

		return result;
	}
}
