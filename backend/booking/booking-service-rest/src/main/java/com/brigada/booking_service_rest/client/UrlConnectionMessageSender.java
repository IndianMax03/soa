package com.brigada.booking_service_rest.client;

import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.X509Certificate;

public class UrlConnectionMessageSender extends HttpUrlConnectionMessageSender {

    @Override
    protected void prepareConnection(HttpURLConnection connection) throws IOException {
        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
            try {
                httpsConnection.setSSLSocketFactory(createUnsafeSslContext().getSocketFactory());
                httpsConnection.setHostnameVerifier((hostname, session) -> true);
            } catch (Exception e) {
                throw new IOException("Failed to configure SSL context", e);
            }
        }
        super.prepareConnection(connection);
    }

    private SSLContext createUnsafeSslContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());
        return sslContext;
    }

}
