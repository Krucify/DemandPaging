package com.utility;

public class Reference {
	private int tblIndex;
	private int pageIndex;
	private int value;
	public boolean set;
	
	public Reference(int tbl, int page, int value)
	{
		this.tblIndex = tbl;
		this.pageIndex = page;
		this.value = value;
		this.set = false;
	}
	
	public int getTblIndex() {
		return tblIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean isSet(){
		return set;
	}
	
	public void set(){
		set = true;
	}
}
