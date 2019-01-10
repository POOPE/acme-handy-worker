
package controllers;

import java.util.Collection;

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
import services.AdminService;
import services.CustomerService;
import services.HandyWorkerService;
import services.RefereeService;
import services.SponsorService;
import domain.Actor;
import domain.Admin;
import domain.Customer;
import domain.HandyWorker;
import domain.Referee;
import domain.Sponsor;

@Controller
@RequestMapping(value = "/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService		actorService;

	@Autowired
	private AdminService		adminService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private SponsorService		sponsorService;

	@Autowired
	private HandyWorkerService	handyworkerService;

	@Autowired
	private RefereeService		refereeService;


	public ActorController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam String role) {
		ModelAndView result;
		Actor actor;
		try {
			if (role.equals("administrator")) {
				actor = this.adminService.createAdmin();
				result = this.createEditModelAndView(actor, role);
			} else if (role.equals("customer")) {
				actor = this.customerService.createCustomer();
				result = this.createEditModelAndView(actor, role);
			} else if (role.equals("referee")) {
				actor = this.refereeService.createReferee();
				result = this.createEditModelAndView(actor, role);
			} else if (role.equals("handyWorker")) {
				actor = this.handyworkerService.createHandyWorker();
				result = this.createEditModelAndView(actor, role);
			} else if (role.equals("sponsor")) {
				actor = this.sponsorService.createSponsor();
				result = this.createEditModelAndView(actor, role);
			} else {
				result = new ModelAndView("redirect:../welcome/index.do");
			}
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:../welcome/index.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Actor actor;
		try {
			Collection<String> authorities = this.actorService.getPrincipalAuthority();
			actor = this.actorService.findPrincipal();
			if (authorities.contains("ADMIN")) {
				result = this.createEditModelAndView(actor, "administrator");
			} else if (authorities.contains("CUSTOMER")) {
				result = this.createEditModelAndView(actor, "customer");
			} else if (authorities.contains("REFEREE")) {
				result = this.createEditModelAndView(actor, "referee");
			} else if (authorities.contains("HANDYWORKER")) {
				result = this.createEditModelAndView(actor, "handyWorker");
			} else if (authorities.contains("SPONSOR")) {
				result = this.createEditModelAndView(actor, "sponsor");
			} else {
				result = new ModelAndView("redirect:../security/login.do");
			}
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:../welcome/index.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "toCustomer")
	public ModelAndView save(@Valid Customer customer, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(customer, "customer", "actor.commit.error");
		} else {

			try {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(customer, "customer", "actor.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "toHandyWorker")
	public ModelAndView save(@Valid HandyWorker handyWorker, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(handyWorker, "handyWorker", "actor.commit.error");
		} else {

			try {
				this.handyworkerService.save(handyWorker);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(handyWorker, "handyWorker", "actor.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "toSponsor")
	public ModelAndView save(@Valid Sponsor sponsor, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(sponsor, "sponsor", "actor.commit.error");
		} else {

			try {
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(sponsor, "sponsor", "actor.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "toReferee")
	public ModelAndView save(@Valid Referee referee, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(referee, "referee", "actor.commit.error");
		} else {
			try {
				Assert.isTrue(this.actorService.getPrincipalAuthority().contains("ADMIN"));
			} catch (Throwable oops) {
				result = this.createEditModelAndView(referee, "referee", "actor.commit.forbidden");
				return result;
			}
			try {
				this.refereeService.save(referee);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(referee, "referee", "actor.commit.error");
			}
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "toAdministrator")
	public ModelAndView save(@Valid Admin admin, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(admin, "administrator", "actor.commit.error");
		} else {
			try {
				Assert.isTrue(this.actorService.getPrincipalAuthority().contains("ADMIN"));
			} catch (Throwable oops) {
				result = this.createEditModelAndView(admin, "administrator", "actor.commit.forbidden");
				return result;
			}

			try {
				this.adminService.save(admin);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(admin, "customer", "actor.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Actor actor, String role) {
		return this.createEditModelAndView(actor, role, null);
	}

	protected ModelAndView createEditModelAndView(Actor actor, String role, String messageCode) {
		ModelAndView result;
		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("role", role);
		result.addObject("message", messageCode);
		return result;
	}
}
