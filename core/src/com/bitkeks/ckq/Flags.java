package com.bitkeks.ckq;

public class Flags {
	public boolean hasFlag(int val, int flag) {
		if ((val & flag) != 0)
			return true;
		return false;
	}
}
