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

import java.util.List;

public class WosIdValidator {
	public static boolean validWosId(String wosId) {
		if (wosId != null && wosId.length() == 19 && wosId.matches("WOS:[0-9]{15}")) {
			return true;
		} else return false;
	}

	public static boolean validWosIds(List<String> wosIds) {
		boolean ret = true;
		for(String id : wosIds) {
			if (id == null || id.length() != 19 || id.matches("WOS:[0-9]{15}") == false) {
				ret = false;
			}
		}
		return ret;
	}
}
