
package utilities;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {

	private static final char	PKG_SEPARATOR		= '.';

	private static final char	DIR_SEPARATOR		= '/';

	private static final String	CLASS_FILE_SUFFIX	= ".class";

	private static final String	BAD_PACKAGE_ERROR	= "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";


	public static Class<?> findByName(final String s) {
		Class<?> res = null;
		try {
			res = Class.forName(s);
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static List<Class<?>> find(final String scannedPackage) {
		final String scannedPath = scannedPackage.replace(ClassFinder.PKG_SEPARATOR, ClassFinder.DIR_SEPARATOR);
		final URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
		if (scannedUrl == null)
			throw new IllegalArgumentException(String.format(ClassFinder.BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
		String decodedUrl = "";
		//decode URL
		try {
			decodedUrl = URLDecoder.decode(scannedUrl.toString(), "UTF-8");
			decodedUrl = decodedUrl.replace("file:", "");
		} catch (final UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final File scannedDir = new File(decodedUrl);
		//fill with classes
		final List<Class<?>> classes = new ArrayList<Class<?>>();

		final File[] files = scannedDir.listFiles();
		if (files != null)
			for (final File file : scannedDir.listFiles())
				if (!file.getName().startsWith("domain.DomainEntity"))
					classes.addAll(ClassFinder.find(file, scannedPackage));
		return classes;
	}

	private static List<Class<?>> find(final File file, final String scannedPackage) {
		final List<Class<?>> classes = new ArrayList<Class<?>>();
		final String resource = scannedPackage + ClassFinder.PKG_SEPARATOR + file.getName();
		if (file.isDirectory())
			for (final File child : file.listFiles())
				classes.addAll(ClassFinder.find(child, resource));
		else if (resource.endsWith(ClassFinder.CLASS_FILE_SUFFIX)) {
			final int endIndex = resource.length() - ClassFinder.CLASS_FILE_SUFFIX.length();
			final String className = resource.substring(0, endIndex);
			if (className != "domain.DomainEntity")
				try {
					classes.add(Class.forName(className));
				} catch (final ClassNotFoundException ignore) {
				}
		}
		return classes;
	}

}
