package com.mrx.finalmvc;

import javax.servlet.DispatcherType;

public class View {
	private String url;
	private String dispatchAction = DispatchActionConstant.FORWARD;
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDispatchAction() {
		return dispatchAction;
	}
	public void setDispatchAction(String dispatchAction) {
		this.dispatchAction = dispatchAction;
	}
}
