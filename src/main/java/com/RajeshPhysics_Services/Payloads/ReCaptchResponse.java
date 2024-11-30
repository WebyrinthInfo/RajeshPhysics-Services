package com.RajeshPhysics_Services.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReCaptchResponse {
	private boolean success;
	private String challenge_ts;
	private String hostname;
}
