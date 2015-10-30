package com.example.pedram.movietime;

public class Friend {

	private String Name;
	private long Id;
	
	
	public Friend(String name, long id) {
		super();
		Name = name;
		Id = id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	
	
}
