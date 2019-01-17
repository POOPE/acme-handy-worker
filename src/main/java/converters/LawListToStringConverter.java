
package converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Law;

@Transactional
@Component
public class LawListToStringConverter implements Converter<List<Law>, String> {

	@Override
	public String convert(List<Law> list) {
		String res = "";

		//		res = String.valueOf(law.getId());
		if (list == null || list.isEmpty()) {
			res = null;
		} else {
			Assert.isInstanceOf(Law.class, list.get(0), "Object is not of type domain.Law");
			for (int i = 0; i < list.size(); i++) {
				res = res + list.get(i).toString();
				if (i < list.size() - 1) {
					res = res + ",";
				}
			}
		}

		return res;
	}

}
