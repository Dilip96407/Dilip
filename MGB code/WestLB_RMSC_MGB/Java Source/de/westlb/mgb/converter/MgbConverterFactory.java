package de.westlb.mgb.converter;

import java.lang.reflect.Constructor;
import java.util.Calendar;

import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConverterFactory {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Session sess;
			SourceSystemImpl sourceSystem = null;
			try {
				sess = StoreSingleton.getUniqueInstance().openBatchSession();
				sourceSystem = (SourceSystemImpl) sess.select(SourceSystemImpl.class, args[0]);
				sess.close();
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sourceSystem != null) {
				MgbConverter converter = getMgbConverter(sourceSystem);
				if (converter != null) {
				    converter.setLoaderStatistic(Calendar.getInstance(), Calendar.getInstance(), 0);
					long jobId = converter.processData();
					System.out.println("JobId = " + jobId);
				} else {
					usage();
				}
			}
			else
			{
				usage();
			}
		} else {
			usage();
		}
	}

	public static MgbConverter getMgbConverter(SourceSystemImpl sourcesystemImpl) 
	{
		MgbConverter converter = null;
		try
		{
			Class<?> converterClass = Class.forName("de.westlb.mgb.converter."+sourcesystemImpl.getConverter());
			Constructor<?> constructor = converterClass.getConstructor(SourceSystemImpl.class);
				converter = (MgbConverter) constructor.newInstance(sourcesystemImpl);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unknown converter '"+sourcesystemImpl.getConverter()+"' for source system "+sourcesystemImpl.getCode()+" to create a matching converter");
		}
		return converter;
	}

	private static void usage() {
		System.out.println();
		System.out.println("Usage: MgbConverterFactory <SourceSystemCode>");
		System.out.println();
	}
}
