/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageBoxService;
import services.MessageService;
import services.SiteConfigurationService;
import domain.Actor;
import domain.SiteConfiguration;
import forms.MessageForm;

@Controller
@RequestMapping("/admin")
public class AdministratorController extends AbstractController {

	@Autowired
	private MessageBoxService			messageBoxService;
	@Autowired
	private MessageService				messageService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private SiteConfigurationService	siteConfigService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Broadcast message ---------------------------------------------------------------		

	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		ModelAndView res;
		MessageForm mail = new MessageForm();
		mail.setLock(true);
		List<Actor> actors = this.actorService.findAll();
		String recipients = "";
		for (int i = 0; i < actors.size(); i++) {
			recipients = recipients + actors.get(i).getId();
			if (i < actors.size() - 1) {
				recipients = recipients + ",";
			}
		}
		mail.setRecipients(recipients);
		res = new ModelAndView("messaging/write");

		List<String> priorities = new ArrayList<>();
		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");
		res.addObject("mail", mail);
		res.addObject("priorities", priorities);
		return res;
	}

	// Site config
	@RequestMapping(value = "/siteconfig", method = RequestMethod.GET)
	public ModelAndView editconfig() {
		ModelAndView result;
		SiteConfiguration siteConfig = this.siteConfigService.find();
		result = new ModelAndView("administrator/siteconfig");
		result.addObject("siteConfiguration", siteConfig);

		return result;
	}

	@RequestMapping(value = "/siteconfig", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SiteConfiguration siteConfig, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(siteConfig);

		} else {
			try {
				this.siteConfigService.update(siteConfig);
				res = new ModelAndView("redirect:siteconfig");
			} catch (Exception e) {
				res = this.createEditModelAndView(siteConfig, "siteconfig.commit.error");
			}
		}
		return res;
	}

	protected ModelAndView createEditModelAndView(SiteConfiguration siteConfig) {
		ModelAndView res;
		res = this.createEditModelAndView(siteConfig, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(SiteConfiguration siteConfig, String messageCode) {
		ModelAndView res;
		res = new ModelAndView("administrator/siteconfig");
		res.addObject("siteConfiguration", siteConfig);
		res.addObject("message", messageCode);
		return res;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

}
