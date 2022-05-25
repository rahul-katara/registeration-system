import com.jedi.api.AdminRestAPI;
import com.jedi.api.ProfessorRestAPI;
import com.jedi.api.StudentRestAPI;
import com.jedi.dao.AdminDaoImplementation;
import com.jedi.dao.ProfessorDaoImplementation;
import com.jedi.dao.StudentDaoImplementation;
import com.jedi.utils.*;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<ApplicationConfiguration> {

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	public void run(ApplicationConfiguration configuration, Environment environment) {
		
		ConnectionUtil connectionUtil = new ConnectionUtil(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
		
		StudentDaoImplementation studentDao = new StudentDaoImplementation(connectionUtil);
        StudentRestAPI studentRestAPI=new StudentRestAPI(studentDao) ;
        environment.jersey().register(studentRestAPI);
        
        ProfessorDaoImplementation professorDao = new ProfessorDaoImplementation(connectionUtil);
        ProfessorRestAPI professorRestAPI = new ProfessorRestAPI(professorDao);
        environment.jersey().register(professorRestAPI);
        
        AdminDaoImplementation adminDao = new AdminDaoImplementation(connectionUtil);
        AdminRestAPI adminRestAPI = new AdminRestAPI(adminDao);
        environment.jersey().register(adminRestAPI);
    }

}
