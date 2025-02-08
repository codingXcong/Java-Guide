package io.zgc.util;

public enum TokenType {
	REFRESH(0,"refresh_token"),
	BEARER(1, "Bearer"),
    MAC(2, "MAC");
	
	private int code;

    private String tokenType;

    TokenType(int code, String grantType) {
    	this.code = code;
        this.tokenType = grantType;
    }

	public int getCode() {
		return code;
	}

	public String getTokenType() {
		return tokenType;
	}
    
    

}