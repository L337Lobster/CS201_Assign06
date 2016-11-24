package edu.ycp.cs201.webcrawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Class to represent a URL (an identifier which specifies
 * the location of a web resource.)
 */
public class URL implements Comparable<URL> {
	// TODO: add fields
	
	/**
	 * Constructor.
	 * 
	 * @param s the text form of a URL
	 */
	public URL(String s) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param other another {@link URL}: the object being initialized
	 *              should be made identical to this object
	 */
	public URL(URL other) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * @return true if this {@link URL} is absolute, false otherwise
	 */
	public boolean isAbsolute() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * @return true if this {@link URL} names a directory, false otherwise
	 */
	public boolean isDirectory() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * @return the protocol of this {@link URL}, e.g., "http:" for an HTTP URL;
	 *         returns an empty string if the URL doesn't specify a protocol
	 */
	public String getProtocol() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * @return the hostname of this {@link URL}, e.g., "ycpcs.github.io";
	 *         returns an empty string if the URL doesn't specify a hostname
	 */
	public String getHost() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Get the path part of the {@link URL}.
	 * If the URL is absolute, the path should start with "/".
	 * If the URL is a directory, the path should end with "/".
	 * 
	 * @return the path part of the URL
	 */
	public String getPath() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Get the directory part of this {@link URL}'s path, which is
	 * the text up to and including the last slash ("/")
	 * character in the path.  As a special case, if the
	 * path has no slash characters, return the empty string.
	 * 
	 * @return the directory part of this URL's path
	 */
	public String getDirectoryPart() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * @return true if the {@link URL}'s path is in canonical form, meaning
	 *         that there are no occurrences of "." or ".."; returns false
	 *         if the URL is not in canonical form
	 */
	public boolean isCanonical() {
		throw new UnsupportedOperationException("TODO - implement");
	}

	/**
	 * Return a {@link URL} that is the canonical form of this URL.
	 * If the URL is already in canonical form, returns it unchanged.
	 * 
	 * @throws IllegalArgumentException if the URL cannot be made
	 *         canonical because it references path components "below"
	 *         the implicit starting point of the URL: e.g., the
	 *         URL "foo/../../bar.html" cannot be made canonical
	 */
	public URL makeCanonical() {
		throw new UnsupportedOperationException("TODO - implement");
	}

	/**
	 * Return the absolute canonical form of a referenced URL.
	 * 
	 * <p>If the referenced URL has no protocol, and is absolute,
	 * return the canonical form of the URL that has the same
	 * protocol and host as this one, but the other
	 * URL's path.
	 * 
	 * <p>If the referenced URL has no protocol, and is relative,
	 * return the canonical form of the URL that has the same
	 * protocol and host as this one, but whose path is the
	 * result of appending the other URL's path to the directory
	 * part of this URL's path.
	 * 
	 * <p>If the referenced URL has a protocol, return its canonical
	 * form.
	 *  
	 * @param refURL a referenced {@link URL}
	 * @return the canonical form of the referenced 
	 * 
	 * @throws IllegalArgumentException if the referenced URL cannot
	 *         be made absolute and canonical
	 */
	public URL getReferencedURL(URL refURL) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	@Override
	public boolean equals(Object obj) {
		// Note: you will not need to modify this method
		if (obj == null || !(obj instanceof URL)) {
			return false;
		}
		return this.toString().equals(obj.toString());
	}
	
	@Override
	public int hashCode() {
		// Note: you will not need to modify this method
		return toString().hashCode();
	}
	
	@Override
	public int compareTo(URL o) {
		// Note: you will not need to modify this method
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	// TODO: you can add helper methods if you would like to
}
