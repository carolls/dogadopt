package com.lauriano.dogadopt.api.controller.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lauriano.dogadopt.core.service.dog.DogRecommenderService;
import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.data.contentitem.dog.DogFormContentItem;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

@Controller
@RequestMapping("/dog")
public class DogController {
	
	private static final String ITEM_TEMPLEATE = "dog/dogDetails";
	private static final String LIST_TEMPLEATE = "dog/dogsList";
	private static final String FORM_TEMPLEATE = "dog/dogForm";
	
	private static final String RECOMMENDATION_MENU_VALUE = "1";
	private static final String DOGS_MENU_VALUE = "2";
	
	private static final String LIST_PAGE_HEADER = "Dogs in adoption";
	private static final String RECOMENDATION_PAGE_HEADER = "Recommendations";
	
	// Used to display the correct menu navigation for multiple uses templates (getOne() template)
	protected String lastMenuValue = RECOMMENDATION_MENU_VALUE;
	
	@Autowired
	@Qualifier("dogRecommenderService")
	protected DogRecommenderService recommenderService;
	
	@Autowired
	@Qualifier("dogSearchService")
	protected DogSearchService dogSearchService;
	
	
	/**
	 * Show all dogs
	 * 
	 * @param model
	 * @return
	 */
    @RequestMapping(value="")
    public String getAll(Model model) {
        final List<DogContentItem> items = dogSearchService.getAll();
        lastMenuValue = DOGS_MENU_VALUE;
        model.addAttribute("menuOption", lastMenuValue);
        model.addAttribute("pageHeader", LIST_PAGE_HEADER);
        model.addAttribute("items", items);
        return LIST_TEMPLEATE;
    }
    
    /**
     * Show a dog by id
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/{id}")
    public String getOne(@PathVariable(value="id", required=true) Long id,
    		@RequestParam(value="similarity", required=false) Integer similarity,
    		Model model) {
        final DogContentItem item = dogSearchService.getById(id);
        if(similarity!=null) {
        	item.setSimilarity(similarity);
        }
        model.addAttribute("menuOption", lastMenuValue);
    	model.addAttribute("item", item);
        return ITEM_TEMPLEATE;
    }


    /**
     * Show dog recommendation form
     * 
     * @param model
     * @return
     */
    @RequestMapping(value="/form")
    public ModelAndView getForm(Model model) {
    	lastMenuValue = RECOMMENDATION_MENU_VALUE;
    	model.addAttribute("menuOption", lastMenuValue);
        return new ModelAndView(FORM_TEMPLEATE, "dogForm", new DogFormContentItem());
    }
	
	/**
	 * Show dog recommendation result
	 * 
	 * @param model
	 * @param dogForm
	 * @return
	 */
    @RequestMapping(value="/recomendation")
    public String getRecomendation(Model model, DogFormContentItem dogForm) {
    	final List<DogContentItem> result = recommenderService.generateRecomendation(dogForm);
    	lastMenuValue = RECOMMENDATION_MENU_VALUE;
    	model.addAttribute("menuOption", lastMenuValue);
    	model.addAttribute("pageHeader", RECOMENDATION_PAGE_HEADER);
    	model.addAttribute("items", result);
        return LIST_TEMPLEATE;
    }

}
