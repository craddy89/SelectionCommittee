package util.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMatcher {

    private static final String regex="[^\\/]+$";

    public static String matchUrl(String string){

        String resultUrl="";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()){
            resultUrl=matcher.group();
        }
        return resultUrl;
    }
}
