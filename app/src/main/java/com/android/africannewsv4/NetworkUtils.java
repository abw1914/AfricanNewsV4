package com.android.africannewsv4;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public NetworkUtils() {

    }

    public static ArrayList<NewsData> fetchNewsData (String requestUrl) throws JSONException {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<NewsData> extractNewsData = extractDataFromJson(jsonResponse);
        return extractNewsData;
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makeHttpRequest (URL requestUrl) throws IOException {
        String jsonResponse = "";
        if(requestUrl == null) {
            return jsonResponse;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = convertStreamToString(inputStream);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Malformed URL " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "Protocal Exception: " + e.getMessage());
        } catch (IOException e) {
            Log.e(LOG_TAG, "IO Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, "General Exception: " + e.getMessage());
        }
        return jsonResponse;
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "IO Exception" + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, " IO Excpetion closing the Input Stream" + e.getMessage());
            }
        }
        return stringBuilder.toString();
    }

    public static ArrayList<NewsData> extractDataFromJson (String newsDataJson) throws JSONException {
        String title;
        String articleId;
        String date;
        String webUrl;

        if(TextUtils.isEmpty(newsDataJson)) {
            return null;
        }
        ArrayList<NewsData> newsDataArrayList = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsDataJson);
            JSONObject newsResults = baseJsonResponse.getJSONObject("response");
            JSONArray currentResultsArray = newsResults.getJSONArray("results");

            for (int i = 0; i < currentResultsArray.length(); i++) {

                JSONObject currentArticle = currentResultsArray.getJSONObject(i);

                title = currentArticle.getString("webTitle");
                articleId = currentArticle.getString("id");
                date = currentArticle.getString("webPublicationDate");
                webUrl = currentArticle.getString("webUrl");
                /**
                 * Note if article author is needed - need to write another JsonArray for the tags id
                 */

                JSONArray authorArray = currentArticle.getJSONArray("tags");
                if (authorArray.length() > 0) {
                    JSONObject authorFirstName = authorArray.getJSONObject(0);
                    String authorFirst = authorFirstName.getString("firstName");
                    //String authorLast = authorFirstName.getString("lastName");

                    NewsData newsData = new NewsData(title, articleId, date, authorFirst, webUrl);
                    newsDataArrayList.add(newsData);

                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Parsing JSON Problem " + e.getMessage());
        }
        return newsDataArrayList;


    }
}
