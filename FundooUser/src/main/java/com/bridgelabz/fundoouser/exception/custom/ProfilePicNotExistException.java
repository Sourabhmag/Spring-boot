package com.bridgelabz.fundoouser.exception.custom;

public class ProfilePicNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 123647765588743966L;
	public ProfilePicNotExistException(String message)
	{
		super(message);
	}
}
