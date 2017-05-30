package survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import survey.Survey;
import survey.SurveyRepository;


@Controller
@RequestMapping(path="/survey")
public class MainController {
	@Autowired	
	private SurveyRepository surveyRepository;
	@RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
	@GetMapping(path="/addSurvey") 
	public @ResponseBody String addNewSurvey (@RequestParam String surveyName
			, @RequestParam String surveyDescription) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Survey newSurvey = new Survey();
		newSurvey.setSurveyName(surveyName);
		newSurvey.setSurveyDescription(surveyDescription);
		surveyRepository.save(newSurvey);
		return "Your new Survey has been added successfully";
	}
	
	@GetMapping(path="/retrieveAllSurvey")
	public @ResponseBody Iterable<Survey> getAllSurveys() {
		// This returns a JSON or XML with the users
		return surveyRepository.findAll();
	}	

}
