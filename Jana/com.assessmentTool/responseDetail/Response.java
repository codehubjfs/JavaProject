package com.responseDetail;

public class Response {
	
	private int mId;
	private String userAnswer;
	private double mark;
	
	public Response(int mId, String userAnswer, double mark) {
		super();
		this.mId = mId;
		this.userAnswer = userAnswer;
		this.mark = mark;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}
	

}

