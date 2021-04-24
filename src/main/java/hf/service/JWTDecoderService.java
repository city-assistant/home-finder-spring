package hf.service;

import java.util.HashMap;

public interface JWTDecoderService {
	String decode(HashMap body) throws Exception;
}