/**
 * Copyright 2012 Ericsson, Uppsala University
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
 * 
 * Uppsala University
 *
 * Project CS course, Fall 2012
 *
 * Projekt DV/Project CS, is a course in which the students develop software for
 * distributed systems. The aim of the course is to give insights into how a big
 * project is run (from planning to realization), how to construct a complex
 * distributed system and to give hands-on experience on modern construction
 * principles and programming methods.
 *
 */
package project.cs.netinfservice.netinf.node.search;

import project.cs.netinfservice.util.metadata.Metadata;

/**
 * Each search result entry is encoded as a SearchResult object.
 * 
 * @author Kim-Anh Tran
 *
 */
public interface SearchResult {
	/**
	 * Returns the meta data of this entity associated with the search result.
	 * 
	 * @return	The meta data
	 */
	Metadata getMetaData();
	
	/**
	 * Returns the identifier of this entity associated with the search result.
	 * 
	 * @return	The identifier
	 */
	String getHash();
	
	/**
	 * Returns the hash algorithm used for hashing the content.
	 * 
	 * @return The hash algorithm
	 */
	String getHashAlgorithm();
}
