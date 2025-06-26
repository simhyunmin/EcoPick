package EcoPick.service;

import EcoPick.domain.member.dto.KakaoTokenResponse;
import EcoPick.domain.member.dto.KakaoUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class KakaoOAuthHandler {
    public static KakaoTokenResponse requestToken(String code, String clientId, String redirectUri) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(
                tokenUrl,
                request,
                KakaoTokenResponse.class
        );
        return response.getBody();
    }

    public static KakaoUserInfo requestUserInfo(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);
        RestTemplate restTemplate = new RestTemplate();
        for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setSupportedMediaTypes(Arrays.asList(
                        MediaType.APPLICATION_JSON,
                        MediaType.valueOf("application/json;charset=UTF-8")
                ));
            }
        }
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );
        KakaoUserInfo userInfo = new KakaoUserInfo();
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null) {
            if (responseBody.get("id") != null) {
                userInfo.setId(responseBody.get("id").toString());
            }
            if (responseBody.get("connected_at") != null) {
                userInfo.setConnected_at(responseBody.get("connected_at").toString());
            }
            if (responseBody.get("properties") != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> properties = (Map<String, Object>) responseBody.get("properties");
                userInfo.setProperties(properties);
            }
            if (responseBody.get("kakao_account") != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> kakaoAccount = (Map<String, Object>) responseBody.get("kakao_account");
                userInfo.setKakao_account(kakaoAccount);
            }
        }
        return userInfo;
    }
} 