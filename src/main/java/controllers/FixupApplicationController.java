
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FixupApplicationService;
import services.FixupTaskService;
import domain.FixupApplication;
import domain.FixupTask;

@Controller
@RequestMapping(value = "/fixupapplication")
public class FixupApplicationController {

	@Autowired
	private FixupApplicationService	fixupApplicationService;
	@Autowired
	private FixupTaskService		fixupTaskService;


	@RequestMapping(value = "/handyworker/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int id) {
		FixupTask fixupTask = this.fixupTaskService.findById(id);
		FixupApplication application = this.fixupApplicationService.create(fixupTask);

		ModelAndView res = new ModelAndView("fixupApplication/edit");
		res.addObject("fixupApplication", application);
		return res;
	}
}
