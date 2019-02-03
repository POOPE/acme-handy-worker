
package controllers;

import java.util.ArrayList;
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

import services.FixupApplicationService;
import services.FixupTaskService;
import services.HandyWorkerService;
import domain.FixupApplication;
import domain.FixupTask;
import domain.HandyWorker;

@Controller
@RequestMapping(value = "/fixupapplication")
public class FixupApplicationController {

	@Autowired
	private FixupApplicationService	fixupApplicationService;
	@Autowired
	private FixupTaskService		fixupTaskService;
	@Autowired
	private HandyWorkerService		handyWorkerService;


	//VIEW
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int id) {
		ModelAndView res;
		FixupApplication application = this.fixupApplicationService.findById(id);
		res = new ModelAndView("fixupapplication/view");
		res.addObject("fixupApplication", application);
		res.addObject("user", this.handyWorkerService.findPrincipal());
		return res;
	}

	//CREATE
	@RequestMapping(value = "/handyworker/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int id) {
		FixupTask fixupTask = this.fixupTaskService.findById(id);
		FixupApplication application = this.fixupApplicationService.create(fixupTask);

		ModelAndView res = new ModelAndView("fixupapplication/edit");
		res.addObject("fixupApplication", application);
		return res;
	}

	//LIST BY TASK
	@RequestMapping(value = "/customer/bytask", method = RequestMethod.GET)
	public ModelAndView listByTask(@RequestParam final int id) {
		ModelAndView result;
		FixupTask fixupTask = this.fixupTaskService.findById(id);
		Assert.isTrue(!fixupTask.isLocked(), "Fixuptask already locked!");
		List<FixupApplication> applications = this.fixupApplicationService.findByTask(fixupTask);

		result = new ModelAndView("fixupapplication/list");
		result.addObject("fixupApplications", applications);
		result.addObject("fixupTask", fixupTask);
		return result;
	}

	//LIST BY HANDYWORKER
	@RequestMapping(value = "/handyworker/applications", method = RequestMethod.GET)
	public ModelAndView viewByWorker() {
		ModelAndView result;
		HandyWorker handyWorker = this.handyWorkerService.findPrincipal();
		List<FixupApplication> applications = this.fixupApplicationService.findByAuthor(handyWorker);

		result = new ModelAndView("fixupapplication/list");
		result.addObject("fixupApplications", applications);
		result.addObject("user", handyWorker);
		return result;
	}

	@RequestMapping(value = "/handyworker/addcomment", method = RequestMethod.GET)
	public ModelAndView comment(@RequestParam int id, @RequestParam String c) {

		FixupApplication application = this.fixupApplicationService.findById(id);
		Assert.notNull(application);
		ArrayList<String> ros = application.getComments();
		ros.add(c);
		application.setComments(ros);
		this.fixupApplicationService.save(application);

		return new ModelAndView("redirect:/fixupapplication/view.do?id=" + application.getId());

	}

	//CHANGE STATUS
	@RequestMapping(value = "/customer/eval", method = RequestMethod.GET)
	public ModelAndView evaluate(@RequestParam final int appId, @RequestParam final int status) {
		ModelAndView res = null;
		FixupApplication application = this.fixupApplicationService.findById(appId);
		FixupTask task = application.getFixupTask();
		FixupApplication saved = null;
		if (status == 0) {
			saved = this.fixupApplicationService.reject(application);
			res = new ModelAndView("redirect:customer/bytask.do?id=" + task.getId());

		} else if (status == 1) {
			saved = this.fixupApplicationService.accept(application);
			res = new ModelAndView("redirect:/fixuptask/view.do?id=" + task.getId());
		}
		return res;
	}

	//EDIT
	@RequestMapping(value = "/handyworker/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		FixupApplication application = this.fixupApplicationService.findById(id);
		Assert.notNull(application);
		res = this.createEditModelAndView(application, application.getFixupTask());
		return res;
	}

	@RequestMapping(value = "/handyworker/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FixupApplication application, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(application, application.getFixupTask());

		} else {
			try {
				FixupApplication saved = this.fixupApplicationService.save(application);
				res = new ModelAndView("redirect:/fixuptask/view.do?id=" + saved.getFixupTask().getId());
			} catch (Exception e) {
				res = this.createEditModelAndView(application, application.getFixupTask(), "fixupapplication.commit.error");
			}
		}
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(FixupApplication application, FixupTask fixupTask) {
		ModelAndView res;
		res = this.createEditModelAndView(application, fixupTask, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(FixupApplication application, FixupTask fixupTask, String messageCode) {
		ModelAndView res;

		res = new ModelAndView("fixupapplication/edit");
		res.addObject("fixupTask", fixupTask);
		res.addObject("fixupApplication", application);
		res.addObject("message", messageCode);
		return res;
	}
}
