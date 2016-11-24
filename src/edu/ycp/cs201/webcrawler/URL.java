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
	private String URL, path, host, protocol;
	
	/**
	 * Constructor.
	 * 
	 * @param s the text form of a URL
	 */
	public URL(String s) {
		URL = s;
		int protoEnd = s.indexOf(':');
		protocol = (protoEnd >= 0) ? s.substring(0, protoEnd+1) : "";
		int hostStart = s.indexOf("//");
		if(hostStart == -1 && !protocol.equals(""))
		{
			throw new IllegalArgumentException("Improper URL format");
		}
		int hostEnd = s.indexOf('/', hostStart+2);
		host = (hostStart >= 0 && hostEnd >= 0) ? s.substring(hostStart+2, hostEnd) : "";
		path = (hostEnd >= 0 && hostStart >= 0) ? s.substring(hostEnd) : s;
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param other another {@link URL}: the object being initialized
	 *              should be made identical to this object
	 */
	public URL(URL other) {
		protocol = other.getProtocol();
		host = other.getHost();
		path = other.getPath();
		URL = protocol+"//"+host+path;
	}
	
	/**
	 * @return true if this {@link URL} is absolute, false otherwise
	 */
	public boolean isAbsolute() {
		return (path.charAt(0) == '/') ? true : false;
	}
	
	/**
	 * @return true if this {@link URL} names a directory, false otherwise
	 */
	public boolean isDirectory() {
		return (path.charAt(path.length()-1) == '/') ? true : false;
	}
	
	/**
	 * @return the protocol of this {@link URL}, e.g., "http:" for an HTTP URL;
	 *         returns an empty string if the URL doesn't specify a protocol
	 */
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * @return the hostname of this {@link URL}, e.g., "ycpcs.github.io";
	 *         returns an empty string if the URL doesn't specify a hostname
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * Get the path part of the {@link URL}.
	 * If the URL is absolute, the path should start with "/".
	 * If the URL is a directory, the path should end with "/".
	 * 
	 * @return the path part of the URL
	 */
	public String getPath() {
		return path;
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
		int firstSlash = path.indexOf('/');
		String directory = "";
		if(firstSlash >= 0)
		{
			int nextSlash = 1;
			int maxSlash = 0;
			while(nextSlash > 0)
			{
				nextSlash = path.indexOf('/', nextSlash);
				if(nextSlash != -1)
				{
					maxSlash = nextSlash;
				}
				nextSlash++;
			}
			directory = path.substring(0, maxSlash);
		}
		return directory;
	}
	
	/**
	 * @return true if the {@link URL}'s path is in canonical form, meaning
	 *         that there are no occurrences of "." or ".."; returns false
	 *         if the URL is not in canonical form
	 */
	public boolean isCanonical() {
		String directory = this.getDirectoryPart();
		return (directory.contains(".") || directory.contains("..")) ? false : true;
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
		String[] pathComp = path.split("/");
		Stack<String> canon = new Stack<String>();
		for(int i = 0; i < pathComp.length; i++)
		{
			if(!pathComp[i].equals("."))
			{
				if(pathComp[i].equals(".."))
				{
					if(canon.isEmpty())
					{
						throw new IllegalArgumentException("Cannot be made canonical");
					}
					else
					{
						canon.pop();
					}
				}
				else
				{
					canon.push(pathComp[i]);
				}
			}
		}
		pathComp = new String[canon.size()];
		int j = canon.size()-1;
		while(!canon.isEmpty())
		{
			pathComp[j] = canon.pop();
			j--;
		}
		String newPath = "";
		for(int i = 0; i < pathComp.length; i++)
		{
			newPath = newPath + pathComp[i];
			if(i != pathComp.length-1)
			{
				newPath = newPath + "/";
			}
		}
		URL newURL = new URL(protocol + ((host.length() > 0) ? "//" : "") + host + newPath);
		return newURL;
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
		URL newURL = null;
		if(refURL.isAbsolute())
		{
			newURL = new URL(this.protocol + "//" + this.host + refURL.getPath());
		}
		else
		{
			newURL = new URL(this.protocol + "//" + this.host + this.getDirectoryPart() + "/" + refURL.getPath());
		}
		newURL = newURL.makeCanonical();
		return newURL;
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
		return URL;
	}
	
	// TODO: you can add helper methods if you would like to
}
