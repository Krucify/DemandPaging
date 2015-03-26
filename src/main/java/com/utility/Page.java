package com.utility;

public class Page {
	private boolean dirty;
	private boolean valid;
	private int memIndex;
	private int size;
	
	public Page(int size)
	{
		this.size = size;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}
	public int getSize(){
		return size;
	}
}
