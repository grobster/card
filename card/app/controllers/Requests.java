package controllers;

import models.Request;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;
import views.html.workerdetails;
import views.html.list;

import java.util.List;

/**
 * Created by quarles on 8/16/2015.
 */
public class Requests extends Controller {
    private static final Form<Request> requestForm = Form.form(Request.class);
    public static Result list() {
        List<Request> requests = Request.findAll();
        return ok(list.render(requests));
    }

    public static Result newRequest() {
        return ok(details.render(requestForm));
    }

    public static Result details(String gid) {
        final Request request = Request.findByGid(gid);
        if (request == null) {
            return notFound(String.format("Request %s does not exists", gid));
        }
        Form<Request> filledForm = requestForm.fill(request);
        return ok(workerdetails.render(filledForm));
    }

    public static Result workerDetails(String gid) {
        final Request request = Request.findByGid(gid);
        if (request == null) {
            return notFound(String.format("Request %s does not exists", gid));
        }
        Form<Request> filledForm = requestForm.fill(request);
        return ok(workerdetails.render(filledForm));
    }

    public static Result save() {
        Form<Request> boundForm = requestForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Request request = boundForm.get();
        request.save();
        flash("success", String.format("Successfully added request %s", request));

        return redirect(routes.Requests.list());
    }

}
