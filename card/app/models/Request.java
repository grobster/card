package models;

import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quarles on 8/16/2015.
 */
public class Request {
    @Constraints.Required
    public String firstName;
    @Constraints.Required
    public String lastName;
    @Constraints.Required
    public String gid;
    @Constraints.Required
    public String lte;
    @Constraints.Required
    public String reasonForReplacement;
    private static List<Request> requests;

    static {
        requests = new ArrayList<>();
        requests.add(new Request("Jeffery", "Quarles", "Z00ABC1", "jeffery.quarles@siemens.com", "lost"));
        requests.add(new Request("John", "Doe", "Z00PD234", "john.doe@siemens.com", "damaged"));
        requests.add(new Request("Jill", "Nun", "Z00MMP5", "jill.nun@siemens.com", "lte change"));
        requests.add(new Request("Scott", "Smith", "Z00PW81", "scott.smith@siemens.com", "lost"));
    }

    public Request() {}

    public Request(String firstName, String lastName, String gid, String lte, String reasonForReplacement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gid = gid;
        this.lte = lte;
        this.reasonForReplacement = reasonForReplacement;
    }

    public static List<Request> findAll() {
        return new ArrayList<>(requests);
    }

    public static Request findByGid(String gid) {
        for (Request r: requests) {
            if (r.gid.equals(gid)) {
                return r;
            }
        }
        return null;
    }

    public static boolean remove(Request request) {
        return requests.remove(request);
    }

    public void save() {
        requests.remove(findByGid(this.gid));
        requests.add(this);
    }

    public String toString() {
        return String.format("%s - %s", gid, reasonForReplacement);
    }
}
