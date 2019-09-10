package files;

public class payLoad {

    // All "bodies" data goes to this file

    public static String getPostData() {

        String postData = "{" +
                "\"location\": {" +
                "\"lat\": -33.8669710," +
                "\"lng\": 151.1958750" +
                "}," +
                "\"accuracy\": 50," +
                "\"name\": \"Google Shoes!\"," +
                "\"phone number\": \"(02) 9374 4000\"," +
                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
                "\"types\": [\"shoe store\"]," +
                "\"website\": \"http://www.google.com.au/\"," +
                "\"language\": \"en-AU\"" +
                "}";

        return postData;

    }

}
