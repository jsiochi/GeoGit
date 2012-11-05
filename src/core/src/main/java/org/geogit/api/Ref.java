/* Copyright (c) 2011 TOPP - www.openplans.org. All rights reserved.
 * This code is licensed under the LGPL 2.1 license, available at the root
 * application directory.
 */
package org.geogit.api;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * Pairing of a name and the {@link ObjectId} it currently has.
 * <p>
 * A ref in Git is (more or less) a variable that holds a single object identifier. The object
 * identifier can be any valid Git object (blob, tree, commit, annotated tag, ...).
 * <p>
 * The ref name has the attributes of the ref that was asked for as well as the ref it was resolved
 * to for symbolic refs plus the object id it points to and (for tags) the peeled target object id,
 * i.e. the tag resolved recursively until a non-tag object is referenced.
 */
public class Ref implements Comparable<Ref> {

    /**
     * By convention, name of the main branch
     */
    public static final String MASTER = "refs/heads/master";

    /**
     * Pointer to the latest commit in the current branch
     */
    public static final String HEAD = "HEAD";

    /**
     * Pointer to the current tree in the staging index
     */
    public static final String STAGE_HEAD = "STAGE_HEAD";

    /**
     * Pointer to the current tree in the working directory
     */
    public static final String WORK_HEAD = "WORK_HEAD";

    /**
     * Directory prefix for refs.
     */
    public static final String REFS_PREFIX = "refs/";

    /**
     * Directory prefix for remotes.
     */
    public static final String REMOTES_PREFIX = "remotes/";

    /**
     * Directory prefix for tags.
     */
    public static final String TAGS_PREFIX = REFS_PREFIX + "tags/";

    /**
     * Directory prefix for heads.
     */
    public static final String HEADS_PREFIX = REFS_PREFIX + "heads/";

    /**
     * By convention, the origin of the repository
     */
    public static final String ORIGIN = "refs/remotes/origin";

    private String name;

    private RevObject.TYPE type;

    private ObjectId objectId;

    /**
     * Constructs a new Ref with the given parameters.
     * 
     * @param name name of this ref
     * @param oid object id for this ref
     * @param type object type
     * @see RevObject.TYPE
     */
    public Ref(final String name, final ObjectId oid, @Nullable final RevObject.TYPE type) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(oid);
        Preconditions.checkArgument(oid.isNull() || type != null);
        this.name = name;
        this.objectId = oid;
        this.type = type;
    }

    /**
     * @return the name for this ref
     */
    public String getName() {
        return name;
    }

    /**
     * @return the object id being referenced
     */
    public ObjectId getObjectId() {
        return objectId;
    }

    /**
     * @return the type of the object this ref refers to
     * @see RevObject.TYPE
     */
    public RevObject.TYPE getType() {
        return type;
    }

    /**
     * @param o object to compare against
     * @return whether or not this ref is equal to the target object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ref)) {
            return false;
        }
        Ref r = (Ref) o;
        return name.equals(r.getName()) && type.equals(r.getType())
                && objectId.equals(r.getObjectId());
    }

    /**
     * @return a hash code for this ref
     */
    @Override
    public int hashCode() {
        return name.hashCode() * objectId.hashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Ref o) {
        return name.compareTo(o.getName());
    }

    /**
     * @return the ref represented by a readable string
     */
    @Override
    public String toString() {
        return new StringBuilder("Ref").append('[').append(name).append(" -> ").append(objectId)
                .append(']').toString();
    }
}