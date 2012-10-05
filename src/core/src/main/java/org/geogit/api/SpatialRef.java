/* Copyright (c) 2011 TOPP - www.openplans.org. All rights reserved.
 * This code is licensed under the LGPL 2.1 license, available at the root
 * application directory.
 */
package org.geogit.api;

import org.geogit.api.RevObject.TYPE;
import org.opengis.geometry.BoundingBox;

public class SpatialRef extends NodeRef {

    private BoundingBox bounds;

    public SpatialRef(String path, ObjectId oid, ObjectId metadataId, TYPE type, BoundingBox bounds) {
        super(path, oid, metadataId, type);
        this.bounds = bounds;
    }

    public BoundingBox getBounds() {
        return bounds;
    }
}