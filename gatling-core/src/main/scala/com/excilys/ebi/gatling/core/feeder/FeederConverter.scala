/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.core.feeder

class FeederConverter(feeder: Array[Map[String, String]]) {

	def convert(conversions: (String, String => Any)*) : Array[Map[String,Any]] = {
		def convertColumn(column: String, content: String) = {
			val conversion = conversions.find(column == _._1).map(_._2).getOrElse(identity[String] _)
			conversion(content)
		}

		feeder.map(_.map{ case (key,value) => (key,convertColumn(key,value))})
	}
}