package com.RajeshPhysics_Services.Utils;

import org.springframework.stereotype.Component;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.ReCaptchResponse;


@Component
public class ReCaptchaUtil {
	
	public boolean validateCaptcha(String captchaResponse){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", AppConstrants.RECAPTCHA_SECRET);
        requestMap.add("response", captchaResponse);

        ReCaptchResponse apiResponse = restTemplate.postForObject(AppConstrants.GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptchResponse.class);
        if(apiResponse == null){
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.isSuccess());
    }

}
