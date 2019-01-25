
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FixupTaskService;
import services.LoremService;
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
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView res;
		List<Lorem> lorems = this.loremService.findAllFinal();
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listByAuthor() {
		ModelAndView res;
		List<Lorem> lorems = this.loremService.findByAuthor();
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByFixupTask(int id) {
		ModelAndView res;

		FixupTask fixupTask = this.fixupTaskService.findById(id);
		Assert.isTrue(fixupTask != null, "FixupTask does not exist");
		List<Lorem> lorems = this.loremService.findByFixupTask(fixupTask);
		res = new ModelAndView("lorem/list");
		res.addObject("lorems", lorems);
		return res;
	}

	//CREATE

	//EDIT

	//DELETE

	//OTHER
}
