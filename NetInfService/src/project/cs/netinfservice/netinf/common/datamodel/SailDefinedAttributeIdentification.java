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
/*
 * Copyright (C) 2009-2011 University of Paderborn, Computer Networks Group
 * (Full list of owners see http://www.netinf.org/about-2/license)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Paderborn nor the names of its contributors may be used to endorse
 *       or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package project.cs.netinfservice.netinf.common.datamodel;

import netinf.common.datamodel.rdf.DefinedRdfNames;

/**
 * This class contains a list of all predefined attribute identifications. With the help of the
 * {@link SailDefinedAttributeIdentification#getURI()} method, it is possible to access the 
 * underlying URI of the according defined attribute.
 *
 * @author PG Augnet 2, University of Paderborn
 */
public enum SailDefinedAttributeIdentification {
    /** Specifies the priority for a locator */
    LOCATOR_PRIORITY("locator_priority", false),
    
    /** Bluetooth Mac Address */
    BLUETOOTH_MAC("bluetooth_mac", false),
    
    /** Wifi Mac Address */
    WIFI_MAC("wifi_mac", false),
    
    /** Wifi Ip Address */
    WIFI_IP("wifi_ip", false),
    
    /** NCS url */
    NCS_URL("ncs_url", false),
    
    /** Filepath */
    FILE_PATH("file_path", false);

    /** URI */
    private final String uri;

    /**
     * Uses RDF. This is still kept but should be changed.
     * @param uri
     *      The URI
     * @param fromNetInf
     *      If it is from NetInf
     */
    private SailDefinedAttributeIdentification(String uri, boolean fromNetInf) {
        if (fromNetInf) {
            // TODO: This is currently a little bit ugly, since we are using RDF in an non-RDF
            //       part of the code.
            this.uri = DefinedRdfNames.NETINF_RDF_SCHEMA_URI + uri;
        } else {
            this.uri = uri;
        }
    }

    /**
     * Returns uri.
     * 
     * @return
     *      The current set URI.
     */
    public String getURI() {
        return uri;
    }

    /**
     * Gets the SAIL defined attribute identification by using the URI.
     * 
     * @param uri
     *      The URI to be checked.
     * @return
     *      SAIL defined attribute identification.
     */
    public static SailDefinedAttributeIdentification
            getDefinedAttributeIdentificationByURI(String uri) {
        SailDefinedAttributeIdentification result = null;

        // Iterate through SAIL identifiers
        for (SailDefinedAttributeIdentification definedAttributeIdentification :
                SailDefinedAttributeIdentification.values()) {
            // Check if it is present
            if (definedAttributeIdentification.getURI().equals(uri)) {
                // Found the attribute identification
                result = definedAttributeIdentification;
                break;
            }
        }

        return result;
    }

    /**
     * Returns the URI based on a defined attribute identification.
     * 
     * @param definedAttributeIdentification
     *      The attribute identification.
     * @return
     *      The URI.
     */
    public static String getURIByAttributeIdentification(SailDefinedAttributeIdentification
            definedAttributeIdentification) {
        return definedAttributeIdentification.getURI();
    }
}