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

package project.cs.lisa.application.http;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import project.cs.netinfutilities.UProperties;
import android.util.Log;

/**
 * Represents a response to a NetInfPublish.
 * @author Linus Sunde
 */
public class NetInfRetrieveResponse extends NetInfResponse {

    /** File path JSON Key used by the RESTful API. */
    private static final String FILE_PATH_KEY =
            UProperties.INSTANCE.getPropertyWithName("restlet.retrieve.file_path");
    /** Content type JSON Key used by the RESTful API. */
    private static final String CONTENT_TYPE_KEY =
            UProperties.INSTANCE.getPropertyWithName("restlet.retrieve.content_type");

    /** The retrieved file. */
    private File mFile;
    /** The content type of the retrieved file. */
    private String mContentType;

    /**
     * Creates a new response for a unsent retrieve.
     */
    public NetInfRetrieveResponse() {
        super();
    }

    /**
     * Creates a new response given the HTTP response to a sent retrieve.
     * @param response
     *      The HTTP response
     */
    public NetInfRetrieveResponse(HttpResponse response) {

        // TODO Remove duplicate code from NetInfResponse subclasses

        int statusCode = response.getStatusLine().getStatusCode();

        // Request did not succeed
        if (statusCode != HttpStatus.SC_OK) {
            setStatus(NetInfStatus.FAILED);
            return;
        }

        // No entity in response
        if (response.getEntity() == null) {
            setStatus(NetInfStatus.NO_CONTENT);
            return;
        }

        // Validate that actual JSON is returned
        String jsonString;
        try {
            jsonString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            setStatus(NetInfStatus.INVALID_CONTENT);
            return;
        }
        Object obj = JSONValue.parse(jsonString);
        if (!(obj instanceof JSONObject)) {
            setStatus(NetInfStatus.INVALID_CONTENT);
            return;
        }
        JSONObject json = (JSONObject) obj;

        // Check for file path
        if (!json.containsKey(FILE_PATH_KEY)) {
            setStatus(NetInfStatus.NO_FILE_PATH);
            return;
        }
        mFile = new File((String) json.get(FILE_PATH_KEY));
        if (!mFile.exists()) {
            setStatus(NetInfStatus.FILE_DOES_NOT_EXIST);
            return;
        }

        // Check for content type
        if (!json.containsKey(CONTENT_TYPE_KEY)) {
            setStatus(NetInfStatus.NO_CONTENT_TYPE);
            return;
        }
        mContentType = (String) json.get(CONTENT_TYPE_KEY);

        // Everything hopefully OK
        setStatus(NetInfStatus.OK);
    }

    /**
     * Gets the retrieved file.
     * @return
     *      The retrieved file
     * @throws RequestFailedException
     *      In case the method is called on a failed request
     */
    public File getFile() throws RequestFailedException {
        if (getStatus() != NetInfStatus.OK) {
            throw new RequestFailedException("getFile() called on failed search");
        }
        return mFile;
    }

    /**
     * Gets the content type of the retrieved file.
     * @return
     *      The content type of the retrieved file
     * @throws RequestFailedException
     *      In case the method is called on a failed request
     */
    public String getContentType() throws RequestFailedException {
        if (getStatus() != NetInfStatus.OK) {
            throw new RequestFailedException("getContentType() called on failed search");
        }
        return mContentType;
    }
}
