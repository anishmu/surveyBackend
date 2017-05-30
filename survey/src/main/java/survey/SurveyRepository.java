package survey;

import org.springframework.data.repository.CrudRepository;

import survey.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
