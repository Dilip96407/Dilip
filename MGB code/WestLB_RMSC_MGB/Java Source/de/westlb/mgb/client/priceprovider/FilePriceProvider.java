package de.westlb.mgb.client.priceprovider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.StringTokenizer;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.definition.PriceDef;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FilePriceProvider implements PriceProvider {
	
	private static final DateFormat datetimeformat = new DateFormat(DateFormat.DATETIME_FORMAT);

	private String path = "c:/rmsc/";

	@SuppressWarnings("unused")
    private FilePriceProvider() {
	}

	public FilePriceProvider(String path) {
		this.path = path;
	}

	/**
	 * @see de.westlb.mgb.client.priceprovider.PriceProvider#getPrice(RequestVo)
	 */
	@Override
    public PriceVo getPrice(RequestVo request) throws RemoteException {
		PriceVo price = null;
		try {
			File file = new File(path, getFileName(request));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while( (line = reader.readLine()) != null ) {
				if (line.startsWith(request.getRequestId() + " ") ) {
					price = readAsciiPrice(line);
					break;
				}
			}
			reader.close();
		} catch (Exception e) {
			throw new RemoteException("Unable to get price", e);
		}
		return price;
	}

	/**
	 * @see de.westlb.mgb.client.priceprovider.PriceProvider#savePrice(RequestVo, PriceVo)
	 */
	@Override
    public void savePrice(RequestVo request, PriceVo price) throws RemoteException {
		try {
			File file = new File(path, getFileName(request));
			FileWriter writer = new FileWriter(file, true);
			writer.write(writeAsciiPrice(price));
			writer.write('\n');
			writer.close();
		} catch (IOException e) {
			throw new RemoteException("Unable to save price", e);
		}
	}

	/**
	 * @see de.westlb.mgb.client.priceprovider.PriceProvider#savePrices(RequestVo[], PriceVo[])
	 */
	@Override
    public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException {
		try {
			String fileName = getFileName(requests[0]);
			FileWriter writer = new FileWriter(new File(path, fileName), true);
			for (int i = 0; i < prices.length; i++) {
				if ( !fileName.equals(getFileName(requests[i])) ) {
					writer.close();
					writer = new FileWriter(new File(path, getFileName(requests[i])), true);
				}
				if (prices[i] != null) {
					writer.write(writeAsciiPrice(prices[i]));
					writer.write('\n');
				}
			}
			writer.close();
		} catch (IOException e) {
			throw new RemoteException("Unable to save price", e);
		}
		
	}

	private String getFileName(RequestVo request) {
		return request.getJobId() + "_" + request.getPriceSource() + ".txt";
	}

	private String writeAsciiPrice(PriceVo price) {
		return price.getRequestId() + " " +datetimeformat.format(price.getPriceDate())+ " " + price.getMinPrice() + " " + price.getMaxPrice();
	}

	private PriceVo readAsciiPrice(String priceString) throws ParseException {
		PriceVo price = new PriceVo();
		StringTokenizer tokenizer = new StringTokenizer(priceString);
		if (tokenizer.countTokens() > 2) {
			price.setRequestId(Long.valueOf(Long.parseLong(tokenizer.nextToken())));
			price.setPriceDate(datetimeformat.parse(tokenizer.nextToken()));
			price.setMinPrice(Double.parseDouble(tokenizer.nextToken()));
		}
		if (tokenizer.hasMoreTokens()) {
			price.setMaxPrice(Double.parseDouble(tokenizer.nextToken()));
			price.setPriceType(PriceDef.INTERVAL);
		} else {
			price.setMaxPrice(price.getMinPrice());			
			price.setPriceType(PriceDef.SINGLE);
		}
		if (price.getMinPrice() == 0) {
			price.setMidPrice(price.getMaxPrice());
		} else if (price.getMaxPrice() == 0) {
			price.setMidPrice(price.getMinPrice());
		} else {
			price.setMidPrice((price.getMinPrice()+price.getMaxPrice())/2.0);				
		}
		return price;
	}

	@Override
    public boolean isAvailable() {
		return path != null && new File(path).exists();
	}

}