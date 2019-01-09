
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.CustomerService;
import domain.Customer;

@Transactional
@Component
public class StringToCustomerConverter implements Converter<String, Customer> {

	@Autowired
	private CustomerService	customerService;


	@Override
	public Customer convert(String text) {
		Customer res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.customerService.findOne(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
