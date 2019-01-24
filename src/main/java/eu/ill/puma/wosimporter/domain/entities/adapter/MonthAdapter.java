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
package eu.ill.puma.wosimporter.domain.entities.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MonthAdapter extends XmlAdapter<String, Integer> {

	/**
	 * return the month as a number
	 *
	 * @param month month string to convert
	 * @return the number of the month
	 * @throws IllegalArgumentException if the month doesn't exist
	 */
	@Override
	public Integer unmarshal(String month) throws IllegalArgumentException {
		switch (month) {
			case "JAN":
				return 1;
			case "FEV":
				return 2;
			case "MAR":
				return 3;
			case "APR":
				return 4;
			case "MAY":
				return 5;
			case "JUN":
				return 6;
			case "JUL":
				return 7;
			case "AUG":
				return 8;
			case "SEP":
				return 9;
			case "OCT":
				return 10;
			case "NOV":
				return 11;
			case "DEC":
				return 12;
			default:
				throw new IllegalArgumentException("Invalid month : " + month);
		}
	}

	/**
	 * return the month as a string
	 *
	 * @param month the number of the month
	 * @return the month as a string og tree char
	 * @throws IllegalArgumentException if the month doesn't exist
	 */
	@Override
	public String marshal(Integer month) throws IllegalArgumentException {
		switch (month) {
			case 1:
				return "JAN";
			case 2:
				return "FEV";
			case 3:
				return "MAR";
			case 4:
				return "APR";
			case 5:
				return "MAY";
			case 6:
				return "JUN";
			case 7:
				return "JUL";
			case 8:
				return "AUG";
			case 9:
				return "SEP";
			case 10:
				return "OCT";
			case 11:
				return "NOV";
			case 12:
				return "DEC";
			default:
				throw new IllegalArgumentException("Invalid month : " + month);
		}
	}
}
