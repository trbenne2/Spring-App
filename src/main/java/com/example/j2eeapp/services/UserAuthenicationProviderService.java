package com.example.j2eeapp.services;

import com.example.j2eeapp.domain.UserEntity;

/**
 * Provides processing service to set user authentication session
 * 
 * @author Tyler Bennett
 *
 */
public interface UserAuthenicationProviderService {
	
	
	/**
	 * process user authentication
	 * 
	 * @param user
	 * @return
	 */
	boolean processUserAuthentication(UserEntity user);

}
