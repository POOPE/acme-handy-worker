
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

import services.ActorService;
import services.CategoryService;
import services.FixupApplicationService;
import services.FixupTaskService;
import services.WarrantyService;
import services.WorkPlanPhaseService;
import domain.Actor;
import domain.Category;
import domain.FixupApplication;
import domain.FixupTask;
import domain.HandyWorker;
import domain.Warranty;
import domain.WorkPlanPhase;

@Controller
@RequestMapping(value = "/fixuptask")
public class FixupTaskController {

	@Autowired
	private FixupTaskService		fixupTaskService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private CategoryService			categoryService;
	@Autowired
	private WarrantyService			warrantyService;
	@Autowired
	private FixupApplicationService	fixupApplicationService;
	@Autowired
	private WorkPlanPhaseService	workPlanService;


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
		List<WorkPlanPhase> phases = this.workPlanService.findByFixupTask(fixupTask);
		result = new ModelAndView("fixuptask/view");
		result.addObject("fixupTask", fixupTask);
		result.addObject("user", actor);
		result.addObject("workPlanPhases", phases);
		if (fixupTask.isLocked()) {
			FixupApplication application = this.fixupApplicationService.findByStatusForTask("ACCEPTED", fixupTask).get(0);
			HandyWorker worker = application.getAuthor();
			result.addObject("handyWorker", worker);
		}
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

	//WORKPLAN
	@RequestMapping(value = "/handyworker/editphase", method = RequestMethod.GET)
	public ModelAndView editPhase(@RequestParam int id) {
		ModelAndView res;
		WorkPlanPhase phase = this.workPlanService.findById(id);
		Assert.notNull(phase);
		res = new ModelAndView("workplanphase/create");
		res.addObject("workPlanPhase", phase);
		return res;
	}

	@RequestMapping(value = "/handyworker/deletephase", method = RequestMethod.GET)
	public ModelAndView deletePhase(@RequestParam int id) {
		ModelAndView res;
		WorkPlanPhase phase = this.workPlanService.findById(id);
		FixupTask fixupTask = phase.getFixupTask();
		Assert.notNull(phase);
		this.workPlanService.delete(phase);

		res = new ModelAndView("redirect:/view.do?id=" + fixupTask.getId());
		return res;
	}

	@RequestMapping(value = "/handyworker/newphase", method = RequestMethod.GET)
	public ModelAndView newPhase(@RequestParam int id) {
		ModelAndView res;
		FixupTask fixupTask = this.fixupTaskService.findById(id);
		WorkPlanPhase phase = this.workPlanService.create(fixupTask);

		res = new ModelAndView("workplanphase/create");
		res.addObject("workPlanPhase", phase);
		return res;
	}

	@RequestMapping(value = "/handyworker/savephase", method = RequestMethod.POST, params = "save")
	public ModelAndView savePhase(@Valid WorkPlanPhase phase, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = new ModelAndView("workplanphase/create");
			res.addObject("workPlanPhase", phase);

		} else {
			try {
				WorkPlanPhase saved = this.workPlanService.save(phase);
				FixupTask task = saved.getFixupTask();
				res = new ModelAndView("redirect:/view.do?id=" + task.getId());
			} catch (Exception e) {
				res = new ModelAndView("workplanphase/create");
				res.addObject("workPlanPhase", phase);
			}
		}
		return res;
	}

}
