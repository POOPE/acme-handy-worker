
package converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Warranty;
import services.WarrantyService;

@Transactional
@Component
public class WarrantyListToStringConverter implements Converter<List<Warranty>, String> {

	@Autowired
	private WarrantyService warrantyService;


	@Override
	public String convert(List<Warranty> list) {
		String res = "";

		if (list == null || list.isEmpty()) {
			res = null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				res = res + list.get(i).getId();
				if (i < list.size() - 1) {
					res = res + ",";
				}
			}
		}

		return res;
	}

}
