package de.westlb.mgb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class SerializabilityTester
{
    private static Logger logger = Logger.getLogger(SerializabilityTester.class);
    
    public static void testSerializability(Object o)
    {
        try
        {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(o);
            new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            logger.info("Test-serializing object successful");
        }
        catch(Exception e0)
        {
            logger.error("Error test-serializing object: "+e0,e0);
        }

    }
    
}
