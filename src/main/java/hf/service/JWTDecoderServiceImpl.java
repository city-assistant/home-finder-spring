package hf.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("jwtDecoderService")
public class JWTDecoderServiceImpl implements JWTDecoderService {
	@Override
	public String decode(HashMap body) throws Exception {
		Base64 base64url = new Base64(true);
		String response = null;
		try {
			String userIdEncoded = body.get("userToken").toString().split("\\.")[1];
			String decodedUserId = new String(base64url.decode(userIdEncoded));
			ObjectMapper mapper = new ObjectMapper();
			Map map = mapper.readValue(decodedUserId, Map.class);
			response = map.get("userId").toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}