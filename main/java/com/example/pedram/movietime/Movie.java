package com.example.pedram.movietime;

public class Movie {

	private String Name;
	private String Year;
	private String PosterApi;
	private long userId;
	private long movieId;
	
	public Movie(String name) {
		super();
		Name = name;
	}
	
	
		
	public Movie(String name, long movieId, long userId ) {
		super();
		Name = name;
		this.userId = userId;
		this.movieId = movieId;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getPosterApi() {
		return PosterApi;
	}
	public void setPosterApi(String posterApi) {
		PosterApi = posterApi;
	}
	
	
}
