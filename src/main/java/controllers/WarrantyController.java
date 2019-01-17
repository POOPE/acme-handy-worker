
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
import services.LawService;
import services.WarrantyService;
import domain.Law;
import domain.Warranty;

@Controller
@RequestMapping(value = "/warranty")
public class WarrantyController {

	@Autowired
	private WarrantyService	warrantyService;

	@Autowired
	private ActorService	actorService;
	@Autowired
	private LawService		lawService;


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
	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		Warranty warranty = this.warrantyService.findById(id);
		Assert.notNull(warranty);
		res = this.createEditModelAndView(warranty);
		return res;
	}

	@RequestMapping(value = "admin/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Warranty warranty, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(warranty);

		} else {
			try {
				Warranty saved = this.warrantyService.initialize(warranty);
				res = new ModelAndView("redirect:list.do");
			} catch (Exception e) {
				res = this.createEditModelAndView(warranty, "messagebox.commit.error");
			}
		}
		return res;
	}

	//CREATE
	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Warranty warranty = this.warrantyService.create();

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

	//LOCK
	@RequestMapping(value = "admin/final", method = RequestMethod.GET)
	public ModelAndView lock(@RequestParam final int id) {
		ModelAndView res;
		Warranty warranty = this.warrantyService.findById(id);
		Assert.notNull(warranty);
		Assert.isTrue(!warranty.getLocked(), "Warranty already in final mode");
		warranty.setLocked(true);
		this.warrantyService.save(warranty);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	protected ModelAndView createEditModelAndView(Warranty warranty) {
		ModelAndView res;
		res = this.createEditModelAndView(warranty, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(Warranty warranty, String messageCode) {
		ModelAndView res;
		List<Law> laws = this.lawService.findAll();
		res = new ModelAndView("warranty/edit");

		res.addObject("laws", laws);
		res.addObject("warranty", warranty);
		res.addObject("message", messageCode);
		return res;
	}
}
