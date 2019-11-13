package de.westlb.mgb.struts_client.form.converter;

public interface ITextConverter {
	public boolean isValid(String text);
	public Object stringToObject(String string);
	public String objectToString(Object object);
}
