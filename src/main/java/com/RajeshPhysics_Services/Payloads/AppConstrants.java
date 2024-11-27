package com.RajeshPhysics_Services.Payloads;

import java.net.URI;

public class AppConstrants {

//	----------------STUDENT TYPE----------------------
	public static final String FREE_STUDENT = "FREE";
	public static final String PAID_STUDENT = "PAID";
	
//	----------------STUDENT ROLE--------------------
	public static final String STUDENT_ROLE_ID = "2";
	
//	----------searching, shorting, pagination------------------- 
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "7";
	public static final String SORT_BY_ID = "id";
	public static final String SORT_BY_MOBILE = "mobile";
	public static final String SORT_BY_NAME = "name";
	public static final String SORT_BY_ADDRESS = "address";
	public static final String SORT_DIR_DESC = "desc";
	public static final String SORT_DIR_ASC = "asc";
	public static final String SEARCH_BY_ID = "id";
	
//	-------------------ROLE TYPE---------------------------
	public static final String WY_ROLE = "WY";
	public static final String PUBLIC_ROLE = "PUBLIC";
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String TEACHER_ROLE = "TEACHER";
	public static final String STUDENT_ROLE = "STUDENT";
	public static final String CMS_ROLE = "CMS";
	public static final String PARENT_ROLE = "PARENT";
	
	
	
	public static final long JWT_TOKEN_VALIDITY = 3 *24*60 * 60;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
//	------------------GOOGLE RECAPTCHA---------------------------
	public static final String RECAPTCHA_SECRET = null;
	public static final URI GOOGLE_RECAPTCHA_ENDPOINT = null;
	
	
	
	
	

}
