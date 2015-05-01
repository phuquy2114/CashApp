package android.phuquy.dev.cashmedia.Utils;


public interface RequestCallback {
	public int REGISTER_REQUEST = 0;
	public int LOGIN_REQUEST = 1;
	public int REDEEM_REQUEST = 2;
	public int INVITE_REQUEST = 3;
	public int CREDIT_HISTORY = 4;
	public int REFRESH_POINT_REQUEST = 6;
	public int CHANGE_PASS_REQUEST = 7;
	
	public void onStartRequest();
	public void onComplete(String result, int requestType);
	public void onFailed();
}
