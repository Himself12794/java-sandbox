package com.pwhiting.tests.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataObject implements Serializable {

	private static final long serialVersionUID = -7441985539971979300L;
	
	private int id;
	
	public DataObject(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DataObject [id=" + id + "]";
	}

	public void writeToStream(ObjectOutputStream stream) {
		try {
			stream.writeObject(this);
		} catch (IOException e) {
			
		}
	}
	
}
