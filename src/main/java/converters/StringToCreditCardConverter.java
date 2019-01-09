
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.CreditCardService;
import domain.CreditCard;

@Transactional
@Component
public class StringToCreditCardConverter implements Converter<String, CreditCard> {

	@Autowired
	private CreditCardService	creditCardService;


	@Override
	public CreditCard convert(String text) {
		CreditCard res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.creditCardService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
