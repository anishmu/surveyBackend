package survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	//@GetMapping(path="/addSurvey")
	@RequestMapping(value = "/addSurvey", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> addNewSurvey (@RequestBody Survey survey) {

		surveyRepository.save(survey);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	//@GetMapping(path="/retrieveAllSurvey")
	@RequestMapping(value = "/retrieveAllSurvey", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
	public @ResponseBody Iterable<Survey> getAllSurveys() {
		// This returns a JSON or XML with the users
		return surveyRepository.findAll();
	}	

	@RequestMapping(value = "/deleteSurvey/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) 
    {
		Survey retrivedSurvey = surveyRepository.findOne(id);
		
		if(retrivedSurvey != null){
			surveyRepository.delete(retrivedSurvey);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
	
}
