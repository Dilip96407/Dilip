package de.westlb.mgb.converter;

import java.io.File;
import java.io.FilenameFilter;
import java.text.MessageFormat;

import org.apache.log4j.Logger;


/**
 * A filename filter that accepts a wildcard pattern in the format understood
 * by {@link MessageFormat}. Patterns that cannot be parsed will fall back
 * to a verbatim match.
 */
public class MessageFormatFilenameFilter
implements FilenameFilter
{
    private static final Logger logger = Logger.getLogger(MessageFormatFilenameFilter.class);
    
    private MessageFormat mf;
    private String pattern;
    
    /**
     * Constructs an instance that will use <code>pattern</code> in a 
     * {@link MessageFormat} to parse filenames. Note that only the filename
     * will be matched, not the directory name.
     */
    public MessageFormatFilenameFilter(String pattern)
    {
        if(pattern==null) throw new IllegalArgumentException("pattern must not "+
                "be null");
        this.pattern=pattern;
        try
        {
            this.mf = new MessageFormat(pattern);
        }
        catch(IllegalArgumentException e0)
        {
            logger.warn("Invalid pattern "+pattern+" for MessageFormat, will"+
                    " match verbatim filename");
        }
    }

    @Override
    public boolean accept(File dir,String name)
    {
        if(this.mf==null) return pattern.equals(name);
        try
        {
            mf.parse(name);
            return true;
        }
        catch(java.text.ParseException e0)
        {
            return false;
        }
    }
    
    public String toString()
    {
        return pattern;
    }
}
