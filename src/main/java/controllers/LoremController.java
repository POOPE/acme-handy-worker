
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

import services.CustomerService;
import services.FixupTaskService;
import services.LoremService;
import domain.Customer;
import domain.FixupTask;
import domain.Lorem;

@Controller
@RequestMapping(value = "/lorem")
public class LoremController {

	@Autowired
	private LoremService		loremService;
	@Autowired
	private FixupTaskService	fixupTaskService;
	@Autowired
	private CustomerService		customerService;


	// LISTS
	//list all
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView res;
		List<Lorem> lorems = this.loremService.findAllFinal();
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	//list by author
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listByAuthor() {
		ModelAndView res;
		List<Lorem> lorems = this.loremService.findByAuthor();
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	//list by referenced object
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByFixupTask(@RequestParam final int id) {
		ModelAndView res;

		FixupTask fixupTask = this.fixupTaskService.findById(id);
		Assert.isTrue(fixupTask != null, "FixupTask does not exist");
		List<Lorem> lorems = this.loremService.findByFixupTask(fixupTask);
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	//CREATE
	//Input: id of referenced object
	@RequestMapping(value = "/customer/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id) {
		ModelAndView res;
		Lorem lorem = this.loremService.create(id);
		res = new ModelAndView("lorem/edit");
		res.addObject("lorem", lorem);
		return res;
	}

	//EDIT
	//Input: id of object
	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		Lorem lorem = this.loremService.findById(id);
		Assert.notNull(lorem, "Object does not exist");
		res = this.createEditModelAndView(lorem);
		return res;
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView save(@Valid Lorem lorem, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(lorem);
		} else {
			try {
				Lorem saved = this.loremService.save(lorem);
			} catch (Exception e) {

			}
		}
		return res;
	}
	//DELETE
	//Input: id of object to delete
	@RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView res;
		Lorem lorem = this.loremService.findById(id);
		Customer customer = this.customerService.findPrincipal();
		Assert.notNull(lorem, "Object does not exist");
		Assert.isTrue(lorem.getAuthor().equals(customer), "Ownership inconsistency");
		this.loremService.delete(lorem);

		res = new ModelAndView("redirect:/list");
		return res;
	}

	//OTHER
	@RequestMapping(value = "/admin/dash", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView res;
		res = new ModelAndView("lorem/dash");
		res.addObject("publishratio", this.loremService.publishedLoremRatio());
		res.addObject("draftratio", this.loremService.draftLoremRatio());
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(Lorem lorem) {
		ModelAndView res;
		res = this.createEditModelAndView(lorem, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(Lorem lorem, String messageCode) {
		ModelAndView res;

		res = new ModelAndView("lorem/edit");
		res.addObject("lorem", lorem);
		res.addObject("message", messageCode);
		return res;
	}
}
