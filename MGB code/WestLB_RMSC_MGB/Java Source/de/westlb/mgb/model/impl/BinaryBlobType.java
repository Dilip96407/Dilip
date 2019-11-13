package de.westlb.mgb.model.impl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 *	@deprecated 
 *  @modelguid {90A8A27E-8635-42D0-8934-BA34CB4DB8B9} 
 * 
 */
public class BinaryBlobType implements UserType {
	/** @modelguid {64E4ACF5-A359-4501-BA38-D1CF6632CAE9} */
	@Override
    public int[] sqlTypes() {
		return new int[] { Types.BLOB };
	}

	/** @modelguid {5900084D-9ED9-4C02-94FB-9780B69B34DC} */
	@Override
    public Class<byte[]> returnedClass() {
		return byte[].class;
	}

	/** @modelguid {1AAAC9C1-B4E4-41D0-9B5D-40B0A9117881} */
	@Override
    public boolean equals(Object x, Object y) {
		return (x == y)
			|| (x != null
				&& y != null
				&& java.util.Arrays.equals((byte[]) x, (byte[]) y));
	}

	/** @modelguid {1C89BAA7-FB87-47F2-A80C-D3039FDA9F0F} */
	@Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
		throws HibernateException, SQLException {
		Blob blob = rs.getBlob(names[0]);
		return blob.getBytes(1, (int) blob.length());
	}

	/** @modelguid {6471B9B8-08A8-45CE-8A3F-472B515915EC} */
	@Override
    public void nullSafeSet(PreparedStatement st, Object value, int index)
		throws HibernateException, SQLException {
		st.setBytes(index, (byte[]) value);
	}

	/** @modelguid {886FF5E9-F368-411B-BAB5-42B8B987AE88} */
	@Override
    public Object deepCopy(Object value) {
		if (value == null)
			return null;

		byte[] bytes = (byte[]) value;
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 0, result, 0, bytes.length);

		return result;
	}

	/** @modelguid {3F7A86CF-CFE6-42C8-AEE7-260B4B1B9814} */
	@Override
    public boolean isMutable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
    public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
	 */
	@Override
    public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	@Override
    public Serializable disassemble(Object arg0) throws HibernateException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	@Override
    public int hashCode(Object arg0) throws HibernateException {
		return 0;
	}
}