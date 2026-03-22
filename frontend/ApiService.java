import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService{
    private static final String BASE_URL = "http://localhost:8080";

    public static String registerVoter(String jsonInput){
        try{
            URL url = new URL(BASE_URL + "/api/admin/register");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            String output;
            StringBuilder response = new StringBuilder();

            while((output = br.readLine()) != null){
                response.append(output);
            }

            return response.toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public static String getVoter(String aadhaar){
        try{
            URL url = new URL(BASE_URL + "/api/admin/getByAadhaar?aadhaarId=" + aadhaar);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            String line;
            StringBuilder response = new StringBuilder();

            while((line = br.readLine()) != null){
                response.append(line);
            }

            return response.toString();
        }catch(Exception e){
            return null;
        }
    }

    public static String getCandidates() {
        try{
            URL url = new URL(BASE_URL + "/api/admin/candidates");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();
            while((line = br.readLine()) != null){
                response.append(line);
            }

            return response.toString();
        }catch(Exception e) {
            return null;
        }
    }

    public static String vote(String voterId, String candidateId){
        try{
            URL url = new URL(BASE_URL + "/api/vote/submit?voterId=" + voterId + "&candidateId=" + candidateId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            while((line = br.readLine()) != null){
                response.append(line);
            }

            return response.toString();
        }catch(Exception e){
            return null;
        }
    }
}