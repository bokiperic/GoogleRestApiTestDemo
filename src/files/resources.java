package files;

public class resources {

    // All resources data goes here - for POST, GET request, etc.

    public static String placePostData() {
        String resources = "/maps/api/place/add";
        return resources;
    }

    public static String placeDeleteData() {
        String resources = "/maps/api/place/delete";
        return resources;
    }

}
