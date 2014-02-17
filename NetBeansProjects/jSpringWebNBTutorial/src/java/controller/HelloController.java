/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import service.HelloService;
/** 
 *
 * @author mike
 */
public class HelloController extends SimpleFormController {
private HelloService helloService;

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
    public HelloController() {
        //Initialize controller properties here or 
        //in the Web Application Context

    setCommandClass(Name.class);
    setCommandName("name");
    setSuccessView("helloView");
    setFormView("nameView");
    /*
     Setting the FormView enables you to set the name of the view that is
     used to display the form. This is the page that contains the text field
     allowing users to enter their name. Setting the SuccessView likewise lets
     you set the name of the view that should display upon a successful submit.
     When you set the CommandName you define the name of the command in the model.
     In this case, the command is the form object with request parameters bound
     onto it. Setting the CommandClass allows you set the name of the command class.
     An instance of this class gets populated and validated upon each request.
     */
}
    
    
    

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception
    {
          Name name = (Name) command;
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("helloMessage", helloService.sayHello(name.getValue()));
        return mv;
        /*
         the command is recast as a Name object. An instance of ModelAndView
         is created, and the success view is obtained using a getter in
         SimpleFormController. Finally, the model is populated with data.
         The only item in our model is the hello message obtained from the
         HelloService created earlier. You use the addObject() method to add
         the hello message to the model under the name helloMessage.
         */
    }
    

}