
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Warranty;
import services.ActorService;
import services.WarrantyService;

@Controller
@RequestMapping(value = "/warranty")
public class WarrantyController {

	@Autowired
	private WarrantyService	warrantyService;

	@Autowired
	private ActorService	actorService;


	// LIST ALL
	@RequestMapping(value = "admin/list")
	public ModelAndView listAll() {
		ModelAndView result;

		List<Warranty> warranties = this.warrantyService.findAll();

		result = new ModelAndView("warranty/list");
		result.addObject("warranties", warranties);
		result.addObject("user", this.actorService.findPrincipal());

		return result;
	}

	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		Warranty warranty = this.warrantyService.findById(id);
		Assert.notNull(warranty);
		res = this.createEditModelAndView(warranty);
		return res;
	}

	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView result = null;
		Warranty warranty = this.warrantyService.findById(id);
		this.warrantyService.delete(warranty);
		return result;
	}

	//VIEW
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int id) {
		ModelAndView result;
		Warranty warranty = this.warrantyService.findById(id);

		result = new ModelAndView("warranty/list");
		result.addObject("warranty", warranty);

		return result;
	}

	protected ModelAndView createEditModelAndView(Warranty w) {
		ModelAndView res;
		Warranty warranty = this.warrantyService.findById(w.getId());

		res = new ModelAndView("warranty/edit");
		res.addObject("warranty", warranty);

		return res;
	}
}
