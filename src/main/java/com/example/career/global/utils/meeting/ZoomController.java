package com.example.career.global.utils.meeting;

import com.amazonaws.util.Base64;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class ZoomController {
    @Value("${zoom.secret-key}")
    private String secretKey;


    @RequestMapping(value="_new/support/reservation/zoomApi" , method = {RequestMethod.GET, RequestMethod.POST})
    public List<Object> googleAsync(HttpServletRequest req, @RequestParam(required = false) String code) throws IOException, NoSuchAlgorithmException {

        //Access token 을 받는 zoom api 호출 url
        String zoomUrl = "https://zoom.us/oauth/token";
        //통신을 위한 okhttp 사용 maven 추가 필요
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        FormBody formBody = new FormBody.Builder()
                .add("code", code) // 1단계에서 받은 code 값
                .add("redirect_uri", "http://localhost:8080/_new/support/reservation/zoomApi") //등록 된 uri
                .add("grant_type", "authorization_code") // 문서에 명시 된 grant_type
                .add("code_verifier", EncodeUtil.encode(code)) // code를 SHA-256 방식으로 암호화하여 전달
                .build();
        Request zoomRequest = new Request.Builder()
                .url(zoomUrl) // 호출 url
                .addHeader("Content-Type", "application/x-www-form-urlencoded") // 공식 문서에 명시 된 type
                .addHeader("Authorization","Basic " + secretKey) // Client_ID:Client_Secret 을  Base64-encoded 한 값
                .post(formBody)
                .build();

        Response zoomResponse = client.newCall(zoomRequest).execute();
        String zoomText = zoomResponse.body().string();


        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        List<Object> list = mapper.readValue(zoomText, new TypeReference<List<Object>>() {});
        System.out.println("response :: " + list);
//        model.addAttribute("response", list.get(0));
//
//
//        model.addAttribute("code", code);
        return list;
    }

}
