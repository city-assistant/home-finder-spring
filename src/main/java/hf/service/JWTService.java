package hf.service;

import java.util.Map;

public interface JWTService {
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	long getUserId();
	boolean isUsable(String jwt);
	
}