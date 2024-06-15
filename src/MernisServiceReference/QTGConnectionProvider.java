package MernisServiceReference;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QTGConnectionProvider {
    QTGResponseData sendRequest(String url, String requestBody, HashMap<String, String> httpHeaders, QTGRequestResultHandler handler) throws Exception;
}

class QTGHttpConnectionProvider implements QTGConnectionProvider {

    @Override
    public QTGResponseData sendRequest(String url, String requestBody, HashMap<String, String> httpHeaders, QTGRequestResultHandler handler) throws Exception {
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);  // Bu satırı ekledik

        // HTTP header'larını ayarla
        for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // Request body'yi gönder
        try (OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())) {
            wr.write(requestBody);
            wr.flush();
        }

        // Yanıtı al
        QTGResponseData response = new QTGResponseData();
        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                String values = String.join(",", entry.getValue());
                response.getHeaders().put(entry.getKey(), values);
            }
        }

        try (InputStream dataStream = getResponseStream(connection)) {
            response.setBody(QTGHelper.streamToString(dataStream));
        }

        return response;
    }

    protected InputStream getResponseStream(HttpURLConnection connection) throws IOException {
        try {
            return connection.getInputStream();
        } catch (IOException ex) {
            return connection.getErrorStream();
        }
    }
}
