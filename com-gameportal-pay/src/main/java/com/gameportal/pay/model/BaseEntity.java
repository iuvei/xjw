package com.gameportal.pay.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {
	public abstract Serializable getID();

}
