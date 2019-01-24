/*
 * Copyright 2019 Institut Laue–Langevin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.ill.puma.wosimporter.utils;

import eu.ill.puma.wosimporter.domain.Token;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenService {
	private static final SecureRandom random = new SecureRandom();

	private static final Map<String, Token> tokenStorage = new HashMap();

	public static Token get(String tokenId) {
		return tokenStorage.get(tokenId);
	}

	public static Token save(Token token) {

		String tokenKey;

		if (token.getKey() == null) {
			//get key
			tokenKey = new BigInteger(130, random).toString(32);
			while (tokenStorage.containsKey(tokenKey) == true) {
				tokenKey = new BigInteger(130, random).toString(32);
			}

			//set key
			token.setKey(tokenKey);

		} else {
			tokenKey = token.getKey();
		}

		tokenStorage.put(tokenKey, token);
		return token;
	}

	/**
	 * remove old cursor
	 */
	public static void update() {
		Long currentTime = System.currentTimeMillis();
		List<Token> keysToRemoves = new ArrayList();

		for (Token token : tokenStorage.values()) {
			if (currentTime - token.getCreationTime() > 1000L * 3600L) {
				keysToRemoves.add(token);
			}
		}
		for (Token keyToRemove : keysToRemoves) {
			tokenStorage.remove(keyToRemove.getKey());
		}
	}
}
