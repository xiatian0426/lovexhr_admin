package com.acc.util;

public class Constants {

	public static final String PAGE_NOT_FOUND = "/prompt/404";
	public static final String SERVICES_ERROR = "/prompt/500";
	
	public final static String LOGINUSER = "SESSIONLOGINUSER";
	public final static String VALIDATESESSION = "VALIDATECODESESSION";
	//存入cookie的名称
	public static final String COOKIESUSERNAME = "cookieUserName";
	//cookies存放有效期(单位：秒)
	public static final int COOKIESTIMES = 60 * 60 * 24 * 3;

    public final static String honorImgPath  = "res/images/honorImg/";
    public final static String memberImgPath  = "res/images/memberImg/";
    public final static String memberImgWxaCodePath  = "res/images/memberImg/wxaCode/";
    public final static String proImgPath  = "res/images/proImg/";
    public final static String proDetailImgPath  = "res/images/proDetailImg/";
    public final static String recruitImgPath  = "res/images/recruitImg/";
    public final static String companyImgPath  = "res/images/companyImg/";

    public final static String proVideoPath  = "res/video/proVideo/";
    public final static String userVideoPath  = "res/video/userVideoPath/";

    public final static String ROLEIDZ = "0";//客户
    public final static String ROLEIDO = "1";//管理员

    public final static String BASEPATH  = "https://www.lovexhr.com/";
}
