
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.WarrantyService;
import domain.Warranty;

@Transactional
@Component
public class StringToWarrantyConverter implements Converter<String, Warranty> {

	@Autowired
	private WarrantyService	warrantyService;


	@Override
	public Warranty convert(String text) {
		Warranty res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.warrantyService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
